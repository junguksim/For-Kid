<template>
  <div id="naviToggleBtn">
    <b-Button @click="toggleNavi">MENU</b-Button>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  computed: {
    ...mapGetters(["showSide"]),
  },
  methods: {
    toggleNavi() {
      console.log(this.showSide + "->" + !this.showSide);
      this.$store.dispatch("setShowSide", !this.showSide);
    },
  },
};
</script>

<style scoped>
#naviToggleBtn {
  position: absolute;
  bottom: 0;
  right: 0;
  width: 100px;
  margin: auto;
  margin-bottom: 20px;
  font-size: 13px;
  line-height: 30px;
  text-align: center;
  border-radius: 60px;
  z-index: 1;
}
</style>
