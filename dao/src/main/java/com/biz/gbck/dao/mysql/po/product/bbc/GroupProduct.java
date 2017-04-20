package com.biz.gbck.dao.mysql.po.product.bbc;

import com.biz.support.jpa.po.BaseEntity;
import com.google.common.collect.Lists;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 组合商品(主商品)
 *
 * @author david-liu
 * @date 2017年01月23日
 * @reviewer
 */
@Entity
@Table(name = "pro_group_product")
public class GroupProduct extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 8533122359266330668L;

    /**
     * 组合商品主商品
     */
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    /**
     * 组合商品商品项
     */
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GroupProductItem> itemList;

    private transient boolean hasChanged;

    public boolean isHasChanged() {
        return hasChanged;
    }

    public void setHasChanged(boolean hasChanged) {
        this.hasChanged = hasChanged;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<GroupProductItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<GroupProductItem> itemList) {
        this.itemList = itemList;
    }

    public boolean containsItem(String productCode) {
        List<GroupProductItem> productItems = this.itemList;
        if (CollectionUtils.isEmpty(productItems)) {
            return false;
        }
        for (GroupProductItem item : productItems) {
            if (item == null || item.getProduct() == null) {
                return false;
            }

            if (StringUtils.equalsIgnoreCase(productCode, item.getProduct().getProductCode())) {
                return true;
            }
        }
        return false;
    }

    public GroupProductItem getItem(String productCode) {
        if (StringUtils.isBlank(productCode) || CollectionUtils.isEmpty(this.itemList)) {
            return null;
        }

        for (GroupProductItem item : this.itemList) {
            if (StringUtils.equalsIgnoreCase(item.getProduct().getProductCode(), productCode)) {
                return item;
            }
        }
        return null;
    }

    public void addItem(GroupProductItem groupProductItem) {
        if (CollectionUtils.isEmpty(this.itemList)) {
            this.itemList = Lists.newArrayList();
        }
        this.itemList.add(groupProductItem);
        this.hasChanged = true;
    }

    @Override
    public String toString() {
        return "GroupProduct{" +
                "product=" + product +
                ", itemList=" + itemList +
                ", hasChanged=" + hasChanged +
                '}';
    }
}
