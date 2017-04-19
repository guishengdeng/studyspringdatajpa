package com.biz.gbck.vo.product.backend;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2017/2/9
 */
public class VendorCascadeListVo implements Serializable {

    private static final long serialVersionUID = 517410113424143638L;

    /**
     * 配置名称
     */
    private String cascadeName;

    /**
     * 配置ID
     */
    private String cascadeId;

    /**
     * 创建时间
     */
    private Timestamp createTimestamp;

    public VendorCascadeListVo(String cascadeName, String cascadeId, Timestamp createTimestamp) {
        this.cascadeName = cascadeName;
        this.cascadeId = cascadeId;
        this.createTimestamp = createTimestamp;
    }

    public VendorCascadeListVo() {
    }

    public String getCascadeName() {
        return cascadeName;
    }

    public void setCascadeName(String cascadeName) {
        this.cascadeName = cascadeName;
    }

    public String getCascadeId() {
        return cascadeId;
    }

    public void setCascadeId(String cascadeId) {
        this.cascadeId = cascadeId;
    }

    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }
}
