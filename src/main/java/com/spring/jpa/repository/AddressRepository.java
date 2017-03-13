package com.spring.jpa.repository;

import com.spring.jpa.model.Address;
import org.springframework.data.repository.Repository;

/**
 * AddressRepository
 *
 * @author guisheng.deng
 * @date 2017年03月10日
 * @reviewer
 * @description
 * @see
 */
public interface AddressRepository extends Repository<Address,Long> {
    //备注：save(Address var1)方法是直接从接口CrudRepository直接复制過來的
    void save(Address var1);
}
