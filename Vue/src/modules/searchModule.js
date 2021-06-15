import axios from "axios";
import googleApiKey from "../../config/googleApiKey";
let { key } = googleApiKey;

export default {
  getAutoCompleteList: async (keyword) => {
    try {
      let {
        data: { predictions },
      } = await axios.get(
        `https://maps.googleapis.com/maps/api/place/autocomplete/json?key=${key}&input=${keyword}&components=country:kr`
      );
      if (predictions != undefined && predictions.length > 0) {
        predictions = predictions.map(({ description, place_id }) => {
          return {
            description: description.substring(5),
            place_id,
          };
        });
        return predictions;
      }
      return [];
    } catch (error) {
      console.error(error);
    }
  },
  getLocation: async (placeId) => {
    try {
      let {
        data: {
          result: {
            geometry: { location },
          },
        },
      } = await axios.get(
        `https://maps.googleapis.com/maps/api/place/details/json?key=${key}&place_id=${placeId}`
      );
      console.log(location);
      return location;
    } catch (error) {
      console.error(error);
    }
  },
};
