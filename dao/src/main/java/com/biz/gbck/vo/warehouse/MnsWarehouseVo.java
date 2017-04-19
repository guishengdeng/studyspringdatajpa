package com.biz.gbck.vo.warehouse;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 中台省仓信息Vo
 *
 * @author zhangcheng
 * @date 2017/1/6
 * @reviewer
 * @see
 */
public class MnsWarehouseVo implements Serializable {

    private static final long serialVersionUID = 6356280923281890062L;

    /**
     * 类型
     */
    private String title;

    /**
     * 公司代码
     */
    private String bukrs;

    /**
     * 公司名称
     */
    private String butxt;

    /**
     * 仓库编号
     */
    private String werks;

    /**
     * 仓库描述
     */
    private String name1;

    /**
     * 仓库的客户号
     */
    private String kunnr;

    /**
     * 仓库的供应商号
     */
    private String lifnr;

    /**
     * 省编码
     */
    private String regio;

    /**
     * 国家名称
     */
    private String bezei_1;

    /**
     * 国家代码
     */
    private String cityc;

    /**
     * 省名称
     */
    private String bezei_2;

    /**
     * 区/县编码
     */
    private String transpzone;

    /**
     * 区/县名称
     */
    private String vtext;

    /**
     * 最后更新时间
     */
    private String updateTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBukrs() {
        return bukrs;
    }

    public void setBukrs(String bukrs) {
        this.bukrs = bukrs;
    }

    public String getButxt() {
        return butxt;
    }

    public void setButxt(String butxt) {
        this.butxt = butxt;
    }

    public String getWerks() {
        return werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getKunnr() {
        return kunnr;
    }

    public void setKunnr(String kunnr) {
        this.kunnr = kunnr;
    }

    public String getLifnr() {
        return lifnr;
    }

    public void setLifnr(String lifnr) {
        this.lifnr = lifnr;
    }

    public String getRegio() {
        return regio;
    }

    public void setRegio(String regio) {
        this.regio = regio;
    }

    public String getBezei_1() {
        return bezei_1;
    }

    public void setBezei_1(String bezei_1) {
        this.bezei_1 = bezei_1;
    }

    public String getCityc() {
        return cityc;
    }

    public void setCityc(String cityc) {
        this.cityc = cityc;
    }

    public String getBezei_2() {
        return bezei_2;
    }

    public void setBezei_2(String bezei_2) {
        this.bezei_2 = bezei_2;
    }

    public String getTranspzone() {
        return transpzone;
    }

    public void setTranspzone(String transpzone) {
        this.transpzone = transpzone;
    }

    public String getVtext() {
        return vtext;
    }

    public void setVtext(String vtext) {
        this.vtext = vtext;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
