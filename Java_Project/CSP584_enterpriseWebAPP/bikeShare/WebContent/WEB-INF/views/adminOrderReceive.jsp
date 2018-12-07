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
<!-- <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,400italic,700' rel='stylesheet'
          type='text/css'> -->
<link href="${pageContext.request.contextPath}/css/font-awesome.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/templatemo-style.css"
	rel="stylesheet">

<!-- JS -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<!-- jQuery -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/templatemo-script.js"></script>
<!-- Templatemo Script -->
<style>
.head-div {
	font-size: 18px;
}

.goods-table thead {
	background-color: #fbffff;
}

.white-text {
	color: #404040;
}
</style>
</head>
<body>
	<!-- Left column -->
	<div class="templatemo-flex-row">
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
		<!-- Main content -->
		<div class="templatemo-content col-1 light-gray-bg">
			<jsp:include page="adminOrderNav.jsp" />
			<div class="templatemo-content-container">
				<%--<div class="templatemo-content-widget white-bg">--%>
				<%--<h2 class="margin-bottom-10">Geo Charts</h2>--%>
				<%--<p class="margin-bottom-0">Credit goes to <a href="http://jqvmap.com" target="_parent">JQVMap</a>.</p>--%>
				<%--</div>--%>

				<c:forEach items="${pageInfo.data}" var="orderInfo">
					<div class="templatemo-flex-row flex-content-row">
						<div class="col-1">
							<div class="panel panel-default margin-10">
								<div class="panel-heading">
									<h2>${orderInfo.user.username}</h2>
								</div>
								<div class="panel-body">
									<div>
										<div class="order-info margin-bottom-10">
											<div class="head-div">Order Information</div>
											<div>
												<table id="orderinfo"
													class="table table-striped table-bordered templatemo-user-table goods-table">
													<thead>
														<tr>
															<td><a href="" class="white-text templatemo-sort-by">Order No.<span
																	class="caret"></span></a></td>
															<td><a href="" class="white-text templatemo-sort-by">Customer<span
																	class="caret"></span></a></td>
															<td><a href="" class="white-text templatemo-sort-by">Price<span
																	class="caret"></span></a></td>
															<td><a href="" class="white-text templatemo-sort-by">Check Price<span
																	class="caret"></span></a></td>



															<td><a href="" class="white-text templatemo-sort-by">Contact<span
																	class="caret"></span></a></td>
															<td><a href="" class="white-text templatemo-sort-by">Time<span
																	class="caret"></span></a></td>
														</tr>
													</thead>
													<tbody>
														<tr>
															<td>${orderInfo.orderid}</td>
															<td>${orderInfo.user.username}</td>
															<td>￥${orderInfo.oldprice}</td>
															<td>￥${orderInfo.newprice}</td>
															<td>${orderInfo.user.telephone}</td>

															<td>${orderInfo.ordertime}</td>
														</tr>

													</tbody>
												</table>

											</div>
										</div>
										<div class="goods-info margin-bottom-10">
											<div class="head-div">Product Information</div>
											<div>
												<table id="goodsinfo"
													class="table table-striped table-bordered templatemo-user-table goods-table">
													<thead>
														<tr>
															<td><a href="" class="white-text templatemo-sort-by">Product id<span
																	class="caret"></span></a></td>
															<td><a href="" class="white-text templatemo-sort-by">Product Name<span
																	class="caret"></span></a></td>
															<td><a href="" class="white-text templatemo-sort-by">Price<span
																	class="caret"></span></a></td>
															<td><a href="" class="white-text templatemo-sort-by">Count<span
																	class="caret"></span></a></td>
															<td>Detail</td>
														</tr>
													</thead>
													<tbody>

														<tr>
															<td>${orderInfo.good.goodsid}</td>
															<td>${orderInfo.good.goodsname}</td>
															<td>￥${orderInfo.good.price}</td>
															<td>${orderInfo.good.num}</td>
															<td><a
																href="${pageContext.request.contextPath}/detail?goodsid=${orderInfo.good.goodsid}"
																class="templatemo-link">Detail</a></td>
															
														</tr>


													</tbody>
												</table>
											</div>
										</div>

									</div>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>
