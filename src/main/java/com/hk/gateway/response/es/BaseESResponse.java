package com.hk.gateway.response.es;

/**
 * Created by IntelliJ IDEA. User: Vaibhav Date: Feb 25, 2013 Time: 12:20:52 PM
 */
public class BaseESResponse implements ESResponse {

	protected Long id;
	protected Long storeId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
}
