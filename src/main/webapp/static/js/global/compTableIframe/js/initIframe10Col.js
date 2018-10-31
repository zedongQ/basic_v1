/**
 * Created by BJ on 2016/8/19.
 */
document.write('<link rel="stylesheet" href="/compTable/css/cpSmallTitle.css"/>');
var iframeId = "compIframe10";
var proAndPairStr = "1&&&1&2&17&4&9&16&24&30&36&37";

function setCpSmallTitle(){
    var container = document.getElementById(iframeId);
    var smallTitleHtml = '<div class="cpTableTitle10"><div class="cpTTextLeft">交易商实时点差及隔夜利息</div>' +
        '<div class="cpTTextRight"><a id="cpTTCheckMore" target="_blank" href="http://'+window.location.host+'/compTable/cpTableAll.html">查看更多 &gt;&gt;</a>' +
        '</div></div>';
    var smallTitleDiv = document.createElement("div");
    smallTitleDiv.innerHTML = smallTitleHtml;
    container.parentNode.insertBefore(smallTitleDiv,container);
}
function initIframe(proAndPairStr,rowCount){
    var baseStr = 'http://123.59.52.95:8080/compTableIframe/cpTable10Col.html?prosAndPairs&';
    var paramStr = baseStr + proAndPairStr;
    var iframeHeight = rowCount*69 + 38 +1+62+"px";
    var iframeWidth = 839 + "px";
    document.getElementById(iframeId).setAttribute("src",paramStr);
    document.getElementById(iframeId).setAttribute("width",iframeWidth);
    document.getElementById(iframeId).setAttribute("height",iframeHeight);
}