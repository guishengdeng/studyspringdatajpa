package com.biz.support.es.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * 分页工具类
 */
public class PageUtil {


    // 默认第一页(spring data jpa分页0即为第一页)
    public static int DEFAULT_PAGE_NO = 0;

    // 默认分页数
    public static int DEFAULT_PAGE_SIZE = 10;

    // 每页1000条，用于初始化数据到es使用
    public static Integer PAGE_SIZE = 1000;


    /**
     * 获得分页对象
     *
     * @param currentPage 当前页
     * @param pageSize 每页记录数
     */
    public static PageRequest getPageResult(Integer currentPage, Integer pageSize) {
        if (currentPage == null) {
            currentPage = DEFAULT_PAGE_NO;
        } else if (currentPage < 0) {// 接口传递0开始，所以判断小于0
            currentPage = 0;
        } else {
            //currentPage = currentPage - 1;// 接口传递0开始，所以这里不用减1
        }

        if (pageSize == null) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        return new PageRequest(currentPage, pageSize);
    }

    /**
     * @param currentPage 当前页
     * @param pageSize 每页记录数
     * @param sort 排序
     */
    public static PageRequest getPageResult(Integer currentPage, Integer pageSize, Sort sort) {
        if (currentPage == null) {
            currentPage = DEFAULT_PAGE_NO;
        } else if (currentPage < 0) {// 接口传递0开始，所以判断小于0
            currentPage = 0;
        } else {
            //currentPage = currentPage - 1;// 接口传递0开始，所以这里不用减1
        }

        if (pageSize == null) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        return new PageRequest(currentPage, pageSize, sort);
    }

    /**
     * 获取 Es分页对象 Vo
     *
     * @param currentPage 当前页
     * @param pageSize 页大小
     */
    public static EsPage getEsPage(Integer currentPage, Integer pageSize) {
        currentPage = currentPage < 0 ? 0 : currentPage;
        return new EsPage(currentPage * pageSize, pageSize);
    }

}
