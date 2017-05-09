package com.biz.gbck.vo.order.req;

import com.biz.gbck.enums.order.ReturnCause;
import com.biz.gbck.enums.order.ReturnType;
import com.biz.gbck.vo.user.BaseRequestVo;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import static com.google.common.collect.Lists.newArrayList;

/**
 * 订单售后申请Vo
 *
 * @author lei
 * @date 2017年05月03日
 * @reviewer
 * @see
 */
public class OrderApplyReturnReqVo extends BaseRequestVo {

    private static final long serialVersionUID = -1931418367038896879L;

    /**
     * 订单id
     */
    @NotNull(message = "订单Id不能为空")
    private Long orderId;

    /**
     * 售后类型{@link ReturnType}
     */
    @NotNull(message = "售后类型不能为空")
    private Integer type;

    /**
     * 售后原因{@link ReturnCause}
     */
    @NotNull(message = "售后原因不能为空")
    private Integer cause;

    /**
     * 问题描述
     */
    @Length(max = 255, message = "问题描述过长")
    private String description;

    /**
     * 图片
     */
    @Size(max = 10)
    private List<String> images = newArrayList();

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCause() {
        return cause;
    }

    public void setCause(Integer cause) {
        this.cause = cause;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
