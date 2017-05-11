package com.biz.soa.product.service.backend;

import com.alibaba.fastjson.JSON;
import com.biz.core.page.PageResult;
import com.biz.gbck.dao.mysql.po.product.bbc.GeoProduct;
import com.biz.gbck.dao.mysql.po.product.bbc.Product;
import com.biz.gbck.dao.mysql.repository.bbc.product.GeoProductRepository;
import com.biz.gbck.dao.mysql.repository.bbc.product.ProductAuditActionLogRepository;
import com.biz.gbck.dao.mysql.repository.bbc.product.ProductAuditRepository;
import com.biz.gbck.dao.mysql.repository.bbc.product.ProductRepository;
import com.biz.gbck.dao.mysql.repository.brand.BrandRepository;
import com.biz.gbck.dao.mysql.repository.category.CategoryRepository;
import com.biz.gbck.dao.mysql.repository.extendProperty.ExtendPropertyRepository;
import com.biz.gbck.dao.mysql.repository.saleTag.SaleTagRepository;
import com.biz.gbck.dao.redis.repository.product.bbc.PriceRedisDao;
import com.biz.gbck.dao.redis.repository.product.bbc.ProductRedisDao;
import com.biz.gbck.transform.product.GeoProduct2ProductDetailVo;
import com.biz.gbck.vo.product.backend.*;
import com.biz.service.AbstractBaseService;
import com.biz.service.depot.DepotService;
import com.biz.service.product.backend.PlatformProductService;
import com.google.common.collect.Lists;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * 商品 Service (平台后台用)
 *
 * @author david-liu
 * @date 2016年12月25日
 * @reviewer
 * @see
 */
//@Service
public class PlatformProductServiceImpl extends AbstractBaseService implements PlatformProductService, Serializable {

    private static final long serialVersionUID = 6067042018498593447L;

    @Autowired
    private ProductAuditRepository productAuditRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductAuditActionLogRepository productAuditActionLogRepository;

    @Autowired
    private GeoProductRepository geoProductRepository;

    @Autowired
    private SaleTagRepository saleTagRepository;

    @Autowired
    private ExtendPropertyRepository extendPropertyRepository;

    @Autowired
    private ProductRedisDao productRedisDao;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private DepotService depotService;

    @Autowired
    private PriceRedisDao priceRedisDao;

    @Override
    public List<IdNameVo> findProductByIds(List<Long> ids) {
        List<Product> list = productRepository.findByIdAndDeleteFlag(ids, Boolean.FALSE);
        return productList2IdNameList(list);
    }

    private List<IdNameVo> productList2IdNameList(List<Product> productList) {
        List<IdNameVo> resultList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(productList)) {
            for (Product index : productList) {
                resultList.add(new IdNameVo(String.valueOf(index.getId()), index.getName()));
            }
        }
        return resultList;
    }

    @Override
    public PageResult<IdNameVo> findProductByKeyValue(SearchPageVo searchPageVo) {
        PageRequest pageRequest = new PageRequest(searchPageVo.getPageIndex(), searchPageVo.getPageSize());
        Page<Product> pageProduct = null;
        if (Objects.equals(searchPageVo.getSearchKey(), "sku")) {
            pageProduct = productRepository.findByProductCodeLikeAndDeleteFlag(searchPageVo.getSearchValue(), Boolean.FALSE, pageRequest);
        } else if (Objects.equals(searchPageVo.getSearchKey(), "name")) {
            pageProduct = productRepository.findByNameLikeAndDeleteFlag(searchPageVo.getSearchValue(), Boolean.FALSE, pageRequest);
        }
        List<IdNameVo> resultList = productList2IdNameList(pageProduct.getContent());
        return new PageResult<>(searchPageVo.getPageIndex(), searchPageVo.getPageSize(), (int) pageProduct.getTotalElements(), resultList);
    }

    @Override
    public Long getProductIdByProductCode(String productCode) {
        return productRepository.findProductIdByProductCode(productCode);
    }

    @Override
    public List<Long> findProductIdByName(String name) {
        return productRepository.findProductIdByNameLike("%" + name + "%");
    }

    @Override
    public ProductDetailVo getProductDetailVo(String productCode) {
        GeoProduct geoProduct = geoProductRepository.findByProductProductCode(productCode);
        if (geoProduct == null) {
            return new ProductDetailVo();
        }
        return new GeoProduct2ProductDetailVo().apply(geoProduct);
    }

    @Override
    public BootstrapTablePageResult<TypeBBlackProductItemVo> listTypeBBlackList() {
        List<String> blackListProductCodes = productRedisDao.getTypeBProductSaleBlackList();
        List<Product> selectProduct = productRepository.findByProductCodeIn(blackListProductCodes);
        List<TypeBBlackProductItemVo> lists = Lists.newArrayList();
        for (Product product : selectProduct) {
            TypeBBlackProductItemVo tmp = new TypeBBlackProductItemVo(product.getName(), product.getProductCode());
            lists.add(tmp);
        }
        BootstrapTablePageResult result = new BootstrapTablePageResult<TypeBBlackProductItemVo>();
        result.setRows(lists);
        result.setTotal(lists.size());
        return result;
    }

    @Override
    public void appendTypeBBlackList(String productCode) {
        Product product = productRepository.findByProductCode(productCode);
        if (product != null) {
            productRedisDao.append2TypeBProductBlackList(productCode);
        } else {
            throw new RuntimeException("商品编码不在product中 请输入正确的商品编码");
        }
    }

    @Override
    public void removeFromTypeBBlackList(String productCode) {
        productRedisDao.removeFromTypeBProductBlackList(productCode);
    }

    private void setGroupProductPrice(String productCode, String price) {
        List<String> allAreaNo = depotService.listAllAreaNo();
        logger.debug("find all areaNo {}", JSON.toJSONString(allAreaNo));
        List<String> priceKey = Lists.newArrayList();
        for (String areaNo : allAreaNo) {
            priceKey.add(priceRedisDao.getPriceRoId(productCode, areaNo));
        }

        priceRedisDao.updateGroupProductPrice(productCode, priceKey, Integer.valueOf(price), allAreaNo);
    }

}
