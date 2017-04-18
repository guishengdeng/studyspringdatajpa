package com.biz.gbck.dao.redis.repository.stock;

import com.biz.core.util.DateUtil;
import com.biz.gbck.common.Constant;
import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.stock.DepotStockRo;
import com.biz.gbck.enums.oss.OssType;
import com.biz.redis.util.RedisUtil;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.codelogger.utils.ValueUtils;
import org.springframework.stereotype.Repository;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @author lei
 * @date 2016年12月5日
 */
@Repository
public class DepotStockRedisDao extends CrudRedisDao<DepotStockRo, String> {

    public void save(DepotStockRo ro) {
        ro.setUpdateTimestamp(DateUtil.now());
        super.save(ro);
    }

    public void save(List<DepotStockRo> ros) {
        if (CollectionUtils.isEmpty(ros)) {
            return;
        }
        super.save(ros);
    }

    /**
     * 根据全量库存的不同类型，设置自增的当前的版本号
     */
    public void saveLatestDepotStockVersion(OssType type, Long latestVersion) {
        if (type == OssType.STOCK || type == OssType.WAREHOUSE_STOCK) {
            super.set(versionMappingKey(type), RedisUtil.toByteArray(latestVersion));
        }

    }

    /**
     * 根据全量库存的不同类型，获取当前的版本号
     */
    public Long getLatestDepotStockVersion(OssType type) {
        return RedisUtil.byteArrayToLong(super.get(versionMappingKey(type)));
    }


    public void delete(DepotStockRo ro) {
        super.delete(ro);
    }

    public Integer sumQuantityByKeys(List<String> keys, Long latestDepotStockVersion) {
        Integer result = 0;
        if (latestDepotStockVersion != null) {
            List<DepotStockRo> depotStockRos = this.findByKeys(keys);
            if (CollectionUtils.isNotEmpty(depotStockRos)) {
                for (DepotStockRo depotStockRo : depotStockRos) {
                    if (depotStockRo != null && (depotStockRo.getVersion() == null || Objects.equals(depotStockRo
                            .getVersion(), latestDepotStockVersion))) {
                        result += ValueUtils.getValue(depotStockRo.getQuantity()) > 0 ? depotStockRo.getQuantity() : 0;
                    }
                }
            }
        } else { //库存版本不存在,统计全省库存(可能不准确)
            List<Object> quantities = super.pipeHget(keys, Constant.RO_QUANTITY_FIELD);
            if (CollectionUtils.isNotEmpty(quantities)) {
                for (Object quantity : quantities) {
                    if (NumberUtils.isNumber(String.valueOf(quantity))) {
                        result += ValueUtils.getValue((Integer) quantity);
                    }
                }
            }
        }

        return result;
    }

    /**
     * 根据门店编码&商品编号查询
     */
    public DepotStockRo findByDepotCodeAndProductCode(String depotCode, String productCode) {
        if (StringUtils.isBlank(depotCode) || StringUtils.isBlank(productCode)) {
            return null;
        }
        DepotStockRo depotStockRo = findByKey(getHashKey(getId(depotCode, productCode)));
        if (depotStockRo == null) {
            return null;
        } else {
            Long version = depotStockRo.getVersion();
            OssType type = depotStockRo.getType();
            if (version != null) {
                Long latestDepotStockVersion = this.getLatestDepotStockVersion(type);
                if (!Objects.equals(latestDepotStockVersion, version)) {
                    return null;
                }
            }
            return depotStockRo;
        }

    }

    /**
     * 根据门店编码&商品编号查询数量
     */
    public Integer getQuantityByDepotCodeAndProductCode(String depotCode, String productCode) {
        if (StringUtils.isBlank(depotCode) || StringUtils.isBlank(productCode)) {
            return 0;
        }

        DepotStockRo depotStockRo = this.findByDepotCodeAndProductCode(depotCode, productCode);
        return depotStockRo != null ? depotStockRo.getQuantity() : 0;
    }

    /**
     * 根据门店编码&商品编码批量查询
     */
    public List<DepotStockRo> getByDepotCodeAndProductCodes(String depotCode, Set<String> productCodes) {
        if (StringUtils.isBlank(depotCode) || CollectionUtils.isEmpty(productCodes)) {
            return newArrayList();
        }
        List<String> keys = newArrayList();
        for (String productCode : productCodes) {
            keys.add(getHashKey(getId(depotCode, productCode)));
        }
        return findByKeys(keys);
    }

    /**
     * 更新库存
     */
    public void updateQuantity(String depotCode, String productCode, int quantity) {
        Long stock = hincrBy(getHashKey(getId(depotCode, productCode)), Constant.RO_QUANTITY_FIELD.getBytes(),
                quantity);
        if (stock < 0) {
            hset(getHashKey(getId(depotCode, productCode)), Constant.RO_QUANTITY_FIELD, RedisUtil.toByteArray(0));
        }
    }

    public static String getId(String depotCode, String productCode) {
        return String.format("%s%s%s", depotCode, Constant.SEPARATOR, productCode);
    }

    public static String getHashKey(String depotCode, String productCode) {
        return String.format("%s%s%s%s%s", "stock:depot", Constant.SEPARATOR, depotCode, Constant.SEPARATOR,
                productCode);
    }

    public static String versionMappingKey(OssType type) {
        return String.format("%s%s%s%s%s", "stock:depot", Constant.SEPARATOR, "version", Constant.SEPARATOR, type);
    }
}
