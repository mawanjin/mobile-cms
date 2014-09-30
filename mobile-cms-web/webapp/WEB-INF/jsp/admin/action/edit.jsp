<%@ page import="com.joinsoft.mobile.cms.entity.enumerate.ValueType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${ctxRoot}/static/css/cms.css"/>
    <script src="${ctxRoot}/static/tinymce/tinymce.min.js"></script>
    <script>
        $(function () {
            if (!"${command.id}" || "${command.valueType}" == "<%=ValueType.Fixed%>") {
                $("#maxValue").attr("disabled", true);
            }
        });
        function switchValueType() {
            var valueType = $("#valueType").val();
            if (valueType == "<%=ValueType.Fixed%>") {
                $("#maxValue").attr("disabled", true);
            } else if (valueType == "<%=ValueType.Rand%>") {
                $("#maxValue").attr("disabled", false);
            }
        }
    </script>
</head>
<body>

<div class="page-header">
    <h1>
        规则管理
        <small>
            <i class="icon-double-angle-right"></i>
            编辑规则
        </small>
    </h1>
</div>

<div class="row">
    <div class="col-xs-12">
        <%@include file="/WEB-INF/jsp/common/message.jsp" %>
        <form:form action="${ctx}/action/save.do" method="post"
                   class="form-horizontal validate_frm">
            <form:hidden path="id"/>
            <div class="form-group">
                <form:label path="key" class="col-sm-3 control-label no-padding-right">动作:</form:label>
                <div class="col-sm-9">
                    <form:select path="key">
                        <form:options items="${hooks}" itemLabel="description" itemValue="key"/>
                    </form:select>
                </div>
            </div>
            <div class="form-group">
                <form:label path="valueType" class="col-sm-3 control-label no-padding-right">值类型:</form:label>
                <div class="col-sm-9">
                    <form:select path="valueType" items="${valueTypes}" itemLabel="title" id="valueType"
                                 onchange="switchValueType();"
                                 class="col-sm-6 required "/>
                </div>
            </div>
            <div class="form-group">
                <form:label path="minValue" class="col-sm-3 control-label no-padding-right">最小值:</form:label>
                <div class="col-sm-9">
                    <form:input class="col-sm-6 required" path="minValue" id="minValue"/>
                </div>
            </div>
            <div class="form-group">
                <form:label path="maxValue" class="col-sm-3 control-label no-padding-right">最大值:</form:label>
                <div class="col-sm-9">
                    <form:input class="col-sm-6 required" path="maxValue" id="maxValue"/>
                </div>
            </div>
            <div class="form-group">
                <form:label path="cycle" class="col-sm-3 control-label no-padding-right">周期类型:</form:label>
                <div class="col-sm-9">
                    <form:select path="cycle" items="${cycles}" class="col-sm-6 required " itemLabel="title"/>
                </div>
            </div>
            <div class="form-group">
                <form:label path="count" class="col-sm-3 control-label no-padding-right">次数:</form:label>
                <div class="col-sm-9">
                    <form:input class="col-sm-6" path="count"/>
                </div>
            </div>
            <div class="form-group">
                <form:label path="count" class="col-sm-3 control-label no-padding-right">是否有效:</form:label>
                <div class="col-sm-9">
                    <form:radiobuttons path="isValid" items="${options}" itemLabel="title" itemValue="val"/>
                </div>
            </div>
            <div class="clearfix form-actions">
                <input type="submit" id="submit" style="display: none"/>

                <div class="col-md-offset-3 col-md-9">
                    <a class="btn btn-primary submitbtn">
                        <i class="icon-ok bigger-110"></i>保存
                    </a>
                    &nbsp;
                    <a class="btn history" type="button">
                        <i class="icon-arrow-left bigger-110"></i>返回
                    </a>
                </div>
            </div>

        </form:form>
    </div>
</div>

</body>
</html>