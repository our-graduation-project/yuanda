$(function () {
    var thisUrl = location.search;
    if(thisUrl !=null&&thisUrl.indexOf("?") != -1) {
        var id = thisUrl.substr(thisUrl.indexOf("=") + 1);

        selectArticleById(id);
    }else {
        swal({
            title: "一定是哪里出了问题，待会儿再看吧",
        });
        //window.location.href="types.html"
    }
});


function selectArticleById(articleId) {

    var data = {"articleId":articleId};

    $.ajax({
        //请求方式
        type : "GET",
        //请求的媒体类型
        contentType: "application/json;charset=UTF-8",
        //请求地址
        url : "/yuanda/articles/info/"+articleId,
        //数据，json字符串
        //data : JSON.stringify(data),
        success: function (result) {

            if (result.result) {
                if(result.message=="查询失败"){
                    swal({
                        title: "你要查看的内容飞走了，看看别的吧",
                    });
                    //window.location.href="types.html";
                }
                var data = result.data;
                console.log(data);
                // article.addPic();
                article.addArticleData(data);

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
            //window.location.href="types.html"
        }


    });

}


let article = new Vue({
    el: '#articleData',
    data:{
        articleData:{},
    },
    methods:{
        addArticleData(data){
            this.articleData = data;
            this.articleData.authorPicture = 'aa.jpg';
            this.articleData.pictureSrc = 'aa.jpg';
            console.log(this.articleData);
        },
        addPic(){

        },


    }
});
