package com.biz.service.org.interfaces;

import com.biz.gbck.common.bean.PageControl;
import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.common.vo.statistic.DailyStatNewShopResultVo;
import com.biz.gbck.common.vo.statistic.ShopAuditStatisticResultVo;
import com.biz.gbck.dao.mysql.po.org.*;
import com.biz.gbck.dao.mysql.po.payment.LoanApplyType;
import com.biz.gbck.dao.mysql.po.payment.ZsgfApplyStatus;
import com.biz.gbck.dao.mysql.po.payment.ZsgfLoanApplyPo;
import com.biz.gbck.dao.redis.ro.org.ShopRo;
import com.biz.gbck.dao.redis.ro.org.ShopTypeRo;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.enums.user.AuditStatus;
import com.biz.gbck.enums.user.ShopStatus;
import com.biz.gbck.vo.org.ChangePaymentPwdReqVo;
import com.biz.gbck.vo.org.SearchShopRespVo;
import com.biz.gbck.vo.org.ShopAuditDataMap;
import com.biz.gbck.vo.org.ShopAuditReqVo;
import com.biz.gbck.vo.org.ShopChangeDeliveryAddressReqVo;
import com.biz.gbck.vo.org.ShopDetailOrQualificationGetReqVo;
import com.biz.gbck.vo.org.ShopEditVo;
import com.biz.gbck.vo.org.ShopExportVo;
import com.biz.gbck.vo.org.ShopUpdateDetailReqVo;
import com.biz.gbck.vo.org.ShopUpdateQualificationReqVo;
import com.biz.gbck.vo.org.ShopsInfoExportVo;
import com.biz.gbck.vo.org.User20VIPVo;
import com.biz.gbck.vo.org.UserChangeDeliveryNameReqVo;
import com.biz.gbck.vo.org.UserVo;
import com.biz.gbck.vo.search.SearchShopReqVo;
import com.biz.gbck.vo.search.ShopQueryReqVo;
import com.biz.gbck.vo.zsgf.ZsgfLoanQueryReqVo;
import com.biz.manage.vo.FailDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.util.*;


/**
 * Created by defei on 3/11/16.
 */
public interface ShopService {


    /**
     * 为用户创建店铺
     */
    ShopRo createShop(Long userId, String corporateName, String inviterCode);

    /**
     * 管理员创建店铺
     */
    ShopRo createShopByAdmin(ShopEditVo shopEditVo, String admin) throws CommonException;

    /**
     * 管理员新建门店
     */
    ShopRo adminNewShop(ShopEditVo shopEditVo, String loginUsername);

    /**
     * 查找商铺
     */

    ShopRo findShop(Long shopId) throws CommonException;

    /**
     * 根据百度糯米用户账号查找或创建店铺
     */
    ShopPo findShopOrCreateByBaiduUserIdAndBaiduGeoCode(Long baiduUserId, Integer geoCode) throws CommonException;

    //是否百度糯米店铺
    boolean isBaiduNuomiShop(ShopPo shopPo, ShopPo baiduParentShopPo);

    //是否百度糯米默认店铺
    boolean isBaiduNuomiDefaultShop(ShopPo shopPo);

    /**
     * 根据用户查找商户
     */
    ShopRo findShopByUserId(Long userId) throws CommonException;

    ShopPo findShopPo(Long shopId);

    ShopPo findShopByName(String name);

    /**
     * rest 更新用户详细信息
     */
    void updateDetail(ShopUpdateDetailReqVo reqVo) throws CommonException;

    /**
     * 获取最后一次提交详情
     */
    ShopDetailPo findLatestDetail(ShopDetailOrQualificationGetReqVo reqVo)
            throws CommonException;

    /**
     * 审核商户详情
     */

    ShopDetailPo auditShopDetail(ShopAuditReqVo reqVo) throws CommonException;

    /**
     * 更新用户资质
     */
    void updateQualification(ShopUpdateQualificationReqVo reqVo) throws CommonException;

    /**
     * 获取最后一次提交资质
     */
    ShopQualificationPo findLatestQualification(ShopDetailOrQualificationGetReqVo reqVo)
            throws CommonException;

    /**
     * 审核商户资质
     */

    ShopQualificationPo auditShopQualification(ShopAuditReqVo reqVo);

    /**
     * 审核修改shopPO数据
     */
    ShopPo auditUpdateShopPo(ShopAuditReqVo reqVo);

    /**
     * 商户统一审核接口
     */
    void auditShop(ShopAuditReqVo reqVo) throws CommonException;

    /**
     * 修改商户类型
     */
    void updateShopType(ShopAuditReqVo reqVo);


    void updateShopDetailStatus(Long shopId, AuditStatus shopDetailAuditStatus);

    /**
     * 后台审核 保存 店铺信息
     */
    void updateShopDetailStatus(ShopDetailPo shopDetailPo, String priceDepotId,
                                String deliveryDepotId, String assartDepotId, String supportPaymentIds,
                                List<Integer> businessTagIds, List<Integer> priceTagIds);

    void updateShopQualificationAuditStatusToWaitForAudit(Long shopId);

    void syncShopQualificationToShop(ShopQualificationPo shopQualificationPo);

    /**
     * rest 变更收货地址
     */
    void changeDeliveryAddress(ShopChangeDeliveryAddressReqVo reqVo) throws CommonException;

    /**
     * 更改店铺审核状态
     */
    void changeShopStatusToWaitForAudit(ShopDetailPo shopDetailPo);

    /**
     * 更换收货人姓名
     */
    void changeDeliveryName(UserChangeDeliveryNameReqVo reqVo) throws CommonException;

    /**
     * 获取所有待审核商户
     */
    ShopAuditDataMap findShopAuditDataOfWaitForAudit();

    /**
     * 获取单个商户待审核信息
     */
    ShopAuditDataMap findShopAuditDataOfWaitForAuditByShopId(Long shopId);

    void syncAllShopFromMysqlToRedis(Integer pageSize);

    void syncAllShopTypeFromMysqlToRedis(Integer pageSize);

    /**
     * 同步ShopPo到redis数据库
     */
    //ShopRo syncShopPoToRedis(ShopPo shopPo);

    /**
     * 同步ShopTypePo到redis数据库
     */
    //ShopTypeRo syncShopTypePoToRedis(ShopTypePo shopTypePo);

    /**
     *验证商户操作权限
     */
    //void validateShopOperateAuthority(Boolean isShopAdmin, Long requestShopId, Long shopId) throws CommonException;

    List<String> findDetailAuditRejectReason(Long shopId);

    List<String> findQualificationAuditRejectReason(Long shopId);

    Boolean updateShopStatus(Long id, CommonStatusEnum shopStatus);

    ShopPo save(ShopPo shopPo);

    List<SearchShopRespVo> searchShops(SearchShopReqVo vo);

    ShopRo updateShop(ShopEditVo shopEditVo, String loginUsername);

    List<ShopPo> findShopByDepotId(String depotId);


    List<ShopPo> findByAssartDepotId(String depotId);

    List<ShopPo> findByShopIds(Set<Long> shopIds);

    /**
     * 物理删除，慎用
     */
    void destroyShopAndItUsers(Long shopId, String handler)
            throws CommonException;

    //void dispatchInviteVoucher(Long shopId);


    List<SearchShopRespVo> findshopListByUserlist(List<UserPo> users);

    Integer enableShopByIds(String[] ids);

    Integer disableShopByIds(String[] ids);

    /**
     * 查找商户，含有手机号的查找对应用户的对应商户
     */
    List<SearchShopRespVo> findShopAndUserShop(SearchShopReqVo searchShopReqVo)
            throws CommonException;

    /**
     * 分页查找商户
     */
    List<ShopPo> searchPageShops(ShopQueryReqVo reqVo, PageControl pc);

    /**
     * 判断businessLicenceId在数据库中是否已经存在
     */
    Boolean isBusinessLicenceIdExist(String businessLicenceId, Long shopId);

    List<ShopExportVo> findExportShops(String startTime, String endTime)
            throws CommonException;

    /**
     * 商户数据导入（新增加字段）
     */
    List<ShopsInfoExportVo> findExportShopsInfo(String startTime, String endTime) throws CommonException;

    void changeMobile(Long shopId, String mobile);

    List<ShopTypePo> getAllAvialShopTypes();

    List<ShopPo> getShopPosByShopTypeId(Long shopTypeId);

    List<ShopPo> getShopPosByShopTypeIdAndStartTimeAndEndTime(Long shopTypeId,
                                                              Timestamp startTime, Timestamp endTime);

    Map<String, ShopAuditStatisticResultVo> findShopAuditStatisticBy(Timestamp endTime,
                                                                     Map<String, Object> searchParams);

    List<ShopPo> findshopByInviterCode(String inviterCode);

    Map<String, DailyStatNewShopResultVo> getDailyNewShopsCountBy(Date date);

    List<ShopPo> findShopByMobile(String mobile);

    List<ShopPo> findShopPoByMobileAndHaveNotShopId(String mobile, Long shopId);

    void changeShopPaymentPassword(ChangePaymentPwdReqVo reqVo) throws CommonException;

    boolean validatePaymentPassword(Long userId, String md5Password) throws CommonException;

    boolean saveZsgfLoanApply(ZsgfLoanApplyPo zsgfLoanApplyPo);

    ZsgfLoanApplyPo findZsgfLoanApplyByUserId(Long userId) throws CommonException;

    ZsgfLoanApplyPo findZsgfLoanApplyByShopId(Long shopId);

    boolean fillZsgfLoanApplyImgInfo(Long userId, String imgJsonString)
            throws CommonException;

    void createPaymentPassword(Long userId, String password) throws CommonException;


    List<ZsgfLoanApplyPo> searchZsgfLoan(ZsgfLoanQueryReqVo reqVo, PageControl pc);

    ZsgfLoanApplyPo findZsgfLoanApplyById(Long id);

    ZsgfLoanApplyPo saveZsgfLoanApplyPo(ZsgfLoanApplyPo zsgfLoanApplyPo);

    boolean modifyApplyStatus(Long id, ZsgfApplyStatus applyStatus, Integer quota)
            throws CommonException;

    boolean updateAuditComment(Long id, String description) throws CommonException;

    void sendLoanNotice(Long shopId, LoanApplyType loanApplyType)
            throws CommonException;

//    void sendLoanShortMessage(Long shopId, List<UserPo> users, LoanApplyType loanApplyType)
//            throws CommonException;

//    void sendLoanPushMessage(Long shopId, List<UserPo> users, LoanApplyType loanApplyType)
//            throws CommonException;



    /**
     * 导入用户
     *
     * @param userVos
     * @return
     */
    int saveImportUser(List<UserVo> userVos);

    /**
     * 导入20倍会员信息
     *
     * @param user20VIPVos
     * @param failDetails
     * @return
     */
    int saveImportUser20VIP(List<User20VIPVo> user20VIPVos, List<FailDetail> failDetails);

    /**
     * 增加禁用支付方式
     */
    void addDisabledPaymentType(Long shopId, int paymentType);

    /**
     * 删除禁用支付方式
     */
    void removeDisabledPaymentType(Long shopId, int paymentType);

    /**
     * 检查某种支付方式是否被禁用
     *
     * @return
     */
    boolean isDisabledPaymentType(Long shopId, int paymentType);

    /**
     * 查询depotid对应旗下的商户
     *
     * @return
     */
    List<ShopPo> findShops();

    /**
     * 合伙人查看发展商户
     */
    List<ShopPo> findShopFodepotId(ShopQueryReqVo reqVo, PageControl pc);

    /**
     * 分页查询合伙人旗下商户
     */
    Page<ShopPo> searchPageShopsByDepotId(final ShopQueryReqVo vo, Pageable pageable);

    /**
     * 查看所有的商户
     */
    List<ShopPo> searchPageShopsByDepotId(ShopQueryReqVo reqVo, PageControl pc);


    List<ShopPo> getShopPoBackList();

    void deleteBlackList(List<String> shopIds);

    String updateBlackList(String blackList);

    boolean isShopCanCreateOrder(String shopId);


   /* DepotPo getOnlineDepotByShopId(Long shopId, Integer districtId);*/

}
