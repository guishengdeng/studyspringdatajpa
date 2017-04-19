package com.biz.gbck.dao.mysql.po.vendor;

import com.biz.support.jpa.po.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author yanweijin
 * @date 2016/12/19
 */
@Entity
@Table(name = "ven_express")
public class SupportExpress extends BaseEntity {

    private static final long serialVersionUID = -3379776845816521260L;

    private String name;

    //物流公司代码
    private String code;

    //快递100物流公司代码
    private String fastCode;

    //排序
    private Long idx;

    //公司网址
    private String webSite;

    //询件网址
    private String queryExpressWebSite;

    private Boolean used;

    private Boolean deleteFlag = Boolean.FALSE;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFastCode() {
        return fastCode;
    }

    public void setFastCode(String fastCode) {
        this.fastCode = fastCode;
    }

    public Long getIdx() {
        return idx;
    }

    public void setIdx(Long idx) {
        this.idx = idx;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getQueryExpressWebSite() {
        return queryExpressWebSite;
    }

    public void setQueryExpressWebSite(String queryExpressWebSite) {
        this.queryExpressWebSite = queryExpressWebSite;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

}

