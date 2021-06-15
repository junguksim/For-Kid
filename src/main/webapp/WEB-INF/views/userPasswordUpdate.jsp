<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@include file="header.jsp" %>
<link rel="stylesheet" href="/css/userPasswordUpdate.css">
  <script>
    $(document).ready(function () {
      let maxInputNotified = false;
      $("#startBtn").on("click", function () {
        $("p").remove("#startP");
        $("button").remove("#startBtn");
      });
      $("#signUpBtn").on("click", function () {
        window.open("signup.html");
      })
      $("#searchBtn").on("click", function () {
        window.open("houses.html");
      })
      $("#passwordUpdateBtn").on("click", function () {
        window.open("userPasswordUpdate.html");
      })
      $("#modifyBtn").on("click", function () {
        window.open("modify.html");
      })
      $("#loginBtn").on("click", function () {
        $('#loginModal').modal('show');
        $(function () {
          $('[data-toggle="tooltip"]').tooltip()
        })
      })
      $("#logoutLink").on("click", function () {
        $("#userInfoDiv").css("height", "0px");
        $("#welcomeMsg").html("");
        $("#logoutLink").css("display", "none");
      })
      $("#loginFormBtn").on("click", function() {
        $("#signUpBtn").css({"display" : "none"});
        $("#loginBtn").css({"display" : "none"});
        $("#modifyBtn").css({"display" : "inline"});
        $("#passwordUpdateBtn").css({"display" : "inline"});
        $("#logoutBtn").css({"display" : "inline"});
        $('#loginModal').modal('hide');
      })
      $("#logoutBtn").on("click", function() {
        $("#signUpBtn").css({"display" : "inline"});
        $("#loginBtn").css({"display" : "inline"});
        $("#modifyBtn").css({"display" : "none"});
        $("#passwordUpdateBtn").css({"display" : "none"});
        $("#logoutBtn").css({"display" : "none"});
      })
      $("#loginBtn").on("click", function () {
        let inputId = $("#loginInputId").val();
        let inputPw = $("#loginInputPw").val();
        let canLogin = false;
        $.ajax({
          url: "./userlist.xml",
          "dataType": "xml",
          success: function (doc) {
            $(doc).find("user").each(function () {
              let id = $(this).children("id").text();
              let pw = $(this).children("password").text()
              if (inputId == id && inputPw == pw) {
                alert("로그인 되었습니다!");
                $("#userInfoDiv").css("height", "70px");
                $("#welcomeMsg").html(`<p>환영합니다 ${id}님!</p>`);
                $("#logoutLink").css("display", "inline");

                canLogin = true;
                $("#loginModal").modal("toggle");
              }
            })
            if (!canLogin) {
              alert("아이디나 비밀번호가 맞지 않습니다.");
            }
          }
        })
      })
      $("#btnUpdatePwd").on("click", function() {
        window.open("index.html");
      })
    })
  </script>
  <title>Find Password</title>
</head>

<body>
  <header>
    <a href="index.html" id="logo">HappyHouse</a>
    <div id="userDiv">
      <button type="button" class="btn btn-success btn-round" id="signUpBtn" data-remote="signup.html">회원가입</button>
      <button type="button" class="btn btn-info btn-round" id="loginBtn" data-toggle="modal" data-target="#loginModal">로그인</button>
      <button type="button" class="btn btn-success btn-round" id="modifyBtn" style="display : none">회원정보수정</button>
      <button type="button" class="btn btn-info btn-round" id="passwordUpdateBtn" style="display:none">비밀번호수정</button>
      <button type="button" class="btn btn-info btn-secondary" id="logoutBtn" style="display:none">로그아웃</button>
    </div>
  </header>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item active">
          <a class="nav-link" href="#">아파트 거래정보 <span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">관심 지역 정보 관리</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">주변 탐방하기</a>
        </li>
      </ul>
      <form class="form-inline my-2 my-lg-0">
        <input class="form-control mr-sm-2" type="search" placeholder="원하는 아파트를 찾아보세요" aria-label="Search">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit" id="searchBtn">검색</button>
      </form>
    </div>
  </nav>
  <div id="content">
    <div id="updatePwd">
      <div class="head-content">
        <h3>비밀번호 찾기</h3>
      </div>
      <form class="was-validated" id="inputForm">
        <div class="form-group input-div">
          <label for="inputUserId">아이디:</label>
          <input type="text" class="form-control" id="inputUserId" placeholder="Enter ID" name="uid" required>
          <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        <div class="form-group input-div">
          <label for="inputUserPhone">휴대폰 번호:</label>
          <input type="tel" class="form-control" id="inputUserPhone" placeholder="Enter Phone Number"
            name="uphonenumber" required>
          <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        <button type="button" class="btn btn-primary" id="btnUpdatePwd">비밀번호 찾기</button>
      </form>
    </div>
  </div>
</body>

</html>