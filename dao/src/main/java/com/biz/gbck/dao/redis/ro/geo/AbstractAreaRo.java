package com.biz.gbck.dao.redis.ro.geo;

import com.biz.gbck.dao.mysql.po.geo.AbstractAreaWithCode;
import com.biz.redis.bean.BaseRedisObject;

import java.math.BigDecimal;

public abstract class AbstractAreaRo extends BaseRedisObject<Integer> {

	protected String code;

	protected String name;

	protected Integer idx;

	protected Integer weight = 0;

	protected String coordinate;

	protected BigDecimal lat;

	protected BigDecimal lon;

	protected String baiduname;

	protected String description;

	protected Integer baiducode;

	protected String post;

	protected String prefix;

	public AbstractAreaRo(AbstractAreaWithCode po) {
		this.baiducode = po.getBaiducode();
		this.setId(po.getId());
		this.code=po.getCode();
		this.baiduname=po.getBaiduname();
		this.coordinate=po.getCoordinate();
		this.description=po.getDescription();
		this.idx=po.getIdx();
		this.lat=po.getLat();
		this.lon=po.getLon();
		this.name=po.getName();
		this.post=po.getPost();
		this.weight=po.getWeight();
		this.prefix = po.getPrefix();
	}
	
	public AbstractAreaRo() {
		super();
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIdx() {
		return idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}

	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public BigDecimal getLon() {
		return lon;
	}

	public void setLon(BigDecimal lon) {
		this.lon = lon;
	}

	public String getBaiduname() {
		return baiduname;
	}

	public void setBaiduname(String baiduname) {
		this.baiduname = baiduname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getBaiducode() {
		return baiducode;
	}

	public void setBaiducode(Integer baiducode) {
		this.baiducode = baiducode;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
}
