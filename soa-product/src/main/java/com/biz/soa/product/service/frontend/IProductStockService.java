package com.biz.soa.product.service.frontend;

import com.biz.gbck.vo.product.frontend.ProductStockReqProductVo;
import com.biz.gbck.vo.product.frontend.TypeBProductStockVo;
import java.io.Serializable;
import java.util.List;

/**
 * 商品库存接口
 *
 * @author david-liu
 * @date 2017年01月12日
 * @reviewer
 * @see
 */
public interface IProductStockService extends Serializable {

    /**
     * 获取 A 类商品库存
     *
     * @param typeAProductCodes A 类商品编码集合
     * @return 商品库存集合(商品库存顺序和传入 List 当中商品编码顺序一致)
     */
    List<Integer> getTypeAProductStock(List<String> typeAProductCodes);

    /**
     * 获取A类商品库存
     *
     * @param typeAProductCode A类商品编码
     * @return 商品库存
     */
    Integer getTypeAProductStock(String typeAProductCode);

    /**
     * 获取 B 类商品库存
     *
     * @param reqVos B类商品库存请求Vo list
     * @param depotCode 门店 ID
     * @param warehouseDepotCode 省仓门店编码
     * @return B 类商品商品库存 Vo 集合(商品库存顺序和传入 List 当中商品编码顺序一致)
     */
    List<TypeBProductStockVo> getTypeBProductStock(List<ProductStockReqProductVo> reqVos, String depotCode, String warehouseDepotCode);

    /**
     * 获取B类商品库存
     *
     * @param reqVo B类商品库存请求Vo
     * @param depotCode 门店编码
     * @param warehouseDepotCode 省仓门店编码
     * @return B类商品库存Vo
     */
    TypeBProductStockVo getTypeBProductStock(ProductStockReqProductVo reqVo, String depotCode, String warehouseDepotCode);

}
