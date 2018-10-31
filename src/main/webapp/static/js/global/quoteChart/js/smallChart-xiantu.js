/**
 * Created by BJ on 2016/7/5.
 */
$(function(){
   	init("EURUSD","欧元/美元");
});
var requestTime = 0;
function mAjax(url, param,prod,prodName) {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: url,
        data: param,
        success: function(data){
            successGetToken(data,prod,prodName);
        },
        error: function (data) {
            //继续访问
            if (requestTime < 10) {
                mAjax('../../quotechart/gettokenl.do', 'mc=000&sv=000&av=000&mt=pc',prod,prodName);
                requestTime++;
            }
        }
    });
}
var chChart;
function successGetToken(token,product,productName ) {
	//console.log(token+"\t"+product+"\t"+productName);
    configParams.token = token;
    configParams.product = product;
    configParams.productName = productName;

    chChart = new ChCanvas(configParams);
    chChart.init();

}
var configParams = {
    containerID: 'chartDiv4',
    chartType: 3,//图表的种类，第一种文字在左边，第二种文字在右边
    //width : 285,//建议第一种图宽
    //height : 110,//建议第一种图高
    //width: 206,//建议第二种图宽
    //height: 140,//建议第二种图高
    width:323,//第三种图宽
    height:150,//第三种图高
    //productName: GetQueryString("productName"),
    //product: GetQueryString("product"),
    serverPath: 'ws://aliquote.fxhub.cn:5001',
    token: 0,
    //第一种图，列数建议不要超过 width*73%-50-10,因为实际的画图区域宽是width*73%-50
    //第二种图，列数建议不要超过 width-10-50
    //第三种图，列数建议不要超过 width-10-50 ,一般采用(width-50)/2-10
    dataColumn: 120
};
function init(prod,prodName){
    if(chChart!=null){
        chChart.clear();
        }
    setTimeout(mAjax('../../quotechart/gettokenl.do', 'mc=000&sv=000&av=000&mt=pc',prod,prodName),150);
}
function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}