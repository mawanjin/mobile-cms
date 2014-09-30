<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="page-header">
    <h1>
        自动回复
        <small>
            <i class="icon-double-angle-right"></i>
            编辑关键字自动回复
        </small>
    </h1>
</div>

<div class="row">
    <div class="col-xs-12">
        <%@include file="/WEB-INF/jsp/common/message.jsp" %>
        <div class="row">
            <div class="col-xs-12">
                <form:form action="${ctx}/save.do" method="post"
                           class="form-horizontal validate_frm">
                    <form:hidden path="id"/>
                    <input type="hidden" value="${dlgResult}" name="dlgResult" id="dlgResult"/>
                    <form:hidden path="type"/>
                    <div class="form-group">
                        <form:label path="keyWord" class="col-sm-3 control-label no-padding-right">关键字:</form:label>
                        <div class="col-sm-9">
                            <form:input path="keyWord" class="required col-md-10" placeholder="关键字1|关键字2|.."/>
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label path="node"
                                    class="col-sm-3 control-label no-padding-right">绑定消息:</form:label>
                        <div class="col-sm-9">
                            <a class="ajax btn icon-hand-up"
                               href="${ctxRoot}/admin/cms/dialog.do?childAjax=false&multi=false&existIds=${dlgResult}">
                                选择
                            </a>
                            <span id="article_title">${command.node.title}</span>
                        </div>
                    </div>


                    <div class="clearfix form-actions">
                        <input type="submit" id="submit" style="display: none"/>

                        <div class="col-md-offset-9 col-md-3">
                            <a class="btn btn-primary submitbtn">
                                <i class="icon-ok bigger-110"></i>保存
                            </a>
                            &nbsp;
                            <a class="btn history" type="button">
                                <i class="icon-arrow-left bigger-110"></i>返回
                            </a>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        dialog_success = function (article_list) {
            $("#article_title").html(article_list[0].title);
        };
    });
</script>
</body>
</html>