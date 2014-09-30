<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="page-header">
    <h1>
        商品管理
        <small>
            <i class="icon-double-angle-right"></i>
            商品列表
        </small>
    </h1>
</div>
<div class="row">
    <div class="col-xs-12">

        <%@include file="/WEB-INF/jsp/common/message.jsp" %>
        <div class="row">

            <div class="col-xs-12">
                <form:form action="${ctx}/product/index.do" method="post"
                           class="form-inline">
                    <input type="text" name="Q_LIKE_title" value="${param.Q_LIKE_title}" placeholder="名称">
                    <a class="btn submitbtn">
                        <i class="icon-search"></i>&nbsp;&nbsp;查找
                    </a>

                </form:form>

                <div class="hr hr-15 dotted hr-double"></div>
                <form action="${ctx}/product/delete.do" method="post">
                    <a class="btn btn-primary"
                       href="${ctx}/product/edit.do">
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
                                <th>商品名称</th>
                                <th>积分</th>
                                <th>状态</th>
                                <%--<th>描述</th>--%>
                                <th>最后操作时间</th>
                                <th>操作</th>
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
                                    <td>${item.title}</td>
                                    <td>${item.price}</td>
                                    <td>${item.status.title}</td>
                                    <%--<td>${item.description}</td>--%>
                                    <td>
                                        <fmt:formatDate value="${item.lastModified}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </td>
                                    <td>
                                        <div class="action-buttons">
                                            <a href="${ctx}/product/edit.do?id=${item.id}" title="编辑" class="green"><i
                                                    class="icon-edit bigger-120"></i></a>
                                            <a href="${ctx}/product/delete.do?id=${item.id}" class="red delete"
                                               title="删除"><i
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