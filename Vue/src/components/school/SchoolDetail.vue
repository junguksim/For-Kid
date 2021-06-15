<template>
  <div id="schoolDetail">
    <school-title></school-title>
    <school-info></school-info>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import SchoolTitle from "./SchoolTitle.vue";
import SchoolInfo from "./SchoolInfo.vue";

export default {
  components: { SchoolTitle, SchoolInfo },
  computed: {
    ...mapGetters(["aptInfoList"]),
  },
};
</script>

<style>
#schoolDetail {
  width: 100%;
}
</style>
