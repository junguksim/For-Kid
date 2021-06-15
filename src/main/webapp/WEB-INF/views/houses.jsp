<%@ page import="com.ssafy.happyhouse.model.HouseInfo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>

<link rel="stylesheet" href="/css/houses.css">
<%@include file="header.jsp" %>
<script>
	let aptName, aptLocation, dealAmount, area, buildYear;
    $(document).ready(function () {
      $(document).on("mouseenter", "#tradeInfo", function(){
    	  var lng = $(this).children('#lng').val();
    	  var lat = $(this).children('#lat').val();
    	  var aptName = $(this).children('#apartName').text();
    	  geocode(lat, lng, aptName);
      });

      $(document).on("click", "#tradeInfo", function() {
    	  aptName = $(this).children(".houseName").html()
    	  aptLocation =$(this).children(".houseLocation").html()
    	  dealAmount = $(this).children(".houseAmount").html()
    	  area = $(this).children(".houseArea").html()
    	  buildYear = $(this).children(".houseBuildYear").html()
    	  $("#houseDetailName").text(aptName)
    	  $("#houseDetailLocation").html(aptLocation)
    	  $("#houseDetailAmount").html(dealAmount)
    	  $("#houseDetailArea").html(area)
    	  $("#houseDetailBuildYear").html(buildYear)
      })
     }); 
  </script>
 
  <div id="content">
    <div id="tradeInfoContainerDiv">
      <h3>거래 정보</h3>
      <hr>
	  <c:if test="${aptList == null}"><h4>검색결과가 존재하지 않습니다</h4></c:if>
      <c:forEach var="apt" items="${aptList}">

      <div id="tradeInfo">
        <a id="apartName" class="houseName" data-toggle="modal" data-target="#DetailModal">${apt.aptName}</a><br>
		위치: <span class="houseLocation" class="house_info_statement">${apt.sidoName} ${apt.gugunName} ${apt.dong}</span><br>
		<span class="houseAmount" class="house_info_statement">거래금액 : ${apt.dealAmount}</span><br>
		<span class="houseArea" class="house_info_statement">면적 : ${apt.area}</span><br>
		<span class="houseBuildYear" class="house_info_statement">준공년도 : ${apt.buildYear}년 </span><br>
        <input type="hidden" id="lng" value=${apt.lng}  />
        <input type="hidden" id="lat" value=${apt.lat}  />
        <hr>
      </div>
      </c:forEach>
    </div>
    <div id="map" style="height: 500px"></div>
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
              <form>
                <div class="form-group">
                  <input type="email" class="form-control" id="loginEmailInput"placeholder="ID">
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
  
	<!-- APT Detail Modal -->
	 <div class="modal fade" id="DetailModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	    <div class="modal-dialog modal-dialog-centered" role="document">
	      <div class="modal-content">
	        <div class="modal-header border-bottom-0">
	          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	            <span aria-hidden="true">&times;</span>
	          </button>
	        </div>
	        <div class="modal-body">
	          <div class="form-title text-center">
	            <h4><b>상세 정보</b></h4>
	          </div>
	          <div class="d-flex flex-column text-center" id="houseDetailDiv">
	          <div id="apt-image-modal">
	           	  <span id="houseDetailLocation"></span>
	              <span id="houseDetailName"></span>
	              <img src="/img/apartment.jpg" style="width: 300px; height: 300px;">    
              </div>
	          <span id="houseDetailAmount"></span>
	          <span id="houseDetailArea"></span>
	          <span id="houseDetailBuildYear"></span>

	        </div>
	      </div>
	    </div>
	  </div>
	</div>
</div>

<script src="https://unpkg.com/@google/markerclustererplus@4.0.1/dist/markerclustererplus.min.js"></script>
<script defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBOG9rm9I18qgCZLNwd1cHY3vObrOsystg&callback=initMap"></script>
<script>
	var multi = {lat: 37.5665734, lng: 126.978179};
	var map;
	
	function initMap() {
		map = new google.maps.Map(document.getElementById('map'), {
			center: multi, zoom: 14
		});
	}
	function addMarker(tmpLat, tmpLng, aptName) {
		var marker = new google.maps.Marker({
			position: new google.maps.LatLng(parseFloat(tmpLat),parseFloat(tmpLng)),
			label: aptName,
			title: aptName
		});
		marker.addListener('click', function() {
			map.setZoom(17);
			map.setCenter(marker.getPosition());
			//callHouseDealInfo(aptName);
		});
		marker.setMap(map);
	}
	function callHouseDealInfo(aptName) {
		alert(aptName);
	}
	function geocode(lat, lng, aptName) {
		//$.each(jsonData, function(index, vo) {
			let tmpLat;
			let tmpLng;
			var infoWindow;
			infoWindow = new google.maps.InfoWindow;
			
			tmpLat = parseFloat(lat);
			tmpLng = parseFloat(lng);
 			
			initMap();
			addMarker(tmpLat, tmpLng, aptName);
			multi.lat = tmpLat;
			multi.lng = tmpLng;
			//infoWindow.setPosition(multi);
			//infoWindow.setContent(aptName);
			//infoWindow.open(map);
		
			/* $.get("https://maps.googleapis.com/maps/api/geocode/json"
					,{	key:'AIzaSyBOG9rm9I18qgCZLNwd1cHY3vObrOsystg'
						, address:vo.dong+"+"+vo.aptName+"+"+vo.jibun
					}
					, function(data, status) {
						alert(data + " " + status);
						tmpLat = data.results[0].geometry.location.lat;
						tmpLng = data.results[0].geometry.location.lng;
						$("#lat_"+index).text(tmpLat);
						$("#lng_"+index).text(tmpLng);
						addMarker(tmpLat, tmpLng, vo.aptName);
						multi.lat = tmpLat;
						multi.lng = tmpLng;
						infoWindow.setPosition(multi);
						infoWindow.setContent(vo.aptName);
						infoWindow.open(map);
					}
					, "json"
			);//get  */
		//});//each
	}
</script>
</body>

</html>