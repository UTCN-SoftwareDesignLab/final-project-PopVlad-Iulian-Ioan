import authHeader, { BASE_URL, HTTP } from "../http";

export default {
    allClients() {
        return HTTP.get(BASE_URL + "/clients", { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    create(client) {
        return HTTP.post(BASE_URL + "/clients", client, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    edit(client) {
        return HTTP.patch(BASE_URL + "/clients/"+client.id, client, {
            headers: authHeader(),
        }).then((response) => {
            return response.data;
        });
    },
    delete(id) {
        return HTTP.delete(BASE_URL + "/clients/" + id, {
            headers: authHeader(),
        }).then();
    },

};