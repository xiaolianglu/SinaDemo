package com.adapter;

import java.util.Map;

import net.sf.json.JSONObject;

import com.util.CallSinaAPI;
import com.util.Contants;
import com.util.JSONConverter;
import com.util.Token;

public class SinaAdapter {
	
	/**
	 * 获取token值
	 * @return
	 */
	public String getToken() {
		String token = "1234";
		String param = "client_id="+Token.client_id+"&redirect_uri="+Token.myUrl+"&response_type=code";
		String result = CallSinaAPI.callService(Contants.accessURL+param);
		if(result != null) {
			JSONObject obj = JSONConverter.objectToJSONObject(result);
			token = obj.optString("access_token");
			System.out.println("token received");
		}
		return token;
	}
	
	/**
	 * 获取公共微博
	 * @param access_token 必须传
	 * @param count 一页数量
	 * @param page 当前页
	 * @return
	 */
	public String getPublicWB(String access_token, String count, String page) {
		if(access_token==null || "".equals(access_token)) return "";
		String param = "access_token="+access_token;
		if(count != null && !"".equals(count)) {
			param = param+"&count="+count;
		}
		if(page != null && !"".equals(page)) {
			param = param+"&page="+page;
		}
		String result = CallSinaAPI.callService(Contants.accessURL+param);
		if(result != null) {
			System.out.println("weibo received");
		}
		return result;
	}
	
}
