package com.biz.service.voucher;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.biz.gbck.common.model.order.IOrderItemVo;
import com.biz.gbck.common.model.voucher.VoucherConfigure;
import com.biz.gbck.dao.mysql.po.voucher.VoucherPo;
import com.biz.gbck.dao.redis.ro.org.UserRo;
import com.biz.gbck.dao.redis.ro.voucher.VoucherRo;
import com.biz.gbck.dao.redis.ro.voucher.VoucherTypeRo;
import com.biz.gbck.vo.product.frontend.ProductListItemVo;
import com.biz.gbck.vo.voucher.UserVoucherStatisticResultVo;
import com.biz.vo.voucher.ShopCraftVoucherVo;

public interface VoucherService{
    /**
     * 获取指定分类的可用优惠券
     *
     * @param userId        用户id
     * @param voucherTypeId 优惠券类型id
     */
    public Collection<VoucherRo> findUsableVouchersByUserIdAndVoucherType(Long userId,
        Long voucherTypeId);
    
    public VoucherRo fetchVoucher(Long userId, VoucherTypeRo voucherTypeRo);
    
    public void useVoucher(Long userId, Long voucherId, Long orderId, Integer offSetAmount);
    public void expireVoucher(Long userId, Long voucherId); 
    
    public Map<String, List<VoucherRo>> allVouchers(Long userId);
    
    public List<VoucherPo> listAllVouchersByUserId(Long userId);
    public Map<Long, VoucherRo> unusedVouchers(Long userId);
    /**
     * 获取用户可用优惠券数量
     */
    public Integer getUserUseableVoucherCount(Long userId);
    
    public Map<Long, List<VoucherRo>> divideUnusedVouchersByCategory(Long userId);
    
    public Map<Long, List<VoucherRo>> divideVouchersByVoucherType(
        Collection<VoucherRo> voucherRos);
    
    public int getAvailVoucherCount(List<ProductListItemVo> items, UserRo userRo) throws Exception;
    /**
     * 发放优惠券
     *
     * @param userIds
     * @param voucherTypeRo
     * @param dispatcherCnt
     * @throws CommonException
     */
    public void dispatcherVoucher(List<Long> userIds, VoucherTypeRo voucherTypeRo,
        int dispatcherCnt, String handler);
    /**
     * 发放优惠券
     *
     * @param userId
     * @param voucherTypeRo
     * @param quantity
     */
    public void dispatcherVoucher(Long userId, VoucherTypeRo voucherTypeRo, int quantity,
        String handler); 
    
    public boolean validateDispatcherAction(List<Long> userIds, Long shopTypeId, Long voucherTypeId,
        int dispatcherCount); 
    
    public VoucherPo findVoucherPoById(Long voucherId); 
    /**
     * 根据优惠券id查找对应数量
     *
     * @param voucherTypeId:优惠卷类型id
     * @return
     */
    public Integer findVoucherNumberById(Long voucherTypeId);
    /**
     * 定期执行
     * 将还有三天就将到期的优惠卷信息发送给用户
     */
    public void expiredVouchersSendNotificationsTask() throws Exception;
    /**
     * 定期执行
     * 把用户未使用但过期的优惠券更改为已过期
     */
    public void updateExpiredVoucherTask();
    /**
     * 根据动作类型发券
     *
     * @param userId
     */
    public void dispatcherVoucherByAction(Long userId, VoucherConfigure action);
    /**
     * 获取可用优惠券
     */
    public List<ShopCraftVoucherVo> getAvailableVouchers(Long userId,
        List<? extends IOrderItemVo> itemVos) throws Exception;

    public List<UserVoucherStatisticResultVo> findUserOrderStatsBy(
        Map<String, Object> searchParams);
}
