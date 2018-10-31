/**
 * Created by BJ on 2016/7/6.
 */
function ChChart(cv, cover, tipDiv, dataColumn) {
    this.cv = cv;
    this.cover = cover;
    this.tipDiv = tipDiv;
    this.cvWidth = cv.offsetWidth;
    this.cvHeight = cv.offsetHeight;

    //默认显示的区间的个数
    this.column = dataColumn;
    //默认横轴的条数
    this.horizonLineNum = 3;
    //默认纵轴的条数
    this.verticalLineNum = 3;

    //横轴坐标区域(上下两部分，上下预留像素，下坐标)
    this.horizontalArea = 0;
    //纵轴坐标区域
    this.verticalArea = 67;
    //偏移量，消除像素误差
    this.offset = 1;
    //将图和背景虚线网，往上偏移1px,往右移动1px,错开边框
    this.picOffsetX = 1;
    this.picOffsetY = 3;
    //圆数组
    this.circles = new Array();
    //线数组
    this.lines = new Array();

    //字体颜色
    this.fontColor1 = '#188a00';
    this.fontColor2 = '#666';
    this.fontColor3 = '#ff0101';
    //字体样式
    this.fontSize = '12px';
    //显示在哪个点上
    this.near = 0;

    //当前显示区间
    this.end = 0;
    this.begin = 0;
    //线粗细
    this.lineWidth = 1;
    //准备数据
    this.hasDrawData = false;

    //虚线，每行线的高
    this.perRowHight = 0;
    //虚线，每列的宽度
    this.perColWidth = 0;

    //数据，8列，每列的宽
    this.perWidth = 0;
    //左边坐标 toFixed 的位数
    this.tofixNum = 5;
    //数值数组
    this.data = [];
    //对象数组
    this.mData = [];
    //第一次初始化图的标志
    this.firstInit = false;
}
ChChart.prototype = {
    //画图用的是一个值的数组
    setData: function (data) {
        this.mData = data;
        this.end = this.mData.length - 1;
        this.begin = this.end - this.column + 1;
        if (this.begin < 0) {
            this.begin = 0;
        }

        //this.sortData();
        for (var i = 0; i < this.mData.length; i++) {
            this.data[i] = this.mData[i].close;
        }
    },
    initCanvas: function () {
        if (this.firstInit == false) {
            if (!this.cv.getContext) {
                console.log("error!");
                return;
            }
            this.cans = this.cv.getContext('2d');
            this.addMyEventListener(this.cover, this);
            this.firstInit = true;
        }
    },
    addMyEventListener: function (cover, chchart) {
        var that = this;
        addEvent(cover, "mousemove", function (event) {
            var point = getMousePos(cover, event);
            point.x = point.x - that.verticalArea;
            //console.log(point.x+""+point.y);
            //cover.style.cursor = 'none';

            //var crossline = new ChCrossline();
            //crossline.refresh(point);
            that.near = that.getNear(point);
            for (var i = 0; i < that.circles.length; i++) {
                that.circles[i].removeHover();
            }
            if (that.circles[that.near - 1]) {
                that.circles[that.near - 1].hoverCircle();
                that.showTipDiv(that.circles[that.near - 1], that.near);
            } else {
                //防止鼠标移动到左边出现bug
                that.hideTipDiv();
            }
            that.draw();
        });
        addEvent(cover, "mouseout", function (event) {
            that.near = 0;
            that.hideTipDiv();
            that.resetCell();
            that.draw();
        });
    },
    draw: function () {
        //清空画布
        this.drawBackground();
        this.drawAxes();
        this.drawLocation();
        if (!this.hasDrawData) {
            this.createCells();
            this.hasDrawData = true;
        }
        this.drawCells();
    },
    refresh: function () {
        //清空画布
        this.drawBackground();
        this.drawAxes();
        this.drawLocation();
        this.createCells();
        this.drawCells();
    },
    drawBackground: function () {
        this.cans.clearRect(0, 0, this.cvWidth, this.cvHeight);
        //this.cans.beginPath();
        //this.cans.fillStyle='#fff';
        //this.cans.fillRect(0,0,this.cvWidth,this.cvHeight);
        //this.cans.fill();
        //this.cans.closePath();
    },
    drawAxes: function () {
        var height = this.cvHeight;
        var width = this.cvWidth;
        var cans = this.cans;
        //无法将边框画成1px！因为lineWidth=1也是画的两像素！
        this.cans.beginPath();
        //绘制纵轴
        cans.moveTo(this.verticalArea, 0);
        cans.lineTo(this.verticalArea, height - this.horizontalArea - this.offset);
        //绘制横轴
        cans.lineTo(width - this.offset, height - this.horizontalArea - this.offset);
        //绘制右边框
        cans.lineTo(width - this.offset, this.offset);
        //绘制上边框
        cans.lineTo(this.verticalArea, this.offset);
        cans.lineWidth = 2;
        cans.strokeStyle = '#c9c9c9';
        cans.stroke();
        cans.closePath();
    },
    drawLocation: function () {
        //画横轴虚线，上边不精确
        this.perRowHight = Math.floor((this.cvHeight - this.horizontalArea - this.picOffsetY * 2) / (this.horizonLineNum + 1));
        for (var i = 0; i < this.horizonLineNum; i++) {
            this.drawDashLine(this.verticalArea, this.cvHeight - this.perRowHight * (i + 1) - this.horizontalArea - this.picOffsetY, this.cvWidth, this.cvHeight - this.perRowHight * (i + 1) - this.horizontalArea - this.picOffsetY);
        }
        //画纵轴虚线，右边不精确
        this.perColWidth = Math.floor((this.cvWidth - this.verticalArea - this.picOffsetX) / (this.verticalLineNum + 1));
        for (var i = 0; i < this.verticalLineNum; i++) {
            this.drawDashLine(this.perColWidth * (i + 1) + this.verticalArea + this.picOffsetX, 0, this.perColWidth * (i + 1) + this.verticalArea + this.picOffsetX, this.cvHeight);
        }
        //画左边的坐标轴
        this.drawLeftValue();
    },
    drawDashLine: function (x1, y1, x2, y2) {
        //画背景虚线函数
        var cans = this.cans;
        cans.beginPath();
        var lineLong = Math.abs((x2 - x1) != 0 ? x2 - x1 : y2 - y1);
        var lineWeight = this.lineWidth;
        var dashSize = 3;
        var times = Math.floor(lineLong / dashSize);
        for (var i = 0; i < times; i += 2) {
            if (y1 == y2) {
                cans.moveTo(x1 + i * dashSize, y1);
                cans.lineTo(x1 + (i + 1) * dashSize, y1);
            } else {
                cans.moveTo(x1, y1 + i * dashSize);
                cans.lineTo(x1, y1 + (i + 1) * dashSize);
            }
        }
        cans.strokeStyle = '#d1d5de';
        cans.lineWidth = lineWeight;
        cans.stroke();
        cans.closePath();
    },
    createCells: function () {
        var height = this.cvHeight;
        var width = this.cvWidth;
        //右边空出一个位置,8个点7个空位，除以8就空出一个位置
        this.perWidth = Math.floor((width - this.verticalArea - this.picOffsetX * 2) / (this.column - 0.5));
        if (this.perWidth <= 1) {
            this.perWidth = 1;
        }

        var perPxValue = this.getPxValue();
        var minValue = this.getMinValue();
		//判断所有值都一样的情况
		var judge = false;
		if(this.getMaxValue() == this.getMinValue()){
			judge = true;
			var yP = Math.floor(this.cvHeight / 2) + this.horizontalArea;
		}

        this.lines.length = 0;
        this.circles.length = 0;
        for (var i = this.begin; i < this.end; i++) {
            //线
            var beginPoint = {
                x: ((i - this.begin) * this.perWidth + this.verticalArea + this.picOffsetX),
                y: (this.cvHeight - Math.floor((this.data[i] - minValue) / perPxValue) + this.horizontalArea - this.picOffsetY)
            };
            var endPoint = {
                x: ((i - this.begin + 1) * this.perWidth + this.verticalArea + this.picOffsetX),
                y: (this.cvHeight - Math.floor((this.data[i + 1] - minValue) / perPxValue) + this.horizontalArea - this.picOffsetY)
            };
			if(judge == true){
				beginPoint.y = yP;
				endPoint.y = yP;
			}
            var chline = new ChLine(this.cans, this.cvWidth, this.cvHeight, beginPoint, endPoint);
            this.lines.push(chline);

            //圆点
            var point = {
                x: ((i - this.begin) * this.perWidth + this.verticalArea + this.picOffsetX),
                y: (this.cvHeight - Math.floor((this.data[i] - minValue) / perPxValue) - this.picOffsetY)
            };
			if(judge == true){
				point.y = yP;
			}
            var circle = new ChCircle(this.cans, point);
            this.circles.push(circle);
        }
        //圆点比线多一个
        var point = {
            x: ((this.end - this.begin) * this.perWidth + this.verticalArea + this.picOffsetX),
            y: (this.cvHeight - Math.floor((this.data[this.end] - minValue) / perPxValue) - this.picOffsetY)
        };
        var circle = new ChCircle(this.cans, point);
        this.circles.push(circle);

        //判断是否鼠标正放在元素上
        if (this.near) {
            if (this.circles[this.near - 1]) {
                this.circles[this.near - 1].hoverCircle();
                this.showTipDiv(this.circles[this.near - 1], this.near);
            }
        }
    },
    getPxValue: function () {
        if (this.data && this.perRowHight != 0) {
            var max = this.getMaxValue();
            var min = this.getMinValue();
            var pxValue = (max - min) / parseFloat(this.horizonLineNum * this.perRowHight);
            return pxValue;
        }
    },
    getMinValue: function () {
        if (this.data) {
            var data = [];
            for (var i = this.begin; i <= this.end; i++) {
                data.push(this.data[i]);
            }
            this.sortData(data);
            return data[0];
        }
    },
    getMaxValue: function () {
        if (this.data) {
            var data = [];
            for (var i = this.begin; i <= this.end; i++) {
                data.push(this.data[i]);
            }
            this.sortData(data);
            return data[data.length - 1];
        }
    },
    //getBiggerMaxValue:function(){
    //    if(this.data){
    //        var data = [];
    //        for(var i = this.begin;i <= this.end;i++){
    //            data.push(this.data[i]);
    //        }
    //        this.sortData(data);
    //        //让max 和 min的差值变得能被3整除，这样好标坐标
    //        var min = data[0];
    //        var range = data[data.length - 1] - data[0];
    //        range = ((Math.ceil(range * 1000 / this.horizonLineNum) / 1000).toFixed(this.horizonLineNum)*1000) * this.horizonLineNum/1000;
    //        return min + range;
    //    }
    //},
    sortData: function (data) {
        if (data != null) {
            return data.sort(function (a, b) {
                return a - b
            });
        }
    },
    drawCells: function () {
        for (var i = 0; i < this.lines.length; i++) {
            this.lines[i].draw();

            this.circles[i].draw();
        }
        //多画一个点
        this.circles[this.circles.length - 1].draw();

    },
    drawLeftValue: function () {
        //画左边的坐标
        var maxData = this.getMaxValue();
        var minData = this.getMinValue();
        for (var i = 0; i < this.horizonLineNum + 2; i++) {
            if (i == 0) {
                this.drawText((minData + (maxData - minData) * (this.horizonLineNum + 1) / this.horizonLineNum).toFixed(this.tofixNum), this.fontColor1, 2, 10);
            } else if (i == this.horizonLineNum + 1) {
                this.drawText(minData.toFixed(this.tofixNum), this.fontColor3, 2, this.cvHeight - this.horizontalArea);
            } else {
                if (i < parseInt((this.horizonLineNum + 2) / 2)) {
                    this.drawText((minData + (maxData - minData) / this.horizonLineNum * i).toFixed(this.tofixNum), this.fontColor3, 2, this.cvHeight - this.horizontalArea - this.perRowHight * i);
                } else if (i == parseInt((this.horizonLineNum + 2) / 2)) {
                    this.drawText((minData + (maxData - minData) / this.horizonLineNum * i).toFixed(this.tofixNum), this.fontColor2, 2, this.cvHeight - this.horizontalArea - this.perRowHight * i);
                }
                else {
                    this.drawText((minData + (maxData - minData) / this.horizonLineNum * i).toFixed(this.tofixNum), this.fontColor1, 2, this.cvHeight - this.horizontalArea - this.perRowHight * i);
                }
            }
        }
    },
    drawText: function (text, color, x, y) {
        this.cans.beginPath();
        this.cans.fillStyle = color;
        this.cans.fillText(text, x, y);
        this.cans.closePath();
    },
    getNear: function (point) {
        var x = point.x;
        var y = point.y;
        var halfPerWidth = this.perWidth / 2;

        //先计算出当前点在那个区域，8个点有16个区域
        var result = parseInt(x / halfPerWidth);
        var left = x % halfPerWidth;
        if (left > 0) {
            result++;
        }
        //再计算出，当前点离哪个点近
        return parseInt((result + 2) / 2);
    },
    showTipDiv: function (circle, near) {
        var x = circle.x;
        var y = circle.y;
        var tipHeight = this.tipDiv.offsetHeight;
        var tipWidth = this.tipDiv.offsetWidth;
        //小提示框的上下偏移
        var tipOffset = 3;
        //超过画布宽一半往左放
        if (x > ((this.cvWidth - this.verticalArea) / 2 + this.verticalArea)) {
            x = x - tipWidth - tipOffset;
        } else {
            x += tipOffset;
        }
        //超过画布高一半往上放
        if (y > this.cvHeight / 2) {
            y = y - tipHeight - tipOffset;
        } else {
            y += tipOffset;
        }
        this.tipDiv.style.display = "block";
        this.tipDiv.style.left = x + "px";
        this.tipDiv.style.top = y + "px";
        var time = this.mData[this.begin + near - 1].time.substring(8, 10) + ":" + this.mData[this.begin + near - 1].time.substring(10, 12);
        this.tipDiv.innerHTML = '<span style="font-size:12px;white-space:nowrap;overflow:hidden;color:#666;display:block;text-align:center;line-height:19px;">' + this.data[this.begin + near - 1] + '</span><span style="font-size:12px;color:#666;display:block;text-align:center;line-height:19px;">'
        + time + '</span>';
    },
    refreshTipDiv : function(){
        var time = this.mData[this.begin + near - 1].time.substring(8, 10) + ":" + this.mData[this.begin + near - 1].time.substring(10, 12);
        this.tipDiv.innerHTML = '<span style="font-size:12px;white-space:nowrap;overflow:hidden;color:#666;display:block;text-align:center;line-height:19px;">' + this.data[this.begin + near - 1] + '</span><span style="font-size:12px;color:#666;display:block;text-align:center;line-height:19px;">'
        + time + '</span>';
    },
    hideTipDiv: function () {
        this.tipDiv.style.display = "none";
    },
    resetCell: function () {
        for (var i = 0; i < this.circles.length; i++) {
            this.circles[i].removeHover();
        }
    }
};