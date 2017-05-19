package com.biz.rest.vo;

/**
 * Created by defei on 5/19/17.
 */
public class AliStsVO {

	private String accessKeyId;

	private String accessKeySecret;

	private String securityToken;

	private String expiration;

	public AliStsVO(String accessKeyId, String accessKeySecret, String securityToken, String expiration) {

		this.accessKeyId = accessKeyId;
		this.accessKeySecret = accessKeySecret;
		this.securityToken = securityToken;
		this.expiration = expiration;
	}

	/**
	 * {@linkplain AliStsVO#accessKeyId}
	 */
	public String getAccessKeyId() {

		return accessKeyId;
	}

	/**
	 * {@linkplain AliStsVO#accessKeySecret}
	 */
	public String getAccessKeySecret() {

		return accessKeySecret;
	}

	/**
	 * {@linkplain AliStsVO#securityToken}
	 */
	public String getSecurityToken() {

		return securityToken;
	}

	/**
	 * {@linkplain AliStsVO#expiration}
	 */
	public String getExpiration() {

		return expiration;
	}
}
