package com.biz.soa.product.service.backend;

import com.biz.gbck.dao.mysql.po.product.meta.Category;
import com.biz.gbck.dao.mysql.po.product.bbc.GeoProduct;
import com.biz.gbck.dao.mysql.po.product.meta.SaleTag;
import com.biz.gbck.dao.mysql.repository.category.CategoryRepository;
import com.biz.gbck.dao.mysql.repository.bbc.product.GeoProductRepository;
import com.biz.gbck.dao.mysql.repository.saleTag.SaleTagRepository;
import com.biz.gbck.dao.redis.repository.product.SaleTagRedisDao;
import com.biz.gbck.exceptions.product.CategoryNotFoundException;
import com.biz.gbck.exceptions.product.SaleTagNotFoundException;
import com.biz.gbck.vo.product.backend.*;
import com.biz.service.AbstractBaseService;
import com.biz.service.product.backend.SaleTagService;
import com.biz.gbck.transform.product.SaleTag2SaleTagListItemVo;
import com.biz.gbck.transform.product.SaleTag2UpdateSaleTagVo;
import com.google.common.collect.Lists;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 销售标签 Service
 *
 * @author david-liu
 * @date 2016年12月19日
 * @reviewer
 * @see
 */
@Service
public class SaleTagServiceImpl extends AbstractBaseService implements SaleTagService {

    @Autowired
    private SaleTagRepository saleTagRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SaleTagRedisDao saleTagRedisDao;

//    @Autowired
    private GeoProductRepository geoProductRepository;

    /**
     * 创建销售标签
     * 1.创建销售标签Po,设置Id并装换vo到Po
     * 2.设置销售标签的分类(判断分类是否为null,不存在或被删除状态)
     * 3.持久化Po
     * 4.返回创建Vo
     *
     * @param vo 创建销售标签Vo
     * @throws CategoryNotFoundException
     */
    @Override
    public CreateSaleTagVo createSaleTag(CreateSaleTagVo vo) throws CategoryNotFoundException {
        // 创建品牌, 并且设置 ID
        SaleTag saleTag = new SaleTag();
        saleTag.setId(idService.nextId());
        saleTag.fromVo(vo);

        if (logger.isDebugEnabled()) {
            logger.debug("vo: {}", vo.toString());
        }

        // 设置分类
        setSaleTagCategory(vo.getCategoryId(), saleTag);

        saleTagRepository.save(saleTag);
        vo.setId(saleTag.getId());
        return vo;

    }

    private void setSaleTagCategory(Long categoryId, SaleTag saleTag) throws CategoryNotFoundException {
        if (categoryId == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("categoryId is null");
            }
            saleTag.setCategory(new Category());
        } else {
            // 查找数据库中未删除的分类
            Category category = categoryRepository.findByIdAndDeleteFlag(categoryId, Boolean.FALSE);
            if (category == null) {
                logger.debug("categoryId {} is invalid (not exist or already be deleted)");
                throw new CategoryNotFoundException("分类不存在/分类已被删除");
            } else {
                saleTag.setCategory(category);
            }
        }
    }

    /**
     * 更新销售标签
     * 1.查找销售标签
     * 2.更新销售标签信息
     * 3.设置销售标签分类并持久化
     *
     * @throws SaleTagNotFoundException
     * @throws CategoryNotFoundException
     */
    @Override
    public void updateSaleTag(UpdateSaleTagVo vo) throws SaleTagNotFoundException, CategoryNotFoundException {
        SaleTag saleTag = saleTagRepository.findByIdAndDeleteFlag(vo.getId(), Boolean.FALSE);
        if (saleTag == null) {
            throw new SaleTagNotFoundException("被编辑的标签不存在或者已被删除");
        }

        if (logger.isDebugEnabled()) {
            logger.debug("vo:{}", vo.toString());
        }
        saleTag.fromVo(vo);

        // 设置分类
        setSaleTagCategory(vo.getCategoryId(), saleTag);
        saleTagRepository.save(saleTag);

        if (logger.isDebugEnabled()) {
            logger.debug("po after save: {}", saleTag.toString());
        }
    }


    /**
     * 删除销售标签
     * 1.查找销售标签
     * 2.判断销售标签是否存在和是否已被删除,若满足返回
     * 3.设置被删除状态
     * 4.持久化销售标签
     *
     * @param id 销售标签 ID
     * @throws SaleTagNotFoundException
     */
    @Override
    public Boolean deleteSaleTag(Long id) throws SaleTagNotFoundException {
        SaleTag saleTag = saleTagRepository.findOne(id);
        // 如果需要被删除的品牌不存在, 抛出异常

        if (saleTag == null) {
            throw new SaleTagNotFoundException("被删除的销售标签不存在");
        }

        if (saleTag.getDeleteFlag() == Boolean.TRUE) {
            return Boolean.TRUE;
        }
        saleTag.setDeleteFlag(Boolean.TRUE);
        saleTagRepository.save(saleTag);
        return Boolean.TRUE;
    }

    /**
     * 展示搜索销售标签
     * 1.若搜索销售标签名称关键字不为空,根据名称搜索,不分页
     * 2.若分类 ID不为空,根据分类ID查询
     * 3.否则查询全部未删除的销售标签
     * 4.将ListPo transform ListVo
     */
    @Override
    public List<SaleTagListItemVo> listSaleTagItem(Long categoryId, SearchPageVo searchPageVo) {
        List<SaleTag> saleTags = Lists.newArrayList();
        if (StringUtils.isNotBlank(searchPageVo.getSearchValue())) {
            saleTags = saleTagRepository.findByNameAndDeleteFlag(searchPageVo.getSearchValue(), Boolean.FALSE);
        } else if (categoryId != null) {
            saleTags = saleTagRepository.findByCategoryIdAndDeleteFlag(categoryId, Boolean.FALSE);
        } else {
            saleTags = saleTagRepository.findByDeleteFlag(Boolean.FALSE);
        }
        List<SaleTagListItemVo> result = Lists.transform(saleTags, new SaleTag2SaleTagListItemVo());
        return result;
    }

    /**
     * 获取编辑状态销售标签
     * 1.查找销售标签
     * 2.将PO transform Vo
     * 3.return
     */
    @Override
    public UpdateSaleTagVo getUpdateSaleTagVo(Long saletagId) {
        SaleTag saleTag = saleTagRepository.findOne(saletagId);
        UpdateSaleTagVo updateSaleTagVo = new SaleTag2UpdateSaleTagVo().apply(saleTag);
        return updateSaleTagVo;
    }

    /**
     * 批量保存排序
     * 1.判断输入Vo是否为合法
     * 2.通过Vo组装销售标签ID与排序映射的Map和销售标签ListId
     * 3.通过ListId查询销售标签List,遍历设置排序
     * 4.保存
     */
    @Override
    public void saveOrUpdateSort(SaletagSortListVo saletagSortListVos) {
        logger.debug("SaleTagServiceImpl-->saveOrUpdateSort  SaletagSortListVo:{}", saletagSortListVos);
        if (saletagSortListVos != null && saletagSortListVos.getSaletagSortLists() != null) {
            List<SaleTag> saleTagList = Lists.newArrayList();
            List<Long> saleTagIds = Lists.newArrayList();
            Map<Long, Integer> saletagIdAndIdx = new HashMap<>();
            for (SaletagSortList index : saletagSortListVos.getSaletagSortLists()) {
                saleTagIds.add(index.getId());
                saletagIdAndIdx.put(index.getId(), index.getIdx());
            }
            saleTagList = saleTagRepository.findAll(saleTagIds);
            for (SaleTag saleTag : saleTagList) {
                saleTag.setIdx(saletagIdAndIdx.get(saleTag.getId()));
            }
            saleTagRepository.save(saleTagList);
        }
    }


    /**
     * @param saletagId
     * @return
     */
    @Override
    public List<SaleTagProductVo> listSaleTagProductVo(Long saletagId) {
        Map<Long, Double> productIdIdxMap = saleTagRedisDao.getProductBySaletagId(String.valueOf(saletagId));
        Set<Long> productIds = productIdIdxMap.keySet();
        List<GeoProduct> geoProductList = geoProductRepository.findByProductIdIn(Lists.newArrayList(productIds));
        List<SaleTagProductVo> resultList = Lists.newArrayList();
        for (GeoProduct index : geoProductList) {
            SaleTagProductVo saleTagProductVo = new SaleTagProductVo();
            saleTagProductVo.setSaletagId(saletagId);
            saleTagProductVo.setProductId(index.getProduct().getId());
            saleTagProductVo.setProductName(index.getProduct().getName());
            saleTagProductVo.setIdx(productIdIdxMap.get(index.getProduct().getId()));
            resultList.add(saleTagProductVo);
        }
        return resultList;
    }

    /**
     * 添加商品到销售标签
     * 1.将商品ID和销售标签ID做sortSet映射
     */
    @Override
    public void addProduct2SaleTag(SaleTagProductVo reqVo) {
        saleTagRedisDao.addProduct2Saletag(String.valueOf(reqVo.getSaletagId()), String.valueOf(reqVo.getProductId()), reqVo.getIdx());
    }

    @Override
    public void removeProduct2Saletag(SaleTagProductVo reqVo) {
        saleTagRedisDao.removeProduct2saletag(String.valueOf(reqVo.getSaletagId()), String.valueOf(reqVo.getProductId()));
    }

}
