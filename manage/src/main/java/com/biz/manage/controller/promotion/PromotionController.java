package com.biz.manage.controller.promotion;

/*import com.depotnearby.common.po.info.PromotionPo;
import com.depotnearby.manage.transformer.PromotionVoToPromotionPo;
import com.depotnearby.manage.vo.PromotionVo;
import com.depotnearby.service.info.PromotionService;*/
import com.biz.gbck.dao.mysql.po.info.PromotionPo;
import com.biz.gbck.transform.promotion.PromotionVoToPromotionPo;
import com.biz.gbck.vo.promotion.PromotionVo;
import com.biz.service.info.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 活动发布
 * Created by dylan on 2016/3/22.
 */
@Controller
@RequestMapping("/promotions")@Secured("ROLE_PROMOTION") public class PromotionController {
    @Autowired
    PromotionService promotionService;

    @RequestMapping(method = RequestMethod.GET) @PreAuthorize("hasAuthority('OPT_PROMOTION_LIST')")
    public ModelAndView list(Integer number, Integer size) {
        if (size == null || number == null) {
            size = Integer.MAX_VALUE;
            number = 0;
        }
        return new ModelAndView("manage/promotion/list", "promotions",
            promotionService.findNormal(new PageRequest(number, size, null)));
    }

    @RequestMapping(value = "new", method = RequestMethod.GET)  @PreAuthorize("hasAuthority('OPT_PROMOTION_SAVE')")
    public ModelAndView add() {
        return new ModelAndView("manage/promotion/detail");
    }

    @RequestMapping( method = RequestMethod.POST)  @PreAuthorize("hasAuthority('OPT_PROMOTION_SAVE')")
    public ModelAndView submitAdd(PromotionVo vo) {
        PromotionPo po= new PromotionVoToPromotionPo().apply(vo);
        po.setCreateTime(new Timestamp(new Date().getTime()));
        promotionService.save(po);
        return new ModelAndView("redirect:promotions.do");
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET) @PreAuthorize("hasAuthority('OPT_PROMOTION_UPDATE')")
    public ModelAndView toUpdate(@PathVariable("id") Long id) {
        return new ModelAndView("manage/promotion/detail", "promotion", promotionService.get(id));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.POST)  @PreAuthorize("hasAuthority('OPT_PROMOTION_UPDATE')")
    public ModelAndView update(PromotionVo vo) {
        promotionService.save(new PromotionVoToPromotionPo().apply(vo));
        return new ModelAndView("redirect:promotions.do");
    }

    @RequestMapping("/delete") @PreAuthorize("hasAuthority('OPT_PROMOTION_DELETE')")
    public ModelAndView delete(PromotionVo vo) {
        promotionService.delete(vo.getId());
        return new ModelAndView("redirect:promotions.do");
    }
}

