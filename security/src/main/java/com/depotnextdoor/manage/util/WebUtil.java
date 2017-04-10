package com.depotnextdoor.manage.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

public class WebUtil {

    private static final String XCOOKIE_PREFIX = "X1919_COOKIE";

    /**
     * 不可实例化
     */
    private WebUtil() {
    }

    /**
     * 添加cookie
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param name cookie名称
     * @param value cookie值
     * @param maxAge 有效期(单位: 秒)
     * @param path 路径
     * @param domain 域
     * @param secure 是否启用加密
     */
    public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, Integer maxAge, String path, String domain, Boolean secure) {
        Assert.notNull(request);
        Assert.notNull(response);
        Assert.hasText(name);
        try {
            String secureStr;
            String maxAgeStr = "";
            name = URLEncoder.encode(name, "UTF-8");
            value = URLEncoder.encode(value, "UTF-8");
            if (path == null) {
                path = "";
            }
            if (domain == null) {
                domain = "";
            }
            Cookie cookie = new Cookie(name, value);
            if (maxAge != null) {
                cookie.setMaxAge(maxAge);
                maxAgeStr = maxAge.toString();
            } else {
                maxAgeStr = "";
            }
            if (StringUtils.isNotEmpty(path)) {
                cookie.setPath(path);
            } else {
                cookie.setPath("/");
            }
            if (StringUtils.isNotEmpty(domain)) {
                cookie.setDomain(domain);
            }
            if (secure != null) {
                cookie.setSecure(secure);
                secureStr = secure.toString();
            } else {
                secureStr = "";
            }

            response.addCookie(cookie);
            response.addHeader(XCOOKIE_PREFIX + "-" + name, value + ";" + domain + ";" + path + ";" + maxAgeStr + ";" + secureStr);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加cookie
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param name cookie名称
     * @param value cookie值
     * @param maxAge 有效期(单位: 秒)
     */
    public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, Integer maxAge, String path) {
        addCookie(request, response, name, value, maxAge, path, null, null);
    }

    /**
     * 添加cookie
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param name cookie名称
     * @param value cookie值
     */
    public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value) {
        addCookie(request, response, name, value, null, request.getContextPath(), getDomain(request), null);
    }

    /**
     * 获取cookie
     *
     * @param request HttpServletRequest
     * @param name cookie名称
     * @return 若不存在则返回null
     */
    public static String getCookie(HttpServletRequest request, String name) {
        Assert.notNull(request);
        Assert.hasText(name);
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            try {
                name = URLEncoder.encode(name, "UTF-8");
                for (Cookie cookie : cookies) {
                    if (name.equals(cookie.getName())) {
                        return URLDecoder.decode(cookie.getValue(), "UTF-8");
                    }
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        // try X-Cookie
        return getXCookie(request, name);
    }

    private static String getXCookie(HttpServletRequest request, String name) {
        String xCookieStr = request.getHeader(XCOOKIE_PREFIX);
        if (StringUtils.isBlank(xCookieStr)) {
            return null;
        }
        String xCookies[] = xCookieStr.split(";");
        if (xCookies.length == 0) {
            return null;
        }
        String value = null;
        for (int i = 0; i < xCookies.length; ++i) {
            value = getXCookieValue(name, xCookies[i]);
            if (value != null) {
                return value;
            }
        }
        return null;
    }

    private static String getXCookieValue(String name, String line) {
        try {
            String parts[] = line.split("=", 2);
            if (parts.length < 2) {
                return null;
            }
            String key = URLDecoder.decode(parts[0], "UTF-8");
            if (name.equalsIgnoreCase(key)) {
                return URLDecoder.decode(parts[1], "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
        }
        return null;
    }

    /**
     * 移除cookie
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param name cookie名称
     * @param path 路径
     * @param domain 域
     */
    public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String name, String path, String domain) {
        Assert.notNull(request, "request is null");
        Assert.notNull(response, "response is null");
        Assert.hasText(name, "name is null");
        try {
            name = URLEncoder.encode(name, "UTF-8");
            Cookie cookie = new Cookie(name, null);
            cookie.setMaxAge(0);
            if (StringUtils.isNotEmpty(path)) {
                cookie.setPath(path);
            }
            if (StringUtils.isNotEmpty(domain)) {
                cookie.setDomain(domain);
            }
            response.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 移除cookie
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param name cookie名称
     */
    public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String name, String path) {
        removeCookie(request, response, name, path, null);
    }

    /**
     * 根据request获取请求域名
     *
     * @author bruce.qin
     * @date 2016年9月14日
     */
    public static String getDomain(HttpServletRequest request) {
        StringBuffer url = request.getRequestURL();
        String uri = request.getRequestURI();
        int begin = url.indexOf("//");
        begin = begin > 0 ? begin + 2 : 0;
        int end = StringUtils.equals(uri, "/") ? url.lastIndexOf(uri) : url.indexOf(uri);
        end = end > 0 ? end : url.length();
        return url.substring(begin, end);
    }

    /**
     * 根据request获取带协议的请求域名
     *
     * @author zhangning
     * @date 2016年10月08日
     */
    public static String getHttpDomain(HttpServletRequest request) {
        StringBuffer url = request.getRequestURL();
        return url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
    }
}
