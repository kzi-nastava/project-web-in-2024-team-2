<script>
import axios from 'axios'

export default {
  name: "GuestUserView",
  data() {
    return {
      proizvodi: [],
      query: ''
    };
  },
  mounted() {
    this.getProizvodi();
    this.searchProizvodi();
  },
  methods: {
    getProizvodi() {
      axios.get('http://localhost:8081/all-products', {withCredentials: true})
      .then((response) => {
        this.proizvodi = response.data;
      })
      .catch((error) => {
        console.log(error);
        alert(error.message);
      });
    },
    searchProizvodi() {
      axios.get(`http://localhost:8081/products/search?naziv=${this.query}&opis=${this.query}`, {withCredentials: true})
        .then((response) => {
          this.proizvodi = response.data;
        })
        .catch((error) => {
          console.log(error);
          alert("Ne postoje proizvodi sa tim nazivom!");
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
            <a class="nav-link active" aria-current="page" href="/">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Link</a>
          </li>
        </ul>
        <form class="d-flex" role="search" @submit.prevent="searchProizvodi">
          <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" v-model="query">
          <button class="btn btn-outline-success" v-on:click="searchProizvodi">Search</button>
        </form>
        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
          <button id="loginBtn" class="btn btn-primary me-md-2" style="margin-left: 40px" type="button">Login</button>
          <button id="registerBtn" class="btn btn-primary" type="button">Register</button>
        </div>
      </div>
    </div>
  </nav>

  <div><h2 class="proizvod">Proizvodi</h2></div>
  <div class="card-deck">
    <div v-for="proizvod in proizvodi" class="card " style="width: 18rem;">
      <img :src="proizvod.profilnaURL" class="card-img-top" alt="slika">
      <div class="card-body">
        <h5 class="card-title">{{proizvod.naziv}}</h5>
        <p class="card-text">{{proizvod.opis}}</p>
        <a href="#" class="btn btn-primary">Vidi još</a>
      </div>
    </div>
  </div>

  <footer>
    <p style="user-select: none">&copy; {{ new Date().getFullYear() }} - All rights reserved</p>
  </footer>
</template>

<style scoped>

#loginBtn {
  background-color: #198754;
  border-color: #198754;
}

#loginBtn:hover {
  background-color: #12613e;
}

#registerBtn {
  background-color: #198754;
  border-color: #198754;
}

#registerBtn:hover {
  background-color: #12613e;
}

.card-deck {
  display: flex;
  height: fit-content;
  flex-wrap: wrap;
  align-items: stretch;
}

.card {
  display: inline-block;
  padding: 5px;
  margin: 0.5%;
}

.card-text {
  height: 50px;
}

.card-img-top {
  width: 100%;
}
.btn-primary {
  background-color: #198754;
  border-color: #198754;
}

.btn-primary:hover {
  background-color: #12613e;
}

footer {
  margin-top: 5%;
}
</style>