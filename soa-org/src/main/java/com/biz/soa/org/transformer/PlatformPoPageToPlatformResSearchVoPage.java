package com.biz.soa.org.transformer;

import com.biz.gbck.dao.mysql.po.org.PlatformPo;
import com.biz.gbck.vo.platform.PlatformResSearchVo;
import com.biz.gbck.vo.spring.PageVO;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.codelogger.utils.CollectionUtils;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial") public class PlatformPoPageToPlatformResSearchVoPage
    implements Function<Page<PlatformPo>, PageVO<PlatformResSearchVo> >, Serializable {


    @Override
    public PageVO<PlatformResSearchVo> apply(Page<PlatformPo> input) {
        PageVO<PlatformResSearchVo> pageVo=new PageVO<PlatformResSearchVo>();
        if(input != null){
            pageVo.setLast(input.isLast());
            pageVo.setFirst(input.isFirst());
            pageVo.setNumber(input.getNumber());
            pageVo.setNumberOfElements(input.getNumberOfElements());
            pageVo.setSize(input.getSize());
            pageVo.setTotalElements(input.getTotalElements());
            pageVo.setTotalPages(input.getTotalPages());
            List<PlatformPo> platformPos=input.getContent();
            if(CollectionUtils.isNotEmpty(platformPos)){
                pageVo.setContent(Lists.transform(platformPos,new PlatformPoToPlatformResSearchVo()));
            }
        }
        return pageVo;
    }
}
