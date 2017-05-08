package com.biz.gbck.vo;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * @author lei
 * @date 2017年05月07日
 * @reviewer
 */
public class PageRespVo implements Serializable {
    private static final long serialVersionUID = -5113775306566869929L;

    /**
     * 当前页
     */
    private Integer page;

    //集合
    private List<?> list = Lists.newArrayList();

    public PageRespVo(Integer page, List<?> list) {
        this.page = page;
        this.list = list;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
