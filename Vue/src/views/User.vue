<template>
  <div id="user">
    <router-view></router-view>
  </div>
</template>
<script>
export default {
  beforeMount() {
    this.$store.dispatch("setShowMap", false);
    this.$store.dispatch("setShowSide", true);
    this.$store.dispatch("setAreaDropDownShown", false);
  },
};
</script>
<style>
#user {
  width: 80%;
  margin-left: 23%;
}
</style>
