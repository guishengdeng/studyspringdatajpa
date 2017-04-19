package com.biz.gbck.vo.warehouse.event;

import com.biz.gbck.vo.oms.event.OmsWarehouseEvent;
import com.biz.gbck.vo.warehouse.event.vo.UpdateWarehouseEventVo;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 省仓更新事件
 *
 * @author zhangcheng
 * @date 2017/1/10
 * @reviewer
 * @see
 */
public class UpdateWarehouseEvent extends OmsWarehouseEvent {

    private static final long serialVersionUID = -4459795637821683050L;

    private UpdateWarehouseEventVo updateWarehouseEventVo;

    public UpdateWarehouseEvent(Object source, UpdateWarehouseEventVo vo) {
        super(source);
        this.updateWarehouseEventVo = vo;
    }

    public UpdateWarehouseEventVo getUpdateWarehouseEventVo() {
        return updateWarehouseEventVo;
    }

    public void setUpdateWarehouseEventVo(UpdateWarehouseEventVo updateWarehouseEventVo) {
        this.updateWarehouseEventVo = updateWarehouseEventVo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
