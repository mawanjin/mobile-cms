<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title></title>
</head>
<body>

<tags:datefilter/>
<table class="received_box">
    <c:if test="${empty myOrders}">
        <tr class="ln1">
            <th colspan="3" style="text-align:center;"> 对不起，没有兑换记录！</th>

        </tr>
    </c:if>
    <c:forEach items="${myOrders}" var="myOrder">
        <tr class="ln1">
            <th><img src="${vfsRoot}/${myOrder.orderItems[0].product.pic}"/></th>
            <td>${fn:length(myOrder.orderItems) }个</td>
            <td><input type="button" value="详细" class="btn_detail cya"></td>
        </tr>
        <c:forEach items="${myOrder.orderItems}" var="orderItem">
            <tr class="ln3">
                <td colspan="3">
                    串码：${orderItem.wifiCode}<br>
                    所需积点：${orderItem.product.price}点<br>
                    <fmt:formatDate value="${myOrder.order.createTime}" pattern="yyyy年MM月dd日"/>兑换
                </td>
            </tr>
        </c:forEach>

    </c:forEach>

    <tr>
        <td colspan="3">
            <input type="button" value="继续兑换" class="btn_1 ora btn_a" href="${ctx}/mall/index.do"/>
        </td>
    </tr>
</table>
</div>
<div class="space"></div>
</body>
</html>