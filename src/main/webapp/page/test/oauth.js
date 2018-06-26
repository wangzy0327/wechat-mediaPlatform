function getWxOpenId() {
    var openId = getUrlParam('openId');
    return openId;
}

function getOpenId(urlStr,key,value) {
    var stringJson = '{"' + key + '":"' + value + '"}';
    var json = JSON.parse(stringJson);
    $.ajax({
        type: 'get',
        url: urlStr,
        async: false,
        data: JSON.parse(stringJson),
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        success: function (result) {
            var data = result.data;
            if (data != null) {
                var openId = data.openId;
                console.log("openId:" + openId);
                if ($.trim(key) == "code")
                    $.cookie('wxopenid', openId, {expires: 1 / 48});
                return openId;
            }
            else {
                alert('微信身份识别失败 \n ');
            }
        }
    });
}

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]);
    return null; //返回参数值
}