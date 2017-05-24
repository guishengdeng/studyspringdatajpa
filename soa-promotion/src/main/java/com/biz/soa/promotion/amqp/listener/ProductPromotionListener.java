package com.biz.soa.promotion.amqp.listener;

import com.biz.gbck.vo.product.promotion.PromotionNoticeVO;
import com.biz.message.BizMessage;
import com.biz.message.MessageListener;
import com.biz.message.QueueDefinition;
import com.biz.soa.promotion.service.DepotNextDoorPromotionCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 促销变动Listener
 * <p>
 * 用于接受促销变化通知, 接收到促销信息变更之后, 同步促销信息到缓存
 * </p>
 *
 * Created by david-liu on 2017/05/19 14:52.
 */
@Component
public class ProductPromotionListener implements MessageListener<PromotionNoticeVO> {

    @Autowired
    private QueueDefinition promotionQueueDefinition;

    @Autowired
    private DepotNextDoorPromotionCacheService depotNextDoorPromotionCacheService;

    @Override
    public QueueDefinition getListenedQueue() {
        return promotionQueueDefinition;
    }

    @Override
    public void onMessage(BizMessage<PromotionNoticeVO> message) {

    }
}
