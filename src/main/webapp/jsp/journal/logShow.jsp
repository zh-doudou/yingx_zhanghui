<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
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
    <script>
        $(function () {
            $.ajax({
                url: "${path}/yxLog/queryAll",
                data: {rows: 5, page: 1},
                dataType: "json",
                success: function (data) {
                    init(data)
                }
            });

        })

        //主页数据回显
        function init(data) {
            var tbo = $("#tbo");
            tbo.empty()
            //主要数据填充
            $.each(data.rows, function (index, log) {
                var tr = $("<tr></tr>");
                var tdId = $("<td></td>").text(log.id);
                var tdTitle = $("<td></td>").text(log.name);
                var tdContent = $("<td></td>").text(log.dates);
                var tdUserId = $("<td></td>").text(log.operation);
                var tdFeedVBackDate = $("<td></td>").text(log.stauts);
                var del = $(" <button type=\"button\" class=\"btn btn-warning\" onclick='delById(\"" + log.id + "\")'>删除</button>");
                tr.append(tdId).append(tdTitle).append(tdContent).append(tdUserId).append(tdFeedVBackDate).append(del)
                tbo.append(tr)
            })
            //分页组件
            var contRow = $("#contRow");
            contRow.empty()


            var navv = $("<nav aria-label=\"...\">\n" +
                "                <ul class=\"pager\">\n" +
                "                    <li class=\"col-lg-offset-3\">\n" +
                "                        <a onclick=\"queryByPage(1," + data.totalPage + ")\">首页</a>\n" +
                "                        <a onclick=\" queryByPage(" + (data.page - 1) + "," + data.totalPage + ")\">上一页</a>\n" +
                "                        第" + data.page + "页/共" + data.totalPage + "页\n" +
                "                        <a onclick=\"queryByPage(" + (data.page + 1) + "," + data.totalPage + ")\">下一页</a>\n" +
                "                        <a onclick=\"queryByPage(" + data.totalPage + "," + data.totalPage + ")\">尾页</a>\n" +
                "                    </li>\n" +
                "                    <li class=\"col-md-offset-4\">\n" +
                "                        <td>" + ((data.page - 1) * 5 + 1) + "-" + data.page * 5 + "</td>\n" +
                "                        <td>共" + data.records + "条</td>\n" +
                "\n" +
                "                    </li>\n" +
                "                </ul>\n" +
                "            </nav>")
            contRow.append(navv)
        }


        //删除反馈行数据
        function delById(idd) {
            $.ajax({
                url: "${path}/yxLog/delById",
                data: {id: idd},
                dataType: "json",
                success: function (data) {
                    if (data.del == "SUCCESS") {
                        $("#delOk").show()
                        setTimeout(function () {
                            $("#delOk").hide()
                        }, 2000)
                    }
                    init(data)
                }
            })
            alert("删除id" + idd)
        }

        //分页查询
        function queryByPage(page, totalPage) {
            if (page > 0 && page <= totalPage) {
                $.ajax({
                    url: "${path}/yxLog/queryAll",
                    data: {rows: 5, page: page},
                    dataType: "json",
                    success: function (data) {
                        init(data)
                    }
                })
            }
        }
    </script>
</head>
<body>

<div class="panel panel-info">
    <div class="panel-heading text-center">
        <h3 class="panel-title">反馈管理界面</h3>
    </div>
    <div id="delOk" class="alert alert-success alert-dismissable"
         style="display: none">删除成功
    </div>
    <div class="panel-body">
        <div>

            <!-- 标签组 -->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                          data-toggle="tab">日志展示</a></li>
            </ul>
            <!--面板组 表格-->
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="home">
                    <table class="table table-hover">
                        <tr>
                            <td>id</td>
                            <td>执行者</td>
                            <td>执行时间</td>
                            <td>执行内容</td>
                            <td>执行结果</td>
                            <td>操作</td>
                        </tr>
                        <tbody id="tbo">
                        </tbody>
                    </table>
                </div>

            </div>

        </div>
    </div>

    <div class="container-fluid">
        <div class="row" id="contRow">

        </div>
    </div>

</div>

</body>
</html>