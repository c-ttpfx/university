<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>股票推荐系统</title>
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
    <link rel="stylesheet" href="../css/bootstrap.min.css">

    <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
    <link rel="stylesheet" href="../css/bootstrap-theme.min.css"
          integrity="sha384-6pzBo3FDv/PJ8r2KRkGHifhEocL+1X2rVCTTkUfGk7/0pbek5mMa1upzvWbrUbOZ" crossorigin="anonymous">

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/jquery-3.3.1.js"></script>
    <script src="../js/jquery-3.3.1.js"></script>
    <script src="../js/echarts.js"></script>
    <script>
        function show_chart(tru_data, tru_date,coefficient,chart_id) {
            var truth_data = tru_data;
            var date_ = tru_date;
            let base = new Date(date_[date_.length-1]).getTime();
            let oneDay = 24 * 3600 * 1000;
            for (let i = 1; i <= 10; i++) {
                base += oneDay;
                let now = new Date(base);
                var s = now.getFullYear() + "-" + (now.getMonth() + 1) + "-" + now.getDate();
                console.log(s)
                date_.push(s);
            }
            var forecast1 = [];
            var forecast2 = [];
            var b = coefficient;
            var error_data = 0;
            forecast1.push(0);
            for (let i = 1; i <= truth_data.length; i++) {
                var next_num1 = (forecast1[i - 1] * b + (1 - b) * truth_data[i - 1]).toFixed(2);
                var next_num2 = (next_num1 / (1 - Math.pow(b, i))).toFixed(2);
                error_data+=Math.abs(next_num2-tru_data[i-1])
                forecast1.push(next_num1);
                forecast2.push(next_num2);
            }
            error_data = (error_data/truth_data.length).toFixed(2);
            for (let i = truth_data.length + 1; i < truth_data.length + 11; i++) {
                var next_num1 = (forecast1[i - 1] * b + (1 - b) * forecast1[i - 1]).toFixed(2);
                var next_num2 = (next_num1 / (1 - Math.pow(b, i))).toFixed(2);
                forecast1.push(next_num1);
                forecast2.push(next_num2);
            }
            forecast1.shift()
            // console.log(truth_data)
            var index = (parseInt(chart_id.charAt(chart_id.length-1))-1)*2+1;
            var error_index = parseInt(chart_id.charAt(chart_id.length-1));
            var err_id = 'err'+error_index;
            var err_data = document.getElementById(err_id);
            $(err_data).html(error_data);
            var seAll1 = '#info'+index+' td';
            var tds1 = document.querySelectorAll(seAll1);
            $(tds1[0]).html('指数移动平均预测算法');
            $(tds1[2]).html(forecast1[50]);
            $(tds1[3]).html(forecast1[51]);
            $(tds1[4]).html(forecast1[52]);
            $(tds1[5]).html(forecast1[53]);
            $(tds1[6]).html(forecast1[54]);
            $(tds1[7]).html(forecast1[55]);
            $(tds1[8]).html(forecast1[56]);
            $(tds1[9]).html(forecast1[57]);
            $(tds1[10]).html(forecast1[58]);
            $(tds1[11]).html(forecast1[59]);
            index = index+1
            var seAll2 = '#info'+index+' td';
            var tds2 = document.querySelectorAll(seAll2);
            $(tds2[0]).html('修正指数移动平均预测算法');
            $(tds2[2]).html(forecast2[50]);
            $(tds2[3]).html(forecast2[51]);
            $(tds2[4]).html(forecast2[52]);
            $(tds2[5]).html(forecast2[53]);
            $(tds2[6]).html(forecast2[54]);
            $(tds2[7]).html(forecast2[55]);
            $(tds2[8]).html(forecast2[56]);
            $(tds2[9]).html(forecast2[57]);
            $(tds2[10]).html(forecast2[58]);
            $(tds2[11]).html(forecast2[59]);
            $(function () {
                var myChart = document.getElementById(chart_id);
                var echarts_ = echarts.init(myChart);
                echarts_.setOption({
                    xAxis: {
                        type: 'category',
                        data: date_
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [
                        {
                            data: truth_data,
                            type: 'line',
                            // smooth: true
                        },
                        {
                            data: forecast1,
                            type: 'line',
                            // smooth: true
                        },
                        {
                            data: forecast2,
                            type: 'line',
                            // smooth: true
                        }
                    ]
                })
            })
        }

    </script>
    <script>
        function get_first_Chart_data(id) {
            $.ajax({
                url: "http://localhost:8080/stock/historyData_forecast",
                method: 'get',
                data: {'id': id},
                success: function (rowData) {
                    var date1 = [];
                    var data1 = [];
                    for (let i = 0; i < rowData.length; i++) {
                        date1.push((rowData[i].day.split(" "))[0]);
                        data1.push(rowData[i].close);
                    }
                    show_chart(data1, date1,0.5,'myChart1');
                    show_chart(data1, date1,0.9,'myChart2');
                    show_chart(data1, date1,0.95,'myChart3');
                    show_chart(data1, date1,0.98,'myChart4');
                    $('#table1_info').css('display','block');
                    $('#show_forecast').css('display','block');
                    most_accurate(data1[data1.length-1],data1);
                },
                error: function () {
                    alert('获取数据失败,请检查网络')
                },
                dataType: 'json'
            })
        }
    </script>
    <script>
        $(function () {
            $('#submit').click(function () {
                // alert('点击了按钮')
                var stock_id = $('#inputId').val();
                get_first_Chart_data(stock_id);
                // alert('点击了按钮' + stock_id);
            })
            var sid = getParameter('stock_id');
            if (sid != null) {
                get_first_Chart_data(sid);
                $('#inputId').val(sid);
            }
        })
    </script>
    <script >
        function most_accurate(truth_price,truth_data){
            var ed1 = parseFloat($('#err1').html());
            var ed2 = parseFloat($('#err2').html());
            var ed3 = parseFloat($('#err3').html());
            var ed4 = parseFloat($('#err4').html());
            var min = 12345.0;
            var index = 1;
            var b = 0.98;
            if (min > ed1){
                min = ed1;
                index = 1;
                b = 0.5;
            }
            if (min > ed2){
                min = ed2;
                index = 2;
                b = 0.9;
            }
            if (min > ed3){
                min = ed3;
                index = 3;
                b = 0.95;
            }
            if (min > ed4){
                min = ed4;
                index = 4;
                b = 0.98;
            }

            $('#sele_b').html('经过分析,最适合的β因子为：'+b+'基于该β因子对未来7天股票的预测如下:');

            index = index * 2;
            var data_fore = [];
            for (let i = 3; i < 10; i++) {
                var da_id = '#info'+index;
                da_id = da_id+' td:nth-child('+i+')';
                var forecast_data = parseFloat($(da_id).html());
                var random_num = parseInt(Math.random()*2);
                if (random_num == 0){
                    forecast_data = (1+Math.random()*min*0.5)*forecast_data;
                }else {
                    forecast_data = (1-Math.random()*min*0.5)*forecast_data;
                }
                data_fore.push(forecast_data);
            }
            var myChart_next = document.getElementById('myChart_next');
            var echarts2_next= echarts.init(myChart_next);
            echarts2_next.setOption({
                xAxis: {
                    type: 'category',
                    data: ['第一天', '第二天', '第三天', '第四天', '第五天', '第六天', '第七天']
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        data: data_fore,
                        type: 'line'
                    }
                ]
            })

            var min_price = 9999999.0;
            var max_price = -999999.0;
            for (let i = 0; i < truth_data.length; i++) {
                var truData = parseFloat(truth_data[i]);
                min_price = Math.min(min_price,truData);
                max_price = Math.max(max_price,truData);
            }
            var info_stock = ['当前属于上升阶段','当前属于下降阶段','当前股票波动变化不大'];
            var info = '当前股票波动不大';
            var len = truth_data.length-1;
            var golds = [0.191,0.382,0.5,0.618,0.809];
            var advice_mess = '';
            if (truth_data[len] < truth_data[len-1] && truth_data[len-1]<truth_data[len-2]
                && truth_data[len-2] < truth_data[3]){
                info = info_stock[1];
                var adv_data = [];
                for (let i = 0; i < golds.length; i++) {
                    adv_data.push(((1-golds[i])*max_price).toFixed(2))
                }
                advice_mess = '分析股票可以用“黄金分割”来找出压力点和支撑点。当前处于下降阶段，可以算出股票的支撑点' +
                    '分别为<strong>'+adv_data+'</strong>,根据这些支撑点可以大致判断是否股票还会下跌，可以根据近期该股票行情进行选择，' +
                    '<p style="color: #dd001b;display: inline-block;text-indent: 0">'+'可以暂时持观望态度'+'</p>';
            }else if (truth_data[len] > truth_data[len-1] && truth_data[len-1]>truth_data[len-2]
                && truth_data[len-2] > truth_data[3]){
                info = info_stock[0];
                var adv_data = [];
                for (let i = 0; i < golds.length; i++) {
                    adv_data.push(((1+golds[i])*min_price).toFixed(2))
                }
                advice_mess = '分析股票可以用“黄金分割”来找出压力点和支撑点。当前处于上升阶段，可以算出股票的压力点' +
                    '分别为<strong>'+adv_data+'</strong>,根据这些压力点以及上面的预测数据选择是否进行购买，' +
                    '<p style="color: #dd001b;display: inline-block;text-indent: 0">'+'比较推荐'+'</p>';
            }else {
                info = info_stock[2];
                advice_mess = '可以根据历史平均价格以及上图的未来7天股票预测做出判断，' +
                    '<p style="color: #dd001b;display: inline-block;text-indent: 0">'+'谨慎购入'+'</p>';
            }

            var average_id = '#info'+(index-1);
            average_id = average_id+' td:nth-child('+3+')';
            var average_price = $(average_id).html();
            $('#last').html('现在股票的价格为<strong>'+parseFloat(truth_price).toFixed(2)+'</strong>，' + info+'，' +
                '近50天加权平均价格为<strong>'+average_price+'</strong>，'+advice_mess)
        }
    </script>
    <style>
        td {
            text-align: center;
        }
        .chartBox1 {
            height: 400px;
            width: 600px;
            display: inline-block;
            float: left;
            padding-left: 40px;
            position: relative;
        }
        .chartBox2 {
            height: 400px;
            width: 600px;
            display: inline-block;
            padding-left: 40px;
            position: relative;

        }

        .chartBox3 {
            height: 400px;
            width: 600px;
            display: inline-block;
            padding-left: 40px;
            position: relative;

        }

        .chartBox4 {
            height: 400px;
            width: 600px;
            display: inline-block;
            padding-left: 40px;
            position: relative;

        }
        .chart_show {
            height: 400px;
            width: 600px;
            display: inline-block;
        }
        .four_chart {
            position: relative;
        }
    </style>
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
        <div class="form-inline" align="center" style="padding-top: 80px">
            <div class="form-group">
                <input type="text" style="width: 400px;height: 40px" class="form-control" id="inputId"
                       placeholder="输入股票编码进行查询">
            </div>
            <button id="submit" class="btn btn-default" style="padding: 10px 50px">查询</button>
        </div>
        <div id="show_forecast" style="display: none">
            <div id="four_chart" class="four_chart">
                <div style="position: absolute;top:20px;left: 380px">
                    <p style="color: #8195d4;display: inline-block">蓝色：历史股票数据&nbsp;&nbsp;</p>
                    <p style="color: #91cc75;display: inline-block">绿色：指数移动平均预测&nbsp;&nbsp;</p>
                    <p style="color: #fac858;display: inline-block">黄色：修正指数移动平均预测</p>
                </div>
                <div class="chartBox1" id="chart_info1">
                    <div id="myChart1" class="chart_show" ></div>
                    <p style="position: absolute;top:360px;left: 300px;">(1) β = 0.5 </p>
                </div>
                <div class="chartBox2" id="chart_info2">
                    <div id="myChart2" class="chart_show" ></div>
                    <p style="position: absolute;top:360px;left: 300px;">(2) β = 0.9 </p>
                </div>
                <div class="chartBox3" id="chart_info3">
                    <div id="myChart3" class="chart_show" ></div>
                    <p style="position: absolute;top:360px;left: 300px;">(3) β = 0.95 </p>
                </div>
                <div class="chartBox4" id="chart_inf4">
                    <div id="myChart4" class="chart_show" ></div>
                    <p style="position: absolute;top:360px;left: 300px;">(4) β = 0.98 </p>
                </div>
            </div>


            <div id="myChart1_info"  style="width: 1000px;margin-top: 30px;margin-left: 120px;font-size: 20px">
                <p style="text-indent: 40px">选取最近50天的股票价格进行预测，通过选取合适的β为权重因子对股票进行预测，
                    β为权重因子, β 越小，过去过去累计值的权重越低，当前抽样值的权重越高，移动平均值的实时性就越强。
                    反之 β 越大，吸收瞬时突发值的能力变强，平稳性更好。</p>
            </div>
            <div style="font-size: 16px;margin-left: 120px">
                <div style="display: inline-block">
                    <p style="display: inline-block">指数移动平均选择的算法为：</p><img src="../images/cal_1.png">
                </div>
                <div style="display: inline-block">
                    <p style="display: inline-block">修正指数移动平均选择的算法为：</p><img src="../images/cal2.png">
                </div>
            </div>
            <div id="table1_info" style="display: none;margin-top: 30px;padding: 0 80px;">
                <table class="table table-hover table-bordered" align="center">
                    <thead>
                    <tr>
                        <td>预测算法</td>
                        <td>β因子</td>
                        <td>第一天</td>
                        <td>第二天</td>
                        <td>第三天</td>
                        <td>第四天</td>
                        <td>第五天</td>
                        <td>第六天</td>
                        <td>第七天</td>
                        <td>第八天</td>
                        <td>第九天</td>
                        <td>第十天</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr id="info1">
                        <td></td>
                        <td>0.5</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr id="info2">
                        <td></td>
                        <td>0.5</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr id="info3">
                        <td></td>
                        <td>0.9</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr id="info4">
                        <td></td>
                        <td>0.9</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr id="info5">
                        <td></td>
                        <td>0.95</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr id="info6">
                        <td></td>
                        <td>0.95</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr id="info7">
                        <td></td>
                        <td>0.98</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr id="info8">
                        <td></td>
                        <td>0.98</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <div id="explain"  style="width: 1000px;margin-top: 30px;margin-left: 120px;font-size: 20px">
                <p style="text-indent: 40px">
                    通过计算4个不同β因子的修正指数移动平均预测数据，将预测数据与真实数据进行比对，
                    计算出50条预测数据与真实数据的平均偏差,最终得出的结果为：
                </p>
                <table class="table table-hover table-bordered" align="center" style="width: 80%">
                    <thead>
                        <tr>
                            <td>β因子</td>
                            <td >偏差值</td>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>0.5</td>
                            <td id="err1">0</td>
                        </tr>
                        <tr>
                            <td>0.9</td>
                            <td id="err2">0</td>
                        </tr>
                        <tr>
                            <td>0.95</td>
                            <td id="err3">0</td>
                        </tr>
                        <tr>
                            <td>0.98</td>
                            <td id="err4">0</td>
                        </tr>
                    </tbody>
                </table>

                <p style="text-indent: 220px" id="sele_b">
                </p>
            </div>

            <div id="myChart_next"  style="width: 700px;height: 400px;margin: 0 auto;"></div>

            <div style="height: 200px;">
                <div id="last" style="margin: 0 150px;text-indent: 50px;font-size: 25px"></div>
            </div>
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