package com.biz.service.org;

import com.biz.gbck.dao.mysql.po.org.CompanyGroupPo;
import com.biz.gbck.dao.mysql.repository.org.CompanyGroupRepository;
import com.biz.service.AbstractRepositorySupportService;
import com.biz.service.org.interfaces.CompanyGroupService;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: liubin
 * @date 4/26/17 17:30
 */
public class CompanyGroupServiceImpl extends AbstractRepositorySupportService<CompanyGroupPo> implements CompanyGroupService {

    @Autowired
    private CompanyGroupRepository companyGroupRepository;

    @Override
    protected CommonJpaRepository<CompanyGroupPo, Long> getRepository() {
        return companyGroupRepository;
    }






}
