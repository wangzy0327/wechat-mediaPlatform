$(function(){
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
            getFansInterest("/wechat-tools/Weixin/oauthInterest","code",access_code);
            access_code = "";
        }
    } else {
        getFansInterest("/wechat-tools/Weixin/accountFanInterest","openId",wxopenid);
    }
})
    //获取url中的参数
    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r != null) return unescape(r[2]);
        return null; //返回参数值
    }

    function getFansInterest(urlStr,key,value) {
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
                    var categories = data.categories;
                    var openId = data.accountFans.openId;
                    console.log("openId:"+openId);
                    var nickName = data.accountFans.nicknameStr;
                    var str = '';
                    str+='<label class="weui-form-preview__label">OpenID</label>\n' +
                        '<em class="weui-form-preview__value" style="font-size: 1.1em">'+openId+'</em>';
                    $($('.weui-form-preview__hd')[0]).html(str);
                    str = '';
                    str+='<label class="weui-form-preview__label">昵称</label>\n' +
                        '<em class="weui-form-preview__value" style="font-size: 1.1em">'+nickName+'</em>';
                    $($('.weui-form-preview__hd')[1]).html(str);
                    str = '';
                    if(categories&&categories.length>0){
                        for (var i = 0;i<categories.length;i++) {
                            str+='<div class="weui-form-preview__item">\n' +
                                '<label class="weui-form-preview__label">兴趣'+(i+1)+'</label>\n' +
                                '<span class="weui-form-preview__value">'+categories[i].name+'</span>\n' +
                                '</div>';
                        }
                    }else{
                        str+='<div class="weui-form-preview__item">\n' +
                            '<label class="weui-form-preview__label">兴趣</label>\n' +
                            '<span class="weui-form-preview__value">暂无</span>\n' +
                            '</div>';
                    }
                    $($('.weui-form-preview__bd')[0]).html(str);
                    if($.trim(key) == "code")
                        $.cookie('wxopenid',openId,{expires:1/48});
                }
                else {
                    $.alert('微信身份识别失败 \n '+result.msg,"警告");
                }
            }
        });
    }