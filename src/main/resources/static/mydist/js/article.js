var editorConfig;
$(function () {
    //错误信息隐藏
    $('.alert-danger').css("display", "none");

    // 替换 <textarea id="editor1">为CKEditor实例
    // 使用默认配置
    editorConfig = {
        customConfig: './samples/editConfig.js'
    };

    CKEDITOR.replace( 'editor', editorConfig);

    // $('#articleModal').modal('hide');


    $("#jqGrid").jqGrid({
        //请求后台 JSON 数据的 URL
        url: 'articleList',
        //后台返回的数据格式
        datatype: "json",
        //列表信息，包括表头、宽度、是否显示、渲染参数等属性
        colModel: [
            {label: 'id', name: 'articleId', index: 'articleId', width: 10, key: true},
            {label: '标题', name: 'articleTitle', index: 'articleTitle', sortable: false, width: 80},
            {label: '作者', name: 'authorName', index: 'authorName', sortable: false, width: 50},
            {label: '创建时间', name: 'createTime', index: 'createTime', sortable: false, width: 80,formatter: timeFormatter},
            {label:  '更新时间', name: 'updateTime',index: 'updateTime',sortable: false, width: 80,formatter: timeFormatter},
            {label:  '是否通过审核', name: 'checkStatus',index: 'checkStatus',sortable: false, width: 80,formatter: checkFormatter}
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
        rownumbers: false,
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
 * 改变日期格式
 * @param cellvalue
 * @returns {string}
 */
function timeFormatter(cellvalue) {

    var da = new Date(cellvalue);
    var year = da.getFullYear()+'年';
    var month = da.getMonth()+1+'月';
    var date = da.getDate()+'日';
    var hourse = da.getHours()+'时';
    return [year,month,date,hourse].join('-');

}

/**
 * 表单中审核的格式
 * @param cellvalue
 * @returns {string}
 */
function checkFormatter(cellvalue) {
    if(cellvalue == "0"){
        return "暂未审核";
    }else if(cellvalue == "1"){
        return "审核通过";
    }else{
        return "审核未通过";
    }
}

/**
 * 审核的点击事件
 * @param status
 * @param path
 */
function changeStauts(status,path){
    var flag = false;
    if(status == "0"){
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
    if(status != 0){
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
                    if(status == 1){
                        status = 2;
                    }else{
                        status = 1;
                    }
                    changeStatus(list,status);
                    break;
                }
            });
        }

    if(status == 0){
        swal(title, {
            buttons: {
                fuck: {
                    text:"审核通过",
                    value:"cancel"
                },
                catch: {
                    text: "审核未通过",
                    value: "ensure",
                },
            },
        })
        .then((value) => {
        switch (value) {
        case "cancel":
            status = 1
            changeStatus(list,status);
            break;
        case "ensure":
            status = 2;
            changeStatus(list,status);
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
function changeStatus(list,status) {
    console.log("要将状态改为：" + status);
    var data = {"list": list, "status": status};
    var url = '/yuanda/articles/changeStatus';
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

function articleDetails(){
    var id = getSelectedRow();
    if(!id){
        return;
    }
    console.log("id-" + id);

    //请求数据
    $.get("/yuanda/articles/info/" + id, function (r) {
        if (r.code == 200 && r.data != null) {
            $('#articleModalLabel').text("文章详情");
            //填充数据至modal
            $('#articleId').val(r.data.id);
            $('#articleTitle').val(r.data.articleTitle);
            $('#articleAuthor').val(r.data.authorName);
            CKEDITOR.instances.editor.setData(r.data.articleContent);
            $('#articleTitle').attr("readonly",true);
            $('#articleAuthor').attr("readonly",true);
            $('#editor').attr("readonly",true);
        }else {
            return false
        }
    });
    //显示modal
    $('#articleModal').modal('show');
}

/**
 * 审核文章
 */
function checkArticle() {
    //获取选中的行
    var rows = getSelectedRows();
    var list = [];
    var firstValue = $("#jqGrid").getCell(rows[0],'checkStatus');
    for (var i = 0; i < rows.length; i++) {
        //拿到某一行的数据
        list.push($("#jqGrid").getCell(rows[i],'articleId'));
        console.log(list[0]);
        if(i > 0 && firstValue != $("#jqGrid").getCell(rows[i],'checkStatus')){
            alert("请选择审核状态一样的");
            return;
        }
    }

    var title;
    var status;
    if(firstValue == "暂未审核"){
        title = "是否通过审核";
        status = 0;
    }else if(firstValue == "审核通过"){
        title = "是否取消通过审核";
        status = 1;
    }else {
        title = "是否设为通过审核";
        status = 2;
    }
    console.log( "标题：" + title);
    swalAlterForCheck(list,title,status);
}

/**
 * 增加文本
 */
function articleAdd() {
    reset();
    $('#articleModalLabel').text("添加文章");
    //显示modal
    $('#articleModal').modal('show');
}

// 绑定modal上的保存按钮
$('#saveButton').click(function () {
    //验证数据
    if (validObject()) {
        //一切正常后发送网络请求
        //ajax
        var title = $("#articleTitle").val();
        var addName = $("#articleAuthor").val();
        var content = CKEDITOR.instances.editor.getData();
        console.log("获取的内容为：" + content)
        var data = {"articleTitle": title, "articleContent": content, "authorName": addName};
        var url = '/yuanda/articles/save';
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
                    $('#articleModal').modal('hide');
                    alert("保存成功");
                    //reload
                    reload();
                }
                else {
                    $('#articleModal').modal('hide');
                    alert("保存失败");
                };
            },
            error: function () {
                alert("操作失败");
            }
        });

    }
});

/**
 * 数据验证
 */
function validObject() {
    var articleName = $('#articleTitle').val();
    if (isNull(articleName)) {
        showErrorInfo("标题不能为空!");
        return false;
    }
    if (!validLength(articleName, 120)) {
        showErrorInfo("标题字符不能大于120!");
        return false;
    }
    var articleAuthor = $('#articleAuthor').val();
    if (isNull(articleAuthor)) {
        showErrorInfo("作者不能为空!");
        return false;
    }
    if (!validLength(articleAuthor, 10)) {
        showErrorInfo("作者字符不能大于10!");
        return false;
    }
    var ariticleContent = CKEDITOR.instances.editor.getData();
    if (isNull(ariticleContent) || ariticleContent == '请输入...') {
        showErrorInfo("内容不能为空!");
        return false;
    }
    if (!validLength(ariticleContent, 8000)) {
        showErrorInfo("内容字符不能大于8000!");
        return false;
    }
    return true;
}

/**
 * 重置
 */
function reset() {
    //隐藏错误提示框
    $('.alert-danger').css("display", "none");
    //清空数据
    $('#articleId').val(0);
    $('#articleAuthor').val('');
    $('#articleAuthor').removeAttr("readonly");
    $('#articleTitle').val('');
    $('#articleTitle').removeAttr("readonly");
    CKEDITOR.instances.editor.setData( '' );
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

