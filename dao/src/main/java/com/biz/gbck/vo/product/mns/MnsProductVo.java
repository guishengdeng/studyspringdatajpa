package com.biz.gbck.vo.product.mns;

import java.io.Serializable;
import java.sql.Timestamp;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 中台商品信息Vo
 *
 * @author zhangcheng
 * @date 2017/1/5
 * @reviewer
 * @see
 */
public class MnsProductVo implements Serializable {

    private static final long serialVersionUID = -5005054613985016074L;

    /**
     * 商品编码
     */
    private String matnr;

    /**
     * 商品描述
     */
    private String maktx;

    /**
     * 基本单位
     */
    private String meins;

    /**
     * 净重
     */
    private String brgew;

    /**
     * 毛重
     */
    private String ntgew;

    /**
     * 重量单位
     */
    private String gewei;

    /**
     * 体积
     */
    private String voulum;

    /**
     * 体积单位
     */
    private String voleh;

    /**
     * 规格
     */
    private String groes;

    /**
     * 商品主条码
     */
    private String ean11;

    /**
     * 商品大类编码
     */
    private String bclass;

    /**
     * 商品小类编码
     */
    private String sclass;

    /**
     * 保质期
     */
    private String mhdhb;

    /**
     * 是否二维码
     */
    private String zzewm;

    /**
     * 配送模式
     */
    private String zzpsms;

    /**
     * 商品部门
     */
    private String zzspbm;

    /**
     * 商品属性
     */
    private String zzspsx;

    /**
     * 零售价定价模式
     */
    private String zzlsdj;

    /**
     * 会员价定价模式
     */
    private String zzhydj;

    /**
     * 商品毛利份数
     */
    private String zzmlfs;

    /**
     * 市调计价计算百分比
     */
    private String zzsdbl;

    /**
     * 最小补货数量
     */
    private String bstrf;

    /**
     * 品牌类别
     */
    private String zzbrand;

    /**
     * 产国
     */
    private String zzcountry;

    /**
     * 产区
     */
    private String zzregion;

    /**
     * 等级
     */
    private String zzclass;

    /**
     * 葡萄品质
     */
    private String zzgrape;

    /**
     * 删除标识
     */
    private String loevm;

    /**
     * 最后更改时间
     */
    private Timestamp lastmodifytime;

    /**
     * 以下属性为原MNS对接文档中的属性(予以保留)
     */
    private String qty;

    private String degree;

    private String tag;

    private String material;

    private String craft;

    private String smell;

    private String isunique;

    private String grapeyear;

    private String grapevarity;

    private String mznd;

    public String getMatnr() {
        return matnr;
    }

    public void setMatnr(String matnr) {
        this.matnr = matnr;
    }

    public String getMaktx() {
        return maktx;
    }

    public void setMaktx(String maktx) {
        this.maktx = maktx;
    }

    public String getMeins() {
        return meins;
    }

    public void setMeins(String meins) {
        this.meins = meins;
    }

    public String getBrgew() {
        return brgew;
    }

    public void setBrgew(String brgew) {
        this.brgew = brgew;
    }

    public String getNtgew() {
        return ntgew;
    }

    public void setNtgew(String ntgew) {
        this.ntgew = ntgew;
    }

    public String getGewei() {
        return gewei;
    }

    public void setGewei(String gewei) {
        this.gewei = gewei;
    }

    public String getVoulum() {
        return voulum;
    }

    public void setVoulum(String voulum) {
        this.voulum = voulum;
    }

    public String getVoleh() {
        return voleh;
    }

    public void setVoleh(String voleh) {
        this.voleh = voleh;
    }

    public String getGroes() {
        return groes;
    }

    public void setGroes(String groes) {
        this.groes = groes;
    }

    public String getEan11() {
        return ean11;
    }

    public void setEan11(String ean11) {
        this.ean11 = ean11;
    }

    public String getBclass() {
        return bclass;
    }

    public void setBclass(String bclass) {
        this.bclass = bclass;
    }

    public String getSclass() {
        return sclass;
    }

    public void setSclass(String sclass) {
        this.sclass = sclass;
    }

    public String getMhdhb() {
        return mhdhb;
    }

    public void setMhdhb(String mhdhb) {
        this.mhdhb = mhdhb;
    }

    public String getZzewm() {
        return zzewm;
    }

    public void setZzewm(String zzewm) {
        this.zzewm = zzewm;
    }

    public String getZzpsms() {
        return zzpsms;
    }

    public void setZzpsms(String zzpsms) {
        this.zzpsms = zzpsms;
    }

    public String getZzspbm() {
        return zzspbm;
    }

    public void setZzspbm(String zzspbm) {
        this.zzspbm = zzspbm;
    }

    public String getZzspsx() {
        return zzspsx;
    }

    public void setZzspsx(String zzspsx) {
        this.zzspsx = zzspsx;
    }

    public String getZzlsdj() {
        return zzlsdj;
    }

    public void setZzlsdj(String zzlsdj) {
        this.zzlsdj = zzlsdj;
    }

    public String getZzhydj() {
        return zzhydj;
    }

    public void setZzhydj(String zzhydj) {
        this.zzhydj = zzhydj;
    }

    public String getZzmlfs() {
        return zzmlfs;
    }

    public void setZzmlfs(String zzmlfs) {
        this.zzmlfs = zzmlfs;
    }

    public String getZzsdbl() {
        return zzsdbl;
    }

    public void setZzsdbl(String zzsdbl) {
        this.zzsdbl = zzsdbl;
    }

    public String getBstrf() {
        return bstrf;
    }

    public void setBstrf(String bstrf) {
        this.bstrf = bstrf;
    }

    public String getZzbrand() {
        return zzbrand;
    }

    public void setZzbrand(String zzbrand) {
        this.zzbrand = zzbrand;
    }

    public String getZzcountry() {
        return zzcountry;
    }

    public void setZzcountry(String zzcountry) {
        this.zzcountry = zzcountry;
    }

    public String getZzregion() {
        return zzregion;
    }

    public void setZzregion(String zzregion) {
        this.zzregion = zzregion;
    }

    public String getZzclass() {
        return zzclass;
    }

    public void setZzclass(String zzclass) {
        this.zzclass = zzclass;
    }

    public String getZzgrape() {
        return zzgrape;
    }

    public void setZzgrape(String zzgrape) {
        this.zzgrape = zzgrape;
    }

    public String getLoevm() {
        return loevm;
    }

    public void setLoevm(String loevm) {
        this.loevm = loevm;
    }

    public Timestamp getLastmodifytime() {
        return lastmodifytime;
    }

    public void setLastmodifytime(Timestamp lastmodifytime) {
        this.lastmodifytime = lastmodifytime;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getCraft() {
        return craft;
    }

    public void setCraft(String craft) {
        this.craft = craft;
    }

    public String getSmell() {
        return smell;
    }

    public void setSmell(String smell) {
        this.smell = smell;
    }

    public String getIsunique() {
        return isunique;
    }

    public void setIsunique(String isunique) {
        this.isunique = isunique;
    }

    public String getGrapeyear() {
        return grapeyear;
    }

    public void setGrapeyear(String grapeyear) {
        this.grapeyear = grapeyear;
    }

    public String getGrapevarity() {
        return grapevarity;
    }

    public void setGrapevarity(String grapevarity) {
        this.grapevarity = grapevarity;
    }

    public String getMznd() {
        return mznd;
    }

    public void setMznd(String mznd) {
        this.mznd = mznd;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
