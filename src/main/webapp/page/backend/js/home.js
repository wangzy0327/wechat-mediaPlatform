$(function () {
        var myChart = echarts.init(document.getElementById('myfirstchart'),'macarons');
        var mapType = [
            // 23个省
            '广东', '青海', '四川', '海南', '陕西',
            '甘肃', '云南', '湖南', '湖北', '黑龙江',
            '贵州', '山东', '江西', '河南', '河北',
            '山西', '安徽', '福建', '浙江', '江苏',
            '吉林', '辽宁', '台湾',
            // 5个自治区
            '新疆', '广西', '宁夏', '内蒙古', '西藏',
            // 4个直辖市
            '北京', '天津', '上海', '重庆',
            // 2个特别行政区
            '香港', '澳门'
        ];
        var mapData;
        var max;
        $.ajax({
            url: "/wechat-tools/backend/home/map",
            type: "POST",
            dataType: "json",
            async:false,
            contentType: "application/json;charset=UTF-8",
            // 向后端传递的数据
            success: function (result) {
                if( 0 == result.code) {
                    console.log(result);
                    console.log(result.data);
                    // console.log(typeof result.data === 'string');
                    // console.log(typeof result.data);
                    mapData = result.data;
                    max = result.data.max;
                    // dataMap = JSON.parse(result);
                    console.log(mapData);
                    console.log(mapData["北京"]);
                    console.log(mapData["山西"]);
                    console.log(mapData["河北"]);
                    console.log(mapData["北京"]+10);
                }
            }
    });
    var data = [
            {name: '天津', value: (!mapData.天津)?0:mapData.天津},
            {name: '北京', value: (!mapData.北京)?0:mapData.北京},
            {name: '上海', value: (!mapData["上海"])?0:mapData["上海"]},
            {name: '重庆', value: (!mapData["重庆"])?0:mapData["重庆"]},
            {name: '河北', value: (!mapData["河北"])?0:mapData["河北"]},
            {name: '河南', value: (!mapData["河南"])?0:mapData["河南"]},
            {name: '云南', value: (!mapData["云南"])?0:mapData["云南"]},
            {name: '辽宁', value: (!mapData["辽宁"])?0:mapData["辽宁"]},
            {name: '黑龙江', value: (!mapData["黑龙江"])?0:mapData["黑龙江"]},
            {name: '湖南', value: (!mapData["湖南"])?0:mapData["湖南"]},
            {name: '安徽', value: (!mapData["安徽"])?0:mapData["安徽"]},
            {name: '山东', value: (!mapData["山东"])?0:mapData["山东"]},
            {name: '新疆', value: (!mapData["新疆"])?0:mapData["新疆"]},
            {name: '江苏', value: (!mapData["江苏"])?0:mapData["江苏"]},
            {name: '浙江', value: (!mapData["浙江"])?0:mapData["浙江"]},
            {name: '江西', value: (!mapData["江西"])?0:mapData["江西"]},
            {name: '湖北', value: (!mapData["湖北"])?0:mapData["湖北"]},
            {name: '广西', value: (!mapData["广西"])?0:mapData["广西"]},
            {name: '甘肃', value: (!mapData["甘肃"])?0:mapData["甘肃"]},
            {name: '山西', value: (!mapData["山西"])?0:mapData["山西"]},
            {name: '内蒙古', value: (!mapData["内蒙古"])?0:mapData["内蒙古"]},
            {name: '陕西', value: (!mapData["陕西"])?0:mapData["陕西"]},
            {name: '吉林', value: (!mapData["吉林"])?0:mapData["吉林"]},
            {name: '福建', value: (!mapData["福建"])?0:mapData["福建"]},
            {name: '贵州', value: (!mapData["贵州"])?0:mapData["贵州"]},
            {name: '广东', value: (!mapData["广东"])?0:mapData["广东"]},
            {name: '青海', value: (!mapData["青海"])?0:mapData["青海"]},
            {name: '西藏', value: (!mapData["西藏"])?0:mapData["西藏"]},
            {name: '四川', value: (!mapData["四川"])?0:mapData["四川"]},
            {name: '宁夏', value: (!mapData["宁夏"])?0:mapData["宁夏"]},
            {name: '海南', value: (!mapData["海南"])?0:mapData["海南"]},
            {name: '台湾', value: (!mapData["台湾"])?0:mapData["台湾"]},
            {name: '香港', value: (!mapData["香港"])?0:mapData["香港"]},
            {name: '澳门', value: (!mapData["澳门"])?0:mapData["澳门"]}
        ];

        option = {
            title: {
                text:'用户分布',
                subtext: '',
                left: 'center'
            },
            tooltip: {
                trigger: 'item',
                formatter: function (params) {
                    // return '地区: ' + params.name + '<br>客户数量: ' + params.value + '<br>订单成交率: ' +params.value/1000;
                    return '地区: ' + params.name + '<br>用户数量: ' + params.value ;
                }
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['客户']
            },
            visualMap: [{
                min: 0,
                max: max,
                left: 'left',
                top: 'bottom',
                text: ['多', '少'],           // 文本，默认为数值文本
                calculable: true
            }],

            toolbox: {
                show: true,
                orient: 'vertical',
                left: 'right',
                top: 'center',
                feature: {
                    dataView: {readOnly: false},
                    restore: {},
                    saveAsImage: {}
                }
            },
            series: [
                {
                    name: '用户数量',
                    type: 'map',
                    mapType: 'china',
                    roam: false,
                    label: {
                        normal: {
                            show: false,
                        },
                        emphasis: {
                            show: true
                        },

                    },
                    data: data,
                },
            ]
        };
        myChart.setOption(option);
    }
);

function randomData() {
    return Math.round(Math.random() * 1000);
}

