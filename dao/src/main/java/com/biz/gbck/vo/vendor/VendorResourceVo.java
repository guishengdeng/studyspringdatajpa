package com.biz.gbck.vo.vendor;

import com.biz.gbck.vo.IRequestVo;

public class VendorResourceVo implements IRequestVo {

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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


}
