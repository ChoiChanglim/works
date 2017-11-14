UserInfo = function(local, userkey){
    this.local = local.toLowerCase();
    this.store = "google";
    this.userkey = userkey;
    this.shareComplete = function(snsname, code) {
        if(typeof this.userkey != "undefined" && this.userkey !=""){
        	if(snsname == "close"){
        		
        	}else{
        		var url = Contextpath+"/sns/event/"+this.local+"/complete_share.json";
        		var local = this.local;
                var param = "userkey="+this.userkey;
                param = param+"&code="+code;
                param = param+"&snsname="+snsname;
                getJsonToken(url, "get", param, function(data){
                    if(data.result == "SUCCESS"){
                    	if(code == 'pre_regist_like' && snsname=='facebook'){
                    		if(local == 'kr'){
                    			$('.ly_wrap').hide();$('.ly_wrap:eq(16),.dimmed').show();return false;
                    		}else{
                    			$('.ly_wrap').hide();$('.ly_wrap:eq(11),.dimmed').show();return false;
                    		}
                    		
                    	}else{
                    		$('.ly_wrap').hide();$('.ly_wrap:eq(8),.dimmed').show();return false;
                    	}
                    }
                });
        	}
            
        }
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
                "jp":"https://www.youtube.com/embed/CtPre1H1d-0",
                "tw":"https://www.youtube.com/embed/BeSwt2XEWAw",
                "en":"https://www.youtube.com/embed/-lJw6sBzllc",
                "kr":"https://www.youtube.com/embed/WuEOUuh9USA"
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
	
	//select store
    jQuery("input[name=select_store]:radio").on({
        "change":function(e){
            jQuery(".os_area").find("span.check").removeClass("on");
            jQuery(this).parent().addClass("on");
            var store = jQuery("input[name=select_store]:radio:checked").val();
            userInfo.setStore(store);
        }
    }); 
    
    //regist_checkbox
    jQuery(".regist_checkbox").on({
        "change":function(e){
            var flag = jQuery(this).is(":checked");
            if(flag == true){
            	jQuery(this).parent().addClass("on");
            }else{
            	jQuery(this).parent().removeClass("on");
            }
        }
    });
    
    //more agree
    jQuery(".btn_more_agree").on({
    	"click":function(e){
    		e.preventDefault();
    		if(jQuery(this).parent().attr("class") == "check1"){
    			$('.ly_wrap').hide();$('.ly_wrap:eq(14),.dimmed').show();return false;
    		}else{
    			$('.ly_wrap').hide();$('.ly_wrap:eq(13),.dimmed').show();return false;
    		}
    	}
    })
	
	
	//regist
    jQuery(".btn_register").eq(0).click(function(e){
        //console.log(userInfo);
        e.preventDefault();
        var email = jQuery("#email_addr").val();
        if(email == ""){
            $('.ly_wrap').hide();$('.ly_wrap:eq(0),.dimmed').show();return false;
        }
        
        if(userInfo.local=='kr'){
        	if(jQuery("#agree1").is(":checked") == false){
            	$('.ly_wrap').hide();$('.ly_wrap:eq(11),.dimmed').show();return false;
            }
            if(jQuery("#agree2").is(":checked") == false){
                $('.ly_wrap').hide();$('.ly_wrap:eq(12),.dimmed').show();return false;
            }
        }
        
        var url = Contextpath+"/pre-regist/"+userInfo.local+"/regist.json";
        var param = "userkey="+email;
        param = param+"&store="+userInfo.store;
        getJsonToken(url, "get", param, function(data){
            //console.log(data);
            if(data.result == "ALREADY_REGIST"){
                $('.ly_wrap').hide();$('.ly_wrap:eq(2),.dimmed').show();return false; 
            }else if(data.result == "INVALID_USER" || data.result == "INVALID_PARAMETER"){
                $('.ly_wrap').hide();$('.ly_wrap:eq(0),.dimmed').show();return false;
            }else if(data.result == "SUCCESS"){
            	jQuery("#register_userkey").text(data.preRegist.userkey);
                userInfo.setUserkey(email);
                fbq('track', 'CompleteRegistration'); //fb campaign all
                
                if(userInfo.local=='kr'){
                	adpick_webtracking({ site:'angrtbirdislands', event:'registered' }); // adpick
                	goog_report_conversion (); //google campaign
                }
                if(userInfo.local=='jp'){
                	twttr.conversion.trackPid('nwe9d', { tw_sale_amount: 0, tw_order_quantity: 0 });   //twitter campaign
                	goog_report_conversion (); //google campaign
                }
                if(userInfo.local=='tw'){
                	goog_report_conversion (); //google campaign
                }
                $('.ly_wrap').hide();$('.ly_wrap:eq(3),.dimmed').show();return false;
            }else if(data.result == "NOT_ALLOWED_USER"){
            	
            }
        });
    });
    //regist confirm
    jQuery(".btn_register_confirm").click(function(e){
        //console.log(userInfo);
        e.preventDefault();
        var email = jQuery("input[name=email_confirm_input]").val();
        if(email == ""){
            $('.ly_wrap').hide();$('.ly_wrap:eq(0),.dimmed').show();return false;
        }
        var url = Contextpath+"/pre-regist/"+userInfo.local+"/regist_confirm.json";
        var param = "userkey="+email;
        getJsonToken(url, "get", param, function(data){
            //console.log(data);
            if(data.result == "IS_NOT_REGIST"){
                $('.ly_wrap').hide();$('.ly_wrap:eq(5),.dimmed').show();
                if(userInfo.local == 'kr'){
                	$('html,body').animate({scrollTop:2600}, 400);
                }else{
                	$('html,body').animate({scrollTop:1500}, 400);
                }
                
                return false; 
            }else if(data.result == "SUCCESS"){
            	try{
            		userInfo.setUserkey(data.preRegist.userkey);
            	}catch(exception){
            		console.log(exception);
            	}
                
                $('.ly_wrap').hide();$('.dimmed').hide();return false;
                
            }else if(data.result == "INVALID_USER"){
            	$('.ly_wrap').hide();$('.ly_wrap:eq(0),.dimmed').show();return false; 
            	
            }else if(data.result == "INVALID_EMAIL"){
                $('.ly_wrap').hide();$('.ly_wrap:eq(0),.dimmed').show();return false; 
                
            }
        });
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
    jQuery(".share_facebook").on({
        "click":function(e){
            e.preventDefault();
            if(typeof userInfo.userkey == "undefined" || userInfo.userkey ==""){
                $('.ly_wrap').hide();$('.ly_wrap:eq(4),.dimmed').show();return false;
                return;
            }
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
                    userInfo.shareComplete("facebook", "pre_regist");
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
                twitterComplete(param.code);
                
            }else{
                //console.log("트위터 연동 실패");
            }
            
        });
    }
    
    
    jQuery(".share_twitter").on({
        "click":function(e){
            e.preventDefault();
            if(typeof userInfo.userkey == "undefined" || userInfo.userkey ==""){
                $('.ly_wrap').hide();$('.ly_wrap:eq(4),.dimmed').show();return false;
                return;
            }
            //alert("트위터 공유하기 #2\n   - 공유 했는지 알수있음 \n   - 앵아 공식 트위터가 유저 트위터에 직접 글 등록\n   - 이미지 업로드 가능");
            //트위터 공유하기 #2  (공유 했는지 알수있음, 앵아 공식 트위터가 유저 트위터에 직접 글 등록, 이미지 업로드 가능)
            var param = {"code":"pre_regist"};
               twitter_api(param);
        }
    })
    
    //select reply
    jQuery("input[name=sns_reply]:radio").on({
        "change":function(e){
            jQuery(".reviewch_area").find("span.check").removeClass("on");
            jQuery(this).parent().addClass("on");
            var val = jQuery(this).val();
            if(val == "facebook"){
                fb_login();
                
            }else if(val == "twitter"){
                var param = {"code":"login"};
                twitter_api(param);
                
            }else if(val == "kakao"){
                var param = {"code":"login"};
                kakao_api(param);
            }
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
    //fb_reply
    var fb_publish_action = function(message){
        var insert_reply = function(param){
            var url = Contextpath+"/sns/event/"+userInfo.local+"/insert_reply.json";
            getJsonToken(url, "get", param, function(data){
                if(data.result == "SUCCESS"){
                    //댓글 새로고침 
                    reply_refresh();
                    $('.ly_wrap').hide();$('.ly_wrap:eq(10),.dimmed').show();return false;
                }
            });
        }
        FB.login(function(){
            // Note: The call will only work if you accept the permission request
            FB.getLoginStatus(function(response) {
                if (response.status === 'connected') {
                    FB.api('/me/feed', 'post', {message: message}, function(response){
                        if(response && !response.error_message){
                            var param = {"code":"pre_regist_reply", "userkey":userInfo.userkey, "snsname":"facebook", "msg":message};
                            insert_reply(param);
                        }
                    });
                }
            });
            
        }, {scope: 'publish_actions'});
    }
    //reply button
    jQuery(".review_btn").on({
        "click":function(e){
            e.preventDefault();
            
            if(typeof userInfo.userkey == "undefined" || userInfo.userkey ==""){
                $('.ly_wrap').hide();$('.ly_wrap:eq(4),.dimmed').show();return false;
                return;
            }
            
            var val = jQuery("input[name=sns_reply]:radio:checked").val();
            if(typeof val == "undefined"){
                $('.ly_wrap').hide();$('.ly_wrap:eq(9),.dimmed').show();
                return;
            }
            var message = jQuery("#comment").val();
            if(message == ""){
                $('.ly_wrap').hide();$('.ly_wrap:eq(6),.dimmed').show();
                return;
            }
            if(val == "facebook"){
                fb_publish_action(message);
                
            }else if(val == "twitter"){
                var param = {"code":"pre_regist_reply", "msg":message};
                twitter_api(param);
                
            }else if(val == "kakao"){
            	var param = {"code":"pre_regist_reply", "msg":message};
                kakao_api(param);
            }
        }
    })
    
    
    //fb like
    jQuery(".like_f").click(function(e){
        e.preventDefault();
        if(typeof userInfo.userkey == "undefined" || userInfo.userkey ==""){
            $('.ly_wrap').hide();$('.ly_wrap:eq(4),.dimmed').show();
            return;
        }
        userInfo.shareComplete("facebook", "pre_regist_like");
        var link = jQuery(this).attr("href");
        setTimeout(function(){
            var form = jQuery("#newpage");
            var win = window.open(link, "newpage", "width=1280,height=1024,scrollbars=no,resizable=no");
        }, 2000);
        return false;
    })
  
    //cafe_follow
    jQuery(".naver_cafe").click(function(e){
        e.preventDefault();
        //console.log(userInfo);
        if(typeof userInfo.userkey == "undefined" || userInfo.userkey ==""){
            $('.ly_wrap').hide();$('.ly_wrap:eq(4),.dimmed').show();
            return;
        }
        userInfo.shareComplete("navercafe", "pre_regist_like");
        setTimeout(function(){
            var form = jQuery("#newpage");
            var win = window.open("http://cafe.naver.com/angrybirdsislands", "newpage", "width=1280,height=1024,scrollbars=yes,resizable=no");
        }, 2000);
    })
    
    //tw_follow
    jQuery(".follow_t").click(function(e){
        e.preventDefault();
        if(typeof userInfo.userkey == "undefined" || userInfo.userkey ==""){
            $('.ly_wrap').hide();$('.ly_wrap:eq(4),.dimmed').show();
            return;
        }
        var param = {"code":"pre_regist_like"};
        twitter_api(param);
    })
});

function twitterComplete(code){
    if(code == "pre_regist"){
        $('.ly_wrap').hide();$('.ly_wrap:eq(8),.dimmed').show();return false;
    }
    
    if(code == "pre_regist_reply"){
        reply_refresh();
        $('.ly_wrap').hide();$('.ly_wrap:eq(10),.dimmed').show();return false;
    }
    
    if(code == "pre_regist_like"){
        $('.ly_wrap').hide();$('.ly_wrap:eq(10),.dimmed').show();
        setTimeout(function(){
            var form = jQuery("#newpage");
            var link=jQuery(".follow_t").attr("href");
            var win = window.open(link, "newpage", "width=1280,height=1024,scrollbars=no,resizable=no");
        }, 2000);
        return false;
    }
}    
function reply_refresh(){
    var url = Contextpath+"/sns/event/"+userInfo.local+"/reply_list.json";
    getJsonToken(url, "get", null, function(data){
        
        if(data.result == "SUCCESS"){
            var el = jQuery(".review_list").find("ul");
            el.empty();
            for(var i=0 in data.list){
                var obj = data.list[i];
                var li = "<li class='list'><div class='"+obj.snsname.substring(0, 1)+"_icon'></div><p>"+obj.msg+"</p></li>";
                el.append(li);
            }
            
        }
        
    });
}
function kakaoComplete(code){
    //console.log("twitterComplete:"+code);
    if(code == "pre_regist_reply"){
        reply_refresh();
        $('.ly_wrap').hide();$('.ly_wrap:eq(10),.dimmed').show();return false;
    }
}    