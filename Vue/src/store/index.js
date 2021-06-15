import Vue from "vue";
import Vuex from "vuex";
import http from "@/util/http-common.js";
import VuexPersistence from "vuex-persist";

Vue.use(Vuex);
const vuexLocal = new VuexPersistence({
  storage: window.localStorage,
});
export default new Vuex.Store({
  plugins: [vuexLocal.plugin],
  state: {
    userInfo: {},
    searchHistory: [],
    searchItemStore: {},
    centerAddress: "",
    aptInfoList: [],
    mapMarkers: [],
    mapInfoWindows: [],
    aptDealInfoList: [],
    searchedApt: {},
    showMap: true,
    showSide: false,
    schoolList: [],
    schoolInfo: {},
    around: {},
    comment: [],
    aptInfoByArea: {},
    areaDropDownShown: false,
    areaComment: false,
    commentList: {},
  },
  getters: {
    userInfo({ userInfo }) {
      return userInfo;
    },
    searchHistory({ searchHistory }) {
      return searchHistory;
    },
    searchItemStore({ searchItemStore }) {
      return searchItemStore;
    },
    centerAddress({ centerAddress }) {
      return centerAddress;
    },
    aptInfoList({ aptInfoList }) {
      return aptInfoList;
    },
    mapMarkers({ mapMarkers }) {
      return mapMarkers;
    },
    mapInfoWindows({ mapInfoWindows }) {
      return mapInfoWindows;
    },
    aptDealInfoList({ aptDealInfoList }) {
      return aptDealInfoList;
    },
    searchedApt({ searchedApt }) {
      return searchedApt;
    },
    showMap({ showMap }) {
      return showMap;
    },
    showSide({ showSide }) {
      return showSide;
    },
    schoolList({ schoolList }) {
      return schoolList;
    },
    schoolInfo({ schoolInfo }) {
      return schoolInfo;
    },
    around({ around }) {
      return around;
    },
    aptInfoByArea({ aptInfoByArea }) {
      return aptInfoByArea;
    },
    areaDropDownShown({ areaDropDownShown }) {
      return areaDropDownShown;
    },
    areaComment({ areaComment }) {
      return areaComment;
    },
    comment({ comment }) {
      return comment;
    },
    commentList({ commentList }) {
      return commentList;
    },
  },
  mutations: {
    LOGIN(state, payload) {
      state.userInfo = payload;
    },
    LOGOUT(state) {
      state.userInfo = {};
    },
    async ADD_SEARCH_HISTORY(state, payload) {
      if (state.searchHistory.length >= 5) {
        state.searchHistory.shift();
      }
      state.searchHistory.push(payload);
    },
    async DELETE_ALL_SEARCH_HISTORY(state) {
      state.searchHistory = [];
    },
    async DELETE_ONE_SEARCH_HISTORY(state, deleteHistory) {
      state.searchHistory.splice(state.searchHistory.indexOf(deleteHistory), 1);
    },
    async SET_SEARCH_ITEM_STORE(state, payload) {
      state.searchItemStore = payload;
    },
    async SET_CENTER_ADDRESS(state, payload) {
      state.centerAddress = payload;
    },
    async SET_APT_INFO_LIST(state, payload) {
      state.aptInfoList = payload;
    },
    async SET_MAP_MARKERS(state, payload) {
      state.mapMarkers = payload;
    },
    async SET_MAP_INFO_WINDOWS(state, payload) {
      state.mapInfoWindows = payload;
    },
    async SET_APT_DEAL_INFO_LIST(state, payload) {
      state.aptDealInfoList = payload;
    },
    async SET_SEARCHED_APT(state, payload) {
      state.searchedApt = payload;
    },
    SET_SHOW_MAP(state, payload) {
      state.showMap = payload;
    },
    SET_SHOW_SIDE(state, payload) {
      state.showSide = payload;
    },
    async SET_SCHOOL_LIST(state, payload) {
      state.schoolList = payload;
    },
    SET_SCHOOL_INFO(state, payload) {
      state.schoolInfo = payload;
    },
    async SET_AROUND(state, payload) {
      state.around = payload;
    },
    async SET_APT_INFO_BY_AREA(state, payload) {
      state.aptInfoByArea = payload;
    },
    SET_AREA_DROP_DOWN_SHOWN(state, payload) {
      state.areaDropDownShown = payload;
    },
    SET_AREA_COMMENT(state, payload) {
      state.areaComment = payload;
    },
    async SET_COMMENT(state, payload) {
      state.comment = payload;
    },
    ADD_COMMENT_LIST(state, payload) {
      const { aptNo, userId, commentContent } = payload;
      console.log("mutation");
      if (!Array.isArray(state["commentList"][aptNo])) {
        state["commentList"][aptNo] = [];
      }
      console.log(state["commentList"][aptNo]);
      state["commentList"][aptNo].push({
        userId,
        commentContent,
      });
    },
    SET_USER_INFO(state, payload) {
      state.userInfo = payload;
    },
  },
  actions: {
    login({ commit }, inputUser) {
      http
        .post("/member/login", inputUser)
        .then(({ data }) => {
          console.log(data);
          commit("LOGIN", data.data);
        })
        .catch(() => {
          alert("ID나 비밀번호를 확인해주세요.");
        });
    },
    logout({ commit }) {
      commit("LOGOUT");
    },
    async addSearchHistory({ commit }, newHistory) {
      await commit("ADD_SEARCH_HISTORY", newHistory);
    },
    async setSearchItemStore({ commit }, payload) {
      await commit("SET_SEARCH_ITEM_STORE", payload);
    },
    async deleteAllSearchHistory({ commit }) {
      await commit("DELETE_ALL_SEARCH_HISTORY");
    },
    async deleteOneSearchHistory({ commit }, deleteHistory) {
      await commit("DELETE_ONE_SEARCH_HISTORY", deleteHistory);
    },
    async setClickedSearchFilter({ commit }, clickedSearchFilter) {
      await commit("SET_CLICKED_SEARCH_FILTER", clickedSearchFilter);
    },
    async setCenterAddress({ commit }, centerAddr) {
      await commit("SET_CENTER_ADDRESS", centerAddr);
    },
    async setAptInfoList({ commit }, aptInfoList) {
      await commit("SET_APT_INFO_LIST", aptInfoList);
    },
    async setMapMarkers({ commit }, mapMarkers) {
      commit("SET_MAP_MARKERS", mapMarkers);
    },
    async setMapInfoWindows({ commit }, mapInfoWindows) {
      await commit("SET_MAP_INFO_WINDOWS", mapInfoWindows);
    },
    async setAptDealInfoList({ commit }, aptDealInfoList) {
      await commit("SET_APT_DEAL_INFO_LIST", aptDealInfoList);
    },
    async setSearchedApt({ commit }, searchedApt) {
      await commit("SET_SEARCHED_APT", searchedApt);
    },
    setShowMap({ commit }, showMap) {
      commit("SET_SHOW_MAP", showMap);
    },
    setShowSide({ commit }, showSide) {
      commit("SET_SHOW_SIDE", showSide);
    },
    async setSchoolList({ commit }, schoolList) {
      await commit("SET_SCHOOL_LIST", schoolList);
    },
    async setSchoolInfo({ commit }, schoolInfo) {
      await commit("SET_SCHOOL_INFO", schoolInfo);
    },
    async setAround({ commit }, around) {
      await commit("SET_AROUND", around);
    },
    async setAptInfoByArea({ commit }, aptInfoByArea) {
      await commit("SET_APT_INFO_BY_AREA", aptInfoByArea);
    },
    setAreaDropDownShown({ commit }, areaDropDownShown) {
      commit("SET_AREA_DROP_DOWN_SHOWN", areaDropDownShown);
    },
    setAreaComment({ commit }, areaComment) {
      commit("SET_AREA_COMMENT", areaComment);
    },
    async setComment({ commit }, comment) {
      await commit("SET_COMMENT", comment);
    },
    addCommentList({ commit }, payload) {
      commit("ADD_COMMENT_LIST", payload);
    },
    setUserInfo({ commit }, userInfo) {
      commit("SET_USER_INFO", userInfo);
    },
  },
  modules: {},
});
