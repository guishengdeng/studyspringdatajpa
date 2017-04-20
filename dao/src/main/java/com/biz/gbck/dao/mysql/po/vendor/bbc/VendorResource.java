package com.biz.gbck.dao.mysql.po.vendor.bbc;

/**
 * 店铺图片资源(以json格式存入数据库和 redis)
 *
 * @author yanweijin
 * @da7/3/17
 */
public class VendorResource {

    public static final String KEY_BANNER = "banner";

    private String key;

    private String description;

    private String imgPath;

    private String targetLink;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getTargetLink() {
        return targetLink;
    }

    public void setTargetLink(String targetLink) {
        this.targetLink = targetLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VendorResource)) return false;

        VendorResource that = (VendorResource) o;

        return key.equals(that.key);
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }
}
