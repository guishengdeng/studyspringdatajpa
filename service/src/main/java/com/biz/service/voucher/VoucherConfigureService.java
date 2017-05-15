package com.biz.service.voucher;

import java.util.List;

import com.biz.gbck.common.model.voucher.VoucherConfigure;
import com.biz.gbck.dao.redis.ro.voucher.VoucherConfigureRo;
import com.biz.gbck.vo.voucher.VoucherCfgVo;

public interface VoucherConfigureService {

	List<VoucherCfgVo> findAll();

	void delete(VoucherConfigure voucherconfigure, Long voucherType);

	void save(VoucherConfigure voucherconfigure, Long voucherType, int parseInt) throws Exception;

	VoucherConfigureRo getVoucherConfigureRo(String voucherconfigure);

}
