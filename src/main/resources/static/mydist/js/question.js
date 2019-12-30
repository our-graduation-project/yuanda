$(function () {
    //错误信息隐藏
    $('.alert-danger').css("display", "none");

    $('#articleModal').on('hidden.bs.modal', function () {
        editor.html('请输入...');
    });

    // $('#articleModal').modal('hide');


    $("#jqGrid").jqGrid({
        //请求后台 JSON 数据的 URL
        url: '/yuanda/admin/selectQuestionList',
        mtype: 'POST',
        //后台返回的数据格式
        datatype: "json",

        //列表信息，包括表头、宽度、是否显示、渲染参数等属性
        colModel: [
            {label: 'id', name: 'questionId', index: 'questionId', width: 30, hidden: true, key: true},
            {label: '问题标题', name: 'questionTitle', index: 'questionTitle', sortable: false, width: 80},
            {label: '详细内容', name: 'questionDescript', index: 'questionDescript',hidden: true,sortable: false, width: 100},
            {label:  '回答数量', name: 'answerNumber',index: 'answerNumber',sortable: false, width: 40},
            {label:  '是否通过审核', name: 'checkStatusStr',index: 'checkStatusStr',sortable: false, width: 60},
            {label:  '热度', name: 'hot',index: 'hot',sortable: false, width: 40},
            {label:  '提问时间', name: 'createTimeStr',index: 'createTimeStr',sortable: false, width: 100},
            {label:  '提问人id', name: 'authorId',index: 'authorId',sortable: false,hidden: true, width: 40},
            {label:  '浏览次数', name: 'clickNumber',index: 'clickNumber',sortable: false, hidden: true,width: 40},
            {label:  '关注数量', name: 'followNumber',index: 'followNumber',sortable: false,hidden: true, width: 40},
            {label:  '图片', name: 'authorPicture',index: 'authorPicture',sortable: false,hidden: true, width: 40},
            {label:  '提问人', name: 'author',index: 'author',sortable: false, width: 40}

        ],
        //表格高度，可自行调节
        height: 560,
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
        rownumWidth: 30,
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
        // onSelectRow : function(ids) {
        //     console.log(ids);
        //     if(ids != null){
        //         var url = '/answer.html?questionId='+ids;
        //         window.location = url;
        //     }
        // }
    });

});

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


// 审核

function updateCheckStatus(status) {
    var rows=[];
        rows= getSelectedRows();

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
    var data = {"questionIds":rows,"state":status};
    $.ajax({
        //请求方式
        type : "POST",
        //请求的媒体类型
        contentType: "application/json;charset=UTF-8",
        //请求地址
        url : "updateCheckStatus",
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

function deleteQuestion() {
    var rows=[];
    rows= getSelectedRows();
    if(rows == null){
        swal({
            title: "请选择要删除的数据",
        });
    }
    var title = "你确定要删除这"+rows.length+"个问题吗？";
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
                deleteQuestions();
                break;
        }
    });
}

function deleteQuestions() {
    var rows=[];
    rows= getSelectedRows();

    if(rows != null){
        var data = {"questionIds":rows};
        $.ajax({
            //请求方式
            type : "POST",
            //请求的媒体类型
            contentType: "application/json;charset=UTF-8",
            //请求地址
            url : "deleteQuestion",
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
    }else {
        swal({
            title: "请选择要删除的数据",
        });
    }

    $('#questionModal').modal('hide');
    reset();
    reload();
}

function details() {
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
    var data = {"questionId":rowData.questionId,"questionTitle":rowData.questionTitle,"questionDescript":rowData.questionDescript,
    "answerNumber":rowData.answerNumber,"checkStatusStr":rowData.checkStatusStr,"hot":rowData.hot,"createTimeStr":rowData.createTimeStr,
    "authorId":rowData.authorId,"clickNumber":rowData.clickNumber,"followNumber":rowData.followNumber,"author":rowData.author,"authorPicture":pic};

    vm.addDatas(data);
    //显示modal
    $('#questionModal').modal('show');
    
}

function answerByQuestion(){
    var rows=[];
    rows= getSelectedRows();
    if(rows.length>1){
        swal({
            title: "只能选择一个数据",
        });
        return false;
    }
    var rowData = $("#jqGrid").jqGrid('getRowData',rows[0]);
    console.log("answer.html?questionId="+rowData.questionId);
    window.location = "answer.html?questionId="+rowData.questionId;
}

let vm = new Vue(
    {
        el: '#questionData',
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