import { saveAs } from "file-saver";

export default function getToken() {
    let user = JSON.parse(localStorage.getItem("user"));
    if (user) {
        return { Authorization: "Bearer " + user.token };
    }
    return {};
}

export function saveFile(extension, response) {
    if (extension === "PDF") {
        let blob = new Blob([response.data], { type: "application" });
        saveAs(blob, "Invoice.pdf");
    }
}