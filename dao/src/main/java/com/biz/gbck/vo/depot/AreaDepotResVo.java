package com.biz.gbck.vo.depot;

import java.io.Serializable;
import java.util.List;

/**
 * 销售区域下的所有门店以及对应的省仓门店
 *
 * @author zhangcheng
 * @date 2017/2/6
 * @reviewer
 * @see
 */
public class AreaDepotResVo implements Serializable {

    private static final long serialVersionUID = -7745733215421462436L;

    /**
     * 该区域下所有门店集合
     */
    private List<DepotResponseVo> areaDepot;

    /**
     * 对应的省仓门店
     */
    private DepotResponseVo warehouseDepot;

    public List<DepotResponseVo> getAreaDepot() {
        return areaDepot;
    }

    public void setAreaDepot(List<DepotResponseVo> areaDepot) {
        this.areaDepot = areaDepot;
    }

    public DepotResponseVo getWarehouseDepot() {
        return warehouseDepot;
    }

    public void setWarehouseDepot(DepotResponseVo warehouseDepot) {
        this.warehouseDepot = warehouseDepot;
    }
}
