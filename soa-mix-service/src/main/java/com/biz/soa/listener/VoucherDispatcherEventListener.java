package com.biz.soa.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.biz.common.event.DepotnearbyEvent;
import com.biz.core.event.AbstractBizEventListener;
import com.biz.gbck.common.model.voucher.VoucherConfigure;
import com.biz.gbck.dao.mysql.po.org.UserPo;
import com.biz.gbck.dao.redis.repository.voucher.VoucherTypeRedisDao;
import com.biz.soa.event.voucher.VoucherDispatcherEvent;
import com.biz.soa.event.voucher.VoucherEvent;
import com.biz.soa.service.voucher.VoucherConfigureService;
import com.biz.soa.service.voucher.VoucherService;

/**
 * 
 * @ClassName: VoucherDispatcherEventListener 
 * @Description: 发放优惠券事件监听处理
 * @author Nian.Li
 * @date 2016年4月15日 下午5:05:18 
 *  
 */
public class VoucherDispatcherEventListener extends AbstractBizEventListener<VoucherDispatcherEvent> {

    private static final Logger logger = LoggerFactory.getLogger(VoucherDispatcherEventListener.class);
    
    @Autowired 
    private VoucherService voucherService;
    
//    @Autowired 
//    private RecommendUserService recommendUserService;

    @Override 
	public void onApplicationEvent(VoucherDispatcherEvent voucherDispatcherEvent) {
		if (voucherDispatcherEvent instanceof VoucherDispatcherEvent) {
			UserPo userPo = voucherDispatcherEvent.getUserPo();
			try {
				// 当前用户不为空则进行发券处理
				if (userPo != null) {
					voucherService.dispatcherVoucherByAction(userPo.getId(), VoucherConfigure.REGISTER_VOUCHERCONFIGURE);
					// 查询该用户对应的推荐人
					//TODO
//					RecommendUserRo recommendUserRo = this.recommendUserService.getRecommendUserRo(userPo.getMobile());
//					if (recommendUserRo != null) {
//						// 推荐人不为空则进行发券处理
//						voucherService.dispatcherVoucherByAction(recommendUserRo.getRecommendUserId(), VoucherConfigure.INVITATION_VOUCHERCONFIGURE);
//					}
				}
			} catch (Exception e) {
				logger.error("针对审核通过发放优惠券事件监听处理异常,userPo[" + userPo + "]，,异常信息:" + e.getMessage(), e);
			}
		}
	}

	@Override
	protected void handleEvent(VoucherDispatcherEvent event) {
		// TODO Auto-generated method stub
		
	}

//    /**
//     * 
//     * @Description: 发券公共方法处理
//     * @param userIds
//     * @param dispatcherCnt
//     * @param voucherConfigureRo
//     * 
//     * @author Nian.Li
//     * @date 2016年4月15日 下午5:42:41 
//     */
//    protected void dispatcherVoucher(List<Long> userIds, int dispatcherCnt,
//        VoucherConfigureRo voucherConfigureRo) throws Exception {
//        if (voucherConfigureRo != null) {
//            List<Long> voucherTypeIds = voucherConfigureRo.getVoucherTypeIds();
//            for (Long voucherTypeId : voucherTypeIds) {
//                VoucherTypeRo voucherTypeRo =
//                    voucherTypeRedisDao.getVoucherTypeRoById(voucherTypeId);
//                if (voucherTypeRo.getStartTime() > System.currentTimeMillis()) {
//                    logger.info("优惠券未到发放时间,优惠券类型ID[" + voucherTypeRo.getId() + "]");
//                } else if (voucherTypeRo.getExpireTime() < System.currentTimeMillis()) {
//                    logger.info("优惠券发放时间已经过期,优惠券类型ID[" + voucherTypeRo.getId() + "]");
//                } else if (!voucherService
//                    .validateDispatcherAction(userIds, null, voucherTypeRo.getId(),dispatcherCnt)) {
//                    logger.info("优惠券数量不足,优惠券类型ID[" + voucherTypeRo.getId() + "]");
//                } else {
//                    voucherService.dispatcherVoucher(userIds, voucherTypeRo, dispatcherCnt);
//                }
//            }
//
//        }
//    }
}
