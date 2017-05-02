package com.biz.service.org.interfaces;

import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.dao.mysql.po.org.ShopTypePo;
import com.biz.gbck.dao.redis.ro.org.ShopTypeRo;
import com.biz.gbck.enums.user.ShopTypeStatus;
import com.biz.vo.org.ShopTypeCreateReqVo;
import com.biz.vo.org.ShopTypeUpdateReqVo;
import java.util.List;


public interface ShopTypeService {

    /**
     * 查找所有店铺类型
     */
    List<ShopTypeRo> findAllShopTypeRo();

    /**
     * 按状态查找所有店铺类型
     */
    List<ShopTypeRo> findAllShopTypeRo(ShopTypeStatus shopTypeStatus);

    List<ShopTypeRo> sortShopTypeRos(List<ShopTypeRo> shopTypeRos);

    /**
     * 获取所有的商铺类型Po
     */
    List<ShopTypePo> getAllShopTypePo(ShopTypeStatus shopTypeStatus);

    /**
     * 查找商户类型
     */
    ShopTypeRo findShopTypeRo(Long shopTypeId);

    ShopTypePo findOne(Long id);

    /**
     * 创建商户类型
     */
    ShopTypeRo saveShopType(ShopTypeCreateReqVo shopTypeCreateReqVo);

    /**
     * 更新商户类型
     */
    ShopTypeRo updateShopType(ShopTypeUpdateReqVo shopTypeUpdateReqVo)
            throws CommonException;

    /**
     * 删除商户类型
     */
    Boolean disableShopType(Long shopTypeId);

    /**
     * 同步所有ShopType到redis
     */
    void syncAllShopTypeFromMysqlToRedis(Integer pageSize);

    /**
     * 同步ShopTypePo到redis数据库
     */
    ShopTypeRo syncShopTypePoToRedis(ShopTypePo shopTypePo);

    List<ShopTypePo> findAllShopTypes();
}
