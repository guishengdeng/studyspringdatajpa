package com.biz.gbck.vo.order.req;

import com.biz.gbck.dao.mysql.repository.org.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * OrderQueryReqVo
 *
 * @author guisheng.deng
 * @date 2017年05月18日
 * @reviewer
 * @description
 * @see
 */
public class
OrderQueryReqVo implements Serializable {
    private static final long serialVersionUID = 8027931950538646190L;

    private String  platFormCompanyName;
    private String  partnerName;
    //在没给查询条件时,默认是根据订单日期进行查询
    private String beginTimestamp = "2010/02/01 14:20:14";
    private String endTimestamp = "2017/05/31 16:19:42";
    private Long platFormId;

    public Long getPlatFormId() {
        return platFormId;
    }

    public void setPlatFormId(Long platFormId) {
        this.platFormId = platFormId;
    }

    @Min(1)
    private Integer page = 1;

    @Max(100)
    private Integer pageSize = 20;

    public String getBeginTimestamp() {
        return beginTimestamp;
    }

    public void setBeginTimestamp(String beginTimestamp) {
        this.beginTimestamp = beginTimestamp;
    }

    public String getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(String endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    public String getPlatFormCompanyName() {
        return platFormCompanyName;
    }

    public void setPlatFormCompanyName(String platFormCompanyName) {
        this.platFormCompanyName = platFormCompanyName;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
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
