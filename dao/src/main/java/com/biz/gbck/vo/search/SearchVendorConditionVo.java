package com.biz.gbck.vo.search;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 搜索商户条件 Vo
 *
 * @author david-liu
 * @date 2017年01月06日
 * @reviewer
 * @see
 */
public class SearchVendorConditionVo implements Serializable {

    private static final long serialVersionUID = 5070815492074545162L;

    /**
     * 搜索关键字
     */
    private String keyword;

    /**
     * 页
     */
    private int page = 0;

    /**
     * 页大小
     */
    private int pageSize = 30;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
