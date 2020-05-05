$(
    function () {
        selectHot(1,5,"desc",null);
    }
);

function select(page,limite,order,label) {
        selectHot(page,limite,order,label);

}


// 标签，搜索标签有关的热榜
function selectHotQuestionByType(label) {
    var page = 1;
    selectHot(page,5,"desc",label);

}


// 通用搜索热榜
function selectHot(page,limit,order,label){
    console.log(hot.status.status+"label:"+label);
    if(hot.status.status ==1){
        if(label == null||label == ""){
            var url = "pageArticleList";
            var data = {"page":page,"limit":limit,"orderName":"hot_number","order":order};
        }else {
            hot.addLabel(label);
            var url = "selectArticleByLabelId";
            var data = {"page":page,"limit":limit,"orderName":"hot_number","order":order,"label":label};
        }
    }else {
        if(label == null||label == ""){
            var url = "pageSelectQuestions";
            var data = {"page":page,"limit":limit,"orderName":"hot","order":order};
        }else {
            hot.addLabel(label);
            var url = "pageSelectQuestionsByType";
            var data = {"page":page,"limit":limit,"orderName":"hot","order":order,"label":label};
        }
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
                hot.addQuestionDatas(data);
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
        el: '#hot',
        data:{
            pro:{},
            label:{},
            status:{"status":1},
        },
        methods:{
            //增加数据
            addQuestionDatas(data){
                this.pro = data;
            },
            //修改状态（1，文章 2，问题）,并且重新查询
            updateState(){
                if(this.status == null||this.status.status == undefined||this.status.status == 2){
                    this.status = {"status":1};
                }else {
                    this.status = {"status":2};
                }
                console.log("状态"+this.status.status);
                selectHot(1,5,"desc",null)

            },

            //修改或增加标签分类
            addLabel(labe){
                if(this.label!=null){
                    this.label.label=labe;
                }else {
                    this.label={"label":labe};
                }


            },

            //跳转到详细页面
            toDetail(index){
                var url="";
                if(this.status.status == 1){
                    url = "blog.html?articleId="+hot.pro.list[index].articleId;
                }else {
                    let id = hot.pro.list[index].questionId;
                    url = "question.html?questionId="+id;
                }


                window.location.href = url;
            },

            // 下一页
            nextPageData(){
                let nextPage = hot.pro.nextPage;
                console.log(nextPage);
                let oldLabel = null;
                if(hot.label.label!== null){
                    oldLabel = hot.label.label;
                }

                selectHot(nextPage,5,"desc",oldLabel);
            },

            //上一页
            prePageData(){
                let prePage = hot.pro.prePage;
                let oldLabel = null;
                if(hot.label.label!== null){
                    oldLabel = hot.label.label;
                }
                selectHot(prePage,5,"desc",oldLabel);
            },
            //获取状态数据（1，文章 2，问题）
            getStatus(){
                return this.status.status;
            }
        }
    }
);