/**
 * Created by gjx on 2016/8/19.
 */
function CpTable(config) {
    this.config = config;
    this.attrs = config.attrs;
    this.containerID = config.containerID;
    this.container = document.getElementById(this.containerID);
    this.products = config.products;
    this.productsNum;
    this.hasBottomSign = config.hasBottomSign;
    this.headHasHref = config.headHasHref;
    this.imgHref = config.imgHref;
    this.titleDCText = config.titleDCText;
    this.superHead = config.superHead;
    //数字->交易商
	this.titleName = {
			'fibo':'飞博FIBO GROUP',
			'abs':'澳盛ABS GROUP',
			'xm':'xm.com',
			'gkfx':'捷凯金融GKFX',
			'vantagefx':'万致VantageFX',
			'markets':'迈肯司MARKETS',
			'oanda':'安达OANDA',
			'exness':'EXNESS',
			'fxpro':'浦汇FXPRO',
			'fxcm':'福汇FXCM',
			'forexclub':'福瑞斯ForexClub',
			'aetos':'艾拓思AETOS',
			'thinkmarkets':'智汇ThinkForex',
			'ifx':'爱福斯IFX',
			'lmax':'LMAX',
			'forex.com':'嘉盛forex.com',
			'ads':'达汇ADS',
			'axitrader':'AXITRADER',
			'avatrade':'AVATRADE',
			'icmcapital':'ICMCapital',
			'icmarkets':'ICMarkets',
			'tickmill':'Tickmill',
			'gmi':'GMI',
			'brtfx':'BRTFX',
			'svsfx':'SVSFX',
			'gmo':'GMO',
			'kvbkunlun':'KVBKunlun',
			'mdf':'MDF',
			'charterprime':'Charterprime',
			'tahoe':'Tahoe',
			'henyep':'Henyep',
			'ausforex':'AUSForex',
			'formax':'Formax',
			'axitrader':'AXITRADER',
			'gomarkets':'GOMarkets',
			'usgfx':'USGFX',
			'infinox':'INFINOX',
			'xcoq':'XCOQ爱客',
			'roboforex':'RoboForex',
			'pepperstone':'Pepperstone',
			'ig':'IGMARKETS',
			'terafx':'TeraEurope',
			'hycm':'HYCM兴业投资',
			'fx88':'FX88'
	};
    this.NumToPro = new Array();
    this.productsList = this.products.split(',');
    this.curPairs = config.curPairs;
    this.curPairsList = this.curPairs.split(',');
    this.curPairsName = new Array();
    this.serverPath = config.serverPath;
    this.url = config.url;
    this.defaultFontColor = "#333";
    this.riseFontColor = "green";
    this.fallFontColor = "#dd292c";
    this.riseFontWeight = 'normal';
    this.fallFontWeight = 'normal';
    this.defaultFontWeight = 'normal';
    this.defaultFontSize = '17px';
    this.singleColor = '#fff';
    this.doubleColor = '#FAFAFA';
    this.commonBorderColor = '1px solid #ddd';
    //this.rows = config.rows;
    this.rows = this.productsList.length;
    this.titleType = config.titleType;
    this.table = document.createElement("table");
    this.mData = new Array();
    this.rowData = new Array();
    this.mCostData = new Array();
    //isFirstInit
    this.isFirstInit = true;
    //setTimeout变量
    this.flagTimer;
    //websocket
    this.ws = null;
    //是否正在加载数据
    this.isRequestProducts = false;
    this.isRequestPairs = false;
    this.requestTime = 0;
	var urlss =document.referrer;
	var value1=urlss.indexOf("talkfx.com");
	if(value1!=-1){
		this.productUrl = {
        "fibo-47": "http://www.talkfx.com/Cp/gylx?pros&47",
        "abs-46": "http://www.talkfx.com/Cp/gylx?pros&46",
        "fxpro-1": "http://www.talkfx.com/Cp/gylx?pros&1",
        "xm-2": "http://www.talkfx.com/Cp/gylx?pros&2",
        "markets-3": "http://www.talkfx.com/Cp/gylx?pros&3",
        "markets-17": "http://www.talkfx.com/Cp/gylx?pros&17",
        "fxcm-4": "http://www.talkfx.com/Cp/gylx?pros&4",
        "thinkmarkets-5": "http://www.talkfx.com/Cp/gylx?pros&5",
        "exness-6": "http://www.talkfx.com/Cp/gylx?pros&6",
        "ifx-7": "http://www.talkfx.com/Cp/gylx?pros&7",
        "forexclub-8": "http://www.talkfx.com/Cp/gylx?pros&8",
        "lmax-9": "http://www.talkfx.com/Cp/gylx?pros&9",
        "gkfx-10": "http://www.talkfx.com/Cp/gylx?pros&10",
        "forex.com-11": "http://www.talkfx.com/Cp/gylx?pros&11",
        "oanda-12": "http://www.talkfx.com/Cp/gylx?pros&12",
        "aetos-13": "http://www.talkfx.com/Cp/gylx?pros&13",
        "vantagefx-14": "http://www.talkfx.com/Cp/gylx?pros&14",
        "gmi-15": "http://www.talkfx.com/Cp/gylx?pros&15",
		"avatrade-31" : "http://www.talkfx.com/Cp/gylx?pros&31",
		"axitrader-35" : "http://www.talkfx.com/Cp/gylx?pros&35",
        "ads-44" : "http://www.talkfx.com/Cp/gylx?pros&44",
		"icmcapital-22" : "http://www.talkfx.com/Cp/gylx?pros&22", 
		"icmarkets-23" : "http://www.talkfx.com/Cp/gylx?pros&23",
		"tickmill-24" : "http://www.talkfx.com/Cp/gylx?pros&24",
		"britanniafx-25" : "http://www.talkfx.com/Cp/gylx?pros&25", 
		"svsfx-26" : "http://www.talkfx.com/Cp/gylx?pros&26",
		"gmo-27" : "http://www.talkfx.com/Cp/gylx?pros&27", 
		"kvbkunlun-28" : "http://www.talkfx.com/Cp/gylx?pros&28",
		"ausforex-29" : "http://www.talkfx.com/Cp/gylx?pros&29", 
		"formax-30" : "http://www.talkfx.com/Cp/gylx?pros&30", 
		"charterprime-32" : "http://www.talkfx.com/Cp/gylx?pros&32", 
		"mdf-33" : "http://www.talkfx.com/Cp/gylx?pros&33", 
		"tahoe-34" : "http://www.talkfx.com/Cp/gylx?pros&34",
		"roboforex-36" : "http://www.talkfx.com/Cp/gylx?pros&36", 
		"gomarkets-37" : "http://www.talkfx.com/Cp/gylx?pros&37", 
		"henyep-38" : "http://www.talkfx.com/Cp/gylx?pros&38",
		"usgfx-39" : "http://www.talkfx.com/Cp/gylx?pros&39",
		"pepperstone-40" : "http://www.talkfx.com/Cp/gylx?pros&40", 
		"infinox-41" : "http://www.talkfx.com/Cp/gylx?pros&41", 
		"xcoq-42" : "http://www.talkfx.com/Cp/gylx?pros&42", 
		"ig-43" : "http://www.talkfx.com/Cp/gylx?pros&43",
		"terafx-45" : "http://www.talkfx.com/Cp/gylx?pros&45",
		"hycm-49" : "http://www.talkfx.com/Cp/gylx?pros&49",
		"fx88-50" : "http://www.talkfx.com/Cp/gylx?pros&50"
	 };
	 }
	else{
   this.productUrl = {
        "fibo-47": "http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&47",
        "abs-46": "http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&46",
        "fxpro-1": "http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&1",
        "xm-2": "  http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&2",
        "markets-3": "http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&3",
        "markets-17": "http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&17",
        "fxcm-4": "  http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&4",
        "thinkmarkets-5": "  http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&5",
        "exness-6": "  http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&6",
        "ifx-7": "  http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&7",
        "forexclub-8": "  http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&8",
        "lmax-9": "  http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&9",
        "gkfx-10": "  http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&10",
        "forex.com-11": "  http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&11",
        "oanda-12": "  http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&12",
        "aetos-13": "  http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&13",
        "vantagefx-14": "  http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&14",
        "gmi-15": "  http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&15",
		"avatrade-31" : "  http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&31",
		"axitrader-35" : "  http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&35",
        "ads-44" : "  http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&44",
		"icmcapital-22" : "http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&22", 
		"icmarkets-23" : "http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&23",
		"tickmill-24" : "http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&24",
		"britanniafx-25" : "http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&25", 
		"svsfx-26" : "http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&26",
		"gmo-27" : "http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&27", 
		"kvbkunlun-28" : "http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&28",
		"ausforex-29" : "http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&29", 
		"formax-30" : "http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&30", 
		"charterprime-32" : "http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&32", 
		"mdf-33" : "http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&33", 
		"tahoe-34" : "http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&34",
		"roboforex-36" : "http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&36", 
		"gomarkets-37" : "http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&37", 
		"henyep-38" : "http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&38",
		"usgfx-39" : "http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&39",
		"pepperstone-40" : "http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&40", 
		"infinox-41" : "http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&41", 
		"xcoq-42" : "http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&42", 
		"ig-43" : "http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&43",
		"terafx-45" : "http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&45",
		"hycm-49" : "http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&49",
		"fx88-50" : "http://www.ibrebates.com/g_InterestPlug/swapTableAll.html?pros&50"
    };
}
    //真正的tdheight
    this.normalTdHeight = 58;
    this.shorterTdHeight = 43;
    //用来算container高度的，和实际table表格的高度无关
    this.tableHeadHeight = 38;
    this.tableTrHeight = this.normalTdHeight + 11;
    this.otherHeight = 50;
    //当前网站的域名
    this.parentUrl = document.referrer;
    this.subStrNum = this.parentUrl.substring(7,this.parentUrl.length).indexOf("/");
    this.baseUrl = this.parentUrl.substring(0,this.subStrNum+7);
    //表格宽度
    this.normalTdWidth = 6;
    //是否有eurusd标记
    this.hasEurusd = false;
    //是否有gold标记
    this.hasGold = false;
	this.productsId;
	this.PairsName=new Array();
	this.allId=new Array();
	this.dataLine=new Array();
}
CpTable.prototype = {
    init : function(){
		this.initContainer();
        this.mAjax(this,this.url);
    },
    mAjax : function(self,url){
        $.ajax({
            type: "get",
            dataType: "JSON",
            url: url,
            success: function(data){
            	data=eval("("+data+")")
                self.putData(data[0]);
                self.setProductsMsg(self);
                self.sortPairs();
                self.setPairsMsg(self);
                self.initData();
				self.initTable();
                self.initWS();
				self.refreshTableBySwap(data[0]);
            },
            error:function(data){
                //继续访问
                if(self.requestTime < 10){
                    self.mAjax(self,url);
                    self.requestTime++;
                }
            }
        });
    },
    reOpen : function(products,pairs){
        this.closeWS();
        this.isFirstInit = true;
        this.NumToPro = new Array();
        this.curPairsName = new Array();
        this.mData = new Array();
        this.rowData = new Array();
        this.mCostData = new Array();
        this.table = document.createElement("table");
        this.products = products;
        this.productsList = this.products.split(',');
        this.curPairs = pairs;
        this.curPairsList = this.curPairs.split(',');
        this.rows = this.productsList.length;
        this.init();
    },
    //断线重连
    reSetWS:function(){
        //先关闭
        if(this.ws != null){
            this.ws.close();
            this.ws = null;
        }
		var self = this;
        self.startFlagTimer(self);
        self.ws = new WebSocket(self.serverPath);
        self.reSetMsg(self);
    },
    reSetMsg : function(self){
        //打开Socket
        self.ws.onopen = function(event){
            self.requestProductsData(self);
        };
        //监听消息
        self.ws.onmessage = function(event){
            self.isRequestProducts = false;
            self.isRequestPairs = false;
            if(event.data == "m&s"){
                self.requestPairsData(self);
                self.startFlagTimer(self);
            }
            var msg = event.data.split("&");
            if(msg.length < 1){
                return ;
            }
            if(msg[0] == "mtd"){
                self.setConnecterFlag();
                self.refreshTable(msg);
            }
        };
        // 监听Socket的关闭
        self.ws.onclose = function(event) {
            console.log('Client notified socket has closed',event);
        };

        // 监听Socket的关闭
        self.ws.onerror = function(event) {
            console.log('Client notified socket has error',event);
        };
    },
    closeWS : function(){
        //关闭
        if(this.ws != null){
            this.ws.close();
            this.ws = null;
        }
    },
    initWS:function(){
        //先关闭
        if(this.ws != null){
            this.ws.close();
            this.ws = null;
        }
        //创建一个Socket实例
        this.ws = new WebSocket(this.serverPath);
        this.getMsg(this);
    },
    getMsg : function(self){
        //打开Socket
        self.ws.onopen = function(event){
            self.requestProductsData(self);
        };
        //监听消息
        self.ws.onmessage = function(event){
            self.isRequestProducts = false;
            self.isRequestPairs = false;
            if(event.data == "m&s"){
                self.requestPairsData(self);
                self.startFlagTimer(self);
            }
            var msg = event.data.split("&");
            if(msg.length < 1){
                return ;
            }
			
            if(msg[0] == "mtd"){
                self.setConnecterFlag();
                self.initTable();
                self.refreshTable(msg);
            }
        };
        // 监听Socket的关闭
        self.ws.onclose = function(event) {
            console.log('Client notified socket has closed',event);
        };

        // 监听Socket的关闭
        self.ws.onerror = function(event) {
            console.log('Client notified socket has error',event);
        };
    },
    initContainer : function(){
        document.body.style.padding = 0;
        document.body.style.margin = 0;
        this.container.innerHTML ="";
        this.container.style.position = "relative";
        var contHeight = 0;
        if(this.imgHref == "yes"){
            this.tableHeadHeight = 55;
            this.tableTrHeight = this.shorterTdHeight + 11;
        }
        if(this.hasBottomSign == "yes"){
            contHeight = this.rows*this.tableTrHeight + this.tableHeadHeight + this.otherHeight +70+"px";
            this.container.style.height = contHeight;
        }else{
            contHeight = this.rows*this.tableTrHeight + this.tableHeadHeight +50+"px";
            this.container.style.height = contHeight;
        }
        //var myContHeight = parseInt(contHeight.substring(0,contHeight.length-2));
        this.container.style.background = "url('http://123.59.52.95:8080/compTableIframe/imgs/loading.gif') no-repeat center "+40+"px";
    },
    putData : function(dataList){
        for(var i=0;i<dataList.length;i++){
            var num = dataList[i].keyName.split("-")[1];
            var item = {
                keyName : dataList[i].keyName,
                calcTypeId : dataList[i].calcTypeId,
                dealerName : dataList[i].dealerName,
                forex : dataList[i].forex,
                gold : dataList[i].gold,
                eurusd : dataList[i].eurusd,
                forexratio : dataList[i].forexratio,
				caculForex : 0,
				caculGold : 0
            };
            this.mCostData[num] = item;
        }
    },
    setConnecterFlag : function(){
        this.startFlagTimer(this);
    },
    startFlagTimer : function(self){
        //星期六、星期天就不会再断线重连了。
        var date = new Date();
        if(date.getDay().toString() == "0" || date.getDay().toString() == "6"){
            return;
        }
        if(self.flagTimer){
            clearTimeout(self.flagTimer);
        }
        this.flagTimer = setTimeout(function(){
            self.reSetWS();
        },10000);
    },
    refreshTable : function(dataLine){
        var productNum = dataLine[1];
        var pair = dataLine[2];
        var length = this.rowData[this.containerID+"_"+dataLine[1]].cells.length;
        var cellId = this.containerID+"_"+dataLine[1]+"_"+dataLine[2];
        var cellsItem = this.rowData[this.containerID+"_"+dataLine[1]].cells;
        var firstDianChaId = this.containerID+"_"+dataLine[1]+"_"+this.curPairsName[0];
        var firstDataCellId = this.containerID+"_"+dataLine[1]+"_"+ this.attrs[1].attr;
        var whBackId = this.containerID+"_"+dataLine[1]+"_"+ this.attrs[3].attr;
        var goldBackId = this.containerID+"_"+dataLine[1]+"_"+ this.attrs[4].attr;
        for(var i = 0;i<length;i++){
            if(cellsItem[i].id == cellId){
                //有实际成本和没有实际成本赋值不一样
                if(this.attrs[2].flag == "need" && (pair != "silver"&&pair != "usoil"&&pair != "ukoil")){
     
                }
            }
            //第一项货币对，而且是买入和卖出价项目
            else if(cellId == firstDianChaId && cellsItem[i].id == firstDataCellId){
				cellsItem[i].setDianChaColor(this.getColor(cellsItem[i].getBuyValue(),parseFloat(dataLine[3]).toFixed(5)),this.getColor(cellsItem[i].getSaleValue(),parseFloat(dataLine[4]).toFixed(5)));
                cellsItem[i].refreshTwoSpan(parseFloat(dataLine[3]).toFixed(5),parseFloat(dataLine[4]).toFixed(5));
            }
        }
    },    
	
	refreshTableBySwap : function(dataLine){
		var count=0;
		var productNum=this.productsId.split(",");
		for(var i=0;i<productNum.length;i++){
			for(var j=1;j<=this.PairsName.length;j++){
				this.allId[j+(i*this.PairsName.length)]=this.containerID+"_"+productNum[i]+"_"+this.PairsName[j-1];
			}
		}
		if(dataLine!=undefined){
			for(var i=0;i<dataLine.length;i++){
				for(var j=0;j<this.allId.length;j++){
					var curPairName = this.allId[j];
					if(dataLine[i].code=='abs'){
						if(curPairName=='spTableContainer_46_gold'){
							curPairName='spTableContainer_46_xauusd';
						}
						if(curPairName=='spTableContainer_46_silver'){
							curPairName='spTableContainer_46_xagusd';
						}
						curPairName+='abs';
					}
					if(dataLine[i].code=='exness'){
						if(curPairName=='spTableContainer_6_gold'){
							curPairName='spTableContainer_6_xauusd';
						}
						if(curPairName=='spTableContainer_6_silver'){
							curPairName='spTableContainer_6_xagusd';
						}
					}
					if(this.containerID+"_"+dataLine[i].accttype+"_"+dataLine[i].currencyPair.toLowerCase()==curPairName){
						var b=document.getElementById(this.allId[j]).firstChild.getElementsByTagName("div");
						b[0].innerHTML=dataLine[i].longswap.toFixed(2);
						b[1].innerHTML=dataLine[i].shortswap.toFixed(2);
						b[2].innerHTML="&thinsp;"+dataLine[i].swapSymbol;
						//单位是pips的除以10
						if(dataLine[i].swapSymbol=='pips' && dataLine[i].code!='fibo'){
							b[0].innerHTML=(dataLine[i].longswap/10).toFixed(3);
							b[1].innerHTML=(dataLine[i].shortswap/10).toFixed(3);
						}
						count++;
						if(count==this.allId.length){
							return;
						}
					}
				}
			}
        }
    },
    //给所有的货币对排序
    sortPairs : function(){
        var hasEurusd = false;
        var hasGold = false;
        var hasSilver = false;
        var hasUsOil = false;
        var hasUkOil = false;
        var length = this.curPairsList.length;
        var pairsList = new Array();
        for(var i=0;i<length;i++){
            if(this.curPairsList[i] == "eurusd-1"){
                hasEurusd = true;
            }else if(this.curPairsList[i] == "gold-36"){
                hasGold = true;
            }else if(this.curPairsList[i] == "silver-37"){
                hasSilver = true;
            }else if(this.curPairsList[i] == "usoil-38"){
                hasUsOil = true;
            }else if(this.curPairsList[i] == "ukoil-39"){
                hasUkOil = true;
            }else{
                pairsList.push(this.curPairsList[i]);
            }
        }
        pairsList.sort();
        //下边这两个货币对必须要有，因为需要用他们计算外汇返现和黄金返现！！这里强制添加的
        if(hasEurusd == true){
            pairsList.unshift("eurusd-1");
            this.hasEurusd = true;
        }
        if(hasGold == true){
            pairsList.push("gold-36");
            this.hasGold = true;
        }
        if(hasSilver == true){
            pairsList.push("silver-37");
        }
        if(hasUsOil == true){
            pairsList.push("usoil-38");
        }
        if(hasUkOil == true){
            pairsList.push("ukoil-39");
        }
        this.curPairsList = pairsList;
    },
    requestProductsData : function(self){
        if(self.isRequestProducts == false){
            self.ws.send("m&s&"+self.productsNum);
            self.isRequestProducts = true;
        }
    },
    requestPairsData : function(self){
        if(self.isRequestPairs == false){
            self.ws.send("m&i&"+self.curPairs);
            self.isRequestPairs = true;
        }
    },
    initData : function(){
        var length = this.rows;
        for(var i=0;i<length;i++) {
            this.mData.push({
                "num": this.productsList[i].split("-")[1],
				"nameTitle": this.titleName[this.productsList[i].split("-")[0]]
            });
        }
    },
    getColor : function(valueOld,valueNow){
        if(valueOld == -100000000 || valueNow == valueOld || typeof(valueOld)=='undefined'){
            return this.defaultFontColor;
        }else if(valueNow > valueOld){
            return this.riseFontColor;
        }else{
            return this.fallFontColor;
        }
    },
    initTable : function(){
        if(this.isFirstInit == false){
            return;
        }
        //只有第一次初始化才执行下边的代码
        this.isFirstInit = false;
        //div容器的样式的设置
        this.container.style.room = '1';
        this.container.style.overflow = 'hidden';
        //table的样式
        this.table.style.backgroundColor = "#fff";
        this.table.style.border = this.commonBorderColor;
        this.table.style.borderCollapse = "collapse";
        this.table.style.fontSize = "13px";
        this.table.style.width = "1055px";
        this.table.style.fontFamily = "Microsoft Yahei";
        if(this.superHead =="yes") {
            this.table.style.tableLayout = "fixed";
        }
        //固定表头的特定表格
        var superHeadTable;
        //tr，表头的样式
        var tr = document.createElement('tr');
        tr.style.backgroundColor = '#f7f7f7';
		

        //长度为所有部件都有的时候的长度
        for(var i=0;i<this.curPairsName.length + 5;i++){
            //头部交易商第一列
            if(i==0 && (this.attrs[0].flag =="need")){
                var th = document.createElement('th');
                th.innerHTML = this.attrs[0].name;
                th.style.color = "#666";
            }
            //头部第二列货币点差
            else if(i==1 && (this.attrs[1].flag =="need")){
                var th = document.createElement('th');
                var thHtml = "";
                if(this.headHasHref == "yes"){
                    if(this.titleType == "blue"){
                        thHtml = '<a href="http://www.ibrebates.com/compTable/cpTableAll.html" target="_blank"style="text-decoration:none;color:#367cd3;font-weight:normal;">';
                    }else if(this.titleType == "yellow"){
                        th.style.color = "#bca47e";
                        thHtml = '<a href="http://www.ibrebates.com/compTable/cpTableAll.html" target="_blank"style="text-decoration:none;color:#bca47e;">';
                    }
                }else{
                    if(this.titleType == "blue"){
                        th.style.color = "#367cd3";
                        th.style.fontWeight = "normal";
                    }else if(this.titleType == "yellow"){
                        th.style.color = "#bca47e";
                    }
                }
                if(this.curPairsName[i-1] == "gold"){
					thHtml += "XAUUSD";
				}else if(this.curPairsName[i-1] == "silver"){
					thHtml += "XAGUSD";
				}else if(this.curPairsName[i-1] == "usoil"){
                    thHtml += "WTI";
                }else if(this.curPairsName[i-1] == "ukoil"){
                    thHtml += "BRENT";
                }else{
                    thHtml += this.curPairsName[i-1].toUpperCase();//1.16
                }            

                if(this.titleDCText == "yes"){
                    thHtml += '<p style="font-weight:normal;margin:0;padding:0;font-size:12px:color:#bca47e;">(点差)</p>';
                }
                thHtml+="</a>";
                th.colSpan = "2";
				//第一行内加一行
				var divTop = document.createElement('div');
                    divTop.style.textAlign = "center";
                    divTop.innerHTML = thHtml;
                    divTop.style.height = "28px";
                    divTop.style.lineHeight = "19px";
                    divTop.style.borderBottom = this.commonBorderColor;
                    th.appendChild(divTop);
				var divBottom = document.createElement('div');
                    divBottom.style.textAlign = "center";
					//divBottom.style.padding-bottom="0px";
                    divBottom.innerHTML = "<div class='bao'><div class='xiao yi'>实时报价</div><div class='xiao'>买</div><div class='xiao'>卖</div><div class='xiao'>单位</div></div>";
                    divBottom.style.height = "28px";
                    divBottom.style.lineHeight = "28px";
                    divBottom.style.color = "#666";
                    th.appendChild(divBottom);
                //th.innerHTML = thHtml;
				
				
            }
            //头部第二至所有的货币点差列完毕
            else if(i>=1 && i<=this.curPairsName.length){
                var th = document.createElement('th');
                var thHtml = "";
                if(this.headHasHref == "yes"){
                    if(this.titleType == "blue"){
                        thHtml = '<a href="http://www.ibrebates.com/compTable/cpTableAll.html" target="_blank"style="text-decoration:none;color:#367cd3;font-weight:normal;">';
                    }else if(this.titleType == "yellow"){
                        th.style.color = "#bca47e";
                        thHtml = '<a href="http://www.ibrebates.com/compTable/cpTableAll.html" target="_blank"style="text-decoration:none;color:#bca47e;">';
                    }
                }else{
                    if(this.titleType == "blue"){
                        th.style.color = "#367cd3";
                        th.style.fontWeight = "normal";
                    }else if(this.titleType == "yellow"){
                        th.style.color = "#bca47e";
                    }
                }
                if(this.curPairsName[i-1] == "gold"){
					thHtml += "XAUUSD";
				}else if(this.curPairsName[i-1] == "silver"){
					thHtml += "XAGUSD";
				}else if(this.curPairsName[i-1] == "usoil"){
                    thHtml += "WTI";
                }else if(this.curPairsName[i-1] == "ukoil"){
                    thHtml += "BRENT";
                }else{
                    thHtml += this.curPairsName[i-1].toUpperCase();
                }
                if(this.titleDCText == "yes"){
                    thHtml += '<p style="font-weight:normal;margin:0;padding:0;font-size:12px:color:#bca47e;">(点差)</p>';
                }
                thHtml+="</a>";
                //th.innerHTML = thHtml;
				var divTop = document.createElement('div');
                    divTop.style.textAlign = "center";
                    divTop.innerHTML = thHtml;
                    divTop.style.height = "28px";
                    divTop.style.lineHeight = "19px";
                    divTop.style.borderBottom = this.commonBorderColor;
                    th.appendChild(divTop);
				var divBottom = document.createElement('div');
                    divBottom.style.textAlign = "center";
					//divBottom.style.padding-bottom="0px";
                    divBottom.innerHTML = "<div class='bao'><div class='san sanyi'>买</div><div class='san'>卖</div><div class='san'>单位</div></div>";
                    divBottom.style.height = "28px";
                    divBottom.style.lineHeight = "28px";
                    divBottom.style.color = "#666";
                    th.appendChild(divBottom);
            }
            //头部最后一列交易商名称
            else if(i==this.curPairsName.length+4 && (this.attrs[6].flag =="need")){
                var th = document.createElement('th');
                th.innerHTML = this.attrs[6].name;
                th.style.color = "#666";
            }
            if(th){
                th.style.border = this.commonBorderColor;
                th.style.padding ="10px 0px";//10px->0  5px ->0px 
				th.style.position="relative";
                if(this.superHead == "yes"){
                    th.width = this.normalTdWidth+3+"%";
                    if(i==0){
                        th.width = this.normalTdWidth+1+"%";
                    }
                    if(i==1){
                        th.width = this.normalTdWidth*2+6+"%";
                    }
                }
                tr.appendChild(th);
                th=null;
            }
        }
        if(this.superHead =="yes"){
            var supHeadTable = document.createElement("table");
            var newTr = document.createElement("tr");
            newTr.innerHTML = tr.innerHTML;
            newTr.style.backgroundColor = "#fafafa";
            supHeadTable.style.borderCollapse = "collapse";
            supHeadTable.style.width = 1055+"px";
            supHeadTable.style.fontSize = "12px";
            supHeadTable.style.fontFamily = "Microsoft Yahei";
            supHeadTable.style.tableLayout = "fixed";
            var trLen = newTr.children.length;
            for(var k = 0;k<trLen;k++){
                if(k==0){
                    newTr.children[k].setAttribute("width",this.normalTdWidth+1+"%");
                }else if(k==1){
                    newTr.children[k].setAttribute("width",this.normalTdWidth*2+6+"%");
                }else{
                    newTr.children[k].setAttribute("width",this.normalTdWidth+3+"%");
                }
            }
            supHeadTable.appendChild(newTr);
            superHeadTable = supHeadTable;
//          tr.style.visibility = "hidden";
        }
        this.table.appendChild(tr);
        var cptRowHead = new CptRow(tr);
        this.rowData["headRow"] = cptRowHead;
        //创建内容
        //this.mData保存了交易商的名称，用于生成tr行id，如果有其他初始化数据可以放在其中
        for(var i = 0;i < this.mData.length;i++){
            var tr = document.createElement("tr");
            tr.id = this.containerID+"_"+this.mData[i].num;
            var cptRow = new CptRow(tr);
            //通用的tr的样式
            if(i%2 == 0){
                tr.style.background = this.singleColor;
            }else{
                tr.style.backgroundColor = this.doubleColor;
            }
            var row = new CptRow(this.containerID+'_'+this.mData[i].num);
            //第二列是两列合并的，所以length+6而不是加5
            for(var j=0;j<this.curPairsName.length+6;j++){
                //table内容行第一列
                if(j==0 && (this.attrs[0].flag =="need")){
                    if(this.imgHref == "yes"){
                        var td = document.createElement('td');
                        var id = this.containerID+'_'+this.mData[i].num+'_'+j;
                        td.id = id;
						var num = parseInt(this.productsList[i].split("-")[1]);
                        if(num<22||num==31||num==35||num==44){
                            var a = document.createElement('a');
                            a.style.display = "block";
                            a.style.textAlign = "center";
                            a.innerHTML = '<img style="height:35px;width:auto;" src="imgs/'+this.productsList[i]+'.png" alt="'+this.mData[i].nameTitle+'" title="'+this.mData[i].nameTitle+'"/>';
//                            a.href = this.productUrl[this.productsList[i]];
                            a.style.height = "35px";
                            a.target = "_blank";
                            td.appendChild(a);
                        }else{
                            var img = document.createElement('img');
                            img.src = 'imgs/'+this.productsList[i]+'.png';
							img.title = ''+this.mData[i].nameTitle;
							img.alt = ''+this.mData[i].nameTitle;
                            img.style.height = "35px";
                            img.style.outline = "none";
                            img.style.border = "transparent";
                            td.appendChild(img);
                            td.style.textAlign = "center";
                        }
                    }else{
                        var td = document.createElement('td');
                        var id = this.containerID+'_'+this.mData[i].num+'_'+j;
                        td.id = id;
                        var img = document.createElement('img');
                        img.src = "imgs/"+this.productsList[i]+".png";
						img.title = ''+this.mData[i].nameTitle;
						img.alt = ''+this.mData[i].nameTitle;
                        img.style.height = "35px";
                        img.style.width = "auto";
                        img.style.outline = "none";
                        img.style.border = "transparent";
                        td.appendChild(img);
                        td.style.textAlign = "center";
                        var num = parseInt(this.productsList[i].split("-")[1]);
                        var a = document.createElement('a');
                        a.style.display = "block";
                        a.style.textAlign = "center";
//                        a.innerHTML = "详情";
//                        a.href = this.productUrl[this.productsList[i]];
                        a.target = "_blank";
                        if(this.titleType == "yellow"){
                            a.style.color = "#bca482";
                        }else{
                            a.style.color = "#6f9bdc";
                        }
                        td.appendChild(a);
                    }
                }
                //table内容行第二列，买入，卖出列
                else if(j==1 && (this.attrs[1].flag =="need")){
                    var td = document.createElement('td');
                    var id = this.containerID+'_'+this.mData[i].num+"_"+this.attrs[1].attr;
                    td.id = id;
                    var divTop = document.createElement('div');
                    divTop.style.textAlign = "center";
                    divTop.innerHTML = "卖：<span style='font-size:12px;color:#666;'></span>";
                    divTop.style.height = "28px";
                    divTop.style.lineHeight = "28px";
                    divTop.style.borderBottom = this.commonBorderColor;
                    divTop.style.color = "#666";
                    td.appendChild(divTop);
                    var divBottom = document.createElement('div');
                    divBottom.style.textAlign = "center";
                    divBottom.innerHTML = "买：<span style='font-size:12px;color:#666;'></span>";
                    divBottom.style.height = "28px";
                    divBottom.style.lineHeight = "28px";
                    divBottom.style.color = "#666";
                    td.appendChild(divBottom);
                }
                //table内容行第三列，第一个点差列
                else if(j==2){
                    var td = document.createElement('td');
                    var id = this.containerID+'_'+this.mData[i].num+'_'+this.curPairsName[j-2];
                    td.id = id;
					td.style.position="relative";
                    var divDc = document.createElement('div');
                    divDc.style.textAlign = "center";
					divDc.className="bao2";
                    divDc.innerHTML = "<div class='san sanyi'></div><div class='san'></div><div class='san' style='color:rgb(149, 149, 149)'></div>";
                    //divDc.style.fontSize = this.defaultFontSize;
                    divDc.style.color = this.defaultFontColor;
                    divDc.style.transition = "color 0.3s linear 0s";
                    divDc.style.fontWeight = this.defaultFontWeight;
                    divDc.style.height = "100%";
                    divDc.style.minWidth = "60px";
					divDc.style.top="";
                    td.appendChild(divDc);
                }
                //table内容行全部普通点差列
                else if(j>2 && j<=this.curPairsName.length+1){
                    var td = document.createElement('td');
                    var id = this.containerID+'_'+this.mData[i].num+'_'+this.curPairsName[j-2];
                    td.id = id;
					td.style.position="relative";
                    var divDc = document.createElement('div');
					divDc.className="bao2";
                    divDc.innerHTML = "<div class='san sanyi'></div><div class='san'></div><div class='san' style='color:rgb(149, 149, 149)'></div>";
                    //divDc.style.fontSize = this.defaultFontSize;
                    divDc.style.textAlign = "center";
                    divDc.style.color = this.defaultFontColor;
                    divDc.style.transition = "color 0.3s linear 0s";
                    divDc.style.fontWeight = this.defaultFontWeight;
                    divDc.style.height = "100%";
                    if(this.imgHref == "yes"){
                        td.style.height = this.shorterTdHeight+"px";
                    }else{
                        td.style.height = this.normalTdHeight+"px";
                    }
                    td.appendChild(divDc);
                    if(this.attrs[2].flag == "need"){
                        var divAc = document.createElement('div');
                        //divAc.innerHTML = "1.5";
                        divAc.style.textAlign = "right";
                        divAc.style.paddingRight = "13px";
                        divAc.style.color = "#999";
                        divAc.style.margin = "5px 0 -5px 0";
                        td.appendChild(divAc);
                    }
                }

                if(td){
                    td.style.border = this.commonBorderColor;
                    td.style.padding = "5px 0";
                    tr.appendChild(td);
                    cptRow.addCell(new CptCell(td));
                    td=null;
                }
            }
            this.table.appendChild(tr);
            this.rowData[tr.id] = cptRow;
        }
        this.container.appendChild(this.table);

        if(this.hasBottomSign == "yes"){
            //下边的提示
            var tipsP = document.createElement("p");
            tipsP.innerHTML = "本功能由IB外汇返现网ibrebates.com提供";
            tipsP.style.textAlign = "right";
            tipsP.style.color = "#666";
            tipsP.style.fontSize = "12px";
            tipsP.style.lineHeight = "45px";
            tipsP.style.padding = "0";
            tipsP.style.margin = "0";
            this.container.appendChild(tipsP);
        }
        if(this.superHead =="yes"){
            var fakeDiv = document.createElement("div");
            fakeDiv.style.position = "fixed";
            fakeDiv.style.top = "0";
            fakeDiv.style.left = "0";
            fakeDiv.style.width = this.container.offsetWidth+"px";
            fakeDiv.style.height = this.tableHeadHeight+18+23+"px";
            fakeDiv.style.overflow = "hidden";

            fakeDiv.appendChild(superHeadTable);
            this.container.appendChild(fakeDiv);
        }
        //设置特殊的列的位置,外汇返现列的表头用的是绝对定位，需要计算靠近左边的距离
        if(this.attrs[3].flag =="need"){
            var specialBack = document.getElementsByClassName("specialBack");
            var parentWidth = specialBack[0].parentNode.offsetWidth;
            var nowWidth = specialBack[0].offsetWidth;
            var width = (parentWidth - nowWidth)/2;
            specialBack[0].style.left = width+"px";
            specialBack[1].style.left = width+"px";
        }
    },
    setPairsMsg : function(self){
        var len = this.curPairsList.length;
        var msgParis = "";
        for(var i=0;i<len;i++){
            msgParis+=self.curPairsList[i].split("-")[1]+",";
            self.curPairsName.push(self.curPairsList[i].split("-")[0]);
        }
        self.curPairs=msgParis.substring(0,msgParis.length-1);	
		this.PairsName=self.curPairsName;
        //强行加上了欧美eurusd和黄金的gold的，因为需要他们计算外汇返现和黄金返现
        if(self.hasEurusd == false){
            self.curPairs = "1,"+self.curPairs;
        }
        if(self.hasGold == false){
            self.curPairs = self.curPairs+",36";
        }
		
    },
    setProductsMsg : function(self){
        var begin = this.rowData.length;
        var end = 0;
        if((self.productsList.length - begin - self.rows)>0){
            end = self.rows +begin;
        }else{
            end = self.productsList.length;
        }
        var msgProduct = '';
        var msgProductNum = '';
        for(var i = begin;i<end;i++){
            msgProduct += self.productsList[i].split("-")[0]+',';
            msgProductNum += self.productsList[i].split("-")[1]+',';
            self.NumToPro[self.productsList[i].split("-")[1]] = self.productsList[i].split("-")[0];
        }
        self.products = msgProduct.substring(0,msgProduct.length-1);
        self.productsNum = msgProductNum.substring(0,msgProductNum.length-1);
		this.productsId=self.productsNum;
    },
    //resortProduct : function(){
    //    alert("sort");
    //},
    addEvent : function (obj,type,handle){
        try{  // Chrome、FireFox、Opera、Safari、IE9.0及其以上版本
            obj.addEventListener(type,handle,false);
        }catch(e){
            try{  // IE8.0及其以下版本
                obj.attachEvent('on' + type,handle);
            }catch(e){  // 早期浏览器
                obj['on' + type] = handle;
            }
        }
    }

};