var editorConfig;
$(function () {
    //错误信息隐藏
    $('.alert-danger').css("display", "none");

    // 替换 <textarea id="editor1">为CKEditor实例
    // 使用默认配置
    editorConfig = {
        customConfig: './samples/editConfig.js'
    };


    // $('#articleModal').modal('hide');


    $("#jqGrid").jqGrid({
        //请求后台 JSON 数据的 URL
        url: 'labelList',
        //后台返回的数据格式
        datatype: "json",
        //列表信息，包括表头、宽度、是否显示、渲染参数等属性
        colModel: [
            {label: 'id', name: 'labelId', index: 'labelId', width: 10, key: true},
            {label: '标题名', name: 'labelName', index: 'labelName', sortable: false, width: 80}
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


// function labelAdd(){
//     var id = getSelectedRow();
//     if(!id){
//         return;
//     }
//     console.log("id-" + id);
//
//     //请求数据
//     $.get("/yuanda/articles/info/" + id, function (r) {
//         if (r.code == 200 && r.data != null) {
//             $('#articleModalLabel').text("文章详情");
//             //填充数据至modal
//             $('#articleId').val(r.data.id);
//             $('#articleTitle').val(r.data.articleTitle);
//             $('#articleAuthor').val(r.data.authorName);
//             CKEDITOR.instances.editor.setData(r.data.articleContent);
//             $('#articleTitle').attr("readonly",true);
//             $('#articleAuthor').attr("readonly",true);
//             $('#editor').attr("readonly",true);
//         }else {
//             return false
//         }
//     });
//     //显示modal
//     $('#articleModal').modal('show');
// }


/**
 * 增加文本
 */
function labelAdd() {
    reset();
    //显示modal
    $('#labelModal').modal('show');
}

function labelDelete(){
    var id = getSelectedRow();
    var labelName = $("#jqGrid").getCell(id,'labelName');
    swal("确认删除", {
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
            $.get("/yuanda/labelDelete/" + id, function (r) {
            if (r.result) {
                swal({
                    title: "删除成功",
                    icon: "success",
                    button: "确定",
                });
            }else {
                swal({
                    title: "删除失败",
                    icon: "error",
                    button: "确定",
                });
            }
        });
            break;
        }
    });
}

// 绑定modal上的保存按钮
$('#saveButton').click(function () {
    //验证数据
    if (validObject()) {
        //一切正常后发送网络请求
        //ajax
        var labelName = $("#labelName").val();

        console.log("获取的内容为：" + labelName)
        var data = {"labelName": labelName};
        var url = '/yuanda/label/save';
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

                if (result.result) {
                    $('#labelModal').modal('hide');
                    alert("保存成功");
                    //reload
                    reload();
                }
                else {
                    $('#labelModal').modal('hide');
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
    var labelName = $('#labelName').val();
    if (isNull(labelName)) {
        showErrorInfo("标签名不能为空!");
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
    $('#labelName').val('');
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

