<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="page-header">
    <h1>
        订单管理
        <small>
            <i class="icon-double-angle-right"></i>
            订单详情
        </small>
    </h1>
</div>
<div class="row">
    <div class="col-xs-12">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <div class="widget-box transparent invoice-box">
                    <div class="widget-header widget-header-large">
                        <h3 class="grey lighter pull-left position-relative">
                            <i class="icon-leaf green"></i>
                            商品列表
                        </h3>

                    </div>

                    <div class="widget-body">
                        <div class="widget-main padding-24">
                            <div>
                                <table class="table table-striped table-bordered">
                                    <thead>
                                    <tr>
                                        <th class="center">#</th>
                                        <th>商品名称</th>
                                        <th class="hidden-480">积分</th>
                                        <th class="hidden-480">商品数量</th>
                                        <th>总积分</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <c:forEach items="${orderItems}" var="orderItem" varStatus="status">
                                        <tr>
                                            <td class="center">${status.count}</td>
                                            <td>
                                                    ${orderItem.product.title}
                                            </td>
                                            <td class="hidden-xs">
                                                    ${orderItem.product.score}
                                            </td>
                                            <td class="hidden-480"> ${orderItem.count} </td>
                                            <td> ${orderItem.product.score * orderItem.count }</td>
                                        </tr>
                                    </c:forEach>


                                    </tbody>
                                </table>
                            </div>

                            <div class="hr hr8 hr-double hr-dotted"></div>

                            <div class="row">
                                <div class="col-sm-5 pull-right">
                                    <h4 class="pull-right">
                                        总积分 :
                                        <span class="red">$395</span>
                                    </h4>
                                </div>
                                <div class="col-sm-7 pull-left"> Extra Information</div>
                            </div>

                            <div class="space-6"></div>
                            <div class="well">
                                Thank you for choosing Ace Company products.
                                We believe you will be satisfied by our services.
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>