package com.biz.manage.vo.voucher;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 
 * @ClassName: DispatcherVoucherVo 
 * @Description: 优惠券发放Vo
 * @author Nian.Li
 * @date 2016年4月15日 下午4:19:48 
 *  
 */
public class DispatcherVoucherVo {
	
	/**
	 * 优惠券类型ID
	 */
    private Long voucherTypeId;

    /**
     * 优惠券类型名称
     */
    private String voucherTypeName;

    /**
     * 发放目标用户(针对发送个具体用户使用)
     */
    private String dispatcherto;

    /**
     * 发放数量
     */
    private String dispatchCount;

    /**
     * 商户类型(针对发送给某商户类型下的用户使用)
     */
    private Long shopType;

    private List<Long> dispatcherUserIds;

    private Long userId;

    private String handler;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public DispatcherVoucherVo() {

    }

    public void setDispatcherUserIds(List<Long> dispatcherUserIds) {
        this.dispatcherUserIds = dispatcherUserIds;
    }

    public List<Long> getDispatcherUserIds() {
        return dispatcherUserIds;
    }

    public Long getShopType() {
        return shopType;
    }

    public void setShopType(Long shopType) {
        this.shopType = shopType;
    }

    public Long getVoucherTypeId() {
        return voucherTypeId;
    }

    public void setVoucherTypeId(Long voucherTypeId) {
        this.voucherTypeId = voucherTypeId;
    }

    public String getVoucherTypeName() {
        return voucherTypeName;
    }

    public void setVoucherTypeName(String voucherTypeName) {
        this.voucherTypeName = voucherTypeName;
    }

    public String getDispatcherto() {
        return dispatcherto;
    }

    public void setDispatcherto(String dispatcherto) {
        this.dispatcherto = dispatcherto;
    }

    public String getDispatchCount() {
        return dispatchCount;
    }

    public void setDispatchCount(String dispatchCount) {
        this.dispatchCount = dispatchCount;
    }

    public void fillUserIds() {
        this.dispatcherUserIds = Lists.newArrayList();
        String dispatcherStr = this.getDispatcherto();
        if(dispatcherStr!=null && !"".equals(dispatcherStr)){
            for (String str : dispatcherStr.split(",")) {
                if (!str.equals("")) {
                    dispatcherUserIds.add(Long.valueOf(str));
                }
            }       	
        }
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }
}
