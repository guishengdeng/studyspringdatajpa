package com.biz.gbck.common.exception;

/**
 * 异常错误码及提示文字
 *
 * @author defei
 */
public class DepotnearbyExceptionFactory {

    public static class GLOBAL {

        public static final CommonException PARAMETER_ERROR =
            new CommonException("参数错误", ExceptionCode.Global.PARAMETER_ERROR);

        public static final CommonException NO_AUTHORITY =
            new CommonException("无权操作", ExceptionCode.Global.NO_AUTHORITY);
    }


    public static class SMS {

        public static final CommonException ILLEGAL_MOBILE =
            new CommonException("请输入正确的手机号码", ExceptionCode.Global.INFO_TO_USER);

        public static final CommonException USER_EXIST =
            new CommonException("您输入的手机号码已存在，请登录", ExceptionCode.Global.INFO_TO_USER);

        public static final CommonException INVALID_SMS_CODE =
            new CommonException("验证码输入不正确", ExceptionCode.Global.INFO_TO_USER);
    }


    public static class User {

        public static final CommonException ILLEGAL_MOBILE =
            new CommonException("请输入正确的手机号码", ExceptionCode.Global.INFO_TO_USER);

        public static final CommonException USER_NOT_EXIST =
            new CommonException("用户不存在", ExceptionCode.User.USER_NOT_EXIST);

        public static final CommonException USER_EXIST =
            new CommonException("用户已存在", ExceptionCode.User.MOBILE_EXISTED);

        public static final CommonException PWD_NOT_MATCH =
            new CommonException("密码不正确", ExceptionCode.User.PWD_NOT_MATCH);

        public static final CommonException ILLEGAL_PASSWORD =
            new CommonException("密码格式不正确", ExceptionCode.User.ILLEGAL_PASSWORD);

        public static final CommonException USER_DISABLED =
            new CommonException("您的账号已被禁用，请联系客服", ExceptionCode.User.USER_DISABLED);
        public static final CommonException ILLEGAL_INVITER_CODE =
            new CommonException("请输入正确的推荐码", ExceptionCode.User.USER_DISABLED);
    }


    public static class Shop {

        public static final CommonException SHOP_NOT_EXIST =
            new CommonException("商户不存在", ExceptionCode.Global.INFO_TO_USER);

        public static final CommonException REJECT_REASON_IS_EMPTY =
            new CommonException("拒绝原因为空", ExceptionCode.Global.INFO_TO_USER);

        public static final CommonException SHOP_DETAIL_IS_AUDITING =
            new CommonException("资料正在审核", ExceptionCode.Shop.SHOP_STATUS_UNSUITABILITY);

        public static final CommonException SHOP_DETAIL_NOT_FOUND =
            new CommonException("请提交店铺资料", ExceptionCode.Shop.SHOP_STATUS_UNSUITABILITY);

        public static final CommonException SHOP_DISABLED =
            new CommonException("店铺状态异常，请联系客服", ExceptionCode.Shop.SHOP_DISABLED);
    }


    public static class ShopType {
        public static final CommonException SHOP_TYPE_NOT_EXIST =
            new CommonException("商户类型不存在", ExceptionCode.Global.INFO_TO_USER);
    }


    public static class Voucher {

        public static final CommonException VOUCHER_NOT_EXISTS =
            new CommonException("优惠券不存在", ExceptionCode.Voucher.VOUCHER_NOT_EXISTS);

        public static final CommonException VOUCHER_OFFSET_COUNT_ERROR =
            new CommonException("优惠金额不满足", ExceptionCode.Voucher.VOUCHER_OFFSET_COUNT_ERROR);

        public static final CommonException VOUCHER_SHORTAGE_ERROR =
            new CommonException("优惠券可用数量不足", ExceptionCode.Voucher.VOUCHER_SHORTAGE_ERROR);

        public static final CommonException VOUCHER_PAYMENT_NOT_SUPPORT =
            new CommonException("优惠券不支持当前支付类型", ExceptionCode.Voucher.VOUCHER_PAYMENT_NOT_SUPPORT);

        public static final CommonException VOUCHER_VALIDATE_ERROR =
            new CommonException("优惠券使用条件不满足", ExceptionCode.Voucher.VOUCHER_VALIDATE_ERROR);
    }

    public static class Promotion {
        public static final CommonException PROMOTION_NOT_EXISTS =
                new CommonException("促销活动不存在", ExceptionCode.Promotion.NOT_EXISTS);


    }

    public static class GEO {
        public static final CommonException PROVINCE_NOT_EXIST =
          new CommonException("省份不存在", ExceptionCode.Global.INFO_TO_USER);
        public static final CommonException CITY_NOT_EXIST =
          new CommonException("城市不存在", ExceptionCode.Global.INFO_TO_USER);
        public static final CommonException DISTRICT_NOT_EXIST =
          new CommonException("区/县不存在", ExceptionCode.Global.INFO_TO_USER);
    }

}
