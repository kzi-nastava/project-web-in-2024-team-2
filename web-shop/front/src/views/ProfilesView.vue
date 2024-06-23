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
};
</script>

<template>
  <div class="korisnici-container">
    <div><h2 class="Korisnici">Korisnici</h2></div>
    <div class="card-deck">
      <div v-for="korisnik in korisnici" class="card " style="width: 18rem;">
        <img :src="korisnik.profilnaURL" class="card-img-top" alt="slika">
        <div class="card-body">
          <h5 class="card-title"> Korisnik: {{korisnik.ime}} {{korisnik.prezime}} </h5>
          <h5 class="card-title"> Kontakt telefon: {{korisnik.brojTelefona}}</h5>
          <h5 class="card-title"> Uloga: {{korisnik.uloga}}</h5>
          <router-link :to="{ name: 'profil', params: { id: korisnik.id}}"> Vidi jos </router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>