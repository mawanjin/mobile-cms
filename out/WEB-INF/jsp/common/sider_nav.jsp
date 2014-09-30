<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="sidebar" id="sidebar">

    <div class="sidebar-shortcuts" id="sidebar-shortcuts">
        <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
            <button class="btn btn-success">
                <i class="icon-signal"></i>
            </button>

            <button class="btn btn-info">
                <i class="icon-pencil"></i>
            </button>

            <button class="btn btn-warning">
                <i class="icon-group"></i>
            </button>

            <button class="btn btn-danger">
                <i class="icon-cogs"></i>
            </button>
        </div>

        <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
            <span class="btn btn-success"></span>

            <span class="btn btn-info"></span>

            <span class="btn btn-warning"></span>

            <span class="btn btn-danger"></span>
        </div>
    </div>
    <ul class="nav nav-list">
        <c:forEach var="item" items="${siderNavList}">
            <c:if test="${empty item.subMenu}">
                <li class="<c:if test="${item.isActive}">active </c:if>">
                    <a href="${ctxRoot}${item.url}">
                        <i class="${item.cssClass}"></i>
                        <span class="menu-text">${item.title}</span>
                    </a>
                </li>
            </c:if>
            <!--二级菜单-->
            <c:if test="${not empty item.subMenu}">
                <li id="parent_menu_${item.id}" class="">
                    <a href="#" class="dropdown-toggle">
                        <i class="${item.cssClass}"></i>
                        <span class="menu-text">${item.title}</span>
                        <b class="arrow icon-angle-down"></b>
                    </a>
                    <ul class="submenu">
                        <c:forEach var="submenu" items="${item.subMenu}">
                            <li class="<c:if test="${submenu.isActive}">active</c:if>">
                                <a href="${ctxRoot}${submenu.url}">
                                    <i class="icon-double-angle-right"></i>
                                        ${submenu.title}
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </li>
            </c:if>
        </c:forEach>
    </ul>

    <div class="sidebar-collapse" id="sidebar-collapse">
        <i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
    </div>
    <script>
        $(".submenu li.active").parent("ul").parent("li").addClass("open").addClass("active");
    </script>
</div>
