<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>

<head>
    <meta name="viewport"
          content="width=device-width,target-densitydpi=high-dpi,initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=false"/>
    <title></title>
    <link rel="stylesheet" href="${ctxRoot}/static/css/goods.css"/>

</head>
<body>
<script>
    $(function () {
        $("#select").bind("click", function () {
            if ($("#yundan").val() == "" && $("#box").val() == "") {
                return;
            }
            location.href = "${ctxRoot}/front/at/goods/findGoods.do?yundan=" + $("#yundan").val() + "&box=" + $("#box").val() + "";
        });
    });
</script>
<div id="mytable">
    <h2 style="background:#6699cc;color:#ffffff;padding-top:10px;padding-bottom: 10px;padding-left: 5px;margin-bottom: 10px;">
        货物跟踪</h2>
    <table width="100%" border="0px solid red">
        <tr>
            <td width="25%">&nbsp;</td>
            <td width="25%" align="center">运单号:</td>
            <td width="25%"><input type="text" id="yundan" value="${yundan}"  class="editbox"></td>
            <td width="25%">&nbsp;</td>
        </tr>
        <tr><td colspan="4" height="20px"></td></tr>
        <tr>
            <td width="25%">&nbsp;</td>
            <td align="center">箱号:</td>
            <td><input value="${box}" type="text" id="box" class="editbox"></td>
            <td width="25%">&nbsp;</td>
        </tr>
        <tr><td colspan="4" height="20px"></td></tr>
        <tr><td colspan="4" valign="middle" align="center"><a href="#" id="select"
                               style="width: 70px;margin-bottom: 25px;"
                               class="button white bigrounded">查询</a></td></tr>
    </table>

    <c:if test="${not empty hint}">
        <div>未找到符合条件的记录</div>
    </c:if>


    <c:forEach var="info" items="${infos}">
        <ul>
            <li class="red">运单号:&nbsp;&nbsp;${info.运单号}</li>
            <li class="row">箱号:&nbsp;&nbsp;${info.箱号}</li>
            <li class="row">始发港:&nbsp;&nbsp;${info.始发港}</li>
            <li class="row">目的港:&nbsp;&nbsp;${info.目的港}</li>
            <li class="row">目前状态:&nbsp;&nbsp;${info.目前状态}</li>
            <li class="row">目前状态时间:&nbsp;&nbsp;<fmt:formatDate value="${info.目前状态起始时间}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
            <li class="row">预期状态:&nbsp;&nbsp;${info.预期状态}</li>
            <li class="row">预期状态时间:&nbsp;&nbsp;<fmt:formatDate value="${info.预期状态起始时间}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
            <li class="row">一程船名航次:&nbsp;&nbsp;${info.一程船名航次}</li>
            <li class="row">二程船名航次:&nbsp;&nbsp;${info.二程船名航次}</li>
            <li class="row">三程船名航次:&nbsp;&nbsp;${info.三程船名航次}</li>
            <li class="row">四程船名航次:&nbsp;&nbsp;${info.四程船名航次}</li>
            <li class="row">五程船名航次:&nbsp;&nbsp;${info.五程船名航次}</li>
            <li class="row">六程船名航次:&nbsp;&nbsp;${info.六程船名航次}</li>
            <li class="row">预配船名航次:&nbsp;&nbsp;${info.预配船名航次}</li>
            <li class="row">预计开航时间:&nbsp;&nbsp;<fmt:formatDate value="${info.预计开航时间}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
        </ul>
    </c:forEach>



    <%--<table class="bordered">--%>
        <%--<caption></caption>--%>
        <%--<thead>--%>
        <%--<tr>--%>
            <%--<th scope="col">运单号</th>--%>
            <%--<th scope="col">箱号</th>--%>
            <%--<th scope="col">始发港</th>--%>
            <%--<th scope="col">目的港</th>--%>
            <%--<th scope="col">目前状态</th>--%>
            <%--<th scope="col">目前状态时间</th>--%>
            <%--<th scope="col">预期状态</th>--%>
            <%--<th scope="col">预期状态时间</th>--%>
            <%--<th scope="col">一程船名航次</th>--%>
            <%--<th scope="col">二程船名航次</th>--%>
            <%--<th scope="col">三程船名航次</th>--%>
            <%--<th scope="col">四程船名航次</th>--%>
            <%--<th scope="col">五程船名航次</th>--%>
            <%--<th scope="col">六程船名航次</th>--%>
            <%--<th scope="col">预配船次</th>--%>
            <%--<th scope="col">预计开航时间</th>--%>
        <%--</tr>--%>
        <%--</thead>--%>
        <%--<tbody>--%>
        <%--<c:forEach var="info" items="${infos}">--%>
            <%--<tr>--%>
                <%--<td class="row">${info.运单号}</td>--%>
                <%--<td class="row">${info.箱号}</td>--%>
                <%--<td class="row">${info.始发港}</td>--%>
                <%--<td class="row">${info.目的港}</td>--%>
                <%--<td class="row">${info.目前状态}</td>--%>
                <%--<td class="row"><fmt:formatDate value="${info.目前状态起始时间}" pattern="yyyy-MM-dd HH:mm:ss"/></td>--%>
                <%--<td class="row">${info.预期状态}</td>--%>
                <%--<td class="row"><fmt:formatDate value="${info.预期状态起始时间}" pattern="yyyy-MM-dd HH:mm:ss"/></td>--%>
                <%--<td class="row">${info.一程船名航次}</td>--%>
                <%--<td class="row">${info.二程船名航次}</td>--%>
                <%--<td class="row">${info.三程船名航次}</td>--%>
                <%--<td class="row">${info.四程船名航次}</td>--%>
                <%--<td class="row">${info.五程船名航次}</td>--%>
                <%--<td class="row">${info.六程船名航次}</td>--%>
                <%--<td class="row">${info.预配船名航次}</td>--%>
                <%--<td class="row"><fmt:formatDate value="${info.预计开航时间}" pattern="yyyy-MM-dd HH:mm:ss"/></td>--%>
            <%--</tr>--%>
        <%--</c:forEach>--%>
        <%--<c:if test="${not empty hint}">--%>
            <%--<tr>--%>
                <%--<td class="row">未找到符合条件的记录!!</td>--%>
            <%--</tr>--%>
        <%--</c:if>--%>
        <%--</tbody>--%>
    <%--</table>--%>
</div>
</body>
</html>
