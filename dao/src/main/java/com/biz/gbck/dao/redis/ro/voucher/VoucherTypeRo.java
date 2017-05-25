package com.biz.gbck.dao.redis.ro.voucher;

import com.biz.gbck.dao.mysql.po.voucher.VoucherExpireType;
import com.biz.gbck.dao.mysql.po.voucher.VoucherLimitType;
import com.biz.gbck.dao.mysql.po.voucher.VoucherTypeStatus;
import com.biz.redis.annotation.Ro;
import com.biz.redis.annotation.RoSortedSet;
import com.biz.redis.bean.BaseRedisObject;
import org.apache.commons.lang3.StringUtils;
import org.codelogger.utils.DateUtils;

import java.util.Date;
import static org.codelogger.utils.ValueUtils.getValue;

@Ro(key = "ro:VoucherTypeRo")
@RoSortedSet(key = "list", score = "createTimestamp")
public class VoucherTypeRo extends BaseRedisObject<Long> implements Comparable<VoucherTypeRo> {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6595409033537117992L;

	/**
     * 优惠券类型ID
     */
    private Long id;

    /**
     * 优惠券类型名
     */
    private String name;

    /**
     * 订单限额（使用优惠券需达到订单限额） 面值 单位：分
     */
    private int paymentLimit;

    /**
     * 过期类型
     */
    private VoucherExpireType voucherExpireType;

    /**
     * 优惠券使用特点(使用互斥/可叠加使用)
     */
    private VoucherTypeStatus typeStatus;

    /**
     * 优惠券领取特点(只能领取一次/还是能领取多次)
     */
    private int fetchType;

    /**
     * 优惠券状态(下单立即使用还是用户选择使用)
     */
    private int useStatus;

    /**
     * 优惠券面值
     */
    private int faceValue;

    /**
     * 优惠券有效期
     */
    private int periodDays;

    /**
     * 优惠券类型描述
     */
    private String description;

    /**
     * 优惠券开始发行的时间
     */
    private Long startTime;

    /**
     * 优惠券领取过期的时间
     */
    private Long expireTime;

    /**
     * 优惠券适用的商户类型ID  多个
     */
    private String shopTypeIds;

    private VoucherLimitType voucherLimitType;

    /**
     * 优惠券适用的商品种类的ID 一个
     */
    private Long categoryId;

    /**
     * 优惠券发行的数量
     */
    private int issueCount;

    /**
     * 优惠券支持的付款方式
     * 多个支付方式采用逗号分割
     */
    private String paymentType;

    /**
     * 销售区域（校验不考虑）
     */
    private String saleAreaIds;

    /**
     * 可用券商品Ids
     */
    private String productIds;

    private String limitInfo;

    public VoucherTypeRo() {
        super();
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public int getPaymentLimit() {
        return paymentLimit;
    }


    public void setPaymentLimit(int paymentLimit) {
        this.paymentLimit = paymentLimit;
    }

    public VoucherExpireType getVoucherExpireType() {
        return voucherExpireType;
    }

    public void setVoucherExpireType(VoucherExpireType voucherExpireType) {
        this.voucherExpireType = voucherExpireType;
    }

    public VoucherTypeStatus getTypeStatus() {
        return typeStatus;
    }


    public void setTypeStatus(VoucherTypeStatus typeStatus) {
        this.typeStatus = typeStatus;
    }


    public int getFetchType() {
        return fetchType;
    }


    public void setFetchType(int fetchType) {
        this.fetchType = fetchType;
    }


    public int getUseStatus() {
        return useStatus;
    }


    public void setUseStatus(int useStatus) {
        this.useStatus = useStatus;
    }


    public int getFaceValue() {
        return faceValue;
    }


    public void setFaceValue(int faceValue) {
        this.faceValue = faceValue;
    }


    public int getPeriodDays() {
        return periodDays;
    }


    public void setPeriodDays(int periodDays) {
        this.periodDays = periodDays;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public Long getStartTime() {
        return startTime;
    }


    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }


    public Long getExpireTime() {
        return expireTime;
    }


    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }


    public String getShopTypeIds() {
        return shopTypeIds;
    }


    public void setShopTypeIds(String shopTypeIds) {
        this.shopTypeIds = shopTypeIds;
    }

    public VoucherLimitType getVoucherLimitType() {
        return voucherLimitType;
    }

    public void setVoucherLimitType(VoucherLimitType voucherLimitType) {
        this.voucherLimitType = voucherLimitType;
    }

    public Long getCategoryId() {
        return categoryId;
    }


    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }


    public int getIssueCount() {
        return issueCount;
    }


    public void setIssueCount(int issueCount) {
        this.issueCount = issueCount;
    }


    public String getPaymentType() {
        return paymentType;
    }


    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }


    public String getSaleAreaIds() {
        return saleAreaIds;
    }


    public void setSaleAreaIds(String saleAreaIds) {
        this.saleAreaIds = saleAreaIds;
    }


    public String getProductIds() {
        return productIds;
    }


    public void setProductIds(String productIds) {
        this.productIds = productIds;
    }

    public String getLimitInfo() {
        return limitInfo;
    }

    public boolean canUseByShopType(Long st) {
        if (StringUtils.isBlank(this.shopTypeIds)) {
            return true;
        } else {
            return StringUtils.contains(this.shopTypeIds, "," + st + ",");
        }
    }

    public void setLimitInfo(String limitInfo) {
        this.limitInfo = limitInfo;
    }

    public boolean isExpire() {
        long firstOfToday = DateUtils.formatToStartOfDay(new Date()).getTime();
        long startTime = DateUtils.formatToStartOfDay(new Date(getValue(getStartTime()))).getTime();
        Date expireTime = new Date(getValue(getExpireTime()));
        if (voucherExpireType == VoucherExpireType.EXPIRE_BY_DATE_RANGE) {
            return firstOfToday < startTime || firstOfToday > expireTime.getTime();
        } else {
            return firstOfToday < startTime || firstOfToday > DateUtils
                .getDateOfDaysBack(-(periodDays), expireTime).getTime();
        }
    }


	@Override
	public int compareTo(VoucherTypeRo o) {
		 return -this.getId().compareTo(o.getId());
	}

}
