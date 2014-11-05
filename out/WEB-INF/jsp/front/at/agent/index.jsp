<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${ctxRoot}/static/css/agent.css"/>
    <script type="text/javascript" src="${ctxRoot}/static/js/mSelect.js"></script>
</head>
<body>
<script>
    $(function () {
        $("#select").bind("click", function () {
            location.href = "${ctx}/agent/findByTbSectionAgentId.do?id=" + $("#section").val();
        });
    });
    var mySelect = new mSelect('mySelect', '${ctxRoot}/static/css/mSelect.css');
    window.onload = function () {
        var aS = document.getElementsByTagName('select');
        for (var i = 0; i < aS.length; i++) {
            switch (aS[i].getAttribute('mSty')) {
                case 'redLine':
                    mySelect.Create(aS[i], 'redLine');
                    break;
                case 'blueCircle':
                    mySelect.Create(aS[i], 'blueCircle', true);
                    break;
                case 'orangeHeart':
                    mySelect.Create(aS[i], 'orangeHeart', true);
                    break;
            }
        }
    }

</script>
<div id="mytable">
    <h1 style="margin: 6px 7px;background:#66cccc;color:#ffffff;">网点查询</h1>

    <p style="margin-left: 7px;margin-bottom: 6px;">
        <span>片区:</span>&nbsp;&nbsp;&nbsp;
        <select style="width: 100px;" id="section" mSty="blueCircle">
            <c:forEach var="section" items="${sections}">
                <option
                        <c:if test="${section.id eq sections_id}">selected="selected"</c:if>
                        value="${section.id}">${section.sectionName}</option>
            </c:forEach>
        </select>&nbsp;&nbsp;&nbsp;<a href="#" id="select" class="button white bigrounded">查询</a></p>
    <table class="bordered">
        <caption></caption>
        <thead>
        <tr>
            <th scope="col">部门</th>
            <th scope="col">负责人</th>
            <th scope="col">电话</th>
            <th scope="col">传真</th>
            <th scope="col">邮编</th>
            <th scope="col">地址</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="agent" items="${agents}">
            <tr>
                <td class="row">${agent.agentName}</td>
                <td class="row">${agent.header}</td>
                <td class="row">${agent.telephone}</td>
                <td class="row">${agent.fax}</td>
                <td class="row">${agent.post}</td>
                <td class="row">${agent.addr}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
