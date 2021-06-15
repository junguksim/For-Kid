<template>
  <div id="post">
    <div id="post-title">
      <router-link to="/post/list" class="link-color">QnA 게시판</router-link>
    </div>
    <hr />
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
#post {
  width: 80%;
  text-align: center;
  margin-left: 23%;
}
#post-title {
  margin-top: 3%;
  margin-bottom: 3%;
}
.link-color {
  color: black;
  font-size: 30px;
  font-weight: 500;
  text-decoration: none;
  margin-top: 10px;
}
</style>
