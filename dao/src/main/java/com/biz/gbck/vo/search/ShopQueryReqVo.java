package com.biz.gbck.vo.search;


import java.util.List;

/**
 * Created by dylan on 16/4/6.
 */
public class ShopQueryReqVo {

    public static final int DEFAULT_PAGESIZE = 50;

    public static final int DEFAULT_PAGE = 1;

    int page = DEFAULT_PAGE;

    int pageSize = DEFAULT_PAGESIZE;


    private Integer detailAuditStatus;  //详情审核状态
    private String mobile;               //手机号
    private Long id;                     //id
    private Integer provinceId;          //省
    private Integer cityId;               //市
    private Integer districtId;          //县
    private List<Integer> status;        //商户状态
    private List<Integer> shopTypes;     //商户类型
    private List<Integer> priceTags;     //智选价格
    private List<Integer> businessTags;  //智选类型
    private List<Integer> saleAreas;     //销售区域
    private String businessLicenceId;   //营业执照ID

    String depotId;
    Integer depotType;
    Long companyId;

    public String getBusinessLicenceId() {
        return businessLicenceId;
    }

    public void setBusinessLicenceId(String businessLicenceId) {
        this.businessLicenceId = businessLicenceId;
    }

    public Integer getDetailAuditStatus() {
        return detailAuditStatus;
    }

    public void setDetailAuditStatus(Integer detailAuditStatus) {
        this.detailAuditStatus = detailAuditStatus;
    }

    public List<Integer> getSaleAreas() {
        return saleAreas;
    }

    public void setSaleAreas(List<Integer> saleAreas) {
        this.saleAreas = saleAreas;
    }

    public static int getDefaultPagesize() {
        return DEFAULT_PAGESIZE;
    }

    public static int getDefaultPage() {
        return DEFAULT_PAGE;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public List<Integer> getStatus() {
        return status;
    }

    public void setStatus(List<Integer> status) {
        this.status = status;
    }

    public List<Integer> getShopTypes() {
        return shopTypes;
    }

    public void setShopTypes(List<Integer> shopTypes) {
        this.shopTypes = shopTypes;
    }

    public List<Integer> getPriceTags() {
        return priceTags;
    }

    public void setPriceTags(List<Integer> priceTags) {
        this.priceTags = priceTags;
    }

    public List<Integer> getBusinessTags() {
        return businessTags;
    }

    public void setBusinessTags(List<Integer> businessTags) {
        this.businessTags = businessTags;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page > 0 ? page : DEFAULT_PAGE;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {

        this.pageSize = pageSize > 0 ? pageSize : DEFAULT_PAGESIZE;
    }

    public String getDepotId() {
        return depotId;
    }

    public void setDepotId(String depotId) {
        this.depotId = depotId;
    }

    public Integer getDepotType() {
        return depotType;
    }

    public void setDepotType(Integer depotType) {
        this.depotType = depotType;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
