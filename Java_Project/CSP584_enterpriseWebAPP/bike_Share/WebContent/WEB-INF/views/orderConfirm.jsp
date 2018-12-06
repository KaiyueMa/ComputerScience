<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Order Confirmation</title>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/main.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/shopcart.css">
<script
	src="${pageContext.request.contextPath}/css/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/sort.js"></script>
<script src="${pageContext.request.contextPath}/js/holder.js"></script>
<script src="${pageContext.request.contextPath}/js/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/sweetalert.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/order.css">
<script src="${pageContext.request.contextPath}/js/order.js"></script>
</head>
<body>
	<div id="main" class="container">
		<jsp:include page="header.jsp" />
	</div>
	<div class="shopping_cart">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="account_heading account_heading_ah">
						<h1 class="header-border">Order Confirmation</h1>
					</div>
				</div>
			</div>


			<h4 class="header-border h4-mar">Customer：${user.username}</h4>

			<div class="row">
				<div class="all_wis_frm">
					<div class="col-md-12">
						<div class="wishlist-content wishlist-content-2">

							<div class="wishlist-table wishlist-table-2 table-responsive">
								<table id="cart-table">
									<thead>
										<tr>
											<th class="product-thumbnail product-thumbnail-2">Pic</th>
											<th class="product-name product-name_2"><span
												class="nobr">Bike</span></th>
											<th class="product-price"><span class="nobr">
													Price </span></th>
											<th class="product-stock-stauts"><span class="nobr">
													Sale </span></th>
											<th class="product-stock-stauts"><span class="nobr">
													Sale Price </span></th>
											<th class="product-add-to-cart"><span class="nobr">
													Comments</span></th>
										</tr>
									</thead>
									<tbody>


										<tr>

											<td class="product-thumbnail product-thumbnail-2"><a
												href="${pageContext.request.contextPath}/ShowFrontGoodDetail?goodsid=${goods.goodsid}"><img
													src="${pageContext.request.contextPath}/shopimage/${goods.imgepath}"
													alt="" /></a></td>
											<td class="product-name product-name_2"><a
												href="${pageContext.request.contextPath}/ShowFrontGoodDetail?goodsid=${goods.goodsid}">${goods.goodsname}</a>
											</td>
											<td class="product-price"><span
												class="amount-list amount-list-2">USD
													${goods.price}/Hour</span></td>
											<td class="product-stock-status">
												<div class="latest_es_from_2">
													<span>${goods.activity.activityname}</span>
												</div>
											</td>
											<td class="product-stock-status">
												<div class="latest_es_from_2">
													<span>${goods.newPrice}/Hour</span>
												</div>
											</td>
											<td class="product-price"><span
												class="amount-list amount-list-2">Charge starts after
													confirmation! :)</span></td>

											<%--<c:set value="${oldTotalPrice+goods.price*goods.num*goods.activity.discount}" var="oldTotalPrice"/>--%>
										</tr>


									</tbody>

								</table>
							</div>
							<div class="col-md-6 col-md-offset-1 col-xs-12">
								<div class="cart_totals">
									<div class="wc-proceed-to-checkout">
										<input type="hidden" id="flag" value="orderFinish" name="flag">
										<input type="hidden" id="goodsid" name="goodsid"
											value="${goods.goodsid}"></input>
										<button id="confirm-orders"
											class="button_act button_act-tc confirm-orders pull-right">Confirm
										</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


	</div>

	<div id="path" hidden>${pageContext.request.contextPath}</div>
</body>
</html>

