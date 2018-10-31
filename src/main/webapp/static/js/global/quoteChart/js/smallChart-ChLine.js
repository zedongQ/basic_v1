/**
 * Created by BJ on 2016/7/5.
 */
function ChLine(canvas, width, height, beginPoint, endPoint) {
    this.cans = canvas;
    this.x1 = beginPoint.x;
    this.y1 = beginPoint.y;
    this.x2 = endPoint.x;
    this.y2 = endPoint.y;
    this.lineWidth = 1;
    this.lineColor = "#506d99";
    this.fillColor = "#a5c4fd";
    this.width = width;
    this.height = height;
    this.opacity = 0.4;
    this.originalOpacity = 1;
}
ChLine.prototype = {
    draw: function () {
        this.cans.beginPath();
        this.cans.moveTo(this.x1, this.height - 2);
        this.cans.lineTo(this.x1, this.y1);
        this.cans.lineTo(this.x2, this.y2);
        this.cans.lineTo(this.x2, this.height - 2);
        this.cans.lineTo(this.x1, this.height - 2);
        this.cans.fillStyle = this.fillColor;
        this.cans.globalAlpha = this.opacity;
        this.cans.fill();
        this.cans.closePath();
        this.cans.globalAlpha = this.originalOpacity;

        this.cans.beginPath();
        this.cans.moveTo(this.x1, this.y1);
        this.cans.lineTo(this.x2, this.y2);
        this.cans.lineWidth = this.lineWidth;
        this.cans.strokeStyle = this.lineColor;
        this.cans.stroke();
        this.cans.closePath();
    }
};