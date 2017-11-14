UserInfo = function(local, userkey){
    this.local = local.toLowerCase();
    this.store = "google";
    this.userkey = userkey;
    this.shareComplete = function(snsname, code) {
        
    }
};
UserInfo.prototype.setStore = function(store) {
    this.store = store;
};
UserInfo.prototype.setUserkey = function(userkey) {
    this.userkey = userkey;
};


(function(){ // 외부 라이브러리와 충돌을 막고자 모듈화.
	// 브라우저 및 버전을 구하기 위한 변수들.
	'use strict';
	var agent = navigator.userAgent.toLowerCase(),
	    name = navigator.appName,
	    browser;
	// MS 계열 브라우저를 구분하기 위함.
	if(name === 'Microsoft Internet Explorer' || agent.indexOf('trident') > -1 || agent.indexOf('edge/') > -1) {
		browser = 'ie';
		if(name === 'Microsoft Internet Explorer') { // IE old version (IE 10 or Lower)
			agent = /msie ([0-9]{1,}[\.0-9]{0,})/.exec(agent);
			browser += parseInt(agent[1]);
		} else { // IE 11+
			if(agent.indexOf('trident') > -1) { // IE 11 
				browser += 11;
			} else if(agent.indexOf('edge/') > -1) { // Edge
				browser = 'edge';
			}
		}
	} else if(agent.indexOf('safari') > -1) { // Chrome or Safari
		if(agent.indexOf('opr') > -1) { // Opera
			browser = 'opera';
		} else if(agent.indexOf('chrome') > -1) { // Chrome
			browser = 'chrome';
		} else { // Safari
			browser = 'safari';
		}
	} else if(agent.indexOf('firefox') > -1) { // Firefox
		browser = 'firefox';
	}
	
	// IE: ie7~ie11, Edge: edge, Chrome: chrome, Firefox: firefox, Safari: safari, Opera: opera
	if(browser == 'ie11'){
		setTimeout(function(){
			document.getElementById('browser_notice').style.display = 'block';
		}, 5000);
	}
	
}());


jQuery(document).ready(function(){
	$('.game_src_lst1').bxSlider({
        mode:'horizontal', //default : 'horizontal', options: 'horizontal', 'vertical', 'fade'
        speed:500, //default:500 
        auto: false, //default:false 
        captions: false, // image title 
        autoControls: false, //default:false 
        prevSelector:'#intro_btn_prev1',
        prevText:'&nbsp;',
        nextSelector:'#intro_btn_next1',
        nextText:'&nbsp;'
    });
	$('.game_src_lst2').bxSlider({
        mode:'horizontal', //default : 'horizontal', options: 'horizontal', 'vertical', 'fade'
        speed:500, //default:500 
        auto: false, //default:false 
        captions: false, // image title 
        autoControls: false, //default:false 
        prevSelector:'#intro_btn_prev2',
        prevText:'&nbsp;',
        nextSelector:'#intro_btn_next2',
        nextText:'&nbsp;'
    });
	
	$('.game_src_lst3').bxSlider({
        mode:'horizontal', //default : 'horizontal', options: 'horizontal', 'vertical', 'fade'
        speed:500, //default:500 
        auto: false, //default:false 
        captions: false, // image title 
        autoControls: false, //default:false 
        prevSelector:'#intro_btn_prev3',
        prevText:'&nbsp;',
        nextSelector:'#intro_btn_next3',
        nextText:'&nbsp;'
    });
	
	jQuery(".btn_pop_ok, .btn_ly_cls,.btn_prev,.btn_next").click(function(e){
		e.preventDefault();
		jQuery('.ly_wrap,.dimmed').hide();
	})
	jQuery(".selected").click(function(e){
		e.preventDefault();
		var classes = jQuery(".select_area").attr("class").split(" ");
		var flag = false;
		for(var i=0;i<classes.length;i++){
			if(classes[i] == "on"){
				flag = true;
			}
		}
		if(flag){
			jQuery(".select_area").removeClass("on");
		}else{
			jQuery(".select_area").addClass("on");
		}
	})
	
	jQuery("div.cls").click(function(){
		jQuery(".notice").hide();
	})
	
	jQuery(".btn_video").on({
        "click": function(e){
        	e.preventDefault();
            jQuery(".dimmed").show();
            jQuery(".ly_mov").show();
            
        }
    });
	
	jQuery(".btn_ly_close").on({
        "click": function(e){
            e.preventDefault();
            jQuery(".dimmed").hide();
            jQuery(".ly_mov").hide();
            
            var tr_movie={
                "jp":"https://www.youtube.com/embed/QubEdSDmV4U",
                "tw":"https://www.youtube.com/embed/NXvV3hteXlY",
                "en":"https://www.youtube.com/embed/L5Tk5FaRCe4",
                "kr":"https://www.youtube.com/embed/ZkUBWYhQ2Oo"
            };
		    $.each(tr_movie, function(key, value){
		        if(key == userInfo.local){
		            $("#tr_movie").attr("src",value);
		            return false;
		        }else{
		            $("#tr_movie").attr("src",tr_movie.en);
		        }
		    })
        }
    });
	
    
    var kakao_api = function(param){
        //console.log(param);
        var url = Contextpath+"/sns/kakao/oauth.json";
        getJsonToken(url, "get", param, function(data){
            //console.log(data);
            if(data.result == "ready"){
                var form = jQuery("#newpage");
                var win = window.open(data.oauthUrl, "newpage", "width=500,height=500,scrollbars=no,resizable=no");
                
            }else if(data.result == "success"){
                kakaoComplete(param.code);
                
            }else if(data.result == "NotExistKakaoStoryUser"){
            	$('.ly_wrap').hide();$('.ly_wrap:eq(15),.dimmed').show();return false;
            }
            
        });
    }
    
    //fb share
    jQuery(".facebook").on({
        "click":function(e){
            e.preventDefault();
            var fileName = jQuery("meta[name='og.image']").attr("content");
            var message = jQuery("meta[name='og.description']").attr("content")+ "\n"+jQuery("meta[name='og.caption']").attr("content");
            var link = jQuery("meta[name='og.url']").attr("content");
            FB.ui({
                method: 'feed',
                picture: fileName,
                link:link,
                quote:message
              
            }, function(response){
                //console.log(response);
                if(response && !response.error_message){
                    
                }
            });
        }
    });
    
    //twitter share
    var twitter_api = function(param){
        var url = Contextpath+"/sns/twitter/"+userInfo.local+"/oauth.json";
        getJsonToken(url, "get", param, function(data){
            //console.log(data);
            if(data.result == "ready"){
                var form = jQuery("#newpage");
                var win = window.open(data.oauthUrl, "newpage", "width=500,height=700,scrollbars=no,resizable=no");
                
            }else if(data.result == "success"){
                
            }else{
                //console.log("트위터 연동 실패");
            }
            
        });
    }
    
    
    jQuery(".twitter").on({
        "click":function(e){
            e.preventDefault();
            //alert("트위터 공유하기 #2\n   - 공유 했는지 알수있음 \n   - 앵아 공식 트위터가 유저 트위터에 직접 글 등록\n   - 이미지 업로드 가능");
            //트위터 공유하기 #2  (공유 했는지 알수있음, 앵아 공식 트위터가 유저 트위터에 직접 글 등록, 이미지 업로드 가능)
            var param = {"code":"pre_regist"};
               twitter_api(param);
        }
    })
    
    jQuery(".line").on({
        "click":function(){
            var text = jQuery(this).find(".line-text").text();
            var url = "http://line.me/R/msg/text/";
            url = url+"?"+encodeURI(text);
            window.open(url, '_blank'); 
        }
    });
    
    
    //fb login 
    var fb_login = function(){
        FB.getLoginStatus(function(response) {
            if (response.status === 'connected') {
                  //console.log('Logged in.');
              }else{
                  FB.login(function(){}, {scope: 'publish_actions'});
              }
        });
    };
    
    //fb like
    jQuery(".like_f").click(function(e){
        e.preventDefault();
        var link = jQuery(this).attr("href");
        var form = jQuery("#newpage");
        var win = window.open(link, "newpage", "width=1280,height=1024,scrollbars=no,resizable=no");
        return false;
    })
  
    //cafe_follow
    jQuery(".naver_cafe").click(function(e){
        e.preventDefault();
        var form = jQuery("#newpage");
        var win = window.open("http://cafe.naver.com/angrybirdsislands", "newpage", "width=1280,height=1024,scrollbars=yes,resizable=no");
    })
    
    //tw_follow
    jQuery(".follow_t").click(function(e){
        e.preventDefault();
        //var param = {"code":"pre_regist_like"};
        var link = jQuery(this).attr("href");
        var form = jQuery("#newpage");
        var win = window.open(link, "newpage", "width=1280,height=1024,scrollbars=no,resizable=no");
        twitter_api(param);
    })
});


function kakaoComplete(code){
    //console.log("twitterComplete:"+code);
    if(code == "pre_regist_reply"){
        reply_refresh();
        $('.ly_wrap').hide();$('.ly_wrap:eq(10),.dimmed').show();return false;
    }
}    