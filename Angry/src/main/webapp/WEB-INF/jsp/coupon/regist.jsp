<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script>
jQuery(document).ready(function(){  
    var contextPath = "${info.contextPath}";
    jQuery("#inpt_code").on({
        "keyup": function(){
            jQuery(this).val(jQuery(this).val().toUpperCase());
        }
    });
    var autoFont = function () {
        $("p").css('font-size', Math.max(Math.min($("p").width() / (2*10))));
    }
    autoFont();
    $(window).resize(function(){
        autoFont();
    });
    jQuery("a.btn_entry").click(function(e){
        e.preventDefault();
        var cnum = jQuery("#inpt_code").val();
        if(cnum=="" || typeof(cnum)=="undefied"){
            $('.pop_content').hide();$('.dimmed,.pop3').show();
        }else{
            var url = contextPath + "/coupon/couponItemSend.json";
            var param = "cnum="+cnum;
            getJson(url, "post", param, couponValidAfter);
        }
        
    });
    var couponValidAfter = function(data){
    	//console.log(data);
        if(data.result=="84"){
            $('.pop_content').hide();$('.dimmed,.pop3').show();return false  //존재하지않는 쿠폰인데 여기에서는 잘못된쿠폰으로 보임
        }else if(data.result=="72"){
            $('.pop_content').hide();$('.dimmed,.pop2').show();return false  //이미 사용한 쿠폰
        }else if(data.result=="81"){
            $('.pop_content').hide();$('.dimmed,.pop5').show();return false  //이미 이벤트에 참여 했음
        }else if(data.result=="82"){
            $('.pop_content').hide();$('.dimmed,.pop4').show();return false  //유효기간이 맞지않음
        }else if(data.result=="90"){
            $('.pop_content').hide();$('.dimmed,.pop9').show();return false  //쿠폰 사용 조건에 맞지않음 (ex 어느시점 이후 가입자만 사용가능)
        }else if(data.result=="0"){
            $('.pop_content').hide();$('.dimmed,.pop1').show();return false  //정상적으로 보상이 지급
        }else if(data.result=="EMPTY_SNO" || data.result=="INVALID_SNO"){
        	$('.pop_content').hide();$('.dimmed,.pop8').show();return false  //잘못된 회원번호
        }else{
            $('.pop_content').hide();$('.dimmed,.pop7').show();return false
            //시스템에러
        }
    }
    $(function(){$('.pop_content,.btn_check').click(function(){$('.pop_content,.dimmed').hide();return false})})
});
</script>
<div id="wrap">
    <div class="content event">
        <div class="coupon">
            <!--text 2줄일 경우 클래스명 "entry"를 "entry2"로-->
            <div class="entry">
                <p class="title"><spring:message code="coupon.enter.info"/></p>
                <div class="entry_input">
                    <input type="text" id="inpt_code" maxlength="16" autocomplete="off"><label for="inpt_code" class="blind">쿠폰 번호 입력</label>
                    <a href="#" class="btn_entry"><spring:message code="coupon.enter.button"/></a>
                </div>
                <span class="tt"><spring:message code="coupon.receive.info"/></span>
            </div>
        </div>
        <div class="coupon_notice">
            <div class="text_box">
            <h2 class="tit"><spring:message code="coupon.note.title"/></h2>
            <ul>
            <li><spring:message code="coupon.note.text1"/></li>
            <li><spring:message code="coupon.note.text2"/></li>
            <li><spring:message code="coupon.note.text3"/></li>
            <li><spring:message code="coupon.note.text4"/></li>
            </ul>
            <a href="${info.contextPath }/coupon/mylist.nhn" class="btn_cpnview"><spring:message code="coupon.used.button"/></a>
            </div>
        </div>
    </div>
    
    <div class="dimmed" style="display:none"></div>
    <div class="pop_content pop1" style="display:none">
        <h1 class="tit"><spring:message code="coupon.success"/></h1>
        <p><spring:message code="coupon.success.text"/></p>
        <a href="#" class="btn_check"><spring:message code="coupon.popup.close"/></a>
    </div>
    <div class="pop_content pop2" style="display:none">
        <h1 class="tit"><spring:message code="coupon.already"/></h1>
        <p><spring:message code="coupon.already.text"/></p>
        <a href="#" class="btn_check"><spring:message code="coupon.popup.close"/></a>
    </div>
    <div class="pop_content pop3" style="display:none">
        <h1 class="tit"><spring:message code="coupon.notExist"/></h1>
        <p><spring:message code="coupon.invalid.text"/></p>
        <a href="#" class="btn_check"><spring:message code="coupon.popup.close"/></a>
    </div>
    <div class="pop_content pop4" style="display:none">
        <h1 class="tit"><spring:message code="coupon.invalid.period"/></h1>
        <a href="#" class="btn_check"><spring:message code="coupon.popup.close"/></a>
    </div>
    <div class="pop_content pop5" style="display:none">
        <h1 class="tit"><spring:message code="coupon.invalid.used"/></h1>
        <p><spring:message code="coupon.try.listen"/></p>
        <a href="#" class="btn_check"><spring:message code="coupon.popup.close"/></a>
    </div>
    <div class="pop_content pop6" style="display:none">
        <h1 class="tit"><spring:message code="coupon.invalid.length"/></h1>
        <p><spring:message code="coupon.try.listen"/></p>
        <a href="#" class="btn_check"><spring:message code="coupon.popup.close"/></a>
    </div>
    <div class="pop_content pop7" style="display:none">
        <h1 class="tit"><spring:message code="coupon.exception.system"/></h1>
        <p><spring:message code="coupon.tryagain"/></p>
        <a href="#" class="btn_check"><spring:message code="coupon.popup.close"/></a>
    </div>
    <div class="pop_content pop8" style="display:none">
        <h1 class="tit"><spring:message code="coupon.invalid.member"/></h1>
        <p><spring:message code="coupon.contact.admin"/></p>
        <a href="#" class="btn_check"><spring:message code="coupon.popup.close"/></a>
    </div>
    <div class="pop_content pop9" style="display:none">
        <h1 class="tit"><spring:message code="coupon.under.condition"/></h1>
        <p><spring:message code="coupon.condition.info"/></p>
        <a href="#" class="btn_check"><spring:message code="coupon.popup.close"/></a>
    </div>
</div>
