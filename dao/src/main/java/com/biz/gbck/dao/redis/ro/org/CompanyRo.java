package com.biz.gbck.dao.redis.ro.org;

import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.enums.org.CompanyLevel;
import com.biz.redis.annotation.Ro;
import com.biz.redis.bean.BaseRedisObject;

/**
 * @author: liubin
 * @date 4/25/17 11:48
 */
@Ro(key="org:CompanyRo")
public class CompanyRo extends BaseRedisObject<Long> {

    private String name;

    private String description;

    private CommonStatusEnum status;

    private Integer companyLevel;























}
