package com.biz.gbck.dao.mysql.po.org;


import com.biz.core.util.DateUtil;
import com.biz.gbck.dao.mysql.po.geo.CityPo;
import com.biz.gbck.dao.mysql.po.geo.DistrictPo;
import com.biz.gbck.dao.mysql.po.geo.ProvincePo;
import com.biz.gbck.dao.mysql.po.salearea.SaleAreaPo;
import com.biz.gbck.enums.user.AuditStatus;
import com.biz.gbck.enums.user.ShopStatus;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * 店铺
 * Created by defei on 3/16/16.
 */
//@SqlResultSetMappings({
//    @SqlResultSetMapping(
//        name = "ShopAuditStatisticBinding",
//        classes = {
//            @ConstructorResult(
//                targetClass = ShopAuditStatisticResultVo.class,
//                columns = {
//                    @ColumnResult(name = "province_id"),
//                    @ColumnResult(name = "province_name"),
//                    @ColumnResult(name = "depot_name"),
//                    @ColumnResult(name = "register_not_complete"),
//                    @ColumnResult(name = "audit_not_pass"),
//                    @ColumnResult(name = "audit_pass")
//                }
//            )
//        }
//    ),
//    @SqlResultSetMapping(
//        name = "DailyNewShopBinding",
//        classes = {
//            @ConstructorResult(
//                targetClass = DailyStatNewShopResultVo.class,
//                columns = {
//                    @ColumnResult(name = "d_day"),
//                    @ColumnResult(name = "province_id"),
//                    @ColumnResult(name = "shop_type_id"),
//                    @ColumnResult(name = "day_new_shop_count")
//                }
//            )
//        }
//    ),
//    @SqlResultSetMapping(
//        name = "shopsInfo",
//        classes = {
//            @ConstructorResult(
//                targetClass = ShopsInfoExportVo.class,
//                columns = {
//                    @ColumnResult(name = "shop_id"),
//                    @ColumnResult(name = "user_mobile"),
//                    @ColumnResult(name = "shop_type"),
//                    @ColumnResult(name = "shop_province"),
//                    @ColumnResult(name = "user_register_time"),
//                    @ColumnResult(name = "user_lastlogintime"),
//                    @ColumnResult(name = "shop_status"),
//                    @ColumnResult(name = "shop_invite_code"),
//                    @ColumnResult(name = "shop_inviter_name"),
//                    @ColumnResult(name = "shop_assart_depot_name"),
//                    @ColumnResult(name = "shop_price_depot_name"),
//                    @ColumnResult(name = "shop_deliver_depot_name"),
//                    @ColumnResult(name = "shop_name"),
//                    @ColumnResult(name = "province_name"),
//                    @ColumnResult(name = "city_name"),
//                    @ColumnResult(name = "district_name"),
//                    @ColumnResult(name = "shop_address")
//                }
//            )
//        }
//    )
//})
@Entity
@Table(name = "org_shop")
public class ShopPo extends Company {


    /**
     * 店铺名称
     */
    @Column(length = 40) private String name;

    /**
     * 法人名字
     */
    @Column(length = 40) private String corporateName;

    /**
     * 店铺头像
     */
    @Column(length = 50) private String avatar;

    /**
     * 店铺成员
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "shop")
    private Set<UserPo> users;

    /**
     * 店铺类型
     */
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "shopTypeId") private ShopTypePo shopType;

    /**
     * 店铺手机
     */
    @Column(length = 25) private String mobile;

    /**
     * 店铺电话
     */
    @Column(length = 48) private String tel;

//    /**
//     * 价格门店
//     */
//    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "depotId") private DepotPo depot;
//
//    /**
//     * 开荒门店
//     */
//    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "assartDepotId") private DepotPo
//        assartDepot;
//
//    /**
//     * 配送门店
//     */
//    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "deliveryDepotId") private DepotPo
//        deliveryDepot;

    /**
     * 店铺经度
     */
    @Column(columnDefinition = "DECIMAL(9,6)") private BigDecimal longitude;

    /**
     * 店铺纬度
     */
    @Column(columnDefinition = "DECIMAL(9,6)") private BigDecimal latitude;

    /**
     * 收货人姓名
     */
    @Column(length = 40) private String deliveryName;

    /**
     * 收货人电话
     */
    @Column(length = 30) private String deliveryMobile;

    /**
     * 店铺收货地址
     */
    @Column(length = 100) private String deliveryAddress;

    /**
     * 店铺地址
     */
    @Column(length = 100) private String shopAddress;

    /**
     * 省
     */
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "provinceId") private ProvincePo province;

    /**
     * 市
     */
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "cityId") private CityPo city;

    /**
     * 区
     */
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "districtId") private DistrictPo district;

    /**
     * 营业执照ID
     */
    @Column(length = 50) private String businessLicenceId;

    /**
     * 营业执照名称
     */
    @Column(length = 50) private String businessLicenceName;

    /**
     * 营业执照
     */
    @Column(length = 50) private String businessLicence;

    /**
     * 门头照片
     */
    @Column(length = 50) private String shopPhoto;

    /**
     * 酒类流通许可证ID
     */
    @Column(length = 50) private String liquorSellLicenceId;

    /**
     * 酒类流通许可证
     */
    @Column(length = 50) private String liquorSellLicence;

    /**
     * 法人身份证Id
     */
    @Column(length = 18) private String corporateId;

    /**
     * 法人身份证
     */
    @Column(length = 50) private String corporateIdPhoto;

    /**
     * 创建时间
     */
    private Timestamp createTime = DateUtil.now();

    /**
     * 智选价格标签
     */
//    @ManyToMany(fetch = FetchType.LAZY) @JoinTable(name = "shop_pricetag",
//
//        joinColumns = {@JoinColumn(name = "shop_id", referencedColumnName = "id")},
//        inverseJoinColumns = {@JoinColumn(name = "pricetag_id", referencedColumnName = "id")},
//        uniqueConstraints = {@UniqueConstraint(columnNames = {"shop_id", "pricetag_id"})})
//
//    private List<PriceTagPo> priceTags;

//    /**
//     * 智选分类标签
//     */
//    @ManyToMany(fetch = FetchType.LAZY) @JoinTable(name = "shop_businesstag",
//        joinColumns = {@JoinColumn(name = "shop_id", referencedColumnName = "id")},
//        inverseJoinColumns = {@JoinColumn(name = "businesstag_id", referencedColumnName = "id")},
//        uniqueConstraints = {@UniqueConstraint(columnNames = {"shop_id", "businesstag_id"})})
//
//    private List<BusinessTagPo> businessTags;

    /**
     * 销售区域
     */
    @ManyToMany(fetch = FetchType.LAZY) @JoinTable(name = "shop_salearea",
        joinColumns = {@JoinColumn(name = "shop_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "salearea_id", referencedColumnName = "id")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"shop_id", "salearea_id"})})

    private List<SaleAreaPo> saleAreas;

    /**
     * 邀请者
     */
    @Column(length = 20) private String inviterCode;

    /**
     * 商户详情审核状态
     */
    private Integer detailAuditStatus = AuditStatus.NEED_INFO.getValue();

    /**
     * 资质审核状态
     */
    private Integer qualificationAuditStatus = AuditStatus.NEED_INFO.getValue();
    /**
     * 店铺状态
     */
    @Column(nullable = false) private Integer status = ShopStatus.NORMAL.getValue();

    /**
     * 支持的 付款方式
     */
    @Column(length = 50) private String supportPaymentIds;

    /**
     * 禁用的付款方式
     */
    @Column(length = 50) private String disabledPaymentIds;

    /**
     * 支付密码
     */
    @Column(length = 32) private String paymentPassword;

    /**
     * 渠道用户id
     */
    private Long channelUserId;

//    /**
//     * 渠道，参看{@linkplain com.depotnearby.common.shop.ShopChannel}. 数据为其value
//     */
//    private Integer channel;

    /**
     * 父店铺
     */
    @ManyToOne(optional = true) @JoinColumn(name = "parentId") private ShopPo parent;


    /**
     * 子店铺
     */
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy(value = "id") @NotFound(action = NotFoundAction.IGNORE) private List<ShopPo> children;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partnerId")
    private PartnerPo partner;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCorporateName() {
        return corporateName;
    }

    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Set<UserPo> getUsers() {
        return users;
    }

    public void setUsers(Set<UserPo> users) {
        this.users = users;
    }

    public ShopTypePo getShopType() {
        return shopType;
    }

    public void setShopType(ShopTypePo shopType) {
        this.shopType = shopType;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }


    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public String getDeliveryMobile() {
        return deliveryMobile;
    }

    public void setDeliveryMobile(String deliveryMobile) {
        this.deliveryMobile = deliveryMobile;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public ProvincePo getProvince() {
        return province;
    }

    public void setProvince(ProvincePo province) {
        this.province = province;
    }

    public CityPo getCity() {
        return city;
    }

    public void setCity(CityPo city) {
        this.city = city;
    }

    public DistrictPo getDistrict() {
        return district;
    }

    public void setDistrict(DistrictPo district) {
        this.district = district;
    }

    public String getBusinessLicenceId() {
        return businessLicenceId;
    }

    public void setBusinessLicenceId(String businessLicenceId) {
        this.businessLicenceId = businessLicenceId;
    }

    public String getBusinessLicenceName() {
        return businessLicenceName;
    }

    public void setBusinessLicenceName(String businessLicenceName) {
        this.businessLicenceName = businessLicenceName;
    }

    public String getBusinessLicence() {
        return businessLicence;
    }

    public void setBusinessLicence(String businessLicence) {
        this.businessLicence = businessLicence;
    }

    public String getShopPhoto() {
        return shopPhoto;
    }

    public void setShopPhoto(String shopPhoto) {
        this.shopPhoto = shopPhoto;
    }

    public String getLiquorSellLicenceId() {
        return liquorSellLicenceId;
    }

    public void setLiquorSellLicenceId(String liquorSellLicenceId) {
        this.liquorSellLicenceId = liquorSellLicenceId;
    }

    public String getLiquorSellLicence() {
        return liquorSellLicence;
    }

    public void setLiquorSellLicence(String liquorSellLicence) {
        this.liquorSellLicence = liquorSellLicence;
    }

    public String getCorporateId() {
        return corporateId;
    }

    public void setCorporateId(String corporateId) {
        this.corporateId = corporateId;
    }

    public String getCorporateIdPhoto() {
        return corporateIdPhoto;
    }

    public void setCorporateIdPhoto(String corporateIdPhoto) {
        this.corporateIdPhoto = corporateIdPhoto;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getInviterCode() {
        return inviterCode;
    }

    public void setInviterCode(String inviterCode) {
        this.inviterCode = inviterCode;
    }

    public Integer getDetailAuditStatus() {
        return detailAuditStatus;
    }

    public void setDetailAuditStatus(Integer detailAuditStatus) {
        this.detailAuditStatus = detailAuditStatus;
    }

    public Integer getQualificationAuditStatus() {
        return qualificationAuditStatus;
    }

    public void setQualificationAuditStatus(Integer qualificationAuditStatus) {
        this.qualificationAuditStatus = qualificationAuditStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSupportPaymentIds() {
        return supportPaymentIds;
    }

    public void setSupportPaymentIds(String supportPaymentIds) {
        this.supportPaymentIds = supportPaymentIds;
    }

    public String getDisabledPaymentIds() {
        return disabledPaymentIds;
    }

    public void setDisabledPaymentIds(String disabledPaymentIds) {
        this.disabledPaymentIds = disabledPaymentIds;
    }

    public Long getChannelUserId() {
        return channelUserId;
    }

    public void setChannelUserId(Long channelUserId) {
        this.channelUserId = channelUserId;
    }

    public ShopPo getParent() {
        return parent;
    }

    public void setParent(ShopPo parent) {
        this.parent = parent;
    }

    public List<ShopPo> getChildren() {
        return children;
    }

    public void setChildren(List<ShopPo> children) {
        this.children = children;
    }

    public String getPaymentPassword() {
        return paymentPassword;
    }

    public void setPaymentPassword(String paymentPassword) {
        this.paymentPassword = paymentPassword;
    }
}
