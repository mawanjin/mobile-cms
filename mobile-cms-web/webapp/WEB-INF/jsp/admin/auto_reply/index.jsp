<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="page-header">
    <h1>
        自动回复管理
        <small>
            <i class="icon-double-angle-right"></i>
            自动回复
        </small>
    </h1>
</div>

<div class="row">
    <div class="col-xs-12">

        <%@include file="/WEB-INF/jsp/common/message.jsp" %>
        <div class="row">
            <div class="col-xs-12">
                <a class="btn btn-primary ajax"
                   href="${ctx}/reply_type.do">
                    <i class="icon-plus"></i>添加
                </a>
                <hr/>
                <div class="table-responsive">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>回复类型</th>
                            <th>关键字</th>
                            <th>动作</th>
                            <th>绑定消息</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${autoReplyList}" varStatus="status">
                            <tr>
                                <td>${item.id}</td>
                                <td>${item.type.title}</td>
                                <td>${item.keyWord}</td>
                                <td>${item.eventKey}</td>
                                <td>
                                        ${empty item.node?"无关联消息":item.node.title}
                                </td>
                                <td>
                                    <div class="action-buttons">
                                        <a href="${ctx}/edit.do?id=${item.id}"
                                           title="编辑"
                                           class="green"><i
                                                class="icon-edit bigger-120"></i>编辑</a>
                                        <a href="${ctx}/delete.do?id=${item.id}"
                                           class="red delete"
                                           title="删除"><i
                                                class="icon-trash bigger-120"></i>删除</a>
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
            </div>
        </div>
    </div>
</div>

</body>
</html>