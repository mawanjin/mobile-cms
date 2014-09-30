<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title></title>


</head>
<body>

<div class="page-header">
    <h1>
        菜单管理
        <a class="btn btn-primary" href="${ctx}/menu/edit.do">
            <i class="icon-plus icon-large"></i>添加
        </a>
        <a class="btn ajax btn-purple show_modal" href="${ctx}/menu/sync.do">
            <i class="icon-upload icon-large"></i>同步
        </a>
    </h1>
</div>

<div class="content">
    <div class="row">
        <div class="col-xs-12">
            <%@include file="/WEB-INF/jsp/common/message.jsp" %>
            <div class="row">
                <div class="col-md-offset-2 col-md-8">
                    <div class="dd" id="nestable">
                        <ol class="dd-list">
                            <c:forEach var="menu" items="${menuItems}">
                                <li class="dd-item" data-id="1">
                                    <div class="dd-handle">
                                        <i class="icon-angle-right bigger-100"></i> ${menu.name}
                                        <div class="pull-right action-buttons">
                                            <a class="green"
                                               href="${ctx}/menu/edit.do?parentId=${menu.id}">
                                                <i class="icon-plus bigger-130"></i>
                                            </a>
                                            <a class="blue" href="${ctx}/menu/edit.do?id=${menu.id}">
                                                <i class="icon-pencil bigger-130"></i>
                                            </a>
                                            <a class="red delete" href="${ctx}/menu/delete.do?id=${menu.id}">
                                                <i class="icon-trash bigger-130"></i>
                                            </a>
                                        </div>
                                    </div>
                                    <c:if test="${fn:length(menu.subMenu) ne 0}">
                                        <ol class="dd-list">
                                            <c:forEach var="subButton" items="${menu.subMenu}">
                                                <li class="dd-item" data-id="3">
                                                    <div class="dd-handle">
                                                            ${subButton.name}
                                                        <div class="pull-right action-buttons">
                                                            <a class="blue"
                                                               href="${ctx}/menu/edit.do?id=${subButton.id}&parentId=${subButton.parent.id}">
                                                                <i class="icon-pencil bigger-130"></i>
                                                            </a>

                                                            <a class="red delete"
                                                               href="${ctx}/menu/delete.do?id=${subButton.id}">
                                                                <i class="icon-trash bigger-130"></i>
                                                            </a>
                                                        </div>
                                                    </div>
                                                </li>
                                            </c:forEach>

                                        </ol>
                                    </c:if>
                                </li>

                            </c:forEach>
                        </ol>
                    </div>
                </div>
            </div>
        </div>
    </div>


</div>
</body>
</html>
