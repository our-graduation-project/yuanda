function modal() {
    $('.ui.modal').modal('show');
}

var vmDetail = new Vue({
    el:'#app',
    created(){
        function getQueryStringChinese(id) {
            var reg = new RegExp("(^|&)" + id + "=([^&]*)(&|$)", "i");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return decodeURI(r[2]);
            return null;
        }
        var id = getQueryStringChinese("id");
        if(isNull(id)){
            alert("加载收藏夹是发生错误！")
        }else{
            this.getData(id);
        }

    },
    data:{
        favorites:{},
        favoritesName:"",
        favoritesConnections:[],
        userName:"",
        userPic:""
    },
    methods: {
        tohref(index){
            if(vmDetail.favoritesConnections[index].type === 1){
                window.location.href="http://localhost:8080/yuanda/blog.html?articleId="+vmDetail.favoritesConnections[index].connectionId;
            }
        },
        getData(id) {
            this.userName = getUserData("userName");
            this.userPic = getUserData("userPicture");
            let data = {"favoritesId": id};
            $.ajax({
                //请求方式.
                type: "POST",
                //请求的媒体类型
                contentType: "application/json;charset=UTF-8",
                //请求地址
                url: "http://localhost:8080/yuanda/user/searchfavoritdetailbyid",
                //数据，json字符串
                data: JSON.stringify(data),
                success: function (result) {
                    vmDetail.favorites = result.data.favorites;
                    vmDetail.favoritesName = vmDetail.favorites.favoritesName;
                    vmDetail.favoritesConnections = result.data.favoritesConnections;
                },
                error: function () {
                    alert("加载收藏夹是发生错误！")
                }

            });
        },
        delFavorites(){
            let data = {"favoritesId": vmDetail.favorites.favoritesId};
            if(isNull(vmDetail.favorites.favoritesId)){
                alert("删除收藏夹失败");
            }else{
                var str = "你确定要删除标题为:"+vmDetail.favorites.favoritesName+"的收藏夹吗？";
                if (confirm(str)) {
                    $.ajax({
                        //请求方式.
                        type: "POST",
                        //请求的媒体类型
                        contentType: "application/json;charset=UTF-8",
                        //请求地址
                        url: "http://localhost:8080/yuanda/user/delfavorites",
                        //数据，json字符串
                        data: JSON.stringify(data),
                        success: function (result) {
                            alert(result.message);
                            window.location.href = "favorites.html";
                        },
                        error: function () {
                            alert("删除收藏夹失败");
                        }
                    });
                }

            }
        },
        delFavoritesConnection(index){
                let data = {"id": vmDetail.favoritesConnections[index].id,
                            "favoritesId": vmDetail.favorites.favoritesId,
                            "favoritesNumber": vmDetail.favorites.favoritesNumber,
                           };
                var str = "你确定要删除标题为:" + vmDetail.favoritesConnections[index].contentTitle + "的收藏记录吗？";
                if(isNull(vmDetail.favoritesConnections[index].id)){
                    alert("删除收藏记录失败！");
                }else if(isNull(vmDetail.favorites.favoritesId)){
                    alert("删除收藏记录失败！");
                }else if(isNull(vmDetail.favorites.favoritesNumber)){
                    alert("删除收藏记录失败！");
                }
                else {
                    if (confirm(str)) {
                        $.ajax({
                            //请求方式.
                            type: "POST",
                            //请求的媒体类型
                            contentType: "application/json;charset=UTF-8",
                            //请求地址
                            url: "http://localhost:8080/yuanda/user/delfavoritesconnection",
                            //数据，json字符串
                            data: JSON.stringify(data),
                            success: function (result) {
                                alert(result.message);
                                window.location.reload();
                            },
                            error: function () {
                                alert("删除收藏记录失败！");
                            }
                        });
                    }
                }

            }
        },
});

function editFavorites() {
    let editfavoritesName = $("#editfavoritesName").val();
    let data={"editfavoritesName":editfavoritesName,
        "favoritesId": vmDetail.favorites.favoritesId,
    };

    if(isNull(editfavoritesName)){
        alert("编辑收藏夹失败");
    }else if(isNull(vmDetail.favorites.favoritesId)){
        alert("编辑收藏夹失败");
    } else{
        $.ajax({
            //请求方式
            type : "POST",
            //请求的媒体类型
            contentType: "application/json;charset=UTF-8",
            //请求地址
            url : "http://localhost:8080/yuanda/user/editfavorites",
            //数据，json字符串
            data : JSON.stringify(data),
            success: function (result) {
                if (result.result) {
                    alert("添加成功");
                    window.location.reload();
                }
                else {
                    $("#error").html(result.message);
                    console.log(result.message+"--"+result.result);
                    alert("添加失败");
                }
            },
            error: function () {
                alert("编辑收藏夹失败");
            }

        });
    }


}