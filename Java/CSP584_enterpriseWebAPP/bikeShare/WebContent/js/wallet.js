$(document).ready(function(){
    var oldPswflag=0;
    var newPswflag=0;
    $("#name").val($("#nameVal").text());
    $("#email").val($("#emailVal").text());
    $("#telephone").val($("#telephoneVal").text());
    $("#changeInfo").click(function(){
        $("#update-info").modal({
            backdrop:'static'
        });
    });


    $("#saveInfo").click(function (){
        var saveInfo={};
        saveInfo.payCash=$("#payCash").val();
        saveInfo.userId=$("#userIdVal").val();
        saveInfo.flag=$("#flag").val();
       
       
        $.ajax({
            type: "POST",
            url: "FrontWalletManage",
            contentType:"application/x-www-form-urlencoded; charset=utf-8",
            data:saveInfo,
            dateType:"json",
            
            success: function(result){
                if (result.code==100)
                {
                    swal(result.msg);
                }
                else {
                    $("#update-info").modal('hide');
                    swal(result.msg, "", "fail");
                    $("button").click(function (){
                    	location.reload();
                    });
                }
                location.reload();
            },
            error:function (){
                alert("服务器错误");
            }
        });
    });

   

});
