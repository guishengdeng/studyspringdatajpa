package com.biz.gbck.dao.redis.ro.voucher;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class VoucherTypeWithQuantity {
	
	
    /**
     * 优惠券类型ID
     */
    private Long id;
    
    
    /**
     * 发放数量
     */
    private int quantity=1;


    
    
	public VoucherTypeWithQuantity() {
		super();
	}
	
	public VoucherTypeWithQuantity(Long id) {
		super();
		this.id = id;
	}


	public VoucherTypeWithQuantity(Long id, int quantity) {
		super();
		this.id = id;
		this.quantity = quantity;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String toJoinStr(String split){
		return ""+id+split+quantity;
	}
    
    public String toString(){
    	return ToStringBuilder.reflectionToString(this);
    }
    
    

}
