package com.biz.gbck.transform.product;

import com.biz.gbck.dao.mysql.po.product.TemporaryVendorProduct;
import com.biz.gbck.vo.product.backend.SyncVendorProductDataVo;
import com.google.common.base.Function;
import org.codelogger.utils.BeanUtils;

/**
 * @author zhangcheng
 * @date 2017/2/23
 * @reviewer
 * @see
 */
public class SyncVendorProductDataVo2TemporaryVendorProduct implements Function<SyncVendorProductDataVo, TemporaryVendorProduct> {

    @Override
    public TemporaryVendorProduct apply(SyncVendorProductDataVo dataVo) {
        if (dataVo != null) {
            TemporaryVendorProduct temporaryVendorProduct = new TemporaryVendorProduct();
            BeanUtils.copyProperties(temporaryVendorProduct, dataVo);
            return temporaryVendorProduct;
        }
        return null;
    }
}
