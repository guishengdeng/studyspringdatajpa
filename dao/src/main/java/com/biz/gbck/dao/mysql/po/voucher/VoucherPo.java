package com.biz.gbck.dao.mysql.po.voucher;

import com.biz.gbck.dao.mysql.po.order.Order;
import com.biz.gbck.vo.voucher.UserVoucherStatisticResultVo;
import com.biz.support.jpa.po.BaseEntity;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;

@SqlResultSetMappings({
  @SqlResultSetMapping(
      name = "UserVoucherStatsBinding",
      classes = {
          @ConstructorResult(
              targetClass = UserVoucherStatisticResultVo.class,
              columns = {
                  @ColumnResult(name = "time"),
                  @ColumnResult(name = "user_mobile"),
                  @ColumnResult(name = "user_id"),
                  @ColumnResult(name = "province_name"),
                  @ColumnResult(name = "shop_type_name"),
                  @ColumnResult(name = "voucher_type_name"),
                  @ColumnResult(name = "voucher_type_id"),
                  @ColumnResult(name = "voucher_issue_count")
              })
      })
})
@Entity 
@Table(name = "vou_voucher") 
public class VoucherPo extends BaseEntity implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 597987311446158670L;


	/**
     * 状态
     */
    @Column(nullable = false) private int status;


    /**
     * 创建时间
     */
    @Column private Timestamp createTime;

    /**
     * 管理员用户名
     */
    @Column(length = 50) private String createBy;

    /**
     * 绑定用户id
     */
    @Column private Long bindingUserId;

    /**
     * 绑定日期
     */
    @Column private Timestamp bindingTime;

    /**
     * 锁定时间
     */
    @Column private Timestamp blockTime;

    /**
     * 过期时间
     */
    @Column private Timestamp expireTime;

    /**
     * 使用时间
     */
    @Column private Timestamp useTime;

    /**
     * 使用金额 单位分
     */
    @Column private Integer useAmount;

    /**
     * 使用者的设备id
     */
    private String useDeviceId;


    /**
     * 对应订单
     */
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "orderId") 
    private Order orderPo;

    /**
     * 优惠券类型
     */
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "voucherTypeId") 
    private VoucherTypePo
        voucherType;

    public VoucherPo() {
        super();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Long getBindingUserId() {
        return bindingUserId;
    }

    public void setBindingUserId(Long bindingUserId) {
        this.bindingUserId = bindingUserId;
    }

    public Timestamp getBindingTime() {
        return bindingTime;
    }

    public void setBindingTime(Timestamp bindingTime) {
        this.bindingTime = bindingTime;
    }

    public Timestamp getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Timestamp expireTime) {
        this.expireTime = expireTime;
    }

    public Timestamp getUseTime() {
        return useTime;
    }

    public void setUseTime(Timestamp useTime) {
        this.useTime = useTime;
    }

    public Timestamp getBlockTime() {
        return blockTime;
    }

    public void setBlockTime(Timestamp blockTime) {
        this.blockTime = blockTime;
    }

    public Integer getUseAmount() {
        return useAmount;
    }

    public void setUseAmount(Integer useAmount) {
        this.useAmount = useAmount;
    }

    public String getUseDeviceId() {
        return useDeviceId;
    }

    public void setUseDeviceId(String useDeviceId) {
        this.useDeviceId = useDeviceId;
    }

    public Order getOrderPo() {
        return orderPo;
    }

    public void setOrderPo(Order orderPo) {
        this.orderPo = orderPo;
    }

    public VoucherTypePo getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(VoucherTypePo voucherType) {
        this.voucherType = voucherType;
    }

}
