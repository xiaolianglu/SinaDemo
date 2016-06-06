package com.util;


import javax.ws.rs.core.MediaType;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class CallSinaAPI {
	
	private static final Client client = Client.create();
	public static String callService(String url){
		if(url==null)return null;
		
		WebResource resource=client.resource(url);
		
		ClientResponse response = resource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).accept(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);
		if(response.getStatus() == 200){
			System.out.println(response.getHeaders());
			String str = response.getEntity(String.class);
			return str;
		}
		return null;
	}
	
	public static void main(String[] args) {
//		/CallSinaAPI.callService("https://api.weibo.com/oauth2/authorize?client_id=123050457758183&redirect_uri=http://m.mash5.cn&display=client");
		CallSinaAPI.callService("https://api.weibo.com/oauth2/authorize?client_id=123050457758183&redirect_uri=http://www.example.com/response&response_type=code");
	}
	
}
