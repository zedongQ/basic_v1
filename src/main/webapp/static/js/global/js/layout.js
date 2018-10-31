function log(msg){
	if(console && console.log){
		console.log(msg);
	}
}

(function($){
	
	v = getCookieValue("tools-panel-wx-static");//获取
	if(v=="no"){
		$(".tools-panel .wx-qrcode-static").css("display","none");
	}else{
		$(".tools-panel .wx-qrcode-static").css("display","block");
	}
	
	/*
	*/
	var $win=$(window).scroll(function(){
		var $this=$(this);
		var top=$this.scrollTop();
		/*
		var $hmenu=$('.header-block');
		if(top>10){
			$(".carousel-block").css("padding-top",$hmenu.outerHeight());
			$hmenu.addClass('header-fixed');
		}else{
			$(".carousel-block").css("padding-top","0px");
			$hmenu.removeClass('header-fixed');
		}
		*/

		if(top>50){
			//$(".header-block").attr("style","transition: top 300ms linear; top: -36px;");
			$(".header-block").css("top","-36px");
			$(".jfdb-side-menu").css("top","61px");
			$(".header-menu-bar").css("box-shadow","0 5px 15px rgba(0, 0, 0, .05)");
		}else{
			//$(".header-block").attr("style","transition: top 300ms linear; top: 0px;");
			$(".header-block").css("top","0px");
			$(".jfdb-side-menu").css("top","97px");
			$(".header-menu-bar").css("box-shadow","");
		}

		//返回顶部  第一屏不要出现
		if(top>200){//$this.width()/3
			$(".tools-panel>.totop").css("visibility","visible");
		}else{
			$(".tools-panel>.totop").css("visibility","hidden");
		}

		/* 交易商比较 列表向下滚动时,隐藏比较列表
		if(top>200){
			$("#pop-compare-brokers .compare-items").hide();
		} */
		/* 交易商比较 详情 固定头 */
		if(top>100){
			$(".compare-brokers-detail-main .compare-hd").show();
			if(window.document.location.href.indexOf("broker/compare")>0){
				$(".header-block").hide();
				$("#dealer_content").css('margin-top',"39px");
				$(".first-row").hide();	
			}
		}else{
			$(".compare-brokers-detail-main .compare-hd").hide();
			if(window.document.location.href.indexOf("broker/compare")>0){
				$(".header-block").show();
				$(".first-row").show();
				$("#dealer_content").css('margin-top',"0px");
			}
		}
	}).scroll();

	//log("win.width="+$win.width()+",container.w="+$(".container").width());
	var carousel_control_margin=($win.width()-$(".container").width())/2;	//carousel-control 的位置,与.container对齐
	//log("carousel_control_margin="+carousel_control_margin);
	$("#index-carousel-control-left").css("left",carousel_control_margin+"px");
	$("#index-carousel-control-right").css("right",carousel_control_margin+"px");

	//--------------------------------------------------------------
	//设置 #news-list-carousel .carousel-indicators > li 的宽度
	var $newsIdcLi=$("#news-list-carousel .carousel-indicators > li");
	var w=$newsIdcLi.parent().width()/$newsIdcLi.length - 4 * (1- 1/ $newsIdcLi.length);
	$newsIdcLi.each(function(){
		$(this).width(w);
	});
	//-------------------------
	//check-box 点击状态切换
	$(".check-box").click(function(event) {
		$(this).toggleClass("checked");
	});

	//-------------------------
	//登录 loginform-panel 切换
	$(".loginform-panel>.selector-row a").each(function(){
		var $this=$(this);
		$this.click(function(event) {
			event.preventDefault();

			$this.addClass("active").siblings().removeClass("active");
			var $panel=$this.parent().parent();

			if($this.index()==0) {
				$panel.find(".login-p1").show();
				$panel.find(".login-p2").hide();
			}else{
				$panel.find(".login-p1").hide();
				$panel.find(".login-p2").show();
			}
		});
	});
	//-------------------------
	//注册 panel 切换
	$(".loginform-panel .switch_reg_method").each(function(){
		var $this=$(this);
		$this.click(function(event) {
			event.preventDefault();
			$this.parentsUntil(".loginform-panel",".login-p1,.login-p2").toggle().siblings(".login-p1,.login-p2").toggle();
		});
	});

	//-------------------------
	//荔枝课堂 左边菜单点击
	$(".ketang-left .list-group .active").each(function(){
		var $this=$(this);
		var gly = $(this).find(".pull-right .glyphicon");
		var $item=$this;
		$this.click(function(event) {
			event.preventDefault();
			if(gly.hasClass( "glyphicon-triangle-bottom" )) {
				$item.nextUntil( "div" ).hide();
				gly.removeClass( "glyphicon-triangle-bottom" ).addClass("glyphicon-triangle-top");
			}else{
				$item.nextUntil( "div" ).show();
				gly.addClass( "glyphicon-triangle-bottom" ).removeClass("glyphicon-triangle-top");
			}
		});
	});


	//初始化所有 tooltip
	$('[data-toggle="tooltip"]').tooltip('show').tooltip('hide');//.tooltip('show');

	function modalevent(event) {
		var $body=$("body");

		//log("body.style="+$body.attr("style"));
		$body.css("padding-right","");//移除body的 style 属性 padding-right，防止右边空白带
	}
	//modal
	//$('.modal').on('shown.bs.modal', modalevent).on('hidden.bs.modal',modalevent);


	//对话框 close 按钮
	$('.modal .close-dlg').click(function(event){
		event.preventDefault();
		$(this).parentsUntil("modal").parent().modal('hide');
	});

	//===========================================================
	// 交易商 hot-product-row
	$(".hot-product-row .arrow-left").click(function(event){
		var content_w=$(".hot-product-row .content").width();
		var $table=$(".hot-product-row .content table");
		var table_left=$table.offset().left;
		if(table_left>=0){
			return;
		}
		//log("table.position:"+$table.offset().left);
		$table.animate({"left": '+='+(content_w) }, 1000);
	});
	$(".hot-product-row .arrow-right").click(function(event){		
		var content_w=$(".hot-product-row .content").width();
		var $table=$(".hot-product-row .content table");
		var table_left=parseInt($table.css("left"));
		var table_w=$table.width();
		log("table_left="+table_left+",table_w="+table_w);
		if((table_left+table_w-200)<=content_w){
			return;
		}
//		log("table.position:"+$table.offset().left);
		$table.animate({"left": '+='+(0-content_w) }, 1000);
	});
	//===========================================================
	//交易商列表 点差对比
	var $popdiff=$("#pop-compare-brokers");
	$popdiff.find(".toggle-op").click(function(event){//显示隐藏compare-items
		event.preventDefault();
		togglePopCompare();
	});

	function togglePopCompare(){
		var b=$popdiff.css("bottom");
		if(b!="0px") {
			//$popdiff.find(".compare-items").show();
			$popdiff.animate({"bottom": 0 }, 500);
		}else{
			//$popdiff.find(".compare-items").hide();
			$popdiff.animate({"bottom": -110 }, 500);
		}
		$popdiff.find(".toggle-op").find(".glyphicon").toggleClass("glyphicon-menu-up").toggleClass("glyphicon-menu-down");
	}

	$popdiff.find(".hide-pop-compare").click(function(event){//隐藏 pop-compare
		event.preventDefault();
		$popdiff.hide();
	});
	//是否登录
	$.ajax({
		dataType:"json",
		async:false,
		url:'user/isLogin',
		success:function(r){
			var isLogin=$("#isLogin");
			var html="";
			var msgTip="";
			if(r.messageNum>0){
				msgTip="new-msg-tip";
			}
			if(r.code==0){
				html="<div id='btnMyAccount' class='btn btn-myaccount'><a href='customer/home'>我的账户</a> <span class='glyphicon glyphicon-menu-down'></span></div>"+
				"<div id='btnMyAccounts' class='myaccount-menu'>"+
                 	"<a class='menu-item' href='customer/home'>账户总览</a>"+
                 	"<a class='menu-item' href='customer/trading'>交易账号</a>"+
	                "<a class='menu-item "+msgTip+"' href='customer/sysmsg'>未读消息</a>"+
	                "<a class='menu-item exit' href='user/logout'>退出</a>"+
                 "</div>";
			}
			else{
				html="<a class='btn btn-blue bc-blue' style='width:100px;' href='user/login'>登录</a> "+
				 "<a onclick='btnZc()' class='btn btn-blue' style='width:100px;' href='####' role='button' data-toggle='modal' data-target='#dlg-signup'>注册</a>";
			}
			isLogin.append(html);
		}
	});
	
	//-------------------------
	//我的账号 按钮
	$("#btnMyAccount").mouseover(function(event) {
		event.preventDefault();
		var $this=$(this);
		$this.addClass("opened").find(".glyphicon").addClass("glyphicon-menu-up").removeClass("glyphicon-menu-down");
		$(".myaccount-menu").show();
	});
	$("#btnMyAccount").mouseleave(function(event) {
		$("#btnMyAccount").removeClass("opened").find(".glyphicon").removeClass("glyphicon-menu-up").addClass("glyphicon-menu-down");
		$(".myaccount-menu").hide();
	});
	$("#btnMyAccounts").mouseover(function(event) {
		$("#btnMyAccount").addClass("opened").find(".glyphicon").addClass("glyphicon-menu-up").removeClass("glyphicon-menu-down");
		$(".myaccount-menu").show();
	});	
	$("#btnMyAccounts").mouseleave(function(event) {
		$("#btnMyAccount").removeClass("opened").find(".glyphicon").removeClass("glyphicon-menu-up").addClass("glyphicon-menu-down");
		$(".myaccount-menu").hide();
	});
	
	//--------------------------------------------------------------------------
	//循环滚动 中奖者名单 begin
	function scrollNews(obj){
	   var $firstRow = obj.find(".row:first");
	   var rowHeight = $firstRow.height();
	   $firstRow.animate({ "margin-top" : - rowHeight +"px" },600 , function(){
		   $firstRow.css({"margin-top":"0px"}).appendTo(obj);
	   });
	}
	var duobaoWinners = $(".duobao-winners");
    var scrollTimer;
    duobaoWinners.hover(function(){
			clearInterval(scrollTimer);
		 },function(){
			scrollTimer=setInterval(function(){
							scrollNews(duobaoWinners);
						},1500);
		}).trigger("mouseout");
	//循环滚动 中奖者名单 end
    
    //循环滚动 个人中心邀请排行榜 begin
    function scrollPhb(obj){
    	var $firstRow = obj.find(".row:first");
    	var rowHeight = $firstRow.height();
    	$firstRow.animate({ "margin-top" : - rowHeight +"px" },600 , function(){
    		$firstRow.css({"margin-top":"0px"}).appendTo(obj);
    	});
    }
    var yqphWinners = $(".yqph-winners");
    var scrollTimer;
    yqphWinners.hover(function(){
    	clearInterval(scrollTimer);
    },function(){
    	scrollTimer=setInterval(function(){
    		scrollPhb(yqphWinners);
    	},2000);
    }).trigger("mouseout");
    //循环滚动 个人中心邀请排行榜  end

	//----------------------------------
	var scrolling_top=false;
	$(".totop").click(function() {//回到顶部
		if(scrolling_top)return;
		scrolling_top=true;
		 //滚回顶部的时间，越小滚的速度越快~
		$('html,body').animate({'scrollTop' : 0}, 600, function() {
			// Animation complete.
			crolling_top=false;
			scrolling_top=false;
		});
	});
	
	//积分夺宝 jfdb-side-menu 菜单点击滚动效果
	var jfdb_scrolling=false;
	$('[data-scroll="jfdb-side-menu"]').click(function (){
		if(jfdb_scrolling) return;
		jfdb_scrolling=true;
		var seltor=$(this).data("scroll-to-target");
		var $target=$(seltor);
		//log(seltor);
		var top=$target.offset().top-30;
		if(seltor=="#jfcj") {
			top=top-50;
		}
		if(top<1)return;
        $('html, body').animate({scrollTop:top}, 700, function() {
			// Animation complete.
			jfdb_scrolling=false;
        });
     });
	//在线客服
	$.ajax({
		dataType:"json",
		async:false,
		url:'liveChat',
		success:function(r){
			if(r==""||r==null){
				$("a[name='liveChats']").each(function(){
					$(this).attr("href","javascript:void(0);");
				});
			}else{
				$("a[name='liveChats']").each(function(){
					$(this).attr("href",r);
				});
			}
			
		}
	});
	
	//侧边微信二维码隐藏显示
	$("#qrcode-close").click(function(){
		$(".tools-panel .wx-qrcode-static").css("display","none");
		addCookie("tools-panel-wx-static","no",60*60*24*5);
	});
	
	//顶部微信二维码隐藏显示
	$(".header-1 .top-wx").mousemove(function(){
		var left = ($(".header-1 .top-wx img").eq(0).offset().left)-80;
		$(".header-1 .top-wx .top-wx-show").css("left",left);
		$(".header-1 .top-wx img").eq(0).attr("src","resources/images/top-icon-wx2.png");
		$(".header-1 .top-wx .top-wx-show").css("display","block");
	}).mouseout(function(){
		$(".header-1 .top-wx img").eq(0).attr("src","resources/images/top-icon-wx1.png");
		$(".header-1 .top-wx .top-wx-show").css("display","none");
	});;
	
	
	//----------------------------------
	//忘记密码 切换 手机/邮箱 找回
	var $forget_pwd_btns=$('.forget-pwd-btn-mobile,.forget-pwd-btn-email');
	$forget_pwd_btns.click(function (){
			$forget_pwd_btns.removeClass("cur");
			$forget_pwd_btns.each(function(){
				$($(this).data("target")).hide();
			});
			$($(this).addClass("cur").data("target")).show();
	});
})(jQuery);










