<template>
  <div id="signUpDiv">
    <div v-if="type === 'create'">
      <h3>회원가입</h3>
    </div>
    <div v-else-if="type === 'modify'">
      <h3>회원정보수정</h3>
    </div>
    <div v-else>
      <h3>회원정보</h3>
    </div>
    <form id="signupform" method="post" aciton="">
      <div class="input-div" v-if="type === 'create'">
        <label for="inputId" class="inputLabel">사용자 ID (* 20자 이내)</label
        ><br />
        <input
          type="text"
          v-model="inputUserId"
          placeholder="ID"
          maxlength="20"
          :disabled="type === 'info'"
        />
      </div>
      <div class="input-div" v-if="type === 'create'">
        <label for="inputPassword" class="inputLabel">암호 (* 20자 이내)</label
        ><br />
        <input
          type="password"
          v-model="inputUserPwd"
          placeholder="비밀번호"
          maxlength="20"
          :disabled="type === 'info'"
        />
      </div>
      <div class="input-div">
        <label for="inputUserName" class="inputLabel">이름 (* 10자 이내)</label
        ><br />
        <input
          type="text"
          v-model="inputUserName"
          placeholder="이름"
          maxlength="10"
          :disabled="type === 'info'"
        />
      </div>
      <div class="input-div">
        <label for="inputCellPhone" class="inputLabel"
          >휴대폰 번호 (* 20자 이내)</label
        ><br />
        <input
          type="text"
          v-model="inputPhoneNum"
          placeholder="01012345678"
          maxlength="20"
          :disabled="type === 'info'"
        />
      </div>
      <div class="input-div">
        <label for="inputemail" class="inputLabel">E-mail 주소 (*)</label><br />
        <input
          type="text"
          v-model="inputEmail"
          placeholder="ssafy@ssafy.com"
          :disabled="type === 'info'"
        />
      </div>
      <button
        v-if="type === 'create'"
        type="button"
        class="btn btn-warning btn-block"
        @click="signup"
      >
        가입하기
      </button>
      <b-button
        v-if="type === 'info'"
        type="button"
        class="btn btn-info btn-block"
        to="/user/modify"
      >
        수정하기
      </b-button>
      <b-button
        v-if="type === 'modify'"
        type="button"
        class="btn btn-info btn-block"
        @click="modify"
      >
        수정하기
      </b-button>
    </form>
  </div>
</template>
<script>
import http from "@/util/http-common.js";
import { mapGetters } from "vuex";

export default {
  name: "signupform",
  props: {
    type: { type: String },
  },
  data() {
    return {
      inputUserId: "",
      inputUserPwd: "",
      inputUserName: "",
      inputPhoneNum: "",
      inputEmail: "",
    };
  },
  computed: {
    ...mapGetters(["userInfo"]),
  },
  created() {
    if (this.type === "modify" || this.type === "info") {
      this.inputUserId = this.userInfo.userId;
      this.inputUserPwd = this.userInfo.userPwd;
      this.inputUserName = this.userInfo.userName;
      this.inputPhoneNum = this.userInfo.phoneNum;
      this.inputEmail = this.userInfo.email;
    }
  },
  beforeMount() {
    this.$store.dispatch("setShowMap", false);
    this.$store.dispatch("setShowSide", true);
    this.$store.dispatch("setAreaDropDownShown", false);
    if (
      (this.type === "info" || this.type === "modify") &&
      !this.userInfo.userId
    ) {
      alert("로그인 후 이용해주세요.");
      this.moveToMain();
    }
  },
  methods: {
    checkValidate() {
      return (
        this.inputUserId.length > 0 ||
        this.inputUserPwd.length > 0 ||
        this.inputUserName.length > 0 ||
        this.inputPhoneNum.length > 0 ||
        this.inputEmail.length > 0
      );
    },
    signup() {
      if (this.checkValidate()) {
        http
          .post("/member/", {
            userId: this.inputUserId,
            userPwd: this.inputUserPwd,
            userName: this.inputUserName,
            phoneNum: this.inputPhoneNum,
            email: this.inputEmail,
          })
          .then(({ data }) => {
            console.log(data);
            if (data.status === "OK") {
              alert("회원가입에 성공했습니다!");
              this.moveToMain();
            } else {
              alert("서버에 오류가 있습니다. 잠시 후 다시 시도해주세요.");
            }
          });
      } else {
        alert("정보를 다시 확인해주세요.");
        console.log(this.$data);
      }
    },
    moveToMain() {
      this.$router.push("/");
    },
    modify() {
      if (this.checkValidate()) {
        const newInfo = {
          userId: this.userInfo.userId,
          userName: this.inputUserName,
          phoneNum: this.inputPhoneNum,
          email: this.inputEmail,
        };

        http.put("/member/", newInfo).then(({ data }) => {
          alert("회원정보수정에 성공했습니다!");
          this.$store.dispatch("setUserInfo", data.data);
          this.$router.push("/user/info");
        });
      } else {
        alert("정보를 다시 확인해주세요.");
      }
    },
  },
};
</script>
<style scoped>
#signUpDiv {
  padding: 30px;
  width: 70%;
  text-align: center;
  margin: 0 auto;
  margin-top: 5%;
}

#signUpDiv h3 {
  margin-bottom: 50px;
}
.inputLabel {
  font-weight: 500;
  text-align: left;
}

.input-div {
  width: 70%;
  margin: 0 auto;
  margin-bottom: 20px;
  text-align: left;
}

.input-div input {
  width: 100%;
}
input {
  height: 35px;
  border: solid 1px gray;
  border-radius: 0.3rem;
}
label {
  margin-bottom: 2px;
  color: rgb(70, 70, 70);
}
button {
  margin-top: 2%;
}
#userInfoDiv {
  text-align: right;
  font-weight: 500;
}

#userInfoDiv {
  display: inline;
  height: 0px;
}

#joinBtn {
  margin-top: 50px;
  width: 30%;
  float: right;
  font-weight: 900;
}
</style>
