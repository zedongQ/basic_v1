/**
 * Created by BJ on 2016/7/5.
 */
function ChCanvas(configParam) {
    this.configParam = configParam;
    this.serverPath = configParam.serverPath;
    this.token = configParam.token;
    this.mData = [];
    this.product = configParam.product;
    this.productName = configParam.productName;
    //分钟现价
    this.nowValue = 0;
    //分钟收盘价
    this.minuteClose = 0;
    //分钟开盘价
    this.minuteOpen = 0;
    //图表上有多少列
    this.dataColumn = configParam.dataColumn;
    this.containerID = configParam.containerID;
    this.containerElement = document.getElementById(this.containerID);

    this.width = configParam.width;
    this.height = configParam.height;
    this.chartType = configParam.chartType;
    //第一种样式，ul框在左边
    this.canvasDivWidth = parseInt(this.width * 73.5 / 100);
    this.canvasDivHeight = this.height;
    this.leftTextDivWidth = this.width - this.canvasDivWidth;
    this.leftTextDivHeight = this.height;
    //第二种样式，ul框在上边
    this.topDivWidth = this.width;
    this.topDivHeight = parseInt(this.height * 37 / 100);
    this.bottomDivWidth = this.width;
    this.bottomDivHeight = this.height - this.topDivHeight;
    this.isRequesting = false;
    //第三种样式，没有文字框
    this.divWidth = this.width;
    this.divHeight = this.height;


}
ChCanvas.prototype = {
    init: function () {
        //先关闭
        if (this.ws != null) {
            this.ws.close();
            this.ws = null;
        }
        // 创建一个Socket实例
        this.ws = new WebSocket(this.serverPath);
        this.getMsg(this);
    },
    clear : function(configParam){
        this.ws.close();
        this.ws = null;
        this.containerElement.innerHTML = "";
    },
    //建立长连接
    getMsg: function (self) {
        // 打开Socket
        self.ws.onopen = function (event) {
            //验证token
            self.ws.send('c:0&' + self.token);
        };
        // 监听消息
        self.ws.onmessage = function (event) {
            var msg = event.data.split("&");
            //console.log(event.data);
            if (msg.length < 1) {
                return;
            }
            //验证token
            if (msg[0].indexOf("s:0") > 0 && msg[1] == 0) {
                self.requestData(self);
            } else if (msg[0].indexOf("s:1") > 0) {
                if (msg[1] != self.product) {
                    console.log("ProductName isn't match.");
                    return;
                }
                self.mData.length = 0;
                for (var i = 3; i < msg.length; i += 7) {
                    var item = {
                        //用收盘价
                        close: parseFloat(msg[i + 4]),
                        time: msg[i]
                    };
                    self.mData.push(item);
                }
                self.initChCanvas();
                self.isRequesting = false;
                //赋值开盘价和现价
                self.minuteOpen = parseFloat(msg[i - 7 + 4]);
                self.minuteClose = parseFloat(msg[i - 7 + 4]);
                self.nowValue = parseFloat(msg[i - 7 + 4]);

                self.ws.send('c:2&' + self.product);
            } else if (msg[0].indexOf("s:2") > 0) {
                if (msg[1] != self.product) {
                    console.log("ProductName isn't match.");
                    return;
                }
                //让时间配套都是分钟
                if (msg[2].substring(0, 12) != self.mData[self.mData.length - 1].time) {
                    //赋值开盘价和现价
                    self.minuteOpen = parseFloat(msg[3]);
                    self.minuteClose = parseFloat(msg[3]);
                    self.nowValue = parseFloat(msg[3]);
                    self.mData.shift();
                    var item = {
                        //用的是卖出价
                        close: parseFloat(msg[3]),
                        time: msg[2].substring(0, 12)
                    };
                    self.mData.push(item);
                    //时间没变就直接赋值
                } else if (msg[2].substring(0, 12) == self.mData[self.mData.length - 1].time) {
                    //赋值当前值
                    self.nowValue = parseFloat(msg[3]);
                    var item = {
                        //用的是卖出价
                        close: parseFloat(msg[3]),
                        time: msg[2].substring(0, 12)
                    };
                    self.mData[self.mData.length - 1] = item;
                }
            }
        };

        // 监听Socket的关闭
        this.ws.onclose = function (event) {
            console.log('Client notified socket has closed', event);
        };

        // 监听Socket的关闭
        this.ws.onerror = function (event) {
            console.log('Client notified socket has error', event);
        };

    },
    requestData: function (self) {
        if (self.isRequesting == false) {
            //self.setProductMsg(self);
            //self.ws.send('c:14&'+self.product);
            self.ws.send('c:1&' + self.product + '&1&0&20160507103010&' + self.dataColumn);
            self.isRequesting = true;
        }
    },
    initChCanvas: function () {
        this.containerElement.innerHTML = '';
        this.containerElement.style.width = this.width + "px";
        this.containerElement.style.height = this.height + "px";
        if (this.chartType == "1") {
            //初始化第一种画布  26.5%
            var leftDiv = document.createElement("div");
            var leftUl = document.createElement("ul");
            leftUl.style.margin = 0;
            leftUl.style.padding = 0;
            leftUl.style.fontFamily = "Microsoft Yahei";
            leftDiv.style.width = this.leftTextDivWidth + "px";
            leftDiv.style.height = this.leftTextDivHeight + "px";
            leftDiv.style.float = "left";
            for (var i = 0; i < 5; i++) {
                var li = document.createElement("li");
                li.style.listStyle = "none";
                li.style.lineHeight = this.height / 5 + "px";
                if (i == 0) {
                    li.innerHTML = "黄金延期";
                    li.style.fontSize = "16px";
                    li.style.color = "#333";
                    this.li0 = li;
                } else if (i == 1) {
                    li.innerHTML = "AU(T+D)";
                    li.style.fontSize = "12px";
                    li.style.color = "#b5b6ba";
                    this.li1 = li;
                } else if (i == 2) {
                    li.innerHTML = "1.7328";
                    li.style.fontSize = "16px";
                    li.style.fontWeight = "bold";
                    li.style.color = "#0bbb73";
                    this.li2 = li;
                } else if (i == 3) {
                    li.innerHTML = "+0.0002";
                    li.style.fontSize = "14px";
                    li.style.color = "#0bbb73";
                    this.li3 = li;
                } else if (i == 4) {
                    li.innerHTML = "(+0.03%)";
                    li.style.fontSize = "14px";
                    li.style.color = "#0bbb73";
                    this.li4 = li;
                }

                leftUl.appendChild(li);
            }
            leftDiv.appendChild(leftUl);
            this.containerElement.appendChild(leftDiv);

            var rightDiv = document.createElement("div");
            rightDiv.style.width = this.canvasDivWidth + "px";
            rightDiv.style.height = this.canvasDivHeight + "px";
            rightDiv.style.float = "left";
            rightDiv.style.position = "relative";

            //鼠标移动时显示信息的小框
            this.tipDiv = document.createElement("div");
            this.tipDiv.style.paddingTop = "4px";
            this.tipDiv.style.paddingBottom = "4px";
            this.tipDiv.style.width = "51px";
            this.tipDiv.style.height = "37px";
            this.tipDiv.style.border = "1px solid #ccc";
            this.tipDiv.style.borderRadius = "3px";
            this.tipDiv.style.backgroundColor = "#f7f7f7";
            this.tipDiv.style.position = "absolute";
            this.tipDiv.style.left = "0px";
            this.tipDiv.style.top = "0px";
            this.tipDiv.style.zIndex = 100;
            this.tipDiv.style.display = "none";

            rightDiv.appendChild(this.tipDiv);
            //生成画布对象
            this.cv = document.createElement('canvas');
            this.cv.width = this.canvasDivWidth - 2;
            this.cv.height = this.canvasDivHeight - 2;
            this.cv.style.position = 'absolute';
            this.cv.style.top = '0px';
            this.cv.style.left = '0px';
            this.cv.style.zIndex = 50;
            rightDiv.appendChild(this.cv);
            //生成事件层
            this.cover = document.createElement('canvas');
            this.cover.width = this.canvasDivWidth - 2;
            this.cover.height = this.canvasDivHeight - 2;
            this.cover.style.position = 'absolute';
            this.cover.style.top = '0px';
            this.cover.style.left = '0px';
            this.cover.style.zIndex = 200;
            rightDiv.appendChild(this.cover);
            this.containerElement.appendChild(rightDiv);

            var chchart = new ChChart(this.cv, this.cover, this.tipDiv, this.dataColumn);
            var that = this;
            setInterval(function () {
                chchart.setData(that.mData);
                //chchart.setData([40,50,30,10,45,60,55,33,22,54,33,50,30,10,45,60,55,33,22,54,33]);
                chchart.initCanvas();
                chchart.refresh();
                //刷新左边显示的数字等
                that.refreshLeftData();
            }, 200);
        } else if (this.chartType == "2") {
            //初始化第二种画布
            var topDiv = document.createElement("div");
            topDiv.style.fontFamily = "Microsoft Yahei";
            topDiv.style.width = this.topDivWidth + "px";
            topDiv.style.height = this.topDivHeight + "px";

            var div1 = document.createElement("div");
            div1.style.overflow = "hidden";
            var sp1 = document.createElement("span");
            sp1.style.lineHeight = "24px";
            sp1.innerHTML = this.productName;
            sp1.style.fontSize = "16px";
            sp1.style.color = "#333";
            sp1.style.float = "left";
            this.sp1 = sp1;
            div1.appendChild(sp1);

            var sp2 = document.createElement("span");
            sp2.innerHTML = this.product;
            sp2.style.fontSize = "12px";
            sp2.style.color = "#b5b6ba";
            sp2.style.float = "left";
            sp2.style.marginLeft = "10px";
            sp2.style.paddingTop = "5px";
            this.sp2 = sp2;
            div1.appendChild(sp2);
            topDiv.appendChild(div1);

            var div2 = document.createElement("div");
            div2.style.overflow = "hidden";
            var sp3 = document.createElement("span");
            sp3.innerHTML = this.nowValue + "";
            sp3.style.fontSize = "14px";
            sp3.style.fontWeight = "bold";
            sp3.style.float = "left";
            sp3.style.color = "#0bbb73";
            this.sp3 = sp3;
            div2.appendChild(sp3);

            var sp4 = document.createElement("span");
            sp4.innerHTML = this.getZd() + "";
            sp4.style.fontSize = "13px";
            sp4.style.float = "left";
            sp4.style.color = "#0bbb73";
            sp4.style.marginLeft = "8px";
            sp4.style.paddingTop = "1px";
            this.sp4 = sp4;
            div2.appendChild(sp4);

            var sp5 = document.createElement("span");
            sp5.innerHTML = "(" + this.getZdf() + "%)";
            sp5.style.fontSize = "13px";
            sp5.style.float = "left";
            sp5.style.color = "#0bbb73";
            sp5.style.paddingTop = "1px";
            sp5.style.marginLeft = "5px";
            this.sp5 = sp5;
            div2.appendChild(sp5);

            topDiv.appendChild(div2);
            this.containerElement.appendChild(topDiv);

            var bottomDiv = document.createElement("div");
            bottomDiv.style.width = this.bottomDivWidth + "px";
            bottomDiv.style.height = this.bottomDivHeight + "px";
            bottomDiv.style.position = "relative";

            //鼠标移动时显示信息的小框
            this.tipDiv = document.createElement("div");
            this.tipDiv.style.paddingTop = "4px";
            this.tipDiv.style.paddingBottom = "4px";
            this.tipDiv.style.width = "51px";
            this.tipDiv.style.height = "37px";
            this.tipDiv.style.border = "1px solid #ccc";
            this.tipDiv.style.borderRadius = "3px";
            this.tipDiv.style.backgroundColor = "#f7f7f7";
            this.tipDiv.style.position = "absolute";
            this.tipDiv.style.left = "0px";
            this.tipDiv.style.top = "0px";
            this.tipDiv.style.zIndex = 100;
            this.tipDiv.style.display = "none";

            bottomDiv.appendChild(this.tipDiv);
            //生成画布对象
            this.cv = document.createElement('canvas');
            this.cv.width = this.bottomDivWidth - 2;
            this.cv.height = this.bottomDivHeight - 2;
            this.cv.style.position = 'absolute';
            this.cv.style.top = '0px';
            this.cv.style.left = '0px';
            this.cv.style.zIndex = 50;
            bottomDiv.appendChild(this.cv);
            //生成事件层
            this.cover = document.createElement('canvas');
            this.cover.width = this.bottomDivWidth - 2;
            this.cover.height = this.bottomDivHeight - 2;
            this.cover.style.position = 'absolute';
            this.cover.style.top = '0px';
            this.cover.style.left = '0px';
            this.cover.style.zIndex = 200;
            bottomDiv.appendChild(this.cover);
            this.containerElement.appendChild(bottomDiv);

            var chchart = new ChChart(this.cv, this.cover, this.tipDiv, this.dataColumn);
            var that = this;
            setInterval(function () {
                chchart.setData(that.mData);
                //chchart.setData([40,50,30,10,45,60,55,33,22,54,33,50,30,10,45,60,55,33,22,54,33]);
                chchart.initCanvas();
                chchart.refresh();
                //刷新左边显示的数字等
                that.refreshLeftData();
            }, 200);
        }else if (this.chartType == "3") {
            //初始化第三种画布
            var bottomDiv = document.createElement("div");
            bottomDiv.style.width = this.divWidth;
            bottomDiv.style.height = this.divHeight;
			bottomDiv.style.backgroundColor = "white";
            bottomDiv.style.position = "relative";

            //鼠标移动时显示信息的小框
            this.tipDiv = document.createElement("div");
            this.tipDiv.style.paddingTop = "4px";
            this.tipDiv.style.paddingBottom = "4px";
            this.tipDiv.style.width = "51px";
            this.tipDiv.style.height = "37px";
            this.tipDiv.style.border = "1px solid #ccc";
            this.tipDiv.style.borderRadius = "3px";
            this.tipDiv.style.backgroundColor = "#f7f7f7";
            this.tipDiv.style.position = "absolute";
            this.tipDiv.style.left = "0px";
            this.tipDiv.style.top = "0px";
            this.tipDiv.style.zIndex = 100;
            this.tipDiv.style.display = "none";

            bottomDiv.appendChild(this.tipDiv);
            //生成画布对象
            this.cv = document.createElement('canvas');
            this.cv.width = this.divWidth - 2;
            this.cv.height = this.divHeight - 2;
            this.cv.style.position = 'absolute';
            this.cv.style.top = '0px';
            this.cv.style.left = '0px';
            this.cv.style.zIndex = 50;
            bottomDiv.appendChild(this.cv);
            //生成事件层
            this.cover = document.createElement('canvas');
            this.cover.width = this.divWidth - 2;
            this.cover.height = this.divHeight - 2;
            this.cover.style.position = 'absolute';
            this.cover.style.top = '0px';
            this.cover.style.left = '0px';
            this.cover.style.zIndex = 200;
            bottomDiv.appendChild(this.cover);
            this.containerElement.appendChild(bottomDiv);

            var chchart = new ChChart(this.cv, this.cover, this.tipDiv, this.dataColumn);
            var that = this;
            setInterval(function () {
                chchart.setData(that.mData);
                //chchart.setData([40,50,30,10,45,60,55,33,22,54,33,50,30,10,45,60,55,33,22,54,33]);
                chchart.initCanvas();
                chchart.refresh();
            }, 200);
        }
    },
    getZdf: function () {
        if (this.nowValue - this.minuteClose == 0 || this.minuteClose == 0) {
            return 0;
        }
        return ((this.nowValue - this.minuteClose) / this.minuteClose ) * 100;
    },
    getZd: function () {
        return this.nowValue - this.minuteOpen;
    },
    refreshLeftData: function () {
        if (this.chartType == 1) {
            this.li0.innerHTML = this.productName;
            this.li1.innerHTML = this.product;
            this.li2.innerHTML = this.nowValue;
            this.li3.innerHTML = "(" + this.getZd().toFixed(5) + ")";
            this.li4.innerHTML = "(" + this.getZdf().toFixed(4) + "%)";
        } else if (this.chartType == 2) {
            this.sp1.innerHTML = this.productName;
            this.sp2.innerHTML = this.product;
            this.sp3.innerHTML = this.nowValue;
            this.sp4.innerHTML = "(" + this.getZd().toFixed(5) + ")";
            this.sp5.innerHTML = "(" + this.getZdf().toFixed(4) + "%)";
        }
    }
};
