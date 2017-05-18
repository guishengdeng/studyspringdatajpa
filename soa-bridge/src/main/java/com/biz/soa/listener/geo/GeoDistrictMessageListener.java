package com.biz.soa.order.listener.geo;


import com.biz.gbck.vo.geo.MnsGeoDistrictVo;
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
public class GeoDistrictMessageListener implements MessageListener<MnsGeoDistrictVo> {

    private Logger logger = LoggerFactory.getLogger(GeoDistrictMessageListener.class);

    @Reference
    private GeoService geoService;

    @Override
    public QueueDefinition getListenedQueue() {
        return BizBaseQueue.MQ_GEO_CITY_QUEUE;
    }

    @Override
    public void onMessage(BizMessage<MnsGeoDistrictVo> message) {
        try {
            logger.debug("Received message[{}] form queue:{}", message, BizBaseQueue.MQ_GEO_CITY_QUEUE.getSignature());
            MnsGeoDistrictVo mnsGeoDistrictVo = message.getPayload();
            geoService.trans(mnsGeoDistrictVo);
        } catch (Exception e) {
            logger.debug("Maintain geo strict change error. {}", message, e);
        }
    }

}
