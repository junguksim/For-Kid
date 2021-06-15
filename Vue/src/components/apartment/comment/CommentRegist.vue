<template>
  <b-form id="registReplyForm">
    <span>
      <b-form-textarea
        id="replyTextarea"
        v-model="commentContent"
        placeholder="추억을 남겨주세요!"
        rows="3"
        max-rows="6"
      >
      </b-form-textarea>
      <b-button id="replyRegistBtn" @click="registReply">등록</b-button>
    </span>
  </b-form>
</template>

<script>
//import http from "@/util/http-common.js";
import { mapGetters } from "vuex";

export default {
  data() {
    return {
      commentContent: "",
    };
  },
  computed: {
    ...mapGetters(["commentList", "userInfo"]),
  },
  props: ["aptNo"],
  methods: {
    registReply() {
      if (this.userInfo == null || !this.userInfo.userId) {
        alert("로그인 이후 이용하실 수 있습니다.");
        return;
      }
      this.$store.dispatch("addCommentList", {
        aptNo: this.aptNo,
        userId: this.userInfo.userId,
        commentContent: this.commentContent,
        registDate: new Date().toString,
      });
      this.commentContent = "";
    },
  },
};
</script>
<style scoped>
#registReplyForm {
  width: 80%;
  padding: 10px;
  position: absolute;
  bottom: 0;
  text-align: center;
  margin-right: 15px;
}
#replyTextarea {
  width: 100%;
  display: inline;
}
#replyTextarea::-webkit-scrollbar {
  display: none;
}
#replyRegistBtn {
  width: 30%;
  height: 40px;
  display: inline;
  vertical-align: middle;
  margin-bottom: 10px;
  background-color: #4d4035;
}
</style>
