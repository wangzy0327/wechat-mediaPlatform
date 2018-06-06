$(function () {
    var wxopenid = $.cookie('wxopenid');
    var access_code = getUrlParam('code');
    if (wxopenid == null) {
        if (access_code == null) {
            var fromurl = window.location.href;
            console.log(fromurl);
            var url = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx88ddca24ac18c8b8&redirect_uri=' + encodeURIComponent(fromurl) + '&response_type=code&scope=snsapi_userinfo&state=STATE%23wechat_redirect&connect_redirect=1#wechat_redirect';
            location.href = url;
        }
        else {
            getFansInfo("/wechat-tools/Weixin/oauth","code",access_code);
        }
    } else {
        access_code = "";
        getFansInfo("/wechat-tools/Weixin/accountFanInfo","openId",wxopenid);
    }

    //获取url中的参数
    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r != null) return unescape(r[2]);
        return null; //返回参数值
    }

    function getFansInfo(urlStr,key,value) {
        var stringJson ='{"'+key+'":"'+value+'"}';
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
                    var str = "";
                    $(".weui-cell__ft").each(function (i) {
                        switch (i) {
                            case 0:
                                str = (data.subscribeStatus == 1) ? '已关注' : '未关注';
                                $($(".weui-cell__ft")[i]).html(str);
                                break;
                            case 1:
                                str = data.openId;
                                $($(".weui-cell__ft")[i]).html(str);
                                break;
                            case 2:
                                str = '<img src="' + data.headimgurl + '">';
                                $($(".weui-cell__ft")[i]).html(str);
                                break;
                            case 3:
                                str = data.nicknameStr;
                                $($(".weui-cell__ft")[i]).html(str);
                                break;
                            case 4:
                                if (data.gender == 1)
                                    str = "男";
                                else if (data.gender == 2)
                                    str = "女";
                                else {
                                    str = "未知";
                                }
                                $($(".weui-cell__ft")[i]).html(str);
                                break;
                            case 5:
                                str = data.country + " " + data.province + " " + data.city;
                                $($(".weui-cell__ft")[i]).html(str);
                                break;
                            case 6:
                                str = data.language;
                                $($(".weui-cell__ft")[i]).html(str);
                                break;
                            case 7:
                                var date = new Date();
                                date.setTime(data.subscribeTime);
                                str = date.getFullYear() + "年" + (date.getMonth() + 1) + "月" + date.getDate() + "日";
                                $($(".weui-cell__ft")[i]).html(str);
                                break;
                            case 8:
                                str = (data.remark != "") ? data.remark : "无";
                                $($(".weui-cell__ft")[i]).html(str);
                                break;
                        }
                    });
                    if($.trim(key) == "code")
                        $.cookie('wxopenid',data.openId,{expires:1/48});
                }
                else {
                    alert('微信身份识别失败 \n ' + result.msg);
                }
            }
        });
    }

});