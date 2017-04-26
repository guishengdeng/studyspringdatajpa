package com.biz.gbck.dao.mysql.po.qrcode;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.biz.core.model.Identifiable;
import com.biz.gbck.dao.mysql.po.BasePo;
import com.biz.gbck.dao.mysql.po.qrcode.enums.BusinessStatusEnum;
import com.biz.gbck.dao.mysql.po.qrcode.enums.BusinessTypeEnum;
import com.biz.gbck.enums.CommonStatusEnum;

/**
 * 二维码履历
 */
@Entity
@Table(name = "qrc_qrcode_resume")
public class QRCodeResumePO extends BasePo<Long> implements Identifiable<Long>{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 50)
    private String bcno;//瓶码
    @Column(length = 50)
    private String boxno;//箱码
    @Column(length = 50)
    private String sendCode;//出库方code
    @Column(length = 200)
    private String sendDesc;//出库方描述,如名称（冗余）
    @Column(length = 50)
    private String receiveCode;//入库方code
    @Column(length = 200)
    private String receiveDesc;//入库方描述如名称 （冗余）
    @Column(length = 200)
    private String orderNo;//单号(发生业务时候产生的单号)
    @Column
    @Enumerated(EnumType.STRING)
    private BusinessStatusEnum businessStatus;//状态（枚举）
    @Column
    @Enumerated(EnumType.STRING)
    private BusinessTypeEnum businessType;//业务类型（枚举）
    @Column(length = 50)
    private String optUserId;//处理人编码
    @Column(length = 150)
    private String optUserName;//处理人名称（冗余）
    @Column
    private Timestamp optDate;//处理时间
    @Column
    @Enumerated(EnumType.STRING)
    private CommonStatusEnum status = CommonStatusEnum.ENABLE;//状态
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
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