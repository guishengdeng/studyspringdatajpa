package com.biz.manage.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.biz.gbck.enums.order.PaymentType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.String.format;

public class PaymentTypeTag extends TagSupport {

    private static final long serialVersionUID = -2123253186591293149L;

    private static PaymentType[] editAblePaymentTypes;{
        ArrayList<PaymentType> paymentTypeArrayList = newArrayList(PaymentType.values());
        paymentTypeArrayList.remove(PaymentType.ALIPAY);
        paymentTypeArrayList.remove(PaymentType.WECHAT);
        editAblePaymentTypes =
            paymentTypeArrayList.toArray(new PaymentType[paymentTypeArrayList.size()]);
    }

    private Object supportPaymentIds;

    private Object excludePaymentIds;

    @Override 
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            for (PaymentType data : editAblePaymentTypes) {

                if(!excludePaymentIdsContains(String.valueOf(data.getValue()))) {
                    if (contains(String.valueOf(data.getValue()))) {
                        out.print(
                            format("<option value=\"%s\" selected >%s</option>", data.getValue(),
                                data.getDesc()));
                    } else {
                        out.print(format("<option value=\"%s\" >%s</option>", data.getValue(), data.getDesc()));
                    }
                }
            }
            return SKIP_BODY;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.doStartTag();
    }

    private Boolean contains(String dataId) {
        if (supportPaymentIds instanceof Collection) {
            @SuppressWarnings("rawtypes")
			Collection selectedPriceTags = (Collection) this.supportPaymentIds;
            for (Object tagId : selectedPriceTags) {
                if (equals(tagId, dataId))
                    return true;
            }
        } else if (supportPaymentIds instanceof String) {
            String[] selectedPriceTags = ((String) this.supportPaymentIds).split("\\D");
            for (String tagId : selectedPriceTags) {
                if (equals(tagId, dataId))
                    return true;
            }
        }
        return false;
    }

    private Boolean excludePaymentIdsContains(String dataId) {
        if (excludePaymentIds instanceof Collection) {
            @SuppressWarnings("rawtypes")
			Collection selectedPriceTags = (Collection) this.excludePaymentIds;
            for (Object tagId : selectedPriceTags) {
                if (equals(tagId, dataId))
                    return true;
            }
        } else if (excludePaymentIds instanceof String) {
            String[] selectedPriceTags = ((String) this.excludePaymentIds).split("\\D");
            for (String tagId : selectedPriceTags) {
                if (equals(tagId, dataId))
                    return true;
            }
        }
        return false;
    }

    private Boolean equals(Object selectData, String dataId) {
        return Objects.equals(String.valueOf(selectData), dataId);
    }

    public void setSupportPaymentIds(Object supportPaymentIds) {
        this.supportPaymentIds = supportPaymentIds;
    }

    public void setExcludePaymentIds(Object excludePaymentIds) {
        this.excludePaymentIds = excludePaymentIds;
    }
}