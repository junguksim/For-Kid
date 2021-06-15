<template>
  <div id="apartment">
    <left-aside type="ApartmentList" />
    <bottom-address-bar />
    <navi-toggle-btn></navi-toggle-btn>
  </div>
</template>
<script>
import BottomAddressBar from "../common/BottomAddressBar.vue";
import LeftAside from "../common/LeftAside.vue";
import NaviToggleBtn from "../common/NaviToggleBtn.vue";

export default {
  components: { LeftAside, BottomAddressBar, NaviToggleBtn },
  beforeMount() {
    this.$store.dispatch("setShowMap", true);
    this.$store.dispatch("setShowSide", false);
    this.$store.dispatch("setAreaDropDownShown", false);
  },
};
</script>
<style></style>
