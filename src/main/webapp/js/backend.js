$(document).ready(function () {
    $("#url_btn").click(function () {
        var url = $("#url").val();
        console.log(url);
        if(url == ""){
            alert("请输入URL");
            return false;
        }
        $.ajax({
            url: "/urlSubmit/result",
            type: "POST",
            dataType: "json",
            contentType: "application/json;charset=UTF-8",
            // 向后端传递的数据
            data: JSON.stringify({
                "url": url
            }),
            success: function (data) {
                <!-- 处理后端返回的数据 -->
                if(data.error){
                    alert(data.error);
                }else{
                    alert("操作成功!");
                }
                window.location.reload();
                //
            },
            error: () => {
                console.log("err");
            }
        });
    });
});
// window.addEventListener("unload", () => {
//     alert("close");
// });
window.onbeforeunload= function(event) { return confirm("确定离开此页面吗？"); }

window.onunload = function(event) { return confirm("确定离开此页面吗？"); }
// $(function(){
//     // var result = "这是段落的一些文本";
//     $("button").click(function(){
//         console.log("11111111");
//         $.ajax({url:"text1.txt",success:function(result){
//             $("#div1").html(result);
//         }});
//         console.log("22222222");
//     });
// });