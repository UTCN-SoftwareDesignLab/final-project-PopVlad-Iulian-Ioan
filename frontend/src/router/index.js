import Vue from "vue";
import VueRouter from "vue-router";
import UserList from "../views/UserList.vue";
import { auth as store } from "../store/auth.module";
import Login from "../views/Login";
import Preselection from "../views/Preselection";
import BookingList from "../views/BookingList";
import ClientList from "../views/ClientList";
import HotelList from "../views/HotelList";
import RoomList from "../views/RoomList";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Login",
    component: Login,
  },
  {
    path: "/users",
    name: "Users",
    component: UserList,
    beforeEnter: (to, from, next) => {
      if (store.getters.isAdmin) {
        next();
      } else {
        next({ name: "Home" });
      }
    },
  },
  {
    path: "/preselection",
    name: "Preselection",
    component: Preselection,
  },
  {
    path: "/bookings",
    name: "Bookings",
    component: BookingList,
  },
  {
    path: "/clients",
    name: "Clients",
    component: ClientList,
  },
  {
    path: "/hotels",
    name: "Hotels",
    component: HotelList,
  },
  {
    path: "/rooms",
    name: "Rooms",
    component: RoomList,
  },
  {
    path: "/about",
    name: "About",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/About.vue"),
  },
];

const router = new VueRouter({
  routes,
});

export default router;
