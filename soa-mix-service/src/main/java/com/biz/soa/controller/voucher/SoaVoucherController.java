package com.biz.soa.controller.voucher;

import static com.google.common.collect.Lists.newArrayList;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biz.gbck.common.model.order.IOrderItemVo;
import com.biz.gbck.dao.mysql.po.voucher.VoucherPo;
import com.biz.gbck.dao.redis.ro.voucher.VoucherRo;
import com.biz.gbck.dao.redis.ro.voucher.VoucherTypeRo;
import com.biz.gbck.util.DateTool;
import com.biz.gbck.vo.order.resp.IOrderPeriodQueryReqVo;
import com.biz.gbck.vo.voucher.VoucherVo;
import com.biz.soa.base.SoaBaseController;
import com.biz.soa.service.voucher.VoucherService;
import com.biz.soa.service.voucher.VoucherTypeService;
import com.biz.support.web.handler.JSONResult;
import com.biz.vo.voucher.ShopCraftVoucherVo;
import com.google.common.collect.Maps;

@RestController
@RequestMapping(value = "/soa/voucher")
public class SoaVoucherController extends SoaBaseController{

	@Autowired
	private VoucherService voucherService;
	
	@Autowired
	private VoucherTypeService voucherTypeService;
	
	@PostMapping("/all")
    public JSONResult allVouchers(@RequestParam("userId") String userId) {
        Map<String, List<VoucherVo>> voucherVoMap =
            Maps.newHashMap(getVouchersByUserId(Long.valueOf(userId)));
        return new JSONResult(voucherVoMap);
    }
	
	private Map<String, List<VoucherVo>> getVouchersByUserId(Long userId) {
        Map<String, List<VoucherRo>> voucherRoMap = voucherService.allVouchers(userId);
        Map<String, List<VoucherVo>> voucherVoMap = Maps.newHashMap();
        for (String key : voucherRoMap.keySet()) {
            List<VoucherVo> voucherVos = newArrayList();
            for (VoucherRo voucherRo : voucherRoMap.get(key)) {
                VoucherTypeRo voucherTypeRo = voucherTypeService.getVoucherTypeRoById(voucherRo.getVoucherTypeId());
                voucherVos.add(buildVoucherVo(voucherTypeRo, voucherRo));
            }
            Collections.sort(voucherVos);
            voucherVoMap.put(key, voucherVos);
        }
        return voucherVoMap;
    }

    /**
     * 构建优惠券view
     * @param voucherTypeRo
     * @param voucherRo
     * @return
     */
    private VoucherVo buildVoucherVo(VoucherTypeRo voucherTypeRo, VoucherRo voucherRo) {
        VoucherVo voucherVo = new VoucherVo();
        voucherVo.setVoucherTypeId(voucherTypeRo.getId());
        voucherVo.setName(voucherTypeRo.getName());
        voucherVo.setPaymentLimit(voucherTypeRo.getPaymentLimit());
        voucherVo.setPayPatternLimit(voucherTypeRo.getPaymentType());
        voucherVo.setTypeStatus(voucherTypeRo.getTypeStatus());
        voucherVo.setFaceValue(voucherTypeRo.getFaceValue());
        voucherVo.setStartTime(voucherRo.getCreateTime());
        voucherVo.setExpireTime(voucherRo.getExpireTime());
        voucherVo.setCategoryInfo(voucherTypeRo.getLimitInfo());
        voucherVo.setCategoryId(voucherTypeRo.getCategoryId()==null ? 0 : voucherTypeRo.getCategoryId().intValue());
        
        // 当前时间
        Long currentTime = System.currentTimeMillis();
        // 优惠券过期时间
        Long expireTime = voucherRo.getExpireTime();
        
        if(voucherRo.getUseTime()!=null){
        	//voucherVo.setFreeMsg("已使用");
        }else if(expireTime<currentTime){
        	voucherVo.setFreeMsg("已过期");
        }else if (expireTime>=currentTime
        		&& DateTool.afterDays(7) > expireTime) {
            voucherVo.setFreeMsg("快过期");
        }
        logger.debug("voucherNmae["+voucherVo.getName()+"],currentTime["+currentTime+"],expireTime["+expireTime+"],add7Time["+(currentTime + DateTool.afterDays(7))+"]");
        
        return voucherVo;
    }
    
    /**
     * 根据userId 获取优惠券po数据
     * @param userId
     * @return
     */
    @PostMapping(value="/listAllVouchersByUserId")
    public List<VoucherPo> listAllVouchersByUserId(@RequestParam("userId") Long userId){
    	return voucherService.listAllVouchersByUserId(userId);
    }

    /**
     * 验证
     * @param userIds
     * @param shopTypeId
     * @param voucherTypeId
     * @param dispatcherCnt
     * @return
     */
    @PostMapping(value="/validataAction")
	public boolean validateDispatcherAction(@RequestBody List<Long> userIds, @RequestParam("shopTypeId") Long shopTypeId, 
				@RequestParam("voucherTypeId") Long voucherTypeId, @RequestParam("dispatcherCnt") int dispatcherCnt){
    	return voucherService.validateDispatcherAction(userIds, shopTypeId, voucherTypeId, dispatcherCnt);
		
	}

    /**
     * 
     * @param userIds
     * @param voucherTypeRo
     * @param dispatcherCnt
     * @param loginUsername
     */
    @PostMapping(value="/dispatcherVoucher")
	public void dispatcherVoucher(@RequestParam("userIds") List<Long> userIds, @RequestBody VoucherTypeRo voucherTypeRo, @RequestParam("dispatcherCnt") Integer dispatcherCnt,
			@RequestParam("loginUsername") String loginUsername){
		voucherService.dispatcherVoucher(userIds, voucherTypeRo, dispatcherCnt, loginUsername);
	}
	

    @PostMapping(value="/findVoucherNumberById")
	public int findVoucherNumberById(@RequestParam("voucherTypeId") Long voucherTypeId){
		return voucherService.findVoucherNumberById(voucherTypeId);
	}
	
    /**
     * 获取优惠券可用数量
     * @param reqVo
     * @return
     */
    @PostMapping(value="/getUsableCount")
    public int getUsableCount(@RequestBody IOrderPeriodQueryReqVo reqVo){
		return voucherService.getUserUseableVoucherCount(reqVo.getUserId());
    }
    
    /**
     * 购物车,获取可用优惠券
     * @throws Exception 
     */
    @PostMapping(value="/getAvailableVouchers")
    public  List<ShopCraftVoucherVo> availableVouchers(@RequestParam("userId") Long userId,@RequestBody List<? extends IOrderItemVo> itemVos) throws Exception{
    	 return voucherService.getAvailableVouchers(userId, itemVos);
     }
}
