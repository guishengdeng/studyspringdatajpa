package com.biz.gbck.common.ro;


import com.biz.gbck.enums.user.ShopChannel;

/**
 * Created by defei on 3/11/16.
 * Key 命名规则  大模块:对象名:Id
 * <p/>
 * 大模块列表：
 * 全局  global
 * 用户 user
 * 店铺 shop
 * 商品 product
 * 订单 order
 * 消息活动 info
 * 添加大模块 需要 和邓德飞 商议。
 * <p/>
 * 对象名 连字符 使用 _ (下划线)
 */
public class RedisKeyGenerator {
    public static class Upgrade {

        /**
         * 客户端升级程序 sortset socre 是版本号转换成的整数,member是 os +":"+ version;
         *
         * @param os
         * @return
         */
        public static String getUpgradeSortSetKey(String os) {
            return "global:all_upgrade:" + os;
        }

        /**
         * 存储客户端升级程序的对象的HASH
         *
         * @param id
         * @return
         */
        public static String getUpgradeHashKey(String id) {
            return "global:upgrade:" + id;
        }
    }

    public static class Promotion {

        public static String getPromotionHashKey(Long id) {
            return "info:promotion:" + id;
        }

        /**
         * 所有用户的Id， score ： 发布时间，member ： promotionId
         *
         * @return
         */
        public static String getAllPromotionSortSetKey() {
            return "global:all_promotionIds";
        }

        /**
         * 活动最后一次更新时间 hash key
         */
        public static String getSystemLatestUpdateTimeHashKey(){
            return "global:promotion_system_latestUpdateTime";
        }

        /**
         * 用户最后一次查看活动中心时间 hash key
         */
        public static String getUserLatestUpdateTimeHashKey(Long userId){
            return "global:promotion_user_latestUpdateTime:" + userId;
        }

    }

    public static class Notice {

        public static String getNoticeHashKey(Long id) {
            return "info:notice:" + id;
        }

        /**
         * 某用户的消息
         *
         * @param userId score ： noticeId，member ： noticeId
         * @return
         */
        public static String getUserNoticeSortSetKey(Long userId) {
            return "user:notice:" + userId;
        }

    }

    public static class User {

        public static final String DEVICEID = "lastLoginDeviceToken";
        public static final String TOEKN = "lastToken";
        public static final String LASTUSERAGENT = "lastUserAgent";

        public static String getUserInfoHashKey(Long userId) {
            return "user:user_" + userId;
        }

        /**
         * 根据手机号获取对应HASH 的 key 和 value
         * 手机号码是否存在,hash key = USER_MOBILE+手机号码前9位
         */
        @Deprecated public static String[] getMobileKeyAndField(String mobile) {
            return new String[] {"global:mobile:" + mobile.substring(0, 9), mobile.substring(9)};
        }

        /**
         * 根据手机号获取对应HASH 的 key 和 value
         */
        public static String getMobileToUserIdHashKey(String mobile) {
            return "global:mobile:" + mobile;
        }

        /**
         * 根据店铺渠道查询用户id
         */
        public static String getChannelUserIdToUserIdHashKey(ShopChannel shopChannel, Long channelUserId) {
            return "global:channel:" + shopChannel.getValue() + ":user:" + channelUserId;
        }

        /**
         * 根据店铺渠道mobile查询渠道用户id
         */
        public static String getChannelMobileToChannelUserIdHashKey(ShopChannel shopChannel, String mobile) {
            return "global:channel:" + shopChannel.getValue() + ":mobile:" + mobile;
        }

        /**
         * 所有用户的Id， score ： 注册时间，member ： userId
         *
         * @return
         */
        public static String getAllUserSortSetKey() {
            return "global:all_userIds";
        }

        /**
         * @param tel
         * @return
         * @Description: 根据手机号获取对应HASH 的 key 和 value
         * @author Nian.Li
         * @date 2016年4月15日 下午12:56:15 
         */
        public static String getRecommendUserHashKey(String tel) {
            return "user:recommend:" + tel;
        }

        /**
         * 同步至老系统失败用户HASH key
         * @return
         */
        public static String getRegisterFailedUserHashKey(){
            return "user:register_failed_ids";
        }

        /**
         * 同步重置密码失败用户HASH key
         * @return
         */
        public static String getResetPwdToOMSFailedUserHashKey(){
            return "user:reset_pwd_to_oms_failed_ids";
        }

        public static String getBlockCreateOrderUserKey() {
            return "global:block_create_order_user";
        }

        public static String getShareTimesKey(){
            return "global:shareTimes";
        }

    }

}
