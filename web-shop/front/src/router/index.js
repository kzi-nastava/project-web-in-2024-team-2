import { createRouter, createWebHistory } from 'vue-router'
import GuestUserView from "@/views/GuestUserView.vue";
import ProductView from "@/views/ProductView.vue";
import LoginView from "@/views/LoginView.vue";
import RegisterView from "@/views/RegisterView.vue";
import KupacView from "@/views/KupacView.vue";
import ProdavacView from "@/views/ProdavacView.vue"

const routes = [
  {
    path: '/',
    name: 'home',
    component: GuestUserView,
    meta: {
      title: 'Home',
    }
  },
  {
    path: '/product/:id',
    name: 'product',
    component: ProductView,
    meta: {
      title: 'Product',
    }
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView,
    meta: {
      title: 'Login',
    }
  },
  {
    path: '/register',
    name: 'register',
    component: RegisterView
  },
  {
    path: '/kupac_view',
    name: 'kupacView',
    component: KupacView,
  },
  {
    path: '/prodavac_view',
    name: 'prodavacView',
    component: ProdavacView,
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
