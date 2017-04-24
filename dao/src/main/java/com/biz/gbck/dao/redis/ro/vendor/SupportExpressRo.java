package com.biz.gbck.dao.redis.ro.vendor;

import com.biz.core.util.DateUtil;
import com.biz.redis.annotation.Ro;
import com.biz.redis.annotation.RoSortedSet;
import com.biz.redis.bean.BaseRedisObject;
import java.sql.Timestamp;

/**
 * @author mounan
 * @Description: 快递公司ro
 * @time:2017年1月4日 下午2:45:50
 */
@Ro(key = "ro:SupportExpressRo")
@RoSortedSet(key = "list", score = "createTimestamp")
public class SupportExpressRo extends BaseRedisObject<Long> implements Comparable<SupportExpressRo> {

    private String name;

    // 物流公司代码
    private String code;

    // 快递100物流公司代码
    private String fastCode;

    // 排序
    private Long idx;

    // 公司网址
    private String webSite;

    // 询件网址
    private String queryExpressWebSite;

    private Timestamp createTimestamp = DateUtil.now();

    @Override
    public int compareTo(SupportExpressRo o) {
        return -this.createTimestamp.compareTo(o.getCreateTimestamp());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFastCode() {
        return fastCode;
    }

    public void setFastCode(String fastCode) {
        this.fastCode = fastCode;
    }

    public Long getIdx() {
        return idx;
    }

    public void setIdx(Long idx) {
        this.idx = idx;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getQueryExpressWebSite() {
        return queryExpressWebSite;
    }

    public void setQueryExpressWebSite(String queryExpressWebSite) {
        this.queryExpressWebSite = queryExpressWebSite;
    }

}
