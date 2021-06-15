<template>
  <div>
    <table id="searchFilter">
      <div
        v-for="(type, index) in filterTypes"
        :key="index"
        class="search-filter-row"
      >
        <div
          @click="setClickedFilter(index + 1)"
          :class="{
            'search-filter-item': clickedNo != index + 1,
            'search-filter-item-clicked': clickedNo == index + 1,
          }"
        >
          {{ type }}
        </div>
        <tr v-if="index % 2 === 0"></tr>
      </div>
    </table>
    <div id="logo">
      <img src="../../assets/logo.png" width="330px" alt="로고" />
    </div>
  </div>
</template>
<script>
import { mapGetters } from "vuex";
export default {
  data() {
    return {
      filterTypes: ["매매", "유형", "평형", "가격", "세대수", "입주년차"],
      clickedNo: 0,
    };
  },
  computed: {
    ...mapGetters(["clickedSearchFilter"]),
  },
  methods: {
    setClickedFilter(clickedNo) {
      this.clickedNo = clickedNo;
    },
  },
};
</script>
<style scoped>
#searchFilter {
  width: 100%;
}

.search-filter-row {
  display: inline-block;
  width: 50%;
}

.search-filter-item {
  border: 1px solid #e2e2e2;
  width: 100%;
  padding: 15px;
}

.search-filter-item-clicked {
  border: 1px solid #e2e2e2;
  width: 100%;
  padding: 15px;
  background-color: #faebad;
}
#logo {
  margin: 0 auto;
  margin-top: 60px;
}
h3 {
  font-weight: 500;
  color: #faebad;
  font-display: fallback;
}
</style>
