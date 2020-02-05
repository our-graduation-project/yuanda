
/**
 * 写入cookie
 *
 * @param name
 * @param value
 */
function setCookie(name, value) {
    var Days = 30;
    var exp = new Date();
    exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString() + ";path=/";

}

/**
 * 读取cookie
 * @param name
 * @returns {null}
 */
function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}

/**
 * 删除cookie
 * @param name
 */
function delCookie(name) {
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval = getCookie(name);
    if (cval != null)
        document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
}

/**
 * 检查cookie
 */
function checkCookie() {
    if (getCookie("token") == null) {
        alert("未登录！");
        window.location.href = "login.html";
    }
}

/**
 * 检查cookie
 */
function checkResultCode(code) {
    if (code == 402) {
        window.location.href = "login.html";
    }
}

/**
 * 检查用户是否拥有power权限
 * @param power
 */
function checkPower(power) {
    var cookie = getCookie("token");
    var load = cookie.split(".");
    //获得jwt里面的loder
    //console.log(window.atob(load[1]));

    //转为json对象
    var obj = new Function("return" + window.atob(load[1]))();
    //获取权力
    console.log(obj.right);
    var rights = obj.right;
    for (var i = 0; i < rights.length; i++) {
        if(rights[i] == power){
            return true;
        }
    }
    return false;
}

/**
 * 将本页面涉及到的权限传入，若用户没有某项权限，class="right" 此项的全部隐藏
 * @param power
 */
function powerManager(power) {
    // console.log(power.length);
    for (var i = 0; i < arguments.length; i++) {
        console.log(arguments[i]);
        if(!checkPower(arguments[i])){
            $("."+arguments[i]+"").css('display','none');
        }
    }
}


/**
 * 检查用户是否拥有power权限
 * @param power
 */
function getUserId() {
    var cookie = getCookie("token");
    if(cookie == null || cookie == undefined){
        return null;
    }
    var load = cookie.split(".");
    //获得jwt里面的loder
    //console.log(window.atob(load[1]));

    //转为json对象
    var obj = new Function("return" + window.atob(load[1]))();
    //获取id
    return obj.userId;
}



function getUserData(data) {
    var cookie = getCookie("token");
    if(cookie == null || cookie == undefined){
        return null;
    }
    var load = cookie.split(".");
    //获得jwt里面的loder
    //console.log(window.atob(load[1]));

    //转为json对象
    var obj = new Function("return" + window.atob(load[1]))();
    // console.log("token："+JSON.stringify(obj));
    return obj[data];
}



<!-- cookie操作 end-->