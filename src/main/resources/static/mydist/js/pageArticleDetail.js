$(function () {
    var thisUrl = location.search;
    if(thisUrl !=null&&thisUrl.indexOf("?") != -1) {
        var id = thisUrl.substr(thisUrl.indexOf("=") + 1);
        selectArticleById(id);
        article.getData();
    }else {
        swal({
            title: "一定是哪里出了问题，待会儿再看吧",
        });
        //window.location.href="types.html"
    }
});


function selectArticleById(articleId) {

    var data = {"articleId":articleId,"userId":getUserId()};

    $.ajax({
        //请求方式
        type : "POST",
        //请求的媒体类型
        contentType: "application/json;charset=UTF-8",
        //请求地址
        url : "/yuanda/articles/details",
        //数据，json字符串
        data : JSON.stringify(data),
        success: function (result) {

            if (result.result) {
                if(result.message=="查询失败"){
                    swal({
                        title: "你要查看的内容飞走了，看看别的吧",
                    });
                    //window.location.href="types.html";
                }
                var data = result.data;
                console.log(data[0] + "---" + data[1]);
                article.likeStatus = data[1];
                console.log("是否喜欢1："+article.likeStatus);
                // article.likeStatus = 1;
                article.addArticleData(data[0]);

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
        favorites:[],
        likeStatus:{},
    },
    methods:{
        addArticleData(data){
            this.articleData = data;
            this.articleData.authorPicture = 'classification.jpg';
            this.articleData.pictureSrc = 'classification.jpg';
            console.log("是否喜欢2："+article.likeStatus);
        },
        addPic(){

        },
        modal(){
            $('.ui.modal').modal('show');
        },
        addFavoritesConnection(index){
            var favoritesId = article.favorites[index].favoritesId;

            var data = {"favoritesId":favoritesId,"type":1,"connectionId":article.articleData.articleId,
                "contentTitle":article.articleData.articleTitle,"author":article.articleData.authorName
            };
            $.ajax({
                //请求方式.
                type: "POST",
                //请求的媒体类型
                contentType: "application/json;charset=UTF-8",
                //请求地址
                url: "http://localhost:8080/yuanda/user/addfavoritesconnection",
                //数据，json字符串
                data: JSON.stringify(data),
                success: function (result) {
                    alert(result.message);
                },
                error: function () {
                    alert("添加失败");
                }

            });
        },
        getData(){
            axios.get('http://localhost:8080/yuanda/user/loadallfavorites').then(
                res=>{//sucesses
                    article.favorites = res.data.data;
                },err=>{//error
                    console.log(err.message);
                });
        },
        like(){
            if(getUserId() == undefined || getUserId() == ""){
                // if(alert("还未登陆是否跳入登陆页面")){
                //     window.location.href="person/login.html";
                // }
                swal("还未登陆是否跳入登陆页面", {
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
                                break;
                            case "ensure":
                                window.location.href="person/login.html";
                                break;
                        }
                    });
                return;
            }
            let articleId = this.articleData.articleId;
            let userId = getUserId(articleId,getUserId(),1);
            article.changeLikeStatus(articleId,userId,1);
        },
        disLike(){
            if(getUserId() == undefined || getUserId() == ""){
                alert("请登陆");
                return;
            }
            let articleId = this.articleData.articleId;
            let userId = getUserId(articleId,getUserId());
            article.changeLikeStatus(articleId,userId,-1);
        },
        changeLikeStatus(articleId,userId,likeStatus){
            let data = {"userId":userId,"articleId":articleId,"likeStatus":likeStatus};
            console.log(data);
            axios.post('http://localhost:8080/yuanda/articleLikeStatus',data).then(
                res=>{//sucesses
                    article.likeStatus = likeStatus;
                    article.articleData.agreementNumber = res.data.data[0];
                    article.articleData.disagreementNumber = res.data.data[1];
                },err=>{//error
                    console.log(err.message);
                });
        },


    }
});