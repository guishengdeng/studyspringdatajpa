package com.biz.gbck.vo.qrcode;

import java.io.Serializable;
import java.sql.Timestamp;

import com.biz.gbck.dao.mysql.po.qrcode.enums.BusinessStatusEnum;
import com.biz.gbck.enums.CommonStatusEnum;

public class QRCodeVO implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 3326304330936438102L;
    /**
     * 瓶码
     */
    private String bcno;
    /**
     * 箱码
     */
    private String boxno;
    /**
     * 商品编号
     */
    private String litm;
    /**
     * 生产批次
     */
    private String vendSn;
    /**
     * 出库方code
     */
    private String sendCode;
    /**
     * 出库方描述,如名称（冗余）
     */
    private String sendDesc;
    /**
     * 入库方code
     */
    private String receiveCode;
    /**
     * 入库方描述如名称 （冗余）
     */
    private String receiveDesc;
    /**
     * 发生业务时的行号
     */
    private int lnid;
    /**
     * 单号(发生业务时候产生的单号)
     */
    private String orderNo;
    /**
     * 业务状态（枚举）
     */
    private BusinessStatusEnum businessStatus;
    /**
     * 包码
     */
    private String packgeNo;
    /**
     * 页码
     */
    private String pageNo;
    /**
     * 处理人编码
     */
    private String optUserId;
    /**
     * 处理人名称（冗余）
     */
    private String optUserName;
    /**
     * 处理时间
     */
    private Timestamp optDate;
    /**
     * 创建时间
     */
    private Timestamp createDate;
    /**
     * 状态
     */
    private CommonStatusEnum status;
    public String getBcno() {
        return bcno;
    }
    public void setBcno(String bcno) {
        this.bcno = bcno;
    }
    public String getBoxno() {
        return boxno;
    }
    public void setBoxno(String boxno) {
        this.boxno = boxno;
    }
    public String getLitm() {
        return litm;
    }
    public void setLitm(String litm) {
        this.litm = litm;
    }
    public String getVendSn() {
        return vendSn;
    }
    public void setVendSn(String vendSn) {
        this.vendSn = vendSn;
    }
    public String getSendCode() {
        return sendCode;
    }
    public void setSendCode(String sendCode) {
        this.sendCode = sendCode;
    }
    public String getSendDesc() {
        return sendDesc;
    }
    public void setSendDesc(String sendDesc) {
        this.sendDesc = sendDesc;
    }
    public String getReceiveCode() {
        return receiveCode;
    }
    public void setReceiveCode(String receiveCode) {
        this.receiveCode = receiveCode;
    }
    public String getReceiveDesc() {
        return receiveDesc;
    }
    public void setReceiveDesc(String receiveDesc) {
        this.receiveDesc = receiveDesc;
    }
    public int getLnid() {
        return lnid;
    }
    public void setLnid(int lnid) {
        this.lnid = lnid;
    }
    public String getOrderNo() {
        return orderNo;
    }
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    public BusinessStatusEnum getBusinessStatus() {
        return businessStatus;
    }
    public void setBusinessStatus(BusinessStatusEnum businessStatus) {
        this.businessStatus = businessStatus;
    }
    public String getPackgeNo() {
        return packgeNo;
    }
    public void setPackgeNo(String packgeNo) {
        this.packgeNo = packgeNo;
    }
    public String getPageNo() {
        return pageNo;
    }
    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }
    public String getOptUserId() {
        return optUserId;
    }
    public void setOptUserId(String optUserId) {
        this.optUserId = optUserId;
    }
    public String getOptUserName() {
        return optUserName;
    }
    public void setOptUserName(String optUserName) {
        this.optUserName = optUserName;
    }
    public Timestamp getOptDate() {
        return optDate;
    }
    public void setOptDate(Timestamp optDate) {
        this.optDate = optDate;
    }
    public Timestamp getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
    public CommonStatusEnum getStatus() {
        return status;
    }
    public void setStatus(CommonStatusEnum status) {
        this.status = status;
    }
}
