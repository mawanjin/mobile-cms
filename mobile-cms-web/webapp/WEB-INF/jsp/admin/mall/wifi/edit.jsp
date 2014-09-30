<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page import="com.joinsoft.framework.web.StringJspWriter" %>
<%@ page import="com.joinsoft.framework.web.json.JsonDialog" %>
<%@ page import="com.joinsoft.framework.web.json.JsonObject" %>
<%@ page import="com.joinsoft.mobile.cms.entity.enumerate.WifiCodeStatus" %>
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
                <h4 class="blue bigger">编辑WIFI串码</h4>
            </div>
            <form:form id="primary" action="${ctx}/wifi/save.do" method="post" class="form-horizontal validate_frm">
                <div class="modal-body">
                    <div class="row">
                        <form:hidden path="id"/>
                        <div class="form-group">
                            <label  class="col-sm-4 control-label  no-padding-right">状态:</label>
                            <div class="col-sm-8">
                                <form:select   class="input-large required" path="status" items="<%=WifiCodeStatus.values()%>"
                                        itemLabel="title">
                                </form:select>
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