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
}

.card-body {
  text-align: center; /* Centriranje sadržaja unutar kartice */
}

.card-title {
  margin-bottom: 10px; /* Razmak između naslova */
}

.card-img-top {
  max-width: 100%; /* Maksimalna širina slike unutar kartice */
  height: auto; /* Automatska visina slike */
}

.router-link {
  display: inline-block; /* Blokni element za router-link */
  margin-top: 10px; /* Razmak između linka i prethodnog elementa */
  text-decoration: none; /* Bez podvučenosti linka */
  padding: 5px 10px; /* Prostor oko teksta linka */
  background-color: #1e90ff; /* Tamno plava pozadinska boja */
  color: white; /* Bela boja teksta */
  border-radius: 5px; /* Zaobljeni ivičnjaci */
}

.router-link:hover {
  background-color: #0069d9; /* Promena boje pozadine pri hoveru */
}

</style>