$(function () {
    var wxopenid = $.cookie('wxopenid');
    var access_code = getUrlParam('code');
    interestList();
    if (wxopenid == null) {
        if (access_code == null) {
            var fromurl = window.location.href;
            console.log(fromurl);
            var url = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx88ddca24ac18c8b8&redirect_uri=' + encodeURIComponent(fromurl) + '&response_type=code&scope=snsapi_userinfo&state=STATE%23wechat_redirect&connect_redirect=1#wechat_redirect';
            location.href = url;
        }
        else {
            getFansInterestInfo("/wechat-tools/Weixin/oauthFansInterestInfo","code",access_code);
            access_code = "";
        }
    } else {
        getFansInterestInfo("/wechat-tools/Weixin/FansInterestInfo","openId",wxopenid);
    }

    $("#submitInterest").click(function() {
        var checked = new Array();
        var flag = 0;
        $("input:checkbox:checked").each(function () {
            checked.push(parseInt($(this).attr("id")));
        });
        console.log("openId:"+wxopenid);
        console.log("interest:"+checked);
        if(checked.length<1){
            $.alert("选取兴趣个数至少一个！", "警告！");
        }else{
            $.ajax({
                type: 'POST',//提交方式 post 或者get
                url: "/wechat-tools/Weixin/editInterest",//提交到那里 后他的服务
                traditional: true,
                data:"openId="+wxopenid+"&interests="+checked,
                success:function(result){
                    if(result.code == 0){
                        window.location.href = "interest_preview.html";
                    }
                },
                error:function(){
                    $.alert("兴趣录入异常！", "警告！");
                }
            });
        }
    });

})
    function interestList(){
        $.ajax({
            type: "get",
            url: "/wechat-tools/Weixin/category.json",
            async:false,
            success: function (result) {
                var code = result.code;
                var data = result.data;
                if (code == 0) {
                    var str = "";
                    for (var i in data) {
                        str+='<label class="weui-cell weui-check__label" for="'+data[i].id+'">\n' +
                            '<div class="weui-cell__hd">\n' +
                            '<input type="checkbox" class="weui-check" name="category" id="'+data[i].id+'">\n' +
                            '<i class="weui-icon-checked"></i>\n' +
                            '</div>\n' +
                            '<div class="weui-cell__bd">\n' +
                            '<p>'+data[i].name+'</p>\n' +
                            '</div>\n' +
                            '</label>'
                    }
                    $("#interest").html(str);
                }
            }
        });
    }
    //获取url中的参数
    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r != null) return unescape(r[2]);
        return null; //返回参数值
    }

    function getFansInterestInfo(urlStr,key,value) {
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
                    var openId = data.id;
                    console.log("openId:"+openId);
                    var str = '';
                    for(var i in categories){
                        console.log("categoryId:"+categories[i].id);
                        $("#"+categories[i].id+"").attr("checked",true);
                    }
                    if($.trim(key) == "code")
                        $.cookie('wxopenid',openId,{expires:1/48});
                }
                else {
                    $.alert('微信身份识别失败 \n '+result.msg,"警告");
                }
            }
        });
    }