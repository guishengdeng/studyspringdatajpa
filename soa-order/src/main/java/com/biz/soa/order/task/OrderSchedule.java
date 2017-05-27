package com.biz.soa.order.task;

import com.biz.service.order.frontend.OrderFrontendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


/**
 * 订单定时任务
 *
 * @author lei
 * @date 2017年05月27日
 * @reviewer
 * @see
 */
@Configuration
@EnableScheduling // 启用定时任务
public class OrderSchedule {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderFrontendService orderFrontendService;

    @Scheduled(cron = "0 */1 * * * ?") // 每分支执行一次
    public void cancelOrderTask() {
        logger.info("Begin cancel order task...");
        orderFrontendService.payLimitOrderTask();
        logger.info("End cancel order task...");
    }
}
