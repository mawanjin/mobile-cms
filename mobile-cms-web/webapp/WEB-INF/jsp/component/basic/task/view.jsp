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
                <h4 class="blue bigger">查看任务</h4>
            </div>
            <div class="modal-body">
                <h3 style="margin-top: -10px;">任务名称:  ${task.title} </h3>
                <h3>状态：${task.status.title}</h3>
                <h3>开始时间:<fmt:formatDate value="${task.startTime}" pattern="yyyy-MM-dd HH:mm:ss" /> </h3>
                <h3>进度： ${task.progress}%</h3>
                <div class="progress progress-striped">
                    <div class="progress-bar " style="width: ${task.progress}%"></div>
                </div>
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