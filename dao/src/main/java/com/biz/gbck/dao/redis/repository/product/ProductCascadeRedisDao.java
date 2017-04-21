package com.biz.gbck.dao.redis.repository.product;

import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.product.master.ProductCascadeRo;
import com.biz.redis.util.RedisUtil;
import com.google.common.collect.Lists;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

/**
 * 商品配置(款型)RedisDao
 *
 * @author david-liu
 * @date 2017年01月04日
 * @reviewer
 */
@Repository
public class ProductCascadeRedisDao extends CrudRedisDao<ProductCascadeRo, Long> implements Serializable {

    private static final long serialVersionUID = -2311004434429662957L;

    public void setVendorProduct2CascadeMapping(List<Long> vendorIds, List<String> productCodes, Long cascadeId) {
        assert CollectionUtils.isNotEmpty(vendorIds) && CollectionUtils.isNotEmpty(productCodes) && vendorIds.size() == productCodes.size();
        List<String> keys = Lists.newArrayList();
        List<Long> values = Lists.newArrayList();
        for (int i = 0; i < vendorIds.size(); i++) {
            Long vendorId = vendorIds.get(i);
            String productCode = productCodes.get(i);
            keys.add(this.getVendorProduct2CascadeStringKey(vendorId, productCode));
            values.add(cascadeId);
        }
        List<byte[]> valuesByteArray = Lists.newArrayList();
        for (Long index : values) {
            valuesByteArray.add(RedisUtil.toByteArray(index));
        }
        super.pipeSet(keys, valuesByteArray);
    }

    public ProductCascadeRo getCascadeByVendorIdAndProductCode(Long vendorId, String productCode) {
        byte[] bytes = super.get(this.getVendorProduct2CascadeStringKey(vendorId, productCode));
        Long cascadeId = RedisUtil.byteArrayToLong(bytes);
        return super.findOne(cascadeId);
    }

    private String getVendorProduct2CascadeStringKey(Long vendorId, String productCode) {
        return super.getKeyByParams("vendorId", vendorId, "productCode", productCode);
    }
}
