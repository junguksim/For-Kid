<template>
  <div id="commentList">
    <div v-if="commentList[aptNo] != undefined && commentList[aptNo].length">
      <comment-item
        v-for="(comment, index) in commentList[aptNo]"
        :key="index"
        :comment="comment"
      />
    </div>
    <div id="no-comment" v-else>아직 의견이 없어요!</div>
  </div>
</template>

<script>
//import http from "@/util/http-common.js"
import { mapGetters } from "vuex";
import CommentItem from "./CommentItem.vue";

export default {
  props: ["aptNo"],
  components: {
    CommentItem,
  },
  computed: {
    ...mapGetters(["commentList"]),
  },
  beforeMount() {
    console.log(this.commentList[this.aptNo]);
  },
  //   mounted() {
  //     console.log(`boardNo : ${this.boardNo}`);
  //     http.get(`/reply/${this.boardNo}/all`).then(({ data }) => {
  //       console.log(data.data);
  //       this.replies = data.data;
  //     });
  //   },
};
</script>
<style scoped>
#commentList {
  height: 80%;
  overflow: scroll;
  padding: 2px;
}
#commentList::-webkit-scrollbar {
  display: none;
}
#no-comment {
  position: absolute;
  top: 40%;
  left: 30%;
}
</style>
