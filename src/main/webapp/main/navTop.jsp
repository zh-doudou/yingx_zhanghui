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

    <script>
        $(function () {
            $("#exit").click(function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/yxAdmin/removeSession",
                    datatype: "json",
                    success: function (data) {
                        if (data.remove == 'ok') {
                            window.parent.location.href = "${pageContext.request.contextPath}/login/login.jsp";
                        }
                    }
                })
            })
        })
    </script>
</head>

<body>
<!-- 导航条 -->
<nav class=" navbar navbar-inverse">
    <!-- 容器水平排列 -->
    <div class="container-fluid col-md-11">
        <div class="row">
            <div class="navbar-header col-sm-3">
                <p class="navbar-text navbar-right">应学后台管理系统</p>
                <img src="${pageContext.request.contextPath}/mycss/yx.png" alt="Brand" width="70px" height="60px">
                <!-- 画面变形隐藏 -->
                <button class="navbar-toggle collapsed" data-toggle="collapse" data-target="#ad">
                    <span class="glyphicon glyphicon-list"></span>
                </button>
            </div>


            <!-- 需要隐藏的div -->
            <div class=" collapse navbar-collapse " id="ad">
                <!-- 用户状态选择 -->
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a id="time"></a>
                    </li>


                    <p class="navbar-text navbar-right">
                        <a href="#" id="exit">
                            退出登录
                            <span class="glyphicon glyphicon-log-out"></span>
                        </a>
                    </p>

                    <p class="navbar-text navbar-right">欢迎用户：${sessionScope.Admin.username}</p>
                </ul>
            </div>

        </div>


    </div>
</nav>
</body>
</html>