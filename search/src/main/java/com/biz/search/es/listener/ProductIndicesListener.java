package com.biz.search.es.listener;

import com.biz.gbck.vo.product.UpdateProductIdxVO;
import com.biz.message.BizMessage;
import com.biz.message.MessageListener;
import com.biz.message.QueueDefinition;
import com.biz.message.queue.BizBaseQueue;
import com.biz.search.es.service.interfaces.ProductSearchService;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by david-liu on 2017/05/16 00:04.
 */
@Component
public class ProductIndicesListener implements MessageListener<UpdateProductIdxVO> {

    @Autowired
    private ProductSearchService productSearchService;

    @Override
    public QueueDefinition getListenedQueue() {
        return BizBaseQueue.MQ_SEARCH_PRODUCT_QUEUE;
    }

    @Override
    public void onMessage(BizMessage<UpdateProductIdxVO> message) {
        UpdateProductIdxVO VO = message.getPayload();
        if (Objects.isNull(VO.getProductId())) {
            productSearchService.updateTotalIndices(VO);
        } else {
            productSearchService.updateIncrIndices(VO);
        }
    }
}
