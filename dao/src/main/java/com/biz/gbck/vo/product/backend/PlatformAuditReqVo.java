package com.biz.gbck.vo.product.backend;

import com.biz.gbck.enums.product.ProductAuditStatusEnum;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 平台端审核商家商品请求 Vo
 *
 * @author david-liu
 * @date 2016年12月26日
 * @reviewer
 * @see
 */
public class PlatformAuditReqVo implements Serializable {

    private static final long serialVersionUID = -2894445013677237113L;

    /**
     * 操作人信息
     */
    private String admin;

    /**
     * 商品审核记录 ID
     */
    private Long productAuditId;

    /**
     * 商品审核状态
     */
    private ProductAuditStatusEnum auditStatus;

    /**
     * 审核备注
     */
    private String auditMessage;

    public Long getProductAuditId() {
        return productAuditId;
    }

    public void setProductAuditId(Long productAuditId) {
        this.productAuditId = productAuditId;
    }

    public ProductAuditStatusEnum getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(ProductAuditStatusEnum auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditMessage() {
        return auditMessage;
    }

    public void setAuditMessage(String auditMessage) {
        this.auditMessage = auditMessage;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
