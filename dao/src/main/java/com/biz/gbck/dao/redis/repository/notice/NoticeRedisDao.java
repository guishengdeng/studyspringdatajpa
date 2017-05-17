package com.biz.gbck.dao.redis.repository.notice;

import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.notice.NoticeRo;
import com.biz.redis.util.RedisUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用户消息
 * <p/>
 * 每个用户的消息 放在 一个sortset中，
 * 每个消息放在 一个Hash中，
 * 后台发布消息直接写入到相应的用户的sortset中。
 *
 */
@Repository
public class NoticeRedisDao extends CrudRedisDao<NoticeRo,String> {

    public void save(NoticeRo noticeRo) {
        hmset(getHashKey(noticeRo.getId()), noticeRo.toMap());
    }

    public void addToUser(Long noticeId, Long userId) {
        zadd(getKeyByParams(userId), noticeId.longValue(),
            RedisUtil.toByteArray(noticeId));
    }


    public NoticeRo get(Long noticeId) {
        if (noticeId == null) {
            return null;
        }
        Map<byte[], byte[]> map = hgetAll(getHashKey(noticeId));
        if (MapUtils.isNotEmpty(map)) {
            NoticeRo ro = new NoticeRo();
            ro.fromMap(map);
            return ro;
        } else
            return null;
    }


    public Long getRemainMSgCount(String userId){
    	return super.zCard(getKeyByParams(userId));
    }

    public List<NoticeRo> findAfter(Long userId, Long noticeId) {
        List<NoticeRo> list = new ArrayList<NoticeRo>();
        String lastId = "0";
        if (noticeId != null)
            lastId = "(" + noticeId.toString();
        List<Long> ids = RedisUtil.stringSetToLongList(
                zrangeByScore(getKeyByParams(userId), lastId, "+inf"));
        if (CollectionUtils.isNotEmpty(ids)) {
            for (Long id : ids) {
                NoticeRo ro = get(id);
                if (ro != null)
                    list.add(ro);
            }
        }
        return list;
    }

    public void deleteAfter(Long userId, Long lastNoticeId) {
        super.zremrangeByScore(getKeyByParams(userId), "-inf", "+inf");
    }


}
