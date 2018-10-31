/**
 * Created by BJ on 2016/8/19.
 */
function CptRow(tr){
    this.id = tr.id;
    this.selfElement = tr;
    this.cells = new Array();
}
CptRow.prototype={
    //获取所有子节点
    getCells : function(){
        return this.cells;
    },
    addCell : function(td){
        this.cells.push(td);
    }
};