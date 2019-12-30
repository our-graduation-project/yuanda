$(function (questionId) {
    var url;
    var thisUrl = location.search;
    if(thisUrl !=null&&thisUrl.indexOf("?") != -1){
        var str = thisUrl.substr(thisUrl.indexOf("=")+1);
        console.log(str);
        url = '/yuanda/admin/selectAnswersByQuestionId?questionId='+str;
    }else {
        url = '/yuanda/admin/selectAnswerList';
    }
    selectAnswer(url);
});

function selectAnswer(url) {
    //错误信息隐藏
    $('.alert-danger').css("display", "none");

    $('#articleModal').on('hidden.bs.modal', function () {
        editor.html('请输入...');
    });

    // $('#articleModal').modal('hide');


    $("#jqGrid").jqGrid({
        //请求后台 JSON 数据的 URL
        url: url,
        //后台返回的数据格式
        datatype: "json",
        //列表信息，包括表头、宽度、是否显示、渲染参数等属性

    colModel: [
            {label: 'id', name: 'answerId', index: 'answerId', width: 80, hidden: true, key: true},
            {label: '回答者名称', name: 'autherName', index: 'autherName', sortable: false, width: 80},
            {label: '粗略内容', name: 'roughAnswerContent', index: 'roughAnswerContent', sortable: false, width: 100},
            {label: '问题标题', name: 'questionTitile', index: 'questionTitile', sortable: false, width: 100},
            {label:  '创建时间', name: 'createTimeStr',index: 'createTimeStr',sortable: false, width: 140},
            {label:  '最后更新时间', name: 'updateTimeStr',index: 'updateTimeStr',sortable: false, width: 140},
            {label:  '是否通过审核', name: 'checkStatusStr',index: 'checkStatusStr',sortable: false, width: 80},

            {label: '回答者名称', name: 'answerContent', index: 'answerContent', hidden: true, sortable: false, width: 80},
            {label: '同意数', name: 'agreeNumber', index: 'agreeNumber', hidden: true, sortable: false, width: 80},
            {label: '不同意数', name: 'disagreeNumber', index: 'disagreeNumber',  hidden: true,sortable: false, width: 80},
            {label: '评论数', name: 'commentNumber', index: 'commentNumber', hidden: true, sortable: false, width: 80},
            {label: '浏览次数', name: 'clickNumber', index: 'clickNumber', hidden: true, sortable: false, width: 80},
            {label: '是否匿名', name: 'isNoNameStr', index: 'isNoNameStr', hidden: true, sortable: false, width: 80},
            {label: '回答者id', name: 'authorId', index: 'authorId', hidden: true, sortable: false, width: 80},
            {label: '作者头像', name: 'authorPicture', index: 'authorPicture', hidden: true, sortable: false, width: 80},
            {label: '问题id', name: 'questionId', index: 'questionId', hidden: true, sortable: false, width: 80},
        {label: '粗略内容', name: 'answerContent', index: 'answerContent', sortable: false, width: 100},


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

}

// 审核

function updateAnswerStatus(status) {
    var rows=[];
    rows= getSelectedRows();
    console.log(rows);
    if(rows != null&&rows.length>0){
        for (var i = 0; i < rows.length; i++) {
            var rowData = $("#jqGrid").jqGrid('getRowData',rows[i]);
            var state = rowData.checkStatusStr;
            if((state == "审核未通过"&&status==2)||state == "审核通过"){
                swal({
                    title: "选择字段中有已经审核完成的数据",
                });

                return false;
            }
        }
        var data = {"answerIds":rows,"state":status};
        $.ajax({
            //请求方式
            type : "POST",
            //请求的媒体类型
            contentType: "application/json;charset=UTF-8",
            //请求地址
            url : "updateAnswerCheckStatus",
            //数据，json字符串
            data : JSON.stringify(data),
            success: function (result) {

                if (result.result) {
                    swal({
                        icon: "success",
                    });

                }
                else {

                    swal({
                        title: "出错了，请重新审核",
                    });
                }
            },
            error: function () {
                swal({
                    title: "出错了，请重新审核",
                });
            }


        })


        $('#questionModal').modal('hide');
        reload();
    }



}


function deleteAnswer() {
    var rows=[];
    rows= getSelectedRows();
    if(rows == null){
    swal({
        title: "请选择要删除的数据",
    });
}
    var title = "你确定要删除这"+rows.length+"条回答吗？";
    console.log(title);
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
                return false;
            case "ensure":
                deleteAnswers();
                break;
        }
    });
}


/**
 * 删除操作
 */
function deleteAnswers() {
    var rows=[];
    rows= getSelectedRows();

        console.log(rows);
        var data = {"answerIds":rows};
        $.ajax({
            //请求方式
            type : "POST",
            //请求的媒体类型
            contentType: "application/json;charset=UTF-8",
            //请求地址
            url : "deleteAnswer",
            //数据，json字符串
            data : JSON.stringify(data),
            success: function (result) {

                if (result.result) {
                    swal({
                        icon: "success",
                    });

                }
                else {
                    swal({
                        title: "出错了，请重新删除",
                    });

                }
            },
            error: function () {
                swal({
                    title: "出错了，请重新删除",
                });
            }


        })


    $('#answerModal').modal('hide');
    reset();
    reload();
}

function detailsAnswer() {
    reset();
    var rows=[];
    rows= getSelectedRows();
    if(rows.length>1){
        swal({
            title: "只能选择一个数据",
        });
        return false;
    }
    var rowData = $("#jqGrid").jqGrid('getRowData',rows[0]);
    var pic=rowData.authorPicture;
    if(pic==null||pic==""){
        pic="spare.jpg";
    }
    var data = {"answerId":rowData.answerId,"autherName":rowData.autherName,"roughAnswerContent":rowData.roughAnswerContent,
        "questionTitile":rowData.questionTitile,"createTimeStr":rowData.createTimeStr,"updateTimeStr":rowData.updateTimeStr,"checkStatusStr":rowData.checkStatusStr,
        "answerContent":rowData.answerContent,"agreeNumber":rowData.agreeNumber,"disagreeNumber":rowData.disagreeNumber,"commentNumber":rowData.commentNumber,"clickNumber":rowData.clickNumber,
        "isNoNameStr":rowData.isNoNameStr,"authorId":rowData.authorId,"authorPicture":pic,"questionId":rowData.questionId,"answerContent":rowData.answerContent};
    vm.addDatas(data);
    //显示modal
    $('#answerModal').modal('show');

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

let vm = new Vue(
    {
        el: '#answerData',
        data:{
            pro:{},
        },
        methods:{
            addDatas(data){
                this.pro = data;
                console.log(this.pro);
            }
        }
    }
);