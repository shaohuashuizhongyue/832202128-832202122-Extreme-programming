// 指定图表的配置项和数据
export const option = {
    title: { text: '简单饼状图' },
    tooltip: {},
    xAxis: {
        data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"],
        name: '产品'
    },
    yAxis: {},
    series: [{
        name: '销量',
        type: 'bar', //柱状图
        //type: 'line', //线性
        /*type: 'pie', //扇形
        radius : '55%',
        center: ['50%', '60%'],*/
        data: [],
        itemStyle: {  /*图的颜色*/
            normal: {
                color: 'hotpink' /*粉红色*/ /*blue 蓝色 green 绿色*/
            }
        }
    }]
}