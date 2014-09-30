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
            <th>当月获取总流量</th>
            <td>
                ${empty myTraffic.currentMonthTraffic?0:myTraffic.currentMonthTraffic}MB
            </td>
        </tr>
    </table>
    <div class="switch_tab"><span class="tab te1 ts">本月流量历史</span><span class="tab te2">生效</span></div>
    <table class="score_box tag1">
        <c:forEach items="${myTraffic.currentMonthRecords}" var="record">
            <tr class="ln1">
                <th>
                    <fmt:formatDate value="${record.createTime}" pattern="yyyy-MM-dd"/>
                </th>
                <td>
                        ${record.status.title} ${record.traffic}MB
                </td>
            </tr>
        </c:forEach>
    </table>
    <table class="score_box tag2">
        <form action="${ctx}/my/apply.do" method="post" class="apply_form">
            <tr class="ln1">
                <th colspan="2">
                    您的可生效流量：
                    <c:choose>
                        <c:when test="${empty myTraffic.availableTraffic }">
                            0
                            <input type="hidden" name="availableTraffic" id="availableTraffic" value="0"/>
                        </c:when>
                        <c:otherwise>
                            ${myTraffic.availableTraffic}
                            <input type="hidden" name="availableTraffic" id="availableTraffic"
                                   value="${myTraffic.availableTraffic}"/>
                        </c:otherwise>
                    </c:choose>
                    MB
                </th>
            </tr>

            <tr class="ln1">
                <th>申请生效</th>
                <td>
                    <%--<img src="${ctxRoot}/static/front/images/btn_minus.gif" class="count rem" id="min"/>--%>
                    <input type="text" value="100" class="ipt_4" id="text_box" readonly name="applyTraffic"/>
                    <%--<img src="${ctxRoot}/static/front/images/btn_plus.gif" class="count add" id="add"/>--%>
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <input type="submit" value="生效申请提交" disabled  class="btn_1 gra"/>
                </td>
            </tr>
            <tr  style="text-align: center;color: red;">
                <th colspan="2">
                    10月28日正式开启此生效申请提交功能
                </th>
            </tr>
        </form>
    </table>

</div>

</body>
</html>
