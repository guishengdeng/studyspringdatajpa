package com.biz.service.payment.interf;

import com.biz.gbck.dao.mysql.po.payment.WechatPaymentLogPo;
import com.biz.gbck.vo.payment.pay.AlipayPaymentVo;
import org.springframework.data.domain.Page;

/**
 * Created by lzz on 2017/5/15.
 */
public interface WechatPaymentService {
    Page<WechatPaymentLogPo> findList(AlipayPaymentVo.WechatPaymentVo wechatPaymentVo);
}
