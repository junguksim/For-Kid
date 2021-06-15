<template>
  <div id="replyItem">
    <p id="writerName">
      {{ comment.userId }}<span>{{ comment.registDate }}</span>
    </p>
    <!-- <p id="registDate">{{ registDate }}</p>
    <div v-if="isUpdating">
      <b-form-textarea v-model="updateContent" rows="3" max-rows="6"></b-form-textarea>
    </div>
    <div v-else> -->
    <p id="content">{{ comment.commentContent }}</p>
    <!-- </div> -->
    <!-- <div id="updateDeleteDiv" v-if="userInfo.userId === writerId">
      <b-button @click="deleteReply">삭제</b-button>
      <b-button v-if="!isUpdating" @click="setUpdating" style="background-color: #605042"
        >수정</b-button
      >
      <b-button v-if="isUpdating" @click="updateReply" style="background-color: #605042"
        >등록</b-button
      >
    </div> -->
  </div>
</template>

<script>
//import http from "@/util/http-common.js";
import { mapGetters } from "vuex";

export default {
  computed: {
    ...mapGetters(["commentList"]),
  },
  props: ["comment"],
  //   methods: {
  //     deleteReply() {
  //       http.delete(`/reply/${this.replyNo}`).then(({ data }) => {
  //         if (data.status === "OK") {
  //           alert("답글 삭제 성공!");
  //           this.$router.go(this.$router.currentRoute);
  //         } else {
  //           alert("답글 삭제 실패!");
  //         }
  //       });
  //     },
  //     setUpdating() {
  //       this.isUpdating = !this.isUpdating;
  //     },
  //     updateReply() {
  //       http
  //         .put(`/reply`, {
  //           replyNo: this.replyNo,
  //           boardNo: this.boardNo,
  //           content: this.updateContent,
  //           writerId: this.userInfo.userId,
  //           writerName: this.userInfo.userName,
  //         })
  //         .then(({ data }) => {
  //           if (data.status === "OK") {
  //             alert("답글 수정 성공!");
  //             this.$router.go(this.$router.currentRoute);
  //           } else {
  //             alert("답글 수정 실패!");
  //           }
  //         });
  //     },
  //   },
};
</script>
<style scoped>
#replyItem {
  width: 100%;
  background-color: white;
  margin: 30px auto;
  border-radius: 3px;
  border: 1px solid;
  border-color: gray;
  padding: 18px;
}

#writerName {
  font-size: 13px;
  font-weight: 900;
  text-align: left;
}

#registDate {
  text-align: left;
}

#content {
  height: 3%;
  font-size: 15px;
  overflow: visible;
}
#updateDeleteDiv {
  text-align: right;
}
button {
  margin-top: 10px;
}
</style>
