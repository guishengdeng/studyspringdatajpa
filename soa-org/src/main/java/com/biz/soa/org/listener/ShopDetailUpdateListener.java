package com.biz.soa.org.listener;

import com.biz.core.event.AbstractBizEventListener;
import com.biz.core.event.BizEvent;
import com.biz.soa.org.event.ShopDetailUpdateEvent;
import com.biz.soa.org.service.interfaces.ShopSoaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 商户详情变更事件
 */
public class ShopDetailUpdateListener extends AbstractBizEventListener<BizEvent> {

    private final static Logger logger = LoggerFactory.getLogger(ShopDetailUpdateListener.class);

    @Autowired
    private ShopSoaService shopSoaService;

    @Override
    protected void handleEvent(BizEvent event) {
        logger.info("ShopDetailUpdateListener start");
//        if (event instanceof ShopDetailUpdateEvent) {
//            ShopDetailUpdateEvent shopDetailUpdateEvent = (ShopDetailUpdateEvent) event;
//            logger.debug("Received Shop[{}] detail update event.",
//                    shopDetailUpdateEvent.getShopDetailPo().getShop().getId());
//        }
    }

//    @Override
//    public void onApplicationEvent(DepotnearbyEvent event) {
//
//
//    }

}
