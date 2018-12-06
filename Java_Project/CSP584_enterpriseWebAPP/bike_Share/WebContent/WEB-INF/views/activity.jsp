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
<!--    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,400italic,700' rel='stylesheet'
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

</head>
<body>

	<!-- Left column -->
	<div class="templatemo-flex-row">
		<jsp:include page="sidebar.jsp" />
		<!-- Main content -->
		<div class="templatemo-content col-1 light-gray-bg">
			<div class="templatemo-top-nav-container">
				<div class="row">
					<nav class="templatemo-top-nav col-lg-12 col-md-12">
						<ul class="text-uppercase">
							<li><a
								href="${pageContext.request.contextPath}/AdminActivitySerlvet?flag=showActivity"
								class="active">All Sale</a></li>
							<li><a
								href="${pageContext.request.contextPath}/AdminActivityServlet?flag=showaddActivity">Add Sale</a></li>
							<%--<li><a href="${pageContext.request.contextPath}/admin/goods/addCategory">添加分类</a></li>--%>
							<%--<li><a href="login.html">。。。</a></li>--%>
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
									<td><a href="" class="white-text templatemo-sort-by">Sale Name<span
											class="caret"></span></a></td>
									<td><a href="" class="white-text templatemo-sort-by">Sale Comments<span
											class="caret"></span></a></td>
									<td><a href="" class="white-text templatemo-sort-by">Discount<span
											class="caret"></span></a></td>
									<td><a href="" class="white-text templatemo-sort-by">Off<span
											class="caret"></span></a></td>
									<td><a href="" class="white-text templatemo-sort-by">Cash back<span
											class="caret"></span></a></td>
									<td>Action</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${pageInfo}" var="activity" varStatus="num">
									<tr>
										<td>${activity.activityid}</td>
										<td>${activity.activityname}</td>
										<td>${activity.activitydes}</td>
										<td>${activity.discount}</td>
										<c:if test="${!empty activity.fullprice}">
											<td>Over ${activity.fullprice}, ${activity.reduceprice} off</td>
										</c:if>
										<c:if test="${empty activity.fullprice}">
											<td>No off</td>
										</c:if>

										<c:if test="${!empty activity.fullnum}">
											<td>Over ${activity.fullnum}, ${activity.reducenum} cash back</td>
										</c:if>
										<c:if test="${empty activity.fullnum}">
											<td>No cash back</td>
										</c:if>
										<td><a
											href="${pageContext.request.contextPath}/AdminActivityServlet?activityid=${activity.activityid}&flag=delteActivity"
											class="templatemo-delete-btn" style="padding: 0;">Delete</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>


			</div>
		</div>
	</div>
	<div id="path" style="display: none;">${pageContext.request.contextPath}</div>
	<!-- jQuery -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/templatemo-script.js"></script>
	<!-- Templatemo Script -->
	<script>
    $(document).ready(function () {
        // Content widget with background image
        var imageUrl = $('img.content-bg-img').attr('src');
        $('.templatemo-content-img-bg').css('background-image', 'url(' + imageUrl + ')');
        $('img.content-bg-img').hide();

        /*$("a").click(function () {
            $(this).addClass("active");
        });*/
    });

</script>
</body>
</html>
