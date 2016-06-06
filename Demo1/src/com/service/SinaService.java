package com.service;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.cxf.jaxrs.ext.MessageContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.adapter.SinaAdapter;
import com.util.CallSinaAPI;
import com.util.Contants;
import com.util.JSONConverter;
import com.util.PageInfo;
import com.util.ParseRequestMap;
import com.util.Token;

@Path("/rest")
public class SinaService {
	
	private MessageContext messageContext;
	private SinaAdapter sinaAdapter;
	
	public SinaAdapter getSinaAdapter() {
		return sinaAdapter;
	}

	public void setSinaAdapter(SinaAdapter sinaAdapter) {
		this.sinaAdapter = sinaAdapter;
	}

	@Context
	public void setMessageContext(MessageContext messageContext) {
		this.messageContext = messageContext;
	}
	
	@POST
	@Produces("application/json")
	public String postMethod(MultivaluedMap<String, Object> formParams){
		Map<String,Object> requestMap=null;
		requestMap=this.messageContext.getHttpServletRequest().getParameterMap();
		boolean isGet=false;
		if(formParams!=null){
			requestMap=(Map)formParams;
		}else{
			requestMap=this.messageContext.getHttpServletRequest().getParameterMap();
			isGet=true;
		}
		return this.doService(requestMap,isGet, this.messageContext.getHttpServletRequest(), this.messageContext.getHttpServletResponse(), this.messageContext.getServletContext());
	}
	
	@GET
	@Produces("application/json")
	public String getMethod(){
		return this.postMethod(null);
	}
	
	public String doService(Map<String, Object> requestMap, boolean isGet, HttpServletRequest request, HttpServletResponse response, ServletContext servletContext){
		String resultStr = "{software:sina,method:POST-GET}";
		String token = this.sinaAdapter.getToken();
		requestMap = ParseRequestMap.parseRequestMap(requestMap);
		String count = "";
		if(requestMap.get("count") != null) {
			count = requestMap.get("count").toString();
		}
		
		String page = "";
		if(requestMap.get("page")!=null) {
			page = requestMap.get("page").toString();
		}
		String str = this.sinaAdapter.getPublicWB(token, count, page);
		if(str != null) {
			JSONObject resultObj = JSONConverter.objectToJSONObject(str);
			if(resultObj != null) {
				JSONArray statusesArr = resultObj.optJSONArray("statuses");
				resultStr = JSONConverter.objectToJSONStr(statusesArr);
			}
		}
		return resultStr;
	}
	
}
