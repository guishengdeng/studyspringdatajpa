package com.biz.gbck.dao.redis.repository.org;

import com.biz.core.util.StringTool;
import com.biz.gbck.common.ro.RedisKeyGenerator;
import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.org.UserRo;
import com.biz.gbck.enums.user.ShopChannel;
import com.biz.redis.util.RedisUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.codelogger.utils.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;


@Repository
public class UserRedisDao extends CrudRedisDao<UserRo,String> {
    public static final String DEVICEID = "lastLoginDeviceToken";
    public static final String TOEKN = "lastToken";
    public static final String LASTUSERAGENT = "lastUserAgent";

    public void save(UserRo user) {
        super.save(user);
        mapMobile2User(user.getMobile(), user.getId());
        Timestamp createTime = user.getCreateTime();
        zadd(RedisKeyGenerator.User.getAllUserSortSetKey(),
                createTime == null ? System.currentTimeMillis() : createTime.getTime(),
                RedisUtil.toByteArray(user.getId()));
    }

    /**
     *存手机号码对应的UserId
     */
    public void mapMobile2User(String mobile, String userId) {
        String key = getKeyByParams(mobile);
        set(key, RedisUtil.toByteArray(userId));
    }



    public UserRo get(Long userId) {

        if (userId == null) {
            return null;
        }
        String userInfoKey = getHashKey(userId);
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

    public Long getUserIdByMobile(String mobile) {
        Long userId = getUserIdByMobileMap(mobile); //原来还有在string里面没有取出就从hash里面对应的羽取值
        return userId;
    }

    /**
     * 返回手机号对userId的映射
     */
    private Long getUserIdByMobileMap(String mobile) {
        String mobileToUserIdHashKey =getHashKey(mobile);
        byte[] bytes = get(mobileToUserIdHashKey);
        return bytes == null ? null : RedisUtil.byteArrayToLong(bytes);
    }

    public void clearToken(Long userId, String token, String userAgent) {
        hset(getHashKey(userId),TOEKN,
                RedisUtil.toByteArray(StringUtils.defaultString(token)));
        hset(getHashKey(userId),LASTUSERAGENT,
                RedisUtil.toByteArray(StringUtils.defaultString(userAgent)));
    }

    public void updateUserPassword(String mobile, String password, String originalPassword) {

        UserRo userRo = getUserByMobile(mobile);
        userRo.setPassword(password);
        userRo.setOriginalPassword(originalPassword);
        save(userRo);
    }

    public void removeMapMobileToUser(String mobile) {
        String[] keyAndField = RedisKeyGenerator.User.getMobileKeyAndField(mobile);
        hdel(keyAndField[0], keyAndField[1]);
        del(getKeyByParams(mobile));
    }


    @Override
    public void delete(UserRo userRo) {
        if (userRo != null) {
            super.delete(userRo);
            del(getKeyByParams(userRo.getMobile()));
            zrem(RedisKeyGenerator.User.getAllUserSortSetKey(),
                    RedisUtil.toByteArray(userRo.getId()));
        }
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

}
