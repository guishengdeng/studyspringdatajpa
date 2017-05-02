package com.biz.service.org.interfaces;

import com.biz.gbck.dao.mysql.po.org.CompanyGroupPo;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.vo.org.CompanyGroupReqVo;

import java.util.List;

/**
 * @author: liubin
 * @date 4/26/17 17:30
 */
public interface CompanyGroupService {

    List<CompanyGroupPo> findByStatus(CommonStatusEnum status);

    CompanyGroupPo findOne(Long id);

    CompanyGroupPo saveOrUpdate(CompanyGroupReqVo vo);

    void remove(Long id);

}

