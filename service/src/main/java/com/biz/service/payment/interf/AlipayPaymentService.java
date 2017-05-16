package com.biz.service.payment.interf;

import com.biz.gbck.dao.mysql.po.payment.AlipayPaymentLogPo;
import com.biz.gbck.vo.payment.pay.AlipayPaymentVo;
import org.springframework.data.domain.Page;

/**
 * Created by lzz on 2017/5/15.
 */
public interface AlipayPaymentService {

  Page<AlipayPaymentLogPo> findList(AlipayPaymentVo reqVo);
}
