package com.biz.pay.alipay;

import com.biz.core.exceptions.SystemException;
import com.biz.core.util.DateUtil;
import com.biz.core.util.JsonUtil;
import com.biz.core.util.StringUtil;
import com.biz.core.util.Timers;
import com.biz.pay.alipay.IAlipayPayment.ParamKeys;
import com.biz.pay.alipay.config.AlipayConfig;
import com.biz.pay.alipay.sign.RSA;
import com.biz.pay.alipay.util.AlipayCore;
import com.biz.pay.alipay.util.AlipaySubmit;
import com.biz.pay.alipay.util.UtilDate;
import com.biz.pay.alipay.vo.RefundPayVo;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created @ClassName: AlipayFactory By @author jun.liu
 * 
 * @date 2016年7月12日 上午10:45:32
 */
public class AlipayFactory {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	private final static String invalidParamChars = "\\s*|\t|\r|\n";

	private Properties conf;

	private ImmutableMap<String, String> confMap;

	private final static String CONFIG_PATH = "payment/alipay.properties";

	private final static String CONF_KEY_NOTIFY_URL = "notify.url";
	
	private final static String CONF_KEY_RETURN_URL = "return.url";

	private final static String BATCH_TRANS_NOTIFY = "batch_trans_notify.url";

	private final static String CONF_NOPWD_REFUND_NOTIFY_URL = "notify.nopwd.refund.url";

	private final static String CONF_WAP_SHOW_URL = "alipay.wap.show.url";

	private final static String CONF_KEY_REFUND_NOTIFY_URL = "alipay.refund.notify.url";

	private final static String CONF_PAYMENT_SUCCESS_UI_URL = "payment.success.ui.url";

	private final static String CONF_SERVER_ALIPAY_URL = "server.alipay.url";

	private final static String CONF_CAS_WAIT_FOR_PAY_URL = "cas.wait.for.pay.url";

	private final static String PARTNER = "alipay.partner";

	private final static String ACCOUNT_NAME = "alipay.account.name";

	private final static String EMAIL_ACCOUNT = "alipay.email_account";

	private final static String PRIVATE_KEY = "alipay.private.key";

	private final static String PUBLIC_KEY = "alipay.public.key";

	private final static String SIGN_TYPE_KEY = "alipay.sign.type.key";

	private final static String SIGN_MD5_KEY = "alipay.sign.md5.key";

	private final static AlipayFactory alipayFactory = new AlipayFactory();

	private AlipayFactory() {
		try {
			logger.info("加载支付宝配置文件...");
			conf = new Properties();
			conf.load(AlipayFactory.class.getClassLoader().getResourceAsStream(CONFIG_PATH));
			initAlipayConfig();
		} catch (Throwable e) {
			throw new RuntimeException("加载alipay 配置文件失败", e);
		}
	}

	public static AlipayFactory newInstance() {
		return alipayFactory;
	}

	/**
	 * 初始化支付宝配置
	 */
	private void initAlipayConfig() {
		AlipayConfig.partner = conf.getProperty(PARTNER);
		AlipayConfig.account_name = conf.getProperty(ACCOUNT_NAME);
		AlipayConfig.private_key = conf.getProperty(PRIVATE_KEY);
		AlipayConfig.email_account = conf.getProperty(EMAIL_ACCOUNT);
		AlipayConfig.seller_id = AlipayConfig.partner;
		AlipayConfig.ali_public_key = conf.getProperty(PUBLIC_KEY);
		AlipayConfig.sign_key = conf.getProperty(SIGN_MD5_KEY);
		AlipayConfig.sign_type = conf.getProperty(SIGN_TYPE_KEY);
	}
	/**
	 * 获取带签名的手机端支付参数
	 * Created getSignedMobilePayRequestParams By @author jun.liu  
	 * @date 2016年7月12日 上午10:46:15
	 * @param @param orderPaymentId
	 * @param @param orderSubject
	 * @param @param payAmount
	 * @param @param payLimitTime
	 * @param @throws UnsupportedEncodingException
	 * @return String
	 */ 
	public String getSignedMobilePayRequestParams(String orderPaymentId, String orderSubject, int payAmount,
			Date payLimitTime) throws UnsupportedEncodingException {
		Timers timers = Timers.createAndBegin(false);
		if (logger.isDebugEnabled()) {
			logger.debug("orderPaymentId--> {}", orderPaymentId);
		}
		Map<String, String> sParaTmp = buildRequestParams(orderPaymentId, orderSubject, payAmount, payLimitTime);
		timers.record("step1");
		Map<String, String> sPara = AlipayCore.paraFilter(sParaTmp);
		timers.record("step2");
		String data = AlipayCore.createLinkString(sPara);
		timers.record("step3");
		if (logger.isDebugEnabled()) {
			logger.debug("content--> {}", data);
		}
		//将待签名字符串使用私钥签名。
		String rsa_sign=URLEncoder.encode(RSA.sign(data, AlipayConfig.private_key, AlipayConfig.input_charset), AlipayConfig.input_charset);
		timers.record("encode");
		if (logger.isDebugEnabled()) {
			logger.debug("rsa_sign--> {}", rsa_sign);
			timers.print("use time get-alisign");
		}
		sPara.put(ParamKeys.SIGN, rsa_sign);
		sPara.put(ParamKeys.SIGN_TYPE, AlipayConfig.sign_type);
		return mapToUrlParams(sPara);
	}

	protected Map<String, String> buildRequestParams(String orderPaymentId, String orderSubject, int payAmount,
			Date payLimitTime) {
		if (logger.isDebugEnabled()) {
			logger.debug("orderPaymentId--> {}", orderPaymentId);
			logger.debug("orderSubject--> {}", orderSubject);
			logger.debug("payAmount--> {}", payAmount);
			logger.debug("payLimitTime--> {}", payLimitTime);
		}
		// 支付类型
		String payment_type = "1";
		// 必填，不能修改
		// 服务器异步通知页面路径
		Timers timers = Timers.createAndBegin(logger.isDebugEnabled());
		String notify_url = conf.getProperty(CONF_KEY_NOTIFY_URL);
		timers.record("alipay-get-notify");
		// 页面跳转同步通知页面路径
		// String return_url = "http://www.depotnearby.com";

		// 商户订单号 必填*
		String out_trade_no = orderPaymentId;

		// 订单名称 必填*
		String subject = orderSubject;

		// 付款金额 必填*  将分装为元
		String total_fee = StringUtil.formatPriceToYuanWithFen(payAmount);
		
		// 把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		// "service=\"mobile.securitypay.pay\"",
		// "partner=\"2088101568338364\"",
		// "_input_charset=\"utf-8\"",
		// "notify_url=\"http://notify.msp.hk/notify.htm\"",
		// "out_trade_no=\"0819145412-6177\"",
		// "subject=\"《暗黑破坏神3:凯恩之书》\"",
		// "payment_type=\"1\"",
		// "seller_id=\"alipay-test01@alipay.com\"",
		// "total_fee=\"0.01\""
		timers.record("step2");
		sParaTemp.put(ParamKeys.SERVICE, IAlipayPayment.Service.MOBILE_SECURITY_PAY);
		sParaTemp.put(ParamKeys.PARTNER, AlipayConfig.partner);
		sParaTemp.put(ParamKeys._INPUT_CHARSET, AlipayConfig.input_charset);
		sParaTemp.put(ParamKeys.NOTIFY_URL, notify_url);
		sParaTemp.put(ParamKeys.OUT_TRADE_NO, out_trade_no);
		sParaTemp.put(ParamKeys.SUBJECT, subject);
		sParaTemp.put(ParamKeys.PAYMENT_TYPE, payment_type);
		sParaTemp.put(ParamKeys.SELLER_ID, AlipayConfig.seller_id);
		sParaTemp.put(ParamKeys.TOTAL_FEE, total_fee);
		timers.record("step3");
		timers.print("use time alisign");
		return sParaTemp;
	}
	public static String mapToUrlParams(Map<String, String> sPara) {
		if (sPara == null || sPara.size() == 0) {
			return null;
		}
		List<String> keys = new ArrayList<String>(sPara.keySet());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < keys.size(); i++) {
			String name = (String) keys.get(i);
			String value = (String) sPara.get(name);
			sb.append(name + "=" + value + "&");
		}
		sb.delete(sb.length() - 1, sb.length());
		return sb.toString();
	}

	public String getNotifyUrl() {
		return conf.getProperty(CONF_KEY_NOTIFY_URL);
	}

	public String getPaymentSuccessUrl() {
		return conf.getProperty(CONF_PAYMENT_SUCCESS_UI_URL);
	}

	public String getServerAlipayUrl() {
		return conf.getProperty(CONF_SERVER_ALIPAY_URL);
	}

	public String getNotifyUrl(String service) {
		if (StringUtils.isBlank(service)) {
			return null;
		}
		if (StringUtils.equals(IAlipayPayment.Service.REFUND_FASTPAY_BY_PLATFORM_NOPWD, service)) {
			return conf.getProperty(CONF_NOPWD_REFUND_NOTIFY_URL);
		}
		return conf.getProperty(CONF_KEY_NOTIFY_URL);
	}

	/**
	 * 即时到帐有密退款异步通知地址
	 * @return
	 */
	public String getConfPwdRefundNotifyUrl() {
		return conf.getProperty(CONF_KEY_REFUND_NOTIFY_URL);
	}
	/**
	 * 创建wap支付表单
	 * Created buildWapPayForm By @author jun.liu  
	 * @date 2016年7月12日 上午10:46:49
	 * @param @param orderId
	 * @param @param orderSubject
	 * @param @param description
	 * @param @param payAmount
	 * @param @param payLimitTime
	 * @param @throws SystemException
	 * @return String
	 */ 
	public String buildWapPayForm(String orderPaymentId, String orderSubject, String description, int payAmount, Date payLimitTime) throws SystemException {
		return buildRequest(orderPaymentId, orderSubject, description, payAmount, payLimitTime, IAlipayPayment.Service.WAP_DIRECT_PAY);
	}

	/**
	 * 创建及时到账支付表单（网页版支付）
	 * @param orderPaymentId 商户订单id
	 * @param orderSubject  订单名称
	 * @param description 订单描述
	 * @param payAmount 支付金额
	 * @param payLimitTime 订单关闭时间
	 * @return
	 * @throws SystemException
	 */
	public String buildDirectPayForm(String orderPaymentId, String orderSubject, String description, int payAmount, Date payLimitTime) throws SystemException {
		return buildRequest(orderPaymentId, orderSubject, description, payAmount, payLimitTime, IAlipayPayment.Service.CREATE_DIRECT_PAY_BY_USER);
	}

	private String buildRequest(String orderPaymentId, String orderSubject, String description, int payAmount, Date payLimitTime, String service) {
		// 支付类型
		String payment_type = "1";
		// 必填，不能修改
		String notify_url = AlipayFactory.newInstance().getNotifyUrl();

		// 页面跳转同步通知页面路径
		String return_url = conf.getProperty(CONF_KEY_RETURN_URL);
		// 需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/

		// 商户订单号
		String out_trade_no = orderPaymentId;
		// 商户网站订单系统中唯一订单号，必填

		// 订单名称 //必填
		String subject = filterInvalidChars(orderSubject);

		// 付款金额
		String total_fee = StringUtil.formatPriceToYuanWithFen(payAmount);

		// 商品展示地址(支付完成重定向地址)
		String show_url = conf.getProperty(CONF_WAP_SHOW_URL);

		// 订单描述 选填
		String body = filterInvalidChars(description);
		// 超时时间 选填
		// 取值范围： 1m～15d。m-分钟， h-小时， d-天， 1c-当天（无论交易何时创建，都在 0点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为 90m。
		String it_b_pay = payLimitTime == null ? "" : DateUtil.getMinuteItBPay(payLimitTime.getTime());

		// 钱包token 选填
		// String extern_token = null;
		// 把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", service);
		sParaTemp.put("partner", AlipayConfig.partner);
		sParaTemp.put("seller_id", AlipayConfig.seller_id);
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", payment_type);
		sParaTemp.put("notify_url", notify_url);
		sParaTemp.put("return_url", return_url);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
		sParaTemp.put("total_fee", total_fee);
		sParaTemp.put("show_url", show_url);
		sParaTemp.put("body", body);
		sParaTemp.put("it_b_pay", it_b_pay);
//		 sParaTemp.put("extern_token", extern_token);

		// 创建请求html
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "POST", "确认");
		// System.out.println("html:\r\n" + sHtmlText);
		return sHtmlText;
	}

	/**
	 * 创建批量转账支付表单
	 * Created buildBatchTransForm By @author jun.liu  
	 * @date 2016年7月12日 上午10:47:03
	 * @param @param ali_batch_no
	 * @param @param ali_batch_fee
	 * @param @param ali_batch_num
	 * @param @param ali_detail_data
	 * @param @param payDate
	 * @param @throws SystemException
	 * @return String
	 */ 
	public String buildBatchTransForm(String ali_batch_no, String ali_batch_fee, String ali_batch_num,
			String ali_detail_data, Date payDate) throws SystemException {
		//////////////////////////////////// 请求参数//////////////////////////////////////

		// 服务器异步通知页面路径
		String notify_url = conf.getProperty(BATCH_TRANS_NOTIFY);
		// 需http://格式的完整路径，不允许加?id=123这类自定义参数

		// 付款账号
		String email = AlipayConfig.email_account;
		// 必填

		// 付款账户名
		String account_name = AlipayConfig.account_name;
		// 必填，个人支付宝账号是真实姓名公司支付宝账号是公司名称

		// 付款当天日期
		String pay_date = UtilDate.getDate(payDate);
		// 必填，格式：年[4位]月[2位]日[2位]，如：20100801

		// 批次号
		String batch_no = ali_batch_no;
		// 必填，格式：当天日期[8位]+序列号[3至16位]，如：201008010000001

		// 付款总金额
		String batch_fee = ali_batch_fee;
		// 必填，即参数detail_data的值中所有金额的总和

		// 付款笔数
		String batch_num = ali_batch_num;
		// 必填，即参数detail_data的值中，“|”字符出现的数量加1，最大支持1000笔（即“|”字符出现的数量999个）

		// 付款详细数据
		String detail_data = ali_detail_data;
		// 必填，格式：流水号1^收款方帐号1^真实姓名^付款金额1^备注说明1|流水号2^收款方帐号2^真实姓名^付款金额2^备注说明2....

		//////////////////////////////////////////////////////////////////////////////////
		// 把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("search", "batch_trans_notify");
		sParaTemp.put("partner", AlipayConfig.partner);
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("notify_url", notify_url);
		sParaTemp.put("email", email);
		sParaTemp.put("account_name", account_name);
		sParaTemp.put("pay_date", pay_date);
		sParaTemp.put("batch_no", batch_no);
		sParaTemp.put("batch_fee", batch_fee);
		sParaTemp.put("batch_num", batch_num);
		sParaTemp.put("detail_data", detail_data);
		// 创建请求html
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "POST", "确认");
		// System.out.println("html:\r\n" + sHtmlText);
		return sHtmlText;
	}
	/**
	 * 创建无密退款单
	 * @author yikun.mao
	 * @date 2016年10月13日
	 * @param batch_nos
	 * @param refund_dates
	 * @param batch_nums
	 * @param detail_datas
	 * @param returnId 退单id
	 * @return
	 * @throws Exception
	 */
	public String buildRefundFastpayBynoPwdForm(String batch_nos, String refund_dates, String batch_nums, String detail_datas, String returnId) throws Exception {
		//////////////////////////////////// 请求参数//////////////////////////////////////

		// 服务器异步通知页面路径
		String notify_url = conf.getProperty(CONF_NOPWD_REFUND_NOTIFY_URL);
		logger.info("notify_url===>{}", notify_url);
		// 需http://格式的完整路径，不允许加?id=123这类自定义参数

		// 退款批次号
		String batch_no = batch_nos;
		// 必填，每进行一次即时到账批量退款，都需要提供一个批次号，必须保证唯一性

		// 退款请求时间
		String refund_date = refund_dates;
		// 必填，格式为：yyyy-MM-dd hh:mm:ss

		// 退款总笔数
		String batch_num = batch_nums;
		// 必填，即参数detail_data的值中，“#”字符出现的数量加1，最大支持1000笔（即“#”字符出现的最大数量999个）

		// 单笔数据集
		String detail_data = detail_datas;
		// 必填，格式详见“4.3 单笔数据集参数说明”

		//////////////////////////////////////////////////////////////////////////////////

		// 把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "refund_fastpay_by_platform_nopwd");
		sParaTemp.put("partner", AlipayConfig.partner);
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("notify_url", notify_url);
		sParaTemp.put("batch_no", batch_no);
		sParaTemp.put("refund_date", refund_date);
		sParaTemp.put("batch_num", batch_num);
		sParaTemp.put("detail_data", detail_data);

		// 建立请求
		String sHtmlText = AlipaySubmit.buildRequest("", "", sParaTemp);
		//<alipay>
		// System.out.println("html:\r\n" + sHtmlText);
		return sHtmlText;
	}

	/**
	 * 创建有密即时到帐退款单
	 * @param refundPayVo
	 * @return
	 */
	public Map<String, String> buildRefundFastpayByPlatformPwd(RefundPayVo refundPayVo) throws Exception {
		refundPayVo.setNotifyUrl(getConfPwdRefundNotifyUrl());
		refundPayVo.setPartner(AlipayConfig.partner);
		refundPayVo.setSellerId(AlipayConfig.partner);
		return AlipaySubmit.buildRequestPara(JsonUtil.json2Obj(JsonUtil.obj2Json(refundPayVo), HashMap.class));
	}
	/**
	 * 过滤非法字符串
	 * Created filterInvalidChars By @author jun.liu  
	 * @date 2016年7月12日 上午10:47:16
	 * @param @param str
	 * @return String
	 */ 
	protected String filterInvalidChars(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		String content = new String(str);
		Pattern p = Pattern.compile(invalidParamChars);
		Matcher m = p.matcher(content);
		String result = m.replaceAll("");
		return result;
	}

	/**
	 * 用户中心待支付URL
	 * @return
	 */
	public String getConfCasWaitForPayUrl() {
		return conf.getProperty(CONF_CAS_WAIT_FOR_PAY_URL);
	}
}
