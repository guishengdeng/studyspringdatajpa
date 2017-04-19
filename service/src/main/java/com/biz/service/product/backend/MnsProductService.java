package com.biz.service.product.backend;

import com.biz.gbck.vo.product.backend.*;
import com.biz.gbck.vo.product.event.UpdateMnsProductEventVo;
import com.biz.gbck.vo.product.mns.MnsGroupProductVo;
import com.biz.gbck.vo.product.mns.MnsProductVo;

/**
 * 转换中台商品服务接口
 *
 * @author zhangcheng
 * @date 2017/1/5
 * @reviewer
 * @see
 */
public interface MnsProductService {

    /**
     * 分页查询出所有的中台推送过来的商品
     */
    BootstrapTablePageResult<MnsProductResponseVo> findAllMnsProducts(SearchPageVo vo);

    /**
     * 根据中台编码模糊查询中台商品
     */
    BootstrapTablePageResult<MnsProductResponseVo> findByProductCode(SearchPageVo vo);

    /**
     * 修改中台商品的状态
     */
    UpdateMnsProductStatusResponseVo updateStatus(UpdateMnsProductStatusReqVo reqVo);


    /**
     * 转换和保存从中台同步过来的商品主数据
     *
     * @author zhangcheng
     */
    void trans(MnsProductVo mnsProductVo);

    /**
     * 将中台商品主数据转换为业务需要的商品数据
     *
     * @author zhangcheng
     */
    void transToBusinessProduct(UpdateMnsProductEventVo eventVo);

    /**
     * 转换中台组合商品
     *
     * @author zhangcheng
     */
    void translateMnsGroupProduct(MnsGroupProductVo mnsGroupProductVo);
}
