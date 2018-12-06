<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Order confirmation</title>
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
<script src="${pageContext.request.contextPath}/js/orderFinish.js"></script>
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
						<h1 class="header-border">Return Order</h1>
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
											<th class="product-thumbnail product-thumbnail-2">Image</th>
											<th class="product-name product-name_2"><span
												class="nobr">Product</span></th>
											<th class="product-price"><span class="nobr">
													Price </span></th>
											<th class="product-stock-stauts"><span class="nobr">
													Sale </span></th>
											<th class="product-stock-stauts"><span class="nobr">
													Sale price </span></th>

											<th class="product-stock-stauts"><span class="nobr">
													Start Time </span></th>

											<th class="product-stock-stauts"><span class="nobr">
													End Time </span></th>
											<th class="product-stock-stauts"><span class="nobr">
													Duration</span></th>

											<th class="product-add-to-cart"><span class="nobr">
													Comment</span></th>
										</tr>
									</thead>
									<tbody>


										<tr>

											<td class="product-thumbnail product-thumbnail-2"><a
												href="${pageContext.request.contextPath}/ShowFrontGoodDetail?goodsid=${order.good.goodsid}"><img
													src="${pageContext.request.contextPath}/shopimage/${order.good.imagePaths[0].path}"
													alt="" /></a></td>
											<td class="product-name product-name_2"><a
												href="${pageContext.request.contextPath}/ShowFrontGoodDetail?goodsid=${order.good.goodsid}">${order.good.goodsname}</a>
											</td>
											<td class="product-price"><span
												class="amount-list amount-list-2">USD
													${order.good.price}/hour</span></td>
											<td class="product-stock-status">
												<div class="latest_es_from_2">
													<span>${order.good.activity.activityname}</span>
												</div>
											</td>
											<td class="product-stock-status">
												<div class="latest_es_from_2">
													<span>${order.good.newPrice}/hour</span>
												</div>
											</td>

											<td class="product-stock-status">
												<div class="latest_es_from_2">
													<span>${order.ordertime}</span>

												</div>
											</td>
											<td class="product-stock-status">
												<div class="latest_es_from_2">
													<span id="repayTime">${order.repaytime}</span>
												</div>
											</td>
											<td class="product-stock-status">
												<div class="latest_es_from_2">
													<span>${order.hour}</span>
												</div>
											</td>
											<td class="product-price"><span
												class="amount-list amount-list-2"></span></td>
											<%--<c:set value="${oldTotalPrice+goods.price*goods.num*goods.activity.discount}" var="oldTotalPrice"/>--%>
										</tr>


									</tbody>

								</table>
							</div>
							<div class="row">
								<div class="col-md-5 col-xs-12 form-group form-group-lg">
									<label class="col-sm-2 control-label" for="pay-select">Payment</label>
									<div class="col-sm-10">
										<select class="form-control" id="pay-select">
											<option value="1">wallet</option>
											<option value="0">cash</option>
										</select>
									</div>
								</div>
								<div class="col-md-6 col-md-offset-1 col-xs-12">
									<div class="cart_totals">
										=
										<table class="shop_table shop_table_responsive">
											<tbody>
												<tr class="cart-subtotal">
													<th>total</th>
													<td data-title="Subtotal"><span
														class="woocommerce-Price-amount amount">USD <span
															class="woocommerce-Price-currencySymbol" id="totalold">${order.oldprice}</span>
													</span></td>
												</tr>
												<tr class="order-total">
													<th>actual total</th>
													<td data-title="Total"><strong> <span
															class="woocommerce-Price-amount amount">USD <span
																class="woocommerce-Price-currencySymbol" id="totalnew">${order.newprice}</span>
														</span>
													</strong></td>
												</tr>
											</tbody>
										</table>
										<div class="wc-proceed-to-checkout">
											<input type="hidden" id="flag" value="payReturnOrder"
												name="flag"> <input type="hidden" id="orderid"
												name="orderid" value="${order.orderid}"></input> <input
												type="hidden" id="userId" name="userId"
												value="${user.userid}"></input>
											<button id="confirm-orders"
												class="button_act button_act-tc confirm-orders pull-right">Check
												out</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<%--</form>--%>

		</div>
	</div>
	<div id="path" hidden>${pageContext.request.contextPath}</div>
</body>
</html>

