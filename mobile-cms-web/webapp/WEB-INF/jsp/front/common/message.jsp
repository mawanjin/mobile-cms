<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title></title>

</head>
<body>
<div class="login_top">
    <img src="${ctxRoot}/static/front/images/loginTop.jpg"/>
</div>
<table class="login_box">
    <tr class="ln1" >
        <td colspan="2" style="text-align: center;">${message}
            <c:if test="${ not empty result}" >
               <br/>获得
                <c:choose>
                    <c:when test="${result.pointVal == 0}">
                        ${result.trafficVal}MB流量
                    </c:when>
                    <c:otherwise>
                        ${result.pointVal}积点
                    </c:otherwise>
                </c:choose>
            </c:if>
        </td>
    </tr>
    <tr>
        <td>
            <input type="button" value="关闭" class="btn_1 cya weixin_close"/>
        </td>
    </tr>
</table>
</body>
</html>
