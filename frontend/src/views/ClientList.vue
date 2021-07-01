<template>
  <v-card>
    <v-card-title>
      Clients
      <v-spacer></v-spacer>

      <v-btn @click="addClient">Add Clients</v-btn>
      <v-btn @click="back">Back</v-btn>
    </v-card-title>
    <v-data-table
        :headers="headers"
        :items="clients"
        :search="search"
        @click:row="editClient"
    ></v-data-table>
    <ClientDialog
        :opened="createDialogVisible"
        :client="selectedItem"
        @refresh="refreshList"
    ></ClientDialog>
  </v-card>
</template>

<script>
import api from "../api";
import ClientDialog from "../components/ClientDialog";
import router from "../router";

export default {
  name: "ClientList",
  components: {ClientDialog},
  data() {
    return {
      clients: [],
      search: "",
      headers: [
        {
          text: "name",
          align: "start",
          sortable: false,
          value: "name",
        },
        { text: "email", value: "email" },
        { text: "cnp", value: "cnp" },
        { text: "frequency", value: "frequency" },
      ],
      dialogVisible: false,
      createDialogVisible: false,
      selectedItem: {},
    };
  },
  methods: {
    editClient(client) {
      this.selectedItem = client;
      this.createDialogVisible = true;
    },
    addClient() {
      this.createDialogVisible = true;
    },
    back(){
      router.push("/preselection")
    },
    async refreshList() {
      this.dialogVisible = false;
      this.createDialogVisible = false;
      this.selectedItem = {};
      this.clients = await api.clients.allClients();
    },
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>
