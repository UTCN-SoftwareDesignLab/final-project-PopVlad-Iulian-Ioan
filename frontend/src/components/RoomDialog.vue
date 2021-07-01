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
          <v-text-field v-model="room.capacity" label="capacity" />
          <v-text-field v-model="room.number" label="number" />
          <v-text-field v-model="room.hotelId" label="hotelId" />
          <v-text-field v-model="room.price" label="price" />
        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{ isNew ? "Create" : "Save" }}
          </v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "RoomDialog",
  props: {
    room: Object,
    opened: Boolean,
  },
  methods: {
    persist() {
      if (this.isNew) {
        api.rooms
            .create({
              capacity: this.room.capacity,
              number: this.room.number,
              hotelId: this.room.hotelId,
              price: this.room.price,
            })
            .then(() => this.$emit("refresh"));
      } else {
        api.rooms
            .edit({
              capacity: this.room.capacity,
              number: this.room.number,
              hotelId: this.room.hotelId,
              price: this.room.price,
            })
            .then(() => this.$emit("refresh"));
      }
    },
  },
  computed: {
    isNew: function () {
      return !this.room.id;
    },
  },
};
</script>

<style scoped></style>
