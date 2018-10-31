var resultArray = new Array();
var ws;
var flagTimer;
var judgeTimer;
var judgeFlag = true;
//货币对子（全显示）：20个
var pairs ="eurusd,usdjpy,gbpusd,usdchf,audusd,eurgbp,gbpjpy,usdcad,gold,silver,audcad,audjpy,euraud,eurcad,eurchf,eurjpy,gbpaud,gbpchf,nzdusd,usdsgd";
//货币对子（默认显示）：10个
var notloginPairs="eurusd,usdjpy,gbpusd,usdchf,audusd,eurgbp,gbpjpy,usdcad,gold,silver";
var notloginPaNum="1,2,3,4,5,6,7,8,9,10";
var url = window.location.href;

var products = url.split("^");
var isLogin= products[1];
var  showStuste= products[2];
var pro = products[0].split("pros&")[1];
var jiaoyishang;
var moreXiangQiang;
	var ProductsAllList = [ "axitrader-35", "fxpro-1", "xm-2", "avatrade-31",
		"ThinkMarkets-5", "markets-17", "ads-44", "forexclub-8",
		"fxcm-4", "ifx-7", "oanda-12", "aetos-13", "forex.com-11", "gkfx-10",
		"exness-6", "vantagefx-14", "lmax-9", "icmcapital-22", "icmarkets-23",
		"tickmill-24", "britanniafx-25", "svsfx-26", "gmo-27", "kvbkunlun-28",
		"ausforex-29", "formax-30", "charterprime-32", "mdf-33", "tahoe-34",
		"roboforex-36", "gomarkets-37", "henyep-38", "usgfx-39",
		"pepperstone-40", "infinox-41", "xcoq-42", "ig-43" ,"TeraFX-45"];
for(var i=0;i<ProductsAllList.length;i++){
	var jiaoyishangl=ProductsAllList[i].split("-");
	if(jiaoyishangl[1]==pro){
		jiaoyishang=jiaoyishangl[0].toUpperCase();
  if(pro=="1"){
   moreXiangQiang="http://www.ibrebates.com/jys_list/20140917/418098.html";
  }else if(pro=="2"){
  moreXiangQiang= "http://www.ibrebates.com/jys_list/20140917/418093.html";
  }else if(pro=="3"){	
   moreXiangQiang="http://www.ibrebates.com/jys_list/20140917/454092.html"
  } else if(pro=="4"){
   moreXiangQiang="http://www.ibrebates.com/jys_list/20140917/418088.html";
  }else if(pro=="5"){	
   moreXiangQiang="http://www.ibrebates.com/jys_list/20160317/462882.html";
  } else if(pro=="6"){
   moreXiangQiang="http://www.ibrebates.com/jys_list/20140917/418101.html";
  }else if(pro=="7"){
   moreXiangQiang="http://www.ibrebates.com/jys_list/20140917/418089.html";
  }else if(pro=="8"){
   moreXiangQiang="http://www.ibrebates.com/jys_list/20140917/418096.html";
  } else if(pro=="9"){
   moreXiangQiang="http://www.ibrebates.com/jys_list/20140917/418095.html";
  }else if(pro=="10"){
   moreXiangQiang="http://www.ibrebates.com/jys_list/20140917/418094.html";
  } else if(pro=="11"){
  moreXiangQiang="http://www.ibrebates.com/jys_list/20140917/418091.html";
  }else if(pro=="12"){
   moreXiangQiang= "http://www.ibrebates.com/jys_list/20140917/418087.html";
  }else if(pro=="13"){	  
   moreXiangQiang= "http://www.ibrebates.com/jys_list/20140917/418100.html";
  }else if(pro=="14"){	
   moreXiangQiang= "http://www.ibrebates.com/jys_list/20160612/466793.html";
  }else if(pro=="15"){
   moreXiangQiang="http://www.ibrebates.com/jys_list/20160823/468069.html";
  }else if(pro=="17"){
   moreXiangQiang="http://www.ibrebates.com/jys_list/20140917/454092.html";
  }else if(pro=="31"){
  var moreXiangQiang="http://www.ibrebates.com/jys_list/20160913/468412.html";
  }else if(pro=="35"){
   moreXiangQiang= "http://www.ibrebates.com/jys_list/20160913/468408.html";
  }else if(pro=="44"){
   moreXiangQiang= "http://www.ibrebates.com/jys_list/20160905/468257.html";
  }else{
	  moreXiangQiang="notproxy";
  }
	}
}
window.onload = function(){
	 var parentUrl=document.referrer;		
	var pading1=parentUrl.indexOf("talkfx.com");
	//var pading3=parentUrl.indexOf("http://106.3.137.186/Cp/lxdetail");
	if(pading1>=0){
		 document.getElementById("Poweredy").innerHTML='Powered by talkfx.com';
		 document.getElementById("gobackurl").innerHTML='<a href="http://www.talkfx.com/Cp/cpdetail" target="_blank">更多交易商实时点差 >></a></div>';
	}   
	 document.getElementById("jysid").innerHTML=jiaoyishang;
	if(moreXiangQiang=="notproxy"){
	document.getElementById("dc1").innerHTML="";
	}else{
	  //document.getElementById("dc1").innerHTML='该交易商为IB返现网代理交易商，高额返现，真实降低交易成本<span class="gaps1"></span> <a href="'+moreXiangQiang+'" target="_blank" class="learning">点此了解</a>'
		document.getElementById("dc1").innerHTML='';
	}
	init();resetTimeout();
};
function resetTimeout(){
    //星期六、星期天就不会再断线重连了。
    var date = new Date();
   /* if(date.getDay().toString() == "0" || date.getDay().toString() == "6"){
        return;
    }*/
    if(flagTimer){
        clearTimeout(flagTimer);
    }
    flagTimer = setTimeout(function(){
        getProData();
    },10000);
}
function getProData(){
	if(ws){
		ws.close();
		ws=null;
	}
	resetTimeout();
	ws = new WebSocket("ws://119.28.20.55:7002");
	ws.onopen = function(event){
         ws.send("m&s&"+pro);	
		  // ws.send("m&s&1");
    };
    //监听消息
    ws.onmessage = function(event){
		if(event.data == "m&s"){
			 if(isLogin==0){
				  ws.send("m&i&1,2,3,4,9,16,24,30,36,37");
	       }else{			
		   ws.send("m&i&1,2,3,4,5,7,9,13,14,15,16,17,21,23,24,29,30,35,36,37,38,39");
		   }
        }
		resetTimeout();
        var msg = event.data.split("&");
         if(msg.length < 1){
              return ;
         }
         if(msg[0] == "mtd"){
              getData(msg);
         }
    };
    // 监听Socket的关闭
    ws.onclose = function(event) {
        console.log('Client notified socket has closed',event);
    };

    // 监听Socket的关闭
    ws.onerror = function(event) {
        console.log('Client notified socket has error',event);
    };
}
function getData(msg){
	var msgStr = msg.toString();
	var arrayTemp = msgStr.split(",");
	var num=arrayTemp[1];
	var dateT=arrayTemp[6];
	var dateF=dateT.substr(0,4)+"-"+dateT.substr(4,2)+"-"+dateT.substr(6,2)+" "+dateT.substr(8,2)+":"+dateT.substr(10,2)+":"+dateT.substr(12,2);
	var item={
		jiaoyishang:arrayTemp[1],
		huobidui:arrayTemp[2],
		buyN:arrayTemp[3],
		sellN:arrayTemp[4],
		diancha:arrayTemp[5],
		dateN:dateF
	};
	resultArray[num]=item;
	getFinallyData(num);

}
function getFinallyData(num){	
	if(isLogin==1&&showStuste!="0"&&showStuste!="FanYong"&&showStuste!="TouZiGuanJia"){
		 var allhbdlist=pairs.split(",");
	}else{var allhbdlist=notloginPairs.split(",");}
	//var allhbdlist=pairs.split(",");
	var huobidui=resultArray[num].huobidui;
	for(var i=0;i<allhbdlist.length;i++){	
		if(allhbdlist[i]==huobidui){
               var HBDNum="HBD"+(i+1);
			   var buyN="buyN"+(i+1);
			   var sellN="sellN"+(i+1);					  
			   var diancha="diancha"+(i+1);
			  hbdNames=huobidui.toUpperCase();
			    if(hbdNames=="SILVER"){
				  hbdNames="XAGUSD";}
				if(hbdNames=="GOLD"){
				  hbdNames="XAUUSD";}
				hbdName=hbdNames.substring(0,3)+"/"+hbdNames.substring(3,6);	
				//document.getElementById(HBDNum).innerHTML = hbdName;
				var oldbuy=document.getElementById(buyN).innerText;
				var oldbuy=oldbuy*1;
				if(resultArray[num].buyN>oldbuy){
					document.getElementById(buyN).className="twoTd";
				}else if(resultArray[num].buyN<oldbuy){
					document.getElementById(buyN).className="threeTd";
				}else{document.getElementById(buyN).className="fourTh";}
			    document.getElementById(buyN).innerHTML = resultArray[num].buyN;
				
				var oldsell=document.getElementById(sellN).innerText;
				var oldsell=oldsell*1;
				if(resultArray[num].sellN>oldsell){
					document.getElementById(sellN).className="twoTd";
				}else if(resultArray[num].sellN<oldsell){
					document.getElementById(sellN).className="threeTd";
				}else{document.getElementById(sellN).className="fourTh";}
			    document.getElementById(sellN).innerHTML = resultArray[num].sellN;
				
				var olddiancha=document.getElementById(diancha).innerText;
				var olddiancha=olddiancha*1;
				if(resultArray[num].diancha>olddiancha){
					document.getElementById(diancha).className="twoTd";
				}else if(resultArray[num].diancha<olddiancha){
					document.getElementById(diancha).className="threeTd";
				}else{document.getElementById(diancha).className="fourTh";}
			    document.getElementById(diancha).innerHTML = resultArray[num].diancha;  	
	    }	
    }
}
function init(){
	// 是否登录
	  document.getElementById("tableLeft").innerHTML='<tr>'
                   + '<th class="oneTh">Currency Pair</th>'
                    +'<th class="twoTh">Bid</th>'
                    +'<th class="threeTh">Ask</th>'
                    +'<th class="fourTh">Spread</th>'
                +'</tr>'
				+'<tr>'
                +'<td class="oneTd" id="HBD1">EUR/USD</td>'
                +'<td  class="twoTd" id="buyN1">--</td>'
                +' <td   class="threeTd" id="sellN1">--</td>'
                +'    <td  class="fourTd" id="diancha1">--</td>'
                +' </tr> '
				+'<tr>'
                 +'   <td   class="oneTd" id="HBD3">GBP/USD</td>'
                 +'   <td  class="twoTd"   id="buyN3">--</td>'
                +'    <td  class="threeTd" id="sellN3">--</td>'
                +'    <td  class="fourTd" id="diancha3">--</td>'
                +'  </tr> '
				+'<tr>'
                 +'   <td class="oneTd" id="HBD5">AUD/USD</td>'
                 +'   <td   class="twoTd" id="buyN5">--</td>'
                 +'   <td  class="threeTd" id="sellN5">--</td>'
                +'    <td class="fourTd" id="diancha5">--</td>'
                +' </tr> '
				+'<tr>'
                  +'  <td class="oneTd" id="HBD7">GBP/JPY</td>'
                  +'  <td  class="twoTd" id="buyN7">--</td>'
                  +'  <td  class="threeTd" id="sellN7">--</td>'
                  +'  <td class="fourTd"  id="diancha7">--</td>'
                +'</tr> '
				+'<tr>'
                   +' <td  class="oneTd" id="HBD9">XAU/USD</td>'
                 + ' <td  class="twoTd" id="buyN9">--</td>'
                 + ' <td  class="threeTd" id="sellN9">--</td>'
                 + '<td class="fourTd"  id="diancha9">--</td>'
                 +'</tr>';
				
           document.getElementById("tableRight").innerHTML='<tr>'
                    +'<th class="oneTh">Currency Pair</th>'
                   +' <th class="twoTh">Bid</th>'
                    +'<th class="threeTh">Ask</th>'
                    +'<th class="fourTh">Spread</th>'
                +'</tr>'
		        +'<tr>'
                +'    <td class="oneTd" id="HBD2">USD/JPY</td>'
                +'    <td  class="twoTd" id="buyN2">--</td>'
                +'    <td class="threeTd"  id="sellN2">--</td>'
                +'    <td  class="fourTd" id="diancha2">--</td>'
                +' </tr> '
				+'<tr>'
                 +'   <td  class="oneTd" id="HBD4">USD/CHF</td>'
                 +'   <td  class="twoTd" id="buyN4">--</td>'
                +'    <td  class="threeTd" id="sellN4">--</td>'
                +'    <td  class="fourTd" id="diancha4">--</td>'
                +'  </tr> '
				+'<tr>'
                 +'   <td class="oneTd" id="HBD6">EUR/GBP</td>'
                 +'   <td class="twoTd"  id="buyN6">--</td>'
                 +'   <td  class="threeTd" id="sellN6">--</td>'
                +'    <td  class="fourTd" id="diancha6">--</td>'
                +' </tr> '
				+'<tr>'
                  +'  <td class="oneTd" id="HBD8">USD/CAD</td>'
                  +'  <td class="twoTd" id="buyN8">--</td>'
                  +'  <td class="threeTd" id="sellN8">--</td>'
                  +'  <td  class="fourTd" id="diancha8">--</td>'
                +'</tr> '
				+'<tr>'
                   +' <td class="oneTd" id="HBD10">XAG/USD</td>'
                 + ' <td  class="twoTd" id="buyN10">--</td>'
                 + ' <td  class="threeTd" id="sellN10">--</td>'
                 + '<td  class="fourTd" id="diancha10">--</td>'
                 +'</tr>';					 
    if(isLogin==1&&showStuste!="0"&&showStuste!="FanYong"&&showStuste!="TouZiGuanJia"){
		var proAlll=pairs.split(",");
      
        var proAlllength=proAlll.length;			
		var listlength=proAlllength-10;
		 var otlist=new Array(10);
	 	  for(var i=0;i<10;i++){	
		  	otlist[i]=document.createElement('tr');
		  }
        for(var i=0;i<5;i++){
			var ltPro =proAlll[i+11].toUpperCase().substring(0,3)+"/"+proAlll[i+11].toUpperCase().substring(3,6);	
			 var rtPro= proAlll[proAlllength-i-1].toUpperCase().substring(0,3)+"/"+proAlll[proAlllength-i-1].toUpperCase().substring(3,6);	 
			var liftNUM=11+i;
			var rightNUM=proAlllength-i;
			otlist[i].innerHTML='<tr>'
                +'<td class="oneTd" id="HBD'+liftNUM+'">'+ltPro+'</td>'
                +'    <td  class="twoTd" id="buyN'+liftNUM+'">--</td>'
                +'    <td class="threeTd"  id="sellN'+liftNUM+'">--</td>'
                +'    <td  class="fourTd" id="diancha'+liftNUM+'">--</td>'
                +' </tr> ';	
	        document.getElementById("tableLeft").appendChild(otlist[i]);	
			otlist[listlength-i-1].innerHTML='<tr>'
                +'    <td class="oneTd" id="HBD'+rightNUM+'">'+rtPro+'</td>'
                +'    <td  class="twoTd" id="buyN'+rightNUM+'">--</td>'
                +'    <td class="threeTd"  id="sellN'+rightNUM+'">--</td>'
                +'    <td  class="fourTd" id="diancha'+rightNUM+'">--</td>'
                +' </tr> ';
	        document.getElementById("tableRight").appendChild(otlist[listlength-(i+1)]);
		}
	
  }
	getProData();
	
}
function login(){
	window.parent.location.href='https://www.ibrebates.com/home/html/login/login.html?dir=DCKongJian^'+pro;
}





























