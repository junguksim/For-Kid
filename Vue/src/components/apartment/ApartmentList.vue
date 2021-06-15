<template>
  <div id="apartmentList">
    <apartment-info
      v-for="(aptInfo, index) in aptInfoList"
      :key="index"
      :aptInfo="aptInfo"
    />
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import ApartmentInfo from "./ApartmentInfo.vue";
export default {
  components: {
    ApartmentInfo,
  },
  computed: {
    ...mapGetters(["aptInfoList"]),
  },
};
</script>

<style>
#apartmentList {
  width: 100%;
}
</style>
