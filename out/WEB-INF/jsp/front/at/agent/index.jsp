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
            window.location.href = "${ctx}/agent/findByTbSectionAgentId.do?id=" + $("#section").val();
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
    <h2 style="background:#6699cc;color:#ffffff;margin-bottom: 25px;padding-top:10px;padding-bottom: 10px;padding-left: 5px;">网点查询</h2>
    <div style="height: 25px;">&nbsp;</div>
    <div style="width: 100%;text-align: center;margin-bottom: 25px;">
        <span>片区:</span>&nbsp;&nbsp;&nbsp;
        <select id="section" style="width: 120px;">
            <option value="0">请选择片区</option>
            <c:forEach var="section" items="${sections}">
                <option
                        <c:if test="${section.id eq sections_id}">selected="selected"</c:if>
                        value="${section.id}">${section.sectionName}</option>
            </c:forEach>
        </select></div>
    <div style="width: 100%;text-align: center;"><div id="select" style="width: 50%;margin-bottom: 25px;" class="button white bigrounded">查询</div></div>
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
