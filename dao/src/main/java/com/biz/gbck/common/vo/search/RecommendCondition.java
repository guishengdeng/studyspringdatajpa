package com.biz.gbck.common.vo.search;

import java.util.List;

public interface RecommendCondition {

	public List<Integer> getBusinessTagIds();
	
	public List<Integer> getPriceTagsIds();
	
	public List<Integer> getSaleAreaIds();
	
	public String getDepotId();
	
	public Long getUserId();
}
