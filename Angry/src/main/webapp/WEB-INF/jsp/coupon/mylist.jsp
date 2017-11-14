<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script>
function paging(pagenum){
    jQuery("input[name=page]").val(pagenum);
    jQuery("#mylistform").submit();
}
</script>
<form id="mylistform">
<input type="hidden" name="page" />
</form>
<div id="wrap" class="view">
    <div class="content">
        <div class="coupon_view">
            <h1 class="tit_cpn"><spring:message code="coupon.mylist.title"/></h1>
            <div class="lst_cpn">
                <ul>
	                <li><p><span class="cpn_date"><spring:message code="coupon.mylist.date"/></span><span class="cpn_item"><spring:message code="coupon.mylist.ename"/></span><span class="cpn_nb"><spring:message code="coupon.mylist.serial"/></span></p></li>
	                <c:forEach var="list" items="${list}" varStatus="status">
	                    <c:set var="cnumStartIndex" value="${fn:length(list.cnum)-4}" />
	                    <c:set var="cnumEndIndex" value="${fn:length(list.cnum)}" />
	                    <li><p><span class="cpn_date"><fmt:formatDate value="${list.regdate }" pattern="yy.MM.dd" /><br>(<fmt:formatDate value="${list.regdate }" pattern="HH:mm" />)</span><span class="cpn_item">${list.eventName }</span><span class="cpn_nb">${fn:substring(list.cnum, cnumStartIndex, cnumEndIndex) }</span></p></li>
	                </c:forEach>
	                <c:if test="${fn:length(list) == 0 }"><li style="text-align: center;padding-top:7px;"><spring:message code="coupon.mylist.nocoupon"/></li></c:if>
                </ul>
                
            </div>
            <a href="${info.contextPath }/coupon/regist.nhn" class="btn btn_cpnentry"><spring:message code="coupon.mylist.coupon"/></a>
            ${paging.getHTML() }
        </div>
    </div>
</div>