package com.simple.server.domain.sys;

public class TestCaseAssert implements SysMessage{
	private int id;
	private String testId;
	private String sourceClass;
	private String assertHashCode;
	private Boolean hasErrors;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTestId() {
		return testId;
	}
	public void setTestId(String testId) {
		this.testId = testId;
	}
	public String getSourceClass() {
		return sourceClass;
	}
	public void setSourceClass(String sourceClass) {
		this.sourceClass = sourceClass;
	}
		
	public String getAssertHashCode() {
		return assertHashCode;
	}
	public void setAssertHashCode(String assertHashCode) {
		this.assertHashCode = assertHashCode;
	}
	@Override
	public String getClazz() {
		return this.getClass().getSimpleName();
	}
	public Boolean getHasErrors() {
		return hasErrors;
	}
	public void setHasErrors(Boolean hasErrors) {
		this.hasErrors = hasErrors;
	}
	
	
	
}
