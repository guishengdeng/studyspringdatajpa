package com.biz.soa.feign.client.voucher;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.biz.gbck.common.model.voucher.VoucherConfigure;
import com.biz.gbck.dao.redis.ro.voucher.VoucherConfigureRo;
import com.biz.gbck.vo.voucher.VoucherCfgVo;
import com.biz.soa.feign.hystrix.voucher.VoucherConfigureHystrix;

@FeignClient(name = "soa-mix-service", fallback = VoucherConfigureHystrix.class)
public interface VoucherConfigureFeignClient {

	@RequestMapping(value = "/soa/voucherConfigure/all", method = RequestMethod.POST)
	List<VoucherCfgVo> findAll();

	@RequestMapping(value = "/soa/voucherConfigure/del", method = RequestMethod.POST)
	void delete(@RequestParam("voucherconfigure") VoucherConfigure voucherconfigure, @RequestParam("voucherType") Long voucherType);

	@RequestMapping(value = "/soa/voucherConfigure/save", method = RequestMethod.POST)
	void save(@RequestParam("voucherconfigure") VoucherConfigure voucherconfigure, @RequestParam("voucherType") Long voucherType,
			@RequestParam("parseInt") int parseInt);

	@RequestMapping(value = "/soa/voucherConfigure/getVoucherConfi", method = RequestMethod.POST)
	VoucherConfigureRo getVoucherConfigureRo(@RequestParam("voucherconfigure") String voucherconfigure);

}
