<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0">
<script src="${info.contextPath }/resources/js/jquery-1.11.3.min.js"></script>
<script src="${info.contextPath }/resources/js/common.js"></script>
<style>
.t_icon{margin-top:3px;display:block;float:left;width:17px;height:18px;background:url(https://images.toast.com/toast/s629/angry/web/pre_regist/mobile/m_spot_sec.png) no-repeat -50px 0px;background-size:auto 75px;}
.f_icon{margin-top:3px;display:block;float:left;width:17px;height:18px;background:url(https://images.toast.com/toast/s629/angry/web/pre_regist/mobile/m_spot_sec.png) no-repeat -50px -18px;background-size:auto 75px;}
.k_icon{margin-top:3px;display:block;float:left;width:17px;height:18px;background:url(https://images.toast.com/toast/s629/angry/web/pre_regist/ko_spot_sec.png) no-repeat -80px -29px;background-size:auto 142px;}

.list{
    height: 30px;
    line-height: 18px;
}
</style>
<script>
jQuery(document).ready(function(){
	jQuery(".deleteBtn").click(function(){
		var yn = confirm("삭제 합니까?");
		if(yn == true){
			var url = "${info.contextPath }/sns/event/delete_reply.json";
	        var seq = this.id;
	        var param = "seq="+seq;
	        getJson(url, "get", param, function(data){
	            console.log(data);
	            if(data.result == "success"){
	                location.reload(true);
	            }
	        });
		}
		
	})
});
</script>
</head>
<body>
<h3>총 ${registerCount }건</h3>
<ul>
    <c:forEach var="list" items="${statusList }" varStatus="status">
        <li><span style="display: inline-block;width:150px;margin-left:10px;">${list.language }</span>${list.count }</li>
    </c:forEach>
</ul>

<p style="margin-top:30px;"></p>
<h3>한국 댓글</h3>
<ol>
    <c:forEach var="reply" items="${reply_list_kr }" varStatus="status">
        <li class="list"><div class="${fn:substring(reply.snsname, 0, 1) }_icon"></div>&nbsp;${reply.msg }&nbsp;&nbsp;<button id="${reply.seq }" class="deleteBtn">삭제</button></li>
    </c:forEach>
</ol>
<p style="margin-top:30px;"></p>
<h3>해외 댓글</h3>
<ol>
    <c:forEach var="reply" items="${reply_list_en }" varStatus="status">
        <li class="list"><div class="${fn:substring(reply.snsname, 0, 1) }_icon"></div>&nbsp;${reply.msg }&nbsp;&nbsp;<button id="${reply.seq }" class="deleteBtn">삭제</button></li>
    </c:forEach>
</ol>
</body>
</html>