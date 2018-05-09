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


// 监听页面转动信息
document.addEventListener("changedTo", function(e){
    updateArr();
    // 获取changeTo之后的页面索引
    let active = document.getElementsByClassName('page current')[0].id;
    index = +active.split('-')[1]; // 当前页
    pageInfo[index] = pageInfo[index] || 0;
    // 发生changeTo事件的时间点
    time = Date.now();
});

// 更新当前页面的浏览时长
function updateArr(){
    pageInfo[index] += Date.now() - time;
    time = Date.now();
    return pageInfo;
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