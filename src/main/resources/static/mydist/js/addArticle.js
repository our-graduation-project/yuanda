$(function () {



    editorConfig = {
        customConfig: './samples/editConfig.js'
    };

    CKEDITOR.replace( 'editor', editorConfig);

    $('.menu.toggle').click(function () {
        $('.m-item').toggleClass('m-mobile-hide');
    });
    /*导航栏用户注销显示*/
    $('.ui.dropdown').dropdown({
        on : 'hover'
    });

    $('#save-btn').click(function () {
        $('[name="published"]').val(false);
        $('#blog-form').submit();
    });


    $('#publish-btn').click(function () {
        $('[name="published"]').val(true);
        $('#blog-form').submit();
    });

    /*表单校验*/
    $('.ui.form').form({
        fields:{
            title:{
                identifier:'title',
                rules : [{
                    type : 'empty',
                    prompt:'请输入文章标题！'
                }]
            },
            content : {
                identifier:'content',
                rules : [{
                    type : 'empty',
                    prompt:'请输入文章内容！'
                }]
            },
            typeId : {
                identifier : 'type.id',
                rules : [{
                    type: 'empty',
                    prompt:'请选择文章分类！'
                }]
            },
            firstPicture : {
                identifier : 'firstPicture',
                rules : [{
                    type: 'empty',
                    prompt:'请填写首图地址！'
                }]
            },
            description  : {
                identifier : 'description ',
                rules : [{
                    type: 'empty',
                    prompt:'请填入博客描述！'
                }]
            }
        }
    });


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
                $("#picturePath").attr("value", r.fileName);
                // console.log("src:" + r.fileName);
                // $("#img").attr("style", "width: 100px;height: 100px;display:block;");
                return false;
            } else {
                alert("上传失败");
            }
        }
    });

});

function saveArticle() {
    let content =  CKEDITOR.instances.editor.getData();
    let title = $("#title").val();
    let label = $("#label").val();
    let pictureSrc = $("#picturePath").val();
    let url;
    let data;
    if($("#changForUpdate").text() == "修改"){
        url = "../person/updateArticle";
        let param = window.location.href.split("id=");
        data = {"articleDescript":content,"title":title,"label":label,"pictureSrc":pictureSrc,"articleId":param[1]};
    }else{
        url = "../person/addArticle";
        data = {"articleDescript":content,"title":title,"label":label,"pictureSrc":pictureSrc};
    }


    // console.log(data);
    $.ajax({
        //请求方式
        type : "POST",
        //请求的媒体类型
        contentType: "application/json;charset=UTF-8",
        //请求地址
        url : url,
        //数据，json字符串
        data : JSON.stringify(data),
        success: function (result) {
            if (result.result) {
                    swal({
                        title: result.message,
                    });
                    //window.location.href="blogs-input.html";
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


}