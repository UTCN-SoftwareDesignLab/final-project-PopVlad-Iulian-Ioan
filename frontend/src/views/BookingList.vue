<template>
  <v-card>
    <v-card-title>
      Bookings
      <v-spacer></v-spacer>

      <v-btn @click="addBooking">Add Booking</v-btn>
      <v-btn @click="back">Back</v-btn>
    </v-card-title>
    <v-data-table
        :headers="headers"
        :items="bookings"
        :search="search"
        @click:row="editBooking"
    ></v-data-table>
    <BookingDialog
        :opened="createDialogVisible"
        :booking="selectedItem"
        @refresh="refreshList"
    ></BookingDialog>
  </v-card>
</template>

<script>
import api from "../api";
import BookingDialog from "../components/BookingDialog";
import router from "../router";

export default {
  name: "BookingList",
  components: {BookingDialog},
  data() {
    return {
      bookings: [],
      search: "",
      headers: [
        {
          text: "roomId",
          align: "start",
          sortable: false,
          value: "roomId",
        },
        { text: "clientId", value: "clientId" },
        { text: "inDate", value: "inDate" },
        { text: "outDate", value: "outDate" },
      ],
      dialogVisible: false,
      createDialogVisible: false,
      selectedItem: {},
    };
  },
  methods: {
    editBooking(booking) {
      this.selectedItem = booking;
      this.createDialogVisible = true;
    },
    addBooking() {
      this.createDialogVisible = true;
    },
    back(){
      router.push("/preselection")
    },
    async refreshList() {
      this.dialogVisible = false;
      this.createDialogVisible = false;
      this.selectedItem = {};
      this.bookings = await api.bookings.allBookings();
    },
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>
