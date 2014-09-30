<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="page-header">
    <h1>
        配置管理
        <small>
            <i class="icon-double-angle-right"></i>
            配置内容
        </small>
    </h1>
</div>

<div class="row">
    <div class="col-xs-12">

        <%@include file="/WEB-INF/jsp/common/message.jsp" %>
        <div class="row">

            <div class="col-xs-12">
                <form:form action="${ctx}/config/index.do" method="post"
                           class="form-inline">
                    <input type="text" name="Q_LIKE_name" value="${param.Q_LIKE_name}" placeholder="配置项">
                    <a class="btn submitbtn">
                        <i class="icon-search "></i>&nbsp;&nbsp;查找
                    </a>

                </form:form>

                <div class="hr hr-15 dotted hr-double"></div>
                <form action="${ctx}/config/delete.do" method="post">
                    <a class="btn btn-primary ajax"
                       href="${ctx}/config/edit.do">
                        <i class="icon-plus"></i>添加
                    </a>
                    <a class="btn submitbtn">
                        <i class="icon-remove"></i>删除
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
                                <th>配置项中文名称</th>
                                <th>配置项名称</th>
                                <th>配置项值</th>
                                <th>绑定动作</th>
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
                                    <td>${item.cnName}</td>
                                    <td>${item.name}</td>
                                    <td>${item.val}</td>

                                    <td>
                                        <c:choose>
                                            <c:when test="${empty item.action}">全局配置</c:when>
                                            <c:otherwise>${item.action.cnName}</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <div class="action-buttons">
                                            <a href="${ctx}/config/edit.do?id=${item.id}" title="编辑" class="green ajax"><i
                                                    class="icon-edit bigger-120"></i></a>
                                            <a href="${ctx}/config/delete.do?ids=${item.id}" class="red" title="删除"><i
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