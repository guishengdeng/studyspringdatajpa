package com.biz.service.security;

import com.biz.gbck.dao.mysql.po.security.Resource;
import com.biz.gbck.dao.mysql.repository.admin.ResourceRepository;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.service.AbstractBaseService;
import com.biz.service.security.interfaces.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ResourceServiceImpl
 *
 * @author guisheng.deng
 * @date 2017年04月20日
 * @reviewer
 * @description
 * @see
 */
@Service
public class ResourceServiceImpl extends AbstractBaseService implements ResourceService {


    @Autowired
    private ResourceRepository resourceRepository;
    @Override
    public void addOrUpdate(Resource resource) {
        resourceRepository.save(resource);
    }

    @Override
    public List<Resource> listResources() {
        return resourceRepository.findAll();
    }

    @Override
    public Resource getResource(Long id) {
        return resourceRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        resourceRepository.updateStatus(id,CommonStatusEnum.DISABLE);
    }
    @Override
    public List<Resource> listByStatus(CommonStatusEnum status) {
        return resourceRepository.getByStatus(status);
    }
}