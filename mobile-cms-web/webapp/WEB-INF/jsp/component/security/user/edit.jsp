<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="page-header">
    <h1>
        用户管理
        <small>
            <i class="icon-double-angle-right"></i>
            编辑用户信息
        </small>
    </h1>
</div>

<div class="row">
    <div class="col-xs-12">
        <%@include file="/WEB-INF/jsp/common/message.jsp" %>
        <form:form action="${ctx}/user/save.do" method="post" class="form-horizontal validate_frm">
            <form:hidden path="id"/>
            <div class="form-group">
                <form:label path="loginName" class="col-sm-3 control-label no-padding-right">登录名:</form:label>
                <div class="col-sm-9">
                    <form:input class="col-sm-5 required" path="loginName" placeholder="用户名"/>
                </div>
            </div>
            <div class="form-group">
                <form:label path="password" class="col-sm-3 control-label no-padding-right">密码:</form:label>
                <div class="col-sm-9">
                    <form:password class="col-sm-5 required" path="password" placeholder="密码"/>
                </div>
            </div>
            <div class="form-group">
                <form:label path="name" class="col-sm-3 control-label no-padding-right">真实姓名:</form:label>
                <div class="col-sm-9">
                    <form:input class="col-sm-5 required" path="name"/>
                </div>
            </div>

            <div id="roleType" class="form-group">
                <form:label path="roleIds" class="col-sm-3 control-label no-padding-right">角色:</form:label>
                <div class="col-sm-9">
                    <form:radiobuttons cssClass="required" path="roleIds" items="${roles}" itemValue="id"
                                       itemLabel="cnName"/>
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