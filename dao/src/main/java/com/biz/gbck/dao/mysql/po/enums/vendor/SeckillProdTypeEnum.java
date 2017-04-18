
/**
 * 描述:用于商品详情页区分商品是否是秒杀或者其他
 * 包名:com.biz.bbc.vo.seckill
 * 版本信息: 版本1.0
 * 日期:2017年2月17日  上午10:28:23
 * Copyright 成都博智维讯信息技术股份有限公司
 */

package com.biz.gbck.dao.mysql.po.enums.vendor;


/**
 * @describe：用于商品详情页区分商品是否是秒杀或者其他
 * @author: lvmoney /成都博智维讯信息技术股份有限公司
 * @version:v1.0
 * 2017年2月17日 上午10:28:23
 */

public enum SeckillProdTypeEnum {
    SECKILL("seckill"),
    OTHERS("others"),;
    private final String title;

    SeckillProdTypeEnum(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
