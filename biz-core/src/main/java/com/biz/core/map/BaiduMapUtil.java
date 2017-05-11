package com.biz.core.map;


import com.biz.core.util.HttpUtil;

/**
 * @author david-liu
 * @date 2016年09月12日
 * @reviewer
 * @see
 */
public class BaiduMapUtil {

//    private static final Integer MAX_RETRY_TIMES = 3;

    public static String getLocationDecode(Double latitude, Double longitude) {
        String requestUrl = String.format(BaiduMapConfig.LOCATION_DECODE_URL, BaiduMapConfig.BAIDU_MAP_AK, latitude + "," + longitude);
        return new String(HttpUtil.executeHttpRequest(requestUrl, "GET", null));
    }
}
