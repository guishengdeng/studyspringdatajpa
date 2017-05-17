package com.biz.soa.feign.client.org;

import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.dao.mysql.po.org.ShopDetailPo;
import com.biz.gbck.dao.mysql.po.org.ShopPo;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.vo.org.ShopAuditDataMap;
import com.biz.gbck.vo.org.ShopAuditReqVo;
import com.biz.gbck.vo.org.ShopChangeDeliveryAddressReqVo;
import com.biz.gbck.vo.org.ShopDetailOrQualificationGetReqVo;
import com.biz.gbck.vo.org.ShopSearchVo;
import com.biz.gbck.vo.org.ShopUpdateDetailReqVo;
import com.biz.gbck.vo.org.ShopUpdateQualificationReqVo;
import com.biz.gbck.vo.org.UserChangeDeliveryNameReqVo;
import com.biz.soa.feign.hystrix.org.ShopFeignClientHystrix;
import com.biz.support.web.handler.JSONResult;
import org.springframework.cloud.netflix.feign.FeignClient;
/*import org.springframework.data.domain.Page;*/
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;


/**
 * @author: liubin
 * @date 5/10/17 15:00
 */
@FeignClient(name = "soa-org", fallback = ShopFeignClientHystrix.class)
public interface ShopFeignClient {

    /**
     * 修改商户详细资料
     */
    @RequestMapping(value = "soa/shop/updateDetail", method = RequestMethod.POST)
    JSONResult updateDetails(@RequestBody ShopUpdateDetailReqVo shopUpdateDetailReqVo)
            throws CommonException;

    /**
     * 获取最后一次提交的详细资料
     */
    @RequestMapping(value = "soa/shop/latestDetail", method = RequestMethod.POST)
    JSONResult getLatestDetail(@RequestBody ShopDetailOrQualificationGetReqVo shopDetailOrQualificationGetReqVo)
            throws CommonException;


    /**
     * 修改商户质资
     */
    @RequestMapping(value = "soa/shop/updateQualification", method = RequestMethod.POST)
    JSONResult updateQualification(@RequestBody ShopUpdateQualificationReqVo updateQualificationReqVo)
            throws CommonException;

    /**
     * 返回最后一次提交的商户质资
     */
    @RequestMapping(value = "soa/shop/latestQualification", method = RequestMethod.POST)
    JSONResult getLatestQualification(@RequestBody ShopDetailOrQualificationGetReqVo shopDetailOrQualificationGetReqVo)
            throws CommonException;

    /**
     * 返回所有正常的商户类型
     */
    @RequestMapping(value = "soa/shop/types", method = RequestMethod.POST)
    JSONResult listShopTypes();

    /**
     * 修改商户收货地址
     */
    @RequestMapping(value = "soa/shop/updateDeliveryAddress", method = RequestMethod.POST)
    JSONResult updateDeliveryAddress(@RequestBody ShopChangeDeliveryAddressReqVo changeDeliveryAddressReqVo);

    /**
     * 修改商户收货人姓名
     */
    @RequestMapping(value = "soa/shop/changeDeliveryName", method = RequestMethod.POST)
    JSONResult updateDeliveryName(@RequestBody UserChangeDeliveryNameReqVo changeDeliveryAddressReqVo)
            throws CommonException;


    @RequestMapping(value = "soa/shop/getUpdateAddressStatus", method = RequestMethod.POST)
    JSONResult getUpdateAddressStatus(@RequestBody ShopDetailOrQualificationGetReqVo shopDetailOrQualificationGetReqVo)
            throws CommonException;


    @RequestMapping(value = "soa/shop/findShopAuditDataOfWaitForAudit", method = RequestMethod.POST)
    Page<ShopDetailPo> findShopAuditDataOfWaitForAudit(@RequestBody ShopSearchVo reqVo);

    /**
     * 获取单个商户待审核信息
     */
    @RequestMapping(value = "soa/shop/findShopAuditDataOfWaitForAuditByShopId", method = RequestMethod.POST)
    ShopAuditDataMap findShopAuditDataOfWaitForAuditByShopId(@RequestBody Long shopId);


    /**
     * 判断businessLicenceId在数据库中是否已经存在
     */
    @RequestMapping(value = "soa/shop/isBusinessLicenceIdExist", method = RequestMethod.POST)
    Boolean isBusinessLicenceIdExist(@RequestParam("businessLicenceId") String businessLicenceId,
                                            @RequestParam("shopId") Long shopId);


    /**
     * 商户统一审核接口
     */
    @RequestMapping(value = "soa/shop/auditShop", method = RequestMethod.POST)
    void auditShop(@RequestBody ShopAuditReqVo reqVo) throws CommonException;


    @RequestMapping(value = "soa/shop/updateShopStatus", method = RequestMethod.POST)
    Boolean updateShopStatus(@RequestParam("id") Long id, @RequestParam("shopStatus")CommonStatusEnum shopStatus);


    /**
     * 物理删除，慎用
     */
    @RequestMapping(value = "soa/shop/destroyShopAndItUsers", method = RequestMethod.POST)
    void destroyShopAndItUsers(@RequestParam("shopId") Long shopId, @RequestParam("handler") String handler)
            throws CommonException;


    @RequestMapping(value = "soa/shop/enableShopByIds", method = RequestMethod.POST)
    Integer enableShopByIds(@RequestParam("ids") String[] ids);

    @RequestMapping(value = "soa/shop/disableShopByIds", method = RequestMethod.POST)
    Integer disableShopByIds(@RequestParam("ids") String[] ids);

    @RequestMapping(value = "soa/shop/findShopPoByMobileAndHaveNotShopId", method = RequestMethod.POST)
    List<ShopPo> findShopPoByMobileAndHaveNotShopId(@RequestParam("mobile") String mobile, @RequestParam("shopId") Long shopId);


    @RequestMapping(value = "soa/shop/updateBlackList", method = RequestMethod.POST)
    String updateBlackList(@RequestParam("blackList") String blackList);

    @RequestMapping(value = "soa/shop/getShopPoBackList", method = RequestMethod.POST)
    List<ShopPo> getShopPoBackList();

    @RequestMapping(value = "soa/shop/deleteBlackList", method = RequestMethod.POST)
    JSONResult deleteBlackList(@RequestBody List<String> shopIds);




}