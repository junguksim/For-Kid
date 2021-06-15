<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@include file="header.jsp" %>
<link rel="stylesheet" href="/css/index.css">
<script>

	$(document).ready(function(){
		$("#dongSearchBtn").on("click", function() {
		  	  var dong = $("#dong").val()
		  	  location.href = "${pageContext.request.contextPath}/map/apt/"+dong;
		 });
		
		$.get("${pageContext.request.contextPath}/map/sido"
				,function(data, status){
					$.each(data.data, function(index, vo) {
						$("#sido").append("<option value='"+vo.sidoCode+"'>"+vo.sidoName+"</option>");
					});//each
				}//function
			, "json");//get

		$("#sido").change(function() {
			var sido = $("#sido").val();
			$.get("${pageContext.request.contextPath}/map/gugun/" + sido
					,function(data, status){
						$("#gugun").empty();
						$("#gugun").append('<option value="0">선택</option>');
						$.each(data.data, function(index, vo) {
							$("#gugun").append("<option value='"+vo.gugunCode+"'>"+vo.gugunName+"</option>");
						});//each
					}//function
					, "json"
			);//get
		});//change
		$("#gugun").change(function() {
			var gugun = $("#gugun").val();
			$.get("${pageContext.request.contextPath}/map/dong/" + gugun
					,function(data, status){
						$("#dong").empty();
						$("#dong").append('<option value="0">선택</option>');
						$.each(data.data, function(index, vo) {
							$("#dong").append("<option value='"+vo.dong+"'>"+vo.dong+"</option>");
						});//each
					}//function
					, "json"
			);//get
		});//change
		$("#dong").change(function() {
			$.get("${pageContext.request.contextPath}/map"
					, "json"
			);//get
		});//change
	});//ready
	
	function geocode(jsonData) {
		let idx = 0;
		$.each(jsonData, function(index, vo) {
			let tmpLat;
			let tmpLng;
			$.get("https://maps.googleapis.com/maps/api/geocode/json"
				,{	key:'Google API Key'
					, address:vo.dong+"+"+vo.aptName+"+"+vo.jibun
				}
				, function(data, status) {
					//alert(data.results[0].geometry.location.lat);
					tmpLat = data.results[0].geometry.location.lat;
					tmpLng = data.results[0].geometry.location.lng;
					$("#lat_"+index).text(tmpLat);
					$("#lng_"+index).text(tmpLng);
					addMarker(tmpLat, tmpLng, vo.aptName);
				}
				, "json"
		);//get
	});//each
	}
</script>
  <div id="content">
    <!-- 검색 Section-->
    <section id="searchSection" style="display:none;">
        <div class="divider-custom">
            <div class="divider-custom-line"></div>
            <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
            <div class="divider-custom-line"></div>
        </div>
        <div class="container" id="select-location" style="text-align:center">
        	시 : <select id="sido">
                <option value="0">선택</option>
            </select>
                           구 : <select id="gugun">
                <option value="0">선택</option>
            </select>
        	동 : <select id="dong">
                <option value="0">선택</option>
            </select>
            <input type="button" value="검색" style="width: 65px; background-color:#446280; color: white;" id="dongSearchBtn">
        </div>
    </section>
    <p id="startP">내 집 찾기를 시작해볼까요?</p>
    <button type="button" class="btn btn-secondary" id="startBtn">시작하기</button>
    <div id="signUpModal"></div>
</body>

</html>