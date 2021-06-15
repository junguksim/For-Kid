<template>
  <div v-if="replies.length">
    <reply-item v-for="(reply, index) in replies" :key="index" :reply="reply" />
  </div>
  <div v-else>아직 답변이 없어요!</div>
</template>

<script>
import http from "@/util/http-common.js";
import ReplyItem from "@/components/post/reply/ReplyItem.vue";

export default {
  props: ["boardNo"],
  components: {
    ReplyItem,
  },
  data() {
    return {
      replies: [],
    };
  },
  beforeMount() {
    this.$store.dispatch("setShowMap", false);
    this.$store.dispatch("setShowSide", true);
    this.$store.dispatch("setAreaDropDownShown", false);
  },
  mounted() {
    console.log(`boardNo : ${this.boardNo}`);
    http.get(`/reply/${this.boardNo}/all`).then(({ data }) => {
      console.log(data.data);
      this.replies = data.data;
    });
  },
};
</script>
<style></style>
