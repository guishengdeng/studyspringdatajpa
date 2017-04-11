package com.biz.core.notification;

/**
 * @author david-liu
 * @date 2016年08月13日
 * @reviewer
 * @see
 */
public class PushMessage {
    /**
     * 在锁屏状态下，或关闭app时在上面横幅显示的消息。
     */
    private String alertMsg;

    /**
     * 未处理消息数量（右上角的数字）
     */
    private Integer badge;

    /**
     * 播放的声音
     */
    private String sound = "default";

    /**
     * 跳转URL
     */
    private String url;

    public PushMessage(String alertMsg, Integer badge, String sound, String url) {
        this.alertMsg = alertMsg;
        this.badge = badge;
        this.sound = sound;
        this.url = url;
    }

    public PushMessage() {
    }

    public String getAlertMsg() {
        return alertMsg;
    }

    public void setAlertMsg(String alertMsg) {
        this.alertMsg = alertMsg;
    }

    public Integer getBadge() {
        return badge;
    }

    public void setBadge(Integer badge) {
        this.badge = badge;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
