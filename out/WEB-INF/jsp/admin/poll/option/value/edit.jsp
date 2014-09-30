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
                <h4 class="blue bigger">编辑投票选项[${command.pollOption.title}]的内容</h4>
            </div>
            <form:form id="primary" action="${ctx}/poll/option/value/save.do" method="post"
                       class="form-horizontal validate_frm" enctype="multipart/form-data">
                <div class="modal-body">
                    <div class="row">
                        <form:hidden path="id"/>
                        <form:hidden path="pollOption.id"/>
                        <div class="form-group">
                            <label class="col-sm-4 control-label  no-padding-right">类型:</label>

                            <div class="col-sm-8">
                                <form:select path="type">
                                    <form:options items="${menuTypes}" itemLabel="text"/>
                                </form:select>
                            </div>
                        </div>

                        <div class="form-group" id="_about_String">
                            <label  class="col-sm-4 control-label  no-padding-right">内容:</label>
                            <div class="col-sm-8">
                                <form:input class="input-large required" path="value"/>
                            </div>
                        </div>
                        <div class="form-group" id="_about_Video">
                            <label class="control-label col-xs-12 col-sm-4 no-padding-right">视频:</label>

                            <div class="col-xs-12 col-sm-8">
                                <div class="clearfix">
                                    <form:input class="input-large required url" path="video" placeholder="请填写视频地址"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" id="_about_Pic">
                            <label class="control-label col-xs-12 col-sm-4 no-padding-right">图片:</label>
                            <div class="col-xs-12 col-sm-8">
                                <div class="clearfix">
                                    <input type="file" id="file" class="col-sm-12 file" name="pic"
                                           width="225" height="125">
                                    <div id="preview_file">
                                        <c:choose>
                                            <c:when test="${not empty command.pic}">
                                                <img src="${vfsRoot}${command.pic}" id="img0" width="225">
                                            </c:when>
                                            <c:otherwise>
                                                <div class="center"><h2>请选择图片</h2></div>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <c:if test="${command.pollOption.poll.type ne 'CommonPoll'}">
                            <div class="form-group">
                                <label class="col-sm-4 control-label  no-padding-right">设为答案:</label>
                                <form:checkbox path="answer" cssClass="checkbox-inline"/>
                            </div>
                        </c:if>
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
<script language="JavaScript" type="text/javascript">
    $(function () {
        $("#type").on("change", function () {
            $("div[id^=_about_]").hide();
            $("#_about_" + $(this).val()).show();
        });
        $("#type").change();
    });

</script>
<%
    response.setHeader("Content-Type", "text/plain");
    JsonDialog dialog = new JsonDialog();
    dialog.setHtml(out.toString());
    JsonObject<JsonDialog> json = JsonObject.dialog(dialog);
    ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(response.getWriter(), json);
%>