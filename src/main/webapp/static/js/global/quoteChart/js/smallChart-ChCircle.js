/**
 * Created by BJ on 2016/7/6.
 */
function ChCircle(canvas, point) {
    this.cans = canvas;
    this.x = point.x;
    this.y = point.y;
    this.r = 1.3;
    this.r2 = 2;
    this.color = "#0d3977";
    this.color2 = "red";
}
ChCircle.prototype = {
    draw: function () {
        this.cans.beginPath();
        this.cans.arc(this.x, this.y, this.r, 0, Math.PI * 2, false);
        this.cans.fillStyle = this.color;
        this.cans.fill();
        this.cans.closePath();

        if (this.drawBorder == true) {
            this.cans.beginPath();
            this.cans.arc(this.x, this.y, this.r2, 0, Math.PI * 2, false);
            this.cans.strokeStyle = this.color2;
            this.cans.lineWidth = "1";
            this.cans.stroke();
            this.cans.closePath();
        }
    },
    hoverCircle: function () {
        this.drawBorder = true;
    },
    removeHover: function () {
        this.drawBorder = false;
    }
};