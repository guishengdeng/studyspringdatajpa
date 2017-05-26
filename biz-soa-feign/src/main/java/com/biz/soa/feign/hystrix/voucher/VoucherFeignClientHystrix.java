package com.biz.soa.feign.hystrix.voucher;

import com.biz.gbck.dao.mysql.po.voucher.VoucherPo;
import com.biz.gbck.dao.mysql.po.voucher.VoucherTypePo;
import com.biz.gbck.dao.redis.ro.voucher.VoucherTypeRo;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.vo.order.resp.IProduct;
import com.biz.gbck.vo.order.resp.OrderCouponReqVo;
import com.biz.gbck.vo.spring.PageVO;
import com.biz.gbck.vo.voucher.VoucherSearchVo;
import com.biz.soa.feign.client.voucher.VoucherFeignClient;
import com.biz.support.web.handler.JSONResult;
import com.biz.vo.voucher.ShopCraftVoucherVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VoucherFeignClientHystrix implements VoucherFeignClient {


	@Override
	public List<VoucherPo> listAllVouchersByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validateDispatcherAction(List<Long> userIds, Long shopTypeId, Long voucherTypeId,
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
	public Integer findVoucherNumberById(Long voucherTypeId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Integer getUsableCount(OrderCouponReqVo reqVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ShopCraftVoucherVo> getAvailableVouchers(Long userId, List<? extends IProduct> itemVos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getVoucherLimit(OrderCouponReqVo iOrderPeriodQueryReqVo) throws DepotNextDoorException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void useVoucher(OrderCouponReqVo iOrderPeriodQueryReqVo) {
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
	public JSONResult allVouchers(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
