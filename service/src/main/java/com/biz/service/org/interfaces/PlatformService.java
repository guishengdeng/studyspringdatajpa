package com.biz.service.org.interfaces;

import com.biz.gbck.dao.mysql.po.org.PartnerPo;
import com.biz.gbck.dao.mysql.po.org.PlatformPo;
import com.biz.gbck.enums.org.CompanyLevel;
import com.biz.gbck.vo.org.ShopSearchVo;
import com.biz.gbck.vo.platform.PartnerSearchVo;
import com.biz.gbck.vo.platform.PlatFormRespVo;
import com.biz.gbck.vo.platform.PlatformSearchVo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author: liubin
 * @date 5/2/17 10:10
 */
public interface PlatformService {

    List<PlatformPo> findAll();

    PlatformPo findOne(Long id);

    Page<PlatformPo> findPlatformList(PlatformSearchVo vo);

    Page<PartnerPo> findPartnerList(PartnerSearchVo reqVo);

    PartnerPo findPartnerById(Long id);

    List<PlatFormRespVo>  poList2VoList(List<PlatformPo> platformPos);

    List<PlatformPo>  findByIds(Iterable<Long> iterable);

    List<PlatformPo>  listByName(String name);

    List<PlatFormRespVo> getNotDuplicatedName();

    List<PlatFormRespVo> getRespVoByCompanyLevel(CompanyLevel companyLevel);
}
