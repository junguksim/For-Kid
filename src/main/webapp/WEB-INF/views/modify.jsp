<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@include file="header.jsp" %>
<link rel="stylesheet" href="/css/signup.css">

  <script>
    $(function() {
    	$("#modifyFormBtn").click(function() {
    		let member = JSON.stringify({
    			userId : `${saveid}`,
    			userPwd : $("#inputPassword").val(),
    			userName : $("#inputUserName").val(),
    			phoneNum : $("#inputCellPhone").val(),
    			email : $("#inputemail").val()
    		})
    		$.ajax({
    			url : "/member",
    			contentType : 'application/json',
    			method : 'put',
    			data : member,
    			success : function(data) {
    				if(data == "SUCCESS") {
    					alert("회원정보수정 성공!");
    					$(location).attr("href", "http://localhost/member/memberInfo")
    				}
    			}
    		})
    	})
    	$("#deleteFormBtn").click(function() {
    		if(confirm("정말 탈퇴하시겠습니까?")) {
    			$.ajax({
        			url : `/member/${saveid}`,
        			contentType : 'application/json',
        			method : 'delete',
        			success : function(data) {
        				if(data == "SUCCESS") {
        					alert("회원정보 삭제됨");
        					$(location).attr("href", "http://localhost")
        				}
        			}
        		})
    		}
    	})
    })
  </script>

  <div id="content">
    
    <div id="signUpDiv">
      <h3>회원정보 수정하기</h3>
      <form>
        <!--
        <div class="input-div">
          <label for="inputId" class="inputLabel">사용자 ID (* 20자 이내)</label><br>
          <input id="inputId" value="ssafy"  maxlength="20">
        </div>
          -->
        <div class="input-div">
          <label for="inputPassword" class="inputLabel">암호 (* 20자 이내)</label><br>
          <input id="inputPassword" placeholder="*********"  maxlength="20" value=${userinfo.userPwd}>
        </div>
        <div class="input-div">
          <label for="inputUserName" class="inputLabel">이름 (* 10자 이내)</label><br>
          <input id="inputUserName" placeholder="김싸피"  maxlength="10" value=${userinfo.userName}>
        </div>
        <div class="input-div">
          <label for="inputCellPhone" class="inputLabel">휴대폰 번호 (* 20자 이내)</label><br>
          <input id="inputCellPhone" placeholder="01013245678" maxlength="20" value=${userinfo.phoneNum}>
        </div>
        <div class="input-div">
          <label for="inputemail" class="inputLabel">E-mail 주소 (*)</label><br>
          <input type="text" id="inputemail" placeholder="ssafy@ssafy.com" value=${userinfo.email}>
        </div>
        <button type="button" class="btn btn-warning btn-block" id="modifyFormBtn">
         	 수정하기
        </button>
        <button type="button" class="btn btn-danger btn-block" id="deleteFormBtn">
         	탈퇴하기
        </button>
      </form>
    </div>

    <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
      aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
          <div class="modal-header border-bottom-0">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <div class="form-title text-center">
              <h4>로그인</h4>
            </div>
            <div class="d-flex flex-column text-center">
              <form>
                <div class="form-group">
                  <input type="email" class="form-control" id="loginEmailInput" placeholder="ID">
                </div>
                <div class="form-group">
                  <input type="password" class="form-control" id="loginPwdInput" placeholder="PW">
                </div>
                <button type="button" class="btn btn-info btn-block btn-round">Login</button>
              </form>
            </div>
          </div>
          <div class="modal-footer d-flex justify-content-center">
            <div class="signup-section">아직 회원이 아니신가요? <a href="#a" class="text-info">회원가입하기</a></div>
          </div>
        </div>
      </div>
    </div>
</body>

</html>