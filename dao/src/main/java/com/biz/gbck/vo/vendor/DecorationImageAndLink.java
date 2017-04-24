package com.biz.gbck.vo.vendor;

import com.biz.gbck.vo.IRequestVo;

/**
 * 图片和超链接Vo
 *
 * @author LGJ
 */
public class DecorationImageAndLink implements IRequestVo {

    private String image;

    private String hyperlink;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHyperlink() {
        return hyperlink;
    }

    public void setHyperlink(String hyperlink) {
        this.hyperlink = hyperlink;
    }


}
