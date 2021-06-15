<template>
  <div id="regist">
    <b-form id="regist-form">
      <b-form-group id="input-group-1" label="Title" label-for="input-1">
        <b-form-input
          id="input-1"
          type="text"
          v-model="title"
          ref="title"
          placeholder="제목"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-2" label="Context" label-for="input-2">
        <b-form-textarea
          id="input-2"
          v-model="content"
          ref="content"
          placeholder="내용"
          rows="5"
          required
        ></b-form-textarea>
      </b-form-group>

      <b-button type="reset">초기화</b-button>
      <b-button type="button" @click="inputCheck" id="registBtn">등록</b-button>
    </b-form>
  </div>
</template>

<script>
import axios from "axios";
import { mapGetters } from "vuex";

export default {
  data() {
    return {
      title: "",
      content: "",
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
  methods: {
    inputCheck() {
      let msg = "";
      let err = true;

      console.log("title" + this.title);
      console.log("content" + this.content);

      if (!this.title) {
        (msg = "제목을 입력해주세요"), (err = false), this.$refs.title.focus();
      } else if (!this.content) {
        (msg = "제목을 입력해주세요"), (err = false), this.$refs.content.focus();
      }

      if (err) this.registPost();
      else alert(msg);
    },

    registPost() {
      axios
        .post("http://localhost/board", {
          title: this.title,
          content: this.content,
          writerId: this.userInfo.userId,
          writerName: this.userInfo.userName,
        })
        .then(({ data }) => {
          this.items = data;
          console.log(data);
          alert("등록이 완료되었습니다!");
          this.moveList();
        });
    },
    moveList() {
      this.$router.push("/post/list");
    },
  },
};
</script>

<style>
#regist {
  text-align: center;
  margin-top: 3%;
}
#regist-form {
  width: 60%;
  margin: 0 auto;
}
.btn {
  border: none;
}
#registBtn {
  background-color: #fed363;
  color: #605042;
}
.form-group {
  margin-bottom: 20px;
}
.btn {
  margin-left: 2px;
  margin-right: 2px;
}
</style>
