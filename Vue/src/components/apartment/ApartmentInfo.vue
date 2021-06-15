<template>
  <div id="apartmentInfo" @click="goDetail">
    <span class="apt-name-color">{{ aptInfo.aptName }}</span
    ><br />
    <span class="small-info">{{ aptInfo.area | convertArea }}평</span>
    <span class="small-info indented">{{
      aptInfo.type == "1" ? "매매" : "전월세"
    }}</span>
    <span class="small-info indented"
      >{{ aptInfo.dealAmount | convertPrice }}억</span
    >
    <span class="small-info indented">{{ aptInfo.dong }}</span
    ><br />
  </div>
</template>
<script>
import aptModule from "@/modules/aptModule.js";
export default {
  props: ["aptInfo"],
  methods: {
    async goDetail() {
      console.log(this.aptInfo);
      await this.$store.dispatch("setSearchItemStore", {
        description: this.aptInfo.aptName,
        lat: this.aptInfo.lat,
        lng: this.aptInfo.lng,
      });
      await this.$store.dispatch("setSearchedApt", this.aptInfo);
      const { aptNo, guguncode, aptName, lat, lng } = this.aptInfo;
      const aptDealList = await aptModule.getApartmentDealList(aptNo);
      const aptInfoByArea = await aptModule.getAptInfoByArea(
        guguncode,
        aptName
      );
      const around = await aptModule.getAround(lat, lng);
      const comment = await aptModule.getComment(aptNo);
      await this.$store.dispatch("setAround", around);
      await this.$store.dispatch("setAptDealInfoList", aptDealList);
      await this.$store.dispatch("setAptInfoByArea", aptInfoByArea);
      await this.$store.dispatch("setComment", comment);
      await this.$router.push({ name: "deal-list", params: { aptNo } });
    },
  },
};
</script>
<style scoped>
#apartmentInfo {
  width: 100%;
  padding: 3%;
  cursor: pointer !important;
}
.apt-name-color {
  font-size: 16px;
  line-height: 15px;
  color: #605042;
}

.small-info {
  font-size: 13px;
  line-height: 13px;
}

.indented {
  padding-left: 0.5em;
}
hr {
  margin: 0;
  color: gray;
}
</style>
