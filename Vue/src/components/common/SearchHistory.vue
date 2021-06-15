<template>
  <div id="searchHistoryDiv" v-if="searchHistory.length > 0">
    <span class="small-font gray-font">최근 방문</span>
    <div
      class="search-history-item"
      v-for="(history, index) in searchHistory"
      :key="index"
    >
      <span @click="setHistoryToItem(history)">{{ history.description }}</span>
      <button
        class="delete-one-search-history-btn"
        @click="deleteOneSearchHistory(history)"
      ></button>
    </div>
    <div id="deleteAllSearchHistoryDiv" class="small-medium-font gray-font">
      <a @click="deleteAllSearchHistory">최근 검색기록 삭제</a>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  computed: {
    ...mapGetters(["searchHistory", "searchItemStore"]),
  },
  methods: {
    async setHistoryToItem(history) {
      console.log(`history 클릭됨 : ${history.description}`);
      await this.$store.dispatch("setSearchItemStore", history);
    },
    async deleteAllSearchHistory() {
      let result = confirm("정말 삭제하시겠어요?");
      if (result) {
        await this.$store.dispatch("deleteAllSearchHistory");
      }
    },
    async deleteOneSearchHistory(history) {
      await this.$store.dispatch("deleteOneSearchHistory", history);
    },
  },
};
</script>
<style>
#searchHistoryDiv {
  border: 1px solid #e3d684;
  border-top: none;
}

#deleteAllSearchHistoryDiv {
  background-color: #e2e2e2;
  padding-left: 15px;
  cursor: pointer;
}

.search-history-item {
  padding-left: 15px;
  font-size: 14px;
  cursor: pointer;
}

.delete-one-search-history-btn {
  background: url("../../assets/x.png") no-repeat;
  border: none;
  width: 12px;
  height: 12px;
  cursor: pointer;
  background-color: #e2e2e2;
  border-radius: 50px;
  margin-left: 5px;
}
</style>
