<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title>参与投票</title>
</head>
<body>
<div class="document_content">
    <h1>${poll.node.title}</h1>

    <h2><fmt:formatDate value="${poll.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/> <span>${poll.node.authorName}</span>
    </h2>

    <form action="${ctx}/cms/submit_poll.do" method="post">
        <input type="hidden" name="poll_id" value="${poll.id}">
        <c:forEach var="option" items="${options}" varStatus="status">
            <div class="vote_thread">
                <h3>${status.count}. ${option.title}</h3>
                <c:if test="${!empty option.values}">
                    <ul>
                        <c:forEach var="item" items="${option.values}">
                            <li>
                                <c:if test="${'CheckBox' == option.type}">
                                    <input type="checkbox"
                                           name="value_checkbox_${option.id}" value="${item.id}">
                                </c:if>
                                <c:if test="${'Radio' == option.type}">
                                    <input type="radio"
                                           name="value_radio_${option.id}" value="${item.id}">
                                </c:if>
                                <c:if test="${item.type eq 'String'}">
                                    ${item.value}
                                </c:if>
                                <c:if test="${item.type eq 'Pic'}">
                                    <img src="${vfsRoot}${item.value}" height="50">
                                </c:if>
                                <c:if test="${item.type eq 'Video'}">
                                    <a href="${item.value}" target="_blank">${item.value}</a>
                                </c:if>
                            </li>
                        </c:forEach>
                    </ul>
                </c:if>
                <c:if test="${'Text' == option.type}">
                    <input type="text" name="value_text_${option.id}" class="ipt_3">
                </c:if>
            </div>
        </c:forEach>
        <input type="submit" value="投　票" class="btn_vote ora"
               <c:if test="${!submit_enable}">disabled="disabled" title="只能预览不能提交" </c:if>/>
    </form>
</div>

</body>
</html>