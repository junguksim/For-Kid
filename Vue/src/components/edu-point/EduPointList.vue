<template>
  <div id="eduPointList">
    <table id="eduAptList">
      <tr>
        <th class="apt-name">아파트이름</th>
        <th>거래가</th>
        <th>평수</th>
        <th>교육지수</th>
      </tr>
      <!-- 매매 정보 -->
      <tr v-for="(eduApt, index) in eduAptList" :key="index">
        <td>{{ eduApt.aptName }}</td>
        <td>{{ eduApt.dealAmount | convertPrice }} 억</td>
        <td>{{ eduApt.area | convertArea }} 평</td>
        <td>{{ eduApt.eduPoint }}</td>
      </tr>
      <!-- {{
        eduAptList
      }} -->
    </table>
  </div>
</template>
<script>
import aptModule from "../../modules/aptModule";

export default {
  async created() {
    await this.setEduPoint();
  },
  beforeMount() {
    this.setShowComponents();
  },
  methods: {
    goBack() {
      this.$router.go(-1);
    },
    setShowComponents() {
      this.$store.dispatch("setShowMap", false);
      this.$store.dispatch("setShowSide", true);
      this.$store.dispatch("setAreaDropDownShown", false);
    },

    async setEduPoint() {
      const { lat, lng } = await aptModule.getLatLng(this.guguncode, this.dong);
      let { aptList } = await aptModule.getApartmentList(this.dong, lat, lng, 4);
      for (let apt of aptList) {
        const { lat, lng } = apt;
        const schoolList = await aptModule.getSchoolList(this.guguncode, lat, lng, 3);
        let schoolPoint = schoolList == null || schoolList == undefined ? 0 : schoolList.length;
        const { kindergarden } = await aptModule.getAround(lat, lng, 3);
        let otherEduPoint =
          kindergarden == null || kindergarden == undefined ? 0 : kindergarden.length;
        apt["eduPoint"] = schoolPoint + otherEduPoint;
      }
      aptList.sort(function (a1, a2) {
        return a1.eduPoint < a2.eduPoint ? 1 : -1;
      });
      this.eduAptList = aptList;
    },
  },
  props: ["sido", "gugun", "dong"],
  data() {
    return {
      guguncode: this.sido + "" + this.gugun,
      eduAptList: [],
    };
  },
};
</script>
<style scoped>
#eduPointList {
  padding: 30px;
  width: 100%;
  text-align: center;
  margin: 0 auto;
  margin-top: 5%;
}
#backBtn {
  cursor: pointer;
  float: left;
}
.small-size-font {
  font-size: 14px;
}
#eduAptList {
  width: 100%;
  height: 30%;
  table-layout: fixed;
  margin: 0 auto;
  margin-bottom: 5px;
}
tr {
  height: 10px;
}
th {
  text-align: center;
  background-color: #d3d5ec;
  border: solid 1px white;
}
td {
  text-align: center;
}
.apt-name {
  width: 50%;
}
</style>
