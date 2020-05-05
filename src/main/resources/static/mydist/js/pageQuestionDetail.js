
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


    var data = {"content":content,"isNoName":isNoName,"questionId":detail.questionData.questionId,"question":detail.questionData.questionTitle};
    $.ajax({
        //请求方式
        type : "POST",
        //请求的媒体类型
        contentType: "application/json;charset=UTF-8",
        beforeSend : function(request){
          request.setRequestHeader("token",getCookie("token"));
          console.log("token" + getCookie("token"));
        },
        //请求地址
        url : "/yuanda/saveAnswer",
        //数据，json字符串
        data : JSON.stringify(data),
        success: function (result) {

            if (result.result) {
                if(result.message=="操作失败"){
                    swal({
                        title: "你的提交有问题，待会儿再试试吧",
                    });
                     }else {
                    swal({
                        title: "操作成功",
                    });
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
            // window.location.href="question.html"
        }


    });
    window.location.href="question.html?questionId="+detail.questionData.questionId;
    //answers(detail.questionData.questionId,1,5,"desc","create_time");


}


function selectQuestionDetail(questionId) {

        var data = {"questionId":questionId};
        $.ajax({
            //请求方式
            type : "POST",
            //请求的媒体类型
            contentType: "application/json;charset=UTF-8",
            //请求地址
            url : "/yuanda/questionById",
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
        url : "/yuanda/pageSelectAnswersByQuestionId",
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
                if(questionData.authorPicture == null||questionData.authorPicture==""){
                    questionData.authorPicture="plugins/blog/images/classification.jpg";
                }
                if(questionData.authorPicture == null||questionData.authorPicture==""){
                    questionData.authorPicture="plugins/blog/images/classification.jpg";
                }
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
        toAnswerDetail(index){
            let id = answersData.answerData.list[index].answerId;
            // console.log(id);
            window.location.href="answerDetail.html?answerId="+id;
        },


    }
});