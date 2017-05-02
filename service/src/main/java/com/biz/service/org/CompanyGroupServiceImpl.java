package com.biz.service.org;

import com.biz.core.util.CollectionUtil;
import com.biz.gbck.dao.mysql.po.org.Company;
import com.biz.gbck.dao.mysql.po.org.CompanyGroupPo;
import com.biz.gbck.dao.mysql.repository.org.CompanyGroupRepository;
import com.biz.gbck.dao.mysql.repository.org.CompanyRepository;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.vo.org.CompanyGroupReqVo;
import com.biz.service.AbstractRepositorySupportService;
import com.biz.service.org.interfaces.CompanyGroupService;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static org.codelogger.utils.ExceptionUtils.iae;

/**
 * @author: liubin
 * @date 4/26/17 17:30
 */
@Service
public class CompanyGroupServiceImpl extends AbstractRepositorySupportService<CompanyGroupPo> implements CompanyGroupService {

    @Autowired
    private CompanyGroupRepository companyGroupRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    protected CommonJpaRepository<CompanyGroupPo, Long> getRepository() {
        return companyGroupRepository;
    }


    @Override
    public List<CompanyGroupPo> findByStatus(CommonStatusEnum status) {
        return companyGroupRepository.findByStatus(status);
    }

    @Override
    public CompanyGroupPo findOne(Long id) {
        return companyGroupRepository.findOne(id);
    }

    @Override
    @Transactional
    public CompanyGroupPo saveOrUpdate(CompanyGroupReqVo vo) {

        iae.throwIfNull(vo, "参数不能为空");

        CompanyGroupPo po = new CompanyGroupPo();

        if (vo.getId() == null) {
            po.setId(idService.nextId());
        } else {
            po = companyGroupRepository.findOne(vo.getId());
        }
        po.setName(vo.getName());
        po.setCode(vo.getCode());

        List<Company> companies = CollectionUtil.collectionAsStream(vo.getChildrenIds())
                .map(childId -> companyRepository.findOne(childId)).collect(Collectors.toList());
        po.setChildren(companies);
//        po.setChildrenLevel();
//        po.setParent();
        po.setStatus(CommonStatusEnum.ENABLE);

        return po;
    }

    @Override
    @Transactional
    public void remove(Long id) {
        companyGroupRepository.updateStatus(id, CommonStatusEnum.DISABLE);
    }
}
