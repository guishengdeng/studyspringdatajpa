package com.biz.message;

/**
 * 通用队列定义抽象
 *
 * @author yanweijin
 * @date 2016年7月24日
 * @reviewer
 */
public interface QueueDefinition {
    /**
     * 队列签名<p> 规则:[exchangeName:[routingKey:]]queueName<p> 形如:com.biz.demoexchange:myroutingkey:smsqueue<br>
     * ->解出队列名问smsqueue,交换机名为com.biz.demoexchange,路由键为myroutingkey<p> 或:com.biz.demoexchange:smsqueue<br>
     * ->解出队列名为smsqueue,交换机名为com.biz.demoexchange,路由键与队列名相同<p> 或:smsqueue<br>
     * ->解出队列名为smsqueue,交换机名根据是队列定义还是主题定义,选用默认交换机(com.biz.base.direct/com.biz.base.topic),路由键与队列名相同<p>
     *
     * <b><font color="red">注意:***在rocketmq(ons)的场合,只有队列名有效***</font></b>
     *
     * @return 组件签名字符串
     * @author yanweijin
     * @date 2016年7月24日
     */
    String getSignature();

    /**
     * 是否自动由程序创建
     *
     * @return true表示是, false表示否
     * @author yanweijin
     * @date 2016年7月24日
     */
    boolean isAutomaticCreation();

    /**
     * 返回队列类型:queue/topic
     *
     * @author yanweijin
     * @date 2016年7月30日
     */
    QueueType getType();
}
