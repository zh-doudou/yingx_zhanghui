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
    <title>192聊天室</title>
    <%--引入GoEasy的js文件--%>
    <script type="text/javascript" src="https://cdn.goeasy.io/goeasy-1.0.17.js"></script>
    <script src="${path}/bootstrap/js/jquery.min.js"></script>
    <script>
        /*初始化GoEasy对象*/
        var goEasy = new GoEasy({
            host: 'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
            appkey: "BC-49c4bb3ed91945448c35358477615835", //替换为您的应用appkey
        });

        $(function () {

            var contentMsg;

            /*接收消息*/
            goEasy.subscribe({
                channel: "192ToChatWith", //替换为您自己的channel
                onMessage: function (message) {
                    //获取导内容
                    var msgs = message.content;

                    //判断改消息是否是自己发的
                    if (msgs != contentMsg) {
                        //设置内容样式
                        var msgStyle = ("" +
                            "<div style='width: auto;height: 30px;'>" +
                            "<div style='float: left;background-color: #05E8FA;border-radius:100px'>" +
                            "" + msgs + "" +
                            "</div>" +
                            "</div>"
                        );

                        //渲染页面  将发送的结果放置在展示框的右侧
                        $("#showMsg").append(msgStyle);
                    }
                }
            });

            //发送消息
            $("#sendMsg").click(function () {
                //获取输入框的内容
                var msg = $("#msg").val();

                //将自己输入的内容赋值到变量上
                contentMsg = msg;

                //通过GoEasy发送数据
                goEasy.publish({
                    channel: "192ToChatWith", //替换为您自己的channel
                    message: msg, //替换为您想要发送的消息内容
                    onSuccess: function () {
                        //清空输入框
                        $("#msg").val("");

                        //设置内容样式
                        var msgStyle = ("" +
                            "<div style='width: auto;height: 30px;'>" +
                            "<div style='float: right;background-color: #78FA84;border-radius:100px'>" +
                            "" + msg + "" +
                            "</div>" +
                            "</div>"
                        );

                        //渲染页面  将发送的结果放置在展示框的右侧
                        $("#showMsg").append(msgStyle);
                    },
                    onFailed: function (error) {
                        alert("消息发送失败，错误编码：" + error.code + " 错误信息：" + error.content);
                    }
                });


            });
        });
    </script>

</head>
<body>
<div align="center">
    <h1>192聊天室</h1>
    <%--聊天室框--%>
    <div style="width: 600px;height: 700px;border: 3px #4cae4c solid ">

        <%--聊天内容展示区域--%>
        <div id="showMsg" style="width: 594px;height: 600px;border: 3px #e4b9b9 solid "></div>
        <%--输入发送聊天内容--%>
        <div style="width: 594px;height: 88px;border: 3px #ccaadd solid ">
            <%--输入框--%>
            <textarea id="msg" style="width: 500px;height: 82px;border: 3px black solid;float: left "></textarea>
            <%--提交按钮--%>
            <button id="sendMsg" style="width: 84px;height: 88px;border: 3px #2aabd2 solid;float: right ">发送</button>
        </div>
    </div>
</div>
</body>
</html>