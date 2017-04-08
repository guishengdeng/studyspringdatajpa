package com.depotnextdoor.core.notification;

import java.io.Serializable;

/**
 * 推送对象
 * @author david-liu
 * @date 2016年08月13日
 * @reviewer
 * @see
 */
public class Notification implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 5275911233087953818L;

    public static final int MI_RETRY_TIMES = 3;

    public static final String MI_PACKAGE_NAME = "com.yijiuyijiu.eshop";

    public static final String NOTIFICATION_TITLE = "快喝";

    /**
     * 推送的方式(发送给所有人/发送给单个用户)
     * P2P
     */
    private NotifyType notifyType = NotifyType.P2P;

    /**
     * 推送目标(单个目标使用向推送平台注册的registerId作为target,
     * 选用广播方式发送推送不需要提供target信息)
     * UserRo.lastToken;
     */
    private String target;

    /**
     * 推送的目标平台(IOS/ANDROID/MI)
     * userRo.lastUserAgent
     * NotificationChannel
     */
    private String platform;

    /**
     * 推送目标预留字段
     */
    private String deviceFree;

    /**
     * 推送方式(以弹出框的形式推送还是推送到APP内部)
     * ALERT
     */
    private NotificationSendType sendType = NotificationSendType.ALERT;

    /**
     * 推送消息的实体对象
     */
    private PushMessage pushMessage;


    /**
     * Apple App 唯一标识Token
     */
    private String deviceToken;

    public static int getMiRetryTimes() {
        return MI_RETRY_TIMES;
    }

    public static String getMiPackageName() {
        return MI_PACKAGE_NAME;
    }

    public static String getNotificationTitle() {
        return NOTIFICATION_TITLE;
    }

    public NotifyType getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(NotifyType notifyType) {
        this.notifyType = notifyType;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getDeviceFree() {
        return deviceFree;
    }

    public void setDeviceFree(String deviceFree) {
        this.deviceFree = deviceFree;
    }

    public NotificationSendType getSendType() {
        return sendType;
    }

    public void setSendType(NotificationSendType sendType) {
        this.sendType = sendType;
    }

    public PushMessage getPushMessage() {
        return pushMessage;
    }

    public void setPushMessage(PushMessage pushMessage) {
        this.pushMessage = pushMessage;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

}
