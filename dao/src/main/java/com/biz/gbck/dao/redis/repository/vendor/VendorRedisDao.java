package com.biz.gbck.dao.redis.repository.vendor;

import com.biz.core.util.JsonUtil;
import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.vendor.VendorRo;
import com.biz.redis.util.RedisUtil;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.codelogger.utils.ArrayUtils;
import org.codelogger.utils.CollectionUtils;
import org.springframework.stereotype.Repository;


/**
 * 商铺Redis Dao
 *
 * @author LGJ
 */
@Repository
public class VendorRedisDao extends CrudRedisDao<VendorRo, Long> {


    @Override
    public void save(VendorRo ro) {
        super.save(ro);
        String key2 = userIdMappingKey(ro.getUserId());
        zadd(key2, ro.getCreateTimestamp(), ro.getId());
        if (ro.getVendorName().equals("1919自营")) {
            set(getKeyByParams("vendorName", ro.getVendorName()), RedisUtil.toByteArray(ro.getId()));
        }
        String stautsKey = vendorStatusMappingKey(ro.getUserId());
        set(stautsKey, RedisUtil.toByteArray(ro.getStatus().toString()));
        mappingVendorCodeToVendorIdMappingKey(ro);
    }

    /**
     * 做VendorCode到VendorId的映射
     */
    private void mappingVendorCodeToVendorIdMappingKey(VendorRo ro) {
        if (ro != null && StringUtils.isNotBlank(ro.getVendorCode()) && ro.getId() != null) {
            set(getCodeToIdHashKey(ro.getVendorCode()), RedisUtil.toByteArray(ro.getId()));
            zadd(getCodeSetsKey(), System.currentTimeMillis(), RedisUtil.toByteArray(ro.getVendorCode()));
        }
    }

    /**
     * 移除VendorCode到VendorId的映射
     */
    private void removeMappingOfVendorCodeToVendorId(String vendorCode) {
        if (StringUtils.isNotBlank(vendorCode)) {
            zrem(getCodeSetsKey(), RedisUtil.toByteArray(vendorCode));
            del(getCodeToIdHashKey(vendorCode));
        }
    }

    private String getCodeSetsKey() {

        return getKeyByParams(getKeyPrefix(), "codeSets");
    }

    private String getCodeToIdHashKey(String vendorCode) {

        return getKeyByParams(getKeyPrefix(), "codeToId", vendorCode);
    }

    private String vendorStatusMappingKey(Long userId) {
        return getKeyByParams("VendorRo:Status", userId);
    }

    /**
     * 重做VendorCode到VendorId的映射
     */
    public void remappingVendorCodeToVendorId() {

        List<String> codes = RedisUtil.bytesSetToStringList(zRange(getCodeSetsKey(), 0, -1));
        if (CollectionUtils.isEmpty(codes)) {
            return;
        }
        List<VendorRo> vendors = findAll();
        if (CollectionUtils.isEmpty(vendors)) {
            return;
        }
        for (VendorRo vendor : vendors) {
            if (vendor == null) {
                continue;
            }
            mappingVendorCodeToVendorIdMappingKey(vendor);
            codes.remove(vendor.getVendorCode());
        }
        for (String vendorCode : codes) {
            removeMappingOfVendorCodeToVendorId(vendorCode);
        }
    }

    /**
     * 根据VendorCode查找VendorId
     *
     * @param vendorCode 店铺编号
     */
    public Long findVendorIdByVendorCode(String vendorCode) {

        if (StringUtils.isBlank(vendorCode)) {
            return null;
        }
        return RedisUtil.byteArrayToLong(get(getCodeToIdHashKey(vendorCode)));
    }

    public String isMerChant(Long userId) {
        String key = vendorStatusMappingKey(userId);
        byte[] status = get(key);
        if (status != null && status.length > 0) {
            return RedisUtil.byteArrayToStr(status);
        }
        return null;
    }

    public String getVendorIdByName(String vendorName) {
        byte[] vendorId = get(getKeyByParams("vendorName", vendorName));
        if (vendorId != null && vendorId.length > 0) {
            return RedisUtil.byteArrayToStr(vendorId);
        }
        return null;
    }


    @Override
    public void delete(VendorRo ro) {
        super.delete(ro);
        zrem(userIdMappingKey(ro.getUserId()), RedisUtil.toByteArray(ro.getId()));
        removeMappingOfVendorCodeToVendorId(ro.getVendorCode());
    }

    /**
     * 根据ID查询店铺详情
     */
    public VendorRo findById(Long id) {
        return findOne(id);
    }


    /**
     * 根据ID集合查询店铺
     */
    public List<VendorRo> findVendorByIds(List<Long> ids) {
        return findByIds(ids);
    }

    /**
     * 根据ID集合查询店铺 包括NULL
     */
    public List<VendorRo> findVendorByIdsWithNull(List<Long> ids) {
        return findByIdsWithNull(ids);
    }

    /**
     * 通过用户ID查询店铺信息
     */
    public List<Long> findVendorIdByUserId(Long userId) {
        Long[] result = RedisUtil.bytesSetToLongArray(zRange(userIdMappingKey(userId), 0, -1));
        return ArrayUtils.isEmpty(result) ? Collections.<Long>emptyList() : ArrayUtils.toList(result);
    }


    public VendorRo findVendorByProperty(Long id, List<byte[]> property) {
        byte[][] bytes = RedisUtil.ListBytetoByteArray(property);
        List<byte[]> list = hmget(getHashKey(id), bytes);
        String jsonStr = "{";
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                String values = list.get(i).toString();
                String propertys = property.get(i).toString();
                jsonStr += "\"" + propertys + "\":\"" + values + "\"";
            }
        }
        jsonStr += "}";
        VendorRo ro = JsonUtil.json2Obj(jsonStr, VendorRo.class);
        return ro;
    }


    public String getHashKey(Serializable id) {
        return new StringBuffer(getKeyPrefix()).append(":").append(id).toString();
    }


    private String userIdMappingKey(Long userId) {
        return getKeyByParams("userId", userId);
    }

}
