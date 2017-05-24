package com.biz.gbck.vo.voucher;

import java.io.Serializable;

/**
 * 
 * @author yang
 *
 */
public class VoucherCfgVo implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -4191572951515228088L;
	private String voucherconfigure;
    private String voucherconfigurename;
    
    private Long voucherType;
    private String voucherTypename;
    
    private int quantity;

    public String getVoucherconfigurename() {
        return voucherconfigurename;
    }

    public void setVoucherconfigurename(String voucherconfigurename) {
        this.voucherconfigurename = voucherconfigurename;
    }

    public Long getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(Long voucherType) {
        this.voucherType = voucherType;
    }

    public String getVoucherconfigure() {
        return voucherconfigure;
    }

    public void setVoucherconfigure(String voucherconfigure) {
        this.voucherconfigure = voucherconfigure;
    }

    public String getVoucherTypename() {
        return voucherTypename;
    }

    public void setVoucherTypename(String voucherTypename) {
        this.voucherTypename = voucherTypename;
    }

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
    
    
}
