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
                <h4 class="blue bigger">选择自动回复类型</h4>
            </div>
            <form:form id="primary" action="${ctx}/edit.do" method="post" class="form-horizontal validate_frm">

                <div class="modal-body">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-sm-4 control-label  no-padding-right">选择类型:</label>

                            <div class="col-sm-8">
                                <select name="type">
                                    <c:forEach items="${replyTypes}" var="type">
                                        <option value="${type}">${type.title}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <input type="submit" id="submit" style="display: none"/>
                    <a href="#" class="btn" data-dismiss="modal">
                        <i class="icon-arrow-left icon-large"></i>&nbsp;&nbsp;取消
                    </a>
                    <a class="btn btn-primary submitbtn">
                        <i class="icon-save icon-large"></i>&nbsp;&nbsp;提交
                    </a>
                </div>
            </form:form>
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