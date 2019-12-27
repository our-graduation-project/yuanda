$(function () {
    //错误信息隐藏
    $('.alert-danger').css("display", "none");

    $('#articleModal').on('hidden.bs.modal', function () {
        editor.html('请输入...');
    });

    // $('#articleModal').modal('hide');


    $("#jqGrid").jqGrid({
        //请求后台 JSON 数据的 URL
        url: 'http://localhost:8080/yuanda/admins/loadAdmin',
        //后台返回的数据格式
        datatype: "json",
        //列表信息，包括表头、宽度、是否显示、渲染参数等属性
        colModel: [
            {label: 'id', name: 'adminId', index: 'adminId', width: 50, hidden: true, key: true},
            {label: '管理员名', name: 'adminName', index: 'adminName', sortable: false, width: 80},
            {label: '电话', name: 'phone', index: 'phone', sortable: false, width: 80},
            {label:  '邮箱', name: 'email',index: 'email',sortable: false, width: 80},
            {label:  '性别', name: 'sex',index: 'sex',sortable: false, width: 80,formatter: SexFormatter},
            {label:  '权限', name: 'right',index: 'right',sortable: false, width: 80,formatter: RightFormatter},
            {label:  '状态', name: 'isDeleted',index: 'isDeleted',sortable: false, width: 80,formatter: checkFormatter}
        ],
        //表格高度，可自行调节
        height: 485,
        //默认一页显示多少条数据，可自行调节
        rowNum: 10,
        //翻页控制条中，每页显示记录数可选集合
        rowList: [10, 30, 50],
        //主题，这里选用的是 Bootstrap 主题
        styleUI: 'Bootstrap',
        //数据加载时显示的提示信息
        loadtext: '信息读取中...',
        //是否显示行号，默认值是 false，不显示
        rownumbers: true,
        //行号列的宽度
        rownumWidth: 20,
        //宽度是否自适应
        autowidth: true,
        //是否可以多选
        multiselect: true,
        //分页信息 DOM
        pager: "#jqGridPager",
        pgbuttons:true,

        //jsonReader 对象定义了如何对后端返回的 JSON 数据进行解析，
        jsonReader: {
            root: "data.list",           //数据列表模型
            page: "data.pageNum",       //数据页码
            total: "data.pages",     //数据总页码
            records: "data.total",  //数据总记录数
        },
        // 向后台请求的参数
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        // 数据加载完成并且 DOM 创建完毕之后的回调函数
        gridComplete: function () {
            //隐藏 Grid 底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });

});

/**
 * 重置
 */
function reset() {
    //隐藏错误提示框
    $('.alert-danger').css("display", "none");
    //清空数据
    $('#addsex').val('');
    $('#addadminName').val('');
    $('#addadminPassword').val('');
    $('#addright').val('');
    $('#addphone').val('');
    $('#addisDeleted').val('');
    $('#addemail').val('');
}

/**
 * 表单中性别的格式
 * @param cellvalue
 * @returns {string}
 */
function SexFormatter(cellvalue) {
    if(cellvalue == false){
        return "男";
    }else if(cellvalue == true) {
        return "女";
    }
}

/**
 * 表单中权限的格式
 * @param cellvalue
 * @returns {string}
 */
function RightFormatter(cellvalue) {
    if(cellvalue == "0"){
        return "一般管理员";
    }else if(cellvalue == "1") {
        return "超级管理员";
    }
}

/**
 * 表单中删除的格式
 * @param cellvalue
 * @returns {string}
 */
function checkFormatter(cellvalue) {
    if(cellvalue == false){
        return "存在";
    }else if(cellvalue == true) {
        return "禁用";
    }
}

/**
 * 删除的点击事件
 * @param status
 * @param path
 */
function changeDeleted(status,path){
    var flag = false;
    if(status == false){
        swalAlterForCheck(status);
    }

}

/**
 * list为要改变的状态的id列表，title为弹框的标题，status为状态码
 * 相应跳出的提示框
 * @param status
 */
function swalAlterForCheck(list,title,status){

    //如果status != 0
    if(status == false && status == true){
        swal(title, {
            buttons: {
                fuck: {
                    text:"取消",
                    value:"cancel"
                },
                catch: {
                    text: "确认",
                    value: "ensure",
                },
            },
        }).then((value) => {
            switch (value) {
            case "cancel":
                swal("取消成功");
                break;
            case "ensure":
                if(status ==false){
                    status = true;
                }else{
                    status = false;
                }
                changeIsDeleted(list,status);
                break;
            }
        });
    }

    if(status != false || status != true){
        swal(title, {
            buttons: {
                fuck: {
                    text:"存在",
                    value:"cancel"
                },
                catch: {
                    text: "禁用",
                    value: "ensure",
                },
            },
        })
            .then((value) => {
            switch (value) {
            case "cancel":
                status = false
                changeAdminIsDeleted(list,status);
                break;
            case "ensure":
                status = true;
                changeAdminIsDeleted(list,status);
                break;
            }
        });
    }

}

/**
 * 改变状态码
 * @param list
 * @param status
 */
function changeAdminIsDeleted(list,status) {
    console.log("要将状态改为：" + status);
    var data = {"list": list, "status": status};
    var url = 'http://localhost:8080/yuanda/admins/changeIsDeleted';
    $.ajax({
        type: 'POST',//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: url,//url
        contentType: "application/json; charset=utf-8",
        beforeSend: function (request) {
            //设置header值
            request.setRequestHeader("token", getCookie("token"));
        },
        data: JSON.stringify(data),
        success: function (result) {
            // checkResultCode(result.code);
            if (result.result) {
                swal({
                    title: "操作成功",
                    icon: "success",
                    button: "确定",
                });
                reload();
            }
            else {
                swal({
                    title: "操作失败",
                    icon: "error",
                    button: "确定",
                });
                reload();
            };
        },
        error: function () {
            alert("操作失败");
        }
    });
}

/**
 * 管理员信息
 */
function adminDetails(){
    var adminId = getSelectedRow();
    if(!adminId){
        return;
    }
    console.log("id-" + adminId);

    //请求数据
    $.get("/yuanda/admins/info/" + adminId, function (r) {
        if (r.code == 200 && r.data != null) {
            $('#modalEditLabel').text("管理员详情");
            //填充数据至modal
            $('#editadminId').val(r.data.adminId);
            $('#editadminName').val(r.data.adminName);
            $('#editphone').val(r.data.phone);
            $("input[name='sex'][value="+r.data.sex+"]").attr("checked",true);
            $('#editemail').val(r.data.email);
            $("input[name='adminright'][value="+r.data.right+"]").attr("checked",true);
            $("input[name='adminIsDeleted'][value="+r.data.isDeleted+"]").attr("checked",true);
        }else {
            return false
        }
    });
    //显示modal
    $('#modalEdit').modal('show');
}

/**
 * 删除管理员
 */
function checkAdminIsDeleted() {
    //获取选中的行
    var rows = getSelectedRows();
    var list = [];
    var firstValue = $("#jqGrid").getCell(rows[0],'isDeleted');
    for (var i = 0; i < rows.length; i++) {
        //拿到某一行的数据
        list.push($("#jqGrid").getCell(rows[i],'adminId'));
        console.log(list[0]);
        if(i > 0 && firstValue != $("#jqGrid").getCell(rows[i],'isDeleted')){
            alert("请选择审核状态一样的");
            return;
        }
    }

    var title;
    var status;
    if(firstValue == "存在"){
        title = "是否删除管理员";
        status = false;
    }else if(firstValue == "禁用") {
        title = "是否还原管理员";
        status = true;
    }
    console.log( "标题：" + title);
    swalAlterForCheck(list,title,status);
}


/**
 * 管理员增加
 * @returns {boolean}
 */
function addAdmin() {
    var adminName = $("#addadminName").val();
    var adminPassword = $("#addadminPassword").val();
    var right =$('input:radio[name="right"]:checked').val();
    var sex =$('input:radio[name="adminSex"]:checked').val();
    var email = $("#addemail").val();
    var phone = $("#addphone").val();
    var isDeleted =$('input:radio[name="isdeleted"]:checked').val();
    console.log(adminName+"----"+adminPassword+"----"+right+"----"+sex+"----"
        +email+"----"+phone+"----"+isDeleted);
    if(adminName == null || adminName == "" || adminName == undefined){
        $("#error").css("display","block");
        $("#error").html("管理员名不能为空");
        return false;
    }
    if(adminPassword == null || adminPassword == "" || adminPassword == undefined){
        $("#error").css("display","block");
        $("#error").html("管理员密码不能为空");
        return false;
    }
    if(right==null|| right == ""|| right == undefined){
        $("#error").css("display","block");
        $("#error").html("权限不能为空");
        return false;
    }
    if(sex == null || sex == "" || sex == undefined){
        $("#error").css("display","block");
        $("#error").html("性别不能为空");
        return false;
    }
    if(email==null || email == "" || email == undefined){
        $("#error").css("display","block");
        $("#error").html("邮箱不能为空");
        return false;
    }
    if(phone == null || phone == "" || phone == undefined){
        $("#error").css("display","block");
        $("#error").html("电话不能为空");
        return false;
    }
    if(isDeleted == null || isDeleted == "" || isDeleted == undefined){
        $("#error").css("display","block");
        $("#error").html("状态不能为空");
        return false;
    }
    var data = {"adminName" : adminName,"adminPassword" : adminPassword,"right":right,
        "sex":sex,"email":email ,"phone":phone,"isDeleted":isDeleted};
    $.ajax({
        //请求方式
        type : "POST",
        //请求的媒体类型
        contentType: "application/json;charset=UTF-8",
        //请求地址
        url : "http://localhost:8080/yuanda/admins/addAdmin",
        //数据，json字符串
        data : JSON.stringify(data),
        success: function (result) {
            if (result.result) {
                alert("添加成功");
                window.location.href="http://localhost:8080/yuanda/admin/admin.html";
            }
            else {
                $("#error").html(result.message);
                console.log(result.message+"--"+result.result);
                alert("添加失败");
            }
        },
        error: function () {
            alert("操作失败");
        }

    })
}

/**
 * 管理员登录
 * @returns {boolean}
 */
function loginAdmin(){
    var email = $('#email').val();
    var adminPassword = $('#adminPassword').val();

    console.log(email + "----" + adminPassword);
    var data = {"email": email,"adminPassword":adminPassword};

    $.ajax({
        //请求方式
        type : "POST",
        //请求的媒体类型
        contentType: "application/json;charset=UTF-8",
        //请求地址
        url : "http://localhost:8080/yuanda/admins/loginAdmin",
        //数据，json字符串
        data : JSON.stringify(data),
        success: function (result) {
            if (result.result) {
                alert("登录成功");
                window.location.href = "http://localhost:8080/yuanda/admin/user.html";
            }
            else {
                console.log(result.message+"--"+result.result);
            }
        },
        error: function () {
            alert("操作失败");
        }

    })

}





/**
 * 修改管理员
 * @returns {boolean}
 */
function editAdmin(){
    console.log("进入");
    var adminId = $("#editadminId").val();
    var adminName = $("#editadminName").val();
    var adminPassword= $("#editadminPassword").val();
    var right =$('input:radio[name="adminright"]:checked').val();
    var sex = $('input:radio[name="sex"]:checked').val();
    var email = $("#editemail").val();
    var phone = $("#editphone").val();
    var isDeleted = $('input:radio[name="adminIsDeleted"]:checked').val();
    console.log(adminId+"----"+adminName+"----"+adminPassword+"----"+right+"----"+sex+"----"
        +email+"----"+phone+"----"+isDeleted);
    if(adminName == null || adminName == "" || adminName.trim()==""){
        $("#error").css("display","block");
        $("#error").html("用户名不能为空");
        return false;
    }
    if(adminPassword == null || adminPassword == "" || adminPassword.trim()==""){
        $("#error").css("display","block");
        $("#error").html("用户密码不能为空");
        return false;
    }
    if(right==null|| right == ""|| right.trim()==""){
        $("#error").css("display","block");
        $("#error").html("用户头像不能为空");
        return false;
    }
    if(sex == null || sex == "" || sex.trim()==""){
        $("#error").css("display","block");
        $("#error").html("性别不能为空");
        return false;
    }
    if(email==null || email == "" || email.trim()==""){
        $("#error").css("display","block");
        $("#error").html("邮箱不能为空");
        return false;
    }
    if(phone == null || phone == "" || phone.trim()==""){
        $("#error").css("display","block");
        $("#error").html("电话不能为空");
        return false;
    }
    if(isDeleted == null || isDeleted == "" || isDeleted.trim()==""){
        $("#error").css("display","block");
        $("#error").html("状态不能为空");
        return false;
    }
    console.log("sex:" + sex);
    var data = {"adminId" : adminId,"adminName" : adminName,"adminPassword" : adminPassword,"right":right,
        "sex":sex,"email":email ,"phone":phone,"isDeleted":isDeleted};
    $.ajax({
        //请求方式
        type : "POST",
        //请求的媒体类型
        contentType: "application/json;charset=UTF-8",
        //请求地址
        url : "http://localhost:8080/yuanda/admins/editAdmin",
        //数据，json字符串
        data : JSON.stringify(data),
        success: function (result) {
            if (result.result) {
                alert("修改成功");
                window.location.href="http://localhost:8080/yuanda/admin/admin.html";
            }
            else {
                console.log(result.message+"--"+result.result);
                alert("保存失败");
            }
        },
        error: function () {
            alert("操作失败");
        }

    })
}

/**
 * jqGrid重新加载
 */
function reload() {
    reset();
    var page = $("#jqGrid").jqGrid('getGridParam', 'page');
    $("#jqGrid").jqGrid('setGridParam', {
        page: page
    }).trigger("reloadGrid");
}