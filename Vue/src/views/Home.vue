<template>
  <div id="home">
    <left-aside type="Home" />
    <bottom-address-bar></bottom-address-bar>
    <navi-toggle-btn></navi-toggle-btn>
  </div>
</template>
<script>
import BottomAddressBar from "../components/common/BottomAddressBar.vue";
import LeftAside from "../components/common/LeftAside.vue";
import NaviToggleBtn from "../components/common/NaviToggleBtn";

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
