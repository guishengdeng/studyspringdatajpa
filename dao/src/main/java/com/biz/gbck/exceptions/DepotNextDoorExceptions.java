package com.biz.gbck.exceptions;

/**
 * SOA 异常信息定义
 *
 * @author yanweijin
 * @date 2016/12/13
 */
public interface DepotNextDoorExceptions {

    /**
     * use code 600-699
     */
    enum Common implements ExceptionType {
        DEPOT_NOT_EXIT(601, "门店不存在，请重新启动APP或者检查定位设备"),
        DEPOT_DISTANCE_ERROR(602, "Srroy,门店定位数据有误！"),
        DEPOT_BUSINESS_TIME_ERROR(603, "门店营业时间数据有误, 请联系客服！"),
        NO_NEARBY_DEPOT_EXCEPTION(604, "未发现附近门店");

        private int code;
        private String description;

        Common(int code, String description) {
            this.code = code;
            this.description = description;
        }

        public int getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }
    }

    /**
     * use code 700-799
     */
    enum User implements ExceptionType {

        USER_NOT_EXIST(700, "用户不存在"),
        MOBILE_ERROR(701, "手机号格式错误"),
        USER_ALREADY_EXIST(702, "用户已存在"),
        USERNAME_OR_PASSWORD_ERROR(703, "用户名或密码错误"),
        ILLEGAL_USER_LEVEL(704, "用户等级有误，请咨询客服！"),
        OLD_PASSWORD_ERROR(705, "原密码错误，请重新输入！"),
        PASSWORD_ERROR(706, "密码错误"),
        ADDRESS_NOT_EXIST(707, "收货地址不存在，请新建或者重新选择！"),
        HAND_PASSWORD_DISABELD(708, "手势密码已被禁用"),
        TOKEN_GET_FAILED(709, "imtoken获取失败"),
        USER_EXCEPTION(710, "您的账号数据异常，请拨打400电话联系客服解决"),
        MOBILE_NOT_FOUND(711, "您的账号尚未绑定手机号"),
        WALLET_RECHARGE_LEVEL_ERROR(712, "该服务不支持20级会员使用!"),
        USER_BE_FROZEN(713, "您的账号被冻结，请联系客服。"),
        OTHER_DEVICE_LOGIN(714, "您的账号在其他设备登录，如非本人操作请修改密码！"),
        AUTH_DENIED(715, "您的账号在其他设备登录，如非本人操作请修改密码！"),
        USERLEVEL_NOT_EXIST(716, "用户等级不存在"),
        USER_ID_MISMATCH(717, "用户不匹配,您没有权限执行此操作"),
        MOBILE_NOT_EXIST(718, "手机号不能为空,请输入手机号"),
        USERNAME_NOT_EXIST(719, "用户名不能为空,请输入用户名"),
        PASSWORD_NOT_EXIST(720, "密码不能为空,请输入密码"),
        NEW_PASSWORD_REPEATED(721, "新密码与老密码不能相同"),
        UCENTER_ERROR(722, "注册到统一用户中心异常"),
        CHANNEL_CODE_NOT_EXIST(723, "渠道编码不能为空"),
        CHANNEL_NOT_EXIST(724, "用户渠道不存在"),
        USER_MOBILE_NOT_EXIST(725, "用户没有绑定手机号"),
        RESET_PASSWORD_ERROR(725, "重置密码失败"),
        UPDATE_PASSWORD_ERROR(726, "修改密码失败"),
        NOT_LOGIN_ERROR(727, "用户未登录"),
        USER_REGISTER_INFO_ERROR(728, "用户注册信息填写错误"),
        PASSWORD_RULE_ERROR(729, "密码格式错误"),
        VENDOR_IS_NOT_EXIST(730, "商户不存在"),
        VENDOR_STATUS_CLOSE(731, "店铺已关闭"),
        VENDOR_FROZEN(732, "店铺已冻结"),
        VENDOR_NOT_PERFECT(733, "店铺信息未完善");
        private int code;
        private String description;

        User(int code, String description) {
            this.code = code;
            this.description = description;
        }

        public int getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }
    }

    /**
     * use code 800-899
     */
    enum Stock implements ExceptionType {


        ;
        private int code;
        private String description;

        Stock(int code, String description) {
            this.code = code;
            this.description = description;
        }

        public int getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }
    }

    /**
     * use code 900-999
     */
    enum Product implements ExceptionType {
        NOT_EXIST(900, "商品不存在"),
        ALREADY_OFF_SALE(901, "商品已经下架"),
        WITHOUT_STOCK(902, "商品库存不足"),
        WITHOUT_PRICE(903, "商品价格不存在"),
        IDX_INVALID(904, "显示顺序字段顺序值已存在"),
        INVALID_PRICE(905, "商品价格异常"),
        CATEGORY_NOT_EXISTS(906, "商品分类不存在"),
        POST_PRODUCT_DATA_ERROR(907, "提交商品数据错误"),
        BRAND_NOT_EXISTS(908, "商品品牌不存在"),
        EXTEND_NOT_VALID(909, "商品属性异常"),
        EDIT_DATA_ID_ERROR(910, "更新数据 ID 不存在"),
        ILLEGAL_ERROR(911, "参数错误"),
        LOG_NOT_ERROR(912, "商品审核记录不存在"),
        LOCKED_ERROR(913, "商品已被锁定"),
        CASCADE_PRICE(914, "商品配置已存在"),
        PROPERTY_NOT_ERROR(915, "属性值不存在"),
        CASCADE_ERROR(918, "扩展属错误"),

        GEO_PRODUCT_NOT_EXISTS(916, "区域商品信息不存在"),
        SALE_STATUS_ERROR(917, "商品上下架状态出错"),
        INTERNAL_ERROR(918, "内部数据存错"),

        EVALUATION_NOT_EXISTS(920, "商品评价不存在");

        private int code;
        private String description;

        Product(int code, String description) {
            this.code = code;
            this.description = description;
        }

        public int getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }
    }

    /**
     * use code 1000-1999
     */
    enum Global implements ExceptionType {
        SERVER_EXCEPTION(1000, "Sorry,服务器开小差了！"),
        ID_NOT_EXIST(1001, "id不存在"),
        PARAMETER_ERROR(1002, "参数有误，请稍后重试！"),
        MISSING_REQUIRED_PARAMS(1003, "参数有误，请稍后重试！"),
        SMS_SEND_TIME_INVALID(1004, "验证码发送失败"),
        ILLEGAL_SMS_CODE(1005, "短信验证码错误"),
        SERVER_TIPS(1006, ""),//服务器给手机端的提示信息
        SIGN_ERROR(1007, "签名错误"),
        ILLEGAL_OPERATION(1008, "非法操作"),
        NULL_OBJECT(1009, "对象不能为NULL"),
        LOCATION_ERROR(1010, "获取定位城市失败"),
        SMS_NOT_EXIST(1011, "短信验证码不能为空,请输入短信验证码"),
        CHANNEL_CODE_NOT_EXIST(1012, "渠道编码不能为空"),
        CHANNEL_NOT_EXIST(1013, "用户渠道不存在"),
        VALIDATE_CODE_NOT_EXIST(1014, "图片验证码不能为空,请输入图片验证码"),
        VALIDATE_CODE_ERROR(1015, "图片验证码错误"),
        TGT_COOKIE_INVALID(1016, "TGT无效"),
        SESSION_INVALID(1017, "会话失效"),
        CONTAIN_SENSITIVE_WORLD(1018, "包含敏感词");

        private int code;
        private String description;

        Global(int code, String description) {
            this.code = code;
            this.description = description;
        }


        public int getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }

    }

    /**
     * 2000 - 2100
     */
    enum ShippingAddress implements ExceptionType {
        NOT_EXISTED(2000, "收货地址不存在"),
        OPERATION_NOT_ALLOWED(2001, "用户不匹配,无权操作"),
        ERROR_ADDRESSALIAS(2002, "错误的地址类型"),
        ERROR_DATA(2003, "获取到异常地址"),
        INDEX_OUT_OF_BOUNDS_ERROR(2004, "收货地址超过限制");
        private int code;
        private String description;

        ShippingAddress(int code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public String getDescription() {
            return description;
        }
    }

    /**
     * 2200 - 2300
     */
    enum VerifyCode implements ExceptionType {
        NOT_EQUAL(2200, "验证码错误"),;
        private int code;
        private String description;

        VerifyCode(int code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public String getDescription() {
            return description;
        }
    }

    /**
     * 2300 - 2400
     */
    enum SecretQuestion implements ExceptionType {
        NOT_EXIST(2311, "密保问题不存在"),
        OPERATION_NOT_ALLOW(2312, "无权查看别人的密保问题"),;
        private int code;
        private String description;

        SecretQuestion(int code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public String getDescription() {
            return description;
        }
    }

    /**
     * 2400-2500
     */
    enum BankCard implements ExceptionType {
        NOT_EXIST(2411, "用户没有绑定银行卡"),
        EXCEPTED_ONE(2412, "您只能绑定一张银行卡"),
        ERROR_BANK_CODE(2413, "错误的银行卡号"),
        BANKCARD_INFO_ERROR(2414, "银行卡信息不全");
        private int code;
        private String description;

        BankCard(int code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public String getDescription() {
            return description;
        }
    }

    /**
     * 3000-3100
     */
    enum PrePaidCard implements ExceptionType {
        CARD_ID_ERROR(3000, "未输入储值卡卡号"),
        FAILED_TO_GET_PREPAID(3001, "未获取到储值卡信息");

        PrePaidCard(int code, String description) {
            this.code = code;
            this.description = description;
        }

        private int code;
        private String description;

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public String getDescription() {
            return description;
        }
    }
}
