<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
</head>
<body>
<div class="document">
    <c:forEach var="article" items="${articles}" varStatus="status">
        <c:choose>
            <c:when test="${status.first}">
                <a href="${article.contentUrl}">
                    <div class="doc_pic"><img src="${article.picUrl}"/>

                        <p>${article.title}</p>
                    </div>
                </a>
            </c:when>
            <c:otherwise>
                <a href="${article.contentUrl}">
                    <div class="doc_txt">
                        <img src="${article.picUrl}"/>

                        <p>${article.title}</p>
                    </div>
                </a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</div>
</body>
</html>