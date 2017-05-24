package com.biz.gbck.common.model.voucher;

public interface IVoucher {


    interface Action {
        /**
         * 注册发放的优惠券类型
         */
        int REGIST = 1;

        /**
         * 邀请他人注册发放的优惠券类型
         */
        int INVITE = 2;

        /**
         * 系统管理员发放
         */
        int ADMIN_SEND = 3;

    }


    interface Status {

        /**
         * 作废
         */
        int DISCARD = 0;

        /**
         * 新建 未发放
         */
        int NORMAL = 10;

        /**
         * 已经绑定用户
         */
        int BINDING = 15;

        /**
         * 已激活
         */
        int ACTIVIED = 20;

        /**
         * 锁定不能被支付时使用
         */
        int BLOCK = 25;

        /**
         * 过期
         */
        int EXPIRED = 35;

        /**
         * 下单使用了
         */
        int USED = 50;

    }

}
