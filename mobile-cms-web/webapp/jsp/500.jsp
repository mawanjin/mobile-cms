<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="login_top">
    <img src="${ctxRoot}/static/front/images/loginTop.jpg"/>
</div>
<table class="login_box">
    <tr class="ln1">
        <td>${errorMessage}</td>
    </tr>

    <c:if test="${not empty nextUrl}">
        <tr>
            <td>
                <input type="button" value="返回" class="btn_1 cya btn_a" href="${ctxRoot}${nextUrl}"/>
            </td>
        </tr>
    </c:if>
    <tr>
        <td>
            <input type="button" value="关闭" class="btn_1 cya weixin_close"/>
        </td>
    </tr>
</table>


</body>
</html>