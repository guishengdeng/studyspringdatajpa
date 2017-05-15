package com.biz.service.org;

import com.biz.core.util.DateUtil;
import com.biz.core.util.StringTool;
import com.biz.event.org.ShopAuditEvent;
import com.biz.event.org.ShopDetailUpdateEvent;
import com.biz.event.org.ShopQualificationUpdateEvent;
import com.biz.gbck.common.bean.PageControl;
import com.biz.gbck.common.com.transformer.UserPoToUserRo;
import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.common.exception.DepotnearbyExceptionFactory;
import com.biz.gbck.common.exception.ExceptionCode;
import com.biz.gbck.common.vo.statistic.DailyStatNewShopResultVo;
import com.biz.gbck.common.vo.statistic.ShopAuditStatisticResultVo;
import com.biz.gbck.dao.mysql.po.geo.CityPo;
import com.biz.gbck.dao.mysql.po.geo.DistrictPo;
import com.biz.gbck.dao.mysql.po.geo.ProvincePo;
import com.biz.gbck.dao.mysql.po.org.ShopDetailPo;
import com.biz.gbck.dao.mysql.po.org.ShopLevel;
import com.biz.gbck.dao.mysql.po.org.ShopPo;
import com.biz.gbck.dao.mysql.po.org.ShopQualificationPo;
import com.biz.gbck.dao.mysql.po.org.ShopTypePo;
import com.biz.gbck.dao.mysql.po.org.UserPo;
import com.biz.gbck.dao.mysql.po.payment.LoanApplyType;
import com.biz.gbck.dao.mysql.po.payment.ZsgfApplyStatus;
import com.biz.gbck.dao.mysql.po.payment.ZsgfLoanApplyPo;
import com.biz.gbck.dao.mysql.repository.geo.CityRepository;
import com.biz.gbck.dao.mysql.repository.geo.DistrictRepository;
import com.biz.gbck.dao.mysql.repository.geo.ProvinceRepository;
import com.biz.gbck.dao.mysql.repository.org.ShopDetailRepository;
import com.biz.gbck.dao.mysql.repository.org.ShopQualificationRepository;
import com.biz.gbck.dao.mysql.repository.org.ShopRepository;
import com.biz.gbck.dao.mysql.repository.org.ShopTypeRepository;
import com.biz.gbck.dao.mysql.repository.org.UserRepository;
import com.biz.gbck.dao.mysql.specification.org.ShopSearchSpecification;
import com.biz.gbck.dao.redis.repository.org.ShopRedisDao;
import com.biz.gbck.dao.redis.repository.org.UserRedisDao;
import com.biz.gbck.dao.redis.ro.org.ShopRo;
import com.biz.gbck.dao.redis.ro.org.ShopTypeRo;
import com.biz.gbck.dao.redis.ro.org.UserRo;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.enums.user.AuditRejectReason;
import com.biz.gbck.enums.user.AuditStatus;
import com.biz.gbck.enums.user.ShopTypeStatus;
import com.biz.gbck.transform.org.ShopPoToSearchShopRespVo;
import com.biz.gbck.transform.org.ShopPoToShopDetailPo;
import com.biz.gbck.transform.org.ShopPoToShopRo;
import com.biz.gbck.transform.org.ShopTypePoToShopTypeRo;
import com.biz.gbck.transform.org.UserPoToShopPo;
import com.biz.gbck.vo.org.ChangePaymentPwdReqVo;
import com.biz.gbck.vo.org.SearchShopRespVo;
import com.biz.gbck.vo.org.ShopAuditDataMap;
import com.biz.gbck.vo.org.ShopAuditReqVo;
import com.biz.gbck.vo.org.ShopChangeDeliveryAddressReqVo;
import com.biz.gbck.vo.org.ShopDetailOrQualificationGetReqVo;
import com.biz.gbck.vo.org.ShopEditVo;
import com.biz.gbck.vo.org.ShopExportVo;
import com.biz.gbck.vo.org.ShopSearchVo;
import com.biz.gbck.vo.org.ShopUpdateDetailReqVo;
import com.biz.gbck.vo.org.ShopUpdateQualificationReqVo;
import com.biz.gbck.vo.org.ShopsInfoExportVo;
import com.biz.gbck.vo.org.User20VIPVo;
import com.biz.gbck.vo.org.UserChangeDeliveryNameReqVo;
import com.biz.gbck.vo.org.UserCreateVo;
import com.biz.gbck.vo.org.UserVo;
import com.biz.gbck.vo.search.SearchShopReqVo;
import com.biz.gbck.vo.search.ShopQueryReqVo;
import com.biz.gbck.vo.search.bbc.SearchUserReqVo;
import com.biz.gbck.vo.zsgf.ZsgfLoanQueryReqVo;
import com.biz.manage.vo.FailDetail;
import com.biz.service.CommonService;
import com.biz.service.org.interfaces.ShopService;
import com.biz.service.org.interfaces.ShopTypeService;
import com.biz.service.org.interfaces.UserService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.codelogger.utils.ArrayUtils;
import org.codelogger.utils.BeanUtils;
import org.codelogger.utils.CollectionUtils;
import org.codelogger.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.codelogger.utils.ValueUtils.getValue;


/**
 * Created by defei on 3/11/16.
 */
@Service
@Transactional
public class ShopServiceImpl extends CommonService  implements ShopService {

    private static final Logger logger = LoggerFactory.getLogger(ShopService.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ShopDetailRepository shopDetailRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ShopTypeRepository shopTypeRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private ShopQualificationRepository shopQualificationRepository;

    @Autowired
    private ShopRedisDao shopRedisDao;

    @Autowired
    private ShopTypeService shopTypeService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRedisDao userRedisDao;

//    @Autowired
//    private IdPool idPool;

//    @Override
//    public ShopRo createShop(Long userId, String corporateName, String inviterCode) {
//        UserPo userPo = userService.findUserById(userId);
//        ShopPo shopPo;
//        if (userPo.getShop() != null) {
//            shopPo = userPo.getShop();
//        } else {
//            shopPo = new UserPoToShopPo().apply(userPo);
//            assert shopPo != null;
//            //shopPo.setId(idPool.getNextId(IdType.SHOP));
//            shopPo.setId(idService.nextId());
//        }
//        shopPo.setCorporateName(corporateName);
//        if (inviterCode != null) {
//            shopPo.setInviterCode(inviterCode);
////            DepotEmployeePo employeePo = depotEmployeeService.getDepotEmployeeById(inviterCode);
////            if (employeePo != null) {
////                shopPo.setDepot(employeePo.getDepot());
////                shopPo.setAssartDepot(employeePo.getDepot());
////                shopPo.setDeliveryDepot(employeePo.getDepot());
////            }else {
////                DepotPo depotPo=depotService.findDepotByMcuCode(inviterCode);//dylan.hou 2016/10/12
////                if (depotPo != null) {
////                    shopPo.setDepot(depotPo);
////                    shopPo.setAssartDepot(depotPo);
////                    shopPo.setDeliveryDepot(depotPo);
////                }
////            }
//
//
//        }
//        ShopPo savedShopPo = shopRepository.save(shopPo);
//        return syncShopPoToRedis(savedShopPo);
//    }
//
//    @Override
//    public ShopRo createShopByAdmin(ShopEditVo shopEditVo, String admin) throws CommonException {
//        logger.info("Create shop[{}] info by[{}]", shopEditVo.getShopId(), admin);
//        UserPo userPo = userRepository.findByMobile(shopEditVo.getMobile());
//        if(userPo == null){
//            userPo = userRepository.findByAccount(shopEditVo.getMobile());
//        }
//        if (userPo != null) {
//            throw DepotnearbyExceptionFactory.User.USER_EXIST;
//        }
//        UserCreateVo userCreateVo = new UserCreateVo();
//        userCreateVo.setName(shopEditVo.getName());
//        userCreateVo.setMobile(shopEditVo.getMobile());
//        userCreateVo.setOriginalPassword("00000000");
//        userCreateVo.setPassword(StringTool.encodedByMD5(userCreateVo.getOriginalPassword()));
//        UserRo user = userService.createUser(userCreateVo, admin);
//
//        ShopRo shopRo = createShop(Long.valueOf(user.getId()), shopEditVo.getCorporateName(), null);
//
//        userPo = userService.findUserById(Long.valueOf(user.getId()));
//        userPo.setShop(findShopPo(Long.valueOf(shopRo.getId())));
//        userPo.setIsAdmin(true);
//        userService.saveUser(userPo);
//        shopEditVo.setShopId(Long.valueOf(shopRo.getId()));
//        adminNewShop(shopEditVo, admin);
//        ShopPo shopPo = findShopPo(Long.valueOf(shopRo.getId()));
//        shopPo.setDetailAuditStatus(AuditStatus.NORMAL.getValue());
//        shopPo.setQualificationAuditStatus(AuditStatus.NORMAL.getValue());
//        save(shopPo);
//        return syncShopPoToRedis(shopPo);
//    }
//
//    @Override
//    public ShopRo adminNewShop(ShopEditVo shopEditVo, String loginUsername) {
//        logger.info("Update shop[{}] info by[{}]", shopEditVo.getShopId(), loginUsername);
//        ShopPo shopPo = shopRepository.findOne(shopEditVo.getShopId());  //shopId
//        shopPo.setSupportPaymentIds(shopEditVo.getSupportPaymentIds());  //支持付款类型
//        shopPo.setName(shopEditVo.getName());                            //店铺名称
//
//        shopPo.setCorporateName(shopEditVo.getCorporateName());          //法人名字
//        shopPo.setShopType(shopTypeRepository.findOne(shopEditVo.getShopTypeId())); //店铺类型ID
//        shopPo.setMobile(shopEditVo.getMobile());                        //手机号
//        shopPo.setDeliveryMobile(shopEditVo.getMobile());                //收货人电话（手机号）
//        shopPo.setDeliveryName(shopEditVo.getCorporateName());           //收货人名字（法人名字）
//        shopPo.setDeliveryAddress(shopEditVo.getDeliveryAddress());     //收货地址
//       /* shopPo.setTel(shopEditVo.getTel()); */                            //店铺电话
//        shopPo.setProvince(provinceRepository.findOne(shopEditVo.getProvinceId())); //省
//        shopPo.setCity(cityRepository.findOne(shopEditVo.getCityId()));             //市
//        shopPo.setDistrict(districtRepository.findOne(shopEditVo.getDistrictId())); //县
//        //shopPo.setDepot(depotRepository.findOne(shopEditVo.getDepotId()));          //价格门店
////        String assartDepotId = shopEditVo.getAssartDepotId();
////        if (StringUtils.isNotBlank(assartDepotId)) {
////            shopPo.setAssartDepot(depotRepository.findOne(assartDepotId));        //开发门店
////        }
////        String deliveryDepotId = shopEditVo.getDeliveryDepotId();
////        if (StringUtils.isNotBlank(deliveryDepotId)) {
////            shopPo.setDeliveryDepot(depotRepository.findOne(deliveryDepotId));    //配送门店
////        }
//        shopPo.setShopPhoto(shopEditVo.getShopPhoto()); //门头照片
////        shopPo.setPriceTags(priceTagService.findByIds(shopEditVo.getPriceTagIds()));//智选价格
////        shopPo.setSaleAreas(saleAreaRepository.findAll(shopEditVo.getSaleAreaIds())); //销售区域
////        shopPo.setBusinessTags(businessTagService.findByIds(shopEditVo.getBusinessTagIds()));//智选分类
//
//        shopPo.setBusinessLicence(
//                isNotBlank(shopEditVo.getBusinessLicence()) ? shopEditVo.getBusinessLicence() : null); //营业执照
//        shopPo.setBusinessLicenceName(
//                isNotBlank(shopEditVo.getBusinessLicenceName()) ?         //营业执照名称
//                        shopEditVo.getBusinessLicenceName() : null);
//        shopPo.setBusinessLicenceId(isNotBlank(shopEditVo.getBusinessLicenceId()) ?      //营业执照ID
//                shopEditVo.getBusinessLicenceId() : null);
//
//        shopPo.setLiquorSellLicence(isNotBlank(shopEditVo.getLiquorSellLicence()) ?
//                shopEditVo.getLiquorSellLicence() :
//                null); //酒类流通许可证
//        shopPo
//                .setLiquorSellLicenceId(isNotBlank(shopEditVo.getLiquorSellLicenceId()) ?    //酒类流通许可证ID
//                        shopEditVo.getLiquorSellLicenceId() : null);
//        shopPo.setCorporateIdPhoto(
//                isNotBlank(shopEditVo.getCorporateIdPhoto()) ? shopEditVo.getCorporateIdPhoto() : null); //法人身份证
//        shopPo.setCorporateId(isNotBlank(shopEditVo.getCorporateId()) ?
//                shopEditVo.getCorporateId() :
//                null);    //法人身份证ID
//        shopPo.setDetailAuditStatus(shopEditVo.getDetailAuditStatus());        //详情审核状态
//        shopPo.setQualificationAuditStatus(shopEditVo.getDetailAuditStatus()); //资质审核详情
////        DepotEmployeePo employeePo =
////                depotEmployeeService.getDepotEmployeeById(shopEditVo.getInviterCode());
////        if (employeePo != null) {
////            shopPo.setInviterCode(employeePo.getId());
////        }
//        ShopPo savedShopPo = shopRepository.save(shopPo);
//        return syncShopPoToRedis(savedShopPo);
//    }
//
//    @Override
//    public ShopRo findShop(Long shopId) throws CommonException {
//        if (shopId == null) {
//            return null;
//        }
//        ShopRo shopRo = shopRedisDao.findOne(String.valueOf(shopId));
//        if (shopRo == null) {
//            ShopPo shopPo = shopRepository.findOne(shopId);
//            shopRo = syncShopPoToRedis(shopPo);
//        }
//        if (shopRo == null) {
//            throw DepotnearbyExceptionFactory.Shop.SHOP_NOT_EXIST;
//        }
//        return shopRo;
//    }
//
//    @Override
//    public ShopPo findShopOrCreateByBaiduUserIdAndBaiduGeoCode(Long baiduUserId, Integer geoCode) throws CommonException {
//        return null;
//    }
//
//    @Override
//    public boolean isBaiduNuomiShop(ShopPo shopPo, ShopPo baiduParentShopPo) {
//        return false;
//    }
//
//    @Override
//    public boolean isBaiduNuomiDefaultShop(ShopPo shopPo) {
//        return false;
//    }
//
//    @Override
//    public ShopRo findShopByUserId(Long userId) throws CommonException {
//        UserRo user = userService.findUser(userId);
//        return user == null ? null : findShop(user.getShopId());
//    }
//
//    @Override
//    public ShopPo findShopPo(Long shopId) {
//        return shopRepository.findOne(shopId);
//    }
//
//    @Override
//    public ShopPo findShopByName(String name) {
//        return shopRepository.findByName(name);
//    }
//
//    @Override
//    public void updateDetail(ShopUpdateDetailReqVo reqVo) throws CommonException {
//        UserRo user = userService.findUser(reqVo.getUserId());
//        validateShopOperateAuthority(user.getIsAdmin(), reqVo.getShopId(), user.getShopId());
//
//        List<ShopDetailPo> shopDetailsOfWaitForAudit = shopDetailRepository
//                .findByShopIdAndAuditStatusInOrderByCreateTimeDesc(reqVo.getShopId(),
//                        newArrayList(AuditStatus.WAIT_FOR_AUDIT.getValue(),
//                                AuditStatus.NORMAL_AND_HAS_NEW_UPDATE_WAIT_FOR_AUDIT.getValue()));
//        ShopDetailPo shopDetailPo = CollectionUtils.getFirstOrNull(shopDetailsOfWaitForAudit);
//        if (shopDetailPo == null) {
//            shopDetailPo = new ShopDetailPo();
//            ShopPo shopPo = shopRepository.findOne(reqVo.getShopId());
//            shopDetailPo.setShop(shopPo);
//            shopDetailPo.setAuditStatus(
//                    Objects.equals(shopPo.getDetailAuditStatus(), AuditStatus.NORMAL.getValue()) ?
//                            AuditStatus.NORMAL_AND_HAS_NEW_UPDATE_WAIT_FOR_AUDIT.getValue() :
//                            AuditStatus.WAIT_FOR_AUDIT.getValue());
//        }
//        shopDetailPo.setName(reqVo.getName());
//        shopDetailPo.setCorporateName(reqVo.getCorporateName());
//        shopDetailPo.setMobile(user.getMobile());
//        shopDetailPo.setDeliveryName(reqVo.getCorporateName());
//        shopDetailPo.setDeliveryMobile(user.getMobile());
//        ShopTypePo shopType = shopTypeRepository.findOne(reqVo.getShopTypeId());
//        if(shopType == null){
//            throw DepotnearbyExceptionFactory.ShopType.SHOP_TYPE_NOT_EXIST;
//        }
//        shopDetailPo.setShopType(shopType);
//        ProvincePo provincePo = provinceRepository.findOne(reqVo.getProvinceId());
//        if(provincePo == null){
//            throw DepotnearbyExceptionFactory.GEO.PROVINCE_NOT_EXIST;
//        }
//        shopDetailPo.setProvince(provincePo);
//        CityPo cityPo = cityRepository.findOne(reqVo.getCityId());
//        if(cityPo == null){
//            throw DepotnearbyExceptionFactory.GEO.CITY_NOT_EXIST;
//        }
//        shopDetailPo.setCity(cityPo);
//        DistrictPo districtPo = districtRepository.findOne(reqVo.getDistrictId());
//        if(districtPo == null){
//            throw DepotnearbyExceptionFactory.GEO.DISTRICT_NOT_EXIST;
//        }
//        shopDetailPo.setDistrict(districtPo);
//        shopDetailPo.setDeliveryAddress(reqVo.getDeliveryAddress());
//        ShopDetailPo savedShopDetailPo = shopDetailRepository.save(shopDetailPo);
//
//        changeShopStatusToWaitForAudit(savedShopDetailPo);
//        publishEvent(new ShopDetailUpdateEvent(this, savedShopDetailPo));
//    }
//
//    @Override
//    public ShopDetailPo findLatestDetail(ShopDetailOrQualificationGetReqVo reqVo) throws CommonException {
//        UserRo user = userService.findUser(reqVo.getUserId());
//        validateShopOperateAuthority(user.getIsAdmin(), reqVo.getShopId(), user.getShopId());
//        ShopDetailPo latestShopDetailPo = CollectionUtils
//                .getFirstOrNull(shopDetailRepository.findByShopIdOrderByIdDesc(user.getShopId()));
//        if (latestShopDetailPo == null) {
//            latestShopDetailPo = new ShopDetailPo();
//            ShopPo shopPo = findShopPo(reqVo.getShopId());
//            latestShopDetailPo.setShop(shopPo);
//            latestShopDetailPo.setProvince(shopPo.getProvince());
//            latestShopDetailPo.setCity(shopPo.getCity());
//            latestShopDetailPo.setDistrict(shopPo.getDistrict());
//            latestShopDetailPo.setDeliveryAddress(shopPo.getDeliveryAddress());
//            latestShopDetailPo.setAuditStatus(shopPo.getDetailAuditStatus());
//        }
//        return latestShopDetailPo;
//    }
//
//    @Override
//    public ShopDetailPo auditShopDetail(ShopAuditReqVo reqVo) throws CommonException {
//        AuditStatus detailAuditStatus = reqVo.getAuditStatus();
//        if (reqVo.getShopDetailId() == null) {
//            //            return ;
//            return new ShopDetailPo();
//        }
//        ShopDetailPo shopDetailPo = shopDetailRepository.findOne(reqVo.getShopDetailId());
//        if (shopDetailPo == null) {
//            //            return;
//            return new ShopDetailPo();
//        }
//        Integer originAuditStatus = shopDetailPo.getAuditStatus();
//        if (detailAuditStatus == AuditStatus.AUDIT_FAILED) {
//            if (Objects.equals(originAuditStatus,
//                    AuditStatus.NORMAL_AND_HAS_NEW_UPDATE_WAIT_FOR_AUDIT.getValue())) {
//                shopDetailPo
//                        .setAuditStatus(AuditStatus.NORMAL_AND_NEW_UPDATE_AUDIT_FAILED.getValue());
//            } else {
//                shopDetailPo.setAuditStatus(AuditStatus.AUDIT_FAILED.getValue());
//            }
//        } else {
//            shopDetailPo.setAuditStatus(detailAuditStatus.getValue());
//        }
//
//        shopDetailPo.setRejectReason(CollectionUtils.join(reqVo.getAuditRejectReasons(), ","));
//        shopDetailPo.setHandlerUserName(reqVo.getHandler());
//        shopDetailPo.setHandTime(DateUtil.now());
//        /*
//        dylan :添加修改后的收货地址 deliveryAddress
//         */
//        if(reqVo.getProvinceId() != null) {
//            shopDetailPo.setProvince(provinceRepository.findOne(reqVo.getProvinceId()));
//        }
//        if(reqVo.getCityId() != null) {
//            shopDetailPo.setCity(cityRepository.findOne(reqVo.getCityId()));
//        }
//        if(reqVo.getDistrictId() != null) {
//            shopDetailPo.setDistrict(districtRepository.findOne(reqVo.getDistrictId()));
//        }
//        shopDetailPo.setDeliveryAddress(reqVo.getDeliveryAddress());
//
//        ShopDetailPo savedShopDetailPo = shopDetailRepository.save(shopDetailPo);
//        shopDetailRepository
//                .updateAuditStatus(shopDetailPo.getShop().getId(), AuditStatus.DATA_EXPIRED.getValue(),
//                        newArrayList(AuditStatus.WAIT_FOR_AUDIT.getValue(),
//                                AuditStatus.NORMAL_AND_HAS_NEW_UPDATE_WAIT_FOR_AUDIT.getValue()));
//        /*
//        保存商铺 审核信息,添加字段为：开发门店
//         */
//        updateShopDetailStatus(savedShopDetailPo, reqVo.getDepotId(), reqVo.getDeliveryDepotId(),
//                reqVo.getAssartDepotId(), reqVo.getSupportPaymentIds(), reqVo.getBusinessTagIds(),
//                reqVo.getPriceTagIds());
//
//        return savedShopDetailPo;
//    }
//
//    @Override
//    public void updateQualification(ShopUpdateQualificationReqVo reqVo) throws CommonException {
//
//        UserRo user = userService.findUser(reqVo.getUserId());
//        validateShopOperateAuthority(user.getIsAdmin(), reqVo.getShopId(), user.getShopId());
//
//        List<ShopQualificationPo> shopQualificationsOfWaitForAudit = shopQualificationRepository
//                .findByShopIdAndAuditStatusInOrderByIdDesc(reqVo.getShopId(),
//                        newArrayList(AuditStatus.WAIT_FOR_AUDIT.getValue(),
//                                AuditStatus.NORMAL_AND_HAS_NEW_UPDATE_WAIT_FOR_AUDIT.getValue()));
//
//        ShopQualificationPo shopQualificationPo =
//                CollectionUtils.getFirstOrNull(shopQualificationsOfWaitForAudit);
//        if (shopQualificationPo == null) {
//            shopQualificationPo = new ShopQualificationPo();
//            ShopPo shopPo = shopRepository.findOne(reqVo.getShopId());
//            shopQualificationPo.setShop(shopPo);
//            shopQualificationPo.setAuditStatus(Objects
//                    .equals(shopPo.getQualificationAuditStatus(), AuditStatus.NORMAL.getValue()) ?
//                    AuditStatus.NORMAL_AND_HAS_NEW_UPDATE_WAIT_FOR_AUDIT.getValue() :
//                    AuditStatus.WAIT_FOR_AUDIT.getValue());
//        }
//
//        shopQualificationPo.setBusinessLicence(reqVo.getBusinessLicence());
//        shopQualificationPo.setShopPhoto(reqVo.getShopPhoto());
//        shopQualificationPo.setLiquorSellLicence(reqVo.getLiquorSellLicence());
//        shopQualificationPo.setCorporateIdPhoto(reqVo.getCorporateIdPhoto());
//        shopQualificationPo.setCreateTime(DateUtil.now());
//        ShopQualificationPo savedShopQualificationPo =
//                shopQualificationRepository.save(shopQualificationPo);
//        publishEvent(new ShopQualificationUpdateEvent(this, savedShopQualificationPo));
//    }
//
//    @Override
//    public ShopQualificationPo findLatestQualification(ShopDetailOrQualificationGetReqVo reqVo) throws CommonException {
//        UserRo user = userService.findUser(reqVo.getUserId());
//        validateShopOperateAuthority(user.getIsAdmin(), reqVo.getShopId(), user.getShopId());
//        return CollectionUtils.getFirstOrNull(
//                shopQualificationRepository.findByShopIdOrderByIdDesc(reqVo.getShopId()));
//    }
//
//    @Override
//    public ShopQualificationPo auditShopQualification(ShopAuditReqVo reqVo) {
//        Long shopQualificationId = reqVo.getShopQualificationId();
//        if (shopQualificationId == null)
//            //            return;
//            return new ShopQualificationPo();
//        ShopQualificationPo shopQualificationPo =
//                shopQualificationRepository.findOne(shopQualificationId);
//        if (shopQualificationPo == null) {
//            //            return;
//            return new ShopQualificationPo();
//        }
//        Integer originAuditStatus = shopQualificationPo.getAuditStatus();
//        shopQualificationPo.setAuditStatus(reqVo.getAuditStatus().getValue());
//        shopQualificationPo.setBusinessLicenceId(reqVo.getBusinessLicenceId());
//        shopQualificationPo.setBusinessLicenceName(reqVo.getBusinessLicenceName());
//        shopQualificationPo.setLiquorSellLicenceId(reqVo.getLiquorSellLicenceId());
//        shopQualificationPo.setCorporateId(reqVo.getCorporateId());
//        shopQualificationPo.setHandlerUserName(reqVo.getHandler());
//        shopQualificationPo.setHandTime(DateUtil.now());
//        shopQualificationPo
//                .setRejectReason(CollectionUtils.join(reqVo.getAuditRejectReasons(), ","));
//        ShopQualificationPo savedShopQualificationPo =
//                shopQualificationRepository.save(shopQualificationPo);
//        shopQualificationRepository.updateAuditStatus(shopQualificationPo.getShop().getId(),
//                AuditStatus.DATA_EXPIRED.getValue(), newArrayList(AuditStatus.WAIT_FOR_AUDIT.getValue(),
//                        AuditStatus.NORMAL_AND_HAS_NEW_UPDATE_WAIT_FOR_AUDIT.getValue()));
//
//        syncShopQualificationToShop(savedShopQualificationPo);
//
//        if (Objects.equals(originAuditStatus, AuditStatus.WAIT_FOR_AUDIT.getValue())
//                && reqVo.getAuditStatus() == AuditStatus.NORMAL) {
//            dispatchInviteVoucher(shopQualificationPo.getShop().getId());
//        }
//
//        return savedShopQualificationPo;
//    }
//
//    @Override
//    public ShopPo auditUpdateShopPo(ShopAuditReqVo reqVo) {
//        ShopPo shopPo = shopRepository.findOne(reqVo.getShopId());
//        if (reqVo.getLongitude() != null) {//经度
//            shopPo.setLongitude(reqVo.getLongitude());
//        }
//        if (reqVo.getLatitude() != null) {//纬度
//            shopPo.setLatitude(reqVo.getLatitude());
//        }
//        if (reqVo.getProvinceId() != null) {//省市县
//            shopPo.setProvince(provinceRepository.findOne(reqVo.getProvinceId()));
//        }
//        if (reqVo.getCityId() != null) {
//            shopPo.setCity(cityRepository.findOne(reqVo.getCityId()));
//        }
//        if (reqVo.getDistrictId() != null) {
//            shopPo.setDistrict(districtRepository.findOne(reqVo.getDistrictId()));
//        }
//        if (reqVo.getDeliveryAddress() != null) {//详情地址
//            shopPo.setDeliveryAddress(reqVo.getDeliveryAddress());
//        }
////        if (reqVo.getDepotId() != null) { //价格门店
////            shopPo.setDepot(depotRepository.findOne(reqVo.getDepotId()));
////        }
////        if (reqVo.getDeliveryDepotId() != null) { //配送门店
////            shopPo.setDeliveryDepot(depotRepository.findOne(reqVo.getDeliveryDepotId()));
////        }
////        if (reqVo.getAssartDepotId() != null) { //开发门店
////            shopPo.setAssartDepot(depotRepository.findOne(reqVo.getAssartDepotId()));
////        }
//        if (isNotBlank(reqVo.getInviterCode())) {
//            shopPo.setInviterCode(reqVo.getInviterCode());
//        }
////        //应冉寻要求，20倍会员不记录推荐人
////        if(shopPo.getShopLevel() == ShopLevel.VIP_20){
////            shopPo.setInviterCode(null);
////        }
//        ShopPo returnShopPo = shopRepository.save(shopPo);
//        syncShopPoToRedis(shopPo); //同步到redis
//        return returnShopPo;
//    }
//
//    @Override
//    public void auditShop(ShopAuditReqVo reqVo) throws CommonException {
//        ShopPo shop;
//        if (reqVo.getAuditStatus() == AuditStatus.NORMAL) {
//            reqVo.setAuditStatus(AuditStatus.NORMAL);
//        } else {
//            reqVo.setAuditStatus(AuditStatus.AUDIT_FAILED);
//        }
//       /* this.updateShopType(reqVo);
//        this.auditUpdateShopPo(reqVo);*/ //修改shopPo dylan
//        ShopDetailPo shopDetailPo = auditShopDetail(reqVo); //修改商户详情
//        ShopQualificationPo shopQualificationPo = auditShopQualification(reqVo); //修改商户资质
//        publishEvent(new ShopAuditEvent(this, shopDetailPo, shopQualificationPo)); //发布事件
//
//        if (reqVo.getShopDetailId() != null) {
//            shop = shopDetailRepository.findOne(reqVo.getShopDetailId()).getShop();
//        } else {
//            shop = shopQualificationRepository.findOne(reqVo.getShopQualificationId()).getShop();
//        }
//        String mobile = shop.getMobile();
//      /* 消息发送  if (reqVo.getAuditStatus() == AuditStatus.NORMAL) {
//            logger.debug("send message to [{}],", mobile);
//            smsService.SMSMsg(mobile, AlidayuTemplateCode.AUDIT_SUCCESS, null);
//        } else if (reqVo.getAuditStatus() == AuditStatus.AUDIT_FAILED) {
//            for (String reason : reqVo.getAuditRejectReasons()) {
//                if (AuditRejectReason.DETAIL_INVALID.getValue().toString().equals(reason)) {
//                    logger.debug("send message to [{}],", mobile);
//                    smsService.SMSMsg(mobile, AlidayuTemplateCode.AUDIT_DETAIL_FAILED, null);
//                } else if (AuditRejectReason.BUSINESS_LICENCE_PHOTO_NOT_CLEAR.getValue().toString()
//                        .equals(reason)) {
//                    smsService.SMSMsg(mobile, AlidayuTemplateCode.AUDIT_LICENCE_PHOTO_FAILED, null);
//                } else if (AuditRejectReason.SHOP_PHOTO_INVALID.getValue().toString()
//                        .equals(reason)) {
//                    smsService.SMSMsg(mobile, AlidayuTemplateCode.AUDIT_PHOTO_FAILED, null);
//                } else if (AuditRejectReason.BUSINESS_LICENCE_ID_EXIST.getValue().toString()
//                        .equals(reason)) {
//                    smsService
//                            .SMSMsg(mobile, AlidayuTemplateCode.AUDIT_LICENCE_NUMBER_FAILED, null);
//                } else if (AuditRejectReason.BUSINESS_LICENCE_TYPE_INVALID.getValue().toString()
//                        .equals(reason)) {
//                    smsService.SMSMsg(mobile, AlidayuTemplateCode.AUDIT_COMPANY_TYPE_FAILED, null);
//                } else if (AuditRejectReason.BUSINESS_LICENCE_SCOP_INVALID.getValue().toString()
//                        .equals(reason)) {
//                    smsService
//                            .SMSMsg(mobile, AlidayuTemplateCode.AUDIT_COMPANY_MANAGE_FAILED, null);
//                }
//            }
//        }*/
//    }
//
//    @Override
//    public void updateShopType(ShopAuditReqVo reqVo) {
//        ShopPo po = shopRepository.findOne(reqVo.getShopId());
//        po.setShopType(shopTypeService.findOne(reqVo.getShopTypeId()));
//        shopRepository.save(po);
//    }
//
//    @Override
//    public void updateShopDetailStatus(Long shopId, AuditStatus shopDetailAuditStatus) {
//        shopRepository.updateDetailAuditStatus(shopId, shopDetailAuditStatus.getValue());
//        ShopPo shopPo = shopRepository.findOne(shopId);
//        syncShopPoToRedis(shopPo);
//    }
//
//    @Override
//    public void updateShopDetailStatus(ShopDetailPo shopDetailPo, String priceDepotId, String deliveryDepotId, String assartDepotId, String supportPaymentIds, List<Integer> businessTagIds, List<Integer> priceTagIds) {
//        ShopPo shopPo = shopRepository.findOne(shopDetailPo.getShop().getId());
//        if (Objects.equals(shopDetailPo.getAuditStatus(),
//                AuditStatus.NORMAL_AND_NEW_UPDATE_AUDIT_FAILED.getValue())) {
//            return;
//        }
//        shopPo.setName(shopDetailPo.getName());
////        shopPo.setDepot(depotRepository.findOne(priceDepotId));
////        if (StringUtils.isNotBlank(deliveryDepotId)) {
////            shopPo.setDeliveryDepot(depotRepository.findOne(deliveryDepotId));
////        }
////        if (StringUtils.isNotBlank(assartDepotId)) {
////            shopPo.setAssartDepot(depotRepository.findOne(assartDepotId));
////        }
//
//        shopPo.setProvince(shopDetailPo.getProvince());
//        shopPo.setCity(shopDetailPo.getCity());
//        shopPo.setDistrict(shopDetailPo.getDistrict());
//        shopPo.setShopType(shopDetailPo.getShopType());
//        if (isBlank(shopPo.getDeliveryName()) || Objects
//                .equals(shopPo.getDeliveryName(), shopPo.getMobile()) || Objects
//                .equals(shopPo.getDeliveryName(), shopPo.getName())) {
//            shopPo.setCorporateName(shopDetailPo.getCorporateName());
//        }
//        shopPo.setDeliveryName(shopDetailPo.getCorporateName());
//        shopPo.setDeliveryMobile(shopDetailPo.getDeliveryMobile());
//        shopPo.setDeliveryAddress(shopDetailPo.getDeliveryAddress());
//        shopPo.setSupportPaymentIds(supportPaymentIds);
//        if (!Objects.equals(shopDetailPo.getAuditStatus(),
//                AuditStatus.NORMAL_AND_HAS_NEW_UPDATE_WAIT_FOR_AUDIT.getValue())) {
//            shopPo.setDetailAuditStatus(shopDetailPo.getAuditStatus());
//        }
//        shopPo = shopRepository.save(shopPo);
//        syncShopPoToRedis(shopPo);
//    }
//
//    @Override
//    public void updateShopQualificationAuditStatusToWaitForAudit(Long shopId) {
//        shopRepository.updateQualificationAuditStatus(shopId, AuditStatus.WAIT_FOR_AUDIT.getValue());
//        ShopPo shopPo = shopRepository.findOne(shopId);
//        syncShopPoToRedis(shopPo);
//    }
//
//    @Override
//    public void syncShopQualificationToShop(ShopQualificationPo shopQualificationPo) {
//        ShopPo shopPo = shopRepository.findOne(shopQualificationPo.getShop().getId());
//        shopPo.setBusinessLicence(shopQualificationPo.getBusinessLicence());
//        shopPo.setBusinessLicenceName(shopQualificationPo.getBusinessLicenceName());
//        shopPo.setBusinessLicenceId(isNotBlank(shopQualificationPo.getBusinessLicenceId()) ?
//                shopQualificationPo.getBusinessLicenceId() :
//                null);
//        shopPo.setShopPhoto(shopQualificationPo.getShopPhoto());
//        shopPo.setLiquorSellLicence(shopQualificationPo.getLiquorSellLicence());
//        shopPo.setLiquorSellLicenceId(isNotBlank(shopQualificationPo.getLiquorSellLicenceId()) ?
//                shopQualificationPo.getLiquorSellLicenceId() :
//                null);
//        shopPo.setCorporateIdPhoto(shopQualificationPo.getCorporateIdPhoto());
//        shopPo.setCorporateId(isNotBlank(shopQualificationPo.getCorporateId()) ?
//                shopQualificationPo.getCorporateId() :
//                null);
//        shopPo.setQualificationAuditStatus(shopQualificationPo.getAuditStatus());
//        ShopPo savedShopPo = shopRepository.save(shopPo);
//        syncShopPoToRedis(savedShopPo);
//    }
//
//    @Override
//    public void changeDeliveryAddress(ShopChangeDeliveryAddressReqVo reqVo) throws CommonException {
//        UserRo user = userService.findUser(reqVo.getUserId());
//        validateShopOperateAuthority(user.getIsAdmin(), reqVo.getShopId(), user.getShopId());
//
//        List<ShopDetailPo> unNormalDetails = shopDetailRepository
//                .findByShopIdAndAuditStatusInOrderByCreateTimeDesc(reqVo.getShopId(),
//                        newArrayList(AuditStatus.WAIT_FOR_AUDIT.getValue(),
//                                AuditStatus.NORMAL_AND_HAS_NEW_UPDATE_WAIT_FOR_AUDIT.getValue()));
//        if (CollectionUtils.isNotEmpty(unNormalDetails)) {
//            throw DepotnearbyExceptionFactory.Shop.SHOP_DETAIL_IS_AUDITING;
//        }
//        List<ShopDetailPo> normalDetails = shopDetailRepository
//                .findByShopIdAndAuditStatusOrderByCreateTimeDesc(reqVo.getShopId(),
//                        AuditStatus.NORMAL.getValue());
//        ShopDetailPo latestShopDetailPo = CollectionUtils.getFirstOrNull(normalDetails);
//        if (latestShopDetailPo == null) {
//            ShopPo shopPo = findShopPo(user.getShopId());
//            latestShopDetailPo = new ShopPoToShopDetailPo().apply(shopPo);
//        }
//        ShopDetailPo newShopDetailPo = new ShopDetailPo();
//        BeanUtils.copyProperties(latestShopDetailPo, newShopDetailPo);
//        newShopDetailPo.setId(null);
//        newShopDetailPo.setProvince(provinceRepository.findOne(reqVo.getProvinceId()));
//        newShopDetailPo.setCity(cityRepository.findOne(reqVo.getCityId()));
//        newShopDetailPo.setDistrict(districtRepository.findOne(reqVo.getDistrictId()));
//        newShopDetailPo.setDeliveryAddress(reqVo.getDeliveryAddress());
//        newShopDetailPo.setReason(reqVo.getReason());
//        ShopRo shop = findShop(reqVo.getShopId());
//        newShopDetailPo.setDeliveryName(shop.getDeliveryName());
//        newShopDetailPo.setDeliveryMobile(user.getMobile());
//        newShopDetailPo
//                .setAuditStatus(AuditStatus.NORMAL_AND_HAS_NEW_UPDATE_WAIT_FOR_AUDIT.getValue());
//        ShopDetailPo savedShopDetailPo = shopDetailRepository.save(newShopDetailPo);
//        //用户提交修改收货地址请求，会修改当前商铺的审核状态！！！
//        //        changeShopStatusToWaitForAudit(savedShopDetailPo);
//        publishEvent(new ShopDetailUpdateEvent(this, savedShopDetailPo));
//    }
//
//    @Override
//    public void changeShopStatusToWaitForAudit(ShopDetailPo shopDetailPo) {
//        ShopPo shop = shopDetailPo.getShop();
//        if (Objects.equals(shop.getDetailAuditStatus(), AuditStatus.NEED_INFO.getValue()) || Objects
//                .equals(shop.getDetailAuditStatus(), AuditStatus.AUDIT_FAILED.getValue())) {
//            updateShopDetailStatus(shop.getId(), AuditStatus.WAIT_FOR_AUDIT);
//        }
//    }
//
//    @Override
//    public void changeDeliveryName(UserChangeDeliveryNameReqVo reqVo) throws CommonException {
//        UserRo user = userService.findUser(reqVo.getUserId());
//        validateShopOperateAuthority(user.getIsAdmin(), user.getShopId(), user.getShopId());
//        ShopPo shopPo = shopRepository.findOne(user.getShopId());
//        shopPo.setDeliveryName(reqVo.getDeliveryName());
//        shopRepository.updateDeliveryName(user.getShopId(), reqVo.getDeliveryName());
//        syncShopPoToRedis(shopPo);
//    }
//
//    @Override
//    public Page<ShopDetailPo> findShopAuditDataOfWaitForAudit(ShopSearchVo reqVo) {
//        return shopDetailRepository.findAll(new ShopSearchSpecification(reqVo), new PageRequest(reqVo.getPage()-1, reqVo.getPageSize()));
//    }
//
//    @Override
//    public ShopAuditDataMap findShopAuditDataOfWaitForAuditByShopId(Long shopId) {
//        List<ShopDetailPo> shopsOfDetailWaitForAudit = shopDetailRepository
//                .findByShopIdAndAuditStatusInOrderByCreateTimeDesc(shopId,
//                        newArrayList(AuditStatus.WAIT_FOR_AUDIT.getValue(),
//                                AuditStatus.NORMAL_AND_HAS_NEW_UPDATE_WAIT_FOR_AUDIT.getValue()));
//        if (CollectionUtils.isEmpty(shopsOfDetailWaitForAudit)) {
//            shopsOfDetailWaitForAudit = shopDetailRepository.findByShopIdOrderByIdDesc(shopId);
//        }
//
//        List<ShopQualificationPo> shopsOfQualificationWaitForAudit = shopQualificationRepository
//                .findByShopIdAndAuditStatusInOrderByIdDesc(shopId,
//                        newArrayList(AuditStatus.WAIT_FOR_AUDIT.getValue(),
//                                AuditStatus.NORMAL_AND_HAS_NEW_UPDATE_WAIT_FOR_AUDIT.getValue()));
//
//        if (CollectionUtils.isEmpty(shopsOfQualificationWaitForAudit)) {
//            shopsOfQualificationWaitForAudit =
//                    shopQualificationRepository.findByShopIdOrderByIdDesc(shopId);
//        }
//
//        return new ShopAuditDataMap(shopsOfDetailWaitForAudit, shopsOfQualificationWaitForAudit);
//    }
//
//    @Override
//    public void syncAllShopFromMysqlToRedis(Integer pageSize) {
//
//        logger.debug("Sync all shop from mysql to redis begin.");
//        Integer startPage = 0;
//        Page<ShopPo> userPage = null;
//        while (userPage == null || userPage.hasNext()) {
//            logger.debug("Sync page {} of shops...", startPage);
//            Pageable page = new PageRequest(startPage++, pageSize);
//            userPage = shopRepository.findAll(page);
//            List<ShopPo> content = userPage.getContent();
//            for (ShopPo userPo : content) {
//                syncShopPoToRedis(userPo);
//            }
//        }
//        logger.debug("Sync all shop from mysql to redis finish.");
//    }
//
//    @Override
//    public void syncAllShopTypeFromMysqlToRedis(Integer pageSize) {
//        logger.debug("Sync all shop type from mysql to redis begin.");
//        Integer startPage = 0;
//        Page<ShopTypePo> userPage = null;
//        while (userPage == null || userPage.hasNext()) {
//            logger.debug("Sync page {} of shop type...", startPage);
//            Pageable page = new PageRequest(startPage++, pageSize);
//            userPage = shopTypeRepository.findAll(page);
//            List<ShopTypePo> content = userPage.getContent();
//            for (ShopTypePo shopTypePo : content) {
//                syncShopTypePoToRedis(shopTypePo);
//            }
//        }
//        logger.debug("Sync all shop type from mysql to redis finished.");
//    }
//
//
//    /**
//     * 同步ShopPo到redis数据库
//     */
//    private ShopRo syncShopPoToRedis(ShopPo shopPo) {
//        ShopRo shopRo = null;
//        if (shopPo != null) {
//            shopRo = new ShopPoToShopRo().apply(shopPo);
//            shopRedisDao.save(shopRo);
//        }
//        return shopRo;
//    }
//
//    /**
//     * 同步ShopTypePo到redis数据库
//     */
//    private ShopTypeRo syncShopTypePoToRedis(ShopTypePo shopTypePo) {
//
//        ShopTypeRo shopTypeRo = null;
//        if (shopTypePo != null) {
//            shopTypeRo = new ShopTypePoToShopTypeRo().apply(shopTypePo);
//        }
//        return shopTypeRo;
//    }
//
//    private void validateShopOperateAuthority(Boolean isShopAdmin, Long requestShopId, Long shopId)
//            throws CommonException {
//        if (!Objects.equals(requestShopId, shopId) || !isShopAdmin) {
//            throw DepotnearbyExceptionFactory.GLOBAL.NO_AUTHORITY;
//        }
//    }
//
//    @Override
//    public List<String> findDetailAuditRejectReason(Long shopId) {
//
//        List<ShopDetailPo> failedShopDetails = shopDetailRepository
//                .findByShopIdAndAuditStatusOrderByCreateTimeDesc(shopId,
//                        AuditStatus.AUDIT_FAILED.getValue());
//        ShopDetailPo latestFailedShopDetail = CollectionUtils.getFirstOrNull(failedShopDetails);
//        if (latestFailedShopDetail != null) {
//            String rejectReasonIds = latestFailedShopDetail.getRejectReason();
//            if (rejectReasonIds != null) {
//                String[] values = rejectReasonIds.split("\\D");
//                for (String value : values) {
//                    if (NumberUtils.isNumber(value)) {
//                        if (Objects.equals(Integer.valueOf(value),
//                                AuditRejectReason.DETAIL_INVALID.getValue())) {
//                            return newArrayList(AuditRejectReason.DETAIL_INVALID.getDescription());
//                        }
//                    }
//                }
//                //                return AuditRejectReason.parseRejectReasons(values);
//            }
//        }
//        return newArrayList();
//    }
//
//    @Override
//    public List<String> findQualificationAuditRejectReason(Long shopId) {
//
//        List<ShopQualificationPo> failedShopQualification = shopQualificationRepository
//                .findByShopIdAndAuditStatusOrderByIdDesc(shopId, AuditStatus.AUDIT_FAILED.getValue());
//        ShopQualificationPo latestFailedShopQualification =
//                CollectionUtils.getFirstOrNull(failedShopQualification);
//        if (latestFailedShopQualification != null) {
//            String rejectReasonIds = latestFailedShopQualification.getRejectReason();
//            if (rejectReasonIds != null) {
//                String[] values = rejectReasonIds.split("\\D");
//                List<String> rejectReasons = AuditRejectReason.parseRejectReasons(values);
//                rejectReasons.remove(AuditRejectReason.DETAIL_INVALID.getDescription());
//                return rejectReasons;
//            }
//        }
//        return newArrayList();
//    }
//
//    @Override
//    public Boolean updateShopStatus(Long id, CommonStatusEnum shopStatus) {
//
//        ShopPo shopPo = shopRepository.findOne(id);
//        if (shopPo == null) {
//            return false;
//        } else {
//            shopPo.setStatus(shopStatus);
//            shopRepository.updateShopStatusById(id, shopStatus);
//        }
//        syncShopPoToRedis(shopPo);
//        return true;
//    }
//
//    public ShopPo save(ShopPo shopPo) {
//
//        if (shopPo != null) {
//            ShopPo savedShopPo = shopRepository.save(shopPo);
//            syncShopPoToRedis(savedShopPo);
//            return savedShopPo;
//        }
//        return null;
//    }
//
//    public List<SearchShopRespVo> searchShops(SearchShopReqVo vo) {
//        return Lists.transform(shopRepository.searchShops(vo), new ShopPoToSearchShopRespVo());
//    }
//
//    public ShopRo updateShop(ShopEditVo shopEditVo, String loginUsername) {
//
//        logger.info("Update shop[{}] info by[{}]", shopEditVo.getShopId(), loginUsername);
//        ShopPo shopPo = shopRepository.findOne(shopEditVo.getShopId());  //shopId
//        shopPo.setSupportPaymentIds(shopEditVo.getSupportPaymentIds());  //支持付款类型
//        shopPo.setName(shopEditVo.getName());                            //店铺名称
//
//        shopPo.setCorporateName(shopEditVo.getCorporateName());          //法人名字
//        shopPo.setShopType(shopTypeRepository.findOne(shopEditVo.getShopTypeId())); //店铺类型ID
//        shopPo.setMobile(shopEditVo.getMobile());                        //手机号
//        shopPo.setDeliveryMobile(shopEditVo.getMobile());                //收货人电话（手机号）
//        shopPo.setDeliveryName(shopEditVo.getCorporateName());           //收货人名字（法人名字）
//        shopPo.setDeliveryAddress(shopEditVo.getDeliveryAddress());     //收货地址
//       /* shopPo.setTel(shopEditVo.getTel()); */                            //店铺电话
//        shopPo.setProvince(provinceRepository.findOne(shopEditVo.getProvinceId())); //省
//        shopPo.setCity(cityRepository.findOne(shopEditVo.getCityId()));             //市
//        shopPo.setDistrict(districtRepository.findOne(shopEditVo.getDistrictId())); //县
////        shopPo.setDepot(depotRepository.findOne(shopEditVo.getDepotId()));          //价格门店
//        String assartDepotId = shopEditVo.getAssartDepotId();
////        if (StringUtils.isNotBlank(assartDepotId)) {
////            shopPo.setAssartDepot(depotRepository.findOne(assartDepotId));        //开发门店
////        }
////        String deliveryDepotId = shopEditVo.getDeliveryDepotId();
////        if (StringUtils.isNotBlank(deliveryDepotId)) {
////            shopPo.setDeliveryDepot(depotRepository.findOne(deliveryDepotId));    //配送门店
////        }
//       /* shopPo.setShopPhoto(shopEditVo.getShopPhoto()); //门头照片*/
////        shopPo.setPriceTags(priceTagService.findByIds(shopEditVo.getPriceTagIds()));//智选价格
//        /*shopPo.setSaleAreas(saleAreaRepository.findAll(shopEditVo.getSaleAreaIds()));*/ //销售区域
////        shopPo.setBusinessTags(businessTagService.findByIds(shopEditVo.getBusinessTagIds()));//智选分类
//
//       /* shopPo.setBusinessLicence(
//                isNotBlank(shopEditVo.getBusinessLicence()) ? shopEditVo.getBusinessLicence() : null); 营业执照*/
//        shopPo.setBusinessLicenceName(
//                isNotBlank(shopEditVo.getBusinessLicenceName()) ?         //营业执照名称
//                        shopEditVo.getBusinessLicenceName() : null);
//        shopPo.setBusinessLicenceId(isNotBlank(shopEditVo.getBusinessLicenceId()) ?      //营业执照ID
//                shopEditVo.getBusinessLicenceId() : null);
//
//       /* shopPo.setLiquorSellLicence(isNotBlank(shopEditVo.getLiquorSellLicence()) ?
//                shopEditVo.getLiquorSellLicence() :
//                null); 酒类流通许可证*/
//        shopPo
//                .setLiquorSellLicenceId(isNotBlank(shopEditVo.getLiquorSellLicenceId()) ?    //酒类流通许可证ID
//                        shopEditVo.getLiquorSellLicenceId() : null);
//       /* shopPo.setCorporateIdPhoto(
//                isNotBlank(shopEditVo.getCorporateIdPhoto()) ? shopEditVo.getCorporateIdPhoto() : null); 法人身份证*/
//        shopPo.setCorporateId(isNotBlank(shopEditVo.getCorporateId()) ?
//                shopEditVo.getCorporateId() :
//                null);    //法人身份证ID
//        shopPo.setDetailAuditStatus(shopEditVo.getDetailAuditStatus());        //详情审核状态
//        shopPo.setQualificationAuditStatus(shopEditVo.getDetailAuditStatus()); //资质审核详情
////        DepotEmployeePo employeePo =
////                depotEmployeeService.getDepotEmployeeById(shopEditVo.getInviterCode());
////        if (employeePo != null) {
////            shopPo.setInviterCode(employeePo.getId());
////        }
////        if(shopPo.getShopLevel() == ShopLevel.VIP_20) {
////            shopPo.setInviterCode(null);
////        }
//        shopPo.setDisabledPaymentIds(shopEditVo.getDisabledPaymentIds()); //不支持支付类型
//
//        ShopPo savedShopPo = shopRepository.save(shopPo);
//        return syncShopPoToRedis(savedShopPo);
//    }
//
//    public List<ShopPo> findShopByDepotId(String depotId) {
//        if(StringUtils.isBlank(depotId)){
//            return newArrayList();
//        }
//        return null;//shopRepository.findByDepotId(depotId);
//    }
//
//
//    public List<ShopPo> findByAssartDepotId(String depotId) {
//        return null;//shopRepository.findByAssartDepotId(depotId);
//    }
//
//    public List<ShopPo> findByShopIds(Set<Long> shopIds) {
//        return shopRepository.findByIdIn(shopIds);
//    }
//
//    /**
//     * 物理删除，慎用
//     *
//     * @throws CommonException
//     */
//    @Deprecated
//    public void destroyShopAndItUsers(Long shopId, String handler)
//            throws CommonException {
//
//        logger.info("Destroy shop[{}] and it's users by admin[{}]", shopId, handler);
//        ShopRo shopRo = findShop(shopId);
//        ShopPo shopPo = findShopPo(shopId);
//        if (shopPo != null) {
//            Set<UserPo> users = shopPo.getUsers();
//            if (CollectionUtils.isNotEmpty(users)) {
//                for (UserPo user : users) {
//                    userService.destroyUserById(user.getId(), handler);
//                }
//            }
//            shopDetailRepository
//                    .updateAuditStatusByShopId(Long.valueOf(shopRo.getId()), AuditStatus.DATA_EXPIRED.getValue());
//            shopQualificationRepository
//                    .updateAuditStatusByShopId(Long.valueOf(shopRo.getId()), AuditStatus.DATA_EXPIRED.getValue());
//            shopPo.setMobile("0" + shopPo.getMobile().substring(1));
//            shopPo.setStatus(CommonStatusEnum.DISABLE);
//            shopRepository.save(shopPo);
//        }
//        if (shopRo != null) {
//            shopRedisDao.delete(shopRo);
//        }
//    }
//
//    private void dispatchInviteVoucher(Long shopId) {
//
//        ShopPo shopPo = findShopPo(shopId);
//        if (Objects.equals(shopPo.getDetailAuditStatus(), AuditStatus.NORMAL.getValue()) && Objects
//                .equals(shopPo.getQualificationAuditStatus(), AuditStatus.NORMAL.getValue())) {
//            //publishEvent(new VoucherDispatcherEvent(this, userService.findAdminByShopId(shopId)));
//        }
//    }
//
//    @Override
//    public List<SearchShopRespVo> findshopListByUserlist(List<UserPo> users) {
//        List<ShopPo> shops = new ArrayList<>();
//        if (CollectionUtils.isEmpty(users)) {
//            return Collections.emptyList();
//        }
//        for (UserPo user : users) {
//            try {
//                ShopRo ro = findShopByUserId(user.getId());
//                if (ro == null) {
//                    //                    System.out.println("null shop");
//                    continue;
//                } else {
//                    ShopPo shop = shopRepository.findOne(Long.valueOf(ro.getId()));
//                    shops.add(shop);
//                }
//            } catch (CommonException e) {
//                e.printStackTrace();
//            }
//        }
//        logger.debug("shop size:" + shops.size());
//        return Lists.transform(shops, new ShopPoToSearchShopRespVo());
//    }
//
//    public Integer enableShopByIds(String[] ids) {
//        Integer count = 0;
//        for (String id : ids) {
//            updateShopStatus(Long.valueOf(id), CommonStatusEnum.ENABLE);
//            count++;
//        }
//        return count;
//    }
//
//    public Integer disableShopByIds(String[] ids) {
//        Integer count = 0;
//        for (String id : ids) {
//            updateShopStatus(Long.valueOf(id), CommonStatusEnum.DISABLE);
//            count++;
//        }
//        return count;
//    }
//
//    /**
//     * 查找商户，含有手机号的查找对应用户的对应商户
//     */
//    public List<SearchShopRespVo> findShopAndUserShop(SearchShopReqVo searchShopReqVo)
//            throws CommonException {
//        List<SearchShopRespVo> list = new ArrayList<SearchShopRespVo>();
//        List<SearchShopRespVo> listUser = new ArrayList<SearchShopRespVo>();
//        List<UserPo> users = new ArrayList<UserPo>();
//        List<SearchShopRespVo> shops = this.searchShops(searchShopReqVo);
//        if (ArrayUtils.isNotEmpty(shops)) {
//            list.addAll(shops);
//        }
//        String mobile = null;
//        try {
//            mobile = searchShopReqVo.getMobile();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (mobile != null && mobile.length() != 0) {
//            SearchUserReqVo vo = new SearchUserReqVo();
//            vo.setMobile(mobile);
//            users = userService.searchUsers(vo);
//            if (users.size() != 0) {
//                List<SearchShopRespVo> shoplist = this.findshopListByUserlist(users);
//                if (shoplist.size() != 0) {
//                    for (SearchShopRespVo v : shoplist) {
//                        for (SearchShopRespVo o : shops) {
//                            if (!v.getId().equals(o.getId())) {
//                                list.add(v);
//                            }
//                        }
//
//                    }
//                }
//            }
//        }
//        if (users.size() != 0 && list.size() != 0) {
//            for (UserPo userPo : users) {
//                for (SearchShopRespVo voShop : list) {
//                    if (userPo.getShop().getId().equals(voShop.getId())) {
//                        voShop.setUserName(userPo.getName());
//                        voShop.setUserId(userPo.getId());
//                        listUser.add(voShop);
//                    }
//                }
//            }
//        }
//        if (listUser.size() != 0) {
//            return listUser;
//        }
//        return list;
//    }
//
//    /**
//     * 分页查找商户
//     */
//    public List<ShopPo> searchPageShops(ShopQueryReqVo reqVo, PageControl pc) {
//        String mobile = reqVo.getMobile();
//        if (StringUtils.isNotBlank(mobile)) {
//            UserRo ro = userService.findUserByMobile(mobile);
//            if (ro != null) {
//                reqVo.setId(ro.getShopId());
//                reqVo.setMobile(null);
//            }
//        }
//
//        PageRequest pageable = new PageRequest(Math.max(0, pc.getCurrentPage() - 1), pc.getPageSize(),
//                Sort.Direction.DESC, "createTime");
//        Page<ShopPo> page = shopRepository.searchPageShops(reqVo, pageable);
//        pc.setCount((int) page.getTotalElements());
//        reqVo.setMobile(mobile);
//        return page.getContent();
//    }
//
//    /**
//     * 判断businessLicenceId在数据库中是否已经存在
//     */
//    public Boolean isBusinessLicenceIdExist(String businessLicenceId, Long shopId) {
//        if (isBlank(businessLicenceId)) {
//            return true;
//        }
//        return CollectionUtils.isNotEmpty(
//                shopRepository.findShopIdBybusinessLicenceId(businessLicenceId, getValue(shopId)));
//    }
//
//    public List<ShopExportVo> findExportShops(String startTime, String endTime)
//            throws CommonException {
//        List<ShopExportVo> result = new ArrayList<>();
//        Timestamp startTimestamp = new Timestamp(1L);
//        if (StringUtils.isNotBlank(startTime)) {
//            startTimestamp =
//                    new Timestamp(DateUtils.getDateFromString(startTime, "yyyy-MM-dd").getTime());
//        }
//        Timestamp endTimestamp = new Timestamp(System.currentTimeMillis());
//        if (StringUtils.isNotBlank(endTime)) {
//            endTimestamp = new Timestamp(
//                    DateUtils.getDateFromString(endTime, "yyyy-MM-dd").getTime() + TimeUnit.DAYS
//                            .toMillis(1));
//        }
//        List<ShopPo> shops = shopRepository.findByStartTimeAndEndTime(startTimestamp, endTimestamp);
////        if (CollectionUtils.isNotEmpty(shops)) {
////            List<DepotEmployeePo> employees = depotEmployeeService.findAllEmployees();
////            Map<String, DepotEmployeePo> employeeIdToPo = newHashMap();
////            for (DepotEmployeePo employee : employees) {
////                employeeIdToPo.put(employee.getId(), employee);
////            }
////            for (ShopPo po : shops) {
////                if (po.getParent() != null) {
////                    continue;
////                }
////                ShopExportVo vo = new ShopExportVo();
////                vo.setMobile(po.getMobile());
////                vo.setShopName(po.getName());
////                vo.setCreateTime(po.getCreateTime());
////                Set<UserPo> usersOfShop = po.getUsers();
////                if (CollectionUtils.isNotEmpty(usersOfShop)) {
////                    for (UserPo userPo : usersOfShop) {
////                        if (userPo != null) {
////                            UserRo userRo = userService.findUser(userPo.getId());
////                            if (userRo != null && userRo.getIsAdmin()) {
////                                vo.setLatestLoginTime(userRo.getLastLoginTime());
////                                break;
////                            }
////                        }
////                    }
////                }
////                if (po.getShopType() != null) {
////                    vo.setShopType(po.getShopType().getName());
////                }
////                Integer qualificationAuditStatus = po.getQualificationAuditStatus();
////                vo.setAuditStaus(qualificationAuditStatus == null ?
////                        null :
////                        AuditStatus.valueOf(qualificationAuditStatus).getDescription());
////                if (po.getProvince() != null) {
////                    vo.setProvinceName(po.getProvince().getName());
////                }
////                if (StringUtils.isNotBlank(po.getInviterCode())) {
////                    DepotEmployeePo employeePo = employeeIdToPo.get(po.getInviterCode().trim());
////                    if (employeePo != null) {
////                        vo.setInvaterCode(employeePo.getId());
////                        vo.setInvaterName(employeePo.getName());
////                    }
////                }
////                result.add(vo);
////            }
////        }
//        return result;
//    }
//
//    /**
//     * 商户数据导入（新增加字段）
//     * @param startTime
//     * @param endTime
//     * @return
//     * @throws CommonException
//     */
//    public List<ShopsInfoExportVo> findExportShopsInfo(String startTime, String endTime) throws CommonException{
//        Timestamp startTimestamp = new Timestamp(1L);
//        if(StringUtils.isNotBlank(startTime)){
//            startTimestamp = new Timestamp(DateUtils.getDateFromString(startTime, "yyyy-MM-dd").getTime());
//        }
//        Timestamp endTimestamp = new Timestamp(System.currentTimeMillis());
//        if(StringUtils.isNotBlank(endTime)){
//            endTimestamp = new Timestamp(
//                    DateUtils.getDateFromString(endTime, "yyyy-MM-dd").getTime() + TimeUnit.DAYS.toMillis(1));
//        }
//        List<ShopsInfoExportVo> shopsResult = shopRepository.findShopsInfoBetween(startTimestamp , endTimestamp);
//        return shopsResult;
//    }
//
//
//    public void changeMobile(Long shopId, String mobile) {
//        ShopPo shopPo = findShopPo(shopId);
//        shopPo.setMobile(mobile);
//        shopPo.setDeliveryMobile(mobile);
//        save(shopPo);
//    }
//
//    public List<ShopTypePo> getAllAvialShopTypes() {
//        return shopTypeRepository.findAllByStatus(ShopTypeStatus.NORMAL.getValue());
//    }
//
//    public List<ShopPo> getShopPosByShopTypeId(Long shopTypeId) {
//        return shopRepository.findByShopTypeId(shopTypeId);
//    }
//
//    public List<ShopPo> getShopPosByShopTypeIdAndStartTimeAndEndTime(Long shopTypeId,
//                                                                     Timestamp startTime, Timestamp endTime) {
//        return shopRepository
//                .findByShopTypeIdAndStartTimeAndEndTime(shopTypeId, startTime, endTime);
//    }
//
//    public Map<String, ShopAuditStatisticResultVo> findShopAuditStatisticBy(Timestamp endTime,
//                                                                            Map<String, Object> searchParams) {
//        Map<String, ShopAuditStatisticResultVo> shopAuditStatisticResultVoMap = Maps.newHashMap();
//        List<ShopAuditStatisticResultVo> shopAuditStatisticResultVos =
//                shopRepository.findShopAuditStatisticBy(endTime, searchParams);
//        for (ShopAuditStatisticResultVo shopAuditStatisticResultVo : shopAuditStatisticResultVos) {
//            shopAuditStatisticResultVoMap.put(String
//                    .format("%s%s", shopAuditStatisticResultVo.getProvinceId(),
//                            shopAuditStatisticResultVo.getDepotName()), shopAuditStatisticResultVo);
//        }
//
//        return shopAuditStatisticResultVoMap;
//    }
//
//    public List<ShopPo> findshopByInviterCode(String inviterCode) {
//        List<ShopPo> shops = shopRepository.findshopByInviterCode(inviterCode);
//        return shops;
//    }
//
//    public Map<String, DailyStatNewShopResultVo> getDailyNewShopsCountBy(Date date) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
//        String startTime =
//                sdf.format(org.apache.commons.lang3.time.DateUtils.truncate(date, Calendar.DATE));
//        String endTime = sdf.format(org.apache.commons.lang3.time.DateUtils
//                .addMilliseconds(org.apache.commons.lang3.time.DateUtils.ceiling(date, Calendar.DATE),
//                        -1));
//        Map<String, DailyStatNewShopResultVo> dailyStatNewShopResultVoMap = Maps.newHashMap();
//        List<DailyStatNewShopResultVo> dailyStatNewShopResultVos =
//                shopRepository.findDailyNewShopCountBy(startTime, endTime);
//        for (DailyStatNewShopResultVo dailyStatNewShopResultVo : dailyStatNewShopResultVos) {
//            dailyStatNewShopResultVoMap.put(String
//                    .format("%s%s%s", dailyStatNewShopResultVo.getdDay(),
//                            dailyStatNewShopResultVo.getProvinceId(),
//                            dailyStatNewShopResultVo.getShopTypeId()), dailyStatNewShopResultVo);
//        }
//        return dailyStatNewShopResultVoMap;
//    }
//
//    public List<ShopPo> findShopByMobile(String mobile) {
//        return shopRepository.findByMobileOrderByIdDesc(mobile);
//    }
//
//    public List<ShopPo> findShopPoByMobileAndHaveNotShopId(String mobile, Long shopId) {
//        if (shopId != null) {
//            return shopRepository.findShopPoByMobileAndHaveNotShopId(mobile, shopId);
//        } else {
//            return shopRepository.findByMobileOrderByIdDesc(mobile);
//        }
//
//    }
//
//    public void changeShopPaymentPassword(ChangePaymentPwdReqVo reqVo) throws CommonException {
//        Long userId = reqVo.getUserId();
//        UserRo userRo = userService.findUser(userId);
//        ShopRo shopRo = findShopByUserId(userId);
//        if (userRo == null) {
//            throw new CommonException("未找到指定的用户", ExceptionCode.User.USER_NOT_EXIST);
//        }
//
//        if (!userRo.getIsAdmin()) {
//            throw new CommonException("帐号修改支付密码权限不足(只有管理员能修改支付密码)",
//                    ExceptionCode.Account.CHANGE_PAYMENT_PASSWORD_PERMISSION_DENIED);
//        }
//
//        ShopPo shopPo = shopRepository.findOne(Long.valueOf(shopRo.getId()));
//        if (shopPo == null) {
//            throw new CommonException("未找到指定商户", ExceptionCode.Shop.SHOP_NOT_EXIST);
//        }
//
//        if (!StringUtils
//                .equals(shopPo.getPaymentPassword(), StringUtils.trim(reqVo.getPaymentPassword()))) {
//            throw new CommonException("原支付密码输入错误",
//                    ExceptionCode.Account.PAYMENT_PASSWORD_NOT_CORRECT);
//        }
//
//        if (!StringUtils.equals(StringUtils.trim(reqVo.getNewPassword()),
//                StringUtils.trim(reqVo.getRepeatPassword()))) {
//            throw new CommonException("更改支付密码两次密码输入不一致",
//                    ExceptionCode.Account.CONFIRM_PAYMENT_PASSWORD_FAILD);
//        }
//
//        shopPo.setPaymentPassword(StringUtils.trim(reqVo.getNewPassword()));
//        save(shopPo);
//        syncShopPoToRedis(shopPo);
//    }
//
//    public boolean validatePaymentPassword(Long userId, String md5Password) throws CommonException {
//        UserRo userRo = userService.findUser(userId);
//        Long shopId = userRo.getShopId();
//        ShopRo shopRo = shopRedisDao.findOne(String.valueOf(shopId));
//        if (shopId == null || shopRo == null) {
//            throw new CommonException("用户未绑定商户", ExceptionCode.Shop.SHOP_NOT_EXIST);
//        }
//        if (shopRo.getPaymentPassword() == null) {
//            throw new CommonException("该用户绑定的商户未设置支付密码",
//                    ExceptionCode.Account.SHOP_PAYMENT_PASSWORD_NOT_EXIST);
//        }
//        return StringUtils.equals(shopRo.getPaymentPassword(), StringUtils.trim(md5Password));
//    }
//
//    public boolean saveZsgfLoanApply(ZsgfLoanApplyPo zsgfLoanApplyPo) {
////        try {
////            zsgfLoanApplyRepository.save(zsgfLoanApplyPo);
////        } catch (Exception e) {
////            logger.error("保存嘴上功夫抵货券申请失败", e);
////            return false;
////        }
//        return true;
//    }
//
//    public ZsgfLoanApplyPo findZsgfLoanApplyByUserId(Long userId) throws CommonException {
////        ShopRo shopRo = findShopByUserId(userId);
////        return findZsgfLoanApplyByShopId(shopRo.getId());
//        return null;
//    }
//
//    public ZsgfLoanApplyPo findZsgfLoanApplyByShopId(Long shopId) {
////        return zsgfLoanApplyRepository.findByShopId(shopId);
//        return null;
//    }
//
//    public boolean fillZsgfLoanApplyImgInfo(Long userId, String imgJsonString)
//            throws CommonException {
//        ZsgfLoanApplyPo zsgfLoanApplyPo = findZsgfLoanApplyByUserId(userId);
//        zsgfLoanApplyPo.setShopCertificateImgs(imgJsonString);
//        zsgfLoanApplyPo.setApplyStatus(ZsgfApplyStatus.WAIT_FOR_AUDIT);
//        return this.saveZsgfLoanApply(zsgfLoanApplyPo);
//    }
//
//    public void createPaymentPassword(Long userId, String password) throws CommonException {
//        ShopRo shopRo = findShopByUserId(userId);
//        ShopPo shopPo = shopRepository.findOne(Long.valueOf(shopRo.getId()));
//        shopPo.setPaymentPassword(password);
//        save(shopPo);
//        syncShopPoToRedis(shopPo);
//    }
//
//  /*  public List<ZsgfLoanApplyPo> findZsgfApplyByApplyStatus(ZsgfApplyStatus applyStatus,
//        PageControl pc) {
//        if (applyStatus != null) {
//            return zsgfLoanApplyRepository
//                .findZsgfLoanApplyByApplyStatus(applyStatus, pageControl2PageRequest(pc));
//        }
//        return zsgfLoanApplyRepository.findAll(pageControl2PageRequest(pc)).getContent();
//    }*/
//
//    public List<ZsgfLoanApplyPo> searchZsgfLoan(ZsgfLoanQueryReqVo reqVo, PageControl pc) {
////        Page<ZsgfLoanApplyPo> page = zsgfLoanApplyRepository.searchZsgfLoans(reqVo, pageControl2PageRequest(pc));
////        pc.setCount((int) page.getTotalElements());
////        return page.getContent();
//        return null;
//    }
//
//    public ZsgfLoanApplyPo findZsgfLoanApplyById(Long id) {
////        return zsgfLoanApplyRepository.findOne(id);
//        return null;
//    }
//
//    public ZsgfLoanApplyPo saveZsgfLoanApplyPo(ZsgfLoanApplyPo zsgfLoanApplyPo) {
////        return zsgfLoanApplyRepository.save(zsgfLoanApplyPo);
//        return null;
//    }
//
//    public boolean modifyApplyStatus(Long id, ZsgfApplyStatus applyStatus, Integer quota)
//            throws CommonException {
////        try {
////            ZsgfLoanApplyPo zsgfLoanApplyPo = zsgfLoanApplyRepository.findOne(id);
////            zsgfLoanApplyPo.setApplyStatus(applyStatus);
////            zsgfLoanApplyPo.setQuota(quota != null ? quota * 100 : null);
////            zsgfLoanApplyRepository.save(zsgfLoanApplyPo);
////            Long currentShopId = zsgfLoanApplyPo.getShop().getId();
////            List<UserPo> shopAdminUsers = userService.findAdminUsersByShopId(currentShopId, true);
////            sendLoanShortMessage(currentShopId, shopAdminUsers, LoanApplyType.ZSGF);
////            sendLoanPushMessage(currentShopId, shopAdminUsers, LoanApplyType.ZSGF);
////            return true;
////        } catch (Exception e) {
////            logger.error("修改嘴上功夫审核信息失败", e);
////            return false;
////        }
//        return true;
//    }
//
//    public boolean updateAuditComment(Long id, String description) throws CommonException {
////        try {
////            ZsgfLoanApplyPo po = zsgfLoanApplyRepository.findOne(id);
////            po.setAuditComment(description);
////            zsgfLoanApplyRepository.save(po);
////            return true;
////        } catch (Exception e) {
////            logger.error("修改嘴上功夫审核备注失败", e);
////            return false;
////        }
//        return true;
//    }
//
//    public void sendLoanNotice(Long shopId, LoanApplyType loanApplyType)
//            throws CommonException {
//        List<UserPo> users = userService.findAdminUsersByShopId(shopId, true);
//        sendLoanShortMessage(shopId, users, loanApplyType);
//    }
//
//    private void sendLoanShortMessage(Long shopId, List<UserPo> users, LoanApplyType loanApplyType)
//            throws CommonException {
////        AlidayuTemplateCode alidayuTemplateCode;
////        JSONObject templateParams = new JSONObject();
////        if (loanApplyType == LoanApplyType.ZSGF) {
////            logger.info("准备发送[{}]审核结果短信消息到商户ID[{}]的管理员用户", loanApplyType.ZSGF.getDescroption(), shopId);
////            ZsgfLoanApplyPo zsgfLoanApplyPo = findZsgfLoanApplyByShopId(shopId);
////            if (zsgfLoanApplyPo.getApplyStatus() == ZsgfApplyStatus.APPLY_SUCCESS) {
////                // 嘴上功夫券抵货款申请成功
////                alidayuTemplateCode = AlidayuTemplateCode.ZSGF_LOAN_APPLY_SUCCESS;
////            } else if (zsgfLoanApplyPo.getApplyStatus() == ZsgfApplyStatus.AUDIT_FAILED) {
////                // 嘴上功夫券抵货款申请失败
////                alidayuTemplateCode = AlidayuTemplateCode.ZSGF_LOAN_APPLY_FAILED;
////            } else {
////                throw new CommonException("未知的嘴上功夫券抵货款审核状态");
////            }
////        } else if (loanApplyType == LoanApplyType.XIMU) {
////            logger.info("准备发送[{}]审核结果短信消息到商户ID[{}]的管理员用户", loanApplyType.XIMU.getDescroption(), shopId);
////            XiMuPaymentApplyPo xiMuPaymentApplyPo = xiMuPaymentApplyRepository.findByShopId(shopId);
////            PaymentApplyStatus paymentApplyStatus = xiMuPaymentApplyPo.getPaymentApplyStatus();
////            if (paymentApplyStatus == PaymentApplyStatus.APPLY_SUCCESS) {
////                // 徙木金融酒先拿走申请成功
////                alidayuTemplateCode = AlidayuTemplateCode.XIMU_LOAN_APPLY_SUCCESS;
////            } else if (paymentApplyStatus == PaymentApplyStatus.APPLY_FAILED) {
////                // 徙木金融酒先拿走申请失败
////                alidayuTemplateCode = AlidayuTemplateCode.XIMU_LOAN_APPLY_FAILED;
////            } else if (paymentApplyStatus == PaymentApplyStatus.APPLY_PENDING) {
////                // 徙木金融酒先拿走部分申请资料审核不通过
////                alidayuTemplateCode = AlidayuTemplateCode.XIMU_LOAN_APPLY_PENDING;
////                String failReason = xiMuPaymentApplyPo.getFailApplyField();
////                XimuLoanApplyFailInfoVo ximuLoanApplyFailInfoVo =
////                        JsonUtil.json2Obj(failReason, XimuLoanApplyFailInfoVo.class);
////                templateParams.put("service", "隔壁仓库酒先拿走");
////                templateParams.put("content", ximuLoanApplyFailInfoVo.getFieldConcatString());
////            } else {
////                throw new CommonException("未知的徙木金融酒先拿走审核状态");
////            }
////        } else {
////            throw new CommonException("未知的贷款服务类型", ExceptionCode.Account.UNKNOWN_LOAN_TYPE);
////        }
////
////        for (UserPo userPo : users) {
////            smsService.SMSMsg(userPo.getMobile(), alidayuTemplateCode, templateParams);
////        }
//        logger.info("发送审核结果完成");
//    }
//
//    private void sendLoanPushMessage(Long shopId, List<UserPo> users, LoanApplyType loanApplyType)
//            throws CommonException {
////        String msgTitle = "您的记我账上服务有新的状态";
////        String msgContent = "";
////        if (loanApplyType == LoanApplyType.ZSGF) {
////            logger.info("准备发送[{}]审核结果推送消息到商户ID[{}]的管理员用户", loanApplyType.ZSGF.getDescroption(), shopId);
////            ZsgfLoanApplyPo zsgfLoanApplyPo = findZsgfLoanApplyByShopId(shopId);
////            if (zsgfLoanApplyPo.getApplyStatus() == ZsgfApplyStatus.APPLY_SUCCESS) {
////                // 嘴上功夫券抵货款申请成功
////                msgContent = PushMessageContent.ZSGF_APPLY_SUCCESS;
////            } else if (zsgfLoanApplyPo.getApplyStatus() == ZsgfApplyStatus.AUDIT_FAILED) {
////                // 嘴上功夫券抵货款申请失败
////                msgContent = PushMessageContent.ZSGF_APPLY_FAILED;
////            } else {
////                throw new CommonException("未知的嘴上功夫券抵货款审核状态");
////            }
////        } else if (loanApplyType == LoanApplyType.XIMU) {
////            logger.info("准备发送[{}]审核结果推送消息到商户ID[{}]的管理员用户", loanApplyType.XIMU.getDescroption(), shopId);
////            XiMuPaymentApplyPo xiMuPaymentApplyPo = xiMuPaymentApplyRepository.findByShopId(shopId);
////            PaymentApplyStatus paymentApplyStatus = xiMuPaymentApplyPo.getPaymentApplyStatus();
////            if (paymentApplyStatus == PaymentApplyStatus.APPLY_SUCCESS) {
////                // 徙木金融酒先拿走申请成功
////                msgContent = PushMessageContent.XIMU_APPLY_SUCCESS;
////            } else if (paymentApplyStatus == PaymentApplyStatus.APPLY_FAILED) {
////                // 徙木金融酒先拿走申请失败
////                msgContent = PushMessageContent.XIMU_APPLY_FAILED;
////            } else if (paymentApplyStatus == PaymentApplyStatus.APPLY_PENDING) {
////                // 徙木金融酒先拿走部分申请资料审核不通过
////                String failReason = xiMuPaymentApplyPo.getFailApplyField();
////                XimuLoanApplyFailInfoVo ximuLoanApplyFailInfoVo =
////                        JsonUtil.json2Obj(failReason, XimuLoanApplyFailInfoVo.class);
////                JSONObject msgParams = new JSONObject();
////                msgParams.put("service", "隔壁仓库酒先拿走");
////                msgParams.put("content", ximuLoanApplyFailInfoVo.getFieldConcatString());
////                PushMessageContent.formatContentWithParams(PushMessageContent.XIMU_APPLY_PENDING, msgParams);
////            } else {
////                throw new CommonException("未知的徙木金融酒先拿走审核状态");
////            }
////        } else {
////            throw new CommonException("未知的贷款服务类型", ExceptionCode.Account.UNKNOWN_LOAN_TYPE);
////        }
////
////        for (UserPo user : users) {
////            noticeService.sendMsgToUser(user.getId(), msgTitle, msgContent, null);
////        }
//        logger.info("发送推送消息成功");
//    }
//
////    public CustAccrPo getShopCreditInfo(Long shopId) {
////        return xiMuCustAccrRepository.findByMemberId(shopId);
////        return null;
////    }
//
//
//    /**
//     * 导入用户
//     *
//     * @param userVos
//     * @return
//     */
//    public int saveImportUser(List<UserVo> userVos) {
//        Integer num = 0;
//        List<UserPo> userPos = newArrayList();
//        List<ShopPo> shopPos = newArrayList();
//        for (UserVo user : userVos) {
//            String mobile = user.getMobile();
//            String depotId = user.getDepotId();
//            if (mobile != null && mobile.length() == 11) {
//                UserRo userRo = userService.findUserByMobile(mobile);
//                if (userRo == null) {
//                    List<ShopPo> existShops = this.findShopByMobile(mobile);
//                    if (CollectionUtils.isEmpty(existShops)) {
//                        UserPo userPo = new UserPo();
//                        //userPo.setId(idService.nextId().getNextId(IdType.USER));
//                        //userPo.setStatus(UserStatus.NORMAL.getValue());
//                        userPo.setIsAdmin(true);
//                        userPo.setName(user.getName());
//                        userPo.setMobile(mobile);
//                        userPo.setOriginalPassword(mobile.substring(mobile.length() - 6, mobile.length()));
//                        userPo.setPassword(StringTool.encodedByMD5(userPo.getOriginalPassword()));
//                        ShopPo newShop = new ShopPo();
//                        //newShop.setId(idPool.getNextId(IdType.SHOP));
//                        ShopPo updateShop = shopRepository.save(newShop);
//                        updateShop.setName(user.getShopName());
//                        updateShop.setMobile(mobile);
//                        updateShop.setShopLevel(ShopLevel.VIP_15);
////                        DepotPo depotPo = null;
////                        if (StringUtils.isNotBlank(depotId)) {
////                            depotPo = depotRepository.findOne(depotId);
////                        }
////                        if (depotPo != null) {
////                            updateShop.setDepot(depotPo);
////                            updateShop.setAssartDepot(depotPo);
////                            updateShop.setDeliveryDepot(depotPo);
////                        } else {
////                            DepotPo depotNearBy = depotRepository.findOne(DepotId.DEPOTNEARBY.getId());
////                            updateShop.setDepot(depotNearBy);
////                            updateShop.setAssartDepot(depotNearBy);
////                            updateShop.setDeliveryDepot(depotNearBy);
////                        }
//                        shopPos.add(updateShop);
//                        userPo.setShop(updateShop);
//                        userPos.add(userPo);
//                        num += 1;
//                    }
//                }
//            }
//        }
//        List<ShopPo> shopPos2 = shopRepository.save(shopPos);
//        List<UserPo> userPos2 = userRepository.save(userPos);
//        for (ShopPo shop : shopPos2) {
//            shopRedisDao.save(new ShopPoToShopRo().apply(shop));
//        }
//        for (UserPo user : userPos2) {
//            userRedisDao.save(new UserPoToUserRo().apply(user));
//        }
//        return num;
//    }
//
//
//    /**
//     * 导入20倍会员信息
//     *
//     * @param user20VIPVos
//     * @param failDetails
//     * @return
//     */
//    public int saveImportUser20VIP(List<User20VIPVo> user20VIPVos, List<FailDetail> failDetails) {
//        return 0;
//    }
//
//    /**
//     * 增加禁用支付方式
//     */
//    @SuppressWarnings("Duplicates")
//    public void addDisabledPaymentType(Long shopId, int paymentType) {
//        ShopPo shop = shopRepository.findOne(shopId);
//
//        if (shop == null) {
//            return;
//        }
//        Set<Integer> disabledPaymentTypes = newHashSet();
//        if (StringUtils.isNotBlank(shop.getDisabledPaymentIds())) {
//            String[] arr = shop.getSupportPaymentIds().split(",");
//            for (String s : arr) {
//                if (StringUtils.isNumeric(s)) {
//                    disabledPaymentTypes.add(Integer.valueOf(s));
//                }
//            }
//        }
//        disabledPaymentTypes.add(paymentType);
//        shop.setDisabledPaymentIds(StringUtils.join(disabledPaymentTypes, ","));
//        shopRepository.save(shop);
//    }
//
//
//    /**
//     * 删除禁用支付方式
//     */
//    public void removeDisabledPaymentType(Long shopId, int paymentType){
//        ShopPo shop = shopRepository.findOne(shopId);
//
//        if (shop == null) {
//            return;
//        }
//        Set<Integer> disabledPaymentTypes = newHashSet();
//        if (StringUtils.isNotBlank(shop.getDisabledPaymentIds())) {
//            String[] arr = shop.getSupportPaymentIds().split(",");
//            for (String s : arr) {
//                if (StringUtils.isNumeric(s)) {
//                    disabledPaymentTypes.add(Integer.valueOf(s));
//                }
//            }
//            disabledPaymentTypes.remove(paymentType);
//            shop.setDisabledPaymentIds(StringUtils.join(disabledPaymentTypes, ","));
//            shopRepository.save(shop);
//        }
//    }
//
//    /**
//     * 检查某种支付方式是否被禁用
//     * @return
//     */
//    public boolean isDisabledPaymentType(Long shopId, int paymentType) {
//        ShopPo shop = shopRepository.findOne(shopId);
//        if (shop != null && StringUtils.isNotBlank(shop.getDisabledPaymentIds())) {
//            String[] arr = shop.getSupportPaymentIds().split(",");
//            if (ArrayUtils.contains(arr, "" + paymentType + "")) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    /**
//     * 查询depotid对应旗下的商户
//     * @return
//     */
//    public List<ShopPo> findShops(){
//        //return shopRepository.findByDepotId(ManageServlet.getDepotIdOfCurrentSelected());
//        return null;
//    }
//
//    /**
//     * 合伙人查看发展商户
//     * @author hcs
//     * 16/9/29
//     */
//
//    public List<ShopPo> findShopFodepotId(ShopQueryReqVo reqVo, PageControl pc){
////        Admin admin = ManageServlet.getAdmin();
////        if(admin.getAdminLevel() == AdminLevel.ADMIN_DEPOT){
//////    		return shopRepository.findByDepotId(ManageServlet.getDepotIdOfCurrentSelected());
////            PageRequest pageable = new PageRequest(Math.max(0, pc.getCurrentPage() - 1), pc.getPageSize(),
////                    Sort.Direction.DESC, "createTime");
////            Page<ShopPo> page = this.searchPageShopsByDepotId(reqVo, pageable);
////            pc.setCount((int) page.getTotalElements());
////            return page.getContent();
////
////        }else{
////            logger.debug("不属于门店用户");
////        }
//        return null;
//    }
//
//    /**
//     * 分页查询合伙人旗下商户
//     */
//    public Page<ShopPo> searchPageShopsByDepotId(final ShopQueryReqVo vo, Pageable pageable) {
//        return shopRepository.findAll(new Specification<ShopPo>() {
//            @Override public Predicate toPredicate(Root<ShopPo> root, CriteriaQuery<?> query,
//                                                   CriteriaBuilder cb) {
//                List<Predicate> predicates = new ArrayList<>();
////                predicates.add(cb.equal(root.get("depot"),ManageServlet.getDepotOfCurrentSelected()));
//                // 将所有条件用 and 联合起来
//                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
//
//            }
//        }, pageable);
//
//    }
//
//    /**
//     * 查看所有的商户
//     * @param reqVo
//     * @param pc
//     * @return
//     */
//    public List<ShopPo> searchPageShopsByDepotId(ShopQueryReqVo reqVo, PageControl pc) {
//
//        PageRequest pageable = new PageRequest(Math.max(0, pc.getCurrentPage() - 1), pc.getPageSize(),
//                Sort.Direction.DESC, "createTime");
//        Page<ShopPo> page = shopRepository.searchPageShops(reqVo, pageable);
//        pc.setCount((int) page.getTotalElements());
//        return page.getContent();
//    }
//
//
//    public List<ShopPo> getShopPoBackList() {
////        List<String> shopIds = shopRedisDao.getBlackList();
//        //List<ShopPo> shopPos = shopRepository.findByIdIn(CollectionUtils.toSet(RedisUtil.strListToLongList(shopIds)));
////        return shopPos;
//        return null;
//    }
//
//    public void deleteBlackList(List<String> shopIds) {
//        shopRedisDao.deleteBlackList(shopIds);
//    }
//
//    public String updateBlackList(String blackList) {
//        String[] shopIds = blackList.split("\n");
//        for (String shopId : shopIds) {
//            try {
//                ShopPo shopPo = shopRepository.findOne(Long.valueOf(StringUtils.trimToEmpty(shopId)));
//                if (shopPo == null) {
//                    return shopId + "商户Id不存在";
//                }
//            } catch (Exception e) {
//                logger.debug(e.getMessage());
//                return shopId + "商户Id异常";
//            }
//        }
//        shopRedisDao.updateBlackList(shopIds);
//        return "";
//    }
//
//    public boolean isShopCanCreateOrder(String shopId) {
//        return shopRedisDao.getShopCanOrder(shopId);
//    }


//    public DepotPo getOnlineDepotByShopId(Long shopId, Integer districtId) {
//        ShopPo shop = shopPoRepository.findOne(shopId);
//        if (shop.getDepot() == null) {
//            List<DepotPo> depotPos = depotRepository.findByDistrictId(districtId);
//            if (CollectionUtils.isNotEmpty(depotPos)) {
//                for (DepotPo depotPo : depotPos) {
//                    if (Objects.equals(depotPo.getDepotType(), DepotType.DEPOT_TYPE_ONLINE) &&
//                            Objects.equals(depotPo.getEnableStatus(), CommonStatus.ENABLE)) {
//                        return depotPo;
//                    }
//                }
//            }
//        }
//        return null;
//    }
}
