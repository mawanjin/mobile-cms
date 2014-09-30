<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page import="com.joinsoft.framework.web.StringJspWriter" %>
<%@ page import="com.joinsoft.framework.web.json.JsonDialog" %>
<%@ page import="com.joinsoft.framework.web.json.JsonObject" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<%
    out = new StringJspWriter();
    pageContext.pushBody(out);
%>

<c:if test="${!param.child_ajax}">
    <div class="modal fade">
    <div class="modal-dialog">
    <div class="modal-content">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="blue bigger">请选择需要添加的用户</h4>
    </div>
    <div class="modal-body">
    <form:form action="${ctx}/poll/security/edit.do?poll_node_id=${param.poll_node_id}"
               method="post" class="form-inline ajaxForm">
        <input type="hidden" name="child_ajax" value="true">
        <input type="text" name="Q_LIKE_loginName" value="${param.Q_LIKE_loginName}" placeholder="登录名">
        <input type="text" name="Q_LIKE_name" value="${param.Q_LIKE_name}" placeholder="真实姓名">
        <a class="btn submitbtn">
            <i class="icon-search icon-large"></i>&nbsp;&nbsp;查找
        </a>
    </form:form>
    <div class="hr hr-15 dotted hr-double"></div>
</c:if>
<div id="append_parent_child" class="table-responsive">
    <form action="${ctx}/poll/security/save.do?poll_node_id=${param.poll_node_id}" method="post" class="ajaxForm">
    <table class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
            <th class="center">
                <label><input type="checkbox" class="ace"><span class="lbl"></span></label>
            </th>
            <th>登录名</th>
            <th>用户姓名</th>
            <th>角色</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${page.content}">
            <tr>
                <td class="center">
                    <label>
                        <input type="checkbox" class="ace dialog_select" name="ids" id="ids"
                               value="${item.id}">
                        <span class="lbl">  </span>
                    </label>
                </td>
                <td>${item.loginName}</td>
                <td>${item.name}</td>
                <td>${item.roleNames}</td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
        <a class="btn btn-primary submitbtn">
            <i class="icon-search icon-large"></i>&nbsp;&nbsp;确定
        </a>
    </form>
    <div class="row">
        <tags:page page="${page}"/>
    </div>
</div>

<c:if test="${!childAjax}">
    </div>
    </div>
    </div>
    </div>
</c:if>
<%
    response.setHeader("Content-Type", "text/plain");
    JsonDialog dialog = new JsonDialog();
    dialog.setHtml(out.toString());
    JsonObject<JsonDialog> json = JsonObject.dialog(dialog);
    ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(response.getWriter(), json);
%>