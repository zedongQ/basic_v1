/**
 * Created by BJ on 2016/8/19.
 */
function CptCell(td){
    this.id = td.id;
    this.selfElement = td;
    this.value = -100000000;
}
CptCell.prototype = {
    refreshTwoDiv:function(diancha,chengben){
        //刷新的代码
        this.selfElement.firstChild.innerHTML = diancha;
        this.selfElement.lastChild.innerHTML = chengben;
        this.value = diancha;
    },
    refreshTwoSpan : function(buy,sale){
        //刷新的代码
        this.selfElement.firstChild.lastChild.innerHTML = buy;
        this.selfElement.firstChild.lastChild.value = buy;
        this.selfElement.lastChild.lastChild.innerHTML = sale;
		this.selfElement.lastChild.lastChild.value = sale;
    },
    refreshTd : function(tdData){
        //刷新的代码
        this.selfElement.innerHTML = tdData;
    },
    setColor : function(color){
        this.selfElement.firstChild.style.color = color;
    },
	setDianChaColor : function(buycolor,sellcolor){		
		this.selfElement.firstChild.lastChild.style.color = buycolor;
		this.selfElement.lastChild.lastChild.style.color = sellcolor;
    },
    getValue : function(){
        return this.value;
    },
	getBuyValue : function(){
//		var b=this.selfElement.getElementsByTagName("div");

		return  this.selfElement.firstChild.lastChild.value;
//      return b[1].lastChild.value;
    },   
	getSaleValue : function(){
        return this.selfElement.lastChild.lastChild.value;
    }
};