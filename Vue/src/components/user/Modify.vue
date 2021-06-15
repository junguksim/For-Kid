<template>
  <div>
    <sign-up-form type="modify"></sign-up-form>
  </div>
</template>
<script>
import SignUpForm from "@/components/user/include/SignUpForm.vue";

export default {
  name: "usermodify",
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
<style scoped>
#modifyForm {
  margin: 0 auto;
}
</style>
