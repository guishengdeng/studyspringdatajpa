package com.biz.service.product.backend;


import com.biz.gbck.exceptions.product.ApartTagNotFoundException;
import com.biz.gbck.vo.product.backend.ApartTagCreateVo;
import com.biz.gbck.vo.product.backend.ApartTagListItemVo;
import com.biz.gbck.vo.product.backend.ApartTagUpdateVo;
import java.util.List;

/**
 * @author 江南
 * @usage
 * @reviewer
 * @since 2016/12/28
 */
public interface ApartTagService {

    /**
     * 展示列表页
     */
    List<ApartTagListItemVo> listApartTag();

    /**
     * 创建ApartTag
     */
    ApartTagCreateVo createApartTag(ApartTagCreateVo apartTagCreateVo);

    /**
     * 更新ApartTag
     */
    ApartTagUpdateVo updateApartTag(ApartTagUpdateVo apartTagUpdateVo) throws ApartTagNotFoundException;

    /**
     * 删除ApartTag
     */
    boolean deleteApartTag(Long apartTagId) throws ApartTagNotFoundException;


    ApartTagUpdateVo getApartTagUpdateVo(Long apartTagId) throws ApartTagNotFoundException;
}