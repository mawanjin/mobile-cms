<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="page bgray">
    <div class="exc_inforbox" style="display: block;">
        <img src="${ctxRoot}/static/front/images/btn_close.png" class="btn_quit" />
        <c:if test="${success}">
            <img src="${ctxRoot}/static/front/images/icon_success.png" class="hinticon" />
            <h1>投票成功！</h1>
        </c:if>
        <c:if test="${!success}">
            <img src="${ctxRoot}/static/front/images/icon_fail.png" class="hinticon" />
            <h1>投票失败！${message}</h1>
        </c:if>
    </div>
</div>
</body>
</html>
