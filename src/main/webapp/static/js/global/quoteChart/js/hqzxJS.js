/**
 * Created by yh on 2016/6/7.
 */
 var mJquery = jQuery.noConflict();
mJquery(function(){
   bindClick();
})
//绑定切换事件
function bindClick(){
//  tab切换与画布canvas
    mJquery('.btn').click(function () {
        mJquery('.tabContent').addClass('hide');
        mJquery('.btn').removeClass('selected');
        var tabID = mJquery(this).data('id');
        mJquery('#' + tabID).removeClass('hide');
        mJquery(this).addClass("selected");
    });
}