package com.simple.server.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.simple.server.config.AppConfig;
import com.simple.server.domain.contract.UniMsg;
import com.simple.server.domain.sys.IncomingBuffer;


@SuppressWarnings("static-access")
@RestController

public class MonController {

	@Autowired
	private AppConfig appConfig;
	
	@RequestMapping(value = "json/pub/uni", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> jsonPub(@RequestBody UniMsg msg) {
		System.out.println(msg.toString());
		HttpHeaders headers = new HttpHeaders();		
		try {			
			IncomingBuffer ib = new IncomingBuffer();
			ib.setJuuid(msg.getJuuid());
			ib.setDatetime((new Date()).toString());
			ib.setEventId(msg.getEventId());
			ib.setMsg(msg.toString());
			
			appConfig.getMsgService().insert(ib);		
			return new ResponseEntity<String>("", headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getCause().toString(), headers, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@RequestMapping(value = "json/pub/err", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> jsonErr(@RequestBody UniMsg msg) {
		System.out.println(msg.toString());
		HttpHeaders headers = new HttpHeaders();		
		try {			
					
			return new ResponseEntity<String>("", headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getCause().toString(), headers, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "json/pub/success", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> jsonSuccess(@RequestBody UniMsg msg) {
		System.out.println(msg.toString());
		HttpHeaders headers = new HttpHeaders();		
		try {			
			
			return new ResponseEntity<String>("", headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getCause().toString(), headers, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "json/sub/confirm", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> jsonConfirm(@RequestBody UniMsg msg) {
		System.out.println(msg.toString());
		HttpHeaders headers = new HttpHeaders();		
		try {			
			IncomingBuffer ib = new IncomingBuffer();
			ib.setJuuid(msg.getJuuid());
			ib.setDatetime((new Date()).toString());
			ib.setEventId(msg.getEventId());
			ib.setMsg(msg.toString());
			
			appConfig.getMsgService().insert(ib);		
			return new ResponseEntity<String>("", headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getCause().toString(), headers, HttpStatus.BAD_REQUEST);
		}
	}
	
}