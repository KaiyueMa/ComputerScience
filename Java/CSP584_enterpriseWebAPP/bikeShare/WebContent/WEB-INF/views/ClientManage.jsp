<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Background Management</title>
<meta name="description" content="">
<meta name="author" content="templatemo">
<!--
    Visual Admin Template
    http://www.templatemo.com/preview/templatemo_455_visual_admin
    -->
<!-- <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,400italic,700' rel='stylesheet' type='text/css'> -->
<link href="${pageContext.request.contextPath}/css/font-awesome.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/templatemo-style.css"
	rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->


<script src="${pageContext.request.contextPath}/js/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/sweetalert.css">

</head>
<body>
	<!-- Left column -->
	<div class="templatemo-flex-row">
		<jsp:include page="sidebar.jsp"></jsp:include>
		<!-- Main content -->
		<div class="templatemo-content col-1 light-gray-bg">
			<div class="templatemo-top-nav-container">
				<div class="row">
					<nav class="templatemo-top-nav col-lg-12 col-md-12">
						<ul class="text-uppercase">
							<li><a
								href="${pageContext.request.contextPath}/AdminUserServlet?flag=showAdmin">All
									Staff</a></li>
							<li><a href="" class="active">All Customers</a></li>
						</ul>
					</nav>
				</div>
			</div>
			<div class="templatemo-content-container">
				<div class="templatemo-content-widget no-padding">
					<div class="panel panel-default table-responsive">
						<table id="goodsinfo"
							class="table table-striped table-bordered templatemo-user-table">
							<thead>
								<tr>
									<td><a href="" class="white-text templatemo-sort-by">id<span
											class="caret"></span></a></td>
									<td><a href="" class="white-text templatemo-sort-by">Customer
											Name<span class="caret"></span>
									</a></td>
									<td><a href="" class="white-text templatemo-sort-by">Password<span
											class="caret"></span></a></td>
									<td><a href="" class="white-text templatemo-sort-by">Reg
											Date<span class="caret"></span>
									</a></td>
									<td><a href="" class="white-text templatemo-sort-by">Mail<span
											class="caret"></span></a></td>
									<td><a href="" class="white-text templatemo-sort-by">Phone<span
											class="caret"></span></a></td>
									<td><a href="" class="white-text templatemo-sort-by">Wallet<span
											class="caret"></span></a></td>
									<td><a href="" class="white-text templatemo-sort-by">Deposit<span
											class="caret"></span></a></td>
									<td><a href="" class="white-text templatemo-sort-by">Last
											Recharge Date<span class="caret"></span>
									</a></td>
									<td>Delete</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${ulist}" var="user" varStatus="num">
									<tr>
										<td>${user.userid}</td>
										<td>${user.username}</td>
										<td>${user.password}</td>
										<td>${user.regtime}</td>
										<td>${user.email}</td>
										<td>${user.telephone}</td>
										<td>${user.wallet}</td>
										<td>${user.securitycash}</td>
										<td>${user.paytime}</td>
										<td><a
											href="${pageContext.request.contextPath}/AdminUserServlet?userid=${user.userid}&flag=deleteClient"
											class="templatemo-delete-btn" style="padding: 0;">Delete</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>

				<div class="pagination-wrap" id="page-div-nav">
					<div class="page-info" id="page-info-area"></div>
				</div>
			</div>
		</div>
	</div>
	<div id="path" style="display: none;">${pageContext.request.contextPath}</div>
	<!-- JS -->
	<script
		src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
	<!-- jQuery -->
	<script
		src="${pageContext.request.contextPath}/js/jquery-migrate-1.2.1.min.js"></script>
	<!--  jQuery Migrate Plugin -->


	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/templatemo-script.js"></script>
	<!-- Templatemo Script -->
	<%-- <script src="${pageContext.request.contextPath}/js/userManage.js"></script> --%>
	<c:if test="${!empty msg}">
		<script type="text/javascript">
			$(document).ready(function() {
				swal('${msg}');
			});
		</script>
	</c:if>

</body>
</html>
