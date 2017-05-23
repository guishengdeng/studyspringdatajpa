package com.biz.rest.controller.global;

import com.biz.gbck.vo.info.ListNoticeAfterLastReqVo;
import com.biz.rest.util.RestUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.biz.soa.feign.client.global.NoticeFeignClient;
import com.biz.support.web.handler.JSONResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 消息，活动
 *
 * @author gongshutao
 */

@RestController
@RequestMapping("/info") public class InfoController {

    private static Logger logger = LoggerFactory.getLogger(InfoController.class);


    @Autowired
    private NoticeFeignClient noticeFeignClient;


    /**
     * 消息中心
     */
    @RequestMapping(value = "/notices" , method = RequestMethod.POST)
    public JSONResult notices(HttpServletRequest httpRequest, HttpServletResponse response) {
        ListNoticeAfterLastReqVo reqVo =
                RestUtil.parseBizData(httpRequest, ListNoticeAfterLastReqVo.class);
        return noticeFeignClient.findUserNoticeAfter(reqVo);
    }


   /* @RequestMapping("/promotions")
    public Object promotions(HttpServletRequest httpRequest, HttpServletResponse response) {
        CommonReqVoBindUserId reqVo =
            RestUtil.parseBizData(httpRequest, CommonReqVoBindUserId.class);
        List<PromotionRo> roList = promotionService.findAllShowInApp();
        List<PromotionVo> voList = Lists.transform(roList, new PromotionRoToPromotionVo());
        promotionService.updateUserLatestFetchTime(reqVo.getUserId());
        return new JSONResult(voList);
    }*/


}
