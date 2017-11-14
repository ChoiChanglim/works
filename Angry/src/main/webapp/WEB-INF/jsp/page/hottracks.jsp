<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html lang="${LanguageEnum.htmlLang }">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0">
<meta name="format-detection" content="telephone=no">
<title>Angry Birds Islands X HOTTRACKS</title>
<script src="${info.contextPath }/resources/js/jquery-1.11.3.min.js"></script>
<c:choose>
	<c:when test="${Device eq 'MOBILE' }">
	   <link rel="stylesheet" type="text/css" href="${info.contextPath }/resources/css/page/hottracks_mobile.css">
	</c:when>
	<c:otherwise>
	   <link rel="stylesheet" type="text/css" href="${info.contextPath }/resources/css/page/hottracks_normal.css">
	</c:otherwise>
</c:choose>
<script>
jQuery(document).ready(function(){
	jQuery(".btn_offline").click(function(){
		$('.ly_pop').hide();$('.ly_pop,.dimmed').show();return false;
	});
	jQuery(".close, .dimmed").click(function(){
		$('.ly_pop').hide();$('.ly_pop,.dimmed').hide();return false;
	})
})
</script>
</head>
<body>
<div class="wrap">
    <div class="img">
        <div class="btn">
            <div class="btn_offline"></div>
            <a href="http://www.hottracks.co.kr/ht/hot/eventDetail?eventId=44751" class="btn_online"></a>
        </div>
    </div>
</div>
<!-- 레이어팝업 -->
<!-- [D] 팝업 노출시 dimmed함께 노출 -->
<div class="dimmed" style="display:none"></div>
<div class="ly_pop" style="display:none">
    <div class="ly_cont">
        <div class="close"></div>
    </div>
</div>
</body>
</html>
