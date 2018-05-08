package com.hk.gateway.constant;

public enum EnumStoreType {
	GENERAL(1L, "GENERAL_STORE"), CFA(2L, "CFA_STORE");

	private Long id;
	private String name;

	EnumStoreType(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
