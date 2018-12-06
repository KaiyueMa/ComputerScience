var activity = [];
var currentPage = 1;
$(document).ready(function() {

	var path = $("#path").text();

	to_page(path, 1);

});

$(document).on("click", ".description", function() {
	$(this).popover();
});

$(document).on(
		"click",
		".templatemo-edit-btn",
		function() {
			$("#update-goods").modal({
				backdrop : 'static'
			});

			var upGoodsid = $(this).parents("tr").find("td:eq(0)").text();
			var upGoodsname = $(this).parents("tr").find("td:eq(1)").text();
			var upGoodsPrice = $(this).parents("tr").find("td:eq(2)").text();
			var upGoodsNum = $(this).parents("tr").find("td:eq(3)").text();
			var upGoodsDetailCate = $(this).parents("tr").find("td:eq(4)")
					.text();
			var upGoodsDes = $(this).parents("tr").find(".description").attr(
					"data-content");

			$("#goodsid").text(upGoodsid);
			$("#goodsname").val(upGoodsname);
			$("#price").val(upGoodsPrice);
			$("#num").val(upGoodsNum);
			$("#detailcate").val(upGoodsDetailCate);
			$("#description").val(upGoodsDes);
		});

$(document).on("click", "#saveUpdate", function() {
	var ugoodsid = $("#goodsid").text();
	var ugoodsname = $("#goodsname").val();
	var uprice = $("#price").val();
	var unum = $("#num").val();
	var udescription = $("#description").val();
	var ucategory = $("#category").val();
	var ustatue = $("#statue").val();
	var activity = $("#activity").val();

	var uaddress = $("#address").val();

	$.ajax({
		url : "AdminGoodServlet?flag=updateGood",
		type : "POST",
		dataType : "json",
		data : {
			goodsid : ugoodsid,
			goodsname : ugoodsname,
			price : uprice,
			num : unum,
			description : udescription,
			category : ucategory,
			address : uaddress,
			statue : ustatue,
			activity : activity
		},
		success : function(result) {
			$("#update-goods").modal('hide');
			swal(result.msg, '');
			to_page('/bikeShare', currentPage);
		},
		error : function() {
			alert("Error！！");
		}
	});

});

$(document).on("click", ".templatemo-delete-btn", function() {
	var goodsname = $(this).parents("tr").find("td:eq(1)").text();
	var goodsid = $(this).parents("tr").find("td:eq(0)").text();
	var flag = "deleteGood";
	swal({
		title : "Are you sure to DELETE" + goodsname + "?",
		type : "warning",
		showCancelButton : true,
		cancelButtonText : "Not sure.",
		confirmButtonColor : "#DD6B55",
		confirmButtonText : "Sure！",
		closeOnConfirm : false,
	}, function() {
		$.ajax({
			url : "AdminGoodServlet",
			data : {
				goodsid : goodsid,
				flag : flag,
			},
			dataType : "json",
			success : function(result) {
				swal(result.msg, "");
				to_page('/bikeShare', currentPage);
			},
			error : function() {
				to_page('/bikeShare', currentPage);
			}
		});
	});
});

function showActInfo(activityId) {
	$('#activityname').text(activity[activityId - 1].activityname);
	$('#activitydes').text(activity[activityId - 1].activitydes);
	$('#discount').text(activity[activityId - 1].discount);
	$('#fullprice').text(activity[activityId - 1].fullprice);
	$('#reduceprice').text(activity[activityId - 1].reduceprice);
	$('#fullnum').text(activity[activityId - 1].fullnum);
	$('#reducenum').text(activity[activityId - 1].reducenum);
}

$("#activity-id").change(function() {
	showActInfo($(this).val());
});

function getActivity() {
	$.ajax({
		url : "AdminActivityServlet?flag=showjson",
		type : "post",
		dataType : "json",
		success : function(result) {
			$("#activity-id").empty();
			if (result.data.length > 0) {
				for (var i = 0; i < result.data.length; i++) {

					$("#activity-id").append(
							$("<option></option>").attr("value",
									result.data[i].activityid).append(
									result.data[i].activityid));
				}
			}
			showActInfo(1);
		},
		error : function() {
			to_page('/bikeShare', currentPage);
		}

	});
}

$(document).on("click", "#saveActivity", function() {
	var goodsid = $("#activity-goodsid").text();
	var activityid = $("#activity-id").val();

	$.ajax({
		url : "../activity/update/",
		type : "POST",
		data : {
			goodsid : goodsid,
			activityid : activityid
		},
		success : function(result) {
			$("#activity-goods").modal('hide');
			swal(result.msg, '');
			to_page('/shop', currentPage);
		},
		error : function() {
			alert("Error！！");
		}
	});
});

function to_page(path, page) {
	$.ajax({
		url : "AdminGoodServlet?flag=showjson",
		data : "page=" + page,
		type : "get",
		dataType : "json",
		success : function(result) {

			build_goods_table(path, result);

			build_page_info(path, result);

			build_page_nav(path, result);

			currentPage = page;
		}
	});
}

function build_goods_table(path, result) {
	$("#goodsinfo tbody").empty();
	if (result.data.length > 0) {
		for (var i = 0; i < result.data.length; i++) {
			var goodsid = $("<td></td>").append(result.data[i].goodsid);
			var goodsname = $("<td></td>").append(result.data[i].goodsname);
			var price = $("<td></td>").append(result.data[i].price);
			var num = $("<td></td>").append(result.data[i].num);
			var detailcate = $("<td></td>").append(result.data[i].categoryId);
			var activityid = $("<td></td>").append(result.data[i].activityid);
			var addressid = $("<td></td>").append(result.data[i].address);

			var detailBtn = $(
					'<button type="button" class="description" data-container="body" data-toggle="popover" data-placement="top"></button>')
					.append("Description");

			detailBtn = detailBtn.attr("data-content",
					result.data[i].description);

			var detailA = $("<a></a>").addClass("templatemo-link").attr(
					"href",
					"../bikeShare/ShowFrontGoodDetail?goodsid="
							+ result.data[i].goodsid).append("Details");

			var editBtn = $("<button></button>")
					.addClass("templatemo-edit-btn").append("Update");
			var deleteBtn = $("<button></button>").addClass(
					"templatemo-delete-btn").append("Delete");

			var desTd = $("<td hidden></td>").append(detailBtn);

			var detailTd = $("<td></td>").append(detailA);
			var editTd = $("<td></td>").append(editBtn);
			var deleteTd = $("<td></td>").append(deleteBtn);

			$("<tr></tr>").append(goodsid).append(goodsname).append(price)
					.append(num).append(detailcate).append(activityid).append(
							addressid).append(desTd).append(detailTd).append(
							editTd).append(deleteTd).appendTo(
							"#goodsinfo tbody");
		}
	}
}

function build_page_info(path, result) {

	$("#page-info-area").empty();
	$("#page-info-area").append(
			"Page " + result.pageInfo.pageNo + ", in "
					+ result.pageInfo.totalPage + " pages, in total "
					+ result.pageInfo.totalCount + " records.")
}

function build_page_nav(path, result) {
	$("#page-div-nav ul").empty();
	var pageUl = $("<ul></ul>").addClass("pagination")

	var firstPage = $("<li></li>").append(
			$("<a aria-label=\"Next\"></a>").append(
					$("<span aria-hidden=\"true\"></span>").append("First")));

	var prePage = $("<li></li>")
			.append(
					$("<a aria-label=\"Next\"></a>")
							.append(
									$("<span aria-hidden=\"true\"><i class=\"fa fa-backward\"></i></span>")));

	if (result.pageInfo.pageNo == 1) {
		prePage.addClass("li-none");
	} else {
		prePage.click(function() {
			to_page('/bikeShare', result.pageInfo.pageNo - 1);
		});
	}

	firstPage.click(function() {
		to_page('/bikeShare', 1);
	});

	var nextPage = $("<li></li>")
			.append(
					$("<a aria-label=\"Next\"></a>")
							.append(
									$("<span aria-hidden=\"true\"><i class=\"fa fa-forward\"></i></span>")));

	var lastPage = $("<li></li>").append(
			$("<a aria-label=\"Next\"></a>").append(
					$("<span aria-hidden=\"true\"></span>").append("Last")));

	if (result.pageInfo.pageNo == result.pageInfo.totalPage) {
		nextPage.addClass("li-none");
	} else {
		nextPage.click(function() {
			to_page('/bikeShare', result.pageInfo.pageNo + 1);
		});
	}

	lastPage.click(function() {
		to_page('/bikeShare', result.pageInfo.totalPage);
	});

	pageUl.append(firstPage).append(prePage);

	/*
	 * $.each(result.info.pageInfo.navigatepageNums,function (index,item) { var
	 * numLi = $("<li></li>").append($("<a></a>") .append($("<span
	 * aria-hidden=\"true\"></span>").append(item)));
	 * if(result.info.pageInfo.pageNum === item) { numLi.addClass("active"); }
	 * numLi.click(function () { to_page('/bikeShare',item); });
	 * pageUl.append(numLi); });
	 */

	pageUl.append(nextPage).append(lastPage).appendTo("#page-div-nav");
}
