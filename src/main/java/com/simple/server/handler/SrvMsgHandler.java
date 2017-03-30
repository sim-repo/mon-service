package com.simple.server.handler;

import org.springframework.stereotype.Service;

@Service("srvMsgHandler")
public class SrvMsgHandler extends AbstractMsgHandler {

	public void handleSrvJsonMsg(String json) throws Exception {
		
		System.out.println("mon:::::: handleSrvJsonMsg"+ json);		
	}

	public void handleSrvXmlMsg(String xml) throws Exception {
		// TODO Auto-generated method stub
	}
}
