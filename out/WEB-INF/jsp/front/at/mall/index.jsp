<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title></title>
</head>
<body>

<div class="exchange_box">
    <c:choose>
        <c:when test="${page.number >0 }">
            <a href="${ctx}/mall/index.do?page=${page.number}">

            </a>
        </c:when>
        <c:otherwise>
            <span class="arr_exc nomore"><img src="${ctxRoot}/static/front/images/arrow_cleft.png"/></span>
        </c:otherwise>
    </c:choose>

    <div class="exchange_frame">
        <div class="exchange_thread">
            <c:choose>
                <c:when test="${fn:length(page.content) eq 0 }">
                    <p>对不起，暂无商品！</p>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${page.content}" var="product">
                        <img src="${vfsRoot}/${product.pic}"/>

                        <p>${product.title}</p>
                        <c:set value="${product.id}" var="productId"></c:set>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <c:choose>
        <c:when test="${page.number+2<=page.totalPages}">
            <a href="${ctx}/mall/index.do?page=${page.number+2}">
                <span class="arr_exc"><img src="${ctxRoot}/static/front/images/arrow_cright.png"/></span>
            </a>
        </c:when>
        <c:otherwise>
            <span class="arr_exc nomore"><img src="${ctxRoot}/static/front/images/arrow_cright.png"/></span>
        </c:otherwise>
    </c:choose>
</div>
<c:if test="${fn:length(page.content) > 0 }">
    <table class="score_box">
        <tr>
            <td colspan="2">
                <input type="button" value="兑换" class="btn_1 ora btn_a"
                       href="${ctx}/mall/product.do?id=${productId}"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="button" value="兑换记录" class="btn_1 cya btn_a" href="${ctx}/my/order.do"/>
            </td>
        </tr>
    </table>
</c:if>
<div class="space"></div>
</body>
</html>