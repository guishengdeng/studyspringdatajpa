package com.biz.soa.feign.client.voucher;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.biz.gbck.dao.mysql.po.voucher.VoucherTypePo;
import com.biz.gbck.dao.redis.ro.voucher.VoucherTypeRo;
import com.biz.gbck.vo.voucher.VoucherTypeVo;
import com.biz.soa.feign.hystrix.voucher.VoucherTypeFeignClientHystrix;

@FeignClient(name = "soa-mix-service", fallback = VoucherTypeFeignClientHystrix.class)
public interface VoucherTypeFeignClient {

	/**
	 * 获取所有优惠券类型
	 * @return
	 */
	@RequestMapping(value="/soa/voucherType/all", method = RequestMethod.POST)
	public List<VoucherTypeRo> allVoucherTypesInApp();

	/**
	 * 保存优惠券类型
	 * @param voucherTypeVo
	 */
	@RequestMapping(value="/soa/voucherType/save",method = RequestMethod.POST)
	public void save(@RequestBody VoucherTypeVo voucherTypeVo);

	/**
	 * 根据优惠券类型id获取优惠券类型
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/soa/voucherType/getVoucherType",method = RequestMethod.POST)
	public VoucherTypePo getVoucherTypeById(@RequestParam("id") Long id);

	/**
	 * 修改优惠券类型
	 * @param voucherTypeVo
	 */
	
	@RequestMapping(value="/soa/voucherType/update",method = RequestMethod.POST)
	public void update(@RequestBody VoucherTypeVo voucherTypeVo);

	/**
	 * 添加数量
	 * @param id
	 * @param addIssueCount
	 */
	@RequestMapping(value="/soa/voucherType/addCount",method = RequestMethod.POST)
	public void addVoucherTypeIssueCount(@RequestParam("id") Long id, @RequestParam("addIssueCount") int addIssueCount);

	/**
	 * 根据优惠券类型id删除优惠券类型
	 * @param id
	 */
	@RequestMapping(value="/soa/voucherType/del",method = RequestMethod.POST)
	public void deleteVoucherType(@RequestParam("id") Long id);

	/**
	 * 获取优惠券类型RO
	 * @param voucherTypeId
	 * @return
	 */
	@RequestMapping(value="/soa/voucherType/getVouRO",method = RequestMethod.POST)
	public VoucherTypeRo getVoucherTypeRoById(@RequestParam("voucherTypeId") Long voucherTypeId);

}
