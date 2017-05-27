package com.biz.rest.controller.voucher;

import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.common.vo.CommonReqVoBindUserId;
import com.biz.gbck.vo.order.req.ProductItemReqVo;
import com.biz.gbck.vo.order.resp.ProductInfoVo;
import com.biz.gbck.vo.promotion.PromotionReqVo;
import com.biz.gbck.vo.voucher.VoucherRequestVo;
import com.biz.rest.controller.BaseRestController;
import com.biz.rest.util.RestUtil;
import com.biz.soa.feign.client.org.UserFeignClient;
import com.biz.soa.feign.client.product.PromotionFeignClient;
import com.biz.soa.feign.client.voucher.VoucherFeignClient;
import com.biz.support.web.handler.JSONResult;
import com.biz.vo.voucher.ShopCraftVoucherVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController @RequestMapping("/vouchers") 
public class VoucherController extends BaseRestController {

	
	private static final Logger logger = LoggerFactory.getLogger(VoucherController.class);
	 
    @Autowired 
    private VoucherFeignClient voucherFeignClient;
    
    @Autowired
    private UserFeignClient  userFeignClient;

    @Autowired 
    private PromotionFeignClient promotionFeignClient;

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
    	Long CompanyGroupId = null;//userFeignClient.findCompanyGroupIdByUserId(reqVo.getUserId());
        // 排除掉与优惠活动互斥的优惠券
    	List<ProductInfoVo> voucherAvailAbleOrderItems = this.getVoucherAvailAbleOrderItem(reqVo.getOrderItemVos(),CompanyGroupId);
        
        List<ShopCraftVoucherVo> availableVouchers = voucherFeignClient.getAvailableVouchers(reqVo.getUserId(), voucherAvailAbleOrderItems);

        return new JSONResult(availableVouchers);
    }


    private List<ProductInfoVo> getVoucherAvailAbleOrderItem(List<ProductItemReqVo> itemVos, Long userGroupsId) {
        List<ProductInfoVo> orderItemVos = new ArrayList<>();
        for (ProductItemReqVo itemVo : itemVos) {
            ProductInfoVo productInfoVo = new ProductInfoVo();
            productInfoVo.setProductId(Long.valueOf(itemVo.getProductId()));
            productInfoVo.setQuantity(itemVo.getQuantity());
            //TODO categoryId etc.
            PromotionReqVo promotionReqVo = new PromotionReqVo();
        	//TODO
//        	promotionReqVo.setCategoryId();
        	promotionReqVo.setProductIds(Long.valueOf(itemVo.getProductId()));
        	promotionReqVo.setCompanyGroupId(userGroupsId);
        	//TODO
//            List<PromotionRespAppVo> promotionRespAppVos = promotionFeignClient.getUseablePromotionsForProductId(promotionReqVo);
//            if (CollectionUtils.isNotEmpty(promotionRespAppVos)) {
//                boolean voucherAble = true;
//                for (PromotionRespAppVo promotionRespAppVo : promotionRespAppVos) {
//                    voucherAble = voucherAble && promotionRespAppVo.getVoucherAble();
//                }
//                if (voucherAble) {
//                    orderItemVos.add(itemVo);
//                }
//            }else {
//                logger.debug("No promotion found for product:{}", itemVo.getProductId());
//                orderItemVos.add(itemVo);
//            }
        	orderItemVos.add(productInfoVo);
        }
        return orderItemVos;
    }
}
