package com.biz.gbck.dao.redis.repository.org;

import com.biz.core.util.StringTool;
import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.org.UserRo;
import com.biz.redis.util.RedisUtil;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Repository;

import java.util.Map;


@Repository
public class UserRedisDao extends CrudRedisDao<UserRo,String> {

    public void save(UserRo user) {
        super.save(user);
        mapMobile2User(user.getMobile(), user.getId());
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


}
