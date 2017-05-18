package com.biz.gbck.dao.redis.repository.voucher;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codelogger.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.biz.gbck.dao.mysql.po.voucher.VoucherExpireType;
import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.voucher.VoucherRo;
import com.biz.gbck.dao.redis.ro.voucher.VoucherTypeRo;
import com.biz.gbck.util.DateTool;
import com.biz.redis.util.RedisUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import static com.google.common.collect.Lists.newArrayList;

@Component
public class VoucherRedisDao extends CrudRedisDao<VoucherRo, Long>{
	
    private static final Logger logger = LoggerFactory.getLogger(VoucherRedisDao.class);

    @Autowired VoucherTypeRedisDao voucherTypeRedisDao;

	public VoucherRo getVoucherRo(Long id) {
		Map<byte[], byte[]> map = hgetAll(getVoucherHashKey(id));
		if (map == null || map.isEmpty()) {
			return null;
		}
		VoucherRo ro = new VoucherRo();
		ro.fromMap(map);
		return ro;
	}

    public VoucherRo bindVoucherToUser(Long userId, VoucherTypeRo voucherType) {
        Long voucherTypeId = voucherType.getId();
        byte[] bytes = lpop(voucherTypeRedisDao.getVoucherTypeVoucherListKey(voucherTypeId));
        if(bytes!=null){
	        Long voucherId = RedisUtil.byteArrayToLong(bytes);
	        VoucherRo voucherRo = getVoucherRo(voucherId);
	        if(voucherRo!=null){
		        voucherRo.setBindingUserId(userId);
		        voucherRo.setCreateTime(System.currentTimeMillis());
              voucherRo.setExpireTime(
                  voucherType.getVoucherExpireType() == VoucherExpireType.EXPIRE_BY_PERIOD ?
                      DateTool.afterDays(voucherType.getPeriodDays()) :
                      voucherType.getExpireTime());
              hmset(getVoucherHashKey(voucherId), voucherRo.toMap());
		        move(voucherRo.getId(), null, getUserAllVouchersSortSetKey(userId));
		        move(voucherRo.getId(), null, getUserVoucherUnusedSortSetKey(userId));
		        return voucherRo;
	        }else{
	        	logger.error("voucher id : {} is not exists",voucherId);
	        }
        }else{
        	logger.error("voucher id queue  for {} is empty",voucherType.getId());
        }
        return null;
    }

    
    public void use(Long userId, Long voucherRoId) {
        VoucherRo voucherRo = new VoucherRo();
        voucherRo.fromMap(hgetAll(getVoucherHashKey(voucherRoId)));
        if(new Date().getTime() > voucherRo.getExpireTime()) {
            move(voucherRoId, getUserVoucherUnusedSortSetKey(userId),
                    getUserVoucherExpiredSortSetKey(userId));
        } else {
            move(voucherRoId, getUserVoucherUnusedSortSetKey(userId),
                    getUserVoucherUsedSortSetKey(userId));
        }
    }

    public void expire(Long userId, Long voucherRoId) {
        move(voucherRoId, getUserVoucherUnusedSortSetKey(userId),
                getUserVoucherUsedSortSetKey(userId));
    }

    /**
     * 获取用户所有可用优惠券
     */
    public List<VoucherRo> listAllUsableVoucher(Long userId){
        return getVouchers(getUserVoucherUnusedSortSetKey(userId),null);
    }

    public Map<String, List<VoucherRo>> allVouchers(Long userId) {
        Map<String, List<VoucherRo>> map = Maps.newHashMap();
        
        /**
         * map的key说明：
         * unused-未使用的集合
         * expired-过期的集合
         */
        List<VoucherRo> unusedVouchers = getUnusedVouchers(getUserVoucherUnusedSortSetKey(userId));

        List<VoucherRo> useableVoucherRos = newArrayList();
        List<VoucherRo> usedVoucherRos = newArrayList();
        List<VoucherRo> expiredVoucherRos = newArrayList();
        for (VoucherRo unusedVoucher : unusedVouchers) {
            if(unusedVoucher.getUseTime() == null){
                if(unusedVoucher.getExpireTime() != null && unusedVoucher.getExpireTime() < System.currentTimeMillis()){
                    VoucherTypeRo voucherType =
                        voucherTypeRedisDao.getVoucherTypeRoById(unusedVoucher.getVoucherTypeId());
                    if(voucherType != null && voucherType.getVoucherExpireType() == VoucherExpireType.EXPIRE_BY_PERIOD){
                        expiredVoucherRos.add(unusedVoucher);
                    } else if(voucherType != null && voucherType.getExpireTime() < System.currentTimeMillis()){
                        expiredVoucherRos.add(unusedVoucher);
                    } else {
                        useableVoucherRos.add(unusedVoucher);
                    }
                } else {
                    useableVoucherRos.add(unusedVoucher);
                }
            } else {
                usedVoucherRos.add(unusedVoucher);
            }
        }
        map.put("unused", useableVoucherRos);
        map.put("used", getVouchers(getUserVoucherUsedSortSetKey(userId),null));
        map.put("expired", getVouchers(getUserVoucherExpiredSortSetKey(userId),expiredVoucherRos));
        return map;
    }

    public int getVoucherTypeVoucherCount(Long voucherTypeId) {
        return new Long(llen(voucherTypeRedisDao.getVoucherTypeVoucherListKey(voucherTypeId))).intValue();
    }

    private void move(Long voucherRoId, String from, String to) {
        if(StringUtils.isEmpty(to)) return ;
        if(StringUtils.isNotBlank(from)) {
            srem(from, RedisUtil.toByteArray(voucherRoId));
        }
        sadd(to, RedisUtil.toByteArray(voucherRoId));
    }

    /**
     * 
     * @Description: 
     * @param key
     * @return
     */
    public List<VoucherRo> getVouchers(String key,List<VoucherRo> vrs) {
        List<VoucherRo> voucherRos = newArrayList();
        for(byte[] voucherId : smembers(key)) {
            VoucherRo voucherRo = getVoucherRo(RedisUtil.byteArrayToLong(voucherId));
            if(voucherRo!=null){
            	voucherRos.add(voucherRo);
            }
        }
        if(vrs!=null){
        	voucherRos.addAll(vrs);
        }
        return voucherRos;
    }
    
    /**
     * 
     * @Description: 获取未使用的优惠券
     * @param key
     * @return  
     * unused-未使用的集合
     * expired-过期的集合
     */
    public List<VoucherRo> getUnusedVouchers(String key) {
        List<VoucherRo> unusedVoucherRos = newArrayList();// 未使用的优惠券
        for(byte[] voucherId : smembers(key)) {
            VoucherRo voucherRo = new VoucherRo();
            voucherRo.fromMap(hgetAll(getVoucherHashKey(RedisUtil.byteArrayToLong(voucherId))));
            unusedVoucherRos.add(voucherRo);
        }
        return unusedVoucherRos;
    }  
    
    /**
     * 
     * @Description: 根据优惠券类型集合获取对应的未使用但已过期的优惠券数据
     * @param allVoucherTypes
     * @return
     */
    public List<VoucherRo> getVouchersByType(List<VoucherTypeRo> allVoucherTypes) {
        List<VoucherRo> voucherRos = newArrayList();
    	String key = "";
	    if(allVoucherTypes!=null && allVoucherTypes.size()>0){
	    	for(VoucherTypeRo voucherTypeRo:allVoucherTypes){
	    		key = voucherTypeRedisDao.getVoucherTypeVoucherListKey(voucherTypeRo.getId());
	            for(byte[] voucherId : smembers(key)) {
	                VoucherRo voucherRo = new VoucherRo();
	                voucherRo.fromMap(hgetAll(getVoucherHashKey(RedisUtil.byteArrayToLong(voucherId))));
	                if(voucherRo.getBindingUserId()!=null
	                		&& voucherRo.getUseTime()==null 
	                		&& voucherRo.getExpireTime()<System.currentTimeMillis()){//该优惠券未使用，但已经过期
	                	 voucherRos.add(voucherRo);
	                }
	            }
	    	}
        }
        return voucherRos;
    }    
    
    /**
     * 优惠券 Hash Key
     *
     * @param voucherId
     * @return
     */
    public static String getVoucherHashKey(Long voucherId) {
        return "voucher:voucher_" + voucherId;
    }

    /**
     * 用户未使用优惠券 Sort Set Key
     *
     * @param userId
     * @return
     */
    public static String getUserVoucherUnusedSortSetKey(Long userId) {
        return "user:voucher_unused:" + userId;
    }

    /**
     * 用户已使用优惠券 Sort Set Key
     *
     * @param userId
     * @return
     */
    public static String getUserVoucherUsedSortSetKey(Long userId) {
        return "user:voucher_used:" + userId;
    }

    /**
     * 用户已过期优惠券 Sort Set Key
     *
     * @param userId
     * @return
     */
    public static String getUserVoucherExpiredSortSetKey(Long userId) {
        return "user:voucher_expired:" + userId;
    }

    public static String getUserAllVouchersSortSetKey(Long userId) {
        return "user:voucher_all:" + userId;
    }

}
