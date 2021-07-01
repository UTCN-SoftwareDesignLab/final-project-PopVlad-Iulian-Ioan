<template>
  <v-dialog
      transition="dialog-bottom-transition"
      max-width="600"
      :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNew ? "Create Client" : "Edit Client" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="client.name" label="name" />
          <v-text-field v-model="client.email" label="email" />
          <v-text-field v-model="client.cnp" label="cnp" />
          <v-text-field v-model="client.frequency" label="frequency" />
        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{ isNew ? "Create" : "Save" }}
          </v-btn>
          <v-btn @click="erase">
            delete
          </v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "ClientDialog",
  props: {
    client: Object,
    opened: Boolean,
  },
  methods: {
    persist() {
      if (this.isNew) {
        api.clients
            .create({
              name: this.client.name,
              email: this.client.email,
              cnp: this.client.cnp,
              frequency: this.client.frequency,
            })
            .then(() => this.$emit("refresh"));
      } else {
        api.clients
            .edit({
              id: this.client.id,
              name: this.client.name,
              email: this.client.email,
              cnp: this.client.cnp,
              frequency: this.client.frequency,
            })
            .then(() => this.$emit("refresh"));
      }
    },
    erase(){
      api.clients.delete(this.client.id)
          .then(() => this.$emit("refresh"));
    }
  },
  computed: {
    isNew: function () {
      return !this.client.id;
    },
  },
};
</script>

<style scoped></style>

