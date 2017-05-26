package com.biz.gbck.vo;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.builder.ToStringBuilder;

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
     * 最后访问标示
     */
    private String lastFlag;

    //集合
    private List<?> list = Lists.newArrayList();

    public PageRespVo(String lastFlag, List<?> list) {
        this.lastFlag = lastFlag;
        this.list = list;
    }

    public String getLastFlag() {
        return lastFlag;
    }

    public void setLastFlag(String lastFlag) {
        this.lastFlag = lastFlag;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
