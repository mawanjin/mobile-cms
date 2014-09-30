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
                <h4 class="blue bigger">绑定配置项</h4>
            </div>
            <form:form id="primary" action="${ctx}/action/bind.do" method="post"
                       class="form-horizontal validate_frm">

                <div class="modal-body">
                    <div class="row">
                        <input type="hidden" name="id" value="${id}"/>

                        <div class="col-xs-12 table-responsive">
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>配置项</th>
                                    <th>值</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="config" items="${configs}">
                                    <tr>
                                        <td> ${config.cnName}</td>
                                        <td>
                                            <input type="text" name="val" value="${config.val}"/>
                                            <input type="hidden" name="ids" value="${config.id}"/>
                                        </td>
                                    </tr>
                                </c:forEach>

                                </tbody>
                            </table>
                        </div>

                    </div>
                    <div class="modal-footer">
                        <input type="submit" id="submit" style="display: none"/>
                        <a href="#" class="btn" data-dismiss="modal">
                            <i class="icon-arrow-left icon-large"></i>&nbsp;&nbsp;取消
                        </a>
                        <a href="${ctx}/action/unbind.do?id=${id}" class="btn btn-danger">
                            <i class="icon-remove icon-large"></i>&nbsp;&nbsp;删除
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