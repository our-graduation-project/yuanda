function modal() {
    $('.ui.modal').modal('show');
}

var vm = new Vue({
    el:'#app',
    created(){
        this.getData();
    },
    data:{
        favorites:[],
        addFavoritesName:"",
    },
    methods:{
        toDetail(index){
            let id =vm.favorites[index].favoritesId;
            window.location.href="favorites-detail.html?id="+id;
        },
        getData(){
            axios.get('http://localhost:8080/yuanda/loadallfavorites').then(
                res=>{//sucesses
                    this.favorites = res.data.data;
                },err=>{//error
                    console.log(err);
                });
        }


    }
});


function addFavorites() {
    alert(1);
    let addFavoritesName = $("#addFavoritesName").val();
    let data={"addFavoritesName":addFavoritesName};
    $.ajax({
        //请求方式
        type : "POST",
        //请求的媒体类型
        contentType: "application/json;charset=UTF-8",
        //请求地址
        url : "http://localhost:8080/yuanda/addfavorites",
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
            alert("操作失败");
        }

    });
}


