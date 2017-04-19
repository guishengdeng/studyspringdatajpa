package com.biz.gbck.transform.product;

import com.biz.gbck.dao.mysql.po.product.TemporaryProduct;
import com.biz.gbck.vo.product.backend.SyncProductDataVo;
import com.google.common.base.Function;
import org.codelogger.utils.BeanUtils;

/**
 * 转换器 SyncProductDataVo --> TemporaryProduct
 *
 * @author zhangcheng
 * @date 2017/2/19
 * @reviewer
 * @see
 */
public class SyncProductDataVo2TemporaryProduct implements Function<SyncProductDataVo, TemporaryProduct> {
    @Override
    public TemporaryProduct apply(SyncProductDataVo dataVo) {
        if (dataVo != null) {
            TemporaryProduct temporaryProduct = new TemporaryProduct();
            BeanUtils.copyProperties(temporaryProduct, dataVo);
            return temporaryProduct;
        }
        return null;
    }
}
