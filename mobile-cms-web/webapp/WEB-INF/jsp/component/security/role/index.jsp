<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="page-header">
    <h1>
        角色管理
    </h1>
</div>

<div class="content">
    <div class="row">
        <div class="col-xs-12">
            <%@include file="/WEB-INF/jsp/common/message.jsp" %>
            <div class="row">
                <div class="col-xs-12">
                    <form action="${ctx}/role/delete.do" method="post">
                        <a class="btn btn-primary" href="${ctx}/role/edit.do">
                            <i class="icon-plus icon-large"></i>新建
                        </a>
                        <a class="btn submitbtn">
                            <i class="icon-remove icon-large"></i>删除
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
                                    <th>角色名</th>
                                    <th>中文名</th>
                                    <th>是否内置</th>
                                    <th>权限</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>

                                <c:forEach var="role" items="${roles}">
                                    <tr>
                                        <td class="center">
                                            <label><input type="checkbox" class="ace" name="ids" id="ids"
                                                          value="${role.id}">
                                                <span class="lbl"></span>
                                            </label>
                                        </td>
                                        <td>${role.id}</td>
                                        <td>${role.name}</td>
                                        <td>${role.cnName}</td>
                                        <td>${BooleanMap[role.internal]}</td>
                                        <td>${role.permissions}</td>
                                        <td>
                                            <div class="action-buttons">
                                                <a href="${ctx}/role/edit.do?id=${role.id}" title="编辑" class="green"><i
                                                        class="icon-edit bigger-120"></i></a>
                                                <a href="${ctx}/role/delete.do?id=${role.id}" class="red delete" title="删除"><i
                                                        class="icon-trash bigger-120"></i></a>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>

                                </tbody>
                            </table>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>





</div>

</body>
</html>