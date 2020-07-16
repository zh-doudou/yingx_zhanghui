<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/boot/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/mycss/show.css"/>
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/boot/js/bootstrap.js"></script>
    <style>


        .div1{
            /*这里的宽和高必须固定*/
            height: 50%;
            width: 100%;
            display: flex;/*设置为弹性容器*/
            align-items: center; /*定义div1的元素垂直居中*/
            justify-content: center; /*定义div1的里的元素水平居中*/
        }
    </style>
</head>
<body>
<!-- 导航条 -->
<nav class="div1 navbar navbar-default  navbar-fixed-bottom">
    <!-- 容器水平排列 -->
    <div class="container-fluid col-md-11">

        <div class="row">
            <div class="text-center ">
                zhanghui@zparkhr.com
            </div>
        </div>
    </div>
</nav>
</body>
</html>