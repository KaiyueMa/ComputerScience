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
			<jsp:include page="goodsNav.jsp"></jsp:include>
			<div class="templatemo-content-container">
				<div class="templatemo-content-widget white-bg">
					<h2 class="margin-bottom-10">Add Product</h2>
					<p>Basic Information of the Product</p>
					<form action="${pageContext.request.contextPath}/UploadPicServlet"
						class="templatemo-login-form" method="post"
						enctype="multipart/form-data">
						<!--  <input type="hidden" value="addGoodsSuccess" name="flag"> -->
						<div class="row form-group">
							<div class="col-lg-6 form-group">
								<label class="control-label" for="inputWithSuccess">Product
									Name</label> <input type="text" class="form-control"
									id="inputWithSuccess" name="goodsname">
							</div>
							<div class="form-group">
								<label for="detailcate" class="col-sm-2 control-label">Sale</label>
								<div class="col-sm-6">
									<select class="form-control" id="activity" name="activity">
										<c:forEach items="${activityList}" var="activity">
											<option value="${activity.activityid}">${activity.activityname}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="row form-group">
							<div class="col-lg-6 form-group">
								<label class="control-label" for="inputWithWarning">Price</label>
								<input type="number" class="form-control" id="inputWithWarning"
									name="price">
							</div>
							<div class="col-lg-6 form-group">
								<label class="control-label" for="inputWithError">Count</label>
								<input type="number" class="form-control" id="inputWithError"
									name="num">
							</div>
						</div>
						<div class="row form-group">
							<div class="col-lg-12 form-group">
								<label class="control-label" for="inputNote">Product
									Description</label>
								<textarea class="form-control" id="inputNote" rows="3"
									name="description"></textarea>
							</div>
						</div>
						<div class="row form-group">
							<div class="col-lg-6 col-md-6 form-group">
								<label class="control-label templatemo-block">Category</label> <select
									class="form-control" name="category">
									<c:forEach items="${categoryList}" var="item">
										<option value="${item.cateid}">${item.catename}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-lg-6 col-md-6 form-group">
								<label class="control-label" for="inputWithSuccess2">State</label>
								<select class="form-control" name="statue">

									<option value="1">Available</option>
									<option value="0">Rented</option>

								</select>
							</div>
						</div>
						<div class="row form-group">
							<div class="col-lg-6">
								<label class="control-label templatemo-block">Image</label> <input
									type="file" name="file" id="fileToUpload" class="filestyle"
									data-buttonName="btn-primary" data-buttonBefore="true"
									data-icon="false">

							</div>
							<div class="col-lg-6 col-md-6 form-group">
								<label class="control-label" for="inputWithSuccess2">Address</label>

								<select class="form-control" name="address">
									<c:forEach items="${addressList}" var="address">
										<option value="${address.addressid}">${address.addressname}</option>
									</c:forEach>
								</select>
							</div>

						</div>
						<div class="form-group text-right">
							<button type="submit" class="templatemo-blue-button">Add</button>
							<button type="reset" class="templatemo-white-button">Reset</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- JS -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
	<!-- jQuery -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/bootstrap-filestyle.min.js"></script>
	<!-- http://markusslima.github.io/bootstrap-filestyle/ -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/templatemo-script.js"></script>
	<!-- Templatemo Script -->
	<c:if test="${!empty msg}">
		<script type="text/javascript">
			$(document).ready(function() {
				swal('${msg}', 'Success', 'success');
			});
		</script>
	</c:if>
</body>
</html>

