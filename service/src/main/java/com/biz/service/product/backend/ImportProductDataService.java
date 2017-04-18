package com.biz.service.product.backend;

/**
 * @author zhangcheng
 * @date 2017/2/10
 * @reviewer
 * @see
 */
public interface ImportProductDataService {

    /**
     * 导入商品顶层基础分类
     * 备注：分类数据是最先进行导入的
     */
    void importCategory();

    /**
     * 导入品牌
     * 备注：在导入品牌的之前，首先需要创建分类，目前的最顶层分类为：
     *      洋酒、白酒、啤酒、葡萄酒、黄酒、酒具、果酒、饮料、推荐酒品和其他
     */
    void importBrand();

    /**
     * 转换导入的商品
     * 备注：在导入商品前，建议先将原始数据导入到一个临时表中，后面的操作会用到这个临时表
     */
    void importProduct();

    /**
     * 导入商品统计数据
     */
    void importProductStatistic();

    /**
     * 关联商品统计数据
     */
    void relevanceProductStatistic();

    /**
     * 将B类商品导入到临时表中
     */
    void importTemporaryProduct();

    /**
     * 将导入的商品中的所包含的扩展属性写入到商品扩展属性表中
     */
    void importProductExtendProperty();

    /**
     * 将正式商品数据表中的有关图片的字段导出
     */
    void exportImages();

    /**
     * 导入商家端商品到临时表中
     */
    void importVendorTemporaryProduct();

    /**
     * 导入商家端品牌
     */
    void importVendorBrands();

    /**
     * 将商户端商品图片新的名称导入到临时表中
     */
    void importVendorImages();

    /**
     * 将A类商品临时表中的商品数据导入到商品正式表中
     */
    void importVendorProducts();

    /**
     * 补充A类商品的审核情况
     */
    void replenishVendorProductAuditCondition();

    /**
     * 初始化A类区域商品
     */
    void mappingProductGeo();

    /**
     * 关联商品与商品扩展属性值的关系
     */
    void mappingProductExtendProperty();

    /**
     * 创建不同分类下的不同商品扩展属性名
     */
    void importProductExtends();

    /**
     * 导入A类商户中虚拟店铺的品牌与店铺的映射关系
     */
    void importVirtualVendorWithBrandRelationship();

    /**
     * 导入B类组合商品到临时表中
     */
    void importTemporaryGroupProduct();

    /**
     * 导入B类组合商品到正式商品表中
     */
    void importGroupProduct();

    /**
     * 将临时表中的组合商品数据导入到正式组合商品表中
     */
    void importGroupProductToGroupTable();

    /**
     * 将正式商品表与正式组合商品表进行关联
     */
    void relevanceProductToGroupProduct();

    /**
     * 导入组合商品下的明细商品到组合商品明细表中
     */
    void importGroupProductItem();

    /**
     * 关联组合商品和其包含的明细商品
     */
    void relevanceGroupProductAndItem();

    /**
     * 比较品牌的数量，并将原始数据中没有的品牌进行保存
     */
    void compareFile();

    /**
     * fix product of group product and product type
     */
    void fixProduct();
}
