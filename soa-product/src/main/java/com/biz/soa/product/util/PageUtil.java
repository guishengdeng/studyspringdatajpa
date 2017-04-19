package com.biz.soa.product.util;

import com.biz.gbck.vo.PageVo;
import java.io.Serializable;
import org.codelogger.utils.ValueUtils;

import static org.springframework.beans.support.PagedListHolder.DEFAULT_PAGE_SIZE;


/**
 * 分页工具类
 *
 * @author david-liu
 * @date 2017年02月17日
 * @reviewer
 */
public class PageUtil implements Serializable {
    private static final long serialVersionUID = 8555885033440847165L;

    /**
     * 获取分页Vo
     *
     * @param currentPage 当前页
     * @param totalElementsCount 总共记录条数
     * @param pageSize 页大小
     * @return 分页Vo
     */
    public static PageVo getPage(int currentPage, int totalElementsCount, int pageSize) {
        Integer startPageIndex = 0;
        Integer endPageIndex = 0;
        Integer startElementIndex = null;
        Integer endElementIndex = null;
        pageSize = ValueUtils.getValue(pageSize) == 0 ? DEFAULT_PAGE_SIZE : pageSize;
        if (ValueUtils.getValue(totalElementsCount) > 0) {
            startElementIndex = 0;
            endElementIndex = 0;
            if (ValueUtils.getValue(totalElementsCount / pageSize) == 0) {
                endPageIndex = startPageIndex;
                if (totalElementsCount - 1 >= 0) {
                    endElementIndex = totalElementsCount - 1;
                }
            } else {
                if (ValueUtils.getValue(totalElementsCount % pageSize) > 0) {
                    endPageIndex = totalElementsCount / pageSize;
                } else {
                    endPageIndex = totalElementsCount / pageSize - 1;
                }

                if ((currentPage + 1) * pageSize > totalElementsCount) {
                    if (currentPage <= endPageIndex) {
                        endElementIndex = totalElementsCount - 1;
                    } else {
                        startElementIndex = null;
                        endElementIndex = null;
                    }
                } else {
                    endElementIndex = (currentPage + 1) * pageSize - 1;
                }
            }
        }
        return new PageVo(startPageIndex, endPageIndex, currentPage, totalElementsCount, pageSize, startElementIndex, endElementIndex);
    }

    public static void main(String[] args) {
        PageVo pageVo = PageUtil.getPage(0, 12, 16);
        System.out.println(pageVo);
    }

}
