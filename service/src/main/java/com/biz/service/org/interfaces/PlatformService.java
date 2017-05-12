package com.biz.service.org.interfaces;

import com.biz.gbck.dao.mysql.po.org.PlatformPo;

import java.util.List;

/**
 * @author: liubin
 * @date 5/2/17 10:10
 */
public interface PlatformService {

    List<PlatformPo> findAll();

    PlatformPo findOne(Long id);
}
