<template>
  <v-dialog
      transition="dialog-bottom-transition"
      max-width="600"
      :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNew ? "Create Hotel" : "Edit Hotel" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="hotel.name" label="name" />
          <v-text-field v-model="hotel.city" label="city" />
          <v-text-field v-model="inDate" label="inDate" />
          <v-text-field v-model="outDate" label="outDate" />
        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{ isNew ? "Create" : "Save" }}
          </v-btn>
          <v-btn @click="bookedRooms">
            bookedRooms
          </v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";
import reports from "../api/services/reports";

export default {
  name: "HotelDialog",
  props: {
    inDate: Object,
    outDate: Object,
    hotel: Object,
    opened: Boolean,
  },
  methods: {
    persist() {
      if (this.isNew) {
        api.hotels
            .create({
              name: this.hotel.name,
              city: this.hotel.city,
            })
            .then(() => this.$emit("refresh"));
      } else {
        api.hotels
            .edit({
              id: this.hotel.id,
              name: this.hotel.name,
              city: this.hotel.city,
            })
            .then(() => this.$emit("refresh"));
      }
    },
    bookedRooms(){
      reports.getBookedRooms(this.hotel,this.inDate,this.outDate)
    },
  },
  computed: {
    isNew: function () {
      return !this.hotel.id;
    },
  },
};
</script>

<style scoped></style>
