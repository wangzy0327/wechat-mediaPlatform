wx.ready(function () {
    var link = window.location.href;
    var protocol = window.location.protocol;
    var host = window.location.host;
    //分享朋友圈
    wx.onMenuShareTimeline({
        title: share.data.title,
        link: share.data.url,
        imgUrl: share.data.imgUrl,// 自定义图标
        trigger: function (res) {
            // 不要尝试在trigger中使用ajax异步请求修改本次分享的内容，因为客户端分享操作是一个同步操作，这时候使用ajax的回包会还没有返回.
            //alert('click shared');
        },
        success: function (res) {
            console.log("shared success MenuShareTimeline!");
            //some thing you should do
            var openId = getWxOpenId();
            // var openId = "ohIIkv0EWHRMIFNgCn9iM4obFktI";
            var url = window.location.href.split('#')[0].toString();
            var itemId = url.slice(url.lastIndexOf("/")+1,url.indexOf(".html"));
            $.ajax({
                type: 'POST',
                url: "/wechat-tools/Weixin/shareWxItem",//提交到那里 后他的服务
                async: false,
                traditional: true,
                data:"openId="+openId+"&itemId="+itemId,
                success:function(result){
                    if(result.code == 0){
                        alert("记录分享成功！");
                    }
                },
                error:function(){
                }

            });
        },
        cancel: function (res) {
            //alert('shared cancle');
        },
        fail: function (res) {
            //alert(JSON.stringify(res));
        }
    });
    //分享给好友
    wx.onMenuShareAppMessage({
        title: share.data.title, // 分享标题
        desc: share.data.description, // 分享描述
        link: share.data.url, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
        imgUrl: share.data.imgUrl, // 自定义图标
        type: 'link', // 分享类型,music、video或link，不填默认为link
        dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
        success: function () {
            // 用户确认分享后执行的回调函数
            console.log("shared success ShareAppMessage!");
            var openId = getWxOpenId();
            var url = window.location.href.split('#')[0].toString();
            // var openId = "ohIIkv0EWHRMIFNgCn9iM4obFktI";
            var itemId = url.slice(url.lastIndexOf("/")+1,url.indexOf(".html"));
            $.ajax({
                type: 'POST',
                url: "/wechat-tools/Weixin/shareWxItem",//提交到那里 后他的服务
                async: false,
                traditional: true,
                data:"openId="+openId+"&itemId="+itemId,
                success:function(result){
                    if(result.code == 0){
                        alert("记录分享成功！");
                    }
                },
                error:function(){
                }

            });
        },
        cancel: function () {
            // 用户取消分享后执行的回调函数
        }
    });
    wx.error(function (res) {
        alert(res.errMsg);
    });
});