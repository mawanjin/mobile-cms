<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="page-header">
    <h1>
        权限管理
        <small>
            <i class="icon-double-angle-right"></i>
            用户管理
        </small>
    </h1>
</div>

<div class="row">
    <div class="col-xs-12">
        <%@include file="/WEB-INF/jsp/common/message.jsp" %>
        <div class="row">
            <div class="col-xs-12">
                <form:form action="${ctx}/user/index.do" method="post" class="form-inline">
                    <input type="text" name="Q_LIKE_loginName" value="${param.Q_LIKE_loginName}" placeholder="登录名">
                    <input type="text" name="Q_LIKE_name" value="${param.Q_LIKE_name}" placeholder="真实姓名">
                    <a class="btn submitbtn">
                        <i class="icon-search icon-large"></i>&nbsp;&nbsp;查找
                    </a>
                </form:form>
                <div class="hr hr-15 dotted hr-double"></div>

                <form action="${ctx}/user/delete.do" method="post">
                    <a class="btn btn-primary" href="${ctx}/user/edit.do">
                        <i class="icon-plus icon-large"></i>新建
                    </a>
                    <a class="btn submitbtn">
                        <i class="icon-remove icon-large "></i>删除
                    </a>

                    <hr/>

                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th class="center">
                                    <label><input type="checkbox" class="ace"><span class="lbl"></span></label>
                                </th>
                                <th>#</th>
                                <th>登录名</th>
                                <th>姓名</th>
                                <th>角色</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach var="item" items="${page.content}">
                                <tr>
                                    <td class="center">
                                        <label><input type="checkbox" class="ace" name="ids" id="ids"
                                                      value="${item.id}">
                                            <span class="lbl"></span>
                                        </label>
                                    </td>
                                    <td>${item.id}</td>
                                    <td>${item.loginName}</td>
                                    <td>${item.name}</td>
                                    <td>${item.roleNames}</td>
                                    <td>
                                        <div class="action-buttons">
                                            <a href="${ctx}/user/edit.do?id=${item.id}" title="编辑" class="green"><i
                                                    class="icon-edit bigger-120"></i></a>
                                            <a href="${ctx}/user/delete.do?id=${item.id}" class="red delete" title="删除"><i
                                                    class="icon-trash bigger-120"></i></a>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                    </div>
                    <div class="row">
                        <tags:page page="${page}"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>