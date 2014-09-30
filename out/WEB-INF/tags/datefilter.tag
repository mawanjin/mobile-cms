<%@tag pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>

<div class="calendar">
    <c:choose>
        <c:when test="${month - 1 > 0}">
            <a href="?year=${year}&month=${month-1}">
                <span class="arr_cal"><img src="${ctxRoot}/static/front/images/arrow_left.png"/></span>
            </a>
        </c:when>
        <c:when test="${(month-1)eq 0 }">
            <a href="?month=12&year=${year-1}">
                <span class="arr_cal"><img src="${ctxRoot}/static/front/images/arrow_left.png"/></span>
            </a>
        </c:when>
        <c:otherwise>
            <span class="arr_cal nomore"><img src="${ctxRoot}/static/front/images/arrow_left.png"/></span>
        </c:otherwise>
    </c:choose>

    <div class="title_cal">
        <h2>${year}年</h2>

        <h3>${month}月</h3>
    </div>

    <c:choose>
        <c:when test="${month < currentMonth}">
            <a href="?year=${year}&month=${month+1}">
                <span class="arr_cal"><img src="${ctxRoot}/static/front/images/arrow_right.png"/></span>
            </a>
        </c:when>
        <c:when test="${(month >= currentMonth)  and (year < currentYear)}">
            <a href="?month=1&year=${year+1}">
                <span class="arr_cal"><img src="${ctxRoot}/static/front/images/arrow_right.png"/></span>
            </a>
        </c:when>
        <c:otherwise>
            <span class="arr_cal nomore"><img src="${ctxRoot}/static/front/images/arrow_right.png"/></span>
        </c:otherwise>
    </c:choose>
</div>