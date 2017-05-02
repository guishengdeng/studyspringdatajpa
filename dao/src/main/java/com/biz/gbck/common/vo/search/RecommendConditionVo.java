package com.biz.gbck.common.vo.search;

import com.biz.core.util.StringTool;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class RecommendConditionVo implements RecommendCondition,java.io.Serializable{

	private List<Integer> businessTagIds;
	private List<Integer> priceTagsIds;
	private List<Integer> saleAreaIds;
	private String depotId;
	
	private Long userId;
	
	public List<Integer> getBusinessTagIds() {
		return businessTagIds;
	}
	public void setBusinessTagIds(List<Integer> businessTagIds) {
		this.businessTagIds = businessTagIds;
	}
	public List<Integer> getPriceTagsIds() {
		return priceTagsIds;
	}
	public void setPriceTagsIds(List<Integer> priceTagsIds) {
		this.priceTagsIds = priceTagsIds;
	}
	public List<Integer> getSaleAreaIds() {
		return saleAreaIds;
	}
	public void setSaleAreaIds(List<Integer> saleAreaIds) {
		this.saleAreaIds = saleAreaIds;
	}
	
	
	public void setBusinessTags(String businessTags) {
		this.businessTagIds = StringTool.strToIntArray(businessTags);
	}
	public void setPriceTagss(String priceTags) {
		this.priceTagsIds = StringTool.strToIntArray(priceTags);
	}
	public void setSaleAreas(String saleAreas) {
		this.saleAreaIds = StringTool.strToIntArray(saleAreas);
		
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
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}

}
