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


        });

    </script>
</head>
<body>

<div class="page-header">
    <h1>
        网点管理
        <small>
            <i class="icon-double-angle-right"></i>
            编辑网点
        </small>
    </h1>
</div>

<div class="row">
    <div class="col-xs-12">
        <%@include file="/WEB-INF/jsp/common/message.jsp" %>
        <form:form action="${ctx}/agent/save.do" method="post"
                   class="form-horizontal validate_frm">
            <form:hidden path="id"/>

            <div class="form-group">
                <form:label path="section" class="col-sm-3 control-label no-padding-right">片区:</form:label>
                <div class="col-sm-9">
                    <form:select path="section">
                        <form:options items="${options}" itemLabel="title" itemValue="val"/>
                    </form:select>
                </div>
            </div>

            <div class="form-group">
                <form:label path="agentName" class="col-sm-3 control-label no-padding-right">部门:</form:label>
                <div class="col-sm-9">
                    <form:input class="col-sm-6 required" path="agentName" />
                </div>
            </div>

            <div class="form-group">
                <form:label path="header" class="col-sm-3 control-label no-padding-right">负责人:</form:label>
                <div class="col-sm-9">
                    <form:input class="col-sm-6 required" path="header" />
                </div>
            </div>

            <div class="form-group">
                <form:label path="telephone" class="col-sm-3 control-label no-padding-right">电话:</form:label>
                <div class="col-sm-9">
                    <form:input class="col-sm-6 required" path="telephone" />
                </div>
            </div>

            <div class="form-group">
                <form:label path="fax" class="col-sm-3 control-label no-padding-right">传真:</form:label>
                <div class="col-sm-9">
                    <form:input class="col-sm-6 required" path="fax" />
                </div>
            </div>

            <div class="form-group">
                <form:label path="addr" class="col-sm-3 control-label no-padding-right">网点地址:</form:label>
                <div class="col-sm-9">
                    <form:input class="col-sm-6 required" path="addr" />
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