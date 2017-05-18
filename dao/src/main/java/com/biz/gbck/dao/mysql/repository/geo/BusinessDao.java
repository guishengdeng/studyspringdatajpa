package com.biz.gbck.dao.mysql.repository.geo;


import com.biz.gbck.common.bean.PageControl;
import com.biz.gbck.dao.mysql.po.geo.BusinessPo;

import java.util.List;


public interface BusinessDao {

    public void findAll(PageControl pc);

    public int findAllCount();

    public List<BusinessPo> findAllBusinessByDistrictId(int findId);

    public BusinessPo update(BusinessPo businessPo);
}
