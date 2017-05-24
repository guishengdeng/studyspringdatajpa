package com.biz.soa.feign.hystrix.voucher;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.biz.gbck.common.model.order.IOrderItemVo;
import com.biz.gbck.dao.mysql.po.voucher.VoucherPo;
import com.biz.gbck.dao.mysql.po.voucher.VoucherTypePo;
import com.biz.gbck.dao.redis.ro.voucher.VoucherRo;
import com.biz.gbck.dao.redis.ro.voucher.VoucherTypeRo;
import com.biz.gbck.vo.order.resp.IOrderPeriodQueryReqVo;
import com.biz.gbck.vo.spring.PageVO;
import com.biz.gbck.vo.voucher.VoucherSearchVo;
import com.biz.soa.feign.client.voucher.VoucherFeignClient;
import com.biz.vo.voucher.ShopCraftVoucherVo;

@Component
public class VoucherFeignClientHystrix implements VoucherFeignClient {

	@Override
	public Map<String, List<VoucherRo>> allVouchers(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VoucherPo> listAllVouchersByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validateDispatcherAction(List<Long> userIds, Long shopTypeId, Long voucherTypeId,
			int dispatcherCnt) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void dispatcherVoucher(List<Long> userIds, VoucherTypeRo voucherTypeRo, Integer dispatcherCnt,
			String loginUsername) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int findVoucherNumberById(Long voucherTypeId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getUsableCount(IOrderPeriodQueryReqVo reqVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ShopCraftVoucherVo> availableVouchers(Long userId, List<? extends IOrderItemVo> itemVos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getVoucherLimit(IOrderPeriodQueryReqVo iOrderPeriodQueryReqVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void useVoucher(IOrderPeriodQueryReqVo iOrderPeriodQueryReqVo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PageVO<VoucherTypePo> searchVoucher(VoucherSearchVo voucherSearchVo) {
		// TODO Auto-generated method stub
		return null;
	}


}
