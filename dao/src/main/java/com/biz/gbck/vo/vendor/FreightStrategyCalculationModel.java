package com.biz.gbck.vo.vendor;

/**
 * @author yanweijin
 * @date 2017/1/24
 */
public interface FreightStrategyCalculationModel {
    Integer getFirstWeight();

    Integer getNextWeight();

    Integer getFirstPrice();

    Integer getNextPrice();

    Integer getFreeIfExceedPrice();
}
