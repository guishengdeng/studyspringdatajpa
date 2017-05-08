package com.biz.service.org;

import com.biz.gbck.dao.mysql.repository.org.ShopDao;
import com.biz.gbck.dao.mysql.repository.org.ShopRepositoryImpl;
import com.biz.gbck.dao.mysql.repository.org.UserDao;
import com.biz.gbck.dao.mysql.repository.org.UserRepositoryImpl;
import com.biz.gbck.dao.redis.IdRedisDao;
import com.biz.gbck.enums.IdType;
import org.codelogger.utils.ValueUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by defei on 3/20/16.
 */
@Service
public class IdPool {

    private static final Logger log = LoggerFactory.getLogger(IdPool.class);

    private static final int POOLED_KEY_COUNT = 10000;

    private static final Long DEFAULT_FROM = 100000L;

//    @Autowired
//    private IdRedisDao idRedisDao;
//
//    @Autowired
//    private UserDao userDao;
//
//    @Autowired
//    private ShopDao shopDao;

//    /**
//     * 获取池中生成好的Id
//     */
//    public Long getNextId(IdType idType) {
//        long count = idRedisDao.remainCount(idType);
//        if (count < POOLED_KEY_COUNT) {
//            checkRemain();
//        }
//        return idRedisDao.nextLongId(idType);
//    }
//
//    /**
//     * 检查ID池中剩余ID数量
//     * 如果ID池中剩余的Id数量小于阀值,生成阀值的10倍个数量的Id放进去。
//     */
//    @PostConstruct
//    public void init() {
//        checkRemain();
//    }
//
//    private synchronized void checkRemain() {
//        log.info("id pool check remain");
//        try {
//            for (IdType idType : IdType.values()) {
//                long count = idRedisDao.remainCount(idType);
//                while (count < POOLED_KEY_COUNT) {
//                    Long maxDbId = 0L;
//                    if(idType == IdType.USER) {
//                        maxDbId = ValueUtils.getValue(userDao.findMaxUserId());
//                    } else if(idType == IdType.SHOP){
//                        maxDbId = ValueUtils.getValue(shopDao.findMaxUserId());
//                    }
//                    long from = idRedisDao.getFromNum(idType, Math.max(DEFAULT_FROM, maxDbId + 1)).longValue();
//                    int size = POOLED_KEY_COUNT;
//                    idRedisDao.generatorLongIds(idType, from, size);
//                    count = idRedisDao.remainCount(idType);
//                    log.debug(" {} queue remain size : {}", idType, count);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error("IdPoolImpl checkRemain with error{}", e.getMessage());
//        }
//    }

}
