package com.biz.gbck.vo.product.frontend;

import java.io.Serializable;
import java.util.List;

/**
 * @author death
 * @date 2016年09月26日
 * @revirewer
 * @see
 */
public class AliRecDataVo implements Serializable {
    private static final long serialVersionUID = 9056944072820993227L;

    /**
     * 系统自动分配的任务id
     */
    private String trace_id;

    /**
     * 推荐列表,已排序
     */
    private List<List<String>> rec;

    /**
     * ABtest标签，即path_code
     */
    private String abtag;

    public String getTrace_id() {
        return trace_id;
    }

    public void setTrace_id(String trace_id) {
        this.trace_id = trace_id;
    }

    public List<List<String>> getRec() {
        return rec;
    }

    public void setRec(List<List<String>> rec) {
        this.rec = rec;
    }

    public String getAbtag() {
        return abtag;
    }

    public void setAbtag(String abtag) {
        this.abtag = abtag;
    }

    @Override
    public String toString() {
        return "AliRecDataVo{" +
                "trace_id='" + trace_id + '\'' +
                ", rec=" + rec +
                ", abtag='" + abtag + '\'' +
                '}';
    }
}
