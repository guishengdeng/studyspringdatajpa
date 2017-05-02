package com.biz.gbck.vo.zsgf;


import com.biz.gbck.dao.mysql.po.payment.ZsgfApplyStatus;

/**
 * Created by lenovo on 2016/7/18.
 */
public class ZsgfLoanQueryReqVo {

    public static final Integer DEFAULT_PAGESIZE = 50;

    public static final Integer DEFAULT_PAGE = 1;

    Integer page = DEFAULT_PAGE;

    Integer pageSize = DEFAULT_PAGESIZE;

//    Long id;

    ZsgfApplyStatus applyStatus;

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public ZsgfApplyStatus getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(ZsgfApplyStatus applyStatus) {
        this.applyStatus = applyStatus;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page > 0 ? page : DEFAULT_PAGE;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {

        this.pageSize = pageSize > 0 ? pageSize : DEFAULT_PAGESIZE;
    }
}
