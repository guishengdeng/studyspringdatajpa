package com.biz.gbck.dao.redis.ro.voucher;

import com.biz.redis.annotation.Ro;
import com.biz.redis.annotation.RoSortedSet;
import com.biz.redis.bean.BaseRedisObject;

@Ro(key = "ro:VoucherRo")
@RoSortedSet(key = "list", score = "createTimestamp")
public class VoucherRo extends BaseRedisObject<Long> implements Comparable<VoucherRo> {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6918146285867714465L;

	private Long id;

    private Long voucherTypeId;

    private Long bindingUserId;

    private Long expireTime;

    private Long createTime;

    private Long useTime;

    private Integer useAmount;

    private Long orderId;

    public Long getBindingUserId() {
        return bindingUserId;
    }

    public void setBindingUserId(Long bindingUserId) {
        this.bindingUserId = bindingUserId;
    }

    public VoucherRo() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVoucherTypeId() {
        return voucherTypeId;
    }

    public void setVoucherTypeId(Long voucherTypeId) {
        this.voucherTypeId = voucherTypeId;
    }


    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public Long getUseTime() {
        return useTime;
    }

    public void setUseTime(Long useTime) {
        this.useTime = useTime;
    }

    public Integer getUseAmount() {
        return useAmount;
    }

    public void setUseAmount(Integer useAmount) {
        this.useAmount = useAmount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Override public int compareTo(VoucherRo o) {
        return new Long(this.expireTime - o.expireTime).intValue();
    }
}
