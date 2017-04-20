package com.biz.rest.controller;

import com.biz.gbck.dao.redis.ro.info.PromotionRo;
import com.biz.rest.transformer.info.PromotionRoToPromotionVo;
import com.biz.rest.vo.PromotionVo;
import com.biz.service.info.PromotionService;
import com.biz.support.web.handler.JSONResult;
import com.google.common.collect.Lists;
import org.codelogger.utils.CollectionUtils;
import org.codelogger.utils.ValueUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;


/**
 * 消息，活动
 *
 * @author gongshutao
 */

@RestController
@RequestMapping("/info") public class InfoController {


    private static Logger logger = LoggerFactory.getLogger(InfoController.class);


    @Autowired
    private PromotionService promotionService;


    @RequestMapping("/promotions")
    public Object promotions(HttpServletRequest httpRequest, HttpServletResponse response) {
       /* CommonReqVoBindUserId reqVo =
            RestUtil.parseBizData(httpRequest, CommonReqVoBindUserId.class);*/
        List<PromotionRo> roList = promotionService.findAllShowInApp();
        List<PromotionVo> voList = Lists.transform(roList, new PromotionRoToPromotionVo());
//        voList = mockNoticeVoIfSourceIsEmpty(voList);
       /* promotionService.updateUserLatestFetchTime(reqVo.getUserId());*/
        return new JSONResult(voList);
    }


}
