package com.biz.core.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.net.ssl.*;
import org.apache.commons.io.IOUtils;
import org.codelogger.utils.StringUtils;

public class HttpUtil {
    /**
     * 执行一次http或https请求，并获取返回数据
     *
     * @param uri url请求地址
     * @param method 请求方式GET or POST
     * @param requestBody 发送数据
     * @return 返回数据
     */
    public static byte[] executeRequest(String uri, String method, byte[] requestBody) throws IOException {
        if (StringUtils.isBlank(uri)) {
            return null;
        }
        Boolean isHttpsRequest = uri.contains("https://");
        if (isHttpsRequest) {
            return executeHttpsRequestForTrust(uri, method, requestBody);
        } else {
            return executeHttpRequest(uri, method, requestBody);
        }
    }

    /**
     * 执行一次http请求，并获取返回数据
     *
     * @param uri url请求地址
     * @param method 请求方式GET or POST
     * @param requestBody 发送数据
     * @return 返回数据
     */
    public static byte[] executeHttpRequest(String uri, String method, byte[] requestBody) {
        URL url;
        InputStream input = null;
        DataOutputStream out = null;
        ByteArrayOutputStream outStream = null;
        try {
            url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("GET".equalsIgnoreCase(method) ? "GET" : "POST");
            conn.setInstanceFollowRedirects(true);
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            if (requestBody != null) {
                out = new DataOutputStream(conn.getOutputStream());
                out.write(requestBody);
                out.flush();
                out.close();
            }
            if (conn.getResponseCode() == 200) {
                input = conn.getInputStream();
            } else {
                input = conn.getErrorStream();
            }
            if (input != null) {
                outStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = input.read(buffer)) != -1) {
                    outStream.write(buffer, 0, len);
                }
                byte[] data = outStream.toByteArray();
                outStream.close();
                input.close();
                return data;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(out);
            IOUtils.closeQuietly(outStream);
        }
        return null;
    }

    /**
     * 无证书执行一次https请求，并获取返回数据
     *
     * @param uri url请求地址
     * @param method 请求方式GET or POST
     * @param requestBody 发送数据
     * @return 返回数据
     */
    public static byte[] executeHttpsRequestForTrust(String uri, String method, byte[] requestBody) throws IOException {
        URL url;
        InputStream input = null;
        DataOutputStream out = null;
        ByteArrayOutputStream outStream = null;
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new BizX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            url = new URL(uri);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("GET".equalsIgnoreCase(method) ? "GET" : "POST");
            conn.setInstanceFollowRedirects(true);
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            conn.setSSLSocketFactory(ssf);
            if (requestBody != null) {
                out = new DataOutputStream(conn.getOutputStream());
                out.write(requestBody);
                out.flush();
                out.close();
            }
            if (conn.getResponseCode() == 200) {
                input = conn.getInputStream();
            } else {
                input = conn.getErrorStream();
            }
            if (input != null) {
                outStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = input.read(buffer)) != -1) {
                    outStream.write(buffer, 0, len);
                }
                byte[] data = outStream.toByteArray();
                outStream.close();
                input.close();
                return data;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(out);
            IOUtils.closeQuietly(outStream);
        }
        return null;
    }

    /**
     * 生成http请求形式的key-value字符串
     *
     * @param map 参数key-value
     * @param encode 是否URL编码
     */
    public static String buildHttpKeyValueSting(Map<String, Object> map, boolean encode) {
        if (map == null || map.isEmpty())
            return "";
        return buildHttpKeyValueSting(map.entrySet(), encode);
    }

    /**
     * 生成http请求形式的key-value字符串
     *
     * @param entrys 参数key-value
     * @param encode 是否URL编码
     */
    public static String buildHttpKeyValueSting(Set<Entry<String, Object>> entrys, boolean encode) {
        if (entrys == null || entrys.isEmpty())
            return "";
        StringBuilder sb = new StringBuilder();
        Iterator<Entry<String, Object>> iter = entrys.iterator();
        while (iter.hasNext()) {
            Entry<String, Object> entry = iter.next();
            if (entry.getValue() != null) {
                try {
                    sb.append(entry.getKey()).append("=").append(encode ? URLEncoder.encode(entry.getValue().toString(), "UTF-8") : entry.getValue()).append("&");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.substring(0, sb.length() - 1);
    }
}

class BizX509TrustManager implements X509TrustManager {
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[]{};
    }

    public void checkClientTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
    }
}
