<template>
  <v-dialog
      transition="dialog-bottom-transition"
      max-width="600"
      :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNew ? "Create Booking" : "Edit Booking" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="booking.roomId" label="roomId" />
          <v-text-field v-model="booking.clientId" label="clientId" />
          <v-text-field v-model="booking.inDate" label="inDate" />
          <v-text-field v-model="booking.outDate" label="outDate" />
        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{ isNew ? "Create" : "Save" }}
          </v-btn>
          <v-btn @click="erase">
            delete
          </v-btn>
          <v-btn @click="invoice">
            invoice
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
  name: "BookingDialog",
  props: {
    booking: Object,
    opened: Boolean,
  },
  methods: {
    persist() {
      if (this.isNew) {
        api.bookings
            .create({
              roomId: this.booking.roomId,
              clientId: this.booking.clientId,
              inDate: this.booking.inDate,
              outDate: this.booking.outDate,
            })
            .then(() => this.$emit("refresh"));
      } else {
        api.bookings
            .edit({
              id: this.booking.id,
              roomId: this.booking.roomId,
              clientId: this.booking.clientId,
              inDate: this.booking.inDate,
              outDate: this.booking.outDate,
            })
            .then(() => this.$emit("refresh"));
      }
    },
    erase(){
      api.bookings.delete(this.booking.id)
          .then(() => this.$emit("refresh"));
    },

    invoice(){
      reports.getInvoice(this.booking)
    }
  },
  computed: {
    isNew: function () {
      return !this.booking.id;
    },
  },
};
</script>

<style scoped></style>
