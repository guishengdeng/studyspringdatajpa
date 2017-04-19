package com.biz.gbck.vo.upgrade;

import javax.validation.constraints.NotNull;

public class AddUpgradeVo implements java.io.Serializable {

    @NotNull private String os;

    @NotNull private boolean force;

    @NotNull private String version;

    @NotNull private String info;

    @NotNull private String url;

    @NotNull private String md5;

    @NotNull private Boolean inhourse;

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public boolean isForce() {
        return force;
    }

    public void setForce(boolean force) {
        this.force = force;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Boolean getInhourse() {
        return inhourse;
    }

    public void setInhourse(Boolean inhourse) {
        this.inhourse = inhourse;
    }



}
