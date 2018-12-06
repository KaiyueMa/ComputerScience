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
<!-- <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,400italic,700' rel='stylesheet'
          type='text/css'> -->
<!-- JS -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<link href="${pageContext.request.contextPath}/css/font-awesome.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/css/bootstrap/js/bootstrap.min.js"></script>
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
<%--<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>--%>
<style>
.show-span {
	border: none !important;
}
</style>
</head>
<body>


	<!-- Modal -->
	<div class="modal fade" id="update-goods" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Update Product Info</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="update-form" name="update-form"
						method="post">
						<div class="form-group">
							<label for="goodsid" class="col-sm-2 control-label">id</label>
							<div class="col-sm-9">
								<span id="goodsid" class="form-control"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="goodsname" class="col-sm-2 control-label">Name</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" name="goodsname"
									id="goodsname">
							</div>
						</div>
						<div class="form-group">
							<label for="price" class="col-sm-2 control-label">Price</label>
							<div class="col-sm-9">
								<input type="number" class="form-control" name="price"
									id="price">
							</div>
						</div>
						<div class="form-group">
							<label for="num" class="col-sm-2 control-label">Count</label>
							<div class="col-sm-9">
								<input type="number" class="form-control" id="num" name="num">
							</div>
						</div>
						<div class="form-group">
							<label for="description" class="col-sm-2 control-label">Description</label>
							<div class="col-sm-9">
								<textarea class="form-control" id="description"
									name="description"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label for="category" class="col-sm-2 control-label">Category</label>
							<div class="col-sm-9">
								<select class="form-control" id="category" name="category">
									<c:forEach items="${categoryList}" var="item">
										<option value="${item.cateid}">${item.catename}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label for="detailcate" class="col-sm-2 control-label">Sale</label>
							<div class="col-sm-9">
								<select class="form-control" id="activity" name="activity">
									<c:forEach items="${activityList}" var="activity">
										<option value="${activity.activityid}">${activity.activityname}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="detailcate" class="col-sm-2 control-label">Address</label>
							<div class="col-sm-9">
								<select class="form-control" id="address" name="address">
									<c:forEach items="${addressList}" var="address">
										<option value="${address.addressid}">${address.addressname}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="detailcate" class="col-sm-2 control-label">State</label>
							<div class="col-sm-9">
								<select class="form-control" id="statue" name="statue">

									<option value="0">Rented</option>
									<option value="1" selected>Available</option>
								</select>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-primary" id="saveUpdate">Save</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="activity-goods" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Add Sale</h4>
				</div>
				<div class="modal-body">
					<form class="templatemo-login-form">
						<div class="row form-group">
							<div class="col-lg-6 form-group">
								<div id="activity-goodsid" hidden></div>
								<label class="control-label" for="activity-id">Sale No.</label>
								<select class="form-control" id="activity-id">

								</select>
							</div>
						</div>
						<div class="row form-group">
							<div class="col-lg-6 form-group">
								<label class="control-label" for="activityname">Sale
									Name</label> <span type="text" class="form-control show-span"
									id="activityname" name="activityname"></span>
							</div>
							<div class="col-lg-6 form-group">
								<label class="control-label" for="discount">Discount</label> <span
									type="number" class="form-control show-span" id="discount"
									name="discount" step="0.01"></span>
							</div>
						</div>
						<div class="row form-group">
							<div class="col-lg-6 form-group">
								<label class="control-label" for="fullprice">满</label> <span
									type="number" class="form-control show-span" id="fullprice"
									name="fullprice"></span>
							</div>
							<div class="col-lg-6 form-group">
								<label class="control-label" for="reduceprice">减</label> <span
									type="number" class="form-control show-span" id="reduceprice"
									name="reduceprice"></span>
							</div>
						</div>

						<div class="row form-group">
							<div class="col-lg-6 form-group">
								<label class="control-label" for="fullnum">满</label> <span
									type="number" class="form-control show-span" id="fullnum"
									name="fullnum"></span>
							</div>
							<div class="col-lg-6 form-group">
								<label class="control-label" for="reducenum">免</label> <span
									type="number" class="form-control show-span" id="reducenum"
									name="reducenum"></span>
							</div>
						</div>

						<div class="row form-group">
							<div class="col-lg-12 form-group">
								<label class="control-label" for="activitydes">Sale
									Description</label> <span class="form-control show-span"
									id="activitydes" rows="3" name="activitydes">asfd</span>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-primary" id="saveActivity">Save</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Left column -->
	<div class="templatemo-flex-row">
		<div class="templatemo-sidebar">
			<header class="templatemo-site-header">
				<div class="square"></div>
				<h1>BacKground Management</h1>
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

					<%--<li><a href="${pageContext.request.contextPath}/admin/goods/show"><i class="fa fa-map-marker fa-fw"></i>活动管理</a></li>--%>
					<%-- <li><a href="${pageContext.request.contextPath}/admin/chat"><i class="fa fa-sliders fa-fw"></i>客服管理</a></li> --%>
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
				<div class="templatemo-content-widget no-padding">
					<div class="panel panel-default table-responsive">
						<table id="goodsinfo"
							class="table table-striped table-bordered templatemo-user-table">
							<thead>
								<tr>
									<td><a href="" class="white-text templatemo-sort-by">id<span
											class="caret"></span></a></td>
									<td><a href="" class="white-text templatemo-sort-by">Name<span
											class="caret"></span></a></td>
									<td><a href="" class="white-text templatemo-sort-by">Price<span
											class="caret"></span></a></td>
									<td><a href="" class="white-text templatemo-sort-by">Count<span
											class="caret"></span></a></td>
									<td><a href="" class="white-text templatemo-sort-by">Category<span
											class="caret"></span></a></td>
									<td><a href="" class="white-text templatemo-sort-by">Sale<span
											class="caret"></span></a></td>
									<td><a href="" class="white-text templatemo-sort-by">Address<span
											class="caret"></span></a></td>
									<td>Detail</td>
									<td>Update</td>
									<td>Delete</td>

								</tr>
							</thead>
							<tbody>

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
	<!-- jQuery -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/templatemo-script.js"></script>
	<script src="${pageContext.request.contextPath}/js/goodsManage.js"></script>
	<!-- Templatemo Script -->
	<script>
		$(document).ready(
				function() {
					// Content widget with background image
					var imageUrl = $('img.content-bg-img').attr('src');
					$('.templatemo-content-img-bg').css('background-image',
							'url(' + imageUrl + ')');
					$('img.content-bg-img').hide();
				});
	</script>
</body>
</html>
