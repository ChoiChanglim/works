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
<link rel="stylesheet" type="text/css" href="${info.contextPath }/resources/css/page/hottracks2.css">
<script>
jQuery(document).ready(function(){
	var firstlogin = "${firstlogin}";
	var level = "${level}";
    jQuery(".btn_1").click(function(){
    	if(firstlogin == "false"){
            $('.ly_pop_2').eq(0).show();$('.dimmed').show();return false;
        }else if(level == "false"){
        	$('.ly_pop_2').eq(1).show();$('.dimmed').show();return false;
        }else{
        	$('.ly_pop,.ly_pop_2').hide();$('.ly_pop,.dimmed').show();return false;
        }
    	
        
    });
    
    jQuery(".btn_2").click(function(){
    	if(firstlogin == "false"){
            $('.ly_pop_2').eq(0).show();$('.dimmed').show();return false;
        }else if(level == "false"){
            $('.ly_pop_2').eq(1).show();$('.dimmed').show();return false;
        }else{
            location.href="http://www.hottracks.co.kr/ht/hot/eventDetail?eventId=44751";
        }
    });
    
    jQuery(".close,.btn_ly_ok, .dimmed").click(function(){
        $('.ly_pop_2').hide();$('.ly_pop,.dimmed').hide();return false;
    })
})
</script>
</head>
<body>
<div id="wrap">
    <div id="__cover_area" class="front_area" style="background-image:url(https://images.toast.com/toast/s629/angry/web/page/hottracks/1024x706.jpg);height:105%;">
    <div class="content">
        <div class="btn_area">
            <div class="btn_1"></div>
            <div class="blank1"></div>
            <div class="btn_2"></div>
        </div>
    </div>
    </div>
</div>
<div class="dimmed" style="display:none"></div>
<div class="ly_pop_2" style="display:none">
    <div class="ly_cont_2">
        <p class="ly_tit">신규 유저 대상 이벤트 입니다.</p>
        <div class="btn_ly_ok"><p>확인</p></div>
    </div>
</div>

<div class="ly_pop_2" style="display:none">
    <div class="ly_cont_2">
        <p class="ly_tit">5레벨 달성 후 시도해주세요.</p>
        <div class="btn_ly_ok"><p>확인</p></div>
    </div>
</div>

<div class="ly_pop" style="display:none">
    <div class="ly_cont">
        <div class="close"></div>
    </div>
</div>
</body>
</html>
