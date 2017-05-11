package com.biz.gbck.dao.redis.repository.product;

import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.product.master.ProductRO;
import com.biz.gbck.vo.product.frontend.ProductIncrSaleVolumeItemVo;
import com.google.common.base.Preconditions;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

/**
 * 商品RedisDao
 * Created by david-liu on 2017/04/21 12:04.
 */
@Repository
public class ProductRedisDao extends CrudRedisDao<ProductRO, String> {

    public void incrSalesVolume(List<ProductIncrSaleVolumeItemVo> vos) {
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(vos), "已售商品集合不能为空");
        Map<String, Integer> keyToQuantity =
                vos.stream().collect(
                        Collectors.toMap(ProductIncrSaleVolumeItemVo::getProductCode, ProductIncrSaleVolumeItemVo::getSoldQuantity)
                );
        super.pipeHincrBy(keyToQuantity, "salesVolume");
    }

}
