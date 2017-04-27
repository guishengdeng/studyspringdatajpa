package com.biz.service.org;

import com.biz.core.codec.PasswordUtil;
import com.biz.event.org.AutoLoginEvent;
import com.biz.event.org.UserLoginEvent;
import com.biz.gbck.common.com.SMSType;
import com.biz.gbck.common.com.transformer.UserPoToUserRo;
import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.common.exception.DepotnearbyExceptionFactory;
import com.biz.gbck.common.model.order.PaymentType;
import com.biz.gbck.common.org.UserStatus;
import com.biz.gbck.common.vo.CommonReqVoBindUserId;
import com.biz.gbck.common.vo.search.RecommendConditionVo;
import com.biz.gbck.common.vo.search.RecommendConditionVo2;
import com.biz.gbck.common.vo.search.SearchProductCondition;
import com.biz.gbck.dao.mysql.po.org.ShopLevel;
import com.biz.gbck.dao.mysql.po.org.ShopPo;
import com.biz.gbck.dao.mysql.po.org.UserPo;
import com.biz.gbck.dao.mysql.repository.org.UserRepository;
import com.biz.gbck.dao.redis.repository.org.ShopRedisDao;
import com.biz.gbck.dao.redis.repository.org.UserRedisDao;
import com.biz.gbck.dao.redis.ro.org.ShopRo;
import com.biz.gbck.dao.redis.ro.org.UserRo;
import com.biz.gbck.enums.user.AuditStatus;
import com.biz.gbck.enums.user.ShopStatus;
import com.biz.gbck.vo.search.SearchUserReqVo;
import com.biz.gbck.vo.user.MemberIdRequestVo;
import com.biz.gbck.vo.user.UserResponseVo;
import com.biz.service.CommonService;
import com.biz.service.org.interfaces.ShopService;
import com.biz.service.org.interfaces.UserService;
import com.biz.transformer.org.UserRoToUserVo;
import com.biz.vo.org.*;
import com.google.common.base.Stopwatch;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;


@Service
public class UserServiceImpl extends CommonService implements UserService{
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Autowired private UserRedisDao userRedisDao;

    @Autowired private UserRepository userRepository;

    @Autowired private ShopRedisDao shopRedisDao;

    @Autowired private ShopService shopService;

  /*  @Autowired private SMSService smsService;*/



    @Override
    public List<Long> findAdminUserIdsByShopId(Long shopId, Boolean isAdmin) {
        return null;
    }

    @Override
    public List<UserPo> findAdminUsersByShopId(Long shopId, Boolean isAdmin) {
        return null;
    }

    @Override
    public UserLoginResVo createUserAndShop(UserRegisterReqVo userRegisterReqVo) throws CommonException {
        return null;
    }

    @Override
    public UserRo createUser(UserCreateVo userCreateVo, String admin) throws CommonException {
        return null;
    }

    @Override
    public UserRo createUser(UserPo userPo) throws CommonException {
        return null;
    }

    @Override
    public UserRo saveUser(UserPo userPo) {
        return null;
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
                    shopRo =shopService.createShop(userPo.getId(), userPo.getName(), null);// TODO: 17-4-27 没商户创建商户
                    userPo.setShop(shopService.findShopPo(Long.parseLong(shopRo.getId())));// TODO: 17-4-27 查询商户
                    userRo = syncUserPoToRedis(userRepository.save(userPo));
                } else {
                    shopRo =shopService.findShop(userPo.getShop().getId());// TODO: 17-4-27  shop方法没实现
                }
            } else {
                userRedisDao.delete(userRo);
                throw DepotnearbyExceptionFactory.User.USER_NOT_EXIST;
            }
        }
        assert userLoginResVo != null;
        if (!Objects.equals(shopRo.getStatus(), ShopStatus.NORMAL.getValue())) {
            throw DepotnearbyExceptionFactory.Shop.SHOP_DISABLED;
        }
        userLoginResVo.setShopProperties(shopRo);
        //为20倍会员做双重检查，以防状态不正确
         if(!Objects.equals(userLoginResVo.getDetailAuditStatus(), AuditStatus.NORMAL.getValue()) || !Objects
                .equals(userLoginResVo.getQualificationAuditStatus(), AuditStatus.NORMAL.getValue())){
            ShopPo shopPo = shopService.findShopPo(Long.parseLong(shopRo.getId())); // TODO: 17-4-27 根据商户id获取shopPo
            if(shopPo.getShopLevel() == ShopLevel.VIP_20){
                userLoginResVo.setDetailAuditStatus(AuditStatus.NORMAL.getValue());
                userLoginResVo.setQualificationAuditStatus(AuditStatus.NORMAL.getValue());
            }
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
       if (Objects
                .equals(userLoginResVo.getDetailAuditStatus(), AuditStatus.AUDIT_FAILED.getValue())) {
            List<String> detailAuditRejectReason =
                    shopService.findDetailAuditRejectReason(Long.parseLong(shopRo.getId()));
            userLoginResVo.setDetailRejectReasons(detailAuditRejectReason);
        }
        if (Objects.equals(userLoginResVo.getQualificationAuditStatus(),
                AuditStatus.AUDIT_FAILED.getValue())) {
            List<String> qualificationAuditRejectReason =
                    shopService.findQualificationAuditRejectReason(Long.parseLong(shopRo.getId()));
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
                publishEvent(new UserLoginEvent(this, userRo, userLoginReqVo));
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
        UserRo userRo = this.findUser(autoLoginReqVo.getUserId());
        publishEvent(new AutoLoginEvent(this, userRo, autoLoginReqVo));
        return buildRespVo(userRo);
    }

    @Override
    public void logout(CommonReqVoBindUserId commonReqVoBindUserId) {
        userRedisDao.clearToken(commonReqVoBindUserId.getUserId(), "", "");
        userRepository.cleanLoginDevice(commonReqVoBindUserId.getUserId()); //保存用户最后登录设备
    }

    @Override
    public void forgotPassword(ForgotPasswordReqVo forgotPasswordReqVo) throws CommonException {

        if (forgotPasswordReqVo == null || StringUtils.isBlank(forgotPasswordReqVo.getPassword())
                || forgotPasswordReqVo.getPassword().length() != 32) {
            throw DepotnearbyExceptionFactory.User.ILLEGAL_PASSWORD;
        }
        if (false) { //smsService.validateAndDisableSMSCode(forgotPasswordReqVo.getMobile(),
                    // SMSType.FORGOT_PASSWORD,forgotPasswordReqVo.getSmsCode()) // TODO: 17-4-27 校验短信验证码是否正确,如果正确则使该短信验证码失效
            UserRo userRo = findUserByMobile(forgotPasswordReqVo.getMobile());
            if (userRo == null) {
                throw DepotnearbyExceptionFactory.User.USER_NOT_EXIST;
            } else {
                String originalPassword =
                        PasswordUtil.aes2Original(forgotPasswordReqVo.getOriginalPassword());
                userRepository.updateUserPassword(forgotPasswordReqVo.getMobile(),
                        forgotPasswordReqVo.getPassword(), originalPassword);
                userRedisDao.updateUserPassword(forgotPasswordReqVo.getMobile(),
                        forgotPasswordReqVo.getPassword(), originalPassword);
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
        return null;
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
    public UserPo findUserPoByAccount(String mobile) {
        return null;
    }

    @Override
    public void disableUser(Long userId) {

    }

    @Override
    public void destroyUserById(Long userId, String handler) {

    }

    @Override
    public List<Long> findUserIdsByShopId(Long shopId) {
        return null;
    }

    @Override
    public List<Long> findUserIdByAreaId(Integer areaType, Integer areaId) throws CommonException {
        return null;
    }

    @Override
    public List<Long> findUserIdByShopType(Long shopTypeId) {
        return null;
    }

    @Override
    public List<Long> findUserIdByAreaAndUserType(Integer areaType, Integer areaId, Integer shopTypeId) throws CommonException {
        return null;
    }

    @Override
    public List<Long> findAllUserId() {
        return null;
    }

    @Override
    public List<Long> findAllUserIdByAuditStatus(AuditStatus auditStatus) {
        return null;
    }

    @Override
    public List<UserPo> findAllUserByAuditStatus(AuditStatus auditStatus) {
        return null;
    }

    @Override
    public String getDepotIdByUserId(Long userId) throws CommonException {
        return null;
    }

    @Override
    public void bindSearchParam(SearchProductCondition condition) throws CommonException {

    }

    @Override
    public RecommendConditionVo getRecommendConditionVo(Long userId) throws CommonException {
        return null;
    }

    @Override
    public RecommendConditionVo2 getRecommendConditionVo2(Long userId) throws CommonException {
        return null;
    }

    @Override
    public void changeMobile(UserChangeMobileReqVo reqVo) throws CommonException {

    }

    @Override
    public void resetPassword(String mobile, String rawPassword, String handler) throws CommonException {

    }

    @Override
    public void changeAvatar(UserChangeAvatarReqVo reqVo) throws CommonException {

    }

    @Override
    public Long findUserIdByBaidu(Long baiduUserId, String mobile, Integer geoCode) throws CommonException {
        return null;
    }

    @Override
    public void syncAllUserFromMysqlToRedis(Integer pageSize) {

    }

    @Override
    public UserRo syncUserPoToRedis(UserPo userPo) {
        UserRo userRo = null;
        if (userPo != null) {
            userRo = new UserPoToUserRo().apply(userPo);
            userRedisDao.save(userRo);
        }
        return userRo;
    }

    @Override
    public List<UserPo> searchUsers(SearchUserReqVo vo) {
        return null;
    }

    @Override
    public void updateUserStatus(Long id, Integer status) {

    }

    @Override
    public UserPo findUserById(Long id) {
        return null;
    }

    @Override
    public List<UserPo> findAllUserPo() {
        return null;
    }

    @Override
    public void removeUserRoByMobile(String mobile) {

    }

    @Override
    public boolean canUserCreateOrder(String mobile) {
        return false;
    }

    @Override
    public List<String> getBlockList() {
        return null;
    }

    @Override
    public void changePwd(ChangePwdVo changePwdVo) throws CommonException {

    }

    @Override
    public boolean validateUserLoginPwd(Long userId, String md5Password) throws CommonException {
        return false;
    }

    @Override
    public void registerFailedUsers() {

    }

    /*
    六位i引入，是否可删除
     */
    @Override
    public UserResponseVo findByMemberIdCondition(MemberIdRequestVo memberIdRequestVo) {
        return null;
    }
}
