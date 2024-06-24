<script>
import axios from "axios";

export default {
  name: 'AddProizvodView',
  data() {
    return {
      korisnik: {},
      proizvod: {}
    };
  },
  mounted() {
    this.getLoggedUser();
    this.isUserLogged();
  },
  methods: {
    isUserLogged() {
      if (localStorage.getItem('korisnik') == null) {
        this.$router.push('/');
      }
    },
    addProduct() {
      axios.post('http://localhost:8081/dodaj-proizvod', this.proizvod, {withCredentials: true}).then((response) => {
        console.log(response.data);
        this.$router.push('/');
      })
      .catch((error) => {
        console.log(error);
      });
    },
    getLoggedUser() {
      this.korisnik = JSON.parse(localStorage.getItem('korisnik'));
      console.log(this.korisnik);
    },
    logout() {
      localStorage.removeItem('korisnik');
      this.$router.push('/');
    },
    goUpdate() {
      this.$router.push('/update_profile');
    }
  }
};
</script>

<template>
  <nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
      <a class="navbar-brand" href="/">WebShop</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="/">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="/profiles">Profiles</a>
          </li>
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="/add_proizvod">Dodaj proizvod</a>
          </li>
        </ul>
        <div @load="getLoggedUser" class="d-grid gap-2 d-md-flex justify-content-md-end">
          <div v-on:click="goUpdate()" v-if="korisnik != null" id="user" class="d-grid gap-2 d-md-flex justify-content-md-end">
            <img id="icon" :src="korisnik.profilnaURL" alt="user icon">
            <p><b>{{korisnik.username}}</b></p>
          </div>
          <a v-on:click="logout()" href="#">Logout</a>
        </div>
      </div>
    </div>
  </nav>

  <form class="row g-3" @submit.prevent="addProduct">
    <h1><b>Dodaj proizvod</b></h1>
    <div class="col-md-6">
      <label for="inputNaziv" class="form-label"><b>Naziv</b></label>
      <input type="text" class="form-control" id="inputNaziv" v-model="proizvod.naziv">
    </div>
    <div class="col-md-6">
      <label for="inputOpis" class="form-label"><b>Opis</b></label>
      <input type="text" class="form-control" id="inputOpis" v-model="proizvod.opis">
    </div>
    <div class="col-md-6">
      <label for="inputProfilna" class="form-label"><b>Profilna URL</b></label>
      <input type="text" class="form-control" id="inputProfilna" v-model="proizvod.profilnaURL">
    </div>
    <div class="col-md-6">
      <label for="inputCena" class="form-label"><b>Cena</b></label>
      <input type="text" class="form-control" id="inputCena" v-model="proizvod.cena">
    </div>
    <div class="col-12">
      <label for="inputKategorija" class="form-label"><b>Kategorija</b></label>
      <input type="text" class="form-control" id="inputKategorija" v-model="proizvod.nazivKategorije">
    </div>
    <div class="col-md-6">
      <label for="inputTipProdaje" class="form-label"><b>Tip prodaje</b></label>
      <select id="inputTipProdaje" class="form-select" v-model="proizvod.tipProdaje">
        <option selected>Izaberi...</option>
        <option>FIKSNA_CENA</option>
        <option>AUKCIJA</option>
      </select>
    </div>
    <div class="col-md-6">
      <label for="inputProdavac" class="form-label"><b>Prodavac</b></label>
      <input type="text" class="form-control" id="inputProdavac" v-model="korisnik.uloga">
    </div>
    <div class="col-12">
      <button class="btn btn-primary" id="btn-register">Dodaj</button>
    </div>
  </form>
</template>

<style scoped>
#user {
  cursor: pointer;
}

#icon {
  width: 40px;
  height: 40px;
}

.g-3 {
  width: 50%;
  margin: 1% auto auto;
  border-radius: 15px;
  padding: 1%;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
}
</style>