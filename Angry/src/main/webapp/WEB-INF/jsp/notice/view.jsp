<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script>
    jQuery(document).ready(function() {
        jQuery("a.false-a").click(function(e) {
            e.preventDefault();
        })
        
        
        var Clock = function(now, remainTime) {
            this.now = now
            this.remainTime = remainTime+100;
        }
        Clock.prototype.flowTimer = function() {
            this.remainTime = this.remainTime - 1000;
        }
        
        var timer = function(){
            if(uClock.remainTime > 1000){
                setTimeout(timer, 1000);
                uClock.flowTimer();
            }
            jQuery(".timer").text(msToTime(uClock.remainTime));
        }
        
        var status = "${Status}";
        if(status == "REMAIN_TIME"){
            var now = ${now};
            var remainTime = ${remainTime};
            var uClock = new Clock(now, remainTime);
            timer();    
        }
        
    });
    function msToTime(duration) {
        var milliseconds = parseInt((duration%1000)/100)
            , seconds = parseInt((duration/1000)%60)
            , minutes = parseInt((duration/(1000*60))%60)
            , hours = parseInt((duration/(1000*60*60))%24);

        hours = (hours < 10) ? "" + hours : hours;
        minutes = (minutes < 10) ? "0" + minutes : minutes;
        seconds = (seconds < 10) ? "0" + seconds : seconds;
        
        //return hours + ":" + minutes + ":" + seconds + "." + milliseconds;
        if(hours == 0){
        	return minutes + '<spring:message code="notice.remain_m"/> ' + seconds+'<spring:message code="notice.remain_s"/>';    
        }else{
        	return hours + '<spring:message code="notice.remain_h"/> '+ minutes + '<spring:message code="notice.remain_m"/>';	
        }
        
    }
</script>
<div id="wrap">
    <div class="content">
        <div class="view">
            <div class="board">
                <ul>
                <li class="category">
	                <p><em class="category_event"><spring:message code="base.notice.title"/></em>${notice.title }</p>
	                <span class="date"><fmt:formatDate value="${notice.regdate }" pattern="yyyy.MM.dd"/></span>
	                <div class="line"></div>
                </li>
                <li class="content_text">${notice.content }</li>
                </ul>
            </div>
            
        </div>
    </div>
        <div class="footer">
        
        <a href="#">
            <div class="top">
                <div class="start_bg"></div>
                <p>TOP</p>
                <div class="end_bg"></div>
            </div>
        </a>
        </div>
    </div>
<%-- <div id="wrap">
	<div class="content">
		<div class="view">
			<div class="board">
				<ul>
					<li class="category">
						<p>
							<em class="category_notice"><img src="http://images.hangame.co.kr/toast/s629/woopang/web_resource/notice/notice_icon.png" alt=""></em>${notice.title }
						</p>
						<div class="line"></div>
						<c:if test="${Status eq 'NOT_ALLOWED_VIEW' == false}">
							<p>
								<em class="category_notice"><img src="http://images.hangame.co.kr/toast/s629/woopang/web_resource/notice/time_icon.png" alt=""></em>
								<c:choose>
	                                <c:when test="${Status eq 'NOT_ALLOWED_VIEW'}"></c:when>
	                                <c:when test="${Status eq 'EVENT_END'}"><spring:message code="notice.end"/></c:when>
	                                <c:when test="${Status eq 'INVALID_PERIOD'}"></c:when>
	                                <c:when test="${Status eq 'REMAIN_INFINITE'}"></c:when>
	                                <c:when test="${Status eq 'REMAIN_DATE'}">
	                                    Time left :  ${remainDays }<spring:message code="notice.remain_d"/> ${remainHour }<spring:message code="notice.remain_h"/>
	                                </c:when>
	                                <c:when test="${Status eq 'REMAIN_TIME'}">
	                                    Time left :  <span class="timer"></span> 
	                                </c:when>
	                                
	                            </c:choose>
							</p>
							<div class="line"></div>
						</c:if>
					</li>
					<li class="content_text">
					   ${notice.content }
					</li>
				</ul>
			</div>

		</div>
	</div>
	
	<div class="footer">
        <a href="${info.contextPath }/notice/main.nhn">
            <div class="list">
            <div class="start_bg"></div>
                <p><spring:message code="base.notice.tomain"/></p>
                <div class="end_bg"></div>
            </div>
        </a>
        <a href="#">
            <div class="top">
                <div class="start_bg"></div>
                <p><spring:message code="base.notice.top"/></p>
                <div class="end_bg"></div>
            </div>
        </a>
    </div>
	
</div> --%>