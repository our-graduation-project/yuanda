$(function () {
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