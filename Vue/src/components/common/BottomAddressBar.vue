<template>
  <div id="bottomAddressBar">
    <img src="../../assets/navigation.png" alt="네비게이션 아이콘" />
    {{ centerAddress }}
  </div>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  computed: {
    ...mapGetters(["centerAddress"]),
  },
};
</script>

<style>
#bottomAddressBar {
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  width: 200px;
  margin: auto;
  margin-bottom: 20px;
  border: 1px solid gray;
  font-size: 13px;
  line-height: 30px;
  text-align: center;
  border-radius: 60px;
  background-color: white;
}
</style>
