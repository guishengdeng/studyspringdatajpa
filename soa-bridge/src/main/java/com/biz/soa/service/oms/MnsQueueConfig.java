package com.biz.soa.service.oms;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * mns队列名称配置
 * Created by lei on 9/18/16.
 */
@Component
public class MnsQueueConfig {

    /**
     * Oms增量库存队列
     */
    @Value("${mns.queue.stockChange}")
    public String mnsStockChangeQueue;

    /**
     * Oms全量库存队列
     */
    @Value("${mns.queue.stockAll}")
    public String mnsStockAllQueue;

    /**
     * Oms全量价格队列
     */
    @Value("${mns.queue.priceAll}")
    public String mnsPriceAllQueue;

    /**
     * OMS省仓全量库存队列
     */
    @Value("${mns.queue.provinceStockAll}")
    public String mnsProvinceStockAllQueue;

    /**
     * Oms门店主数据信息队列
     */
    @Value("${mns.queue.depot}")
    public String mnsDepotQueue;

    /**
     * Oms组合商品主数据信息队列
     */
    @Value("${mns.queue.mixProduct}")
    public String mnsMixProductQueue;

    /**
     * 中台GEO数据省
     */
    @Value("${mns.queue.geo.province}")
    public String mnsGeoProvince;

    /**
     * 中台GEO数据市
     */
    @Value("${mns.queue.geo.city}")
    public String mnsGeoCity;

    /**
     * 中台GEO数据区
     */
    @Value("${mns.queue.geo.district}")
    public String mnsGeoDistrict;

    /**
     * Oms会员信息变动队列
     */
    @Value("${mns.queue.memberChange}")
    public String mnsMemberChangeQueue;

    /**
     * Oms门店员工信息队列
     */
    @Value("${mns.queue.employee}")
    public String mnsEmployeeQueue;

    /**
     * oms商品信息队列
     */
    @Value("${mns.queue.product}")
    public String mnsProductQueue;

    /**
     * oms省仓信息队列
     */
    @Value("${mns.queue.warehouse}")
    public String mnsWarehouseQueue;

    /**
     * oms价格信息队列
     */
    @Value("${mns.queue.price}")
    public String mnsPriceQueue;

    /**
     * oms门店促销增量队列
     */
    @Value("${mns.queue.depotPromotionIncrement}")
    public String mnsDepotPromotionIncrementQueue;

    /**
     * oms门店促销全量队列
     */
    @Value("${mns.queue.depotPromotionAll}")
    public String mnsDepotPromotionAllQueue;

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
