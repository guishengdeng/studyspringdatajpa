package com.biz.gbck.dao.mysql.po.qrcode;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.biz.gbck.dao.mysql.po.qrcode.enums.BusinessStatusEnum;
import com.biz.gbck.dao.mysql.po.qrcode.enums.BusinessTypeEnum;
import com.biz.gbck.enums.CommonStatusEnum;

/**
 * 二维码基础信息
 */
@Entity
@Table(name = "qrc_qrcode")
public class QRCode implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @Column(length = 50, nullable = false)
    private String bcno;//瓶码
    @Column(length = 50)
    private String boxno;//箱码
    @Column(length = 200)
    private String litm;//商品编号(商品对象)
    @Column(length = 50)
    private String sendCode;//出库方code
    @Column(length = 200)
    private String sendDesc;//出库方描述,如名称（冗余）
    @Column(length = 50)
    private String receiveCode;//入库方code
    @Column(length = 200)
    private String receiveDesc;//入库方描述如名称 （冗余）
    @Column
    private int lnid;//发生业务时的行号
    @Column(length = 200)
    private String orderNo;//单号(发生业务时候产生的单号)
    @Column
    @Enumerated(EnumType.STRING)
    private BusinessStatusEnum businessStatus;//状态（枚举）
    @Column
    @Enumerated(EnumType.STRING)
    private BusinessTypeEnum businessType;//业务类型（枚举）
    @Column(length = 50)
    private String packgeNo;//包码
    @Column(length = 50)
    private String pageNo;//页码
    @Column(length = 50)
    private String optUserId;//处理人编码
    @Column(length = 150)
    private String optUserName;//处理人名称（冗余）
    @Column
    private Timestamp optDate;//处理时间
    @Column
    @Enumerated(EnumType.STRING)
    private CommonStatusEnum status = CommonStatusEnum.ENABLE;//状态
    
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
    public BusinessTypeEnum getBusinessType() {
        return businessType;
    }
    public void setBusinessType(BusinessTypeEnum businessType) {
        this.businessType = businessType;
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
    public CommonStatusEnum getStatus() {
        return status;
    }
    public void setStatus(CommonStatusEnum status) {
        this.status = status;
    }
}
