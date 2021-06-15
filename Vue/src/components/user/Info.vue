<template>
  <div id="info">
    <sign-up-form type="info"></sign-up-form>
  </div>
</template>
<script>
import SignUpForm from "@/components/user/include/SignUpForm.vue";

export default {
  name: "userinfo",
  components: {
    SignUpForm,
  },
  beforeMount() {
    this.$store.dispatch("setShowMap", false);
    this.$store.dispatch("setShowSide", true);
    this.$store.dispatch("setAreaDropDownShown", false);
  },
};
</script>
<style>
#info {
  margin: 0 auto;
}
</style>
