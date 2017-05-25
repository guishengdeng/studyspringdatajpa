package com.biz.service.predicate;

import com.biz.gbck.dao.redis.ro.voucher.VoucherRo;
import com.google.common.base.Predicate;

public class VoucherTypePredicate implements Predicate<VoucherRo> {

    private Long voucherTypeId;

    public VoucherTypePredicate(Long voucherTypeId) {
        if (voucherTypeId == null) {
            throw new IllegalArgumentException("VoucherTypeId can not be null.");
        }
        this.voucherTypeId = voucherTypeId;
    }

    @Override public boolean apply(VoucherRo voucherRo) {

        return voucherRo != null && voucherRo.getVoucherTypeId().equals(voucherTypeId);
    }

}
