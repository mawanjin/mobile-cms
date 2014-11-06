<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <script src='${ctxRoot}/static/framework/js/jquery-1.10.2.min.js'></script>
    <style>
        a {
            text-decoration: none;
        }

    </style>
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

    <h2 style="background:#6699cc;color:#ffffff;">新闻中心</h2>
    <table style="width: 98%;margin: 0 auto;border:0px solid red;" cellspacing="2">
        <c:forEach var="new" items="${news}">
            <tr >
                <td valign="middle" height="40px"  style=" border-bottom-style:dashed;  border-width:1px; border-color:#000000;">&nbsp;&nbsp;&nbsp;<a href="${ctxRoot}/front/at/news/findByTbNewsId.do?id=${new.id}"
                                         style="color: #444;">
                    <c:choose>
                        <c:when test="${fn:length(new.title) > 10}">
                            <c:out value="${fn:substring(new.title, 0, 16)}..."/>
                        </c:when>
                        <c:otherwise>
                            <c:out value="${new.title}"/>
                        </c:otherwise>
                    </c:choose>
                </a>
                </td>
                <td valign="middle" style="text-align: right;border-bottom-style:dashed;  border-width:1px; border-color:#000000;"><fmt:formatDate value="${new.operTime}"
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
