$(
    function () {
        selectHotQuestion(1,5,"desc",null);
    }
);


function selectHotQuestionByType(label) {
    var page = 1;
    selectHotQuestion(page,5,"desc",label);

}


function selectHotQuestion(page,limit,order,label){

    if(label == null||label == ""){
        var url = "admin/pageSelectQuestions";
        var data = {"page":page,"limit":limit,"orderName":"hot","order":order};
    }else {
        hot.addLabel(label);
        var url = "admin/pageSelectQuestionsByType";
        var data = {"page":page,"limit":limit,"orderName":"hot","order":order,"label":label};
    }

    console.log(data);
    $.ajax({
        //请求方式
        type : "POST",
        //请求的媒体类型
        contentType: "application/json;charset=UTF-8",
        //请求地址
        url : url,
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
                hot.addDatas(data);
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
        }


    });


}

let hot = new Vue(
    {
        el: '#hotQuestions',
        data:{
            pro:{},
            label:{},
        },
        methods:{
            addDatas(data){
                this.pro = data;
            },
            addLabel(labe){
                if(this.label!=null){
                    this.label.label=labe;
                }else {
                    this.label={"label":labe};
                }

                console.log(this.label);
            },
            toDetail(index){
                let id = hot.pro.list[index].questionId;
                window.location.href="question.html?questionId="+id;
            },
            nextPageData(){
                let nextPage = hot.pro.nextPage;
                console.log(nextPage);
                let oldLabel = null;
                if(hot.label.label!== null){
                    oldLabel = hot.label.label;
                }

                selectHotQuestion(nextPage,5,"desc",oldLabel);
            },
            prePageData(){
                let prePage = hot.pro.prePage;
                let oldLabel = null;
                if(hot.label.label!== null){
                    oldLabel = hot.label.label;
                }
                selectHotQuestion(prePage,5,"desc",oldLabel);
            }
        }
    }
);