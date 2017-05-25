package com.biz.rest.transformer.voucher;

import java.io.Serializable;

import com.biz.gbck.dao.redis.ro.voucher.VoucherTypeRo;
import com.biz.gbck.util.DateTool;
import com.biz.gbck.vo.voucher.VoucherTypeVo;
import com.google.common.base.Function;

public class VoucherTypeRoToVoucherTypeVo implements Function<VoucherTypeRo, VoucherTypeVo>, Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 483412219443711423L;

	@Override
    public VoucherTypeVo apply(VoucherTypeRo voucherTypeRo) {
        if(voucherTypeRo == null) return null;
        VoucherTypeVo voucherTypeVo = new VoucherTypeVo();
        voucherTypeVo.setId(voucherTypeRo.getId());
        voucherTypeVo.setName(voucherTypeRo.getName());
        voucherTypeVo.setFaceValue(String.valueOf(voucherTypeRo.getFaceValue()));
        voucherTypeVo.setPeriodDays(voucherTypeRo.getPeriodDays()+"");
        voucherTypeVo.setStartTime(DateTool.longToDateStr(voucherTypeRo.getStartTime(), "yyyy-MM-dd HH:mm:ss"));
        return voucherTypeVo;
    }

}
