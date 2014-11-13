<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${ctxRoot}/static/css/agent.css"/>
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
<div id="mytable"
     style="width: 100%;height: 100%;background: #ffffff;font-family: 'trebuchet MS', 'Lucida sans', Arial;font-size: 12px;color: #444;">
    <h2 style="background:#6699cc;color:#ffffff;">货物跟踪</h2>

    <div style="margin-top:10px;text-align: center;">
        <p>运单号&nbsp;&nbsp;<input type="text" id="yundan" value="${yundan}"
                                 style="border:1px solid #000000;">&nbsp;&nbsp;&nbsp;箱&nbsp;号&nbsp;&nbsp;<input
                value="${box}"
                type="text" id="box" style="border:1px solid #000000;">&nbsp;&nbsp;&nbsp;<a href="#" id="select"
                                                                                            style="width: 70px;margin-bottom: 25px;"
                                                                                            class="button white bigrounded">查询</a>
        </p>
    </div>
    <table class="bordered">
        <caption></caption>
        <thead>
        <tr>
            <th scope="col">运单号</th>
            <th scope="col">箱号</th>
            <th scope="col">始发港</th>
            <th scope="col">目的港</th>
            <th scope="col">目前状态</th>
            <th scope="col">目前状态时间</th>
            <th scope="col">预期状态</th>
            <th scope="col">预期状态时间</th>
            <th scope="col">一程船名航次</th>
            <th scope="col">二程船名航次</th>
            <th scope="col">三程船名航次</th>
            <th scope="col">四程船名航次</th>
            <th scope="col">五程船名航次</th>
            <th scope="col">六程船名航次</th>
            <th scope="col">预配船次</th>
            <th scope="col">预计开航时间</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="info" items="${infos}">
            <tr>
                <td class="row">${info.运单号}</td>
                <td class="row">${info.箱号}</td>
                <td class="row">${info.始发港}</td>
                <td class="row">${info.目的港}</td>
                <td class="row">${info.目前状态}</td>
                <td class="row"><fmt:formatDate value="${info.目前状态起始时间}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td class="row">${info.预期状态}</td>
                <td class="row"><fmt:formatDate value="${info.预期状态起始时间}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td class="row">${info.一程船名航次}</td>
                <td class="row">${info.二程船名航次}</td>
                <td class="row">${info.三程船名航次}</td>
                <td class="row">${info.四程船名航次}</td>
                <td class="row">${info.五程船名航次}</td>
                <td class="row">${info.六程船名航次}</td>
                <td class="row">${info.预配船名航次}</td>
                <td class="row"><fmt:formatDate value="${info.预计开航时间}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
