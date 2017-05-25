package com.biz.soa.feign.hystrix.voucher;

import java.util.List;

import org.springframework.stereotype.Component;

import com.biz.gbck.dao.mysql.po.voucher.VoucherTypePo;
import com.biz.gbck.dao.redis.ro.voucher.VoucherTypeRo;
import com.biz.gbck.vo.voucher.VoucherTypeVo;
import com.biz.soa.feign.client.voucher.VoucherTypeFeignClient;

@Component
public class VoucherTypeFeignClientHystrix implements VoucherTypeFeignClient {

	@Override
	public List<VoucherTypeRo> allVoucherTypesInApp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(VoucherTypeVo voucherTypeVo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public VoucherTypePo getVoucherTypeById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(VoucherTypeVo voucherTypeVo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addVoucherTypeIssueCount(Long id, int addIssueCount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteVoucherType(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public VoucherTypeRo getVoucherTypeRoById(Long voucherTypeId) {
		// TODO Auto-generated method stub
		return null;
	}

}
