/**
 * Created by BJ on 2016/8/19.
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
    this.NumToPro = new Array();
    this.productsList = this.products.split(',');
    this.curPairs = config.curPairs;
    this.curPairsList = this.curPairs.split(',');
    this.curPairsName = new Array();
    this.serverPath = config.serverPath;
    this.url = config.url;
	this.url2=config.url2;
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
    this.productUrl = {
        "fxpro-1": "http://www.ibrebates.com/jys_list/20140917/418098.html",
        "xm-2": "http://www.ibrebates.com/jys_list/20140917/418093.html",
        "markets-3": "http://www.ibrebates.com/jys_list/20140917/454092.html",
        "markets-17": "http://www.ibrebates.com/jys_list/20140917/454092.html",
        "fxcm-4": "http://www.ibrebates.com/jys_list/20140917/418088.html",
        "thinkforex-5": "http://www.ibrebates.com/jys_list/20160317/462882.html",
        "exness-6": "http://www.ibrebates.com/jys_list/20140917/418101.html",
        "ifx-7": "http://www.ibrebates.com/jys_list/20140917/418089.html",
        "forexclub-8": "http://www.ibrebates.com/jys_list/20140917/418096.html",
        "lmax-9": "http://www.ibrebates.com/jys_list/20140917/418095.html",
        "gkfx-10": "http://www.ibrebates.com/jys_list/20140917/418094.html",
        "forex.com-11": "http://www.ibrebates.com/jys_list/20140917/418091.html",
        "oanda-12": "http://www.ibrebates.com/jys_list/20140917/418087.html",
        "aetos-13": "http://www.ibrebates.com/jys_list/20140917/418100.html",
        "vantagefx-14": "http://www.ibrebates.com/jys_list/20160612/466793.html",
        "gmi-15": "http://www.ibrebates.com/jys_list/20160823/468069.html",
		"avatrade-31" : "http://www.ibrebates.com/jys_list/20160913/468412.html",
		"axitrader-35" : "http://www.ibrebates.com/jys_list/20160913/468408.html",
        "ads-44" : "http://www.ibrebates.com/jys_list/20160905/468257.html"
    };
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
}
CpTable.prototype = {
    init : function(){
		this.initContainer();
        this.mAjax(this,this.url);
    },
    mAjax : function(self,url){
        $.ajax({
            type: "get",
            dataType: "json",
            url: url,
            success: function(data){
                self.putData(data.list);
                self.setProductsMsg(self);
                self.sortPairs();
                self.setPairsMsg(self);
                self.initData();
                self.initWS();
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
                self.initTable(self);
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
            contHeight = this.rows*this.tableTrHeight + this.tableHeadHeight + this.otherHeight +1+"px";
            this.container.style.height = contHeight;
        }else{
            contHeight = this.rows*this.tableTrHeight + this.tableHeadHeight +1+66+"px";
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
                    if(this.mCostData[productNum].calcTypeId=="2" &&pair!="gold"){
                        //计算实际成本,全都是用自己算的
                        var actCost = (parseFloat(dataLine[5]) - parseFloat(dataLine[5])*parseFloat(this.mCostData[productNum].forex)).toFixed(2);
                    }else if(productNum == "13"&&pair!="gold"){
                        //计算实际成本
                        if(pair == "eurusd"){
                            var actCost = (parseFloat(dataLine[5]) - this.mCostData[productNum].eurusd).toFixed(2);
                        }else{
                            var actCost = (parseFloat(dataLine[5]) - this.mCostData[productNum].forex).toFixed(2);
                        }
                    }else if(pair=="gold"){
                        //计算实际成本
						if(this.mCostData[productNum].calcTypeId=="2"){
							var actCost = (parseFloat(dataLine[5]) - (parseFloat(dataLine[5])*parseFloat(this.mCostData[productNum].gold))).toFixed(2);
						}else{
							var actCost = (parseFloat(dataLine[5]) - this.mCostData[productNum].gold).toFixed(2);
						}
                    }else{
                        //计算实际成本
                        var actCost = (parseFloat(dataLine[5]) - this.mCostData[productNum].forex).toFixed(2);
                    }
                    //有实际成本的，第一项和之后的项目显示不一样
                    if(cellId == firstDianChaId && this.attrs[1].flag == "need"){
                        cellsItem[i].setColor(this.getColor(cellsItem[i].getValue(),parseFloat(dataLine[5]).toFixed(2)));
                        cellsItem[i].refreshTwoDiv(parseFloat(dataLine[5]).toFixed(2),"实际成本"+actCost);
                    }else if(cellId == firstDianChaId){
                        cellsItem[i].setColor(this.getColor(cellsItem[i].getValue(),parseFloat(dataLine[5]).toFixed(2)));
                        cellsItem[i].refreshTwoDiv(parseFloat(dataLine[5]).toFixed(2),"实际成本"+actCost);
                    }else{
                        cellsItem[i].setColor(this.getColor(cellsItem[i].getValue(),parseFloat(dataLine[5]).toFixed(2)));
                        cellsItem[i].refreshTwoDiv(parseFloat(dataLine[5]).toFixed(2),actCost);
                    }
                }else{
                    cellsItem[i].setColor(this.getColor(cellsItem[i].getValue(),parseFloat(dataLine[5]).toFixed(2)));
                    cellsItem[i].refreshOneDiv(parseFloat(dataLine[5]).toFixed(2));
                }
            }
            //第一项货币对，而且是买入和卖出价项目
            else if(cellId == firstDianChaId && cellsItem[i].id == firstDataCellId){
				cellsItem[i].setDianChaColor(this.getColor(cellsItem[i].getBuyValue(),parseFloat(dataLine[3]).toFixed(5)),this.getColor(cellsItem[i].getSaleValue(),parseFloat(dataLine[4]).toFixed(5)));
                cellsItem[i].refreshTwoSpan(parseFloat(dataLine[3]).toFixed(5),parseFloat(dataLine[4]).toFixed(5));
            }
            //外汇返现
            else if(cellsItem[i].id == whBackId){
                if(this.mCostData[productNum].calcTypeId=="2" && pair == "eurusd"){
                    var foreBackT = 0;
                    foreBackT = (parseFloat(dataLine[5])*parseFloat(this.mCostData[productNum].forex)).toFixed(2);
                    cellsItem[i].refreshTd(foreBackT);
					this.mCostData[productNum].caculForex = foreBackT;
                }
            }
            //黄金返现
            else if(cellsItem[i].id == goldBackId){
                //cellsItem[i].refreshTd(parseFloat(dataLine[5]).toFixed(2));
                if(this.mCostData[productNum].calcTypeId=="2" && pair == "gold"){
                    var foreBack = 0;
                    foreBack = (parseFloat(dataLine[5])*parseFloat(this.mCostData[productNum].gold)).toFixed(2);
                    cellsItem[i].refreshTd(foreBack);
					this.mCostData[productNum].caculGold = foreBack;
                }
            }
        }
    },
	refreshTable_swap : function(data){
		for(var i=0;i<this.curPairsName.length;i++){
			if(i==0){
				for(var j =0;j<data.length;j++){
					if(data[j].currencyPair==this.curPairsName[i]){
						var d=document.getElementById(this.curPairsName[i]).getElementsByTagName("div");
						if(data[j].longSwapIntact.substr(0,1)!="-"){
							d[1].lastChild.innerHTML="&thinsp;&thinsp;"+data[j].longSwapIntact;							
						}else{
							d[1].lastChild.innerHTML=data[j].longSwapIntact;
						}
						if(data[j].shortSwapIntact.substr(0,1)!="-"){
							d[2].lastChild.innerHTML="&thinsp;&thinsp;"+data[j].shortSwapIntact;
						}else{
							d[2].lastChild.innerHTML=data[j].shortSwapIntact;							
						}
					}
				}
			}else{
				for(var j =0;j<data.length;j++){
					if(data[j].currencyPair==this.curPairsName[i]){
						var d=document.getElementById(this.curPairsName[i]).getElementsByTagName("div");
						if(data[j].longSwapIntact.substr(0,1)!="-"){
							d[0].lastChild.lastChild.innerHTML="&thinsp;&thinsp;"+data[j].longSwapIntact;					
						}else{
							d[0].lastChild.lastChild.innerHTML=data[j].longSwapIntact;
						}
						if(data[j].shortSwapIntact.substr(0,1)!="-"){
							d[1].lastChild.lastChild.innerHTML="&thinsp;&thinsp;"+data[j].shortSwapIntact;
						}else{
							d[1].lastChild.lastChild.innerHTML=data[j].shortSwapIntact;						
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
                "num": this.productsList[i].split("-")[1]
            });
        }
    },
    getColor : function(valueOld,valueNow){
        if(valueOld == -100000000 || valueNow == valueOld){
            return this.defaultFontColor;
        }else if(valueNow > valueOld){
            return this.riseFontColor;
        }else{
            return this.fallFontColor;
        }
    },
    initTable : function(self){
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
        this.table.style.fontSize = "12px";
        this.table.style.width = "100%";
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
                if(this.curPairsName[i-1] == "usoil"){
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
                th.colSpan = "2";
                th.innerHTML = thHtml;
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
                if(this.curPairsName[i-1] == "usoil"){
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
                th.innerHTML = thHtml;
            }
            //头部外汇返现
            else if(i==this.curPairsName.length+1 && (this.attrs[3].flag =="need")){
                var th = document.createElement('th');
                th.innerHTML = '<div class="specialBack" style="font-size:12px;font-weight:normal;line-height:14px;position:absolute;top:4px;"><div style="font-weight:bold;">'+this.attrs[3].name+'</div>(EURUSD)</div>';
                th.style.color = "#666";
                th.style.position = "relative";
            }
            //头部黄金返现
            else if(i==this.curPairsName.length+2 && (this.attrs[4].flag =="need")){
                var th = document.createElement('th');
                th.innerHTML = this.attrs[4].name;
                th.style.color = "#666";
            }
            //头部返现
            else if(i==this.curPairsName.length+3 && (this.attrs[5].flag =="need")){
                var th = document.createElement('th');
                th.innerHTML = this.attrs[5].name;
                th.style.color = "#bca47e";
            }
            //头部最后一列交易商名称
            else if(i==this.curPairsName.length+4 && (this.attrs[6].flag =="need")){
                var th = document.createElement('th');
                th.innerHTML = this.attrs[6].name;
                th.style.color = "#666";
            }
            if(th){
                th.style.border = this.commonBorderColor;
                th.style.padding ="10px 5px";
                if(this.superHead == "yes"){
                    th.width = this.normalTdWidth+"%";
                    if(i==0){
                        th.width = this.normalTdWidth+2+"%";
                    }
                    if(i==1){
                        th.width = this.normalTdWidth*2+4+"%";
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
            supHeadTable.style.width = this.container.offsetWidth+"px";
            supHeadTable.style.fontSize = "12px";
            supHeadTable.style.fontFamily = "Microsoft Yahei";
            supHeadTable.style.tableLayout = "fixed";
            var trLen = newTr.children.length;
            for(var k = 0;k<trLen;k++){
                if(k==0){
                    newTr.children[k].setAttribute("width",this.normalTdWidth+2+"%");
                }else if(k==1){
                    newTr.children[k].setAttribute("width",this.normalTdWidth*2+4+"%");
                }else{
                    newTr.children[k].setAttribute("width",this.normalTdWidth+"%");
                }
            }
            supHeadTable.appendChild(newTr);
            superHeadTable = supHeadTable;
            tr.style.visibility = "hidden";
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
                            a.innerHTML = '<img style="height:35px;width:auto;" src="imgs/'+this.productsList[i]+'.png"/>';
                            a.href = this.productUrl[this.productsList[i]];
                            a.style.height = "35px";
                            a.target = "_blank";
                            td.appendChild(a);
                        }else{
                            var img = document.createElement('img');
                            img.src = 'imgs/'+this.productsList[i]+'.png';
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
                        img.style.height = "35px";
                        img.style.width = "auto";
                        img.style.outline = "none";
                        img.style.border = "transparent";
                        td.appendChild(img);
                        td.style.textAlign = "center";
                        var num = parseInt(this.productsList[i].split("-")[1]);
                        if(num<22||num==31||num==35||num==44){
                            var a = document.createElement('a');
                            a.style.display = "block";
                            a.style.textAlign = "center";
                            a.innerHTML = "详情";
                            a.href = this.productUrl[this.productsList[i]];
                            a.target = "_blank";
                            if(this.titleType == "yellow"){
                                a.style.color = "#bca482";
                            }else{
                                a.style.color = "#6f9bdc";
                            }
                            td.appendChild(a);
                        }
                    }
                }
                //table内容行第二列，买入，卖出列
                else if(j==1 && (this.attrs[1].flag =="need")){
                    var td = document.createElement('td');
                    var id = this.containerID+'_'+this.mData[i].num+"_"+this.attrs[1].attr;
                    td.id = id;
					td.style.width="140px";                  
					var divLeft = document.createElement('div');
                    divLeft.style.textAlign = "center";
                    divLeft.innerHTML = "<a href='http://www.ibrebates.com/compTable/cpTableAll.html' target='_blank' style='border-bottom:1px solid #bda57f;text-decoration: none;font-size:15px;color:#bda57f'>点差</a>";
                    divLeft.style.height = "57px";
                    divLeft.style.lineHeight = "55px";
                    divLeft.style.color = "#666";
                    divLeft.style.width = "50px";
                    divLeft.style.float = "left";
                    td.appendChild(divLeft);
                    var divTop = document.createElement('div');
                    divTop.style.textAlign = "center";
                    divTop.innerHTML = "买：<span style='font-size:12px;color:#666;'></span>";
                    divTop.style.height = "28px";
                    divTop.style.lineHeight = "28px";
                    divTop.style.color = "#666";
					divTop.style.width = "90px";
					divTop.style.float = "left";
                    td.appendChild(divTop);
                    var divBottom = document.createElement('div');
                    divBottom.style.textAlign = "center";
                    divBottom.innerHTML = "卖：<span style='font-size:12px;color:#666;'></span>";
                    divBottom.style.height = "28px";
                    divBottom.style.lineHeight = "28px";
                    divBottom.style.color = "#666";
					divBottom.style.width = "90px";
					divBottom.style.float = "left";
                    td.appendChild(divBottom);
                }
                //table内容行第三列，第一个点差列
                else if(j==2){
                    var td = document.createElement('td');
                    var id = this.containerID+'_'+this.mData[i].num+'_'+this.curPairsName[j-2];
                    td.id = id;
					td.style.width="95px";
                    var divDc = document.createElement('div');
                    divDc.style.textAlign = "center";
                    divDc.innerHTML = "--";
                    divDc.style.fontSize = this.defaultFontSize;
                    divDc.style.color = this.defaultFontColor;
                    divDc.style.transition = "color 0.3s linear 0s";
                    divDc.style.fontWeight = this.defaultFontWeight;
                    divDc.style.height = "25px";
                    divDc.style.minWidth = "60px";
					divDc.style.marginLeft="-38px";
                    td.appendChild(divDc);
                    if(this.attrs[2].flag == "need"){
                        var divAc = document.createElement('div');
                        divAc.style.textAlign = "center";
                        //divAc.innerHTML = "实际成本1.5";
                        divAc.style.color = "#999";
                        divAc.style.margin = "5px 0 -5px 0";
                        td.appendChild(divAc);
                    }
                }
                //table内容行全部普通点差列
                else if(j>2 && j<=this.curPairsName.length+1){
                    var td = document.createElement('td');
                    var id = this.containerID+'_'+this.mData[i].num+'_'+this.curPairsName[j-2];
                    td.id = id;
                    var divDc = document.createElement('div');
                    divDc.innerHTML = "--";
                    divDc.style.fontSize = this.defaultFontSize;
                    divDc.style.textAlign = "center";
                    divDc.style.color = this.defaultFontColor;
                    divDc.style.transition = "color 0.3s linear 0s";
                    divDc.style.fontWeight = this.defaultFontWeight;
                    divDc.style.height = "25px";
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
                //table内容行外汇返佣列
                else if(j==this.curPairsName.length+2 && (this.attrs[3].flag =="need")){
                    var td = document.createElement('td');
                    var id = this.containerID+'_'+this.mData[i].num+'_'+this.attrs[3].attr;
                    td.id = id;
                    //外汇返佣内容
                    if(this.mCostData[this.mData[i].num].forex){
                        td.innerHTML = this.mCostData[this.mData[i].num].forex;
                    }else{
                        td.innerHTML = "--";
                    }
                    //13是aetos-13，取欧美的点差
                    if(this.mData[i].num == "13"){
                        td.innerHTML = this.mCostData[this.mData[i].num].eurusd;
                    }
                    //exness 为36%*实时点差
                    if(this.mData[i].num == "6"){
                        td.innerHTML = "--";
                    }
                    td.style.textAlign = "center";
                    td.style.fontSize = this.defaultFontSize;
                    td.style.color = "#999";
                    td.style.fontWeight = this.defaultFontWeight;
                }
                //table内容行黄金返佣列
                else if(j==this.curPairsName.length+3 && (this.attrs[4].flag =="need")){
                    var td = document.createElement('td');
                    var id = this.containerID+'_'+this.mData[i].num+'_'+this.attrs[4].attr;
                    td.id = id;
                    //黄金返佣内容
                    //外汇返佣内容
                    if(this.mCostData[this.mData[i].num].gold){
                        td.innerHTML = this.mCostData[this.mData[i].num].gold;
                    }else{
                        td.innerHTML = "--";
                    }

                    //exness 为36%*实时点差
                    if(this.mData[i].num == "6"){
                        td.innerHTML = "--";
                    }
                    td.style.textAlign = "center";
                    td.style.fontSize = this.defaultFontSize;
                    td.style.color = "#999";
                    td.style.fontWeight = this.defaultFontWeight;
                }
                //table内容行交易商名称
                else if(j==this.curPairsName.length+4 && (this.attrs[5].flag =="need")){
                    var td = document.createElement('td');
                    var id = this.containerID+'_'+this.mData[i].num+'_'+j;
                    td.id = id;
                    var div = document.createElement("div");
                    div.style.height = "25px";
                    div.style.textAlign = "center";
                    div.style.color = this.defaultFontColor;
                    div.style.fontSize = this.defaultFontSize;
                    if(this.mCostData[this.mData[i].num].forexratio){
                        div.innerHTML = this.mCostData[this.mData[i].num].forexratio;
                    }else{
                        div.innerHTML = "--"
                    }
                    td.appendChild(div);
                }
                //table内容行交易商名称
                else if(j==this.curPairsName.length+5 && (this.attrs[6].flag =="need")){
                    if(this.imgHref == "yes"){
                        var td = document.createElement('td');
                        var id = this.containerID+'_'+this.mData[i].num+'_'+j;
                        td.id = id;
                        var num = parseInt(this.productsList[i].split("-")[1]);
                        if(num<22||num==31||num==35||num==44){
                            var a = document.createElement('a');
                            a.style.display = "block";
                            a.style.textAlign = "center";
                            a.innerHTML = '<img style="height:35px;width:auto;" src="imgs/'+this.productsList[i]+'.png"/>';
                            a.href = this.productUrl[this.productsList[i]];
                            a.style.height = "35px";
                            a.target = "_blank";
                            td.appendChild(a);
                        }else{
                            var img = document.createElement('img');
                            img.src = 'imgs/'+this.productsList[i]+'.png';
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
                        img.style.height = "35px";
                        img.style.width = "auto";
                        img.style.outline = "none";
                        img.style.border = "transparent";
                        td.appendChild(img);
                        td.style.textAlign = "center";
                        var num = parseInt(this.productsList[i].split("-")[1]);
                        if(num<22||num==31||num==35||num==44){
                            var a = document.createElement('a');
                            a.style.display = "block";
                            a.style.textAlign = "center";
                            a.innerHTML = "详情";
                            a.href = this.productUrl[this.productsList[i]];
                            a.target = "_blank";
                            if(this.titleType == "yellow"){
                                a.style.color = "#bca482";
                            }else{
                                a.style.color = "#6f9bdc";
                            }
                            td.appendChild(a);
                        }
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
		//利息
		if(this.mData.length==1){
			var swap_tr=document.createElement("tr");
			for(var i=0;i<this.curPairsName.length;i++){
			if(i==0){
			var td1=document.createElement("td");
			td1.id=this.curPairsName[i];
			td1.colSpan="2";               
					var divLeft = document.createElement('div');
                    divLeft.style.textAlign = "center";
                    divLeft.innerHTML = "<a href='http://www.ibrebates.com/swapTable/swapTableAll.html' target='_blank' style='border-bottom:1px solid #bda57f;text-decoration: none;font-size:15px;color:#bda57f'>利息</a>";
                    divLeft.style.height = "57px";
                    divLeft.style.lineHeight = "55px";
                    divLeft.style.color = "#666";
                    divLeft.style.width = "50px";
                    divLeft.style.float = "left";
                    td1.appendChild(divLeft);
                    var divTop = document.createElement('div');
                    
                    divTop.innerHTML = "买：<span style='font-size:12px;color:#666;'></span>";
                    divTop.style.height = "28px";
                    divTop.style.lineHeight = "28px";
                    divTop.style.color = "#666";
					divTop.style.width = "139px";
					divTop.style.float = "left";
					divTop.style.paddingLeft = "45px";
                    td1.appendChild(divTop);
                    var divBottom = document.createElement('div');
                    
                    divBottom.innerHTML = "卖：<span style='font-size:12px;color:#666;'></span>";
                    divBottom.style.height = "28px";
                    divBottom.style.lineHeight = "28px";
                    divBottom.style.color = "#666";
					divBottom.style.width = "139px";
					divBottom.style.float = "left";
					divBottom.style.paddingLeft = "45px";
                    td1.appendChild(divBottom);
					swap_tr.appendChild(td1);
				}else{
				var td=document.createElement("td");
				td.id=this.curPairsName[i];
				td.style.border="1px solid rgb(221, 221, 221)";
				    var divTop = document.createElement('div');
                    //divTop.style.textAlign = "center";
                    divTop.innerHTML = "<span style='margin-left:22px;'>买：<span style='font-size:12px;color:#666;'></span></span>";
                    divTop.style.height = "28px";
                    divTop.style.lineHeight = "28px";
                    divTop.style.color = "#666";
                    divTop.style.borderBottom="1px solid rgb(221,221,221)";
                    
					//divTop.style.width = "120px";
					//divTop.style.float = "left";
                    td.appendChild(divTop);
                    var divBottom = document.createElement('div');
                    //divBottom.style.textAlign = "center";
                    divBottom.innerHTML = "<span style='margin-left:22px;'>卖：<span style='font-size:12px;color:#666;'></span></span>";
                    divBottom.style.height = "28px";
                    divBottom.style.lineHeight = "28px";
                    divBottom.style.color = "#666";
					//divBottom.style.width = "120px";
					//divBottom.style.float = "left";
                    td.appendChild(divBottom);
				swap_tr.appendChild(td);
				}
			}
			this.table.appendChild(swap_tr);
			this.container.appendChild(this.table);
			$.ajax({
			type: "get",
            dataType: "json",
            url: this.url2,
			success:function(data){
				self.refreshTable_swap(data);
			},
			error:function(data){
				console.log("获取利息错误");
			}
		});
		}

		
        if(this.hasBottomSign == "yes"){
            //下边的提示
            var tipsP = document.createElement("p");
            tipsP.innerHTML = "本功能由IB外汇返现网ibrebates.com提供";
            tipsP.style.textAlign = "right";
            tipsP.style.color = "#666";
            tipsP.style.fontSize = "12px";
            tipsP.style.fontWeight = "bold";
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
            fakeDiv.style.height = this.tableHeadHeight+1+"px";
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