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

var listen = {};
listen.service = {
    getWxOpenId:function () {
        var openId = getUrlParam("openId");
        return openId;
    },
    sumArr:function(arr) {
        return eval(arr.join("+"))
    },//直接把他变成各个数的加法运算字符串

};

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

});


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

window.onbeforeunload= function(event) {
    var openId = listen.service.getWxOpenId();
    var pageSize = $('.page-list li').length;
    var spendTime = parseInt(listen.service.sumArr(pageInfo)/1000/pageSize);
    console.log(pageInfo);
    console.log("pageSize:"+pageSize);
    console.log("sumTime:"+listen.service.sumArr(pageInfo));
    var url = window.location.href;
    var itemId = url.slice(url.lastIndexOf("/")+1,url.indexOf(".html"));
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