<%@ page import="org.apache.shiro.SecurityUtils" %>
<%@ page import="org.apache.shiro.subject.Subject" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<%
    String ctx = request.getContextPath();
    Subject subject = SecurityUtils.getSubject();
    if (subject != null) {
        response.sendRedirect(ctx + "/admin/index.do");

    }
%>
</body>
</html>