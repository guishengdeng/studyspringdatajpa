package com.spring.jpa.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * GrantAuthorityImpl
 *
 * @author guisheng.deng
 * @date 2017年03月16日
 * @reviewer
 * @description
 * @see
 */
public class GrantAuthorityImpl implements GrantedAuthority {


    private String authority;

    public GrantAuthorityImpl(){

    }

    public GrantAuthorityImpl(String s) {
         authority=s;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}