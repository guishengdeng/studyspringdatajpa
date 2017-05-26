package com.biz.rest.controller.voucher;

import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.common.vo.CommonReqVoBindUserId;
import com.biz.gbck.dao.redis.ro.voucher.VoucherRo;
import com.biz.gbck.dao.redis.ro.voucher.VoucherTypeRo;
import com.biz.gbck.util.DateTool;
import com.biz.gbck.vo.order.resp.IProduct;
import com.biz.gbck.vo.voucher.PromotionReqVo;
import com.biz.gbck.vo.voucher.VoucherReqVo;
import com.biz.gbck.vo.voucher.VoucherRequestVo;
import com.biz.gbck.vo.voucher.VoucherVo;
import com.biz.rest.controller.BaseRestController;
import com.biz.rest.util.RestUtil;
import com.biz.rest.vo.order.OrderItemVo;
import com.biz.soa.feign.client.voucher.VoucherFeignClient;
import com.biz.soa.feign.client.voucher.VoucherTypeFeignClient;
import com.biz.support.web.handler.JSONResult;
import com.biz.vo.voucher.ShopCraftVoucherVo;
import com.google.common.collect.Maps;

@RestController @RequestMapping("/vouchers") 
public class VoucherController extends BaseRestController {

	
	private static final Logger logger = LoggerFactory.getLogger(VoucherController.class);
	 
    @Autowired 
    private VoucherFeignClient voucherFeignClient;

//    @Autowired 
//    private SalePromotionService salePromotionService;

    /**
     * 获取用户下所有优惠券
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("all")
    public JSONResult allVouchers(HttpServletRequest request, HttpServletResponse response) {
        CommonReqVoBindUserId reqVo = RestUtil.parseBizData(request, CommonReqVoBindUserId.class);
        return voucherFeignClient.allVouchers(reqVo.getUserId());
    }

    /**
     * 获取可用优惠券
     * @param request
     * @param response
     * @return
     * @throws CommonException
     */
    @RequestMapping(value = "availVouchers4shopcraft",method =RequestMethod.POST)
    public JSONResult getAvailVouchersForShopCraft(HttpServletRequest request, HttpServletResponse response)
        throws CommonException {
    	VoucherRequestVo reqVo = RestUtil.parseBizData(request, VoucherRequestVo.class);
    	//通过userId获取用户组userGroupsIdId
    	//TODO
    	Long userGroupsId = null;
        // 排除掉与优惠活动互斥的优惠券
    	List<? extends IProduct> iproductList = this.getVoucherAvailAbleOrderItem(reqVo.getOrderItemVos(),userGroupsId);
        
        List<ShopCraftVoucherVo> availableVouchers = voucherFeignClient.getAvailableVouchers(reqVo.getUserId(), iproductList);

        return new JSONResult(availableVouchers);
    }


    private List<IProduct> getVoucherAvailAbleOrderItem(List<? extends IProduct> itemVos,Long userGroupsId) {
        List<IProduct> orderItemVos = new ArrayList<>();
        for (IProduct itemVo : itemVos) {
        	PromotionReqVo promotionReqVo = new PromotionReqVo();
        	promotionReqVo.setCategoryId(itemVo.getCategoryId());
        	promotionReqVo.setProductIds(itemVo.getProductId());
        	promotionReqVo.setCompanyGroupId(userGroupsId);
        	//TODO
//            List<SalePromotionRo> salePromotionRos = salePromotionService.getUseablePromotionsForProductId(promotionReqVo);
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

        }
        return orderItemVos;
    }
}
