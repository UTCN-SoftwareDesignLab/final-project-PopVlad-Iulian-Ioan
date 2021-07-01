<template>
  <v-card>
    <v-card-title>
      Rooms
      <v-spacer></v-spacer>

      <v-btn @click="addRoom">Add Rooms</v-btn>
      <v-btn @click="back" >Back</v-btn>
    </v-card-title>
    <v-data-table
        :headers="headers"
        :items="rooms"
        :search="search"
        @click:row="editRoom"
    ></v-data-table>
    <RoomDialog
        :opened="createDialogVisible"
        :room="selectedItem"
        @refresh="refreshList"
    ></RoomDialog>
  </v-card>
</template>

<script>
import api from "../api";
import RoomDialog from "../components/RoomDialog";
import router from "../router";

export default {
  name: "RoomList",
  components: {RoomDialog: RoomDialog},
  data() {
    return {
      rooms: [],
      search: "",
      headers: [
        {
          text: "capacity",
          align: "start",
          sortable: false,
          value: "capacity",
        },
        { text: "number", value: "number" },
        { text: "hotelId", value: "hotelId" },
        { text: "price", value: "price" },
      ],
      dialogVisible: false,
      createDialogVisible: false,
      selectedItem: {},
    };
  },
  methods: {
    editRoom(room) {
      this.selectedItem = room;
      this.createDialogVisible = true;
    },
    addRoom() {
      this.createDialogVisible = true;
    },
    back(){
      router.push("/preselection")
    },
    async refreshList() {
      this.dialogVisible = false;
      this.createDialogVisible = false;
      this.selectedItem = {};
      this.rooms = await api.rooms.allRooms();
    },
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>