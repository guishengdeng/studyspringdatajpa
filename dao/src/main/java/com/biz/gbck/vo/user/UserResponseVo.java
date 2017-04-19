package com.biz.gbck.vo.user;

import com.biz.gbck.enums.user.*;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import org.codelogger.utils.StringUtils;

/**
 * @author lei
 * @date 2017年02月15日
 * @reviewer
 * @see
 */
public class UserResponseVo implements Serializable {

    private static final long serialVersionUID = 2454850678848499259L;

    /**
     * 账号(登录使用的手机、邮箱、账户名)
     */
    private String accountName;
    /**
     * 登陆zhanghaoId
     */
    private Long accountId;
    /**
     * 商户ID
     */
    private Long vendorId;

    /**
     * 绑定手机号
     */
    private String mobile;

    /**
     * 绑定邮箱
     */
    private String email;

    /**
     * 登录次数
     */
    private Integer loginTimes = 0;

    /**
     * 登录类型
     */
    private String loginType;

    /**
     * 最近登录IP
     */
    private String loginIp;

    /**
     * 最近登录时间
     */
    private Timestamp loginDate;

    /**
     * 最近登录纬度
     */
    private BigDecimal loginLat;

    /**
     * 最近登录经度
     */
    private BigDecimal loginLon;

    private Timestamp createTimestamp;

    /**
     * 用户Id(非中台)
     */
    private Long userId;

    private String userIdString;
    /**
     * memberId(中台)
     */
    private Long memberId;

    /**
     * crm 会员id
     */
    private Long crmMemberId;

    /**
     * 姓名
     */
    private String username;

    /**
     * 省id
     */
    private Integer provinceId;

    /**
     * 市id
     */
    private Integer cityId;

    /**
     * 区id
     */
    private Integer districtId;

    /**
     * 地址
     */
    private String address;

    /**
     * 区域
     */
    private String area;

    /**
     * 固定电话
     */
    private String tel;

    /**
     * 邮政编码
     */
    private String zip;

    /**
     * 身份证号码
     */
    private String idCard;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 注册渠道
     */
    private String channelCode;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 上次登录城市id
     */
    private Long lastCityId;

    /**
     * 生日
     */
    private Timestamp birthday;

    /**
     * 会员等级
     */
    private Integer userLevel;
    private String userLevelText;

    /**
     * 性别
     */
    private Sex sex;

    /**
     * 用户状态
     */
    private UserState state = UserState.NORMAL;

    /**
     * 会员类型
     */
    private MemberType memberType;

    /**
     * 会员积分
     */
    private Integer point;

    /**
     * 推荐人类型
     */
    private RefereeType refereeType;

    /**
     * 推荐人（推荐号，可以用来存储会员id）
     */
    private String referee;

    /**
     * 婚姻状况
     */
    private Wedlock wedlock;

    /**
     * 学历
     */
    private Education education;

    /**
     * 职业
     */
    private String vocation;

    /**
     * 兴趣爱好
     */
    private String interest;

    /**
     * 备注
     */
    private String remark;

    /**
     * 来源(pc, wap)
     */
    private String source;

    /**
     * 注册时间
     */
    private Timestamp regTimestamp;

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(StringUtils.getRandomPasswordChar());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Long getCrmMemberId() {
        return crmMemberId;
    }

    public void setCrmMemberId(Long crmMemberId) {
        this.crmMemberId = crmMemberId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getUserLevelText() {
        return userLevelText;
    }

    public void setUserLevelText(String userLevelText) {
        this.userLevelText = userLevelText;
    }

    public Long getLastCityId() {
        return lastCityId;
    }

    public void setLastCityId(Long lastCityId) {
        this.lastCityId = lastCityId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getReferee() {
        return referee;
    }

    public void setReferee(String referee) {
        this.referee = referee;
    }

    public RefereeType getRefereeType() {
        return refereeType;
    }

    public void setRefereeType(RefereeType refereeType) {
        this.refereeType = refereeType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public UserState getState() {
        return state;
    }

    public void setState(UserState state) {
        this.state = state;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVocation() {
        return vocation;
    }

    public void setVocation(String vocation) {
        this.vocation = vocation;
    }

    public Wedlock getWedlock() {
        return wedlock;
    }

    public void setWedlock(Wedlock wedlock) {
        this.wedlock = wedlock;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Timestamp getRegTimestamp() {
        return regTimestamp;
    }

    public void setRegTimestamp(Timestamp regTimestamp) {
        this.regTimestamp = regTimestamp;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Timestamp getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Timestamp loginDate) {
        this.loginDate = loginDate;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public BigDecimal getLoginLat() {
        return loginLat;
    }

    public void setLoginLat(BigDecimal loginLat) {
        this.loginLat = loginLat;
    }

    public BigDecimal getLoginLon() {
        return loginLon;
    }

    public void setLoginLon(BigDecimal loginLon) {
        this.loginLon = loginLon;
    }

    public Integer getLoginTimes() {
        return loginTimes;
    }

    public void setLoginTimes(Integer loginTimes) {
        this.loginTimes = loginTimes;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public String getUserIdString() {
        return userIdString;
    }

    public void setUserIdString(String userIdString) {
        this.userIdString = userIdString;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }
}
