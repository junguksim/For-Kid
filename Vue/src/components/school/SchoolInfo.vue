<template>
  <div id="schoolInfo">
    <div>
      <img src="../../assets/person.png" />
      <a :href="schoolInfo.url" class="link">학교 홈페이지 바로가기</a><br />
      <img src="../../assets/person.png" />
      <a href="#" class="link">{{ schoolInfo.phoneNum }}</a>
      <hr />
    </div>
    <div>
      구분 : {{ schoolInfo.coed }} <br />설립일 : {{ schoolInfo.anniversary }} <br />교육청 :
      {{ schoolInfo.eduOffice }}
    </div>
    <hr />
    <table id="classTable">
      <tr>
        <th>구분</th>
        <td>{{ schoolInfo.coed }}</td>
      </tr>
      <tr>
        <th>학급 수</th>
        <td>{{ schoolInfo.classCount }}</td>
      </tr>
      <tr>
        <th>학생 수</th>
        <td>{{ schoolInfo.studentCount }}명</td>
      </tr>
      <tr>
        <th>학급당 학생 수</th>
        <td>{{ schoolInfo.studentPerClass }}명</td>
      </tr>
    </table>
    <div class="classChart">
      <chart :chart-data="chartDataCollection"></chart>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import Chart from "./chart";

export default {
  components: { Chart },
  computed: {
    ...mapGetters(["schoolInfo"]),
  },
  data() {
    return {
      chartDataCollection: {},
    };
  },
  methods: {
    setChartData() {
      let { classCount } = this.schoolInfo;
      let normal = parseInt(classCount.split("(")[0]);
      let special = parseInt(classCount.split("(")[1].replace(/[^0-9]/g, ""));
      this.chartDataCollection = {
        labels: ["일반학급", "특수학급"],
        datasets: [
          {
            label: "학급 구분",
            backgroundColor: ["#FAEBAD", "#F8A934"],
            data: [normal, special],
          },
        ],
      };
    },
  },
  watch: {
    schoolInfo: {
      immediate: true,
      handler() {
        this.setChartData();
      },
    },
  },
};
</script>

<style scoped>
#schoolInfo {
  text-align: center;
  padding: 5%;
}
#classTable {
  width: 100%;
  text-align: left;
  margin-bottom: 5%;
}
#classTable > tr > th {
  width: 50%;
  background-color: #9ed5a7;
  border-bottom: solid 1px;
  border-color: white;
  font-weight: 500;
}
#classTable > tr > td {
  text-align: center;
}
.link {
  color: black;
  text-decoration: none;
  font-weight: 300;
}
.classChart {
  margin: 0 auto;
  width: 70%;
}
</style>
