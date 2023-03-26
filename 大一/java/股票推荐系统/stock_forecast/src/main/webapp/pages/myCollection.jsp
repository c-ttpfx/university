<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>股票预测系统</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">

    <!-- Tell the browser to be responsive to screen width -->
    <meta
            content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"
            name="viewport">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/iCheck/square/blue.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/morris/morris.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/datepicker/datepicker3.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.theme.default.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/select2/select2.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/colorpicker/bootstrap-colorpicker.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/bootstrap-markdown/css/bootstrap-markdown.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/adminLTE/css/AdminLTE.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/adminLTE/css/skins/_all-skins.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.skinNice.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/bootstrap-slider/slider.css">


    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="../css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">

    <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
    <link rel="stylesheet" href="../css/bootstrap-theme.min.css" integrity="sha384-6pzBo3FDv/PJ8r2KRkGHifhEocL+1X2rVCTTkUfGk7/0pbek5mMa1upzvWbrUbOZ" crossorigin="anonymous">

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="../js/bootstrap.min.js" integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous"></script>
    <script src="../js/jquery-3.3.1.js"></script>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="../css/bootstrap.min.css" >
    <style>
        .search {
            padding-top: 50px;
        }
        td {
            text-align: center;
        }
        tbody tr td{
            line-height: 30px !important;
        }
    </style>
    <script>
        $(function (){
            $('tbody tr td:nth-child(2)').css('color','#00349a');
            var tds = document.querySelectorAll('tbody tr td:nth-child(8)');
            var tdss = document.querySelectorAll('tbody tr td:nth-child(5)');
            for (let i = 0; i < tds.length; i++) {
                var char= $(tds[i]).html().charAt(0);
                console.log(char)
                if (char==='-'){
                    $(tds[i]).css('color','#008438');
                    $(tdss[i]).css('color','#008438');
                }else {
                    $(tds[i]).css('color','#e30000');
                    $(tdss[i]).css('color','#e30000');
                }
            }
        })
    </script>
</head>

<body class="hold-transition skin-blue sidebar-mini">

<div class="wrapper">

    <!-- 页面头部 -->
    <jsp:include page="header.jsp"></jsp:include>
    <!-- 页面头部 /-->

    <!-- 导航侧栏 -->
    <jsp:include page="aside.jsp"></jsp:include>
    <!-- 导航侧栏 /-->

    <!-- 内容区域 -->
    <div class="content-wrapper" style="padding-top: 70px">
        <div class="container">
            <h3 style="text-align: center;margin-bottom: 30px">用户信息列表</h3>
            <form  class="form-inline" action="${pageContext.request.contextPath}/userStock/showAllStock" method="post" style="float: left">
                <div class="form-group">
                    <label for="stock_id">股票id</label>
                    <input class="form-control" type="text" id="stock_id" placeholder="输入股票id" name="stock_id">
                </div>
                <div class="form-group">
                    <label for="stock_name">股票名字</label>
                    <input class="form-control" id="stock_name" type="text" placeholder="输入股票名称" name="stock_name">
                </div>
                <button type="submit" class="btn btn-info"> &nbsp;查 询 &nbsp;</button>
            </form>
            <span style=" float:right;margin-bottom: 5px">
        <a class="btn btn-primary" href="javascript:void(0);" style="margin-right: 4px;" id="#">删除选中</a>
    </span>
            <div style="padding-top: 52px !important;">
                <form  method="post" id="form" >
                    <table border="1" class="table table-bordered table-hover" >
                        <thead>
                        <tr class="success">
                            <th>
                                <input type="checkbox" id="all">
                            </th>
                            <td>股票代码</td>
                            <td>股票名称</td>
                            <td>更新时间</td>
                            <td>最新价格</td>
                            <td>最高价</td>
                            <td>最低价</td>
                            <%--            涨跌幅=(现价-上一个交易日收盘价)/上一个交易日收盘价*100%--%>
                            <td>涨跌幅</td>
                            <%--            股票振幅=（当期最高价－当期最低价)/上期收盘价×100%--%>
                            <td>振幅</td>
                            <td>成交额</td>
                            <td>操作</td>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${page.list}" varStatus="vs" var="stock">
                            <tr>
                                <td><input type="checkbox" name="uName" value="${stock.id}"></td>
                                <td>${stock.id}</td>
                                <td>${stock.name}</td>
                                <td>${stock.date}</td>
                                <td>${stock.currentPrice}</td>
                                <td>${stock.highestPrice}</td>
                                <td>${stock.lowestPrice}</td>
                                <td>${stock.fluctuationRange}</td>
                                <td>${stock.amplitude}</td>
                                <td>${stock.turnover}</td>
                                <td><a class="btn btn-default btn-sm btn-success"
                                       href="${pageContext.request.contextPath}/pages/stockSearch.jsp?stock_id=${stock.id}">查看详情</a>
                                    &nbsp;<button class="btn btn-default btn-sm btn-danger" onclick="deleteStock('${stock.id}')">删除</button></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                </form>
            </div>
            <script >
                function deleteStock(id) {
                    if (confirm("确认要取消收藏吗?")) {
                        $.ajax({
                            url:"http://localhost:8080/userStock/delete",
                            data:{"stock_id":id},
                            success:function (data){
                                console.log(data);
                                if (data=='succeed'){
                                    console.log('删除成功')
                                    // location.href="localhost:8080/userStock/showAllStock";
                                }else {
                                    alert('删除失败');
                                }
                            },
                            error:function (){
                                alert('删除失败')
                            }
                        })
                    }
                }
            </script>
            <div>
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <c:if test="${page.currentPage==1}">
                        <li class="disabled">
                            </c:if>
                            <c:if test="${page.currentPage!=1}">
                        <li>
                            </c:if>
                            <a href="${pageContext.request.contextPath}/userStock/showAllStock?currentPage=${page.currentPage-1}&rows=5"
                               aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        <c:forEach begin="1" end="${page.totalPage}" var="i">
                            <c:if test="${i==page.currentPage}">
                                <li class="active">
                                    <a href="${pageContext.request.contextPath}/userStock/showAllStock?currentPage=${i}&rows=5">${i}</a>
                                </li>
                            </c:if>
                            <c:if test="${i!=page.currentPage}">
                                <li>
                                    <a href="${pageContext.request.contextPath}/userStock/showAllStock?currentPage=${i}&rows=5">${i}</a>
                                </li>
                            </c:if>
                        </c:forEach>
                        <c:if test="${page.currentPage>=page.totalPage}">
                            <li class="disabled">
                                <a href="${pageContext.request.contextPath}/userStock/showAllStock?currentPage=${page.currentPage}&rows=5"
                        </c:if>
                        <c:if test="${page.currentPage!=page.totalPage}">
                        <li>
                            </c:if>
                            <a href="${pageContext.request.contextPath}/userStock/showAllStock?currentPage=${page.currentPage+1}&rows=5"
                               aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                        <span style="font-size: 25px;margin-left: 5px">共${page.totalCount}条记录,一共${page.totalPage}页</span>
                    </ul>
                </nav>
            </div>

            <script>

                    var all = document.getElementById("all");
                    var tbody_tr = document.querySelector("tbody").querySelectorAll("input");
                    var flag = -1;



                    all.onclick = function () {
                        // console.log(tbody_tr);
                        for (let i = 0; i < tbody_tr.length; i++) {
                            tbody_tr[i].checked = this.checked;
                        }
                        // console.log(flag);
                    }
                    for (let i = 0; i < tbody_tr.length; i++) {
                        tbody_tr[i].onclick = function () {
                            var k = 0;
                            for (let i = 0; i < tbody_tr.length; i++) {
                                if (tbody_tr[i].checked == true) {
                                    k = k + 1;
                                }
                            }
                            // console.log(k);
                            if (k == tbody_tr.length) {
                                all.checked = true;
                            } else {
                                all.checked = false;
                            }
                        };
                    }




            </script>
        </div>
    </div>
    <!-- 内容区域 /-->



</div>

<script
        src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/jQueryUI/jquery-ui.min.js"></script>
<script>
    $.widget.bridge('uibutton', $.ui.button);
</script>
<script
        src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/raphael/raphael-min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/morris/morris.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/sparkline/jquery.sparkline.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/knob/jquery.knob.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/daterangepicker/moment.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.zh-CN.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/datepicker/bootstrap-datepicker.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/fastclick/fastclick.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/iCheck/icheck.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/adminLTE/js/app.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/select2/select2.full.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.zh-CN.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/js/bootstrap-markdown.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/locale/bootstrap-markdown.zh.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/js/markdown.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/js/to-markdown.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/ckeditor/ckeditor.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.extensions.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/datatables/jquery.dataTables.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/chartjs/Chart.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.resize.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.pie.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.categories.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.min.js"></script>
<script
        src="${pageContext.request.contextPath}/plugins/bootstrap-slider/bootstrap-slider.js"></script>
<script>
    $(document).ready(function() {
        // 选择框
        $(".select2").select2();

        // WYSIHTML5编辑器
        $(".textarea").wysihtml5({
            locale : 'zh-CN'
        });
    });

    // 设置激活菜单
    function setSidebarActive(tagUri) {
        var liObj = $("#" + tagUri);
        if (liObj.length > 0) {
            liObj.parent().parent().addClass("active");
            liObj.addClass("active");
        }
    }

    $(document).ready(function() {
        // 激活导航位置
        setSidebarActive("admin-index");
    });
</script>
</body>

</html>