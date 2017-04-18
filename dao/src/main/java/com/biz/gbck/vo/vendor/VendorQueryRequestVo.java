package com.biz.gbck.vo.vendor;

import com.biz.gbck.dao.mysql.po.enums.vendor.VendorStatus;
import com.biz.gbck.enums.product.VendorTypeEnum;
import com.biz.gbck.vo.PageableRequestVo;

/**
 * @author yanweijin
 * @date 2016/12/19
 */
public class VendorQueryRequestVo extends PageableRequestVo {

    private static final long serialVersionUID = 3174032017108854622L;

    /**
     * 当前页大小
     */
    private int pageSize = 20;

    /**
     * 当前第几页
     */
    private int pageIndex = 1;

    /**
     * 搜索条件
     */
    private String searchKey;


    private String searchValue;


    private VendorTypeEnum vendorType;

    private String vendorName;

    private VendorStatus vendorStatus;

    private String vendorCode;


    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public VendorTypeEnum getVendorType() {
        return vendorType;
    }

    public void setVendorType(VendorTypeEnum vendorType) {
        this.vendorType = vendorType;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public VendorStatus getVendorStatus() {
        return vendorStatus;
    }

    public void setVendorStatus(VendorStatus vendorStatus) {
        this.vendorStatus = vendorStatus;
    }


}
