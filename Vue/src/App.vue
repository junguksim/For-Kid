<template>
  <div id="app">
    <side-navi />
    <keep-alive>
      <kakao-map v-if="showMap" />
    </keep-alive>
    <router-view />
    <div v-if="areaDropDownShown"><area-drop-down /></div>
    <div v-if="areaComment"><area-comment /></div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import KakaoMap from "./components/common/KakaoMap.vue";
import SideNavi from "./components/common/SideNavi.vue";
import AreaDropDown from "./components/common/AreaDropDown.vue";
import AreaComment from "./components/apartment/AreaComment.vue";

export default {
  name: "App",
  components: { KakaoMap, SideNavi, AreaDropDown, AreaComment },
  computed: {
    ...mapGetters(["showMap", "areaDropDownShown", "areaComment"]),
  },
};
</script>
<style>
@import url(//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSansNeo.css);
* {
  font-family: "Spoqa Han Sans Neo", "sans-serif";
}

.small-font {
  font-size: 12px;
  padding-left: 15px;
}

.small-medium-font {
  font-size: 15px;
  padding-left: 15px;
}

.gray-font {
  color: #808080;
}
</style>
