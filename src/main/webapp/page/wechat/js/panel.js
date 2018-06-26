$(function() {
    categoryList();
    wxItemList();
    $('#searchInput').bind('input propertychange',function(){
        var searchInput = $.trim($('#searchInput').val());
        if(searchInput && searchInput!=''){
            // var url = "wechat-tools/wechat/wxItem/items.json?searchValue="+searchInput;
            searchKeyword(searchInput);
        }
        else if($.trim(searchInput)==''){
            wxItemList();
        }
    });
});

//获取url中的参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]);
    return null; //返回参数值
}

function getFansInfo(urlStr,key,value) {
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
                if ($.trim(key) == "code")
                    $.cookie('wxopenid', openId, {expires: 1 / 48});
                return openId;
            }
            else {
                $.alert('微信身份识别失败 \n ' + result.msg, "警告");
                return null;
            }
        }
    });
}

function getOpenId() {
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
            wxopenid = getFansInfo("/wechat-tools/Weixin/oauth","code",access_code);
            access_code = "";
            return wxopenid;
        }
    }else{
        return wxopenid;
    }
}

function searchKeyword(searchInput) {
    $.ajaxSettings.async = false;
    $.get("/wechat-tools/wechat/wxItem/items.json", {"searchValue": searchInput}).done(function (result) {
        console.log(result);
        console.log(result.code);
        if (result.code == 0) {
            var data = result.data;
            if(Array.isArray(data) && data.length === 0){
                $(".weui-loadmore.weui-loadmore_line")[0].style.display = "";
                $($('.weui-panel__bd')[0]).html("");
            }else{
                var str = "";
                for (var i in data) {
                    var tags = data[i].tags;
                    console.log(tags);
                    // str+='<a href="'+data[i].url+'?id='+data[i].id+'" class="weui-media-box weui-media-box_appmsg">' +
                    var openId = getOpenId();
                    str+='<a href="'+data[i].url+'?openId='+openId+'" class="weui-media-box weui-media-box_appmsg">' +
                        '<div class="weui-media-box__hd" style="width: 80px;height: 80px">' +
                        '<img class="weui-media-box__thumb" src="'+data[i].imgUrl+'" alt="">' +
                        '</div>' +
                        '<div class="weui-media-box__bd">' +
                        '<h4 class="weui-media-box__title">'+data[i].title+'</h4>' +
                        '<p class="weui-media-box__desc">'+data[i].description+'</p>' +
                        '<ul class="weui-media-box__info">' +
                        '<li class="weui-media-box__info__meta">'+data[i].category.name+'</li>';
                    if(tags){
                        for (j = 0; j < tags.length; j++) {
                            str = str + '<li class="weui-media-box__info__meta weui-media-box__info__meta_extra">'+tags[j].name+'</li>';
                        }
                    }
                    str+='</ul>' +
                        '</div>' +
                        '</a>'
                }
                $($('.weui-panel__bd')[0]).html(str);
                $(".weui-loadmore.weui-loadmore_line")[0].style.display = "none";
                $('#keyword')[0].style.display = "none";
            }
        } else {
            BootstrapDialog.alert({title:"提示",message:result.msg});
        }
    }).fail(function () {

    });
    $.ajaxSettings.async = true;
}
function wxItemList() {
    $.ajax({
        //            /wechat-tools/backend/wxItem/category.json
        url: "/wechat-tools/wechat/wxItem/items.json",
        type: "GET",
        dataType: "json",
        contentType: "application/json;charset=UTF-8",
        async:false,
        // 向后端传递的数据
        data: {"searchValue":""},
        success: function (result) {
            if(result.code == 0) {
                var data = result.data;
                if(Array.isArray(data) && data.length === 0){
                    $('.weui-loadmore.weui-loadmore_line')[0].style.display = "";
                }else{
                    var str = "";
                    for (var i in data) {
                        var tags = data[i].tags;
                        console.log(tags);
                        // str+='<a href="'+data[i].url+'?id='+data[i].id+'" class="weui-media-box weui-media-box_appmsg">' +
                        var openId = getOpenId();
                        str+='<a href="'+data[i].url+'?openId='+openId+'" class="weui-media-box weui-media-box_appmsg">' +
                            '<div class="weui-media-box__hd" style="width: 80px;height: 80px">' +
                            '<img class="weui-media-box__thumb" src="'+data[i].imgUrl+'" alt="">' +
                            '</div>' +
                            '<div class="weui-media-box__bd">' +
                            '<h4 class="weui-media-box__title">'+data[i].title+'</h4>' +
                            '<p class="weui-media-box__desc">'+data[i].description+'</p>' +
                            '<ul class="weui-media-box__info">' +
                            '<li class="weui-media-box__info__meta">'+data[i].category.name+'</li>';
                        if(tags){
                            for (j = 0; j < tags.length; j++) {
                                str = str + '<li class="weui-media-box__info__meta weui-media-box__info__meta_extra">'+tags[j].name+'</li>';
                            }
                        }
                        str+='</ul>' +
                            '</div>' +
                            '</a>'
                    }
                    $($('.weui-panel__bd')[0]).html(str);
                    $(".weui-loadmore.weui-loadmore_line")[0].style.display = "none";
                    $('#keyword')[0].style.display = "none";
                }
            }else{
                BootstrapDialog.alert({title:"提示",message:result.msg});
            }
        },
        error: function(){

        }
    });
}

function categoryList(){
    $.ajax({
        url: "/wechat-tools/wechat/wxItem/categorys.json",
        type: "GET",
        dataType: "json",
        contentType: "application/json;charset=UTF-8",
        async:false,
        // 向后端传递的数据
        success: function (result) {
            if(result.code == 0) {
                var data = result.data;
                if(Array.isArray(data) && data.length === 0){
                }else{
                    var primary = new Object();
                    primary.name = '全部';
                    var $primary = JSON.stringify(primary).replace(/\"/g,"'");
                    var str = "";
                    str+='<a href="javascript:;" style="display:inline-block;vertical-align:top;text-align:center;font-size:12px;line-height:18px;     margin:3px 8px 3px 2px;padding: 0 5px;border-radius:3px;border: 1px solid #adadad;line-height: 16px;color:#777" class="weui-btn weui-btn_mini weui-btn_default" onclick="clickOnTag(event,'+$primary+')">'+primary.name+'</a>';
                    for (var i in data) {
                        var category = data[i];
                        var $category = JSON.stringify(category).replace(/\"/g,"'");
                        str+='<a href="javascript:;" style="display:inline-block;vertical-align:top;text-align:center;font-size:12px;line-height:18px;     margin:3px 8px 3px 2px;padding: 0 5px;border-radius:3px;border: 1px solid #adadad;line-height: 16px;color:#777" class="weui-btn weui-btn_mini weui-btn_default" onclick="clickOnTag(event,'+$category+')">'+data[i].name+'</a>';
                        // str+='<a href="javascript:;" style="display:inline-block;vertical-align:top;text-align:center;font-size:12px;line-height:18px;     margin:3px 8px 3px 2px;padding: 0 5px;border-radius:3px;border: 1px solid #adadad;line-height: 16px;color:#777" class="weui-btn weui-btn_mini weui-btn_default" onclick="clickOnTag(event,\''+data[i].name+'\')">'+data[i].name+'</a>';
                    }
                    $($('.button_sp_area')[0]).html(str);
                }
            }else{
                BootstrapDialog.alert({title:"提示",message:result.msg});
            }
        },
        error: function(){

        }
    });
}


function clickOnTag(event,tag) {
    event.preventDefault();
    // window.location = "/wechat-tools/wechat/wxItem/items.json?searchValue="+tag;
    // console.log(tag);
    searchKeyword(tag.name);
    console.log($($('#keyword.column-tag')[0]).text());
    console.log(typeof (tag));
    console.log(tag);
    console.log(tag.name);
    if($.trim(tag.name) != $.trim('全部')){
        $($('.weui-btn.weui-btn_mini.weui-btn_default.column-tag')[0]).text(tag.name);
        $('#keyword')[0].style.display = "";
    }
}