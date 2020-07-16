<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <%--引入goueasyjs文件网络地址--%>
    <script type="text/javascript" src="https://cdn.goeasy.io/goeasy-1.0.17.js"></script>
    <script>
        var goEasy = new GoEasy({
            host: 'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
            appkey: "BC-2557e7bba400456192dad9d9386f117e", //替换为您的应用appkey
        });
        //GoEasy-OTP可以对appkey进行有效保护,详情请参考​ ​

        goEasy.subscribe({
            channel: "zh_channel", //替换为您自己的channel
            onMessage: function (message) {
                console.log("Channel:" + message.channel + " content:" + message.content);
            }
        });

    </script>
</head>
<body></body>
</html>