$(document).ready(function () {
    $("#confirm-orders").click(function () {
        
        var goodsid=$('#goodsid').val();
        var flag=$('#flag').val();
        var isPay = $('#pay-select').val();
      
        $.ajax({
            url: "ShowAddOrder",
            type: "POST",
            dataType:"json",
            data: {
                flag:flag,
                isPay: isPay,
                goodsid:goodsid,
            },
            
            success: function (result) {
            	
            	if(result.code==100){
                swal("Rent Success", "", "success");
               // location.href = "info/list"
            	}else{
            		 swal("Rent failed", "", "error");
            	}
            },
            error: function () {
                swal("404");
            }
        });
    });
});