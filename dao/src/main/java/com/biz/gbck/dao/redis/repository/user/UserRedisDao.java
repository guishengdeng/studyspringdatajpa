package com.biz.gbck.dao.redis.repository.user;

import com.biz.core.util.StringTool;
import com.biz.gbck.common.dao.redis.CommonRedisDao;
import com.biz.gbck.common.ro.RedisKeyGenerator;
import com.biz.gbck.dao.redis.ro.user.UserRo;
import com.biz.gbck.enums.user.ShopChannel;
import com.biz.redis.util.RedisUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.codelogger.utils.CollectionUtils;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by defei on 3/11/16.
 */
@Component
public class UserRedisDao extends CommonRedisDao {

    public void save(UserRo user) {
        // 存用户信息
        hmset(RedisKeyGenerator.User.getUserInfoHashKey(user.getId()), user.toMap());
        mapMobile2User(user.getMobile(), user.getId());
        Timestamp createTime = user.getCreateTime();
        zadd(RedisKeyGenerator.User.getAllUserSortSetKey(),
            createTime == null ? System.currentTimeMillis() : createTime.getTime(),
            RedisUtil.toByteArray(user.getId()));
    }

    public void mapMobile2User(String mobile, Long userId) {
        // 存mobile
        String key = RedisKeyGenerator.User.getMobileToUserIdHashKey(mobile);
        set(key, RedisUtil.toByteArray(userId));
    }

    public void removeMapMobileToUser(String mobile) {
        String[] keyAndField = RedisKeyGenerator.User.getMobileKeyAndField(mobile);
        hdel(keyAndField[0], keyAndField[1]);
        del(RedisKeyGenerator.User.getMobileToUserIdHashKey(mobile));
    }

    public Long getUserIdByMobile(String mobile) {
        Long userId = getUserIdByMobileMap(mobile);
        if (userId == null) {
            String[] keyAndField = RedisKeyGenerator.User.getMobileKeyAndField(mobile);
            byte[] bytes = hget(keyAndField[0], keyAndField[1]);
            return bytes == null ? null : RedisUtil.byteArrayToLong(bytes);
        } else {
            return userId;
        }
    }

    /**
     * 返回手机号对userId的映射
     */
    private Long getUserIdByMobileMap(String mobile) {
        String mobileToUserIdHashKey = RedisKeyGenerator.User.getMobileToUserIdHashKey(mobile);
        byte[] bytes = get(mobileToUserIdHashKey);
        return bytes == null ? null : RedisUtil.byteArrayToLong(bytes);
    }

    /**
     * 根据店铺渠道查询用户id
     */
    public Long getUserIdByShopChannelAndChannelUserId(ShopChannel shopChannel, Long channelUserId){

        String channelUserIdHashKey = RedisKeyGenerator.User.getChannelUserIdToUserIdHashKey(
            shopChannel, channelUserId);
        byte[] bytes = get(channelUserIdHashKey);
        return bytes == null ? null : RedisUtil.byteArrayToLong(bytes);
    }

    /**
     * 映射用户id和店铺渠道及渠道用户id之间的关系
     */
    public void mapShopChannelAndChannelIdToUser(ShopChannel shopChannel, Long channelUserId, Long userId) {
        // 存mobile
        String key = RedisKeyGenerator.User.getChannelUserIdToUserIdHashKey(shopChannel, channelUserId);
        set(key, RedisUtil.toByteArray(userId));
    }

    /**
    * 根据店铺渠道mobile查询渠道用户id
    */
    public Long getChannelUserIdByShopChannelAndChannelMobile(ShopChannel shopChannel, String channelMobile){

        String channelUserIdHashKey = RedisKeyGenerator.User.getChannelMobileToChannelUserIdHashKey(
                shopChannel, channelMobile);
        byte[] bytes = get(channelUserIdHashKey);
        return bytes == null ? null : RedisUtil.byteArrayToLong(bytes);
    }

    /**
     * 映射渠道用户mobile和用户id之间的关系
     */
    public void mapShopChannelAndChannelMobileToChannelUserId(ShopChannel shopChannel, String channelMobile, Long
            channelUserId) {
        // 存mobile
        String key = RedisKeyGenerator.User.getChannelMobileToChannelUserIdHashKey(shopChannel, channelMobile);
        set(key, RedisUtil.toByteArray(channelUserId));
    }

    public UserRo get(Long userId) {

        if (userId == null) {
            return null;
        }
        String userInfoKey = RedisKeyGenerator.User.getUserInfoHashKey(userId);
        Map<byte[], byte[]> map = hgetAll(userInfoKey);
        if (MapUtils.isNotEmpty(map)) {
            UserRo userInfo = new UserRo();
            userInfo.fromMap(map);
            return userInfo;
        } else
            return null;
    }

    public UserRo getUserByMobile(String mobile) {

        if (!StringTool.isMobileValid(mobile)) {
            return null;
        }
        Long userIdByMobile = getUserIdByMobile(mobile);
        UserRo userRo = get(userIdByMobile);
        if (userRo != null && !mobile.equalsIgnoreCase(userRo.getMobile())) {
            return null;
        } else {
            return userRo;
        }
    }

    public void updateUserPassword(String mobile, String password, String originalPassword) {

        UserRo userRo = getUserByMobile(mobile);
        userRo.setPassword(password);
        userRo.setOriginalPassword(originalPassword);
        save(userRo);
    }

    public void delete(UserRo userRo) {
        if (userRo != null) {
            String[] keyAndField = RedisKeyGenerator.User.getMobileKeyAndField(userRo.getMobile());
            hdel(keyAndField[0], keyAndField[1]);
            del(RedisKeyGenerator.User.getMobileToUserIdHashKey(userRo.getMobile()));
            del(RedisKeyGenerator.User.getUserInfoHashKey(userRo.getId()));
            zrem(RedisKeyGenerator.User.getAllUserSortSetKey(),
                RedisUtil.toByteArray(userRo.getId()));
        }
    }

    public void saveWithToken(Long userId, String deviceId, String token, String userAgent) {
        hset(RedisKeyGenerator.User.getUserInfoHashKey(userId),
            RedisKeyGenerator.User.DEVICEID,
            RedisUtil.toByteArray(StringUtils.defaultString(deviceId)));
        hset(RedisKeyGenerator.User.getUserInfoHashKey(userId), RedisKeyGenerator.User.TOEKN,
            RedisUtil.toByteArray(StringUtils.defaultString(token)));
        hset(RedisKeyGenerator.User.getUserInfoHashKey(userId),
            RedisKeyGenerator.User.LASTUSERAGENT,
            RedisUtil.toByteArray(StringUtils.defaultString(userAgent)));
    }

    public void clearToken(Long userId, String token, String userAgent) {
        hset(RedisKeyGenerator.User.getUserInfoHashKey(userId), RedisKeyGenerator.User.TOEKN,
            RedisUtil.toByteArray(StringUtils.defaultString(token)));
        hset(RedisKeyGenerator.User.getUserInfoHashKey(userId),
            RedisKeyGenerator.User.LASTUSERAGENT,
            RedisUtil.toByteArray(StringUtils.defaultString(userAgent)));
    }

    //存储注册老系统失败的用户Id
    public void putRegisterFailedUserId(Long userId) {
        zadd(RedisKeyGenerator.User.getRegisterFailedUserHashKey(), System.currentTimeMillis(),
            RedisUtil.toByteArray(userId));
    }

    //获取所有注册老系统失败的用户
    public List<UserRo> getSyncFailedUsers() {

        List<Long> userIds = RedisUtil.bytesSetToLongList(
            zRange(RedisKeyGenerator.User.getRegisterFailedUserHashKey(), 0, -1));
        List<UserRo> users = newArrayList();
        if (CollectionUtils.isNotEmpty(userIds)) {
            for (Long userId : userIds) {
                users.add(get(userId));
            }

        }
        return users;
    }

    //删除注册老系统失败的用户Id
    public void removeSyncFailedUser(Long userId) {
        zrem(RedisKeyGenerator.User.getRegisterFailedUserHashKey(), RedisUtil.toByteArray(userId));
    }

    //重置密码到oms失败的用户Id
    public void putResetPwdToOMSFailedUserId(Long userId) {
        zadd(RedisKeyGenerator.User.getResetPwdToOMSFailedUserHashKey(), System.currentTimeMillis(),
                RedisUtil.toByteArray(userId));
    }
//
//    //获取所有重置密码到oms的用户
//    public List<UserRo> getResetPwdToOMSFailedUsers() {
//
//        List<Long> userIds = RedisUtil.bytesSetToLongList(
//                zRange(RedisKeyGenerator.User.getResetPwdToOMSFailedUserHashKey(), 0, -1));
//        List<UserRo> users = newArrayList();
//        if (CollectionUtils.isNotEmpty(userIds)) {
//            for (Long userId : userIds) {
//                users.add(get(userId));
//            }
//
//        }
//        return users;
//    }
//
//    //删除重置密码到oms的用户Id
//    public void removeResetPwdToOMSFailedUser(Long userId) {
//        zrem(RedisKeyGenerator.User.getResetPwdToOMSFailedUserHashKey(), RedisUtil.toByteArray(userId));
//    }

    public boolean getUserCanOrder(String mobile) {
        return zscore(RedisKeyGenerator.User.getBlockCreateOrderUserKey(),
            RedisUtil.toByteArray(mobile)) == null;
    }

    public List<String> getBlackList() {

        String[] blackList = RedisUtil.bytesSetToStringArray(
            zRange(RedisKeyGenerator.User.getBlockCreateOrderUserKey(), 0, -1));
        if(blackList == null){
            return newArrayList();
        } else {
            return newArrayList(blackList);
        }
    }

    public void updateBlackList(String[] list) {
        List<String> oldBlackList = getBlackList();
        List<String> newBlackList = new ArrayList<>();

        if (list != null) {
            for (String s : list) {
                String fixedMobile = StringUtils.trimToEmpty(s);
                if (fixedMobile.length() == 11) {
                    newBlackList.add(fixedMobile);
                }
            }
        }
        oldBlackList.removeAll(newBlackList);
        for (String old : oldBlackList) {
            zrem(RedisKeyGenerator.User.getBlockCreateOrderUserKey(), RedisUtil.toByteArray(old));
        }
        for (String n : newBlackList) {
            zadd(RedisKeyGenerator.User.getBlockCreateOrderUserKey(), System.currentTimeMillis(),
                RedisUtil.toByteArray(n));
        }
    }
}
