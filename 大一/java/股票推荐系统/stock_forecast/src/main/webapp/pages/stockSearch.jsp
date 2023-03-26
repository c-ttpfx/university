<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <script src="../js/getParameter.js"></script>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="../css/bootstrap.min.css" >

    <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
    <link rel="stylesheet" href="../css/bootstrap-theme.min.css" integrity="sha384-6pzBo3FDv/PJ8r2KRkGHifhEocL+1X2rVCTTkUfGk7/0pbek5mMa1upzvWbrUbOZ" crossorigin="anonymous">

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="../js/bootstrap.min.js" ></script>
    <script src="../js/jquery-3.3.1.js"></script>
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
    <script src="../js/jquery-3.3.1.js"></script>
    <script src="../js/echarts.js"></script>
    <script>
        function isCollect(stock_id){
            $.ajax({
                url:"/userStock/judgeCollection",
                data:{'stock_id':stock_id},
                method:'post',
                success:function (flag){
                    if (flag == 'true'){
                        $('#collection').attr('disabled',true);
                        $('#collection').html('已收藏');
                    }else {
                        console.log('收藏失败')
                    }
                },
                error:function (){
                    console.log('网络错误')
                }
            })
        }
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
                        $('#collection').attr('disabled',true);
                        $('#collection').html('已收藏');
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
    <script>
        function leftChart(id){
            const upColor = '#ec0000';
            const upBorderColor = '#8A0000';
            const downColor = '#00da3c';
            const downBorderColor = '#008F28';
// Each item: open，close，lowest，highest

            var data1
            // = splitData([
            //     ['2013/1/24', 2320.26, 2320.26, 2287.3, 2362.94],
            //     ['2013/1/25', 2300, 2291.3, 2288.26, 2308.38],
            //     ['2013/1/28', 2295.35, 2346.5, 2295.35, 2346.92],
            //     ['2013/1/29', 2347.22, 2358.98, 2337.35, 2363.8],
            //     ['2013/1/30', 2360.75, 2382.48, 2347.89, 2383.76],
            //     ['2013/1/31', 2383.43, 2385.42, 2371.23, 2391.82]
            // ]);
            $.ajax({url:"http://localhost:8080/stock/historyData",
                data:{'id':id},
                success:function(result){
                    // alert('获取数据成功')
                    data1 = splitDataJson(result);
                    // console.log(data1)
                    var myChart1 = document.getElementById('myChart1');
                    var echarts1_= echarts.init(myChart1);
                    echarts1_.setOption({
                        title: {
                            text: '上证指数',
                            left: 0
                        },
                        tooltip: {
                            trigger: 'axis',
                            axisPointer: {
                                type: 'cross'
                            }
                        },
                        legend: {
                            data: ['日K', 'MA5', 'MA10', 'MA20', 'MA30']
                        },
                        grid: {
                            left: '10%',
                            right: '10%',
                            bottom: '15%'
                        },
                        xAxis: {
                            type: 'category',
                            data: data1.categoryData,
                            scale: true,
                            boundaryGap: false,
                            axisLine: { onZero: false },
                            splitLine: { show: false },
                            min: 'dataMin',
                            max: 'dataMax'
                        },
                        yAxis: {
                            scale: true,
                            splitArea: {
                                show: true
                            }
                        },
                        dataZoom: [
                            {
                                type: 'inside',
                                start: 50,
                                end: 100
                            },
                            {
                                show: true,
                                type: 'slider',
                                top: '90%',
                                start: 50,
                                end: 100
                            }
                        ],
                        series: [
                            {
                                name: '日K',
                                type: 'candlestick',
                                data: data1.values,
                                itemStyle: {
                                    color: upColor,
                                    color0: downColor,
                                    borderColor: upBorderColor,
                                    borderColor0: downBorderColor
                                },
                                markPoint: {
                                    label: {
                                        formatter: function (param) {
                                            return param != null ? Math.round(param.value) + '' : '';
                                        }
                                    },
                                    data: [
                                        {
                                            name: 'Mark',
                                            coord: ['2013/5/31', 2300],
                                            value: 2300,
                                            itemStyle: {
                                                color: 'rgb(41,60,85)'
                                            }
                                        },
                                        {
                                            name: 'highest value',
                                            type: 'max',
                                            valueDim: 'highest'
                                        },
                                        {
                                            name: 'lowest value',
                                            type: 'min',
                                            valueDim: 'lowest'
                                        },
                                        {
                                            name: 'average value on close',
                                            type: 'average',
                                            valueDim: 'close'
                                        }
                                    ],
                                    tooltip: {
                                        formatter: function (param) {
                                            return param.name + '<br>' + (param.data.coord || '');
                                        }
                                    }
                                },
                                markLine: {
                                    symbol: ['none', 'none'],
                                    data: [
                                        [
                                            {
                                                name: 'from lowest to highest',
                                                type: 'min',
                                                valueDim: 'lowest',
                                                symbol: 'circle',
                                                symbolSize: 10,
                                                label: {
                                                    show: false
                                                },
                                                emphasis: {
                                                    label: {
                                                        show: false
                                                    }
                                                }
                                            },
                                            {
                                                type: 'max',
                                                valueDim: 'highest',
                                                symbol: 'circle',
                                                symbolSize: 10,
                                                label: {
                                                    show: false
                                                },
                                                emphasis: {
                                                    label: {
                                                        show: false
                                                    }
                                                }
                                            }
                                        ],
                                        {
                                            name: 'min line on close',
                                            type: 'min',
                                            valueDim: 'close'
                                        },
                                        {
                                            name: 'max line on close',
                                            type: 'max',
                                            valueDim: 'close'
                                        }
                                    ]
                                }
                            },
                            {
                                name: 'MA5',
                                type: 'line',
                                data: calculateMA(5),
                                smooth: true,
                                lineStyle: {
                                    opacity: 0.5
                                }
                            },
                            {
                                name: 'MA10',
                                type: 'line',
                                data: calculateMA(10),
                                smooth: true,
                                lineStyle: {
                                    opacity: 0.5
                                }
                            },
                            {
                                name: 'MA20',
                                type: 'line',
                                data: calculateMA(20),
                                smooth: true,
                                lineStyle: {
                                    opacity: 0.5
                                }
                            },
                            {
                                name: 'MA30',
                                type: 'line',
                                data: calculateMA(30),
                                smooth: true,
                                lineStyle: {
                                    opacity: 0.5
                                }
                            }
                        ]
                    })
                },
                method:'post',
                error:function (){
                    alert('error')
                },
                dataType:'json'
            });
            function splitDataJson(rawData) {
                const categoryData = [];
                const values = [];
                for (let i = 0; i < rawData.length; i++) {
                    categoryData.push(rawData[i].day);
                    let arr = [];
                    arr.push(rawData[i].open);
                    arr.push(rawData[i].close);
                    arr.push(rawData[i].low);
                    arr.push(rawData[i].high);
                    values.push(arr);
                }
                // console.log(categoryData)
                // console.log(values)
                return {
                    categoryData: categoryData,
                    values: values
                };
            }
            // function splitData(rawData) {
            //     const categoryData = [];
            //     const values = [];
            //     for (var i = 0; i < rawData.length; i++) {
            //         categoryData.push(rawData[i].splice(0, 1)[0]);
            //         values.push(rawData[i]);
            //     }
            //     return {
            //         categoryData: categoryData,
            //         values: values
            //     };
            // }
            function calculateMA(dayCount) {
                var result = [];
                for (var i = 0, len = data1.values.length; i < len; i++) {
                    if (i < dayCount) {
                        result.push('-');
                        continue;
                    }
                    var sum = 0;
                    for (var j = 0; j < dayCount; j++) {
                        sum += +data1.values[i - j][1];
                    }
                    result.push(sum / dayCount);
                }
                return result;
            }
        }
        function rightChart(id){
            var date1 = [];
            var data1 = [];
            function change(rawData) {
                for (let i = 0; i < rawData.length; i++) {
                    date1.push(rawData[i].day);
                    data1.push(rawData[i].close);
                }
            }
            $.ajax({url:"http://localhost:8080/stock/historyData",
                data:{'id':id},
                success:function(result){

                    var myChart2 = document.getElementById('myChart2');
                    var echarts2_= echarts.init(myChart2);
                    change(result)
                    echarts2_.setOption({
                        tooltip: {
                            trigger: 'axis',
                            position: function (pt) {
                                return [pt[0], '10%'];
                            }
                        },
                        title: {
                            left: 'center',
                            text: '股票价格走势'
                        },
                        toolbox: {
                            feature: {
                                dataZoom: {
                                    yAxisIndex: 'none'
                                },
                                restore: {},
                                saveAsImage: {}
                            }
                        },
                        xAxis: {
                            type: 'category',
                            boundaryGap: false,
                            data: date1
                        },
                        yAxis: {
                            type: 'value',
                            boundaryGap: [0, '100%']
                        },
                        dataZoom: [
                            {
                                type: 'inside',
                                start: 0,
                                end: 10
                            },
                            {
                                start: 0,
                                end: 10
                            }
                        ],
                        series: [
                            {
                                name: '当前价格',
                                type: 'line',
                                symbol: 'none',
                                sampling: 'lttb',
                                itemStyle: {
                                    color: 'rgb(255, 70, 131)'
                                },
                                areaStyle: {
                                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                                        {
                                            offset: 0,
                                            color: 'rgb(255, 158, 68)'
                                        },
                                        {
                                            offset: 1,
                                            color: 'rgb(255, 70, 131)'
                                        }
                                    ])
                                },
                                data: data1
                            }
                        ]})
                },
                method:'post',
                error:function (){
                    alert('error')
                },
                dataType:'json'
            });
        }
        function topTable(id){
            $.ajax({
                url:"/stock/searchStock",
                data:{'id':id},
                method:'GET',
                contentType: 'text/json,charset=utf-8',
                dataType:'json',
                success:function (data){
                    // console.log(data);
                    // console.log(decodeURI(data.stock.name));
                    // console.log(decodeURI(data.stock.turnover));
                    var tds = document.querySelectorAll('#info td');
                    $(tds[0]).html(data.stock.id);
                    $(tds[1]).html(decodeURI(data.stock.name));
                    $(tds[2]).html(data.stock.date);
                    $(tds[3]).html(data.stock.currentPrice);
                    $(tds[4]).html(data.stock.highestPrice);
                    $(tds[5]).html(data.stock.lowestPrice);
                    $(tds[6]).html(data.stock.fluctuationRange);
                    $(tds[7]).html(data.stock.amplitude);
                    $(tds[8]).html(decodeURI(data.stock.turnover));
                    var buid = $(tds[0]).html();
                    var buname = $(tds[1]).html();
                    $('#collection').click(function (){
                        collect(buid,buname);
                    })
                    $('#forecast').click(function (){
                        location.href = "forecast_.jsp?stock_id="+$(tds[0]).html();
                    })
                    $('#table_info').css('display','block');
                    isCollect(buid)
                },
                error:function (){
                    console.log('失败')
                }
            })
        }
        function searchStockId(id){
            $.ajax({
                url:"/stock/searchStockId",
                data:{'id':id},
                method:'GET',
                contentType: 'text/json,charset=utf-8',
                success:function (data){
                    if (data == 'true'){
                        topTable(id);
                        leftChart(id);
                        rightChart(id);
                    }else {
                        alert('该股票不存在,请输入正确的股票id');
                    }
                },
                error:function (){
                    alert('该股票不存在,请输入正确的股票id');
                }
            })
        }
    </script>
    <script>
        $(function (){
            $('#submit').click(function (){
                // alert('点击了按钮')
                var stock_id = $('#inputId').val();
                console.log(stock_id)
                searchStockId(stock_id)
            })

            var sid = getParameter('stock_id');
            if (sid != null){
                searchStockId(sid);
                $('#inputId').val(sid);
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
    <div class="content-wrapper">
        <div class="form-inline search" align="center" style="padding-top: 80px">
            <div class="form-group">
                <input type="text" style="width: 400px;height: 40px" class="form-control" id="inputId" placeholder="输入股票编码进行查询">
            </div>
            <button id="submit" class="btn btn-default" style="padding: 10px 50px">查询</button>
        </div>
        <div id="table_info" style="display: none;margin-top: 30px;">
            <table class="table table-hover table-bordered" >
                <thead>
                <tr>
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
                    <tr id="info">
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>
                            <button type="button" class="btn btn-primary" id="forecast">股票分析</button>
                            <button type="button" class="btn btn-danger" id="collection">收藏</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="chartBox" style="margin-top: 40px;" align="center" id="chart_info">
            <div id="myChart1" style="height: 400px;width: 550px; display: inline-block" ></div>
            <div id="myChart2" style="height: 400px;width: 550px; display:inline-block;" ></div>
        </div>

    </div>
    <!-- 底部导航 /-->

</div>

<script src="../plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="../plugins/jQueryUI/jquery-ui.min.js"></script>
<script>
    $.widget.bridge('uibutton', $.ui.button);
</script>
<script src="../plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="../plugins/raphael/raphael-min.js"></script>
<script src="../plugins/morris/morris.min.js"></script>
<script src="../plugins/sparkline/jquery.sparkline.min.js"></script>
<script src="../plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="../plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<script src="../plugins/knob/jquery.knob.js"></script>
<script src="../plugins/daterangepicker/moment.min.js"></script>
<script src="../plugins/daterangepicker/daterangepicker.js"></script>
<script src="../plugins/daterangepicker/daterangepicker.zh-CN.js"></script>
<script src="../plugins/datepicker/bootstrap-datepicker.js"></script>
<script
        src="../plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<script
        src="../plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<script src="../plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script src="../plugins/fastclick/fastclick.js"></script>
<script src="../plugins/iCheck/icheck.min.js"></script>
<script src="../plugins/adminLTE/js/app.min.js"></script>
<script src="../plugins/treeTable/jquery.treetable.js"></script>
<script src="../plugins/select2/select2.full.min.js"></script>
<script src="../plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
<script
        src="../plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.zh-CN.js"></script>
<script src="../plugins/bootstrap-markdown/js/bootstrap-markdown.js"></script>
<script
        src="../plugins/bootstrap-markdown/locale/bootstrap-markdown.zh.js"></script>
<script src="../plugins/bootstrap-markdown/js/markdown.js"></script>
<script src="../plugins/bootstrap-markdown/js/to-markdown.js"></script>
<script src="../plugins/ckeditor/ckeditor.js"></script>
<script src="../plugins/input-mask/jquery.inputmask.js"></script>
<script
        src="../plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="../plugins/input-mask/jquery.inputmask.extensions.js"></script>
<script src="../plugins/datatables/jquery.dataTables.min.js"></script>
<script src="../plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="../plugins/chartjs/Chart.min.js"></script>
<script src="../plugins/flot/jquery.flot.min.js"></script>
<script src="../plugins/flot/jquery.flot.resize.min.js"></script>
<script src="../plugins/flot/jquery.flot.pie.min.js"></script>
<script src="../plugins/flot/jquery.flot.categories.min.js"></script>
<script src="../plugins/ionslider/ion.rangeSlider.min.js"></script>
<script src="../plugins/bootstrap-slider/bootstrap-slider.js"></script>
<script>
    $(document).ready(function () {
        // 选择框
        $(".select2").select2();

        // WYSIHTML5编辑器
        $(".textarea").wysihtml5({
            locale: 'zh-CN'
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

</script>
</body>

</html>