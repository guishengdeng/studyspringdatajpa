package com.biz.gbck.dao.redis.repository.user;

import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.user.UserRo;
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


}
