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
                alert("????????? ???????????????!");
                $("#userInfoDiv").css("height", "70px");
                $("#welcomeMsg").html(`<p>??????????????? ${id}???!</p>`);
                $("#logoutLink").css("display", "inline");

                canLogin = true;
                $("#loginModal").modal("toggle");
              }
            })
            if (!canLogin) {
              alert("???????????? ??????????????? ?????? ????????????.");
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
      <button type="button" class="btn btn-success btn-round" id="signUpBtn" data-remote="signup.html">????????????</button>
      <button type="button" class="btn btn-info btn-round" id="loginBtn" data-toggle="modal" data-target="#loginModal">?????????</button>
      <button type="button" class="btn btn-success btn-round" id="modifyBtn" style="display : none">??????????????????</button>
      <button type="button" class="btn btn-info btn-round" id="passwordUpdateBtn" style="display:none">??????????????????</button>
      <button type="button" class="btn btn-info btn-secondary" id="logoutBtn" style="display:none">????????????</button>
    </div>
  </header>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item active">
          <a class="nav-link" href="#">????????? ???????????? <span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">?????? ?????? ?????? ??????</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">?????? ????????????</a>
        </li>
      </ul>
      <form class="form-inline my-2 my-lg-0">
        <input class="form-control mr-sm-2" type="search" placeholder="????????? ???????????? ???????????????" aria-label="Search">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit" id="searchBtn">??????</button>
      </form>
    </div>
  </nav>
  <div id="content">
    <div id="updatePwd">
      <div class="head-content">
        <h3>???????????? ??????</h3>
      </div>
      <form class="was-validated" id="inputForm">
        <div class="form-group input-div">
          <label for="inputUserId">?????????:</label>
          <input type="text" class="form-control" id="inputUserId" placeholder="Enter ID" name="uid" required>
          <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        <div class="form-group input-div">
          <label for="inputUserPhone">????????? ??????:</label>
          <input type="tel" class="form-control" id="inputUserPhone" placeholder="Enter Phone Number"
            name="uphonenumber" required>
          <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        <button type="button" class="btn btn-primary" id="btnUpdatePwd">???????????? ??????</button>
      </form>
    </div>
  </div>
</body>

</html>