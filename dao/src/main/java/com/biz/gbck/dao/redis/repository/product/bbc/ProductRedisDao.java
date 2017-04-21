package com.biz.gbck.dao.redis.repository.product.bbc;

import com.biz.core.util.StringTool;
import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.product.bbc.ProductRo;
import com.biz.gbck.enums.product.VendorTypeEnum;
import com.biz.redis.util.RedisUtil;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

/**
 * 商品 RedisDao
 *
 * @author david-liu
 * @date 2017年01月04日
 * @reviewer
 * @see
 */
@Repository
public class ProductRedisDao extends CrudRedisDao<ProductRo, String> implements Serializable {

    private static final long serialVersionUID = -4837987643262273637L;

    public void incrSalesVolume(List<String> productCode, List<Integer> quantities) {
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(productCode)
                && CollectionUtils.isNotEmpty(quantities)
                && productCode.size() == quantities.size());
        Map<String, Integer> keyToQuantity = Maps.newHashMap();
        for (int i = 0; i < productCode.size(); i++) {
            String code = productCode.get(i);
            Integer quantity = quantities.get(i);
            keyToQuantity.put(getHashKey(code), quantity);
        }
        super.pipeHincrBy(keyToQuantity, "salesVolume");
    }

    public void appendProductCode2TypeProductSortedSet(String productCode, VendorTypeEnum type) {
        if (type == null) return;
        String sortedSetKey = type == VendorTypeEnum.TYPE_A ? this.getTypeAProductsSortedSetKey() : this.getTypeBProductsSortedSetKey();
        super.zadd(sortedSetKey, new Date(System.currentTimeMillis()), productCode);
    }

    public void removeProductCodeFromProductSortedSet(String productCode, VendorTypeEnum type) {
        if (type == null) return;
        String sortedSetKey = type == VendorTypeEnum.TYPE_A ? this.getTypeAProductsSortedSetKey() : this.getTypeBProductsSortedSetKey();
        super.zrem(sortedSetKey, productCode);
    }

    /**
     * 设置商品可销售区域对应关系
     *
     * @param productCode 商品编码
     * @param geoIds 区域Id字符串
     */
    public void setProductSaleGeoIds(String productCode, String geoIds) {
        super.set(this.getTypeAProductGeoIdsMappingStringKey(productCode), RedisUtil.toByteArray(geoIds));
    }

    /**
     * 批量设置商品可销售区域对应关系
     *
     * @param productCodes 商品编码集合
     * @param geoIds 区域Id字符串集合
     */
    public void pipeSetProductSaleGeoIds(List<String> productCodes, List<String> geoIds) {
        assert CollectionUtils.isNotEmpty(productCodes) &&
                CollectionUtils.isNotEmpty(geoIds) &&
                productCodes.size() == geoIds.size();
        List<String> keys = Lists.newArrayList();
        List<byte[]> values = Lists.newArrayList();
        for (int i = 0; i < productCodes.size(); i++) {
            String productCode = productCodes.get(i);
            String geoId = geoIds.get(i);
            keys.add(this.getTypeAProductGeoIdsMappingStringKey(productCode));
            values.add(RedisUtil.toByteArray(geoId));
        }
        super.pipeSet(keys, values);
    }

    public List<String> getProductSaleGeoIdMapping(String productCode) {
        if (StringUtils.isBlank(productCode)) {
            return Lists.newArrayList();
        }
        String ids = RedisUtil.byteArrayToStr(super.get(this.getTypeAProductGeoIdsMappingStringKey(productCode)));
        return StringUtils.isBlank(ids) ? Lists.<String>newArrayList() : StringTool.split(ids, ",");
    }

    public List<Object> pipeGetProductSaleGeoIdsMapping(List<String> productCodes) {
        List<String> keys = Lists.newArrayList();
        List<Object> res = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(productCodes)) {
            for (String productCode : productCodes) {
                keys.add(this.getTypeAProductGeoIdsMappingStringKey(productCode));
            }
            res = super.pipeGet(keys);
        }
        return res;
    }

    /**
     * 移除商品可销售区域对应关系
     *
     * @param productCode 商品编码
     */
    public void removeProductSaleGeoIds(String productCode) {
        super.del(this.getTypeAProductGeoIdsMappingStringKey(productCode));
    }

    /**
     * 返回 B 类商品的销售黑名单
     *
     * @return 商品 ID 集合
     */
    public List<String> getTypeBProductSaleBlackList() {
        String sortedSetKey = this.getTypeBProductSaleBlackListSortedSetKey();
        Set<byte[]> idBytes = zRange(sortedSetKey, 0, -1);
        String[] productCodes = RedisUtil.bytesSetToStringArray(idBytes);
        return ArrayUtils.isNotEmpty(productCodes) ? Lists.newArrayList(productCodes) : Lists.<String>newArrayList();
    }

    /**
     * 添加内容到商品销售黑名单当中
     */
    public void append2TypeBProductBlackList(String productCode) {
        String sortedSetKey = this.getTypeBProductSaleBlackListSortedSetKey();
        super.zadd(sortedSetKey, System.currentTimeMillis(), RedisUtil.toByteArray(productCode));
    }

    /**
     * 从商品销售黑名单当中移除商品
     */
    public void removeFromTypeBProductBlackList(String productCode) {
        String sortedSetKey = this.getTypeBProductSaleBlackListSortedSetKey();
        super.zrem(sortedSetKey, RedisUtil.toByteArray(productCode));
    }

    /**
     * 获取所有A类商品或者B类商品的商品编码集合
     *
     * @param vendorType 商家类型
     * @return 商品编码集合
     */
    public List<String> getTypeProductCodes(VendorTypeEnum vendorType) {
        String key = vendorType == VendorTypeEnum.TYPE_B ? this.getTypeBProductsSortedSetKey() : this.getTypeAProductsSortedSetKey();
        Set<byte[]> codeBytes = super.zRange(key, 0, -1);
        String[] codesArr = RedisUtil.bytesSetToStringArray(codeBytes);
        if (ArrayUtils.isEmpty(codesArr)) {
            return Lists.newArrayList();
        } else {
            return Lists.newArrayList(codesArr);
        }
    }

    /**
     * 查找商品对应的关联商品
     */
    public List<String> findRelevanceList(String productCode) {
        Set<byte[]> bytes = zRange(getTypeARelevanceSortedSetKey(productCode), 0, -1);
        return RedisUtil.bytesSetToStringList(bytes);
    }

    /**
     * 保存商品关联关系
     *
     * @param productCode 被关联的商品编码
     * @param relevanceIds 关联的商品ID
     */
    public void saveRelevance(String vendorId, String productCode, List<String> relevanceIds) {
        addRelevance2Vendor(vendorId, productCode);
        del(getTypeARelevanceSortedSetKey(productCode));
        int i = 0;
        for (String relevanceId : relevanceIds) {
            zadd(getTypeARelevanceSortedSetKey(productCode), i, relevanceId.getBytes());
            i++;
        }
    }

    /**
     * 获取商家关联商品集合
     */
    public List<String> listRelevanceProductCode(Long vendorId) {
        Set<byte[]> bytes = zRange(getTypeAVendorRelevanceSortedSetKey(String.valueOf(vendorId)), 0, -1);
        return RedisUtil.bytesSetToStringList(bytes);
    }

    /**
     * 添加关联商品ID到vendor
     */
    public void addRelevance2Vendor(String vendorId, String relevanceProductId) {
        zadd(getTypeAVendorRelevanceSortedSetKey(vendorId), new Date(), relevanceProductId);
    }

    /**
     * 获取B类商品销售黑名单的 sortedSet key
     */
    private String getTypeBProductSaleBlackListSortedSetKey() {
        return getKeyByParams("TypeB", "SaleBlackList");
    }

    /**
     * 获取A类商品SortedSet Key
     *
     * @return key
     */
    private String getTypeAProductsSortedSetKey() {
        return getKeyByParams("TypeA");
    }

    /**
     * 获取B类商品SortedSet Key(存商品编码)
     *
     * @return key
     */
    private String getTypeBProductsSortedSetKey() {
        return getKeyByParams("TypeB");
    }

    /**
     * 获取A类商品SortedSet Key(保存关联商品)
     *
     * @return key
     */
    private String getTypeARelevanceSortedSetKey(String productCode) {
        return getKeyByParams("TypeA", "Relevance", productCode);
    }

    private String getTypeAVendorRelevanceSortedSetKey(String vendorId) {
        return getKeyByParams("TypeA", "VendorRelevance", vendorId);
    }

    /**
     * 获取A类商品销售区域对应关系的String Key
     *
     * @param productCode 商品编码
     * @return String Key
     */
    private String getTypeAProductGeoIdsMappingStringKey(String productCode) {
        return getKeyByParams("typeA", productCode, "geoIds");
    }

}
