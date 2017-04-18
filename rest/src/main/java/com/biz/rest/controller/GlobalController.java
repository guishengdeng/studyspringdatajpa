package com.biz.rest.controller;

/*import com.depotnearby.common.model.GlobalParams;
import com.depotnearby.common.ro.config.AppConfigRo;
import com.depotnearby.common.ro.config.AppSideslipBarConfigRo;
import com.depotnearby.common.ro.config.NavigationRo;
import com.depotnearby.common.ro.info.AdvRo;
import com.depotnearby.common.transformer.AppConfigRoToAppConfigVo;
import com.depotnearby.common.util.JsonUtil;
import com.depotnearby.common.vo.adv.AdvVo;
import com.depotnearby.common.vo.config.AppConfigVo;
import com.depotnearby.common.vo.config.NavigationVo;
import com.depotnearby.rest.bean.JSONResult;
import com.depotnearby.rest.component.ServiceDiscoverer;
import com.depotnearby.rest.transformer.info.AdvRoToAdvVo;
import com.depotnearby.rest.transformer.info.NavigationRoToNavigationVo;
import com.depotnearby.rest.util.RestUtil;
import com.depotnearby.service.CacheService;
import com.depotnearby.service.UpgradeService;
import com.depotnearby.service.info.AdvService;
import com.depotnearby.service.info.NavigationService;
import com.depotnearby.service.info.SideslipBarService;
import com.depotnearby.util.DateTool;
import com.depotnearby.util.DepotnearbyQiNiuUtils;
import com.depotnearby.web.util.HttpServletHelper;*/
import com.biz.gbck.common.ro.upgrade.UpgradeRo;
import com.biz.service.UpgradeService;
import com.biz.support.web.handler.JSONResult;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("init")
public class GlobalController  { //extends BaseController

    private static final Logger logger = LoggerFactory.getLogger(GlobalController.class);

   /* @Autowired
    private CacheService cacheService;

    @Autowired
    private AdvService advService;
*/
    @Autowired
    private UpgradeService upgradeService;

   /* @Autowired
    private NavigationService navigationService;


    @Autowired
    private ServiceDiscoverer restDiscoverer;

    @Autowired
    private SideslipBarService sideslipBarService;

    private static long nextVer = UpgradeRo.versionToSroce("2.1.0");*/

//    
//    public static void printHeader(HttpServletRequest response) {
//    	Enumeration<String> headers = response.getHeaderNames();
//		StringBuilder cookie = new StringBuilder();
//
//		while (headers.hasMoreElements()) {
//			String name = headers.nextElement();
//			String value = response.getHeader(name);
//			logger.info("request header {} : {}",name,value);
//		}
//		System.out.println(cookie.toString());
//	}


   /* @RequestMapping("/init")
    public JSONResult init(HttpServletRequest request) {

    	String ver = request.getParameter("ver");
    	long currentVer = 0;
    	try{
    		currentVer = UpgradeRo.versionToSroce(ver);
    	}catch(Exception e){
    		
    	}

        Map result = new HashMap();

        AppConfigVo config = cacheService.getAppConfigVo();
        result.putAll(config.getMap());

        result.put("qiniu", DepotnearbyQiNiuUtils.getBuckets());

        List<NavigationRo> roList = navigationService.findAll();
        if (roList != null) {
            List<NavigationVo> navigationVoList = Lists.transform(roList, new NavigationRoToNavigationVo());
            
            for(NavigationVo vo:navigationVoList){
            	if(currentVer>=nextVer){
            		vo.uri = vo.uri.replace("/search.do", "/search2.do");
            	}else{
            		vo.uri = vo.uri.replace("/search2.do", "/search.do");
            	}
            }
            result.put("navigations", navigationVoList);
        }

        List<AppSideslipBarConfigRo> sideslipBarConfigRos = sideslipBarService.get();
        result.put("appSideslipBars",sideslipBarConfigRos);

        return new JSONResult(result);
    }
*/

/*
    @RequestMapping("/adv")
    public JSONResult adv(HttpServletRequest request) {
        List<AdvRo> roList = advService.findAll();
        List<AdvVo> voList = Lists.transform(roList, new AdvRoToAdvVo());
        return new JSONResult(voList);
    }*/


   /* @RequestMapping(value = "dispatch")
    public JSONResult dispatch(HttpServletRequest request) {

        Map map = new HashMap();
        //      map.put("token", Constant.DEFAULT_TOKEN);
        String proxy = request.getHeader("X-depotnearby-proxy");
        String netType = request.getHeader("X-depotnearby-netType");
        String host = request.getHeader("Host");
        String clientIp = HttpServletHelper.getClientIP(request);

        logger.debug("proxy:{}, netType:{}, host:{}, clientIp:{}", proxy, netType, host, clientIp);
        GlobalParams globalParams = RestUtil.parseGlobalParams(request);

        Map<String, List<String>> mainNet = new HashMap<>();
        mainNet.put("rest", restDiscoverer.getRestProviders(clientIp, globalParams));
        map.put("server", mainNet);

        return new JSONResult(map);
    }*/

    @RequestMapping("upgrade")
    public JSONResult upgrade(
            @RequestParam(value = "ver", required = true, defaultValue = "0") String ver,
            @RequestParam(value = "os", required = true, defaultValue = "") String os,
            @RequestParam(value = "partner", required = true, defaultValue = "") String partner,
            HttpServletRequest request) {
        boolean inhourse = StringUtils.equalsIgnoreCase("inhouse", partner);
        UpgradeRo ro = upgradeService.needUpgrade(os, ver, inhourse);
        if (ro != null) {
            return new JSONResult(ro);
        } else {
            return new JSONResult("need", false);
        }
    }



   /* public static void main(String[] args) {
    	System.out.println(UpgradeRo.versionToSroce("2.1.0"));
    }
    

    public static void main3(String[] args) {
        Map result = new HashMap();


        AppConfigRo ro = new AppConfigRo();
        AppConfigVo config = (new AppConfigRoToAppConfigVo()).apply(ro);
        result.putAll(config.getMap());

        result.put("qiniu", DepotnearbyQiNiuUtils.getBuckets());
        List<NavigationRo> roList = new ArrayList<NavigationRo>();

        roList.add(new NavigationRo(0, "仓库推荐", "html", "homepage/index.do", ""));
        roList.add(new NavigationRo(1, "白酒仓库", "native", "product/search.do", ""));
        roList.add(new NavigationRo(2, "红酒仓库", "native", "product/search.do", ""));
        roList.add(new NavigationRo(3, "啤酒仓库", "native", "product/search.do", ""));
        roList.add(new NavigationRo(4, "更多", "native", "product/search.do", ""));


        if (roList != null) {
            List<NavigationVo> navigationVoList = Lists.transform(roList, new NavigationRoToNavigationVo());
            result.put("navigations", navigationVoList);
        }

        System.out.println(JsonUtil.obj2Json(new JSONResult(result)));
    }

    public static void main1(String[] args) {
        List<AdvRo> roList = new ArrayList<AdvRo>();

        for (int i = 1; i < 3; i++) {
            AdvRo ro = new AdvRo();
            ro.setId(new Long(i));
            ro.setClickUrl("http://www.1919.cn");
            ro.setImgUrl("http://www.1919.cn");
            ro.setStartTime(DateTool.nowTimestamp());
            ro.setEndTime(new java.sql.Timestamp(DateTool.afterDays(10)));
            ro.setPriority(i);
            roList.add(ro);
        }
        List<AdvVo> voList = Lists.transform(roList, new AdvRoToAdvVo());

        System.out.println(JsonUtil.obj2Json(new JSONResult(voList)));
    }*/

}
