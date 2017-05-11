package com.biz.rest.controller.payment;

import com.biz.core.util.JsonUtil;
import com.biz.gbck.mo.AlipayNotifyMessage;
import com.biz.message.MessageService;
import com.biz.pay.alipay.IAlipayPayment;
import com.biz.pay.alipay.util.AlipayNotify;
import com.biz.rest.controller.BaseRestController;
import com.biz.soa.order.service.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 支付相关 controller
 *
 * @author lei
 * @date 2017年04月25日
 * @reviewer
 * @see
 */
@RestController
@RequestMapping("/notify")
public class AlipayController extends BaseRestController {

    private static final String SUCCESS = "success";

    private static final String FAIL = "fail";

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private MessageService messageService;


    /**
     * 支付回调通知
     *
     * @author jun.liu(by xiaoyu)
     * @date 2016年9月2日
     * @param request
     * @param response
     */
    @RequestMapping("/notify")
    public void notify(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        AlipayNotifyMessage message = getParameters(request);
        String msg = FAIL;
        try {
            // 验证签名
            if (AlipayNotify.verify(message.getParam())) {
                logger.info("received an alipay notify, parameters:{}", JsonUtil.obj2Json(message));
                //				messageService.sendMessage(PaymentRollBackQueue.ALINOTITY_QUEUE, SimpleBizMessage.newMessage(message));
                HashMap<String, String> map = message.getParam();
                if (logger.isDebugEnabled()) {
                    logger.debug("received alipay notify, parameters\r\n:{}", JsonUtil.obj2Json(message));
                }
                String notifyType = map.get(IAlipayPayment.ParamKeys.NOTIFY_TYPE);
                // 支付交易通知
                if (IAlipayPayment.NotifyType.TRADE_STATUS_SYNC.equals(notifyType)) {
                    // 处理支付通知
                    paymentService.aliNotify(map);
                    msg = SUCCESS;
                } else {
                    if (logger.isWarnEnabled()) {
                        logger.warn("unsupport notify message.parameters:{}", JsonUtil.obj2Json(message));
                    }
                }
            } else {
                logger.warn("received an illegal alipay notify:{}", JsonUtil.obj2Json(message));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("send alipay notify message fail", e);
        } finally {
            try {
                response.getWriter().write(msg);
                response.getWriter().close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    @SuppressWarnings("rawtypes")
    private AlipayNotifyMessage getParameters(HttpServletRequest request) {
        AlipayNotifyMessage params = new AlipayNotifyMessage();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
            params.put(name, valueStr);
        }
        return params;
    }



}
