package com.biz.gbck.dao.mysql.repository.payment;

import com.biz.gbck.dao.mysql.po.payment.WechatPaymentLogPo;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.stereotype.Repository;

/**
 * WechatPaymentLogPoRepository
 *
 * @author lei
 * @date 2017年05月15日
 * @reviewer
 * @see
 */
@Repository
public interface WechatPaymentLogPoRepository extends CommonJpaRepository<WechatPaymentLogPo, Long> {
}

