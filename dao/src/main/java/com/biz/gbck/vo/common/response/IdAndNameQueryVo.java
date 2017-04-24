package com.biz.gbck.vo.common.response;

import com.biz.gbck.vo.IRequestVo;

public class IdAndNameQueryVo implements IRequestVo {

    private String id;

    private String name;

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


}
