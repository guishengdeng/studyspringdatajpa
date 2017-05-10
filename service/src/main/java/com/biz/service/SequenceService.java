package com.biz.service;

import com.biz.gbck.dao.redis.repository.order.SequenceRedisDao;
import com.biz.gbck.enums.order.SequenceType;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Date;

/**
 * 各类code 生成
 *
 * @author lei
 * @date 2017年05月10日
 * @reviewer
 * @see
 */
@Service
public class SequenceService extends AbstractBaseService {

    private static final String DATE_FMT_PATTERN = "yyyyMMdd";

    @Autowired
    private SequenceRedisDao sequenceRedisDao;

    public String generateOrderCode() {
        return this.generate(SequenceType.ORDER);
    }

    public String generatePurchaseOrderCode() {
        return this.generate(SequenceType.PURCHASE);
    }

    public String generatePaymentCode() {
        return this.generate(SequenceType.PAYMENT);
    }

    public String generateReturnCode() {
        return this.generate(SequenceType.RETURN);
    }

    public String generateStockInCode() {
        return this.generate(SequenceType.STOCK_IN);
    }

    public String generateStockOutCode() {
        return this.generate(SequenceType.STOCK_OUT);
    }

    private String generate(SequenceType type) {
        String dateStr = DateUtils.formatDate(new Date(), DATE_FMT_PATTERN);
        Integer sequence = sequenceRedisDao.nextSequence(SequenceType.ORDER, dateStr);
        return String.format("%s%s%s", type.getPrefix(), dateStr, new DecimalFormat("0000000").format(sequence), type
                .getSuffix());
    }


}
