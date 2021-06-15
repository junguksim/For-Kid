<template>
  <div>
    <left-aside type="schoolDetail"></left-aside>
    <navi-toggle-btn></navi-toggle-btn>
  </div>
</template>

<script>
import LeftAside from "../common/LeftAside.vue";
import NaviToggleBtn from "../common/NaviToggleBtn.vue";

export default {
  components: { LeftAside, NaviToggleBtn },
  mounted() {
    this.$store.dispatch("setAreaDropDownShown", false);
  },
};
</script>
