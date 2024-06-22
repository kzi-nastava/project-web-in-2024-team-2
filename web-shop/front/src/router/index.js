import { createRouter, createWebHistory } from 'vue-router'
import GuestUserView from "@/views/GuestUserView.vue";
import ProductView from "@/views/ProductView.vue";
import LoginView from "@/views/LoginView.vue";
import RegisterView from "@/views/RegisterView.vue";
import KupacView from "@/views/KupacView.vue";
import ProdavacView from "@/views/ProdavacView.vue"
import ProfileUpdate from "@/views/ProfileUpdate.vue";
import AdministratorRecenzijeView from "@/views/AdministratorRecenzijeView.vue";
import AdministratorPrijaveView from "@/views/AdministratorPrijaveView.vue";

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
    path: '/administrator_view',
    name: 'administratorView',
    component: AdministratorRecenzijeView,
  },
  {
    path: '/update_profile',
    name: 'update',
    component: ProfileUpdate
  },
  {
    path: '/administrator_prijave',
    name: 'administratorPrijave',
    component: AdministratorPrijaveView
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
