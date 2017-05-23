package com.biz.soa.org.service.interfaces;

import com.biz.gbck.dao.mysql.po.org.PlatformPo;
import com.biz.gbck.vo.platform.PartnerSearchResVo;
import com.biz.gbck.vo.platform.PartnerSearchVo;
import com.biz.gbck.vo.platform.PlatformResSearchVo;
import com.biz.gbck.vo.platform.PlatformSearchVo;
import com.biz.gbck.vo.spring.PageVO;

import java.util.List;

/**
 * @author: liubin
 * @date 5/2/17 10:10
 */
public interface PlatformSoaService {

    List<PlatformPo> findAll();

    PlatformPo findOne(Long id);

    PageVO<PlatformResSearchVo> findPlatformList(PlatformSearchVo vo);

    PageVO<PartnerSearchResVo> findPartnerList(PartnerSearchVo reqVo);

    PartnerSearchResVo findPartnerById(Long id);
}
