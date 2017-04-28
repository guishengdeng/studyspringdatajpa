package com.biz.gbck.vo.qrcode;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.biz.gbck.dao.mysql.po.qrcode.enums.BusinessStatusEnum;
import com.biz.gbck.enums.CommonStatusEnum;
/**
 * 二维码列表查询实体
 * @author xs
 *
 */
public class QRCodeSearchVO {
    /**
     * 二维码（瓶码or箱码）
     */
    private String bcno;

    /**
     * 业务状态
     */
    private BusinessStatusEnum busStatus;

    /**
     * 生命体征(可理解为是否删除)
     */
    private CommonStatusEnum status;

    @Min(1)
    private Integer page = 1;

    @Max(100)
    private Integer pageSize = 20;

    public String getBcno() {
        return bcno;
    }

    public void setBcno(String bcno) {
        this.bcno = bcno;
    }

    public BusinessStatusEnum getBusStatus() {
        return busStatus;
    }

    public void setBusStatus(BusinessStatusEnum busStatus) {
        this.busStatus = busStatus;
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
