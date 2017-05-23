package com.biz.soa.org.transformer;

import com.biz.gbck.dao.mysql.po.org.PartnerPo;
import com.biz.gbck.vo.platform.PartnerSearchResVo;
import com.biz.gbck.vo.spring.PageVO;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.codelogger.utils.CollectionUtils;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;


@SuppressWarnings("serial") public class PartnerPoPageToPartnerSearchResVoPage
    implements Function<Page<PartnerPo>, PageVO<PartnerSearchResVo> >, Serializable {


    @Override
    public PageVO<PartnerSearchResVo> apply(Page<PartnerPo> input) {
        PageVO<PartnerSearchResVo> pageVo=new PageVO<PartnerSearchResVo>();
        if(input != null){
            pageVo.setLast(input.isLast());
            pageVo.setFirst(input.isFirst());
            pageVo.setNumber(input.getNumber());
            pageVo.setNumberOfElements(input.getNumberOfElements());
            pageVo.setSize(input.getSize());
            pageVo.setTotalElements(input.getTotalElements());
            pageVo.setTotalPages(input.getTotalPages());
            List<PartnerPo> platformPos=input.getContent();
            if(CollectionUtils.isNotEmpty(platformPos)){
                pageVo.setContent(Lists.transform(platformPos,new PartnerPoToPartnerSearchResVo()));
            }
        }
        return pageVo;
    }
}
