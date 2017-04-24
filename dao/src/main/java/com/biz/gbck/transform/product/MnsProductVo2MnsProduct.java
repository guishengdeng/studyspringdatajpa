package com.biz.gbck.transform.product;

import com.biz.gbck.dao.mysql.po.product.MnsProduct;
import com.biz.gbck.vo.product.mns.MnsProductVo;
import com.google.common.base.Function;
import org.codelogger.utils.BeanUtils;

/**
 * MnsProductVo --> MnsProduct
 *
 * @author zhangcheng
 * @date 2017/1/5
 * @reviewer
 * @see
 */
public class MnsProductVo2MnsProduct implements Function<MnsProductVo, MnsProduct> {

    @Override
    public MnsProduct apply(MnsProductVo mnsProductVo) {
        if (mnsProductVo != null) {
            MnsProduct mnsProduct = new MnsProduct();
            BeanUtils.copyProperties(mnsProductVo, mnsProduct);
            return mnsProduct;
        }
        return null;
    }
}
