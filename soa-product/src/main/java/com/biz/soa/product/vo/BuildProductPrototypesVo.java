package com.biz.soa.product.vo;

import com.biz.gbck.vo.product.ProductTypeWithIndexVo;
import com.biz.soa.product.service.interfaces.ProductValidator;
import com.google.common.collect.Lists;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;

/**
 * 商品原型数据构造Vo
 *
 * @author david-liu
 * @date 2017年01月16日
 * @reviewer
 * @see
 */
public class BuildProductPrototypesVo implements Serializable {
    private static final long serialVersionUID = -3451032502430299777L;

    /**
     * 区域Id
     */
    private Long geoId;

    /**
     * A类商品商品编码
     */
    private List<String> typeAProductCodes = Lists.newArrayList();

    /**
     * B类商品商品编码
     */
    private List<String> typeBProductCodes = Lists.newArrayList();

    /**
     * 用户等级
     */
    private Integer userLevel;

    /**
     * 门店编码
     */
    private String depotCode;

    /**
     * 省仓门店编码
     */
    private String warehouseDepotCode;

    /**
     * 原始商品类型顺序集合
     */
    private List<ProductTypeWithIndexVo> indexVos;

    private ProductValidator typeAProductValidator;

    private ProductValidator typeBProductValidator;

    /**
     * 当前定位经度
     */
    private BigDecimal latitude;

    /**
     * 当前定位纬度
     */
    private BigDecimal longitude;

    public Long getGeoId() {
        return geoId;
    }

    public void setGeoId(Long geoId) {
        this.geoId = geoId;
    }

    public List<String> getTypeAProductCodes() {
        if (CollectionUtils.isEmpty(this.typeAProductCodes)) {
            typeAProductCodes = Lists.newArrayList();
        }
        return typeAProductCodes;
    }

    public void setTypeAProductCodes(List<String> typeAProductCodes) {
        this.typeAProductCodes = typeAProductCodes;
    }

    public List<String> getTypeBProductCodes() {
        if (CollectionUtils.isEmpty(this.typeBProductCodes)) {
            typeBProductCodes = Lists.newArrayList();
        }
        return typeBProductCodes;
    }

    public void setTypeBProductCodes(List<String> typeBProductCodes) {
        this.typeBProductCodes = typeBProductCodes;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public String getDepotCode() {
        return depotCode;
    }

    public void setDepotCode(String depotCode) {
        this.depotCode = depotCode;
    }

    public List<ProductTypeWithIndexVo> getIndexVos() {
        return indexVos;
    }

    public void setIndexVos(List<ProductTypeWithIndexVo> indexVos) {
        this.indexVos = indexVos;
    }

    public String getWarehouseDepotCode() {
        return warehouseDepotCode;
    }

    public void setWarehouseDepotCode(String warehouseDepotCode) {
        this.warehouseDepotCode = warehouseDepotCode;
    }

    public ProductValidator getTypeAProductValidator() {
        return typeAProductValidator;
    }

    public void setTypeAProductValidator(ProductValidator typeAProductValidator) {
        this.typeAProductValidator = typeAProductValidator;
    }

    public ProductValidator getTypeBProductValidator() {
        return typeBProductValidator;
    }

    public void setTypeBProductValidator(ProductValidator typeBProductValidator) {
        this.typeBProductValidator = typeBProductValidator;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }
}
