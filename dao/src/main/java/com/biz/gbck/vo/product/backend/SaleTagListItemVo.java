package com.biz.gbck.vo.product.backend;

import com.biz.gbck.enums.CommonStatusEnum;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 销售标签列表项 Vo(后台用)
 *
 * @author 江南
 * @usage
 * @reviewer
 * @since 2016/12/22
 */
public class SaleTagListItemVo implements Serializable {

    private static final long serialVersionUID = 7708721440964909171L;

    /**
     * ID
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 排序
     */
    private String idx;

    /**
     * 更新时间
     */
    private Timestamp createTimeStamp;

    /**
     * 状态
     */
    private CommonStatusEnum status;

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

    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

    public Timestamp getCreateTimeStamp() {
        return createTimeStamp;
    }

    public void setCreateTimeStamp(Timestamp createTimeStamp) {
        this.createTimeStamp = createTimeStamp;
    }

    public CommonStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CommonStatusEnum status) {
        this.status = status;
    }
}
