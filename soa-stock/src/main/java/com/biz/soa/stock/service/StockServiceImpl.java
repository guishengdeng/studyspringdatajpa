package com.biz.soa.stock.service;

import com.biz.core.util.DateUtil;
import com.biz.gbck.dao.redis.repository.stock.OrderStockLockRedisDao;
import com.biz.gbck.dao.redis.repository.stock.PartnerStockLockRedisDao;
import com.biz.gbck.dao.redis.repository.stock.PartnerStockRedisDao;
import com.biz.gbck.dao.redis.ro.stock.PartnerStockLockRo;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.vo.stock.*;
import com.biz.service.AbstractBaseService;
import com.biz.service.stock.StockService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.codelogger.utils.ValueUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

/**
 * StockService
 *
 * @author lei
 * @date 2017年05月11日
 * @reviewer
 * @see
 */
public class StockServiceImpl extends AbstractBaseService implements StockService {


    @Autowired
    private OrderStockLockRedisDao orderStockLockRedisDao;

    @Autowired
    private PartnerStockRedisDao partnerStockRedisDao;

    @Autowired
    private PartnerStockLockRedisDao partnerStockLockRedisDao;


    @Override
    public PartnerStockRespVO getStock(PartnerStockReqVO reqVo) throws DepotNextDoorException {
        return null;
    }

    @Override
    public List<PartnerStockRespVO> getStocks(PartnerStocksReqVO reqVo) throws DepotNextDoorException {
        return null;
    }

    @Override
    public void updateStocks(List<UpdatePartnerStockReqVO> reqVos) throws DepotNextDoorException {

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
                    PartnerStockLockRo stockLockRo = new PartnerStockLockRo(partnerId, productId, null, DateUtil.now());
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
}
