package com.biz.gbck.common.vo.search;

import java.math.BigDecimal;
import java.util.List;

public interface SearchProductCondition extends java.io.Serializable {

	public String getKeyword();

	void setKeyword(String keyword);

	public Integer getCategoryId();

	public Long getUserId();

	public List<Integer> getSaleAreaIds();

	public void setSaleAreaIds(List<Integer> saleAreaIds);

	public String getSort();

	public int getPageSize();

	public BigDecimal getLat();

	public BigDecimal getLon();

	public int getPageFromLastFlag();

	public String getDepotId();

	public void setDepotId(String v);


}
