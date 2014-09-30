<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title></title>
    <script>
        <c:if test="${ not empty result}">
        $(function () {
            $.blockUI({ message: $('.exchange_success') });
        });

        </c:if>
    </script>
</head>
<body>

<div class="exchange_box">
    <div class="exchange_thread">
        <img src="${vfsRoot}/${product.pic}"/>

        <p>${product.title}</p>
    </div>
    <p class="exc_infor">${product.description}</p>
</div>
<table class="score_box">
    <tr>
        <td colspan="2">
            <input type="button" value="确认兑换" class="btn_1 ora btn_a"
                   href="${ctx}/mall/pay.do?productId=${product.id}"/>
        </td>
    </tr>
</table>

<div class="space"></div>
<div class="exc_inforbox exchange_success">
    <img src="${ctxRoot}/static/front/images/btn_close.png" class="btn_quit"/>

    <h1>兑换成功！</h1>

    <p>WIFI串码已在兑换历史</p>

    <div class="pop_btn">
        <input type="button" value="继续兑换" class="ora btn_a" href="${ctx}/mall/index.do"/>

        <input type="button" value="兑换记录" class="cya btn_a" href="${ctx}/my/order.do"/>
    </div>
</div>
</body>
</html>