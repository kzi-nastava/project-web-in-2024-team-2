<script>
import axios from 'axios';

export default {
  name : 'ProfilesView',
  data() {
    return {
      korisnici: [],
    };
  },
  mounted() {
    this.getKorisnici();
  },
  methods: {
    getKorisnici() {
      axios.get('http://localhost:8081/profiles', {withCredentials: true})
          .then((response)=> {
            console.log(response.data);
            this.korisnici = response.data;
          })
          .catch((error) => {
            console.log(error);
            alert(error.message);
          });
    },
  }
}

</script>

<template>

  <div class="korisnici-container">
    <div><h2 class="Korisnici">Korisnici</h2></div>
    <div class="card-deck">
      <div v-for="korisnik in korisnici" class="card " style="width: 18rem;">
      <img :src="korisnik.profilnaURL" class="card-img-top" alt="slika">
      <div class="card-body">
      <h5 class="card-title">{{korisnik.ime}} {{korisnik.prezime}} </h5>
      <h5 class="card-title">{{korisnik.opis}}</h5>
      <h5 class="card-title">{{korisnik.brojTelefona}}</h5>
      <h5 class="card-title">{{korisnik.uloga}}</h5>
        <h5 class="card-title">{{korisnik.id}}</h5>
        <router-link :to="{ name: 'profil', params: { id: korisnik.id}}"> Vidi jos </router-link>
      </div>
    </div>
  </div>
  </div>

</template>

<style scoped>
.korisnici-container {
margin: 20px auto;
max-width: 1200px;
padding: 20px;
}

.card {
margin-bottom: 20px;
box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.card-img-top {
max-height: 200px;
object-fit: cover;
}

.card-title {
font-size: 1.25rem;
margin-bottom: 10px;
}

.card-body {
padding: 1.25rem;
}

</style>