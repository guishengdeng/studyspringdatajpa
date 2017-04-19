package com.biz.service;

/*import com.depotnearby.bean.PageControl;
import com.depotnearby.common.event.DepotnearbyEvent;
import com.depotnearby.common.mo.cache.CacheChangeMessageType;
import com.depotnearby.common.mo.cache.CacheMessage;
import com.depotnearby.common.spring.DepotnearbyTransactionManager;
import com.depotnearby.exception.CommonException;
import com.depotnearby.vo.mq.MQMessage;*/
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public abstract class CommonService implements ApplicationEventPublisherAware,
        ApplicationContextAware {

    @Autowired
    protected IdService idService;

  /*  @Autowired
    protected MQService mqService;

    @Autowired
    protected IdPool idPool;*/

    private ApplicationEventPublisher applicationEventPublisher;

    private static ApplicationContext APPLICATIONCONTEXT;

    private static final long CACHE_MAX_SIZE = 5000;

    private static final Map<Class, Map<String, LoadingCache<Object, Object>>> loadingCacheMap = new ConcurrentHashMap<>();


    @Override public void setApplicationContext(ApplicationContext applicationContext)
        throws BeansException {
        APPLICATIONCONTEXT = applicationContext;
    }

    public static ApplicationContext getContext() {
        return APPLICATIONCONTEXT;
    }


    public static <T> T getBean(Class<T> beanType) {
        return APPLICATIONCONTEXT == null ? null : APPLICATIONCONTEXT.getBean(beanType);
    }

    public static Object getBean(String beanName) {
        return APPLICATIONCONTEXT == null ? null : APPLICATIONCONTEXT.getBean(beanName);
    }

   /* protected void publishEvent(DepotnearbyEvent event) {
        DepotnearbyTransactionManager.publishEvent(event);
    }


    protected void sendCacheChangeMessage(CacheChangeMessageType type) throws CommonException {
        MQMessage msg = new MQMessage("cache", CacheMessage.newMsg(type), TimeUnit.SECONDS.toMillis(10), 5, null);
        mqService.sendMessage(msg);
    }

    protected PageRequest pageControl2PageRequest(PageControl pc)
        throws IllegalArgumentException {
        if (pc == null)
            throw new IllegalArgumentException("pc can not be null");
        return new PageRequest(pc.getCurrentPage() - 1, pc.getPageSize());
    }

    protected PageRequest pageControl2PageRequest(PageControl pc, Sort sort)
        throws IllegalArgumentException {
        if (pc == null)
            throw new IllegalArgumentException("pc can not be null");
        return new PageRequest(pc.getCurrentPage() - 1, pc.getPageSize(), sort);
    }
*/

    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @SuppressWarnings("unchecked")
    protected <K, V> V loadFromCacheOrCacheLoader(String cacheLoaderKey, CacheLoader<K , V> cacheLoader, K dataKey,
        TimeUnit cacheTimeUnit, long cacheDuration){

        Class<? extends CommonService> serviceClass = getClass();
        Map<String, LoadingCache<Object, Object>> serviceLoadingCacheMap =
            loadingCacheMap.get(serviceClass);
        if(serviceLoadingCacheMap == null) {
            synchronized (serviceClass){
                serviceLoadingCacheMap = loadingCacheMap.get(serviceClass);
                if(serviceLoadingCacheMap == null){
                    serviceLoadingCacheMap = new ConcurrentHashMap<>();
                    loadingCacheMap.put(serviceClass, serviceLoadingCacheMap);
                }
            }
        }
        LoadingCache<Object, Object> loadingCache = serviceLoadingCacheMap.get(cacheLoaderKey);
        if (loadingCache == null)
            synchronized (serviceClass) {
                loadingCache = serviceLoadingCacheMap.get(cacheLoaderKey);
                if (loadingCache == null) {
                    loadingCache = (LoadingCache<Object, Object>) CacheBuilder.newBuilder()
                        .maximumSize(CACHE_MAX_SIZE)
                        .expireAfterWrite(cacheDuration, cacheTimeUnit).build(cacheLoader);
                    serviceLoadingCacheMap.put(cacheLoaderKey, loadingCache);
                }
            }
        try {
            return (V) loadingCache.get(dataKey);
        } catch (ExecutionException | CacheLoader.InvalidCacheLoadException e) {
            return null;
        }
    }

}
