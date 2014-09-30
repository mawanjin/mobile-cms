<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title>${article.node.title}</title>
</head>
<body>
<div class="document_content">
    <h1>${article.node.title}</h1>

    <h2>
        <fmt:formatDate value="${article.node.lastModified}" pattern="yyyy-MM-dd HH:mm"/>
        <span>${article.node.authorName}</span>
    </h2>
    <img src="${vfsRoot}/${article.node.picPath}" class="document_picture"/>

    <p>
        ${article.content}
    </p>
</div>
</body>
</html>