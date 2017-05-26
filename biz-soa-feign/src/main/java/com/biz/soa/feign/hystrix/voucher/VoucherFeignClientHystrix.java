package com.biz.soa.feign.hystrix.voucher;

import java.util.List;

import org.springframework.stereotype.Component;

import com.biz.gbck.dao.mysql.po.voucher.VoucherPo;
import com.biz.gbck.dao.mysql.po.voucher.VoucherTypePo;
import com.biz.gbck.dao.redis.ro.voucher.VoucherTypeRo;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.vo.order.resp.IOrderPeriodQueryReqVo;
import com.biz.gbck.vo.order.resp.IProduct;
import com.biz.gbck.vo.spring.PageVO;
import com.biz.gbck.vo.voucher.DispatcherVoucherReqVo;
import com.biz.gbck.vo.voucher.VoucherSearchVo;
import com.biz.gbck.vo.voucher.VoucherValidataReqVo;
import com.biz.soa.feign.client.voucher.VoucherFeignClient;
import com.biz.support.web.handler.JSONResult;
import com.biz.vo.voucher.ShopCraftVoucherVo;

@Component
public class VoucherFeignClientHystrix implements VoucherFeignClient {

	@Override
	public JSONResult allVouchers(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VoucherPo> listAllVouchersByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validateDispatcherAction(VoucherValidataReqVo voucherValidataReqVo) {
		// TODO Auto-generated method stub
		return null;
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
	public List<ShopCraftVoucherVo> getAvailableVouchers(String userId, List<? extends IProduct> itemVos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getVoucherLimit(IOrderPeriodQueryReqVo iOrderPeriodQueryReqVo) throws DepotNextDoorException {
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

	@Override
	public void dispatcherUserGroupsVoucher(Long userIdGroupsId, VoucherTypeRo voucherTypeRo, Integer dispatcherCnt,
			String loginUsername) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispatcherVoucher(DispatcherVoucherReqVo dispatcherVoucherReqVo) {
		// TODO Auto-generated method stub
		
	}



}
