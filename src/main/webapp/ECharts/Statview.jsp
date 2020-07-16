<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="${pageContext.request.contextPath}/js/echarts.min.js"></script>

    <script type="text/javascript" src="../js/china.js"></script>
    <script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
    <%--第一次页面加载数据展示--%>
    <%--   <script>
           $(function () {
               // 基于准备好的dom，初始化echarts实例
               var myChart = echarts.init(document.getElementById('main'));
               //向后台发送请求获取数据
               var series = [];
               var months;
               $.get('${path}/yxUser/selectAllBySexAndCreateDate', function (data) {
                   $.each(data, function (index, monts) {
                       //数据填充
                       series.push(
                           {
                               name: monts.sex, //选项卡名称
                               type: 'bar', //图表类型
                               data: monts.couts
                           }
                       )
                       months = monts.months;
                   })
                   // 指定图表的配置项和数据
                   var option = {
                       title: {
                           text: '用户注册数量趋势图', //标题
                           show: true,
                           link: "${path}/main/main.jsp",
                           target: 'self',
                           subtext: "纯属虚构"
                       },
                       tooltip: {}, //鼠标提示
                       legend: {
                           /*data: ['小男孩', '小姑娘']*/ //选项卡
                           //data: data.months
                       },
                       xAxis: {
                           data: months //横坐标
                       },
                       yAxis: {}, //纵坐标 自适应
                       series: series
                   };
                   // 使用刚指定的配置项和数据显示图表。
                   myChart.setOption(option);
               }, "JSON");
           });

       </script>--%>

    <script>
        $(function () {
            setTimeout('init()', 2000); //延迟1秒
        })

        function init() {
            $.ajax({
                url: "${path}/yxUser/selectOneGoEasy",
            })
        }
    </script>
    <%--用于前台推送--%>
    <script type="text/javascript" src="https://cdn.goeasy.io/goeasy-1.0.17.js"></script>
    <script type="text/javascript">
        /*初始化GoEasy对象*/
        var goEasy = new GoEasy({
            host: 'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
            appkey: "BC-2557e7bba400456192dad9d9386f117e", //替换为您的应用appkey
        });
        $(function () {

            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));
            /*接收通道中的消息消息*/
            goEasy.subscribe({
                channel: "zh_channel", //替换为您自己的channel
                onMessage: function (message) {
                    //获取内容
                    var datas = message.content;
                    console.log("推送消息为" + datas);

                    //将json字符串转为javascript对象
                    var data = JSON.parse(datas);
                    var series = [];
                    var months;
                    $.each(data, function (index, monts) {
                        //数据填充
                        series.push(
                            {
                                name: monts.sex, //选项卡名称
                                type: 'bar', //图表类型
                                data: monts.couts
                            }
                        )
                        months = monts.months;
                    })
                    // 指定图表的配置项和数据
                    var option = {
                        title: {
                            text: '用户注册数量趋势图', //标题
                            show: true,
                            link: "${path}/main/main.jsp",
                            target: 'self',
                            subtext: "纯属虚构"
                        },
                        tooltip: {}, //鼠标提示
                        legend: {
                            data: ['小男孩', '小姑娘'] //选项卡
                            //data: data.months
                        },
                        xAxis: {
                            data: months //横坐标
                        },
                        yAxis: {}, //纵坐标 自适应
                        series: series
                    };
                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);
                }
            })
        })
    </script>
</head>
<body>
<div align="center">
    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div id="main" style="width: 600px;height:400px;"></div>
</div>
</body>
</html>