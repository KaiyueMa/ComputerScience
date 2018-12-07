
$.validator.setDefaults({
	submitHandler: function() {
		form.submit();
	}
});
$(document).ready(function() {
	$('#form').validate({
		rules: {
			username: "required",
            telephone: {
				required: true,
			},
            email: {
				required: true,
				email: true,
			},
            password: {
				required: true,
				minlength: 6,
			},
			confirmPassword: {
				required: true,
				minlength: 6,
				equalTo: "#password",
			}
		},
		messages: {
            username: "Username canot be null",
            telephone: "phone canot be null",
            email: {
				required: "email canot be null",
				email: "wrong email format",
			},
            password: {
				required: "password canot be null",
				minlength: "password length must be more than 6",
			},
			confirmPassword: {
				required: "canot be null",
				minlength: "password length must be more than 6",
				equalTo: "password mismatch",
			}
		}
	});
});