<template>
  <div id="aptDetail">
    <div id="aptInfo">
      <img src="https://img.icons8.com/android/24/000000/back.png" @click="goBack" id="backBtn" />
      <span class="apt-name-white">{{ searchedApt.aptName }}</span
      ><br />
      <span class="small-info">{{ searchedApt.addressName }}</span>
      <div id="infoBarCategories">
        <div
          class="info-bar-category-dropdown"
          @click="
            {
              showTypeDropDown = !showTypeDropDown;
            }
          "
        >
          {{ dealType }}
          <img
            :class="{
              'down-arrow': !showTypeDropDown,
              'up-arrow': showTypeDropDown,
            }"
            src="@/assets/down-arrow.png"
            alt="아래 화살표"
          />
        </div>
        <div
          class="info-bar-category-dropdown"
          @click="
            {
              showAreaDropDown = !showAreaDropDown;
            }
          "
        >
          {{ areas[0] }}평
          <img
            :class="{
              'down-arrow': !showAreaDropDown,
              'up-arrow': showAreaDropDown,
            }"
            src="@/assets/down-arrow.png"
            alt="아래 화살표"
          />
        </div>
        <div
          class="info-bar-category-dropdown"
          @click="
            {
              showAreaComment = !showAreaComment;
            }
          "
        >
          코멘트
        </div>
      </div>
    </div>
    <div id="body">
      <div id="typeDropDownShown" v-if="showTypeDropDown">
        <div class="info-bar-category-shown" @click="changeTypeSale">매매</div>
        <div class="info-bar-category-shown" @click="changeTypeRent">전세</div>
      </div>
      <span class="label">실거래 정보</span>
      <apartment-deal-info :dealType="dealType"></apartment-deal-info>
      <deal-chart :chart-data="chartDataCollection"></deal-chart>
      <div id="aroundInfo">
        <span class="label">주변 시설 정보</span>
        <div>
          <span class="category" v-b-toggle.myHospital>
            <img src="@/assets/toggle_down.png" width="20px" />병원
            {{ around.hospital != null ? around.hospital.length : 0 }}개</span
          >
          <b-collapse id="myHospital">
            <ul>
              <li v-for="(hospital, index) in around.hospital" :key="index">
                {{ hospital.placeName }}
                <span class="small-info">{{ hospital.distance }}m</span>
              </li>
            </ul>
          </b-collapse>
        </div>
        <div>
          <span class="category" v-b-toggle.myMart>
            <img src="@/assets/toggle_down.png" width="20px" />마트
            {{ around.mart != null ? around.mart.length : 0 }}개</span
          >
          <b-collapse id="myMart">
            <ul>
              <li v-for="(mart, index) in around.mart" :key="index">
                {{ mart.placeName }}
                <span class="small-info">{{ mart.distance }}m</span>
              </li>
            </ul>
          </b-collapse>
        </div>
        <div>
          <span class="category" v-b-toggle.myKinder>
            <img src="@/assets/toggle_down.png" width="20px" />유치원/어린이집
            {{ around.kindergarden != null ? around.kindergarden.length : 0 }}개</span
          >
          <b-collapse id="myKinder">
            <ul>
              <li v-for="(kinder, index) in around.kindergarden" :key="index">
                {{ kinder.placeName }}
                <span class="small-info">{{ kinder.distance }}m</span>
              </li>
            </ul>
          </b-collapse>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { mapGetters } from "vuex";
import ApartmentDealInfo from "./ApartmentDealInfo.vue";
import DealChart from "./dealListChart";

export default {
  components: { ApartmentDealInfo, DealChart },
  data() {
    return {
      showTypeDropDown: false,
      showAreaDropDown: false,
      showAreaComment: false,
      showSaleList: true,
      areas: [],
      dealType: "",
      showHospital: false,
      showMart: false,
      showKinder: false,
      dealsPerMonth: new Array(12),
      chartDataCollection: {},
    };
  },
  computed: {
    ...mapGetters(["searchedApt", "aptInfoByArea", "around", "aptDealInfoList"]),
  },
  beforeMount() {
    this.$store.dispatch("setAreaDropDownShown", false);
    this.areas = Object.keys(this.aptInfoByArea.areaPrice);
    this.dealType = "매매";
    this.setDealsPerMonth(this.aptDealInfoList.saleList);
  },
  methods: {
    changeTypeSale() {
      this.dealType = "매매";
    },
    setDealsPerMonth(List) {
      this.dealsPerMonth = this.dealsPerMonth.fill(0);
      List.forEach((Item) => {
        this.dealsPerMonth[Item.dealMonth - 1]++;
      });
      this.chartDataCollection = {
        labels: [
          "1월",
          "2월",
          "3월",
          "4월",
          "5월",
          "6월",
          "7월",
          "8월",
          "9월",
          "10월",
          "11월",
          "12월",
        ],
        datasets: [
          {
            label: "2019년 거래 정보",
            backgroundColor: "#f87979",
            data: this.dealsPerMonth,
          },
        ],
      };
    },
    changeTypeRent() {
      this.dealType = "전세";
    },
    goBack() {
      this.$router.go(-1);
    },
  },
  watch: {
    showAreaDropDown: {
      handler(val) {
        this.$store.dispatch("setAreaDropDownShown", val);
      },
    },
    showAreaComment: {
      handler(val) {
        this.$store.dispatch("setAreaComment", val);
      },
    },
    aptDealInfoList: {
      immediate: true,
      handler(val) {
        console.log("변경된 aptDealInfoList", val);
        this.changeTypeSale();
        this.setDealsPerMonth(this.aptDealInfoList.saleList);
      },
    },
    dealType: {
      handler(val) {
        console.log("바뀐 값 : " + val);
        if (this.dealType == "매매") this.setDealsPerMonth(this.aptDealInfoList.saleList);
        else this.setDealsPerMonth(this.aptDealInfoList.rentList);
      },
    },
  },
};
</script>
<style scoped>
#aptInfo {
  background-color: #4d55b2;
  width: 100%;
  height: max-content;
  text-align: center;
  color: rgb(250, 250, 250, 0.9);
  padding: 5%;
  padding-bottom: 0% !important;
}
#body {
  padding: 5%;
}
#apartmentDealList {
  width: 100%;
}
#apartmentDetail {
  width: 100%;
  padding: 5%;
}

#infoBarCategories {
  width: 100%;
  display: inline-flex;
  position: relative;
  bottom: 0;
  margin: 0 auto;
  margin-top: 5%;
  margin-left: 10%;
  cursor: pointer;
}
#typeDropDownShown {
  background-color: #4d55b2;
  color: rgb(250, 250, 250, 0.7);
  width: 25%;
  margin-top: -6%;
  margin-left: 10%;
}
#aroundInfo {
  margin-top: 10px;
}
.info-bar-category-dropdown {
  width: 25%;
  border-top: 1px solid rgb(250, 250, 250, 0.1);
  border-right: 1px solid rgb(250, 250, 250, 0.1);
}
.info-bar-category-shown {
  position: inherit;
  top: 0;
  width: 100%;
  margin-left: 0.4em;
}
.down-arrow {
  filter: invert(100%);
}
.up-arrow {
  transform: rotate(-180deg);
  filter: invert(100%);
}
.apt-name-white {
  font-size: 20px;
  margin-top: 5px;
}
.small-info {
  font-size: 13px;
  line-height: 13px;
  margin-bottom: 5%;
}

.indented {
  padding-left: 0.5em;
}
.label {
  font-weight: 300;
  margin-top: 5%;
  margin-bottom: 5%;
}
.category {
  width: 100%;
  cursor: pointer !important;
}

#backBtn {
  filter: invert(100%);
  position: absolute;
  left: 0;
  margin-top: -10px;
}
</style>
