package com.biz.pay.wechat;

import com.biz.pay.wechat.lang.CertificateLoader;
import com.biz.pay.wechat.lang.CheckName;
import com.biz.pay.wechat.lang.Signer;
import com.biz.pay.wechat.lang.TradeType;
import com.biz.pay.wechat.req.*;
import org.codelogger.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

import static com.biz.pay.wechat.lang.Keys.*;
import static org.codelogger.utils.StringUtils.isBlank;

/**
 * Created @ClassName: WeChatPayFactory By @author jun.liu 
 * @date 2016年7月12日 上午10:54:19
 */ 
public class WeChatPayFactory {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private static final int NONCE_STRING_LENGTH = 16;
    public static final String CONFIG_PATH = "/payment/wechatpay.properties";
    public static final String UTF_8 = "utf-8";

    private static Properties conf;

    private static class WeChatPayFactoryHolder {
        static WeChatPayFactory instance = new WeChatPayFactory();
    }
    
//	static{
//		conf = new Properties();
//	    try {
////	        if (logger.isDebugEnabled()) {
////	            logger.debug("load wechat payment configurations.");
////	        }
//	        conf.load(new InputStreamReader(WeChatPayFactory.class.getResourceAsStream(CONFIG_PATH),
//	            UTF_8));
//	    } catch (Exception ex) {
//	        throw new MissingResourceException(
//	            format("Failed to load conf resource [%s]", CONFIG_PATH), ex);
//	    }
//	}

    public static void setConf(Properties conf) {
        WeChatPayFactory.conf =  conf;
    }

    private WeChatPayFactory() {
        
    }


    public static WeChatPayFactory newInstance() {
        return WeChatPayFactoryHolder.instance;
    }

    public Signer newSigner(String appId) {
        return new Signer(conf, getPropertyByAppId(KEY, appId));
    }
    

    /**
     * @param appId      支付的的appId
     * @param outTradeNo 商户订单号
     * @param body       商品描述
     * @param totalFee   总金额, 金额单位为【分】
     * @param clientIp   终端IP
     * @param tradeType  APP和网页支付提交用户端ip
     * @return {@link UnifiedOrder}
     */
    public UnifiedOrder newUnifiedOrder(String appId, String outTradeNo, String body,
        Integer totalFee, String clientIp, TradeType tradeType, String timeExpire, String openid) {
    	UnifiedOrder unifiedOrder = new UnifiedOrder(outTradeNo, body, totalFee, clientIp, tradeType, timeExpire, openid);
    	appId = getFixedAppId(appId);
    	setConfigurations(appId, unifiedOrder);
        unifiedOrder.setProperty(ATTACH, appId);
        unifiedOrder.setProperty(NOTIFY_URL, getPropertyByAppId(NOTIFY_URL, appId));
        return unifiedOrder;
    }
    
    /**
     * 新建一个公众号版微信统一订单
     * @author bruce.qin
     * @date 2016年9月30日
     * @param outTradeNo 商户订单号
     * @param body       商品描述
     * @param totalFee   总金额, 金额单位为【分】
     * @param clientIp   终端IP
     * @param timeExpire 过期时间格式化yyyyMMddHHmmss
     * @param openid     用户对父公众号的唯一标识
     * @param subOpenid  用户对子公众号的唯一标识（服务商版本），openid、subOpenid不能同时为空
     * @return
     */
    public UnifiedOrder newMPUnifiedOrder(String appId, String outTradeNo, String body,Integer totalFee, 
    		String clientIp ,String timeExpire, String openid ,String subOpenid){
    	UnifiedOrder unifiedOrder = new UnifiedOrder(outTradeNo, body, totalFee, clientIp == null ? conf.getProperty(SPBILL_CREATE_IP) : clientIp, timeExpire, openid, subOpenid);
    	appId = getFixedAppId(appId);
    	setConfigurations(appId, unifiedOrder);
    	unifiedOrder.setProperty(ATTACH, getFixedAppId(appId));
        unifiedOrder.setProperty(NOTIFY_URL, getPropertyByAppId(NOTIFY_URL, appId));
    	return unifiedOrder;
    }

    /**
     * 微信的订单号[transactionId]，优先使用.<br/>
     * 当没提供 微信的订单号[transactionId] 时需要传 商户系统内部的订单号[outTradeNo]。<br/>
     * 可以二选一,不能同时为空.
     *
     * @param transactionId 微信订单号
     * @param outTradeNo    商户订单号
     * @return {@link OrderQuery}
     */
    public OrderQuery newOrderQuery(String appId, String transactionId, String outTradeNo) {
        OrderQuery orderQuery = new OrderQuery(transactionId, outTradeNo);
        setConfigurations(appId, orderQuery);
        return orderQuery;
    }

    /**
     * @param outTradeNo 商户订单号
     * @return {@link OrderRefund}
     */
    public CloseOrder newCloseOrder(String appId, String outTradeNo) {
        CloseOrder closeOrder = new CloseOrder(outTradeNo);
        setConfigurations(appId, closeOrder);
        return closeOrder;
    }

    /**
     * 微信的订单号[transactionId]，优先使用.<br/>
     * 当没提供 微信的订单号[transactionId] 时需要传 商户系统内部的订单号[outTradeNo]。<br/>
     * 可以二选一,不能同时为空.
     *
     * @param transactionId 微信的订单号
     * @param outTradeNo    商户系统内部的订单号
     * @param outRefundNo   商户退款单号
     * @param totalFee      总金额
     * @param refundFee     退款金额
     * @return {@link OrderRefund}
     */
    public OrderRefund newOrderRefund(String appId, String transactionId, String outTradeNo,
        String outRefundNo, Integer totalFee, Integer refundFee) {
        OrderRefund refundOrder = new OrderRefund(transactionId, outTradeNo, outRefundNo, totalFee, refundFee);
        appId = getFixedAppId(appId);
        refundOrder.setSslContext(new CertificateLoader(getPropertyByAppId(LOAD_IDENTIFICATION, appId), getPropertyByAppId(MCH_ID, appId)).getSSLContext());
        setConfigurations(appId, refundOrder);
        refundOrder.setProperty(OP_USER_ID, getPropertyByAppId(MCH_ID, appId));
        return refundOrder;
    }

    /**
     * refundId、outRefundNo 两个参数必填一个，如果同时存在优先级为 refundId > outRefundNo
     *
     * @param refundId    微信退款单号
     * @param outRefundNo 商户退款单号
     * @return {@link RefundQuery}
     */
    public RefundQuery newRefundQuery(String appId, String refundId, String outRefundNo) {
        RefundQuery refundQuery = new RefundQuery(refundId, outRefundNo);
        setConfigurations(appId, refundQuery);
        return refundQuery;
    }

    /**
     * 微信转账
     *
     * @param partnerTradeNo 商户的转账订单号。必填
     * @param openid         微信用户openid。必填
     * @param amount         付款金额，单位为分。必填
     * @param desc           付款操作说明信息。必填
     * @param checkName      校验用户姓名选项。必填
     * @param receiverName   收款用户姓名。如果check_name设置为FORCE_CHECK或OPTION_CHECK，则必填用户真实姓名。
     * @return {@link Transfer}
     */
    public Transfer newTransfer(String appId, String partnerTradeNo, String openid, Integer amount,
        String desc, CheckName checkName, String receiverName) {
        Transfer transfer =
            new Transfer(partnerTradeNo, openid, amount, desc, checkName, receiverName);
        transfer.setSslContext(new CertificateLoader(getPropertyByAppId(LOAD_IDENTIFICATION, getFixedAppId(appId)),
            getPropertyByAppId(MCH_ID, getFixedAppId(appId))).getSSLContext());
        setConfigurations(getFixedAppId(appId), transfer);
        transfer.setProperty(MCH_APPID, getFixedAppId(appId));
        transfer.setProperty(MCHID, getPropertyByAppId(MCH_ID, getFixedAppId(appId)));
        transfer.setProperty(SPBILL_CREATE_IP, conf.getProperty(SPBILL_CREATE_IP));
        return transfer;
    }

    /**
     * 微信转账查询
     *
     * @param partnerTradeNo 商户转账单号
     * @return {@link TransferQuery}
     */
    public TransferQuery newTransferQuery(String appId, String partnerTradeNo) {
        TransferQuery refundQuery = new TransferQuery(partnerTradeNo);
        setConfigurations(getFixedAppId(appId), refundQuery);
        refundQuery.setSslContext(
            new CertificateLoader(getPropertyByAppId(LOAD_IDENTIFICATION, getFixedAppId(appId)),
                getPropertyByAppId(MCH_ID, getFixedAppId(appId))).getSSLContext());
        return refundQuery;
    }

    @SuppressWarnings("rawtypes")
	private void setConfigurations(String appId, WechatPayRequestBase wechatPayRequestBase) {
        wechatPayRequestBase.setProperties(conf);
        wechatPayRequestBase.setProperty(APPID, getFixedAppId(appId));
        wechatPayRequestBase.setProperty(MCH_ID, getPropertyByAppId(MCH_ID, appId));
        wechatPayRequestBase.setProperty(KEY, getPropertyByAppId(KEY, appId));
        String subAppid = getPropertyByAppId(SUB_APPID, appId);
        if(StringUtils.isNotBlank(subAppid))
        	wechatPayRequestBase.setProperty(SUB_APPID, subAppid);
        String subMchid = getPropertyByAppId(SUB_MCH_ID, appId);
        if(StringUtils.isNotBlank(subMchid))
        	wechatPayRequestBase.setProperty(SUB_MCH_ID, subMchid);
        wechatPayRequestBase
            .setProperty(NONCE_STR, StringUtils.getRandomPasswordString(NONCE_STRING_LENGTH));
    }

    private String getFixedAppId(String appId) {
        return isBlank(appId) ? conf.getProperty(DEFAULT_APP_ID) : appId;
    }

    private String getPropertyByAppId(String propertyKey, String appId) {
    	if (logger.isDebugEnabled()) {
            logger.debug("propertyKey --> {}", propertyKey);
            logger.debug("appId --> {}", appId);
        }
        String fixedAppId = getFixedAppId(appId);
        if (logger.isDebugEnabled()) {
            logger.debug("fixedAppId --> {}", fixedAppId);
        }
        return conf.getProperty(propertyKey + "." + fixedAppId);
    }

    /**
     * 获取当前开放平台appid
     * @author bruce.qin
     * @date 2016年10月23日
     * @return
     */
    public static String getCurrentOpenAppid(){
    	return conf.getProperty(CURRENT_OPEN_APPID);
    }
    
    /**
     * 获取当前微信公众号appid
     * @author bruce.qin
     * @date 2016年9月30日
     * @return
     */
    public static String getCurrentMPAppid(){
    	return conf.getProperty(CURRENT_MP_APPID);
    }
    /**
     * 获取默认微信公众号appid
     * @author bruce.qin
     * @date 2016年9月30日
     * @return
     */
    public static String getDefaultAppid(){
    	return conf.getProperty(DEFAULT_APP_ID);
    }
    /**
     * 获取当前微信公众号app secret
     * @author bruce.qin
     * @date 2016年9月30日
     * @return
     */
    public static String getCurrentMPAppSecret(){
    	return conf.getProperty(CURRENT_MP_APP_SECRET);
    }
    /**
     * 判断当前微信公众号appId和支付appId
     * @author zhangning
     * @date 2016年10月09日
     * @return
     */
    public Boolean judgeCurrentAndDefaultAppId(){
    	String currentMpAppId = conf.getProperty(CURRENT_MP_APPID);
    	String defaultAppId = conf.getProperty(DEFAULT_APP_ID);
    	if(currentMpAppId.equals(defaultAppId)){
    		return true;
    	}else{
    		return false;
    	}
    }
}
