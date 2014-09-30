<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>

<%@ page import="com.joinsoft.framework.web.StringJspWriter" %>
<%@ page import="com.joinsoft.framework.web.json.JsonDialog" %>
<%@ page import="com.joinsoft.framework.web.json.JsonObject" %>
<%@page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/taglib.jsp" %>

<%
    out = new StringJspWriter();
    pageContext.pushBody(out);
%>
<div class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="blue bigger">查看通知</h4>
            </div>
            <div class="modal-body">
                <h3 style="margin-top: -10px;">通知名称: ${notify.title}</h3>
                <h3 >通知类型: ${notify.type.title}</h3>
                <h3 >通知状态: ${notify.status.title}</h3>
                <h3 >通知样式:  <i class="${notify.cssClass}"> </i></h3>
                <h3 >创建时间: <fmt:formatDate value="${notify.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /> </h3>
                <c:if test="${'Read' eq notify.status}">
                    <h3 >处理时间: <fmt:formatDate value="${notify.readTime}" pattern="yyyy-MM-dd HH:mm:ss" /> </h3>
                </c:if>
            </div>

        </div>
    </div>
</div>
<%
    response.setHeader("Content-Type", "text/plain");
    JsonDialog dialog = new JsonDialog();
    dialog.setHtml(out.toString());
    JsonObject<JsonDialog> json = JsonObject.dialog(dialog);
    ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(response.getWriter(), json);
%>