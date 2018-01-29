package com.simple.server.domain.sys;

public class LogSub implements SysMessage{

	private int id;
	private String juuid;
	private String eventId;
	private String serviceRoleFrom;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJuuid() {
		return juuid;
	}
	public void setJuuid(String juuid) {
		this.juuid = juuid;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getServiceRoleFrom() {
		return serviceRoleFrom;
	}
	public void setServiceRoleFrom(String serviceRoleFrom) {
		this.serviceRoleFrom = serviceRoleFrom;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eventId == null) ? 0 : eventId.hashCode());
		result = prime * result + ((serviceRoleFrom == null) ? 0 : serviceRoleFrom.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LogSub other = (LogSub) obj;
		if (eventId == null) {
			if (other.eventId != null)
				return false;
		} else if (!eventId.equals(other.eventId))
			return false;
		if (serviceRoleFrom == null) {
			if (other.serviceRoleFrom != null)
				return false;
		} else if (!serviceRoleFrom.equals(other.serviceRoleFrom))
			return false;
		return true;
	}
	@Override
	public String getClazz() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
