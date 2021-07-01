<template>
  <v-card>
    <v-card-title>
      Hotels
      <v-spacer></v-spacer>

      <v-btn @click="addHotel">Add Hotels</v-btn>
      <v-btn @click="back">Back</v-btn>
    </v-card-title>
    <v-data-table
        :headers="headers"
        :items="hotels"
        :search="search"
        @click:row="editHotel"
    ></v-data-table>
    <HotelDialog
        :opened="createDialogVisible"
        :hotel="selectedItem"
        @refresh="refreshList"
    ></HotelDialog>
  </v-card>
</template>

<script>
import api from "../api";
import HotelDialog from "../components/HotelDialog";
import router from "../router";

export default {
  name: "HotelList",
  components: {HotelDialog},
  data() {
    return {
      hotels: [],
      search: "",
      headers: [
        {
          text: "name",
          align: "start",
          sortable: false,
          value: "name",
        },
        { text: "city", value: "city" },
      ],
      dialogVisible: false,
      createDialogVisible: false,
      selectedItem: {},
    };
  },
  methods: {
    editHotel(hotel) {
      this.selectedItem = hotel;
      this.createDialogVisible = true;
    },
    addHotel() {
      this.createDialogVisible = true;
    },
    back(){
      router.push("/preselection")
    },
    async refreshList() {
      this.dialogVisible = false;
      this.createDialogVisible = false;
      this.selectedItem = {};
      this.hotels = await api.hotels.allHotels();
    },
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>