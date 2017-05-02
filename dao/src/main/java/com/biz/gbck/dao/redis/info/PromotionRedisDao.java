package com.biz.gbck.dao.redis.info;

import com.biz.gbck.common.ro.RedisKeyGenerator.Promotion;
import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.gbck.dao.redis.ro.info.PromotionRo;
import com.biz.redis.util.RedisUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 活动中心的消息
 *
 * @author gongshutao
 */
@Component
public class PromotionRedisDao extends CrudRedisDao<PromotionRo, Long> {

    private static final Logger logger = LoggerFactory.getLogger(PromotionRedisDao.class);

    public void save(PromotionRo promotionRo) {
        zadd(Promotion.getAllPromotionSortSetKey(), promotionRo.getIdx(),
            RedisUtil.toByteArray(promotionRo.getId()));
        hmset(Promotion.getPromotionHashKey(promotionRo.getId()), promotionRo.toMap());
        logger.debug("Update system promotion latest update time to now.");
        set(Promotion.getSystemLatestUpdateTimeHashKey(), RedisUtil.toByteArray(new Date()));
    }

    public void delete(Long id) {
        zrem(Promotion.getAllPromotionSortSetKey(), RedisUtil.toByteArray(id));
        del(Promotion.getPromotionHashKey(id));
    }

    public PromotionRo get(Long promotionId) {
        if (promotionId == null) {
            return null;
        }
        Map<byte[], byte[]> map = hgetAll(Promotion.getPromotionHashKey(promotionId));
        if (MapUtils.isNotEmpty(map)) {
            PromotionRo ro = new PromotionRo();
            ro.fromMap(map);
            return ro;
        } else
            return null;
    }

    public List<Long> getAllIds() {
        return RedisUtil.bytesSetToLongList(zRange(Promotion.getAllPromotionSortSetKey(), 0, -1));
    }

    public List<PromotionRo> findAll() {
        List<PromotionRo> list = new ArrayList<PromotionRo>();
        //按照Idx 从大到小给出
        List<Long> ids = RedisUtil
            .bytesSetToLongList(super.zrevrange(Promotion.getAllPromotionSortSetKey(), 0, -1));
        if (CollectionUtils.isNotEmpty(ids)) {
            for (Long id : ids) {
                PromotionRo ro = get(id);
                if (ro != null)
                    list.add(ro);
            }
        }
        return list;
    }

    public Date getSystemLatestUpdateTime(){
        return parseDate(get(Promotion.getSystemLatestUpdateTimeHashKey()));
    }

    public Date getUserLatestUpdateTime(Long userId){
        return parseDate(get(Promotion.getUserLatestUpdateTimeHashKey(userId)));
    }

    public void updateUserLatestFetchTime(Long userId){
        set(Promotion.getUserLatestUpdateTimeHashKey(userId), RedisUtil.toByteArray(new Date()));
    }

    private Date parseDate(byte[] latestUpdateTimeDataBytes) {
        if(latestUpdateTimeDataBytes == null){
            return null;
        }
        return RedisUtil.byteArrayToDate(latestUpdateTimeDataBytes);
    }
}
