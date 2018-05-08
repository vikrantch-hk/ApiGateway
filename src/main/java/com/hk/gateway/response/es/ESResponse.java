package com.hk.gateway.response.es;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA. User: Vaibhav Date: Feb 25, 2013 Time: 12:19:49 PM
 *
 */
public interface ESResponse extends Serializable {

	public Long getId();

	public void setId(Long id);

	public Long getStoreId();

	public void setStoreId(Long storeId);

}
