package com.biz.soa.event.voucher;

import com.biz.core.event.BizEvent;
import com.biz.gbck.dao.mysql.po.org.UserPo;

/**
 *
 * @ClassName: VoucherDispatcherEvent 
 * @Description: 发放优惠券事件
 * @author Nian.Li
 * @date 2016年4月15日 下午5:01:38 
 *  
 */
public class VoucherDispatcherEvent extends BizEvent {

	/**
	 *
	 */
	private static final long serialVersionUID = 757446859082168795L;

	/**
	 * 用户
	 */
    private UserPo userPo;

		public VoucherDispatcherEvent(Object source, UserPo userPo) {
        super(source);
        this.userPo = userPo;
    }

	public UserPo getUserPo() {
		return userPo;
	}

	public void setUserPo(UserPo userPo) {
		this.userPo = userPo;
	}


}
