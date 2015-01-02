<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<div class="navbar-container" id="navbar-container">
    <div class="navbar-header pull-left">
        <a href="#" class="navbar-brand">
            <small>
                <i class="icon-leaf"></i>
                微信CMS系统
            </small>
        </a><!-- /.brand -->
    </div>
    <!-- /.navbar-header -->

    <div class="navbar-header pull-right" role="navigation">
        <ul class="nav ace-nav">

            <li class="grey">
                <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                    <i class="icon-tasks"></i>
                    <span class="badge badge-grey">${fn:length(backProgressList)}</span>
                </a>

                <ul class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
                    <li class="dropdown-header">
                        <i class="icon-ok">
                            ${fn:length(backProgressList)}条任务正在执行
                        </i>
                    </li>

                    <c:forEach var="back_progress" items="${backProgressList}">
                        <li>
                            <a href="${componentCtx}/basic/task/view.do?id=${back_progress.id}" class="ajax">
                                <div class="clearfix">
                                    <span class="pull-left">${back_progress.title}</span>
                                    <span class="pull-right">${back_progress.progress}%</span>
                                </div>

                                <div class="progress progress-mini ">
                                    <div style="width:${back_progress.progress}%" class="progress-bar "></div>
                                </div>
                            </a>
                        </li>
                    </c:forEach>

                    <li>
                        <a href="${componentCtx}/basic/task/index.do">
                            查看所有
                            <i class="icon-arrow-right"></i>
                        </a>
                    </li>
                </ul>
            </li>
            <li class="purple">
                <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                    <i class="icon-bell-alt icon-animated-bell"></i>
                    <span class="badge badge-important">
                        ${fn:length(notificationsList)}
                    </span>
                </a>

                <ul class="pull-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
                    <li class="dropdown-header">
                        <i class="icon-warning-sign"></i>
                        ${fn:length(notificationsList)}条通知
                    </li>

                    <c:forEach var="notification" items="${notificationsList}">
                        <li>
                            <a href="${ctxAdmin}/basic/notify/view.do?id=${notification.id}" class="ajax">
                                <div class="clearfix">
											<span class="pull-left">
												<i class="${notification.cssClass}"></i>
                                                ${notification.title}

											</span>
                                </div>
                            </a>
                        </li>
                    </c:forEach>

                    <li>
                        <a href="${componentCtx}/basic/notify/index.do">
                            查看所有
                            <i class="icon-arrow-right"></i>
                        </a>
                    </li>
                </ul>
            </li>

            <li class="light-blue">
                <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                    <img class="nav-user-photo" src="${ctxAvatar}/${loginUser.avatar}" style="width:40px;height:40px;" alt="您的头像"/>
								<span class="user-info">
									<small>欢迎,</small>
									<s:principal/>
								</span>

                    <i class="icon-caret-down"></i>
                </a>

                <ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                    <li>
                        <a href="#">
                            <i class="icon-cog"></i>
                            设置
                        </a>
                    </li>

                    <li>
                        <a href="${componentCtx}/security/user/profile.do">
                            <i class="icon-user"></i>
                            我的资料
                        </a>
                    </li>

                    <li class="divider"></li>


                    <li>
                        <%--<a href="${ctxRoot}/logout">--%>
                        <a href="${ctxRoot}/admin/dologout.do">
                            <i class="icon-off"></i>
                            退出
                        </a>
                    </li>
                </ul>
            </li>
        </ul>
        <!-- /.ace-nav -->
    </div>
    <!-- /.navbar-header -->
</div>
