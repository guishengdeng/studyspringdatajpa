package com.biz.core.algorithm.kmeans;

import java.io.Serializable;
import org.apache.commons.lang3.StringUtils;

/**
 * KMeans 区间 Vo
 *
 * @author david-liu
 * @date 2017年01月11日
 * @reviewer
 * @see
 */
public class KMeansRangeVo implements Serializable {

    private static final long serialVersionUID = 3036681723149460194L;

    /**
     * 字段
     */
    private String field;

    /**
     * 标题
     */
    private String title;

    public KMeansRangeVo(Integer min, Integer max, String titleTemplate) {

        this.field = max == Integer.MAX_VALUE - 1 ? String.format("%d_", min * 100) : String.format("%d_%d", min * 100, max * 100);
        if (StringUtils.isNotBlank(titleTemplate)) {
            this.title = max == Integer.MAX_VALUE - 1 ? String.format("%d以上", min) : String.format(titleTemplate, min, max);
        }
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
