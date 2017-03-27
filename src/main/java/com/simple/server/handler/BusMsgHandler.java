package com.simple.server.handler;

import org.springframework.stereotype.Service;

@Service("busMsgHandler")
public class BusMsgHandler extends AbstractMsgHandler {

	public void handleBusJsonMsg(String json) throws Exception {

		// System.out.println("log handleBusJsonMsg"+ json);
		getAppConfig().getDirtyBusJsonQueue().put(json);
	}

	public void handleBusXmlMsg(String xml) throws Exception {
		// TODO Auto-generated method stub
	}
}
