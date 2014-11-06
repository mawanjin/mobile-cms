
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
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
        新闻管理
        <small>
            <i class="icon-double-angle-right"></i>
            新闻列表
        </small>
    </h1>
</div>

<div class="row">
    <div class="col-xs-12">

        <%@include file="/WEB-INF/jsp/common/message.jsp" %>
        <form:form action="${ctx}/news/index.do" method="post"
                   class="form-inline">
            <input type="text" name="Q_LIKE_title" value="${param.Q_LIKE_title}" placeholder="标题">
            <a class="btn submitbtn">
                <i class="icon-search icon-large"></i>&nbsp;&nbsp;查找
            </a>

        </form:form>
        <div class="hr hr-15 dotted hr-double"></div>
        <form action="${ctx}/news/delete2.do" method="post " id="cmd">
            <a class="btn btn-primary"
               href="${ctx}/news/edit.do">
                <i class="icon-plus icon-large"></i>添加
            </a>
            <a class="btn submitbtn">
                <i class="icon-remove icon-large"></i>删除
            </a>
            <a class="btn" id="up">
                <i class="icon-circle-arrow-up icon-large"></i>上线
            </a>
            <a class="btn" id="down">
                <i class="icon-circle-arrow-down icon-large"></i>下线
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
                        <th>标题</th>
                        <th>状态</th>
                        <th>创建人</th>

                        <th>最后修改时间</th>
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
                            <td>${item.online}</td>
                            <td>${item.oper}</td>

                            <td>
                                <fmt:formatDate value="${item.operTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                            </td>
                            <td>
                                <div class="action-buttons">
                                    <a href="${ctx}/news/edit.do?id=${item.id}" title="编辑" class="green"><i
                                            class="icon-edit bigger-120"></i></a>
                                    <a href="${ctx}/news/delete.do?id=${item.id}" class="red" title="删除"><i
                                            class="icon-trash bigger-120"></i></a>

                                    <a href="${ctx}/news/fabu.do?id=${item.id}&flag=up"  class="yellow" title="上线"><i
                                            class="icon-circle-arrow-up bigger-120"></i></a>

                                    <a href="${ctx}/news/fabu.do?id=${item.id}&flag=down"  class="red" title="下线"><i
                                            class="icon-circle-arrow-down bigger-120"></i></a>
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

</body>
</html>