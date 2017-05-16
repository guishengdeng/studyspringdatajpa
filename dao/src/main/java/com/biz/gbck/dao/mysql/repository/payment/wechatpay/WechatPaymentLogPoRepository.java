package com.biz.gbck.dao.mysql.repository.payment.wechatpay;

import com.biz.gbck.dao.mysql.po.payment.WechatPaymentLogPo;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
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
public interface WechatPaymentLogPoRepository extends CommonJpaRepository<WechatPaymentLogPo, Long> ,JpaSpecificationExecutor<WechatPaymentLogPo> , WechatPaymentLogDao {

}

