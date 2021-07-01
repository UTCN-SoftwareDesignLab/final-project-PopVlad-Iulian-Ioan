import authHeader, { BASE_URL, HTTP } from "../http";

export default {
    allBookings() {
        return HTTP.get(BASE_URL + "/bookings", { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    create(booking) {
        return HTTP.post(BASE_URL + "/bookings", booking, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    edit(booking) {
        return HTTP.patch(BASE_URL + "/bookings/"+booking.id, booking, {
            headers: authHeader(),
        }).then((response) => {
            return response.data;
        });
    },
    delete(id) {
        return HTTP.delete(BASE_URL + "/bookings/" + id, {
            headers: authHeader(),
        }).then();
    },

};