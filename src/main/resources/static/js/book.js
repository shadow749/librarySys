$(function () {
    initUser()

    $('#myModal').on('hidden.bs.modal', function () {
        $('#userForm')[0].reset();
        $("#userId").val("");
    });
    $('#bModal').on('hidden.bs.modal', function () {
        $('#bookForm')[0].reset();
        $("#bookId").val("");
    });

    $("#datetimepicker").datetimepicker({
        format: "yyyy-MM-dd",
        autoclose: true,
        todayBtn: true,
        minView:2,
        language:'zh-CN',
    });
})

function tableInit() {
    $('#table').bootstrapTable({
        url: '/do/book/list', // 请求后台的URL（*）
        method: 'get', // 请求方式（*）
        contentType: "application/x-www-form-urlencoded",//post 必须制定类型，否则不能正常传值
        toolbar: '#toolbar', // 工具按钮用哪个容器
        striped: true, // 是否显示行间隔色
        cache: false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true, // 是否显示分页（*）
        sortName: "id",//默认排序列
        sortable: true, // 是否启用排序
        sortOrder: "asc", // 排序方式
        queryParams: tableQueryParams,// 传递参数（*）
        sidePagination: "server", // 分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1, // 初始化加载第一页，默认第一页
        pageSize: 10, // 每页的记录行数（*）
        pageList: [10, 15, 20], // 可供选择的每页的行数（*）
        search: false, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: true,
        showColumns: true, // 是否显示所有的列
        showRefresh: true, // 是否显示刷新按钮
        minimumCountColumns: 2, // 最少允许的列数
        clickToSelect: true, // 是否启用点击选中行
        singleSelect: true,//开启单选，默认为多选
        uniqueId: "id", // 每一行的唯一标识，一般为主键列
        showToggle: false, // 是否显示详细视图和列表视图的切换按钮
        cardView: false, // 是否显示详细视图
        detailView: false, // 是否显示父子表
        columns: [ {
            field: 'name',
            title: '书名'
        },{
            field: 'author',
            title: '作者'
        },{
            field: 'code',
            title: '条形码'
        },{
            field: 'statue',
            title: '借阅状态',
            formatter: function (data, row, index) {
                if(data=="1"){
                    return "<span style='color: green' >可借阅</span>";
                }else if(data =="2"){
                    return "<span style='color: red' >已借出</span>";
                }else{
                    return data;
                }

            }
        },{
            field: 'address',
            title: '馆藏地址',
        },{
            field: 'storageNum',
            title: '库键码',
        }, {
            field: 'id',
            title: '操作',
            formatter: function (data, row, index) {
                var temp = ""
                if(type=="2") {
                    temp = "<a href='javascript:void(0)' onclick=edit('" + data + "','" + row.name + "','" + row.author
                        + "','" + row.code + "','" + row.address + "','" + row.storageNum + "')>编辑</a>  &nbsp;&nbsp;";
                    temp += "<a href='javascript:void(0)' onclick=dele('" + data + "')>删除</a>   &nbsp;&nbsp;";
                }
                if(type=="1" && row.statue == "1"){
                    temp += "<a href='javascript:void(0)' onclick=borrow('" + data + "')>借阅</a>   &nbsp;&nbsp;";
                }
                return temp;
            }
        }]
    });
}


function tableQueryParams(params) {
        var page = (params.offset / params.limit) + 1;
        var temp = {
            size: params.limit, // 页面大小
            page: page, // 第几页
            name: $("#search_name").val(),
            author: $("#search_author").val(),
            order: params.order,
            sort: params.sort
        };
        return temp;
}

function query() {
    $("#table").bootstrapTable('destroy');
    tableInit()
}
function openModal() {
    $('#myModal').modal('show');
    $('#myModalLabel').html("新增");
}


function edit(id, name,author, code,address,storageNum) {
    $("#userName").val(name);
    $("#userAuthor").val(author=="undefined"?"":author);
    $("#userCode").val(code=="undefined"?"":code);
    $("#userAdd").val(address=="undefined"?"":address);
    $("#userSto").val(storageNum=="undefined"?"":storageNum);
    $("#userId").val(id);

    $('#myModal').modal('show');
    $('#myModalLabel').html("编辑");
}

function borrow(id) {
    $("#bookId").val(id);
    $('#bModal').modal('show');
}
function saveOrUpdate() {
    var name = $("#userName").val();
    var author = $("#userAuthor").val();
    var code = $("#userCode").val();
    var address = $("#userAdd").val();
    var storageNum = $("#userSto").val();
    $("#m_btn").attr('disabled', "true");
    $.ajax({
        type: 'post',
        url: '/do/book/edit',
        data: {
            id: $("#userId").val(),
            name: name,
            author: author,
            code:code,
            address:address,
            storageNum:storageNum
        },
        dataType: "json",
        success: function (data) {
            $('#m_btn').removeAttr("disabled");
                $('#myModal').modal('toggle');
                $("#table").bootstrapTable('refresh');
                Ewin.alert(data.msg)
        }
    });
}

function dele(id) {
    Ewin.confirm({message: "确认要删除该图书吗？"}).on(function (e) {
        if (!e) {
            return;
        }
        $.ajax({
            type: 'delete',
            url: '/do/book/' + id,
            dataType: "json",
            success: function (data) {
                if (data.code == 1) {
                    $("#table").bootstrapTable('refresh');
                } else {
                    Ewin.alert(data.msg)
                }
            }
        });
    });
}
let type;
//当前用户的初始化
function initUser() {
    $.ajax({
        url:"/login/getSession",
        type:"post",
        data:{} ,
        dataType:"json",
        success:function (res) {
            if(res && res.type){
                type = res.type
                if(type=="1"){
                    $("#btn_add").hide();
                }
                tableInit();
            }
        }
    })
}

function borrowSub() {
        let rtime = $("#returnTime").val();
        if(!rtime){
            alert("请输入归还日期")
            return ;
        }
        $.ajax({
            type: 'post',
            url: '/do/book/borrow' ,
            data:{
                bookId:$("#bookId").val(),
                time:rtime
            },
            dataType: "json",
            success: function (data) {
                Ewin.alert(data.msg)
                $('#bModal').modal('toggle');
                if (data.code == 1) {
                    $("#table").bootstrapTable('refresh');
                }
            }
        });
}