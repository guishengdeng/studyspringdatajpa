package com.biz.service.warehouse.backend;


import com.biz.gbck.enums.org.CompanyLevel;
import com.biz.gbck.vo.warehouse.MnsWarehouseVo;
import com.biz.gbck.vo.warehouse.WarehouseResponseVo;

import java.util.List;

/**
 * 省仓后端Service
 *
 * @author zhangcheng
 * @date 2017/1/6
 * @reviewer
 * @see
 */
public interface WarehouseBackendService {

    /**
     * 转换中台省仓信息
     */
    void trans(MnsWarehouseVo mnsWarehouseVo);

    /**
     * 根据companyLevel查询省隔壁仓库
     * @param companyLevel
     * @return
     */
    List<WarehouseResponseVo> getRespVoByCompanyLevel(CompanyLevel companyLevel);

}
