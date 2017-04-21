package com.biz.soa.product.service.backend;

import com.biz.gbck.dao.mysql.po.product.meta.ProductStatistic;
import com.biz.gbck.dao.mysql.repository.bbc.productStatistic.ProductStatisticRepository;
import com.biz.gbck.dao.redis.repository.product.CategoryRedisDao;
import com.biz.gbck.dao.redis.repository.product.ProductRedisDao;
import com.biz.gbck.vo.product.backend.ProductSalesVo;
import com.biz.gbck.vo.product.frontend.CategoryProductSalesTopVo;
import com.biz.gbck.vo.product.frontend.ProductIdxIdentityVo;
import com.biz.message.MessageService;
import com.biz.message.SimpleBizMessage;
import com.biz.message.queue.BizBaseQueue;
import com.biz.service.AbstractBaseService;
import com.biz.service.product.backend.ProductStatisticService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品统计Service实现
 *
 * @author david-liu
 * @date 2017年02月28日
 * @reviewer
 */
@Service
public class ProductStatisticServiceImpl extends AbstractBaseService implements ProductStatisticService {

    private static final Logger logger = LoggerFactory.getLogger(ProductStatisticServiceImpl.class);

    @Autowired
    private ProductStatisticRepository productStatisticRepository;

    @Autowired
    private ProductRedisDao productRedisDao;

    @Autowired
    private MessageService messageService;

    @Autowired
    private CategoryRedisDao categoryRedisDao;

    @Override
    @Transactional
    public void updateProductStatistic(List<ProductSalesVo> salesVoList) {
        if (CollectionUtils.isEmpty(salesVoList)) {
            if (logger.isDebugEnabled()) {
                logger.debug("salesVoList is empty, do nothing.");
            }
            return;
        }

        List<Long> productIds = Lists.newArrayList();
        Map<Long, ProductSalesVo> productSalesVoMap = Maps.newHashMap();
        List<String> productCodes = Lists.newArrayList();
        List<Integer> quantity = Lists.newArrayList();
        for (ProductSalesVo vo : salesVoList) {
            productIds.add(vo.getProductId());
            productSalesVoMap.put(vo.getProductId(), vo);
            productCodes.add(vo.getProductCode());
            if (vo.getQuantity() == null) {
                quantity.add(0);
            } else {
                quantity.add(vo.getQuantity().intValue());
            }
        }

        List<ProductStatistic> statistics = productStatisticRepository.findByProductIds(productIds);
        if (CollectionUtils.isNotEmpty(statistics)) {
            synchronized (ProductStatisticServiceImpl.class) {
                for (ProductStatistic statistic : statistics) {
                    ProductSalesVo vo = productSalesVoMap.get(statistic.getProduct().getId());
                    if (vo != null) {
                        Integer saleCount = statistic.getTotalSalesVolume() == null ? 0 : statistic.getTotalSalesVolume();
                        if (vo.getQuantity() != null) {
                            saleCount = saleCount + vo.getQuantity().intValue();
                        }
                        statistic.setTotalSalesVolume(saleCount);
                    }
                }
                productStatisticRepository.save(statistics);
            }

            productRedisDao.incrSalesVolume(productCodes, quantity);

            caluCategorySalesTopN(10, salesVoList);

            for (String productCode : productCodes) {
                ProductIdxIdentityVo vo = new ProductIdxIdentityVo();
                vo.setProductCode(productCode);
                messageService.sendMessage(BizBaseQueue.MQ_PRODUCT_SALE_STATS_QUEUE, SimpleBizMessage.newMessage(vo));
            }
        }
    }

    /**
     * 计算分类热销商品 topN
     */
    void caluCategorySalesTopN(final int topN, List<ProductSalesVo> productSalesVos) {
        //      遍历productSalesVos,获取分类集合
        logger.debug("统计各分类销量前{} 的商品数据", topN);
        Set<Long> categoryIdSet = new HashSet<>();
        for (ProductSalesVo productSalesVo : productSalesVos) {
            categoryIdSet.add(productSalesVo.getCategoryId());
        }
        //        遍历categoryIdSet ,获取对应分类的商品集合,排序,选取topN,存Redis
        final List<CategoryProductSalesTopVo> lists = Lists.newArrayList();
        for (Long categoryId : categoryIdSet) {
            CategoryProductSalesTopVo cat = new CategoryProductSalesTopVo();
            cat.setCategoryId(categoryId);
            List<ProductSalesVo> tmpSalesVo = Lists.newArrayList();
            for (ProductSalesVo productSalesVo : productSalesVos) {
                if (productSalesVo.getCategoryId() == categoryId) {
                    tmpSalesVo.add(productSalesVo);
                }
            }
            cat.setProductSalesVos(tmpSalesVo);
            lists.add(cat);
        }
        delayExecuteRedisOpt(() -> categoryRedisDao.setCategorySalesTopN(lists));


    }

}
