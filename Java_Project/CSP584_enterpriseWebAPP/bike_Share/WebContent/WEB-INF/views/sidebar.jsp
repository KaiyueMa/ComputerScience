<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<div class="templatemo-sidebar">
	<header class="templatemo-site-header">
		<div class="square"></div>
		<h1>Background Management</h1>
	</header>
	<div class="mobile-menu-icon">
		<i class="fa fa-bars"></i>
	</div>
	<nav class="templatemo-left-nav">
		<ul>
			<li><a
				href="${pageContext.request.contextPath}/AdminUserServlet?flag=showAdmin"><i
					class="fa fa-user fa-fw"></i>User Management</a></li>

			<li><a
				href="${pageContext.request.contextPath}/AdminActivityServlet?flag=showActivity"><i
					class="fa fa-database fa-fw"></i>Sale Management</a></li>

			<li><a
				href="${pageContext.request.contextPath}/AdminUserServlet?flag=adminLoginOut"><i
					class="fa fa-eject fa-fw"></i>Logout</a></li>
		</ul>
	</nav>
</div>
