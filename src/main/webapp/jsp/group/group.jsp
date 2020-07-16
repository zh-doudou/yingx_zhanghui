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
                url: "${path}/yxGroup/queryAll",
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
            $.each(data.rows, function (index, group) {
                var tr = $("<tr></tr>");
                var tdId = $("<td></td>").text(group.id);
                var tdName = $("<td></td>").text(group.name);
                var tdGropDate = $("<td></td>").text(group.gropDate);
                var tdUserId = $("<td></td>").text(group.userId);
                var del = $(" <button type=\"button\" class=\"btn btn-warning\" onclick='delById(\"" + group.id + "\")'>删除</button>");
                var update = $("<button  data-toggle=\"modal\" data-target=\".bs-example-modal-lg\" type=\"button\" class=\"btn bg-primary\" onclick='updateById(\"" + group.id + "\")'>修改</button>");
                tr.append(tdId).append(tdName).append(tdGropDate).append(tdUserId).append(del).append(update)
                tbo.append(tr)
            })
        }

        //添加分组
        function addGroup() {
            $.ajax({
                url:"${path}/yxGroup/"
            })
        }

        //删除分组
        function delById(idd) {
            alert("删除id" + idd)

        }

        //修改分组
        function updateById(idd) {
            alert("修改id" + idd)

        }
    </script>
</head>
<body>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">添加分组</h4>
            </div>
            <div class="modal-body">
                <form>
                    分组名字：<input class="el-input__inner" type="text" name="name"><br>
                    用户id：&nbsp; &nbsp;&nbsp;<input class="el-input__inner" type="text" name="userId">
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="addGroup()">提交</button>
            </div>
        </div>
    </div>
</div>
<br>
<div class="panel panel-default">
    <div class="panel-heading text-center">
        <h3 class="panel-title">分组管理界面</h3>
    </div>

    <div class="panel-body">
        <div>

            <!-- 标签组 -->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                          data-toggle="tab">分组展示</a></li>
                <li role="presentation"><a data-toggle="modal" data-target="#myModal">添加分组</a></li>
            </ul>
            <!--面板组 表格-->
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="home">
                    <table class="table table-hover">
                        <tr>
                            <td>id</td>
                            <td>分组名字</td>
                            <td>用户id</td>
                            <td>视频id</td>
                            <td>操作</td>
                        </tr>
                        <tbody id="tbo">
                        </tbody>
                    </table>
                </div>

            </div>

        </div>
    </div>
</div>

</body>
</html>