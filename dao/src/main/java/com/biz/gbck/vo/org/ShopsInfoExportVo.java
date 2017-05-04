package com.biz.gbck.vo.org;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.Date;

/**
 * Created by zhangcheng on 2016/8/3.
 */
public class ShopsInfoExportVo {

    /**
     * 商户id
     */
    private Number shop_id;

    /**
     * 会员手机号
     */
    private String user_mobile;

    /**
     * 会员类型
     */
    private String shop_type;

    /**
     * 会员归属省份
     */
    private String shop_province;

    /**
     * 注册时间
     */
    private Date user_register_time;

    /**
     * 最后一次登录时间
     */
    private Date user_lastlogintime;

    /**
     * 会员状态
     */
    private String shop_status;

    /**
     * 推荐人工号
     */
    private String shop_invite_code;

    /**
     * 推荐人姓名
     */
    private String shop_inviter_name;

    /**
     * 开发门店
     */
    private String shop_assart_depot_name;

    /**
     * 价格门店
     */
    private String shop_price_depot_name;

    /**
     * 配送门店
     */
    private String shop_deliver_depot_name;

    /**
     * 店铺名称
     */
    private String shop_name;

    /**
     * 店铺所在省
     */
    private String province_name;

    /**
     * 店铺所在市
     */
    private String city_name;

    /**
     * 店铺所在区
     */
    private String district_name;

    /**
     * 店铺详细地址
     */
    private String shop_address;

    public ShopsInfoExportVo(
            Number shop_id,
            String user_mobile,
            String shop_type,
            String shop_province,
            Date user_register_time,
            Date user_lastlogintime,
            String shop_status,
            String shop_invite_code,
            String shop_inviter_name,
            String shop_assart_depot_name,
            String shop_price_depot_name,
            String shop_deliver_depot_name,
            String shop_name,
            String province_name,
            String city_name,
            String district_name,
            String shop_address) {
        this.shop_id=shop_id;
        this.user_mobile = user_mobile;
        this.shop_type = shop_type;
        this.shop_province = shop_province;
        this.user_register_time = user_register_time;
        this.user_lastlogintime = user_lastlogintime;
        this.shop_status = shop_status;
        this.shop_invite_code = shop_invite_code;
        this.shop_inviter_name = shop_inviter_name;
        this.shop_assart_depot_name = shop_assart_depot_name;
        this.shop_price_depot_name = shop_price_depot_name;
        this.shop_deliver_depot_name = shop_deliver_depot_name;
        this.shop_name = shop_name;
        this.province_name = province_name;
        this.city_name = city_name;
        this.district_name = district_name;
        this.shop_address = shop_address;
    }


    public Number getShop_id() {
        return shop_id;
    }

    public void setShop_id(Number shop_id) {
        this.shop_id = shop_id;
    }

    public String getUser_mobile() {
        return user_mobile;
    }

    public void setUser_mobile(String user_mobile) {
        this.user_mobile = user_mobile;
    }

    public String getShop_type() {
        return shop_type;
    }

    public void setShop_type(String shop_type) {
        this.shop_type = shop_type;
    }

    public String getShop_province() {
        return shop_province;
    }

    public void setShop_province(String shop_province) {
        this.shop_province = shop_province;
    }

    public Date getUser_register_time() {
        return user_register_time;
    }

    public void setUser_register_time(Date user_register_time) {
        this.user_register_time = user_register_time;
    }

    public Date getUser_lastlogintime() {
        return user_lastlogintime;
    }

    public void setUser_lastlogintime(Date user_lastlogintime) {
        this.user_lastlogintime = user_lastlogintime;
    }

    public String getShop_status() {
        return shop_status;
    }

    public void setShop_status(String shop_status) {
        this.shop_status = shop_status;
    }

    public String getShop_invite_code() {
        return shop_invite_code;
    }

    public void setShop_invite_code(String shop_invite_code) {
        this.shop_invite_code = shop_invite_code;
    }

    public String getShop_inviter_name() {
        return shop_inviter_name;
    }

    public void setShop_inviter_name(String shop_inviter_name) {
        this.shop_inviter_name = shop_inviter_name;
    }

    public String getShop_assart_depot_name() {
        return shop_assart_depot_name;
    }

    public void setShop_assart_depot_name(String shop_assart_depot_name) {
        this.shop_assart_depot_name = shop_assart_depot_name;
    }

    public String getShop_price_depot_name() {
        return shop_price_depot_name;
    }

    public void setShop_price_depot_name(String shop_price_depot_name) {
        this.shop_price_depot_name = shop_price_depot_name;
    }

    public String getShop_deliver_depot_name() {
        return shop_deliver_depot_name;
    }

    public void setShop_deliver_depot_name(String shop_deliver_depot_name) {
        this.shop_deliver_depot_name = shop_deliver_depot_name;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getShop_address() {
        return shop_address;
    }

    public void setShop_address(String shop_address) {
        this.shop_address = shop_address;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
