package com.simple.server.job.time;


public class MySchedule {
	
	private String testId;
	private boolean sunday;
	private boolean monday;
	private boolean tuesday;
	private boolean wednesday;
	private boolean thursday;
	private boolean friday;
	private boolean saturday;
		
	
	public String getTestId() {
		return testId;
	}
	public void setTestId(String testId) {
		this.testId = testId;
	}
	public boolean isSunday() {
		return sunday;
	}
	public void setSunday(boolean sunday) {
		this.sunday = sunday;
	}
	public boolean isMonday() {
		return monday;
	}
	public void setMonday(boolean monday) {
		this.monday = monday;
	}
	public boolean isTuesday() {
		return tuesday;
	}
	public void setTuesday(boolean tuesday) {
		this.tuesday = tuesday;
	}
	public boolean isWednesday() {
		return wednesday;
	}
	public void setWednesday(boolean wednesday) {
		this.wednesday = wednesday;
	}
	public boolean isThursday() {
		return thursday;
	}
	public void setThursday(boolean thursday) {
		this.thursday = thursday;
	}
	public boolean isFriday() {
		return friday;
	}
	public void setFriday(boolean friday) {
		this.friday = friday;
	}
	public boolean isSaturday() {
		return saturday;
	}
	public void setSaturday(boolean saturday) {
		this.saturday = saturday;
	}
	@Override
	public String toString() {
		return "MySchedule [testId=" + testId + ", sunday=" + sunday + ", monday=" + monday + ", tuesday=" + tuesday
				+ ", wednesday=" + wednesday + ", thursday=" + thursday + ", friday=" + friday + ", saturday="
				+ saturday + "]";
	}	
	
}
