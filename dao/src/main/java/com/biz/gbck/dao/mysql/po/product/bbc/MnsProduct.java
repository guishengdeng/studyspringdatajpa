package com.biz.gbck.dao.mysql.po.product.bbc;

import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.support.jpa.po.BaseEntity;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 中台商品主数据Po
 *
 * @author zhangcheng
 * @date 2017/1/5
 * @reviewer
 * @see
 */
//@Entity
//@Table(name = "mns_product")
public class MnsProduct extends BaseEntity {

    private static final long serialVersionUID = 4113485603284449827L;

    /**
     * 商品编码
     */
    @Column(length = 18)
    private String matnr;

    /**
     * 商品描述
     */
    @Column(length = 50)
    private String maktx;

    /**
     * 基本单位
     */
    @Column(length = 3)
    private String meins;

    /**
     * 净重
     */
    @Column(length = 13)
    private String brgew;

    /**
     * 毛重
     */
    @Column(length = 13)
    private String ntgew;

    /**
     * 重量单位
     */
    @Column(length = 3)
    private String gewei;

    /**
     * 体积
     */
    @Column(length = 13)
    private String volum;

    /**
     * 体积单位
     */
    @Column(length = 3)
    private String voleh;

    /**
     * 规格
     */
    @Column(length = 32)
    private String groes;

    /**
     * 商品主条码
     */
    @Column(length = 18)
    private String ean11;

    /**
     * 商品大类编码
     */
    @Column(length = 18)
    private String bclass;

    /**
     * 商品小类编码
     */
    @Column(length = 40)
    private String sclass;

    /**
     * 保质期
     */
    @Column(length = 4)
    private String mhdhb;

    /**
     * 是否二维码
     */
    @Column(length = 1)
    private String zzewm;

    /**
     * 配送模式
     */
    @Column(length = 4)
    private String zzpsms;

    /**
     * 商品部门
     */
    @Column(length = 4)
    private String zzspbm;

    /**
     * 商品属性
     */
    @Column(length = 4)
    private String zzspsx;

    /**
     * 零售价定价模式
     */
    @Column(length = 4)
    private String zzlsdj;

    /**
     * 会员价定价模式
     */
    @Column(length = 4)
    private String zzhydj;

    /**
     * 商品毛利份数
     */
    @Column(length = 50)
    private String zzmlfs;

    /**
     * 市调计价计算百分比
     */
    @Column(length = 13)
    private String zzsdbl;

    /**
     * 最小补货数量
     */
    @Column(length = 13)
    private String bstrf;

    /**
     * 品牌类别
     */
    @Column(length = 40)
    private String zzbrand;

    /**
     * 产国
     */
    @Column(length = 30)
    private String zzcountry;

    /**
     * 产区
     */
    @Column(length = 40)
    private String zzregion;

    /**
     * 等级
     */
    @Column(length = 40)
    private String zzclass;

    /**
     * 葡萄品质
     */
    @Column(length = 40)
    private String zzgrape;

    /**
     * 删除标识
     */
    @Column(length = 1)
    private String loevm;

    /**
     * 最后更改时间
     */
    private Timestamp lastmodifytime;

    /**
     * 中台商品状态(默认转态为正常)
     */
    private CommonStatusEnum status = CommonStatusEnum.ENABLE;

    /**
     * 以下属性为原MNS接入文档属性(予以保留)
     */
    @Column(length = 4)
    private String qty;

    @Column(length = 4)
    private String degree;

    @Column(length = 8)
    private String tag;

    @Column(length = 10)
    private String material;

    @Column(length = 10)
    private String craft;

    @Column(length = 10)
    private String smell;

    @Column(length = 1)
    private String isunique;

    @Column(length = 4)
    private String grapeyear;

    @Column(length = 10)
    private String grapevarity;

    @Column(length = 4)
    private String mznd;

    public CommonStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CommonStatusEnum status) {
        this.status = status;
    }

    public String getMatnr() {
        return matnr == null ? "" : matnr;
    }

    public void setMatnr(String matnr) {
        this.matnr = matnr;
    }

    public String getMaktx() {
        return maktx == null ? "" : maktx;
    }

    public void setMaktx(String maktx) {
        this.maktx = maktx;
    }

    public String getMeins() {
        return meins == null ? "" : meins;
    }

    public void setMeins(String meins) {
        this.meins = meins;
    }

    public String getBrgew() {
        return brgew == null ? "" : brgew;
    }

    public void setBrgew(String brgew) {
        this.brgew = brgew;
    }

    public String getNtgew() {
        return ntgew == null ? "" : ntgew;
    }

    public void setNtgew(String ntgew) {
        this.ntgew = ntgew;
    }

    public String getGewei() {
        return gewei == null ? "" : gewei;
    }

    public void setGewei(String gewei) {
        this.gewei = gewei;
    }

    public String getVolum() {
        return volum == null ? "" : volum;
    }

    public void setVolum(String volum) {
        this.volum = volum;
    }

    public String getVoleh() {
        return voleh == null ? "" : voleh;
    }

    public void setVoleh(String voleh) {
        this.voleh = voleh;
    }

    public String getGroes() {
        return groes == null ? "" : groes;
    }

    public void setGroes(String groes) {
        this.groes = groes;
    }

    public String getEan11() {
        return ean11 == null ? "" : ean11;
    }

    public void setEan11(String ean11) {
        this.ean11 = ean11;
    }

    public String getBclass() {
        return bclass == null ? "" : bclass;
    }

    public void setBclass(String bclass) {
        this.bclass = bclass;
    }

    public String getSclass() {
        return sclass == null ? "" : sclass;
    }

    public void setSclass(String sclass) {
        this.sclass = sclass;
    }

    public String getMhdhb() {
        return mhdhb == null ? "" : mhdhb;
    }

    public void setMhdhb(String mhdhb) {
        this.mhdhb = mhdhb;
    }

    public String getZzewm() {
        return zzewm == null ? "" : zzewm;
    }

    public void setZzewm(String zzewm) {
        this.zzewm = zzewm;
    }

    public String getZzpsms() {
        return zzpsms == null ? "" : zzpsms;
    }

    public void setZzpsms(String zzpsms) {
        this.zzpsms = zzpsms;
    }

    public String getZzspbm() {
        return zzspbm == null ? "" : zzspbm;
    }

    public void setZzspbm(String zzspbm) {
        this.zzspbm = zzspbm;
    }

    public String getZzspsx() {
        return zzspsx == null ? "" : zzspsx;
    }

    public void setZzspsx(String zzspsx) {
        this.zzspsx = zzspsx;
    }

    public String getZzlsdj() {
        return zzlsdj == null ? "" : zzlsdj;
    }

    public void setZzlsdj(String zzlsdj) {
        this.zzlsdj = zzlsdj;
    }

    public String getZzhydj() {
        return zzhydj == null ? "" : zzhydj;
    }

    public void setZzhydj(String zzhydj) {
        this.zzhydj = zzhydj;
    }

    public String getZzmlfs() {
        return zzmlfs == null ? "" : zzmlfs;
    }

    public void setZzmlfs(String zzmlfs) {
        this.zzmlfs = zzmlfs;
    }

    public String getZzsdbl() {
        return zzsdbl == null ? "" : zzsdbl;
    }

    public void setZzsdbl(String zzsdbl) {
        this.zzsdbl = zzsdbl;
    }

    public String getBstrf() {
        return bstrf == null ? "" : bstrf;
    }

    public void setBstrf(String bstrf) {
        this.bstrf = bstrf;
    }

    public String getZzbrand() {
        return zzbrand == null ? "" : zzbrand;
    }

    public void setZzbrand(String zzbrand) {
        this.zzbrand = zzbrand;
    }

    public String getZzcountry() {
        return zzcountry == null ? "" : zzcountry;
    }

    public void setZzcountry(String zzcountry) {
        this.zzcountry = zzcountry;
    }

    public String getZzregion() {
        return zzregion == null ? "" : zzregion;
    }

    public void setZzregion(String zzregion) {
        this.zzregion = zzregion;
    }

    public String getZzclass() {
        return zzclass == null ? "" : zzclass;
    }

    public void setZzclass(String zzclass) {
        this.zzclass = zzclass;
    }

    public String getZzgrape() {
        return zzgrape == null ? "" : zzgrape;
    }

    public void setZzgrape(String zzgrape) {
        this.zzgrape = zzgrape;
    }

    public String getLoevm() {
        return loevm == null ? "" : loevm;
    }

    public void setLoevm(String loevm) {
        this.loevm = loevm;
    }

    public String getQty() {
        return qty == null ? "" : qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getDegree() {
        return degree == null ? "" : degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getTag() {
        return tag == null ? "" : tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getMaterial() {
        return material == null ? "" : material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getCraft() {
        return craft == null ? "" : craft;
    }

    public void setCraft(String craft) {
        this.craft = craft;
    }

    public String getSmell() {
        return smell == null ? "" : smell;
    }

    public void setSmell(String smell) {
        this.smell = smell;
    }

    public String getIsunique() {
        return isunique == null ? "" : isunique;
    }

    public void setIsunique(String isunique) {
        this.isunique = isunique;
    }

    public String getGrapeyear() {
        return grapeyear == null ? "" : grapeyear;
    }

    public void setGrapeyear(String grapeyear) {
        this.grapeyear = grapeyear;
    }

    public String getGrapevarity() {
        return grapevarity == null ? "" : grapevarity;
    }

    public void setGrapevarity(String grapevarity) {
        this.grapevarity = grapevarity;
    }

    public String getMznd() {
        return mznd == null ? "" : mznd;
    }

    public void setMznd(String mznd) {
        this.mznd = mznd;
    }

    public Timestamp getLastmodifytime() {
        return lastmodifytime;
    }

    public void setLastmodifytime(Timestamp lastmodifytime) {
        this.lastmodifytime = lastmodifytime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        MnsProduct that = (MnsProduct) o;

        if (matnr != null ? !matnr.equals(that.matnr) : that.matnr != null)
            return false;
        if (maktx != null ? !maktx.equals(that.maktx) : that.maktx != null)
            return false;
        if (meins != null ? !meins.equals(that.meins) : that.meins != null)
            return false;
        if (brgew != null ? !brgew.equals(that.brgew) : that.brgew != null)
            return false;
        if (ntgew != null ? !ntgew.equals(that.ntgew) : that.ntgew != null)
            return false;
        if (gewei != null ? !gewei.equals(that.gewei) : that.gewei != null)
            return false;
        if (volum != null ? !volum.equals(that.volum) : that.volum != null)
            return false;
        if (voleh != null ? !voleh.equals(that.voleh) : that.voleh != null)
            return false;
        if (groes != null ? !groes.equals(that.groes) : that.groes != null)
            return false;
        if (ean11 != null ? !ean11.equals(that.ean11) : that.ean11 != null)
            return false;
        if (bclass != null ? !bclass.equals(that.bclass) : that.bclass != null)
            return false;
        if (sclass != null ? !sclass.equals(that.sclass) : that.sclass != null)
            return false;
        if (mhdhb != null ? !mhdhb.equals(that.mhdhb) : that.mhdhb != null)
            return false;
        if (zzewm != null ? !zzewm.equals(that.zzewm) : that.zzewm != null)
            return false;
        if (zzpsms != null ? !zzpsms.equals(that.zzpsms) : that.zzpsms != null)
            return false;
        if (zzspbm != null ? !zzspbm.equals(that.zzspbm) : that.zzspbm != null)
            return false;
        if (zzspsx != null ? !zzspsx.equals(that.zzspsx) : that.zzspsx != null)
            return false;
        if (zzlsdj != null ? !zzlsdj.equals(that.zzlsdj) : that.zzlsdj != null)
            return false;
        if (zzhydj != null ? !zzhydj.equals(that.zzhydj) : that.zzhydj != null)
            return false;
        if (zzmlfs != null ? !zzmlfs.equals(that.zzmlfs) : that.zzmlfs != null)
            return false;
        if (zzsdbl != null ? !zzsdbl.equals(that.zzsdbl) : that.zzsdbl != null)
            return false;
        if (bstrf != null ? !bstrf.equals(that.bstrf) : that.bstrf != null)
            return false;
        if (zzbrand != null ? !zzbrand.equals(that.zzbrand) : that.zzbrand != null)
            return false;
        if (zzcountry != null ? !zzcountry.equals(that.zzcountry) : that.zzcountry != null)
            return false;
        if (zzregion != null ? !zzregion.equals(that.zzregion) : that.zzregion != null)
            return false;
        if (zzclass != null ? !zzclass.equals(that.zzclass) : that.zzclass != null)
            return false;
        if (zzgrape != null ? !zzgrape.equals(that.zzgrape) : that.zzgrape != null)
            return false;
        if (loevm != null ? !loevm.equals(that.loevm) : that.loevm != null)
            return false;
        if (qty != null ? !qty.equals(that.qty) : that.qty != null)
            return false;
        if (degree != null ? !degree.equals(that.degree) : that.degree != null)
            return false;
        if (tag != null ? !tag.equals(that.tag) : that.tag != null)
            return false;
        if (material != null ? !material.equals(that.material) : that.material != null)
            return false;
        if (craft != null ? !craft.equals(that.craft) : that.craft != null)
            return false;
        if (smell != null ? !smell.equals(that.smell) : that.smell != null)
            return false;
        if (isunique != null ? !isunique.equals(that.isunique) : that.isunique != null)
            return false;
        if (grapeyear != null ? !grapeyear.equals(that.grapeyear) : that.grapeyear != null)
            return false;
        if (grapevarity != null ? !grapevarity.equals(that.grapevarity) : that.grapevarity != null)
            return false;
        if (mznd != null ? !mznd.equals(that.mznd) : that.mznd != null)
            return false;
        return lastmodifytime != null ? lastmodifytime.equals(that.lastmodifytime) : that.lastmodifytime == null;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
