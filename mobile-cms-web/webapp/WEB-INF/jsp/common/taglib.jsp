<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="s" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}${portalPrefix}"/>
<c:set var="componentCtx" value="${pageContext.request.contextPath}/component"/>
<c:set var="ctxRoot" value="${pageContext.request.contextPath}"/>
<c:set var="ctxAvatar" value="${pageContext.request.contextPath}/upload/avatar"/>
<c:set var="vfsRoot" value="${pageContext.request.contextPath}/upload"/>