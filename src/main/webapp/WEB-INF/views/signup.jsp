<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@include file="header.jsp" %>
<link rel="stylesheet" href="/css/signup.css">
<script>

    function signup() {
    	if(document.getElementById("inputId").value == "") {
    		alert("아이디 입력!!!!");
    		return;
    	} else if(document.getElementById("inputPassword").value == "") {
    		alert("비밀번호 입력!!!!");
    		return;
    	}else if(document.getElementById("inputUserName").value == "") {
    		alert("이름 입력!!!!");
    		return;
    	}else if(document.getElementById("inputCellPhone").value == "") {
    		alert("휴대폰번호 입력!!!!");
    		return;
    	}else if(document.getElementById("inputemail").value == "") {
    		alert("이메일 입력!!!!");
    		return;
    	}else {
    		let member = JSON.stringify({
    			userId : $("#inputId").val(),
    			userPwd : $("#inputPassword").val(),
    			userName : $("#inputUserName").val(),
    			phoneNum : $("#inputCellPhone").val(),
    			email : $("#inputemail").val()
    		})
    		$.ajax({
    			url : "/member",
    			contentType : 'application/json',
    			method : 'post',
    			data : member,
    			success : function(data) {
    				if(data == "SUCCESS") {
    					alert("회원가입 성공!");
    					$(location).attr("href", "http://localhost")
    				}
    			}
    		})
    	}
    }	
  </script>
  <div id="content">
    
    <div id="signUpDiv">
      <h3>회원가입하기</h3>
      <h4>${msg}</h4>
      <form id="signupform" method="post" aciton="">
      	<input type="hidden" name="act" value="signup">
        <div class="input-div">
          <label for="inputId" class="inputLabel">사용자 ID (* 20자 이내)</label><br>
          <input type="text" id="inputId"  placeholder="ID" maxlength="20">
        </div>
        <div class="input-div">
          <label for="inputPassword" class="inputLabel">암호 (* 20자 이내)</label><br>
          <input type="text" id="inputPassword" placeholder="비밀번호" maxlength="20" >
        </div>
        <div class="input-div">
          <label for="inputUserName" class="inputLabel">이름 (* 10자 이내)</label><br>
          <input type="text" id="inputUserName" placeholder="이름" maxlength="10" >
        </div>
        <div class="input-div">
          <label for="inputCellPhone" class="inputLabel">휴대폰 번호 (* 20자 이내)</label><br>
          <input type="text" id="inputCellPhone" placeholder="01012345678" maxlength="20" >
        </div>
        <div class="input-div">
          <label for="inputemail" class="inputLabel">E-mail 주소 (*)</label><br>
          <input type="text" id="inputemail" placeholder="ssafy@ssafy.com" >
        </div>
        <button type="button" class="btn btn-warning btn-block"  onclick="javascript:signup();">
         	 가입하기
        </button>
      </form>
    </div>
 
</body>

</html>