package com.biz.gbck.vo.security;

import javax.servlet.http.HttpServletRequest;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Created by defei on 6/30/15.
 */
public class SecurityReqVo implements Comparable<SecurityReqVo> {

    public static final String X_REAL_IP = "X-Real-IP";
    private String uri;

    private String ip;

    private Long timestamp;

    public SecurityReqVo(HttpServletRequest request) {
        String xRealIp = request.getHeader(X_REAL_IP);
        ip = isBlank(xRealIp) ? request.getRemoteHost() : xRealIp;
        uri = request.getRequestURI();
        timestamp = System.currentTimeMillis();
    }

    public SecurityReqVo(String ip, Long timestamp, String uri) {
        this.ip = ip;
        this.timestamp = timestamp;
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "SecurityReqVo{" +
                "uri='" + uri + '\'' +
                ", ip='" + ip + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    public String getUri() {
        return uri;
    }

    public String getIp() {
        return ip;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    @Override
    public int compareTo(SecurityReqVo o) {

        return o.getTimestamp().compareTo(timestamp);
    }
}
