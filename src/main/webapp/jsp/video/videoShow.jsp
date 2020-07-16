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
        //页面加载查询所有
        $(function () {
            $.ajax({
                url: "${path}/yxVideo/queryByPage",
                data: {rows: 2, page: 1},
                dataType: "json",
                success: function (data) {
                    init(data);

                }
            })
        })

        //主页数据初始化
        function init(data) {
            var tbo = $("#tbo");
            tbo.empty();
            //类别回显
            var addVideoSelect = $("#addVideoSelect")
            addVideoSelect.empty()
            var updateVideoSelect = $("#updateVideoSelect");
            updateVideoSelect.empty();

            $.each(data.twoClass, function (index, cateGory) {
                var option = $("<option value=" + cateGory.id + ">" + cateGory.categoryName + "</option>");
                addVideoSelect.append(option)
            })
            $.each(data.twoClass, function (index, cateGory) {
                var option = $("<option value=" + cateGory.id + ">" + cateGory.categoryName + "</option>");
                updateVideoSelect.append(option)
            })


            //数据展示
            $.each(data.rows, function (index, video) {
                var tr = $("<tr></tr>");
                var tdId = $("<td></td>").append(video.id);
                var tdTitle = $("<td></td>").append(video.title);
                var tdBrief = $("<td  style=\"word-break : break-all;overflow:hidden;width: 250px\"></td>").append(video.brief);
                var tdVideoDate = $("<td></td>").append(video.videoDate);
                //类别id
                var tdcCategoryId = $("<td></td>").append(video.categoryId);
                //分组id
                var tdGroupId = $("<td></td>").append(video.groupId);
                //类别id
                var teUserId = $("<td></td>").append(video.userId);
                var tdVideoPath = $("<td></td>").html("<video controls src=" + video.videoPath + " style=\"height: 120px;width: 200px \">");

                var tdcoverPath = $("<td></td>").html("<img src=" + video.coverPath + " style=\"height: 120px;width: 200px \">");
                var del = $(" <button type=\"button\" class=\"btn btn-warning\" onclick='delById(\"" + video.id + "\")'>删除</button>");
                var update = $("<button  data-toggle=\"modal\" data-target=\".bs-example-modal-lg\" type=\"button\" class=\"btn bg-primary\" onclick='updateById(\"" + video.id + "\")'>修改</button>");
                tr.append(tdId).append(tdTitle).append(tdBrief)/*.append(tdVideoDate).append(tdcCategoryId).append(tdGroupId)*/
                    .append(tdVideoDate).append(tdVideoPath).append(tdcoverPath).append(del).append(update)
                tbo.append(tr)
            })


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


        }

        //上传视频
        function addVideo() {
            $.ajax({
                url: "${path}/yxVideo/addVideo",
                data: $("#videoAddForm").serialize(),
                dataType: "json",
                success: function (data) {
                    if (data.add == "SUCCESS") {
                        alert("文件上传中")
                        //console.log($("#videoFile").val());
                        var formData = new FormData()
                        formData.append("videoFile", document.getElementById("addVideoFile").files[0]);
                        $.ajax({
                            url: "${path}/yxVideo/uplodeVideo?id=" + data.newId,
                            dataType: 'json',
                            method: 'POST', //提交方式
                            async: false,//安全协议提交
                            data: formData,
                            processData: false, // 数据不做处理
                            contentType: false, // 不要设置Content-Type请求头内容类型
                            success: function (data) {
                                console.log("文件上传结果：" + data.upLode)
                                if (data.upLode == "SUCCESS") {
                                    //添加警告添加成功提示
                                    $("#addOneClassOk").show()
                                    //设置隐藏
                                    setTimeout(function () {
                                        $("#addOneClassOk").hide()
                                        $('#myOneModal').modal('hide')
                                        document.getElementById("videoAddForm").reset();
                                    }, 2000)
                                    init(data)
                                }
                            }
                        })
                    }
                }
            });

        }

        //删除视频
        function delById(idd) {
            alert(idd)
            $.ajax({
                url: "${path}/yxVideo/delById",
                data: {id: idd},
                dataType: "json",
                success: function (data) {
                    if (data.del == "SUCCESS") {
                        $("#delVideoOk").show()
                        setTimeout(function () {
                            $("#delVideoOk").hide();
                        }, 3000);
                    }
                    init(data);
                }
            })
        }

        //视频修改数据回显
        function updateById(idd) {
            //根据id查询
            $.ajax({
                url: "${path}/yxVideo/selectOne",
                data: {id: idd},
                dataType: "json",
                success: function (data) {
                    $("#uptitle").attr("value", data.title);
                    $("#brief").attr("value", data.brief);
                    $("#id").attr("value", data.id);
                    console.log(data);
                }
            })
        }

        //修改
        function updateVideo() {
            $.ajax({
                url: "${path}/yxVideo/upDateVideo",
                data: $("#videoUpdateForm").serialize(),
                dataType: "json",
                success: function (data) {
                    console.log(data.update)
                    if (data.update == "SUCCESS") {
                        //进行ajax异步文件上传
                        var videoFileUpdate = $("#videoFile").val();
                        console.log(videoFileUpdate);
                        /*var exp = "undefined"
                        console.log(exp);*/
                        if (videoFileUpdate != "") {
                            alert("进入文件上传")
                            var formData = new FormData()
                            formData.append("videoFile", document.getElementById("videoFile").files[0]);
                            $.ajax({
                                url: "${path}/yxVideo/uplodeVideo?id=" + data.id,
                                data: formData,
                                dataType: 'json',
                                method: 'POST', //提交方式
                                async: false,//安全协议提交
                                data: formData,
                                processData: false, // 数据不做处理
                                contentType: false, // 不要设置Content-Type请求头内容类型
                                success: function (data) {
                                    console.log("文件上传结果：" + data.upLode)
                                    if (data.upLode == "SUCCESS") {
                                        //添加警告添加成功提示
                                        $("#updateVideoOk").show()
                                        //设置隐藏
                                        setTimeout(function () {
                                            $("#updateVideoOk").hide()
                                            $('#myUpdateModal').modal('hide')
                                            document.getElementById("videoUpdateForm").reset();
                                        }, 2000)
                                        init(data)
                                    }
                                }
                            })
                        } else {
                            alert("视频未修改")
                            //添加警告添加成功提示
                            $("#updateVideoOk").show()
                            //设置隐藏
                            setTimeout(function () {
                                $("#updateVideoOk").hide()
                                $('#myUpdateModal').modal('hide')
                                document.getElementById("videoUpdateForm").reset();
                            }, 2000)

                        }

                    }
                    init(data);
                }
            })
        }

        //分页查询
        function markPage(page) {
            $.ajax({
                url: "${path}/yxVideo/queryByPage",
                data: {rows: 2, page: page},
                dataType: "json",
                success: function (data) {
                    init(data)
                }
            })
        }
    </script>
</head>
<body>

<%--模态框修改--%>
<div class="modal fade bs-example-modal-lg" id="myUpdateModal" tabindex="-1" role="dialog"
     aria-labelledby="myOneModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">修改信息</h4>

            </div>
            <form id="videoUpdateForm">
                <div class="modal-body">
                    标题：&nbsp;&nbsp;&nbsp; <input type="text" class="el-input__inner" name="title" id="uptitle"><br>
                    内容：&nbsp;&nbsp; &nbsp;<input type="text" class="el-input__inner" name="brief" value=""
                                                 id="brief"><br>
                    选择上传视频：<input type="file" id="videoFile" name="videoFile"><br>
                    所属类别：
                    <select id="updateVideoSelect" name="categoryId">
                    </select>
                </div>
                <%--当前用户id--%>
                <input type="hidden" name="id" id="id">
                <div class="modal-footer">
                    <div id="updateVideoOk" class="alert alert-success alert-dismissable"
                         style="display: none">修改成功
                    </div>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" onclick="updateVideo()">提交</button>
                </div>
            </form>
        </div>
    </div>
</div>
<%--视频上传模态框展示--%>
<div class="modal fade" id="myOneModal" tabindex="-1" role="dialog" aria-labelledby="myOneModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myOneModalLabel">视频上传</h4>

            </div>
            <form id="videoAddForm">
                <div class="modal-body">
                    标题：&nbsp;&nbsp;&nbsp; <input type="text" class="el-input__inner" name="title"><br>
                    内容：&nbsp;&nbsp; &nbsp;<input type="text" class="el-input__inner" name="brief" value=""><br>
                    选择上传视频：<input type="file" id="addVideoFile" name="videoFile" value=""><br>
                    所属类别：
                    <select id="addVideoSelect" name="categoryId">
                    </select>
                </div>
                <%--当前用户id--%>
                <input type="hidden" name="userId" value="${sessionScope.Admin.id}">
                <input type="hidden" name="id" id="thisId">
                <div class="modal-footer">
                    <div id="addOneClassOk" class="alert alert-success alert-dismissable"
                         style="display: none">添加成功
                    </div>


                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" onclick="addVideo()">提交</button>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="panel panel-warning">
    <!-- Default panel contents -->
    <div class="panel-heading">视频管理页面

    </div>
    <div class="panel-body">
        <div>
            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                          data-toggle="tab">视频展示</a></li>
                <li role="presentation"><a href="#profile" data-toggle="modal" data-target="#myOneModal">上传视频</a></li>

            </ul>
            <!-- Tab panes -->
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="home">
                    <!-- Table -->
                    <table class="table table-condensed " style=＂table-layout:fixed>
                        <tr class="text-center">
                            <td>id</td>
                            <td>标题</td>
                            <td style="width: 10px">内容</td>
                            <td>上传时间</td>
                            <%-- <td>类别id</td>
                             <td>分组id</td>
                             <td>用户id</td>--%>
                            <td>视频地址</td>
                            <td>截图地址</td>
                            <td>操作</td>
                        </tr>
                        <tbody id="tbo" class="text-center">

                        </tbody>
                    </table>
                </div>

                <%--分页组件--%>
                <nav aria-label="...">
                    <ul class="pager" id="layPage">


                    </ul>
                </nav>
            </div>
        </div>
        <div id="delVideoOk" class="alert alert-success alert-dismissable"
             style="display: none">删除成功
        </div>
    </div>
</div>
</body>
</html>