$(function () {

    initUser();//当前用户的初始化
});
var dataStu=
    [{
        id: '1',
        text: '图书查询',
        icon: 'icon-book',
        url: '',//点击菜单要打开的页面，如果是一级菜单，url要为空
    }, {
        id: '2',
        text: '图书借阅',
        icon: 'icon-credit-card',
        url: '',
}];
var dataAdmin=
    [{
        id: '1',
        text: '图书查询',
        icon: 'icon-book',
        url: '',//点击菜单要打开的页面，如果是一级菜单，url要为空
    }, {
        id: '2',
        text: '用户管理',
        icon: 'icon-user',
        url: '',
    }, {
        id: '3',
        text: '管理员管理',
        icon: 'icon-user',
        url: '',
    }, {
        id: '4',
        text: '图书借阅管理',
        icon: 'icon-credit-card',
        url: '',
    }];


//当前用户的初始化
function initUser() {
    $.ajax({
        url:"/login/getSession",
        type:"post",
        data:{} ,
        dataType:"json",
        success:function (res) {
            if(res && res.name){
                $("#topName").html(res.name);
            }
            if(res && res.type){
                if(res.type=="1"){
                    $('#menu').sidebarMenu({
                        data:dataStu
                    });
                }else if(res.type=="2"){
                    $('#menu').sidebarMenu({
                        data:dataAdmin
                    });
                }
            }
        }
    })
}