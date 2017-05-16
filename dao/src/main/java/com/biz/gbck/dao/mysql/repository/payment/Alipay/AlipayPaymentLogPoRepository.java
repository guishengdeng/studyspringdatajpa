package com.biz.gbck.dao.mysql.repository.payment.Alipay;

import com.biz.gbck.dao.mysql.po.payment.AlipayPaymentLogPo;
import com.biz.gbck.dao.mysql.po.payment.WechatPaymentLogPo;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * WechatPaymentLogPoRepository
 *
 * @author lei
 * @date 2017年05月15日
 * @reviewer
 * @see
 */
@Repository
public interface AlipayPaymentLogPoRepository extends CommonJpaRepository<AlipayPaymentLogPo, Long> , JpaSpecificationExecutor<AlipayPaymentLogPo> ,AlipayPaymentLogDao {

}

