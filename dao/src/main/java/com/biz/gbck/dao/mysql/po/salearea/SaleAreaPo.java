package com.biz.gbck.dao.mysql.po.salearea;

import com.biz.gbck.dao.mysql.po.user.ShopPo;
import com.biz.gbck.enums.CommonStatusEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * 销售区域
 * @author gongshutao
 *
 */
@SuppressWarnings("serial")
@Entity @Table(name = "pro_salearea")
public class SaleAreaPo implements Serializable {
  
  
  @Id @GeneratedValue(strategy = GenerationType.AUTO) private Integer id;
  
  /**
   * 名称
   */
  @Column(length = 50, nullable = false) private String name;
  
  /**
   * 状态(0：删除,1:正常)
   */
  @Column(nullable = false) private Integer status = CommonStatusEnum.ENABLE.getValue();

  /**
   * 备注
   */
  @Column(length = 255) private String description;

  @ManyToMany(fetch = FetchType.LAZY) @JoinTable(name = "shop_salearea",
      joinColumns = {@JoinColumn(name = "salearea_id", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "shop_id", referencedColumnName = "id")},
      uniqueConstraints = {@UniqueConstraint(columnNames = {"salearea_id", "shop_id"})})
  private Set<ShopPo> shops;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }



  public Set<ShopPo> getShops() {
    return shops;
  }

  public void setShops(Set<ShopPo> shops) {
    this.shops = shops;
  }
}
