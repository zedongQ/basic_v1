/**
 * Created by BJ on 2016/8/19.
 */
document.write("<script src='js/jquery-1.8.3.min.js'></script>");
document.write("<script src='js/cpTable.js'></script>");
document.write("<script src='js/CptCell.js'></script>");
document.write("<script src='js/CptRow.js'></script>");
//默认的交易商和货币对
//var proAndPairStr = "35&1&2&31&5&15&3&44&8&4&7&12&13&11&10&6&14&9&&&1&2&3&4&9&16&24&30&36&37";
//全部的交易商和货币对
var products =["axitrader-35","fxpro-1","xm-2","avatrade-31","thinkforex-5","gmi-15","markets-17","ads-44","forexclub-8","fxcm-4","ifx-7",
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
var contID = "cpTableContainer";
var table;

window.onload = function(){
    initOneTable();
};
function initOneTable(){
    var proAndPair = getProsAndParis();
    var containerID = contID;
    var param = {
        //各个项目显示或者不显示的选项，不为need为不要
        attrs:[
            {
                name:'交易商名称',attr:'nameLeft',flag:'need'
            },{
                name:'点差第一项',attr:'firstDianCha',flag:'need'
            },{
                name:'实际成本',attr:'actualCost',flag:'need'
            },{
                name:'外汇返现',attr:'whBack',flag:'need'
            }, {
                name:'黄金返现',attr:'goldBack',flag:'need'
            },{
                name:'返现',attr:'goldRebate',flag:'no'
            },{
                name:'交易商名称',attr:'nameRight',flag:'no'
            }
        ],
        products : proAndPair.products,
        curPairs : proAndPair.pairs,
        serverPath : 'ws://119.28.20.55:7002',
        url : 'http://123.59.52.95:8080/quotechart/dchac.do',
        //url : 'http://www.rentou.net/home/front/dianchacalc/selectAll',
        //url : 'http://106.75.192.235/home/front/dianchacalc/selectAll',
        containerID : containerID,
        titleType : "blue",//blue和yellow两种选项
        titleDCText : "no",//title上的货币对下的点差两字
        hasBottomSign : "yes",//yes是有签名，no是没有
        headHasHref : "no",//头部交易商有没有链接，no为没有链接
        imgHref : "no",//将超链接加到图片上
        superHead : "yes"//将表头固定住
    };
    table = new CpTable(param);
    table.init();
}

function getProsAndParis(){
    var url = window.location.href;
    var proAndPairStr = url.split("prosAndPairs&")[1];
    var proStr = proAndPairStr.split("&&&")[0];
    var pairStr = proAndPairStr.split("&&&")[1];
    var proArr = proStr.split("&");
    var pairArr = pairStr.split("&");
    var proStrRes = "";
    var pairStrRes = "";
    var iLen = proArr.length;
    var jLen = products.length;
    for(var i = 0;i<iLen;i++){
        for(var j = 0;j<jLen;j++){
            if(products[j].split("-")[1] == proArr[i]){
                proStrRes +=products[j]+",";
            }
        }
    }
    proStrRes = proStrRes.substring(0,proStrRes.length-1);
    iLen = pairArr.length;
    jLen = pairs.length;
    for(var i = 0;i<iLen;i++){
        for(var j = 0;j<jLen;j++){
            if(pairs[j].split("-")[1] == pairArr[i]){
                pairStrRes +=pairs[j]+",";
            }
        }
    }
    pairStrRes = pairStrRes.substring(0,pairStrRes.length-1);
    return {
        products:proStrRes,
        pairs:pairStrRes
    }
}
