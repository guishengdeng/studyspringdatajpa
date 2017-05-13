package com.biz.soa.stock.service;

import com.biz.core.util.DateUtil;
import com.biz.gbck.dao.redis.repository.stock.OrderStockLockRedisDao;
import com.biz.gbck.dao.redis.repository.stock.PartnerStockLockRedisDao;
import com.biz.gbck.dao.redis.repository.stock.PartnerStockRedisDao;
import com.biz.gbck.dao.redis.ro.stock.PartnerStockLockRo;
import com.biz.gbck.dao.redis.ro.stock.PartnerStockRo;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.vo.stock.*;
import com.biz.service.AbstractBaseService;
import com.biz.service.stock.StockService;
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
    private PartnerStockRedisDao partnerStockRedisDao;

    @Autowired
    private PartnerStockLockRedisDao partnerStockLockRedisDao;


    @Override
    public PartnerStockRespVO getStock(PartnerStockReqVO reqVo) throws DepotNextDoorException {
        if (logger.isDebugEnabled()) {
            logger.debug("合伙人库存查询-------请求vo: {}", reqVo);
        }

        if (reqVo == null || reqVo.getPartnerId() == null || reqVo.getProductId() == null) {
            logger.warn("合伙人库存查询-------参数不合法: {}", reqVo);
            return null;
        }
        PartnerStockRespVO respVo = new PartnerStockRespVO();
        Long partnerId = reqVo.getPartnerId();
        Long productId = respVo.getProductId();

        respVo.setPartnerId(partnerId);
        respVo.setProductId(productId);

        Integer availablePartnerStock = this.getAvailablePartnertStock(partnerId, productId);
        respVo.setQuantity(availablePartnerStock);

        if (logger.isDebugEnabled()) {
            logger.debug("合伙人库存查询-------请求: {}, 返回值: {}", reqVo, respVo);
        }
        return respVo;
    }

    @Override
    public List<PartnerStockRespVO> getStocks(PartnerStocksReqVO reqVo) throws DepotNextDoorException {
        if (logger.isDebugEnabled()) {
            logger.debug("合伙人批量库存查询-------请求vo: {}", reqVo);
        }

        List<PartnerStockRespVO> respVos = newArrayList();
        if (reqVo == null || reqVo.getPartnerId() == null || CollectionUtils.isEmpty(reqVo.getProductIds())) {
            logger.warn("合伙人批量库存查询-------参数不合法: {}", reqVo);
            return respVos;
        }
        Long partnerId = reqVo.getPartnerId();
        List<Long> productIds = reqVo.getProductIds();
        Map<Long, Integer> productIdToAvailableQuantity = this.getAvailablePartnertStocks(partnerId, newLinkedHashSet
                (productIds));
        for (Long productId : productIds) {
            Integer availableQuantity = ValueUtils.getValue(productIdToAvailableQuantity.get(productId));
            respVos.add(new PartnerStockRespVO(partnerId, productId, availableQuantity));
        }

        if (logger.isDebugEnabled()) {
            logger.debug("合伙人批量库存查询-------请求: {}, 返回值: {}", reqVo, respVos);
        }
        return respVos;
    }

    @Override
    public void updateStocks(List<UpdatePartnerStockReqVO> reqVos) throws DepotNextDoorException {
        if (logger.isDebugEnabled()) {
            logger.debug("合伙人库存更新-------请求vo: {}", reqVos);
        }
        if (CollectionUtils.isEmpty(reqVos)) {
            logger.warn("合伙人库存更新-------参数不合法: {}", reqVos);
            return;
        }

        Map<String, Integer> keyToQuantity = newHashMap();
        List<PartnerStockRo> stockRos = newArrayList();
        for (UpdatePartnerStockReqVO reqVo : reqVos) {
            if (reqVo == null || reqVo.getPartnerId() == null || reqVo.getProductId() == null) {
                logger.warn("合伙人Lock库存参数无效!");
                return;
            }
            Long partnerId = reqVo.getPartnerId();
            Long productId = reqVo.getProductId();
            PartnerStockRo stockLockRo = new PartnerStockRo(partnerId, productId, null);
            stockRos.add(stockLockRo);
            keyToQuantity.put(PartnerStockRedisDao.getHashKey(partnerId, productId), reqVo.getQuantity());

        }

        partnerStockRedisDao.updateQuantities(keyToQuantity);
        partnerStockRedisDao.save(stockRos);
    }

    @Override
    public void orderLockStocks(List<UpdatePartnerLockStockReqVO> reqVos) throws DepotNextDoorException {
        if (logger.isDebugEnabled()) {
            logger.debug("合伙人Lock库存更新-------请求vo: {}", reqVos);
        }
        if (CollectionUtils.isEmpty(reqVos)) {
            logger.warn("合伙人Lock库存更新-------参数不合法: {}", reqVos);
            return;
        }
        for (UpdatePartnerLockStockReqVO lockVo : reqVos) {
            if (lockVo == null || StringUtils.isBlank(lockVo.getOrderCode()) || lockVo.getPartnerId() == null) {
                logger.warn("合伙人Lock库存参数无效!");
                return;
            }
            String orderCode = lockVo.getOrderCode();
            Long partnerId = lockVo.getPartnerId();
            Map<String, Integer> keyToLockQuantity = newHashMap();
            List<PartnerStockLockRo> stockLockRos = newArrayList();
            boolean releaseStock = false; //是否为释放库存(即存在数量为负认为释放库存)
            for (StockItemVO item : lockVo.getItems()) {
                if (item != null && item.getProductId() != null) {
                    int quantity = ValueUtils.getValue(item.getQuantity());
                    if (quantity < 0) {
                        releaseStock = true;
                    }
                    Long productId = item.getProductId();
                    PartnerStockLockRo stockLockRo = new PartnerStockLockRo(partnerId, productId, null);
                    stockLockRos.add(stockLockRo);
                    keyToLockQuantity.put(PartnerStockLockRedisDao.getHashKey(partnerId, productId), quantity);
                } else {
                    logger.warn("合伙人Lock库存更新-------item参数不合法: {}", item);
                }
            }
            partnerStockLockRedisDao.updateQuantities(keyToLockQuantity);
            partnerStockLockRedisDao.save(stockLockRos);

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
     * 批量获取合伙人可用库存
     *
     * @param partnerId
     * @param productIds
     * @return
     */
    private Map<Long, Integer> getAvailablePartnertStocks(Long partnerId, Set<Long> productIds) {
        Map<Long, Integer> productIdToAvailabletStock = newHashMap();
        List<PartnerStockRo> partnerStockRos = partnerStockRedisDao.findByPartnerIdAndProductIds(partnerId, productIds);
        List<PartnerStockLockRo> partnerStockLockRos = partnerStockLockRedisDao.findByParnterIdAndProductId
                (partnerId, productIds);
        for (PartnerStockRo partnerStockRo : partnerStockRos) {
            Long productId = partnerStockRo.getProductId();
            Integer availableQuantity = partnerStockRo.getQuantity();
            for (PartnerStockLockRo partnerStockLockRo : partnerStockLockRos) {
                if (Objects.equals(productId, partnerStockLockRo.getProductId())) {
                    availableQuantity = this.calcAvailableStock(availableQuantity, partnerStockLockRo.getQuantity());
                }
            }
            productIdToAvailabletStock.put(productId, availableQuantity);
        }
        return productIdToAvailabletStock;
    }

    /**
     * 获取合伙人可用库存
     *
     * @param partnerId
     * @param productId
     * @return
     */
    private Integer getAvailablePartnertStock(Long partnerId, Long productId) {
        Integer quantity = partnerStockRedisDao.getByQuantityPartnerIdAndProductId(partnerId, productId);
        Integer lockQuantity = partnerStockLockRedisDao.getQuantityByPartnerIdAndProductId(partnerId, productId);
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
