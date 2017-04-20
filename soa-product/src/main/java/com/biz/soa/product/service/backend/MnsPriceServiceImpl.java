package com.biz.soa.product.service.backend;

import com.alibaba.fastjson.JSON;
import com.biz.core.util.DateUtil;
import com.biz.gbck.dao.redis.repository.product.bbc.PriceRedisDao;
import com.biz.gbck.dao.redis.ro.product.PriceRo;
import com.biz.gbck.enums.oms.InvalidOmsDataType;
import com.biz.gbck.enums.oms.InvalidOmsMessageType;
import com.biz.gbck.enums.oss.OssType;
import com.biz.gbck.enums.product.VendorTypeEnum;
import com.biz.gbck.vo.oms.event.InvalidOmsMessageVo;
import com.biz.gbck.vo.product.event.MnsPriceVo;
import com.biz.gbck.vo.product.frontend.ProductIdxIdentityVo;
import com.biz.message.MessageService;
import com.biz.message.SimpleBizMessage;
import com.biz.message.queue.BizBaseQueue;
import com.biz.service.AbstractBaseService;
import com.biz.service.mns.MnsPriceService;
import com.biz.gbck.transform.product.MnsPriceVo2PriceRo;
import com.google.common.collect.Lists;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 中台价格主数据服务
 *
 * @author zhangcheng
 * @date 2017/1/20
 * @reviewer
 * @see
 */
@Service
public class MnsPriceServiceImpl extends AbstractBaseService implements MnsPriceService {

    @Autowired
    private MessageService messageService;

    @Autowired
    private PriceRedisDao priceRedisDao;

    /**
     * 将未通过校验的Vo发送指定队列
     *
     * @author zhangcheng
     */
    private void sendInvalidVoToMessage(InvalidOmsMessageVo vo) {
        messageService.sendMessage(BizBaseQueue.MQ_INVALID_OMS_MSG_QUEUE, SimpleBizMessage.newMessage(vo));
    }

    /**
     * 校验中台Vo
     *
     * @return 未通过校验的中台Vo
     * @author zhangcheng
     */
    private InvalidOmsMessageVo validMnsPriceVo(MnsPriceVo vo) {
        logger.debug("开始校验中台价格Vo中的参数: {}", vo);
        InvalidOmsMessageVo invalidOmsMessageVo = new InvalidOmsMessageVo();
        invalidOmsMessageVo.setType(InvalidOmsDataType.OMS_PRICE);
        List<String> messages = Lists.newArrayList();
        if (StringUtils.isBlank(vo.getMatnr())) {
            invalidOmsMessageVo.setValid(false);
            messages.add(InvalidOmsMessageType.PRICE_PRODUCT_CODE_EMPTY.getDescription());
        }
        if (StringUtils.isBlank(vo.getMaktx())) {
            invalidOmsMessageVo.setValid(false);
            messages.add(InvalidOmsMessageType.PRICE_PRODUCT_NAME_INVALID.getDescription());
        }
        if (StringUtils.isBlank(vo.getArea())) {
            invalidOmsMessageVo.setValid(false);
            messages.add(InvalidOmsMessageType.PRICE_AREA_INVALID.getDescription());
        }
        if (StringUtils.isBlank(vo.getPrice())) {
            invalidOmsMessageVo.setValid(false);
            messages.add(InvalidOmsMessageType.PRICE_RETAIL_PRICE_INVALID.getDescription());
        }
        if (StringUtils.isBlank(vo.getCostprice())) {
            invalidOmsMessageVo.setValid(false);
            messages.add(InvalidOmsMessageType.PRICE_COST_PRICE_INVALID.getDescription());
        }
        if (CollectionUtils.isEmpty(messages)) {
            invalidOmsMessageVo.setValid(true);
            return invalidOmsMessageVo;
        }
        //将未通过校验的Vo序列化为json字符串
        invalidOmsMessageVo.setContent(JSON.toJSONString(vo));
        invalidOmsMessageVo.setMessages(messages);
        if (logger.isDebugEnabled()) {
            logger.debug("未通过校验Vo序列化为: {}", vo);
        }
        return invalidOmsMessageVo;
    }

    /**
     * 转换中台增量价格
     * 1.首先调用私有方法validMnsPriceVo，用来校验中台增量价格Vo对象
     * 2.如果该中台增量价格Vo对象未通过校验，则将该对象发送到指定的队列
     * 3.如果该中台增量价格Vo对象通过了校验，则开始进行转换
     * 4.首先调用MnsPriceVo2PriceRo对象中的apply方法将该中台Vo
     * 转换为PriceRo信息
     * 5.再调用redis中的findPriceRo方法获取到本地的existedPriceRo信息
     * 6.如果本地的existedPriceRo为空的话，则打印日志记录新价格数据，并保存
     * 该新价格数据
     * 7.如果本地的existedPriceRo不为空并且existedPriceRo的更新时间
     * 不为空并且PriceRo的更新时间也不为空，同时existedPriceRo更新
     * 时间在PriceRo的更新时间之后，这四个条件条件同时满足的话，则通过该中台Vo
     * 转换的PriceRo为过期价格数据，不保存该价格数据
     * 8.价格保存redis成功后，创建商品增量索引Vo，并且将productCode和areaCode
     * 设置到该Vo中，然后将该Vo发送到指定的队列
     *
     * @author zhangcheng
     */
    @Override
    public void trans(MnsPriceVo mnsPriceVo) {
        logger.debug("开始转换中台价格数据: {}", mnsPriceVo);
        InvalidOmsMessageVo messageVo = this.validMnsPriceVo(mnsPriceVo);
        if (!messageVo.isValid()) {
            //如果MnsPriceVo没有通过校验，则发送到指定的队列里
            logger.debug("start sending message");
            this.sendInvalidVoToMessage(messageVo);
            logger.debug("Send message end");
        } else {
            PriceRo priceRo = new MnsPriceVo2PriceRo().apply(mnsPriceVo);
            PriceRo existedPriceRo = priceRedisDao.findPriceRo(priceRo.getProductCode(), priceRo.getAreaCode());
            if (existedPriceRo == null) {
                logger.info("新价格数据,中台商品编号为: {}", priceRo.getProductCode());
            }
            if (existedPriceRo != null && existedPriceRo.getUpdateTimestamp() != null &&
                    priceRo.getUpdateTimestamp() != null && existedPriceRo.getUpdateTimestamp().after(priceRo.getUpdateTimestamp())) {
                logger.warn("过期价格数据! 原价格更新时间: {} => 当前价格更新时间: {}", existedPriceRo.getUpdateTimestamp(), priceRo.getUpdateTimestamp());
                return;
            }
            priceRedisDao.save(priceRo);
            //价格保存redis成功后，触发更新商品增量索引
            ProductIdxIdentityVo productIdxIdentityVo = new ProductIdxIdentityVo();
            productIdxIdentityVo.setProductCode(priceRo.getProductCode());
            productIdxIdentityVo.setAreaNo(priceRo.getAreaCode());
            productIdxIdentityVo.setProductType(VendorTypeEnum.TYPE_B.getValue());
            //这里调用商品增量索引接口利用RabbitMQ队列进行交互
            messageService.sendMessage(BizBaseQueue.MQ_SEARCH_PRODUCT_QUEUE, SimpleBizMessage.newMessage(productIdxIdentityVo));
        }
    }

    @Override
    public void preHandleFullPrice(OssType type) {
        //TODO 此处暂时没有具体的逻辑处理，等待后期有需求后进行添加
    }

    @Override
    public void afterHandleFullPrice(OssType type) {
        //保存redis成功后触发全量商品索引
        logger.info("更新全量价格完成, 开始更新全量商品索引. 时间: {}", DateUtil.now());
        messageService.sendMessage(BizBaseQueue.MQ_SEARCH_PRODUCT_QUEUE, SimpleBizMessage.newMessage(new ProductIdxIdentityVo()));
        logger.info("更新全量价格完成, 更新全量商品索引完成. 时间: {}", DateUtil.now());
    }

    @Override
    public void saveFullPrice(List<MnsPriceVo> mnsPriceVos) {
        List<PriceRo> priceRos = Lists.newArrayList();
        for (MnsPriceVo vo : mnsPriceVos) {
            //校验中台全量价格中的参数是否合法
            InvalidOmsMessageVo invalidOmsMessageVo = this.validMnsPriceVo(vo);
            if (!invalidOmsMessageVo.isValid()) {
                //将未通过校验的中台Vo发送到指定的队列
                invalidOmsMessageVo.setType(InvalidOmsDataType.OMS_PRICE_ALL);
                logger.debug("start sending message");
                this.sendInvalidVoToMessage(invalidOmsMessageVo);
                logger.debug("Send message end");
            } else {
                PriceRo priceRo = new MnsPriceVo2PriceRo().apply(vo);
                priceRos.add(priceRo);
            }
        }
        this.savePriceRos(priceRos);
        logger.debug("批量重置全量价格完成. 总计: {}", mnsPriceVos.size());
    }

    /**
     * 批量保存价格
     *
     * @author zhangcheng
     */
    private void savePriceRos(List<PriceRo> ros) {
        try {
            priceRedisDao.save(ros);
        } catch (Exception e) {
            logger.error("写入价格到redis异常", e);
        }
    }
}
