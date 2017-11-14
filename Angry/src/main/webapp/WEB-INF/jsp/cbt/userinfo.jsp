<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<!-- saved from url=(0098)http://ui-dev.nhnent.com/svnview/hivelab/promotion/s_150225_mheroeswanted_board/mheroeswanted.html -->
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0">
<title></title>
<script src="${info.contextPath }/resources/js/jquery-1.11.3.min.js"></script>
<script src="${info.contextPath }/resources/js/common.js"></script>
<link rel="stylesheet" type="text/css" href="${info.contextPath }/resources/css/web/cbt.css">

<script>
jQuery(document).ready(function(){
	$(function(){$('.btn_check, .btn_check1, .btn_check2, .btn_check4').click(function(){$('.lypop,.dimmed').hide();return false})})
    
	
	jQuery("#agree_ok").on({
		"change":function(){
			var flag = jQuery(this).is(":checked");
			if(flag == true){
				jQuery("span.check").addClass("on");
			}else{
				jQuery("span.check").removeClass("on");
			}
		}
	});
	jQuery(".agree_view").click(function(){
		$('.lypop').hide();$('.dimmed,.pop2').show();
	})
	
	
	var contextPath = "${info.contextPath}";
	jQuery("a.btn_entry").click(function(e){
        e.preventDefault();
        if(jQuery("#agree_ok").is(":checked") == false){
        	$('.lypop').hide();$('.dimmed,.pop7').show();
        	return false;
        }
        var user_no = jQuery("#inpt_code").val();
        if(user_no == ""){
        	$('.lypop').hide();$('.dimmed,.pop6').show();
        	return false;
        }else{
        	jQuery(".user_no").text(user_no);
        	$('.lypop').hide();$('.dimmed,.pop1').show();
        	
        	jQuery("a.btn_check3").off("click.realbtn");
        	jQuery("a.btn_check3").on({
        		"click.realbtn":function(){
        			var url=contextPath+"/cbt/userinfo_save.json";
                    var param = "userkey="+user_no;
                    getJson(url, "post", param, saveInfoResponse);
        		}
        	});
        }
        
    });
    var saveInfoResponse = function(data){
        //console.log(data);
        if(data.result == "SUCCESS"){
        	$('.lypop').hide();$('.dimmed,.pop5').show();
        	
        }else if(data.result == "ALREADY_USN"){
        	jQuery(".user_no_yet").text(data.key);
        	$('.lypop').hide();$('.dimmed,.pop3').show();
        	
        }else if(data.result == "EMPTY_USER"){
        	$('.lypop').hide();$('.dimmed,.pop6').show();
        	
        }else if(data.result == "ALREADY_PHONE"){
        	$('.lypop').hide();$('.dimmed,.pop4').show();
        	
        }
    }
})
  
</script>
<style>

</style>
</head>
<body>
<div id="wrap">
    <div class="content event">
        <div class="coupon">
            <!--text 2줄일 경우 클래스명 "entry"를 "entry2"로-->
            <div class="entry">
                <div class="entry_input">
                    <input type="number" id="inpt_code" maxlength="16">
                    <label for="inpt_code" class="blind">정보 입력</label>
                    <a href="#" class="btn_entry">입력</a>
                </div>
                <div class="agree">
                    <!-- 체크박스 클릭시 on 클래스 추가 -->
                    <span class="check on">
                        <input type="checkbox" id="agree_ok" class="blind" checked>
                    </span>
                    <label for="agree_ok">
                        <span class="tt">개인정보 수집 및 SMS 수신에 동의합니다.</span>
                    </label>
                    <em class="agree_view">자세히 보기</em>
                </div>
            </div>
        </div>
    </div>
    <div class="dimmed" style="display:none"></div>
    
    <div class="lypop pop1" style="display:none">
		<div class="pop_content">
			<p class="tit">
				입력하신 번호는 [<em class="user_no">00000000000</em>]입니다. <br>해당 번호가 맞습니까?
			</p>
			<p class="ttt">입력한 번호를 다시 확인 부탁드립니다.</p>
			<a href="#" class="btn_check2">취소</a> <a href="#" class="btn_check3">확인</a>
		</div>
	</div>
	
    <div class="lypop pop2" style="display:none">
        <div class="pop_content">
	        <p class="tit">[개인정보 수집 및 이용 동의 안내]</p>
	        <p class="ttt">앵그리버드는 CBT 이벤트 관련하여 <br>다음과 같은 개인정보를 수집하고 있습니다. <br>개인정보 수집을 거부하는 경우 사전등록 이벤트 참여에 <br>제한이 될 수 있습니다.</p>
	        <span class="tt">
	        <br><br>수집하고자 하는 항목 : 휴대폰 번호
	        <br>개인정보 수집 및 이용 목적 : CBT 이벤트 당첨 공지, 경품 발송, 정식 런칭 안내 및 CBT 참여자 선물
	        <br>개인정보 수집 및 이용 기간 : 게임 정식 런칭 후 60일 이후 폐기처리
	        </span>
	        <br>
	        <a href="#" class="btn_check4">확인</a>
        </div>
    </div>
    
    <div class="lypop pop3" style="display:none">
	    <div class="pop_content">
	        <p class="tit">이미 [<em class="user_no_yet"></em>]를 입력하셨습니다.</p>
	        <p class="ttt">핸드폰 입력은 계정당 1회만 가능합니다.</p>
	        <a href="#" class="btn_check1">확인</a>
	    </div>
    </div>
    
    <div class="lypop pop4" style="display:none">
	    <div class="pop_content">
	        <p class="tit">이미 입력된 번호입니다.</p>
	        <p class="ttt">핸드폰 번호를 다시 확인해주세요. </p>
	        <a href="#" class="btn_check">확인</a>
	    </div>
    </div>
    
    <div class="lypop pop5" style="display:none">
	    <div class="pop_content">
	        <p class="tit">정상적으로 입력 완료하셨습니다.</p>
	        <p class="ttt">정식 런칭을 기다려 주세요!</p>
	        <a href="#" class="btn_check">확인</a>
        </div>
    </div>
    
    <div class="lypop pop6" style="display:none">
	    <div class="pop_content">
	        <p class="tit">휴대폰 번호를 입력해주세요.</p>
	        <p class="ttt">숫자만 입력해주세요.</p>
	        <a href="#" class="btn_check">확인</a>
	    </div>
    </div>
    
    <div class="lypop pop7" style="display:none">
        <div class="pop_content">
            <p class="tit">개인정보 수집 및 SMS 수신에 동의해주세요.</p>
            <p class="ttt">선물 전송에 사용될 예정 입니다.</p>
            <a href="#" class="btn_check">확인</a>
        </div>
    </div>
</div>
</body>
</html>