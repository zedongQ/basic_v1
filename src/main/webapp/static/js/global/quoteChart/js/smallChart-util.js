/**
 * Created by BJ on 2016/7/5.
 */
//获取鼠标位置
function getMousePos(canvas, event) {
    if (!canvas) {
        return;
    }
    var style = canvas.currentStyle || window.getComputedStyle(canvas, null);
    borderLeftWidth = parseInt(style['borderLeftWidth'], 10);
    borderTopWidth = parseInt(style['borderTopWidth'], 10);
    var rect = canvas.getBoundingClientRect();
    x = event.clientX - borderLeftWidth - rect.left;
    y = event.clientY - borderTopWidth - rect.top;
    return {
        x: x,
        y: y
    }
}

function addEvent(elem, evType, fn) {
    if (elem.addEventListener) {
        elem.addEventListener(evType, fn);
    } else if (elem.attachEvent) {
        elem.attchEvent('on' + evType, fn);
    } else {
        elem['on' + evType] = fn;
    }
}

