/**
 * Created by BJ on 2016/9/21.
 */
//默认的交易商和货币对
var productsStrBase = "axitrader-35,fxpro-1,xm-2,avatrade-31,thinkforex-5,gmi-15,markets-17,ads-44,fxcm-4,ifx-7,"
	+"oanda-12,aetos-13,forex.com-11,gkfx-10,exness-6,"
    +"vantagefx-14,lmax-9";
var pairsStrBase = "eurusd-1,usdjpy-2,gbpusd-3,usdchf-4,audusd-9,eurgbp-16,gbpjpy-24,usdcad-30,gold-36,silver-37";
var proBaseStr = "35&1&2&31&5&15&17&44&4&7&12&13&11&10&6&14&9";
var pairBaseStr = "&&&1&2&3&4&9&16&24&30&36&37";
//全部的交易商和货币对
var products =["axitrader-35","fxpro-1","xm-2","avatrade-31","thinkforex-5","gmi-15","markets-17","ads-44","fxcm-4","ifx-7",
    "oanda-12","aetos-13","forex.com-11","gkfx-10","exness-6","vantagefx-14","lmax-9",
    "icmcapital-22","icmarkets-23","tickmill-24","britanniafx-25","svsfx-26","gmo-27","kvbkunlun-28","ausforex-29","formax-30",
    "charterprime-32","mdf-33","tahoe-34","roboforex-36","gomarkets-37","henyep-38","usgfx-39","pepperstone-40",
    "infinox-41","xcoq-42","ig-43"
];
var pairs =["eurusd-1","usdjpy-2","gbpusd-3","usdchf-4","audcad-5","audchf-6",
    "audjpy-7","audnzd-8","audusd-9","cadchf-10","cadjpy-11","chfjpy-12",
    "euraud-13","eurcad-14","eurchf-15","eurgbp-16","eurjpy-17","eurnok-18",
    "eurnzd-19","eursek-20","gbpaud-21","gbpcad-22","gbpchf-23","gbpjpy-24",
    "gbpnzd-25","nzdcad-26","nzdchf-27","nzdjpy-28","nzdusd-29","usdcad-30",
    "usdcnh-31","usdnok-32","usdsek-33","usdtry-34","usdsgd-35","gold-36",
    "silver-37","usoil-38","ukoil-39"
];
var iframeId = "compIframe";
var proLastList = proBaseStr;
var pairsLastList = pairBaseStr;
$(function(){
	initSelHead(iframeId);
});

//初始化头部的代码，默认是有选择交易商和货币对的头部的
function initSelHead(containerID){
    var html ='<div class="bj-selItemLeft"><div class="bj-selILText">外汇交易商实时点差对比</div><div class="bj-selILIcon">' +
        '<img id="popQuesPic" src="analysis/analysisimg/questionIcon.png"/><div id="popTipbox" class="bj-selILITip">' +
        '<p class="bj-sleILHeadP">交易商点差对比</p><p>此为真实环境下的交易商点差对比；</p><p>右下角数据为扣除返佣之后的实际交易成本。</p></div>' +
        '</div><div class="bj-smallPovered">Povered by ibrebates.com</div></div><div class="bj-selItemRight" style="display:none;"><div id="selDealerButton" class="bj-selIRDivL">交易商' +
        '<div id="selDealer" class="bj-selDealer"></div></div><div id="selPairsButton" class="bj-selIRDivR">货币对' +
        '<div id="selPairs" class="bj-selPairs"></div></div></div>';
    var selHeadDiv = document.createElement("div");
    selHeadDiv.innerHTML = html;
    selHeadDiv.setAttribute("class","bj-selItemsDiv");
    var container = document.getElementById(containerID);
    //selHeadDiv.style.width = container.offsetWidth+"px";
    container.parentNode.insertBefore(selHeadDiv,container);
    putPairs();
    putProducts();
    setPopboxEvents();

    //设置iframe高度
    initIframe(proBaseStr+pairBaseStr,proBaseStr.split("&").length);
}
//初始化交易商选择框
function putProducts(){
    var html="<h5>选择交易商</h5>";
    html+='<label id="selAllLable" for="dealerAll"><input name="selDealerAll" id="dealerAll"type="checkbox" value="all"/><span style="color:#367cd3;">全选</span></label><br/>';
    for(var i=0;i<products.length;i++){
        html+='<label for="p'+products[i].split("-")[1]+'"><input name="selectedProducts" id="p'+products[i].split("-")[1]+'"type="checkbox" value="'
        +products[i]+'"/><span>'+products[i].split("-")[0].toUpperCase()+'</span></label>';
    }
    html+='<div class="bj-selBtn"><a id="prodConf" style="text-decoration:none;"class="bj-prodConf">确定</a></div>';
    document.getElementById("selDealer").innerHTML = html;
    setProductsChecked();
}
function setProductsChecked(){
    var proSBList = productsStrBase.split(",");
    var prodtAllLen = products.length;
    var length = proSBList.length;
    if(prodtAllLen == length){
        $("#dealerAll")[0].checked = true;
    }
    for(var j=0;j<length;j++){
        $("#p"+proSBList[j].split("-")[1])[0].checked = true;
    }
}
//初始化货币对选择框
function putPairs(){
    var html="<h5>选择货币对</h5>";
    for(var i=0;i<pairs.length;i++){
        if(i<pairs.length - 4){
            html+='<label for="'+pairs[i]+'"><input name="selectedPairs" id="'+pairs[i]+'"type="checkbox" value="'
            +pairs[i]+'"/><span>'+pairs[i].toUpperCase().substring(0,3)+'/'+pairs[i].split("-")[0].toUpperCase().substring(3,6)+'</span></label>';
        }else{
            if(pairs[i] == "usoil-38"){
                html+='<label for="'+pairs[i]+'"><input name="selectedPairs" id="'+pairs[i]+'"type="checkbox" value="'+pairs[i]+'"/><span>WTI</span></label>';
            }else if(pairs[i] == "ukoil-39"){
                html+='<label for="'+pairs[i]+'"><input name="selectedPairs" id="'+pairs[i]+'"type="checkbox" value="'+pairs[i]+'"/><span>BRENT</span></label>';
            }else{
                html+='<label for="'+pairs[i]+'"><input name="selectedPairs" id="'+pairs[i]+'"type="checkbox" value="'
                +pairs[i]+'"/><span>'+pairs[i].split("-")[0].toUpperCase()+'</span></label>';
            }
        }
    }
    html+='<span id="selTooMuchTip" class="bj-selParisTip"></span>';
    html+='<div class="bj-selBtn"><a id="pairsConf"style="text-decoration:none;" class="bj-pairsConf">确定</a></div>';
    document.getElementById("selPairs").innerHTML = html;
    setPairsChecked();
}
function setPairsChecked(){
    var pairsSBList = pairsStrBase.split(",");
    var length = pairsSBList.length;
    for(var j=0;j<length;j++){
        $("#"+pairsSBList[j])[0].checked = true;
    }
    $("#selTooMuchTip").hide();
}
//绑定弹出框的事件，如果页面上还有其他的原生弹出框使用了event.stopPropagation()，会出问题
function setPopboxEvents(){
    //小圆问号，鼠标移动上去就出现的提示框
    var popTipbox = document.getElementById("popTipbox");
    var popQuesPic = document.getElementById("popQuesPic");
    popQuesPic.onmouseover = function(){
        popTipbox.style.display = "block";
    };
    popQuesPic.onmouseout = function(){
        popTipbox.style.display = "none";
    };
    //选择交易商的弹出框的事件
    var selDealerButton =document.getElementById("selDealerButton");
    var selDealer = document.getElementById("selDealer");
    var selAllLable = document.getElementById("selAllLable");
    var selAll = document.getElementsByName("selDealerAll")[0];
    var selDealerLabels = selDealer.getElementsByTagName("label");
    var selProducts = document.getElementsByName("selectedProducts");
    var prodConf = document.getElementById("prodConf");
    //所有的label取消冒泡
    for(var i=0;i<selDealerLabels.length;i++){
        selDealerLabels[i].onclick = function(event){
            event.stopPropagation();
        }
    }
    //全选按钮
    selAllLable.onclick = function(event){
        event.stopPropagation();
        if(selAll.checked == true){
            for(var i = 0;i<selProducts.length;i++){
                selProducts[i].checked = true;
            }
        }else{
            for(var i = 0;i<selProducts.length;i++){
                selProducts[i].checked = false;
            }
        }
    };
    //弹出框触发按钮
    selDealerButton.onclick = function(event){
        selPairs.style.display = "none";
        event.stopPropagation();
        if(selDealer.style.display == "block"){
            selDealer.style.display = "none";
            refreshComTable();
        }else{
            selDealer.style.display = "block";
        }
    };
    selDealer.onclick = function(event){
        event.stopPropagation();
    };
    //确定按钮
    prodConf.onclick=function(event){
        event.stopPropagation();
        selDealer.style.display = "none";
        refreshComTable();
        return false;
    };

    //选择货币对的弹出框的事件selPairsButton
    var selPairsButton =document.getElementById("selPairsButton");
    var selPairs = document.getElementById("selPairs");
    var pairsLabels = selPairs.getElementsByTagName("label");
    var selectedPairs = document.getElementsByName("selectedPairs");
    var selTooMuchTip = document.getElementById("selTooMuchTip");
    var pairsConf = document.getElementById("pairsConf");
    //弹出框触发按钮
    selPairsButton.onclick = function(event){
        selDealer.style.display = "none";
        event.stopPropagation();
        if(selPairs.style.display == "block"){
            selPairs.style.display = "none";
            refreshComTable();
        }else{
            selPairs.style.display = "block";
        }
    };
    //阻止交易商选择框的冒泡事件
    selPairs.onclick = function(event){
        event.stopPropagation();
    };
    pairsConf.onclick = function(event){
        event.stopPropagation();
        selPairs.style.display = "none";
        refreshComTable();
    };
    //在每选择一个checkBox的时候判断是否多选
    for(var i=0;i<pairsLabels.length;i++){
        pairsLabels[i].onclick = function(event){
            var count=0;
            for(var j=0;j<selectedPairs.length;j++){
                if(selectedPairs[j].checked == true){
                    count++;
                }
            }
            if(count>=11){
                selTooMuchTip.style.display = "block";
                selTooMuchTip.innerHTML = "一次最多比较十个货币对";
            }else if(count<=0){
                selTooMuchTip.style.display = "block";
                selTooMuchTip.innerHTML = "一次最少选择一个货币对";
            }else{
                selTooMuchTip.style.display = "none";
            }
            if(count==11){
                return false;
            }
        }
    }
    //形成参数字符串发送消息，刷新表格
    function refreshComTable(){
        var countProducts = 0;
        var countPairs = 0;
        var productsStr = "";
        for(var i=0;i<selProducts.length;i++){
            if(selProducts[i].checked == true){
                productsStr = productsStr + selProducts[i].value.split("-")[1] + "&";
                countProducts++;
            }
        }
        productsStr = productsStr.substring(0,productsStr.length-1);
        var pairsStr = "&&&";
        for(var j=0;j<selectedPairs.length;j++){
            if(selectedPairs[j].checked == true){
                pairsStr = pairsStr + selectedPairs[j].value.split("-")[1] + "&";
                countPairs++;
            }
        }
        pairsStr = pairsStr.substring(0,pairsStr.length-1);

        if(countProducts == selProducts.length){
            $("#dealerAll")[0].checked = true;
        }else{
            $("#dealerAll")[0].checked = false;
        }
        if(countProducts>0&&countPairs>=1){
            if(productsStr == proLastList&&pairsStr == pairsLastList){
                return;
            }
            proLastList = productsStr;
            pairsLastList = pairsStr;
            if(countProducts == 0){
                countProducts = productsStrBase.split(",").length;
            }
            initIframe(productsStr+pairsStr,countProducts);
        }else if(countProducts>0&&countPairs<1){
            setPairsChecked();
            if(productsStr == proLastList&&pairsLastList == pairsStrBase){
                return;
            }
            proLastList = productsStr;
            pairsLastList = pairsStrBase;
            if(countProducts == 0){
                countProducts = productsStrBase.split(",").length;
            }
            initIframe(productsStr+pairBaseStr,countProducts);
        }else if(countProducts==0&&countPairs>=1){
            setProductsChecked();
            if(proLastList == productsStrBase&&pairsStr == pairsLastList){
                return;
            }
            proLastList = productsStrBase;
            pairsLastList = pairsStr;
            if(countProducts == 0){
                countProducts = productsStrBase.split(",").length;
            }
            initIframe(proBaseStr+pairsStr,countProducts);
        }else{
            setProductsChecked();
            setPairsChecked();
            if(proLastList == productsStrBase&&pairsLastList == pairsStrBase){
                return;
            }
            proLastList = productsStrBase;
            pairsLastList = pairsStrBase;
            if(countProducts == 0){
                countProducts = productsStrBase.split(",").length;
            }
            initIframe(proBaseStr+pairBaseStr,countProducts);
        }
    }
}
function initIframe(proAndPairStr,rowCount){
    var baseStr = 'http://123.59.52.95:8080/compTableIframe/cpTable.html?prosAndPairs&';
    var paramStr = baseStr + proAndPairStr;
    var iframeHeight = rowCount*69 + 38 + 50 +1;
    var iframeWidth = 1190 + "px";
    document.getElementById(iframeId).setAttribute("src",paramStr);
    //document.getElementById(iframeId).setAttribute("width",iframeWidth);
    if(iframeHeight>245){
        iframeHeight = 246;
        if(userAgent() == "safari or chrome"){
            iframeHeight = 245;
        }
    }
    document.getElementById(iframeId).setAttribute("height",iframeHeight+"px");
}
function userAgent(){
    var ua = navigator.userAgent;
    ua = ua.toLowerCase();
    var match = /(webkit)[ \/]([\w.]+)/.exec(ua) ||
        /(opera)(?:.*version)?[ \/]([\w.]+)/.exec(ua) ||
        /(msie) ([\w.]+)/.exec(ua) ||
        !/compatible/.test(ua) && /(mozilla)(?:.*? rv:([\w.]+))?/.exec(ua) ||
        [];
//match[2]判断版本号
    switch(match[1]){
        case "msie":      //ie
            if (parseInt(match[2]) == 6)    //ie6
                return("ie6");
            else if (parseInt(match[2]) == 7)    //ie7
                return("ie7");
            else if (parseInt(match[2]) == 8)    //ie8
                return("ie8");
            break;
        case "webkit":     //safari or chrome
            return("safari or chrome");
            break;
        case "opera":      //opera
            return("opera");
            break;
        case "mozilla":    //Firefox
            return("Firefox");
            break;
        default:
            break;
    }
}