<template>
  <div id="aptComment">
    <button
      @click="close"
      id="closeBtn"
      type="button"
      aria-label="Close"
      class="close text-dark"
    >
      <svg
        viewBox="0 0 16 16"
        width="1.8em"
        height="1.8em"
        focusable="false"
        role="img"
        aria-label="x"
        xmlns="http://www.w3.org/2000/svg"
        fill="currentColor"
        class="bi-x b-icon bi"
      >
        <g>
          <path
            d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"
          ></path>
        </g>
      </svg>
    </button>
    <comment-list :aptNo="searchedApt.aptNo" />
    <comment-regist :aptNo="searchedApt.aptNo" />
  </div>
</template>
<script>
import { mapGetters } from "vuex";
import CommentRegist from "@/components/apartment/comment/CommentRegist.vue";
import CommentList from "./comment/CommentList.vue";
export default {
  components: { CommentRegist, CommentList },
  data() {
    return {};
  },
  computed: {
    ...mapGetters(["searchedApt"]),
  },
  created() {},
  methods: {
    close() {
      this.$store.dispatch("setAreaComment", false);
    },
  },
};
</script>
<style scoped>
#aptComment {
  margin-left: 23%;
  width: 23%;
  height: 100%;
  z-index: 1;
  position: absolute;
  background-color: white;
  top: 0;
  padding: 2%;
  max-height: 100%;
  overflow: scroll;
  box-shadow: 0 0.5rem 0.5rem rgba(0, 0, 0, 0.15);
}
#aptComment::-webkit-scrollbar {
  display: none;
}
</style>
