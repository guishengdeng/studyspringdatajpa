package com.biz.gbck.vo.org;

/**
 * 接收用户导入信息
 * Created by dylan on 16-7-18.
 */
public class UserVo {

    String mobile; //手机号

    String name;  //姓名

    String depotId;    //门店

    String shopName; //名称

    public UserVo() {
        super();
    }

    public UserVo(String mobile, String name, String depotId, String shopName) {
        this.mobile = mobile;
        this.name = name;
        this.depotId = depotId;
        this.shopName = shopName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepotId() {
        return depotId;
    }

    public void setDepotId(String depotId) {
        this.depotId = depotId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
