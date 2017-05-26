package com.biz.soa.event.voucher;

import com.biz.core.event.BizEvent;
import com.biz.gbck.dao.redis.ro.voucher.VoucherRo;

/**
 * 
 * @author yang
 *
 */
public class VoucherEvent extends BizEvent {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8877320264244079838L;
	
	private VoucherRo voucherRo;

    protected VoucherEvent(Object source, VoucherRo voucherRo) {
        super(source);
        this.voucherRo = voucherRo;
    }

    public VoucherRo getVoucherRo() {
        return voucherRo;
    }

    public void setVoucherRo(VoucherRo voucherRo) {
        this.voucherRo = voucherRo;
    }
}
