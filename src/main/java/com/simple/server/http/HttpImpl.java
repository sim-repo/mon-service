package com.simple.server.http;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.simple.server.domain.sys.Jolokia;
import com.simple.server.domain.sys.SysMsg;
import com.simple.server.util.ObjectConverter;

public class HttpImpl {

	public static String doGet(String url) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> res = restTemplate.getForEntity(url, String.class);
		return res.getBody();
	}

	public static Jolokia doGet2(String url) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> json = restTemplate.getForEntity(url, String.class);
		Jolokia jolokia = new Jolokia();
		jolokia = (Jolokia) ObjectConverter.jsonToObject(json.getBody(), jolokia);
		return jolokia;
	}

	public static SysMsg doPost(String url) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
