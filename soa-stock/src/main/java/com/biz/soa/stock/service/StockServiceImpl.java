package com.biz.soa.stock.service;

import com.biz.core.util.DateUtil;
import com.biz.gbck.dao.redis.repository.stock.OrderStockLockRedisDao;
import com.biz.gbck.dao.redis.repository.stock.CompanyStockLockRedisDao;
import com.biz.gbck.dao.redis.repository.stock.CompanyStockRedisDao;
import com.biz.gbck.dao.redis.ro.stock.CompanyStockLockRo;
import com.biz.gbck.dao.redis.ro.stock.CompanyStockRo;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.vo.stock.*;
import com.biz.service.AbstractBaseService;
import com.google.common.base.Stopwatch;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.codelogger.utils.ValueUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newLinkedHashSet;

/**
 * StockService
 *
 * @author lei
 * @date 2017年05月11日
 * @reviewer
 * @see
 */
@Service
public class StockServiceImpl extends AbstractBaseService implements StockService {

    @Autowired
    private OrderStockLockRedisDao orderStockLockRedisDao;

    @Autowired
    private CompanyStockRedisDao companyStockRedisDao;

    @Autowired
    private CompanyStockLockRedisDao companyStockLockRedisDao;


    @Override
    public CompanyStockRespVO getStock(CompanyStockReqVO reqVo) throws DepotNextDoorException {
        if (logger.isDebugEnabled()) {
            logger.debug("公司库存查询-------请求vo: {}", reqVo);
        }

        if (reqVo == null || reqVo.getCompanyId() == null || reqVo.getProductId() == null) {
            logger.warn("公司库存查询-------参数不合法: {}", reqVo);
            return null;
        }
        CompanyStockRespVO respVo = new CompanyStockRespVO();
        Long companyId = reqVo.getCompanyId();
        Long productId = respVo.getProductId();

        respVo.setCompanyId(companyId);
        respVo.setProductId(productId);

        Integer availablePartnerStock = this.getAvailablePartnertStock(companyId, productId);
        respVo.setQuantity(availablePartnerStock);

        if (logger.isDebugEnabled()) {
            logger.debug("公司库存查询-------请求: {}, 返回值: {}", reqVo, respVo);
        }
        return respVo;
    }

    @Override
    public List<CompanyStockRespVO> getStocks(CompanyStocksReqVO reqVo) throws DepotNextDoorException {
        if (logger.isDebugEnabled()) {
            logger.debug("公司批量库存查询-------请求vo: {}", reqVo);
        }

        List<CompanyStockRespVO> respVos = newArrayList();
        if (reqVo == null || reqVo.getCompanyId() == null || CollectionUtils.isEmpty(reqVo.getProductIds())) {
            logger.warn("公司批量库存查询-------参数不合法: {}", reqVo);
            return respVos;
        }
        Long companyId = reqVo.getCompanyId();
        List<Long> productIds = reqVo.getProductIds();
        Map<Long, Integer> productIdToAvailableQuantity = this.getAvailablePartnertStocks(companyId, newLinkedHashSet
                (productIds));
        for (Long productId : productIds) {
            Integer availableQuantity = ValueUtils.getValue(productIdToAvailableQuantity.get(productId));
            respVos.add(new CompanyStockRespVO(companyId, productId, availableQuantity));
        }

        if (logger.isDebugEnabled()) {
            logger.debug("公司批量库存查询-------请求: {}, 返回值: {}", reqVo, respVos);
        }
        return respVos;
    }

    @Override
    public void updateStocks(List<UpdateCompanyStockReqVO> reqVos) throws DepotNextDoorException {
        if (logger.isDebugEnabled()) {
            logger.debug("公司库存更新-------请求vo: {}", reqVos);
        }
        if (CollectionUtils.isEmpty(reqVos)) {
            logger.warn("公司库存更新-------参数不合法: {}", reqVos);
            return;
        }

        Map<String, Integer> keyToQuantity = newHashMap();
        List<CompanyStockRo> stockRos = newArrayList();
        for (UpdateCompanyStockReqVO reqVo : reqVos) {
            if (reqVo == null || reqVo.getCompanyId() == null || reqVo.getProductId() == null) {
                logger.warn("公司Lock库存参数无效!");
                return;
            }
            Long companyId = reqVo.getCompanyId();
            Long productId = reqVo.getProductId();
            CompanyStockRo stockLockRo = new CompanyStockRo(companyId, productId, null);
            stockRos.add(stockLockRo);
            keyToQuantity.put(CompanyStockRedisDao.getHashKey(companyId, productId), reqVo.getQuantity());

        }

        companyStockRedisDao.updateQuantities(keyToQuantity);
        companyStockRedisDao.save(stockRos);
    }

    @Override
    public void orderUpdateLockStocks(List<UpdateCompanyLockStockReqVO> reqVos) throws DepotNextDoorException {
        if (logger.isDebugEnabled()) {
            logger.debug("公司Lock库存更新-------请求vo: {}", reqVos);
        }
        if (CollectionUtils.isEmpty(reqVos)) {
            logger.warn("公司Lock库存更新-------参数不合法: {}", reqVos);
            return;
        }
        for (UpdateCompanyLockStockReqVO lockVo : reqVos) {
            if (lockVo == null || StringUtils.isBlank(lockVo.getOrderCode()) || lockVo.getCompanyId() == null) {
                logger.warn("公司Lock库存参数无效!");
                return;
            }
            String orderCode = lockVo.getOrderCode();
            Long companyId = lockVo.getCompanyId();
            Map<String, Integer> keyToLockQuantity = newHashMap();
            List<CompanyStockLockRo> stockLockRos = newArrayList();
            boolean releaseStock = false; //是否为释放库存(即存在数量为负认为释放库存)
            for (StockItemVO item : lockVo.getItems()) {
                if (item != null && item.getProductId() != null) {
                    int quantity = ValueUtils.getValue(item.getQuantity());
                    if (quantity < 0) {
                        releaseStock = true;
                    }
                    Long productId = item.getProductId();
                    CompanyStockLockRo stockLockRo = new CompanyStockLockRo(companyId, productId, null);
                    stockLockRos.add(stockLockRo);
                    keyToLockQuantity.put(CompanyStockLockRedisDao.getHashKey(companyId, productId), quantity);
                } else {
                    logger.warn("公司Lock库存更新-------item参数不合法: {}", item);
                }
            }
            companyStockLockRedisDao.updateQuantities(keyToLockQuantity);
            companyStockLockRedisDao.save(stockLockRos);

            if (releaseStock) { //释放库存
                orderStockLockRedisDao.removeInvalidOrderStockLockKeys(orderCode, keyToLockQuantity.keySet());
            } else { //锁定库存
                orderStockLockRedisDao.recordOrderLockStock(orderCode, keyToLockQuantity, lockVo.getAliveTime());
            }
        }
    }


    @Override
    public void releaseLockStocks() {
        logger.info("开始释放订单锁定库存. 当前时间: {}", DateUtil.now());
        Stopwatch stopwatch = Stopwatch.createStarted();
        Set<String> expiredLockStockOrderCodes = orderStockLockRedisDao.getExpiredLockStockOrderCodes(DateUtil.now());
        if (CollectionUtils.isEmpty(expiredLockStockOrderCodes)) {
            logger.warn("未查询到无过期锁定库存订单!");
        }
        int total = expiredLockStockOrderCodes.size();
        logger.debug("锁定库存过期订单总数: {}", total);
        int count = 0;
        for (String expiredLockStockOrderCode : expiredLockStockOrderCodes) {
            this.releaseOrderLockStocks(expiredLockStockOrderCode);
            logger.debug("释放订单[{}]锁定库存. 进度: {}/{}", expiredLockStockOrderCode, ++count, total);
        }
        logger.info("完成释放订单锁定库存. 用时: {} ms, 当前时间: {}", stopwatch.elapsed(TimeUnit.MILLISECONDS), DateUtil.now());
    }

    protected void releaseOrderLockStocks(String expiredLockStockOrderCode) {
        Map<String, Integer> keyToReleaseQuantity = newHashMap();
        Map<String, Integer> orderStockLockKeyWithQuantities = orderStockLockRedisDao
                .getOrderStockLockKeyWithQuantities(expiredLockStockOrderCode);
        for (Map.Entry<String, Integer> entry : orderStockLockKeyWithQuantities.entrySet()) {
            if (entry.getValue() < 0) { //小于0才释放
                keyToReleaseQuantity.put(entry.getKey(), -entry.getValue());
            }
        }
        orderStockLockRedisDao.updateQuantities(keyToReleaseQuantity);
        orderStockLockRedisDao.removeInvalidOrderStockLockKeys(expiredLockStockOrderCode, keyToReleaseQuantity.keySet
                ());
        orderStockLockRedisDao.removedExpiredLockStockOrder(expiredLockStockOrderCode);
    }


    /**
     * 批量获取公司可用库存
     *
     * @param companyId
     * @param productIds
     * @return
     */
    private Map<Long, Integer> getAvailablePartnertStocks(Long companyId, Set<Long> productIds) {
        Map<Long, Integer> productIdToAvailabletStock = newHashMap();
        List<CompanyStockRo> companyStockRos = companyStockRedisDao.findByCompanyIdAndProductIds(companyId, productIds);
        List<CompanyStockLockRo> companyStockLockRos = companyStockLockRedisDao.findByCompanyIdAndProductId
                (companyId, productIds);
        for (CompanyStockRo companyStockRo : companyStockRos) {
            Long productId = companyStockRo.getProductId();
            Integer availableQuantity = companyStockRo.getQuantity();
            for (CompanyStockLockRo companyStockLockRo : companyStockLockRos) {
                if (Objects.equals(productId, companyStockLockRo.getProductId())) {
                    availableQuantity = this.calcAvailableStock(availableQuantity, companyStockLockRo.getQuantity());
                }
            }
            productIdToAvailabletStock.put(productId, availableQuantity);
        }
        return productIdToAvailabletStock;
    }

    /**
     * 获取公司可用库存
     *
     * @param companyId
     * @param productId
     * @return
     */
    private Integer getAvailablePartnertStock(Long companyId, Long productId) {
        Integer quantity = companyStockRedisDao.getByQuantityCompanyIdAndProductId(companyId, productId);
        Integer lockQuantity = companyStockLockRedisDao.getQuantityByCompanyIdAndProductId(companyId, productId);
        return this.calcAvailableStock(quantity, lockQuantity);
    }

    /**
     * 根据库存和锁定库存计算可用库存
     */
    private Integer calcAvailableStock(Integer quantity, Integer lockQuantity) {
        quantity = ValueUtils.getValue(quantity) <= 0 ? 0 : quantity;
        lockQuantity = ValueUtils.getValue(lockQuantity) <= 0 ? 0 : lockQuantity;
        Integer availableQuantity = ValueUtils.getValue(quantity) - lockQuantity;
        return availableQuantity > 0 ? availableQuantity : 0;
    }
}
