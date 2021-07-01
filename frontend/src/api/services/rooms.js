import authHeader, { BASE_URL, HTTP } from "../http";

export default {
    allRooms() {
        return HTTP.get(BASE_URL + "/rooms", { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    create(room) {
        return HTTP.post(BASE_URL + "/rooms", room, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    edit(room) {
        return HTTP.patch(BASE_URL + "/rooms/"+room.id, room, {
            headers: authHeader(),
        }).then((response) => {
            return response.data;
        });
    },
    delete(id) {
        return HTTP.delete(BASE_URL + "/rooms/" + id, {
            headers: authHeader(),
        }).then();
    },

};