package com.biz.service.predicate;

import com.biz.gbck.dao.redis.ro.voucher.VoucherRo;
import com.biz.gbck.dao.redis.ro.voucher.VoucherTypeRo;
import com.google.common.base.Predicate;
import org.codelogger.utils.DateUtils;

import java.util.Date;

public class VoucherNotExpirePredicate implements Predicate<VoucherRo> {

    private VoucherTypeRo voucherTypeRo;

    public VoucherNotExpirePredicate(VoucherTypeRo voucherTypeRo) {
        this.voucherTypeRo = voucherTypeRo;
    }

    @Override public boolean apply(VoucherRo voucherRo) {

        return voucherRo != null && !voucherTypeRo.isExpire() && DateUtils.formatToStartOfDay(new Date()).getTime() <= voucherRo.getExpireTime();
    }

}
