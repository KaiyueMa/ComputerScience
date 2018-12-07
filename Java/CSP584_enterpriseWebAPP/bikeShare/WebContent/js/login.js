
$.validator.setDefaults({
	submitHandler: function() {
		form.submit();
	}
});
$(document).ready(function() {
	$('#form2').validate({
		rules: {
			username: "required",
			
			password: {
				required: true,
			},
			confirmlogo: "required",
		},
		messages: {
			username: "Username cannot be blank",
			
			password: {
				required: "Password cannot be blank",
			},
			confirmlogo: "Verification code cannot be blank",
		}
	});
});