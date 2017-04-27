package com.biz.service.org;

import com.biz.gbck.common.bean.PageControl;
import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.common.vo.statistic.DailyStatNewShopResultVo;
import com.biz.gbck.common.vo.statistic.ShopAuditStatisticResultVo;
import com.biz.gbck.dao.mysql.po.org.*;
import com.biz.gbck.dao.mysql.po.payment.LoanApplyType;
import com.biz.gbck.dao.mysql.po.payment.ZsgfApplyStatus;
import com.biz.gbck.dao.mysql.po.payment.ZsgfLoanApplyPo;
import com.biz.gbck.dao.mysql.po.ximu.CustAccrPo;
import com.biz.gbck.dao.redis.ro.org.ShopRo;
import com.biz.gbck.dao.redis.ro.org.ShopTypeRo;
import com.biz.gbck.enums.user.AuditStatus;
import com.biz.gbck.enums.user.ShopStatus;
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
import com.biz.vo.org.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Created by defei on 3/11/16.
 */
@Service
@Transactional
public class ShopServiceImpl extends CommonService  implements ShopService {


    private static final Logger logger = LoggerFactory.getLogger(ShopService.class);


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

    }

    @Override
    public ShopDetailPo findLatestDetail(ShopDetailOrQualificationGetReqVo reqVo) throws CommonException {
        return null;
    }

    @Override
    public ShopDetailPo auditShopDetail(ShopAuditReqVo reqVo) throws CommonException {
        return null;
    }

    @Override
    public void updateQualification(ShopUpdateQualificationReqVo reqVo) throws CommonException {

    }

    @Override
    public ShopQualificationPo findLatestQualification(ShopDetailOrQualificationGetReqVo reqVo) throws CommonException {
        return null;
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

    }

    @Override
    public void changeShopStatusToWaitForAudit(ShopDetailPo shopDetailPo) {

    }

    @Override
    public void changeDeliveryName(UserChangeDeliveryNameReqVo reqVo) throws CommonException {

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
    public CustAccrPo getShopCreditInfo(Long shopId) {
        return null;
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
