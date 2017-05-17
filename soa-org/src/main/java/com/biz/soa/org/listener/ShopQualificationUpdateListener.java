package com.biz.soa.org.listener;

import com.biz.core.event.AbstractBizEventListener;
import com.biz.core.event.BizEvent;
import com.biz.gbck.dao.mysql.po.org.ShopPo;
import com.biz.gbck.dao.mysql.po.org.ShopQualificationPo;
import com.biz.gbck.enums.user.AuditStatus;
import com.biz.soa.org.event.ShopQualificationUpdateEvent;
import com.biz.soa.org.service.interfaces.ShopSoaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Objects;


/**
 * 商店资质变更事件
 */
@Component
public class ShopQualificationUpdateListener implements ApplicationListener<ShopQualificationUpdateEvent> {

    private final static Logger logger =
        LoggerFactory.getLogger(ShopQualificationUpdateListener.class);

    @Autowired
    private ShopSoaService shopSoaService;


    @Override
    public void onApplicationEvent(ShopQualificationUpdateEvent event) {
        logger.info("ShopQualificationUpdateListener start");

        if (event instanceof ShopQualificationUpdateEvent) {
            logger.info("ShopQualificationUpdateListener start");

            ShopQualificationUpdateEvent shopQualificationUpdateEvent =
                    (ShopQualificationUpdateEvent) event;
            logger.debug("Received Shop[{}] qualification update event.",
                    shopQualificationUpdateEvent.getShopQualificationPo().getShop().getId());
            ShopQualificationPo shopQualificationPo =
                    shopQualificationUpdateEvent.getShopQualificationPo();
            ShopPo shop = shopQualificationPo.getShop();
            if (Objects.equals(shop.getQualificationAuditStatus(),
                    AuditStatus.NEED_INFO.getValue()) || Objects
                    .equals(shop.getQualificationAuditStatus(),
                            AuditStatus.AUDIT_FAILED.getValue())) {
                shopSoaService.updateShopQualificationAuditStatusToWaitForAudit(shop.getId());
            }
        }
    }

//    @Override
//    protected void handleEvent(ShopQualificationUpdateEvent event) {
//        if (event instanceof ShopQualificationUpdateEvent) {
//            logger.info("ShopQualificationUpdateListener start");
//
//            ShopQualificationUpdateEvent shopQualificationUpdateEvent =
//                    (ShopQualificationUpdateEvent) event;
//            logger.debug("Received Shop[{}] qualification update event.",
//                    shopQualificationUpdateEvent.getShopQualificationPo().getShop().getId());
//            ShopQualificationPo shopQualificationPo =
//                    shopQualificationUpdateEvent.getShopQualificationPo();
//            ShopPo shop = shopQualificationPo.getShop();
//            if (Objects.equals(shop.getQualificationAuditStatus(),
//                    AuditStatus.NEED_INFO.getValue()) || Objects
//                    .equals(shop.getQualificationAuditStatus(),
//                            AuditStatus.AUDIT_FAILED.getValue())) {
//                shopSoaService.updateShopQualificationAuditStatusToWaitForAudit(shop.getId());
//            }
//        }
//    }

}
