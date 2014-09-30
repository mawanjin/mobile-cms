<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="page-header">
    <h1>
        通知管理
        <small>
            <i class="icon-double-angle-right"></i>
            通知列表
        </small>
    </h1>
</div>

<div class="row">
    <div class="col-xs-12">
        <%@include file="/WEB-INF/jsp/common/message.jsp" %>
        <div class="row">
            <ul class="nav nav-tabs" id="tab_notify">
                <c:forEach var="type" items="${NotificationType}">
                    <li class="dropdown"  id="notify_${type}">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            ${type.title}
                            <i class="icon-caret-down bigger-110 width-auto"></i>
                        </a>

                        <ul class="dropdown-menu dropdown-info">
                            <c:forEach var="status" items="${NotificationStatus}">
                                 <li id="status_${status}">
                                    <a  href="${ctx}/notify/index.do?status=${status}&type=${type}">@${status.title}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </li>
                </c:forEach>
            </ul>
            <hr/>
            <div class="col-xs-12">

                <form:form action="${ctx}/notify/index.do?status=${Q_status}&type=${Q_type}" method="post" class="form-inline">
                    <input type="text" name="Q_LIKE_title" value="${param.Q_LIKE_title}" placeholder="通知">

                    <a class="btn submitbtn">
                        <i class="icon-search icon-large "></i>&nbsp;&nbsp;查找
                    </a>
                </form:form>
                <div class="hr hr-15 dotted hr-double"></div>

                <form  method="post" id="frm_del_deal" action="${ctx}/notify/delete.do?status=${Q_status}&type=${Q_type}">
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
                                <th>通知</th>
                                <th>通知类型</th>
                                <th>创建时间</th>
                                <th>处理时间</th>
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
                                           ${item.type.title}-${item.status.title}
                                    </td>
                                    <td>
                                        <fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </td>
                                    <td>
                                        <fmt:formatDate value="${item.readTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </td>

                                    <td>
                                        <i class="${item.cssClass}"></i>
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
    $(function(){
        selectTab();
    });
    //选择tab处于选中状态
    function selectTab(){
        var type = "${EQ_type}";
        var status = "${EQ_status}";
        if(status==""){
            $("#notify_Info").addClass("active");
        }else{
            $("#notify_"+type).addClass("active");
            $("#notify_"+type+" #status_"+status).addClass("active");
        }
    }
</script>
</body>
</html>