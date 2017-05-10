package com.biz.service.product.backend;

import com.biz.gbck.dao.mysql.po.tag.SaleTag;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.vo.product.backend.*;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 商品销售标签 Service 接口定义
 *
 * @author lzz
 * @date 2017年5月2日
 * @reviewer
 * @see
 */
public interface SaleTagService {

  List<SaleTag> listByStatus(CommonStatusEnum status);

  void addOrUpdateSaleTag(SaleTagVo vo);

  SaleTag findById(Long id);

  void remove(Long id);

  //分页
  Page<SaleTag> searchSaleTag(SaleTagSearchVo saleTagSearchVo);
}
