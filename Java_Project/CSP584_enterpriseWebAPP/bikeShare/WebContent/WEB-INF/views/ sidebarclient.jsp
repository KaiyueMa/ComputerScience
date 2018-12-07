<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<div class="templatemo-sidebar">
	<header class="templatemo-site-header">
		<div class="square"></div>
		<h1>Backend Management</h1>
	</header>
	<div class="mobile-menu-icon">
		<i class="fa fa-bars"></i>
	</div>
	<nav class="templatemo-left-nav">
		<ul>

			<li><a
				href="${pageContext.request.contextPath}/AdminGoodServlet?flag=goodshow"><i
					class="fa fa-bar-chart fa-fw"></i>Product Management</a></li>
			<li><a
				href="${pageContext.request.contextPath}/AdminOrderServlet?flag=orderSend"><i
					class="fa fa-users fa-fw"></i>Order Management</a></li>
			<li><a
				href="${pageContext.request.contextPath}/AdminUserServlet?flag=adminLoginOut"><i
					class="fa fa-eject fa-fw"></i>Logout</a></li>
		</ul>
	</nav>
</div>
