package com.biz.soa.org.controller;

import com.biz.gbck.dao.redis.ro.notice.NoticeRo;
import com.biz.gbck.vo.info.ListNoticeAfterLastReqVo;
import com.biz.gbck.vo.info.NoticeResVo;
import com.biz.soa.org.service.notice.interfaces.NoticeSoaService;
import com.biz.soa.org.transformer.NoticeRoToNoticeResVo;
import com.biz.support.web.handler.JSONResult;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 消息，活动
 *
 * @author gongshutao
 */

@RestController
@RequestMapping("/info") public class InfoController extends BaseRestController{


    private static Logger logger = LoggerFactory.getLogger(InfoController.class);

    @Autowired
    private NoticeSoaService noticeSoaService;


    /**
     *消息中心
     */
    @RequestMapping("/notices")
    public Object notices(@RequestBody ListNoticeAfterLastReqVo reqVo,
            HttpServletRequest httpRequest, HttpServletResponse response) {
        List<NoticeRo> roList =
                noticeSoaService.findUserNoticeAfter(reqVo.getUserId(), reqVo.getLastNoticeId());
        List<NoticeResVo> voList = Lists.transform(roList, new NoticeRoToNoticeResVo());
        return new JSONResult(voList);
    }




}
