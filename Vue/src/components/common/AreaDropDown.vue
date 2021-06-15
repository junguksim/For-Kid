<template>
  <div id="areaDropDownShown">
    <button
      @click="close"
      id="closeBtn"
      type="button"
      aria-label="Close"
      class="close text-dark"
    >
      <svg
        viewBox="0 0 16 16"
        width="1.8em"
        height="1.8em"
        focusable="false"
        role="img"
        aria-label="x"
        xmlns="http://www.w3.org/2000/svg"
        fill="currentColor"
        class="bi-x b-icon bi"
      >
        <g>
          <path
            d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"
          ></path>
        </g>
      </svg>
    </button>

    <h4>평형 선택</h4>
    <div v-for="(area, index) in areas" :key="index" id="areaItem">
      <div id="areaSize" style="width: 100%">{{ area }}평</div>
      <span
        v-for="(areaType, index2) in areaPrice[area]"
        :key="index2"
        id="areaItemInfo"
        class="info"
      >
        <!-- {{ areaType }} -->
        <span v-if="areaType.type == 1">
          <b>매매</b> {{ areaType.dealAmount | convertPrice }}억
        </span>

        <span v-else-if="areaType.type == 2">
          <b>전세</b> {{ areaType.guarantyMoney | convertPrice }}억
        </span>
      </span>
    </div>
  </div>
</template>
<script>
import { mapGetters } from "vuex";

export default {
  data() {
    return {
      areas: [],
      areaPrice: {},
      areaInfo: {},
      houseInfo: {},
    };
  },
  computed: {
    ...mapGetters(["aptInfoByArea"]),
  },
  created() {
    this.areas = Object.keys(this.aptInfoByArea.areaPrice);
    this.houseInfo = this.aptInfoByArea.houseInfo;
    this.areas.forEach((area) => {
      this.areaPrice[area] = this.aptInfoByArea.areaPrice[area];
    });
  },
  methods: {
    close() {
      this.$store.dispatch("setAreaDropDownShown", false);
    },
  },
};
</script>
<style>
#areaDropDownShown {
  margin-left: 23%;
  width: 23%;
  height: 100%;
  z-index: 1;
  position: absolute;
  background-color: white;
  top: 0;
  padding: 2%;
  max-height: 100%;
  overflow: scroll;
  box-shadow: 0 0.5rem 0.5rem rgba(0, 0, 0, 0.15);
}
#areaDropDownShown::-webkit-scrollbar {
  display: none;
}
#areaItem {
  text-align: right;
  height: 80px;
  margin-top: 10px;
  border-bottom: solid 1px lightgray;
}
#areaItemInfo {
  text-align: right;
  display: inline-block;
}
#areaSize {
  font-size: 25px;
  font-weight: 500;
  color: #4d55b2;
  text-align: left;
}
#area {
  color: gray;
}
#closeBtn {
  padding: 0 !important;
  position: absolute;
  right: 0;
  margin-right: 20px;
}
.info {
  width: 40%;
  text-align: right;
}
h4 {
  margin-bottom: 30px;
}
hr {
  margin: 0px;
}
</style>
