<script>
import axios from 'axios';

export default {
  data() {
    return {
      proizvod: null,
      korisnik: {},
    };
  },
  mounted() {
    this.fetchProizvod();
    this.getLoggedUser();
  },
  methods: {
    fetchProizvod() {
      const id = this.$route.params.id;
      axios.get(`http://localhost:8081/product/${id}`, { withCredentials: true })
      .then((response) => {
        this.proizvod = response.data;
        console.log(this.proizvod);
      })
      .catch((error) => {
        console.log(error);
        alert("Ne postoji proizvod sa tim ID-em");
      });
    },
    getLoggedUser() {
      this.korisnik = JSON.parse(localStorage.getItem('korisnik'));
      console.log(this.korisnik);
    },
    login() {
      this.$router.push('/login');
    },
    register() {
      this.$router.push('/register');
    },
    logout() {
      localStorage.removeItem('korisnik');
      this.$router.push('/');
    },
    goUpdate() {
      this.$router.push('/update_profile');
    },
    goUpdateProduct(id) {
      this.$router.push(`/update_product/` + id);
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

  <div v-if="proizvod">
    <h1 class="nazivProizvoda">{{ proizvod.ime }}</h1>
    <img id="proizvodImg" :src="proizvod.profilnaURL" alt="slika proizvoda" class="product-image">
    <p><strong>{{ proizvod.opis }}</strong></p>
    <ul>
      <li><strong>Cena:</strong> {{ proizvod.cena }} RSD</li>
      <li><strong>Kategorija:</strong> {{ proizvod.kategorija.naziv }}</li>
    </ul>
    <a class="btn btn-primary" v-on:click="goUpdateProduct(proizvod.id)">Ažuriraj</a>
  </div>
  <div v-else>
    <h4>Proizvod se učitava ili ne postoji.</h4>
  </div>
</template>

<style scoped>

.btn-primary {
  background-color: #198754;
  border-color: #198754;
  margin-left: 2%;
  margin-bottom: 2%;
}

.btn-primary:hover {
  background-color: #12613e;
}

#proizvodImg {
  width: 40%;
}

#user {
  cursor: pointer;
}

#icon {
  width: 40px;
  height: 40px;
}

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

ul li {
  list-style-type: none;
  padding: 0;
  margin: 0;
}

.product-image {
  max-width: 100%;
  height: auto;
}

.nazivProizvoda {
  padding: 15px;
}
</style>