package com.biz.soa.dao.db.mysql.repository;


import com.biz.soa.dao.db.mysql.po.MnsGeoMessage;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lei
 * @date 2016年12月22日
 * @reviewer
 * @see
 */
@Repository
public interface MnsGeoMessageRepository extends CommonJpaRepository<MnsGeoMessage, Long> {

}
