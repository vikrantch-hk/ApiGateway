package com.hk.gateway.exception;

/**
 * @author adlakha.vaibhav
 */
public class HKRuntimeException extends RuntimeException {

	protected String messageKey;
	protected Object[] params;

	public HKRuntimeException(String messageKey, Object... params) {
		super();
		this.params = params;
		this.messageKey = messageKey;
	}

	@Override
	public String getMessage() {
		StringBuilder s = new StringBuilder();

		if (null != this.params && this.params.length > 0) {
			s.append(" : ");
			for (Object param : this.params) {
				s.append(param);
			}
		}

		return messageKey + s.toString();
	}

	@Override
	public String toString() {

		return super.toString();
	}

}
