<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div id="wrap">
    <div class="content main">
        <div class="notice">
            <ul>
                <c:forEach var="top" items="${topList}" varStatus="status">
	                <li class="lst_ntc">
		                <a href="${top.finalLink }"><p><em class="category_notice"><img src="http://images.hangame.co.kr/toast/s629/woopang/web_resource/notice/${top.iconNameByType2 }_icon.png" alt=""></em>${top.title}</p></a>
		                <div class="line"></div>
	                </li>
                </c:forEach>
            </ul>
        </div>
        <div class="event">
            <div class="lst_bn">
                <ul>
                <c:forEach var="bottom" items="${bottomList}" varStatus="status">
                    <li><a href="${bottom.finalLink }"><img src="${bottom.imgaddr }" alt=""></a></li>
                </c:forEach>
                </ul> 
            </div>
        </div>
    </div>
       <div class="footer">
           <a href="#">
	           <div class="top">
	                <div class="start_bg"></div>
	                <p><spring:message code="base.notice.top"/></p>
	                <div class="end_bg"></div>
	           </div>
           </a>
       </div>
</div>
