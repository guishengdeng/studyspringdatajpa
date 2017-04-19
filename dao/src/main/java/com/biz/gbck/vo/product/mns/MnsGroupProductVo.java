package com.biz.gbck.vo.product.mns;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 中台组合商品Vo
 *
 * @author zhangcheng
 * @date 2017/3/11
 * @reviewer
 * @see
 */
public class MnsGroupProductVo implements Serializable {

    private static final long serialVersionUID = -6074539658380380312L;

    /**
     * 包装号
     */
    private String bzno;

    /**
     * 开始日期
     */
    private String begda;

    /**
     * 结束日期
     */
    private String endda;

    /**
     * 组合商品描述
     */
    private String bztxt;

    /**
     * 商品定价百分比
     */
    private String perct;

    /**
     * 删除标示
     */
    private String loekz;

    /**
     * 组合商品明细
     */
    private List<MnsGroupProductItemVo> skus;

    public String getBzno() {
        return bzno;
    }

    public void setBzno(String bzno) {
        this.bzno = bzno;
    }

    public String getBegda() {
        return begda;
    }

    public void setBegda(String begda) {
        this.begda = begda;
    }

    public String getEndda() {
        return endda;
    }

    public void setEndda(String endda) {
        this.endda = endda;
    }

    public String getBztxt() {
        return bztxt;
    }

    public void setBztxt(String bztxt) {
        this.bztxt = bztxt;
    }

    public String getPerct() {
        return perct;
    }

    public void setPerct(String perct) {
        this.perct = perct;
    }

    public String getLoekz() {
        return loekz;
    }

    public void setLoekz(String loekz) {
        this.loekz = loekz;
    }

    public List<MnsGroupProductItemVo> getSkus() {
        return skus;
    }

    public void setSkus(List<MnsGroupProductItemVo> skus) {
        this.skus = skus;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
