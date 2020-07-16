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

<div class="iframe-container">
    <!--上部导航栏-->
    <iframe id="treeIframe1" frameborder="0" width="100%" scrolling="yes"
            height="10%" src="${pageContext.request.contextPath}/main/navTop.jsp" style="float: top;"></iframe>

    <!--左部功能-->
    <iframe id="treeIframe2" frameborder="0" width="15%" scrolling="yes"
            height="72%" src="${pageContext.request.contextPath}/main/leftNav.jsp" style="float: left;"></iframe>

    <!--右边巨幕-->
    <iframe id="tableIframe3" frameborder="0" width="85%" scrolling="yes" height="72%"
            src="${pageContext.request.contextPath}/main/rightShow.jsp" style="float: right;"
            name="Homepage"></iframe>

    <!--底部导航栏-->
    <iframe id="treeIframedown" frameborder="0" width="100%" scrolling="yes"
            height="14%" src="${pageContext.request.contextPath}/main/vavbottom.jsp" style="float: top;"></iframe>
</div>
</body>
</html>