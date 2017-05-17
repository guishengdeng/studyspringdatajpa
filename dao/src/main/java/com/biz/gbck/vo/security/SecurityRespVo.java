package com.biz.gbck.vo.security;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.google.common.collect.Sets.newTreeSet;

/**
 * Created by defei on 6/30/15.
 */
public class SecurityRespVo {

    private Set<SecurityReqVo> requestTreeSet = newTreeSet();

    public Integer getVisitCountBeforeNow(TimeUnit timeUnit, Integer numberOfRange) {

        int count = 0;
        long currentTimeMillis = System.currentTimeMillis();
        long range = timeUnit.toMillis(numberOfRange);
        for (SecurityReqVo securityReqVo : requestTreeSet) {
            if (currentTimeMillis - securityReqVo.getTimestamp() < range) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    public void addSecurityReqVo(SecurityReqVo securityReqVo) {
        if (securityReqVo != null)
            requestTreeSet.add(securityReqVo);
    }
}
