package com.biz.gbck.common.vo.search;

import com.biz.core.util.StringTool;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;
import java.util.Map;

public class RecommendConditionVo2 implements RecommendCondition2, java.io.Serializable {

	private List<Integer> businessTagIds;
	private Map<Integer, List<Integer>> priceTagMap;
	private List<Integer> saleAreaIds;
	private String depotId;

	private Long userId;

	public List<Integer> getBusinessTagIds() {
		return businessTagIds;
	}

	public void setBusinessTagIds(List<Integer> businessTagIds) {
		this.businessTagIds = businessTagIds;
	}

	public Map<Integer, List<Integer>> getPriceTagMap() {
		return priceTagMap;
	}

	public void setPriceTagMap(Map<Integer, List<Integer>> priceTagMap) {
		this.priceTagMap = priceTagMap;
	}

	public List<Integer> getSaleAreaIds() {
		return saleAreaIds;
	}

	public void setSaleAreaIds(List<Integer> saleAreaIds) {
		this.saleAreaIds = saleAreaIds;
	}

	public String getDepotId() {
		return depotId;
	}

	public void setDepotId(String depotId) {
		this.depotId = depotId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setBusinessTags(String businessTags) {
		this.businessTagIds = StringTool.strToIntArray(businessTags);
	}

	public void setSaleAreas(String saleAreas) {
		this.saleAreaIds = StringTool.strToIntArray(saleAreas);
		
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
