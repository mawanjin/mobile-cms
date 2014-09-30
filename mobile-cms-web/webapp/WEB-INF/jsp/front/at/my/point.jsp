<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title></title>
</head>
<body>
<tags:datefilter/>
<div class="score_page">
    <table class="score_box">
        <tr class="ln1">
            <th>当前积点</th>
            <td>
                ${empty myPoint.totalPoint?"0": myPoint.totalPoint}积点

            </td>
        </tr>
        <tr class="ln1">
            <th colspan="2">当月消费历史</th>
        </tr>
        <c:choose>
            <c:when test="${empty myPoint.currentMonthConsumeRecords}">
                <tr class="ln1 center">
                    <th colspan="2" style="text-align:center;color:red;">
                        本月暂无消费
                    </th>
                </tr>
            </c:when>
            <c:otherwise>
                <c:forEach items="${myPoint.currentMonthConsumeRecords}" var="record">
                    <tr class="ln1">
                        <th>
                            <fmt:formatDate value="${record.createTime}" pattern="yyyy-MM-dd"/>
                        </th>
                        <td>
                            消费 ${record.point*-1}MB
                        </td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>

    </table>
</div>
</div>
</body>
</html>
