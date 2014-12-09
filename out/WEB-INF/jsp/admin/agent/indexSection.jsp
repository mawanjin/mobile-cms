<%@ page import="com.joinsoft.mobile.cms.entity.enumerate.ValueType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible"content="IE=9; IE=8; IE=7; IE=EDGE">
    <title></title>
</head>
<body>
<div class="page-header">
    <h1>
        网点片区管理
        <small>
            <i class="icon-double-angle-right"></i>
            网点片区查询
        </small>
    </h1>
</div>
<c:set value="<%=ValueType.Fixed%>" var="Fixed"/>
<div class="row">
    <div class="col-xs-12">

        <%@include file="/WEB-INF/jsp/common/message.jsp" %>
        <div class="row">

            <div class="col-xs-12">
                <form:form action="${ctx}/agent/indexSection.do" method="post"
                           class="form-inline">
                    <input type="text" name="Q_LIKE_sectionName" value="${param.Q_LIKE_sectionName}" placeholder="片区名称">
                    <a class="btn submitbtn">
                        <i class="icon-search"></i>&nbsp;&nbsp;查找
                    </a>

                </form:form>

                <div class="hr hr-15 dotted hr-double"></div>
                <form action="${ctx}/agent/deleteSection.do" method="post">
                    <a class="btn btn-primary"
                       href="${ctx}/agent/editSection.do">
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
                                <th>片区名称</th>

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
                                    <td>${item.sectionName}</td>
                                   
                                    <td>
                                        <div class="action-buttons">
                                            <a href="${ctx}/agent/editSection.do?id=${item.id}" title="编辑" class="green"><i
                                                    class="icon-edit bigger-120"></i></a>
                                            <a href="${ctx}/agent/deleteSection.do?id=${item.id}" class="red"
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