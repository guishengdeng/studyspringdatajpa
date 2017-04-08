package com.depotnextdoor.support.web.assist;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;


/**
 * 全局参数
 */
public class GlobalParams implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2980565642013490335L;

    /**
     * 手机操作系统 {ios, android}
     */
    private String os;

    /**
     * 操作系统版本号
     */
    private String osVersion;

    /**
     * 设备ID
     */
    private String deviceId;

    /**
     * 客户端版本号
     */
    private String ver;

    /**
     * 手机型号
     */
    private String userAgent;

    /**
     * 网络类型{wifi,wan}
     */
    private String apn;

    /**
     * 签名
     */
    private String sign;

    /**
     * 当前登录用户Id
     */
    private Long userId;

    /**
     * 推广渠道标志
     */
    private String partner;

    /**
     * 推广子渠道标志
     */
    private String sub;

    /**
     * 定位纬度
     */
    private BigDecimal lat;
    /**
     * 定位经度
     */
    private BigDecimal lon;

    /**
     * 配送地址定位纬度
     */
    private BigDecimal addressLat;
    /**
     * 配送地址定位经度
     */
    private BigDecimal addressLon;

    /**
     * 手机mac地址
     */
    private String mac;

    /**
     * 国际移动用户识别码
     */
    private String imsi;

    /**
     * 国际移动设备标识
     */
    private String imei;

    /**
     * 路由器地址
     */
    private String routerMac;

    /**
     * geo 城市id
     */
    private Long cityId;

    //	/**
    //	 * geo 城市id
    //	 */
    //	private Long provinceId;

    /**
     * 门店id
     */
    private Long depotId;

    /**
     * 基站信息
     */
    private String station;
    /**
     * 用户登录token
     */
    private String authToken;

    /**
     * 省仓店Id
     */
    private Long warehouseDepotId;

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getApn() {
        return apn;
    }

    public void setApn(String apn) {
        this.apn = apn;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }

    public BigDecimal getAddressLat() {
        return addressLat;
    }

    public void setAddressLat(BigDecimal addressLat) {
        this.addressLat = addressLat;
    }

    public BigDecimal getAddressLon() {
        return addressLon;
    }

    public void setAddressLon(BigDecimal addressLon) {
        this.addressLon = addressLon;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getRouterMac() {
        return routerMac;
    }

    public void setRouterMac(String routerMac) {
        this.routerMac = routerMac;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    //	public Long getProvinceId() {
    //		return provinceId;
    //	}
    //
    //	public void setProvinceId(Long provinceId) {
    //		this.provinceId = provinceId;
    //	}

    public Long getDepotId() {
        return depotId;
    }

    public void setDepotId(Long depotId) {
        this.depotId = depotId;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public Long getWarehouseDepotId() {
        return warehouseDepotId;
    }

    public void setWarehouseDepotId(Long warehouseDepotId) {
        this.warehouseDepotId = warehouseDepotId;
    }

    public static GlobalParams fromRequest(HttpServletRequest httpRequest) {
        GlobalParams params = null;
        try {
            GlobalParams p = new GlobalParams();
            p.setApn(httpRequest.getParameter("apn"));
            p.setDeviceId(httpRequest.getParameter("deviceId"));
            String latStr = httpRequest.getParameter("lat");
            if (StringUtils.isNotEmpty(latStr)) {
                try {
                    p.setLat(new BigDecimal(latStr));
                } catch (Exception e) {
                }
            }
            String lonStr = httpRequest.getParameter("lon");
            if (StringUtils.isNotEmpty(lonStr)) {
                try {
                    p.setLon(new BigDecimal(lonStr));
                } catch (Exception e) {
                }
            }
            p.setOs(httpRequest.getParameter("os"));
            p.setOsVersion(httpRequest.getParameter("osVersion"));

            p.setPartner(httpRequest.getParameter("partner"));
            p.setSign(httpRequest.getParameter("sign"));
            p.setSub(httpRequest.getParameter("sub"));
            p.setUserAgent(httpRequest.getParameter("userAgent"));
            String userIdStr = httpRequest.getParameter("userId");
            if (StringUtils.isNumeric(userIdStr)) {
                p.setUserId(Long.valueOf(userIdStr));
            }
            p.setVer(httpRequest.getParameter("ver"));
            p.setMac(httpRequest.getParameter("mac"));
            p.setImsi(httpRequest.getParameter("imsi"));
            p.setImei(httpRequest.getParameter("imei"));
            p.setRouterMac(httpRequest.getParameter("routerMac"));
            p.setStation(httpRequest.getParameter("station"));
            String cityIdStr = httpRequest.getParameter("cityId");
            if (StringUtils.isNumeric(cityIdStr)) {
                p.setCityId(Long.valueOf(cityIdStr));
            }

            //			String provinceIdStr = httpRequest.getParameter("provinceId");
            //			if (StringUtils.isNumeric(provinceIdStr)) {
            //				p.setProvinceId(Long.valueOf(provinceIdStr));
            //			}

            String depotIdStr = httpRequest.getParameter("depotId");
            if (StringUtils.isNumeric(depotIdStr)) {
                p.setDepotId(Long.valueOf(depotIdStr));
            }
            String addressLatStr = httpRequest.getParameter("addressLat");
            if (StringUtils.isNotEmpty(addressLatStr)) {
                try {
                    p.setAddressLat(new BigDecimal(addressLatStr));
                } catch (Exception e) {
                }
            }
            String addressLonStr = httpRequest.getParameter("addressLon");
            if (StringUtils.isNotEmpty(addressLonStr)) {
                try {
                    p.setAddressLon(new BigDecimal(addressLonStr));
                } catch (Exception e) {
                }
            }
            params = p;
        } catch (Exception e) {
            //			logger.error("解析全局参数出错", e);
            e.printStackTrace();
        }
        return params;
    }

}
