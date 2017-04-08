package com.depotnextdoor.support.web.assist;

/**
 * 标记一个vo具有ip属性,在构建消息时注入
 *
 * @author yanweijin 2016年5月9日
 */
public interface IPAddressAware {
    String getIp();

    void setIp(String ip);
}
