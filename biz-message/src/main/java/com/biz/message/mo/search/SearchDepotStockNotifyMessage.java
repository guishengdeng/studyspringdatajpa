package com.biz.message.mo.search;

import java.io.Serializable;
import java.util.List;

/**
 * 门店商品增量库存改变通知搜索建立索引消息对象
 *
 * @author jun.liu(by xiaoyu)
 * @date 2016年9月2日
 * @reviewer
 */
public class SearchDepotStockNotifyMessage implements Serializable {

    private static final long serialVersionUID = 4529777362829502393L;

    private Long depotId;

    private List<Long> productIds;

    public Long getDepotId() {
        return depotId;
    }

    public void setDepotId(Long depotId) {
        this.depotId = depotId;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

}
