package com.biz.gbck.vo.product.backend;

import com.biz.gbck.enums.CommonStatusEnum;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 分类列表项 Vo(后台用)
 *
 * @author david-liu
 * @date 2016年12月21日
 * @reviewer
 * @see
 */
public class CategoryListItemVo implements Serializable {

    private static final long serialVersionUID = 7188668934280030876L;

    private String id;

    private String name;

    private Integer idx;

    private String createTimeStamp;

    private CommonStatusEnum status;

    private String showDel;

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

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public String getCreateTimeStamp() {
        return createTimeStamp;
    }

    public void setCreateTimeStamp(String createTimeStamp) {
        this.createTimeStamp = createTimeStamp;
    }

    public CommonStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CommonStatusEnum status) {
        this.status = status;
    }

    public String getShowDel() {
        return showDel;
    }

    public void setShowDel(String showDel) {
        this.showDel = showDel;
    }

}
