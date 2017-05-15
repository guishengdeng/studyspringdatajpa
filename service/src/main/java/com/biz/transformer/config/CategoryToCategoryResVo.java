package com.biz.transformer.config;


import com.biz.gbck.dao.mysql.po.product.meta.Category;
import com.biz.gbck.vo.config.CategoryResVo;
import com.google.common.base.Function;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CategoryToCategoryResVo implements Function<Category, CategoryResVo>, Serializable {

    @Override public CategoryResVo apply(Category po) {
        CategoryResVo vo=null;
        if(po != null){
            vo=new CategoryResVo();
            vo.setId(po.getId().toString());
            vo.setName(po.getName());
            Map<String,String> map=new HashMap<String,String>();
            map.put("categoryId",po.getId().toString());
            vo.setPostData(map);
        }
        return vo;
    }
}
