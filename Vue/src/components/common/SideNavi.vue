<template>
  <div
    id="sideNavi"
    :class="{ showNavi: this.showSide, hideNavi: !this.showSide }"
  >
    <div id="my-sidebar" title="MENU">
      <div id="login">
        <span v-if="userInfo != null && userInfo.userId">
          <img src="../../assets/user_icon.png" width="100px" /><br />
          {{ userInfo.userId }}님 환영합니다 :)
        </span>
        <span v-else>
          <router-link to="" class="link" v-b-modal.loginModal
            >로그인이 필요합니다</router-link
          >
        </span>
      </div>
      <div id="menu">
        <div class="menuItem">
          <p>아파트</p>
          <router-link to="/" class="link">아파트 검색</router-link><br />
          <router-link to="/edu-point/search" class="link"
            >교육지수 순위</router-link
          >
        </div>
        <div class="menuItem" v-if="userInfo != null && userInfo.userId">
          <p>회원정보</p>
          <router-link to="/user/info" class="link">마이페이지</router-link>
        </div>
        <div class="menuItem">
          <p>고객센터</p>
          <router-link to="/post/list" class="link">공지사항</router-link><br />
          <router-link to="/post/list" class="link">QnA</router-link>
        </div>
      </div>
      <div id="footer">
        <span class="copy">&copy;SSAFY</span>
        <span id="logout" v-if="userInfo != null && userInfo.userId">
          <a @click="logout" class="link">로그아웃</a>
        </span>
        <span id="signup" v-else>
          <router-link to="/user/signup" id="signup" class="link"
            >회원가입</router-link
          >
        </span>
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
  </div>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  data() {
    return {
      loginForm: {
        userId: "",
        userPwd: "",
      },
    };
  },

  computed: {
    ...mapGetters(["userInfo", "showSide"]),
  },
  created() {
    if (this.userInfo != null) this.userId = this.userInfo.userId;
    console.log(this.userInfo);
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
    goInfo() {
      alert("준비중");
    },
    login() {
      this.$store.dispatch("login", this.loginForm);
    },
    logout() {
      this.$store.dispatch("logout");
      this.$router.go(this.$router.currentRoute);
    },
  },
};
</script>
<style>
#sideNavi {
  width: 23%;
  height: 100%;
  padding: 10px 0px;
  background-color: #f8f9fa !important;
  position: absolute;
  top: 0;
  box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15);
  transition: 1s;
  overflow: hidden;
  z-index: 2;
}
#login {
  margin: 0 auto;
  margin-top: 10%;
  font-size: 20px;
  text-align: center;
}
#loginModal {
  margin-top: 7%;
}
#menu {
  margin-top: 20%;
  margin-left: 10%;
  margin-right: 10%;
}
#logout {
  margin-left: 35%;
  color: gray;
  font-weight: 300;
}
#signup {
  margin-left: 20%;
  color: gray;
  font-weight: 300;
}
#navBtn {
  font-size: 25px;
  border-radius: 10rem;
  width: 3rem;
  height: 3rem;
  background-color: #faebad;
  position: absolute;
  top: 0;
  right: 0;
}
#my-sidebar {
  display: inline-block;
  width: 100%;
  height: 100%;
}

.b-sidebar > .b-sidebar-header .close {
  display: none;
}
.menuItem {
  font-size: 18px;
  margin-bottom: 20%;
}
.copy {
  margin-top: 20%;
}
.link {
  color: black;
  text-decoration: none;
  font-weight: 500;
  margin: 0;
}
.close {
  border: none;
  background-color: inherit;
}
.showNavi {
  margin-left: 0%;
}
.hideNavi {
  margin-left: -23%;
}
#footer {
  width: 100%;
  position: absolute;
  bottom: 0;
  margin-left: 10%;
}
p {
  margin-bottom: 8px !important;
  color: gray;
}
</style>
