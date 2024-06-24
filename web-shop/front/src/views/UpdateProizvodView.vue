<script>
  import axios from "axios";

  export default {
    name: "UpdateProizvodView",
    data() {
      return {
        korisnik: {},
        proizvod: {},
        kategorija: {},
        kat: false
      };
    },
    mounted() {
      this.getLoggedUser();
      this.getProizvod();
    },
    methods: {
      getLoggedUser() {
        this.korisnik = JSON.parse(localStorage.getItem('korisnik'));
        console.log(this.korisnik);
      },
      getProizvod() {
        const id = this.$route.params.id;
        axios.get(`http://localhost:8081/product/${id}`, {withCredentials: true})
        .then((response) => {
          this.proizvod = response.data;
          this.kategorija = response.data.kategorija;
        })
        .catch((error) => {
          console.log(error);
        })
      },
      updateProizvod() {
        axios.post(`http://localhost:8081/azuriraj-proizvod/${this.proizvod.id}`, this.proizvod, {withCredentials: true})
        .then((response) => {
          alert(response.data);
          this.$router.push('/product/' + this.proizvod.id);
        })
        .catch((error) => {
          alert(error.response.data);
        });
      },
      logout() {
        localStorage.removeItem('korisnik');
        this.$router.push('/');
      },
      goUpdate() {
        this.$router.push('/update_profile');
      }
    }
  }
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
        </ul>
        <div v-if="korisnik === null" class="d-grid gap-2 d-md-flex justify-content-md-end">
          <button id="loginBtn" class="btn btn-primary me-md-2" style="margin-left: 40px" type="button" v-on:click="login()">Login</button>
          <button id="registerBtn" class="btn btn-primary" type="button" v-on:click="register()">Register</button>
        </div>
        <div v-on:click="goUpdate" v-else id="user" class="d-grid gap-2 d-md-flex justify-content-md-end">
          <img id="icon" :src="korisnik.profilnaURL" alt="user icon">
          <p><b>{{korisnik.username}}</b></p>
        </div>
        <a v-if="korisnik !== null" v-on:click="logout()" href="#">Logout</a>
      </div>
    </div>
  </nav>

  <form class="row g-3" @submit.prevent="updateProizvod">
    <h1><b>Ažuriraj proizvod</b></h1>
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
      <input type="text" class="form-control" id="inputProdavac" v-model="korisnik.uloga" disabled>
    </div>
    <div class="col-12">
      <button class="btn btn-primary" id="btn-register">Ažuriraj</button>
    </div>
  </form>
</template>

<style scoped>

.btn-primary {
  background-color: #198754;
  border-color: #198754;
  margin-left: 2%;
}

.btn-primary:hover {
  background-color: #12613e;
}

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