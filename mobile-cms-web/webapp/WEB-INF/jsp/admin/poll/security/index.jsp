<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title></title>
    <script language="JavaScript">
        $(function(){
            $("a.submitbtn_op").on("click", function(){
                var u = "visible=" + $(this).attr("visible");
                var form = $(this).closest("form");
                form.attr("action", form.attr("action") + u);
                $("#real_submitbtn_op").click();
            });
        });
    </script>
</head>
<body>
<div class="page-header">
    <h1>
        <a href="${ctx}/poll/index.do">
            投票管理
        </a>
        <small>
            <i class="icon-double-angle-right"></i>
            开放权限用户列表
        </small>
    </h1>
</div>

<div class="row">
    <div class="col-xs-12">

        <%@include file="/WEB-INF/jsp/common/message.jsp" %>
        <div class="row">

            <div class="col-xs-12">
                <form:form action="${ctx}/poll/security/index.do" method="post"
                           class="form-inline">
                    <input type="text" name="Q_LIKE_user.name" value="${param['Q_LIKE_user.name']}" placeholder="用户姓名">
                    <input type="text" name="Q_LIKE_user.loginName" value="${param['Q_LIKE_user.loginName']}" placeholder="用户登录名">
                    <input type="hidden" name="Q_EQ_poll.node.id" value="${param['Q_EQ_poll.node.id']}">
                    <a class="btn submitbtn">
                        <i class="icon-search icon-large"></i>&nbsp;&nbsp;查找
                    </a>
                </form:form>
                <div class="hr hr-15 dotted hr-double"></div>
                <form action="${ctx}/poll/security/delete.do?poll_node_id=${param['Q_EQ_poll.node.id']}" method="post">
                    <a class="btn submitbtn">
                        <i class="icon-lock icon-large"></i>对选中用户关闭
                    </a>
                    <a class="btn btn-primary ajax" href="${ctx}/poll/security/edit.do?child_ajax=false&poll_node_id=${param['Q_EQ_poll.node.id']}">
                        <i class="icon-unlock icon-large"></i>开放给指定用户
                    </a>
                    <hr/>
                    <c:if test="${page.totalElements eq 0}"><label>已经对所有用户开放</label></c:if>
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th class="center">
                                    <label><input type="checkbox" class="ace"><span class="lbl"></span></label>
                                </th>
                                <th>用户姓名</th>
                                <th>用户登录名</th>
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
                                    <td>${item.user.name}</td>
                                    <td>${item.user.loginName}</td>
                                    <td>
                                        <div class="action-buttons">
                                            <a href="${ctx}/poll/security/delete.do?poll_node_id=${param['Q_EQ_poll.node.id']}&ids=${item.id}"
                                               title="关闭" class="red">
                                                <i class="icon-lock bigger-120"></i></a>
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