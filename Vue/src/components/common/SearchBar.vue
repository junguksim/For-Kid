<template>
  <div>
    <div id="searchBar">
      <img id="homeBtn" src="@/assets/house.png" alt="홈" @click="goMain" />
      <input
        id="searchItemInput"
        type="text"
        placeholder="아파트, 지역, 또는 학교명으로 검색하기"
        v-model="searchItem.description"
        @focus="searchBarFocused = true"
        class="gray-font small-font"
        autocomplete="off"
      />
      <input type="button" class="search-button" @click="addSearchHistory" />
    </div>

    <div v-if="searchBarFocused && searchItem.description.length == 0">
      <search-history></search-history>
    </div>
    <div v-if="predictions.length > 0" id="predictionsDiv">
      <div
        v-for="(prediction, index) in predictions"
        :key="index"
        class="prediction-item"
        @click="setSearchItem(prediction)"
      >
        {{ prediction.description }}
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import SearchHistory from "./SearchHistory.vue";
import aptModule from "@/modules/aptModule.js";
import searchModule from "@/modules/searchModule.js";

export default {
  components: { SearchHistory },
  data() {
    return {
      searchItem: {
        description: "",
        placeId: "",
        lat: 0,
        lng: 0,
      },
      searchBarFocused: false,
      predictions: [],
    };
  },
  computed: {
    ...mapGetters(["searchHistory", "searchItemStore"]),
  },
  async updated() {
    // * 자동완성 기능
    //if (this.searchItem.description.length == 0) return;
    this.predictions = await searchModule.getAutoCompleteList(this.searchItem.description);
  },
  methods: {
    goMain() {
      this.$router.push("/");
    },
    isSearchItemNull() {
      let { description, lat, lng, placeId } = this.searchItem;
      if (description.length === 0 || lat === 0 || lng === 0 || placeId.length === 0) {
        console.error("아직 item 설정안됨!");
        return true;
      }
    },
    addHistory() {
      let alreadyHistory = false;
      for (let history of this.searchHistory) {
        console.log(`${history.description} ${this.searchItem.description}`);
        if (history.description === this.searchItem.description) {
          alreadyHistory = true;
          break;
        }
      }
      if (!alreadyHistory) {
        this.$store.dispatch("addSearchHistory", this.searchItem);
      }
    },
    setSearchItemToNull() {
      this.searchItem = {
        description: "",
        placeId: "",
        lat: 0,
        lng: 0,
      };
    },
    async addSearchHistory() {
      // * 검색 버튼 눌렀을 때
      if (this.isSearchItemNull()) {
        return;
      }
      this.addHistory();
      let { description, lat, lng } = this.searchItem;
      let defaultLevel = 3;
      const result = await aptModule.getApartmentList(description, lat, lng, defaultLevel);
      if (result != null) {
        this.setSearchItemToNull();
        const { aptList, origin } = result;
        console.log("aptList : ", aptList);
        const schoolList = await aptModule.getSchoolList(origin.guguncode, origin.lat, origin.lng);
        console.log("schoolList : ", schoolList);
        const around = await aptModule.getAround(origin.lat, origin.lng);
        console.log("around :", around);
        await this.$store.dispatch("setAround", around);
        await this.$store.dispatch("setSchoolList", schoolList);
        await this.$store.dispatch("setAptInfoList", aptList);
      }

      await this.$router.push({
        path: "/apartment/list",
        query: { keyword: description, lat, lng },
      });
    },
    async setSearchItem({ description, place_id }) {
      const location = await searchModule.getLocation(place_id);
      const result = {
        description,
        ...location,
        placeId: place_id,
      };
      console.log("history 요소 결과 : " + result);
      this.predictions = [];
      this.searchItem = result;
      await this.$store.dispatch("setSearchItemStore", result);
    },
  },
  watch: {
    searchItemStore: async function (newSearchItem) {
      console.log(`searchItemStore 변경됨! ${newSearchItem.description}`);
      this.searchItem = { ...newSearchItem };
    },
  },
};
</script>
<style>
#searchBar {
  width: 100%;
  display: inline-flex;
  border-bottom: solid 2px #faebad;
  padding: 5px;
}

#searchItemInput {
  line-height: 30px;
  width: 80%;
  border: none;
  font-family: "Malgun Gothic";
  font-size: 12px;
  padding-left: 10px !important;
}
#homeBtn {
  width: 9%;
  height: 9%;
  margin: 3px;
  cursor: pointer;
}
.search-button {
  background: url("../../assets/button_search.png") no-repeat;
  height: 30px;
  width: 30px;
  border: none;
  cursor: pointer;
  margin-top: 2px;
}

#predictionsDiv {
  border: 1px solid #e2e2e2;
}

.prediction-item {
  width: 100%;
  overflow-x: hidden;
  white-space: nowrap;
  margin: 2px 0;
}

.prediction-item:hover {
  display: inline-block;
  overflow-x: visible;
  border: 1px solid #e2e2e2;
  background-color: rgb(245, 245, 245);
  background-repeat: no-repeat;
  width: max-content;
}
</style>
