let vmNotification = new Vue({

    el:'#app',
    mounted(){
        this.getData();
    },
    data:{
        isReadNotifications:[],
        notReadNotifications:[],
        pageInfoIsRead:[],
        pageInfoNotRead:[],
        pageInfo:[],
        userPic:"",
        userName:""
    },
    methods:{
        nextPage(pageInfoTemp,num){
            if(num === 0){
                vmNotification.pageInfo = pageInfoTemp;
                if(vmNotification.pageInfo.hasNextPage){
                    alert(vmNotification.pageInfo.pageNum+1);
                    let data = {"page" : vmNotification.pageInfo.pageNum+1};
                    $.ajax({
                        //请求方式.
                        type: "POST",
                        //请求的媒体类型
                        contentType: "application/json;charset=UTF-8",
                        //请求地址
                        url: "http://localhost:8080/yuanda/user/loadnotreadmessage",
                        //数据，json字符串
                        data: JSON.stringify(data),
                        success: function (result) {
                            vmNotification.pageInfoNotRead = result.data;
                            vmNotification.notReadNotifications = vmNotification.pageInfoNotRead.list;
                        },
                        error: function () {
                            alert("加载通知失败！");
                        }
                    });
                }else{
                    alert("已经是最后一页了！");
                }
            }else{
                vmNotification.pageInfo = pageInfoTemp;
                if(vmNotification.pageInfo.hasNextPage){
                    alert(vmNotification.pageInfo.pageNum+1);
                    let data = {"page" : vmNotification.pageInfo.pageNum+1};
                    $.ajax({
                        //请求方式.
                        type: "POST",
                        //请求的媒体类型
                        contentType: "application/json;charset=UTF-8",
                        //请求地址
                        url: "http://localhost:8080/yuanda/user/loadisreadmessage",
                        //数据，json字符串
                        data: JSON.stringify(data),
                        success: function (result) {
                            vmNotification.pageInfoIsRead = result.data;
                            vmNotification.isReadNotifications = vmNotification.pageInfoIsRead.list;
                        },
                        error: function () {
                            alert("加载通知失败！");
                        }
                    });
                }else{
                    alert("已经是最后一页了！");
                }
            }

        },
        proPage(pageInfoTemp,num){
            if(num === 0){
                vmNotification.pageInfo = pageInfoTemp;
                if(vmNotification.pageInfo.hasPreviousPage){
                    alert(vmNotification.pageInfo.pageNum-1);
                    let data = {"page" : vmNotification.pageInfo.pageNum-1};
                    $.ajax({
                        //请求方式.
                        type: "POST",
                        //请求的媒体类型
                        contentType: "application/json;charset=UTF-8",
                        //请求地址
                        url: "http://localhost:8080/yuanda/user/loadnotreadmessage",
                        //数据，json字符串
                        data: JSON.stringify(data),
                        success: function (result) {
                            vmNotification.pageInfoNotRead = result.data;
                            vmNotification.notReadNotifications = vmNotification.pageInfoNotRead.list;
                        },
                        error: function () {
                            alert("加载通知失败！");
                        }
                    });
                }else{
                    alert("已经是第一页了！");
                }
            }else{
                vmNotification.pageInfo = pageInfoTemp;
                if(vmNotification.pageInfo.hasPreviousPage){
                    alert(vmNotification.pageInfo.pageNum-1);
                    let data = {"page" : vmNotification.pageInfo.pageNum-1};
                    $.ajax({
                        //请求方式.
                        type: "POST",
                        //请求的媒体类型
                        contentType: "application/json;charset=UTF-8",
                        //请求地址
                        url: "http://localhost:8080/yuanda/user/loadisreadmessage",
                        //数据，json字符串
                        data: JSON.stringify(data),
                        success: function (result) {
                            vmNotification.pageInfoIsRead = result.data;
                            vmNotification.isReadNotifications = vmNotification.pageInfoIsRead.list;
                        },
                        error: function () {
                            alert("加载通知失败！");
                        }
                    });
                }else{
                    alert("已经是第一页了！");
                }
            }

        },
        del(messageId){
            let data = {
                "messageId": messageId
            };
            var str = "你确定要删除该条通知吗？";
            if (confirm(str)) {
                $.ajax({
                    //请求方式.
                    type: "POST",
                    //请求的媒体类型
                    contentType: "application/json;charset=UTF-8",
                    //请求地址
                    url: "http://localhost:8080/yuanda/user/delmessage",
                    //数据，json字符串
                    data: JSON.stringify(data),
                    success: function (result) {
                        alert(result.message);
                        window.location.reload();
                    },
                    error: function () {
                        alert("删除通知失败！");
                    }
                });
            }
        },
        becomeRead(index){
            let data = {
                "messageId": vmNotification.notReadNotifications[index].messageId
            };
            $.ajax({
                //请求方式.
                type: "POST",
                //请求的媒体类型
                contentType: "application/json;charset=UTF-8",
                //请求地址
                url: "http://localhost:8080/yuanda/user/updatemessage",
                //数据，json字符串
                data: JSON.stringify(data),
                success: function (result) {
                    alert(result.message);
                    window.location.reload();
                },
                error: function () {
                    alert("修改通知失败！");
                }

            });

        },
        setIsReadData(){
            let data = {"page" : vmNotification.pageInfo.pageNum};
            $.ajax({
                //请求方式.
                type: "POST",
                //请求的媒体类型
                contentType: "application/json;charset=UTF-8",
                //请求地址
                url: "http://localhost:8080/yuanda/user/loadisreadmessage",
                //数据，json字符串
                data: JSON.stringify(data),
                success: function (result) {
                    vmNotification.pageInfoIsRead = result.data;
                    vmNotification.isReadNotifications = vmNotification.pageInfoIsRead.list;
                },
                error: function () {
                    alert("加载通知失败！");
                }
            });
        },
        setNotReadData(){
            let data = {"page" : vmNotification.pageInfo.pageNum};
            $.ajax({
                //请求方式.
                type: "POST",
                //请求的媒体类型
                contentType: "application/json;charset=UTF-8",
                //请求地址
                url: "http://localhost:8080/yuanda/user/loadnotreadmessage",
                //数据，json字符串
                data: JSON.stringify(data),
                success: function (result) {
                    vmNotification.pageInfoNotRead = result.data;
                    vmNotification.notReadNotifications = vmNotification.pageInfoNotRead.list;
                },
                error: function () {
                    alert("加载通知失败！");
                }
            });
        },
        getData() {
            console.log("aaaaaaa");
            this.userName = getUserData("userName");
            this.userPic = getUserData("userPicture");
            // let data = {"favoritesId": id};
        },

    }
});

vmNotification.setIsReadData();
vmNotification.setNotReadData();