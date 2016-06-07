/**
 * 调用web services接口及属性
 */
var WebServices={
	subUrl:"http://localhost:8080/Demo1/service/sinaRest/rest",
	callAPIFunction: function(params, callback,isAsync) {
		if(isAsync==undefined)isAsync=true;
		if(isAsync!=false)isAsync=true;
		if(params==undefined)params="";
		$.ajax({
			async:isAsync,//同步false、异步true,默认异步
			type: "POST",
			contentType: "application/x-www-form-urlencoded",
			url:this.subUrl,
			timeout: 10000,
			data: params,
			complete: function(r, s) {
				var result = "";
				var resultStr="";
				if (s == "success"){
					resultStr=r.responseText;
					result = $.parseJSON(resultStr);
				}
				if (typeof(callback) == "function") 
					callback(result,resultStr);
			}.bind(this)
		});
	}
	
};