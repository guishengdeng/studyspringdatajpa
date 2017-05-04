package com.biz.gbck.common.vo.search;

import java.util.List;
import java.util.Map;

public interface RecommendCondition2 {

	public List<Integer> getBusinessTagIds();
	
	public Map<Integer,List<Integer>> getPriceTagMap();
	
	public List<Integer> getSaleAreaIds();
	
	public String getDepotId();
	
	public Long getUserId();
}
