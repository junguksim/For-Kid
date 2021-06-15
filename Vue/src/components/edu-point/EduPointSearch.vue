<template>
  <div id="eduPointSearch">
    <div>
      <span class="title">
        동네 교육 지수 순위
        <img
          id="help"
          src="@/assets/help.png"
          width="20px"
          v-b-tooltip.hover.right="
            '교육지수란? 아파트 주변 학교, 학원, 어린이집의 개수를 합한 수를 의미합니다.'
          "
        />
      </span>
    </div>
    <!-- <div id="backBtn">
      <img src="https://img.icons8.com/android/24/000000/back.png" @click="goBack" />
      <span class="small-size-font">다시 검색하기</span>
    </div> -->
    <b-form id="selectZoneForm">
      <b-form-select class="select" v-model="sido" :options="sidoOptions">
        <template #first>
          <b-form-select-option class="select-option" :value="null" disabled
            >-- 시/도 --</b-form-select-option
          >
        </template>
      </b-form-select>
      <b-form-select class="select" v-model="gugun" :options="gugunOptions">
        <template #first>
          <b-form-select-option class="select-option" :value="null" disabled
            >-- 구/군 --</b-form-select-option
          >
        </template>
      </b-form-select>
      <b-form-select class="select" v-model="dong" :options="dongOptions">
        <template #first>
          <b-form-select-option class="select-option" :value="null" disabled
            >-- 동 --</b-form-select-option
          >
        </template>
      </b-form-select>
    </b-form>
    <div id="resultTable" v-if="allSelected">
      <edu-point-list :sido="sido" :gugun="gugun" :dong="dong" />
    </div>
  </div>
</template>
<script>
import EduPointList from "./EduPointList.vue";
export default {
  components: { EduPointList },
  beforeMount() {
    this.$store.dispatch("setShowMap", false);
    this.$store.dispatch("setShowSide", true);
    this.$store.dispatch("setAreaDropDownShown", false);
  },
  data() {
    return {
      sido: null,
      sidoOptions: hangjungdong.sido.map((sido) => {
        return {
          value: sido.sido,
          text: sido.codeNm,
        };
      }),
      gugun: null,
      gugunOptions: [],
      dong: null,
      dongOptions: [],
      guguncode: null,
      allSelected: false,
      showHelp: false,
    };
  },
  methods: {
    goBack() {
      this.$forceUpdate();
    },
    setGugunOptions() {
      this.gugunOptions = hangjungdong.sigugun
        .filter((gugun) => {
          return gugun.sido == this.sido;
        })
        .map((gugun) => {
          return {
            value: gugun.sigugun,
            text: gugun.codeNm,
          };
        });
    },
    setDongOptions() {
      this.dongOptions = hangjungdong.dong
        .filter((dong) => {
          return dong.sido == this.sido && dong.sigugun == this.gugun;
        })
        .map((dong) => {
          return {
            value: dong.codeNm,
            text: dong.codeNm,
          };
        });
    },
  },
  watch: {
    sido() {
      this.setGugunOptions();
    },
    gugun() {
      this.setDongOptions();
    },
    dong() {
      this.allSelected = true;
    },
  },
};
</script>
<style scoped>
#eduPointSearch {
  padding: 30px;
  width: 70%;
  text-align: center;
  margin: 0 auto;
  margin-top: 5%;
}

#selectZoneForm {
  margin-top: 1%;
}
.select {
  margin: 15px;
}
.select-option {
  text-align: center;
}
#backBtn {
  cursor: pointer;
  float: left;
}
#resultTable {
  margin-top: -5%;
}
.show {
  display: block;
}
.non-show {
  display: none;
}
.title {
  font-size: 30px;
}
</style>
