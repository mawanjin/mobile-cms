<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title></title>
    <script src="${ctxRoot}/static/framework/js/echarts-2.0.2/esl/esl.js"></script>
    <style type="text/css">
        .chart_container {
            overflow-wrap: normal;
        }
        .chart_div {
            height: 400px;
            width: 33%;
            float: left;
        }
    </style>
</head>
<body>
<div class="page-header">
    <h1>
        投票结果统计
    </h1>
</div>

<div class="row">
    <div class="col-xs-12">

        <%@include file="/WEB-INF/jsp/common/message.jsp" %>
        <div class="row chart_container">
            <c:forEach var="item" items="${page.content}">
                <c:if test="${item.type eq 'Text'}">
                    <div class="chart_div">文本框类型选项无法展示</div>
                </c:if>
                <c:if test="${item.type ne 'Text'}">
                    <div class="chart_div main_chart" option_id="${item.id}"></div>
                </c:if>
            </c:forEach>
        </div>
        <div class="row">
            <tags:page page="${page}"/>
        </div>
    </div>
</div>

<script type="text/javascript">
    // 路径配置
    require.config({
        paths: {
            'echarts': '${ctxRoot}/static/framework/js/echarts-2.0.2/echarts',
            'echarts/chart/pie': '${ctxRoot}/static/framework/js/echarts-2.0.2/echarts'
        }
    });

    // 使用
    require(
            [
                'echarts',
                'echarts/chart/pie' // 使用饼状图就加载pie模块，按需加载
            ],
            function (ec) {
                var u = "${ctx}/poll/option/value/chart.do";
                $('.main_chart').each(function() {
                    // 基于准备好的dom，初始化echarts图表
                    var myChart = ec.init(this);
                    X.get(u, {option_id: $(this).attr('option_id')},
                    function(data) {
                        var option = data['data'];
                        console.log(option);
                        myChart.setOption(option);
                    });

                });

            }
    );
</script>
</body>
</html>