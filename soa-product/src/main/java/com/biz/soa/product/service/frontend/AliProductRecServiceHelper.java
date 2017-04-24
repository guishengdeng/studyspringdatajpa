package com.biz.soa.product.service.frontend;

import com.alibaba.fastjson.JSONObject;
import com.biz.core.util.JsonUtil;
import com.biz.gbck.util.AESDecode;
import com.biz.gbck.vo.product.frontend.AliRecByUserProductReqVo;
import com.biz.gbck.vo.product.frontend.AliRecRespVo;
import com.biz.service.AbstractBaseService;
import com.biz.soa.product.config.RecConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author 江南
 * @date 2017/1/20
 * @reviewer
 */
@Service
public class AliProductRecServiceHelper extends AbstractBaseService {

    @Autowired
    private RecConfig recConfig;

    public AliRecRespVo findRecommendByAli(AliRecByUserProductReqVo aliRecReqVo) {
        JSONObject param = new JSONObject();
        param.put("biz_code", aliRecReqVo.getBizCode());
        param.put("scn_code", aliRecReqVo.getScnCode());
        param.put("user_id", aliRecReqVo.getUserId());
        param.put("item_id", aliRecReqVo.getItemId());
        String url = associateGetUrl(param.toJSONString().replace(",", "&").replace(":", "=").replace("{", "").replace("}", "").replace("\"", ""));
        String result = AESDecode.sendGet(url, recConfig.getAccessKeyId(), recConfig.getAccessKeySecret());
        return JsonUtil.json2Obj(result, AliRecRespVo.class);
    }

    private String associateGetUrl(String urlParam) {
        return String.format("%s?%s", recConfig.getRecRequestUrl(), urlParam);
    }

}
