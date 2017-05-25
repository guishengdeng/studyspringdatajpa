package com.biz.soa.feign.client.voucher;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.biz.gbck.dao.mysql.po.voucher.VoucherPo;
import com.biz.gbck.dao.mysql.po.voucher.VoucherTypePo;
import com.biz.gbck.dao.redis.ro.voucher.VoucherRo;
import com.biz.gbck.dao.redis.ro.voucher.VoucherTypeRo;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.vo.order.resp.IOrderPeriodQueryReqVo;
import com.biz.gbck.vo.order.resp.IProduct;
import com.biz.gbck.vo.spring.PageVO;
import com.biz.gbck.vo.voucher.VoucherSearchVo;
import com.biz.soa.feign.hystrix.voucher.VoucherFeignClientHystrix;
import com.biz.vo.voucher.ShopCraftVoucherVo;
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
	public boolean validateDispatcherAction(@RequestBody List<Long> userIds, @RequestParam("shopTypeId") Long shopTypeId, 
				@RequestParam("voucherTypeId") Long voucherTypeId, @RequestParam("dispatcherCnt") int dispatcherCnt);

	@RequestMapping(value = "/soa/voucher/dispatcherVoucher", method = RequestMethod.POST)
	public void dispatcherVoucher(@RequestParam("userIds") List<Long> userIds, @RequestBody VoucherTypeRo voucherTypeRo, 
			@RequestParam("dispatcherCnt") Integer dispatcherCnt,@RequestParam("loginUsername")	String loginUsername);

	@RequestMapping(value = "/soa/voucher/findVoucherNumberById", method = RequestMethod.POST)
	public int findVoucherNumberById(@RequestParam("voucherTypeId") Long voucherTypeId);
	
	/**
     * 获取优惠券可用数量
     * @param reqVo
     * @return
     */
    @RequestMapping(value = "/soa/voucher/getUsableCount", method = RequestMethod.POST)
    public int getUsableCount(@RequestBody IOrderPeriodQueryReqVo reqVo);
    
    /**
     *  购物车,获取可用优惠券
     * @param userId
     * @param itemVos
     * @return
     */
    @RequestMapping(value="/soa/voucher/getAvailableVouchers",method=RequestMethod.POST)
    public  List<ShopCraftVoucherVo> availableVouchers(@RequestParam("userId") Long userId,@RequestBody List<? extends IProduct> itemVos);

    /**
     * 获取优惠额度
     * @param iOrderPeriodQueryReqVo
     * @return
     */
    @RequestMapping(value="/soa/voucher/getVoucherlimit",method=RequestMethod.POST)
    public int getVoucherLimit(@RequestBody IOrderPeriodQueryReqVo iOrderPeriodQueryReqVo) throws DepotNextDoorException;
    
    /**
     * 使用优惠券
     * @param iOrderPeriodQueryReqVo
     */
    @RequestMapping(value="/soa/voucher/useVoucher",method=RequestMethod.POST)
    public void useVoucher(@RequestBody IOrderPeriodQueryReqVo iOrderPeriodQueryReqVo);
    
    
    /**
     * 后台优惠券列表
     * @return
     */
    @RequestMapping(value="/soa/voucher/searchVoucher",method=RequestMethod.POST)
    public PageVO<VoucherTypePo> searchVoucher(@RequestBody VoucherSearchVo voucherSearchVo);
    
    /**
     * 用户组优惠券批量发放
     * @param userIdGroupsType 用户组类型
     * @param voucherTypeRo	优惠券类型
     * @param dispatcherCnt	发放数量
     * @param loginUsername 发放人
     */
    @RequestMapping(value="/soa/voucher/dispatcherUserGroupsVoucher",method=RequestMethod.POST)
    public void dispatcherUserGroupsVoucher(@RequestParam("userIdGroupsType") String userIdGroupsType, @RequestBody VoucherTypeRo voucherTypeRo, 
			@RequestParam("dispatcherCnt") Integer dispatcherCnt,@RequestParam("loginUsername")	String loginUsername);
}
