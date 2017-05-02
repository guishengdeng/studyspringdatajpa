package com.biz.gbck.vo.saletage;

import com.biz.gbck.enums.CommonStatusEnum;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * 销售状态
 * Created by lzz on 2017/5/2.
 */
public class SaleTageVo implements Serializable {


    private static final long serialVersionUID = -1990977475077000107L;

    /*
    *标签名
    */
    private String tagName;

    /*
     *前台展示
     */
    private String showName;

    /*
     *标签
     */
    private String tag;

    /*
     *品牌排序
     */
    private Long idx;

    /*
     *后台备注
     */
    private String note;

    /*
     *启用状态
     */
    private String saleStatus;

    /*
     *状态(是否删除状态)
     */
    private CommonStatusEnum status;

    @Min(1)
    private Integer page = 1;

    @Max(100)
    private Integer pageSize = 20;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Long getIdx() {
        return idx;
    }

    public void setIdx(Long idx) {
        this.idx = idx;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(String saleStatus) {
        this.saleStatus = saleStatus;
    }

    public CommonStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CommonStatusEnum status) {
        this.status = status;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
