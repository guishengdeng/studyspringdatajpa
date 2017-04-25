package com.biz.gbck.dao.redis.ro.upgrade;

import com.biz.redis.bean.BaseRedisObject;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;


public class UpgradeBaseRo extends BaseRedisObject<String> implements Serializable{

    private String os;
    private boolean force = false;
    private String version;
    private String info;
    private String url;
    private String md5;
    private boolean inhourse = false;

    private boolean need = true;

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public boolean getForce() {
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

    public static String generateId(String os, String version) {
        return os + ":" + version;
    }

    public void generateId() {
      setId(generateId(os, version));
    }

    public boolean getNeed() {
        return need;
    }

    public void setNeed(boolean need) {
        this.need = need;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public boolean getInhourse() {
        return inhourse;
    }

    public void setInhourse(boolean inhourse) {
        this.inhourse = inhourse;
    }

    public static long versionToSroce(String version) {
        if (StringUtils.isNotBlank(version)) {
            if (version.indexOf(".") > 0) {
                String[] arr = version.split("\\.");
                if (arr != null && arr.length == 3) {
                    return Long.parseLong(arr[0]) * 1000000 + Integer.parseInt(arr[1]) * 1000
                        + Integer.parseInt(arr[2]);
                }
            } else {
                return Long.parseLong(version);
            }
        }
        return 0;
    }

    public long versionToSroce() {
        return versionToSroce(version);
    }


}
