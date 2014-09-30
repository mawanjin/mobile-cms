<div id="alert_message"></div>
<c:if test="${not empty global_message}">
    <div class="alert alert-warning">
        <button type="button" class="close" data-dismiss="alert">
            <i class="icon-remove"></i>
        </button>

        <c:forEach var="item" items="${global_message}">
            ${item.message}<br/>
        </c:forEach>
    </div>
</c:if>
<c:if test="${not empty global_success}">
    <div class="alert alert-success">
        <button type="button" class="close" data-dismiss="alert">
            <i class="icon-remove"></i>
        </button>

        <c:forEach var="item" items="${global_success}">
            <i class="icon-ok"></i>
            ${item.message}<br/>
        </c:forEach>
    </div>
</c:if>
<c:if test="${not empty global_error}">
    <div class="alert alert-danger">
        <button type="button" class="close" data-dismiss="alert">
            <i class="icon-remove"></i>
        </button>

        <c:forEach var="item" items="${global_error}">
            <i class="icon-remove"></i>
            ${item.message}<br/>
        </c:forEach>
    </div>
</c:if>