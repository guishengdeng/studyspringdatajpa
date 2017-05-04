package com.biz.service.org;

import com.biz.core.util.DateUtil;
import com.biz.event.org.ShopDetailUpdateEvent;
import com.biz.event.org.ShopQualificationUpdateEvent;
import com.biz.gbck.common.bean.PageControl;
import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.common.exception.DepotnearbyExceptionFactory;
import com.biz.gbck.common.vo.statistic.DailyStatNewShopResultVo;
import com.biz.gbck.common.vo.statistic.ShopAuditStatisticResultVo;
import com.biz.gbck.dao.mysql.po.geo.CityPo;
import com.biz.gbck.dao.mysql.po.geo.DistrictPo;
import com.biz.gbck.dao.mysql.po.geo.ProvincePo;
import com.biz.gbck.dao.mysql.po.org.*;
import com.biz.gbck.dao.mysql.po.payment.LoanApplyType;
import com.biz.gbck.dao.mysql.po.payment.ZsgfApplyStatus;
import com.biz.gbck.dao.mysql.po.payment.ZsgfLoanApplyPo;
import com.biz.gbck.dao.mysql.repository.geo.CityRepository;
import com.biz.gbck.dao.mysql.repository.geo.DistrictRepository;
import com.biz.gbck.dao.mysql.repository.geo.ProvinceRepository;
import com.biz.gbck.dao.mysql.repository.org.ShopDetailRepository;
import com.biz.gbck.dao.mysql.repository.org.ShopQualificationRepository;
import com.biz.gbck.dao.mysql.repository.org.ShopRepository;
import com.biz.gbck.dao.mysql.repository.org.ShopTypeRepository;
import com.biz.gbck.dao.redis.ro.org.ShopRo;
import com.biz.gbck.dao.redis.ro.org.ShopTypeRo;
import com.biz.gbck.dao.redis.ro.org.UserRo;
import com.biz.gbck.enums.user.AuditStatus;
import com.biz.gbck.enums.user.ShopStatus;
import com.biz.gbck.transform.org.ShopPoToShopDetailPo;
import com.biz.gbck.vo.org.ShopExportVo;
import com.biz.gbck.vo.org.ShopsInfoExportVo;
import com.biz.gbck.vo.org.User20VIPVo;
import com.biz.gbck.vo.org.UserVo;
import com.biz.gbck.vo.search.SearchShopReqVo;
import com.biz.gbck.vo.search.ShopQueryReqVo;
import com.biz.gbck.vo.zsgf.ZsgfLoanQueryReqVo;
import com.biz.manage.vo.FailDetail;
import com.biz.service.CommonService;
import com.biz.service.org.interfaces.ShopService;
import com.biz.service.org.interfaces.UserService;
import com.biz.vo.org.*;
import org.codelogger.utils.BeanUtils;
import org.codelogger.utils.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

import static com.google.common.collect.Lists.newArrayList;


/**
 * Created by defei on 3/11/16.
 */
@Service
@Transactional
public class ShopServiceImpl extends CommonService  implements ShopService {

    private static final Logger logger = LoggerFactory.getLogger(ShopService.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ShopDetailRepository shopDetailRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ShopTypeRepository shopTypeRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private ShopQualificationRepository shopQualificationRepository;


    @Override
    public ShopRo createShop(Long userId, String corporateName, String inviterCode) {
        return null;
    }

    @Override
    public ShopRo createShopByAdmin(ShopEditVo shopEditVo, String admin) throws CommonException {
        return null;
    }

    @Override
    public ShopRo adminNewShop(ShopEditVo shopEditVo, String loginUsername) {
        return null;
    }

    @Override
    public ShopRo findShop(Long shopId) throws CommonException {
        return null;
    }

    @Override
    public ShopPo findShopOrCreateByBaiduUserIdAndBaiduGeoCode(Long baiduUserId, Integer geoCode) throws CommonException {
        return null;
    }

    @Override
    public boolean isBaiduNuomiShop(ShopPo shopPo, ShopPo baiduParentShopPo) {
        return false;
    }

    @Override
    public boolean isBaiduNuomiDefaultShop(ShopPo shopPo) {
        return false;
    }

    @Override
    public ShopRo findShopByUserId(Long userId) throws CommonException {
        return null;
    }

    @Override
    public ShopPo findShopPo(Long shopId) {
        return null;
    }

    @Override
    public ShopPo findShopByName(String name) {
        return null;
    }

    @Override
    public void updateDetail(ShopUpdateDetailReqVo reqVo) throws CommonException {
        UserRo user = userService.findUser(reqVo.getUserId());
        validateShopOperateAuthority(user.getIsAdmin(), reqVo.getShopId(), user.getShopId());

        List<ShopDetailPo> shopDetailsOfWaitForAudit = shopDetailRepository
                .findByShopIdAndAuditStatusInOrderByCreateTimeDesc(reqVo.getShopId(),
                        newArrayList(AuditStatus.WAIT_FOR_AUDIT.getValue(),
                                AuditStatus.NORMAL_AND_HAS_NEW_UPDATE_WAIT_FOR_AUDIT.getValue()));
        ShopDetailPo shopDetailPo = CollectionUtils.getFirstOrNull(shopDetailsOfWaitForAudit);
        if (shopDetailPo == null) {
            shopDetailPo = new ShopDetailPo();
            ShopPo shopPo = shopRepository.findOne(reqVo.getShopId());
            shopDetailPo.setShop(shopPo);
            shopDetailPo.setAuditStatus(
                    Objects.equals(shopPo.getDetailAuditStatus(), AuditStatus.NORMAL.getValue()) ?
                            AuditStatus.NORMAL_AND_HAS_NEW_UPDATE_WAIT_FOR_AUDIT.getValue() :
                            AuditStatus.WAIT_FOR_AUDIT.getValue());
        }
        shopDetailPo.setName(reqVo.getName());
        shopDetailPo.setCorporateName(reqVo.getCorporateName());
        shopDetailPo.setMobile(user.getMobile());
        shopDetailPo.setDeliveryName(reqVo.getCorporateName());
        shopDetailPo.setDeliveryMobile(user.getMobile());
        ShopTypePo shopType = shopTypeRepository.findOne(reqVo.getShopTypeId());
        if(shopType == null){
            throw DepotnearbyExceptionFactory.ShopType.SHOP_TYPE_NOT_EXIST;
        }
        shopDetailPo.setShopType(shopType);
        ProvincePo provincePo = provinceRepository.findOne(reqVo.getProvinceId());
        if(provincePo == null){
            throw DepotnearbyExceptionFactory.GEO.PROVINCE_NOT_EXIST;
        }
        shopDetailPo.setProvince(provincePo);
        CityPo cityPo = cityRepository.findOne(reqVo.getCityId());
        if(cityPo == null){
            throw DepotnearbyExceptionFactory.GEO.CITY_NOT_EXIST;
        }
        shopDetailPo.setCity(cityPo);
        DistrictPo districtPo = districtRepository.findOne(reqVo.getDistrictId());
        if(districtPo == null){
            throw DepotnearbyExceptionFactory.GEO.DISTRICT_NOT_EXIST;
        }
        shopDetailPo.setDistrict(districtPo);
        shopDetailPo.setDeliveryAddress(reqVo.getDeliveryAddress());
        ShopDetailPo savedShopDetailPo = shopDetailRepository.save(shopDetailPo);

        changeShopStatusToWaitForAudit(savedShopDetailPo);
        publishEvent(new ShopDetailUpdateEvent(this, savedShopDetailPo));
    }

    @Override
    public ShopDetailPo findLatestDetail(ShopDetailOrQualificationGetReqVo reqVo) throws CommonException {
        UserRo user = userService.findUser(reqVo.getUserId());
        validateShopOperateAuthority(user.getIsAdmin(), reqVo.getShopId(), user.getShopId());
        ShopDetailPo latestShopDetailPo = CollectionUtils
                .getFirstOrNull(shopDetailRepository.findByShopIdOrderByIdDesc(user.getShopId()));
        if (latestShopDetailPo == null) {
            latestShopDetailPo = new ShopDetailPo();
            ShopPo shopPo = findShopPo(reqVo.getShopId());
            latestShopDetailPo.setShop(shopPo);
            latestShopDetailPo.setProvince(shopPo.getProvince());
            latestShopDetailPo.setCity(shopPo.getCity());
            latestShopDetailPo.setDistrict(shopPo.getDistrict());
            latestShopDetailPo.setDeliveryAddress(shopPo.getDeliveryAddress());
            latestShopDetailPo.setAuditStatus(shopPo.getDetailAuditStatus());
        }
        return latestShopDetailPo;
    }

    @Override
    public ShopDetailPo auditShopDetail(ShopAuditReqVo reqVo) throws CommonException {
        return null;
    }

    @Override
    public void updateQualification(ShopUpdateQualificationReqVo reqVo) throws CommonException {

        UserRo user = userService.findUser(reqVo.getUserId());
        validateShopOperateAuthority(user.getIsAdmin(), reqVo.getShopId(), user.getShopId());

        List<ShopQualificationPo> shopQualificationsOfWaitForAudit = shopQualificationRepository
                .findByShopIdAndAuditStatusInOrderByIdDesc(reqVo.getShopId(),
                        newArrayList(AuditStatus.WAIT_FOR_AUDIT.getValue(),
                                AuditStatus.NORMAL_AND_HAS_NEW_UPDATE_WAIT_FOR_AUDIT.getValue()));

        ShopQualificationPo shopQualificationPo =
                CollectionUtils.getFirstOrNull(shopQualificationsOfWaitForAudit);
        if (shopQualificationPo == null) {
            shopQualificationPo = new ShopQualificationPo();
            ShopPo shopPo = shopRepository.findOne(reqVo.getShopId());
            shopQualificationPo.setShop(shopPo);
            shopQualificationPo.setAuditStatus(Objects
                    .equals(shopPo.getQualificationAuditStatus(), AuditStatus.NORMAL.getValue()) ?
                    AuditStatus.NORMAL_AND_HAS_NEW_UPDATE_WAIT_FOR_AUDIT.getValue() :
                    AuditStatus.WAIT_FOR_AUDIT.getValue());
        }

        shopQualificationPo.setBusinessLicence(reqVo.getBusinessLicence());
        shopQualificationPo.setShopPhoto(reqVo.getShopPhoto());
        shopQualificationPo.setLiquorSellLicence(reqVo.getLiquorSellLicence());
        shopQualificationPo.setCorporateIdPhoto(reqVo.getCorporateIdPhoto());
        shopQualificationPo.setCreateTime(DateUtil.now());
        ShopQualificationPo savedShopQualificationPo =
                shopQualificationRepository.save(shopQualificationPo);
        publishEvent(new ShopQualificationUpdateEvent(this, savedShopQualificationPo));
    }

    @Override
    public ShopQualificationPo findLatestQualification(ShopDetailOrQualificationGetReqVo reqVo) throws CommonException {
        UserRo user = userService.findUser(reqVo.getUserId());
        validateShopOperateAuthority(user.getIsAdmin(), reqVo.getShopId(), user.getShopId());
        return CollectionUtils.getFirstOrNull(
                shopQualificationRepository.findByShopIdOrderByIdDesc(reqVo.getShopId()));
    }

    @Override
    public ShopQualificationPo auditShopQualification(ShopAuditReqVo reqVo) {
        return null;
    }

    @Override
    public ShopPo auditUpdateShopPo(ShopAuditReqVo reqVo) {
        return null;
    }

    @Override
    public void auditShop(ShopAuditReqVo reqVo) throws CommonException {

    }

    @Override
    public void updateShopType(ShopAuditReqVo reqVo) {

    }

    @Override
    public void updateShopDetailStatus(Long shopId, AuditStatus shopDetailAuditStatus) {

    }

    @Override
    public void updateShopDetailStatus(ShopDetailPo shopDetailPo, String priceDepotId, String deliveryDepotId, String assartDepotId, String supportPaymentIds, List<Integer> businessTagIds, List<Integer> priceTagIds) {

    }

    @Override
    public void updateShopQualificationAuditStatusToWaitForAudit(Long shopId) {

    }

    @Override
    public void syncShopQualificationToShop(ShopQualificationPo shopQualificationPo) {

    }

    @Override
    public void changeDeliveryAddress(ShopChangeDeliveryAddressReqVo reqVo) throws CommonException {
        UserRo user = userService.findUser(reqVo.getUserId());
        validateShopOperateAuthority(user.getIsAdmin(), reqVo.getShopId(), user.getShopId());

        List<ShopDetailPo> unNormalDetails = shopDetailRepository
                .findByShopIdAndAuditStatusInOrderByCreateTimeDesc(reqVo.getShopId(),
                        newArrayList(AuditStatus.WAIT_FOR_AUDIT.getValue(),
                                AuditStatus.NORMAL_AND_HAS_NEW_UPDATE_WAIT_FOR_AUDIT.getValue()));
        if (CollectionUtils.isNotEmpty(unNormalDetails)) {
            throw DepotnearbyExceptionFactory.Shop.SHOP_DETAIL_IS_AUDITING;
        }
        List<ShopDetailPo> normalDetails = shopDetailRepository
                .findByShopIdAndAuditStatusOrderByCreateTimeDesc(reqVo.getShopId(),
                        AuditStatus.NORMAL.getValue());
        ShopDetailPo latestShopDetailPo = CollectionUtils.getFirstOrNull(normalDetails);
        if (latestShopDetailPo == null) {
            ShopPo shopPo = findShopPo(user.getShopId());
            latestShopDetailPo = new ShopPoToShopDetailPo().apply(shopPo);
        }
        ShopDetailPo newShopDetailPo = new ShopDetailPo();
        BeanUtils.copyProperties(latestShopDetailPo, newShopDetailPo);
        newShopDetailPo.setId(null);
        newShopDetailPo.setProvince(provinceRepository.findOne(reqVo.getProvinceId()));
        newShopDetailPo.setCity(cityRepository.findOne(reqVo.getCityId()));
        newShopDetailPo.setDistrict(districtRepository.findOne(reqVo.getDistrictId()));
        newShopDetailPo.setDeliveryAddress(reqVo.getDeliveryAddress());
        newShopDetailPo.setReason(reqVo.getReason());
        ShopRo shop = findShop(reqVo.getShopId());
        newShopDetailPo.setDeliveryName(shop.getDeliveryName());
        newShopDetailPo.setDeliveryMobile(user.getMobile());
        newShopDetailPo
                .setAuditStatus(AuditStatus.NORMAL_AND_HAS_NEW_UPDATE_WAIT_FOR_AUDIT.getValue());
        ShopDetailPo savedShopDetailPo = shopDetailRepository.save(newShopDetailPo);
        //用户提交修改收货地址请求，会修改当前商铺的审核状态！！！
        //        changeShopStatusToWaitForAudit(savedShopDetailPo);
        publishEvent(new ShopDetailUpdateEvent(this, savedShopDetailPo));
    }

    @Override
    public void changeShopStatusToWaitForAudit(ShopDetailPo shopDetailPo) {

    }

    @Override
    public void changeDeliveryName(UserChangeDeliveryNameReqVo reqVo) throws CommonException {
        UserRo user = userService.findUser(reqVo.getUserId());
        validateShopOperateAuthority(user.getIsAdmin(), user.getShopId(), user.getShopId());
        ShopPo shopPo = shopRepository.findOne(user.getShopId());
        shopPo.setDeliveryName(reqVo.getDeliveryName());
        shopRepository.updateDeliveryName(user.getShopId(), reqVo.getDeliveryName());
        syncShopPoToRedis(shopPo);
    }

    @Override
    public ShopAuditDataMap findShopAuditDataOfWaitForAudit() {
        return null;
    }

    @Override
    public ShopAuditDataMap findShopAuditDataOfWaitForAuditByShopId(Long shopId) {
        return null;
    }

    @Override
    public void syncAllShopFromMysqlToRedis(Integer pageSize) {

    }

    @Override
    public void syncAllShopTypeFromMysqlToRedis(Integer pageSize) {

    }

    @Override
    public ShopRo syncShopPoToRedis(ShopPo shopPo) {
        return null;
    }

    @Override
    public ShopTypeRo syncShopTypePoToRedis(ShopTypePo shopTypePo) {
        return null;
    }

    @Override
    public void validateShopOperateAuthority(Boolean isShopAdmin, Long requestShopId, Long shopId) throws CommonException {
        if (!Objects.equals(requestShopId, shopId) || !isShopAdmin) {
            throw DepotnearbyExceptionFactory.GLOBAL.NO_AUTHORITY;
        }
    }

    @Override
    public List<String> findDetailAuditRejectReason(Long shopId) {
        return null;
    }

    @Override
    public List<String> findQualificationAuditRejectReason(Long shopId) {
        return null;
    }

    @Override
    public Boolean updateShopStatus(Long id, ShopStatus shopStatus) {
        return null;
    }

    @Override
    public ShopPo save(ShopPo shopPo) {
        return null;
    }

    @Override
    public List<SearchShopRespVo> searchShops(SearchShopReqVo vo) {
        return null;
    }

    @Override
    public ShopRo updateShop(ShopEditVo shopEditVo, String loginUsername) {
        return null;
    }

    @Override
    public List<ShopPo> findShopByDepotId(String depotId) {
        return null;
    }

    @Override
    public List<ShopPo> findByAssartDepotId(String depotId) {
        return null;
    }

    @Override
    public List<ShopPo> findByShopIds(Set<Long> shopIds) {
        return null;
    }

    @Override
    public void destroyShopAndItUsers(Long shopId, String handler) throws CommonException {

    }

    @Override
    public void dispatchInviteVoucher(Long shopId) {

    }

    @Override
    public List<SearchShopRespVo> findshopListByUserlist(List<UserPo> users) {
        return null;
    }

    @Override
    public Integer enableShopByIds(String[] ids) {
        return null;
    }

    @Override
    public Integer disableShopByIds(String[] ids) {
        return null;
    }

    @Override
    public List<SearchShopRespVo> findShopAndUserShop(SearchShopReqVo searchShopReqVo) throws CommonException {
        return null;
    }

    @Override
    public List<ShopPo> searchPageShops(ShopQueryReqVo reqVo, PageControl pc) {
        return null;
    }

    @Override
    public Boolean isBusinessLicenceIdExist(String businessLicenceId, Long shopId) {
        return null;
    }

    @Override
    public List<ShopExportVo> findExportShops(String startTime, String endTime) throws CommonException {
        return null;
    }

    @Override
    public List<ShopsInfoExportVo> findExportShopsInfo(String startTime, String endTime) throws CommonException {
        return null;
    }

    @Override
    public void changeMobile(Long shopId, String mobile) {

    }

    @Override
    public List<ShopTypePo> getAllAvialShopTypes() {
        return null;
    }

    @Override
    public List<ShopPo> getShopPosByShopTypeId(Long shopTypeId) {
        return null;
    }

    @Override
    public List<ShopPo> getShopPosByShopTypeIdAndStartTimeAndEndTime(Long shopTypeId, Timestamp startTime, Timestamp endTime) {
        return null;
    }

    @Override
    public Map<String, ShopAuditStatisticResultVo> findShopAuditStatisticBy(Timestamp endTime, Map<String, Object> searchParams) {
        return null;
    }

    @Override
    public List<ShopPo> findshopByInviterCode(String inviterCode) {
        return null;
    }

    @Override
    public Map<String, DailyStatNewShopResultVo> getDailyNewShopsCountBy(Date date) {
        return null;
    }

    @Override
    public List<ShopPo> findShopByMobile(String mobile) {
        return null;
    }

    @Override
    public List<ShopPo> findShopPoByMobileAndHaveNotShopId(String mobile, Long shopId) {
        return null;
    }

    @Override
    public void changeShopPaymentPassword(ChangePaymentPwdReqVo reqVo) throws CommonException {

    }

    @Override
    public boolean validatePaymentPassword(Long userId, String md5Password) throws CommonException {
        return false;
    }

    @Override
    public boolean saveZsgfLoanApply(ZsgfLoanApplyPo zsgfLoanApplyPo) {
        return false;
    }

    @Override
    public ZsgfLoanApplyPo findZsgfLoanApplyByUserId(Long userId) throws CommonException {
        return null;
    }

    @Override
    public ZsgfLoanApplyPo findZsgfLoanApplyByShopId(Long shopId) {
        return null;
    }

    @Override
    public boolean fillZsgfLoanApplyImgInfo(Long userId, String imgJsonString) throws CommonException {
        return false;
    }

    @Override
    public void createPaymentPassword(Long userId, String password) throws CommonException {

    }

    @Override
    public List<ZsgfLoanApplyPo> searchZsgfLoan(ZsgfLoanQueryReqVo reqVo, PageControl pc) {
        return null;
    }

    @Override
    public ZsgfLoanApplyPo findZsgfLoanApplyById(Long id) {
        return null;
    }

    @Override
    public ZsgfLoanApplyPo saveZsgfLoanApplyPo(ZsgfLoanApplyPo zsgfLoanApplyPo) {
        return null;
    }

    @Override
    public boolean modifyApplyStatus(Long id, ZsgfApplyStatus applyStatus, Integer quota) throws CommonException {
        return false;
    }

    @Override
    public boolean updateAuditComment(Long id, String description) throws CommonException {
        return false;
    }

    @Override
    public void sendLoanNotice(Long shopId, LoanApplyType loanApplyType) throws CommonException {

    }

    @Override
    public void sendLoanShortMessage(Long shopId, List<UserPo> users, LoanApplyType loanApplyType) throws CommonException {

    }

    @Override
    public void sendLoanPushMessage(Long shopId, List<UserPo> users, LoanApplyType loanApplyType) throws CommonException {

    }

    @Override
    public int saveImportUser(List<UserVo> userVos) {
        return 0;
    }

    @Override
    public int saveImportUser20VIP(List<User20VIPVo> user20VIPVos, List<FailDetail> failDetails) {
        return 0;
    }

    @Override
    public void addDisabledPaymentType(Long shopId, int paymentType) {

    }

    @Override
    public void removeDisabledPaymentType(Long shopId, int paymentType) {

    }

    @Override
    public boolean isDisabledPaymentType(Long shopId, int paymentType) {
        return false;
    }

    @Override
    public List<ShopPo> findShops() {
        return null;
    }

    @Override
    public List<ShopPo> findShopFodepotId(ShopQueryReqVo reqVo, PageControl pc) {
        return null;
    }

    @Override
    public Page<ShopPo> searchPageShopsByDepotId(ShopQueryReqVo vo, Pageable pageable) {
        return null;
    }

    @Override
    public List<ShopPo> searchPageShopsByDepotId(ShopQueryReqVo reqVo, PageControl pc) {
        return null;
    }

    @Override
    public List<ShopPo> getShopPoBackList() {
        return null;
    }

    @Override
    public void deleteBlackList(List<String> shopIds) {

    }

    @Override
    public String updateBlackList(String blackList) {
        return null;
    }

    @Override
    public boolean isShopCanCreateOrder(String shopId) {
        return false;
    }
}
