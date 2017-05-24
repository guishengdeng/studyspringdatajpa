package com.biz.gbck.common.voucher;

import java.io.Serializable;

import com.biz.gbck.dao.mysql.po.voucher.VoucherPo;
import com.biz.gbck.dao.mysql.po.voucher.VoucherTypePo;
import com.biz.gbck.dao.redis.ro.voucher.VoucherRo;
import com.biz.gbck.util.DateTool;
import com.google.common.base.Function;

public class VoucherRoToVoucherPo implements Function<VoucherRo, VoucherPo>, Serializable {

		/**
	 * 
	 */
	private static final long serialVersionUID = -1815890011698050635L;
		private String handler;

		public VoucherRoToVoucherPo(String handler) {
				this.handler = handler;
		}

		@Override
	public VoucherPo apply(VoucherRo ro) {
		VoucherPo po = new VoucherPo();
		po.setId(ro.getId());
		po.setBindingTime(DateTool.nowTimestamp());
		po.setBindingUserId(ro.getBindingUserId());
				po.setCreateBy(handler);
		if(ro.getCreateTime()!=null){
			po.setCreateTime(new java.sql.Timestamp(ro.getCreateTime()));
		}
		po.setStatus(0);
		po.setExpireTime(new java.sql.Timestamp(ro.getExpireTime()));
		VoucherTypePo vtPo = new VoucherTypePo();
		vtPo.setId(ro.getVoucherTypeId());
		po.setVoucherType(vtPo);
		return po;
	}


}
