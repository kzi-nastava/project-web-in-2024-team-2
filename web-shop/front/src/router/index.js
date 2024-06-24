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
import EditRecenzijaView from "@/views/EditRecenzijaView.vue"
import OceniKupcaView from "@/views/OceniKupcaView.vue";
import OceniProdavcaView from "@/views/OceniProdavcaView.vue";
import ProfileView from "@/views/ProfileView.vue";
import ProfilesView from "@/views/ProfilesView.vue";
import PrijaviProdavcaView from "@/views/PrijaviProdavcaView.vue";
import PrijaviKupcaView from "@/views/PrijaviKupcaView.vue";
import AddProizvodView from "@/views/AddProizvodView.vue"
import UpdateProizvodView from "@/views/UpdateProizvodView.vue";

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
  },
  {
    path: '/administrator_prijave/edit/:id',
    name: 'administratorPrijaveEdit',
    component: EditRecenzijaView
  },
  {
    path: '/profiles',
    name: '/Profiles',
    component: ProfilesView
  },
  {
    path: '/profil/:id',
    name: 'profil',
    component: ProfileView
  },
  {
    path: '/oceni-prodavca/:id',
    name: 'oceni-prodavca',
    component: OceniProdavcaView
  },
  {
    path: '/oceni-kupca/:id',
    name: 'oceni-kupca',
    component: OceniKupcaView
  },
  {
    path: '/prijavi-prodavca/:id',
    name: 'prijavi-prodavca',
    component: PrijaviProdavcaView
  },
  {
    path: '/prijavi-kupca/:id',
    name: 'prijavi-kupca',
    component: PrijaviKupcaView
  },
  {
    path: '/add_product',
    name: 'addProduct',
    component: AddProizvodView
  },
  {
    path: '/update_product/:id',
    name: 'updateProduct',
    component: UpdateProizvodView
  }

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router