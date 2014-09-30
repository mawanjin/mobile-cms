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
        <a class="btn ajax" href="${ctx}/menu/edit.do">
            <i class="icon-plus icon-large "></i>添加
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
                                            <i class="${menu.cssClass} bigger-100"></i> ${menu.title}
                                            <div class="pull-right action-buttons">
                                                <a class="green ajax" href="${ctx}/menu/sub_edit.do?parentId=${menu.id}">
                                                    <i class="icon-plus bigger-130"></i>
                                                </a>
                                                <a class="blue ajax" href="${ctx}/menu/edit.do?id=${menu.id}">
                                                    <i class="icon-pencil bigger-130"></i>
                                                </a>
                                                <a class="red" href="${ctx}/menu/delete.do?id=${menu.id}">
                                                    <i class="icon-trash bigger-130"></i>
                                                </a>
                                            </div>
                                        </div>
                                        <c:if test="${fn:length(menu.subMenu) ne 0}">
                                            <ol class="dd-list">
                                                <c:forEach var="subMenu" items="${menu.subMenu}">
                                                    <li class="dd-item" data-id="3">
                                                        <div class="dd-handle">
                                                                ${subMenu.title}
                                                            <div class="pull-right action-buttons">
                                                                <a class="blue ajax" href="${ctx}/menu/sub_edit.do?id=${subMenu.id}&parentId=${menu.id}">
                                                                    <i class="icon-pencil bigger-130"></i>
                                                                </a>

                                                                <a class="red" href="${ctx}/menu/delete.do?id=${subMenu.id}">
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
