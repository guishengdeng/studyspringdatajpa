package com.biz.soa.product.service.frontend;

import com.biz.gbck.vo.product.frontend.AliRecByUserProductReqVo;
import com.biz.gbck.vo.product.frontend.AliRecRespVo;
import com.biz.gbck.vo.product.frontend.ProductRecItemsVo;
import com.biz.service.AbstractBaseService;
import com.biz.service.product.frontend.ProductRecommendService;
import com.biz.soa.product.config.RecConfig;
import com.google.common.collect.Lists;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 商品推荐Service实现
 *
 * @author 江南
 * @date 2017/1/20
 * @reviewer
 */
//@Service
public class ProductRecommendServiceImpl extends AbstractBaseService implements ProductRecommendService {

    @Autowired
    private AliProductRecServiceHelper aliProductRecServiceHelper;

    @Autowired
    private RecConfig recConfig;

    @Override
    public ProductRecItemsVo getHomePageProductRec(String userId) {
        if (userId == null) {
            userId = "";
        }
        //        todo:jiangnan 初始使用快喝的推荐数据
        //        AliRecByUserProductReqVo reqVo = new AliRecByUserProductReqVo(RecConfig.scn_code.home.name(), userId, null);
        AliRecByUserProductReqVo reqVo = new AliRecByUserProductReqVo(recConfig.getAccessKeyId(), recConfig.getAccessKeySecret(),
                recConfig.getBizCode(), RecConfig.scn_code.main_rec.name(), userId, null);
        AliRecRespVo recommendByAli = aliProductRecServiceHelper.findRecommendByAli(reqVo);
        return new ProductRecItemsVo(recommendByAli.getData().getTrace_id(), this.parseRecIds(recommendByAli.getData().getRec()));
    }

    @Override
    public ProductRecItemsVo getVendorHomeProductRec(String userId) {
        return null;
    }

    @Override
    public ProductRecItemsVo getProductDetailRec(String userId, String itemId) {
        //        todo:jiangnan
        //        AliRecByUserProductReqVo reqVo = new AliRecByUserProductReqVo(RecConfig.scn_code.detail.name(), userId, itemId);
        if (userId == null) {
            userId = "";
        }
        if (itemId == null) {
            itemId = "";
        }
        AliRecByUserProductReqVo reqVo = new AliRecByUserProductReqVo(recConfig.getAccessKeyId(), recConfig.getAccessKeySecret(),
                recConfig.getBizCode(), RecConfig.scn_code.productRec.name(), userId, itemId);
        AliRecRespVo recommendByAli = aliProductRecServiceHelper.findRecommendByAli(reqVo);
        return new ProductRecItemsVo(recommendByAli.getData().getTrace_id(), this.parseRecIds(recommendByAli.getData().getRec()));
    }

    private List<String> parseRecIds(List<List<String>> rec) {
        List<String> centerIds = Lists.newArrayList();
        for (List<String> stringList : rec) {
            centerIds.add(stringList.get(0));
        }
        return centerIds;
    }
}
