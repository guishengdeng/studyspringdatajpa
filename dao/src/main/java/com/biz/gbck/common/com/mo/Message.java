package com.biz.gbck.common.com.mo;

public interface Message {

    interface QUEUE {

        /**
         * 短信校验码队列
         */
        String MQ_SMS_CODE = "smsqueue";

        /**
         * 邮件发送队列
         */
        String MQ_EMAIL = "emailqueue";

        /**
         * 内容审核队列
         */
        String MQ_VERIFY_CONTENT = "verifyContent";

        /**
         * 支付宝消息通知
         */
        String MQ_ALIPAY_NOTIFY = "alipayNotify";

        /**
         * 微信支付通知
         */
        String MQ_WECHAT_NOTIFY = "wechatNotify";


        /**
         * 客户端推送消息队列
         */
        String MQ_CLIENT_PUSH_MSG = "clientPushMessageQueue";

        /**
         * 徙木申请提交队列
         */
        String XIMU_SUBMIT_SUCCESS = "ximuSubmitSuccessQueue";

        /**
         * 徙木贷款订单完队列，用于向徙木上传贷款合同pdf
         */
        String XIMU_FINISH_LOAN_ORDER_QUEUE = "ximuFinishLoanOrderQueue";

        /**
         * 发送徙木赊销协议pdf给用户
         */
        String EMAIL_XIMU_AGREE_PDF_TO_USER_QUEUE ="emailXimuAgreePdfToUserQueue";

        /**
         * 徙木金融贷款协议邮件发送到用户
         */
        String XIMU_AGREEMENT_MAIL_QUEUE = "ximuAgreementMailQueue";

        /**
         * OMS推送创建订单消息队列
         */
        String MQ_OMS_ORDER_CREATED_QUEUE = "omsOrderCreatedQueue";

        /**
         * OMS推送取消订单消息队列
         */
        String MQ_OMS_ORDER_CANCEL_QUEUE = "omsOrderCancelQueue";

        /**
         * OMS创建会员消息队列
         */
        String MQ_OMS_MEMBER_CREATE_QUEUE = "omsMemberCreateQueue";

        /**
         * OMS会员重置密码消息队列
         */
        String MQ_OMS_MEMBER_RESET_PASSWORD_QUEUE = "omsMemberResetPasswordQueue";


        /** OMS主数据队列 begin**/

        /**
         * OMS 订单状态回写消息队列
         */
        String MQ_OMS_ORDER_RESERVE_QUEUE = "omsOrderReserveQueue";

        /**
         * OSS全量数据队列
         */
        String MQ_OSS_ALL_DATA_QUEUE = "ossAllDataQueue";

        /**
         * OMS增量库存消息队列
         */
        String MQ_OMS_STOCK_CHANGE_QUEUE = "omsStockChangeQueue";

        /**
         * OMS商品消息队列
         */
        String MQ_OMS_PRODUCT_QUEUE = "omsProductQueue";

        /**
         * OMS组合商品消息队列
         */

        String MQ_OMS_GROUP_PRODUCT_QUEUE = "omsGroupProductQueue";
        /**
         * OMS价格消息队列
         */
        String MQ_OMS_DEPOT_QUEUE = "omsDepotQueue";

        /**
         * OMS价格消息队列
         */
        String MQ_OMS_PRICE_QUEUE = "omsPriceQueue";

        /**
         * OMS会员消息队列
         */
        String MQ_OMS_MEMBER_QUEUE = "omsMemberQueue";

        /**
         * OMS员工消息队列
         */
        String MQ_OMS_STAFF_QUEUE = "omsStaffQueue";

        /**
         * OMS仓库主数据消息队列
         */
        String MQ_OMS_WAREHOUSE_QUEUE = "omsWarehouseQueue";
        /** OMS主数据队列 end**/


    }


    interface TOPIC {

        /**
         * 词典更新队列
         */
        String TP_DICT_UPDATE = "lexiconUpdate";

        /**
         * 索引更新队列
         */
        String TP_INDEX = "seachindex";

        /**
         * 内容审核的敏感词库更新
         */
        String TP_SENSITIVEWORD_UPDATE = "sensitiveWordUpdate";


        /**
         * 内容审核的敏感词库更新
         */
        String TP_CACHE_UPDATE = "cacheUpdate";

        /**
         * 区域首页模板更新
         */
        String TP_TEMPLATE_UPDATE = "templateUpdate";

        /**
         * 商品限制变更
         */
        String TP_PRODUCT_LIMIT_CHANGES = "productLimitChanges";
    }


}
