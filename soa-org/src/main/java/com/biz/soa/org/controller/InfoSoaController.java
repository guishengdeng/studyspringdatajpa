package com.biz.soa.org.controller;

import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.dao.redis.ro.notice.NoticeRo;
import com.biz.gbck.vo.info.ListNoticeAfterLastReqVo;
import com.biz.gbck.vo.info.NoticeResVo;
import com.biz.soa.org.service.notice.interfaces.NoticeSoaService;
import com.biz.soa.org.transformer.NoticeRoToNoticeResVo;
import com.biz.support.web.handler.JSONResult;
import com.biz.vo.notify.NotifyVo;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 消息，活动
 *
 * @author gongshutao
 */

@RestController
@RequestMapping("soa/info")
public class InfoSoaController extends BaseRestController{


    private static Logger logger = LoggerFactory.getLogger(InfoSoaController.class);

    @Autowired
    private NoticeSoaService noticeSoaService;


    /**
     *rest给用户发送消息列表
     */
    @RequestMapping(value = "/notices", method = RequestMethod.POST)
    public Object notices(@RequestBody ListNoticeAfterLastReqVo reqVo,
            HttpServletRequest httpRequest, HttpServletResponse response) {
        List<NoticeRo> roList =
                noticeSoaService.findUserNoticeAfter(Long.valueOf(reqVo.getUserId()), reqVo.getLastNoticeId());
        List<NoticeResVo> voList = Lists.transform(roList, new NoticeRoToNoticeResVo());
        return new JSONResult(voList);
    }

    /**
     * manage给对应用户发送消息
     * @param name 后台登录用户
     * @param notifyVo 消息参数
     */
    @RequestMapping(value = "/sendNotification", method = RequestMethod.POST)
    public void sendNotification (@RequestParam("name") String name, @RequestBody NotifyVo notifyVo)
            throws CommonException {
         noticeSoaService.sendNotification(name,notifyVo);
    }




}
