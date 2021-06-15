<template>
  <div id="schoolTitle">
    <span class="classification">{{ schoolInfo.classification }}</span>
    <span class="school-name-white">{{ schoolInfo.name }}</span
    ><br />
    <span class="small-info">{{ schoolInfo.category }}</span
    ><br />
    <span class="small-info">{{ schoolInfo.address }}</span>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  computed: {
    ...mapGetters(["schoolInfo"]),
  },
};
</script>

<style scoped>
#schoolTitle {
  background-color: #4cae71;
  color: white;
  text-align: center;
  padding: 5%;
}
.classification {
  padding: 2%;
  height: 15px;
  font-size: 13px;
  background-color: gray;
  border-radius: 5rem;
}
.school-name-white {
  font-size: 24px;
  margin-top: 5px;
  margin-left: 2%;
}
.small-info {
  font-size: 13px;
  line-height: 13px;
}
</style>
