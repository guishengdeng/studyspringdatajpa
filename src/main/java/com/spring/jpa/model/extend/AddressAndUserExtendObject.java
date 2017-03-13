package com.spring.jpa.model.extend;

import com.spring.jpa.model.Address;
import com.spring.jpa.model.User;

/**
 * AddressAndUserExtendObject
 *
 * @author guisheng.deng
 * @date 2017年03月10日
 * @reviewer
 * @description
 * @see
 */
public class AddressAndUserExtendObject {
    private User user;
    private Address address;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}