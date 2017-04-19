package com.biz.gbck.vo.product.frontend;

import java.io.Serializable;

/**
 * 阿里云推荐引擎请求Vo
 *
 * @author david-liu
 * @date 2016年10月10日
 * @reviewer
 * @see
 */
public class AliRecByUserProductReqVo extends AliRecReqVo implements Serializable {

    private static final long serialVersionUID = 1929623279399641478L;

    private String userId;

    private String itemId;

    public AliRecByUserProductReqVo(String accessKeyID, String accessKeySecret, String bizCode, String scnCode, String userId, String itemId) {
        super(accessKeyID, accessKeySecret, bizCode, scnCode);
        this.userId = userId;
        this.itemId = itemId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
