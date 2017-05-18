package com.biz.gbck.vo.config;

import java.util.Map;

/**
 * app启动上报返回商品类型参数
 * Created by dylan on 17-5-15.
 */
public class CategoryResVo {
    private String id;
    private String name; //类型名称
    private String url="/product/search.do"; //请求url
    private Map postData;//请求参数

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map getPostData() {
        return postData;
    }

    public void setPostData(Map postData) {
        this.postData = postData;
    }
}
