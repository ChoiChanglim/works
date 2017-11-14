<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script>
var result = "${result}";
var code = "${code}";
var snsname = "${snsname}";
if(result=="success"){
	/* if(snsname == "twitter"){
		window.opener.twitterComplete(code);
	}else if(snsname == "kakao"){
		window.opener.kakaoComplete(code);
	}
	if(typeof window.opener.userInfo != "undefined"){
        window.opener.userInfo.shareComplete("close"); 
    } */
    window.self.close();
}
</script>
