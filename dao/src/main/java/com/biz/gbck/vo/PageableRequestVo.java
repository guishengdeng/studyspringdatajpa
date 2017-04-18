package com.biz.gbck.vo;

import java.io.Serializable;

/**
 * @author yanweijin
 * @date 2016/12/19
 */
public class PageableRequestVo implements Serializable {

    private static final long serialVersionUID = -4766678678283424811L;

    private int page = 0;

    private int size = 10;

    public PageableRequestVo(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public PageableRequestVo() {
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
