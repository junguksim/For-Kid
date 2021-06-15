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
    	})
    })
  </script>
<div id="content">
	<c:if test="${userinfo == null}">
      <h3> 권한이 없습니다! </h3>
      <button type="button" class="btn btn-success btn-round" id="signUpBtn" onclick="javascript:moveJoin();">회원가입</button>
      <button type="button" class="btn btn-info btn-round" id="loginBtn" data-toggle="modal" data-target="#loginModal">로그인</button>
    </c:if>
    <c:if test="${userinfo != null}">
      <div id="signUpDiv">
      <h3>회원정보</h3>
      <form>
        <div class="input-div">
         	ID : ${userinfo.getUserId()}
        </div>
        <div class="input-div">
          	이름 : ${userinfo.getUserName()}
        </div>
        <div class="input-div">
         	 휴대폰번호 : ${userinfo.getPhoneNum()}
        </div>
        <div class="input-div">
         	 E-Mail : ${userinfo.getEmail()}
        </div>
        
      <button type="button" class="btn btn-success btn-round" id="modifyBtn" onclick="javascript:modify();">회원정보수정</button>
      </form>
    </div>
    </c:if>
	
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