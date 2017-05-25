package com.biz.soa.feign.hystrix.voucher;

import java.util.List;

import org.springframework.stereotype.Component;

import com.biz.gbck.common.model.voucher.VoucherConfigure;
import com.biz.gbck.dao.redis.ro.voucher.VoucherConfigureRo;
import com.biz.gbck.vo.voucher.VoucherCfgVo;
import com.biz.soa.feign.client.voucher.VoucherConfigureFeignClient;

@Component
public class VoucherConfigureHystrix implements VoucherConfigureFeignClient {

	@Override
	public List<VoucherCfgVo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(VoucherConfigure voucherconfigure, Long voucherType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(VoucherConfigure voucherconfigure, Long voucherType, int parseInt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public VoucherConfigureRo getVoucherConfigureRo(String voucherconfigure) {
		// TODO Auto-generated method stub
		return null;
	}

}
