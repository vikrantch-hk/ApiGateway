package com.devglan.gatewayservice.config;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

public class SecurityResponseObj implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String SECURITY_RESPONSE = "securityResponseObj";

	private Map<String, Set<String>> clientIdAndClientIPsMapping;
	private Map<String, Map<Integer, Set<String>>> nonStarClientIdAndClientAPIsMapping;
	private Map<Integer, Set<String>> nonStarOpenApiMap;
	private Map<String, Map<Integer, Set<String>>> starClientIdAndClientAPIsMapping;
	private Map<Integer, Set<String>> starOpenApiMap;

	public Map<String, Set<String>> getClientIdAndClientIPsMapping() {
		return clientIdAndClientIPsMapping;
	}

	public void setClientIdAndClientIPsMapping(Map<String, Set<String>> clientIdAndClientIPsMapping) {
		this.clientIdAndClientIPsMapping = clientIdAndClientIPsMapping;
	}

	public Map<String, Map<Integer, Set<String>>> getNonStarClientIdAndClientAPIsMapping() {
		return nonStarClientIdAndClientAPIsMapping;
	}

	public void setNonStarClientIdAndClientAPIsMapping(
			Map<String, Map<Integer, Set<String>>> nonStarClientIdAndClientAPIsMapping) {
		this.nonStarClientIdAndClientAPIsMapping = nonStarClientIdAndClientAPIsMapping;
	}

	public Map<Integer, Set<String>> getNonStarOpenApiMap() {
		return nonStarOpenApiMap;
	}

	public void setNonStarOpenApiMap(Map<Integer, Set<String>> nonStarOpenApiMap) {
		this.nonStarOpenApiMap = nonStarOpenApiMap;
	}

	public Map<String, Map<Integer, Set<String>>> getStarClientIdAndClientAPIsMapping() {
		return starClientIdAndClientAPIsMapping;
	}

	public void setStarClientIdAndClientAPIsMapping(
			Map<String, Map<Integer, Set<String>>> starClientIdAndClientAPIsMapping) {
		this.starClientIdAndClientAPIsMapping = starClientIdAndClientAPIsMapping;
	}

	public Map<Integer, Set<String>> getStarOpenApiMap() {
		return starOpenApiMap;
	}

	public void setStarOpenApiMap(Map<Integer, Set<String>> starOpenApiMap) {
		this.starOpenApiMap = starOpenApiMap;
	}

	@Override
	public String toString() {
		return "SecurityResponseObj [clientIdAndClientIPsMapping=" + clientIdAndClientIPsMapping
				+ ", nonStarClientIdAndClientAPIsMapping=" + nonStarClientIdAndClientAPIsMapping
				+ ", nonStarOpenApiMap=" + nonStarOpenApiMap + ", starClientIdAndClientAPIsMapping="
				+ starClientIdAndClientAPIsMapping + ", starOpenApiMap=" + starOpenApiMap + "]";
	}

}
