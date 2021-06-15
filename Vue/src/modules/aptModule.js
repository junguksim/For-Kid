import axios from "axios";

const disPerLevel = [0, 160, 240, 400, 800, 2000, 4000, 8000, 16000];
const MAX_RADIUS_LEVEL = 8;
export default {
  async getApartmentList(keyword, lat, lng, level) {
    if (level > MAX_RADIUS_LEVEL) return null;
    try {
      const {
        data: {
          data: { aptList, origin },
        },
      } = await axios.get(
        `http://localhost/map/apt/${keyword}/${lat}/${lng}/${disPerLevel[level]}`
      );
      if (!aptList || aptList.length == 0) {
        throw new Error("아파트를 한 번에 찾지 못함. 레벨을 올리면서 재탐색");
      }
      return { aptList, origin };
    } catch (error) {
      let _level = level + 1;
      try {
        while (_level <= MAX_RADIUS_LEVEL) {
          const { data } = await axios.get(
            `http://localhost/map/apt/${keyword}/${lat}/${lng}/${disPerLevel[_level++]}`
          );
          if (data.data != null && data.data.aptList && data.data.aptList.length > 0) {
            console.log(`레벨 ${_level} 에서 아파트 찾음!`);
            const { aptList, origin } = data.data;
            return { aptList, origin };
          }
        }
        throw new Error("주변에 아파트가 전혀 없음.");
      } catch (error) {
        console.error(error);
      }
    }
  },
  async getSimpleApartmentList(keyword, lat, lng, level) {
    if (level > MAX_RADIUS_LEVEL) return null;
    try {
      const {
        data: {
          data: { aptList, origin },
        },
      } = await axios.get(
        `http://localhost/map/apt/${keyword}/${lat}/${lng}/${disPerLevel[level]}`
      );
      if (!aptList || aptList.length == 0) {
        throw new Error("아파트를 찾지 못함");
      }
      return { aptList, origin };
    } catch (error) {
      return null;
    }
  },
  async getAptInfoByArea(guguncode, aptName) {
    try {
      let {
        data: { data },
      } = await axios.get(`http://localhost/map/apt/title/${guguncode}/${aptName}`);
      return data;
    } catch (error) {
      console.error(error);
    }
  },
  async getApartmentDealList(aptNo) {
    try {
      let {
        data: { data },
      } = await axios.get(`http://localhost/map/apt/deal/${aptNo}`);
      return data;
    } catch (error) {
      console.error(error);
    }
  },
  async getSchoolList(guguncode, lat, lng, level) {
    try {
      let {
        data: { data },
      } = await axios.get(
        `http://localhost/map/apt/school/${guguncode}/${lat}/${lng}/${
          disPerLevel[level == undefined ? 3 : level]
        }`
      );
      return data;
    } catch (error) {
      console.error(error);
      return null;
    }
  },
  async getAround(lat, lng, level) {
    try {
      let {
        data: { data },
      } = await axios.get(
        `http://localhost/map/apt/around/${lat}/${lng}/${
          disPerLevel[level == undefined ? 3 : level]
        }`
      );
      return data;
    } catch (error) {
      console.error(error);
      return null;
    }
  },
  async getComment(aptNo) {
    try {
      let {
        data: { data },
      } = await axios.get(`http://localhost/comment/${aptNo}`);
      return data;
    } catch (error) {
      console.error(error);
    }
  },
  async getLatLng(guguncode, dong) {
    try {
      let {
        data: {
          data: {
            houseInfo: { lat, lng },
          },
        },
      } = await axios.get(`http://localhost/map/apt/latlng/${guguncode}/${dong}`);
      return { lat, lng };
    } catch (error) {
      console.error(error);
      return null;
    }
  },
};
