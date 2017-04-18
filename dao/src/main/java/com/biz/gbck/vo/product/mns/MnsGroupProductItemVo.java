package com.biz.gbck.vo.product.mns;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 中台组合商品明细Vo
 *
 * @author zhangcheng
 * @date 2017/3/11
 * @reviewer
 * @see
 */
public class MnsGroupProductItemVo implements Serializable {

    private static final long serialVersionUID = -1758418406954756826L;

    /**
     * 商品编码
     */
    private String matnr;

    /**
     * 数量
     */
    private Double menge;

    /**
     * 基本计量单位
     */
    private String meins;

    /**
     * 最后更新日期
     */
    private String cdate;

    public String getMatnr() {
        return matnr;
    }

    public void setMatnr(String matnr) {
        this.matnr = matnr;
    }

    public Double getMenge() {
        return menge;
    }

    public void setMenge(Double menge) {
        this.menge = menge;
    }

    public String getMeins() {
        return meins;
    }

    public void setMeins(String meins) {
        this.meins = meins;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
