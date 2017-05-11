package com.biz.soa.listener.geo;


import com.biz.gbck.vo.geo.MnsGeoProvinceVo;
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
public class GeoProvinceMessageListener implements MessageListener<MnsGeoProvinceVo> {

    private Logger logger = LoggerFactory.getLogger(GeoProvinceMessageListener.class);

    @Reference
    private GeoService geoService;

    @Override
    public QueueDefinition getListenedQueue() {
        return BizBaseQueue.MQ_GEO_PROVINCE_QUEUE;
    }

    @Override
    public void onMessage(BizMessage<MnsGeoProvinceVo> message) {
        try {
            logger.debug("Received message[{}] form queue:{}", message, BizBaseQueue.MQ_GEO_PROVINCE_QUEUE.getSignature());
            MnsGeoProvinceVo mnsGeoProvinceVo = message.getPayload();
            geoService.trans(mnsGeoProvinceVo);
        } catch (Exception e) {
            logger.error("Maintain geo province change error. {}", message, e);
        }
    }

}
