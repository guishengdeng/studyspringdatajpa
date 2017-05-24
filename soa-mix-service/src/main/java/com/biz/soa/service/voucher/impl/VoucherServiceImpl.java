package com.biz.soa.service.voucher.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.codelogger.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.biz.gbck.common.model.order.IOrderItemVo;
import com.biz.gbck.common.model.voucher.VoucherConfigure;
import com.biz.gbck.common.voucher.VoucherRoToVoucherPo;
import com.biz.gbck.dao.mysql.po.org.ShopPo;
import com.biz.gbck.dao.mysql.po.org.UserPo;
import com.biz.gbck.dao.mysql.po.voucher.VoucherCategory;
import com.biz.gbck.dao.mysql.po.voucher.VoucherPo;
import com.biz.gbck.dao.mysql.po.voucher.VoucherTypePo;
import com.biz.gbck.dao.mysql.po.voucher.VoucherTypeStatus;
import com.biz.gbck.dao.mysql.repository.voucher.VoucherDao;
import com.biz.gbck.dao.mysql.repository.voucher.VoucherRepository;
import com.biz.gbck.dao.mysql.repository.voucher.VoucherTypeRepository;
import com.biz.gbck.dao.mysql.specification.voucher.VoucherSearchSpecification;
//import com.biz.gbck.dao.redis.repository.product.bbc.ProductRedisDao;
import com.biz.gbck.dao.redis.repository.voucher.VoucherRedisDao;
import com.biz.gbck.dao.redis.repository.voucher.VoucherTypeRedisDao;
import com.biz.gbck.dao.redis.ro.org.ShopTypeRo;
import com.biz.gbck.dao.redis.ro.org.UserRo;
import com.biz.gbck.dao.redis.ro.voucher.VoucherConfigureRo;
import com.biz.gbck.dao.redis.ro.voucher.VoucherRo;
import com.biz.gbck.dao.redis.ro.voucher.VoucherTypeRo;
import com.biz.gbck.dao.redis.ro.voucher.VoucherTypeWithQuantity;
import com.biz.gbck.util.DateTool;
import com.biz.gbck.vo.product.frontend.ProductListItemVo;
import com.biz.gbck.vo.spring.PageVO;
import com.biz.gbck.vo.voucher.UserVoucherStatisticResultVo;
import com.biz.gbck.vo.voucher.VoucherSearchVo;
import com.biz.service.AbstractBaseService;
import com.biz.service.predicate.VoucherNotExpirePredicate;
import com.biz.service.predicate.VoucherTypePredicate;
import com.biz.soa.feign.client.global.NoticeFeignClient;
import com.biz.soa.feign.client.org.ShopFeignClient;
import com.biz.soa.feign.client.org.ShopTypeFeignClient;
import com.biz.soa.feign.client.sms.SMSFeignClient;
import com.biz.soa.service.voucher.VoucherConfigureService;
import com.biz.soa.service.voucher.VoucherService;
import com.biz.soa.service.voucher.VoucherTypeService;
import com.biz.vo.notify.NotifyVo;
import com.biz.vo.voucher.ShopCraftVoucherVo;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service
public class VoucherServiceImpl extends AbstractBaseService implements VoucherService {

	public static final int THREE_DAYS_PERIOD = 3 * 24 * 60 * 60 * 1000;
	
	@Autowired
	private VoucherTypeRedisDao voucherTypeRedisDao;
	
	@Autowired
	private VoucherRedisDao voucherRedisDao;
	
	@Autowired
	private VoucherRepository voucherRepository;
	
	@Autowired
	private VoucherTypeRepository voucherTypeRepository;
	
	@Autowired
	private VoucherTypeService voucherTypeService;
	
	@Autowired
	private ShopFeignClient shopFeignClient;
	
	@Autowired
	private NoticeFeignClient noticeFeignClient;
	
	@Autowired
	private ShopTypeFeignClient shopTypeFeignClient;
	
//	@Autowired
//	private UserFeignClient userFeignClient;
	
	@Autowired
	private SMSFeignClient sMSFeignClient;
	
	@Autowired
	private VoucherConfigureService configureService;
	
//	@Autowired
//	private ProductFeignClient productRedisDao;
	
	@Autowired 
	private VoucherDao voucherRepositoryImpl;
	
	@Override
	public Collection<VoucherRo> findUsableVouchersByUserIdAndVoucherType(Long userId, Long voucherTypeId) {
		VoucherTypeRo voucherTypeRo = voucherTypeRedisDao.getVoucherTypeRoById(voucherTypeId);
      if (voucherTypeRo.isExpire()) {
          return new ArrayList<VoucherRo>();
      }
      List<VoucherRo> usableVouchers = voucherRedisDao.listAllUsableVoucher(userId);
      if (CollectionUtils.isEmpty(usableVouchers)) {
          return new ArrayList<VoucherRo>();
      }
      Collection<VoucherRo> matchedVoucherRos =
          Collections2.filter(usableVouchers, new VoucherTypePredicate(voucherTypeId));
      matchedVoucherRos =
          Collections2.filter(matchedVoucherRos, new VoucherNotExpirePredicate(voucherTypeRo));
      return matchedVoucherRos;
	}

	@Override
	public VoucherRo fetchVoucher(Long userId, VoucherTypeRo voucherTypeRo) {
		return voucherRedisDao.bindVoucherToUser(userId, voucherTypeRo);
	}

	@Override
	public void useVoucher(Long userId, Long voucherId, Long orderId, Integer offSetAmount) {
      voucherRedisDao.use(userId, voucherId);
      voucherRepository.useVoucher(voucherId, orderId, offSetAmount, DateTool.nowTimestamp());
	}

	@Override
	public void expireVoucher(Long userId, Long voucherId) {
		voucherRedisDao.expire(userId, voucherId);
	}

	@Override
	public Map<String, List<VoucherRo>> allVouchers(Long userId) {
		return voucherRedisDao.allVouchers(userId);
	}

	@Override
	public List<VoucherPo> listAllVouchersByUserId(Long userId) {
		return voucherRepository.findByBindingUserId(userId);
	}

	@Override
	public Map<Long, VoucherRo> unusedVouchers(Long userId) {
		Map<Long, VoucherRo> map = Maps.newHashMap();
      for (VoucherRo voucherRo : this.allVouchers(userId).get("unused")) {
          map.put(voucherRo.getId(), voucherRo);
      }
      return map;
	}

	@Override
	public Integer getUserUseableVoucherCount(Long userId) {
		Map<String, List<VoucherRo>> userVoucherMap = allVouchers(userId);
      return userVoucherMap == null ? 0 : CollectionUtils.getCount(userVoucherMap.get("unused"));
	}

	@Override
	public Map<Long, List<VoucherRo>> divideUnusedVouchersByCategory(Long userId) {
      Map<Long, List<VoucherRo>> voucherRoMap = Maps.newHashMap();
      for (VoucherRo voucherRo : this.allVouchers(userId).get("unused")) {
          VoucherTypeRo voucherTypeRo =
              voucherTypeService.getVoucherTypeRoById(voucherRo.getVoucherTypeId());
          List<VoucherRo> voucherRos;

          Long key = voucherTypeRo.getCategoryId() != null ?
              voucherTypeRo.getCategoryId() :
              VoucherCategory.NONE.getValue();

          if (voucherRoMap.containsKey(key)) {
              voucherRos = voucherRoMap.get(key);
          } else {
              voucherRos = new ArrayList<VoucherRo>();
          }

          voucherRos.add(voucherRo);
          voucherRoMap.put(key, voucherRos);
      }

      if (logger.isDebugEnabled()) {
          Iterator<Map.Entry<Long, List<VoucherRo>>> iterator =
              voucherRoMap.entrySet().iterator();
          while (iterator.hasNext()) {
              Map.Entry<Long, List<VoucherRo>> entry = iterator.next();
              logger.debug("category {} can use voucher count {} ", entry.getKey(),
                  entry.getValue().size());
          }
      }
      return voucherRoMap;
	}

	@Override
	public Map<Long, List<VoucherRo>> divideVouchersByVoucherType(Collection<VoucherRo> voucherRos) {
		 Map<Long, List<VoucherRo>> voucherRoMap = Maps.newHashMap();
       if (voucherRos == null)
           return voucherRoMap;
       for (VoucherRo voucherRo : voucherRos) {
           List<VoucherRo> voucherRoList;
           if (voucherRoMap.containsKey(voucherRo.getVoucherTypeId())) {
               voucherRoList = voucherRoMap.get(voucherRo.getVoucherTypeId());
           } else {
               voucherRoList = new ArrayList<VoucherRo>();
           }
           voucherRoList.add(voucherRo);
           voucherRoMap.put(voucherRo.getVoucherTypeId(), voucherRoList);
       }
       return voucherRoMap;
	}

	@Override
	public int getAvailVoucherCount(List<ProductListItemVo> items, UserRo userRo) throws Exception {
		int count = 0;
		// TODO Auto-generated method stub
      ShopPo shopRo = shopFeignClient.findShopRoById(userRo.getShopId());
      List<Long> categoryIds = new ArrayList<Long>();
      Long shopTypeId = shopRo.getShopType().getId();
      Map<Long, List<VoucherRo>> categoryVouchersMap =  divideUnusedVouchersByCategory(Long.parseLong(userRo.getId()));

      for (ProductListItemVo item : items) {
          logger.debug("item.cateogry is {}", item.getProductType());
          if (!categoryIds.contains(item.getProductType()) && categoryVouchersMap
              .containsKey(item.getProductType())) {
              for (VoucherRo voucherRo : categoryVouchersMap.get(item.getProductType())) {
                  VoucherTypeRo voucherTypeRo =
                      voucherTypeService.getVoucherTypeRoById(voucherRo.getVoucherTypeId());
                  if (Objects.equals(voucherTypeRo.getCategoryId(), item.getProductType())
                      && voucherTypeRo.canUseByShopType(shopTypeId)) {
                      count = count + 1;
                  }
              }
              categoryIds.add(Long.valueOf(item.getProductType()));
          }
      }
      if (categoryVouchersMap.containsKey(VoucherCategory.NONE.getValue())) {
          for (VoucherRo voucherRo : categoryVouchersMap.get(VoucherCategory.NONE.getValue())) {
              VoucherTypeRo voucherTypeRo =
                  voucherTypeService.getVoucherTypeRoById(voucherRo.getVoucherTypeId());
              if (voucherTypeRo.canUseByShopType(shopTypeId)) {
                  count = count + 1;
              }
          }
      }
      return count;
	}

	@Override
	public void dispatcherVoucher(List<Long> userIds, VoucherTypeRo voucherTypeRo, Integer dispatcherCnt, String loginUsername) {
		for (Long userId : userIds) {
          dispatcherVoucher(userId, voucherTypeRo, dispatcherCnt, loginUsername);
      }
	}

	@Override
	public void dispatcherVoucher(Long userId, VoucherTypeRo voucherTypeRo, Integer quantity, String loginUsername) {
		logger.debug("Dispatch {} 数量 {} to users {}", voucherTypeRo.getName(), quantity, userId);
      for (int i = 0; i < quantity; i++) {
          VoucherRo ro = voucherRedisDao.bindVoucherToUser(userId, voucherTypeRo);
          System.out.println("RO:"+ro.getId()+"and:"+ro.getVoucherTypeId());
          if (ro != null) {
              voucherRepository.save((new VoucherRoToVoucherPo(loginUsername)).apply(ro));
              String title = "您收到新的优惠券";
              String content =
                  "您收到一张" + voucherTypeRo.getFaceValue() / 100 + "元 " + voucherTypeRo.getName()
                      + "，购买商品结算可抵扣现金哟，点击查看详情";
              try {
            	// TODO Auto-generated method stub
            	  NotifyVo notifyVo = new NotifyVo();
            	  notifyVo.setTitle(title);
            	  notifyVo.setNotifyContent(content);
            	  //TODO
//            	  noticeFeignClient.sendMsgToUser(userId, title, content,
//                      "depotnearby://redirect?target=voucherList");
              } catch (Exception e) {
                  logger.error("dispatcherVoucher", e);
              }
          } else {
              logger.error("优惠券 null id:{}", voucherTypeRo.getId());
          }
      }
	}

	@Override
	public boolean validateDispatcherAction(List<Long> userIds, Long shopTypeId, Long voucherTypeId,
			int dispatcherCount) {
		boolean result = true;
      int userCount = CollectionUtils.getCount(userIds);
      int voucherTypeVoucherCount = voucherRedisDao.getVoucherTypeVoucherCount(voucherTypeId);
      if (shopTypeId == null) {
          if (CollectionUtils.isEmpty(userIds)) {
        	  ///TODO
              List<ShopTypeRo> shopTypes =  null;//shopTypeFeignClient.findAllShopTypeRo(ShopTypeStatus.NORMAL);
              for (ShopTypeRo ro : shopTypes) {
            	// TODO Auto-generated method stub
//                  userCount = userCount + userFeignClient.findUserIdByShopType(Long.valueOf(ro.getId())).size();
              }
          }
      } else {
    	// TODO Auto-generated method stub
//          userCount = userFeignClient.findUserIdByShopType(shopTypeId).size();
      }
      if ((userCount * dispatcherCount > voucherTypeVoucherCount) || (
          userIds.size() * dispatcherCount > voucherTypeVoucherCount)) {
          result = false;
      }
      return result;
	}

	@Override
	public VoucherPo findVoucherPoById(Long voucherId) {
		return voucherRepository.findOne(voucherId);
	}

	@Override
	public Integer findVoucherNumberById(Long voucherTypeId) {
		return voucherRedisDao.getVoucherTypeVoucherCount(voucherTypeId);
	}
				
	@Override
	public void expiredVouchersSendNotificationsTask() throws Exception {
		logger.debug("Begin send notifications task(vouchers' expired time < 3 days).");
      String title = "您有一张优惠卷即将到期";
      boolean needMessageFlag = false;
      int voucherCount = 0;
   // TODO Auto-generated method stub
      List<UserPo> users = null;//userFeignClient.findAllUserByAuditStatus(AuditStatus.NORMAL);
      for (UserPo user : users) {
          Long userId = user.getId();
          List<VoucherRo> voucherRos = this.voucherRedisDao.listAllUsableVoucher(userId);
          for (VoucherRo voucherRo : voucherRos) {
              long expireTime = voucherRo.getExpireTime();
              long currentTime = System.currentTimeMillis();
              boolean isVoucherWillBeExpired = (expireTime - currentTime < THREE_DAYS_PERIOD && expireTime - currentTime >= 0);
              if(isVoucherWillBeExpired) {
                  needMessageFlag = true;
                  voucherCount++;
              }
          }
          if (needMessageFlag) {
              logger.info("检测到用户[{}]有[{}]张优惠券即将到期", user.getId(), voucherCount);
              String content = "您有优惠券即将到期，点击查看";
              String targetSchema = "depotnearby://redirect?target=voucherList";
           // TODO Auto-generated method stub
//              noticeFeignClient.sendMsgToUser(userId, title, content, targetSchema);
              logger.debug("Send success! [userId={}, title={}, content={}, targetSchema={}]", userId, title, content, targetSchema);
           // TODO Auto-generated method stub
//              sMSFeignClient.SMSMsg(user.getMobile(), AlidayuTemplateCode.VOUCHER_TO_BE_EXPIRED, null);
              logger.info("发送优惠券即将到期通知成功");
              needMessageFlag = false;
          }
      }
      logger.debug("Finished send notifications task.");
	}

	/**
	 * 修改过期优惠券事物
	 */
	@Override
	public void updateExpiredVoucherTask() {
		logger.debug("timer task for update expired voucher status to expired start...");
      List<VoucherTypeRo> allVoucherTypes = this.voucherTypeRedisDao.allVoucherTypes();
      List<VoucherRo> getVouchersByType = this.voucherRedisDao.getVouchersByType(allVoucherTypes);
      if (getVouchersByType != null && getVouchersByType.size() > 0) {
          for (VoucherRo voucherRo : getVouchersByType) {
              this.voucherRedisDao.expire(voucherRo.getBindingUserId(), voucherRo.getId());
          }
      }
      logger.debug("timer task for update expired voucher status to expired end...");
	}

	@Override
	public void dispatcherVoucherByAction(Long userId, VoucherConfigure action) {
		 VoucherConfigureRo voucherConfigureRo =
	            configureService.getVoucherConfigureRo(action.getMark());
	        if (voucherConfigureRo != null) {
	            Map<Long, VoucherTypeWithQuantity> needSendToUser =
	                voucherConfigureRo.getVoucherTypeWithQuantity();
	            for (VoucherTypeWithQuantity voucherTypeWithQuantity : needSendToUser.values()) {
	                VoucherTypeRo voucherTypeRo =
	                    voucherTypeRedisDao.getVoucherTypeRoById(voucherTypeWithQuantity.getId());
	                if (voucherTypeRo != null) {
	                    if (voucherTypeRo.getStartTime() > System.currentTimeMillis()) {
	                        logger.info("优惠券未到发放时间,优惠券类型ID[" + voucherTypeRo.getId() + "]");
	                    } else if (voucherTypeRo.getExpireTime() < System.currentTimeMillis()) {
	                        logger.info("优惠券发放时间已经过期,优惠券类型ID[" + voucherTypeRo.getId() + "]");
	                    } else {
	                        dispatcherVoucher(userId, voucherTypeRo,
	                            voucherTypeWithQuantity.getQuantity(), null);
	                    }
	                } else {
	                    logger.error("动作 {}  配置的优惠券类型 {}不存在", action.getMark(),
	                        voucherTypeWithQuantity.getId());
	                }
	            }
	        } else {
	            logger.error("动作 {}  配置的信息不存在", action.getMark());
	        }
	}

	@Override
	public List<ShopCraftVoucherVo> getAvailableVouchers(Long userId, List<? extends IOrderItemVo> itemVos)
			throws Exception {
		// TODO
//		 Map<Long, List<VoucherRo>> categoryVouchersMap = divideUnusedVouchersByCategory(userId);
//		         ShopRo shopRo = shopFeignClient.findShopByUserId(userId);
//		         Map<Long, Long> costMap = Maps.newHashMap();
//		         List<Long> categories = Lists.newArrayList();
//		         for (IOrderItemVo orderItemVo : itemVos) {
//		             ProductRo productRo = productRedisDao.get(orderItemVo.getProductId().toString());
//		             DepotProductRo depotProductRo = depotProductService
//		                 .getDepotProductRo(orderItemVo.getProductId(), shopRo.getDepotId());
//		             if (costMap.containsKey(productRo.getCategoryId())) {
//		            	 Long cost =
//		                     costMap.get(productRo.getCategoryId()) + depotProductRo.getSalePrice() * orderItemVo
//		                         .getQuantity();
//		                 costMap.put(productRo.getCategoryId(), cost);
//		             } else {
//		                 costMap.put(productRo.getCategoryId(),
//		                     depotProductRo.getSalePrice() * orderItemVo.getQuantity());
//		             }
//		             if (!categories.contains(productRo.getCategoryId())) {
//		                 categories.add(productRo.getCategoryId());
//		             }
//		         }
//		 
//		         List<ShopCraftVoucherVo> voucherVos = Lists.newArrayList();
//		         for (Long categoryId : categories) {
//		             List<VoucherRo> categoryVouchers = categoryVouchersMap.get(categoryId);
//		             Map<Long, List<VoucherRo>> voucherTypeVouchers =
//		                 divideVouchersByVoucherType(categoryVouchers);
//		             for (Map.Entry<Long, List<VoucherRo>> voucherTypeVouchersEntity : voucherTypeVouchers
//		                 .entrySet()) {
//		                 voucherVos.add(buildShopCraftVoucherVo(voucherTypeVouchersEntity.getKey(),
//		                     voucherTypeVouchersEntity.getValue(), costMap.get(categoryId)));
//		             }
//		         }
//		 
//		         Long totalCost = (long) 0;
//		         for (Long cost : costMap.values()) {
//		             totalCost = totalCost + cost;
//		         }
//		         List<VoucherRo> categoryVouchers = categoryVouchersMap.get(VoucherCategory.NONE.getValue());
//		         Map<Long, List<VoucherRo>> voucherTypeVouchers =
//		             divideVouchersByVoucherType(categoryVouchers);
//		         for (Long voucherTypeId : voucherTypeVouchers.keySet()) {
//		             voucherVos.add(
//		                 buildShopCraftVoucherVo(voucherTypeId, voucherTypeVouchers.get(voucherTypeId),
//		                     totalCost));
//		         }
//		         return voucherVos;
		return null;
	}
	
  @SuppressWarnings("unused")
  private ShopCraftVoucherVo buildShopCraftVoucherVo(Long voucherTypeId,
	  List<VoucherRo> voucherRos, Long cost){
	  VoucherTypeRo voucherTypeRo = voucherTypeService.getVoucherTypeRoById(voucherTypeId);
	  int maxCount = 0;
	
	  if (VoucherTypeStatus.MUTEX == voucherTypeRo.getTypeStatus()) {
	      maxCount = 1;
	  } else {
	      int tmpCost = 0;
	      while (maxCount < voucherRos.size()) {
	          if (voucherTypeRo.getPaymentLimit() != 0) {
	              tmpCost = tmpCost + voucherTypeRo.getPaymentLimit();
	          } else {
	              tmpCost = tmpCost + voucherTypeRo.getFaceValue();
	          }
	          if (cost >= tmpCost) {
	              maxCount = maxCount + 1;
	          } else {
	              break;
	          }
	
	      }
	  }
	
	  Collections.sort(voucherRos, new Comparator<VoucherRo>() {
	      public int compare(VoucherRo arg0, VoucherRo arg1) {
	          return arg0.getExpireTime().compareTo(arg1.getExpireTime());
	      }
	  });
	
	  ShopCraftVoucherVo voucherVo = new ShopCraftVoucherVo();
	  voucherVo.setPaymentLimit(voucherTypeRo.getPaymentLimit());
	  voucherVo.setName(voucherTypeRo.getName());
	  voucherVo.setFaceValue(voucherTypeRo.getFaceValue());
	  voucherVo.setMaxCount(maxCount);
	  voucherVo.setVoucherTypeId(voucherTypeId);
	  voucherVo.setStartTime(voucherTypeRo.getStartTime());
	  voucherVo.setRemainAmount(voucherRos.size());
	  voucherVo.setExpireTime(voucherRos.get(0).getExpireTime());
	  voucherVo.setCategoryInfo(voucherTypeRo.getLimitInfo());
	  voucherVo.setCategoryId(voucherTypeRo.getCategoryId());
	  voucherVo.setPaymentType(voucherTypeRo.getPaymentType());
	  if (voucherTypeRo.getProductIds() != null && !"".equals(voucherTypeRo.getProductIds())) {
	      voucherVo.setProductIds(voucherTypeRo.getProductIds());
	  } else {
	      voucherVo.setProductIds("");
	  }
	
	  return voucherVo;
}


	@Override
	public List<UserVoucherStatisticResultVo> findUserOrderStatsBy(Map<String, Object> searchParams) {
		 List<UserVoucherStatisticResultVo> userVoucherStatisticResultVos = Lists.newArrayList();
         for (UserVoucherStatisticResultVo resultVo : voucherRepositoryImpl
             .findAllUserVouchers(searchParams)) {
             userVoucherStatisticResultVos.add(this.fillUserVoucherCount(resultVo));
         }
         return userVoucherStatisticResultVos;
	}

	
	private UserVoucherStatisticResultVo fillUserVoucherCount(UserVoucherStatisticResultVo resultVo){
	  Map<String, List<VoucherRo>> userVouchersMap =
	      this.allVouchers(resultVo.getUserId().longValue());
	  List<VoucherRo> userUnusedVouchers = userVouchersMap.get("unused");
	  List<VoucherRo> userUsedVouchers = userVouchersMap.get("used");
	  List<VoucherRo> userExpireVouchers = userVouchersMap.get("expired");
	  int userVoucherCount =
	      userUnusedVouchers.size() + userUsedVouchers.size() + userExpireVouchers.size();
	  Map<Long, List<VoucherRo>> userUnusedVouchersMap =
	      divideVouchersByVoucherType(userUnusedVouchers);
	  resultVo.setUserVoucherCount(userVoucherCount);
	  int voucherTypeVoucherCount =
	      userUnusedVouchersMap.get(resultVo.getVoucherTypeId().longValue()) == null ?
	          0 :
	          userUnusedVouchersMap.get(resultVo.getVoucherTypeId().longValue()).size();
	  resultVo.setUserVoucherRemianCount(voucherTypeVoucherCount);
	  return resultVo;
	}
	
	/**
	 * 搜索列表
	 */
	@Override
	public PageVO<VoucherTypePo> searchVoucher(VoucherSearchVo reqVo) {
		return new PageVO<VoucherTypePo>(voucherTypeRepository.findAll(new VoucherSearchSpecification(reqVo), new PageRequest(reqVo.getPage()-1, reqVo.getPageSize(), Sort.Direction.DESC, "startTime")));
	}
}
