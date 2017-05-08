package com.biz.service.sync;

import com.biz.core.logging.Loggers;
import com.biz.core.logging.LogstashLog;
import com.biz.gbck.dao.redis.CrudRedisDao;
import com.biz.redis.bean.BaseRedisObject;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 数据同步接口,要求所有需要保存到redis的实体的service实现此接口,以便于将mysql中的数据同步到redis中
 * @author yanweijin
 * @since 2016年8月16日
 * @usage 见{@link com.bozhi.search.user.UserServiceHelper}中对该方法的实现方式
 * @reviewer
 */
public interface DataSyncService {
	
	/**
	 * 将mysql数据同步到redis,请自行分页
	 * @author yanweijin
	 * @date 2016年8月16日
	 */
	@Transactional(noRollbackFor=Throwable.class,propagation=Propagation.REQUIRES_NEW)
	void syncMysql2Redis();
	
	/**
	 * 数据同步助手,传入PO,RO对应的repository,以及将po转换为ro的function,即可同步数据
	 * @author yanweijin
	 * @since 2016年8月16日
	 * @usage 
	 * @reviewer
	 */
	class DataSyncHelper{
		/**
		 * 便捷同步方式
		 * @author yanweijin
		 * @date 2016年8月16日
		 * @param repo 		PO的repository
		 * @param redisDao	RO的redisDao
		 * @param func		将PO转换为RO的function
		 */
		public static <PO,RO extends BaseRedisObject<? extends Number>> void syncData(
				JpaRepository<PO, ? extends Number> repo,
				CrudRedisDao<RO, ? extends Number> redisDao,
				final Function<PO,RO> func
		){
			/*
			 * [0] Thread.getStackTrace
			 * [1] DataSyncHelper.syncData
			 * [2] syncMysql2Redis(及实现类)
			 */
			final StackTraceElement signature =  Thread.currentThread().getStackTrace()[2];
			Loggers.SYNC.info(LogstashLog.newLog(signature, "data sync begin"));
			Page<PO> page = null;
			for (	Pageable pageable = new PageRequest(0,1000); 
					(page = repo.findAll(pageable)).hasContent(); 
					pageable = pageable.next()
				) {
				String logContent = String.format(
						"sync...page=%d,size=%d,total=%d",
						page.getNumber(),page.getSize(),page.getTotalElements());
				Loggers.SYNC.info(LogstashLog.newLog(signature, logContent));
				List<PO> content = page.getContent();
				List<RO> roList = Lists.transform(content, new Function<PO,RO>(){
					@Override
					public RO apply(PO input) {
						try {
							return func.apply(input);
						} catch (Exception e) {
							e.printStackTrace();
							Loggers.SYNC.error(LogstashLog.newExLog(e, signature));
							return null;
						}
					}
				});
				for (RO ro : roList) {
					redisDao.save(ro);
				}
//				redisDao.save(roList);
			}
		}
		
		public static SyncMeta resolveSyncMeta(DataSyncService impl){
			Method syncMethod = ReflectionUtils.findMethod(impl.getClass(), "syncMysql2Redis");
			SyncDefinition def = AnnotationUtils.findAnnotation(syncMethod, SyncDefinition.class);
//			SyncDefinition def = syncMethod.getAnnotation(SyncDefinition.class);
			if(def != null){
				return new SyncMeta(def.syncId(),def.desc(), impl);
			}else{
				return new SyncMeta(impl.getClass().getName()+"."+syncMethod.getName(),"", impl);
			}
		}
		
		public static class SyncMeta {
			private final String syncId;
			private final String desc;
			private final DataSyncService impl;
			private final String className;
			public String getSyncId() {
				return syncId;
			}
			public String getDesc() {
				return desc;
			}
			
			public DataSyncService getImpl() {
				return impl;
			}
			
			public String getClassName() {
				return className;
			}
			public SyncMeta(String syncId, String desc, DataSyncService impl) {
				super();
				this.syncId = syncId;
				this.desc = desc;
				this.impl = impl;
				this.className = impl.getClass().getName();
			}
		}
	}
}
