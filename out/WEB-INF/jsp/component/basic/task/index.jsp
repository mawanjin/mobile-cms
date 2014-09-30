<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="page-header">
    <h1>
        任务管理
        <small>
            <i class="icon-double-angle-right"></i>
            任务列表
        </small>
    </h1>
</div>

<div class="row">
    <div class="col-xs-12">
        <%@include file="/WEB-INF/jsp/common/message.jsp" %>
        <div class="row">
            <ul class="nav nav-tabs" id="tab_task">
                <c:forEach var="status" items="${TaskStatus}">
                    <li id="task_${status}">
                        <a  href="${ctx}/task/index.do?status=${status}" class="notify_a">
                            ${status.title}
                        </a>
                    </li>
                </c:forEach>
            </ul>
            <hr/>

            <div class="col-xs-12">

                <form:form action="${ctx}/task/index.do?status=${EQ_status}" method="post" class="form-inline">
                    <input type="text" name="Q_LIKE_title" value="${param.Q_LIKE_title}" placeholder="任务名">
                    <a class="btn submitbtn">
                        <i class="icon-search icon-large"></i>&nbsp;&nbsp;查找
                    </a>

                </form:form>
                <div class="hr hr-15 dotted hr-double"></div>

                <form action="${ctx}/task/delete.do?${pageContext.request.queryString}" method="post">
                    <a class="btn submitbtn">
                        <i class="icon-remove icon-large"></i>删除
                    </a> <hr/>
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th class="center">
                                    <label><input type="checkbox" class="ace"><span class="lbl"></span></label>
                                </th>
                                <th>#</th>
                                <th>任务</th>
                                <th>开始时间</th>
                                <th>结束时间</th>
                                <th>状态</th>
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
                                    <td>${item.title}</td>
                                    <td>
                                        <fmt:formatDate value="${item.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </td>
                                    <td>
                                        <fmt:formatDate value="${item.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </td>
                                    <td>
                                        ${item.status.title}
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
<script>
    jQuery(function(){
        selectTab();
    });
    //选择tab处于选中状态
    function selectTab(){
        var status = "${EQ_status}";
        if(status==""){
            $("#task_Ready").addClass("active");
        }else{
            $("#task_"+status).addClass("active");
        }
    }
</script>
</body>
</html>