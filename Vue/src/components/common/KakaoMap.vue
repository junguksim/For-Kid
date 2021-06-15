<template>
  <div id="map"></div>
</template>
<script>
import { mapGetters } from "vuex";
import kakaoApiKey from "../../../config/kakaoApiKey.js";
import aptModule from "@/modules/aptModule.js";

let map,
  geocoder = new kakao.maps.services.Geocoder(),
  infoWindows = [];
export default {
  data() {
    return {
      searchItem: {},
      markers: [],
      centerAddr: "",
      lat: 0,
      lng: 0,
    };
  },
  computed: {
    ...mapGetters([
      "searchItemStore",
      "centerAddress",
      "mapMarkers",
      "mapInfoWindows",
      "aptInfoList",
      "schoolList",
      "around",
    ]),
  },
  mounted() {
    let { key } = kakaoApiKey;
    this.searchItem = this.searchItemStore;
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = `http://dapi.kakao.com/v2/maps/sdk.js?appkey=${key}`;
      document.head.appendChild(script);
    }
  },
  methods: {
    initMap() {
      const container = document.querySelector("#map");
      let { lat, lng } = this.searchItem;
      let center;
      if (lat == 0 && lng == 0) {
        center = new kakao.maps.LatLng(37.561161, 127.038481);
      } else {
        center = new kakao.maps.LatLng(this.searchItem.lat, this.searchItem.lng);
      }
      const options = {
        center,
        level: 3,
      };
      map = new kakao.maps.Map(container, options);
      // * map control
      const mapTypeControl = new kakao.maps.MapTypeControl();
      map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
      const zoomControl = new kakao.maps.ZoomControl();
      map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
      // * 중심 좌표 to 주소
      geocoder = new kakao.maps.services.Geocoder();
      const marker = new kakao.maps.Marker({
        position: center,
      });
      // var circle = new kakao.maps.Circle({
      //   center, // 원의 중심좌표 입니다
      //   radius: 800, // 미터 단위의 원의 반지름입니다
      //   strokeWeight: 5, // 선의 두께입니다
      //   strokeColor: "#75B8FA", // 선의 색깔입니다
      //   strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
      //   strokeStyle: "dashed", // 선의 스타일 입니다
      //   fillColor: "#CFE7FF", // 채우기 색깔입니다
      //   fillOpacity: 0.7, // 채우기 불투명도 입니다
      // });
      //circle.setMap(map);
      this.markers.push(marker);
      this.getCenter();
      this.setMarkers(map);
      this.addIdleEvent();
      window.map = map;
    },
    setMarkers(map) {
      for (const marker of this.markers) {
        marker.setMap(map);
      }
    },
    closeInfoWindows() {
      for (let infoWindow of infoWindows) {
        infoWindow.close();
      }
      infoWindows = [];
    },
    getCenter() {
      const latlng = map.getCenter();
      this.lat = latlng.getLat();
      this.lng = latlng.getLng();
      geocoder.coord2RegionCode(latlng.getLng(), latlng.getLat(), (result, status) => {
        if (status === kakao.maps.services.Status.OK) {
          for (const res of result) {
            if (res.region_type === "H") {
              // 법정동 코드
              this.$store.dispatch("setCenterAddress", res.address_name);
              this.centerAddr = res.address_name.replace(/\s/gi, "");
            }
          }
        }
      });
    },
    addIdleEvent() {
      kakao.maps.event.addListener(map, "idle", async () => {
        this.getCenter();
      });
    },
    async setApartmentInfo(level) {
      this.setMarkers(null);
      const result = await aptModule.getSimpleApartmentList(
        this.centerAddr,
        this.lat,
        this.lng,
        level
      );
      console.log(result);
      if (result != null) {
        await this.$store.dispatch("setAptInfoList", result.aptList);
      }
    },
    convertPrice(price) {
      return ((parseInt(price) * 10000) / 100000000).toString();
    },
    convertArea(area) {
      return Math.round(parseFloat(area) * 0.3025).toString();
    },
    changeInfoWindowCss() {
      let infowindows = document.querySelectorAll(
        ".map-info-window, .map-info-window-school,  .map-info-window-around"
      );
      infowindows.forEach(function (e) {
        let w = e.offsetWidth + 10;
        let ml = w / 2;
        e.parentElement.style.left = "50%";
        e.parentElement.style.marginLeft = -ml - 5 + "px";
        e.parentElement.style.marginTop = "3px";
        e.parentElement.style.width = w / 2 + "px";
        e.parentElement.previousSibling.style.display = "none";
        e.parentElement.parentElement.style.border = "0px";
        e.parentElement.parentElement.style.background = "unset";
      });
    },
    addClickEvent() {
      let infowindows = document.querySelectorAll(
        ".map-info-window, .map-info-window-school, .map-info-window-around"
      );
      infowindows.forEach((infowindow) => {
        infowindow.addEventListener("click", async () => {
          console.log(infowindow);
          if (infowindow.id.startsWith("S")) {
            for (const school of this.schoolList) {
              if (infowindow.id === school.schoolCode) {
                console.log(school);
                this.$store.dispatch("setSchoolInfo", school);
                this.$router.push({ name: "school", params: { schoolCode: school.schoolCode } });
              }
            }
          } else if (infowindow.id.startsWith("APT")) {
            for (const apt of this.aptInfoList) {
              if (infowindow.id.substring(3) === apt.aptNo) {
                const { aptNo, aptName, guguncode, lat, lng } = apt;
                await this.$store.dispatch("setSearchedApt", apt);
                const aptDealList = await aptModule.getApartmentDealList(aptNo);
                const aptInfoByArea = await aptModule.getAptInfoByArea(guguncode, aptName);
                const around = await aptModule.getAround(lat, lng);
                await this.$store.dispatch("setAround", around);
                await this.$store.dispatch("setAptDealInfoList", aptDealList);
                await this.$store.dispatch("setAptInfoByArea", aptInfoByArea);
                await this.$router.push({
                  name: "deal-list",
                  params: { aptNo },
                });
                map.panTo(new kakao.maps.LatLng(lat, lng));
              }
            }
          }
        });
      });
    },
  },
  watch: {
    searchItemStore: {
      handler(val) {
        this.searchItem = val;
        const position = new kakao.maps.LatLng(this.searchItem.lat, this.searchItem.lng);
        const marker = new kakao.maps.Marker({
          position,
        });
        this.setMarkers(null);
        this.markers = [];
        this.markers.push(marker);
        this.setMarkers(map);
        map.panTo(position);
      },
    },
    aptInfoList: {
      handler(val) {
        console.log("watcher : ", val);
        this.closeInfoWindows();
        if (val != null) {
          for (const aptInfo of val) {
            const content = `
            <div class='info-window map-info-window' id='APT${aptInfo.aptNo}'>
              <span style="font-size:10px">${this.convertArea(aptInfo.area)} 평</span><br/>
              <span style="font-size:14px; font-weight:600">${this.convertPrice(
                aptInfo.dealAmount
              )} 억</span>
            </div>
          `;
            const { lat, lng } = aptInfo;
            const position = new kakao.maps.LatLng(lat, lng);
            const infowindow = new kakao.maps.InfoWindow({
              map,
              position,
              content,
            });
            infoWindows.push(infowindow);
          }
        }

        if (this.schoolList != null) {
          for (const schoolInfo of this.schoolList) {
            const content = `
            <div class='info-window map-info-window-school' id='${schoolInfo.schoolCode}'>
              <span style="font-size:10px">${schoolInfo.category}</span><br/>
              <span style="font-size:14px; font-weight:600">${schoolInfo.classification}</span>
            </div>
          `;
            const position = new kakao.maps.LatLng(schoolInfo.lat, schoolInfo.lng);
            const infowindow = new kakao.maps.InfoWindow({
              map,
              position,
              content,
            });
            infoWindows.push(infowindow);
          }
        }
        if (this.around != undefined && this.around.hospital != null) {
          for (const hospitalInfo of this.around.hospital) {
            const content = `
            <div class='info-window map-info-window-around'>
              <img class="around-image" src="https://img.icons8.com/pastel-glyph/20/000000/hospital--v1.png"/>
              <div class='map-info-window-around-hover-box'>
                <div style="text-align:left">
                <span class='hover-box-place-name'>${hospitalInfo.placeName}</span>
                <span class='hover-box-place-distance'>${hospitalInfo.distance}m</span>
                </div>
                <span class='hover-box-address-name'>${hospitalInfo.addressName}</span>
              </div>
            </div>
          `;
            const position = new kakao.maps.LatLng(hospitalInfo.lat, hospitalInfo.lng);
            const infowindow = new kakao.maps.InfoWindow({
              map,
              position,
              content,
            });
            infoWindows.push(infowindow);
          }
        }
        if (this.around != undefined && this.around.mart != null) {
          for (const martInfo of this.around.mart) {
            const content = `
            <div class='info-window map-info-window-around'>
              <img class="around-image" src="https://img.icons8.com/fluent-systems-filled/20/000000/shopping-bag-full.png"/>
              <div class='map-info-window-around-hover-box'>
                <div style="text-align:left">
                <span class='hover-box-place-name'>${martInfo.placeName}</span>
                <span class='hover-box-place-distance'>${martInfo.distance}m</span>
                </div>
                <span class='hover-box-address-name'>${martInfo.addressName}</span>
              </div>
            </div>
          `;
            const position = new kakao.maps.LatLng(martInfo.lat, martInfo.lng);
            const infowindow = new kakao.maps.InfoWindow({
              map,
              position,
              content,
            });
            infoWindows.push(infowindow);
          }
        }

        this.changeInfoWindowCss();
        this.addClickEvent();
      },
    },
  },
};
</script>
<style>
#map {
  z-index: 0;
  position: relative;
  width: 100%;
  height: 100vh;
}

.info-window {
  user-select: none;
  -moz-user-select: none;
  -webkit-user-drag: none;
  -webkit-user-select: none;
  -ms-user-select: none;
}

.map-info-window {
  position: relative;
  width: 60px;
  height: 50px;
  background: #f3e8a0;
  border-radius: 10px;
  text-align: center;
  transition: transform 0.2s;
}

.map-info-window::after {
  border-top: 10px solid #f3e8a0;
  border-left: 10px solid transparent;
  border-right: 10px solid transparent;
  border-bottom: 0px solid transparent;
  content: "";
  position: absolute;
  top: 50px;
  left: 20px;
}

.map-info-window:hover {
  transform: scale(1.3);
}

.map-info-window-school {
  position: relative;
  width: 60px;
  height: 50px;
  background: #1da359;
  border-radius: 10px;
  text-align: center;
  color: white;
  transition: transform 0.2s;
}

.map-info-window-school::after {
  border-top: 10px solid #1da359;
  border-left: 10px solid transparent;
  border-right: 10px solid transparent;
  border-bottom: 0px solid transparent;
  content: "";
  position: absolute;
  top: 50px;
  left: 20px;
}

.map-info-window-school:hover {
  transform: scale(1.3);
}
.around-image {
  filter: invert(100%);
}
.map-info-window-around {
  position: relative;
  width: 40px;
  height: 30px;
  background: #363636;
  border-radius: 10px;
  text-align: center;
  color: white;
  transition: transform 0.2s;
}

.map-info-window-around::after {
  border-top: 10px solid #363636;
  border-left: 10px solid transparent;
  border-right: 10px solid transparent;
  border-bottom: 0px solid transparent;
  content: "";
  position: absolute;
  top: 30px;
  left: 10px;
}

.map-info-window-around:hover {
  transform: scale(1.3);
}

.map-info-window-around:hover .map-info-window-around-hover-box {
  display: block;
}
.map-info-window-around-hover-box {
  position: relative;
  width: max-content;
  height: 60px;
  top: -85px;
  right: 47px;
  color: black;
  border: 1px solid black;
  background-color: white;
  text-align: center;
  display: none;
  padding: 5px;
}
.hover-box-place-name {
  font-weight: 500;
  font-size: 13px;
  text-align: left;
}
.hover-box-place-distance {
  font-size: 10px;
  color: #b4b4b4;
  text-align: left;
}
.hover-box-address-name {
  font-size: 12px;
}
</style>
