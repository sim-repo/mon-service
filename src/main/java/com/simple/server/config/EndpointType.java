package com.simple.server.config;

public enum EndpointType {
	LOG("LOG"), TESTED("TESTED"), MON("MON"), UNKNOWN("UNKNOWN");

	private final String value;

	EndpointType(String value) {
		this.value = value;
	}

	public static EndpointType fromValue(String value) {
		if (value != null) {
			for (EndpointType endpoint : values()) {
				if (endpoint.value.equals(value)) {
					return endpoint;
				}
			}
		}
		return getDefault();
	}

	public String toValue() {
		return value;
	}

	public static EndpointType getDefault() {
		return UNKNOWN;
	}
}
