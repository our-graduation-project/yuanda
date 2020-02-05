
$(function () {
    var thisUrl = location.search;
    if(thisUrl !=null&&thisUrl.indexOf("?") != -1) {
        var searchValue = thisUrl.substr(thisUrl.indexOf("=") + 1);
        $("#searchValue").val(searchValue);
        search(searchValue)
    }else {
        alert("一定是哪里出错了,重新搜索一下吧")
    }
});

function search(searchValue) {

    var data = {"title":decodeURIComponent(searchValue)};
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
                var data = result.data;
                // console.log(data);
                searchData.addArticle(data);
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
                var vm = this;
                if (vm.currentPage == 0) return;
                vm.currentPage--;

            },
            toPage: function(page){
                var vm = this;
                vm.currentPage = page
            },
            toDetail(index){
                var url = "blog.html?articleId=" + searchData.newData[(searchData.currentPage*10+index)].articleId;
                window.location.href=url;
            },



        }
    }
);