<template>
  <v-card>
    <v-card-title>
      Preselection
      <v-spacer></v-spacer>

      <v-btn @click="toHotel">To Hotels</v-btn>
      <v-btn @click="toRoom">To Room</v-btn>
      <v-btn @click="toBooking">To Bookings</v-btn>
      <v-btn @click="toClient">To Client</v-btn>
    </v-card-title>
  </v-card>
</template>

<script>
import router from "../router"

export default {
  name: "Preselection",
  components: {},
  data() {
    return {
      dialogVisible: false,
      createDialogVisible: false,
      selectedItem: {},
    };
  },
  methods: {
    toHotel() {
      router.push("/hotels")
    },
    toRoom() {
      router.push("/rooms")
    },
    toClient() {
      router.push("/clients")
    },
    toBooking() {
      router.push("/bookings")
    },
    // async refreshList() {
    //   this.dialogVisible = false;
    //   this.createDialogVisible = false;
    //   this.selectedItem = {};
    //   this.bookings = await api.bookings.allBookings();
    // },
  },
  // created() {
  //   this.refreshList();
  // },
};
</script>

<style scoped></style>
