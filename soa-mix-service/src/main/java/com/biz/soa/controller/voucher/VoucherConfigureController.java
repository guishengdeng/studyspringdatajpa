package com.biz.soa.controller.voucher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biz.gbck.common.model.voucher.VoucherConfigure;
import com.biz.gbck.dao.redis.ro.voucher.VoucherConfigureRo;
import com.biz.gbck.vo.voucher.VoucherCfgVo;
import com.biz.soa.base.SoaBaseController;
import com.biz.soa.service.voucher.VoucherConfigureService;

@RestController
@RequestMapping(value = "/soa/voucherConfigure")
public class VoucherConfigureController extends SoaBaseController{

	@Autowired
	private VoucherConfigureService  voucherConfigureService;
	
	/**
	 * 获取所有优惠券配置
	 * @return
	 */
	@PostMapping(value="/all")
	public List<VoucherCfgVo> findAll() {
		return voucherConfigureService.findAll();
	}

	/**
	 * 删除优惠券配置
	 * @param voucherconfigure
	 * @param voucherType
	 */
	@PostMapping(value="/del")
	public void delete(@RequestParam("voucherconfigure") VoucherConfigure voucherconfigure, @RequestParam("voucherType") Long voucherType) {
		voucherConfigureService.delete(voucherconfigure, voucherType);
	}

	/**
	 * 保存优惠券配置
	 * @param voucherconfigure
	 * @param voucherType
	 * @param parseInt
	 */
	@PostMapping(value="/save")
	public void save(@RequestParam("voucherconfigure") VoucherConfigure voucherconfigure, @RequestParam("voucherType") Long voucherType, @RequestParam("parseInt") int parseInt) {
		try {
			voucherConfigureService.save(voucherconfigure, voucherType, parseInt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取优惠券配置
	 * @param voucherconfigure
	 * @return
	 */
	@PostMapping(value="/getVoucherConfi")
	public VoucherConfigureRo getVoucherConfigureRo(@RequestParam("voucherconfigure") String voucherconfigure) {
		return voucherConfigureService.getVoucherConfigureRo(voucherconfigure);
	}
}
