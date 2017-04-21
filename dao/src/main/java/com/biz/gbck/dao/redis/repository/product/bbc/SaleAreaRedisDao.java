package com.biz.gbck.dao.redis.repository.product.bbc;

import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.product.meta.SaleAreaRo;
import com.biz.redis.util.RedisUtil;
import java.io.Serializable;
import org.springframework.stereotype.Repository;

/**
 * 销售区域 RedisDao
 *
 * @author david-liu
 * @date 2017年01月04日
 * @reviewer
 * @see
 */
@Repository
public class SaleAreaRedisDao extends CrudRedisDao<SaleAreaRo, String> implements Serializable {
    public static final String TYPE_A_PRICE_AREA_NO = "PA0CN";
    private static final long serialVersionUID = 7233687876668184174L;

    /**
     * 通过 GeoId 获取 销售区域编码
     *
     * @param geoId geoId
     * @return 销售区域编码
     */
    public String getTypeASaleAreaCodeByGeoId(Long geoId) {
        return RedisUtil.byteArrayToStr(super.get(this.getTypeAGeoIdSaleAreaMappingKey(geoId)));
    }

    /**
     * 设置A类商品GeoId和销售区域编码对应关系
     *
     * @param geoId geoId
     */
    public void setTypeASaleAreaCodeAndGeoIdMapping(Long geoId) {
        super.set(this.getTypeAGeoIdSaleAreaMappingKey(geoId), RedisUtil.toByteArray(TYPE_A_PRICE_AREA_NO));
    }

    /**
     * 通过 geoId 获取A类商品 geoId 和 SaleAreaCode 的 Mapping Redis String key
     *
     * @param geoId geoId
     * @return Mapping Key
     */
    private String getTypeAGeoIdSaleAreaMappingKey(Long geoId) {
        return getKeyByParams("typeA", "geoId", geoId);
    }
}
