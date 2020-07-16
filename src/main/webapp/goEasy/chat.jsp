<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/boot/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/btn.css">
    <%-- boostrap核心.js --%>
    <script src="${pageContext.request.contextPath}/boot/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="https://cdn.goeasy.io/goeasy-1.0.17.js"></script>
    <%-- jquery核心.js --%>
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script>
        /*初始化GoEasy对象*/
        var goEasy = new GoEasy({
            host: 'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
            appkey: "BC-2557e7bba400456192dad9d9386f117e", //替换为您的应用appkey
        });


        $(function () {
            var contentMsg;
            //接收消息
            goEasy.subscribe({
                channel: "zh_channel", //替换为您自己的channel
                onMessage: function (message) {
                    //获取导内容
                    var msgs = message.content;

                    //判断改消息是否是自己发的
                    if (msgs != contentMsg) {
                        var div = $("  <div style=\"width:auto;height:30px;border-radius: 30px 30px 30px 30px\">\n" +
                            "                <div style=\"color: #0f0f0f ;float: left;background-color: #2ca024;\">\n" +
                            "                      " + msgs + "\n" +
                            "                </div>\n" +
                            "\n" +
                            "            </div>")
                        $("#showMsg").append(div)
                    }
                }
            });

            //发送消息

            $("#sendMsg").click(function () {
                //将自己输入的内容赋值到变量上
                var msg = $("#msg").val();
                contentMsg = msg;
                //通过massgae发送数据
                goEasy.publish({
                    channel: "zh_channel",
                    message: msg,
                    onSuccess: function () {
                        $("#msg").val("");
                        //渲染页面 将发送的结果放置在展示框的右侧
                        var div = $("  <div style=\"width:auto;height:30px;border-radius: 30px 30px 30px 30px\">\n" +
                            "                <div style=\"color: #0f0f0f ;float: right;background-color: #fb7a80;\">\n" +
                            "                      " + msg + "\n" +
                            "                </div>\n" +
                            "\n" +
                            "            </div>")
                        $("#showMsg").append(div)
                    },
                    onFailed: function (error) {
                        alert("消息发送失败，错误编码：" + error.code + " 错误信息：" + error.content);
                    }
                })
            })
        })
    </script>
</head>
<body>
<div align="center">
    <div style="border: #c12e2a solid 1px;width: 800px;height: 700px">
        <%--上部--%>
        <div style="width: 795px; height: 50px ;border: #2ca024 solid 2px">
            zh自定义聊天室
        </div>
        <%--中部内容展示--%>
        <div id="showMsg" style="width: 795px; height: 500px ;border: #0f0f0f solid 2px">

        </div>
        <%--下部输入框展示--%>

        <div class="row">
            <div class="col-lg-10">
                <div class="input-group">
                    <input id="msg" type="text" class="form-control" placeholder="在这里输入文字吧">
                    <span class="input-group-btn">
                            <button id="sendMsg" class="btn btn-default" type="button">发送!</button>
                    </span>
                </div>
            </div>
        </div>

    </div>
</div>
</body>
</html>