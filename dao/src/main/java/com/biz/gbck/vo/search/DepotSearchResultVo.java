package com.biz.gbck.vo.search;

import java.io.Serializable;

/**
 * 门店搜素结果
 *
 * @author Nian.Li <br>2016年9月5日
 */
public class DepotSearchResultVo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3130864416300686832L;

    /**
     * 门店ID
     */
    private Long id;

    /**
     * 距离
     */
    private Integer distance = -1;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }


}
