package com.biz.service.org.interfaces;

import com.biz.gbck.dao.redis.ro.user.UserRo;
import com.biz.gbck.enums.user.AuditStatus;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.vo.user.*;
import java.util.List;

/**
 * 类说明：用户service
 *
 * @author xiaoyasong
 * @version 创建时间：2016年12月26日 上午9:24:55
 * @E-mail:yasong.xiao@biz-united.com.cn
 */
public interface UserService {

    /**
     * 方法说明:用户注册
     *
     * @throws DepotNextDoorException
     * @author xiaoyasong
     * @version 2016年12月26日
     */
    RegisterResponseVo register(RegisterRequestVo req) throws DepotNextDoorException;

    /**
     * 方法说明:用户登录
     *
     * @throws DepotNextDoorException
     * @author xiaoyasong
     * @version 2016年12月26日
     */
    LoginResponseVo login(LoginRequestVo req) throws DepotNextDoorException;


    /**
     * 方法说明:校验密码是否正确
     *
     * @throws DepotNextDoorException
     * @author xiaoyasong
     * @version 2016年12月26日
     */
    boolean verifyPassword(VerifyPasswordRequestVo reqVo) throws DepotNextDoorException;


    /**
     * 方法说明:修改密码
     *
     * @return true修改成功   false修改失败
     * @throws DepotNextDoorException
     * @author xiaoyasong
     * @version 2016年12月26日
     */
    boolean updatePassword(UpdatePasswordRequestVo req) throws DepotNextDoorException;

    /**
     * 根据账号(手机号、邮箱、用户名+渠道编码) 查询用户信息
     *
     * @throws DepotNextDoorException
     */
    UserResponseVo findByAccountNameCondition(AccountNameRequestVo reqVo) throws DepotNextDoorException;

    /**
     * 根据用户id+渠道编码 查询用户信息
     *
     * @throws DepotNextDoorException
     */
    UserResponseVo findByUserIdCondition(UserIdRequestVo reqVo) throws DepotNextDoorException;

    /**
     * 根据用户id+渠道编码 查询用户信息
     *
     * @throws DepotNextDoorException
     */
    UserResponseVo findByMemberIdCondition(MemberIdRequestVo reqVo) throws DepotNextDoorException;

    /**
     * 根据用户ids(非中台memberId)查询用户信息
     *
     * @throws DepotNextDoorException
     */
    List<UserResponseVo> findByBatchUserIdCondition(BatchUserIdRequestVo reqVo) throws DepotNextDoorException;

    /**
     * 根据用户ids(非中台memberId)查询用户信息
     *
     * @throws DepotNextDoorException
     */
    List<UserResponseVo> findByBatchMemberIdCondition(BatchMemberIdRequestVo reqVo) throws DepotNextDoorException;

    /**
     * 根据用户名模糊查询用户信息
     *
     * @param accountName (手机号、邮箱、用户名)
     * @throws DepotNextDoorException
     */
    List<UserResponseVo> findByAccountName(String accountName) throws DepotNextDoorException;

    /**
     * 修改用户信息
     *
     * @throws DepotNextDoorException
     * @author Nian.Li
     */
    UpdateUserResponseVo updateUserInfo(UpdateUserRequestVo reqVo) throws DepotNextDoorException;

    /**
     * 方法说明:重置密码
     *
     * @return true找回成功   false找回失败
     * @throws DepotNextDoorException
     * @author xiaoyasong
     * @version 2017年1月12日
     */
    boolean retrievePassword(RetrievePasswordRequestVo req) throws DepotNextDoorException;

    /**
     * 通过userId获取密保问题
     *
     * @Author JKLiues
     * @Date 2017/2/17 17:39
     */
    List<SecretQuestionVo> findSecretQuestion(Long userId);

    /**
     * 保存密保问题
     *
     * @throws DepotNextDoorException
     * @Author JKLiues
     * @Date 2017/2/17 18:56
     */
    void saveSecretQuestion(Long userId, List<SecretQuestionVo> vo) throws DepotNextDoorException;

    /**
     * 更新头像
     *
     * @Author JKLiues
     * @Date 2017/2/19 12:33
     */
    void updateAvatar(UpdateUserAvatarRequestVo reqVo) throws DepotNextDoorException;

    /**
     * 根据账号(手机号、邮箱、用户名+渠道编码) 查询用户信息
     *
     * @throws DepotNextDoorException
     */
    UpdateUserResponseVo findByAccountName(AccountNameRequestVo reqVo) throws DepotNextDoorException;

    /**
     * 根据商户类型查询商户id集合
     * @Author dylan
     * @param shopTypeId
     * @return
     */
    List<Long> findUserIdByShopType(Long shopTypeId);

    /**
     * 根据手机号码查询用户
     * @param mobile
     * @return
     */
    UserRo findUserByMobile(String mobile);

    public List<Long> findAllUserIdByAuditStatus(AuditStatus auditStatus);
}
