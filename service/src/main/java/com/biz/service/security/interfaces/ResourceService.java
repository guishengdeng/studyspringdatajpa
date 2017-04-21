package com.biz.service.security.interfaces;

import com.biz.gbck.dao.mysql.po.security.Resource;

import java.util.List;

/**
 * ResourceService
 *
 * @author guisheng.deng
 * @date 2017年04月20日
 * @reviewer
 * @description
 * @see
 */
public interface ResourceService  {
    void  addOrUpdate(Resource resource);
    List<Resource> listResources();
    Resource getResource(Long id);
    void delete(Long id);
}
