package com.biz.soa.org.controller;

import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.common.exception.ExceptionCode;
import com.biz.gbck.dao.mysql.po.org.ShopDetailPo;
import com.biz.gbck.dao.mysql.po.org.ShopQualificationPo;
import com.biz.gbck.dao.redis.ro.org.ShopTypeRo;
import com.biz.gbck.enums.user.ShopTypeStatus;
import com.biz.gbck.transform.org.ShopDetailPoToShopUpdateDetailVo;
import com.biz.gbck.vo.org.ShopChangeDeliveryAddressReqVo;
import com.biz.gbck.vo.org.ShopDetailOrQualificationGetReqVo;
import com.biz.gbck.vo.org.ShopUpdateDetailReqVo;
import com.biz.gbck.vo.org.ShopUpdateQualificationReqVo;
import com.biz.gbck.vo.org.SimpleShopDetail;
import com.biz.gbck.vo.org.SimpleShopQualification;
import com.biz.gbck.vo.org.UserChangeDeliveryNameReqVo;
import com.biz.soa.org.service.interfaces.ShopSoaService;
import com.biz.soa.org.service.interfaces.ShopTypeSoaService;
import com.biz.soa.org.util.RestUtil;
import com.biz.support.web.handler.JSONResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @RequestMapping("updateDetail") public JSONResult updateDetails(HttpServletRequest request)
        throws CommonException {

        ShopUpdateDetailReqVo shopUpdateDetailReqVo =
            RestUtil.parseBizData(request, ShopUpdateDetailReqVo.class);
        logger.debug("Received /shops/updateDetail POST request with ShopUpdateDetailReqVo:{}",
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
    @RequestMapping("latestDetail") public JSONResult getLatestDetail(HttpServletRequest request)
        throws CommonException {

        ShopDetailOrQualificationGetReqVo shopDetailOrQualificationGetReqVo =
            RestUtil.parseBizData(request, ShopDetailOrQualificationGetReqVo.class);
        logger.debug("Received /shops/latestDetail POST request with shopId:{}, userId:{}",
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
    @RequestMapping("updateQualification") public JSONResult updateQualification(
        HttpServletRequest request) throws CommonException {

        ShopUpdateQualificationReqVo updateQualificationReqVo =
            RestUtil.parseBizData(request, ShopUpdateQualificationReqVo.class);
        logger.debug(
            "Received /shops/updateQualification POST request with ShopUpdateQualificationReqVo:{}",
            updateQualificationReqVo);
        shopSoaService.updateQualification(updateQualificationReqVo);
        return new JSONResult();
    }

    /**
     * 反回最后一次提交的商户质资
     */
    @RequestMapping("latestQualification") public JSONResult getLatestQualification(
        HttpServletRequest request) throws CommonException {

        ShopDetailOrQualificationGetReqVo shopDetailOrQualificationGetReqVo =
            RestUtil.parseBizData(request, ShopDetailOrQualificationGetReqVo.class);
        logger.debug("Received /shops/latestQualification POST request with shopId:{}, userId:{}",
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
    @RequestMapping(value = "types") public JSONResult listShopTypes() {

        logger.debug("Received /shops/types GET request.");
        List<ShopTypeRo> normalShopTypes = shopTypeSoaService.findAllShopTypeRo(ShopTypeStatus.NORMAL);
        return new JSONResult(normalShopTypes);
    }

    /**
     * 修改商户收货地址
     */
    @RequestMapping("updateDeliveryAddress") public JSONResult updateDeliveryAddress(
        HttpServletRequest request) throws CommonException {

        ShopChangeDeliveryAddressReqVo changeDeliveryAddressReqVo =
            RestUtil.parseBizData(request, ShopChangeDeliveryAddressReqVo.class);
        logger.debug(
            "Received /shops/updateDeliveryAddress POST request with ShopChangeDeliveryAddressReqVo:{}",
            changeDeliveryAddressReqVo);
        shopSoaService.changeDeliveryAddress(changeDeliveryAddressReqVo);
        return new JSONResult();
    }

    /**
     * 修改商户收货人姓名
     */
    @RequestMapping("changeDeliveryName") public JSONResult updateDeliveryName(
        HttpServletRequest request) throws CommonException {

        UserChangeDeliveryNameReqVo changeDeliveryAddressReqVo =
            RestUtil.parseBizData(request, UserChangeDeliveryNameReqVo.class);
        logger.debug(
            "Received /shops/updateDeliveryName POST request with userId:{}, deliveryName:{}",
            changeDeliveryAddressReqVo.getUserId(), changeDeliveryAddressReqVo.getDeliveryName());
        shopSoaService.changeDeliveryName(changeDeliveryAddressReqVo);
        return new JSONResult();
    }

    @RequestMapping("getUpdateAddressStatus")
    public JSONResult getUpdateAddressStatus(HttpServletRequest request) throws CommonException {

        ShopDetailOrQualificationGetReqVo shopDetailOrQualificationGetReqVo =
            RestUtil.parseBizData(request, ShopDetailOrQualificationGetReqVo.class);
        logger.debug("Received /shops/getUpdateAddressStatus request with shopID:{}",
            shopDetailOrQualificationGetReqVo.getShopId());
        ShopDetailPo latestDetail = shopSoaService.findLatestDetail(shopDetailOrQualificationGetReqVo);
        return new JSONResult(new ShopDetailPoToShopUpdateDetailVo().apply(latestDetail));
    }
}
