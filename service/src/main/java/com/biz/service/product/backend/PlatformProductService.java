package com.biz.service.product.backend;

import com.biz.core.page.PageResult;
import com.biz.gbck.enums.product.SaleStatusEnum;
import com.biz.gbck.exceptions.product.*;
import com.biz.gbck.vo.product.backend.*;
import java.util.List;

/**
 * 商品 Service (平台后台用)
 *
 * @author david-liu
 * @date 2016年12月25日
 * @reviewer
 * @see
 */
public interface PlatformProductService {

    /**
     * 返回平台端待审核商品记录列表
     *
     * @param reqVo 请求 Vo
     * @return 待审核商品记录列表
     */
    PageResult<PlatformProductAuditListItemVo> listPlatformAuditProducts(PlatformProductAuditListReqVo reqVo) throws IllegalParameterException;

    /**
     * 开关商品的锁定状态
     *
     * @param vendorId 商家 ID
     * @param productId 商品 ID
     * @param locked 是否锁定
     */
    Boolean toggleProductLock(Long vendorId, Long productId, Boolean locked) throws IllegalParameterException, ProductNotFoundException;

    /**
     * 获取商户商品审核记录详情
     *
     * @param productAuditId 商品审核记录 ID
     * @return 审核记录详情
     */
    PlatformProductAuditDetailVo getVendorProductAudit(Long productAuditId) throws IllegalParameterException, ProductAuditNotFoundException;

    /**
     * 平台端审核商家商品
     *
     * @param reqVo 商品审核请求 Vo
     * @return true: 操作成功, false: 操作失败
     */
    Boolean auditVendorProduct(PlatformAuditReqVo reqVo) throws IllegalParameterException, ProductAuditNotFoundException, ProductLockedException, ProductAuditLogNotFoundException, GeoProductNotFoundException, ProductSaleStatusException;

    /**
     * 根据商品ID集合查询商品
     */
    List<IdNameVo> findProductByIds(List<Long> ids);

    /**
     * 根据searchKey 和 searchValue,模糊查询对应商品
     *
     * @param searchPageVo name 或 sku
     */
    PageResult<IdNameVo> findProductByKeyValue(SearchPageVo searchPageVo);

    /**
     * 根据商品编码查找商品id
     *
     * @return product id
     */
    Long getProductIdByProductCode(String productCode);


    /**
     * B类商品列表
     */
    PageResult<TypeBProductListItemVo> listTypeBProductItemVo(TypeProductListReqVo reqVo);

    /**
     * 根据商品名称模糊查询商品ID集合
     */
    List<Long> findProductIdByName(String name);


    /**
     * 根据productCode查询商品详情
     */
    ProductDetailVo getProductDetailVo(String productCode);

    BootstrapTablePageResult<TypeBBlackProductItemVo> listTypeBBlackList();

    void appendTypeBBlackList(String productCode);

    void removeFromTypeBBlackList(String productCode);

    void setTypeBProductSaleStauts(Long productId, SaleStatusEnum saleStatus);

    PlatformTypeBProductDetailVo viewTypeBProductDetailVo(Long productId);

    void updateTypeBProduct(UpdateTypeBProductVo updateTypeBProductVo);

    void updateGroupProductPrice(ProductGroupOnSaleVo reqVo);

    GroupProductRespVo getGroupProductEditVo(Long productId);
}
