package com.simple.server.job;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.simple.server.config.AppConfig;
import com.simple.server.config.ContentType;
import com.simple.server.config.JobStatusType;
import com.simple.server.domain.contract.IContract;
import com.simple.server.domain.contract.StatusMsg;
import com.simple.server.domain.contract.UniMsg;
import com.simple.server.domain.sys.SysMessage;
import com.simple.server.domain.sys.TestCaseAssert;
import com.simple.server.http.HttpImpl;


public class TestCaseJob implements IJob {

	private AppConfig appConfig;

	private String testId;
	private Integer orderId;
	private Integer groupId;
	
	private String eventId;
	private String serviceHandler;
	private String juuid;
	private String senderId;
	private String body;
	private String contentType;

	private Integer timeout;
	private Date targetWhen;
	private Date targetUntil;
	private Long targetDelay;
	private Long targetPeriod;
	private Date jobWhen;
	private Date jobUntil;
	private Long jobDelay;
	private Long jobPeriod;
	private String status;
	private String clazzForRequest;
	

	private Boolean updateHashCodes;
	private Boolean hasErrors;
	private Date lastUpdatedDatetime;

	

	private List<TestCaseAssert> readTestCases() throws Exception {
		Map<String, Object> map = new HashMap();
		map.put("testId", this.testId);
		return (List<TestCaseAssert>) this.appConfig.getMsgService().readbyCriteria(TestCaseAssert.class, map, 0, null);
	}

	private void sendRequest(IContract msg) throws Exception{
		HttpImpl.doPrepareAndPost(msg, getServiceHandler(), getContentType() );
	}
	
	private Boolean assertEvent(List<TestCaseAssert> asserts, String juuid) throws Exception{
		
		Boolean isErrors = false;
		List<SysMessage> res = null;
		
		for(TestCaseAssert asser: asserts) {		
				Class clazz = Class.forName(asser.getSourceClass());
												
				Map<String, Object> map = new HashMap();
				map.put("juuid", juuid);
				res = (List<SysMessage>) appConfig.getMsgService().readbyCriteria(clazz, map, 0,null);
				//System.out.println(String.format("%s %s :",clazz.getSimpleName(),res.size()));
				int hashcode = 0;
				if(res!= null){
					for(SysMessage s: res) {						
						hashcode += s.hashCode();						
					}						
					System.out.println(String.format("%s , %s",clazz.getSimpleName(), hashcode  ));
					
					if(this.updateHashCodes) {
						asser.setAssertHashCode(""+hashcode);		
						asser.setHasErrors(false);
						
					}else {
						Boolean isWell = (asser.getAssertHashCode().equals(""+hashcode));
						asser.setHasErrors(!isWell);	
						if(!isWell) {
							isErrors = true;
						}
					}
					appConfig.getMsgService().insert(asser);					
				}
			}	
		return isErrors;
	}
	
	private void initObject(IContract obj, String juuid) {
		if(obj instanceof UniMsg) {
			UniMsg uniMsg = (UniMsg)obj;
			uniMsg.set(getSenderId(), getEventId(), juuid, getBody());
			return;
		}
		if(obj instanceof StatusMsg) {
			StatusMsg statusMsg = (StatusMsg)obj;
			statusMsg.setEventId(eventId);			
			statusMsg.set(getSenderId(), getEventId(), juuid, "1","Hello World!");
			return;
		}
	}
	
	@Override
	public void run() throws Exception {
					
		
		Class c = Class.forName(this.clazzForRequest);
		
		IContract object = (IContract)c.newInstance();
		
				
		String juuid = this.getJuuid();
		if (juuid == null) {
				juuid = UUID.randomUUID().toString();
				this.setJuuid(juuid);
		}
		
		initObject(object, juuid);			
		sendRequest(object);
		
		List<TestCaseAssert> asserts = readTestCases();
		
		if(asserts == null)
			return;
										
		Thread.currentThread().sleep(20000l);
			
		this.hasErrors = assertEvent(asserts, juuid);	
		
		
		if(this.updateHashCodes){
			this.updateHashCodes = false;
		}

		this.lastUpdatedDatetime=new Date();

		appConfig.getMsgService().insert(this);
		
		System.out.println("well done");
	}
	

	@Override
	public String getKey() {		
		return getTestId();
	}

	public String getTestId() {
		return testId;
	}
	
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getServiceHandler() {
		return serviceHandler;
	}

	public void setServiceHandler(String serviceHandler) {
		this.serviceHandler = serviceHandler;
	}

	public String getJuuid() {
		return juuid;
	}

	public void setJuuid(String juuid) {
		this.juuid = juuid;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public ContentType getContentType() {
		return ContentType.valueOf(contentType);
	}

	public void setContentType(ContentType contentType) {
		this.contentType = contentType.toValue();
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public Date getTargetWhen() {
		return targetWhen;
	}

	public void setTargetWhen(Date targetWhen) {
		this.targetWhen = targetWhen;
	}

	public Date getTargetUntil() {
		return targetUntil;
	}

	public void setTargetUntil(Date targetUntil) {
		this.targetUntil = targetUntil;
	}

	public Long getTargetDelay() {
		return targetDelay;
	}

	public void setTargetDelay(Long targetDelay) {
		this.targetDelay = targetDelay;
	}

	public Long getTargetPeriod() {
		return targetPeriod;
	}

	public void setTargetPeriod(Long targetPeriod) {
		this.targetPeriod = targetPeriod;
	}

	@Override
	public Date getJobWhen() {
		return jobWhen;
	}

	public void setJobWhen(Date jobWhen) {
		this.jobWhen = jobWhen;
	}

	@Override
	public Date getJobUntil() {
		return jobUntil;
	}

	public void setJobUntil(Date jobUntil) {
		this.jobUntil = jobUntil;
	}

	@Override
	public Long getJobDelay() {
		return jobDelay;
	}

	public void setJobDelay(Long jobDelay) {
		this.jobDelay = jobDelay;
	}

	@Override
	public Long getJobPeriod() {
		return jobPeriod;
	}

	public void setJobPeriod(Long jobPeriod) {
		this.jobPeriod = jobPeriod;
	}

	@Override
	public JobStatusType getStatus() {
		return JobStatusType.fromValue(status);
	}

	public void setStatus(JobStatusType status) {
		this.status = status.toValue();
	}

	@Override
	public void setAppConfig(AppConfig appConfig) {
		this.appConfig = appConfig;
	}

	public Boolean getUpdateHashCodes() {
		return updateHashCodes;
	}

	public void setUpdateHashCodes(Boolean updateHashCodes) {
		this.updateHashCodes = updateHashCodes;
	}

	public Boolean getHasErrors() {
		return hasErrors;
	}

	public void setHasErrors(Boolean hasErrors) {
		this.hasErrors = hasErrors;
	}

	public Date getLastUpdatedDatetime() {
		return lastUpdatedDatetime;
	}

	public void setLastUpdatedDatetime(Date lastUpdatedDatetime) {
		this.lastUpdatedDatetime = lastUpdatedDatetime;
	}

	public String getClazzForRequest() {
		return clazzForRequest;
	}

	public void setClazzForRequest(String clazzForRequest) {
		this.clazzForRequest = clazzForRequest;
	}

}
