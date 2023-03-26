<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 14722
  Date: 2021/12/4
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>股票预测系统</title>
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
        function isCollect(stock_id){
            $.ajax({
                url:"/userStock/judgeCollection",
                data:{'stock_id':stock_id},
                method:'post',
                success:function (flag){
                    if (flag == 'true'){
                        $('#button_'+stock_id+'').attr('disabled',true);
                        $('#button_'+stock_id+'').html('已收藏');
                    }else {
                        console.log('收藏失败')
                    }
                },
                error:function (){
                    console.log('网络错误')
                }
            })
        }
        $(function (){
            $('tbody tr td:first-child').css('color','#00349a');
            var tds = document.querySelectorAll('tbody tr td:nth-child(6)');
            var tdss = document.querySelectorAll('tbody tr td:nth-child(3)');
            var gid = document.querySelectorAll('tbody tr td:first-child');
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
                isCollect($(gid[i]).html());
            }
        })
    </script>
</head>
<body>
    <div class="form-inline search" align="center" style="padding-top: 80px">
        <div class="form-group">
            <input type="text" style="width: 400px;height: 40px" class="form-control" id="stockId" placeholder="输入股票编码进行查询">
        </div>
        <button  class="btn btn-default" id="search" style="padding: 10px 50px">查询</button>

    </div>
    <h3 align="center" style="margin-top: 30px;margin-bottom: 20px">热门股票推荐</h3>
    <table class="table table-hover table-bordered">
        <thead>
        <tr>
            <td>股票代码</td>
            <td>股票名称</td>
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
        <c:forEach items="${stockList}" var="stock">
            <tr>
                <td>${stock.id}</td>
                <td>${stock.name}</td>
                <td>${stock.currentPrice}</td>
                <td>${stock.highestPrice}</td>
                <td>${stock.lowestPrice}</td>
                <td>${stock.fluctuationRange}</td>
                <td>${stock.amplitude}</td>
                <td>${stock.turnover}</td>
                <td style="text-align: center">
                    <button type="button" class="btn btn-primary" onclick="showDetail('${stock.id}')" style="margin: 0 !important;">查看详情</button>
                    <button type="button" class="btn btn-danger" id="button_${stock.id}" onclick="collect('${stock.id}','${stock.name}')">收藏</button>
                </td>
            </tr>
        </c:forEach>

        <script>
            function showDetail(stock_id){
                location.href="http://localhost:8080/pages/stockSearch.jsp?stock_id="+stock_id;
            }
            $(function (){
                $('#search').click(function (){
                    showDetail($('#stockId').val());
                })
            })
            function collect(stock_id,stock_name){
                $.ajax({
                    url:"/userStock/collection",
                    data:{'stock_id':stock_id,'stock_name':stock_name},
                    method:'post',
                    success:function (flag){
                        if (flag == 'succeed'){
                            alert('收藏成功')
                            console.log(this)
                            console.log($(this))
                            $('#button_'+stock_id+'').attr('disabled',true);
                            $('#button_'+stock_id+'').html('已收藏');
                        }else {
                            console.log('收藏失败')
                        }
                    },
                    error:function (){
                        alert('网络错误')
                    }
                })
            }
        </script>

        </tbody>
    </table>
</body>
</html>
