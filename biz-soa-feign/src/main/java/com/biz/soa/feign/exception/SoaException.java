package com.biz.soa.feign.exception;

/**
 * Created by defei on 5/20/17.
 */
public class SoaException extends RuntimeException {

	public SoaException() {

		super();
	}

	public SoaException(String message) {

		super(message);
	}

	public SoaException(String message, Throwable cause) {

		super(message, cause);
	}

	public SoaException(Throwable cause) {

		super(cause);
	}

	protected SoaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {

		super(message, cause, enableSuppression, writableStackTrace);
	}
}
