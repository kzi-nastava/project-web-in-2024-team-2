<script>
import axios from 'axios'

export default {
  name: "GuestUserView",
  data() {
    return {
      page: 0,
      size: 5,
      hasNextPage: true,
      proizvodi: [],
      query: '',
      kategorije: [],
      cenaMin: '',
      cenaMax: '',
      naziv: '',
      tipProdaje: ''
    };
  },
  mounted() {
    this.defaultProizvodi();
    this.getProizvodi(this.page);
    this.searchProizvodi();
    this.getKategorije();
    this.filterProizvodi();
    this.isUserLogged();
  },
  methods: {
    isUserLogged() {
      if (localStorage.getItem('korisnik') == null) {
        this.$router.push('/');
      }
      else {
        const korisnik = JSON.parse(localStorage.getItem('korisnik'));
        if (korisnik.uloga === 'KUPAC') {
          this.$router.push('/kupac_view');
        }
        else if (korisnik.uloga === 'PRODAVAC') {
          this.$router.push('/prodavac_view');
        }
        else {
          this.$router.push('/administrator_view');
        }
      }
    },
    defaultProizvodi() {
      this.getProizvodi(this.page);
    },
    getProizvodi(page) {
      axios.get(`http://localhost:8081/all-products`, {
        params: {
          page: page,
          size: this.size,
        }
      }, {withCredentials: true})
      .then((response) => {
        console.log(response.data);
        if (response.status === 204 || response.data.length === 0) {
          this.proizvodi = [];
          this.hasNextPage = false;
        }
        else {
          this.proizvodi = response.data;
          this.page = page;
          this.hasNextPage = response.data.length === this.size;
        }
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
    },
    getKategorije() {
      axios.get('http://localhost:8081/kategorije', {withCredentials: true})
      .then((response) => {
        this.kategorije = response.data;
      })
      .catch((error) => {
        console.log(error);
        alert("Ne postoji kategorija!");
      })
    },
    filterProizvodi() {
      if (this.tipProdaje !== '') {
        axios.get(`http://localhost:8081/products/filter?tipProdaje=${this.tipProdaje}`, {withCredentials: true})
        .then((response) => {
          this.proizvodi = response.data;
        })
        .catch((error) => {
          console.log(error);
          alert("Nema proizvoda!");
        });
      }
      else if (this.cenaMin !== '' && this.cenaMax !== '') {
        axios.get(`http://localhost:8081/products/filter?cenaMin=${this.cenaMin}&cenaMax=${this.cenaMax}`, {withCredentials: true})
        .then((response) => {
          this.proizvodi = response.data;
        })
        .catch((error) => {
          console.log(error);
          alert("Nema proizvoda!");
        });
      }
      else if (this.cenaMin !== '') {
        axios.get(`http://localhost:8081/products/filter?cenaMin=${this.cenaMin}`, {withCredentials: true})
        .then((response) => {
          this.proizvodi = response.data;
        })
        .catch((error) => {
          console.log(error);
          alert("Nema proizvoda!");
        });
      }
      else if (this.cenaMax !== '') {
        axios.get(`http://localhost:8081/products/filter?cenaMax=${this.cenaMax}`, {withCredentials: true})
        .then((response) => {
          this.proizvodi = response.data;
        })
        .catch((error) => {
          console.log(error);
          alert("Nema proizvoda!");
        });
      }
      else if (this.naziv !== '') {
        axios.get(`http://localhost:8081/products/filter?kategorija=${this.naziv}`, {withCredentials: true})
        .then((response) => {
          this.proizvodi = response.data;
        })
        .catch((error) => {
          console.log(error);
          alert("Nema proizvoda!");
        });
      }
    },
    seeMore(id) {
      this.$router.push('/product/' + id);
    },
    login() {
      this.$router.push('/login');
    },
    register() {
      this.$router.push('/register');
    },
    ocistiFiltere() {
      window.location.reload();
      // document.getElementById('filteri').reset();
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
        </ul>
        <form class="d-flex" role="search" @submit.prevent="searchProizvodi">
          <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" v-model="query">
          <button class="btn btn-outline-success">Search</button>
        </form>
        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
          <button id="loginBtn" class="btn btn-primary me-md-2" style="margin-left: 40px" type="button" v-on:click="login()">Login</button>
          <button id="registerBtn" class="btn btn-primary" type="button" v-on:click="register()">Register</button>
        </div>
      </div>
    </div>
  </nav>

  <div class="page">
    <div class="filter">
      <form id="filteri" @submit.prevent="filterProizvodi">
        <div class="mb-4">
          <label for="minPrice" class="form-label">Najmanja cena:</label>
          <input type="number" class="form-control" id="minPrice" name="minPrice" placeholder="Cena od..." v-model="cenaMin">
        </div>

        <div class="mb-4">
          <label for="maxPrice" class="form-label">Najveća cena:</label>
          <input type="number" class="form-control" id="maxPrice" name="maxPrice" placeholder="Cena do..." v-model="cenaMax">
        </div>

        <div class="mb-4">
          <label class="form-label">Kategorije:</label>
          <div v-for="kategorija in kategorije" class="form-check">
            <input class="form-check-input" type="radio" name="category" id="{{kategorija.id}}" :value="kategorija.naziv" v-model="naziv" >
            <label class="form-check-label" for="{{kategorija.naziv}}">{{ kategorija.naziv }}</label>
          </div>
        </div>

        <div class="mb-4">
          <label class="form-label">Način prodaje:</label>
          <div class="form-check">
            <input class="form-check-input" type="radio" name="sellingType" id="fiksnaCena" value="FIKSNA_CENA" v-model="tipProdaje">
            <label class="form-check-label" for="fiksnaCena">Fiksna cena</label>
          </div>
          <div class="form-check">
            <input class="form-check-input" type="radio" name="sellingType" id="aukcija" value="AUKCIJA" v-model="tipProdaje">
            <label class="form-check-label" for="aukcija">Aukcija</label>
          </div>
        </div>
        <button type="submit" class="btn btn-primary">Filter</button>
        <button v-on:click="ocistiFiltere" n type="submit" class="btn btn-primary">Očisti filtere</button>
      </form>
    </div>
    <div class="proizvodi-container">
      <div><h2 class="proizvod">Proizvodi</h2></div>
      <div class="card-deck">
        <div v-for="proizvod in proizvodi" class="card " style="width: 18rem;">
          <img :src="proizvod.profilnaURL" class="card-img-top" alt="slika">
          <div class="card-body">
            <h5 class="card-title">{{proizvod.naziv}}</h5>
            <p class="card-text">{{proizvod.opis}}</p>
            <h5 class="card-text"><b>Cena: {{proizvod.cena}} din.</b></h5>
            <a class="btn btn-primary" v-on:click="seeMore(proizvod.id)">Vidi još</a>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div>
    <button class="btn btn-primary me-md-2" @click="getProizvodi(page - 1)" :disabled="page <= 0">Prethodna</button>
    <button class="btn btn-primary me-md-2" @click="getProizvodi(page + 1)" :disabled="!hasNextPage">Sledeća</button>
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
  margin-left: 2%;
}

.btn-primary:hover {
  background-color: #12613e;
}

.page {
  margin: 1% 0;
  display: flex;
  width: 100%;
}

.filter {
  display: table-column;
  padding: 2%;
  position: fixed;
}

.proizvodi-container {
  margin-left: 25em;
  width: 100%;
  flex-grow: 1;
}

footer {
  margin-top: 5%;
}

</style>