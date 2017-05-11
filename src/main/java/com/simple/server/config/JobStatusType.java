package com.simple.server.config;

public enum JobStatusType {
	READY("READY"), WAITING("WAITING"), RUNNING("RUNNING"), PAUSE("PAUSE"), DONE("DONE"), ERROR("ERROR"), CANCEL("CANCEL"), UNKNOWN("UNKNOWN");

	private final String value;

	JobStatusType(String value) {
		this.value = value;
	}

	public static JobStatusType fromValue(String value) {
		if (value != null) {
			for (JobStatusType statusType : values()) {
				if (statusType.value.equals(value)) {
					return statusType;
				}
			}
		}
		return getDefault();
	}

	public String toValue() {
		return value;
	}

	public static JobStatusType getDefault() {
		return UNKNOWN;
	}
}
