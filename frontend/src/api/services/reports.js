import { BASE_URL, HTTP } from "../http";
import getToken, {saveFile } from "../utils";

export default {
    getBookedRooms(inDate,outDate,hotel) {
        return HTTP.post(BASE_URL + "/reports/" +inDate+"/" +outDate, hotel, {
            responseType: "blob",
            headers: getToken(),
        }).then((response) => {
            saveFile("PDF", response);
            return response.data;
        });
    },
    getInvoice(booking) {
        return HTTP.post(BASE_URL + "/reports" ,booking, {
            responseType: "blob",
            headers: getToken(),
        }).then((response) => {
            saveFile("PDF", response);
            return response.data;
        });
    },
};