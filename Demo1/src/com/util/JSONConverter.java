package com.util;

import net.sf.json.JSONObject;

/**
 * json-object转换
 */
public class JSONConverter {
	
	public static String objectToJSONStr(Object o){
		if(o==null)return null;
		JSONObject json= JSONObject.fromObject(o);
		return json.toString();
	}
	
	public static JSONObject objectToJSONObject(Object o){
		if(o==null)return null;
		return JSONObject.fromObject(o);
	}
	
}  
	 
