<template>
  <div id="regist">
    <b-form id="regist-form">
      <b-form-group id="input-group-1" label="Title" label-for="input-1">
        <b-form-input
          id="input-1"
          type="text"
          v-model="question.title"
          ref="title"
          placeholder="제목"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-2" label="Context" label-for="input-2">
        <b-form-textarea
          id="input-2"
          v-model="question.content"
          ref="content"
          placeholder="내용"
          rows="3"
          required
        ></b-form-textarea>
      </b-form-group>

      <b-button type="reset">초기화</b-button>
      <b-button @click="updatePost" id="modifyBtn">수정</b-button>
    </b-form>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      no: 0,
      question: {},
    };
  },
  beforeMount() {
    this.no = this.$route.params.no;
    this.$store.dispatch("setShowMap", false);
    this.$store.dispatch("setShowSide", true);
    this.$store.dispatch("setAreaDropDownShown", false);
    axios.get("http://localhost/board/" + this.no).then(({ data }) => {
      this.question = data.data;
      console.log(this.question);
    });
  },
  methods: {
    inputCheck() {
      let msg = "";
      let err = true;

      console.log("title" + this.title);
      console.log("content" + this.content);

      if (!this.question.title) {
        (msg = "제목을 입력해주세요"), (err = false), this.$refs.title.focus();
      } else if (!this.question.content) {
        (msg = "제목을 입력해주세요"), (err = false), this.$refs.content.focus();
      }

      if (err) this.registPost();
      else alert(msg);
    },

    updatePost() {
      axios
        .put("http://localhost/board/" + this.no, {
          title: this.question.title,
          content: this.question.content,
          writerId: this.question.writerId,
          writerName: this.question.writerName,
        })
        .then(({ data }) => {
          this.items = data.data;
          alert(data.msg);

          this.moveList();
        });
    },
    moveList() {
      this.$router.push("/post/detail/" + this.no);
    },
  },
};
</script>

<style>
#regist {
  text-align: center;
  margin-top: 10px;
  margin-bottom: 10px;
}
#regist-form {
  width: 60%;
  margin: 0 auto;
}
.form-group {
  margin-bottom: 20px;
}
#modifyBtn {
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
