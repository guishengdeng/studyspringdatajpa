package com.biz.service.mns;

import com.biz.gbck.enums.oss.OssType;
import com.biz.gbck.vo.product.event.MnsPriceVo;
import java.util.List;

/**
 * 价格有关的服务
 *
 * @author zhangcheng
 * @date 2017/1/11
 * @reviewer
 * @see
 */
public interface MnsPriceService {

    /**
     * 转换中台增量价格
     *
     * @author zhangcheng
     */
    void trans(MnsPriceVo mnsPriceVo);

    /**
     * 全量价格操作前置接口
     *
     * @author zhangcheng
     */
    void preHandleFullPrice(OssType type);

    /**
     * 全量价格操作后置接口
     *
     * @author zhangcheng
     */
    void afterHandleFullPrice(OssType type);

    /**
     * 保存全量价格
     *
     * @author zhangcheng
     */
    void saveFullPrice(List<MnsPriceVo> mnsPriceVos);
}
