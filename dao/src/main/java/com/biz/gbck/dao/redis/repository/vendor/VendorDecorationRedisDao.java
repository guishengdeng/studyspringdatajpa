package com.biz.gbck.dao.redis.repository.vendor;

import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.vendor.VendorDecorationRo;
import com.biz.redis.util.RedisUtil;
import org.springframework.stereotype.Repository;

@Repository
public class VendorDecorationRedisDao extends CrudRedisDao<VendorDecorationRo, Long> {


    @Override
    public void save(VendorDecorationRo ro) {
        super.save(ro);
        String key = vendorIdMappingKey(ro.getVendorId());
        zadd(key, ro.getCreateTimestamp(), ro.getId());
    }

    @Override
    public void delete(VendorDecorationRo ro) {
        super.delete(ro);
        zrem(vendorIdMappingKey(ro.getVendorId()), RedisUtil.toByteArray(ro.getId()));
    }

    /**
     * 根据ID查询装饰
     */
    public VendorDecorationRo findVendorDecoration(Long id) {
        return findOne(id);
    }

    /**
     * 根据vendorId查询店铺装饰
     */
    public VendorDecorationRo findVendorDecorationByVendorId(Long vendorId) {
        String key = vendorIdMappingKey(vendorId);
        return findByKey(key);
    }


    private String vendorIdMappingKey(Long vendorId) {
        return getKeyByParams("vendorId", vendorId);
    }
}
