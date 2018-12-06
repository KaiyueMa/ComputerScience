<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html class="" lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>Bike-${goodsInfo['goods'].goodsname}</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- all css here -->
<!-- bootstrap v3.3.6 css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/main.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/font-awesome.min.css">
<!-- style css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">

<link href="${pageContext.request.contextPath}/css/shopdetail.css"
	rel="stylesheet">
<!-- <script src="./detail/js/jquery.js"></script> -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>


<%--<script src="${pageContext.request.contextPath}/js/sort.js"></script>--%>

<!-- bootstrap js -->
<script
	src="${pageContext.request.contextPath}/css/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/sort.js"></script>
<script src="${pageContext.request.contextPath}/js/detail.js"></script>
<script type="text/javascript">
        $(document).ready(function () {
            var showproduct = {
                "boxid": "showbox",
                "sumid": "showsum",
                "boxw": 400,
                "boxh": 550,
                "sumw": 60, //列表每个宽度,该版本中请把宽高填写成一样
                "sumh": 60, //列表每个高度,该版本中请把宽高填写成一样
                "sumi": 7, //列表间隔
                "sums": 5, //列表显示个数
                "sumsel": "sel",
                "sumborder": 1, //列表边框，没有边框填写0，边框在css中修改
                "lastid": "showlast",
                "nextid": "shownext"
            }; //参数定义
            $.ljsGlasses.pcGlasses(showproduct); //方法调用，务必在加载完后执行

            $(function () {

                $('.tabs a').click(function () {

                    var $this = $(this);
                    $('.panel').hide();
                    $('.tabs a.active').removeClass('active');
                    $this.addClass('active').blur();
                    var panel = $this.attr("href");
                    $(panel).show();
                    return fasle; //告诉浏览器  不要纸箱这个链接
                }); //end click


                $(".tabs li:first a").click(); //web 浏览器，单击第一个标签吧

            }); //end ready

            $(".centerbox li").click(function () {
                $("li").removeClass("now");
                $(this).addClass("now");

            });

          

            $('.fav-button').click(function(){
                //$(this).removeClass("glyphicon-heart-empty");
                var goodsId = $(this).attr('data-id');
                var isChangeBtn = true;
                if(!$(this).children("i").hasClass('fa-heart')) {
                 
                    $.ajax({
                        url:"collect",
                        type:"POST",
                        data:{
                            goodsid:goodsId
                        },
                        success:function (result) {
                      
                            if(result.code === 200){
                                location.href = "login";
                                isChangeBtn = false;
                            }
                        },
                        error:function () {
                            alert("收藏失败");
                        }
                    })
                } else {
              
                    $.ajax({
                        url:"deleteCollect",
                        type:"POST",
                        data:{
                            goodsid:goodsId
                        },
                        success:function (result) {
               
                            if(result.code === 200){
                                location.href = "login";
                                isChangeBtn = false;
                            }
                        },
                        error:function () {
                            alert("取消收藏失败");
                        }
                    })
                }


                if(isChangeBtn) {
                    $(this).children("i").toggleClass("fa-heart fa-heart-o");
                    var likeContent = $(this).children("span").text();
                    if(likeContent == '收藏'){
                        $(this).children("span").text('取消收藏');
                    } else {
                        $(this).children("span").text('收藏');
                    }
                }
            });
        });
    </script>
<style>
.head {
	width: 45px !important;
	background: rgba(0, 0, 0, .0001) none repeat scroll 0 0 !important;
	border: 1px solid rgba(0, 0, 0, .075) !important;
}

.span-block {
	display: block !important;
	padding: 5px;
}
</style>

</head>

<body>
	<!--zoom elavator area one start-->
	<div class="elavator_area">
		<div class="container">
			<jsp:include page="header.jsp" />
			<div class="shop_menu shop_menu_2 main-detail-div">
				<ul class="cramb_area cramb_area_5 main-detail-nav">
					<li><h4>
							<a href="${pageContext.request.contextPath}/ShowFrontMainPage">Home
								/</a>
						</h4></li>
					<%--<li><a href="index.html">Shop /</a></li>
                <li><a href="index.html">Headlight/</a></li>
                <li><a href="index.html">Hats /</a></li>--%>
					<li class="br-active">${goodsInfo['goods'].goodsname}</li>
				</ul>
			</div>
			<div class="row">
				<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12">
					<div class="shopdetails">
						<div id="leftbox">
							<div id="showbox">

								<img
									src="${pageContext.request.contextPath}/shopimage/${goodsInfo['image'].path}"
									width="400" height="400" />

							</div>
							<div id="showsum"></div>
							<p class="showpage">
								<a href="javascript:void(0);" id="showlast"> < </a> <a
									href="javascript:void(0);" id="shownext"> > </a>
							</p>
						</div>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="elav_titel">
						<div class="elv_heading" style="margin-top: 30px;">
							<h3>${goodsInfo['goods'].goodsname}</h3>
						</div>
						<div class="price_rating">
				
						</div>
						<!-- <div class="evavet_description">
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce posuere metus vitae arcu imperdiet, id aliquet ante scelerisque. Sed sit amet sem vitae urna fringilla tempus.</p>
                    </div> -->
					</div>
					<div class="elav_info">
						<!-- 价格 -->
						<div class="price_box price_box_acr new_meta">
							<%--<span class="old- price old- price-2">$250.00</span>--%>
							<span class="spical-price spical-price-2">USD
								${goodsInfo['goods'].price}/hour</span>
						</div>
						<div class="new_meta">
							<span class="sku_wrapper big-font"> Category: <span
								class="sku">${goodsInfo['cate'].catename}</span> &nbsp; &nbsp;
								&nbsp; &nbsp; &nbsp; Place: <span class="sku">${goodsInfo['address'].addressname}</span>
							</span>



						</div>
						<div class="new_meta">
							<span class="sku_wrapper big-font"> Remaining: <span
								class="sku">${goodsInfo['goods'].num}</span>
							</span>
						</div>

						<div class="new_meta">
							<span class="sku_wrapper big-font"> Discount: <c:if test="${goodsInfo['goods'].activityid != 1}">
									<span class="sku">${goodsInfo['goods'].activity.activityname}</span>
									<span class="span-block">${goodsInfo['goods'].activity.activitydes}</span>
									<%-- <span class="span-block">${goodsInfo['goods'].activity.activitydes}</span> --%>
									<c:if test="${empty goodsInfo['goods'].activity.fullprice}">
										<span class="span-block">
											满${goodsInfo['goods'].activity.fullprice}减${goodsInfo['goods'].activity.reduceprice}
										</span>
									</c:if>
									<c:if test="${empty goodsInfo['goods'].activity.fullnum}">
										<span class="span-block">
											满${goodsInfo['goods'].activity.fullnum}免${goodsInfo['goods'].activity.reducenum}
										</span>
									</c:if>
								</c:if> <c:if test="${goodsInfo['goods'].activityid == 1}">
									<span class="sku">No discount available currently!</span>
								</c:if>
							</span>
						</div>
						<%--<div class="new_meta"></div>--%>

						<div class="rel-div">

							<form class="cart-btn-area new_meta"
								action="${pageContext.request.contextPath}/ShowAddOrder"
								method="post">
								<input type="hidden" value="${goodsInfo['goods'].goodsid}"
									name="goodsid" /> <input type="hidden" value="addOrder"
									name="flag">
								<button class="add-tocart cart_zpf" type="submit">Rent</button>
							</form>
						

						</div>


					</div>
				</div>
		
			</div>
		</div>
	</div>
	<!--zoom elavator area one end-->
	<!--tab area start-->
	<div class="tab_area_start">
		<div class="container">
			<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12 text-right">
					<div class="my-tabs">
						<!-- Nav tabs -->
						<ul class="favtabs favtabs-2 favtabs-nytr" role="tablist">
							<%--<li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Discription</a></li>--%>
							<li role="presentation" class="active"><a href="#home"
								aria-controls="home" role="tab" data-toggle="tab">Des</a></li>
							<li role="presentation"><a href="#profile"
								aria-controls="profile" role="tab" data-toggle="tab">Reviews
									(${commentList.size()})</a></li>
						</ul>
						<!-- Tab panes -->
						<div class="tab-content">
							<div role="tabpanel" class="tab-pane active" id="home">
								<div class="row">
									<div class="col-md-12 col-xs-12">
										<div class="tb_desc">
											<h2>Bike Description</h2>
											<p>${goodsInfo['goods'].description}</p>
										</div>
									</div>
								</div>
							</div>
							<div role="tabpanel" class="tab-pane" id="profile">
								<div class="row">
									<div class="col-md-12 col-xs-12">
										<div class="tb_desc">
											<div class="review_area_heading">
												<div id="comnt">
													<h2>${commentList.size()}comments</h2>
													<ol class="commentlist">
														<c:forEach items="${commentList}" var="comment">
															<li id="li-comment-22"
																class="comment even thread-even depth-1" itemscope="">
																<div id="comment-22" class="comment_container">
																	<img class="head avatar avatar-60 photo " width="60"
																		height="60"
																		src="${pageContext.request.contextPath}/image/head.jpg"
																		alt="">
																	<div class="comment-text">
																		<div class="star-rating"
																			title="Rated ${comment.point} out of 5" itemscope="">
																			<div class="price_rating price_rating_2">
																				<c:forEach begin="1" end="${comment.point}">
																					<a href="#"> <i class="fa fa-star"></i>
																					</a>
																				</c:forEach>
																				<c:forEach begin="${comment.point+1}" end="5">
																					<a class="not-rated" href="#"> <i
																						class="fa fa-star-o" aria-hidden="true"></i>
																					</a>
																				</c:forEach>
																				<span> <strong>${comment.point}</strong> out
																					of ${5-comment.point}
																				</span>
																			</div>
																		</div>
																		<p class="meta">
																			<strong>${comment.username}</strong> –
																			<time datetime="${comment.commenttime}">
																				${comment.commenttime.year+1900} -
																				${comment.commenttime.month+1} -
																				${comment.commenttime.date} </time>
																			:

																		</p>
																		<div class="description">
																			<p>${comment.content}</p>
																		</div>
																	</div>
																</div>
															</li>
														</c:forEach>
													</ol>
												</div>

											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
	<!--tab area end-->
	<!-- jquery latest version -->
	<!-- <script src="js/vendor/jquery-1.12.0.min.js"></script> -->
	<!-- bootstrap js -->
	<!-- <script src="js/bootstrap.min.js"></script> -->

</body>
</html>


