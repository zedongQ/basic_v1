var whhlArray = new Array();
var whhlKArray = new Array();
function getUpdateK(msg) {
	var ss = msg.toString();
	var arrayT = ss.split(",");
	arrayT.shift();
	updateMapK(arrayT);
	return whhlKArray;
}
function updateMapK(arrayT) {
	var enameN = arrayT[0];
	var timeT = arrayT[1].substr(8, 6);
	var timeN = timeT.substr(0, 2) + ":" + timeT.substr(2, 2) + ":"
			+ timeT.substr(4, 2);
	var sellN = arrayT[2];
	var buyN = arrayT[3];
	var lenT=buyN.split(".");
	var len=lenT[1].length;
	for (var i = 0; i < whhlKArray.length; i++) {
		if (whhlKArray[i].ename === enameN) {
			var baseN=whhlKArray[i].base;
			var zdfN;
			var zdN;
			(buyN-baseN=="NaN")?zdN=0:zdn=buyN-baseN;
			(buyN-baseN=="NaN")?zdfN=0:zdfN=(buyN-baseN)/baseN;
			var nameNN = whhlKArray[i].name;
			var enameNN = whhlKArray[i].ename;
			var lowpriceT = whhlKArray[i].lowprice;
			var lowpriceN;
			if(lowpriceT > sellN){
				lowpriceN = sellN;
			}else{
				lowpriceN = lowpriceT;
			}
			var highpriceT = whhlKArray[i].highprice;
			var highpriceN;
			if(highpriceT > sellN){
				highpriceN = sellN;
			}else{
				highpriceN = highpriceT;
			}
			var sellDiffC = sellN - whhlKArray[i].sell;
			var buyDiffC = buyN - whhlKArray[i].buy;
			whhlKArray[i]={};
			whhlKArray[i]={
				"name" : nameNN,
				"ename" : enameNN,
				"time" : timeN,
				"zdf" : (zdfN * 100).toFixed(2) + "%",
				"zd" : zdN.toFixed(len),
				"lowprice" : lowpriceN,
				"highprice" : highpriceN,
				"sell" : sellN,
				"buy" : buyN,
				"base": baseN,
				"sellDiffC":sellDiffC,
				"buyDiffC":buyDiffC
			};
		}
	}
}
function getDataK(msg) {
	var msgStr = msg.toString();
	var arrayTemp = msgStr.split(",");
	arrayTemp.shift();
	var array = new Array();
	for (var j = 0; j < 5; j++) {
		for (var i = 0; i < 9; i++) {
			array.push(arrayTemp[i]);
		}
		addMapK(array);
		array = [];
		for (var k = 0; k < 10; k++) {
			arrayTemp.shift();
		}
	}
	return whhlKArray;
}
function addMapK(array) {
	var arrt = array[7].split(".");
	var leng = arrt[1].length;
	var zd = (array[5]) - (array[6]);
	var zdf = ((array[5]) - (array[6])) / (array[6]);
	var map = {
		"name" : array[1],
		"ename" : array[0],
		"time" : CurentTime(),
		"zdf" : (zdf * 100).toFixed(2) + "%",
		"zd" : zd.toFixed(leng),
		"lowprice" : array[8],
		"highprice" : array[7],
		"sell" : array[5],
		"buy" : array[4],
		"base":array[6],
		"sellDiffC":0,
		"buyDiffC":0
	};
	whhlKArray.push(map);

}
function getUpdate(msg) {
	var ss = msg.toString();
	var arrayT = ss.split(",");
	arrayT.shift();
	updateMap(arrayT);
	return whhlArray;
}
function updateMap(arrayT) {
	var enameN = arrayT[0];
	var timeT = arrayT[1].substr(8, 6);
	var timeN = timeT.substr(0, 2) + ":" + timeT.substr(2, 2) + ":"
			+ timeT.substr(4, 2);
	var sellN = arrayT[2];
	var buyN = arrayT[3];
	var lenT=buyN.split(".");
	var len=lenT[1].length;
	for (var i = 0; i < whhlArray.length; i++) {
		if (whhlArray[i].ename === enameN) {
			var baseN=whhlArray[i].base;
			var zdfN=(buyN-baseN)/baseN;
			var zdN=buyN-baseN;
			var nameNN = whhlArray[i].name;
			var enameNN = whhlArray[i].ename;
			var lowpriceT = whhlArray[i].lowprice;
			var lowpriceN;
			if(lowpriceT > sellN){
				lowpriceN = sellN;
			}else{
				lowpriceN = lowpriceT;
			}
			var highpriceT = whhlArray[i].highprice;
			var highpriceN;
			if(highpriceT > sellN){
				highpriceN = sellN;
			}else{
				highpriceN = highpriceT;
			}
			var sellDiffC = sellN - whhlArray[i].sell;
			var buyDiffC = buyN - whhlArray[i].buy;
			whhlArray[i]={};
			whhlArray[i]={
				"name" : nameNN,
				"ename" : enameNN,
				"time" : timeN,
				"zdf" : (zdfN * 100).toFixed(2) + "%",
				"zd" : zdN.toFixed(len),
				"lowprice" : lowpriceN,
				"highprice" : highpriceN,
				"sell" : sellN,
				"buy" : buyN,
				"base": baseN,
				"sellDiffC":sellDiffC,
				"buyDiffC":buyDiffC
			};
		}
	}
	
}
function getData(msg) {
	whhlArray=[];
	var msgStr = msg.toString();
	var arrayTemp = msgStr.split(",");
	arrayTemp.shift();
	var array = new Array();
	for (var j = 0; j < 6; j++) {
		for (var i = 0; i < 9; i++) {
			array.push(arrayTemp[i]);
		}
		addMap(array);
		array = [];
		for (var k = 0; k < 10; k++) {
			arrayTemp.shift();
		}
	}
	return whhlArray;
}
function addMap(array) {
	if(array[7]==null || array[7]==undefined){
		return;
	}
	var arrt = array[7].split(".");
	var leng = arrt[1].length;
	var zd = (array[5]) - (array[6]);
	var zdf = ((array[5]) - (array[6])) / (array[6]);
	var map = {
		"name" : array[1],
		"ename" : array[0],
		"time" : CurentTime(),
		"zdf" : (zdf * 100).toFixed(2) + "%",
		"zd" : zd.toFixed(leng),
		"lowprice" : array[8],
		"highprice" : array[7],
		"sell" : array[5],
		"buy" : array[4],
		"base":array[6],
		"sellDiffC":0,
		"buyDiffC":0
	};
	whhlArray.push(map);

}
function CurentTime() {
	var now = new Date();
	var hh = now.getHours(); // 时
	var mm = now.getMinutes(); // 分
	var ss = now.getSeconds(); // 秒
	var clock = "";
	if (hh < 10)
		clock += "0";
	clock += hh + ":";
	if (mm < 10)
		clock += "0";
	clock += mm + ":";
	if (ss < 10)
		clock += "0";
	clock += ss;
	return (clock);
}
function zdCK(zd){
	if(zd>0){
		return " class='cur_range1 zdf_red'";
	}else{
		return " class='cur_range1 zdf_green'";
	}
}
function zdC(zd){
	if(zd>0){
		return " class='zd_red size12_blod'";
	}else{
		return " class='zd_green size12_blod'";
	}
}
function zdfCK(zdf){
	var z=zdf.substr(0,1);
	if(z==="-"){
		return " class='cur_range1 zdf_green'";
	}else{
		return " class='cur_range1 zdf_red'";
	}
}
function zdfC(zdf){
	var z=zdf.substr(0,1);
	if(z==="-"){
		return " class='zdf_green size12_blod'";
	}else{
		return " class='zdf_red size12_blod'";
	}
}
function sellCK(zdf){
	var z=zdf.substr(0,1);
	if(z==="-"){
		return " class='cur_range zdf_green'";
	}else{
		return " class='cur_range zdf_red'";
	}
}
function sellC(sellDiffC){
	if(sellDiffC>0){
		return " class='size12_blod downs_rate'";
	}else if(sellDiffC<0){
		return " class='size12_blod up_rate'";
	}else{
		return "";
	}
}
function buyC(buyDiffC){
	if(buyDiffC>0){
		return " class='size12_blod downs_rate'";
	}else if(buyDiffC<0){
		return " class='size12_blod up_rate'";
	}else{
		return "";
	}
}
function updateLi(){
		if(whhlArray.length>6){
			for(var i=0;i<whhlArray-6;i++){
				whhlArray.shift();
			}
		}
		$.each(whhlArray, function(i, element) {
		var li = "<li class='hqzx_list-li'><div class='t_left'><span>" + 
		element.name + "</span><span class='name_english'>"+
		element.ename+"</span></div><div class='t_right'><span>"+
		element.time+"</span><span id='ups_downs_range'"+ 
		zdfC(element.zdf)+">"+
		element.zdf+"</span><span id='ups_downs'"+
		zdC(element.zd)
		+">"+
		element.zd+"</span><span id='lower_rate'>"+
		element.lowprice+"</span><span id='highest_rate'>"+
		element.highprice+"</span><span id='selling_rate'"+
		sellC(element.sellDiffC)
		+">"+
		element.sell+"</span><span id='buying_rate'"+
		buyC(element.buyDiffC)
		+">"+
		element.buy+"</span></div></li>";
		 $("#liebiao").append(li);
	 });
}
function updateLiKuang(){
		//alert(whhlKArray.length);
		if(whhlKArray.length>5){
			for(var i=0;i<whhlKArray-5;i++){
				whhlKArray.shift();
			}
		}
		$.each(whhlKArray, function(i, element) {
		var li = "<li class='cur_li'><div class='cur_two_name'>" + 
		element.name + "</div><div class='cur_exg'>"+
		element.ename+"</div><div "+
		sellCK(element.zdf)
		+">"+
		element.sell+"</div><div class='cur_up_down'><span "+
		zdCK(element.zd)
		+">"+
		element.zd+"</span><span class='slipt'>|</span><span "+
		zdfCK(element.zdf)+">"+
		element.zdf+"</span></div></li>";
		 $("#libiaokuang").append(li);
	 });
}
function zdT(zd){
	if(zd<0){
		return " class='txt-red'";
	}else{
		return " class='txt-green'";
	}
}
function zdfT(zdf){
	var z=zdf.substr(0,1);
	if(z==="-"){
		return " class='txt-red'";
	}else{
		return " class='txt-green'";
	}
}
function aaa(){
alert('dddd');
}
/*function isName(name){
	var newName;
			if(name.length>6){
				newName = name.substring(0,4)+"...";
				}
				else{
					newName = name;
					}
					return newName;
	}*/
function updateTr(data){
		//alert(whhlKArray.length);
		if(whhlArray.length>6){
			for(var i=0;i<whhlArray-5;i++){
				whhlArray.shift();
			}
		}
		$.each(whhlArray, function(i, element) {
			var zdTT;
			var zdfTT;
			(element.zd=="NaN")?zdTT=0:zdTT=element.zd;
			(element.zdf=="NaN%")?zdfTT=0+"%":zdfTT=element.zdf;
			var typeT = '"'+element.ename+'","'+element.name+'"';
			var tr = "<tr class='cursorP' data-num='"+(i+1)+"' onclick='selectLine(this," + typeT + ");'><td class='txt-blue'><span>" + 
			element.ename+"</span></td><td>"+
			element.sell+"</td><td "+
			zdT(element.zd)
			+">"+
			zdTT+"</td><td "+
			zdfT(element.zdf)+">"+
			zdfTT+"</td></tr>";
			 $("#mainContent").append(tr);
		});
		//在table上加了标记，否则无法判断哪个tr被选中，因为tr一直在更新！！！
		var checkTrNum=parseInt($("#mainContent").parent("table").attr("data-num"));
		if(checkTrNum){
			$("#mainContent tr[data-num='"+checkTrNum+"'").addClass("hover");
		}else{
			$("#mainContent tr[data-num='1'").addClass("hover");
		}
}
function selectLine(tr,type){
	tss(type);
	$("#mainContent tr").removeClass("hover");
	$(tr).addClass("hover");
	var dataNum = $(tr).attr("data-num");
	$("#mainContent").parent("table").attr("data-num",dataNum);
}
function setFirstLine(){
	$("#mainContent tr[data-num='1'").addClass("hover");
	$("#mainContent").parent("table").attr("data-num",1);
}
function useTe(sendValue){
	$.ajax({
		type: "GET",
		dataType: "json",
		url: "../../quotechart/gettokenl.do?mc=000&sv=000&av=000&mt=pc",
		success: function (data) {
			var ws = new WebSocket("ws://aliquote.fxhub.cn:5001");
            ws.onopen = function() {
                var msg = "c:0&"+data;
                ws.send(msg);
            }
            ws.onmessage = function(e) {
            	var msg = e.data.split("&");
            	if(msg[0].indexOf(":0") > 0){
            		var msg = sendValue;
                    ws.send(msg);
            	}
				else if(msg[0].indexOf(":2") > 0){
					getUpdate(msg);
					clearTr();
					updateTr(data);
            	}
            }
		},
		error:function(data){
			
		}
	});
}
function cleanA(){
	closeSend("c:3&EURUSD,GBPUSD,AUDUSD,USDCHF,USDCAD,USDCNH");
	closeSend("c:3&USSPX500,USTECH100,US30,Euro50,US2000USD,UK100");
	closeSend("c:3&BrentOil,Cocoa,Corn,Cotton,NaturalGas,WTI");
	closeSend("c:3&GER10YBond,UK10YBGBP,USB02YUSD,USB05YUSD,USB10YUSD,JPN10yBond");
	clearTr();
	whhlArray=[];
}
function dianjiUpdate(newValue){
	document.getElementById('chartDiv4').innerHTML = '';
	var sendValue="c:14&"+newValue;
	var closeValue="c:3&"+newValue;
	init(newValue.split(",")[0],"美国标普500");
	cleanA();
$().ready( function() {
	$.ajax({
		type: "GET",
		dataType: "json",
		url: "../../quotechart/gettokenl.do?mc=000&sv=000&av=000&mt=pc",
		success: function (data) {
			var ws = new WebSocket("ws://aliquote.fxhub.cn:5001");
            ws.onopen = function() {
                var msg = "c:0&"+data;
                ws.send(msg);
            }
            ws.onmessage = function(e) {
            	var msg = e.data.split("&");
            	if(msg[0].indexOf(":0") > 0){
            		var msg = sendValue;
                    ws.send(msg);
            	}
				else if(msg[0].indexOf(":14") > 0){
					closeSend(closeValue);
					clearTr();
					whhlArray=[];
					getData(msg);
					updateTr(data);
					ws=null;
					useTe(sendValue);
            	}
            }
		},
		error:function(data){
			
		}
	});
});
}
function clearTr(){
	var ul = document.querySelector("#mainContent");
					var lis = ul.querySelectorAll("tr");
					if(lis.length > 2) {
						for(var i=lis.length-1;i>=0;i--){
							lis[i].remove();
						}
					}
}
function closeSend(closeValue){
	$().ready( function() {
	$.ajax({
		type: "GET",
		dataType: "json",
		url: "../../quotechart/gettokenl.do?mc=000&sv=000&av=000&mt=pc",
		success: function (data) {
			var ws = new WebSocket("ws://aliquote.fxhub.cn:5001");
            ws.onopen = function() {
                var msg = "c:0&"+data;
                ws.send(msg);
            }
            ws.onmessage = function(e) {
            	var msg = e.data.split("&");
            	if(msg[0].indexOf(":0") > 0){
            		var msg = closeValue;
                    ws.send(msg);
            	}
				else if(msg[0].indexOf(":3") > 0){
					
            	}
            }
		},
		error:function(data){
			
		}
	});
});
}

//获取路径
function servicePath(){
    var curPath = window.document.location.href;
    var pathName = window.document.location.pathname;
    var pos = curPath.indexOf(pathName);
    var localhostPath = curPath.substring(0,pos);
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return localhostPath;
}

