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
          <h5 class="card-title"> Osnovne informacije o korisniku: </h5>
          <h5 class="card-title"> Korisnik: {{korisnik.ime}} {{korisnik.prezime}} </h5>
          <h5 class="card-title"> Kontakt telefon: {{korisnik.brojTelefona}}</h5>
          <h5 class="card-title"> Uloga: {{korisnik.uloga}}</h5>
          <router-link :to="{ name: 'profil', params: { id: korisnik.id}}" class="vidi-jos-link"> Prikazi profil </router-link>
        </div>
      </div>
    </div>
  </div>

</template>

<style scoped>
.korisnici-container {
  background-color: #198754;
}

.card-deck {
  display: flex; /* Koristimo fleksibilni prikaz */
  flex-wrap: wrap; /* Omogućavamo omotavanje elemenata */
  gap: 20px; /* Razmak između kartica */
  justify-content: space-around; /* Ravnomerno raspoređivanje kartica */
}


.card {
  flex: 0 1 calc(33.3% - 20px); /* Fleksibilan raspored kartica sa razmakom */
  max-width: calc(33.3% - 20px); /* Maksimalna širina kartice */
  box-sizing: border-box; /* Uključuje granice i padding u izračun dimenzija */
  height: 400px; /* Visina kartice */
  overflow: hidden; /* Skrivanje prekoračenja sadržaja */
  background-color: #f0f0f0;
}

.card-body {
  text-align: center; /* Centriranje sadržaja unutar kartice */
}

.card-title {
  font-family: Arial, sans-serif; /* Promenite font ako želite neki drugi */
  font-size: 0.8em; /* Promenite veličinu fonta po potrebi */
  color: #333; /* Promenite boju teksta */
  text-transform: uppercase; /* Pretvara tekst u velika slova */
  text-align: center; /* Centriranje teksta */
  padding: 10px; /* Dodajte prostor oko teksta */
  background-color: #f0f0f0; /* Promenite pozadinu ako je potrebno */
  border: 1px solid #ccc; /* Dodajte ivicu oko naslova */
  border-radius: 5px; /* Zaoblite ivice */
}

.card-img-top {
  max-width: 100%; /* Maksimalna širina slike unutar kartice */
  height: auto; /* Automatska visina slike */
}


.vidi-jos-link {
  display: inline-block;
  margin-top: 10px;
  text-decoration: none;
  padding: 5px 10px;
  background-color: #198754;
  color: white;
  border: 1px solid #198754; /* Dodajemo plavi okvir */
  border-radius: 5px;
}

.vidi-jos-link:hover {
  background-color: #0069d9;
  border-color: #0069d9; /* Promena boje okvira pri hoveru */
}

</style>