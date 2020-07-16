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
                url: "${path}/yxFeedback/queryAll",
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
            $.each(data.rows, function (index, feedback) {
                var tr = $("<tr></tr>");
                var tdId = $("<td></td>").text(feedback.id);
                var tdTitle = $("<td></td>").text(feedback.title);
                var tdContent = $("<td></td>").text(feedback.content);
                var tdUserId = $("<td></td>").text(feedback.userId);
                var tdFeedVBackDate = $("<td></td>").text(feedback.feedbackDate);
                var del = $(" <button type=\"button\" class=\"btn btn-warning\" onclick='delById(\"" + feedback.id + "\")'>删除</button>");
                var update = $("<button  data-toggle=\"modal\" data-target=\".bs-example-modal-lg\" type=\"button\" class=\"btn bg-primary\" onclick='updateById(\"" + feedback.id + "\")'>修改</button>");
                tr.append(tdId).append(tdTitle).append(tdContent).append(tdUserId).append(tdFeedVBackDate).append(del).append(update)
                tbo.append(tr)
            })
        }

        //添加反馈数据
        function addGroup() {
            $.ajax({
                url: "${path}/yxFeedback/addFeedBack",
                data: $("#feedBackForm").serialize(),
                dataType: "json",
                success: function (data) {
                    if (data.add == "SUCCESS") {
                        $("#addOk").show()
                        setTimeout(function () {
                            $("#addOk").hide()
                            $("#myModal").modal("hide")
                            document.getElementById("feedBackForm").reset();
                        }, 2000)
                    }
                    init(data)
                }
            })
        }

        //删除反馈行数据
        function delById(idd) {
            $.ajax({
                url: "${path}/yxFeedback/delById",
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
                <h4 class="modal-title" id="myModalLabel">添加反馈</h4>
                <div id="addOk" class="alert alert-success alert-dismissable"
                     style="display: none">添加成功
                </div>
                <div id="delOk" class="alert alert-success alert-dismissable"
                     style="display: none">删除成功
                </div>
            </div>
            <div class="modal-body">
                <form id="feedBackForm">
                    反馈标题：<input class="el-input__inner" type="text" name="title"><br>
                    反馈内容：<input class="el-input__inner" type="text" name="content"><br>
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
<div class="panel panel-info">
    <div class="panel-heading text-center">
        <h3 class="panel-title">反馈管理界面</h3>
    </div>

    <div class="panel-body">
        <div>

            <!-- 标签组 -->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                          data-toggle="tab">反馈展示</a></li>
                <li role="presentation"><a data-toggle="modal" data-target="#myModal">添加反馈</a></li>
            </ul>
            <!--面板组 表格-->
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="home">
                    <table class="table table-hover">
                        <tr>
                            <td>id</td>
                            <td>标题</td>
                            <td>内容</td>
                            <td>反馈人</td>
                            <td>反馈时间</td>
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