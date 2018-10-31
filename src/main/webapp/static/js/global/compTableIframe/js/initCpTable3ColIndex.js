/**
 * Created by BJ on 2016/8/19.
 */
document.write("<script src='js/jquery-1.8.3.min.js'></script>");
document.write("<script src='js/cpTable.js'></script>");
document.write("<script src='js/CptCell.js'></script>");
document.write("<script src='js/CptRow.js'></script>");
document.write('<link rel="stylesheet" href="css/cpTableHead.css"/>');
//默认的交易商和货币对
var productsStrBase = "axitrader-35,fxpro-1,xm-2";
var pairsStrBase = "eurusd-1,gold-36";
var contID = "cpTableContainer";
var table;

window.onload = function(){
    initOneTable(productsStrBase,pairsStrBase,contID);
};
function initOneTable(products,pairs,containerID){
    var param = {
        //各个项目显示或者不显示的选项，不为need为不要
        attrs:[
            {
                name:'交易商名称',attr:'nameLeft',flag:'need'
            },{
                name:'点差第一项',attr:'firstDianCha',flag:'no'
            },{
                name:'实际成本',attr:'actualCost',flag:'no'
            },{
                name:'外汇返现',attr:'whBack',flag:'no'
            }, {
                name:'黄金返现',attr:'goldBack',flag:'no'
            },{
                name:'返现',attr:'goldRebate',flag:'need'
            },{
                name:'交易商名称',attr:'nameRight',flag:'no'
            }
        ],
        products : products,
        curPairs : pairs,
        serverPath : 'ws://119.28.20.55:7002',
        url : 'http://123.59.52.95:8080/quotechart/dchac.do',
        //url : 'http://www.rentou.net/home/front/dianchacalc/selectAll',
        //url : 'http://106.75.192.235/home/front/dianchacalc/selectAll',
        containerID : containerID,
        titleType : "yellow",//blue和yellow两种选项
        titleDCText : "yes",//title上的货币对下的点差两字
        hasBottomSign : "no",//yes是有签名，no是没有
        headHasHref : "yes",//头部交易商有没有链接，no为没有链接
        imgHref : "yes",//将超链接加到图片上
        superHead : "no"//将表头固定住
    };
    table = new CpTable(param);
    table.init();
}