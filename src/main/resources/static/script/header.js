/**
 * 
 */

function login() {
	if(document.getElementById("userid").value == "") {
		alert("아이디 입력!!!");
		return;
	} else if(document.getElementById("userpwd").value == ""){
		alert("비밀번호 입력!!!");
		return;
	} else {
		let member = JSON.stringify({
			userId : $("#userid").val(),
			userPwd : $("#userpwd").val()
		})
		$.ajax({
			url : "/member/login",
			contentType : 'application/json',
			method : 'post',
			data : member,
			success : function(data) {
				if(data == "SUCCESS") {
					location.reload();
				} else {
					alert("아이디 혹은 비밀번호를 확인해주세요.")
				}
			}
		})
	}
}	
function moveJoin() {
	$(location).attr("href", "http://localhost/member/signupForm")
}
function logout() {
	$.ajax({
		url : "/member/logout",
		contentType : 'application/json',
		method : 'post',
		success : function(data) {
			location.reload();
		}
	})
}
function modify() {
	$(location).attr("href", "http://localhost/member/modifyForm")
}

function info() {
	$(location).attr("href", "http://localhost/member/memberInfo")
}

