package com.biz.service.product.backend;

import com.biz.core.page.PageResult;
import com.biz.gbck.exceptions.product.*;
import com.biz.gbck.vo.product.backend.*;
import java.util.List;

/**
 * 商品 Service(供商家后台用) interface
 *
 * @author david-liu
 * @date 2016年12月19日
 * @reviewer
 * @see
 */
public interface VendorProductService {

    /**
     * 创建商品(商家端创建商品产生商品审核记录供平台端审核)
     *
     * @param vo 商品创建 Vo
     * @throws IllegalParameterException 参数不合法异常
     * @throws BrandNotFoundException 品牌不存在或者已删除异常
     * @throws CategoryNotFoundException 分类不存在或者已删除异常
     * @throws ProductAuditLogNotFoundException 商品审核记录不存在或者已删除异常
     * @throws ProductLockedException 商品已锁定异常
     * @throws ProductNotFoundException 商品不存在或者已删除异常
     */
    CreateProductAuditVo createProduct(CreateProductAuditVo vo)
            throws IllegalParameterException,
            BrandNotFoundException,
            CategoryNotFoundException,
            ProductAuditLogNotFoundException,
            ProductLockedException,
            ProductNotFoundException;

    /**
     * 商家更新商品(商家端更新商品产生的审核记录也要供平台端审核)
     *
     * @param vo 商品更新 Vo
     * @throws IllegalParameterException 参数不合法异常
     * @throws ProductAuditLogNotFoundException 商品审核记录不尊在或者已删除异常
     * @throws CategoryNotFoundException 分类不存在或者已删除异常
     * @throws BrandNotFoundException 品牌不存在或者已删除异常
     * @throws ProductLockedException 商品已锁定异常
     * @throws ProductNotFoundException 商品不存在或者已删除异常
     */
    void updateProduct(UpdateProductAuditVo vo)
            throws IllegalParameterException,
            ProductAuditLogNotFoundException,
            CategoryNotFoundException,
            BrandNotFoundException,
            ProductLockedException,
            ProductNotFoundException;

    /**
     * 商家删除商品
     *
     * @param id 商品 ID
     * @return true: 删除成功, false: 删除失败
     */
    Boolean deleteProduct(Long id);

    /**
     * 商家商品列表
     *
     * @param reqVo 请求 Vo
     * @return 商品列表
     * @throws IllegalParameterException 参数不合法异常
     */
    PageResult<VendorProductListItemVo> listVendorProducts(VendorProductListReqVo reqVo)
            throws IllegalParameterException;

    /**
     * 商家已删除商品列表
     *
     * @param reqVo 请求 Vo
     * @return 商家已删除商品列表
     * @throws IllegalParameterException 参数不合法异常
     */
    PageResult<VendorDeletedProductListItemVo> listVendorDeletedProducts(VendorProductListReqVo reqVo)
            throws IllegalParameterException;

    /**
     * 获取商家商品详情
     *
     * @param vendorId 商家 ID
     * @param productId 商品 ID
     * @return 商家商品详情
     * @throws IllegalParameterException 参数不合法异常
     * @throws ProductNotFoundException 商品未找到异常
     */
    VendorProductDetailVo getVendorProductDetail(Long vendorId, Long productId)
            throws IllegalParameterException, ProductNotFoundException;

    /**
     * 获取商家商品审核详情
     *
     * @param vendorId 商家 ID
     * @param productAuditId 商品审核 ID
     * @return 商品审核详情
     * @throws IllegalParameterException 参数不合法异常
     */
    VendorProductAuditDetailVo getVendorProductAuditDetail(Long vendorId, Long productAuditId)
            throws IllegalParameterException;

    /**
     * 商品上下架状态切换
     *
     * @return true: 上下架操作成功, false: 上下架操作失败
     */
    Boolean toggleProductSaleStatus(VendorToggleProductSaleReqVo reqVo);

    /**
     * 商品上架
     *
     * @throws ProductAuditNotFoundException
     * @throws ProductLockedException
     * @throws GeoProductNotFoundException
     */
    Boolean putAwayProduct(PutAwayVo putAwayVo) throws ProductAuditNotFoundException, ProductLockedException, GeoProductNotFoundException, ProductSaleStatusException;


    /**
     * 获取上架商品编辑信息
     */
    PutAwayVo getPutAway(PutAwayReqVo putAwayReqVo);

    /**
     * 商品下架
     *
     * @throws GeoProductNotFoundException
     */
    Boolean soldOutProduct(SoldOutProductVo soldOutProductVo) throws GeoProductNotFoundException;


    /**
     * 商家创建商品配置(款型)
     *
     * @param reqVo 请求 Vo
     * @return vo 带 ID
     */
    VendorCreateProductCascadeReqVo createProductCascade(VendorCreateProductCascadeReqVo reqVo)
            throws ProductNotFoundException,
            ProductExtendNotFoundException,
            CategoryNotFoundException, IllegalParameterException, ProductCascadeNotFoundException;

    /**
     * 更新商品配置(款型)
     *
     * @param reqVo 请求 Vo
     * @return true: 更新成功, false: 更行失败
     */
    VendorUpdateProductCascadeReqVo updateProductCascade(VendorUpdateProductCascadeReqVo reqVo)
            throws ProductCascadeNotFoundException,
            CategoryNotFoundException,
            ExtendPropertyNotFoundException, IllegalParameterException, ProductExtendNotFoundException;

    /**
     * 删除商品配置(款型)
     *
     * @param reqVo 请求 Vo
     * @return true: 删除成功, false: 删除失败
     */
    Boolean deleteProductCascade(VendorDeleteProductCascadeReqVo reqVo) throws ProductCascadeNotFoundException;

    /**
     * 获取商品配置信息
     */
    VendorProductCascadeDetailVo getVendorProductCascade(SearchVendorCascadeVo reqVo) throws ProductNotFoundException, ProductCascadeException;


    /**
     * 商家端后台获取关联商品信息
     *
     * @param reqVo 传入vendorId 和 productCode
     * @return 该商品的关联商品展示信息
     * @throws ProductNotFoundException
     */
    RelevanceProductVo getRelevanceProduct(SearchRelevanceProductVo reqVo) throws ProductNotFoundException;

    /**
     * 商家创建或更新关联商品信息
     */
    RelevanceProductListVo createOrUpdateRelevanceProductList(RelevanceProductListVo reqVo) throws ProductNotFoundException, IllegalParameterException;

    /**
     * 获取vendor 关联商品列表
     */
    PageResult<ListRelevanceProductVo> listVendorRelevanceProduct(ListVendorRelevanceProductReqVo reqVo) throws IllegalParameterException;

    /**
     * 搜索获得商品列表信息
     */
    PageResult<ProductBriefVo> getVendorProductList(BackendProductListReqVo searchPageVo) throws IllegalParameterException;

    /**
     * 根据商品编码集合 获取商品图片名称信息
     */
    List<ProductLogoVo> getProductLogoList(ProductLogoReqVo productLogoReqVo);

}
