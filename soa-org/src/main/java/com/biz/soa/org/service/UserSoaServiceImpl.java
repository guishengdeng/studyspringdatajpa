package com.biz.soa.org.service;



import com.biz.core.codec.PasswordUtil;
import com.biz.core.transaction.BizTransactionManager;
import com.biz.core.util.DateUtil;
import com.biz.core.util.StringTool;
import com.biz.gbck.common.com.SMSType;
import com.biz.gbck.common.com.mo.Message;
import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.common.exception.DepotnearbyExceptionFactory;
import com.biz.gbck.common.exception.ExceptionCode;
import com.biz.gbck.common.model.geo.IArea;
import com.biz.gbck.common.org.UserStatus;
import com.biz.gbck.common.spring.DepotnearbyTransactionManager;
import com.biz.gbck.common.vo.CommonReqVoBindUserId;
import com.biz.gbck.common.vo.search.RecommendConditionVo;
import com.biz.gbck.common.vo.search.RecommendConditionVo2;
import com.biz.gbck.common.vo.search.SearchProductCondition;
import com.biz.gbck.dao.mysql.po.org.ShopPo;
import com.biz.gbck.dao.mysql.po.org.UserPo;
import com.biz.gbck.dao.mysql.repository.org.UserRepository;
import com.biz.gbck.dao.redis.repository.org.ShopRedisDao;
import com.biz.gbck.dao.redis.repository.org.UserRedisDao;
import com.biz.gbck.dao.redis.ro.org.ShopRo;
import com.biz.gbck.dao.redis.ro.org.UserRo;
import com.biz.gbck.enums.order.PaymentType;
import com.biz.gbck.enums.user.AuditStatus;
import com.biz.gbck.enums.user.ShopChannel;
import com.biz.gbck.transform.org.UserPoToUserRo;
import com.biz.gbck.vo.mq.MQMessage;
import com.biz.gbck.vo.oms.OMSCreateMemberVo;
import com.biz.gbck.vo.org.AutoLoginReqVo;
import com.biz.gbck.vo.org.ChangePwdVo;
import com.biz.gbck.vo.org.ForgotPasswordReqVo;
import com.biz.gbck.vo.org.UserChangeAvatarReqVo;
import com.biz.gbck.vo.org.UserChangeMobileReqVo;
import com.biz.gbck.vo.org.UserCreateVo;
import com.biz.gbck.vo.org.UserLoginReqVo;
import com.biz.gbck.vo.org.UserLoginResVo;
import com.biz.gbck.vo.org.UserRegisterReqVo;
import com.biz.gbck.vo.search.bbc.SearchUserReqVo;
import com.biz.service.CommonService;
import com.biz.soa.org.event.AutoLoginEvent;
import com.biz.soa.org.event.UserLoginEvent;
import com.biz.soa.org.event.UserRegisterEvent;
import com.biz.soa.org.service.interfaces.ShopSoaService;
import com.biz.soa.org.service.interfaces.SmsSoaService;
import com.biz.soa.org.service.interfaces.UserSoaService;
import com.biz.transformer.org.UserRoToUserVo;
import com.google.common.base.Stopwatch;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;


@Service
public class UserSoaServiceImpl extends CommonService implements UserSoaService {
    private static final Logger logger = LoggerFactory.getLogger(UserSoaServiceImpl.class);


    @Autowired
    private UserRedisDao userRedisDao;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShopRedisDao shopRedisDao;

    @Autowired
    private ShopSoaService shopSoaService;

    @Autowired
    private SmsSoaService smsSoaService;

//    @Autowired
//    private IdPool idPool;



    @Override
    public List<Long> findAdminUserIdsByShopId(Long shopId, Boolean isAdmin) {
        return userRepository.findUserIdByShopIdAndAdminStatus(shopId, isAdmin);
    }

    @Override
    public List<UserPo> findAdminUsersByShopId(Long shopId, Boolean isAdmin) {
        return userRepository.findUsersByShopIdAndAdminStatus(shopId, isAdmin);
    }

    @Override
    @Transactional
    public UserLoginResVo createUserAndShop(UserRegisterReqVo userRegisterReqVo) throws CommonException {

        if (userRegisterReqVo == null || !StringTool.isMobileValid(userRegisterReqVo.getMobile())
                || StringUtils.isBlank(userRegisterReqVo.getPassword())) {
            throw DepotnearbyExceptionFactory.GLOBAL.PARAMETER_ERROR;
        }
        if (!StringTool.isMobileValid(userRegisterReqVo.getMobile())) {
            throw DepotnearbyExceptionFactory.User.ILLEGAL_MOBILE;
        }
        if (StringUtils.isBlank(userRegisterReqVo.getPassword())) {
            throw DepotnearbyExceptionFactory.User.ILLEGAL_PASSWORD;
        }
        if(StringUtils.isNotBlank(userRegisterReqVo.getInviterCode())
                && !userRegisterReqVo.getInviterCode().matches("(\\d+)|([A-Z\\d]+)")) {
            throw DepotnearbyExceptionFactory.User.ILLEGAL_INVITER_CODE;
        }

        if (smsSoaService.validateAndDisableSMSCode(userRegisterReqVo.getMobile(), SMSType.REGISTER,
                userRegisterReqVo.getSmsCode())) {

            Boolean userExist = userRedisDao.getUserByMobile(userRegisterReqVo.getMobile()) != null
                    || userRepository.findByMobile(userRegisterReqVo.getMobile()) != null;
            if (userExist) {
                throw DepotnearbyExceptionFactory.User.USER_EXIST;
            }

            Long userIdByMobile = userRedisDao.getUserIdByMobile(userRegisterReqVo.getMobile());
            UserPo userPo;
            if (userIdByMobile != null) {
                userPo = userRepository.findOne(userIdByMobile);
                userPo = userRegisterReqVo.toUserPo(userPo);
            } else {
                userPo = userRegisterReqVo.toUserPo();
            }
            //创建用户
            userPo.setIsAdmin(true);
            UserRo userRo = createUser(userPo);

            //创建商铺
            ShopRo shopRo = shopSoaService.createShop(Long.parseLong(userRo.getId()), null, userRegisterReqVo.getInviterCode());
            userPo.setShop(shopSoaService.findShopPo(Long.parseLong(shopRo.getId())));
            userRepository.save(userPo);
            userRo.setShopId(Long.parseLong(shopRo.getId()));
            userRedisDao.save(userRo);

            UserRegisterEvent registerEvent =
                    new UserRegisterEvent(this, userRo, userRegisterReqVo);
            BizTransactionManager.publishEvent(registerEvent, true);
            //publishEvent(registerEvent);

            UserLoginResVo userLoginResVo = new UserRoToUserVo().apply(userRo);
            assert userLoginResVo != null;
            userLoginResVo.setShopProperties(shopRo);
            return userLoginResVo;
        } else {
            throw DepotnearbyExceptionFactory.SMS.INVALID_SMS_CODE;
        }
    }

    @Override
    public UserRo createUser(UserCreateVo userCreateVo, String admin) throws CommonException {
        UserPo userPo = userCreateVo.toUserPo();
        UserRo user = createUser(userPo);
        //TODO 记录创建日志
        return user;
    }

    @Override
    public UserRo createUser(UserPo userPo) throws CommonException {
        UserRo user = userRedisDao.getUserByMobile(userPo.getMobile());
        if (user != null) {
            throw DepotnearbyExceptionFactory.User.USER_EXIST;
        }
        if (userPo.getId() == null) {
            //Long userId = idPool.getNextId(IdType.USER);
            userPo.setId(idService.nextId());
        }
        userPo.setCreateTime(DateUtil.now());
        return saveUser(userPo);
    }

    @Override
    public UserRo saveUser(UserPo userPo) {
        UserPo savedUserPo = userRepository.save(userPo);
        return syncUserPoToRedis(savedUserPo);
    }

    @Override
    public UserLoginResVo buildRespVo(UserRo userRo) throws CommonException {
        if(!userRepository.exists(Long.parseLong(userRo.getId()))){
            userRedisDao.delete(userRo);
            throw DepotnearbyExceptionFactory.User.USER_NOT_EXIST;
        }
        Stopwatch stopwatch = Stopwatch.createStarted();
        UserLoginResVo userLoginResVo = new UserRoToUserVo().apply(userRo);
        ShopRo shopRo = shopRedisDao.findOne(userRo.getShopId().toString());
        if (shopRo == null) {
            UserPo userPo = userRepository.findOne(Long.parseLong(userRo.getId()));
            if (userPo != null) {
                if (userPo.getShop() == null) {
                    shopRo = shopSoaService.createShop(userPo.getId(), userPo.getName(), null);// TODO: 17-4-27 没商户创建商户
                    userPo.setShop(shopSoaService.findShopPo(Long.parseLong(shopRo.getId())));// TODO: 17-4-27 查询商户
                    userRo = syncUserPoToRedis(userRepository.save(userPo));
                } else {
                    shopRo = shopSoaService.findShop(userPo.getShop().getId());// TODO: 17-4-27  shop方法没实现
                }
            } else {
                userRedisDao.delete(userRo);
                throw DepotnearbyExceptionFactory.User.USER_NOT_EXIST;
            }
        }
        assert userLoginResVo != null;
        // todo liubin
//        if (!Objects.equals(shopRo.getStatus(), ShopStatus.NORMAL.getValue())) {
//            throw DepotnearbyExceptionFactory.Shop.SHOP_DISABLED;
//        }
        userLoginResVo.setShopProperties(shopRo);
        //为20倍会员做双重检查，以防状态不正确
        if(!Objects.equals(userLoginResVo.getDetailAuditStatus(), AuditStatus.NORMAL.getValue()) || !Objects
                .equals(userLoginResVo.getQualificationAuditStatus(), AuditStatus.NORMAL.getValue())){
            ShopPo shopPo = shopSoaService.findShopPo(Long.parseLong(shopRo.getId())); // TODO: 17-4-27 根据商户id获取shopPo
//            if(shopPo.getShopLevel() == ShopLevel.VIP_20){
            userLoginResVo.setDetailAuditStatus(AuditStatus.NEED_INFO.getValue());
            userLoginResVo.setQualificationAuditStatus(AuditStatus.NEED_INFO.getValue());
//            }
        }
       /*userLoginResVo.setLuckMoneyCount(voucherService.getUserUseableVoucherCount(userRo.getId()));*/// TODO: 17-4-27 获取用户可用优惠券数量
       /* userLoginResVo.setShowActivityRedPoint(promotionService.showRedPoint(userRo.getId()));*/ // TODO: 17-4-27 是否显示红点，有消息显示
        //TODO update login res vo: showPaymentButton 加上嘴上功夫判断
      /*  Boolean showPaymentButton = userRo.getIsAdmin() && ((
                (xiMuCustAccrService.isShopCanApply(shopRo.getId()) || xiMuCustAccrService
                        .isShopCanLoan(shopRo.getId())) && !allPaymentTypeIsInExcludePaymentTypes(shopRo,
                        PaymentType.XIMU)) || !allPaymentTypeIsInExcludePaymentTypes(shopRo,
                PaymentType.XIMU));
        userLoginResVo.setShowPaymentButton(showPaymentButton);*/ // TODO: 17-4-27   是否显示记我账上
        if (Objects.equals(userLoginResVo.getDetailAuditStatus(), AuditStatus.AUDIT_FAILED.getValue())) {
            List<String> detailAuditRejectReason =
                    shopSoaService.findDetailAuditRejectReason(Long.parseLong(shopRo.getId()));
            userLoginResVo.setDetailRejectReasons(detailAuditRejectReason);
        }
        if (Objects.equals(userLoginResVo.getQualificationAuditStatus(),
                AuditStatus.AUDIT_FAILED.getValue())) {
            List<String> qualificationAuditRejectReason =
                    shopSoaService.findQualificationAuditRejectReason(Long.parseLong(shopRo.getId()));
            userLoginResVo.setQualificationRejectReasons(qualificationAuditRejectReason);
        }
       /* userLoginResVo.setMsgCount(noticeService.getRemainMSgCount(userRo.getId()));*/ // TODO: 17-4-27 消息数量
        logger.error("获取数据用户[{}] 店铺及系统信息耗时 {} ms", userRo.getId(),
                stopwatch.elapsed(TimeUnit.MILLISECONDS));
        return userLoginResVo;
    }

    @Override
    public Boolean allPaymentTypeIsInExcludePaymentTypes(ShopRo shopRo, PaymentType... paymentTypes) {
        return null;
    }

    @Override
    public UserLoginResVo login(UserLoginReqVo userLoginReqVo) throws CommonException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        UserRo userRo = findUserByMobile(userLoginReqVo.getAccount());
        logger.error("获取数据用户[{}]耗时 {} ms", userLoginReqVo.getAccount(),
                stopwatch.elapsed(TimeUnit.MILLISECONDS));
        if (userRo == null) {
            throw DepotnearbyExceptionFactory.User.USER_NOT_EXIST;
        } else {
            if (userLoginReqVo.getPassword().equalsIgnoreCase(userRo.getPassword())) {
                BizTransactionManager.publishEvent(new UserLoginEvent(this, userRo, userLoginReqVo), true);
//                publishEvent(new UserLoginEvent(this, userRo, userLoginReqVo));
                if (Objects.equals(userRo.getStatus(), UserStatus.NORMAL.getValue())) {
                    return buildRespVo(userRo);
                } else {
                    throw DepotnearbyExceptionFactory.User.USER_DISABLED;
                }
            } else {
                throw DepotnearbyExceptionFactory.User.PWD_NOT_MATCH;
            }
        }
    }

    @Override
    public UserLoginResVo autoLogin(AutoLoginReqVo autoLoginReqVo) throws CommonException {
        UserRo userRo = this.findUser(Long.valueOf(autoLoginReqVo.getUserId()));
        BizTransactionManager.publishEvent(new AutoLoginEvent(this, userRo, autoLoginReqVo), true);
        //publishEvent(new AutoLoginEvent(this, userRo, autoLoginReqVo));
        return buildRespVo(userRo);
    }

    @Override
    public void logout(CommonReqVoBindUserId commonReqVoBindUserId) {
        userRedisDao.clearToken(Long.valueOf(commonReqVoBindUserId.getUserId()), "", "");
        userRepository.cleanLoginDevice(Long.valueOf(commonReqVoBindUserId.getUserId())); //保存用户最后登录设备
    }

    @Override
    @Transactional
    public void forgotPassword(ForgotPasswordReqVo forgotPasswordReqVo) throws CommonException {

        if (forgotPasswordReqVo == null || StringUtils.isBlank(forgotPasswordReqVo.getPassword())
                || forgotPasswordReqVo.getPassword().length() != 32) {
            throw DepotnearbyExceptionFactory.User.ILLEGAL_PASSWORD;
        }
        if (smsSoaService.validateAndDisableSMSCode(forgotPasswordReqVo.getMobile(),
                SMSType.FORGOT_PASSWORD,forgotPasswordReqVo.getSmsCode())) {
            UserRo userRo = findUserByMobile(forgotPasswordReqVo.getMobile());
            if (userRo == null) {
                throw DepotnearbyExceptionFactory.User.USER_NOT_EXIST;
            } else {
//                String originalPassword =
//                        PasswordUtil.aes2Original(forgotPasswordReqVo.getOriginalPassword());
                userRepository.updateUserPassword(forgotPasswordReqVo.getMobile(),
                        forgotPasswordReqVo.getPassword(), forgotPasswordReqVo.getPassword());
                userRedisDao.updateUserPassword(forgotPasswordReqVo.getMobile(),
                        forgotPasswordReqVo.getPassword(), forgotPasswordReqVo.getPassword());
            }
        } else {
            throw DepotnearbyExceptionFactory.SMS.INVALID_SMS_CODE;
        }
    }

    @Override
    public void tokenChange(AutoLoginReqVo reqVo) throws CommonException {

    }

    @Override
    public UserRo findUser(Long userId) throws CommonException {
        if (userId == null) {
            return null;
        }
        UserRo userRo = userRedisDao.get(userId);
        if (userRo == null) {
            logger.debug("Find User by mobile:{} from mysql.", userId);
            UserPo userPo = userRepository.findOne(userId);
            userRo = syncUserPoToRedis(userPo);
        }
        if (userRo == null) {
            throw DepotnearbyExceptionFactory.User.USER_NOT_EXIST;
        }
        return userRo;
    }

    @Override
    public UserPo findAdminByShopId(Long shopId) {
        return null;
    }

    @Override
    public List<UserPo> findByUserIds(Set<Long> userIds) {
        return null;
    }

    @Override
    public UserRo findUserByMobile(String mobile) {
        logger.debug("Find User by mobile:{}", mobile);
        UserRo userRo = userRedisDao.getUserByMobile(mobile);
        if (userRo == null) {
            logger.debug("Find User by mobile:{} from mysql.", mobile);
            UserPo userPo = findUserPoByMobile(mobile);
            userRo = syncUserPoToRedis(userPo);
        }
        return userRo;
    }

    @Override
    public UserPo findUserPoByMobile(String mobile) {
        return userRepository.findByMobile(mobile);
    }

    @Override
    public UserPo findUserPoByAccount(String account) {
        return userRepository.findByAccount(account);
    }

    @Override
    public void disableUser(Long userId) {
        userRepository.disableUser(userId);
        final UserPo userPo = userRepository.findOne(userId);
        DepotnearbyTransactionManager.doWhenTransactionalSuccess(
                new DepotnearbyTransactionManager.Task() {
                    @Override public void justDoIt() {
                        syncUserPoToRedis(userPo);
                    }
                });
    }

    @Override
    public void destroyUserById(Long userId, String handler) {
        logger.info("Destroy user[{}] by admin[{}]", userId, handler);
        if (userId == null) {
            return;
        }
        UserPo userPo = userRepository.findOne(userId);
        if (userPo != null) {
            String newMobile = "0" + userPo.getMobile().substring(1);
            userPo.setMobile(newMobile);
            userPo.setAccount(null);
            userPo.setStatus(UserStatus.DISABLED.getValue());
            userRepository.save(userPo);
        }
        UserRo userRo = userRedisDao.get(userId);
        if (userRo != null) {
            userRedisDao.delete(userRo);
        }
    }

    /**
     * 查找商户所有员工
     */
    public List<Long> findUserIdsByShopId(Long shopId) {
        return userRepository.findUserIdsByShopId(shopId);
    }

    /**
     * 获取某区域下的所有 userId（后台使用 ，不要求高并发）
     *
     * @param areaType IArea.LEVEL_*
     */
    public List<Long> findUserIdByAreaId(Integer areaType, Integer areaId) throws CommonException {

        switch (areaType) {
            case IArea.LEVEL_PROVINCE:
                return userRepository.findUserIdsByProvince(areaId);
            case IArea.LEVEL_CITY:
                return userRepository.findUserIdsByCity(areaId);
            case IArea.LEVEL_DISTRICT:
                return userRepository.findUserIdsByDistrict(areaId);
            default:
                throw DepotnearbyExceptionFactory.GLOBAL.PARAMETER_ERROR;
        }
    }

    /**
     * 获取某个商户类型的用户Ids（后台使用 ，不要求高并发）
     */
    public List<Long> findUserIdByShopType(Long shopTypeId) {

        return userRepository.findUserIdsByShopType(shopTypeId);
    }

    /**
     * 获取区域下的 某个商户类型的用户Id（后台使用 ，不要求高并发）
     */
    public List<Long> findUserIdByAreaAndUserType(Integer areaType, Integer areaId,
                                                  Integer shopTypeId) throws CommonException {

        switch (areaType) {
            case IArea.LEVEL_PROVINCE:
                return userRepository.findUserIdsByProvinceAndShopType(areaId, shopTypeId);
            case IArea.LEVEL_CITY:
                return userRepository.findUserIdsByCityAndShopType(areaId, shopTypeId);
            case IArea.LEVEL_DISTRICT:
                return userRepository.findUserIdsByDistrictAndShopType(areaId, shopTypeId);
            default:
                throw DepotnearbyExceptionFactory.GLOBAL.PARAMETER_ERROR;
        }
    }

    /**
     * 获取所有用户Id（后台使用 ，不要求高并发）
     */
    public List<Long> findAllUserId() {

        return userRepository.findAllUserIds();
    }

    /**
     * 根据店铺审核状态查询所有userId
     */
    public List<Long> findAllUserIdByAuditStatus(AuditStatus auditStatus){

        return userRepository.findAllUserIds(auditStatus.getValue());
    }

    /**
     * 根据店铺审核状态查询所有userPo
     */
    public List<UserPo> findAllUserByAuditStatus(AuditStatus auditStatus){

        return userRepository.findAllUserByAuditStatus(auditStatus.getValue());
    }

    /**
     * 获取用户所属店铺的价格店铺
     */
    public String getDepotIdByUserId(Long userId) throws CommonException {
        if (userId == null || userId == 0L)
            throw new CommonException("必须登录才能使用此功能！", ExceptionCode.Global.INFO_TO_USER);
        UserRo userRo = findUser(userId);
        Long shopId = userRo.getShopId();
        ShopRo shopRo = shopSoaService.findShop(shopId);
        if (shopRo == null) {
            throw new CommonException("您的账号异常！请联系客服解决", ExceptionCode.Global.INFO_TO_USER);
        }
        return shopRo.getDepotId();
    }


    public void bindSearchParam(SearchProductCondition condition) throws CommonException {
        Long userId = condition.getUserId();
        if (userId != null && userId > 0L) {
            UserRo userRo = findUser(userId);
            if (userRo == null)
                throw new CommonException("您的账号异常！请联系客服解决", ExceptionCode.Global.INFO_TO_USER);

            Long shopId = userRo.getShopId();
            ShopRo shopRo = shopSoaService.findShop(shopId);
            if (shopRo == null)
                throw new CommonException("您的账号信息异常！请联系客服解决", ExceptionCode.Global.INFO_TO_USER);
            condition.setSaleAreaIds(StringTool.strToIntArray(shopRo.getSaleAreas()));
            if (StringUtils.isBlank(shopRo.getDepotId()))
                throw new CommonException("您的账号异常，未指定门店！请联系客服解决",
                        ExceptionCode.Global.INFO_TO_USER);
            condition.setDepotId(shopRo.getDepotId());
        }
    }

    public RecommendConditionVo getRecommendConditionVo(Long userId) throws CommonException {
        if (userId == null || userId == 0L)
            throw new CommonException("必须登录才能使用此功能！", ExceptionCode.Global.INFO_TO_USER);
        UserRo userRo = findUser(userId);
        Long shopId = userRo.getShopId();
        ShopRo shopRo = shopSoaService.findShop(shopId);
        if (shopRo == null) {
            throw new CommonException("您的账号异常！请联系客服解决", ExceptionCode.Global.INFO_TO_USER);
        }
        RecommendConditionVo vo = new RecommendConditionVo();
        vo.setUserId(userId);
        vo.setBusinessTags(shopRo.getBusinessTags());
        vo.setPriceTagss(shopRo.getPriceTags());
        vo.setSaleAreas(shopRo.getSaleAreas());
        vo.setDepotId(shopRo.getDepotId());
        return vo;
    }

    public RecommendConditionVo2 getRecommendConditionVo2(Long userId) throws CommonException {
        if (userId == null || userId == 0L)
            throw new CommonException("必须登录才能使用此功能！", ExceptionCode.Global.INFO_TO_USER);
        UserRo userRo = findUser(userId);
        Long shopId = userRo.getShopId();
        ShopRo shopRo = shopSoaService.findShop(shopId);
        if (shopRo == null) {
            throw new CommonException("您的账号异常！请联系客服解决", ExceptionCode.Global.INFO_TO_USER);
        }

        RecommendConditionVo2 vo = new RecommendConditionVo2();
        vo.setUserId(userId);
        vo.setBusinessTags(shopRo.getBusinessTags());
        if (StringUtils.isNotBlank(shopRo.getPriceTags())) {
//            List<Integer> ids = StringTool.strToIntArray(shopRo.getPriceTags());
//            List<PriceTagRo> tags = priceTagRedisDao.findByIds(ids);
//            Map<Integer, List<Integer>> maps = new HashMap<Integer, List<Integer>>();
//            for (PriceTagRo ro : tags) {
//                if (ro != null) {
//                    List<Integer> idList = null;
//                    idList = maps.get(ro.getCategoryId());
//                    if (idList == null) {
//                        idList = new ArrayList<Integer>();
//                        maps.put(ro.getCategoryId(), idList);
//                    }
//                    idList.add(ro.getId());
//                }
//            }
//            vo.setPriceTagMap(maps);
        }
        vo.setSaleAreas(shopRo.getSaleAreas());
        vo.setDepotId(shopRo.getDepotId());
        return vo;
    }



    /**
     * 更改手机号
     */
    @Transactional
    public void changeMobile(final UserChangeMobileReqVo reqVo) throws CommonException {

        if (smsSoaService.validateAndDisableSMSCode(reqVo.getMobile(), SMSType.CHANGE_MOBILE,
                reqVo.getSmsCode())) {
            final UserRo userRo = userRedisDao.get(Long.valueOf(reqVo.getUserId()));
            if (userRo == null) {
                throw DepotnearbyExceptionFactory.User.USER_NOT_EXIST;
            }
            Long userId = userRedisDao.getUserIdByMobile(reqVo.getMobile());
            if (userId != null) {
                throw DepotnearbyExceptionFactory.User.USER_EXIST;
            }
            userRepository.updateUserMobile(Long.valueOf(reqVo.getUserId()), reqVo.getMobile());
            final UserPo userPo = userRepository.findOne(Long.valueOf(reqVo.getUserId()));
            DepotnearbyTransactionManager.doWhenTransactionalSuccess(
                    new DepotnearbyTransactionManager.Task() {
                        @Override public void justDoIt() {
                            syncUserPoToRedis(userPo);
                            userRedisDao.removeMapMobileToUser(userRo.getMobile());
                            if(userPo.getIsAdmin()) {
                                shopSoaService.changeMobile(userRo.getShopId(), reqVo.getMobile());
                            }
                        }
                    });
        } else {
            throw DepotnearbyExceptionFactory.SMS.INVALID_SMS_CODE;
        }
    }

    /**
     * 通过手机号重置密码
     *
     * @param mobile 手机号
     * @throws CommonException
     */
    @Transactional
    public void resetPassword(String mobile, String rawPassword, String handler) throws CommonException {

        if (!StringTool.isMobileValid(mobile)) {
            throw DepotnearbyExceptionFactory.GLOBAL.PARAMETER_ERROR;
        }
        UserRo userRo = userRedisDao.getUserByMobile(mobile);
        UserPo userPo;
        if (userRo == null) {
            userPo = userRepository.findByMobile(mobile);
        } else {
            userPo = findUserById(Long.valueOf(userRo.getId()));
        }

        if (userPo == null) {
            throw DepotnearbyExceptionFactory.User.USER_NOT_EXIST;
        }
        logger.info("Update user[{}] password by [{}]", mobile, handler);
        userPo.setOriginalPassword(rawPassword);
        userPo.setPassword(StringTool.encodedByMD5(userPo.getOriginalPassword()));
        final UserPo savedUserPo = userRepository.save(userPo);
        DepotnearbyTransactionManager.doWhenTransactionalSuccess(new DepotnearbyTransactionManager.Task() {
            @Override public void justDoIt() {
                syncUserPoToRedis(savedUserPo);
            }
        });
    }

    /**
     * 修改用户头像
     */
    @Transactional public void changeAvatar(UserChangeAvatarReqVo reqVo) throws CommonException {

        UserRo userRo = userRedisDao.get(Long.valueOf(reqVo.getUserId()));
        if (userRo == null) {
            throw DepotnearbyExceptionFactory.User.USER_NOT_EXIST;
        }
        userRepository.updateUserAvatar(Long.valueOf(reqVo.getUserId()), reqVo.getAvatar());
        final UserPo userPo = userRepository.findOne(Long.valueOf(reqVo.getUserId()));
        DepotnearbyTransactionManager.doWhenTransactionalSuccess(
                new DepotnearbyTransactionManager.Task() {
                    @Override public void justDoIt() {
                        syncUserPoToRedis(userPo);
                    }
                });
    }

    public Long findUserIdByBaidu(Long baiduUserId, String mobile, Integer geoCode) throws CommonException {

        if (baiduUserId == null) {
            throw new CommonException("Argument baiduUserId can not be null.");
        }

        if(geoCode == null){
            throw new CommonException("Argument geoCode can not be null.");
        }

        logger.debug("Get userId by nuomi userId: {}", baiduUserId);
        Long userId = userRedisDao.getUserIdByShopChannelAndChannelUserId(ShopChannel.BAI_DU_NUO_MI,
                baiduUserId);
        ShopPo shopPo = shopSoaService.findShopOrCreateByBaiduUserIdAndBaiduGeoCode(baiduUserId, geoCode);
        if (shopPo == null) {
            throw new CommonException(
                    format("Create baidu user[%s] shop failed.", baiduUserId));
        }
        UserRo userRo = userRedisDao.get(userId);
        if (userId == null || userRo == null || !Objects.equals(userRo.getShopId(), shopPo.getId()) ||
                !userRepository.exists(userId)) {
            UserPo userPo = new UserPo();
            userPo.setId(userId);
            userPo.setName("百度用户" + baiduUserId);
            userPo.setMobile("");
            userPo.setShop(shopPo);
            userPo.setStatus(UserStatus.DISABLED.getValue());
            userPo.setIsAdmin(false);
            userPo.setPassword("");
            userPo.setOriginalPassword("");
            userRo = createUser(userPo);
            userRedisDao.mapShopChannelAndChannelIdToUser(ShopChannel.BAI_DU_NUO_MI, baiduUserId, Long.valueOf(userRo.getId()));
            userRedisDao.mapShopChannelAndChannelMobileToChannelUserId(ShopChannel.BAI_DU_NUO_MI, mobile, baiduUserId);
            userId = Long.valueOf(userRo.getId());
        }
        logger.debug("Got userId: {} by nuomi userId: {}", userId, baiduUserId);
        return userId;
    }

    public void syncAllUserFromMysqlToRedis(Integer pageSize) {

        logger.debug("Sync all user from mysql to redis begin.");
        Integer startPage = 0;
        Page<UserPo> userPage = null;
        while (userPage == null || userPage.hasNext()) {
            logger.debug("Sync page {} of users...", startPage);
            Pageable page = new PageRequest(startPage++, pageSize);
            userPage = userRepository.findAll(page);
            List<UserPo> content = userPage.getContent();
            for (UserPo userPo : content) {
                syncUserPoToRedis(userPo);
            }
        }
        logger.debug("Sync all user from mysql to redis begin.");
    }

    /**
     * 同步UserPo到redis数据库
     */
    private UserRo syncUserPoToRedis(UserPo userPo) {
        UserRo userRo = null;
        if (userPo != null) {
            userRo = new UserPoToUserRo().apply(userPo);
            userRedisDao.save(userRo);
        }
        return userRo;
    }

    public List<UserPo> searchUsers(SearchUserReqVo vo) {
        return userRepository.searchUser(vo);
    }

    @Transactional
    public void updateUserStatus(Long id, Integer status) {
        userRepository.updateUserStatusById(id, status);
    }

    public UserPo findUserById(Long id) {
        return userRepository.findOne(id);
    }

    public List<UserPo> findAllUserPo() {
        return userRepository.findAll();
    }

    public void removeUserRoByMobile(String mobile){
        userRedisDao.removeMapMobileToUser(mobile);
    }

    public boolean canUserCreateOrder(String mobile) {
        return userRedisDao.getUserCanOrder(mobile);
    }

    /**
     * 从redis查找所有列入黑名单的用户号码
     */
    public List<String> getBlockList() {
        return userRedisDao.getBlackList();
    }

    public void updateBlackList(String blackList) {
        String[] mobiles = blackList.split("\n");
        userRedisDao.updateBlackList(mobiles);
    }

    @Transactional
    public void changePwd(final ChangePwdVo changePwdVo) throws CommonException {
        UserRo userRo = userRedisDao.get(Long.valueOf(changePwdVo.getUserId()));
        if (userRo == null) {
            throw DepotnearbyExceptionFactory.User.USER_NOT_EXIST;
        }
        final String mobile = userRo.getMobile();
        if(changePwdVo.getOriginPassword().length()!=32|changePwdVo.getNewPassword().length()!=32|changePwdVo.getConfirmPassword().length()!=32){
            // todo liubin
            //throw DepotnearbyExceptionFactory.User.ILLEGAL_PASSWORD;
        }
        if (changePwdVo.getOriginPassword().equalsIgnoreCase(userRo.getPassword()) &&
                changePwdVo.getNewPassword().equalsIgnoreCase(changePwdVo.getConfirmPassword())) {
            userRepository.updateUserPassword(mobile,changePwdVo.getConfirmPassword(),changePwdVo.getConfirmPassword());
            DepotnearbyTransactionManager.doWhenTransactionalSuccess(new DepotnearbyTransactionManager.Task() {
                @Override public void justDoIt() {
                    userRedisDao.updateUserPassword(mobile,changePwdVo.getConfirmPassword(),changePwdVo.getConfirmPassword());
                }
            });
        }else{
            throw DepotnearbyExceptionFactory.User.PWD_NOT_MATCH;
        }

    }

    public boolean validateUserLoginPwd(Long userId, String md5Password) throws CommonException {
        UserRo userRo = findUser(userId);
        return StringUtils.equals(StringUtils.trim(md5Password), userRo.getPassword());
    }


    /**
     * 注册失败到OMS用户列表, 定时任务
     */
    public void registerFailedUsers() {
        List<UserRo> users = userRedisDao.getSyncFailedUsers();
        int size = users.size();
        logger.debug("开始批量放失败的用户信息到OMS同步MQ队列. size: {}", size);
        int count = 0;
        for (UserRo userRo : users) {
            if (userRo != null) {
                try {
                    OMSCreateMemberVo omsMemberCreateVo = new OMSCreateMemberVo();
                    omsMemberCreateVo.setUserId(Long.valueOf(userRo.getId()));
                    omsMemberCreateVo.setName(userRo.getName());
                    //omsMemberCreateVo.setChannelCode(IOms.CHANNEL_CODE);
                    omsMemberCreateVo.setMobile(userRo.getMobile());
                    omsMemberCreateVo.setPassword(userRo.getPassword());
                    omsMemberCreateVo.setOriginalPwd(userRo.getOriginalPassword());
                    MQMessage msg = new MQMessage(Message.QUEUE.MQ_OMS_MEMBER_CREATE_QUEUE, omsMemberCreateVo, TimeUnit.SECONDS.toMillis(10), 5, null);
                    //mqService.sendMessage(msg);
                    logger.debug("开始批量注册失败的用户信息到队列成功. 完成: {}/{}, vo: {}", ++count, size, omsMemberCreateVo);
                } catch (Exception e) {
                    logger.error("Sync member to oms queue failed. userRo: {}", userRo, e);
                }
            }
        }
        logger.debug("批量注册用户信息到老系统成功. size: {}", size);
    }
}
