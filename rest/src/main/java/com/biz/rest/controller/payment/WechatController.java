package com.biz.rest.controller.payment;

import com.biz.gbck.mo.WechatNotifyMessage;
import com.biz.message.MessageService;
import com.biz.pay.wechat.lang.Keys;
import com.biz.pay.wechat.lang.ReturnCode;
import com.biz.pay.wechat.lang.XmlBuilder;
import com.biz.pay.wechat.res.WechatPayNotifyRespVo;
import com.biz.rest.controller.BaseRestController;
import com.biz.soa.order.service.payment.PaymentService;
import org.apache.commons.collections.KeyValue;
import org.apache.commons.collections.keyvalue.DefaultKeyValue;
import org.apache.http.entity.ContentType;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * 支付相关 微信支付通知
 *
 * @author lei
 * @date 2017年05月8日
 * @reviewer
 * @see
 */
@RestController
@RequestMapping("/wechatPay")
public class WechatController extends BaseRestController {

    public static final String UTF_8 = "utf-8";

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private MessageService messageService;



    @RequestMapping(value = "notify")
    public void wechatNotify(HttpServletRequest request, HttpServletResponse response) {
        try {
            logger.info("wechatNotify in");
            int contentLength = request.getContentLength();
            byte[] requestBody = new byte[contentLength];
            IOUtils.readFully(request.getInputStream(), requestBody);
            String responseXml = new String(requestBody, UTF_8);

            WechatNotifyMessage message = this.getWechatNotifyMessage(responseXml);
            ReturnCode returnCode = ReturnCode.FAIL;
            String msg = ReturnCode.FAIL.toString();
            try {
                //		        messageService.sendMessage(PaymentRollBackQueue.WECHATNOTITY_QUEUE, SimpleBizMessage.newMessage(message));
                WechatPayNotifyRespVo notifyRes = new WechatPayNotifyRespVo(message.getXmlBody(), "xml");
                //开始处理逻辑
                Long paymentId = Long.valueOf(notifyRes.getOutTradeNo());
                if (paymentService.queryWechatPaid(notifyRes.getTransactionId(), paymentId,notifyRes.getAppId()).isPaid()) {
                    this.paymentService.wechatTradeNotify(notifyRes);
                    returnCode = ReturnCode.SUCCESS;
                    msg = ReturnCode.SUCCESS.toString();
                }
            } catch (Exception e) {
                logger.info("get an exception when process wechat notify.", e);
                msg = e.getMessage();
            }

            List<KeyValue> data = newArrayList();
            data.add(new DefaultKeyValue(Keys.RETURN_CODE, returnCode));
            data.add(new DefaultKeyValue(Keys.RETURN_CODE, msg));
            String buildXmlBody = new XmlBuilder(data).buildXmlBody();

            if (logger.isDebugEnabled()) {
                logger.debug("response data:'{}' to wechat.", buildXmlBody);
            }
            response.setContentType(ContentType.TEXT_XML.getMimeType());
            response.setCharacterEncoding(UTF_8);
            response.getWriter().write(buildXmlBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private WechatNotifyMessage getWechatNotifyMessage(String notifyXml) throws Exception {
        WechatPayNotifyRespVo unifiedOrderResponse = new WechatPayNotifyRespVo(notifyXml, "xml");
        logger.debug("response data:'{}' to wechat.", unifiedOrderResponse.getProperties());
        return new WechatNotifyMessage(notifyXml);
    }




}
