package com.biz.gbck.dao.redis.repository.voucher;

import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.voucher.VoucherConfigureRo;

@Component
public class VoucherConfigureRedisDao extends CrudRedisDao<VoucherConfigureRo, Long>{
	/**
	 * 
	 * @Description: 往redis里面存优惠券配置信息
	 * @param voucherConfigureRo
	 */
    public void save(VoucherConfigureRo voucherConfigureRo) {
    	hmset(getVoucherConfigureHashKey(voucherConfigureRo.getVoucherconfigure()), voucherConfigureRo.toMap());
    }
    
    /**
     * 
     * @Description: 根据配置类型从redis中获取对应的优惠券类型
     * @param voucherconfigure
     * @return
     */
    public VoucherConfigureRo getVoucherConfigureRo(String voucherconfigure) {
        if (voucherconfigure == null) {
            return null;
        }
        Map<byte[], byte[]> map = hgetAll(getVoucherConfigureHashKey(voucherconfigure));
        if (MapUtils.isNotEmpty(map)) {
	    	VoucherConfigureRo ro = new VoucherConfigureRo();
	        ro.fromMap(map);
	        return ro;
        } 
        return null;
    }
    
    /**
     * 
     * @Description: 从redis中删除优惠券配置信息
     * @param voucherconfigure
     */
    public void delete(String voucherconfigure) {
        del(getVoucherConfigureHashKey(voucherconfigure));
    }

    /**
     * @param key
     * @return
     * @Description: 优惠券配置Hash Key
     */
    public static String getVoucherConfigureHashKey(String key) {
        return "voucher_configure:" + key;
    }
}
