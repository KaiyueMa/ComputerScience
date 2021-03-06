<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Administrator Login</title>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/css/bootstrap/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath}/css/font-awesome.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/templatemo-style.css"
	rel="stylesheet">
</head>
<body class="light-gray-bg">
	<div class="templatemo-content-widget templatemo-login-widget white-bg">
		<header class="text-center">
			<div class="square"></div>
			<img src="${pageContext.request.contextPath}/image/adminLogin.jpg"
				style="width: 60px" />
			<h1>Administrator Login</h1>
		</header>
		<form role="form"
			action="${pageContext.request.contextPath}/AdminUserServlet"
			class="templatemo-login-form" method="post">
			<input type="hidden" name="flag" value="confirmLogin">
			<div class="form-group">
				<div class="input-group">
					<div class="input-group-addon">
						<i class="fa fa-user fa-fw"></i>
					</div>
					<input type="text" class="form-control" placeholder="Adminname"
						name="adminname">
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<div class="input-group-addon">
						<i class="fa fa-key fa-fw"></i>
					</div>
					<input type="password" class="form-control" placeholder="password"
						name="password">
				</div>
			</div>
			<div class="form-group">
				<label class="radio-inline"> <input type="radio"
					style="display: inline;" value="1" name="dept" checked>Administrator
				</label> <label class="radio-inline"> <input type="radio"
					style="display: inline;" value="0" name="dept">Staff
				</label>
			</div>


			<div class="form-group">
				<div class="checkbox squaredTwo">
					<input type="checkbox" id="c1" name="cc" /> <span
						class="error-msg">${errorMsg}</span>
				</div>
			</div>
			<div class="form-group">
				<button type="submit" class="templatemo-blue-button width-100">Login</button>
			</div>
		</form>
	</div>

</body>
</html>
