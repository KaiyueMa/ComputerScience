<%@page import="java.security.interfaces.RSAKey"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Bike Share - Welcome</title>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/login.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/main.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap/css/bootstrap.min.css">
<script
	src="${pageContext.request.contextPath}/css/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/sort.js"></script>
<script src="${pageContext.request.contextPath}/js/holder.js"></script>
<script
	src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/js/login.js"></script>
</head>

<script type="text/javascript">
	function reloadcode() {
		var verify = document.getElementById('code');
		verify.setAttribute("src",
				"${pageContext.request.contextPath}/verificationcodeimg?it="
						+ Math.random());
	}
</script>


<body onload="input_value()">
	<div id="main" class="container">
		<div id="header">
			<%@ include file="header.jsp"%>
		</div>
		<div class="login">
			<div class="row">
				<div class="col-md-6">
					<img src="./image/login.png" alt="" width="640" height="400"
						style="margin-left: -40px; margin-top: 30px;">
				</div>
				<div class="col-md-5 form-login">
					<div>
						<form class="form-horizontal" id="form2"
							action="${pageContext.request.contextPath}/UserManageServlet"
							method="post">
							<input type="hidden" value="loginconfirm" name="flag">
							<div class="form-group">
								<label for="username" class="col-sm-2 control-label">Username</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="username"
										name="username" placeholder="Username">
								</div>
							</div>
							<div class="form-group">
								<label for="password" class="col-sm-2 control-label">Passwrod</label>
								<div class="col-sm-10">
									<input type="password" class="form-control" id="password"
										name="password" placeholder="Password">
								</div>
							</div>
							<div class="form-group">
								<label for="confirmlogo" class="col-sm-2 control-label">Verification</label>
								<img
									src="${pageContext.request.contextPath}/ShowVerificationcodeimg"
									id="code" onclick="reloadcode()" style="cursor: pointer;"
									alt="change" width="100px">
								<div class="col-sm-10" style="width: 160px">
									<input type="text" class="form-control" id="confirmlogo"
										name="confirmlogo">
								</div>
							</div>
							<div style="margin-left: 80px; color: red;"></div>

							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<input type="submit" class="btn btn-primary login-input"
										value="Login" name="submit">
									<div class="error">${errorMsg}</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>

		</div>
	</div>
</body>
</html>