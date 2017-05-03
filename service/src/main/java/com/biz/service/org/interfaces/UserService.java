package com.biz.service.org.interfaces;

import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.common.vo.CommonReqVoBindUserId;
import com.biz.gbck.common.vo.search.RecommendConditionVo;
import com.biz.gbck.common.vo.search.RecommendConditionVo2;
import com.biz.gbck.common.vo.search.SearchProductCondition;
import com.biz.gbck.dao.mysql.po.org.UserPo;
import com.biz.gbck.dao.redis.ro.org.ShopRo;
import com.biz.gbck.dao.redis.ro.org.UserRo;
import com.biz.gbck.enums.order.PaymentType;
import com.biz.gbck.enums.user.AuditStatus;
import com.biz.gbck.vo.search.SearchUserReqVo;
import com.biz.gbck.vo.user.MemberIdRequestVo;
import com.biz.gbck.vo.user.UserResponseVo;
import com.biz.vo.org.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
/**
 * 类说明：用户service
 *
 * @author xiaoyasong
 * @version 创建时间：2016年12月26日 上午9:24:55
 * @E-mail:yasong.xiao@biz-united.com.cn
 */
public interface UserService {
    /**
     * 根据商户id查找对应AdminId 集合
     *
     */
    List<Long> findAdminUserIdsByShopId(Long shopId, Boolean isAdmin) ;

    List<UserPo> findAdminUsersByShopId(Long shopId, Boolean isAdmin);

    /**
     * 新建用户
     */
   UserLoginResVo createUserAndShop(UserRegisterReqVo userRegisterReqVo)
            throws CommonException;

    /**
     * 创建用户
     */
    UserRo createUser(UserCreateVo userCreateVo, String admin) throws CommonException;

    /**
     * 创建用户
     */
     UserRo createUser(UserPo userPo) throws CommonException;

    /**
     * 保存用户
     */
    UserRo saveUser(UserPo userPo) ;

    UserLoginResVo buildRespVo(UserRo userRo) throws CommonException;

    Boolean allPaymentTypeIsInExcludePaymentTypes(ShopRo shopRo, PaymentType... paymentTypes);

    /**
     * 用户登录
     */
    UserLoginResVo login(UserLoginReqVo userLoginReqVo) throws CommonException ;

    UserLoginResVo autoLogin(AutoLoginReqVo autoLoginReqVo) throws CommonException ;

    void logout(CommonReqVoBindUserId commonReqVoBindUserId);

    /**
     * 用户通过忘记密码，上传新密码及短信验证码来修改密码
     */
    void forgotPassword(ForgotPasswordReqVo forgotPasswordReqVo) throws CommonException ;


    void tokenChange(AutoLoginReqVo reqVo) throws CommonException ;


    /**
     * 查找用户
     */
    UserRo findUser(Long userId) throws CommonException ;

    UserPo findAdminByShopId(Long shopId) ;

    List<UserPo> findByUserIds(Set<Long> userIds);

    /**
     * 通过电话号码查用户
     */
    UserRo findUserByMobile(String mobile) ;

    /**
     * 通过电话号码查用户
     */
    UserPo findUserPoByMobile(String mobile);

    /**
     * 通过电话号码查用户
     */
    public UserPo findUserPoByAccount(String mobile) ;

    /**
     * 禁用用户
     */
    void disableUser(Long userId);

    /**
     * 物理删除用户，慎用
     */
    @Deprecated
    @Transactional
    void destroyUserById(Long userId, String handler) ;
    /**
     * 查找商户所有员工
     */
    List<Long> findUserIdsByShopId(Long shopId);

    /**
     * 获取某区域下的所有 userId（后台使用 ，不要求高并发）
     *
     * @param areaType IArea.LEVEL_*
     */
    List<Long> findUserIdByAreaId(Integer areaType, Integer areaId) throws CommonException ;

    /**
     * 获取某个商户类型的用户Ids（后台使用 ，不要求高并发）
     */
    List<Long> findUserIdByShopType(Long shopTypeId);

    /**
     * 获取区域下的 某个商户类型的用户Id（后台使用 ，不要求高并发）
     */
    List<Long> findUserIdByAreaAndUserType(Integer areaType, Integer areaId,
                                                  Integer shopTypeId) throws CommonException ;
    /**
     * 获取所有用户Id（后台使用 ，不要求高并发）
     */
    List<Long> findAllUserId();

    /**
     * 根据店铺审核状态查询所有userId
     */
    List<Long> findAllUserIdByAuditStatus(AuditStatus auditStatus);

    /**
     * 根据店铺审核状态查询所有userPo
     */
    List<UserPo> findAllUserByAuditStatus(AuditStatus auditStatus);

    /**
     * 获取用户所属店铺的价格店铺
     */
    String getDepotIdByUserId(Long userId) throws CommonException;


    void bindSearchParam(SearchProductCondition condition) throws CommonException;

    RecommendConditionVo getRecommendConditionVo(Long userId) throws CommonException;

    RecommendConditionVo2 getRecommendConditionVo2(Long userId) throws CommonException ;

    /**
     * 更改手机号
     */
    void changeMobile(final UserChangeMobileReqVo reqVo) throws CommonException;

    /**
     * 通过手机号重置密码
     */
    void resetPassword(String mobile, String rawPassword, String handler) throws CommonException;

    /**
     * 修改用户头像
     */
    void changeAvatar(UserChangeAvatarReqVo reqVo) throws CommonException;

    Long findUserIdByBaidu(Long baiduUserId, String mobile, Integer geoCode) throws CommonException;

    void syncAllUserFromMysqlToRedis(Integer pageSize) ;

    /**
     * 同步UserPo到redis数据库
     */
    UserRo syncUserPoToRedis(UserPo userPo);

    List<UserPo> searchUsers(SearchUserReqVo vo) ;


    void updateUserStatus(Long id, Integer status);

    UserPo findUserById(Long id);

    List<UserPo> findAllUserPo();

    void removeUserRoByMobile(String mobile);

    boolean canUserCreateOrder(String mobile);

    /**
     * 从redis查找所有列入黑名单的用户号码
     */
    List<String> getBlockList();

    void changePwd(final ChangePwdVo changePwdVo) throws CommonException ;

    boolean validateUserLoginPwd(Long userId, String md5Password) throws CommonException ;

    /**
     * 注册失败到OMS用户列表, 定时任务
     */
     void registerFailedUsers();

   UserResponseVo findByMemberIdCondition(MemberIdRequestVo memberIdRequestVo); //刘伟引入的
}
