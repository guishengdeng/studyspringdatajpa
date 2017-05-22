package com.biz.rest.vo.advertisement;

/**
 * Created by defei on 5/22/17.
 */
public class AdvertisementRestVO {

	private String id;

	private String imgUrl;

	private String clickUrl;

	private Long startTime;

	private Long endTime;

	private Integer priority;

	private Long stopTime;

	public AdvertisementRestVO(String id, String imgUrl, String clickUrl, Long startTime, Long endTime, Integer priority, Long stopTime) {

		this.id = id;
		this.imgUrl = imgUrl;
		this.clickUrl = clickUrl;
		this.startTime = startTime;
		this.endTime = endTime;
		this.priority = priority;
		this.stopTime = stopTime;
	}

	/**
	 * {@linkplain AdvertisementRestVO#imgUrl}
	 */
	public String getImgUrl() {

		return imgUrl;
	}

	/**
	 * {@linkplain AdvertisementRestVO#clickUrl}
	 */
	public String getClickUrl() {

		return clickUrl;
	}

	/**
	 * {@linkplain AdvertisementRestVO#startTime}
	 */
	public Long getStartTime() {

		return startTime;
	}

	/**
	 * {@linkplain AdvertisementRestVO#endTime}
	 */
	public Long getEndTime() {

		return endTime;
	}

	/**
	 * {@linkplain AdvertisementRestVO#priority}
	 */
	public Integer getPriority() {

		return priority;
	}

	/**
	 * {@linkplain AdvertisementRestVO#stopTime}
	 */
	public Long getStopTime() {

		return stopTime;
	}
}
