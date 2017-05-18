package com.biz.rest.controller.voucher;

import static com.google.common.collect.Lists.newArrayList;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.common.vo.CommonReqVoBindUserId;
import com.biz.gbck.dao.redis.ro.voucher.VoucherRo;
import com.biz.gbck.dao.redis.ro.voucher.VoucherTypeRo;
import com.biz.gbck.util.DateTool;
import com.biz.rest.controller.BaseRestController;
import com.biz.rest.util.RestUtil;
import com.biz.rest.vo.voucher.VoucherReqVo;
import com.biz.rest.vo.voucher.VoucherVo;
import com.biz.service.voucher.VoucherService;
import com.biz.service.voucher.VoucherTypeService;
import com.biz.support.web.handler.JSONResult;
import com.google.common.collect.Maps;

@RestController @RequestMapping("/vouchers") 
public class VoucherController extends BaseRestController {

	
	private static final Logger logger = LoggerFactory.getLogger(VoucherController.class);
	 
    @Autowired private VoucherService voucherService;

    @Autowired private VoucherTypeService voucherTypeService;

//    @Autowired private SalePromotionService salePromotionService;

    /**
     * 获取用户下所有优惠券
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("all")
    public JSONResult allVouchers(HttpServletRequest request, HttpServletResponse response) {
        CommonReqVoBindUserId reqVo = RestUtil.parseBizData(request, CommonReqVoBindUserId.class);
        Map<String, List<VoucherVo>> voucherVoMap =
            Maps.newHashMap(getVouchersByUserId(reqVo.getUserId()));
        return new JSONResult(voucherVoMap);
    }

    /**
     * 获取可用优惠券
     * @param request
     * @param response
     * @return
     * @throws CommonException
     */
    @RequestMapping("availVouchers4shopcraft")
    public JSONResult getAvailVouchersForShopCraft(HttpServletRequest request, HttpServletResponse response)
        throws CommonException {
        VoucherReqVo reqVo = RestUtil.parseBizData(request, VoucherReqVo.class);

        // 排除掉与优惠活动互斥的优惠券
//        List<OrderItemVo> availableItemVos = this.getVoucherAvailAbleOrderItem(
//            reqVo.getOrderItemVos());

//        List<ShopCraftVoucherVo> availableVouchers = voucherService.getAvailableVouchers(reqVo.getUserId(), availableItemVos);

//        return new JSONResult(availableVouchers);
        return null;
    }


//    private List<OrderItemVo> getVoucherAvailAbleOrderItem(List<OrderItemVo> itemVos) {
//        List<OrderItemVo> orderItemVos = new ArrayList<>();
//        for (OrderItemVo itemVo : itemVos) {
//            List<SalePromotionRo> salePromotionRos = salePromotionService.getUseableSalePromotionsForProductId(itemVo.getProductId());
//            if (CollectionUtils.isNotEmpty(salePromotionRos)) {
//                boolean voucherAble = true;
//                for (SalePromotionRo salePromotionRo : salePromotionRos) {
//                    voucherAble = voucherAble && salePromotionRo.getVoucherAble();
//                }
//                if (voucherAble) {
//                    orderItemVos.add(itemVo);
//                }
//            }else {
//                logger.debug("No promotion found for product:{}", itemVo.getProductId());
//                orderItemVos.add(itemVo);
//            }
//
//        }
//        return orderItemVos;
//    }

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
}
