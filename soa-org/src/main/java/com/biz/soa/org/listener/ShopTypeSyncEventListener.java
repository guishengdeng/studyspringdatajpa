package com.biz.soa.org.listener;

import com.biz.core.event.AbstractBizEventListener;
import com.biz.core.event.BizEvent;
import com.biz.soa.org.event.ShopTypeSyncEvent;
import com.biz.soa.org.service.interfaces.ShopSoaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by defei on 3/26/16.
 */
public class ShopTypeSyncEventListener extends AbstractBizEventListener<BizEvent> {

//    @Override
//    public void onApplicationEvent(DepotnearbyEvent event) {
//    }

    @Override
    protected void handleEvent(BizEvent event) {
        if (event instanceof ShopTypeSyncEvent) {
            logger.debug("Start to sync all shop type from mysql to redis");
            shopSoaService.syncAllShopTypeFromMysqlToRedis(1000);
            logger.debug("Finish sync all shop type from mysql to redis");
        }
    }

    @Autowired
    private ShopSoaService shopSoaService;

    private static final Logger logger = LoggerFactory.getLogger(ShopTypeSyncEventListener.class);
}
