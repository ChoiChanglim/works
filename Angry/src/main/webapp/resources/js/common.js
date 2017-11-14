Contextpath = "";
function getJson(url, method, param, callback) {
	$.ajax({
		url : url,
		method : method,
		data : param,
		dataType : "json",
		success : callback,
		async : false
	});
}
function getJsonBeforeFunc(url, method, param, beforeSend, callback) {
	$.ajax({
		url : url,
		method : method,
		data : param,
		dataType : "json",
		beforeSend:beforeSend,
		success : callback,
		async : false
	});
}
function getJsonToken(url, method, param, callback) {
	$.ajax({
		url : url,
		method : method,
		beforeSend : function(request) {
			authToken.getClientFlow();
			request.setRequestHeader("Authorization", authToken.clientFlow);
		},
		data : param,
		dataType : "json",
		success : callback,
		async : false
	});
}
function getJsonTokenAndHeaderParam(url, method, headerParam, param, callback) {
	$.ajax({
		url : url,
		method : method,
		beforeSend : function(request) {
			authToken.getClientFlow();
			request.setRequestHeader("Authorization", authToken.clientFlow);
			request.setRequestHeader(headerParam.key, headerParam.value);
		},
		data : param,
		dataType : "json",
		success : callback,
		async : false
	});
}

AuthToken = function() {
	this.point = new Date().getTime();
	this.clientFlow;
}
AuthToken.prototype.getClientFlow = function() {
	this.clientFlow = new Date().getTime() - this.point;
}
HeaderParam = function(key, value) {
	this.key = key;
	this.value = value;
}


if (!String.prototype.includes) {
    String.prototype.includes = function() {
        'use strict';
        return String.prototype.indexOf.apply(this, arguments) !== -1;
    };
}
function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}
/*(function() {
    'use strict';
    var root = this;
    var version = '1.0';
    var Regist;
    if(typeof exports !== 'undefined') {
    	Regist = exports;
    } else {
    	Regist = root.Regist = {};
    }
    
    
    Regist.getUser = function() {
    	return user;
        
    }
    
}).call(this);
*/