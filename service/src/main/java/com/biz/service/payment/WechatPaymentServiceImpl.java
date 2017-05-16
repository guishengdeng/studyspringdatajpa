package com.biz.service.payment;

import com.biz.gbck.dao.mysql.po.payment.WechatPaymentLogPo;
import com.biz.gbck.dao.mysql.repository.payment.wechatpay.WechatPaymentLogPoRepository;
import com.biz.gbck.vo.payment.WechatPaymentVo;
import com.biz.service.payment.interf.WechatPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Created by lzz on 2017/5/15.
 */
@Service
public class WechatPaymentServiceImpl implements WechatPaymentService {

    @Autowired
    private WechatPaymentLogPoRepository wechatPaymentLogPoRepository;

    @Override
    public Page<WechatPaymentLogPo> findList(WechatPaymentVo wechatPaymentVo) {
        return wechatPaymentLogPoRepository.findAll(new WechatPaymentSpecification(wechatPaymentVo), new PageRequest(wechatPaymentVo.getPage() - 1, wechatPaymentVo.getPageSize()));
    }
}
