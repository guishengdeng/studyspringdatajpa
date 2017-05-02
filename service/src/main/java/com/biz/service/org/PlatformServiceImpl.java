package com.biz.service.org;

import com.biz.gbck.dao.mysql.po.org.PlatformPo;
import com.biz.gbck.dao.mysql.repository.org.PlatformRepository;
import com.biz.service.AbstractBaseService;
import com.biz.service.org.interfaces.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author: liubin
 * @date 5/2/17 10:10
 */
public class PlatformServiceImpl extends AbstractBaseService implements PlatformService{

    @Autowired
    private PlatformRepository platformRepository;


    @Override
    public List<PlatformPo> findAll() {
        return platformRepository.findAll();
    }

    @Override
    public PlatformPo findOne(Long id) {
        return platformRepository.findOne(id);
    }
}
