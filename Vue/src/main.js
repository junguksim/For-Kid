import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import BootstrapVue from "bootstrap-vue";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

Vue.config.productionTip = false;
Vue.use(BootstrapVue);

Vue.filter("convertPrice", function (price) {
  return ((parseInt(price) * 10000) / 100000000).toFixed(1);
});
Vue.filter("convertArea", function (area) {
  return Math.round(parseFloat(area) * 0.3025).toString();
});
new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
