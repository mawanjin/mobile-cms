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
                <h4 class="blue bigger">编辑配置项</h4>
            </div>
            <form:form id="primary" action="${ctx}/config/save.do" method="post" class="form-horizontal validate_frm">

            <div class="modal-body">
                <div class="row">
                    <form:hidden path="id"/>
                    <div class="form-group">
                        <label  class="col-sm-4 control-label  no-padding-right">配置项名称:</label>
                        <div class="col-sm-8">
                            <c:choose>
                                <c:when test="${not empty command.id}">
                                    <form:input class="input-large required" readonly="true"  path="name"/>
                                </c:when>
                                <c:otherwise>
                                    <form:input class="input-large required"  path="name"/>
                                </c:otherwise>
                            </c:choose>

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-12 col-sm-4 no-padding-right"  >配置项中文名称:</label>
                        <div class="col-xs-12 col-sm-8">
                            <div class="clearfix">
                                <form:input class="input-large required" path="cnName"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-12 col-sm-4 no-padding-right"  >配置项值:</label>
                        <div class="col-xs-12 col-sm-8">
                            <div class="clearfix">
                                <form:input class="input-large required" path="val"/>
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