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
<script src="${info.contextPath }/resources/js/pre_regist/regist.js"></script>
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
<!-- Facebook Pixel Code -->
<script>
!function(f,b,e,v,n,t,s){if(f.fbq)return;n=f.fbq=function(){n.callMethod?
n.callMethod.apply(n,arguments):n.queue.push(arguments)};if(!f._fbq)f._fbq=n;
n.push=n;n.loaded=!0;n.version='2.0';n.queue=[];t=b.createElement(e);t.async=!0;
t.src=v;s=b.getElementsByTagName(e)[0];s.parentNode.insertBefore(t,s)}(window,
document,'script','https://connect.facebook.net/en_US/fbevents.js');
fbq('init', '1196184933731336', {
em: 'insert_email_variable,'
});
fbq('track', 'PageView');
</script>
<noscript><img height="1" width="1" style="display:none"
src="https://www.facebook.com/tr?id=1196184933731336&ev=PageView&noscript=1"
/></noscript>
<!-- DO NOT MODIFY -->
<!-- End Facebook Pixel Code -->
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
<!-- Google Code for Teasing_사전예약완료 Conversion Page
In your html page, add the snippet and call
goog_report_conversion when someone clicks on the
chosen link or button. -->
<script type="text/javascript">
  /* <![CDATA[ */
  goog_snippet_vars = function() {
    var w = window;
    w.google_conversion_id = 859208546;
    w.google_conversion_label = "co_vCOaQw28Q4vbZmQM";
    w.google_remarketing_only = false;
  }
  // DO NOT CHANGE THE CODE BELOW.
  goog_report_conversion = function(url) {
    goog_snippet_vars();
    window.google_conversion_format = "3";
    var opt = new Object();
    opt.onload_callback = function() {
    if (typeof(url) != 'undefined') {
      window.location = url;
    }
  }
  var conv_handler = window['google_trackConversion'];
  if (typeof(conv_handler) == 'function') {
    conv_handler(opt);
  }
}
/* ]]> */
</script>
<script type="text/javascript" src="//www.googleadservices.com/pagead/conversion_async.js"></script> 

<!-- Twitter single-event website tag code -->
<script src="//platform.twitter.com/oct.js" type="text/javascript"></script>
<noscript>
<img height="1" width="1" style="display:none;" alt="" src="https://analytics.twitter.com/i/adsct?txn_id=nwe9d&p_id=Twitter&tw_sale_amount=0&tw_order_quantity=0" />
<img height="1" width="1" style="display:none;" alt="" src="//t.co/i/adsct?txn_id=nwe9d&p_id=Twitter&tw_sale_amount=0&tw_order_quantity=0" />
</noscript>
<!-- End Twitter single-event website tag code -->
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
            <ul class="<c:if test="${local eq 'th' }">th_</c:if>gnb nav">
                <!-- [D] 메뉴 활성화 시 li에 .on 클래스 추가 -->
                <li class="menu1"><a href="#sec_pre-registration"></a></li>
                <li class="menu2"><a href="#sec_share"></a></li>
                <li class="menu3"><a href="#sec_review"></a></li>
                <li class="menu4"><a href="#sec_sns"></a></li>
                <li class="menu5"><a href="#sec_overview"></a></li>
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
        <!-- 01 사전예약 -->
        <div class="event_1">
            <div class="inner">
                <span id="sec_pre-registration"></span>
                <div class="reward_area">
                    <div class="event1_deco"></div>
                </div>
                <div class="p_form">
                    <div class="os_area">
                        <label class="os" for="android_radio">
                            <!-- 체크박스 클릭시 on 클래스 추가 -->
                            <span class="check on"><input type="radio" name="select_store" id="android_radio" class="blind" value="google"/></span>
                            <!-- <label class="google" for="android_radio"><span class="blind">안드로이드</span></label> -->
                        </label>
                        <label class="osv2" for="ios_radio">
                            <!-- 체크박스 클릭시 on 클래스 추가 -->
                            <span class="check"><input type="radio" name="select_store" id="ios_radio" class="blind" value="apple"/></span>
                            <!-- <label class="apple" for="ios_radio"><span class="blind">애플 IOS</span></label> -->
                        </label>
                    </div>
                    <div class="e_area">
                        <input type="email" id="email_addr" placeholder="<spring:message code='preregist.email.input' />">
                        <a href="#" class="bn btn_register"><span class="blind">사전등록하기</span></a>
                    </div>
                </div>
            </div>
        </div>
        <div class="event_2">
            <div class="inner">
            <span id="sec_share"></span>
                <div class="sharebtn_area">
                    <c:choose>
	                    <c:when test="${local eq 'jp' }">
	                        <a href="#" class="share_twitter"></a>
	                        <a href="#" class="share_facebook"></a>
	                    </c:when>
	                    <c:otherwise>
	                       <a href="#" class="share_facebook"></a>
                            <a href="#" class="share_twitter"></a>
	                    </c:otherwise>
                    </c:choose>
                    
                </div>
                <div class="reward_area">
                    <div class="event2_deco"></div>
                </div>
            </div>
        </div>
        <div class="event_3">
            <div class="inner">
            <span id="sec_review"></span>
                <div class="reward_area">
                    <div class="event3_deco"></div>
                </div>
                <div class="reviewch_area">
                    <c:choose>
                        <c:when test="${local eq 'jp' }">
                            <c:set var="first_reply_sns">twitter</c:set>
                            <c:set var="second_reply_sns">facebook</c:set>
                        </c:when>
                        <c:otherwise>
                            <c:set var="first_reply_sns">facebook</c:set>
                            <c:set var="second_reply_sns">twitter</c:set>
                        </c:otherwise>
                    </c:choose>
                    <label class="fb" for="facebook_reply">
                        <!-- 체크박스 클릭시 on 클래스 추가 -->
                        <span class="check"><input type="radio" name="sns_reply" id="facebook_reply" class="blind" value="${first_reply_sns }"></span>
                        <!-- <label class="facebook" for="facebook_reply"><span class="blind">facebook</span></label> -->
                    </label>
                    <label class="tw" for="twitter_reply">
                        <!-- 체크박스 클릭시 on 클래스 추가 -->
                        <span class="check"><input type="radio" name="sns_reply" id="twitter_reply" class="blind" value="${second_reply_sns }"></span>
                        <!-- <label class="twitter" for="twitter_reply"><span class="blind">twitter</span></label> -->
                    </label>
                </div>
                <div class="review_box">
                    <textarea name="comment" id="comment" class="comment" placeholder="<spring:message code='preregist.reply.public' />" tabindex="3"></textarea>
                    <a href="#" class="review_btn"></a>
                </div>
                <div class="review_list">
                    <ul>
                        <c:forEach items="${reply_list }" var="reply">
                            <li class="list"><div class="${fn:substring(reply.snsname, 0, 1) }_icon"></div><p>${reply.msg }</p></li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
        <div class="sec_sns">
            <div class="inner">
            <span id="sec_sns"></span>
                <div class="pig2"></div>
                <div class="gem"></div>
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
                            <iframe id="tr_movie" width="854" height="480" src="https://www.youtube.com/embed/CtPre1H1d-0?rel=0&autoplay=1" frameborder="0" allowfullscreen></iframe>
                        </c:when>
                        <c:when test="${local == 'tw'}">
                            <iframe id="tr_movie" width="854" height="480" src="https://www.youtube.com/embed/BeSwt2XEWAw?rel=0&autoplay=1" frameborder="0" allowfullscreen></iframe>
                        </c:when>
                        <c:otherwise><iframe id="tr_movie" width="854" height="480" src="https://www.youtube.com/embed/-lJw6sBzllc?rel=0&autoplay=1" frameborder="0" allowfullscreen></iframe></c:otherwise>
		            </c:choose>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${local == 'jp'}">
                            <iframe id="tr_movie" width="310" height="174" src="https://www.youtube.com/embed/CtPre1H1d-0" frameborder="0" allowfullscreen></iframe>
                        </c:when>
                        <c:when test="${local == 'tw'}">
                            <iframe id="tr_movie" width="310" height="174" src="https://www.youtube.com/embed/BeSwt2XEWAw" frameborder="0" allowfullscreen></iframe>
                        </c:when>
                        <c:otherwise><iframe id="tr_movie" width="310" height="174" src="https://www.youtube.com/embed/-lJw6sBzllc" frameborder="0" allowfullscreen></iframe></c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
        </div>
        <a href="#" class="btn_ly_close"></a>
    </div>
</div>

<!--사전등록 팝업-->
<!--정확한 이메일 입력 0-->
<div class="ly_wrap ly_bg1" style="display:none">
    <div class="ly_cont">
        <div class="text_pop">
            <p class="ly_pop_tt"><spring:message code='preregist.email.valid' /></p>
            <a href="#" class="btn_pop_ok"><spring:message code='coupon.popup.close' /></a>
        </div>
    </div>
</div>
<!--사전등록완료 1-->
<div class="ly_wrap ly_bg1" style="display:none">
    <div class="ly_cont">
        <div class="text_pop">
            <p class="ly_pop_tt"><spring:message code='preregist.complete.success' /></p>
            <em><spring:message code='preregist.event.thanks' /></em>
            <a href="#" class="btn_pop_ok"><spring:message code='coupon.popup.close' /></a>
        </div>
    </div>
</div>
<!--이미완료 2-->
<div class="ly_wrap ly_bg1" style="display:none">
    <div class="ly_cont">
        <div class="text_pop">
            <p class="ly_pop_tt"><spring:message code='preregist.complete.already' /></p>
            <a href="#" class="btn_pop_ok"><spring:message code='coupon.popup.close' /></a>
        </div>
    </div>
</div>
<!--구글사전등록안내 3-->
<div class="ly_wrap ly_bg2" style="display:none">
    <div class="ly_cont">
        <div class="text_pop">
            <em class="pop_email" id="register_userkey"></em>
            <a href="https://play.google.com/store/apps/details?id=com.studio629.angrysaga" target="_blank" onclick="jQuery('.ly_wrap,.dimmed').hide();"  class="btn_pop_go"></a>
        </div>
        <a href="#" class="btn_ly_cls"></a>
    </div>
</div>
<!--이메일확인 4-->
<div class="ly_wrap ly_bg1" style="display:none">
    <div class="ly_cont">
        <div class="text_pop">
            <div class="ly_pop_title"></div>
            <div class="pop_e_area">
              <a href="#" class="bn btn_register btn_register_confirm"><span><spring:message code='coupon.popup.close' /></span></a>
                <div class="line">
                <input type="email" name="email_confirm_input" placeholder="<spring:message code='preregist.email.input' />">
                </div>
            </div>
            <span class="s_tt"><spring:message code='preregist.before.event' /></span>
        </div>
    </div>
</div>
<!--사전등록 미참여 5-->
<div class="ly_wrap ly_bg1" style="display:none">
    <div class="ly_cont">
        <div class="text_pop">
            <p class="ly_pop_tt"><spring:message code='preregist.before.event' /></p>            
            <a href="#" class="btn_pop_ok"><spring:message code='coupon.popup.close' /></a>
        </div>
    </div>
</div>
<!--기대평을 작성해라 6-->
<div class="ly_wrap ly_bg1" style="display:none">
    <div class="ly_cont">
        <div class="text_pop">
            <p class="ly_pop_tt"><spring:message code='preregist.reply.empty' /></p>
            <a href="#" class="btn_pop_ok"><spring:message code='coupon.popup.close' /></a>
        </div>
    </div>
</div>
<!--사전등록 먼저 하세요  7-->
<div class="ly_wrap ly_bg1" style="display:none">
    <div class="ly_cont">
        <div class="text_pop">
            <p class="ly_pop_tt"><spring:message code='preregist.before.event' /></p>
            <em>Thank you!</em>
            <a href="#" class="btn_pop_ok"><spring:message code='coupon.popup.close' /></a>
        </div>
    </div>
</div>
<!--공유완료 8-->
<div class="ly_wrap ly_bg1" style="display:none">
    <div class="ly_cont">
        <div class="text_pop">
            <p class="ly_pop_tt"><spring:message code='preregist.event.thanks' /></p>
            <em>Thank you!</em>
            <a href="#" class="btn_pop_ok"><spring:message code='coupon.popup.close' /></a>
        </div>
    </div>
</div>
<!--기대평을 작성 플랫폼 선택하시오 9-->
<div class="ly_wrap ly_bg1" style="display:none">
    <div class="ly_cont">
        <div class="text_pop">
            <p class="ly_pop_tt"><spring:message code='preregist.reply.pletform' /></p>
            <a href="#" class="btn_pop_ok"><spring:message code='coupon.popup.close' /></a>
        </div>
    </div>
</div>
<!--기대평을 작성완료 10-->
<div class="ly_wrap ly_bg1" style="display:none">
    <div class="ly_cont">
        <div class="text_pop">
            <p class="ly_pop_tt"><spring:message code='preregist.event.thanks' /></p>
            <a href="#" class="btn_pop_ok"><spring:message code='coupon.popup.close' /></a>
        </div>
    </div>
</div>
<!--좋아요를 눌러주세요. 11-->
<div class="ly_wrap ly_bg1" style="display:none">
    <div class="ly_cont">
        <div class="text_pop">
            <p class="ly_pop_tt"><spring:message code='preregist.sns.like' /></p>
            <a href="#" class="btn_pop_ok"><spring:message code='coupon.popup.close' /></a>
        </div>
    </div>
</div>
</body>
</html>
