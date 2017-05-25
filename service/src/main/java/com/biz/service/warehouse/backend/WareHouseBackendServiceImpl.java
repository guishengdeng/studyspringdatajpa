package com.biz.service.warehouse.backend;

import com.biz.core.util.CollectionUtil;
import com.biz.gbck.dao.mysql.po.org.WarehousePo;
import com.biz.gbck.dao.mysql.repository.org.WareHouseRepository;
import com.biz.gbck.enums.org.CompanyLevel;
import com.biz.gbck.vo.warehouse.MnsWarehouseVo;
import com.biz.gbck.vo.warehouse.WarehouseResponseVo;
import com.biz.service.org.interfaces.PlatformService;
import com.biz.service.partner.interfaces.PartnerService;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * WareHouseBackendServiceImpl
 *
 * @author guisheng.deng
 * @date 2017年05月25日
 * @reviewer
 * @description
 * @see
 */
@Service("wareHouseBackendServiceImpl")
public class WareHouseBackendServiceImpl implements WarehouseBackendService {
    @Autowired
    private WareHouseRepository wareHouseRepository;
    @Autowired
    private PartnerService partnerService;
    @Autowired
    private PlatformService platformService;
    @Override
    public void trans(MnsWarehouseVo mnsWarehouseVo) {

    }

    @Override
    public List<WarehouseResponseVo> getRespVoByCompanyLevel(CompanyLevel companyLevel) {
        if(companyLevel.equals(CompanyLevel.ORG_WAREHOUSE)){
            List<WarehouseResponseVo> voList = Lists.newArrayList();
            List<WarehousePo> poList = wareHouseRepository.findWareHousesByCompanyLevel(companyLevel);
            if(CollectionUtils.isNotEmpty(poList)){
                for(WarehousePo item : poList){
                    WarehouseResponseVo vo = new WarehouseResponseVo();
                    vo.setId(item.getId());
                    vo.setName(item.getName());
                    vo.setCompanyLevel(companyLevel);
                    voList.add(vo);
                }
            }
            return voList;
        }else if(companyLevel.equals(CompanyLevel.ORG_PLATFORM)){
             return platformService.getRespVoByCompanyLevel(companyLevel);
        }else{
             return partnerService.getRespVoByCompanyLevel(companyLevel);
        }

    }
}