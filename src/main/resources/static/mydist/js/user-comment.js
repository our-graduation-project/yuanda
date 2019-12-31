function formatDate(date, fmt) {
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    let o = {
        'M+': date.getMonth() + 1,
        'd+': date.getDate(),
        'h+': date.getHours(),
        'm+': date.getMinutes(),
        's+': date.getSeconds()
    };
    for (let k in o) {
        if (new RegExp(`(${k})`).test(fmt)) {
            let str = o[k] + '';
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? str : padLeftZero(str));
        }
    }
    return fmt;
};

function padLeftZero(str) {
    return ('00' + str).substr(str.length);
}
let vmComment = new Vue({
    el:'#commentApp',
    data:{
        comments:[],
        commentToOther:{},
        commentContentS:"",
        userId:"1",
    },
    filters: {
        formatDate(time) {
            let date = new Date(time);
            return formatDate(date,'yyyy-MM-dd hh:mm');
        }
    },
    methods:{
        del(index){
            var str = "你确定要删除该条吗？";
            if (confirm(str)) {
                let data = {
                    "commentId": vmComment.comments[index].commentId
                };
                if(isNull(vmComment.comments[index].commentId)){
                    alert("删除评论失败");
                }else{
                    $.ajax({
                        //请求方式.
                        type: "POST",
                        //请求的媒体类型
                        contentType: "application/json;charset=UTF-8",
                        //请求地址
                        url: "http://localhost:8080/yuanda/user/delcomment",
                        //数据，json字符串
                        data: JSON.stringify(data),
                        success: function (result) {
                            alert(result.message);
                            window.location.reload();
                        },
                        error: function () {
                            alert("删除评论失败");
                        }

                    });
                }
            }
        },
        commentOther(index){
            if(vmComment.comments[index].commentType === 0){
                vmComment.commentToOther.parentId = vmComment.comments[index].commentId;
            }else{
               vmComment.commentToOther.parentId = vmComment.comments[index].parentId;
            }
            vmComment.commentToOther.commentType = 2;
            vmComment.commentToOther.commentTargetId = vmComment.comments[index].commentId;
            vmComment.commentToOther.commentResourceName = vmComment.comments[index].commentResourceName;

            vmComment.commentContentS="@"+vmComment.comments[index].commentResourceName+":";
        },
        send(){
            if(vmComment.commentToOther.commentType === 2){
                if(vmComment.commentContentS.startsWith("@"+vmComment.commentToOther.commentResourceName+":")) {
                    vmComment.commentToOther.commentContent = "<span style='color: green'>"+"@"+vmComment.commentToOther.commentResourceName+":"+"</span>"+
                        vmComment.commentContentS.substring(vmComment.commentToOther.commentResourceName.length+2);
                }else{
                    vmComment.commentToOther.commentContent = "<span style='color: green'>"+"@"+vmComment.commentToOther.commentResourceName+":"+"</span>"+
                        vmComment.commentContentS;
                }
            }else{
                vmComment.commentToOther.commentContent = vmComment.commentContentS;
            }


            let data = {
                "parentId": vmComment.commentToOther.parentId,
                "commentType" : vmComment.commentToOther.commentType,
                "commentTargetId": vmComment.commentToOther.commentTargetId,
                "commentContent" : vmComment.commentToOther.commentContent,
            };

            if(isNull(vmComment.commentToOther.parentId) &&
                isNull(vmComment.commentToOther.commentType) && isNull(vmComment.commentToOther.commentTargetId) &&
                isNull(vmComment.commentToOther.commentContent)){
                alert("提那家评论失败");
            }else{
                $.ajax({
                    //请求方式.
                    type: "POST",
                    //请求的媒体类型
                    contentType: "application/json;charset=UTF-8",
                    //请求地址
                    url: "http://localhost:8080/yuanda/user/addcomment",
                    //数据，json字符串
                    data: JSON.stringify(data),
                    success: function (result) {
                        alert(result.message);
                        window.location.reload();
                    },
                    error: function () {
                        alert("操作失败");
                    }

                });
            }


        },
        getData(commentTargetId,commentType,userId){
            vmComment.commentToOther.parentId = commentTargetId;
            vmComment.commentToOther.commentType = commentType;
            vmComment.commentToOther.commentTargetId = commentTargetId;
            vmComment.commentToOther.userId = userId;
            let data = {
                "commentTargetId": commentTargetId,
                "commentType" : commentType
            };
            if(isNull(commentTargetId) && isNull(commentType)){
                alert("加载评论失败！");
            }else{
                $.ajax({
                    //请求方式.
                    type: "POST",
                    //请求的媒体类型
                    contentType: "application/json;charset=UTF-8",
                    //请求地址
                    url: "http://localhost:8080/yuanda/user/loadcomment",
                    //数据，json字符串
                    data: JSON.stringify(data),
                    success: function (result) {
                        vmComment.comments = result.data;
                        console.log("开始");
                        console.log(vmComment.comments);
                    },
                    error: function () {
                        alert("操作失败");
                    }

                });
            }

        },
    }

});

$(function () {
    var thisUrl = location.search;
    if(thisUrl !=null&&thisUrl.indexOf("?") != -1) {
        var commentTargetId = thisUrl.substr(thisUrl.indexOf("=") + 1);
        let userId = 1;
        let commentType = $("input[name='commentDataType']").val();
        console.log(commentTargetId);
        console.log(commentType);
        vmComment.getData(commentTargetId,commentType,userId);
    }else {
        swal({
            title: "一定是哪里出了问题，待会儿再看吧",
        });
        //window.location.href="types.html"
    }
});



