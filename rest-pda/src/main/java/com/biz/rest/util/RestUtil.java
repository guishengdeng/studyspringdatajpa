package com.biz.rest.util;

/*import com.depotnearby.common.exception.ExceptionCode;
import com.depotnearby.common.model.GlobalParams;
import com.depotnearby.common.model.InitGlobalParams;
import com.depotnearby.exception.CommonException;
import com.depotnearby.exception.CommonRuntimeException;
import com.depotnearby.rest.bean.Constant;
import com.depotnearby.vo.CommonReqVo;*/
import com.biz.gbck.common.exception.CommonRuntimeException;
import com.biz.gbck.common.exception.ExceptionCode;
import com.biz.gbck.common.model.InitGlobalParams;
import com.biz.gbck.common.vo.CommonReqVo;
import com.biz.rest.bean.Constant;
import com.biz.support.web.assist.GlobalParams;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Rest接口工具
 *
 * @author zhujun
 * @date 2014年9月1日
 */
public class RestUtil {
    private static final Logger LOG = LoggerFactory.getLogger(RestUtil.class);

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private final static Validator VALIDATOR =
        Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * 从http request中解析业务参数
     *
     * @param httpRequest
     * @param clazz       参数类型
     * @return
     * @throws
     */
    public static <T> T parseBizData(HttpServletRequest httpRequest, Class<T> clazz)
        throws CommonRuntimeException {
        String bizData = httpRequest.getParameter(Constant.POST_DATA_PARAM_NAME);
        LOG.debug("请求参数:\n{}", bizData);
        T t = null;
        try {
            if (StringUtils.isNotBlank(bizData)) {
                // 有业务参数, 使用json反序列化
                t = OBJECT_MAPPER.readValue(bizData, clazz);
            } else {
                // 无业务参数, 默认构建
                t = clazz.newInstance();
            }
        } catch (Exception e) {
            LOG.error("解析接口业务参数出错", e);
            throw new CommonRuntimeException("解析接口业务参数出错", ExceptionCode.Global.PARAMETER_ERROR, e);
        }

        if (t instanceof InitGlobalParams) {
            // 初始化全局参数
            GlobalParams globalParams = parseGlobalParams(httpRequest);
            ((InitGlobalParams) t).setGlobalParams(globalParams);
        }

        Class<?>[] groups = null;
        if (t instanceof CommonReqVo) {
            CommonReqVo crv = ((CommonReqVo) t);
            crv.bindRequestParams(httpRequest);
            groups = crv.groupClazz();

            // 设置md5 attribute
            httpRequest.setAttribute(Constant.MD5_HTTP_ATTR_NAME, crv.getMd5());
        }
        validate(t, groups);

        return t;
    }

    /**
     * 解析全局参数
     * <p/>
     * <p>
     * 解析出错时, 返回null, 打印错误日志
     * </p>
     *
     * @param httpRequest
     * @author zhujun
     * @date 2015-2-3
     */
    public static GlobalParams parseGlobalParams(HttpServletRequest httpRequest) {
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
            params = p;
        } catch (Exception e) {
            LOG.error("解析全局参数出错", e);
        }

        return params;
    }

    /**
     * 校验参数
     *
     * @param obj
     * @param groups
     * @throws
     */
    public static void validate(Object obj, Class<?>... groups) throws CommonRuntimeException {
        Set<ConstraintViolation<Object>> constraintViolations = null;
        if (groups == null || groups.length == 0) {
            constraintViolations = VALIDATOR.validate(obj);
        } else {
            constraintViolations = VALIDATOR.validate(obj, groups);
        }

        if (constraintViolations != null && !constraintViolations.isEmpty()) {
            StringBuilder errorInfo = new StringBuilder();
            for (ConstraintViolation<Object> cv : constraintViolations) {
                errorInfo.append(cv.getPropertyPath()).append(", ").append(cv.getMessage())
                    .append("; ");
            }
            throw new CommonRuntimeException("业务参数约束出错:" + errorInfo,
                ExceptionCode.Global.PARAMETER_ERROR);
        }
    }

}
