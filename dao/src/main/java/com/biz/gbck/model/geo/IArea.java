package com.biz.gbck.model.geo;

import java.math.BigDecimal;
import java.util.List;


public interface IArea extends IPoint {

    public static final int LEVEL_REGION = 1;
    public static final int LEVEL_PROVINCE = 2;
    public static final int LEVEL_CITY = 3;
    public static final int LEVEL_DISTRICT = 4;
    public static final int LEVEL_BUSINESS = 5;

    public static final int WEIGHT_HOT = 10;

    public static final int CITYID_DEFAULT = 75;



    public Integer getId();

    public String getName();

    public Integer getIdx();

    public Integer getWeight();

    public Integer getParentId();

    public String getCode();

    public int getLevel();

    public IArea getParent();

    public List getChildren();

    public BigDecimal getLat();

    public BigDecimal getLon();



}
