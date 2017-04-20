package com.biz.soa.product.service.backend;

import com.alibaba.fastjson.JSON;
import com.biz.core.page.PageResult;
import com.biz.core.util.MoneyConverter;
import com.biz.gbck.dao.mysql.po.product.*;
import com.biz.gbck.dao.mysql.po.product.bbc.GeoProduct;
import com.biz.gbck.dao.mysql.po.product.bbc.Product;
import com.biz.gbck.dao.mysql.po.product.bbc.ProductAudit;
import com.biz.gbck.dao.mysql.po.product.bbc.ProductAuditActionLog;
import com.biz.gbck.dao.mysql.repository.brand.BrandRepository;
import com.biz.gbck.dao.mysql.repository.category.CategoryRepository;
import com.biz.gbck.dao.mysql.repository.extendProperty.ExtendPropertyRepository;
import com.biz.gbck.dao.mysql.repository.product.GeoProductRepository;
import com.biz.gbck.dao.mysql.repository.product.ProductAuditActionLogRepository;
import com.biz.gbck.dao.mysql.repository.product.ProductAuditRepository;
import com.biz.gbck.dao.mysql.repository.product.ProductRepository;
import com.biz.gbck.dao.mysql.repository.saleTag.SaleTagRepository;
import com.biz.gbck.dao.mysql.repository.bbc.vendor.VendorRepository;
import com.biz.gbck.dao.mysql.specification.bbc.ProductAuditSpecification;
import com.biz.gbck.dao.mysql.specification.bbc.TypeBProductSpecification;
import com.biz.gbck.dao.redis.repository.product.bbc.PriceRedisDao;
import com.biz.gbck.dao.redis.repository.product.ProductRedisDao;
import com.biz.gbck.dao.redis.ro.product.PriceRo;
import com.biz.gbck.enums.product.ProductAuditStatusEnum;
import com.biz.gbck.enums.product.SaleStatusEnum;
import com.biz.gbck.exceptions.product.*;
import com.biz.gbck.transform.product.*;
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
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品 Service (平台后台用)
 *
 * @author david-liu
 * @date 2016年12月25日
 * @reviewer
 * @see
 */
@Service
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
    private VendorRepository vendorRepository;

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
    public PageResult<PlatformProductAuditListItemVo> listPlatformAuditProducts(PlatformProductAuditListReqVo reqVo) throws IllegalParameterException {
        SearchPageVo searchPageVo = reqVo.getSearchPageVo();
        if (searchPageVo == null) {
            throw new IllegalParameterException("分页对象不能为空");
        }
        PageRequest pageRequest = new PageRequest(searchPageVo.getPageIndex() - 1, searchPageVo.getPageSize());
        Page<ProductAudit> page = productAuditRepository.findAll(new ProductAuditSpecification(reqVo), pageRequest);
        if (page == null || CollectionUtils.isEmpty(page.getContent())) {
            return new PageResult<>(searchPageVo.getPageIndex(), 0, 0, Lists.<PlatformProductAuditListItemVo>newArrayList());
        }
        List<PlatformProductAuditListItemVo> itemVos = Lists.transform(page.getContent(), new ProductAudit2PlatformProductAuditListItemVo(vendorRepository));
        return new PageResult<>(searchPageVo.getPageIndex(), itemVos.size(), (int) page.getTotalElements(), itemVos);
    }

    @Override
    @Transactional
    public Boolean toggleProductLock(Long vendorId, Long productId, Boolean locked) throws IllegalParameterException, ProductNotFoundException {

        if (vendorId == null || productId == null || locked == null) {
            throw new IllegalParameterException("vendorId, productId, locked 不能为空");
        }

        if (logger.isDebugEnabled()) {
            logger.debug("param vendorId: {}, productId: {}, locked: {}", vendorId, productId, locked);
        }

        Product product = productRepository.findByIdAndVendorIdAndDeleteFlag(productId, vendorId, Boolean.FALSE);
        if (product == null) {
            throw new ProductNotFoundException("该商品不存在或者已被删除");
        }

        if (logger.isDebugEnabled()) {
            logger.debug("po before lock toggle: {}", product);
        }
        product.setLocked(locked);
        product = productRepository.save(product);

        if (logger.isDebugEnabled()) {
            logger.debug("po after toggle lock: {}", product);
        }

        return Boolean.TRUE;
    }

    @Override
    public PlatformProductAuditDetailVo getVendorProductAudit(Long productAuditId) throws IllegalParameterException, ProductAuditNotFoundException {
        if (productAuditId == null) {
            throw new IllegalParameterException("productAuditId不能为空");
        }
        ProductAudit productAudit = productAuditRepository.findByIdAndDeleteFlag(productAuditId, Boolean.FALSE);
        if (productAudit == null) {
            throw new ProductAuditNotFoundException("商品审核记录不存在或者已被删除");
        }
        PlatformProductAuditDetailVo vo = new ProductAudit2PlatformProductAuditDetailVo().apply(productAudit);
        if (vendorRepository.findOne(productAudit.getVendorId()) != null) {
            vo.setVendorName(vendorRepository.findOne(productAudit.getVendorId()).getVendorName());
        }
        vo.setProperties(Lists.transform(productAudit.getProperties(), new ExtendProperty2ExtendStringVo()));
        logger.debug("商品审核详情 {}", JSON.toJSONString(vo));
        return vo;
    }

    @Override
    @Transactional
    public Boolean auditVendorProduct(PlatformAuditReqVo reqVo)
            throws IllegalParameterException,
            ProductAuditNotFoundException,
            ProductLockedException, ProductAuditLogNotFoundException, GeoProductNotFoundException, ProductSaleStatusException {
        logger.debug("开始审核商品 [{}]", reqVo.toString());

        logger.debug("验证输入数据");
        if (reqVo.getProductAuditId() == null || reqVo.getAuditStatus() == null) {
            throw new IllegalParameterException("productAuditId或AuditStatus不能为空");
        }
        ProductAudit productAudit = productAuditRepository.findByIdAndDeleteFlag(reqVo.getProductAuditId(), Boolean.FALSE);
        if (productAudit == null) {
            throw new ProductAuditNotFoundException("商品审核记录不存在或者已被删除");
        } else if (productAudit.getAuditStatus() == ProductAuditStatusEnum.PASS) {
            throw new IllegalParameterException("商品已经审核通过，不能重复审核");
        } else if (productAudit.getAuditStatus() == ProductAuditStatusEnum.INIT_DENY || productAudit.getAuditStatus() == ProductAuditStatusEnum.MODIFY_DENY) {
            throw new IllegalParameterException("审核未通过，不能再次审核");
        }

        ProductAuditActionLog log = new ProductAuditActionLog();
        Long productId = productAudit.getProductId();
        Product product = null;
        if (productId != null) {
            product = productRepository.findOne(productId);
            if (product != null) {
                if (product.getLocked()) {
                    // 商品主数据存在, 但是已被锁定, 不能审核
                    throw new ProductLockedException("该商品已被锁定, 不能被审核");
                } else {
                    log.setBeforeAuditInfo(JSON.toJSONString(new Product2ProductRo().apply(product)));
                    logger.debug("记录审核前商品状态 {}", JSON.toJSONString(log));
                    log.setBeforeAuditStatus(productAudit.getAuditStatus());
                }
            }
        } else {
            throw new IllegalParameterException("productAudit 中商品ID为空");
        }

        if (logger.isDebugEnabled()) {
            logger.debug("po before audit: {}", productAudit);
        }

        if (reqVo.getAuditStatus() == ProductAuditStatusEnum.PASS) {
            logger.debug("审核通过");
            productAudit.setAuditStatus(ProductAuditStatusEnum.PASS);
        } else if (reqVo.getAuditStatus() == ProductAuditStatusEnum.INIT_DENY) {
            logger.debug("审核不通过");
            if (productAudit.getAuditStatus() == ProductAuditStatusEnum.INIT) {
                productAudit.setAuditStatus(ProductAuditStatusEnum.INIT_DENY);
            } else if (productAudit.getAuditStatus() == ProductAuditStatusEnum.MODIFIED) {
                productAudit.setAuditStatus(ProductAuditStatusEnum.MODIFY_DENY);
            }
        }

        productAudit.setAuditMessage(reqVo.getAuditMessage());
        productAudit.setDeleteFlag(Boolean.FALSE);
        productAudit = productAuditRepository.save(productAudit);

        if (logger.isDebugEnabled()) {
            logger.debug("po after save: {}", productAudit);
        }

        if (reqVo.getAuditStatus() == ProductAuditStatusEnum.PASS) {
            logger.info("审核状态通过");
            List<SaleTag> saleTags;
            List<ExtendProperty> properties;
            List<Long> saleTagIds = Lists.newArrayList();
            List<Long> propertyIds = Lists.newArrayList();
            if (CollectionUtils.isNotEmpty(productAudit.getSaleTags())) {
                for (SaleTag saleTag : productAudit.getSaleTags()) {
                    saleTagIds.add(saleTag.getId());
                }
                saleTags = saleTagRepository.findAll(saleTagIds);
            } else {
                saleTags = Lists.newArrayList();
            }

            if (CollectionUtils.isNotEmpty(productAudit.getProperties())) {
                for (ExtendProperty property : productAudit.getProperties()) {
                    propertyIds.add(property.getId());
                }
                properties = extendPropertyRepository.findAll(propertyIds);
            } else {
                properties = Lists.newArrayList();
            }

            if (product == null) {
                logger.info("新建商品数据");
                product = new Product();
                product.setId(productAudit.getProductId());
            } else {
                logger.info("更新商品数据");
            }
            product.setVendorId(productAudit.getVendorId());
            product.setName(productAudit.getName());
            product.setSubTitle(productAudit.getSubTitle());
            product.setProductCode(productAudit.getProductCode());
            product.setVendorProductCode(productAudit.getVendorProductCode());
            product.setI18nCode(productAudit.getI18nCode());
            product.setBreif(productAudit.getBreif());
            product.setBrand(productAudit.getBrand());
            product.setProductType(productAudit.getProductType());
            product.setCategory(productAudit.getCategory());
            product.setProperties(properties);
            product.setIntroImages(productAudit.getIntroImages());
            product.setLogo(productAudit.getLogo());
            product.setImages(productAudit.getImages());
            product.setSaleTags(saleTags);
            product.setSeoTitle(productAudit.getSeoTitle());
            product.setSeoKeywords(productAudit.getSeoKeywords());
            product.setSeoDescription(productAudit.getSeoDescription());
            product.setInAudit(Boolean.FALSE);
            product.setWeight(productAudit.getWeight());

            if (logger.isDebugEnabled()) {
                logger.debug("product po before save: {}", JSON.toJSONString(product));
            }

            product = productRepository.save(product);

            log.setAfterAuditStatus(productAudit.getAuditStatus());
            log.setAfterAuditInfo(JSON.toJSONString(new Product2ProductRo().apply(product)));
        } else {
            logger.debug("审核状态不通过");
            log.setAfterAuditStatus(productAudit.getAuditStatus());
            if (product == null) {
                log.setAfterAuditInfo("");
            } else {
                log.setAfterAuditInfo(JSON.toJSONString(new Product2ProductRo().apply(product)));
            }
        }

        log.setAuditorName(reqVo.getAdmin());
        log.setProductId(productId);
        productAuditActionLogRepository.save(log);
        logger.debug("审核日志为 [{}]", JSON.toJSONString(log));
        if (productAudit.getAuditStatus() == ProductAuditStatusEnum.PASS) {
            logger.debug("审核通过,发送事件 创建geo信息");
        }
        return Boolean.TRUE;
    }

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
    public PageResult<TypeBProductListItemVo> listTypeBProductItemVo(TypeProductListReqVo reqVo) {
        PageRequest pageRequest = new PageRequest(reqVo.getSearchPageVo().getPageIndex() - 1, reqVo.getSearchPageVo().getPageSize());
        Page<Product> page = productRepository.findAll(new TypeBProductSpecification(reqVo), pageRequest);
        List<TypeBProductListItemVo> itemVos = Lists.transform(page.getContent(), new Product2TypeBProductListItemVo());
        return new PageResult<>(reqVo.getSearchPageVo().getPageIndex(), itemVos.size(), (int) page.getTotalElements(), itemVos);
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

    @Override
    public void setTypeBProductSaleStauts(final Long productId, final SaleStatusEnum saleStatus) {
        if (productId != null && saleStatus != null) {
            logger.debug("setTypeBProductSaleStauts reqVo productId:{} ,saleStatus:{}", productId, saleStatus.getDescription());
        } else {
            throw new RuntimeException("上下架参数异常");
        }
        final Product product = productRepository.findOne(productId);
        if (product == null) {
            throw new RuntimeException("该商品不存在");
        }
        if (Objects.equals(saleStatus, SaleStatusEnum.ON_SALE)) {
            logger.debug("修改上架状态为 上架");
            product.setTypeBSaleStatus(SaleStatusEnum.ON_SALE);
        } else {
            logger.debug("修改上架状态为 下架");
            product.setTypeBSaleStatus(SaleStatusEnum.OFF_SALE);
        }
        productRepository.save(product);
    }

    @Override
    public PlatformTypeBProductDetailVo viewTypeBProductDetailVo(Long productId) {
        Product product = productRepository.findOne(productId);
        if (product == null) {
            throw new RuntimeException("商品Po不存在");
        }
        PlatformTypeBProductDetailVo detailVo = new PlatformTypeBProductDetailVo();
        detailVo.setProductName(product.getName());
        if (product.getBrand() != null) {
            detailVo.setBrandId(String.valueOf(product.getBrand().getId()));
        }
        detailVo.setBreif(product.getBreif());
        if (product.getCategory() != null) {
            detailVo.setCategoryId(String.valueOf(product.getCategory().getId()));
        }
        detailVo.setI18nCode(product.getI18nCode());
        detailVo.setId(String.valueOf(product.getId()));
        detailVo.setProductCode(product.getProductCode());
        detailVo.setSeoDescription(product.getSeoDescription());
        detailVo.setSeoKeywords(product.getSeoKeywords());
        detailVo.setSeoTitle(product.getSeoTitle());
        detailVo.setSubTitle(product.getSubTitle());
        detailVo.setVendorId(String.valueOf(product.getVendorId()));
        detailVo.setLogo(product.getLogo());
        detailVo.setIntroImages(product.getIntroImages());
        detailVo.setImages(product.getImages());
        List<ExtendProperty> properties = product.getProperties();
        List<ExtendVo> extendVos = Lists.newArrayList();
        for (ExtendProperty property : properties) {
            ExtendVo extendVo = new ExtendVo();
            if (property.getProductExtend().getId() != null) {
                extendVo.setExtendId(String.valueOf(property.getProductExtend().getId()));
            }
            if (property.getId() != null) {
                extendVo.setPropertyId(String.valueOf(property.getId()));
            }
            extendVos.add(extendVo);
        }
        detailVo.setExtendVos(extendVos);
        return detailVo;
    }

    @Override
    @Transactional
    public void updateTypeBProduct(UpdateTypeBProductVo reqVo) {
        logger.debug("updateTypeBProduct reqVo = {}", JSON.toJSONString(reqVo));
        Product product = productRepository.findByProductCode(reqVo.getProductCode());
        product.setName(reqVo.getProductName());
        product.setBrand(brandRepository.findOne(reqVo.getBrandId()));
        product.setCategory(categoryRepository.findOne(reqVo.getCategoryId()));
        product.setI18nCode(reqVo.getI18nCode());
        product.setBreif(reqVo.getBreif());
        product.setImages(reqVo.getProductImages());
        product.setIntroImages(reqVo.getIntroImages());
        product.setLogo(reqVo.getImage());
        product.setSeoDescription(reqVo.getSeoDescription());
        product.setSeoKeywords(reqVo.getSeoKeywords());
        product.setSeoTitle(reqVo.getSeoTitle());
        product.setSubTitle(reqVo.getSubTitle());
        List<ExtendProperty> extendProperties = extendPropertyRepository.findAll(reqVo.getExtendids());
        product.setProperties(extendProperties);
        productRepository.save(product);
        logger.debug("save product po,and push TypeBProductModifyEventVo");
    }

    @Override
    @Transactional
    public void updateGroupProductPrice(ProductGroupOnSaleVo reqVo) {
        Product product = productRepository.findOne(reqVo.getId());
        if (product == null) {
            throw new RuntimeException("商品不存在");
        }
        if (Objects.equals(product.getIsRapidProduct(), Boolean.FALSE)) {
            throw new RuntimeException("该商品不是组合商品");
        }
        if (reqVo.getPrice() == null || reqVo.getPrice() <= 0) {
            throw new RuntimeException("价格异常");
        }
        Double fenPrice = reqVo.getPrice() * 100;
        setGroupProductPrice(product.getProductCode(), String.valueOf(fenPrice.intValue()));
        //        更新上架状态
        setTypeBProductSaleStauts(product.getId(), reqVo.getSaleStatus());
    }

    @Override
    public GroupProductRespVo getGroupProductEditVo(Long productId) {
        Product product = productRepository.findOne(productId);
        GroupProductRespVo groupProuct = new GroupProductRespVo();
        groupProuct.setId(String.valueOf(productId));
        groupProuct.setName(product.getName());
        PriceRo priceRo = priceRedisDao.getTypeBPrice(product.getProductCode(), "PA002");
        if (priceRo != null) {
            groupProuct.setPrice(MoneyConverter.instance.fen2yuan(priceRo.getFinalPrice()));
        }
        groupProuct.setSaleStatus(product.getTypeBSaleStatus());
        return groupProuct;
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
