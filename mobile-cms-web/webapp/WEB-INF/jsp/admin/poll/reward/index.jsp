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
            参与用户列表
        </small>
    </h1>
</div>

<div class="row">
    <div class="col-xs-12">

        <%@include file="/WEB-INF/jsp/common/message.jsp" %>
        <div class="row">

            <div class="col-xs-12">
                <form:form action="${ctx}/poll/reward/index.do" method="post"
                           class="form-inline">
                    <input type="text" name="Q_LIKE_user.name" value="${param['Q_LIKE_user.name']}" placeholder="用户姓名">
                    <input type="text" name="Q_LIKE_user.loginName" value="${param['Q_LIKE_user.loginName']}" placeholder="用户登录名">
                    <input type="hidden" name="Q_EQ_poll.node.id" value="${param['Q_EQ_poll.node.id']}">
                    <input type="hidden" name="Q_EQ_reward" value="${param.Q_EQ_reward}">
                    <a class="btn submitbtn">
                        <i class="icon-search icon-large"></i>&nbsp;&nbsp;查找
                    </a>
                    <br><br>
                    <ul class="nav nav-tabs" _target_input="Q_EQ_reward">
                        <li class="<c:if test="${empty param.Q_EQ_reward}">active</c:if>">
                            <a class="nav_tab_filter" href="">全部</a>
                        </li>
                        <li class="<c:if test="${!empty param.Q_EQ_reward && param.Q_EQ_reward}">active</c:if>">
                            <a class="nav_tab_filter" href="true">已兑奖</a>
                        </li>
                        <li class="<c:if test="${!empty param.Q_EQ_reward && !param.Q_EQ_reward}">active</c:if>">
                            <a class="nav_tab_filter" href="false">未兑奖</a>
                        </li>
                    </ul>
                </form:form>
                <div class="hr hr-15 dotted hr-double"></div>
                <form action="${ctx}/poll/reward/reward.do?poll_id=${poll.id}" method="post" class="ajaxForm">
                    <a class="btn submitbtn">
                        <i class="icon-lock icon-large"></i>对选中用户兑奖
                    </a>
                    <a class="btn btn-primary ajax" href="${ctx}/poll/reward/all_reward.do?poll_id=${poll.id}">
                        <i class="icon-unlock icon-large"></i>全部兑奖
                    </a>
                    <hr/>
                    <c:if test="${page.totalElements eq 0}"><label>没有用户参与记录</label></c:if>
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th class="center">
                                    <label><input type="checkbox" class="ace"><span class="lbl"></span></label>
                                </th>
                                <th>用户姓名</th>
                                <th>用户登录名</th>
                                <c:if test="${poll.type eq 'SurveyScore'}">
                                    <th>回答正确/一共回答</th>
                                </c:if>
                                <th>兑奖状态</th>
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
                                    <td>${item.name}</td>
                                    <td>${item.loginName}</td>
                                    <c:if test="${poll.type eq 'SurveyScore'}">
                                        <td>${item.correct}/${item.correct + item.wrong}</td>
                                    </c:if>
                                    <td><c:if test="${item.reward}">已</c:if><c:if test="${!item.reward}">未</c:if>兑奖</td>
                                    <td>
                                        <div class="action-buttons">
                                            <a href="${ctx}/poll/reward/reward.do?poll_id=${poll.id}&ids=${item.id}"
                                               title="兑奖" class="green ajax<c:if test="${item.reward}"> hidden</c:if>">
                                                <i class="icon-gift bigger-120"></i></a>
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