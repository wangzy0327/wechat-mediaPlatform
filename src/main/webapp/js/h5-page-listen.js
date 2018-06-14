/**
 * 监听H5翻页
 *
 */

var prevIndex = 0, // 上一页
    activeIndex = 0, // 当前页
    startTime = new Date().getTime(),
    pageInfo = [],
    isInitArr = true,
    index = 0,
    time = Date.now();


// m类监听页面转动信息
document.addEventListener("changedTo", function(e){
    updateArr();
    // 获取changeTo之后的页面索引
    let active = document.getElementsByClassName('page current')[0].id;
    index = +active.split('-')[1]; // 当前页
    pageInfo[index] = pageInfo[index] || 0;
    // 发生changeTo事件的时间点
    time = Date.now();
});

// m2类型页面转动信息
document.addEventListener("mousemove", function(e){
    updateArr();
    // 获取changeTo之后的页面索引
    let active = document.getElementsByClassName('page-list')[0].dataset.currPage;
    index = parseInt(active); // 当前页
    pageInfo[index] = pageInfo[index] || 0;
    // 发生changeTo事件的时间点
    time = Date.now();
});

//获取url中的参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]);
    return null; //返回参数值
}

$(function(){
    oauthAuthention();
});

function oauthAuthention() {
    var CURRENT_USER = $.cookie('CURRENT_USER');
    if(CURRENT_USER == null){
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
                getOpenId("/wechat-tools/Weixin/oauth","code",access_code);
                access_code = "";
                $.cookie('wxopenid',openId,{expires:1/48});
            }
        } else {

        }
    }
}


window.onbeforeunload= function(event) {
    oauthAuthention();
    updateArr();
    pageSize = $('.page-list li').length;
    var spendTime = parseInt(sumArr(pageInfo)/1000/pageSize);
    console.log(pageInfo);
    console.log("pageSize:"+pageSize);
    console.log("sumTime:"+sumArr(pageInfo));
    var url = window.location.href;
    var itemId = url.slice(url.indexOf("/push/")+"/push/".length,url.indexOf(".html"));
    var openId = $.cookie('wxopenid');
    console.log("openId:"+openId);
    console.log("itemId:"+itemId);
    console.log("spendTime:"+spendTime);
    if(typeof(openId)!= undefined && spendTime>=1){
        $.ajax({
            type: 'POST',
            url: "/wechat-tools/Weixin/readWxItem",//提交到那里 后他的服务
            async: false,
            traditional: true,
            data:"openId="+openId+"&itemId="+itemId+"&spendTime="+spendTime,
            success:function(result){
                if(result.code == 0){
                }
            },
            error:function(){
            }

        });
    }
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
            }
            else {
                alert('微信身份识别失败 \n ');
            }
        }
    });
}




function sumArr(arr){
    return eval(arr.join("+"))
}//直接把他变成各个数的加法运算字符串

// 更新当前页面的浏览时长
function updateArr(){
    pageInfo[index] += Date.now() - time;
    time = Date.now();
    return pageInfo;
}

function getContent(){
    let text = "";
    // 未加载完成获取不到信息
    if (!window.updateArr){
        return text;
    }
    let info = window.updateArr();
    info.forEach(function(val, index){
        if(val === undefined){
            return;
        }
        text = text + "页面" + (index+1) + "的浏览时间为：" + parseInt(val / 1000) + "秒<br>";
    });
    return text;
}

setInterval(function(){
    let text = getContent();
    console.log(text);
}, 1000);


function initLabel() {
    if (parent.module) {
        var labelNames = parent.module.data.labelNames;

        // 加载当前页的标签数据
        if (labelNames && labelNames.length) {
            var label_view_ele = parent.document.getElementById('label-view').children[0],
                html = '',
                activeLabelArr = labelNames[activeIndex];
            for (var i in activeLabelArr) {
                // todo html生成格式应该与createPageLabel 一样
                var activeLabe = activeLabelArr[i];
                html += '<div class="box-label label-close" data-i="' + i + '">';
                html += '<span data-id="' + activeLabe.id + '">' + activeLabe.name + '</span>';
                html += '<span class="label-hover label-hover-bg"></span>';
                html += '<span class="label-hover"><i class="iconfont icon-close">&#xe601;</i></span>';
                html += '</div>';
            }
            label_view_ele.innerHTML = html;
        }
    }
}