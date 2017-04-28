package com.biz.gbck.vo.product.promotion;

import java.io.Serializable;

/**
 * 满减规则Vo(记录满x减y)
 *
 * Created by david-liu on 2017/04/24 09:27.
 */
public class ReachCutPromotionVo implements Serializable {
    private static final long serialVersionUID = 275318391368125779L;

    /**
     * 满x
     */
    private Integer accountLimit;

    /**
     * 减y
     */
    private Integer cut;

    public Integer getAccountLimit() {
        return accountLimit;
    }

    public void setAccountLimit(Integer accountLimit) {
        this.accountLimit = accountLimit;
    }

    public Integer getCut() {
        return cut;
    }

    public void setCut(Integer cut) {
        this.cut = cut;
    }
}
