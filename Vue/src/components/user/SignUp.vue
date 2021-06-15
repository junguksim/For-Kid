<template>
  <div id="userForm">
    <sign-up-form type="create"></sign-up-form>
  </div>
</template>

<script>
import SignUpForm from "@/components/user/include/SignUpForm.vue";

export default {
  name: "signup",
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
#userForm {
  margin: 0 auto;
}
</style>
