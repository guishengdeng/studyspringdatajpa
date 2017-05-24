package com.biz.soa.org.transformer;

import com.biz.gbck.dao.mysql.po.org.ShopDetailPo;
import com.biz.gbck.vo.org.ShopDetailResVo;
import com.biz.gbck.vo.spring.PageVO;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.codelogger.utils.CollectionUtils;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial") public class ShopDetailPoPageToShopDetailResVoPage
    implements Function<Page<ShopDetailPo>, PageVO<ShopDetailResVo> >, Serializable {


    @Override
    public PageVO<ShopDetailResVo> apply(Page<ShopDetailPo> input) {
        PageVO<ShopDetailResVo> pageVo=new PageVO<ShopDetailResVo>();
        if(input != null){
            pageVo.setLast(input.isLast());
            pageVo.setFirst(input.isFirst());
            pageVo.setNumber(input.getNumber());
            pageVo.setNumberOfElements(input.getNumberOfElements());
            pageVo.setSize(input.getSize());
            pageVo.setTotalElements(input.getTotalElements());
            pageVo.setTotalPages(input.getTotalPages());
            List<ShopDetailPo> shopDetailPos=input.getContent();
            if(CollectionUtils.isNotEmpty(shopDetailPos)){
                pageVo.setContent(Lists.transform(shopDetailPos,new ShopDetailPoToShopDetailResVo()));
            }
        }
        return pageVo;
    }
}
