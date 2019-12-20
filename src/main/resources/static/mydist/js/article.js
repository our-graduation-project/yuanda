$(function () {
    //错误信息隐藏
    $('.alert-danger').css("display", "none");

    $('#articleModal').on('hidden.bs.modal', function () {
        editor.html('请输入...');
    });

    // $('#articleModal').modal('hide');


    $("#jqGrid").jqGrid({
        //请求后台 JSON 数据的 URL
        url: 'user/list.do',
        //后台返回的数据格式
        datatype: "json",
        //列表信息，包括表头、宽度、是否显示、渲染参数等属性
        colModel: [
            {label: 'id', name: 'id', index: 'id', width: 50, hidden: true, key: true},
            {label: '标题', name: 'userName', index: 'userName', sortable: false, width: 80},
            {label: '作者', name: 'createTime', index: 'createTime', sortable: false, width: 80},
            {label:  '创建时间', name: 'userToken',index: 'userToken',sortable: false, width: 80},
            {label:  '最后更新时间', name: 'userToken',index: 'userToken',sortable: false, width: 80},
            {label:  '是否通过审核', name: 'userToken',index: 'userToken',sortable: false, width: 80}

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
            page: "data.currPage",       //数据页码
            total: "data.totalPage",     //数据总页码
            records: "data.totalCount",  //数据总记录数
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


//绑定modal上的保存按钮
$('#saveButton').click(function () {
    //验证数据
    if (validObject()) {
        //一切正常后发送网络请求
        //ajax
        var id = $("#articleId").val();
        var title = $("#articleName").val();
        var addName = $("#articleAuthor").val();
        var content = CKEDITOR.instances.editor.getData();
        console.log("获取的内容为：" + content)
        var data = {"articleTitle": title, "articleContent": content, "addName": addName};
        var url = 'articles/save.do';
        console.log("id:" + id);
        //id>0表示编辑操作
        if (id > 0) {
            data = {"id": id, "articleTitle": title, "articleContent": content, "addName": addName};
            url = 'articles/update.do';
        }
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
                checkResultCode(result.resultCode);
                if (result.resultCode == 200) {
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
    var articleName = $('#articleName').val();
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
    $('#articleName').val('');
    $('#articleAuthor').val('');
    $('#ariticleContent').val('');
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

