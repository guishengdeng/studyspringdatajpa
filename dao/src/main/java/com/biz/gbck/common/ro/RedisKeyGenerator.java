package com.biz.gbck.common.ro;


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

}
