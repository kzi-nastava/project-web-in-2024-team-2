import { createRouter, createWebHistory } from 'vue-router'
import GuestUserView from "@/views/GuestUserView.vue";
import ProfileUpdate from "@/views/ProfileUpdate.vue";

const routes = [
  {
    path: '/',
    name: 'home',
    component: GuestUserView
  },
  {
    path: '/updateProfile',
    name: 'update',
    component: ProfileUpdate
  }


]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
