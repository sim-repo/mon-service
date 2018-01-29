package com.simple.server.domain.sys;



public class LogSubError implements SysMessage{
	
	private int id;
	private String juuid;
	private String eventId;
	private String senderId;	
	private String publisherId;
	
	public Integer getId() {
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
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}
	@Override
	public String getClazz() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eventId == null) ? 0 : eventId.hashCode());
		result = prime * result + ((publisherId == null) ? 0 : publisherId.hashCode());
		result = prime * result + ((senderId == null) ? 0 : senderId.hashCode());
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
		LogSubError other = (LogSubError) obj;
		if (eventId == null) {
			if (other.eventId != null)
				return false;
		} else if (!eventId.equals(other.eventId))
			return false;
		if (publisherId == null) {
			if (other.publisherId != null)
				return false;
		} else if (!publisherId.equals(other.publisherId))
			return false;
		if (senderId == null) {
			if (other.senderId != null)
				return false;
		} else if (!senderId.equals(other.senderId))
			return false;
		return true;
	}
	
	
}