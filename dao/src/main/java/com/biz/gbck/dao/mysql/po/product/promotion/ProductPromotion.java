package com.biz.gbck.dao.mysql.po.product.promotion;

import com.biz.gbck.enums.product.PromotionRoundEnum;
import com.biz.support.jpa.converter.ListLongConverter;
import com.biz.support.jpa.po.BaseEntity;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.*;

/**
 * 商品促销
 *
 * Created by david-liu on 2017/04/21 14:08.
 */
@MappedSuperclass
public class ProductPromotion extends BaseEntity {
    private static final long serialVersionUID = 8000046744483870135L;

    /**
     * 公司ID
     */
    @Column
    protected Long companyId;

    /**
     * 促销名称
     */
    @Column(length = 50)
    protected String name;

    /**
     * 促销开始时间
     */
    @Column(nullable = false)
    protected Timestamp startDate;

    /**
     * 促销结束时间
     */
    @Column(nullable = false)
    protected Timestamp endDate;

    /**
     * 促销循环方式
     */
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    protected PromotionRoundEnum promotionRound;

    /**
     * 活动开始时间(可选, 如果设置了代表当天活动生效的开始时间)
     */
    @Column
    protected Time effectStartTime;

    /**
     * 活动结束时间(可选, 如果设置了代表当天活动生效的结束时间)
     */
    @Column
    protected Time effectEndTime;

    /**
     * 面向的下级采购单位
     */
    @Column(columnDefinition = "TEXT")
    @Convert(converter = ListLongConverter.class)
    protected List<Long> orientedCompany;

    /**
     * 排除的下级采购单位
     */
    @Column(columnDefinition = "TEXT")
    @Convert(converter = ListLongConverter.class)
    protected List<Long> excludeCompany;

    /**
     * 是否支持优惠券
     */
    @Column
    protected Boolean allowVoucher = Boolean.FALSE;

    /**
     * 是否互斥
     */
    @Column
    protected Boolean isExclusive = Boolean.FALSE;
}
