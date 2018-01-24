package com.simple.server.http;

import java.net.URI;
import java.nio.charset.Charset;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.simple.server.config.ContentType;
import com.simple.server.domain.sys.Jolokia;
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

	public static void doPrepareAndPost(Object msg, String url, ContentType contentType) throws Exception {

		String converted = null;
		String sContentType = null;

		if (ContentType.XmlPlainText.equals(contentType)) {
			converted = ObjectConverter.objectToXml(msg, false);
			sContentType = "text/plain;charset=utf-8";
		} else if (ContentType.ApplicationJson.equals(contentType)) {
			converted = ObjectConverter.objectToJson(msg);
			sContentType = "application/json;charset=utf-8";
		} else if (ContentType.ApplicationXml.equals(contentType)) {
			converted = ObjectConverter.objectToXml(msg, false);
			sContentType = "application/xml;charset=utf-8";
		} else {
			converted = ObjectConverter.objectToJson(msg);
			sContentType = "text/plain;charset=utf-8";
		}
		doPost(converted, url);
	}
	
	public static void doPost(String reqBody, String url) throws Exception {						
				URI uri = new URI(url);
				RestTemplate restTemplate = new RestTemplate();			
				HttpEntity<String> entity = null;
				entity = new HttpEntity<String>(reqBody, createHeaders());
				restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);		
	}
	
	public static HttpHeaders createHeaders() {
		return new HttpHeaders() {
			{
				setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
			}
		};
	}
}
