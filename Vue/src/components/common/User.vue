<template>
  <div id="user">
    <p id="logo">
      <router-link to="/" id="home">HappyHouse</router-link>
    </p>
    <div id="userDiv">
      <div v-if="userInfo != null && userInfo.userId">
        <span id="welcome">환영합니다 {{ userInfo.userName }}님!</span>
        <router-link class="link-color" to="/user/info">회원정보</router-link>
        <a class="link-color" @click="logout">로그아웃</a>
      </div>
      <div v-else>
        <router-link class="link-color" to="/user/signup">회원가입</router-link>
        <a class="link-color" v-b-modal.loginModal>로그인</a>
      </div>
    </div>
    <b-modal id="loginModal" @ok="checkValidateLoginForm">
      <h4>로그인</h4>
      <b-form>
        <b-form-group id="input-group-2" label="아이디" label-for="inputId">
          <b-form-input
            id="inputId"
            placeholder="ID"
            v-model="loginForm.userId"
            required
          ></b-form-input>
        </b-form-group>
        <b-form-group id="input-group-2" label="비밀번호" label-for="inputPw">
          <b-form-input
            id="inputPw"
            type="password"
            placeholder="PW"
            v-model="loginForm.userPwd"
            required
          ></b-form-input>
        </b-form-group>
      </b-form>
    </b-modal>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
//import http from "@/util/http-common.js";

export default {
  name: "user",
  data() {
    return {
      loginForm: {
        userId: "",
        userPwd: "",
      },
    };
  },
  computed: {
    ...mapGetters(["userInfo"]),
  },
  created() {
    console.log(this.userInfo);
  },
  beforeMount() {
    this.$store.dispatch("setShowMap", false);
    this.$store.dispatch("setShowSide", true);
    this.$store.dispatch("setAreaDropDownShown", false);
  },
  methods: {
    checkValidateLoginForm() {
      // if (this.loginForm.userId.length > 0 && this.loginForm.userPwd.length > 0) {
      //   this.login();
      // } else {
      //   console.log(this.loginForm);
      //   alert("ID와 비밀번호가 올바르게 입력되었는지 확인해주세요.");
      // }
      this.login();
    },
    login() {
      this.$store.dispatch("login", this.loginForm);
    },
    logout() {
      this.$store.dispatch("logout");
      this.moveToMain();
    },
    moveToMain() {
      this.$router.push("/");
    },
  },
};
</script>

<style>
#user {
  margin: 20px;
  display: flex;
  justify-content: space-between;
}

#logo {
  display: inline-block;
}

#home {
  font-size: 45px;
  text-decoration: none;
  color: rgb(52, 184, 52);
  font-weight: 600;
}

#userDiv {
  display: inline-block;
  padding: 40px;
  position: absolute;
  top: 10px;
  right: 20px;
}

#userDiv button {
  margin: 0px 15px;
}

#loginModal {
  text-align: center;
}
#welcome {
  color: gray;
  font-size: 17px;
  font-weight: 400;
  margin-top: 10px;
  margin-right: 15px;
}
.link-color {
  color: black;
  font-size: 17px;
  font-weight: 500;
  text-decoration: none;
  margin-top: 10px;
}
button {
  background-color: white;
  border: 0px;
}
</style>
