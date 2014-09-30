<%@ page import="com.joinsoft.weixin.cms.web.front.FrontController" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<%
    response.setContentType("text/javascript");
%>
var img = '<img src="{0}{1}" width="${w}" height="${h}"/>'.format("${ctxPic}", "${ad.pic}");
var aHtml = '<a href="${ctxRoot}/<%=FrontController.PORTAL_PREFIX%>/ad/visit.do?id=${ad.id}" target="_blank" title="${ad.title}">'+img+'</a>';
document.write(aHtml);
