$(function () {
    var thisUrl = location.search;
    if(thisUrl !=null&&thisUrl.indexOf("?") != -1) {
        var id = thisUrl.substr(thisUrl.indexOf("=") + 1);
        selectAnswerById(id);
    }else {
        swal({
            title: "一定是哪里出了问题，待会儿再看吧",
        });
        window.location.href="types.html"
    }
});

function selectAnswerById(answerId) {
    var data = {"answerId":answerId};
    $.ajax({
        //请求方式
        type : "POST",
        //请求的媒体类型
        contentType: "application/json;charset=UTF-8",
        //请求地址
        url : "/yuanda/admin/selectAnswerById",
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
                thisAnswer.addAnswer(data);
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


let thisAnswer = new Vue({
    el: '#answerDetail',
        data:{
        answerData:{},
        },
    methods:{

        addAnswer(data){
            this.answerData = data;
        },
    }

});