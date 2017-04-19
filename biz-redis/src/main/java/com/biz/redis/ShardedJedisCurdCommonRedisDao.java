package com.biz.redis;

import com.biz.redis.annotation.*;
import com.biz.redis.bean.BaseRedisObject;
import com.biz.redis.spi.ShardedJedisRedis;
import com.biz.redis.util.ExpressionUtil;
import com.biz.redis.util.RedisUtil;
import com.biz.redis.util.SortedSetAssist;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import redis.clients.jedis.PipelineBase;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;
import redis.clients.util.Pool;

import static com.google.common.collect.Lists.newArrayList;

public class ShardedJedisCurdCommonRedisDao<T extends BaseRedisObject<ID>, ID extends Serializable> extends ShardedJedisRedis {

    private static final String SEPARATOR = ":";

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private Class<T> entityClass;

    /**
     * ro key
     */
    private String keyPrefix = null;

    private String roLockKeyPrefix = null;

    private String roSortedSetKey = null;

    private boolean isExistRoSortedSet = false;

    private Expression expression = null;

    private Map<String, FieldSortedSet> fieldName_Annotation_Map = null;

    private Map<FieldSortedSet, SortedSetAssist<T, ID>> fieldInSortedSetMap = null;

    private Map<String, MethodSortedSet> methodName_Annotation_Map = null;

    private Map<MethodSortedSet, SortedSetAssist<T, ID>> methodInSortedSetMap = null;

    @SuppressWarnings("unchecked")
    public ShardedJedisCurdCommonRedisDao() {
        super();

        /** getClass().getGenericSuperclass()返回表示此 Class 所表示的实体（类、接口、基本类型或 void）
         *  的直接超类的 Type(Class<T>泛型中的类型)，然后将其转换ParameterizedType。。
         *  getActualTypeArguments()返回表示此类型实际类型参数的 Type 对象的数组。
         *  [0]就是这个数组中第一个了。。
         *  简而言之就是获得超类的泛型参数的实际类型。。*/
        entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        //类ro基础注解
        Ro ro = entityClass.getAnnotation(Ro.class);
        boolean isExistRo = (ro != null);
        if (isExistRo) {
            keyPrefix = ro.key().intern();
        } else {
            //keyPrefix.append(entityClass.getCanonicalName());
            throw new RuntimeException("not find Ro annotation");
        }

        //类roLock锁注解
        RoLock roLock = entityClass.getAnnotation(RoLock.class);
        boolean isExistRoLock = (roLock != null);
        if (isExistRoLock) {
            roLockKeyPrefix = roLock.key().intern();
        }

        ExpressionParser parser = new SpelExpressionParser();

        //类roSortedSet注解 基于类ro注解
        RoSortedSet roSortedSet = entityClass.getAnnotation(RoSortedSet.class);
        isExistRoSortedSet = (roSortedSet != null);
        if (isExistRoSortedSet) {
            roSortedSetKey = (getKeyPrefix() + SEPARATOR + roSortedSet.key()).intern();
            if (StringUtils.isNotBlank(roSortedSet.score())) {
                expression = parser.parseExpression(roSortedSet.score());
            }
        }

        if (entityClass != null) {
            /**返回类中所有字段，包括公共、保护、默认（包）访问和私有字段，但不包括继承的字段
             * entity.getFields();只返回对象所表示的类或接口的所有可访问公共字段
             * 在class中getDeclared**()方法返回的都是所有访问权限的字段、方法等；
             * 可看API
             * */
            Field[] fields = entityClass.getDeclaredFields();
            if (fields != null && fields.length > 0) {
                for (Field field : fields) {
                    if (!Modifier.isFinal(field.getModifiers())) {
                        field.setAccessible(true);//修改访问权限
                        //获取字段中包含的注解
                        FieldSortedSet fieldSortedSet = field.getAnnotation(FieldSortedSet.class);
                        boolean isExistFieldSortedSet = (fieldSortedSet == null ? false : true);
                        if (isExistFieldSortedSet) {
                            if (fieldInSortedSetMap == null) {
                                fieldInSortedSetMap = new HashMap<>();
                            }
                            if (fieldName_Annotation_Map == null) {
                                fieldName_Annotation_Map = new HashMap<>();
                            }
                            fieldInSortedSetMap.put(
                                    fieldSortedSet,
                                    new SortedSetAssist<T, ID>(
                                            field.getName(),
                                            StringUtils.isBlank(fieldSortedSet.prefix()) ? getKeyPrefix() + SEPARATOR + field.getName() : fieldSortedSet.prefix() + SEPARATOR + field.getName(),
                                            parser.parseExpression(fieldSortedSet.key()),
                                            StringUtils.isNotBlank(fieldSortedSet.score()) ? parser.parseExpression(fieldSortedSet.score()) : null
                                    )
                            );
                            fieldName_Annotation_Map.put(field.getName(), fieldSortedSet);
                        }
                    }
                }
            }

            Method[] methods = entityClass.getMethods();
            if (methods != null && methods.length > 0) {
                for (Method method : methods) {
                    if (!Modifier.isFinal(method.getModifiers())) {
                        method.setAccessible(true);//修改访问权限
                        //获取方法中包含的注解
                        MethodSortedSet methodSortedSet = method.getAnnotation(MethodSortedSet.class);
                        boolean isExistMethodSortedSet = (methodSortedSet == null ? false : true);
                        if (isExistMethodSortedSet) {
                            if (methodInSortedSetMap == null) {
                                methodInSortedSetMap = new HashMap<>();
                            }
                            if (methodName_Annotation_Map == null) {
                                methodName_Annotation_Map = new HashMap<>();
                            }
                            methodInSortedSetMap.put(
                                    methodSortedSet,
                                    new SortedSetAssist<T, ID>(
                                            method.getName(),
                                            StringUtils.isBlank(methodSortedSet.prefix()) ? getKeyPrefix() + SEPARATOR + method.getName() : methodSortedSet.prefix() + SEPARATOR + method.getName(),
                                            parser.parseExpression(methodSortedSet.key()),
                                            StringUtils.isNotBlank(methodSortedSet.score()) ? parser.parseExpression(methodSortedSet.score()) : null
                                    )
                            );
                            methodName_Annotation_Map.put(method.getName(), methodSortedSet);
                        }
                    }
                }
            }
        }
    }

    public String getKeyByParams(Object... params) {
        StringBuilder key = new StringBuilder(getKeyPrefix());
        if (params != null && params.length > 0) {
            for (Object param : params) {
                key.append(SEPARATOR).append(String.valueOf(param));
            }
        }
        return key.toString();
    }

    /**
     * @param fieldName 类属性名称
     * @param fieldValue 属性对应的值
     * @author jun.liu(by xiaoyu)
     * @date 2016年8月15日
     */
    public String getFieldSortedSetKey(String fieldName, Object fieldValue) {
        StringBuilder key = new StringBuilder();
        FieldSortedSet fieldSortedSet = fieldName_Annotation_Map.get(fieldName);
        if (fieldSortedSet == null) {
            throw new RuntimeException("[" + fieldName + "]--> FieldSortedSet is null");
        }
        String value = (fieldValue != null ? fieldValue.toString() : "");
        if ("".equals(fieldSortedSet.prefix())) {
            key.append(getKeyPrefix()).append(SEPARATOR).append(fieldName).append(SEPARATOR).append(value);
        } else {
            key.append(fieldSortedSet.prefix()).append(SEPARATOR).append(fieldName).append(SEPARATOR).append(value);
        }
        return key.toString();
    }

    /**
     * @param methodName 类方法的名称
     * @param fieldValue 方法对应属性的值
     * @author jun.liu(by xiaoyu)
     * @date 2016年8月15日
     */
    public String getMethodSortedSetKey(String methodName, Object fieldValue) {
        StringBuffer key = new StringBuffer();
        MethodSortedSet methodSortedSet = methodName_Annotation_Map.get(methodName);
        if (methodSortedSet == null)
            throw new RuntimeException("[" + methodName + "]--> MethodSortedSet is null");
        String value = (fieldValue != null ? fieldValue.toString() : "");
        if (methodSortedSet.prefix().equals("")) {
            key.append(getKeyPrefix()).append(SEPARATOR).append(methodName).append(SEPARATOR).append(value);
        } else {
            key.append(methodSortedSet.prefix()).append(SEPARATOR).append(methodName).append(SEPARATOR).append(value);
        }
        return key.toString();
    }

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public String getRoSortedSetKey() {
        return roSortedSetKey;
    }

    //ro
    public String getHashKey(Serializable id) {
        return new StringBuffer(getKeyPrefix()).append(SEPARATOR).append(id).toString();
    }

    //ro
    public String getHashKeyFromIdByte(byte[] byteId) {
        return new StringBuffer(getKeyPrefix()).append(SEPARATOR).append(new String(byteId)).toString();
    }

    //lock
    public String getRoLockKeyPrefix() {
        return roLockKeyPrefix.toString();
    }

    //lock
    public String getLockHashKey(Serializable id) {
        return new StringBuffer(getRoLockKeyPrefix()).append(SEPARATOR).append(id).toString();
    }

    //lock
    public String getLockHashKeyFromIdByte(byte[] byteId) {
        return getRoLockKeyPrefix() + SEPARATOR + new String(byteId);
    }

    public String getRoLockKeyByParams(Object... params) {
        StringBuilder key = new StringBuilder(getRoLockKeyPrefix());
        if (params != null && params.length > 0) {
            for (Object param : params) {
                key.append(SEPARATOR).append(param.toString());
            }
        }
        return key.toString();
    }

    /**
     * 实例化
     *
     * @author jun.liu(by xiaoyu)
     * @date 2016年8月12日
     */
    private T instance() {
        try {
            return entityClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询一个
     *
     * @author jun.liu(by xiaoyu)
     * @date 2016年8月12日
     */
    public T findOne(ID id) {
        if (id == null) {
            return null;
        }
        Map<byte[], byte[]> map = hgetAll(getHashKey(id));
        if (MapUtils.isNotEmpty(map)) {
            T ro = instance();
            ro.fromMap(map);
            return ro;
        } else
            return null;
    }

    /**
     * 根据roSortedSetKey得到对象的所有key集合
     *
     * @author jun.liu(by xiaoyu)
     * @date 2016年8月12日
     */
    public List<String> getKeyListFromSortedSet(String roSortedSetKey) {
        Set<byte[]> ids = zRange(roSortedSetKey, 0, -1);
        if (CollectionUtils.isNotEmpty(ids)) {
            List<String> keys = new ArrayList<>(ids.size());
            for (byte[] bid : ids) {
                keys.add(getHashKeyFromIdByte(bid));
            }
            return keys;
        }
        return newArrayList();
    }

    /**
     * 根据roSortedSetKey ids得到对象的所有key集合
     *
     * @author jun.liu(by xiaoyu)
     * @date 2016年8月12日
     */
    public List<String> getKeyListByIdSet(Set<byte[]> ids) {
        if (CollectionUtils.isNotEmpty(ids)) {
            List<String> keys = new ArrayList<>(ids.size());
            for (byte[] bid : ids) {
                keys.add(getHashKeyFromIdByte(bid));
            }
            return keys;
        }
        return newArrayList();
    }

    /**
     * 查询所有当前对象集合
     *
     * @author jun.liu(by xiaoyu)
     * @date 2016年8月12日
     */
    public List<T> findAll() {
        List<String> keys = getKeyListFromSortedSet(this.getRoSortedSetKey());
        return findByKeys(keys);
    }

    /**
     * 根据页面查询当前对象集合升序排列
     *
     * @param page 第一页为: 1
     * @author jun.liu(by xiaoyu)
     * @date 2016年8月12日
     */
    public List<T> findByScoreAsc(Integer page) {
        Set<byte[]> ids = zRange(this.getRoSortedSetKey(), Math.max(0, page - 1) * 10, Math.max(1, page) * 10 - 1);
        return findByKeys(toHashKeys(ids));
    }

    /**
     * 根据页面查询当前对象集合降序排列
     *
     * @param page 第一页为: 1
     * @author jun.liu(by xiaoyu)
     * @date 2016年8月12日
     */
    public List<T> findByScoreDesc(Integer page) {
        Set<byte[]> ids = zrevrange(this.getRoSortedSetKey(), Math.max(0, page - 1) * 10, Math.max(1, page) * 10 - 1);
        return findByKeys(toHashKeys(ids));
    }

    /**
     * 根据页面查询当前对象集合降序排列
     *
     * @param page 第一页为: 1
     * @author jun.liu(by xiaoyu)
     * @date 2016年8月12日
     */
    public List<T> findByScoreDesc(Integer page, Integer size) {
        if (size == null || size < 1) {
            size = 10;
        }
        Set<byte[]> ids = zrevrange(this.getRoSortedSetKey(), Math.max(0, page - 1) * size, Math.max(1, page) * size - 1);
        return findByKeys(toHashKeys(ids));
    }

    /**
     * 获取集合keys
     *
     * @author jun.liu(by xiaoyu)
     * @date 2016年8月12日
     */
    private List<String> toHashKeys(Collection<byte[]> ids) {
        if (CollectionUtils.isNotEmpty(ids)) {
            List<String> keys = new ArrayList<>(ids.size());
            for (byte[] bid : ids) {
                keys.add(getHashKeyFromIdByte(bid));
            }
            return keys;
        } else {
            return newArrayList();
        }
    }

    /**
     * 根据当前集合id查询集合对象
     *
     * @author jun.liu(by xiaoyu)
     * @date 2016年8月12日
     */
    public List<T> findByIds(Iterable<ID> ids) {
        if (ids == null || !ids.iterator().hasNext())
            return newArrayList();
        List<String> keys = new ArrayList<String>();
        for (ID id : ids) {
            keys.add(getHashKey(id));
        }
        return findByKeys(keys);
    }

    /**
     * 根据当前集合id查询集合对象
     *
     * @author jun.liu(by xiaoyu)
     * @date 2016年8月12日
     */
    public List<T> findByIdsWithNull(Iterable<ID> ids) {
        if (ids == null || !ids.iterator().hasNext())
            return newArrayList();
        List<String> keys = new ArrayList<String>();
        for (ID id : ids) {
            keys.add(getHashKey(id));
        }
        return findByKeysWithNull(keys);
    }


    /**
     * 根据当前集合id查询集合对象
     *
     * @author shutao.gong
     * @date 2016年8月13日
     */
    public List<T> findByStrIds(Iterable<String> ids) {
        if (ids == null || !ids.iterator().hasNext())
            return newArrayList();
        List<String> keys = new ArrayList<String>();
        for (String id : ids) {
            keys.add(getHashKey(id));
        }
        return findByKeys(keys);
    }

    /**
     * 根据id查询集合对象
     *
     * @author jun.liu(by xiaoyu)
     * @date 2016年8月12日
     */
    public List<T> findByIds(Set<byte[]> ids) {
        if (CollectionUtils.isEmpty(ids))
            return newArrayList();
        List<String> keys = new ArrayList<String>(ids.size());
        for (byte[] id : ids) {
            keys.add(getHashKeyFromIdByte(id));
        }
        return findByKeys(keys);
    }

    /**
     * 根据id集合查询集合map key id  value t对象
     *
     * @author jun.liu(by xiaoyu)
     * @date 2016年8月12日
     */
    public Map<ID, T> findMapByIds(Iterable<ID> ids) {
        if (ids == null || !ids.iterator().hasNext())
            return new HashMap<>();
        List<String> keys = new ArrayList<>();
        for (ID id : ids) {
            keys.add(getHashKey(id));
        }
        List<T> list = findByKeys(keys);
        if (!CollectionUtils.isEmpty(list)) {
            Map<ID, T> result = new HashMap<>();
            for (T t : list) {
                if (t != null) {
                    result.put(t.getId(), t);
                }
            }
            return result;
        }
        return new HashMap<>();
    }

    /**
     * 根据id查询map
     *
     * @author jun.liu(by xiaoyu)
     * @date 2016年8月12日
     */
    public Map<ID, T> findMapByIds(Set<byte[]> ids) {
        if (CollectionUtils.isEmpty(ids))
            return new HashMap<>();
        List<String> keys = new ArrayList<String>(ids.size());
        for (byte[] id : ids) {
            keys.add(getHashKeyFromIdByte(id));
        }
        List<T> list = findByKeys(keys);
        if (!CollectionUtils.isEmpty(list)) {
            Map<ID, T> result = new HashMap<>();
            for (T t : list) {
                if (t != null) {
                    result.put(t.getId(), t);
                }
            }
            return result;
        }
        return new HashMap<>();
    }

    /**
     * 根据keys查询集合对象
     *
     * @author jun.liu(by xiaoyu)
     * @date 2016年8月12日
     */
    @SuppressWarnings("unchecked")
    public List<T> findByKeys(List<String> keys) {
        List<T> result = newArrayList();
        if (CollectionUtils.isNotEmpty(keys)) {
            List<Object> list = pipeHgetall(keys);
            for (Object aList : list) {
                Map<byte[], byte[]> map = (Map<byte[], byte[]>) aList;
                if (map != null && !map.isEmpty()) {
                    T ro = instance();
                    try {
                        ro.fromMap(map);
                    } catch (Exception e) {
                        logger.error("ro.fromMap", e);
                    }
                    result.add(ro);
                }
            }
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public List<T> findByKeysWithNull(List<String> keys) {
        List<T> result = newArrayList();
        if (CollectionUtils.isNotEmpty(keys)) {
            List<Object> list = pipeHgetall(keys);
            for (int i = 0; i < list.size(); i++) {
                Map<byte[], byte[]> map = (Map<byte[], byte[]>) list.get(i);
                if (map == null || map.isEmpty()) {
                    // 这一行不能注释, 注释了打死!!!!!
                    result.add(null);
                } else {
                    T ro = instance();
                    try {
                        ro.fromMap(map);
                    } catch (Exception e) {
                        logger.error("ro.fromMap", e);
                    }
                    result.add(ro);
                }
            }
        }
        return result;
    }

    /**
     * @author jun.liu(by xiaoyu)
     * @date 2016年9月13日
     */
    public T findByKey(String key) {
        Map<byte[], byte[]> map = hgetAll(key);
        if (map == null || map.isEmpty()) {
            return null;
        } else {
            T ro = instance();
            Preconditions.checkArgument(ro != null);
            try {
                ro.fromMap(map);
            } catch (Exception e) {
                logger.error("ro.fromMap", e);
            }
            return ro;
        }
    }

    /**
     * 单个保存
     *
     * @author jun.liu(by xiaoyu)
     * @date 2016年8月12日
     */
    public void save(T ro) {
        if (isExistRoSortedSet) {
            long score = ExpressionUtil.getScore(new StandardEvaluationContext(ro), expression);
            zadd(this.getRoSortedSetKey(), score, RedisUtil.toByteArray(ro.getId()));
        }

        if (MapUtils.isNotEmpty(fieldInSortedSetMap)) {
            for (FieldSortedSet fieldSortedSet : fieldInSortedSetMap.keySet()) {
                SortedSetAssist<T, ID> field = fieldInSortedSetMap.get(fieldSortedSet);
                zadd(field.getKey(ro), field.getScore(ro), RedisUtil.toByteArray(ro.getId()));
            }
        }

        if (MapUtils.isNotEmpty(methodInSortedSetMap)) {
            for (MethodSortedSet methodSortedSet : methodInSortedSetMap.keySet()) {
                SortedSetAssist<T, ID> method = methodInSortedSetMap.get(methodSortedSet);
                zadd(method.getKey(ro), method.getScore(ro), RedisUtil.toByteArray(ro.getId()));
            }
        }
        hmset(getHashKey(ro.getId()), ro.toMap());
    }


    /**
     * 单个保存
     *
     * @author jun.liu(by xiaoyu)
     * @date 2016年8月12日
     */
    private void save(T ro, PipelineBase pipeline) {
        if (isExistRoSortedSet) {
            long score = ExpressionUtil.getScore(new StandardEvaluationContext(ro), expression);
            pipeline.zadd(RedisUtil.toByteArray(this.getRoSortedSetKey()), score, RedisUtil.toByteArray(ro.getId()));
        }

        if (MapUtils.isNotEmpty(fieldInSortedSetMap)) {
            for (FieldSortedSet fieldSortedSet : fieldInSortedSetMap.keySet()) {
                SortedSetAssist<T, ID> field = fieldInSortedSetMap.get(fieldSortedSet);
                pipeline.zadd(RedisUtil.toByteArray(field.getKey(ro)), field.getScore(ro), RedisUtil.toByteArray(ro.getId()));
            }
        }

        if (MapUtils.isNotEmpty(methodInSortedSetMap)) {
            for (MethodSortedSet methodSortedSet : methodInSortedSetMap.keySet()) {
                SortedSetAssist<T, ID> method = methodInSortedSetMap.get(methodSortedSet);
                pipeline.zadd(RedisUtil.toByteArray(method.getKey(ro)), method.getScore(ro), RedisUtil.toByteArray(ro.getId()));
            }
        }
        pipeline.hmset(RedisUtil.toByteArray(getHashKey(ro.getId())), ro.toMap());
    }

    /**
     * 批量保存
     *
     * @author jun.liu(by xiaoyu)
     * @date 2016年8月12日
     */
    @SuppressWarnings("deprecation")
    public void save(Iterable<T> ros) {
        //如果注入的是ComJedisRedis<ShardedJedis>
        Pool<ShardedJedis> pool = getPool();
        try (ShardedJedis jedis = pool.getResource()) {
            ShardedJedisPipeline jedisPipeline = jedis.pipelined();
            ros.forEach(ro -> this.save(ro, jedisPipeline));
            jedisPipeline.syncAndReturnAll();
        }
    }

    /**
     * 按照id删除
     *
     * @author jun.liu(by xiaoyu)
     * @date 2016年8月12日
     */
    public void delete(ID id) {
        delete(this.findOne(id));
    }

    /**
     * 按照对象删除
     *
     * @author jun.liu(by xiaoyu)
     * @date 2016年8月12日
     */
    public void delete(T ro) {
        if (ro != null) {
            if (isExistRoSortedSet) {
                zrem(this.getRoSortedSetKey(), RedisUtil.toByteArray(ro.getId()));
            }

            if (MapUtils.isNotEmpty(fieldInSortedSetMap)) {
                for (FieldSortedSet fieldSortedSet : fieldInSortedSetMap.keySet()) {
                    SortedSetAssist<T, ID> field = fieldInSortedSetMap.get(fieldSortedSet);
                    zrem(field.getKey(ro), RedisUtil.toByteArray(ro.getId()));
                }
            }

            if (MapUtils.isNotEmpty(methodInSortedSetMap)) {
                for (MethodSortedSet methodSortedSet : methodInSortedSetMap.keySet()) {
                    SortedSetAssist<T, ID> method = methodInSortedSetMap.get(methodSortedSet);
                    zrem(method.getKey(ro), RedisUtil.toByteArray(ro.getId()));
                }
            }
            del(getHashKey(ro.getId()));
        }
    }

    /**
     * 是否存在此key
     *
     * @author jun.liu(by xiaoyu)
     * @date 2016年8月12日
     */
    public boolean isExists(ID id) {
        return exists(getHashKey(id));
    }

    /**
     * 统计当前对象存放的个数
     *
     * @author jun.liu(by xiaoyu)
     * @date 2016年8月12日
     */
    public Long count() {
        if (isExistRoSortedSet) {
            return zCard(this.getRoSortedSetKey());
        }
        throw new UnsupportedOperationException();
    }

    public void deleteAll() {
        if (isExistRoSortedSet) {
            List<String> keys = getKeyListFromSortedSet(this.getRoSortedSetKey());
            if (CollectionUtils.isNotEmpty(keys)) {
                keys.forEach(super::del);
            }
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public void pipeDelete(List<String> keys) {
        Pool<ShardedJedis> pool = getPool();
        try (ShardedJedis jedis = pool.getResource()) {
            Preconditions.checkArgument(jedis != null);
            ShardedJedisPipeline jedisPipeline = jedis.pipelined();
            keys.forEach(jedisPipeline::del);
            jedisPipeline.syncAndReturnAll();
        }
    }
}
