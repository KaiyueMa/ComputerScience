$(document).ready(function () {
    $("#confirm-orders").click(function () {
        
        var orderid=$('#orderid').val();
        var flag=$('#flag').val();
        var isPay = $('#pay-select').val();
        var totalnew = $('#totalnew').text();
        var totalold = $('#totalold').text();
        var repayTime=$('#repayTime').text();
        var userId=$('#userId').val();
       
     
        $.ajax({
            url: "ShowAddOrder",
            type: "POST",
            data: {
                flag:flag,
                isPay: isPay,
                orderid:orderid,
                totalnew:totalnew,
                totalold:totalold,
                repayTime:repayTime,
                userId:userId
            },
            success: function (result) {
            	
            	if(result.code==100){
            	 swal(result.msg);
            	
            	}else{
            		
            		swal(result.msg);
            		location.href = "FrontWalletManage?flag=wallet"
            	}
            	
              
               // location.href = "info/list"
            },
            error: function () {
                swal("结算失败，无法连接到服务器！");
            }
        });
    });
});