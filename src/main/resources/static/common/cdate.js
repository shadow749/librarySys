Date.prototype.format = function(fmt) {
    var o = {
        "M+" : this.getMonth()+1, //月份
        "d+" : this.getDate(), //日
        "h+" : this.getHours(), //小时
        "m+" : this.getMinutes(), //分
        "s+" : this.getSeconds(), //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S" : this.getMilliseconds() //毫秒
    };
    if(/(y+)/.test(fmt)) {
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
    for(var k in o) {
        if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
    return fmt;
}

function getDate(strDate){
    if(strDate && strDate!=""){
        var date = eval('new Date(' + strDate.replace(/\d+(?=-[^-]+$)/, function (a) { return parseInt(a, 10) - 1; }).match(/\d+/g) + ')');
        return date;
    }
    return undefined;
}

function formatDate(date, fmt){
    var format = fmt || "yyyy-MM-dd hh:mm:ss";
    if(date instanceof Date){
        return date.format(format);
    }else if(typeof date == 'string'){
        var d = getDate(date);
        if(d){
            return d.format(format);
        }
        return '';
    }else{
        return date;
    }
}