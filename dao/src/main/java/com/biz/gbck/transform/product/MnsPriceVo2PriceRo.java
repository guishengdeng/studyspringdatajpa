package com.biz.gbck.transform.product;

import com.biz.gbck.dao.redis.ro.product.PriceRo;
import com.biz.gbck.vo.product.event.MnsPriceVo;
import com.google.common.base.Function;
import java.math.BigDecimal;
import java.sql.Timestamp;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * 转换器 MnsPriceVo --> PriceRo
 *
 * @author zhangcheng
 * @date 2017/2/6
 * @reviewer
 * @see
 */
public class MnsPriceVo2PriceRo implements Function<MnsPriceVo, PriceRo> {
    private static Integer getPrice(String originPrice) {
        if (StringUtils.isBlank(originPrice)) {
            return null;
        }
        return new BigDecimal(originPrice).multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_UP).intValue();
    }

    @Override
    public PriceRo apply(MnsPriceVo vo) {
        if (vo != null) {
            PriceRo ro = new PriceRo();
            ro.setId(String.format("%s%s", vo.getMatnr(), vo.getArea()));
            ro.setProductCode(vo.getMatnr());
            ro.setAreaCode(vo.getArea());
            ro.setFinalPrice(getPrice(vo.getPrice()));
            ro.setMarketPrice(getPrice(vo.getPrice()));
            ro.setCostPrice(getPrice(vo.getCostprice()));
            ro.setMinPrice(getPrice(vo.getMinprice()));
            ro.setPrice1(getPrice(vo.getPrice1()));
            ro.setPrice2(getPrice(vo.getPrice2()));
            ro.setPrice3(getPrice(vo.getPrice3()));
            ro.setPrice4(getPrice(vo.getPrice4()));
            ro.setPrice5(getPrice(vo.getPrice5()));
            ro.setPrice6(getPrice(vo.getPrice6()));
            ro.setPrice7(getPrice(vo.getPrice7()));
            ro.setPrice8(getPrice(vo.getPrice8()));
            ro.setPrice9(getPrice(vo.getPrice9()));
            ro.setPrice10(getPrice(vo.getPrice10()));
            ro.setPrice11(getPrice(vo.getPrice11()));
            ro.setPrice12(getPrice(vo.getPrice12()));
            ro.setPrice13(getPrice(vo.getPrice13()));
            ro.setPrice14(getPrice(vo.getPrice14()));
            ro.setPrice15(getPrice(vo.getPrice15()));
            ro.setPrice16(getPrice(vo.getPrice16()));
            ro.setPrice17(getPrice(vo.getPrice17()));
            ro.setPrice18(getPrice(vo.getPrice18()));
            ro.setPrice19(getPrice(vo.getPrice19()));
            ro.setPrice20(getPrice(vo.getPrice20()));
            if (NumberUtils.isNumber(vo.getUpdatetime())) {
                ro.setUpdateTimestamp(new Timestamp(Long.valueOf(vo.getUpdatetime())));
            }
            return ro;
        }
        return null;
    }
}
