package com.biz.gbck.common.model.voucher;

/**
 * 优惠券分配配置设置
 * @ClassName: VoucherConfigure 
 * @Description: TODO
 * @author Nian.Li
 * @date 2016年4月14日 下午5:41:13 
 *  
 */
public enum VoucherConfigure {

	INVITATION_VOUCHERCONFIGURE("invitation_voucherconfigure", "邀请成功送优惠券"),
    REGISTER_VOUCHERCONFIGURE("register_voucherconfigure", "审核通过送优惠券"),// 审核通过和转发链接使用
	FINISHEDORDER_VOUCHERCONFIGURE("finishorder_voucherconfigure", "订单完成");//订单完成发送优惠券
//	FIRSTORDER_VOUCHERCONFIGURE("finishorder_firstorder", "完成首单");//首单完成发送优惠券
	
   
    private String mark;
    private String title;

    private VoucherConfigure(String mark, String title) {
        this.mark = mark;
        this.title = title;
    }

	public String getMark() {
		return mark;
	}

	public String getTitle() {
		return title;
	}

}
