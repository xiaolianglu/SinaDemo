package com.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ParseRequestMap {
	
	public static Map<String, Object> parseRequestMap(Map<String, Object> requestMap) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Iterator iter = requestMap.keySet().iterator();
	    while(iter.hasNext()){
	    	String _key = (String)iter.next();
	    	Object _objvalue=requestMap.get(_key);
	    	Object[] _objArray = null;
	    	if(_objvalue instanceof List){
	    		_objArray=((List)_objvalue).toArray();
	    	}else if (_objvalue instanceof Object[]) {
				_objArray=(Object[])_objvalue;
			}else{
				_objArray=new Object[]{_objvalue};
			}
	    	if(_objArray==null)continue;
	    	paramMap.put(_key, _objArray.length>1?_objArray:_objArray[0]);
	    }
		return paramMap;
	}
	
}
