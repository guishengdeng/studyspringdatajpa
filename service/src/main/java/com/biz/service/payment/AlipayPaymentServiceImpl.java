package com.biz.service.payment;

import com.biz.gbck.dao.mysql.po.payment.AlipayPaymentLogPo;
import com.biz.gbck.dao.mysql.repository.payment.Alipay.AlipayPaymentLogPoRepository;
import com.biz.gbck.vo.payment.pay.AlipayPaymentVo;
import com.biz.service.AbstractBaseService;
import com.biz.service.payment.interf.AlipayPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Created by lzz on 2017/5/15.
 */
@Service
public class AlipayPaymentServiceImpl extends AbstractBaseService implements AlipayPaymentService {

    @Autowired
    private AlipayPaymentLogPoRepository alipayPaymentLogPoRepository;


    @Override
    public Page<AlipayPaymentLogPo> findList(AlipayPaymentVo alipayPaymentVo) {
        return alipayPaymentLogPoRepository.findAll(new AlipayPaymentSpecification(alipayPaymentVo), new PageRequest(alipayPaymentVo.getPage() - 1, alipayPaymentVo.getPageSize()));
    }
}
