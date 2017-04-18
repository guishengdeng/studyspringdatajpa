package com.biz.gbck.dao.redis.ro.vendor;

import com.biz.core.util.DateUtil;
import com.biz.redis.annotation.Ro;
import com.biz.redis.annotation.RoSortedSet;
import com.biz.redis.bean.BaseRedisObject;
import java.sql.Timestamp;

/**
 * @author mounan
 * @Description: 快递公司和运费策略中间表ro
 * @time:2017年1月4日 下午2:46:50
 */
@Ro(key = "ro:VendorExpressRo")
@RoSortedSet(key = "list", score = "createTimestamp")
public class VendorExpressRo extends BaseRedisObject<Long> implements Comparable<VendorExpressRo> {

    //关联快递id
    private Long supportExpressId;

    //关联店铺id
    private Long vendorId;

    //关联运费策略id
    private Long freightStrategyId;

    private Boolean deleteFlag = Boolean.FALSE;

    private Timestamp createTimestamp = DateUtil.now();

    @Override
    public int compareTo(VendorExpressRo o) {
        return -this.createTimestamp.compareTo(o.getCreateTimestamp());
    }

    public Long getSupportExpressId() {
        return supportExpressId;
    }

    public void setSupportExpressId(Long supportExpressId) {
        this.supportExpressId = supportExpressId;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public Long getFreightStrategyId() {
        return freightStrategyId;
    }

    public void setFreightStrategyId(Long freightStrategyId) {
        this.freightStrategyId = freightStrategyId;
    }


    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

}
