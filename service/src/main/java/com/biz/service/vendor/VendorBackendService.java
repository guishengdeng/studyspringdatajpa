package com.biz.service.vendor;

import com.biz.core.page.PageResult;
import com.biz.gbck.vo.vendor.*;
import java.util.List;

/**
 * 商铺服务(提供给后台应用)
 *
 * @author yanweijin
 * @date 2016/12/19
 */
public interface VendorBackendService {


    /**
     * 根据 vendorVo 数据创建一个商铺,返回数据中回填创建好的商铺的 id
     */
    VendorVo createVendor(VendorVo req);

    /**
     * 根据传入的请求数据更新商铺信息
     */
    boolean updateVendor(VendorVo req);

    /**
     * 根据 id 删除一个商品(软删除)
     */
    boolean deleteVendor(Long id);

    /**
     * 根据 id 获取商铺详情
     */
    VendorDetailVo getDetail(Long id);

    VendorVo getVendor(Long id);

    /**
     * 通过ID集合查询店铺
     */
    List<VendorVo> findVendorByIds(List<Long> ids);

    /**
     * 批量审批
     */
    boolean updateVendorReviewed(List<VendorVo> list);

    /**
     * 查询店铺装饰
     */
    VendorDecorationVo findVendorDecorationByVendorId(Long id);

    /**
     * 保存店铺装饰
     */
    boolean saveVendorDecoration(VendorDecorationVo vo);

    /**
     * 根据商铺类型，店铺名进行查询
     */
    PageResult<VendorVo> findVendorByVendorType(VendorQueryRequestVo vo);

    /**
     * 根据vendorId查找店铺信息和装饰信息
     */
    VendorAndDecorationQueryVo findVendorAndDecorationVo(Long vendorId, Long userId);

    /**
     * 将店铺数据从数据库同步到redis
     */
    void syncVendorData2Redis();

    List<Long> findVendorIdsByUserId(Long userId);

    /**
     * 根据vendorID查询店铺和装饰信息
     */
    VendorVo findVendorAndDecoration(Long vendorId);

    /**
     * 根据vendorCode查询店铺和装饰信息
     */
    VendorVo findVendorAndDecoration(String vendorCode);

    List<VendorVo> findAll();

    VendorVo findByUserId(Long userId);

    /**
     * 校验商户是否存在
     */
    String validVendorIsExist(Long userId);

    /**
     * 检查商户ID是否存在
     */
    boolean checkVendorId(Long id);

    /**
     * 查询1919自营装饰
     */
    VendorDecorationVo findCommonDecoration();

    /**
     * 平台后台根据店铺ID查询店铺和装饰信息
     */
    VendorVo findBackendVendorInfo(Long vendorId);

    /**
     * 商家后台查询装饰信息根据店铺ID
     */
    VendorDecorationVo findVendorBackendDecorationInfo(Long id);
}
