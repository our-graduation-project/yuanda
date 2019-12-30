
$(function () {
    // 替换 <textarea id="editor1">为CKEditor实例
    // 使用默认配置
    editorConfig = {
        customConfig: './samples/editConfig.js'
    };

    CKEDITOR.replace( 'editor', editorConfig);
    var thisUrl = location.search;
    if(thisUrl !=null&&thisUrl.indexOf("?") != -1){
        var id = thisUrl.substr(thisUrl.indexOf("=")+1);
    selectQuestionDetail(id);
    answers(id,"1","5","desc","create_time");
    }else {

        swal({
            title: "一定是哪里出了问题，待会儿再看吧",
        });
        window.location.href="types.html"

    }
});


function saveAnswer(isNoName) {
    var content =  CKEDITOR.instances.editor.getData();
    console.log(content)
    var data = {"content":content,"isNoName":isNoName};
    $.ajax({
        //请求方式
        type : "POST",
        //请求的媒体类型
        contentType: "application/json;charset=UTF-8",
        //请求地址
        url : "/yuanda/admin/saveAnswer",
        //数据，json字符串
        data : JSON.stringify(data),
        success: function (result) {

            if (result.result) {
                if(result.message=="操作失败"){
                    swal({
                        title: "你的提交有问题，再试试吧",
                    });
                    window.location.href="question.html";
                }
                var data = result.data;
                console.log(data);
                detail.addQuestionData(data);
            }
            else {
                swal({
                    title: "出错了，怎么想都不是我的错",
                });

            }
        },
        error: function () {
            swal({
                title: "出错了，一定是你写了不该写的东西",
            });
            window.location.href="question.html"
        }


    });
    
}


function selectQuestionDetail(questionId) {

        var data = {"questionId":questionId};
        $.ajax({
            //请求方式
            type : "POST",
            //请求的媒体类型
            contentType: "application/json;charset=UTF-8",
            //请求地址
            url : "/yuanda/admin/questionById",
            //数据，json字符串
            data : JSON.stringify(data),
            success: function (result) {

                if (result.result) {
                    if(result.message=="查询失败"){
                        swal({
                            title: "你要查看的内容飞走了，看看别的吧",
                        });
                        window.location.href="types.html";
                    }
                    var data = result.data;
                    console.log(data);
                    detail.addQuestionData(data);
                }
                else {
                    swal({
                        title: "出错了，怎么想都不是我的错",
                    });

                }
            },
            error: function () {
                swal({
                    title: "出错了，怎么想都不是我的错",
                });
                window.location.href="types.html"
            }


        });




};

function answers(questionId,page,limit,order,orderName){
    var data = {"questionId":questionId,"page":page,"limit":limit,"orderName":orderName,"order":order};
    $.ajax({
        //请求方式
        type : "POST",
        //请求的媒体类型
        contentType: "application/json;charset=UTF-8",
        //请求地址
        url : "/yuanda/admin/pageSelectAnswersByQuestionId",
        //数据，json字符串
        data : JSON.stringify(data),
        success: function (result) {

            if (result.result) {
                if(result.message=="查询失败"){
                    swal({
                        title: "你要查看的内容飞走了，看看别的吧",
                    });
                    window.location.href="types.html";
                }
                var data = result.data;
                console.log(data);
                answersData.addAnswerDatas(data);
            }
            else {
                swal({
                    title: "出错了，怎么想都不是我的错",
                });

            }
        },
        error: function () {
            swal({
                title: "出错了，怎么想都不是我的错",
            });
             window.location.href="types.html"
        }


    });
}









let detail = new Vue(
    {
        el: '#questiondetail',
        data:{

            questionData:{},
        },
        methods:{

            addQuestionData(data){
                this.questionData = data;
            },

        }
    }
);

let answersData = new Vue({
    el: '#answersData',
    data:{
        answerData:{},
    },
    methods:{
        addAnswerDatas(data){
            this.answerData = data;
        },


    }
});