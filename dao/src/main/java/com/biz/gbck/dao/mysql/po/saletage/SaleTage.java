package com.biz.gbck.dao.mysql.po.saletage;

import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.support.jpa.po.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 销售标签
 * Created by lzz on 2017/5/2.
 */
@Entity
@Table(name = "saleTage")
public class SaleTage extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -7875835108324835738L;

    /*
     *标签名
     */
    @Column(length = 50, nullable = false)
    private String tagName;

    /*
     *前台展示
     */
    @Column(length = 50, nullable = false)
    private String showName;

    /*
     *标签
     */
    @Column(length = 50, nullable = false)
    private String tag;

    /*
     *品牌排序
     */
    @Column
    private Long idx;

    /*
     *后台备注
     */
    @Column(length = 200)
    private String note;

    /*
     *启用状态
     */
    @Column
    private String saleStatus;

    /*
     *状态(是否删除状态)
     */
    @Column
    @Enumerated(EnumType.STRING)
    private CommonStatusEnum status;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Long getIdx() {
        return idx;
    }

    public void setIdx(Long idx) {
        this.idx = idx;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(String saleStatus) {
        this.saleStatus = saleStatus;
    }

    public CommonStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CommonStatusEnum status) {
        this.status = status;
    }
}
