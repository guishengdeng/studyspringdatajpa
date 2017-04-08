package com.depotnextdoor.manage.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LogInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(LogInterceptor.class);


    //    @Autowired
    //    private SystemConfigMonitor verifyConfigService;
    //    @Autowired
    //    private LogService logService;
    //
    //    private SystemConfigMonitor.SignConfig systemConfig = null;
    //
    //    private String excludedUri;
    //
    //    private Boolean manualEnable = true;
    //
    //
    //    @PostConstruct
    //    private void init() {
    //
    //        try {
    //            SystemConfigMonitor.SignConfig cfg =
    //                    verifyConfigService.watch(new SystemConfigMonitor.Watcher() {
    //                        @Override
    //                        public void configChanged(SystemConfigMonitor.SignConfig config) {
    //                            if (logger.isDebugEnabled()) {
    //                                logger.debug("Depotnearby system config changed: {}",
    //                                        ToStringBuilder.reflectionToString(config));
    //                            }
    //                            systemConfig =
    //                                    config == null ? new SystemConfigMonitor.SignConfig() : config;
    //                        }
    //                    });
    //            if (cfg != null) {
    //                systemConfig = cfg;
    //            }
    //            logger.debug("Monitor depotnearby system config success.");
    //        } catch (Exception e) {
    //            logger.error("Monitor depotnearby system config failed.", e);
    //            systemConfig = new SystemConfigMonitor.SignConfig();
    //        }
    //    }
    //
    //    /**
    //     * 使用URI 匹配需要记录log的正则表达式
    //     */
    //    public boolean withinLog(String uri) {
    //        return uri.trim().matches(excludedUri);
    //    }
    //
    //    /**
    //     * 日志记录实现
    //     */
    //    @Override
    //    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
    //                             Object handler) throws Exception {
    //        Boolean enabled = manualEnable;
    //        if (enabled && !withinLog(request.getRequestURI())) {
    //            String uri = request.getRequestURI();
    //            Timestamp timeStamp = DateTool.nowTimestamp();
    //            String user = AuthorityUtil.getLoginUsername();
    //            String http_method = request.getMethod();
    //            JSONObject params = new JSONObject();
    //            Enumeration<String> parameterNames = request.getParameterNames();
    //            for (Enumeration e = parameterNames; e.hasMoreElements(); ) {
    //                String thisName = e.nextElement().toString();
    //                String thisValue = ArrayUtils.join(request.getParameterValues(thisName), ",");
    //                params.put(thisName, thisValue);
    //            }
    //            String requetParams = params.toJSONString();
    //            LogRo ro = new LogRo();
    //            ro.setUri(uri);
    //            ro.setTimeStamp(timeStamp.getTime());
    //            ro.setUser(user);
    //            ro.setHttp_method(http_method);
    //            ro.setRequestParams(requetParams);
    //            logger.debug("uri:{}\nmethod:{}\nuser:{}\ntimestamp:{}\nparams:{}", ro.getUri(), ro.getHttp_method(), ro.getUser(), ro.getTimeStamp(), ro.getRequestParams());
    //            logger.debug("preHadle{} loginfo", uri);
    //            logService.saveLog(ro);
    //        }
    //        return true;
    //    }
    //
    //    public void setExcludedUri(String excludedUri) {
    //        this.excludedUri = excludedUri;
    //    }
    //
    //
    //    public void setEnabled(boolean enabled) {
    //        this.manualEnable = enabled;
    //    }
}
