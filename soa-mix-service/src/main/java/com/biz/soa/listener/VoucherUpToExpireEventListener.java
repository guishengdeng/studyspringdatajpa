package com.biz.soa.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.biz.common.event.DepotnearbyEvent;
import com.biz.core.event.AbstractBizEventListener;
import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.dao.mysql.po.product.meta.Category;
import com.biz.gbck.dao.redis.ro.voucher.VoucherRo;
import com.biz.gbck.dao.redis.ro.voucher.VoucherTypeRo;
import com.biz.service.notice.NoticeService;
import com.biz.service.product.backend.CategoryService;
import com.biz.soa.event.voucher.VoucherEvent;
import com.biz.soa.service.voucher.VoucherTypeService;

public class VoucherUpToExpireEventListener extends AbstractBizEventListener<VoucherEvent> {

    private static final Logger logger = LoggerFactory.getLogger(VoucherUpToExpireEventListener.class);

    @Autowired 
    private VoucherTypeService voucherTypeService;

    @Autowired 
    private NoticeService noticeService;

    @Autowired 
    private CategoryService categoryService;

    @Override public void onApplicationEvent(VoucherEvent voucherEvent) {
        String template = "您有一张%d元{（%s）红包}即将过期，请尽快使用哟，点击查看详情";
        String message;
        if (voucherEvent instanceof VoucherEvent) {
        	VoucherRo voucherRo = voucherEvent.getVoucherRo();
            VoucherTypeRo voucherTypeRo = voucherTypeService.getVoucherTypeRoById(voucherRo.getVoucherTypeId());
            String url = "depotnearby://redirect?target=voucherList&id=" + voucherRo.getBindingUserId();
            if (voucherTypeRo.getCategoryId() == null) {
                message = String.format(template, voucherTypeRo.getFaceValue() / 100, "全品类");
            } else {
            	//TODO
                Category categoryPo = null;//categoryService.get(voucherTypeRo.getCategoryId());
                message = String.format(template, voucherTypeRo.getFaceValue(), categoryPo.getName());
            }
            try {
            	//TODO
//                noticeService.sendMsgToUser(voucherRo.getBindingUserId(), "您有红包即将过期",
//                    message, url);
            } catch (Exception e) {
                logger.error("向用户ID[{}]的用户推送消息错误", voucherRo.getBindingUserId());
            }

        }
    }

	@Override
	protected void handleEvent(VoucherEvent event) {
		// TODO Auto-generated method stub
		
	}
}
