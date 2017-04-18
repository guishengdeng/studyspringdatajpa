package com.biz.gbck.vo.vendor;

import com.biz.gbck.dao.mysql.po.enums.vendor.VendorOperationType;
import java.io.Serializable;

public class VendorAndDecorationQueryVo implements Serializable {

    private static final long serialVersionUID = 8662644537037915959L;

    private String vendorName;

    private String logo;

    private String brandOperation;

    private VendorOperationType vendorOperationType;

    private boolean favoriteStatus;

    private String topImage;

    private String vendorId;


    public boolean isFavoriteStatus() {
        return favoriteStatus;
    }

    public void setFavoriteStatus(boolean favoriteStatus) {
        this.favoriteStatus = favoriteStatus;
    }

    public String getTopImage() {
        return topImage;
    }

    public void setTopImage(String topImage) {
        this.topImage = topImage;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBrandOperation() {
        return brandOperation;
    }

    public void setBrandOperation(String brandOperation) {
        this.brandOperation = brandOperation;
    }

    public VendorOperationType getVendorOperationType() {
        return vendorOperationType;
    }

    public void setVendorOperationType(VendorOperationType vendorOperationType) {
        this.vendorOperationType = vendorOperationType;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    @Override
    public String toString() {
        return "VendorAndDecorationQueryVo [vendorName=" + vendorName + ", logo=" + logo + ", brandOperation="
                + brandOperation + ", vendorOperationType=" + vendorOperationType + ", favoriteStatus=" + favoriteStatus
                + ", topImage=" + topImage + ", vendorId=" + vendorId + "]";
    }

}
