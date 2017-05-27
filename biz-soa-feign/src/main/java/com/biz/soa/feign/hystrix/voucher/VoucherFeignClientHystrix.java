package com.biz.soa.feign.hystrix.voucher;

import java.util.List;

import org.springframework.stereotype.Component;
import com.biz.gbck.dao.mysql.po.voucher.VoucherPo;
import com.biz.gbck.dao.mysql.po.voucher.VoucherTypePo;
import com.biz.gbck.dao.redis.ro.voucher.VoucherTypeRo;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.vo.order.resp.IProduct;
import com.biz.gbck.vo.order.resp.OrderCouponReqVo;
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
	public void dispatcherVoucher(DispatcherVoucherReqVo dispatcherVoucherReqVo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer findVoucherNumberById(Long voucherTypeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getUsableCount(OrderCouponReqVo reqVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShopCraftVoucherVo> getAvailableVouchers(String userId, List<? extends IProduct> itemVos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getVoucherLimit(OrderCouponReqVo OrderCouponReqVo) throws DepotNextDoorException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void useVoucher(OrderCouponReqVo orderCouponReqVo) {
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

}
