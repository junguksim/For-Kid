<template>
  <div id="list">
    <div id="searchBar">
      <input
        type="text"
        id="keyword"
        placeholder="검색할 키워드를 입력하세요"
        @keyup.enter="search"
      />
      <b-button id="searchBtn" @click="search">검색</b-button>
    </div>
    <b-table-simple id="list-table">
      <b-thead>
        <b-tr>
          <b-th>번호</b-th>
          <b-th>제목</b-th>
          <b-th>작성자</b-th>
          <b-th>등록일</b-th>
        </b-tr>
      </b-thead>
      <b-tbody>
        <b-tr v-for="item in items" :key="item.no">
          <b-td v-if="!word || item.title.includes(word)">{{ item.no }}</b-td>
          <b-td v-if="!word || item.title.includes(word)"
            ><a @click="$router.push(`/post/detail/` + item.no)">{{
              item.title
            }}</a></b-td
          >
          <b-td v-if="!word || item.title.includes(word)">{{
            item.writerName
          }}</b-td>
          <b-td v-if="!word || item.title.includes(word)">{{
            item.registDate
          }}</b-td>
        </b-tr>
      </b-tbody>
    </b-table-simple>
    <b-button id="moveRegistBtn" @click="moveRegist">질문 등록</b-button>
    <div id="pagination">
      <b-pagination
        v-model="currentPage"
        :total-rows="rows"
        :per-page="perPage"
        aria-controls="my-table"
      ></b-pagination>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { mapGetters } from "vuex";

export default {
  data() {
    return {
      fields: [
        { key: "no", label: "번호" },
        { key: "title", label: "제목" },
        { key: "writerName", label: "작성자" },
        { key: "registDate", label: "등록일" },
      ],
      items: [],
      word: "",
    };
  },
  computed: {
    ...mapGetters(["userInfo"]),
  },
  beforeMount() {
    this.$store.dispatch("setShowMap", false);
    this.$store.dispatch("setShowSide", true);
    this.$store.dispatch("setAreaDropDownShown", false);
  },
  created() {
    axios.get("http://localhost/board/all").then(({ data }) => {
      this.items = data.data;
      console.log(data.data);
    });
  },
  methods: {
    search() {
      this.word = document.querySelector("#keyword").value;
    },
    moveRegist() {
      if (this.userInfo.userId) this.$router.push("/post/regist");
      else alert("로그인이 필요합니다!");
    },
  },
};
</script>

<style>
thead {
  background-color: #faebad;
}
#list {
  position: relative;
  width: 80%;
  margin: 0 auto;
  margin-top: 3%;
  text-align: center;
}
#list-table {
  width: 100%;
  text-align: center;
  margin: 0 auto;
}
#searchBar {
  margin-top: 20px;
  margin-bottom: 20px;
  border: none;
  text-align: center;
}
#keyword {
  width: 30%;
  margin-right: 2px;
  height: 7%;
}
input {
  height: 6% !important;
}
.btn {
  font-size: 15px;
  padding: 5px !important;
  margin-bottom: 3px !important;
  border: none;
}
#searchBtn {
  width: 6%;
  height: 5%;
  background-color: #605042;
}
#moveRegistBtn {
  position: relative;
  margin-left: 90%;
  width: 10%;
  height: 5%;
  margin-top: 3%;
  background-color: #fed363;
  color: #605042;
  font-weight: 600;
}
.pagination {
  text-align: center;
  display: inline-flex;
}

.page-item .page-link {
  width: 40px;
  color: #605042;
}
</style>
