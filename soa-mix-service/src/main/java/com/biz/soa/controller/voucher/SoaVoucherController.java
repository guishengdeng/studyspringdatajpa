package com.biz.soa.controller.voucher;

import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.dao.mysql.po.voucher.VoucherLimitType;
import com.biz.gbck.dao.mysql.po.voucher.VoucherPo;
import com.biz.gbck.dao.mysql.po.voucher.VoucherTypePo;
import com.biz.gbck.dao.mysql.po.voucher.VoucherTypeStatus;
import com.biz.gbck.dao.redis.ro.voucher.VoucherRo;
import com.biz.gbck.dao.redis.ro.voucher.VoucherTypeRo;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.exceptions.DepotNextDoorExceptions;
import com.biz.gbck.util.DateTool;
import com.biz.gbck.vo.order.resp.IProduct;
import com.biz.gbck.vo.order.resp.OrderCouponReqVo;
import com.biz.gbck.vo.spring.PageVO;
import com.biz.gbck.vo.voucher.VoucherSearchVo;
import com.biz.gbck.vo.voucher.VoucherVo;
import com.biz.soa.base.SoaBaseController;
import com.biz.soa.service.voucher.VoucherService;
import com.biz.soa.service.voucher.VoucherTypeService;
import com.biz.support.web.handler.JSONResult;
import com.biz.vo.voucher.ShopCraftVoucherVo;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.google.common.collect.Lists.newArrayList;

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
    public int getUsableCount(@RequestBody OrderCouponReqVo reqVo){
		return voucherService.getUserUseableVoucherCount(reqVo.getUserId());
    }
    
    /**
     * 购物车,获取可用优惠券
     * @throws Exception 
     */
    @PostMapping(value="/getAvailableVouchers")
    public  List<ShopCraftVoucherVo> availableVouchers(@RequestParam("userId") Long userId,@RequestBody List<IProduct> itemVos) throws Exception{
    	 return voucherService.getAvailableVouchers(userId, itemVos);
     }
    
    /**
     * 优惠券使用
     * @param reqVo
     * @throws DepotNextDoorException 
     */
    @RequestMapping(value="/useVoucher",method=RequestMethod.POST)
    public void useVoucher(@RequestBody OrderCouponReqVo reqVo) throws DepotNextDoorException{
    	Long userId = reqVo.getUserId();
    	List<Long> vouTypeIds = reqVo.getCoupons();
    	for (Long ids : vouTypeIds) {
    		VoucherTypeRo voucherTypeRo = voucherTypeService.getVoucherTypeRoById(ids);
    		 VoucherRo voucherRo = voucherService.fetchVoucher(userId, voucherTypeRo);
    		 //订单id
    		 Long orderId = reqVo.getOrderId();
    		 voucherService.useVoucher(reqVo.getUserId(), voucherRo.getId(), orderId, getVoucherLimit(reqVo));
		}
    }
    
    /**
     * 计算优惠券优惠金额
     * @param reqVo
     * @return
     * @throws CommonException 
     * @throws DepotNextDoorException 
     */
    @RequestMapping(value = "/getVoucherlimit",method=RequestMethod.POST)
    public int getVoucherLimit(@RequestBody OrderCouponReqVo reqVo) throws DepotNextDoorException{
		int voucherLimit =0; ;
		//优惠券类型id
    	List<Long> voucherTypeIds = reqVo.getCoupons();
    	//优惠券使用数量
    	Integer voucherCount = voucherTypeIds.size();
    	//获取所选商品
		List<? extends IProduct> iProduct = reqVo.getProducts();
		//用户 id
		Long userId = reqVo.getUserId();
    	for (Long ids : voucherTypeIds) {
    		//根据优惠券类型id取优惠券类型
    		VoucherTypeRo  voucherTypeRo = voucherTypeService.getVoucherTypeRoById(ids);
    		voucherLimit += getIntLimit(voucherTypeRo.getId(), voucherCount, iProduct, userId);
		}
    	return voucherLimit;
    }
    
    /**
     * @param voucherTypeId
     * @param voucherCount
     * @param iProduct
     * @param userId
     * @return
     * @throws DepotNextDoorException
     */
    private int getIntLimit(Long voucherTypeId,Integer voucherCount,List<? extends IProduct> iProduct,Long userId) throws DepotNextDoorException{
    	VoucherTypeRo voucherTypeRo = voucherTypeService.getVoucherTypeRoById(voucherTypeId);
        if (voucherTypeRo == null) {
//            throw DepotNextDoorExceptions.Voucher.VOUCHER_NOT_EXISTS;
        	throw new DepotNextDoorException(DepotNextDoorExceptions.Voucher.VOUCHER_NOT_EXISTS.toString());
        }
        if (voucherCount == null || voucherCount == 0) {
            return 0;
        }
        //验证付款类型与优惠券设定的支付方式是否满足
        // 优惠券是否可叠加使用判断
        if (voucherTypeRo.getTypeStatus() == VoucherTypeStatus.MUTEX && voucherCount > 1) {
//            throw new CommonException("优惠券"+ voucherTypeRo.getName()+"一次只能使用一张",
//                    ExceptionCode.Voucher.VOUCHER_VALIDATE_ERROR);
        }
        // 计算可用优惠券的商品
//        iProduct = this.calculateVoucherAbleProductItem(iProduct);
        logger.debug("Voucher able items:{}", iProduct);

        // 验证可用优惠券数量
        Collection<VoucherRo> useableVouchers = voucherService
                .findUsableVouchersByUserIdAndVoucherType(userId, voucherTypeId);
        if (org.apache.commons.collections.CollectionUtils.isEmpty(useableVouchers) || useableVouchers.size() < voucherCount) {
//            throw new CommonException("优惠券可用数量不足", ExceptionCode.Voucher.VOUCHER_SHORTAGE_ERROR);
        	throw new DepotNextDoorException(DepotNextDoorExceptions.Voucher.VOUCHER_SHORTAGE_ERROR.toString());
        }
        // 校验当前选择的优惠券是否满足当前所选购商品
        boolean isCheckUseVoucherType = false;// 是否可使用当前选择的优惠券<针对选择了优惠券的情况下使用>  true为可使用  false为不可使用
        Integer totalPayLimitAmount = 0;// 优惠总金额
        Integer totalAmount = 0;// 订单中商品总金额
        logger.debug("校验当前选择的优惠券是否满足当前所选购商品start>>>  categoryId[" + voucherTypeRo.getCategoryId()
                + "] productIds[" + voucherTypeRo.getProductIds() + "]");
        if (voucherTypeRo.getVoucherLimitType() == VoucherLimitType.BY_CATEGORY || (
                voucherTypeRo.getVoucherLimitType() == null
                        && voucherTypeRo.getCategoryId() != null)) {
            for (IProduct item : iProduct) {
                int currentProductPayAmount = item.getSalePrice() * item.getQuantity();
                if (Objects.equals(voucherTypeRo.getCategoryId(), item.getCategoryId())) {
                    totalPayLimitAmount += currentProductPayAmount;
                    isCheckUseVoucherType = true;
                }
                totalAmount += currentProductPayAmount;
            }
        } else if (voucherTypeRo.getVoucherLimitType() == VoucherLimitType.BY_PRODUCTS || (
                voucherTypeRo.getVoucherLimitType() == null && voucherTypeRo.getProductIds() != null)) {
            for (IProduct item : iProduct) {
                int currentProductPayAmount = item.getSalePrice() * item.getQuantity();

                logger.debug("校验当前选择的优惠券是否满足当前所选购商品>>>voucherTypeRoProductIds[" + voucherTypeRo
                        .getProductIds() + "],goodsProductId[" + item.getProductId() + "],result["
                        + (voucherTypeRo.getProductIds().contains("," + item.getProductId() + ","))
                        + "]");
                if (voucherTypeRo.getProductIds().contains("," + item.getProductId() + ",")) {
                    totalPayLimitAmount += currentProductPayAmount;
                    isCheckUseVoucherType = true;
                }
                totalAmount += currentProductPayAmount;
            }
        } else {// 全可用
            for (IProduct item : iProduct) {
                totalPayLimitAmount += item.getSalePrice() * item.getQuantity();
            isCheckUseVoucherType = true;
            totalAmount = totalPayLimitAmount;
            }
        }
        logger.debug("校验当前选择的优惠券是否满足当前所选购商品end>>>  categoryId[" + voucherTypeRo.getCategoryId()
                + "] productIds[" + voucherTypeRo.getProductIds() + "]  isCheckUseVoucherType["
                + isCheckUseVoucherType + "]");
        if (!isCheckUseVoucherType) {
//            throw new CommonException("所选优惠券不支持选购商品", ExceptionCode.Voucher.VOUCHER_VALIDATE_ERROR);
        	throw new DepotNextDoorException(DepotNextDoorExceptions.Voucher.VOUCHER_VALIDATE_ERROR.toString());
        }
        // 订单限额判断
        logger.debug("选购商品未达到优惠券订单限额>>>  voucherTypePaymentLimit[" + voucherTypeRo.getPaymentLimit()
                + "] VoucherCount[" + voucherCount + "]  totalPayLimitAmount["
                + totalPayLimitAmount + "]");
        if (voucherTypeRo.getPaymentLimit() > 0
                && totalPayLimitAmount < voucherTypeRo.getPaymentLimit() * voucherCount) {
//            throw new CommonException("选购商品未达到优惠券订单限额",
//                    ExceptionCode.Voucher.VOUCHER_VALIDATE_ERROR);
        	throw new DepotNextDoorException(DepotNextDoorExceptions.Voucher.VOUCHER_VALIDATE_ERROR.toString());
        }
        // 选择优惠券的优惠金额
        int maxOffsetAmount = voucherTypeRo.getFaceValue()*voucherCount;
        return maxOffsetAmount;
    }
    
    /**
     * 后台优惠券列表
     * @param reqVo
     * @return
     */
    @PostMapping(value = "/searchVoucher")
	PageVO<VoucherTypePo> searchVoucher(@RequestBody VoucherSearchVo reqVo) {
		return voucherService.searchVoucher(reqVo);
	}
    
    
    /**
     * 用户组优惠券批量发放
     * @param userIdGroupsType 用户组类型
     * @param voucherTypeRo	优惠券类型
     * @param dispatcherCnt	发放数量
     * @param loginUsername 发放人
     */
    @RequestMapping(value="/dispatcherUserGroupsVoucher",method=RequestMethod.POST)
    public void dispatcherUserGroupsVoucher(@RequestParam("userIdGroupsType") Long userIdGroupsType, @RequestBody VoucherTypeRo voucherTypeRo, 
			@RequestParam("dispatcherCnt") Integer dispatcherCnt,@RequestParam("loginUsername")	String loginUsername){
    	voucherService.dispatcherUserGroupsVoucher(userIdGroupsType, voucherTypeRo, dispatcherCnt, loginUsername);
    }
}
