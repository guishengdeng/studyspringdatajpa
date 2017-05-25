package com.biz.gbck.dao.mysql.repository.voucher;

import org.springframework.data.repository.NoRepositoryBean;

import com.biz.gbck.dao.mysql.po.voucher.VoucherTypePo;

@NoRepositoryBean

public interface VoucherTypeDao {
    void createVoucherTypeResource(VoucherTypePo voucherTypePo);
}
