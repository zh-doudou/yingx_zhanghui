<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/boot/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/btn.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/mycss/login.css">
    <%-- jquery核心.js --%>
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <%--表单验证--%>
    <script src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
    <%-- boostrap核心.js --%>
    <script src="${pageContext.request.contextPath}/boot/js/bootstrap.min.js"></script>
    <%-- jqGrid核心.js --%>
    <script src="${pageContext.request.contextPath}/jqgrid/js/trirand/jquery.jqGrid.min.js"></script>
    <%-- jqGrid国际化.js --%>
    <script src="${pageContext.request.contextPath}/jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
</head>
<body>
<!--右侧上部分巨幕-->
<div class="col-xs-12 col-lg-12">
    <div class="jumbotron">
        <div class="container text-center">
            <h3>
                欢迎来到应学App视频后台管理系统
            </h3>
        </div>
    </div>
</div>
<!--右侧下部分轮播图-->
<div class="col-xs-12 col-lg-12">

    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
            <li data-target="#carousel-example-generic" data-slide-to="2"></li>
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner" role="listbox">
            <div class="item active">
                <div class="text-center">
                    <img src="${pageContext.request.contextPath}/image/1.jpg" alt="...">
                </div>
                <div class="carousel-caption">
                    one
                </div>
            </div>

            <div class="item">
                <div class="text-center">
                    <img src="${pageContext.request.contextPath}/image/2.jpg"
                         style="height: 300px;width: 300px">
                </div>
                <div class="carousel-caption">
                    two
                </div>
            </div>

            <div class="item" style=" text-align: center">
                <div class="text-center">
                    <img src="${pageContext.request.contextPath}/image/3.3.jpg">
                </div>
                <div class="carousel-caption">
                    three
                </div>
            </div>


        </div>

        <!-- Controls -->
        <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>

</div>

</body>
</html>