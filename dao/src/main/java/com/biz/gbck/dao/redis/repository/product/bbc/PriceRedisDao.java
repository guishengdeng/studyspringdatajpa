package com.biz.gbck.dao.redis.repository.product.bbc;

import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.product.PriceRo;
import com.google.common.collect.Lists;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.stereotype.Repository;


/**
 * @author david-liu
 * @date 2017年01月04日
 * @reviewer
 * @see
 */
@Repository
public class PriceRedisDao extends CrudRedisDao<PriceRo, String> implements Serializable {

    private static final long serialVersionUID = -2075462272866208911L;

    public PriceRo getTypeAPrice(String productCode) {
        return this.findOne(this.getPriceRoId(productCode, SaleAreaRedisDao.TYPE_A_PRICE_AREA_NO));
    }

    public List<PriceRo> getTypeAPrices(List<String> productCodes) {
        List<String> ids = Lists.newArrayList();
        for (String productCode : productCodes) {
            ids.add(this.getPriceRoId(productCode, SaleAreaRedisDao.TYPE_A_PRICE_AREA_NO));
        }

        return this.findByIdsWithNull(ids);
    }

    public PriceRo getTypeBPrice(String productCode, String areaNo) {
        return this.findOne(this.getPriceRoId(productCode, areaNo));
    }

    /**
     * 通过价格 Id 集合获取价格
     * (价格 ID 为: 商品编码 + 区域编码)
     *
     * @param priceIds 价格 ID 集合
     * @return 价格 Ro 集合
     */
    public List<PriceRo> findByIds(List<String> priceIds) {
        return super.findByIdsWithNull(priceIds);
    }

    /**
     * 获取B类商品的Ro Id
     *
     * @param productCode 商品编码
     * @param areaNo 区域编码
     * @return RO ID
     */
    public String getPriceRoId(String productCode, String areaNo) {
        return String.format("%s%s", productCode, areaNo);
    }

    /**
     * 根据商品中台编码和区域编码获取到价格Ro
     *
     * @param productCode 商品编码
     * @param areaNo 区域编码
     * @return PriceRo 价格Ro
     */
    public PriceRo findPriceRo(String productCode, String areaNo) {
        return findOne(String.format("%s%s", productCode, areaNo));
    }

    /**
     * 批量设置组合商品价格信息
     *
     * @param priceKey 价格Ro Key
     * @param price 设置的价格
     */
    public void updateGroupProductPrice(String productCode, List<String> priceKey, Integer price, List<String> areaCode) {
        List<PriceRo> list = Lists.newArrayList();
        for (int i = 0; i < priceKey.size(); i++) {
            PriceRo priceRo = new PriceRo();
            priceRo.setProductCode(productCode);
            priceRo.setUpdateTimestamp(new Timestamp(System.currentTimeMillis()));
            priceRo.setId(priceKey.get(i));
            priceRo.setAreaCode(areaCode.get(i));
            priceRo.setMinPrice(price);
            priceRo.setCostPrice(price);
            priceRo.setFinalPrice(price);
            priceRo.setMarketPrice(price);
            priceRo.setPrice1(price);
            priceRo.setPrice2(price);
            priceRo.setPrice3(price);
            priceRo.setPrice4(price);
            priceRo.setPrice5(price);
            priceRo.setPrice6(price);
            priceRo.setPrice7(price);
            priceRo.setPrice8(price);
            priceRo.setPrice9(price);
            priceRo.setPrice10(price);
            priceRo.setPrice11(price);
            priceRo.setPrice12(price);
            priceRo.setPrice13(price);
            priceRo.setPrice14(price);
            priceRo.setPrice15(price);
            priceRo.setPrice16(price);
            priceRo.setPrice17(price);
            priceRo.setPrice18(price);
            priceRo.setPrice19(price);
            priceRo.setPrice20(price);
            list.add(priceRo);
        }
        save(list);
    }
}
