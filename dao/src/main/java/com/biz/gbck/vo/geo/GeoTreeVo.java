package com.biz.gbck.vo.geo;


/**
 * Created by JKLiues on 4/21/16.
 */
public class GeoTreeVo {

    public String id;

    public String parent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String text;

    public Integer state;
}
