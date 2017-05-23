package com.biz.soa.feign.hystrix.org;

import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.dao.mysql.po.org.ShopDetailPo;
import com.biz.gbck.dao.mysql.po.org.ShopPo;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.vo.org.*;
import com.biz.gbck.vo.spring.PageVO;
import com.biz.soa.feign.client.org.ShopFeignClient;
import com.biz.support.web.handler.JSONResult;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by david-liu on 2017/05/12 12:16.
 */
@Component
public class ShopFeignClientHystrix implements ShopFeignClient {

    @Override
    public JSONResult updateDetails(@RequestBody ShopUpdateDetailReqVo shopUpdateDetailReqVo) throws CommonException {
        return null;
    }

    @Override
    public JSONResult getLatestDetail(@RequestBody ShopDetailOrQualificationGetReqVo shopDetailOrQualificationGetReqVo) throws CommonException {
        return null;
    }

    @Override
    public JSONResult updateQualification(@RequestBody ShopUpdateQualificationReqVo updateQualificationReqVo) throws CommonException {
        return null;
    }

    @Override
    public JSONResult getLatestQualification(@RequestBody ShopDetailOrQualificationGetReqVo shopDetailOrQualificationGetReqVo) throws CommonException {
        return null;
    }

    @Override
    public JSONResult listShopTypes() {
        return null;
    }

    @Override
    public JSONResult updateDeliveryAddress(@RequestBody ShopChangeDeliveryAddressReqVo changeDeliveryAddressReqVo) {
        return null;
    }

    @Override
    public JSONResult updateDeliveryName(@RequestBody UserChangeDeliveryNameReqVo changeDeliveryAddressReqVo) throws CommonException {
        return null;
    }

    @Override
    public JSONResult getUpdateAddressStatus(@RequestBody ShopDetailOrQualificationGetReqVo shopDetailOrQualificationGetReqVo) throws CommonException {
        return null;
    }

    @Override
    public PageVO<ShopDetailResVo> findShopAuditDataOfWaitForAudit(@RequestBody ShopSearchVo reqVo) {
        return null;
    }

    @Override
    public ShopDetailResVo findShopAuditDataOfWaitForAuditByShopId(@RequestBody Long shopId) {
        return null;
    }

    @Override
    public Boolean isBusinessLicenceIdExist(@RequestParam("businessLicenceId") String businessLicenceId, @RequestParam("shopId") Long shopId) {
        return null;
    }

    @Override
    public Boolean findShopByBusinessLicenceId(@RequestParam("businessLicenceId") String businessLicenceId) {
        return null;
    }

    @Override
    public void auditShop(@RequestBody ShopAuditReqVo reqVo) throws CommonException {

    }

    @Override
    public Boolean updateShopStatus(@RequestParam("id") Long id, @RequestParam("shopStatus") CommonStatusEnum shopStatus) {
        return null;
    }

    @Override
    public ShopPo findShopRoById(@RequestParam("id") Long id) {
        return null;
    }

    @Override
    public void destroyShopAndItUsers(@RequestParam("shopId") Long shopId, @RequestParam("handler") String handler) throws CommonException {

    }

    @Override
    public Integer enableShopByIds(@RequestParam("ids") String[] ids) {
        return null;
    }

    @Override
    public Integer disableShopByIds(@RequestParam("ids") String[] ids) {
        return null;
    }

    @Override
    public List<ShopPo> findShopPoByMobileAndHaveNotShopId(@RequestParam("mobile") String mobile, @RequestParam("shopId") Long shopId) {
        return null;
    }

    @Override
    public String updateBlackList(@RequestParam("blackList") String blackList) {
        return null;
    }

    @Override
    public List<ShopPo> getShopPoBackList() {
        return null;
    }

    @Override
    public JSONResult deleteBlackList(@RequestBody List<String> shopIds) {
        return null;
    }

    @Override
    public Boolean saveUpdateDetail(@RequestBody ShopAuditReqVo shopAuditReqVo) {
        return null;
    }
}
