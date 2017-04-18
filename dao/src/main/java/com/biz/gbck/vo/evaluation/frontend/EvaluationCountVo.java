package com.biz.gbck.vo.evaluation.frontend;

import java.io.Serializable;

public class EvaluationCountVo implements Serializable{
		//服务态度评分
		private Integer attitudeScore;

		//物流速度评分
		private Integer logisticsScore;

		//描述相符评分
		private Integer descriptionScore;

		//服务态度评论人数
		private Integer attitudeNum;

		//物流速度评分人数
		private Integer logisticsNum;

		//描述相符评分
		private Integer descriptionNum;
		//商品评综合评分
		private Integer productscore;
		
		public Integer getProductscore() {
			return productscore;
		}

		public void setProductscore(Integer productscore) {
			this.productscore = productscore;
		}

		public Integer getAttitudeScore() {
			return attitudeScore;
		}

		public void setAttitudeScore(Integer attitudeScore) {
			this.attitudeScore = attitudeScore;
		}

		public Integer getLogisticsScore() {
			return logisticsScore;
		}

		public void setLogisticsScore(Integer logisticsScore) {
			this.logisticsScore = logisticsScore;
		}

		public Integer getDescriptionScore() {
			return descriptionScore;
		}

		public void setDescriptionScore(Integer descriptionScore) {
			this.descriptionScore = descriptionScore;
		}

		public Integer getAttitudeNum() {
			return attitudeNum;
		}

		public void setAttitudeNum(Integer attitudeNum) {
			this.attitudeNum = attitudeNum;
		}

		public Integer getLogisticsNum() {
			return logisticsNum;
		}

		public void setLogisticsNum(Integer logisticsNum) {
			this.logisticsNum = logisticsNum;
		}

		public Integer getDescriptionNum() {
			return descriptionNum;
		}

		public void setDescriptionNum(Integer descriptionNum) {
			this.descriptionNum = descriptionNum;
		}
		
}
