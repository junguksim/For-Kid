<template>
  <b-form id="registReplyForm">
    <span>
      <b-form-textarea
        id="replyTextarea"
        v-model="replyContent"
        placeholder="답글을 남겨주세요!"
        rows="3"
        max-rows="6"
      >
      </b-form-textarea>
      <b-button id="replyRegistBtn" @click="registReply">등록</b-button>
    </span>
  </b-form>
</template>

<script>
import http from "@/util/http-common.js";
import { mapGetters } from "vuex";

export default {
  data() {
    return {
      replyContent: "",
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
  props: ["boardNo"],
  methods: {
    registReply() {
      console.log("regist : " + this.boardNo);
      http
        .post("/reply", {
          boardNo: this.boardNo,
          content: this.replyContent,
          writerId: this.userInfo.userId,
          writerName: this.userInfo.userName,
        })
        .then(({ data }) => {
          if (data.status === "OK") {
            alert("답글 등록 성공!");
            this.$router.go(this.$router.currentRoute);
          } else {
            alert("답글 등록 실패!");
            this.$router.go(this.$router.currentRoute);
          }
        })
        .catch(({ data }) => {
          alert(data.status);
          alert("답글 등록 실패!");
        });
    },
  },
};
</script>
<style>
#registReplyForm {
  width: 80%;
  margin: 30px auto;
  padding: 10px;
  vertical-align: middle;
}
#replyTextarea {
  width: 85%;
  display: inline;
  vertical-align: middle;
  margin-right: 2px;
}
#replyRegistBtn {
  width: 7%;
  height: 86px;
  display: inline;
  vertical-align: middle;
  margin-bottom: 10px;
  background-color: #4d4035;
}
</style>
