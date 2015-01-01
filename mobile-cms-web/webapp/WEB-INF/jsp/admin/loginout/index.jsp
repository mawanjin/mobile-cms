
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible"content="IE=9; IE=8; IE=7; IE=EDGE">
    <title></title>
    <script type="text/javascript">
        $(function() {
                $("#up").click(function(){

                    $("#cmd").attr("action","${ctx}/news/fabu2.do?flag=up");
                    $("#cmd").attr("method","post");//不要问我为什么，框架欺负我,我很烦~！！！
                    $("#cmd").submit();
                })
            $("#down").click(function(){

                $("#cmd").attr("action","${ctx}/news/fabu2.do?flag=down");
                $("#cmd").attr("method","post");////不要问我为什么，框架欺负我,我很烦~！！！
                $("#cmd").submit();
            })
        })
    </script>

</head>
<body>

<div class="page-header">
    <h1>
        登录登出管理
        <small>
            <i class="icon-double-angle-right"></i>
            统计列表
        </small>
    </h1>
</div>

<div class="row">
    <div class="col-xs-12">

        <%@include file="/WEB-INF/jsp/common/message.jsp" %>
        <form:form action="${ctx}/loginout/index.do" method="post"
                   class="form-inline">
            <input type="text" name="Q_LIKE_name" value="${param.Q_LIKE_name}" placeholder="名称">
            <a class="btn submitbtn">
                <i class="icon-search icon-large"></i>&nbsp;&nbsp;查找
            </a>

        </form:form>
        <div class="hr hr-15 dotted hr-double"></div>
        <form action="${ctx}/loginout/delete2.do" method="post " id="cmd">
            <%--<a class="btn btn-primary"--%>
               <%--href="${ctx}/loginout/edit.do">--%>
                <%--<i class="icon-plus icon-large"></i>添加--%>
            <%--</a>--%>
            <%--<a class="btn submitbtn">--%>
                <%--<i class="icon-remove icon-large"></i>删除--%>
            <%--</a>--%>

            <%--<hr/>--%>
            <div class="table-responsive">
                <table class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr>
                        <th class="center">
                            <label><input type="checkbox" class="ace"><span class="lbl"></span></label>
                        </th>
                        <th>#</th>
                        <th>IP</th>
                        <th>用户名</th>
                        <th>名称</th>
                        <th>登录时间</th>
                        <th>登出时间</th>
                        <!--<th></th>-->
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
                            <td>${item.ip}</td>
                            <td>${item.loginName}</td>
                            <td>${item.name}</td>
                            <td>${item.loginTime}</td>
                            <td>${item.logoutTime}</td>


                            <%--<td>--%>
                                <%--<div class="action-buttons">--%>
                                    <%--<a href="${ctx}/loginout/edit.do?id=${item.id}" title="编辑" class="green"><i--%>
                                            <%--class="icon-edit bigger-120"></i></a>--%>
                                    <%--<a href="${ctx}/loginout/delete.do?id=${item.id}" class="red" title="删除"><i--%>
                                            <%--class="icon-trash bigger-120"></i></a>--%>
                                <%--</div>--%>

                            <%--</td>--%>

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

</body>
</html>