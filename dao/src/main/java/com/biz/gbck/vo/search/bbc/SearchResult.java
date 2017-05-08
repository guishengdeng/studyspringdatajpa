package com.biz.gbck.vo.search.bbc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 搜索结果
 *
 * @author Nian.Li
 * @ClassName: SearchResult 
 * @Description: TODO
 * @date 2016年8月23日 下午11:32:27   
 */
public class SearchResult<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7985693134496004467L;

    /** 结果集 */
    private List<T> items = new ArrayList<>();

    /** 总数 */
    private Long totalNum = 0L;

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
