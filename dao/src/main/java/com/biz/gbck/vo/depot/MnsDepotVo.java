package com.biz.gbck.vo.depot;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 中台门店信息Vo
 *
 * @author zhangcheng
 * @date 2016年12月12日
 * @reviewer
 * @see
 */
public class MnsDepotVo implements Serializable {

    private static final long serialVersionUID = 2036217829497421369L;

    /**
     * 门店编码
     */
    private String werks;

    /**
     * 门店类型
     */
    private String ze58sttp;

    /**
     * 门店级别
     */
    private String ze58rp01;

    /**
     * 省仓编码
     */
    private String loclb;

    /**
     * 门店名称
     */
    private String name1;

    /**
     * 经度
     */
    private String ze58longi;

    /**
     * 纬度
     */
    private String ze58lati;

    /**
     * 地址
     */
    private String stras;

    /**
     * 电话
     */
    private String tel_number;

    /**
     * 邮编
     */
    private String pstlz;

    /**
     * 房东姓名
     */
    private String ze58hus22;

    /**
     * 房东电话
     */
    private String ze58hus23;

    /**
     * 物管联系电话
     */
    private String tel_number1;

    /**
     * 是否可开增票
     */
    private String ze58huf02;

    /**
     * 是否关店
     */
    private String ze58scyn;

    /**
     * 区域
     */
    private String zclass;

    /**
     * 省
     */
    private String region;

    /**
     * 市
     */
    private String cityc;

    /**
     * 区
     */
    private String lzone;

    /**
     * 删除标识
     */
    private String loevm;

    /**
     * 营业开始时间
     */
    private String zberzet;

    /**
     * 营业结束时间
     */
    private String zeerzet;

    /**
     * 更新时间
     */
    private Date updateTime;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getWerks() {
        return werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }

    public String getZe58sttp() {
        return ze58sttp;
    }

    public void setZe58sttp(String ze58sttp) {
        this.ze58sttp = ze58sttp;
    }

    public String getZe58rp01() {
        return ze58rp01;
    }

    public void setZe58rp01(String ze58rp01) {
        this.ze58rp01 = ze58rp01;
    }

    public String getLoclb() {
        return loclb;
    }

    public void setLoclb(String loclb) {
        this.loclb = loclb;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getZe58longi() {
        return ze58longi;
    }

    public void setZe58longi(String ze58longi) {
        this.ze58longi = ze58longi;
    }

    public String getZe58lati() {
        return ze58lati;
    }

    public void setZe58lati(String ze58lati) {
        this.ze58lati = ze58lati;
    }

    public String getStras() {
        return stras;
    }

    public void setStras(String stras) {
        this.stras = stras;
    }

    public String getTel_number() {
        return tel_number;
    }

    public void setTel_number(String tel_number) {
        this.tel_number = tel_number;
    }

    public String getPstlz() {
        return pstlz;
    }

    public void setPstlz(String pstlz) {
        this.pstlz = pstlz;
    }

    public String getZe58hus22() {
        return ze58hus22;
    }

    public void setZe58hus22(String ze58hus22) {
        this.ze58hus22 = ze58hus22;
    }

    public String getZe58hus23() {
        return ze58hus23;
    }

    public void setZe58hus23(String ze58hus23) {
        this.ze58hus23 = ze58hus23;
    }

    public String getTel_number1() {
        return tel_number1;
    }

    public void setTel_number1(String tel_number1) {
        this.tel_number1 = tel_number1;
    }

    public String getZe58huf02() {
        return ze58huf02;
    }

    public void setZe58huf02(String ze58huf02) {
        this.ze58huf02 = ze58huf02;
    }

    public String getZe58scyn() {
        return ze58scyn;
    }

    public void setZe58scyn(String ze58scyn) {
        this.ze58scyn = ze58scyn;
    }

    public String getZclass() {
        return zclass;
    }

    public void setZclass(String zclass) {
        this.zclass = zclass;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCityc() {
        return cityc;
    }

    public void setCityc(String cityc) {
        this.cityc = cityc;
    }

    public String getLzone() {
        return lzone;
    }

    public void setLzone(String lzone) {
        this.lzone = lzone;
    }

    public String getLoevm() {
        return loevm;
    }

    public void setLoevm(String loevm) {
        this.loevm = loevm;
    }

    public String getZberzet() {
        return zberzet;
    }

    public void setZberzet(String zberzet) {
        this.zberzet = zberzet;
    }

    public String getZeerzet() {
        return zeerzet;
    }

    public void setZeerzet(String zeerzet) {
        this.zeerzet = zeerzet;
    }

    /**
     * 经度是否合法
     */
    public boolean isLonDisable() {
        return StringUtils.isBlank(this.getZe58longi()) || !NumberUtils.isNumber(this.getZe58longi()) || Long.valueOf
                (this.getZe58longi()) <= 0 || BigDecimal.valueOf(Long.valueOf(this.getZe58longi())).abs().compareTo
                (new BigDecimal("180")) > 0;
    }

    /**
     * 纬度是否合法
     */
    public boolean isLatDisable() {
        return StringUtils.isBlank(this.getZe58lati()) || !NumberUtils.isNumber(this.getZe58lati()) || Long.valueOf
                (this.getZe58lati()) <= 0 || BigDecimal.valueOf(Long.valueOf(this.getZe58lati())).abs().compareTo
                (new BigDecimal("180")) > 0;
    }

    public boolean isDisable() {
        return StringUtils.equalsIgnoreCase("x", this.loevm);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
