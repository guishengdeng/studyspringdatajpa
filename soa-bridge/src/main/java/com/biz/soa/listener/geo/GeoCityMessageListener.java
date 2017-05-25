package com.biz.soa.order.listener.geo;


import com.biz.gbck.vo.geo.MnsGeoCityVo;
import com.biz.message.BizMessage;
import com.biz.message.MessageListener;
import com.biz.message.QueueDefinition;
import com.biz.message.queue.BizBaseQueue;
import com.biz.service.geo.interfaces.GeoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Reference;
import org.springframework.stereotype.Component;

/**
 * @author lei
 * @date 2016年12月22日
 */
@Component
public class GeoCityMessageListener implements MessageListener<MnsGeoCityVo> {

    private Logger logger = LoggerFactory.getLogger(GeoCityMessageListener.class);

    @Reference
    private GeoService geoService;

    @Override
    public QueueDefinition getListenedQueue() {
        return BizBaseQueue.MQ_GEO_CITY_QUEUE;
    }

    @Override
    public void onMessage(BizMessage<MnsGeoCityVo> message) {
        try {
            logger.debug("Received message[{}] form queue:{}", message, BizBaseQueue.MQ_GEO_CITY_QUEUE
                    .getSignature());
            MnsGeoCityVo mnsGeoCityVo = message.getPayload();
            geoService.trans(mnsGeoCityVo);
        } catch (Exception e) {
            logger.error("Maintain geo city change error. {}", message, e);
        }
    }

}
