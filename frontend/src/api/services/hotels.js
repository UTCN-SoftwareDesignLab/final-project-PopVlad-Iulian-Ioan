import authHeader, { BASE_URL, HTTP } from "../http";

export default {
    allHotels() {
        return HTTP.get(BASE_URL + "/hotels", { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    create(hotel) {
        return HTTP.post(BASE_URL + "/hotels", hotel, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    edit(hotel) {
        return HTTP.patch(BASE_URL + "/hotels/"+hotel.id, hotel, {
            headers: authHeader(),
        }).then((response) => {
            return response.data;
        });
    },
    delete(id) {
        return HTTP.delete(BASE_URL + "/hotels/" + id, {
            headers: authHeader(),
        }).then();
    },

};