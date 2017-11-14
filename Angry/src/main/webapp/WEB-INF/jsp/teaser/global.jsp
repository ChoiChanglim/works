<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html lang="${LanguageEnum.htmlLang }">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title><spring:message code="base.game.title" /></title>
<meta property="fb:app_id" content="1661537384082631" />
<meta property="og:type" content="website" /> 
<meta name="og.url" property="og:url" content="<spring:message code="base.game.facebook_url" />" /> 
<meta name="og.title" property="og:title" content="<spring:message code="base.game.title" />"/>
<meta name="og.description" property="og:description" content="<spring:message code="base.game.description" />"/>
<meta name="og.caption" property="og:caption" content="<spring:message code="base.game.facebook_caption" />"/>
<meta name="og.image" property="og:image" content="<spring:message code="base.game.facebook_img" />"/>

<link rel="stylesheet" type="text/css" href="${info.contextPath }${CSS_FILE_NAME }">
<link rel="shortcut icon" type="image/x-icon" href="${info.contextPath }/resources/favicon.ico" />
<script src="${info.contextPath }/resources/js/jquery-1.11.3.min.js"></script>
<script src="${info.contextPath }/resources/js/common.js"></script>
<script src="${info.contextPath }/resources/js/teaser/teaser.js"></script>
<script src="${info.contextPath }/resources/js/bootstrap.min.js"></script>
<script src="${info.contextPath }/resources/js/jquery.bxslider.min.js"></script>

<script>
  window.fbAsyncInit = function() {
    FB.init({
      appId      : '1661537384082631',
      xfbml      : true,
      version    : 'v2.8'
    });
    FB.AppEvents.logPageView();
  };

  (function(d, s, id){
     var js, fjs = d.getElementsByTagName(s)[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement(s); js.id = id;
     js.src = "//connect.facebook.net/en_US/sdk.js";
     fjs.parentNode.insertBefore(js, fjs);
   }(document, 'script', 'facebook-jssdk'));
</script>
<script>
(function(){ 
	authToken = new AuthToken();
    userInfo = new UserInfo("${LanguageEnum }", "${regist_key}");
}());
jQuery(document).ready(function(){
	var device = "${Device}";
    if(device == 'NORMAL'){
        jQuery(".dimmed").show();
        jQuery(".ly_mov").show();
        return false;
    }
})

</script>
</head>
<body data-spy="scroll" data-target="#gnbspy" data-offset="20">

<div class="wrap">
    <div class="header">
    <div class="inner">
        <div class="top_menu">
            <!-- [D] 언어선택-->
            <div class="select_area">
                <a href="#" class="selected">${LanguageEnum.descript } <span class="blind">(Selected)</span><span class="ico_arrow">Select Language</span></a>
                <ul class="select_lst">
                    <c:forEach items="${LanguageEnumList }" var="supportLanguage">
                        <c:if test="${fn:toLowerCase(supportLanguage) ne local}">
                            <li><a href="/${fn:toLowerCase(supportLanguage)}">${supportLanguage.descript }</a></li>
                        </c:if>            
                    </c:forEach>
                </ul>
            </div>
        </div>
        <nav id="gnbspy">
            <ul class="gnb nav">
                <!-- [D] 메뉴 활성화 시 li에 .on 클래스 추가 -->
                <li class="blank1"><a href="#"></a></li>
                <li class="menu1"><a href="#sec_download"></a></li>
                <li class="menu2"><a href="#sec_sns"></a></li>
                <li class="menu3"><a href="#sec_overview"></a></li>
                <li class="blank2"><a href="#"></a></li>
            </ul>
        </nav>
        <div class="notice" id="browser_notice">
            <div class="tt_area">
                <span class="tt"><spring:message code='preregist.browser.notice' /></span>
                <div class="cls"></div>
            </div>
        </div>
    </div>
    <div class="gnb_bg">
        <div class="blank"></div>
        <div class="gnb_bg_l"></div>
        <div class="gnb_bg_r"></div>
        <div class="blank"></div>
    </div>
    </div>
    <!-- container -->
    <div class="container">
        <div class="spot">
            <a href="#" class="btn_video"></a>
        </div>
        <div class="download">
            <div class="inner">
            <span id="sec_download"></span>
                <div class="d_area">
                    <a href="${AosDownUrl }" target="_blank"><div class="aos"></div></a>
                    <a href="${IosDownUrl }" target="_blank"><div class="ios"></div></a>
                </div>
                <div class="s_area">
                    <c:if test="${Device eq 'MOBILE' }">
                        <div class="line"><span class="line-text blind"><spring:message code="base.game.facebook_caption" /> http://hgurl.me/bXR</span></div>
                    </c:if>
                    <c:choose>
                        <c:when test="${local eq 'jp' }">
                            <div class="twitter"></div>
                            <div class="facebook"></div>
                        </c:when>
                        <c:otherwise>
                            <div class="facebook"></div>
                            <div class="twitter"></div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
        <div class="sec_sns">
            <div class="inner">
            <span id="sec_sns"></span>
                <div class="pig2"></div>
                <!--일본의 경우 t_time-line-embed로 교체-->
                <c:choose>
                    <c:when test="${local eq 'jp' }">
                        <div class="t_time-line-embed"><!-- ABIslands -->
		                    <a class="twitter-timeline" href="https://twitter.com/ABIslands">Tweets by ABIslands</a> <script async src="//platform.twitter.com/widgets.js" charset="utf-8"></script>
		                </div>
		                <div class="btn_area">
		                    <a href="${TWFanPage.link}" class="follow_t"></a>
		                    <a href="${FBFanPage.link}" class="like_f"></a>
		                </div>
                    </c:when>
                    <c:otherwise>
                        <div class="f_time-line-embed">
	                        <div class="fb-page" 
			                    data-href="https://www.facebook.com/AngryBirdsIslands" 
			                    data-tabs="timeline" data-width="500" 
			                    data-small-header="false" 
			                    data-adapt-container-width="true" 
			                    data-hide-cover="false" 
			                    data-show-facepile="true">
			                    <blockquote cite="https://www.facebook.com/AngryBirdsIslands" class="fb-xfbml-parse-ignore">
			                    <a href="https://www.facebook.com/AngryBirdsIslands">Facebook</a></blockquote>
		                    </div>
	                    </div>
		                <div class="btn_area">
                            <a href="${FBFanPage.link}" class="like_f"></a>
                            <a href="${TWFanPage.link}" class="follow_t"></a>
                        </div>
                    </c:otherwise>
                </c:choose>
                
                <!--일본의 경우 버튼 순서 교체-->
                
            </div>
        </div>
        <div class="sec_overview">
            <div class="inner">
            <span id="sec_overview"></span>
            <div class="story">
                <span id="intro_btn_prev1"></span><a href="#" class="btn btn_prev rf"></a>
                <span id="intro_btn_next1"></span><a href="#" class="btn btn_next rf"></a>
                <div class="game_src_area">
                    <ul class="game_src_lst1">
                        <li><img src="https://images.toast.com/toast/s629/angry/web/pre_regist/story/${local }_1_01.jpg"></li>
                        <li><img src="https://images.toast.com/toast/s629/angry/web/pre_regist/story/${local }_1_02.jpg"></li>
                        <li><img src="https://images.toast.com/toast/s629/angry/web/pre_regist/story/${local }_1_03.jpg"></li>
                        <li><img src="https://images.toast.com/toast/s629/angry/web/pre_regist/story/${local }_1_04.jpg"></li>
                        <li><img src="https://images.toast.com/toast/s629/angry/web/pre_regist/story/${local }_1_05.jpg"></li>
                        <li><img src="https://images.toast.com/toast/s629/angry/web/pre_regist/story/${local }_1_06.jpg"></li>
                        <li><img src="https://images.toast.com/toast/s629/angry/web/pre_regist/story/${local }_1_07.jpg"></li>
                        <li><img src="https://images.toast.com/toast/s629/angry/web/pre_regist/story/${local }_1_08.jpg"></li>
                        <li><img src="https://images.toast.com/toast/s629/angry/web/pre_regist/story/${local }_1_09.jpg"></li>
                        <li><img src="https://images.toast.com/toast/s629/angry/web/pre_regist/story/${local }_1_10.jpg"></li>
                        <li><img src="https://images.toast.com/toast/s629/angry/web/pre_regist/story/${local }_1_11.jpg"></li>
                        <li><img src="https://images.toast.com/toast/s629/angry/web/pre_regist/story/${local }_1_12.jpg"></li>
                        <li><img src="https://images.toast.com/toast/s629/angry/web/pre_regist/story/${local }_1_13.jpg"></li>
                    </ul>
                </div>
            </div>
            <div class="introduction">
                <span id="intro_btn_prev2"></span><a href="#" class="btn btn_prev rf"></a>
                <span id="intro_btn_next2"></span><a href="#" class="btn btn_next rf"></a>
                <div class="game_src_area">
                    <ul class="game_src_lst2">
                        <li><img src="https://images.toast.com/toast/s629/angry/web/pre_regist/story/${local }_2_01.jpg"></li>
                        <li><img src="https://images.toast.com/toast/s629/angry/web/pre_regist/story/${local }_2_02.jpg"></li>
                        <li><img src="https://images.toast.com/toast/s629/angry/web/pre_regist/story/${local }_2_03.jpg"></li>
                        <li><img src="https://images.toast.com/toast/s629/angry/web/pre_regist/story/${local }_2_04.jpg"></li>
                        <li><img src="https://images.toast.com/toast/s629/angry/web/pre_regist/story/${local }_2_05.jpg"></li>
                        <li><img src="https://images.toast.com/toast/s629/angry/web/pre_regist/story/${local }_2_06.jpg"></li>
                        <li><img src="https://images.toast.com/toast/s629/angry/web/pre_regist/story/${local }_2_07.jpg"></li>
                    </ul>
                </div>
            </div>
            <div class="chuck"></div>
            <div class="screenshot">
                <span id="intro_btn_prev3"></span><a href="#" class="btn btn_prev rf"></a>
                <span id="intro_btn_next3"></span><a href="#" class="btn btn_next rf"></a>
                <div class="game_src_area">
                    <ul class="game_src_lst3">
                        <li><img src="https://images.toast.com/toast/s629/angry/web/pre_regist/story/3_01.jpg"></li>
                        <li><img src="https://images.toast.com/toast/s629/angry/web/pre_regist/story/3_02.jpg"></li>
                        <li><img src="https://images.toast.com/toast/s629/angry/web/pre_regist/story/3_03.jpg"></li>
                        <li><img src="https://images.toast.com/toast/s629/angry/web/pre_regist/story/3_04.jpg"></li>
                        <li><img src="https://images.toast.com/toast/s629/angry/web/pre_regist/story/3_05.jpg"></li>
                    </ul>
                </div>
            </div>
            </div>
        </div>
    </div>
        <!-- footer -->
    <div class="footer">
        <div class="btn_area">
            <a href="http://www.toast.com/terms/agreement.nhn?type=WEB&termsType=studio629_global_privacy&lang=en" target="_blank" class="privacy"></a>
            <a href="mailto:ABI@nhnstudio629.com" target="_blank"  class="contact"></a>
        </div> 
    </div>
    <!-- //footer -->
    <div class="bg_l"></div>
    <div class="bg_r"></div>
</div>
<div class="dimmed" style="display:none"></div>
<!--영상-->
<div class="ly_mov" style="display:none">
    <div class="ly_cont">
        <div class="wrap_mov">
            <!-- [D] 여기에 동영상 삽입 -->
            <c:choose>
                <c:when test="${Device == 'NORMAL'}">
                    <c:choose>
		                <c:when test="${local == 'jp'}">
                            <iframe id="tr_movie" width="854" height="480" src="https://www.youtube.com/embed/QubEdSDmV4U?rel=0&autoplay=1" frameborder="0" allowfullscreen></iframe>
                        </c:when>
                        <c:when test="${local == 'tw'}">
                            <iframe id="tr_movie" width="854" height="480" src="https://www.youtube.com/embed/NXvV3hteXlY?rel=0&autoplay=1" frameborder="0" allowfullscreen></iframe>
                        </c:when>
                        <c:otherwise><iframe id="tr_movie" width="854" height="480" src="https://www.youtube.com/embed/L5Tk5FaRCe4?rel=0&autoplay=1" frameborder="0" allowfullscreen></iframe></c:otherwise>
		            </c:choose>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${local == 'jp'}">
                            <iframe id="tr_movie" width="310" height="174" src="https://www.youtube.com/embed/QubEdSDmV4U" frameborder="0" allowfullscreen></iframe>
                        </c:when>
                        <c:when test="${local == 'tw'}">
                            <iframe id="tr_movie" width="310" height="174" src="https://www.youtube.com/embed/NXvV3hteXlY" frameborder="0" allowfullscreen></iframe>
                        </c:when>
                        <c:otherwise><iframe id="tr_movie" width="310" height="174" src="https://www.youtube.com/embed/L5Tk5FaRCe4" frameborder="0" allowfullscreen></iframe></c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
        </div>
        <a href="#" class="btn_ly_close"></a>
    </div>
</div>

</body>
</html>
