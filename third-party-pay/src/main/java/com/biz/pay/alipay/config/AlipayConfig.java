package com.biz.pay.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */
public class AlipayConfig {
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
    // 合作身份者ID，以2088开头由16位纯数字组成的字符串
    public static String partner = "2088221419667143";
    								
    // 收款支付宝账号，以2088开头由16位纯数字组成的字符串
    public static String seller_id = partner;
    //账户名
    public static String account_name = "成都隔壁仓库科技有限公司";
    //账户
    public static String email_account = "defei.deng@biz-united.com.cn";;
    // 商户的私钥
    public static String private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKXyhrbqo1mr44WtniCccWyN" +
            "/tX918D0lxizpe0Zxd7zHxz7mqPli2OTkn+rW+C2kNy+N+8J+/qpWBzPcO9+On1jWnU3FHmZTnJP1O2" +
            "/PVzn2OS1yWk2rLnQGE9lb1GUUBrxCQKds/JBQvABOPGArWA5h6cRcQ9" +
            "+LB5x7q1z0ev7AgMBAAECgYAqk9pqrjMxO8AJuW0b8ufLMXeUEig8UsU9ykIlXnp9ZKWz+v5HVQr5hpCM" +
            "/zt4oX7zlD6nr0kenDqGUdcpK5r3zSsjJ0dfdr0oH5fhxDNUyQQ11hpyy0PzMXb6iUQchfXckzw/XLB" +
            "/Rr1CoMec3FIJCZ3gPD9XUKN0CaThUZSPqQJBANOjX4w5G6mHiuzLZUBO+EtWnEC+nWQ7XqAdwDx58z0GTG8pe0tq9hSQsfU4mw6B" +
            "/Qd8p4aC6CA4ywIe5KObN3UCQQDIu1uaOUaM0s+/PJsdOAi9zhKmJu7AXCgePVV4ez2jPMv6g8q4pD3CFgxx4UkA9jAH" +
            "/oQxIJ1L2iJhxqKT3ZevAkA8MatrZpLHlRWeY/tYXx2fz/KBN7XsXDZ9s/iJOuZD5Ra4R0QaH0H" +
            "+d2kmJgN0DOS6zGmZiIIdKAoVgFPndK5BAkAgCN6Ol1NuB/rFn0t190pV2DxbrdzucAarBMjOKITmtz4qZbwFiZidj90" +
            "+k2COObvimrjICldwEojoJ8YsaPHbAkEAk8zAh7BdpizSpUn6/L4sGcCD2CUtdkcPQw6CSLknpZZMHPz" +
            "+uRRE0k8d8c5WSr2cXYV6PwZcsORt/10uiG3bhQ==";
    // 支付宝的公钥，无需修改该值
    public static String ali_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

    // 调试用，创建TXT日志文件夹路径
    public static String log_path = "/home/pay_log/";

    // 字符编码格式 目前支持 gbk 或 utf-8
    public static String input_charset = "utf-8";

    // 签名方式 不需修改
    public static String sign_type = "RSA";
    //MD5签名约定KEY
    public static String sign_key;

}
