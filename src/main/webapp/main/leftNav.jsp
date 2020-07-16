<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
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
</head>
<body>
<div class="col-xs-12 col-lg-2">
    <div class="panel panel-default">

        <!-- Default panel contents -->
        <div class="panel-heading text-center">系统菜单</div>
        <br>

        <!--系统菜单模块-->
        <div class="panel-group  text-center" id="accordion" role="tablist" aria-multiselectable="true">
            <!--用户管理-->
            <div class="panel panel-success">
                <div class="panel-heading" role="tab" id="headingOnee">
                    <h4 class="panel-title">
                        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFore"
                           aria-expanded="true"
                           aria-controls="collapseOne">
                            用户管理
                        </a>
                    </h4>
                </div>

                <div id="collapseFore" class="panel-collapse collapse " role="tabpanel"
                     aria-labelledby="headingOnee">
                    <div class="panel-body">
                        <p>
                            <a class="btn btn-info" target="Homepage"
                               href="${pageContext.request.contextPath}/jsp/user/userShow.jsp">用户展示</a>
                        </p>
                        <p>
                            <a class="btn btn-info" target="Homepage"
                               href="${pageContext.request.contextPath}/ECharts/Statview.jsp">用户统计</a>
                        </p>
                        <p>
                            <a class="btn btn-info" target="Homepage"
                               href="${pageContext.request.contextPath}/ECharts/distributionView.jsp">用户分布</a>
                        </p>
                    </div>
                </div>
            </div>
            <!--类别管理-->
            <div class="panel panel-danger">

                <div class="panel-heading" role="tab" id="headingOne">
                    <h4 class="panel-title">
                        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne"
                           aria-expanded="true"
                           aria-controls="collapseOne">
                            类别管理
                        </a>
                    </h4>
                </div>

                <div id="collapseOne" class="panel-collapse collapse  " role="tabpanel"
                     aria-labelledby="headingClassOne">
                    <div class="panel-body">
                        <p>
                            <a class="btn btn-info" target="Homepage"
                               href="${pageContext.request.contextPath}/jsp/category/categoryShow.jsp">
                                类别展示</a>
                        </p>
                    </div>
                </div>
            </div>

            <!--视频管理-->
            <div class="panel panel-warning">
                <div class="panel-heading" role="tab" id="headingClassOne">
                    <h4 class="panel-title">
                        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo"
                           aria-expanded="true"
                           aria-controls="collapseOne">
                            视频管理
                        </a>
                    </h4>
                </div>

                <div id="collapseTwo" class="panel-collapse collapse " role="tabpanel"
                     aria-labelledby="headingOne">
                    <div class="panel-body">
                        <p>
                            <a class="btn btn-info" target="Homepage"
                               href="${pageContext.request.contextPath}/jsp/video/videoShow.jsp"
                               id="categoryshow">视频展示</a>
                        </p>
                    </div>
                </div>
            </div>
            <!--分组管理-->
            <div class="panel panel-default">
                <div class="panel-heading" role="tab">
                    <h4 class="panel-title">
                        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#groupDiv"
                           aria-expanded="true"
                           aria-controls="collapseOne">
                            分组管理
                        </a>
                    </h4>
                </div>
                <div id="groupDiv" class="panel-collapse collapse " role="tabpanel"
                     aria-labelledby="headingOne">
                    <div class="panel-body">
                        <p>
                            <a class="btn btn-info" target="Homepage"
                               href="${pageContext.request.contextPath}/jsp/group/group.jsp"
                               id="groupShow">分组展示</a>
                        </p>

                    </div>
                </div>
            </div>

            <!--反馈管理模块-->
            <div class="panel panel-info">
                <div class="panel-heading" role="tab">
                    <h4 class="panel-title">
                        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFive"
                           aria-expanded="true"
                           aria-controls="collapseOne">
                            反馈管理
                        </a>
                    </h4>
                </div>
                <div id="collapseFive" class="panel-collapse collapse " role="tabpanel"
                     aria-labelledby="headingOne">
                    <div class="panel-body">
                        <p>
                            <a class="btn btn-info" target="Homepage"
                               href="${pageContext.request.contextPath}/jsp/feedback/feedback.jsp"
                               id="feedback">反馈展示</a>
                        </p>
                    </div>
                </div>
            </div>
            <!--日志管理模块-->
            <div class="panel panel-info">
                <div class="panel-heading" role="tab">
                    <h4 class="panel-title">
                        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree"
                           aria-expanded="true"
                           aria-controls="collapseOne">
                            日志管理
                        </a>
                    </h4>
                </div>
                <div id="collapseThree" class="panel-collapse collapse " role="tabpanel"
                     aria-labelledby="headingOne">
                    <div class="panel-body">
                        <p>
                            <a class="btn btn-info" target="Homepage"
                               href="${pageContext.request.contextPath}/jsp/journal/logShow.jsp"
                               id="bookshow">日志展示</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
</body>
</html>