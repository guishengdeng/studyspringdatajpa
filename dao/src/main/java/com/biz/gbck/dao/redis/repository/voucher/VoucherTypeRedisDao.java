package com.biz.gbck.dao.redis.repository.voucher;

import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.voucher.VoucherRo;
import com.biz.gbck.dao.redis.ro.voucher.VoucherTypeRo;
import com.biz.redis.util.RedisUtil;
import com.google.common.collect.Lists;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class VoucherTypeRedisDao extends CrudRedisDao<VoucherTypeRo, Long> {

    public void save(VoucherTypeRo voucherTypeRo) {
    	super.save(voucherTypeRo);
    	sadd(getVoucherTypeSortSetKey(),
                RedisUtil.toByteArray(voucherTypeRo.getId()));
        hmset(getVoucherTypeHashKey(voucherTypeRo.getId()),
                voucherTypeRo.toMap());
    }

    public void syncVoucherList(Long voucherTypeId, List<VoucherRo> vouchers) {
        for (VoucherRo voucher : vouchers) {
        	 lpush(getVoucherTypeVoucherListKey(voucherTypeId),
                     RedisUtil.toByteArray(voucher.getId()));
             hmset(getVoucherHashKey(voucher.getId()), voucher.toMap());
        }
    }
    
    public String getVoucherHashKey(Long voucherId) {
        return "voucher:voucher_" + voucherId;
    }

    public List<VoucherTypeRo> allVoucherTypes() {
        List<VoucherTypeRo> voucherTypeRos = Lists.newArrayList();
        for (byte[] idBytes : smembers(getVoucherTypeSortSetKey())) {
            Long voucherTypeId = RedisUtil.byteArrayToLong(idBytes);
            VoucherTypeRo voucherTypeRo = this.getVoucherTypeRoById(voucherTypeId);
            if (voucherTypeRo != null) {
                voucherTypeRos.add(voucherTypeRo);
                voucherTypeRo.setIssueCount(getVoucherIssueCount(voucherTypeId));
            }
        }
        return voucherTypeRos;
    }

    public VoucherTypeRo getVoucherTypeRoById(Long id) {
        Map<byte[], byte[]> map = null;
        if (MapUtils.isNotEmpty(map)) {
            VoucherTypeRo voucherTypeRo = new VoucherTypeRo();
            voucherTypeRo.fromMap(map);
            voucherTypeRo.setIssueCount(getVoucherIssueCount(id));
            return voucherTypeRo;
        }
        return null;
    }

    private Integer getVoucherIssueCount(Long voucherTypeId) {
    	 return (int) llen(getVoucherTypeVoucherListKey(voucherTypeId));
    }

    /**
     * 优惠券类型 Sort Set key
     *
     * @return
     */
    public String getVoucherTypeSortSetKey() {
        return "global:voucher_type_set";
    }

    /**
     * 优惠券类型Hash Key
     *
     * @param id
     * @return
     */
    public String getVoucherTypeHashKey(Long id) {
        return "global:voucher_type_" + id;
    }

    /**
     * 优惠券 List Key
     *
     * @param id
     * @return
     */
    public String getVoucherTypeVoucherListKey(Long id) {
        return "voucher_type_voucher_list_" + id;
    }

    /**
     * @param key
     * @return
     * @Description: 优惠券配置Hash Key
     * @author Nian.Li
     * @date 2016年4月15日 上午10:18:30 
     */
    public String getVoucherConfigureHashKey(String key) {
        return getKeyByParams("voucher_configure",key);
    }


}
