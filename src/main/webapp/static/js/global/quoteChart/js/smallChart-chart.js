/**
 * Created by BJ on 2016/7/5.
 */
$(function(){
    init();
});
var requestTime = 0;
function mAjax(url, param, callBackMethod,detail) {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: url,
        data: param,
        success: function(data){
            callBackMethod(data,detail);
        },
        error: function (data) {
            //继续访问
            if (requestTime < 10) {
                mAjax('/quotechart/gettokenl.do', 'mc=000&sv=000&av=000&mt=pc', callBackMethod);
                requestTime++;
            }
        }
    });
}

function successGetToken(token,configParam) {
    configParam.token = token;
    var chChart = new ChCanvas(configParam);
    chChart.init();
}
var detailData1 = {
        containerID: 'chartDiv1',
        chartType: 1,//图表的种类，第一种文字在左边，第二种文字在右边
        width : 285,//建议第一种图宽
        height : 110,//建议第一种图高
        //width: 206,//建议第二种图宽
        //height: 140,//建议第二种图高
        productName: '欧元/美元',
        product: 'EURUSD',
        serverPath: 'ws://aliquote.fxhub.cn:5001',
        token: 0,
        //第一种图，列数建议不要超过 width*73%-50-10,因为实际的画图区域宽是width*73%-50
        //第二种图，列数建议不要超过 width-10-50
        dataColumn: 65
};
var detailData2 = {
    containerID: 'chartDiv2',
    chartType: 1,//图表的种类，第一种文字在左边，第二种文字在右边
    width : 285,//建议第一种图宽
    height : 110,//建议第一种图高
    //width: 206,//建议第二种图宽
    //height: 140,//建议第二种图高
    productName: '欧元/美元',
    product: 'EURUSD',
    serverPath: 'ws://aliquote.fxhub.cn:5001',
    token: 0,
    //第一种图，列数建议不要超过 width*73%-50-10,因为实际的画图区域宽是width*73%-50
    //第二种图，列数建议不要超过 width-10-50
    dataColumn: 65
};
var detailData3 = {
    containerID: 'chartDiv3',
    chartType: 2,//图表的种类，第一种文字在左边，第二种文字在右边
    //width : 285,//建议第一种图宽
    //height : 110,//建议第一种图高
    width: 206,//建议第二种图宽
    height: 140,//建议第二种图高
    productName: '欧元/美元',
    product: 'EURUSD',
    serverPath: 'ws://aliquote.fxhub.cn:5001',
    token: 0,
    //第一种图，列数建议不要超过 width*73%-50-10,因为实际的画图区域宽是width*73%-50
    //第二种图，列数建议不要超过 width-10-50
    dataColumn: 65
};
var detailData4 = {
    containerID: 'chartDiv4',
    chartType: 3,//图表的种类，第一种文字在左边，第二种文字在右边
    //width : 285,//建议第一种图宽
    //height : 110,//建议第一种图高
    //width: 206,//建议第二种图宽
    //height: 140,//建议第二种图高
    width:323,//第三种图宽
    height:150,//第三种图高
    productName: '欧元/美元',
    product: 'EURUSD',
    serverPath: 'ws://aliquote.fxhub.cn:5001',
    token: 0,
    //第一种图，列数建议不要超过 width*73%-50-10,因为实际的画图区域宽是width*73%-50
    //第二种图，列数建议不要超过 width-10-50
    //第三种图，列数建议不要超过 width-10-50 ,一般采用(width-50)/2-10
    dataColumn: 120
};
function init(){
    setTimeout(mAjax('/quotechart/gettoken.do', 'mc=000&sv=000&av=000&mt=pc', successGetToken,detailData1),150);
    setTimeout(mAjax('/quotechart/gettoken.do', 'mc=000&sv=000&av=000&mt=pc', successGetToken,detailData2),150);
    setTimeout(mAjax('/quotechart/gettoken.do', 'mc=000&sv=000&av=000&mt=pc', successGetToken,detailData3),150);
    setTimeout(mAjax('/quotechart/gettoken.do', 'mc=000&sv=000&av=000&mt=pc', successGetToken,detailData4),150);
}
