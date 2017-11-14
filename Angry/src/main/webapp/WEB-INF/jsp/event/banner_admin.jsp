<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0">
<meta name="format-detection" content="telephone=no">
<title><spring:message code="base.game.title" /></title>
<script src="${info.contextPath }/resources/js/jquery-1.11.3.min.js"></script>
<script src="${info.contextPath }/resources/js/common.js"></script>
<script>
function add(){
	var url="/event/banner_item_insert.json";
	var prob = jQuery("input[name=prob_ins]").val();
	if(prob == ""){
		alert("확률을 입력해주세요.");
		return;
	}
	var descript = jQuery("input[name=descript_ins]").val();
    if(descript == ""){
        alert("당첨 아이템을 입력해주세요.");
        return;
    }
    var itemSerial = jQuery("input[name=itemSerial_ins]").val();
    if(itemSerial == ""){
        alert("아이템 시리얼을 입력해주세요.");
        return;
    }
    var itemType = jQuery("select[name=itemType_ins]").val();
    if(itemType == ""){
        alert("아이템 타입을 선택해주세요.");
        return;
    }
    var itemCount = jQuery("input[name=itemCount_ins]").val();
    if(itemCount == ""){
        alert("아이템 갯수를 입력해주세요.");
        return;
    }
    var used = jQuery("select[name=used_ins]").val();
	var param = "prob="+prob;
    param = param+"&descript="+descript;
    param = param+"&itemSerial="+itemSerial;
    param = param+"&itemType="+itemType;
    param = param+"&itemCount="+itemCount;
    param = param+"&used="+used;
    
    //console.log(param);
	getJson(url, "post", param, function(data){
		location.reload(true);
	});
}
function update(itemKey){
	var url="/event/banner_item_update.json";
	
    var prob = jQuery(".item"+itemKey).find("input[name=prob]").val();
    if(prob == ""){
        alert("확률을 입력해주세요.");
        return;
    }
    var descript = jQuery(".item"+itemKey).find("input[name=descript]").val();
    if(descript == ""){
        alert("당첨 아이템을 입력해주세요.");
        return;
    }
    var itemSerial = jQuery(".item"+itemKey).find("input[name=itemSerial]").val();
    if(itemSerial == ""){
        alert("아이템 시리얼을 입력해주세요.");
        return;
    }
    var itemType = jQuery(".item"+itemKey).find("select[name=itemType]").val();
    if(itemType == ""){
        alert("아이템 타입을 선택해주세요.");
        return;
    }
    var itemCount = jQuery(".item"+itemKey).find("input[name=itemCount]").val();
    if(itemCount == ""){
        alert("아이템 갯수를 입력해주세요.");
        return;
    }
    var used = jQuery(".item"+itemKey).find("select[name=used]").val();
    var param = "itemKey="+itemKey;
    param = param+"&prob="+prob;
    param = param+"&descript="+descript;
    param = param+"&itemSerial="+itemSerial;
    param = param+"&itemType="+itemType;
    param = param+"&itemCount="+itemCount;
    param = param+"&used="+used;
    
    //console.log(param);
    getJson(url, "post", param, function(data){
        location.reload(true);
    });
}


function update_img(itemKey, local){
	
}
function insert_img(){
	var url="/event/banner_img_insert.json";
    var itemKey = jQuery("select[name=itemKey_img_ins]").val();
    if(itemKey == ""){
        alert("아이템 키를 입력해주세요.");
        return;
    }
    var local = jQuery("select[name=local_img_ins]").val();
    if(local == ""){
        alert("언어를 입력해주세요.");
        return;
    }
    var imgurl = jQuery("input[name=url_img_ins]").val();
    if(imgurl == ""){
        alert("이미지 주소를 입력해주세요.");
        return;
    }
    var param = "itemKey="+itemKey;
    param = param+"&local="+local;
    param = param+"&imgurl="+imgurl;
    getJson(url, "post", param, function(data){
        location.reload(true);
    });
}

function deleteResult(sno){
	var url="/event/deleteResult.json";
	var param = "sno="+sno;
    getJson(url, "post", param, function(data){
        location.reload(true);
    });
}
function deleteImg(itemKey, local){
    var url="/event/deleteImg.json";
    var param = "itemKey="+itemKey;
    param = param+"&local="+local;
    getJson(url, "post", param, function(data){
        location.reload(true);
    });
}

</script>
<style>
.line{border:1px #000 dotted;width:100%;margin:40px 0 40px 0}
</style>
<body>
<h3>실시간 적용중인 상품목록</h3>
<table border=1>
    <tr>
        <th>아이템 키</th>
        <th>당첨 확률</th>
        <th>당첨 아이템</th>
        <th>국가</th>
        <th>지급 아이템 시리얼</th>
        <th>지급 아이템 타입</th>
        <th>지급 아이템 갯수</th>
        <th>이미지 주소</th>
    </tr>
    <c:forEach items="${liveItemAndUrlList }" varStatus="status" var="live">
        <tr class="item${live.itemKey }">
            <td>${live.itemKey }</td>
            <td>${live.prob }</td>
            <td>${live.descript }</td>
            <td>${live.local }</td>
            
            <td>${live.itemSerial }</td>
            <td>
                <select name="itemType_live">
                <c:forEach items="${RewardTypeList }" var="rtype">
                    <option value="${rtype.no }" <c:if test="${rtype.no == live.itemType }">selected</c:if>>${rtype.descript }</option>
                </c:forEach>
                </select>
            </td>
            <td>${live.itemCount }</td>
            <td>${live.url }</td>
        </tr>
    </c:forEach>
</table>
<p class="line"></p>
<h3>배너 이벤트 아이템 수정</h3>
<table border=1>
    <tr>
        <th>아이템 키</th>
        <th>당첨 확률</th>
        <th>당첨 아이템</th>
        <th>사용 여부</th>
        <th>지급 아이템 시리얼</th>
        <th>지급 아이템 타입</th>
        <th>지급 아이템 갯수</th>
        <th>수정</th>
    </tr>
    <c:forEach items="${adminBannerList }" varStatus="status" var="banner">
	    <tr class="item${banner.itemKey }">
	        <td>${banner.itemKey }</td>
	        <td><input type="text" name="prob" value="${banner.prob }" /></td>
	        <td><input type="text" name="descript" value="${banner.descript }" /></td>
	        <td>
	            <select name="used">
	               <option value="T" <c:if test="${banner.used eq 'T'}">selected</c:if>>O</option>
	               <option value="F" <c:if test="${banner.used eq 'F'}">selected</c:if>>X</option>
	            </select>
            </td>
	        <td><input type="text" name="itemSerial" value="${banner.itemSerial }" /></td>
	        <td>
	            <select name="itemType">
	            <c:forEach items="${RewardTypeList }" var="rtype">
	                <option value="${rtype.no }" <c:if test="${rtype.no == banner.itemType }">selected</c:if>>${rtype.descript }</option>
	            </c:forEach>
	            </select>
            </td>
	        <td><input type="text" name="itemCount" value="${banner.itemCount }" /></td>
	        <td><button onclick="update('${banner.itemKey }')">수정</button></td>
	    </tr>
    </c:forEach>
</table>

<br>
<h3>배너 이벤트 아이템 등록</h3>
<table border=1>
    <tr>
        <th>당첨 확률</th>
        <th>당첨 아이템</th>
        <th>사용 여부</th>
        <th>지급 아이템 시리얼</th>
        <th>지급 아이템 타입</th>
        <th>지급 아이템 갯수</th>
    </tr>
        <tr>
            <td><input type="text" name="prob_ins" value="" /></td>
            <td><input type="text" name="descript_ins" value="" /></td>
            <td>
                <select name="used_ins">
                   <option value="T" <c:if test="${banner.used eq 'T'}">selected</c:if>>O</option>
                   <option value="F" <c:if test="${banner.used eq 'F'}">selected</c:if>>X</option>
                </select>
            </td>
            <td><input type="text" name="itemSerial_ins" value="" /></td>
            <td>
                <select name="itemType_ins">
                <c:forEach items="${RewardTypeList }" var="rtype">
                    <option value="${rtype.no }">${rtype.descript }</option>
                </c:forEach>
                </select>
            </td>
            <td><input type="text" name="itemCount_ins" value="" /></td>
        </tr>
</table>
<button onclick="add()">아이템 등록</button>
<p class="line"></p>

<h3>배너 이미지 목록</h3>
<table border=1>
    <tr>
        <th>아이템</th>
        <th>언어</th>
        <th>이미지 주소</th>
        <th>삭제</th>
    </tr>
        <c:forEach items="${adminBannerItemImgList }" var="img">
        <tr>
            <td>
                <select name="itemKey_img">
                <c:forEach items="${liveItemList }" var="liveItem">
                    <option value="${liveItem.itemKey }" <c:if test="${liveItem.itemKey == img.itemKey}">selected</c:if>>${liveItem.descript }</option>
                </c:forEach>
                </select>
            </td>
            <td>
                <select name="local_img">
                <c:forEach items="${LanguageList }" var="lang">
                    <option value="${lang.name() }" <c:if test="${lang.name() eq img.local}">selected</c:if>>${lang.descript }</option>
                </c:forEach>
                </select>
            </td>
            <td><input type="text" name="url_img" value="${img.url }" style="width:500px;"/></td>
            <td><button onclick="deleteImg('${img.itemKey }', '${img.local }')">삭제</button></td>
        </tr>
        </c:forEach>
</table>
<br>
<h3>배너 이미지 등록</h3>
<table border=1>
    <tr>
        <th>아이템</th>
        <th>언어</th>
        <th>이미지 주소</th>
    </tr>
        <tr>
            <td>
                <select name="itemKey_img_ins">
                <c:forEach items="${liveItemList }" var="liveItem">
                    <option value="${liveItem.itemKey }" <c:if test="${liveItem.itemKey == img.itemKey}">selected</c:if>>${liveItem.descript }</option>
                </c:forEach>
                </select>
            </td>
            <td>
                <select name="local_img_ins">
                <c:forEach items="${LanguageList }" var="lang">
                    <option value="${lang.name() }" <c:if test="${lang.name() eq img.local}">selected</c:if>>${lang.descript }</option>
                </c:forEach>
                </select>
            </td>
            <td><input type="text" name="url_img_ins" value="" style="width:500px;"/></td>
        </tr>
</table>
<button onclick="insert_img()">이미지 등록</button>
<p class="line"></p>


<h3>배너 이벤트 오늘 참여자</h3>
<table border=1>
    <tr>
        <th>회원번호</th>
        <th>참여일</th>
        <th>당첨상품번호</th>
        <th>국가</th>
        <th>삭제</th>
    </tr>
    <c:forEach items="${adminBannerResultList }" varStatus="status" var="result">
        <tr>
            <td>${result.sno }</td>
            <td><fmt:formatDate value="${result.day }" pattern="yyyy-MM-dd"/> </td>
            <td>
                <c:choose>
                    <c:when test="${result.itemKey == 0 }">꽝</c:when>
                    <c:otherwise>${result.itemKey}</c:otherwise>
                </c:choose>
            </td>
            <td>${result.local }</td>
            <td><button onclick="deleteResult('${result.sno }')">삭제</button></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>