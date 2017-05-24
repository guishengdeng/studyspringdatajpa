package com.biz.soa.controller.voucher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biz.gbck.dao.mysql.po.voucher.VoucherTypePo;
import com.biz.gbck.dao.redis.ro.voucher.VoucherTypeRo;
import com.biz.gbck.vo.voucher.VoucherTypeVo;
import com.biz.soa.base.SoaBaseController;
import com.biz.soa.service.voucher.VoucherTypeService;

@RestController
@RequestMapping(value = "/soa/voucherType")
public class SoaVoucherTypeController extends SoaBaseController{

	@Autowired
	private VoucherTypeService  voucherTypeService;
	
	/**
	 * 获取所有优惠券类型
	 * @return
	 */
	@PostMapping(value="/all")
	public List<VoucherTypeRo> allVoucherTypesInApp() {
		return voucherTypeService.allVoucherTypesInApp();
	}

	/**
	 * 保存优惠券类型
	 * @param voucherTypeVo
	 */
	@PostMapping(value="/save")
	public void save(@RequestBody VoucherTypeVo voucherTypeVo) {
		voucherTypeService.save(voucherTypeVo);
	}

	/**
	 * 根据优惠券类型id获取优惠券类型
	 * @param id
	 * @return
	 */
	@PostMapping(value="/getVoucherType")
	public VoucherTypePo getVoucherTypeById(Long id) {
		return voucherTypeService.getVoucherTypeById(id);
	}

	/**
	 * 修改优惠券类型
	 * @param voucherTypeVo
	 */
	
	@PostMapping(value="/update")
	public void update(VoucherTypeVo voucherTypeVo) {
		voucherTypeService.update(voucherTypeVo);
	}

	/**
	 * 添加数量
	 * @param id
	 * @param addIssueCount
	 */
	@PostMapping(value="/addCount")
	public void addVoucherTypeIssueCount(Long id, int addIssueCount) {
		voucherTypeService.addVoucherTypeIssueCount(id, addIssueCount);
	}

	/**
	 * 根据优惠券类型id删除优惠券类型
	 * @param id
	 */
	@PostMapping(value="/del")
	public void deleteVoucherType(Long id) {
		voucherTypeService.deleteVoucherType(id);
	}
	
}

