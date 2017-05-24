package com.biz.soa.feign.client.voucher;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.gbck.dao.mysql.po.voucher.VoucherPo;
import com.biz.gbck.dao.redis.ro.voucher.VoucherRo;
import com.biz.gbck.dao.redis.ro.voucher.VoucherTypeRo;
import com.biz.gbck.vo.order.resp.IOrderPeriodQueryReqVo;
import com.biz.soa.feign.hystrix.voucher.VoucherFeignClientHystrix;
/**
 * 
 * @author yang
 *
 */
@FeignClient(name = "soa-mix-service", fallback = VoucherFeignClientHystrix.class)
public interface VoucherFeignClient {

	/**
	 * 获取优惠券列表
	 */
	@RequestMapping(value = "/soa/voucher/all", method = RequestMethod.POST)
	public Map<String, List<VoucherRo>> allVouchers(@PathVariable("userId") String userId);

	@RequestMapping(value = "/soa/voucher/listAllVouchersByUserId", method = RequestMethod.POST)
	public List<VoucherPo> listAllVouchersByUserId(@RequestParam("userId") Long userId);

	@RequestMapping(value = "/soa/voucher/validateAction", method = RequestMethod.POST)
	public boolean validateDispatcherAction(@RequestParam("userIds") List<Long> userIds, @RequestParam("shopTypeId") Long shopTypeId, 
				@RequestParam("voucherTypeId") Long voucherTypeId, @RequestParam("dispatcherCnt") int dispatcherCnt);

	@RequestMapping(value = "/soa/voucher/dispatcherVoucher", method = RequestMethod.POST)
	public void dispatcherVoucher(@RequestParam("userIds") List<Long> userIds, @RequestParam("voucherTypeRo") VoucherTypeRo voucherTypeRo, 
			@RequestParam("dispatcherCnt") int dispatcherCnt,@RequestParam("loginUsername")	String loginUsername);

	@RequestMapping(value = "/soa/voucher/findVoucherNumberById", method = RequestMethod.POST)
	public int findVoucherNumberById(@RequestParam("voucherTypeId") Long voucherTypeId);
	
	/**
     * 获取优惠券可用数量
     * @param reqVo
     * @return
     */
    @RequestMapping(value = "/soa/voucher/getUsableCount", method = RequestMethod.POST)
    public int getUsableCount(@RequestParam("reqVo") IOrderPeriodQueryReqVo reqVo);
}
