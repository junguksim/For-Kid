<template>
  <div id="apartmentDealInfo">
    <table id="dealList">
      <tr>
        <th>구분</th>
        <th>거래가</th>
        <th>거래일</th>
        <th>층</th>
      </tr>
      <!-- 매매 정보 -->
      <div
        v-for="(saleItem, index) in dealList"
        v-bind="dealList"
        :key="index"
        class="sale-info"
      >
        <tr v-if="index < showIdx">
          <td>{{ dealType }}</td>
          <td v-if="dealType == '매매'">
            {{ saleItem.dealAmount | convertPrice }}억
          </td>
          <td v-else>{{ saleItem.guarantyMoney | convertPrice }}억</td>
          <td>{{ convertDate(saleItem.dealYear, saleItem.dealMonth) }}</td>
          <td>{{ saleItem.floor }}</td>
        </tr>
      </div>
    </table>
    <span v-if="showIdx < maxSize" id="more" @click="addShowIdx">더보기</span>
    <span v-if="showIdx > 5" id="less" @click="minusShowIdx">접기</span>
  </div>
</template>
<script>
import { mapGetters } from "vuex";
export default {
  props: ["item", "dealType"],
  data() {
    return {
      dealList: [],
      showIdx: 5,
      maxSize: 0,
    };
  },
  computed: {
    ...mapGetters(["aptDealInfoList"]),
  },
  methods: {
    convertDate(dealYear, dealMonth) {
      return dealYear + "-" + dealMonth;
    },
    addShowIdx() {
      if (this.showIdx + 5 <= this.maxSize) {
        this.showIdx += 5;
      } else {
        this.showIdx = this.maxSize;
      }
      console.log(this.showIdx);
    },
    minusShowIdx() {
      if (this.showIdx - 5 >= 5) {
        this.showIdx -= 5;
      } else {
        this.showIdx = 5;
      }
    },
  },
  watch: {
    aptDealInfoList: {
      handler(val) {
        this.showIdx = 5;
        if (this.dealType == "매매") {
          this.dealList = val.saleList;
        } else {
          this.dealList = val.rentList;
        }
        if (this.dealList) this.maxSize = this.dealList.length;
        else this.maxSize = 0;
      },
    },
    dealType: {
      handler() {
        console.log("변경된 dealType!");
        this.showIdx = 5;
        if (this.dealType == "매매") {
          this.dealList = this.aptDealInfoList.saleList;
        } else {
          this.dealList = this.aptDealInfoList.rentList;
        }
        console.log("변경된 dealList " + this.dealList);
        if (this.dealList) this.maxSize = this.dealList.length;
        else this.maxSize = 0;
      },
    },
  },
};
</script>
<style scoped>
#apartmentDealInfo {
  width: inherit;
  cursor: pointer !important;
  margin: 0 auto;
  text-align: center;
  height: max-content;
}
#dealList {
  width: 100%;
  height: 30%;
  table-layout: fixed;
  margin: 0 auto;
  margin-bottom: 5px;
}
#more {
  border: solid 2px #4d55b2;
  border-radius: 1rem;
  padding: 2px;
  padding-left: 10px;
  padding-right: 10px;
}
#less {
  border: solid 2px #4d55b2;
  border-radius: 1rem;
  padding: 2px;
  padding-left: 10px;
  padding-right: 10px;
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

.small-info {
  font-size: 13px;
  line-height: 13px;
}

.indented {
  padding-left: 0.5em;
}

.sale-info {
  display: contents;
  text-align: center;
  height: 10px;
}
</style>
