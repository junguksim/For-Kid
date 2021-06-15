<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>

<c:if test="${cookie.userid.value ne null}">
	<c:set var="saveid" value="${cookie.userid.value}"/>
	<c:set var="idck" value=" checked=\"checked\""/>
</c:if>
<!DOCTYPE html>
<html lang="">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
    integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
    integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
    crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
    integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
    crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
    integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
    crossorigin="anonymous"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="/script/header.js" ></script>
  <script>
  $(document).ready(function () {    	 
	 	$("#startBtn").on("click", function () {
	        $("p").remove("#startP");
	        $("button").remove("#startBtn");
	        $("#searchSection").css({"display" : "inline"});
		});
	    $("#searchBtn").on("click", function () {
	    	var aptName = $('input[name=aptName]').val();
	    	location.href = '${root}' + "/map/apt/name/" + aptName;
	    });
  });
  </script>
  
  	
   <title>Happy House :)</title>
</head>
<body>
  <header>
    <a href="${pageContext.request.contextPath}/" id="logo">HappyHouse</a>
    <div id="userDiv">
   
    <c:if test="${userinfo == null}">
      <button type="button" class="btn btn-success btn-round" id="signUpBtn" onclick="javascript:moveJoin();">회원가입</button>
      <button type="button" class="btn btn-info btn-round" id="loginBtn" data-toggle="modal" data-target="#loginModal">로그인</button>
    </c:if>
    <c:if test="${userinfo != null}">
       ${saveid} 님 환영합니다.
       <button type="button" class="btn btn-success btn-round" id="modifyBtn" onclick="javascript:info();">회원정보</button>
      <button type="button" class="btn btn-info btn-secondary" id="logoutBtn" onclick="javascript:logout();">로그아웃</button>
    </c:if>
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
        <input class="form-control mr-sm-2" type="search" placeholder="원하는 아파트를 찾아보세요" aria-label="Search" name="aptName">
        <button type="button" class="btn btn-outline-success my-2 my-sm-0" id="searchBtn">검색</button>
      </form>
    </div>
  </nav>
  <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
            
              <form id="loginform" method="post">
              	<input type="hidden" name="act" value="login">
                <div class="form-group">
                  <input type="email" class="form-control" id="userid" name="userid" placeholder="ID">
                </div>
                <div class="form-group">
                  <input type="password" class="form-control" id="userpwd" name="userpwd" placeholder="PW">
                </div>
                <button type="button" class="btn btn-info btn-block btn-round" id="loginFormBtn" onclick="javascript:login();">login</button>
              </form>
              
          </div>
        </div>
          <div class="modal-footer d-flex justify-content-center">
            <div class="signup-section">아직 회원이 아니신가요? <a href="/member/signupForm" class="text-info">회원가입하기</a></div>
          </div>
      </div>
    </div>
  </div>