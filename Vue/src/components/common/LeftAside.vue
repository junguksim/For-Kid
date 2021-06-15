<template>
  <div
    id="leftAside"
    class="shadow mb-5 bg-white rounded"
    :class="{ showLeftNavi: !this.showSide, hideLeftNavi: this.showSide }"
  >
    <search-bar></search-bar>
    <div class="content" v-if="type === 'Home'">
      <search-filter></search-filter>
    </div>
    <div class="content" v-else-if="type === 'ApartmentList'">
      <apartment-list />
    </div>
    <div class="content" v-else-if="type === 'ApartmentDealList'">
      <apartment-deal-list :key="apartmentDealListKey" />
    </div>
    <div class="content" v-else-if="type === 'schoolDetail'">
      <school-detail />
    </div>
  </div>
</template>

<script>
import ApartmentDealList from "../apartment/ApartmentDealList.vue";
import ApartmentList from "../apartment/ApartmentList.vue";
import SearchBar from "./SearchBar.vue";
import SearchFilter from "./SearchFilter.vue";
import SchoolDetail from "../school/SchoolDetail.vue";
import { mapGetters } from "vuex";

export default {
  components: {
    SearchBar,
    SearchFilter,
    ApartmentList,
    ApartmentDealList,
    SchoolDetail,
  },
  props: ["type"],
  computed: {
    ...mapGetters(["showSide"]),
  },
  data() {
    return {
      apartmentDealListKey: 0,
    };
  },
  methods: {
    forceRerender() {
      this.apartmentDealListKey += 1;
      console.log(this.apartmentDealListKey);
    },
  },
};
</script>
<style scoped>
#leftAside {
  width: 23%;
  height: 100%;
  position: absolute;
  top: 0;
  background-color: white;
  box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15);
  overflow-y: scroll;
  z-index: 1;
  transition: 1s;
}
#leftAside::-webkit-scrollbar {
  display: none;
}
.scrollBox::-webkit-scrollbar {
  display: none;
}
.content {
  height: inherit;
}
.showLeftNavi {
  margin-left: 0%;
}
.hideLeftNavi {
  margin-left: -23%;
}
</style>
