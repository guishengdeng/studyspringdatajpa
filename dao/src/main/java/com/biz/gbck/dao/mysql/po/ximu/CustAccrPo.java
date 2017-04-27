package com.biz.gbck.dao.mysql.po.ximu;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by defei on 6/28/16.
 */
@Entity @Table(name = "ximu_cust_accr")
public class CustAccrPo {

    /**
     * 隔壁仓库用户shopId
     */
    @Id private Long member_id;

    /**
     * 是否授权
     */
    private Boolean accr_sign;

    /**
     * 授信额度
     */
    private Integer credit_lmt;

    /**
     * 是否准入
     */
    private Boolean accredit_stat;

    /**
     * 不准入原因
     */
    private String reject_reasons;

    /**
     * 申请审核失败原因
     */
    private String xm_reject_reasons;

    public Long getMember_id() {
        return member_id;
    }

    public void setMember_id(Long member_id) {
        this.member_id = member_id;
    }

    public Boolean getAccr_sign() {
        return accr_sign;
    }

    public void setAccr_sign(Boolean accr_sign) {
        this.accr_sign = accr_sign;
    }

    public Integer getCredit_lmt() {
        return credit_lmt;
    }

    public void setCredit_lmt(Integer credit_lmt) {
        this.credit_lmt = credit_lmt;
    }

    public Boolean getAccredit_stat() {
        return accredit_stat;
    }

    public void setAccredit_stat(Boolean accredit_stat) {
        this.accredit_stat = accredit_stat;
    }

    public String getReject_reasons() {
        return reject_reasons;
    }

    public void setReject_reasons(String reject_reasons) {
        this.reject_reasons = reject_reasons;
    }

    public String getXm_reject_reasons() {
        return xm_reject_reasons;
    }

    public void setXm_reject_reasons(String xm_reject_reasons) {
        this.xm_reject_reasons = xm_reject_reasons;
    }
}
