
$(function () {
    new AjaxUpload("#firstPicture",{
        action: "/yuanda/images",
        name: 'upload',
        autoSubmit: true,
        responseType: "json",
        onSubmit: function (file, extension) {
            console.log("开始");
            if (!(extension && /^(jpg|jpeg|png|gif)$/.test(extension.toLowerCase()))) {
                alert('只支持jpg、png、gif格式的图片！', {
                    icon: "error",
                });
                return false;
            }
        },
        onComplete: function (file, r) {
            if (r.uploaded == 1) {
                alert("上传成功");
                //$("#img").attr('src',r.date);
                $("#firstPicture").attr("src",r.fileName);
                $("#pictureStr").attr("value", r.fileName);
                console.log("src:" + r.fileName);
                // $("#img").attr("style", "width: 100px;height: 100px;display:block;");
                return false;
            } else {
                alert("上传失败");
            }
        }
    });

});

function addQustionModal() {
    $('.ui.modal').modal('show');
}

function modelClose() {
    $('.ui.modal')
        .modal('hide')
    ;


    
}

function addQuestion() {
    var content = $("#content").val();
    var title = $("#title").val();
    var label = $("#label").val();
    var pictureStr ="";
        pictureStr = $("#pictureStr").val();
    if(content==null||content.trim()==null||content==""||title==null||title.trim()==null||title==""){
        $("#error").attr("存在为空的信息");

           return false;

    }
    var data = {"questionDescript":content,"title":title,"label":label,"pictureStr":pictureStr};
    console.log(data);
    $.ajax({
        //请求方式
        type : "POST",
        //请求的媒体类型
        contentType: "application/json;charset=UTF-8",
        //请求地址
        url : "admin/addQuestion",
        //数据，json字符串
        data : JSON.stringify(data),
        success: function (result) {

            if (result.result) {
                if(result.message=="查询失败"){
                    swal({
                        title: "你的回答有问题,再来一次吧",
                    });
                }else {
                    swal({
                        title: "提问成功",
                    });
                }


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
    modelClose();
}