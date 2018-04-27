function setCookie(cname,cvalue,exmin){
    var d = new Date();
    d.setTime(d.getTime()+(exmin*5*60*1000));
    var expires = "expires="+d.toGMTString();
    document.cookie = cname+"="+cvalue+"; "+expires;
}
function getCookie(cname){
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i].trim();
        if (c.indexOf(name)==0) { return c.substring(name.length,c.length); }
    }
    return "";
}
function checkCookie(){
    var openId=getCookie("openId");
    console.log("openId:"+openId);
    if (openId!=""){
        $.ajax({
            url : "/wechat-tools/Weixin/oauth2ByOpenId?openId="+openId,//请求地址
            dataType : "json",//数据格式
            type : "get",//请求方式
            async : true,//是否异步请求
            success : function(result) {   //如何发送成功
                console.log(result);
                var user = result.data;
                if (result.status == 0) {
                    var user = result.data
                    console.log($(".user-header").children[0]);
                    $(".user-header").children[0].attr("src", user.headImgUrl);
                    $(".list-right").children[0].text("昵称：" + user.nickname);
                    $(".list-right").children[1].text("openId：" + user.openId);
                    $(".list-right").children[2].text("性别：" + user.sexDesc);
                }else {
                    window.location.href = "/page/error404.html";
                }
            },
        })
    }
    else {
        $.ajax({
            url : "/wechat-tools/Weixin/oauth2Api?resultUrl="+window.location.href,//请求地址
            dataType : "json",//数据格式
            type : "get",//请求方式
            async : true,//是否异步请求
            success : function(result) {   //如何发送成功
                console.log(result);
                if (result.status == 0) {
                    console.log(result.data);
                    window.location.href = result.data;
                }else {
                    window.location.href = "/page/error404.html";
                }
            },
        })
        if (openId!="" && openId!=null){
            setCookie("openId",openId,5);
        }
    }
}
$(document).ready(function() {
    checkCookie();
})