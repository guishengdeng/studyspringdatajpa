package com.biz.gbck.dao.mysql.po.info;

import com.biz.core.util.DateUtil;
import com.biz.gbck.dao.mysql.po.salearea.SaleAreaPo;
import com.biz.gbck.dao.mysql.po.user.ShopTypePo;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 用户消息
 *
 * @author gongshutao
 */
@SuppressWarnings("serial") @Entity @Table(name = "info_notice") public class NoticePo
    implements Serializable {

    @Id private Long id;

    /**
     * 消息标题
     */
    @Column(length = 255, nullable = false) private String title;

    /**
     * 点击消息跳转地址
     */
    @Column(length = 255) private String url;

    /**
     * 消息内容
     */
    @Column(columnDefinition = "MEDIUMTEXT") private String content;

    /**
     * 创建人
     */
    @Column(length = 50) private String adminId;

    /**
     * 创建日期
     */
    @Column(nullable = false) private Timestamp createTime = DateUtil.now();


    /**
     * 销售区域
     */
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "saleAreaId") private SaleAreaPo saleArea;

    /**
     * 店铺类型
     */
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "shopTypeId") private ShopTypePo shopType;

//    /**
//     * 用户
//     */
//    @ManyToMany @JoinTable(name = "info_notice_user", joinColumns = {
//            @JoinColumn(name = "typeId", referencedColumnName = "id")}, inverseJoinColumns = {
//            @JoinColumn(name = "userId", referencedColumnName = "id")}) private Set<UserPo> users;
//
//    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

	public SaleAreaPo getSaleArea() {
		return saleArea;
	}

	public void setSaleArea(SaleAreaPo saleArea) {
		this.saleArea = saleArea;
	}

	public ShopTypePo getShopType() {
		return shopType;
	}

	public void setShopType(ShopTypePo shopType) {
		this.shopType = shopType;
	}
//
//	public Set<UserPo> getUsers() {
//		return users;
//	}
//
//	public void setUsers(Set<UserPo> users) {
//		this.users = users;
//	}


}
