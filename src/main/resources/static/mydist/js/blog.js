let vmBlogComment = new Vue({
    el:'#commentApp',
    data:{
        comment:[],

    },
    methods: {
        //传入当前需要查询评论的id和其类型
        getComment(commentTargetId,commentType) {
            let data = {"commentTargetId" : commentTargetId,
                        "commentType" : commentType,
            };
            $.ajax({
                //请求方式.
                type: "POST",
                //请求的媒体类型
                contentType: "application/json;charset=UTF-8",
                //请求地址
                url: "http://localhost:8080/yuanda/loadcomment",
                //数据，json字符串
                data: JSON.stringify(data),
                success: function (result) {
                    vm.comment = result.data;
                },
                error: function () {
                    alert("操作失败");
                }

            });
        },
    },
});

//获取数据
var commentTargetId = 1;
var commentType = 0;
vmBlogComment.getComment(commentTargetId,commentType);

function delComment(commentId){
    let data = {"commentId": vmDetail.favorites.favoritesId};
    var str = "你确定要删除标题为:"+vmDetail.favorites.favoritesName+"的收藏夹吗？";
    if (confirm(str)) {
        $.ajax({
            //请求方式.
            type: "POST",
            //请求的媒体类型
            contentType: "application/json;charset=UTF-8",
            //请求地址
            url: "http://localhost:8080/yuanda/delfavorites",
            //数据，json字符串
            data: JSON.stringify(data),
            success: function (result) {
                alert(result.message);
                window.location.href="favorites.html";
            },
            error: function () {
                alert("操作失败");
            }
        });
    }
}