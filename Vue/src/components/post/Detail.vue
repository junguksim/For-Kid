<template>
  <form>
    <div id="detail">
      <b-table-simple id="detail-table">
        <b-tr>
          <b-th>제목</b-th>
          <b-td>{{ question.title }}</b-td>
          <b-th>조회수</b-th>
          <b-td>{{ question.viewCount }}</b-td>
        </b-tr>
        <b-tr>
          <b-th>작성자</b-th>
          <b-td>{{ question.writerName }}</b-td>
          <b-th>등록일</b-th>
          <b-td>{{ question.registDate }}</b-td>
        </b-tr>
        <b-tr>
          <b-th>내용</b-th>
          <b-td colspan="3" id="content">{{ question.content }}</b-td>
        </b-tr>
      </b-table-simple>

      <div id="button-group" v-if="userInfo.userId === question.writerId">
        <b-button @click="removePost">삭제</b-button>
        <b-button @click="updatePost" id="moveModifyBtn">수정</b-button>
      </div>
      <template id="replyGroup" v-if="isReplyReady">
        <reply-list :board-no="question.no" />
        <div v-if="userInfo.userId">
          <reply-regist :board-no="question.no" />
        </div>
        <div v-else>로그인 후 답변을 달아보세요!</div>
      </template>
      <div v-else>서버 응답 중입니다</div>
    </div>
  </form>
</template>

<script>
import axios from "axios";
import { mapGetters } from "vuex";
import ReplyRegist from "@/components/post/reply/ReplyRegist.vue";
import ReplyList from "@/components/post/reply/ReplyList.vue";

export default {
  components: {
    ReplyRegist,
    ReplyList,
  },
  data() {
    return {
      no: 0,
      question: Object,
      isReplyReady: false,
    };
  },
  computed: {
    ...mapGetters(["userInfo"]),
  },
  created() {
    this.no = this.$route.params.no;
    setTimeout(() => {
      this.isReplyReady = true;
    }, 500);
    axios.get("http://localhost/board/" + this.no).then(({ data }) => {
      this.question = data.data;
      console.log(data.data);
    });
  },
  beforeMount() {
    this.$store.dispatch("setShowMap", false);
    this.$store.dispatch("setShowSide", true);
    this.$store.dispatch("setAreaDropDownShown", false);
  },
  methods: {
    removePost() {
      axios.delete("http://localhost/board/" + this.no).then(({ data }) => {
        alert(data.msg);
        this.$router.push("/post/list");
      });
    },
    updatePost() {
      this.$router.push("/post/update/" + this.no);
    },
  },
};
</script>

<style>
#detail-table {
  border: 1px solid;
  width: 80%;
  margin: 0 auto;
}
#detail-table > tr > th,
td {
  border: 1px solid #ced4da;
  height: 40px;
}
#detail-table > tr > th {
  width: 10%;
  background-color: #faebad;
}
#content {
  height: 100px;
  text-align: left;
  padding: 10px;
  overflow: auto;
}
#button-group {
  width: 80%;
  margin: 0 auto;
  margin-top: 15px;
  text-align: right;
}

#replyGroup {
  width: 80%;
}
#moveModifyBtn {
  background-color: #fed363;
  color: #605042;
  font-weight: 500;
}
.btn {
  margin-left: 2px;
  margin-right: 2px;
  border: none;
}
</style>
