<%@tag pageEncoding="UTF-8"%>
<%@ attribute name="page" type="org.springframework.data.domain.Page" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${not empty page && page.totalElements!=0}">
    <%
        int current = page.getNumber() + 1;
        int paginationSize = page.getSize();
        int begin = Math.max(1, current - paginationSize / 2);
        int end = Math.min(begin + (paginationSize - 1), page.getTotalPages());

        request.setAttribute("current", current);
        request.setAttribute("begin", begin);
        request.setAttribute("end", end);
    %>

    <div class="dataTables_paginate paging_bootstrap">
        <ul class="pagination">
            <% if (page.hasPreviousPage()) {%>
            <li><a href="${pageurl}?page=1&sortType=${sortType}&${searchParams}" class="${isajax}">&lt;&lt;</a></li>
            <li><a href="${pageurl}?page=${current-1}&sortType=${sortType}&${searchParams}" class="${isajax}">&lt;</a></li>
            <%} else {%>
            <li class="disabled"><a href="#">&lt;&lt;</a></li>
            <li class="disabled"><a href="#">&lt;</a></li>
            <%} %>

            <c:forEach var="i" begin="${begin}" end="${end}">
                <c:choose>
                    <c:when test="${i == current}">
                        <li class="active"><a href="${pageurl}?page=${i}&sortType=${sortType}&${searchParams}" class="${isajax}">${i}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${pageurl}?page=${i}&sortType=${sortType}&${searchParams}" class="${isajax}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <% if (page.hasNextPage()) {%>
            <li><a href="${pageurl}?page=${current+1}&sortType=${sortType}&${searchParams}" class="${isajax}">&gt;</a></li>
            <li><a href="${pageurl}?page=${page.totalPages}&sortType=${sortType}&${searchParams}" class="${isajax}">&gt;&gt;</a></li>
            <%} else {%>
            <li class="disabled"><a href="#">&gt;</a></li>
            <li class="disabled"><a href="#">&gt;&gt;</a></li>
            <%} %>

        </ul>
    </div>
</c:if>