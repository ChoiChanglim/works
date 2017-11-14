<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="${LanguageEnum.htmlLang }">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0">
<meta name="format-detection" content="telephone=no">
<title><spring:message code="base.game.title" /></title>
<link rel="stylesheet" type="text/css" href="${info.contextPath }${CSS_FILE_NAME }">
<script src="${info.contextPath }/resources/js/jquery-1.11.3.min.js"></script>
<script src="${info.contextPath }/resources/js/common.js"></script>
<script>
jQuery(document).ready(function(){
	jQuery(".btn_ly_ok, .btn_no").click(function(e){
        e.preventDefault();
        jQuery('.ly_pop,.dimmed').hide();
    })
    
	jQuery(".rewardItemBtn").on({
		"click": function(e){
			var classes = jQuery(this).attr("class");
			var codeAndCount = this.id.split("^");
			var item_code = codeAndCount[0];
			var req_cnt = codeAndCount[1];
			var flag = classes.includes("on");
			if(flag == true){
				$('.ly_pop').hide();$('.ly_pop:eq(1),.dimmed').show();
				jQuery(".req_flower_count").text(req_cnt);
				jQuery(".btn_ok").off("click.realbtn");
				jQuery(".btn_ok").on({
	                "click.realbtn":function(){
	                	entry(item_code);
	                }
	            });
			}
		}
	})
	var getItemName = function(key){
		if(key == "GLOBAL_ENTRY1"){
			return "<spring:message code='event.flower.GLOBAL_ENTRY1'/>";
		}
		if(key == "GLOBAL_ENTRY2"){
            return "<spring:message code='event.flower.GLOBAL_ENTRY2'/>";
        }
		if(key == "GLOBAL_ENTRY3"){
            return "<spring:message code='event.flower.GLOBAL_ENTRY3'/>";
        }
		if(key == "GLOBAL_ENTRY4"){
            return "<spring:message code='event.flower.GLOBAL_ENTRY4'/>";
        }
		if(key == "KOREAN_ENTRY1"){
            return "<spring:message code='event.flower.KOREAN_ENTRY1'/>";
        }
		if(key == "KOREAN_ENTRY2"){
            return "<spring:message code='event.flower.KOREAN_ENTRY2'/>";
        }
		if(key == "KOREAN_ENTRY3"){
            return "<spring:message code='event.flower.KOREAN_ENTRY3'/>";
        }
		if(key == "KOREAN_ENTRY4"){
            return "<spring:message code='event.flower.KOREAN_ENTRY4'/>";
        }
		if(key == "RECEIPT1"){
            return "<spring:message code='event.flower.RECEIPT1'/>";
        }
		if(key == "RECEIPT2"){
            return "<spring:message code='event.flower.RECEIPT2'/>";
        }
		if(key == "RECEIPT3"){
            return "<spring:message code='event.flower.RECEIPT3'/>";
        }
		if(key == "RECEIPT4"){
            return "<spring:message code='event.flower.RECEIPT4'/>";
        }
		if(key == "RECEIPT5"){
            return "<spring:message code='event.flower.RECEIPT5'/>";
        }
	}
	
	var mywinlist = function(obj){
		//console.log(obj);
		if(obj.myWinCount > 0){
			jQuery("div.no").hide();
			jQuery("ul.prizebox").show();
			jQuery("ul.prizebox").empty();
        }
		
        var idx = 0;
        jQuery.each(obj.mywin, function(key, value) {
            idx++;
            var li = "<li class='prize_"+idx+"'><em>"+getItemName(key)+"</em><span>x"+value+"</span></li>";
            //console.log(li);
            jQuery("ul.prizebox").append(li);
        })
        //console.log(jQuery("ul.prizebox"));
        
        //on 제거
        jQuery(".rewardItemBtn").each(function(){
            var keyAndValidArr = this.id.split("^");
            if(keyAndValidArr.length > 1){
                var key = keyAndValidArr[0];
                var validCount = keyAndValidArr[1];
                if(obj.flowerCount < validCount){
                    jQuery(this).removeClass("on");
                }
            }
        })
        
	}
	var entry = function(item_code){
        var url = "${info.contextPath }/event/flower_entry.json";
        var local = "${LanguageEnum.name() }";
        var param = "usn="+"${usn }";
        param = param+"&item_code="+item_code;
        param = param+"&local="+local;
        //console.log(param);
        getJson(url, "get", param, function(data){
            $('.ly_pop').hide();$('.ly_pop_gif,.dimmed').show();
            jQuery(".count_num").text(numberWithCommas(data.flowerCount));
            //console.log(data);
            
            
            if(data.result == "ITEM_SEND_SUCCESS"){
            	setTimeout(function(){
            		mywinlist(data);
            		$('.ly_pop').hide();$('.ly_pop_gif').hide();
            		$('.ly_pop').hide();$('.ly_pop:eq(3)').show();
                }, data.trendTime);
            	
            	return false;
            	
            }else if(data.result == "ITEM_ENTRY_SUCCESS"){
            	setTimeout(function(){
            		mywinlist(data);
            		jQuery("#entry_item_name").text(getItemName(data.item_code));
                    $('.ly_pop').hide();$('.ly_pop_gif').hide();
                    $('.ly_pop').hide();$('.ly_pop:eq(4)').show();
                }, data.trendTime);
            	return false;
            	
            }else if(data.result == "NOT_ENOUGH_FLOWER"){
            	mywinlist(data);
            	$('.ly_pop').hide();$('.ly_pop_gif').hide();
            	$('.ly_pop').hide();$('.ly_pop:eq(0)').show();
            	return false;
                
            }else{
            	setTimeout(function(){
            		mywinlist(data);
                    $('.ly_pop').hide();$('.ly_pop_gif').hide();
                    $('.ly_pop').hide();$('.ly_pop:eq(2)').show();
                }, data.trendTime);
            	return false;
            }
        });
    }
	
	//timer
    var Clock = function(now, remainTime) {
        this.now = now
        this.remainTime = remainTime+1000; 
    }
    Clock.prototype.flowTimer = function() {
        this.remainTime = this.remainTime - 1000;
    }
    Clock.prototype.setRemainTime = function(remainTime) {
        this.remainTime = remainTime;
    }
    
    var timer = function(){
        if(uClock.remainTime > 1000){
            setTimeout(timer, 1000);
            uClock.flowTimer();
        }
        jQuery(".timeleft").text(msToTime(uClock.remainTime));
    }
    var now = parseInt("${now}");
    var nextTime = parseInt("${remainTime}");
    
    var uClock = new Clock(now, nextTime);
    timer();
});
function msToTime(duration) {
    var milliseconds = parseInt((duration%1000)/100)
        , seconds = parseInt((duration/1000)%60)
        , minutes = parseInt((duration/(1000*60))%60)
        , hours = parseInt((duration/(1000*60*60))%24);

    hours = (hours < 10) ? "" + hours : hours;
    minutes = (minutes < 10) ? "0" + minutes : minutes;
    seconds = (seconds < 10) ? "0" + seconds : seconds;
    
    if(hours+minutes+seconds == 0){
        location.reload(true);
    }
    
    return hours+"<spring:message code='notice.remain_h'/>"+minutes+"<spring:message code='notice.remain_m'/>";
    
} 

</script>
</head>

<body>

<div id="wrap">
    <div class="header">
        <div class="date">
            <spring:message code="notice.remain_preffix"/>${remainDay } <spring:message code="notice.remain_d"/> <span class="timeleft">HH:MM</span> <spring:message code="notice.remain_text"/>
        </div>
        <!--<div class="date">あとDD日HH時間MM分</div>-->
        <!--<div class="date">還剩DD天HH:MM</div>-->
        <!--<div class="date">เหลืออีก DD วัน ชม:นาที</div>-->
        <div class="header_bg"></div>
    </div>
    <div class="container">
        <div class="inner">
        <!--꽃잎 1~4자리일 경우 class명 "count_area"-->
        <!--꽃잎 5자리 이상일 경우 class명 "count_area_over"-->
        <c:choose>
            <c:when test="${flowerCount lt 9999}"><c:set var="countClass" value="count_area"/></c:when>
            <c:otherwise><c:set var="countClass" value="count_area_over"/></c:otherwise>
        </c:choose>
        <div class="${countClass }">
            <ul class="count_inner">
                <li class="count_left"></li>
                <li class="count_num"><fmt:formatNumber>${flowerCount }</fmt:formatNumber></li>
                <li class="count_right"></li>
            </ul>
        </div>
        <div class="reward_area">
            <div class="g_area">
                <c:forEach items="${EntryItemList }" varStatus="status" var="entry">
                    <div id="${entry }^${entry.flower}" class="gift_${status.count } rewardItemBtn <c:if test="${entry.flower <= flowerCount }">on</c:if>">
	                    <span class="ico_reward" style="display:block"></span>
	                </div>
                </c:forEach>
            </div>
            <div class="i_area">
                <c:forEach items="${ReceiptItemList }" varStatus="status" var="receipt">
                    <div id="${receipt }^${receipt.flower}" class="ingame_${status.count } rewardItemBtn <c:if test="${receipt.flower <= flowerCount }">on</c:if>">
                        <span class="ico_reward" style="display:block"></span>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="prizearea">
            <div class="inner">
	            <div class="prize_title"></div>
	            
	            <!--당첨 내역 추가 클래스명 prize_N-->
	            <ul class="prizebox">
		            <c:if test="${fn:length(mywin) > 0}">
		                <c:forEach items="${mywin }" var="win" varStatus="status">
		                    <li class="prize_${status.count }"><em><spring:message code="event.flower.${win.key }"/></em><span>x${win.value }</span></li>
		                </c:forEach>
		            </c:if>
	            </ul>
	            
	            <!--미당첨-->
	            <div class="no">
		            <c:if test="${fn:length(mywin) == 0}">
		                <p class="tt"><spring:message code="event.flower.nowinner"/>
		                <br><spring:message code="event.flower.notice1"/> <spring:message code="event.flower.notice2"/></p>
		            </c:if>
	            </div>
            </div>
        </div>
        </div>
    </div>
    <!--한국만-->
    <c:if test="${LanguageEnum.name() eq 'KR' }">
	    <div class="footer_area">
	        <div class="footer_bg"></div>
	        <div class="notice">
	            <div class="statement">당첨자 발표 <em>2017.05.09(화)</em> 이후</div>
	            <ul>
	                <li class="red">당첨자 발표 후 7일 이내에 배송 정보 등을 입력하지 않을 경우, 경품이 반송될 경우 당첨이 취소됩니다.</li>
	                <li>경품은 경우에 따라 변경될 수 있으며, 경품 이미지는 실물과 다를 수 있습니다.</li>
	                <li class="blue">백화점 상품권, 벅스뮤직 상품권, 요거프레소 상품권 등은 모바일 상품권으로 발송됩니다.
	                <br><em>스팸 수신거부시 문자를 받지 못할 수 있으니 유의하세요</em></li>
	                <li>휴대폰, 백화점 상품권(10만원)의 경우 제세공과금은 당첨자 본인이 부담하셔야 합니다.</li>
	                <li>휴대폰의 배송지는 한국으로 제한됩니다.</li>
	            </ul>
	        </div>
	    </div>
    </c:if> 
    </div>
    <!-- popup -->
    <!-- [D] 팝업 노출시 display:block으로 노출 -->
    <div class="dimmed" style="display:none"></div>
    <!--* 꽃잎 개수가 모자를 때 0-->
    <div class="ly_pop" style="display:none">
        <div class="ly_cont">
            <p class="ly_tit"><spring:message code="event.flower.not_enough1"/></p>
            <p class="ly_txt"><spring:message code="event.flower.not_enough2"/></p>     
            <a href="#" class="btn_ly_ok"><p><spring:message code="event.common.comfirm"/></p></a>
        </div>
    </div>
    <!--* 응모 재확인용 1-->
    <div class="ly_pop" style="display:none">
        <div class="ly_cont">
            <p class="ly_tit"><spring:message code="event.flower.apply1"/></p>
            <p class="ly_txt"><spring:message code="event.flower.apply2"/></p>
            <div class="btn_area">
                <a href="#" onclick="return false;" class="btn_no"><p><spring:message code="event.common.cancel"/></p></a>    
                <a href="#" onclick="return false;" class="btn_ok"><p><spring:message code="event.common.comfirm"/></p></a>
            </div>
        </div>
    </div>
    <!--* 당첨 실패 2-->
    <div class="ly_pop" style="display:none">
        <div class="ly_cont">
            <p class="ly_tit"><spring:message code="event.flower.lose1"/></p>
            <p class="ly_txt"><spring:message code="event.flower.lose2"/></p>        
            <a href="#" class="btn_ly_ok"><p><spring:message code="event.common.comfirm"/></p></a>
        </div>
    </div>
    <!--* 즉시지급 상품 당첨 3-->
    <div class="ly_pop" style="display:none">
        <div class="ly_cont">
            <p class="ly_tit"><spring:message code="event.common.winner1"/></p>
            <p class="ly_txt"><spring:message code="event.common.winner2"/></p>      
            <a href="#" class="btn_ly_ok"><p><spring:message code="event.common.comfirm"/></p></a>
        </div>
    </div>
    
    <!--한국만-->
    <!--* 응모형 상품 게임  당첨 4-->
    <div class="ly_pop" style="display:none">
        <div class="ly_cont">
            <p class="ly_tit">축하합니다!</p>
            <p class="ly_txt">[<label id="entry_item_name">상품명</label>] 에 당첨 되었습니다!</p>        
            <a href="#" class="btn_ly_ok"><p><spring:message code="event.common.comfirm"/></p></a>
        </div>
    </div>
    
    <!--* 결과 노출전 GIF-->
    <div class="ly_pop_gif" style="display:none">
    <div class="ly_cont_gif">
        <div class="pop_gif"></div>
        <p class="gif_tit"><spring:message code="event.flower.trendmsg"/></p>
    </div>
</div>
</body>
</html>
