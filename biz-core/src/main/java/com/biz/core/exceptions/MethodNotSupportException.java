package com.biz.core.exceptions;

/**
 * Created by defei on 5/4/17.
 */
public class MethodNotSupportException extends RuntimeException {

	public MethodNotSupportException() {

		super();
	}

	public MethodNotSupportException(String message) {

		super(message);
	}

	public MethodNotSupportException(String message, Throwable cause) {

		super(message, cause);
	}

	public MethodNotSupportException(Throwable cause) {

		super(cause);
	}

	protected MethodNotSupportException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {

		super(message, cause, enableSuppression, writableStackTrace);
	}
}
