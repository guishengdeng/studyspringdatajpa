package com.biz.gbck.dao.mysql.repository.voucher;

import java.util.List;
import java.util.Map;

import com.biz.gbck.dao.mysql.po.voucher.VoucherConstant;
import com.biz.gbck.dao.mysql.po.voucher.VoucherPo;
import com.biz.gbck.vo.voucher.UserVoucherStatisticResultVo;

public interface VoucherDao {
    Map<VoucherConstant.VoucherUseStatus, List<VoucherPo>> listVoucherByUseStatus(int userId);

    List<UserVoucherStatisticResultVo> findAllUserVouchers(Map<String, Object> searchParams);
}
