<%@tag pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="menulist" type="java.util.List" %>

<ul class="submenu">
    <c:forEach var="submenu" items="${menulist}">
        <li>
        <li class="<c:if test="${item.url}">active</c:if>">
            <c:if test="${empty item.subMenu}">
                <a href="${ctxRoot}${item.url}">
                    <i class="icon-dashboard"></i>
                    <span class="${item.cssClass}">${item.title}</span>
                </a>
            </c:if>
            <c:if test="${not empty item.subMenu}">
                <a href="#">
                    <i class="icon-dashboard"></i>
                    <span class="${item.cssClass}">${item.title}</span>
                </a>

            </c:if>
        </li>
            <a href="elements.html">
                <i class="icon-double-angle-right"></i>
                Elements
            </a>

        </li>
    </c:forEach>
</ul>