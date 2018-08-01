var share={};
share.data = {};
$(function(){
    var CURRENT_USER = $.cookie('CURRENT_USER');
    if(CURRENT_USER == null){
        var url = location.href.split('#')[0].toString();//url不能写死
        console.log("动态url:"+url);
        $.ajax({
            type : "get",
            url : "/wechat-tools/Weixin/jsApiCheck",
            contentType: "application/json; charset=utf-8",
            dataType : "json",
            async : false,
            data:{url:url},
            success : function(result) {
                var data = result.data;
                console.log("data:"+data);
                wx.config({
                    debug: false,////生产环境需要关闭debug模式
                    appId: data.appId,//appId通过微信服务号后台查看
                    timestamp: data.timestamp,//生成签名的时间戳
                    nonceStr: data.nonceStr,//生成签名的随机字符串
                    signature: data.signature,//签名
                    jsApiList: [//需要调用的JS接口列表
                        'checkJsApi',//判断当前客户端版本是否支持指定JS接口
                        'onMenuShareTimeline',//分享给好友
                        'onMenuShareAppMessage'//分享到朋友圈
                    ]
                });
                var itemId = url.slice(url.lastIndexOf("/")+1,url.indexOf(".html"));
                console.log("itemId:"+itemId);
                $.ajax({
                    type: 'POST',
                    url: "/wechat-tools/wechat/wxItem/item.json",//提交到那里 后他的服务
                    async: false,
                    traditional: true,
                    data:"itemId="+itemId,
                    success:function(result){
                        if(result.code == 0){
                            var data = result.data;
                            var itemId = data.itemId;
                            var title = data.title;
                            var description = data.description;
                            var url = data.url;
                            var imgUrl = data.imgUrl;
                            console.log("itemId:" + itemId);
                            console.log("title:" + title);
                            console.log("description:" + description);
                            console.log("url:" + url);
                            console.log("imgUrl:" + imgUrl);
                            share.data.itemId = itemId;
                            share.data.title = title;
                            share.data.description = description;
                            share.data.url = url;
                            share.data.imgUrl = imgUrl;
                        }
                    },
                    error:function(){
                    }

                });
            },
            error: function(xhr, status, error) {
                //alert(status);
                //alert(xhr.responseText);
            }
        });

    }

});