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

<c:if test="${!childAjax}">
    <div class="modal fade" id="cms_dialog">
    <div class="modal-dialog">
    <div class="modal-content">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="blue bigger">请选择需要添加的单图文</h4>
    </div>
    <div class="modal-body">
    <form:form action="${ctx}/dialog.do" method="post" class="form-inline ajaxForm">
        <input type="hidden" name="childAjax" value="true"/>
        <form:hidden path="type"/>
        <form:hidden path="multi"/>
        <form:input path="title" placeholder="内容标题"/>
        <c:if test="${empty command.type}">
            <form:select path="type" cssClass="dialog_type" multiple="false">
                <option value="">全部</option>
                <form:options items="${contentType}" itemLabel="title"/>
            </form:select>
        </c:if>
        <a class="btn btn-primary submitbtn">
            <i class="icon-search"></i>&nbsp;&nbsp;查找
        </a>
        <a class="btn btn-success finish_select">
            <i class="icon-ok"></i>&nbsp;&nbsp;确定
        </a>
    </form:form>
    <div class="hr hr-15 dotted hr-double"></div>
</c:if>
<div id="append_parent_child" class="table-responsive">
    <table class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
            <th class="center">
                勾选
            </th>
            <th>#</th>
            <th>内容标题</th>
            <th>创建人</th>
            <th>类型</th>
            <th>最后修改时间</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${page.content}">
            <tr>
                <td class="center">
                    <label>
                        <c:choose>
                            <c:when test="${command.multi}">
                                <input type="checkbox" class="ace dialog_select" name="ids" id="ids"
                                       value="${item.id}">
                            </c:when>
                            <c:otherwise>
                                <input type="radio" class="ace dialog_select" name="ids" id="ids"
                                       value="${item.id}">
                            </c:otherwise>
                        </c:choose>
                        <span class="lbl">  </span>
                    </label>

                </td>
                <td>${item.id}</td>
                <td id="article_title_${item.id}">${item.title}</td>
                <td>${item.author.loginName}</td>
                <td>${item.typeDescription}</td>
                <td>
                    <fmt:formatDate value="${item.lastModified}"
                                    pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>


            </tr>
        </c:forEach>

        </tbody>
    </table>
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
<script type="text/javascript">
    $(function () {
        $(".dialog_type").change(function () {
            var type = $(this).val();
            var url = "${pageurl}?${searchParams}&type=" + type;
            X.get(url);
        });

        //保存checkbox选中状态
        var t = $("#dlgResult").val();
        if (t) {
            t = t.split(",");
            $(t).each(function (index, item) {
                $("input[value='" + item + "']").attr("checked", "checked");
            });
        }

        //触发checkbox事件
        $(".dialog_select").on("click", function () {
            var val = $(this).val();
            if ($(this).attr("type") == "radio") {
                $("#dlgResult").val(val);
                return;
            }
            var old_data = $("#dlgResult").val();
            if (this.checked) {
                $("#dlgResult").val(old_data + val + ",");
            } else {
                $("#dlgResult").val(old_data.replace(val, ""));
            }
        });

        //回放数据
        $(".finish_select").unbind("click").click(function () {
            var val = $("#dlgResult").val();
            if (val[val.length - 1] == ",") {
                val = val.substring(0, val.length - 1);
            }
            if (val[0] == ",") {
                val = val.substring(1, val.length);
            }
            $("#dlgResult").val(val);
            if (typeof dialog_success !== 'undefined' && $.isFunction(dialog_success)) {
                var article_list = Array();
                $("td[id^=article_title_]").each(function (index, item) {
                    var ele = $(item);
                    var id = ele.attr("id").replace("article_title_", "");
                    if (val.indexOf(id) != -1) {
                        article_list.push({id: id, title: ele.html()});
                    }
                });
                dialog_success(article_list);
            }
            $(".modal").modal("hide");
        });
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