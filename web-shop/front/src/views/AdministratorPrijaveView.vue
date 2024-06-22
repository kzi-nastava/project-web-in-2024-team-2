<script>
import axios from "axios";

export default {
  name: "AdministratorView",
  data() {
    return {
      korisnik: {},
      prijave: [],
    };
  },
  mounted() {
    this.getLoggedUser();
    this.getPrijave();
  },
  methods: {
    getLoggedUser() {
      this.korisnik = JSON.parse(localStorage.getItem('korisnik'));
      console.log(this.korisnik);
    },
    logout() {
      localStorage.removeItem('korisnik');
      this.$router.push('/');
    },
    getPrijave() {
      axios.get('http://localhost:8081/prijave-profila', {withCredentials: true})
      .then((response) => {
        this.prijave = response.data;
      })
      .catch((error) => {
        console.log(error);
      });
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
            <a class="nav-link active" aria-current="page" href="/administrator_view">Recenzije</a>
          </li>
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="/">Prijave Profila</a>
          </li>
        </ul>
        <div @load="getLoggedUser" class="d-grid gap-2 d-md-flex justify-content-md-end">
          <div v-if="korisnik != null" id="user" class="d-grid gap-2 d-md-flex justify-content-md-end">
            <img id="icon" :src="korisnik.profilnaURL" alt="user icon">
            <p><b>{{korisnik.username}}</b></p>
          </div>
          <a v-on:click="logout()" href="#">Logout</a>
        </div>
      </div>
    </div>
  </nav>

  <section @load="getPrijave" v-for="prijava in prijave" class="p-4 p-md-5 text-center text-lg-start shadow-1-strong rounded">
    <div class="row d-flex justify-content-center">
      <div id="reviews" class="col-md-10">
        <div class="card">
          <div class="card-body m-3">
            <div class="row">
              <div class="col-lg-4 d-flex justify-content-center align-items-center mb-4 mb-lg-0">
                <img :src="prijava.podnosilac.profilnaURL" alt="user icon" id="recenzijaImg" class="rounded-circle img-fluid shadow-1" width="200" height="200" />
              </div>
              <div class="col-lg-8">
                <p class="fw-bold lead mb-2"><strong>{{prijava.podnosilac.ime}} {{prijava.podnosilac.prezime}} ({{prijava.podnosilac.uloga}})</strong></p>
                <p class="text-muted fw-light mb-4">{{recenzija.komentar}}</p>
                <p><b>Razlog prijave:</b> {{prijava.razlogPrijave}}</p>
                <p><b>Datum:</b> {{prijava.datumPodnosenjaPrijave}}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<style scoped>
#reviews {
  cursor: pointer;
}

#user {
  cursor: pointer;
}

#icon {
  width: 40px;
  height: 40px;
}
</style>