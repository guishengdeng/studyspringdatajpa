package com.biz.soa.service.oms;


import com.alibaba.fastjson.JSONObject;
import com.aliyun.mns.model.Message;
import com.biz.gbck.enums.geo.GeoLevel;
import com.biz.gbck.vo.geo.MnsGeoCityVo;
import com.biz.gbck.vo.geo.MnsGeoDistrictVo;
import com.biz.gbck.vo.geo.MnsGeoProvinceVo;
import com.biz.message.MessageService;
import com.biz.message.SimpleBizMessage;
import com.biz.message.aliyunmns.interfaces.MNSMessageListener;
import com.biz.message.aliyunmns.interfaces.MNSService;
import com.biz.message.queue.BizBaseQueue;
import com.biz.service.AbstractBaseService;
import com.biz.soa.dao.db.mysql.po.MnsGeoMessage;
import com.biz.soa.dao.db.mysql.repository.MnsGeoMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;

/**
 * 中台GEO变更service处理
 *
 * @author bruce.qin
 * @usage
 * @reviewer
 * @since 2016年11月16日
 */
@Service
public class OmsGeoService extends AbstractBaseService {

    @Autowired
    private MnsQueueConfig mnsQueueConfig;

    @Autowired
    private MNSService mnsService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private MnsGeoMessageRepository mnsGeoMessageRepository;

    @PostConstruct
    public void init() {
        try {
            logger.debug("中台对接[Geo省份对接] --> 准备监听中台Mns队列: [{}]", mnsQueueConfig.mnsGeoProvince);
            bindGeoProvinceQueue();
        } catch (Exception e) {
            logger.error("中台对接[Geo省份对接] --> 监听中台Mns队列[{}]失败", mnsQueueConfig.mnsGeoProvince, e);
        }
        try {
            logger.debug("中台对接[Geo城市对接] --> 准备监听中台Mns队列: [{}]", mnsQueueConfig.mnsGeoCity);
            bindGeoCityQueue();
        } catch (Exception e) {
            logger.error("中台对接[Geo城市对接] --> 监听中台Mns队列[{}]失败", mnsQueueConfig.mnsGeoCity, e);
        }
        try {
            logger.debug("中台对接[Geo区对接] --> 准备监听中台Mns队列: [{}]", mnsQueueConfig.mnsGeoDistrict);
            bindGeoDistrictQueue();
        } catch (Exception e) {
            logger.error("中台对接[Geo区对接] --> 监听中台Mns队列[{}]失败", mnsQueueConfig.mnsGeoDistrict, e);
        }
    }

    private void bindGeoProvinceQueue() throws Exception {
        mnsService.bindListenerToQueue(mnsQueueConfig.mnsGeoProvince, new MNSMessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    logger.debug("中台对接[Geo省份对接] --> 开始消费中台Geo省份消息: [{}]", message.getMessageBody());
                    MnsGeoProvinceVo mnsGeoProvinceVo = JSONObject.parseObject(message.getMessageBody(),
                            MnsGeoProvinceVo.class);
                    if (mnsGeoProvinceVo != null) {
                        MnsGeoMessage geoMnsMessage = new MnsGeoMessage();
                        geoMnsMessage.setId(idService.nextId());
                        geoMnsMessage.setGeoLevel(GeoLevel.GEO_PROVINCE);
                        geoMnsMessage.setMessage(message.getMessageBody());
                        geoMnsMessage.setCreateTimestamp(new Timestamp(message.getEnqueueTime().getTime()));
                        mnsGeoMessageRepository.save(geoMnsMessage);
                        //发送消息到队列
                        messageService.sendMessage(BizBaseQueue.MQ_GEO_PROVINCE_QUEUE, SimpleBizMessage.newMessage
                                (mnsGeoProvinceVo));
                    }
                } catch (Exception e) {
                    logger.error("中台对接[Geo省份对接] --> 消费Geo省份消息失败, Mns消息句柄[{}]", message.getMessageBody());
                }
            }
        });
    }

    private void bindGeoCityQueue() throws Exception {
        mnsService.bindListenerToQueue(mnsQueueConfig.mnsGeoCity, new MNSMessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    logger.debug("中台对接[Geo城市对接] --> 开始消费中台Geo城市消息: [{}]", message.getMessageBody());
                    MnsGeoCityVo mnsGeoCityVo = JSONObject.parseObject(message.getMessageBody(), MnsGeoCityVo.class);
                    if (mnsGeoCityVo != null) {
                        MnsGeoMessage geoMnsMessage = new MnsGeoMessage();
                        geoMnsMessage.setId(idService.nextId());
                        geoMnsMessage.setGeoLevel(GeoLevel.GEO_CITY);
                        geoMnsMessage.setMessage(message.getMessageBody());
                        geoMnsMessage.setCreateTimestamp(new Timestamp(message.getEnqueueTime().getTime()));
                        mnsGeoMessageRepository.save(geoMnsMessage);
                        //发送消息到队列
                        messageService.sendMessage(BizBaseQueue.MQ_GEO_CITY_QUEUE, SimpleBizMessage.newMessage
                                (mnsGeoCityVo));

                    }
                } catch (Exception e) {
                    logger.error("中台对接[Geo城市对接] --> 消费Geo城市消息失败, Mns消息句柄[{}]", message.getMessageBody());
                }
            }
        });
    }

    private void bindGeoDistrictQueue() throws Exception {
        mnsService.bindListenerToQueue(mnsQueueConfig.mnsGeoDistrict, new MNSMessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    logger.debug("中台对接[Geo区对接] --> 开始消费中台Geo区消息: [{}]", message.getMessageBody());
                    MnsGeoDistrictVo mnsGeoDistrictVo = JSONObject.parseObject(message.getMessageBody(),
                            MnsGeoDistrictVo.class);
                    if (mnsGeoDistrictVo != null) {
                        MnsGeoMessage geoMnsMessage = new MnsGeoMessage();
                        geoMnsMessage.setId(idService.nextId());
                        geoMnsMessage.setGeoLevel(GeoLevel.GEO_DISTRICT);
                        geoMnsMessage.setMessage(message.getMessageBody());
                        geoMnsMessage.setCreateTimestamp(new Timestamp(message.getEnqueueTime().getTime()));
                        mnsGeoMessageRepository.save(geoMnsMessage);
                        //发送消息到队列
                        messageService.sendMessage(BizBaseQueue.MQ_GEO_DISTRICT_QUEUE, SimpleBizMessage.newMessage
                                (mnsGeoDistrictVo));
                    }
                } catch (Exception e) {
                    logger.error("中台对接[Geo区对接] --> 消费Geo区消息失败, Mns消息句柄[{}]", message.getMessageBody());
                }
            }
        });
    }
}
