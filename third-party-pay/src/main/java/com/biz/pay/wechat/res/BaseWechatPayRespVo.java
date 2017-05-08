package com.biz.pay.wechat.res;

import com.biz.pay.wechat.WeChatPayFactory;
import com.biz.pay.wechat.exceptions.SignIncorrectException;
import com.biz.pay.wechat.exceptions.WechatResponseParseException;
import com.biz.pay.wechat.lang.PropertyCollector;
import com.biz.pay.wechat.lang.ResultCode;
import com.biz.pay.wechat.lang.ReturnCode;
import com.biz.pay.wechat.lang.TradeType;
import org.apache.commons.lang3.ArrayUtils;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Objects;
import java.util.Properties;

import static com.biz.pay.wechat.lang.Keys.*;
import static java.lang.String.format;
import static org.codelogger.utils.StringUtils.isBlank;

public abstract class BaseWechatPayRespVo implements Serializable {

	private static final long serialVersionUID = 3470857600550538913L;
	protected Properties properties;

    protected String[] getIgnoreParams() {
        return null;
    }

    public BaseWechatPayRespVo(String responseXml)
        throws IllegalArgumentException, WechatResponseParseException {
        if (isBlank(responseXml)) {
            throw new IllegalArgumentException("Argument responseXml can not be blank.");
        }
        try {
            SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
            InputStream source = new ByteArrayInputStream(responseXml.getBytes("utf-8"));
            PropertyCollector pc = new PropertyCollector();
            saxParser.parse(source, pc);
            properties = pc.returnProperties();
        } catch (Exception e) {
            throw new WechatResponseParseException(e);
        }
    }

    public void signValidate() throws SignIncorrectException {
        if (ArrayUtils.isNotEmpty(getIgnoreParams())) {
            for (String ignoreParam : getIgnoreParams()) {
                properties.remove(ignoreParam);
            }
        }
        String sign = WeChatPayFactory.newInstance().newSigner(getAppId()).sign(properties);
        if (!Objects.equals(sign, getSign())) {
            throw new SignIncorrectException(
                format("expected:[%s], but get:[%s]", sign, getSign()));
        }
    }

    public Boolean isProcessSuccess() {
        return getReturnCode() == ReturnCode.SUCCESS && Objects
            .equals(getResultCode(), ResultCode.SUCCESS);
    }

    public Properties getProperties() {
        return properties;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public TradeType getTradeType() {

        return getEnumInstance(TradeType.class, properties.getProperty(TRADE_TYPE));
    }

    public ReturnCode getReturnCode() {

        return getEnumInstance(ReturnCode.class, properties.getProperty(RETURN_CODE));
    }

    public String getReturnMessage() {
        return properties.getProperty(RETURN_MSG);
    }

    public String getAppId() {
        return properties.getProperty(APPID);
    }

    public String getMchId() {
        return properties.getProperty(MCH_ID);
    }

    public String getNonceStr() {
        return properties.getProperty(NONCE_STR);
    }

    public String getSign() {
        return properties.getProperty(SIGN);
    }

    public String getTradeNo() {
        return getProperty(TRANSACTION_ID);
    }

    public String getOutTradeNo() {
        return getProperty(OUT_TRADE_NO);
    }

    public String getOpenId() {
        return getProperty(OPENID);
    }

    public ResultCode getResultCode() {

        return getEnumInstance(ResultCode.class, properties.getProperty(RESULT_CODE));
    }

    public String getErrorCode() {
        return properties.getProperty(ERR_CODE);
    }

    public String getErrorDescption() {
        return properties.getProperty(ERR_CODE_DES);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	protected <T extends Enum> T getEnumInstance(Class<T> ct, String name) {
        return isBlank(name) ? null : (T) Enum.valueOf(ct, name);
    }

}
