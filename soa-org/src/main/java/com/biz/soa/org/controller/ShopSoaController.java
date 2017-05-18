package com.biz.soa.org.controller;

import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.common.exception.ExceptionCode;
import com.biz.gbck.dao.mysql.po.org.ShopDetailPo;
import com.biz.gbck.dao.mysql.po.org.ShopPo;
import com.biz.gbck.dao.mysql.po.org.ShopQualificationPo;
import com.biz.gbck.dao.mysql.po.org.UserPo;
import com.biz.gbck.dao.redis.ro.org.ShopRo;
import com.biz.gbck.dao.redis.ro.org.ShopTypeRo;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.enums.user.AuditStatus;
import com.biz.gbck.enums.user.ShopTypeStatus;
import com.biz.gbck.transform.org.ShopDetailPoToShopUpdateDetailVo;
import com.biz.gbck.vo.org.*;
import com.biz.gbck.vo.spring.PageVO;
import com.biz.soa.org.service.interfaces.ShopSoaService;
import com.biz.soa.org.service.interfaces.ShopTypeSoaService;
import com.biz.soa.org.util.RestUtil;
import com.biz.support.web.handler.JSONResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户模块 注册,登陆,修改密码,获取用户信息等
 *
 * @author defei
 */

@RestController
@RequestMapping("soa/shop")
public class ShopSoaController extends BaseRestController {

    @Autowired
    private ShopSoaService shopSoaService;

    @Autowired
    private ShopTypeSoaService shopTypeSoaService;

    private static Logger logger = LoggerFactory.getLogger(ShopSoaController.class);

    private Integer maxShopNameLength = 40;

    /**
     * 修改商户详细资料
     */
    @RequestMapping(value = "updateDetail", method = RequestMethod.POST)
    public JSONResult updateDetails(@RequestBody ShopUpdateDetailReqVo shopUpdateDetailReqVo)
        throws CommonException {

//        ShopUpdateDetailReqVo shopUpdateDetailReqVo =
//            RestUtil.parseBizData(request, ShopUpdateDetailReqVo.class);
        logger.info("Received /shops/updateDetail POST request with ShopUpdateDetailReqVo:{}",
            shopUpdateDetailReqVo);
        if (shopUpdateDetailReqVo.getName().length() > maxShopNameLength) {
            throw new CommonException("店招名称长度不能超过" + maxShopNameLength,
                ExceptionCode.Global.PARAMETER_ERROR);
        }
        shopSoaService.updateDetail(shopUpdateDetailReqVo);
        return new JSONResult();
    }

    /**
     * 获取最后一次提交的详细资料
     */
    @RequestMapping(value = "latestDetail", method = RequestMethod.POST)
    public JSONResult getLatestDetail(@RequestBody ShopDetailOrQualificationGetReqVo shopDetailOrQualificationGetReqVo)
        throws CommonException {

//        ShopDetailOrQualificationGetReqVo shopDetailOrQualificationGetReqVo =
//            RestUtil.parseBizData(request, ShopDetailOrQualificationGetReqVo.class);
        logger.info("Received /shops/latestDetail POST request with shopId:{}, userId:{}",
            shopDetailOrQualificationGetReqVo.getShopId(),
            shopDetailOrQualificationGetReqVo.getUserId());
        ShopDetailPo latestDetail = shopSoaService.findLatestDetail(shopDetailOrQualificationGetReqVo);
        return latestDetail == null ?
            new JSONResult() :
            new JSONResult(new SimpleShopDetail(latestDetail));
    }

    /**
     * 修改商户质资
     */
    @RequestMapping(value = "updateQualification", method = RequestMethod.POST)
    public JSONResult updateQualification(@RequestBody ShopUpdateQualificationReqVo updateQualificationReqVo)
            throws CommonException {

//        ShopUpdateQualificationReqVo updateQualificationReqVo =
//            RestUtil.parseBizData(request, ShopUpdateQualificationReqVo.class);
        logger.info(
            "Received /shops/updateQualification POST request with ShopUpdateQualificationReqVo:{}",
            updateQualificationReqVo);
        shopSoaService.updateQualification(updateQualificationReqVo);
        return new JSONResult();
    }

    /**
     * 返回最后一次提交的商户质资
     */
    @RequestMapping(value = "latestQualification", method = RequestMethod.POST)
    public JSONResult getLatestQualification(@RequestBody ShopDetailOrQualificationGetReqVo shopDetailOrQualificationGetReqVo)
            throws CommonException {

//        ShopDetailOrQualificationGetReqVo shopDetailOrQualificationGetReqVo =
//            RestUtil.parseBizData(request, ShopDetailOrQualificationGetReqVo.class);
        logger.info("Received /shops/latestQualification POST request with shopId:{}, userId:{}",
            shopDetailOrQualificationGetReqVo.getShopId(),
            shopDetailOrQualificationGetReqVo.getUserId());
        ShopQualificationPo latestQualification =
            shopSoaService.findLatestQualification(shopDetailOrQualificationGetReqVo);
        return latestQualification == null ?
            new JSONResult() :
            new JSONResult(new SimpleShopQualification(latestQualification));
    }

    /**
     * 返回所有正常的商户类型
     */
    @RequestMapping(value = "types", method = RequestMethod.POST)
    public JSONResult listShopTypes() {
        logger.info("Received /shops/types GET request.");
        List<ShopTypeRo> normalShopTypes = shopTypeSoaService.findAllShopTypeRo(ShopTypeStatus.NORMAL);
        return new JSONResult(normalShopTypes);
    }

    /**
     * 修改商户收货地址
     */
    @RequestMapping(value = "updateDeliveryAddress", method = RequestMethod.POST)
    public JSONResult updateDeliveryAddress(@RequestBody ShopChangeDeliveryAddressReqVo changeDeliveryAddressReqVo)
            throws CommonException {

//        ShopChangeDeliveryAddressReqVo changeDeliveryAddressReqVo =
//            RestUtil.parseBizData(request, ShopChangeDeliveryAddressReqVo.class);
        logger.info(
            "Received /shops/updateDeliveryAddress POST request with ShopChangeDeliveryAddressReqVo:{}",
            changeDeliveryAddressReqVo);
        shopSoaService.changeDeliveryAddress(changeDeliveryAddressReqVo);
        return new JSONResult();
    }

    /**
     * 修改商户收货人姓名
     */
    @RequestMapping("changeDeliveryName")
    public JSONResult updateDeliveryName(@RequestBody UserChangeDeliveryNameReqVo changeDeliveryAddressReqVo)
            throws CommonException {

//        UserChangeDeliveryNameReqVo changeDeliveryAddressReqVo =
//            RestUtil.parseBizData(request, UserChangeDeliveryNameReqVo.class);
        logger.info(
            "Received /shops/updateDeliveryName POST request with userId:{}, deliveryName:{}",
            changeDeliveryAddressReqVo.getUserId(), changeDeliveryAddressReqVo.getDeliveryName());
        shopSoaService.changeDeliveryName(changeDeliveryAddressReqVo);
        return new JSONResult();
    }

    @RequestMapping("getUpdateAddressStatus")
    public JSONResult getUpdateAddressStatus(@RequestBody ShopDetailOrQualificationGetReqVo shopDetailOrQualificationGetReqVo)
            throws CommonException {

//        ShopDetailOrQualificationGetReqVo shopDetailOrQualificationGetReqVo =
//            RestUtil.parseBizData(request, ShopDetailOrQualificationGetReqVo.class);
        logger.info("Received /shops/getUpdateAddressStatus request with shopID:{}",
            shopDetailOrQualificationGetReqVo.getShopId());
        ShopDetailPo latestDetail = shopSoaService.findLatestDetail(shopDetailOrQualificationGetReqVo);
        return new JSONResult(new ShopDetailPoToShopUpdateDetailVo().apply(latestDetail));
    }

    /**
     *获取所有条件审核商户
     */
    @RequestMapping(value = "findShopAuditDataOfWaitForAudit", method = RequestMethod.POST)
    public PageVO<ShopDetailResVo> findShopAuditDataOfWaitForAudit(@RequestBody ShopSearchVo reqVo) {
        return shopSoaService.findShopAuditDataOfWaitForAudit(reqVo);
    }

    /**
     * 获取单个商户待审核信息
     */
    @RequestMapping(value = "findShopAuditDataOfWaitForAuditByShopId", method = RequestMethod.POST)
    public ShopDetailResVo findShopAuditDataOfWaitForAuditByShopId(@RequestBody Long shopId) {
        return shopSoaService.findShopAuditDataOfWaitForAuditByShopId(shopId);
    }

    /**
     * 判断businessLicenceId在数据库中是否已经存在
     */
    @RequestMapping(value = "isBusinessLicenceIdExist", method = RequestMethod.POST)
    public Boolean isBusinessLicenceIdExist(@RequestParam("businessLicenceId") String businessLicenceId,
                                            @RequestParam("shopId") Long shopId) {
        return shopSoaService.isBusinessLicenceIdExist(businessLicenceId, shopId);
    }

    /**
     * 商户统一审核接口 保存审核信息
     */
    @RequestMapping(value = "auditShop", method = RequestMethod.POST)
    public void auditShop(@RequestBody ShopAuditReqVo reqVo) throws CommonException {
        shopSoaService.auditShop(reqVo);
    }

    @RequestMapping(value = "updateShopStatus", method = RequestMethod.POST)
    public Boolean updateShopStatus(@RequestParam("id") Long id, @RequestParam("shopStatus")CommonStatusEnum shopStatus) {
        return shopSoaService.updateShopStatus(id, shopStatus);
    }

    /**
     * 物理删除，慎用
     */
    @RequestMapping(value = "destroyShopAndItUsers", method = RequestMethod.POST)
    public void destroyShopAndItUsers(@RequestParam("shopId") Long shopId, @RequestParam("handler") String handler) throws CommonException {
        shopSoaService.destroyShopAndItUsers(shopId, handler);
    }

    @RequestMapping(value = "enableShopByIds", method = RequestMethod.POST)
    public Integer enableShopByIds(@RequestParam("ids") String[] ids) {
        return shopSoaService.enableShopByIds(ids);
    }

    @RequestMapping(value = "disableShopByIds", method = RequestMethod.POST)
    public Integer disableShopByIds(@RequestParam("ids") String[] ids) {
        return shopSoaService.disableShopByIds(ids);
    }

    @RequestMapping(value = "findShopPoByMobileAndHaveNotShopId", method = RequestMethod.POST)
    public List<ShopPo> findShopPoByMobileAndHaveNotShopId(@RequestParam("mobile") String mobile, @RequestParam("shopId") Long shopId) {
        return shopSoaService.findShopPoByMobileAndHaveNotShopId(mobile, shopId);
    }

    @RequestMapping(value = "updateBlackList", method = RequestMethod.POST)
    public String updateBlackList(@RequestParam("blackList") String blackList) {
        return shopSoaService.updateBlackList(blackList);
    }

    @RequestMapping(value = "getShopPoBackList", method = RequestMethod.POST)
    public List<ShopPo> getShopPoBackList() {
        return shopSoaService.getShopPoBackList();
    }

    @RequestMapping(value = "deleteBlackList", method = RequestMethod.POST)
    public JSONResult deleteBlackList(@RequestBody List<String> shopIds) {
        shopSoaService.deleteBlackList(shopIds);
        return new JSONResult();
    }

    @RequestMapping(value = "findShopRoById", method = RequestMethod.POST)
    public ShopRo findShopRoById(@RequestParam("id") Long id) throws CommonException{
        return shopSoaService.findShop(id);
    }


}
