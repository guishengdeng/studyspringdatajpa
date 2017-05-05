package com.biz.pay.chinapay;

import com.biz.core.util.JsonUtil;
import com.biz.pay.chinapay.sdk.AcpService;
import com.biz.pay.chinapay.sdk.SDKConfig;
import com.biz.pay.chinapay.vo.AbstractChinaPayReqVo;
import com.biz.pay.chinapay.vo.ChinaPayRefundReqVo;
import com.biz.pay.chinapay.vo.ChinaPayReqVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static java.lang.String.format;

/**
 * Created by tanghaibin on 2017/2/8.
 */
public class ChinaPayFactory {

    private static final Logger logger = LoggerFactory.getLogger(ChinaPayFactory.class);

    private static final String CONFIG_PATH = "payment/chinapay_config.properties";

    private static final String ENCODING = "chinapay.encoding";

    private static final String VERSION = "chinapay.version";

    private static final String MERID = "chinapay.merId";

    private static final String FRONTURL = "chinapay.frontUrl";

    private static final String BACKURL =  "chinapay.backUrl";

    private static final String SERVER_CHINAPAY_URL = "server.chinapay.url";

    private static final String PAYMENT_SUCCESS_UI_URL = "payment.success.ui.url";

    private final static String CONF_CAS_WAIT_FOR_PAY_URL = "cas.wait.for.pay.url";

    private static Properties conf;

    private static class ChinapayConfigHolder {

        static ChinaPayFactory chinapayConfig = new ChinaPayFactory();
    }

    public static ChinaPayFactory getInstance() {
        return ChinapayConfigHolder.chinapayConfig;
    }

    private ChinaPayFactory() {
        conf = new Properties();
        try {
            conf.load(ChinaPayFactory.class.getClassLoader().getResourceAsStream(CONFIG_PATH));
        } catch (Exception ex) {
            throw new RuntimeException(format("Failed to load conf resource [%s]", CONFIG_PATH), ex);
        }
    }

    /**
     * 构建银联支付表单HTML
     * @param chinaPayReqVo
     * @return
     */
    public String buildChinaPayHtml(ChinaPayReqVo chinaPayReqVo) {
        if(chinaPayReqVo == null) {
            return "";
        }
        setChinaPayVoValue(chinaPayReqVo);
        chinaPayReqVo.setFrontUrl(getFrontUrl());
        return AcpService.createAutoFormHtml(SDKConfig.getInstance().getFrontRequestUrl(),
                AcpService.sign(JsonUtil.json2Obj(JsonUtil.obj2Json(chinaPayReqVo), HashMap.class), getEncoding()),
                getEncoding());
    }

    public String getPaymentSuccessUiUrl() {
        return conf.getProperty(PAYMENT_SUCCESS_UI_URL);
    }

    /**
     * 构建银联退款
     * @param refundReqVo
     * @return
     */
    public Map<String, String> buildChinaPayRefund(ChinaPayRefundReqVo refundReqVo) {
        if(refundReqVo == null) {
            return null;
        }
        setChinaPayVoValue(refundReqVo);
        Map<String, String> result = AcpService.sign(JsonUtil.json2Obj(JsonUtil.obj2Json(refundReqVo), HashMap.class), refundReqVo.getEncoding());
        return AcpService.post(result, SDKConfig.getInstance().getBackRequestUrl(), refundReqVo.getEncoding());
    }

    public void setChinaPayVoValue(AbstractChinaPayReqVo reqVo) {
        reqVo.setVersion(getVersion());
        reqVo.setBackUrl(getBackUrl());
        reqVo.setMerId(getMerId());
    }

   public String getEncoding() {
        return conf.getProperty(ENCODING);
   }

   protected String getVersion() {
        return conf.getProperty(VERSION);
   }

   private String getFrontUrl() {
        return conf.getProperty(FRONTURL);
   }

   private String getBackUrl() {
        return conf.getProperty(BACKURL);
   }

   private String getMerId() {
        return conf.getProperty(MERID);
   }

    public String getServerChinapayUrl() {
        return conf.getProperty(SERVER_CHINAPAY_URL);
    }

    /**
     * 用户中心待支付URL
     * @return
     */
    public String getConfCasWaitForPayUrl() {
        return conf.getProperty(CONF_CAS_WAIT_FOR_PAY_URL);
    }
}
