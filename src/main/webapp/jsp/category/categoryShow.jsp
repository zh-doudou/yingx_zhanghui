<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        //页面加载查询
        $(function () {
            $.ajax({
                url: "${path}/yxCategory/queryAllClass",
                data: {rows: 20, page: 1},
                dataType: "json",
                success: function (data) {
                    init(data)

                }
            })
        })

        //封装展示页面
        function init(data) {
            var tbody = $("#tbody");
            tbody.empty()
            //如果是1级类别那就添加到复选框中
            var select = $("#select");
            select.empty()
            var defultop = $("<option></option>").text("---请选择类别---");
            select.append(defultop)
            //1级类别下2级类别数据填充
            $.each(data.allClass, function (index, category) {
                //console.log(data.allClass);
                var tr1 = $("<tr></tr>");
                var tdId = $("<td></td>").text(category.id);
                var tdName = $("<td></td>").text(category.categoryName);
                var tdLevels = $("<td></td>").text(category.levels);
                var tdParentId = $("<td></td>").text(category.parentId);
                var tdDelete;
                //1级类别下呦内容 不可删除1级类别
                if (category.twoCateGoryS.length == 0) {
                    //console.log(category.category_id);
                    tdDelete = $("<td></td>").html(" <button type=\"button\" class=\"btn btn-danger\" onclick='delById(\"" + category.id + "\")'>删除</button>")

                }
                var tdDelete2;
                //2级类别下有内容也不能删除
                /*   $.each(category.twoCateGoryS, function (index, twoCategory) {
                       if (twoCategory.yxVideos.length == 0) {
                           tdDelete2 = $("<td></td>").html(" <button type=\"button\" class=\"btn btn-warning\" onclick='delById(\"" + category.id + "\")'>删除</button>")

                       }
                   })*/

                tr1.append(tdId).append(tdName).append(tdLevels).append(tdParentId).append(tdDelete)
                tbody.append(tr1)
            });


            //分页ui
            var layPage = $("#layPage");
            layPage.empty()
            //分页组件
            //console.log("当前数为：" + data.page);
            //console.log("总条数为：" + data.records);
            //console.log("总页数为：" + data.total);
            //首页固定
            if (data.page > data.total) {
                var homePage = $(" <li><a href=\"#\" onclick=\"markPage(1)\">首页</a></li>");
                layPage.append(homePage)
            }
            //上一页极限
            if (data.page - 1 == 0) {
                var homePage = $(" <li><a href=\"#\" onclick=\"markPage(1)\">首页</a></li>");
                layPage.append(homePage)

            } else {
                //上一页
                var homePage = $(" <li><a href=\"#\" onclick=\"markPage(1)\">首页</a></li>");
                var pageUp = $(" <li><a href=\"#\" onclick=markPage(" + (data.page - 1) + ")>上一页</a></li>");
            }

            //中间
            var CentralShow = $("\n" +
                "                            <li> 当前第<span style=\"color: #2ca024\">" + data.page + "</span>页</li>\n" +
                "                            <li>共<span style=\"color: #c12e2a\">" + data.total + "</span>页</li>\n");

            //下一页极限控制  如果大于总页数  下一页不展示 展示尾页
            if (data.page + 1 > data.total) {
                var trailerPage = $(" <li><a href=\"#\" onclick=markPage(" + data.total + ")>尾页</a></li>");
                var homePage = $(" <li><a href=\"#\" onclick=\"markPage(1)\">首页</a></li>");

            } else {
                var nextPage = $(" <li><a href=\"#\" onclick=markPage(" + (data.page + 1) + ")>下一页</a></li>");
            }
            layPage.append(homePage).append(pageUp).append(CentralShow).append(nextPage).append(trailerPage)

            //尾页
            if (data.page < data.total) {
                var trailerPage = $(" <li><a href=\"#\" onclick=markPage(" + data.total + ")>尾页</a></li>");
                layPage.append(trailerPage)
            }


            //添加2级类别1级类别回显
            $.each(data.oneClass, function (index, oneClass) {
                if (oneClass.levels == 1) {
                    var optionName = $("<option></option>").html(oneClass.categoryName).attr("value", oneClass.id);
                    select.append(optionName)
                }
            })
        }

        //分页查询
        function markPage(page) {
            $.ajax({
                url: "${path}/yxCategory/queryAllClass",
                data: {rows: 20, page: page},
                dataType: "json",
                success: function (data) {
                    init(data)
                }
            })
        }

        //删除类别
        function delById(idd) {
            alert("删除类别为：" + idd)
            $.ajax({
                url: "${path}/yxCategory/deleteById",
                data: {id: idd},
                dataType: "json",
                success: function (data) {
                    if (data.del == "SUCCESS") {
                        $("#delByIdOk").show()
                        setTimeout(function () {
                            $("#delByIdOk").hide()
                        }, 2000);
                    }
                    init(data);
                }
            })

        }

        //添加1级/2级类别
        function addClass(value) {
            if (value == 1) {
                $.ajax({
                    url: "${pageContext.request.contextPath}/yxCategory/insert",
                    data: $("#OneClassForm").serialize(),
                    dataType: "json",
                    success: function (data) {
                        console.log("添加结果" + data.add)
                        if ("success" == data.add) {
                            //添加警告添加成功提示
                            $("#addOneClassOk").show()
                            //设置隐藏
                            setTimeout(function () {
                                $("#addOneClassOk").hide()
                                $('#myOneModal').modal('hide')
                                document.getElementById("OneClassForm").reset();
                            }, 1000)
                        }
                        init(data);
                    }
                });
            } else if (value == 2) {
                alert("2级类别添加")
                $.ajax({
                    url: "${pageContext.request.contextPath}/yxCategory/insert",
                    data: $("#twoClassFrom").serialize(),
                    dataType: "json",
                    success: function (data) {
                        console.log(data);
                        if ("success" == data.add) {
                            //添加警告添加成功提示
                            $("#addTwoClassOk").show()
                            //设置隐藏
                            setTimeout(function () {
                                $("#addTwoClassOk").hide()
                                $('#myTwoModal').modal('hide')
                                document.getElementById("twoClassFrom").reset();
                            }, 1000)

                        }
                        init(data);
                    }
                })
            }
        }
    </script>

</head>
<body>

<div class="panel panel-danger">
    <%--面板头部--%>
    <div class="panel-heading text-center">
        <h3 class="panel-title">类别功能模块</h3>
    </div>
    <%--面板身体部分--%>
    <div class="panel-body">
        <!--内嵌-->
        <div>

            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                          data-toggle="tab">类别展示</a></li>
                <li role="presentation"><a href="#profile" data-toggle="modal" data-target="#myOneModal">添加一级类别</a></li>
                <li role="presentation"><a href="#messages" data-toggle="modal" data-target="#myTwoModal">添加二级类别</a>
                    <div id="delByIdOk" class="alert alert-success alert-dismissable" style="display: none">删除成功</div>

                </li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="home">

                    <table class="table table-striped">
                        <tr>
                            <td>id</td>
                            <td>名字</td>
                            <td>级别</td>
                            <td>所属级别id</td>
                            <td>操作</td>
                        </tr>
                        <tbody id="tbody">

                        </tbody>

                        类别展示
                    </table>


                    <%--分页组件--%>
                    <nav aria-label="...">
                        <ul class="pager" id="layPage">


                        </ul>
                    </nav>
                </div>

                <!--  添加以及类别模态框展示-->
                <!-- Modal -->
                <div class="modal fade" id="myOneModal" tabindex="-1" role="dialog" aria-labelledby="myOneModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="myOneModalLabel">添加一级类别</h4>
                            </div>

                            <form id="OneClassForm">
                                <div class="modal-body">
                                    类别名：<input type="text" name="categoryName">
                                    <input type="hidden" name="levels" value="1">
                                </div>
                                <div class="modal-footer">
                                    <div id="addOneClassOk" class="alert alert-success alert-dismissable"
                                         style="display: none">添加成功
                                    </div>
                                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                    <button type="button" class="btn btn-primary" onclick="addClass(1)">提交</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <%--添加2级类别模态框--%>
                <div class="modal fade" id="myTwoModal" tabindex="-1" role="dialog" aria-labelledby="mytwoModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="mytwoModalLabel">添加二级类别</h4>
                            </div>
                            <form id="twoClassFrom">
                                <div class="modal-body">
                                    所属一级类别：
                                    <select id="select" name="parentId">

                                    </select>
                                    <br>

                                    类别名：<input type="text" name="categoryName">
                                    <%--类别的等级--%>
                                    <input type="hidden" name="levels" value="2">
                                </div>
                                <div class="modal-footer">
                                    <%--默认隐藏--%>
                                    <div id="addTwoClassOk" class="alert alert-success alert-dismissable"
                                         style="display: none">添加成功
                                    </div>
                                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                    <button type="button" class="btn btn-primary" onclick="addClass(2)">提交</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
</body>
</html>