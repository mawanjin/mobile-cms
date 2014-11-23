<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <%--<meta name="viewport" content="width=device-width,target-densitydpi=high-dpi,initial-scale=1.0, minimum-scale=0.2, maximum-scale=2.0, user-scalable=yes"/>--%>
    <title></title>
    <script>
        function findpage(page) {
            location.href = "${ctxRoot}/front/at/news/findPage.do?page_number=" + page;
        }
        function pagingquery(bool) {
            var page;
            if (!bool) {
                page = parseInt($("#page").val()) - 1;
            } else {
                page = parseInt($("#page").val()) + 1;
            }
            if (page < 0) {
                return;
            }
            if (page == parseInt($("#end").val())) {
                return;
            }
            location.href = "${ctxRoot}/front/at/news/findPage.do?page_number=" + page;
        }
    </script>

</head>
<body>
<div id="mytable"
     style="width: 100%;height: 100%;background: #ffffff;font-family: 'trebuchet MS', 'Lucida sans', Arial;font-size: 12px;color: #444;">
    <input type="hidden" id="page" value="${page_number}"/>
    <input type="hidden" id="end" value="${end}-1"/>

    <h2 style="background:#6699cc;color:#ffffff;padding-top:10px;padding-bottom: 10px;padding-left: 5px;margin-bottom: 10px;">新闻中心</h2>
    <table style="width: 100%;margin: 0 auto;border:0px solid red;" cellspacing="0">
        <c:forEach var="new1" items="${news}">
            <tr style="border-bottom: 1px dotted #000000;height: 35px;font-size: 15px;">
                <td>&nbsp;<a href="${ctxRoot}/front/at/news/findByTbNewsId.do?id=${new1.id}"
                                         style="color: #444;">
                    <c:choose>
                    <c:when test="${fn:length(new1.title) > 10}">
                        <c:out value="${fn:substring(new1.title, 0, 16)}..."/>
                    </c:when>
                    <c:otherwise>
                        <c:out value="${new1.title}"/>
                    </c:otherwise>
                    </c:choose>
                </td>
                <td style="text-align: right;"><fmt:formatDate value="${new1.operTime}"
                                                               type="date"/>&nbsp;&nbsp;&nbsp;</td>
            </tr>
        </c:forEach>
    </table>
    <div id="page_div" style="width: 100%;margin: 0 auto;text-align: center;margin-top: 10px;">
        <a href="javaScript:findpage(0)"
           style="width: 45px;height:15px;border:1px solid #444;color:#000000;display:inline-block;">首页</a>
        <a href="javaScript:pagingquery(false)"
           style="width: 45px;height:15px;border:1px solid #444;color:#000000;display:inline-block;">上一页</a>
        <c:forEach var="page" begin="${begin}" end="${pages}" step="1">
            <c:if test="${page_number==page-1}">
                <a href="javaScript:findpage(${page}-1)"
                   style="width: 15px;border:0px solid #444;height:15px;display:inline-block;color: #000000;">${page}</a>
            </c:if>
            <c:if test="${page_number!=page-1}">
                <a href="javaScript:findpage(${page}-1)"
                   style="width: 15px;border:1px solid #444;height:15px;display:inline-block;">${page}</a>
            </c:if>
        </c:forEach>
        <a href="javaScript:pagingquery(true)"
           style="width: 45px;height:15px;border:1px solid #444;color:#000000;display:inline-block;">下一页</a>
        <a href="javaScript:findpage(${end}-1)"
           style="width: 45px;height:15px;border:1px solid #444;color:#000000;display:inline-block;">尾页</a>
    </div>
</div>
</body>
</html>
