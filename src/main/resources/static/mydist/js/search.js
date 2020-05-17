
$(function () {
    let thisUrl = location.search;
    if(thisUrl !=null&&thisUrl.indexOf("?") != -1) {
        let value = thisUrl.substr(thisUrl.indexOf("=") + 1);
        let arrays = value.split("&");
        // console.log(" url :" + value);
        let option = 0;
        if(arrays.length == 2){//选择了文章或者问题
            option = arrays[1];
        }
        // console.log(" arrays : " + arrays + " " + arrays.length);
        let searchValue = decodeURIComponent(arrays[0]);
        if(option == 0){//搜索文章
            search(searchValue);
        }else{//搜索问题
            searchQuestion(searchValue);
        }
        $("#searchValue").val(searchValue);
    }else {
        alert("一定是哪里出错了,重新搜索一下吧")
    }
});

function search(searchValue) {

    let data = {"title":decodeURIComponent(searchValue)};
    // console.log("title:" + searchValue + "  " + decodeURIComponent(searchValue));
    $.ajax({
        //请求方式
        type : "POST",
        //请求的媒体类型
        contentType: "application/json;charset=UTF-8",
        //请求地址
        url : "search",
        //数据，json字符串
        data : JSON.stringify(data),
        success: function (result) {
            if (result.result) {
                if(result.message=="操作失败"){
                    swal({
                        title: "你的提交有问题，待会儿再试试吧",
                    });
                }
                let data = result.data;
                // console.log(data);
                searchData.addArticle(data);
                searchData.option = 0;
            }
            else {
                swal({
                    title: "出错了，怎么想都不是我的错",
                });

            }
        },
        error: function () {
            swal({
                title: "出错了,怎么想都不是我的错",
            });
            // window.location.href="question.html"
        }
    });
    
}

function searchQuestion(searchValue) {

    let data = {"title":decodeURIComponent(searchValue)};
    console.log("title:" + searchValue + "  " + decodeURIComponent(searchValue));
    $.ajax({
        //请求方式
        type : "POST",
        //请求的媒体类型
        contentType: "application/json;charset=UTF-8",
        //请求地址
        url : "searchQuestion",
        //数据，json字符串
        data : JSON.stringify(data),
        success: function (result) {
            if (result.result) {
                if(result.message=="操作失败"){
                    swal({
                        title: "你的提交有问题，待会儿再试试吧",
                    });
                }
                let data = result.data;
                // console.log(data);
                searchData.addArticle(data);
                searchData.option = 1;
            }
            else {
                swal({
                    title: "出错了，怎么想都不是我的错",
                });

            }
        },
        error: function () {
            swal({
                title: "出错了,怎么想都不是我的错",
            });
            // window.location.href="question.html"
        }
    });

}

let searchData = new Vue(
    {
        el: '#searchData',
        data:{
            newData:{},
            reveal:{},
            pageSize:5,
            currentPage:0,
            option:0,

        },
        computed:{
            dataShow: function(){
                let start = this.currentPage*this.pageSize;
                let end = Math.min((this.currentPage+1)*this.pageSize, this.newData.length)
                return this.newData.slice(start, end)
            },
            pageNum: function(){
                return Math.ceil(this.newData.length / this.pageSize) || 1 ;
            }
        },
        methods:{
            addArticle(data){
                this.newData=data;
                // console.log(this.newData.length);
                this.reveal = this.newData;
            },
            getData(){
                if(this.newData == null){
                    return true;
                }else {
                    return false;
                }
            },
            nextPage: function(){
                var vm = this;
                if (vm.currentPage == vm.pageNum - 1) return;
                vm.currentPage++;

            },
            prePage: function(){
                let vm = this;
                if (vm.currentPage == 0) return;
                vm.currentPage--;

            },
            toPage: function(page){
                var vm = this;
                vm.currentPage = page
            },
            toDetail(index){
                let url;
                console.log("option:" + this.option);

                if(this.option == 0 || this.option == "0"){
                    url = "blog.html?articleId=" + searchData.newData[(searchData.currentPage*5+index)].articleId;
                }else{
                    url = "question.html?questionId=" + searchData.newData[(searchData.currentPage*5+index)].questionId;
                }
                window.location.href=url;
            },
            getType(){
                // console.log("option :" + this.option);
                return this.option == 0 ? true : false;
            }
        }
    }
);