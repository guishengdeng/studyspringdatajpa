package com.biz.soa.org.listener;

import com.biz.core.event.AbstractBizEventListener;
import com.biz.core.event.BizEvent;
import com.biz.gbck.enums.user.AuditRejectReason;
import com.biz.gbck.enums.user.AuditStatus;
import com.biz.service.notice.NoticeService;
import com.biz.soa.org.event.ShopAuditEvent;
import com.biz.soa.org.service.interfaces.ShopSoaService;
import com.biz.soa.org.service.interfaces.UserSoaService;
import org.codelogger.utils.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by JKLiues on 2016/5/11.
 */
public class ShopAuditEventListener extends AbstractBizEventListener<BizEvent> {

    private final static Logger logger =
        LoggerFactory.getLogger(ShopAuditEventListener.class);

    @Autowired
    ShopSoaService shopSoaService;
    @Autowired
    UserSoaService userSoaService;
    @Autowired
    NoticeService noticeService;

    @Override
    @Transactional
    protected void handleEvent(BizEvent event) {
//        if (event instanceof ShopAuditEvent) {
//            ShopAuditEvent shopAuditEvent = (ShopAuditEvent) event;
//            logger.debug("RECIVED SHOP[{}] AUDIT EVENT",
//                    shopAuditEvent.getShopDetailPo().getShop().getId());
//
//            try {
//                List<Long> userIds = userSoaService
//                        .findUserIdsByShopId(shopAuditEvent.getShopDetailPo().getShop().getId());
//                if (CollectionUtils.isNotEmpty(userIds)) {
//                    AuditStatus auditStatus =
//                            AuditStatus.valueOf(shopAuditEvent.getShopDetailPo().getAuditStatus());
//                    String content = "";
//                    if (auditStatus == AuditStatus.NORMAL) {
//                        content="您提交的商户资料已审核通过。";
//                    }else{
//                        String detailRejectReason = shopAuditEvent.getShopDetailPo().getRejectReason();
//                        String qualRejectReason = shopAuditEvent.getShopQualificationPo().getRejectReason();
//                        String rejectReason = detailRejectReason==null?"":detailRejectReason+qualRejectReason==null?"":(","+qualRejectReason);
//                        List<String> strings = AuditRejectReason
//                                .parseRejectReasons(rejectReason.split(","));
//                        content = CollectionUtils.join(strings, ",");
//                    }
//                    for (Long userId : userIds) {
//                        //noticeService.sendMsgToUser(userId,auditStatus.getDescription(), content, "");
//                        logger.debug("Send notice to user:{} with title:{} and content:{}", userId,
//                                auditStatus.getDescription(), content);
//                    }
//                }
//            } catch (Exception e) {
//                logger.debug("PUSH AUDIT RESULT TO CLIENT FAILED");
//            }
//
//        }
    }
}
