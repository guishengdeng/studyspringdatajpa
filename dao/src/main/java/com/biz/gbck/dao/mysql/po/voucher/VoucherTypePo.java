package com.biz.gbck.dao.mysql.po.voucher;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.collections.CollectionUtils;
import org.codelogger.utils.StringUtils;

import com.biz.gbck.dao.mysql.po.org.ShopTypePo;
import com.biz.gbck.dao.mysql.po.product.master.Product;
import com.biz.gbck.dao.mysql.po.product.meta.Category;
import com.biz.gbck.dao.mysql.po.salearea.SaleAreaPo;
import com.biz.gbck.dao.redis.ro.voucher.VoucherTypeRo;
import com.biz.gbck.enums.order.PaymentType;
import com.biz.support.jpa.po.BaseEntity;

@Entity
@Table(name = "vou_type")
public class VoucherTypePo extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1522039863476216141L;

	@Column(length = 50, nullable = false)
	private String name;

	/**
	 * 面值 单位 ：分
	 */
	@Column(nullable = false)
	private int faceValue;

	/**
	 * 订单限额（使用优惠券需达到订单限额） 面值 单位：分
	 */
	@Column(nullable = true)
	private Integer paymentLimit;

	/**
	 * 过期类型
	 */
	@Column(columnDefinition = "TINYINT", nullable = false)
	@Convert(converter = VoucherExpireType.VoucherExpireTypeConverter.class)
	private VoucherExpireType voucherExpireType;

	/**
	 * 有效期 单位：天
	 */
	@Column(nullable = false)
	private int periodDays;

	/**
	 * 池中剩余优惠券阀值 少于此值后 发邮件通知
	 */
	@Column(nullable = false)
	private int minCountInPool;

	/**
	 * 接受通知的人的email
	 */
	@Column(length = 50)
	private String mailTo;

	/**
	 * 发放人
	 */
	@Column(length = 50, nullable = true)
	private String issuerName;

	/**
	 * 使用说明
	 */
	@Column(length = 255)
	private String description;

	/**
	 * 优惠券发放数量
	 */
	@Column
	private Integer issueCount = 0;

	/**
	 * 发放开始时间（不到日期不能发放或者被领取）
	 */
	@Column
	private Timestamp startTime;

	/**
	 * 发放过期时间（超过日期不能发放或者被领取）
	 */
	@Column
	private Timestamp expireTime;

	/**
	 * 优惠券使用特点(使用互斥/可叠加使用)
	 */
	@Column(columnDefinition = "TINYINT", nullable = false)
	@Convert(converter = VoucherTypeStatus.VoucherTypeStatusConverter.class)
	private VoucherTypeStatus typeStatus;

	/**
	 * 优惠券互斥优先级
	 */
	@Column(nullable = false)
	private int mutexPriority;

	/**
	 * 用户领取类型(用户可以领一次/领多次)
	 */
	@Column(nullable = false)
	private int fetchType;

	/**
	 * 优惠券状态(下单使用/用户选择使用)
	 */
	@Column(nullable = false)
	private int useStatus;

	/**
	 * 优惠券绑定支付方式(在线支付, 货到付款使用) 多个支付方式采用逗号分割
	 */
	@Column(nullable = false)
	private String paymentType;

	/**
	 * 删除时间
	 */
	private Timestamp deletedAt;

	/**
	 * 限制类型
	 */
	@Column(columnDefinition = "TINYINT", nullable = false)
	@Convert(converter = VoucherLimitType.VoucherLimitTypeConverter.class)
	private VoucherLimitType voucherLimitType;

	/**
	 * 优惠券适用的分类
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId")
	private Category category;

	/**
	 * 优惠券适用的商品Id
	 */
	@ManyToMany
	@JoinTable(name = "vou_type_product", joinColumns = {@JoinColumn(name = "typeId", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "productId", referencedColumnName = "id")})
	private Set<Product> products;

	/**
	 * 优惠券适用的销售区域
	 */
	@ManyToMany
	@JoinTable(name = "vou_type_salearea", joinColumns = {@JoinColumn(name = "typeId", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "saleAreaId", referencedColumnName = "id")})
	private Set<SaleAreaPo> saleAreas;

	/**
	 * 优惠券适用的店铺类型
	 */
	@ManyToMany
	@JoinTable(name = "vou_type_shoptype", joinColumns = {@JoinColumn(name = "typeId", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "shopTypeId", referencedColumnName = "id")})
	private Set<ShopTypePo> shopTypes;

	public VoucherTypePo() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFaceValue() {
		return faceValue;
	}

	public void setFaceValue(int faceValue) {
		this.faceValue = faceValue;
	}

		public VoucherExpireType getVoucherExpireType() {
				return voucherExpireType;
		}

		public void setVoucherExpireType(VoucherExpireType voucherExpireType) {
				this.voucherExpireType = voucherExpireType;
		}

		public Integer getPaymentLimit() {
		return paymentLimit;
	}

	public void setPaymentLimit(Integer paymentLimit) {
		this.paymentLimit = paymentLimit;
	}

	public int getPeriodDays() {
		return periodDays;
	}

	public void setPeriodDays(int periodDays) {
		this.periodDays = periodDays;
	}

	public int getMinCountInPool() {
		return minCountInPool;
	}

	public void setMinCountInPool(int minCountInPool) {
		this.minCountInPool = minCountInPool;
	}

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	public String getIssuerName() {
		return issuerName;
	}

	public void setIssuerName(String issuerName) {
		this.issuerName = issuerName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getIssueCount() {
		return issueCount;
	}

	public void setIssueCount(Integer issueCount) {
		this.issueCount = issueCount;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Timestamp expireTime) {
		this.expireTime = expireTime;
	}

		public VoucherTypeStatus getTypeStatus() {
				return typeStatus;
		}

		public void setTypeStatus(VoucherTypeStatus typeStatus) {
				this.typeStatus = typeStatus;
		}

		public int getMutexPriority() {
		return mutexPriority;
	}

	public void setMutexPriority(int mutexPriority) {
		this.mutexPriority = mutexPriority;
	}

	public int getFetchType() {
		return fetchType;
	}

	public void setFetchType(int fetchType) {
		this.fetchType = fetchType;
	}

	public int getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(int useStatus) {
		this.useStatus = useStatus;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		if (StringUtils.isBlank(paymentType)) {
			this.paymentType = PaymentType.PAY_ON_DELIVERY+","+PaymentType.ALIPAY + "," + PaymentType.WECHAT;
		} else {
			this.paymentType = paymentType;
		}
	}

	public Timestamp getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(Timestamp deletedAt) {
		this.deletedAt = deletedAt;
	}

	public Set<ShopTypePo> getShopTypes() {
		return shopTypes;
	}

	public void setShopTypes(Set<ShopTypePo> shopTypes) {
		this.shopTypes = shopTypes;
	}

		public VoucherLimitType getVoucherLimitType() {
				return voucherLimitType;
		}

		public void setVoucherLimitType(VoucherLimitType voucherLimitType) {
				this.voucherLimitType = voucherLimitType;
		}

		public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Set<SaleAreaPo> getSaleAreas() {
		return saleAreas;
	}

	public void setSaleAreas(Set<SaleAreaPo> saleAreas) {
		this.saleAreas = saleAreas;
	}

	public VoucherTypeRo voucherTypePo2Ro() {
		VoucherTypeRo voucherTypeRo = new VoucherTypeRo();
		voucherTypeRo.setId(this.getId());
		voucherTypeRo.setName(this.getName());
		voucherTypeRo.setPaymentLimit(this.getPaymentLimit());
		voucherTypeRo.setTypeStatus(this.getTypeStatus());
		voucherTypeRo.setFetchType(this.getFetchType());
		voucherTypeRo.setUseStatus(this.getUseStatus());
		voucherTypeRo.setFaceValue(this.getFaceValue());
		voucherTypeRo.setPeriodDays(this.getPeriodDays());
		voucherTypeRo.setDescription(this.getDescription());
		voucherTypeRo.setStartTime(this.getStartTime().getTime());
		voucherTypeRo.setExpireTime(this.getExpireTime().getTime());
		voucherTypeRo.setVoucherExpireType(voucherExpireType);
		voucherTypeRo.setVoucherLimitType(voucherLimitType);

		voucherTypeRo.setIssueCount(this.getIssueCount());
		voucherTypeRo.setPaymentType(this.getPaymentType());

		if (CollectionUtils.isNotEmpty(shopTypes)) {
			StringBuffer sb = new StringBuffer();
			sb.append(",");
			for (ShopTypePo shopType : shopTypes) {
				sb.append(shopType.getId()).append(",");
			}
			voucherTypeRo.setShopTypeIds(sb.toString());
		} else {
			voucherTypeRo.setShopTypeIds("");
		}

		if (CollectionUtils.isNotEmpty(saleAreas)) {
			StringBuffer sb = new StringBuffer();
			sb.append(",");
			for (SaleAreaPo saleArea : saleAreas) {
				sb.append(saleArea.getId()).append(",");
			}
			voucherTypeRo.setSaleAreaIds(sb.toString());
		} else {
			voucherTypeRo.setSaleAreaIds("");
		}
		
		if(getCategory()!=null){
			voucherTypeRo.setLimitInfo("仅" + getCategory().getName() + "可用");
			voucherTypeRo.setCategoryId(getCategory().getId());
		}else if(CollectionUtils.isNotEmpty(products)){
			StringBuffer sb = new StringBuffer();
			sb.append(",");
			for (Product p : products) {
				sb.append(p.getId()).append(",");
			}
			voucherTypeRo.setProductIds(sb.toString());
			voucherTypeRo.setLimitInfo("仅限部分商品可用");			
		}else{
			voucherTypeRo.setLimitInfo("全场可用");
			voucherTypeRo.setCategoryId(null);	
			voucherTypeRo.setProductIds("");
		}
		return voucherTypeRo;
	}
}
